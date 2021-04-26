package com.ghzk.ghzk.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.cy.dialog.BaseDialog;
import com.ghzk.ghzk.R;
import com.ghzk.ghzk.activity.MachineDetailActivity;
import com.ghzk.ghzk.activity.MainActivity;
import com.ghzk.ghzk.activity.PayDetailActivity;
import com.ghzk.ghzk.base.BaseFragment;
import com.ghzk.ghzk.model.Fragment3BuyModel;
import com.ghzk.ghzk.model.Fragment3Model;
import com.ghzk.ghzk.model.PayModel;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
import com.ghzk.ghzk.utils.MyLogger;
import com.ghzk.ghzk.utils.alipay.PayResult;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Mr.Z on 2016/1/6.
 * 拼团
 */
public class Fragment3 extends BaseFragment {
    String resultCode = "";
    String id = "";
    Fragment3Model model;
    int num = 1, buy_type = 2, payType = -1, operation_type = 1;
    boolean isSelcet = true;

    TextView textView;

    private static final int SDK_PAY_FLAG = 1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    MyLogger.i("payResult" + payResult.toString());
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        MyLogger.i("支付成功" + payResult);
                        showToast(getString(R.string.app_pay_true));

                        Bundle bundle1 = new Bundle();
                        bundle1.putString("id", id);
                        CommonUtil.gotoActivityWithData(getActivity(), MachineDetailActivity.class, bundle1, false);

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        MyLogger.i("支付失败" + payResult);
                        showToast(getString(R.string.app_pay_false));
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        MyLogger.i(">>>>>>>>onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        MyLogger.i(">>>>>>>>onResume");
        if (MainActivity.item == 1) {
            requestServer();

           /* resultCode = getActivity().getIntent().getStringExtra("errCord");
            MyLogger.i(">>>>>>>" + resultCode);
            if (resultCode != null && !resultCode.equals("")) {
                Bundle bundle1 = new Bundle();
                bundle1.putString("id", id);
                CommonUtil.gotoActivityWithData(getActivity(), MachineDetailActivity.class, bundle1, false);

                getActivity().getIntent().removeExtra("code");
            }*/
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        MyLogger.i(">>>>>>>>onHiddenChanged>>>" + hidden);

        /*if (MainActivity.item == 2) {
            requestServer();
        }*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        MyLogger.i(">>>>>>>>getUserVisibleHint()>>>" + getUserVisibleHint());
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 1) {
                    requestServer();
                }
            }
        }
    }

    @Override
    protected void initView(View view) {
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                request("?token=" + localUserInfo.getToken());
            }

            @Override
            public void onLoadmore() {

            }
        });

        textView = findViewByID_My(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model != null) {
                    dialog.contentView(R.layout.dialog_fragment3_pay)
                            .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT))
                            .animType(BaseDialog.AnimInType.BOTTOM)
                            .canceledOnTouchOutside(true)
                            .gravity(Gravity.BOTTOM)
                            .dimAmount(0.8f)
                            .show();
                    //初始化
                    num = 1;
                    payType = -1;
                    buy_type = 2;
                    operation_type = 1;

                    TextView textView1 = dialog.findViewById(R.id.textView1);
                    TextView textView2 = dialog.findViewById(R.id.textView2);
                    TextView textView3 = dialog.findViewById(R.id.textView3);
                    TextView textView4 = dialog.findViewById(R.id.textView4);
                    TextView textView5 = dialog.findViewById(R.id.textView5);

                    ImageView imageView1 = dialog.findViewById(R.id.imageView1);
                    ImageView imageView2 = dialog.findViewById(R.id.imageView2);
                    SeekBar seekBar = dialog.findViewById(R.id.seekBar);
                    RelativeLayout rl_daiyunying = dialog.findViewById(R.id.rl_daiyunying);
                    RelativeLayout rl_ziyunying = dialog.findViewById(R.id.rl_ziyunying);
                    RelativeLayout rl_huigou = dialog.findViewById(R.id.rl_huigou);
                    RelativeLayout rl_huigou_not = dialog.findViewById(R.id.rl_huigou_not);

                    ImageView iv_daiyunying = dialog.findViewById(R.id.iv_daiyunying);
                    ImageView iv_ziyunying = dialog.findViewById(R.id.iv_ziyunying);
                    ImageView iv_huigou = dialog.findViewById(R.id.iv_huigou);
                    ImageView iv_huigou_not = dialog.findViewById(R.id.iv_huigou_not);

                    LinearLayout ll_lingqian = dialog.findViewById(R.id.ll_lingqian);
                    LinearLayout ll_zhifubao = dialog.findViewById(R.id.ll_zhifubao);
                    LinearLayout ll_weixin = dialog.findViewById(R.id.ll_weixin);
                    LinearLayout ll_zhuanzhang = dialog.findViewById(R.id.ll_zhuanzhang);
                    ImageView iv_lingqian = dialog.findViewById(R.id.iv_lingqian);
                    ImageView iv_zhifubao = dialog.findViewById(R.id.iv_zhifubao);
                    ImageView iv_weixin = dialog.findViewById(R.id.iv_weixin);
                    ImageView iv_zhuanzhang = dialog.findViewById(R.id.iv_zhuanzhang);
                    TextView tv_lingqian = dialog.findViewById(R.id.tv_lingqian);
                    textView1.setText(getString(R.string.fragment2_h7)
                            + "（" + "¥" + model.getGoods().getCan_buy_back_price() + "）");
                    textView3.setText(getString(R.string.fragment3_h58) + "¥" + model.getGoods().getCan_buy_back_price()
                            + "/" + getString(R.string.app_tai));

                    tv_lingqian.setText(getString(R.string.fragment5_h87)
                            + "（" + getString(R.string.fragment5_h88) + "¥" + model.getUsable_money() + "）");
                    if (Double.valueOf(model.getUsable_money()) > 0) {
                        payType = -1;
                        isSelcet = true;
                        iv_lingqian.setImageResource(R.mipmap.ic_kuang_true);
                        ll_lingqian.setVisibility(View.VISIBLE);

                    } else {
                        payType = -1;
                        isSelcet = false;
                        iv_lingqian.setImageResource(R.mipmap.ic_kuang_false);
                        ll_lingqian.setVisibility(View.GONE);
                    }

                    calculate(seekBar, textView2, textView4, textView5);

                    //减号
                    imageView1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (num > 1) {
                                num--;
                            }
                            calculate(seekBar, textView2, textView4, textView5);
                        }
                    });
                    //加号
                    imageView2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (model != null) {
                                if (num < Double.valueOf(model.getGoods().getCan_buy_back_price())) {
                                    num++;
                                }
                                calculate(seekBar, textView2, textView4, textView5);
                            }
                        }
                    });
                    //拖动条
                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if (progress < 1) {
                                num = 1;
                            } else {
                                num = progress;
                            }
                            calculate(seekBar, textView2, textView4, textView5);
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });
                    //代运营
                    rl_daiyunying.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            operation_type = 1;
                            iv_daiyunying.setVisibility(View.VISIBLE);
                            iv_ziyunying.setVisibility(View.INVISIBLE);
                        }
                    });
                    //自运营
                    rl_ziyunying.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            operation_type = 2;
                            iv_daiyunying.setVisibility(View.INVISIBLE);
                            iv_ziyunying.setVisibility(View.VISIBLE);
                        }
                    });

                    if (Double.valueOf(model.getGoods().getCannot_buy_back_price()) > 0 && Double.valueOf(model.getGoods().getCan_buy_back_price()) > 0 ) {
                        rl_huigou.setVisibility(View.VISIBLE);
                        rl_huigou_not.setVisibility(View.VISIBLE);
                        buy_type = 2;
                        iv_huigou.setVisibility(View.VISIBLE);
                        iv_huigou_not.setVisibility(View.INVISIBLE);
                    } else {
                        if (Double.valueOf(model.getGoods().getCan_buy_back_price()) > 0) {//能回购
                            rl_huigou.setVisibility(View.VISIBLE);
                            rl_huigou_not.setVisibility(View.GONE);
                            buy_type = 2;
                            iv_huigou.setVisibility(View.VISIBLE);
                            iv_huigou_not.setVisibility(View.INVISIBLE);

                            textView1.setText(getString(R.string.fragment2_h7)
                                    + "（" + "¥" + model.getGoods().getCan_buy_back_price() + "）");
                            textView3.setText(getString(R.string.fragment3_h58) + "¥" + model.getGoods().getCan_buy_back_price()
                                    + "/" + getString(R.string.app_tai));
                            calculate(seekBar, textView2, textView4, textView5);
                        }
                        if (Double.valueOf(model.getGoods().getCannot_buy_back_price()) > 0) {//不能回购
                            rl_huigou.setVisibility(View.GONE);
                            rl_huigou_not.setVisibility(View.VISIBLE);
                            buy_type = 1;
                            iv_huigou.setVisibility(View.INVISIBLE);
                            iv_huigou_not.setVisibility(View.VISIBLE);

                            textView1.setText(getString(R.string.fragment2_h7)
                                    + "（" + "¥" + model.getGoods().getCannot_buy_back_price() + "）");
                            textView3.setText(getString(R.string.fragment3_h58) + "¥" + model.getGoods().getCannot_buy_back_price()
                                    + "/" + getString(R.string.app_tai));
                            calculate(seekBar, textView2, textView4, textView5);
                        }
                    }

                    //能回购
                    rl_huigou.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            buy_type = 2;
                            iv_huigou.setVisibility(View.VISIBLE);
                            iv_huigou_not.setVisibility(View.INVISIBLE);

                            textView1.setText(getString(R.string.fragment2_h7)
                                    + "（" + "¥" + model.getGoods().getCan_buy_back_price() + "）");
                            textView3.setText(getString(R.string.fragment3_h58) + "¥" + model.getGoods().getCan_buy_back_price()
                                    + "/" + getString(R.string.app_tai));
                            calculate(seekBar, textView2, textView4, textView5);
                        }
                    });
                    //不能回购
                    rl_huigou_not.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            buy_type = 1;
                            iv_huigou.setVisibility(View.INVISIBLE);
                            iv_huigou_not.setVisibility(View.VISIBLE);

                            textView1.setText(getString(R.string.fragment2_h7)
                                    + "（" + "¥" + model.getGoods().getCannot_buy_back_price() + "）");
                            textView3.setText(getString(R.string.fragment3_h58) + "¥" + model.getGoods().getCannot_buy_back_price()
                                    + "/" + getString(R.string.app_tai));
                            calculate(seekBar, textView2, textView4, textView5);
                        }
                    });

                    //零钱
                    ll_lingqian.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            payType = -1;
                            if (Double.valueOf(model.getUsable_money()) > 0) {
                                isSelcet = !isSelcet;
                                if (isSelcet) {
                                    iv_lingqian.setImageResource(R.mipmap.ic_kuang_true);
                                } else {
                                    iv_lingqian.setImageResource(R.mipmap.ic_kuang_false);
                                }

                                calculate(seekBar, textView2, textView4, textView5);
                            }

//                            iv_zhifubao.setImageResource(R.drawable.yuanxingbiankuang_baise);
//                            iv_weixin.setImageResource(R.drawable.yuanxingbiankuang_baise);
//                            iv_zhuanzhang.setImageResource(R.drawable.yuanxingbiankuang_baise);

                        }
                    });
                    //支付宝
                    ll_zhifubao.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            payType = 2;
//                            iv_lingqian.setImageResource(R.drawable.yuanxingbiankuang_baise);
                            iv_zhifubao.setImageResource(R.mipmap.ic_gouxuan);
                            iv_weixin.setImageResource(R.drawable.yuanxingbiankuang_baise);
                            iv_zhuanzhang.setImageResource(R.drawable.yuanxingbiankuang_baise);
                        }
                    });
                    //微信
                    ll_weixin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            payType = 1;
//                            iv_lingqian.setImageResource(R.drawable.yuanxingbiankuang_baise);
                            iv_zhifubao.setImageResource(R.drawable.yuanxingbiankuang_baise);
                            iv_weixin.setImageResource(R.mipmap.ic_gouxuan);
                            iv_zhuanzhang.setImageResource(R.drawable.yuanxingbiankuang_baise);
                        }
                    });
                    //转账
                    ll_zhuanzhang.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            payType = 3;
//                            iv_lingqian.setImageResource(R.drawable.yuanxingbiankuang_baise);
                            iv_zhifubao.setImageResource(R.drawable.yuanxingbiankuang_baise);
                            iv_weixin.setImageResource(R.drawable.yuanxingbiankuang_baise);
                            iv_zhuanzhang.setImageResource(R.mipmap.ic_gouxuan);
                        }
                    });


                    TextView tv_confirm = dialog.findViewById(R.id.tv_confirm);
                    tv_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (payType) {
                                case -1:
                                    if (isSelcet) {
                                        dialog.dismiss();
                                        showProgress(true, getString(R.string.app_loading1));
                                        HashMap<String, String> params = new HashMap<>();
                                        params.put("goods_id", model.getGoods().getId());
                                        params.put("buy_type", buy_type + "");
                                        params.put("operation_type", operation_type + "");
                                        params.put("pay_type", payType + "");
                                        params.put("num", num + "");
                                        params.put("token", localUserInfo.getToken());
                                        if (isSelcet) {
                                            params.put("is_wallet", "2");
                                        } else {
                                            params.put("is_wallet", "1");
                                        }
//                            params.put("hk", model.getHk());
                                        RequestBuy(params);
                                    } else {
                                        myToast(getString(R.string.fragment5_h94));
                                    }
                                    break;
                                case 1:
                                case 2:
                                case 3:
                                    dialog.dismiss();
                                    showProgress(true, getString(R.string.app_loading1));
                                    HashMap<String, String> params = new HashMap<>();
                                    params.put("goods_id", model.getGoods().getId());
                                    params.put("buy_type", buy_type + "");
                                    params.put("operation_type", operation_type + "");
                                    params.put("pay_type", payType + "");
                                    params.put("num", num + "");
                                    params.put("token", localUserInfo.getToken());
                                    if (isSelcet) {
                                        params.put("is_wallet", "2");
                                    } else {
                                        params.put("is_wallet", "1");
                                    }
//                            params.put("hk", model.getHk());
                                    RequestBuy(params);
                                    break;
                            }

                        }
                    });
                }

            }
        });

    }

    //计算
    private void calculate(SeekBar seekBar, TextView textView2, TextView textView4, TextView textView5) {
        seekBar.setProgress(num);
        textView2.setText(num + "");

        if (model != null) {
            if (buy_type == 2) {
                if (isSelcet && Double.valueOf(model.getUsable_money()) > 0) {
                    textView5.setVisibility(View.VISIBLE);
                    textView5.setText("（" + getString(R.string.fragment5_h93) + "￥" + model.getUsable_money() + "）");
                    textView4.setText(getString(R.string.fragment5_h91) + "¥" + String.format("%.2f",
                            num * Double.valueOf(model.getGoods().getCan_buy_back_price()) - Double.valueOf(model.getUsable_money())));//算力费用

                } else {
                    textView5.setVisibility(View.GONE);
                    textView4.setText(getString(R.string.fragment5_h91) + "¥" + String.format("%.2f",
                            num * Double.valueOf(model.getGoods().getCan_buy_back_price())));//算力费用
                }

            } else {
                if (isSelcet && Double.valueOf(model.getUsable_money()) > 0) {
                    textView5.setVisibility(View.VISIBLE);
                    textView5.setText("（" + getString(R.string.fragment5_h93) + "￥" + model.getUsable_money() + "）");
                    textView4.setText(getString(R.string.fragment5_h91) + "¥" + String.format("%.2f",
                            num * Double.valueOf(model.getGoods().getCannot_buy_back_price()) - Double.valueOf(model.getUsable_money())));//算力费用
                } else {
                    textView5.setVisibility(View.GONE);
                    textView4.setText(getString(R.string.fragment5_h91) + "¥" + String.format("%.2f",
                            num * Double.valueOf(model.getGoods().getCannot_buy_back_price())));//算力费用
                }
            }

        }

        /*if (Double.valueOf(model.getMill_production_value_fil_money()) != 0) {
            textView8.setText(String.format("%.2f",
                    num * Double.valueOf(model.getMill_production_value_fil_money()))
                    + getString(R.string.app_ge) + getString(R.string.app_type_fil));//预计产值
        }*/

    }

    @Override
    protected void initData() {
//        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        if (isAdded()) {
            showProgress(true, getString(R.string.app_loading));
            request("?token=" + localUserInfo.getToken());
        }
    }

