package com.fone.fone.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.SetAddressModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyz on 2017/9/4.
 * 钱包地址
 */

public class SetAddressActivity extends BaseActivity {
    int type = 1;
    String address = "";
    //    String qk = "";
    EditText editText1, textView2, editText2, editText3;
    TextView textView1, textView3, textView4, textView5;
    private TimeCount time;
    String code = "", addr = "", password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setaddress);
    }

    @Override
    protected void initView() {
        type = getIntent().getIntExtra("type", 1);

        editText1 = findViewByID_My(R.id.editText1);
//        address = getIntent().getStringExtra("address");
//        editText1.setText(address);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);

        textView2.setText("+" + localUserInfo.getMobile_State_Code() + "  " + localUserInfo.getPhonenumber());
        if (type == 1) {
            //USDT
            titleView.setTitle(getString(R.string.address_h1));
            textView1.setText(getString(R.string.address_h2));
            editText1.setHint(getString(R.string.address_h3));
            textView5.setText(getString(R.string.address_h19));
        } else {
            //OFC
            titleView.setTitle(getString(R.string.qianbao_h48));
            textView1.setText(getString(R.string.qianbao_h45));
            editText1.setHint(getString(R.string.qianbao_h49));
            textView5.setText(getString(R.string.qianbao_h51));
        }

    }

    @Override
    protected void initData() {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
//        qk = getIntent().getStringExtra("qk");

        request("?token=" + localUserInfo.getToken());
    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(SetAddressActivity.this, URLs.AddressManage + string,
                new OkHttpClientManager.ResultCallback<SetAddressModel>() {
                    @Override
                    public void onError(Request request, String info, Exception e) {
                        hideProgress();
                        if (!info.equals("")) {
                            myToast(info);
                        }
                    }

                    @Override
                    public void onResponse(SetAddressModel response) {
                        MyLogger.i(">>>>>>>>>地址" + response);
                        hideProgress();
                        if (response.getTrade_password().equals("")) {
                            showToast(getString(R.string.address_h25),
                                    getString(R.string.password_h5), getString(R.string.password_h6),
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            CommonUtil.gotoActivity(SetAddressActivity.this, SetTransactionPasswordActivity.class, false);
                                        }
                                    }, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog.dismiss();
                                            finish();
                                        }
                                    });
                        }

                        if (type == 1) {
                            editText1.setText(response.getUsdt_wallet_addr());
                        } else {
                            editText1.setText(response.getFil_wallet_addr());
                        }


                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView3:
                //验证码
                /*String string = "?mobile=" + localUserInfo.getPhonenumber() +
                        "&type=" + "6" +
                        "&mobile_state_code=" + localUserInfo.getMobile_State_Code();*/
                SetAddressActivity.this.showProgress(true, getString(R.string.app_sendcode_hint1));
                textView3.setClickable(false);
                HashMap<String, String> params1 = new HashMap<>();
                params1.put("mobile", localUserInfo.getPhonenumber());
                params1.put("type", "6");
                params1.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                RequestCode(params1);//获取验证码
                break;
            case R.id.textView4:
                //提交
                if (match()) {
                    textView4.setClickable(false);
                    showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
//                    params.put("qk", qk);
                    params.put("wallet_addr", addr);
                    params.put("money_type", type + "");
//                    params.put("xrp_wallet_addr", xrp_wallet_addr);
                    params.put("code", code);//手机验证码
                    params.put("token", localUserInfo.getToken());
                    params.put("trade_password", password);//交易密码（不能小于6位数）
                    RequestSetWalletAddress(params);//设置钱包地址
                }
                break;
        }
    }

    private void RequestCode(Map<String, String> params) {
        OkHttpClientManager.postAsyn(SetAddressActivity.this, URLs.Code, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                textView3.setClickable(true);
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                time.start();
                MyLogger.i(">>>>>>>>>发送验证码" + response);
                myToast(getString(R.string.app_sendcode_hint));
            }
        }, true);

    }

    //钱包地址设置
    private void RequestSetWalletAddress(Map<String, String> params) {
        OkHttpClientManager.postAsyn(SetAddressActivity.this, URLs.AddressManage, params, new OkHttpClientManager.ResultCallback<SetAddressModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                textView4.setClickable(true);
                hideProgress();
                if (!info.equals("")) {
                    if (info.contains(getString(R.string.password_h1))) {
                        showToast(getString(R.string.password_h2),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(SetAddressActivity.this, SetTransactionPasswordActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    } else {
                        showToast(info);
                    }
                }
            }

            @Override
            public void onResponse(SetAddressModel response) {
                textView4.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>地址设置" + response);

                if (response == null) {
                    if (type == 1) {
                        myToast(getString(R.string.address_h11));
                    } else {
                        myToast(getString(R.string.qianbao_h50));
                    }
                    finish();
                } else if (response != null || response.getCode() == 1) {
                    showToast(getString(R.string.password_h2),
                            getString(R.string.password_h5), getString(R.string.password_h6),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    CommonUtil.gotoActivity(SetAddressActivity.this, SetTransactionPasswordActivity.class, false);
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });
                }

                /*JSONObject jObj;
                try {
                    jObj = new JSONObject(response);

                    JSONObject jObj1 = jObj.getJSONObject("data");
                    int code = jObj1.getInt("code");
                    if (code ==1){
                        showToast(getString(R.string.address_h25), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                CommonUtil.gotoActivity(SetAddressActivity.this, SetTransactionPasswordActivity.class, false);
                            }
                        });

                    }else if (code ==2){
                        showToast(getString(R.string.address_h26), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                CommonUtil.gotoActivity(SetAddressActivity.this, SetAddressActivity.class, false);
                            }
                        });

                    }else {
                        myToast(jObj.getString("msg"));
                        finish();
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/

            }
        }, true);
    }

    public static boolean isGoodJson(String json) {

        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            return false;
        }
    }

    private boolean match() {
        addr = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(addr)) {
            if (type == 1) {
                myToast(getString(R.string.address_h3));
            } else {
                myToast(getString(R.string.qianbao_h49));
            }
            return false;
        }
//        eth_wallet_addr = editText2.getText().toString().trim();
        /*if (TextUtils.isEmpty(password1)) {
            myToast(getString(R.string.settransactionpassword_pwd));
            return false;
        }*/
//        xrp_wallet_addr = editText3.getText().toString().trim();
        /*if (TextUtils.isEmpty(password2)) {
            myToast(getString(R.string.settransactionpassword_pwd1));
            return false;
        }*/
        code = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            myToast(getString(R.string.address_h7));
            return false;
        }
        password = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            myToast(getString(R.string.address_h9));
            return false;
        }
        return true;
    }

    @Override
    protected void updateView() {

    }

    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            textView3.setText(getString(R.string.app_reacquirecode));
            textView3.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            textView3.setClickable(false);
            textView3.setText(millisUntilFinished / 1000 + getString(R.string.app_codethen));
        }
    }
}
