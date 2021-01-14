package com.fone.fone.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cy.dialog.BaseDialog;
import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.USDTOrderInfoModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mr.Z on 2020/11/17.
 * USDT交易信息
 */
public class USDTOrderInfoActivity extends BaseActivity {
    String id = "";
    LinearLayout linearLayout1,ll_nickname,ll_phonenum;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9,
            textView10, textView11, textView12, textView13, textView14, tv_cancel, tv_confirm;
    USDTOrderInfoModel model;


    TimeCount time1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usdtorderinfo);
        mImmersionBar.reset()
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                String string = "?token=" + localUserInfo.getToken()
                        + "&id=" + id;
                Request(string);
            }

            @Override
            public void onLoadmore() {
            }
        });

        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        ll_nickname = findViewByID_My(R.id.ll_nickname);
        ll_phonenum = findViewByID_My(R.id.ll_phonenum);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        textView8 = findViewByID_My(R.id.textView8);
        textView9 = findViewByID_My(R.id.textView9);
        textView10 = findViewByID_My(R.id.textView10);
        textView11 = findViewByID_My(R.id.textView11);
        textView12 = findViewByID_My(R.id.textView12);
        textView13 = findViewByID_My(R.id.textView13);
        textView14 = findViewByID_My(R.id.textView14);
        tv_cancel = findViewByID_My(R.id.tv_cancel);
        tv_confirm = findViewByID_My(R.id.tv_confirm);


    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        requestServer();
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.USDTJiaoYiDetail + string, new OkHttpClientManager.ResultCallback<USDTOrderInfoModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(USDTOrderInfoModel response) {
                showContentPage();
                hideProgress();
                model = response;
                textView1.setText(response.getUsdt_deal().getStatus_title());//状态
                if (response.getUsdt_deal().getMember_type().equals("buy")) {//是买家
                    textView4.setText(getString(R.string.qianbao_h179));//买家or卖家
                } else {
                    textView4.setText(getString(R.string.qianbao_h180));//买家or卖家
                }
                textView5.setText(response.getUsdt_deal().getCny_money() + "CNY");//总价
                textView6.setText(response.getUsdt_deal().getUsdt_cny_price() + "CNY");//单价
                textView7.setText(response.getUsdt_deal().getMoney());//数量
                textView8.setText(response.getUsdt_deal().getSn());//订单号
                textView9.setText(response.getUsdt_deal().getMatching_at());//创建时间
                textView10.setText(response.getUsdt_deal().getOpposite_member_nickname());//卖家昵称
                textView11.setText(response.getUsdt_deal().getOpposite_member_mobile());//手机号
                textView12.setText(response.getUsdt_deal().getSell_member_bank_card_proceeds_name());//姓名
                textView13.setText(response.getUsdt_deal().getSell_member_bank_card_account());//银行卡号
                textView14.setText(response.getUsdt_deal().getSell_member_bank_title());//银行


                linearLayout1.setVisibility(View.GONE);
                tv_cancel.setVisibility(View.VISIBLE);
                tv_confirm.setVisibility(View.VISIBLE);

                switch (response.getUsdt_deal().getStatus()) {
                    case 2:
                        //待付款
                        if (response.getUsdt_deal().getMember_type().equals("buy")) {//是买家
                            tv_cancel.setText(getString(R.string.qianbao_h171));
                            tv_confirm.setText(getString(R.string.qianbao_h172));
                        } else {
                            tv_cancel.setVisibility(View.GONE);
                            tv_confirm.setVisibility(View.GONE);
                        }
                        //倒计时
                        linearLayout1.setVisibility(View.VISIBLE);
                        textView2.setText(getString(R.string.qianbao_h163));
                        if (response.getCount_down() > 0) {
                            showTime();
                        }
                        ll_nickname.setVisibility(View.VISIBLE);
                        ll_phonenum.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        //已付款
                        if (response.getUsdt_deal().getMember_type().equals("sell")) {//是卖家
                            tv_cancel.setText(getString(R.string.qianbao_h174));
                            tv_confirm.setText(getString(R.string.qianbao_h173));
                        } else {
                            tv_cancel.setVisibility(View.GONE);
                            tv_confirm.setVisibility(View.GONE);
                        }
                        //倒计时
                        linearLayout1.setVisibility(View.VISIBLE);
                        textView2.setText(getString(R.string.qianbao_h197));
                        if (response.getCount_down() > 0) {
                            showTime();
                        }
                        ll_nickname.setVisibility(View.VISIBLE);
                        ll_phonenum.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        //申述中
                        tv_cancel.setText(getString(R.string.qianbao_h196));
                        tv_confirm.setVisibility(View.GONE);
                        ll_nickname.setVisibility(View.VISIBLE);
                        ll_phonenum.setVisibility(View.VISIBLE);
                        break;
                    default:
                        tv_cancel.setVisibility(View.GONE);
                        tv_confirm.setVisibility(View.GONE);

                        ll_nickname.setVisibility(View.GONE);
                        ll_phonenum.setVisibility(View.GONE);
                        break;
                }

            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        String string = "?token=" + localUserInfo.getToken()
                + "&id=" + id;
        Request(string);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.textView8:
                //订单号
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", textView8.getText().toString().trim());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                myToast(getString(R.string.recharge_h34));
                break;
            case R.id.textView12:
                //姓名
                //获取剪贴板管理器：
                ClipboardManager cm1 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData1 = ClipData.newPlainText("Label", textView12.getText().toString().trim());
                // 将ClipData内容放到系统剪贴板里。
                cm1.setPrimaryClip(mClipData1);
                myToast(getString(R.string.recharge_h34));
                break;
            case R.id.textView13:
                //卡号
                //获取剪贴板管理器：
                ClipboardManager cm2 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData2 = ClipData.newPlainText("Label", textView13.getText().toString().trim());
                // 将ClipData内容放到系统剪贴板里。
                cm2.setPrimaryClip(mClipData2);
                myToast(getString(R.string.recharge_h34));
                break;
            case R.id.textView14:
                //银行
                //获取剪贴板管理器：
                ClipboardManager cm3 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData3 = ClipData.newPlainText("Label", textView14.getText().toString().trim());
                // 将ClipData内容放到系统剪贴板里。
                cm3.setPrimaryClip(mClipData3);
                myToast(getString(R.string.recharge_h34));
                break;
            case R.id.tv_cancel:
                //取消、申述
                if (model.getUsdt_deal().getStatus() == 2 && model.getUsdt_deal().getMember_type().equals("buy")) {
                    //取消
                    dialog = new BaseDialog(USDTOrderInfoActivity.this);
                    dialog.contentView(R.layout.dialog_usdtorderinfo)
                            .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT))
                            .animType(BaseDialog.AnimInType.CENTER)
                            .canceledOnTouchOutside(true)
                            .dimAmount(0.8f)
                            .show();
                    TextView tv1 = dialog.findViewById(R.id.textView1);
                    tv1.setText(getString(R.string.qianbao_h176));//标题
                    TextView tv2 = dialog.findViewById(R.id.textView2);
                    tv2.setText(getString(R.string.qianbao_h177));//详情
                    TextView tv3 = dialog.findViewById(R.id.textView3);
                    tv3.setText(getString(R.string.qianbao_h178));//提示
                    TextView tv4 = dialog.findViewById(R.id.textView4);
                    tv4.setText(getString(R.string.qianbao_h175));//确认文案

                    dialog.findViewById(R.id.textView5).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           /* HashMap<String, String> params = new HashMap<>();
                            params.put("id", id);
                            RequestCode(params);*/
                            dialog.dismiss();
                            String string = "?id=" + id
                                    + "&token=" + localUserInfo.getToken();
                            RequestCancel(string);
                        }
                    });
                    dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                } else if (model.getUsdt_deal().getStatus() == 3 && model.getUsdt_deal().getMember_type().equals("sell")) {
                    //申述
                    dialog = new BaseDialog(USDTOrderInfoActivity.this);
                    dialog.contentView(R.layout.dialog_usdtorderinfo)
                            .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT))
                            .animType(BaseDialog.AnimInType.CENTER)
                            .canceledOnTouchOutside(true)
                            .dimAmount(0.8f)
                            .show();
                    TextView tv1 = dialog.findViewById(R.id.textView1);
                    tv1.setText(getString(R.string.qianbao_h186));//标题
                    TextView tv2 = dialog.findViewById(R.id.textView2);
                    tv2.setText(getString(R.string.qianbao_h187));//详情
                    TextView tv3 = dialog.findViewById(R.id.textView3);
                    tv3.setText(getString(R.string.qianbao_h188));//提示
                    TextView tv4 = dialog.findViewById(R.id.textView4);
                    tv4.setText(getString(R.string.qianbao_h189));//确认文案

                    dialog.findViewById(R.id.textView5).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           /* HashMap<String, String> params = new HashMap<>();
                            params.put("id", id);
                            RequestCode(params);*/
                            dialog.dismiss();
                            String string = "?id=" + id
                                    + "&token=" + localUserInfo.getToken();
                            RequestShenShu(string);
                        }
                    });
                    dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                } else {
                    //联系客服
                    CommonUtil.gotoActivity(USDTOrderInfoActivity.this, OnlineServiceActivity.class, false);
                }
                break;
            case R.id.tv_confirm:
                //确认
                if (model.getUsdt_deal().getStatus() == 3 && model.getUsdt_deal().getMember_type().equals("sell")) {
                    //确认收款
                    dialog = new BaseDialog(USDTOrderInfoActivity.this);
                    dialog.contentView(R.layout.dialog_usdtorderinfo)
                            .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT))
                            .animType(BaseDialog.AnimInType.CENTER)
                            .canceledOnTouchOutside(true)
                            .dimAmount(0.8f)
                            .show();
                    TextView tv1 = dialog.findViewById(R.id.textView1);
                    tv1.setText(getString(R.string.qianbao_h190));//标题
                    TextView tv2 = dialog.findViewById(R.id.textView2);
                    tv2.setText(getString(R.string.qianbao_h191));//详情
                    TextView tv3 = dialog.findViewById(R.id.textView3);
                    tv3.setText(getString(R.string.qianbao_h192));//提示
                    TextView tv4 = dialog.findViewById(R.id.textView4);
                    tv4.setText(getString(R.string.qianbao_h193));//确认文案

                    dialog.findViewById(R.id.textView5).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           /* HashMap<String, String> params = new HashMap<>();
                            params.put("id", id);
                            RequestCode(params);*/
                            dialog.dismiss();
                            String string = "?id=" + id
                                    + "&token=" + localUserInfo.getToken();
                            RequestShouKuan(string);
                        }
                    });
                    dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                } else {
                    //确认付款
                    dialog = new BaseDialog(USDTOrderInfoActivity.this);
                    dialog.contentView(R.layout.dialog_usdtorderinfo)
                            .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT))
                            .animType(BaseDialog.AnimInType.CENTER)
                            .canceledOnTouchOutside(true)
                            .dimAmount(0.8f)
                            .show();
                    TextView tv1 = dialog.findViewById(R.id.textView1);
                    tv1.setText(getString(R.string.qianbao_h182));//标题
                    TextView tv2 = dialog.findViewById(R.id.textView2);
                    tv2.setText(getString(R.string.qianbao_h183));//详情
                    TextView tv3 = dialog.findViewById(R.id.textView3);
                    tv3.setText(getString(R.string.qianbao_h184));//提示
                    TextView tv4 = dialog.findViewById(R.id.textView4);
                    tv4.setText(getString(R.string.qianbao_h185));//确认文案

                    dialog.findViewById(R.id.textView5).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           /* HashMap<String, String> params = new HashMap<>();
                            params.put("id", id);
                            RequestCode(params);*/
                            dialog.dismiss();
                            String string = "?id=" + id
                                    + "&token=" + localUserInfo.getToken();
                            RequestConfirmPay(string);
                        }
                    });
                    dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
                break;
        }
    }

    private void RequestCancel(String string) {
        OkHttpClientManager.getAsyn(USDTOrderInfoActivity.this, URLs.USDTCancel + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>USDT购买取消" + response);
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    String info = jObj.getString("msg");
                    myToast(info);
                    requestServer();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//
            }
        }, false);

    }

    private void RequestShenShu(String string) {
        OkHttpClientManager.getAsyn(USDTOrderInfoActivity.this, URLs.ShenShu + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>确认付款" + response);
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    String info = jObj.getString("msg");
                    myToast(info);
                    requestServer();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//
            }
        }, false);

    }

    private void RequestConfirmPay(String string) {
        OkHttpClientManager.getAsyn(USDTOrderInfoActivity.this, URLs.ConfirmPay + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>确认付款" + response);
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    String info = jObj.getString("msg");
                    myToast(info);
                    requestServer();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//
            }
        }, false);

    }

    private void RequestShouKuan(String string) {
        OkHttpClientManager.getAsyn(USDTOrderInfoActivity.this, URLs.ShouKuan + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>确认收款" + response);
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    String info = jObj.getString("msg");
                    myToast(info);
                    requestServer();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//
            }
        }, false);

    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }

    private void showTime() {
        MyLogger.i(">>>>>>" + (model.getCount_down() * 1000));
        if (time1 != null) {
            time1.cancel();
        }
        time1 = new TimeCount(model.getCount_down() * 1000, 1000, textView3);//构造CountDownTimer对象
        time1.start();//开始计时
    }

    class TimeCount extends CountDownTimer {
        TextView textView;

        public TimeCount(long millisInFuture, long countDownInterval, TextView textView) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
            this.textView = textView;
        }

        @Override
        public void onFinish() {//计时完毕时触发
            textView.setText("00:00");

        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
//            textView.setText(CommonUtil.timedate3(millisUntilFinished) + "s");//秒计时
            textView.setText(CommonUtil.timedate4(millisUntilFinished, USDTOrderInfoActivity.this));//时分秒倒计时
        }

    }

}
