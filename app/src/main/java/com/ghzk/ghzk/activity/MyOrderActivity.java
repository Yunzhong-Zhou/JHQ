package com.ghzk.ghzk.activity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.cy.dialog.BaseDialog;
import com.ghzk.ghzk.R;
import com.ghzk.ghzk.adapter.Pop_ListAdapter;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.model.MyOrderModel;
import com.ghzk.ghzk.model.PayModel;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
import com.ghzk.ghzk.utils.MyLogger;
import com.ghzk.ghzk.utils.alipay.PayResult;
import com.ghzk.ghzk.view.FixedPopupWindow;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zyz on 2017/9/5.
 * 订单中心
 */

public class MyOrderActivity extends BaseActivity {
    String id = "";
    private RecyclerView recyclerView;
    List<MyOrderModel.OrderListBean> list = new ArrayList<>();
    CommonAdapter<MyOrderModel.OrderListBean> mAdapter;
    //筛选
    private LinearLayout linearLayout1, linearLayout2;
    private TextView textView1, textView2;
    private View view1, view2;

    private LinearLayout pop_view;
    int page = 1;
    String sort = "desc", status = "";
    int i1 = 0;
    int i2 = 0;

    int payType = 0;
    boolean isSelcet = true;

    private static final int SDK_PAY_FLAG = 1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
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
                        CommonUtil.gotoActivityWithData(MyOrderActivity.this, MachineDetailActivity.class, bundle1, false);

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        MyLogger.i("支付失败" + payResult);
                        showToast(getString(R.string.app_pay_false));
                    }
                    requestServer();
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();//获取数据
    }

    @Override
    protected void initView() {
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSpringViewMore(true);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                page = 1;
                String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                        + "&sort=" + sort
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentList(string);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                        + "&sort=" + sort
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentListMore(string);
            }
        });
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        pop_view = findViewByID_My(R.id.pop_view);


    }

    @Override
    protected void initData() {

    }

    private void RequestMyInvestmentList(String string) {
        OkHttpClientManager.getAsyn(MyOrderActivity.this, URLs.MyOrder + string, new OkHttpClientManager.ResultCallback<MyOrderModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                onHttpResult();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(MyOrderModel response) {
                showContentPage();
                onHttpResult();
                list = response.getOrder_list();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<MyOrderModel.OrderListBean>
                            (MyOrderActivity.this, R.layout.item_myorder, list) {
                        @Override
                        protected void convert(ViewHolder holder, MyOrderModel.OrderListBean model, int position) {
                            holder.setText(R.id.textView1, getString(R.string.fragment5_h81) + model.getSn());//订单号
                            holder.setText(R.id.textView2, model.getStatus_title());//状态
                            holder.setText(R.id.textView3, "" + model.getNum());//数量
                            holder.setText(R.id.textView4, model.getOperation_type_title());//运营方式
                            holder.setText(R.id.textView5, model.getPrice());//金额
                            holder.setText(R.id.textView6, model.getCreated_at());//时间

                            //立即支付
                            TextView textView7 = holder.getView(R.id.textView7);
                            textView7.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    id = model.getId();

                                    dialog.contentView(R.layout.dialog_paytype)
                                            .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT))
                                            .animType(BaseDialog.AnimInType.BOTTOM)
                                            .canceledOnTouchOutside(true)
                                            .gravity(Gravity.BOTTOM)
                                            .dimAmount(0.8f)
                                            .show();
                                    //初始化
                                    payType = 0;

                                    LinearLayout ll_lingqian = dialog.findViewById(R.id.ll_lingqian);
                                    LinearLayout ll_zhifubao = dialog.findViewById(R.id.ll_zhifubao);
                                    LinearLayout ll_weixin = dialog.findViewById(R.id.ll_weixin);
                                    LinearLayout ll_zhuanzhang = dialog.findViewById(R.id.ll_zhuanzhang);
                                    ImageView iv_lingqian = dialog.findViewById(R.id.iv_lingqian);
                                    ImageView iv_zhifubao = dialog.findViewById(R.id.iv_zhifubao);
                                    ImageView iv_weixin = dialog.findViewById(R.id.iv_weixin);
                                    ImageView iv_zhuanzhang = dialog.findViewById(R.id.iv_zhuanzhang);
                                    TextView tv_lingqian = dialog.findViewById(R.id.tv_lingqian);
                                    TextView textView4 = dialog.findViewById(R.id.textView4);
                                    TextView textView5 = dialog.findViewById(R.id.textView5);
                                    tv_lingqian.setText(getString(R.string.fragment5_h87)
                                            + "（" + getString(R.string.fragment5_h87) + "¥" + response.getUsable_money() + "）");
                                    textView4.setText(getString(R.string.fragment5_h91) + "¥" + model.getMoney());//实付款

                                    if (Double.valueOf(response.getUsable_money()) > 0) {
                                        payType = 0;
                                        isSelcet = true;
                                        iv_lingqian.setImageResource(R.mipmap.ic_kuang_true);
                                        ll_lingqian.setVisibility(View.VISIBLE);

                                        if (isSelcet && Double.valueOf(response.getUsable_money()) > 0) {
                                            textView5.setVisibility(View.VISIBLE);
                                            textView5.setText("（" + getString(R.string.fragment5_h93) + "￥" + response.getUsable_money() + "）");
                                            textView4.setText(getString(R.string.fragment5_h91) + "¥" + String.format("%.2f",
                                                    Double.valueOf(model.getPrice()) - Double.valueOf(response.getUsable_money())));//算力费用

                                        } else {
                                            textView5.setVisibility(View.GONE);
                                            textView4.setText(getString(R.string.fragment5_h91) + "¥" + String.format("%.2f",
                                                    Double.valueOf(model.getPrice())));//算力费用
                                        }

                                    } else {
                                        payType = 0;
                                        isSelcet = false;
                                        iv_lingqian.setImageResource(R.mipmap.ic_kuang_false);
                                        ll_lingqian.setVisibility(View.GONE);
                                    }

                                    //零钱
                                    ll_lingqian.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
//                                            payType = 0;
                                            if (Double.valueOf(response.getUsable_money()) > 0) {
                                                isSelcet = !isSelcet;
                                                if (isSelcet && Double.valueOf(response.getUsable_money()) > 0) {
                                                    iv_lingqian.setImageResource(R.mipmap.ic_kuang_true);

                                                    textView5.setVisibility(View.VISIBLE);
                                                    textView5.setText("（" + getString(R.string.fragment5_h93) + "￥" + response.getUsable_money() + "）");
                                                    textView4.setText(getString(R.string.fragment5_h91) + "¥" + String.format("%.2f",
                                                            Double.valueOf(model.getPrice()) - Double.valueOf(response.getUsable_money())));//算力费用
                                                } else {
                                                    iv_lingqian.setImageResource(R.mipmap.ic_kuang_false);

                                                    textView5.setVisibility(View.GONE);
                                                    textView4.setText(getString(R.string.fragment5_h91) + "¥" + String.format("%.2f",
                                                            Double.valueOf(model.getPrice())));//算力费用
                                                }
                                            }

                                        }
                                    });
                                    //支付宝
                                    ll_zhifubao.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            payType = 2;