    @Override
    protected void updateView() {

    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment3 + string, new OkHttpClientManager.ResultCallback<Fragment3Model>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                MainActivity.isOver = true;
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(final Fragment3Model response) {
                MyLogger.i(">>>>>>>>>首页" + response);
//                showContentPage();
                if (response != null) {
                    model = response;
                    hideProgress();

                    MainActivity.isOver = true;
                }
            }
        });
    }

    private void RequestBuy(Map<String, String> params) {
        OkHttpClientManager.postAsyn(getActivity(), URLs.Fragment3Buy, params, new OkHttpClientManager.ResultCallback<Fragment3BuyModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                textView7.setClickable(true);
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(Fragment3BuyModel response) {
//                textView7.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>购买" + response);
                myToast(getString(R.string.fragment3_h63));
                id = response.getOrder().getId();
                switch (payType) {
                    case -1:
                        break;
                    case 1:
                        //微信
                    case 2:
                        //支付宝
                    case 3:
                        //转账
                        String is_wallet = "1";
                        if (isSelcet) {
                            is_wallet = "2";
                        }
                        showProgress(true, getString(R.string.app_loading4));
                        String string = "?id=" + id
                                + "&pay_type=" + payType
                                + "&is_wallet=" + is_wallet
                                + "&token=" + localUserInfo.getToken();
                        RequestPay(string);
                        break;
                    /*case 3:
                        //转账
                        Bundle bundle = new Bundle();
                        bundle.putString("id", id);
                        CommonUtil.gotoActivityWithData(getActivity(), PayDetailActivity.class, bundle);
                        break;*/
                }

            }
        }, true);
    }

    private void RequestPay(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.MyOrderPay + string, new OkHttpClientManager.ResultCallback<PayModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(PayModel response) {
                MyLogger.i(">>>>>>>>>支付信息" + response);
                hideProgress();
                switch (payType) {
                    case 1:
                        //微信
                        if (response != null) {
                            IWXAPI api = WXAPIFactory.createWXAPI(getActivity(), "wxe540385418282fe2", false);//填写自己的APPID
                            api.registerApp("wxe540385418282fe2");//填写自己的APPID，注册本身APP
                            PayReq req = new PayReq();//PayReq就是订单信息对象
                            //给req对象赋值
                            req.appId = response.getWecahtPay().getAppid();//APPID
                            req.partnerId = response.getWecahtPay().getPartnerid();//    商户号
                            req.prepayId = response.getWecahtPay().getPrepayid();//  预付款ID
                            req.nonceStr = response.getWecahtPay().getNoncestr();//随机数
                            req.timeStamp = response.getWecahtPay().getTimestamp();//时间戳
                            req.packageValue = "Sign=WXPay";//固定值Sign=WXPay
                            req.sign = response.getWecahtPay().getSign();//签名
                            api.sendReq(req);//将订单信息对象发送给微信服务器，即发送支付请求
                        }
                        break;
                    case 2:
                        //支付宝
                        if (response != null) {
                            Runnable payRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    PayTask alipay = new PayTask(getActivity());
                                    Map<String, String> result = alipay.payV2(response.getAlipay(), true);
                                    Message msg = new Message();
                                    msg.what = SDK_PAY_FLAG;
                                    msg.obj = result;
                                    mHandler.sendMessage(msg);
                                }
                            };
                            // 必须异步调用
                            Thread payThread = new Thread(payRunnable);
                            payThread.start();
                        }
                        break;
                    case 3:
                        //转账
                        Bundle bundle = new Bundle();
                        bundle.putString("id", id);
                        CommonUtil.gotoActivityWithData(getActivity(), PayDetailActivity.class, bundle);
                        break;
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.linearLayout1:
                type = 1;
                changeUI();
                break;
            */

        }
    }

}
