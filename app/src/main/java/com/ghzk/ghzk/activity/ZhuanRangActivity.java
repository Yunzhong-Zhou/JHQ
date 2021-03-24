package com.ghzk.ghzk.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghzk.ghzk.R;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.model.ZhuanRangModel;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
import com.ghzk.ghzk.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by zyz on 2018/5/31.
 * 转让
 */

public class ZhuanRangActivity extends BaseActivity {
    String id = "";
    ImageView imageView1,iv_yongjiu,iv_linshi;
    TextView textView1, textView3, textView4, tv_confirm;
    EditText editText1,editText1_1, editText2, editText3;
    ZhuanRangModel model;
    String mobile = "",mobile1="", trade_password = "", code = "";
    int source_type = 3;//2.临时转让 3.永久转让

    private TimeCount time = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanrang);
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Request("?token=" + localUserInfo.getToken()
                        + "&id=" + id);
            }

            @Override
            public void onLoadmore() {

            }
        });
        imageView1 = findViewByID_My(R.id.imageView1);
        iv_yongjiu = findViewByID_My(R.id.iv_yongjiu);
        iv_linshi = findViewByID_My(R.id.iv_linshi);

        textView1 = findViewByID_My(R.id.textView1);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView4.setText(getString(R.string.takecash_h31)
                + "+" + localUserInfo.getMobile_State_Code() + " "
                + localUserInfo.getPhonenumber());
        textView4.setVisibility(View.GONE);
        time = new TimeCount(60000, 1000, textView3);//构造CountDownTimer对象

        tv_confirm = findViewByID_My(R.id.tv_confirm);
        editText1 = findViewByID_My(R.id.editText1);
        editText1_1 = findViewByID_My(R.id.editText1_1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        requestServer();
    }
    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        Request("?token=" + localUserInfo.getToken()
                + "&id=" + id);
    }
    private void Request(String string) {
        OkHttpClientManager.getAsyn(ZhuanRangActivity.this, URLs.Transfer + string, new OkHttpClientManager.ResultCallback<ZhuanRangModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                onHttpResult();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(final ZhuanRangModel response) {
                onHttpResult();
                MyLogger.i(">>>>>>>>>对方信息" + response);
                if (response != null) {
                    model = response;
                    if (response.getTrade_password().equals("")) {
                        showToast(getString(R.string.address_h25), getString(R.string.password_h5),
                                getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(ZhuanRangActivity.this, SetTransactionPasswordActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        finish();
                                    }
                                }
                        );
                    }
//                    hk = response.getHk();

                    textView1.setText(getString(R.string.scavengingpayment_h12)+response.getGoods_sn());

                }
            }
        });
    }

    private void RequestScavengingPayment(Map<String, String> params) {
        OkHttpClientManager.postAsyn(ZhuanRangActivity.this, URLs.Transfer, params, new OkHttpClientManager.ResultCallback<ZhuanRangModel>() {
            @Override
            public void onError(final Request request, String info, Exception e) {
                hideProgress();
                tv_confirm.setClickable(true);
                if (!info.equals("")) {
                    if (info.contains(getString(R.string.password_h1))) {
                        showToast(getString(R.string.password_h2),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(ZhuanRangActivity.this, SetTransactionPasswordActivity.class, false);
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
                                        CommonUtil.gotoActivity(ZhuanRangActivity.this, AddressManagementActivity.class, false);
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
                Request("?token=" + localUserInfo.getToken()
                        + "&id=" + id);
            }

            @Override
            public void onResponse(ZhuanRangModel response) {
                tv_confirm.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>转账" + response);

                Request("?token=" + localUserInfo.getToken()
                        + "&id=" + id);
                if (response==null){
                    showToast(getString(R.string.scavengingpayment_h26),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                }else {
                    if (response.getCode() == 1) {
                        showToast(getString(R.string.password_h2),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(ZhuanRangActivity.this, SetTransactionPasswordActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        finish();
                                    }
                                });
                    } else if (response.getCode() == 2) {
                        showToast(getString(R.string.password_h4),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(ZhuanRangActivity.this, AddressManagementActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        finish();
                                    }
                                });
                    }
                }

            }
        }, true);

    }

    private boolean match() {
        mobile = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            myToast(getString(R.string.scavengingpayment_h14));
            return false;
        }
        mobile1 = editText1_1.getText().toString().trim();
        if (TextUtils.isEmpty(mobile1)) {
            myToast(getString(R.string.scavengingpayment_h15));
            return false;
        }
        if (!mobile.equals(mobile1)){
            myToast(getString(R.string.scavengingpayment_h19));
            return false;
        }
        trade_password = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(trade_password)) {
            myToast(getString(R.string.settransactionpassword_h7));
            return false;
        }
        code = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            myToast(getString(R.string.settransactionpassword_h5));
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_yongjiu:
                //永久转让
                source_type = 3;
                iv_yongjiu.setVisibility(View.VISIBLE);
                iv_linshi.setVisibility(View.INVISIBLE);
                break;
            case R.id.rl_linshi:
                //临时转让
                source_type = 2;
                iv_yongjiu.setVisibility(View.INVISIBLE);
                iv_linshi.setVisibility(View.VISIBLE);
                break;
            case R.id.textView3:
                //验证码
                showProgress(true, getString(R.string.app_sendcode_hint1));
                textView3.setClickable(false);
                HashMap<String, String> params = new HashMap<>();
                params.put("mobile", localUserInfo.getPhonenumber());
                params.put("type", "9");
                params.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                RequestCode(params, textView3, textView4);//获取验证码
                break;
            case R.id.tv_confirm:
                //确认转账
                if (match()) {
                    showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params1 = new HashMap<>();
                    params1.put("token", localUserInfo.getToken());
                    params1.put("id", id + "");
                    params1.put("code", code);
                    params1.put("source_type",source_type+"");
                    params1.put("mobile", mobile);
                    params1.put("trade_password", trade_password);
//                    params1.put("hk", model.getHk());
                    RequestScavengingPayment(params1);
                }
                break;
        }
    }

    //获取验证码
    private void RequestCode(HashMap<String, String> params, final TextView tv, TextView tv_phone) {
        OkHttpClientManager.postAsyn(ZhuanRangActivity.this, URLs.Code, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                tv.setClickable(true);
                tv_phone.setVisibility(View.GONE);
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>验证码" + response);
                tv.setClickable(true);
                tv_phone.setVisibility(View.VISIBLE);
                time.start();//开始计时
                myToast(getString(R.string.app_sendcode_hint));
            }
        }, true);

    }

    public void onHttpResult() {
        hideProgress();
        // 刷新完成
//        pullRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.scavengingpayment_h27));
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
