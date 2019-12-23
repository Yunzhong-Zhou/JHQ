package com.ofc.ofc.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.AvailableAmountModel;
import com.ofc.ofc.model.TakeCashModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by zyz on 2017/9/4.
 * 提币
 */

public class TakeCashActivity extends BaseActivity {
    LinearLayout linearLayout_addr;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9;
    EditText editText1, editText2, editText3;

    String money = "", password = "", code = "";
    AvailableAmountModel model;

    String input_money = "";
    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takecash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                String string = "?token=" + localUserInfo.getToken();
                RequestAvailableAmount(string);//获取可用币数
            }

            @Override
            public void onLoadmore() {

            }
        });

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        textView8 = findViewByID_My(R.id.textView8);
        time = new TimeCount(60000, 1000, textView8);//构造CountDownTimer对象
        textView9 = findViewByID_My(R.id.textView9);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        //输入监听
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editText1.getText().toString().trim().equals("")) {
                    input_money = editText1.getText().toString().trim();
                    MyLogger.i(">>>>>输入币数>>>>>" + input_money);
                    //手续费 = 输入币数 * 手续费率 /100
                    double service_money = Double.valueOf(model.getWithdrawal_service_charge());
                    MyLogger.i(">>>>>手续费>>>>>" + service_money);
                    //实际到账 =  (输入币数 - 手续费)
                    double real_money = (Double.valueOf(input_money) - service_money);
                    MyLogger.i(">>>>>实际到账>>>>>" + real_money);

                    textView6.setText("¥ " + String.format("%.2f", real_money));//实际到账

                } else {
                    textView6.setText("0");//实际到账
                }
            }
        });

//        LinearLayout linearLayout = findViewByID_My(R.id.linearLayout);
        /*LinearLayout.LayoutParams sp_params = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        sp_params.height = CommonUtil.getScreenHeight(getActivity()) / 4;
        linearLayout.setLayoutParams(sp_params);*/


        //动态设置linearLayout的高度为屏幕高度的1/4
        linearLayout_addr = findViewByID_My(R.id.linearLayout_addr);
        ViewGroup.LayoutParams lp = linearLayout_addr.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(TakeCashActivity.this) / 4;


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView9:
                if (match()) {
                    textView9.setClickable(false);
                    showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("code", code);
                    params.put("trade_password", password);//交易密码（不能小于6位数）
                    params.put("input_money", money);//提现金额
                    params.put("token", localUserInfo.getToken());
                    params.put("hk", model.getHk());
                    RequestTakeCash(params);//提现
                }
                break;
            case R.id.textView1:
                CommonUtil.gotoActivity(TakeCashActivity.this, SetAddressActivity.class);
                break;
            case R.id.textView8:
                    /*String string = "?mobile=" + localUserInfo.getPhonenumber() +
                            "&type=" + "5" +
                            "&mobile_state_code=" + localUserInfo.getMobile_State_Code();*/
                TakeCashActivity.this.showProgress(true, getString(R.string.app_sendcode_hint1));
                textView8.setClickable(false);
                HashMap<String, String> params = new HashMap<>();
                params.put("mobile", localUserInfo.getPhonenumber());
                params.put("type", "20");
                params.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                RequestCode(params);//获取验证码
                break;
        }
    }

    //可用余额
    private void RequestAvailableAmount(String string) {
        OkHttpClientManager.getAsyn(TakeCashActivity.this, URLs.TakeCash + string, new OkHttpClientManager.ResultCallback<AvailableAmountModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(AvailableAmountModel response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>可用余额" + response);

                model = response;
                if (model.getUsdt_wallet_addr() != null && !model.getUsdt_wallet_addr().equals("")) {
                    textView3.setVisibility(View.VISIBLE);
                    textView1.setVisibility(View.GONE);
                    textView3.setText(response.getUsdt_wallet_addr());//地址
                } else {
                    textView3.setVisibility(View.GONE);
                    textView1.setVisibility(View.VISIBLE);
                }
                textView4.setText(getString(R.string.takecash_h3) + response.getCommon_usable_money());//可用余额
                editText1.setHint(getString(R.string.takecash_h8)
                        + "(" + model.getMin_withdrawal_money() + "-" +
                        model.getMax_withdrawal_money() + ")");//请输入提币个数

                textView5.setText(getString(R.string.takecash_h14) + response.getWithdrawal_service_charge()
                        + getString(R.string.recharge_h32));//手续费
                textView7.setText(localUserInfo.getPhonenumber());//手机号码
            }
        });
    }

    //提现
    private void RequestTakeCash(Map<String, String> params) {
        OkHttpClientManager.postAsyn(TakeCashActivity.this, URLs.TakeCash, params, new OkHttpClientManager.ResultCallback<TakeCashModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                textView9.setClickable(true);
                hideProgress();
                if (!info.equals("")) {
                    if (info.contains(getString(R.string.password_h1))) {
                        showToast(getString(R.string.password_h2),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(TakeCashActivity.this, SetTransactionPasswordActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    } else if (info.contains(getString(R.string.password_h3))) {
                        showToast(getString(R.string.password_h4),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(TakeCashActivity.this, SetAddressActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    } /*else if (info.contains(getString(R.string.password_h7))) {
                        showToast(getString(R.string.password_h8),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(TakeCashActivity.this, SetAddressActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    } */else {
                        showToast(info);
                    }
                }
                requestServer();
            }

            @Override
            public void onResponse(TakeCashModel response) {
                textView9.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>提现" + response);
                myToast(getString(R.string.takecash_h13));
                Bundle bundle = new Bundle();
                bundle.putString("id", response.getId());
                CommonUtil.gotoActivityWithData(TakeCashActivity.this, TakeCashDetailActivity.class, bundle, true);
            }
        }, true);
    }

    private void RequestCode(Map<String, String> params) {
        OkHttpClientManager.postAsyn(TakeCashActivity.this, URLs.Code, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                textView8.setClickable(true);
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

    private boolean match() {
        money = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(money)) {
            myToast(getString(R.string.takecash_h8));
            return false;
        }
        password = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            myToast(getString(R.string.takecash_h10));
            return false;
        }
        code = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            myToast(getString(R.string.login_h12));
            return false;
        }
        return true;
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        String string = "?token=" + localUserInfo.getToken();
        RequestAvailableAmount(string);//获取可用币数
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.takecash_h1));
    }

    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        TextView tv;

        public TimeCount(long millisInFuture, long countDownInterval, TextView tv) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
            this.tv = tv;
        }

        @Override
        public void onFinish() {//计时完毕时触发
            tv.setText(getString(R.string.app_reacquirecode));
            tv.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            tv.setClickable(false);
            tv.setText(millisUntilFinished / 1000 + getString(R.string.app_codethen));
        }
    }
}
