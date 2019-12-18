package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.model.ScavengingPaymentModel;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.CommonUtil;
import com.cfo.chocoin.utils.MyLogger;
import com.cy.dialog.BaseDialog;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

import static com.cfo.chocoin.net.OkHttpClientManager.IMGHOST;


/**
 * Created by zyz on 2018/5/31.
 * 扫码付款
 */

public class ScavengingPaymentActivity extends BaseActivity {
    String to_member_id = "";
    ImageView imageView1;
    TextView textView1, textView2, textView3, textView4;
    EditText editText1, editText2;
    ScavengingPaymentModel model;
    String money = "", password = "";

    private TimeCount time = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scavengingpayment);
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Request("?token=" + localUserInfo.getToken()
                        + "&to_member_id=" + to_member_id);
            }

            @Override
            public void onLoadmore() {

            }
        });
        imageView1 = findViewByID_My(R.id.imageView1);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
    }

    @Override
    protected void initData() {
        to_member_id = getIntent().getStringExtra("id");

        showProgress(true, getString(R.string.app_loading2));
        Request("?token=" + localUserInfo.getToken()
                + "&to_member_id=" + to_member_id);

    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(ScavengingPaymentActivity.this, URLs.Transfer + string, new OkHttpClientManager.ResultCallback<ScavengingPaymentModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                onHttpResult();
                if (!info.equals("")) {
                    showToast(info);

                }
            }

            @Override
            public void onResponse(final ScavengingPaymentModel response) {
                onHttpResult();
                MyLogger.i(">>>>>>>>>对方信息" + response);
                if (response != null) {
                    model = response;
//                    hk = response.getHk();
                    textView2.setText(response.getCommon_usable_money());//可用余额
                    textView3.setText(response.getTransfer_service_charge() + "");//手续费
                    //昵称
                    textView1.setText(response.getNickname());
                    //头像
                    if (!response.getHead().equals(""))
                        Glide.with(ScavengingPaymentActivity.this)
                                .load(IMGHOST + response.getHead())
                                .centerCrop()
                                .into(imageView1);//加载图片
                    else
                        imageView1.setImageResource(R.mipmap.headimg);


                }
            }
        });
    }

    private void RequestScavengingPayment(Map<String, String> params) {
        OkHttpClientManager.postAsyn(ScavengingPaymentActivity.this, URLs.Transfer, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(final Request request, String info, Exception e) {
                hideProgress();
                textView4.setClickable(true);
                if (!info.equals("")) {
                    if (info.contains(getString(R.string.password_h1))) {
                        showToast(getString(R.string.password_h2),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(ScavengingPaymentActivity.this, SetTransactionPasswordActivity.class, false);
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
                                        CommonUtil.gotoActivity(ScavengingPaymentActivity.this, SetAddressActivity.class, false);
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
                        + "&to_member_id=" + to_member_id);
            }

            @Override
            public void onResponse(String response) {
                textView4.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>转账" + response);
                showToast(getString(R.string.scavengingpayment_h5), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
//                        finish();
                        CommonUtil.gotoActivity(ScavengingPaymentActivity.this, TransferRecordActivity.class, true);
                    }
                });

            }
        }, true);

    }

    private boolean match() {
        money = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(money)) {
            myToast(getString(R.string.scavengingpayment_h4));
            return false;
        }
        /*password = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            myToast(getString(R.string.scavengingpayment_h6));
            return false;
        }*/
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView3:
                //选择卖出账户
//                showPopupWindow1(textView3);
                break;
            case R.id.textView4:
                //确认转账
                if (match()) {
                    //弹出弹窗
                    dialog = new BaseDialog(ScavengingPaymentActivity.this);
                    dialog.contentView(R.layout.dialog_scavengingpayment)
                            .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT))
                            .animType(BaseDialog.AnimInType.CENTER)
                            .canceledOnTouchOutside(true)
                            .dimAmount(0.8f)
                            .show();
                    TextView tv1 = dialog.findViewById(R.id.textView1);
                    tv1.setText(money);
                    final TextView tv2 = dialog.findViewById(R.id.textView2);
                    final TextView tv3 = dialog.findViewById(R.id.textView3);
                    tv2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //发送验证码
                            if (time != null) {
                                time.cancel();
                            }
                            time = new TimeCount(60000, 1000, tv2);//构造CountDownTimer对象

                            ScavengingPaymentActivity.this.showProgress(true, getString(R.string.app_sendcode_hint1));
                            tv2.setClickable(false);
                                /*String string = "?mobile=" + phonenum +
                                        "&type=" + "10";//类型*/
                            tv3.setText(getString(R.string.scavengingpayment_h12) + localUserInfo.getPhonenumber());

                            HashMap<String, String> params = new HashMap<>();
                            params.put("mobile", localUserInfo.getPhonenumber());
                            params.put("type", "21");
                            params.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                            RequestCode(params, tv2, tv3);//获取验证码

                        }
                    });
                    final EditText editText1 = dialog.findViewById(R.id.editText1);
                    final EditText editText2 = dialog.findViewById(R.id.editText2);
                    dialog.findViewById(R.id.textView4).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!editText1.getText().toString().trim().equals("")) {
                                if (!editText2.getText().toString().trim().equals("")) {
                                    CommonUtil.hideSoftKeyboard_fragment(v, ScavengingPaymentActivity.this);
                                    dialog.dismiss();

                                    textView4.setClickable(false);
                                    showProgress(true, getString(R.string.app_loading1));
                                    HashMap<String, String> params = new HashMap<>();
                                    params.put("token", localUserInfo.getToken());
                                    params.put("to_member_id", to_member_id + "");
                                    params.put("code", editText2.getText().toString().trim());
                                    params.put("money", money);
                                    params.put("trade_password", editText1.getText().toString().trim());
                                    params.put("hk", model.getHk());
                                    RequestScavengingPayment(params);
                                } else {
                                    myToast(getString(R.string.login_h12));
                                }
                            } else {
                                myToast(getString(R.string.scavengingpayment_h6));
                            }
                        }
                    });
                    dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                } else {

                }
                break;
        }
    }

    //获取验证码
    private void RequestCode(HashMap<String, String> params, final TextView tv, final TextView tv3) {
        OkHttpClientManager.postAsyn(ScavengingPaymentActivity.this, URLs.Code, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                tv.setClickable(true);
                tv3.setVisibility(View.GONE);
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>验证码" + response);
                tv.setClickable(true);
                tv3.setVisibility(View.VISIBLE);
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
        titleView.setTitle(getString(R.string.zxing_h12));
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