//                                            iv_lingqian.setImageResource(R.drawable.yuanxingbiankuang_baise);
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
//                                            iv_lingqian.setImageResource(R.drawable.yuanxingbiankuang_baise);
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
//                                            iv_lingqian.setImageResource(R.drawable.yuanxingbiankuang_baise);
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
                                                case 0:
                                                    if (isSelcet) {
                                                        dialog.dismiss();
                                                        showProgress(true, getString(R.string.app_loading1));
                                                        String is_wallet = "1";
                                                        if (isSelcet) {
                                                            is_wallet = "2";
                                                        }
                                                        String string = "?id=" + model.getId()
                                                                + "&pay_type=" + payType
                                                                + "&is_wallet=" + is_wallet
                                                                + "&token=" + localUserInfo.getToken();
                                                        RequestPay(string, model.getId());
                                                    } else {
                                                        myToast(getString(R.string.fragment5_h94));
                                                    }
                                                    break;
                                                case 1:
                                                case 2:
                                                    dialog.dismiss();
                                                    //微信、支付宝
                                                    showProgress(true, getString(R.string.app_loading1));
                                                    String is_wallet = "1";
                                                    if (isSelcet) {
                                                        is_wallet = "2";
                                                    }
                                                    String string = "?id=" + model.getId()
                                                            + "&pay_type=" + payType
                                                            + "&is_wallet=" + is_wallet
                                                            + "&token=" + localUserInfo.getToken();
                                                    RequestPay(string, model.getId());
                                                    break;
                                                case 3:
                                                    //转账
                                                    dialog.dismiss();
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("id", model.getId());
                                                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, PayDetailActivity.class, bundle);
                                                    break;
                                            }



                                        }
                                    });

                                }
                            });
                            //取消
                            TextView textView8 = holder.getView(R.id.textView8);
                            textView8.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showToast("确认取消该订单吗？", "确认", "取消",
                                            new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                    showProgress(true, getString(R.string.app_loading1));
                                        /*HashMap<String, String> params = new HashMap<>();
                                        params.put("goods_id", model.getId());
                                        params.put("pay_type", payType + "");
                                        params.put("token", localUserInfo.getToken());
//                            params.put("hk", model.getHk());
                                        RequestBuyCancel(params);*/
                                                    String string = "?id=" + model.getId()
                                                            + "&token=" + localUserInfo.getToken();
                                                    RequestBuyCancel(string);
                                                }
                                            }, new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                }
                                            });

                                }
                            });

                            //电子合同
                            TextView tv_dianzihetong = holder.getView(R.id.tv_dianzihetong);

                            //纸质合同
                            TextView tv_zhizhihetong = holder.getView(R.id.tv_zhizhihetong);

                            switch (model.getStatus()){
                                case 1:
                                    //待付款
                                    textView7.setVisibility(View.VISIBLE);
                                    textView8.setVisibility(View.VISIBLE);
                                    tv_dianzihetong.setVisibility(View.GONE);
                                    tv_zhizhihetong.setVisibility(View.GONE);
                                    break;
                                /*case 2:
                                    //已付款
                                    textView7.setVisibility(View.GONE);
                                    textView8.setVisibility(View.GONE);
                                    tv_dianzihetong.setVisibility(View.GONE);
                                    tv_zhizhihetong.setVisibility(View.GONE);
                                    break;*/
                                case 3:
                                case 4:
                                    //待处理、已处理
                                    textView7.setVisibility(View.GONE);
                                    textView8.setVisibility(View.GONE);
                                    tv_dianzihetong.setVisibility(View.VISIBLE);
                                    tv_zhizhihetong.setVisibility(View.VISIBLE);
                                    break;
                                default:
                                    //取消
                                    textView7.setVisibility(View.GONE);
                                    textView8.setVisibility(View.GONE);
                                    tv_dianzihetong.setVisibility(View.GONE);
                                    tv_zhizhihetong.setVisibility(View.GONE);
                                    break;
                            }

                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                                /*Bundle bundle1 = new Bundle();
                                bundle1.putString("id", list.get(position).getId());
                                CommonUtil.gotoActivityWithData(MyOrderActivity.this, RechargeDetailActivity.class, bundle1, false);*/
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });
                }
            }
        });

    }

    private void RequestMyInvestmentListMore(String string) {
        OkHttpClientManager.getAsyn(MyOrderActivity.this, URLs.MyOrder + string, new OkHttpClientManager.ResultCallback<MyOrderModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                showErrorPage();
                onHttpResult();
                if (!info.equals("")) {
                    showToast(info);
                }
                page--;
            }

            @Override
            public void onResponse(MyOrderModel response) {
//                showContentPage();
                onHttpResult();
                List<MyOrderModel.OrderListBean> list1 = new ArrayList<>();
                list1 = response.getOrder_list();
                if (list1.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                    page--;
                } else {
                    list.addAll(list1);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void RequestPay(String string, String id) {
        OkHttpClientManager.getAsyn(MyOrderActivity.this, URLs.MyOrderPay + string, new OkHttpClientManager.ResultCallback<PayModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                textView7.setClickable(true);
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(PayModel response) {
//                textView7.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>购买" + response);
//                myToast(getString(R.string.fragment3_h63));
                if (response != null) {
                    switch (payType) {
                        case 1:
                            //微信
                        /*Bundle bundle = new Bundle();
                        bundle.putString("id",id);
                        CommonUtil.gotoActivityWithData(MyOrderActivity.this, WXPayEntryActivity.class,bundle);*/

                            IWXAPI api = WXAPIFactory.createWXAPI(MyOrderActivity.this, "wxe540385418282fe2", false);//填写自己的APPID
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
                            break;
                        case 2:
                            //支付宝
                            Runnable payRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    PayTask alipay = new PayTask(MyOrderActivity.this);
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
                            break;
                        case 3:
                            //转账
                        /*Bundle bundle = new Bundle();
                        bundle.putString("id", id);
                        CommonUtil.gotoActivityWithData(MyOrderActivity.this, PayDetailActivity.class, bundle);*/
                            break;
                    }
                }

//                requestServer();

            }
        }, false);
    }

    private void RequestBuyCancel(String string) {
        OkHttpClientManager.getAsyn(MyOrderActivity.this, URLs.MyOrderCancel + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                textView7.setClickable(true);
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(String response) {
//                textView7.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>购买取消" + response);
                myToast("取消成功");
                requestServer();

            }
        }, false);
    }

    @Override
    public void onClick(View v) {
        Drawable drawable1 = getResources().getDrawable(R.mipmap.down_green);//选中-蓝色
        Drawable drawable2 = getResources().getDrawable(R.mipmap.down_black);//未选-灰色
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        switch (v.getId()) {
            case R.id.linearLayout1:
                textView1.setTextColor(getResources().getColor(R.color.green));
                textView2.setTextColor(getResources().getColor(R.color.black3));
                textView1.setCompoundDrawables(null, null, drawable1, null);
                textView2.setCompoundDrawables(null, null, drawable2, null);
//                view1.setVisibility(View.VISIBLE);
//                view2.setVisibility(View.INVISIBLE);
                showPopupWindow1(pop_view);
                break;
            case R.id.linearLayout2:
                textView1.setTextColor(getResources().getColor(R.color.black3));
                textView2.setTextColor(getResources().getColor(R.color.green));
                textView1.setCompoundDrawables(null, null, drawable2, null);
                textView2.setCompoundDrawables(null, null, drawable1, null);
//                view1.setVisibility(View.INVISIBLE);
//                view2.setVisibility(View.VISIBLE);
                showPopupWindow2(pop_view);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle(R.string.fragment5_h80);
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                + "&sort=" + sort
                + "&page=" + page//当前页号
                + "&count=" + "10"//页面行数
                + "&token=" + localUserInfo.getToken();
        RequestMyInvestmentList(string);
    }

    public void onHttpResult() {
        hideProgress();
        springView.onFinishFreshAndLoad();

    }

    private void showPopupWindow1(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(MyOrderActivity.this).inflate(
                R.layout.pop_list2, null);
        final FixedPopupWindow popupWindow = new FixedPopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        contentView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = contentView.findViewById(R.id.pop_listView).getTop();
                int height1 = contentView.findViewById(R.id.pop_listView).getBottom();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        popupWindow.dismiss();
                    }
                    if (y > height1) {
                        popupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        // 设置按钮的点击事件
        ListView pop_listView = (ListView) contentView.findViewById(R.id.pop_listView1);
        contentView.findViewById(R.id.pop_listView2).setVisibility(View.INVISIBLE);
        final List<String> list = new ArrayList<String>();
        list.add("默认排序");
        list.add("时间降序");
        list.add("时间升序");
        list.add("金额降序");
        list.add("金额升序");

        final Pop_ListAdapter adapter = new Pop_ListAdapter(MyOrderActivity.this, list);
        adapter.setSelectItem(i1);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                adapter.notifyDataSetChanged();
                i1 = i;
                /*if (i == 0) {
                    sort = "desc";
                } else {
                    sort = "asc";
                }*/
//                textView1.setText(list.get(i));
                requestServer();
                popupWindow.dismiss();
            }
        });

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        ColorDrawable dw = new ColorDrawable(this.getResources().getColor(R.color.transparentblack2));
        // 设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);
    }

    private void showPopupWindow2(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(MyOrderActivity.this).inflate(
                R.layout.pop_list2, null);
        final FixedPopupWindow popupWindow = new FixedPopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        contentView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = contentView.findViewById(R.id.pop_listView).getTop();
                int height1 = contentView.findViewById(R.id.pop_listView).getBottom();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        popupWindow.dismiss();
                    }
                    if (y > height1) {
                        popupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        // 设置按钮的点击事件
        contentView.findViewById(R.id.pop_listView1).setVisibility(View.INVISIBLE);
        ListView pop_listView = (ListView) contentView.findViewById(R.id.pop_listView2);
        final List<String> list = new ArrayList<String>();

        list.add(getString(R.string.app_type_quanbu));
        list.add(getString(R.string.app_type_daishenhe));
        list.add(getString(R.string.app_type_yitongguo));
        list.add(getString(R.string.app_type_weitongguo));
        list.add(getString(R.string.app_cancel));
        final Pop_ListAdapter adapter = new Pop_ListAdapter(MyOrderActivity.this, list);
        adapter.setSelectItem(i2);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                i2 = i;
                adapter.notifyDataSetChanged();
                if (i == 0) {
                    status = "";
                } else {
                    status = i + "";
                }
//                textView2.setText(list.get(i));
                requestServer();
                popupWindow.dismiss();
            }
        });

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        ColorDrawable dw = new ColorDrawable(this.getResources().getColor(R.color.transparentblack1));
        // 设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);
    }

/*    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                //获取扫描结果
                Bundle bundle = data.getExtras();
                int errCord = bundle.getInt("errCord");
                MyLogger.i("支付返回》》》", errCord + "");
                if (errCord == 0) {
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("id", id);
                    CommonUtil.gotoActivityWithData(MyOrderActivity.this, MachineDetailActivity.class, bundle1, false);
                }
            }
        }
    }*/
}
