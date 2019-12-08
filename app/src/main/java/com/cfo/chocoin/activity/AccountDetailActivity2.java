package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.model.AccountDetailModel2;
import com.cfo.chocoin.model.GetBuyModel;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.CommonUtil;
import com.cfo.chocoin.utils.MyLogger;
import com.cy.dialog.BaseDialog;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyz on 2019/5/28.
 */
public class AccountDetailActivity2 extends BaseActivity {
    int type = 1;
    private RecyclerView recyclerView;
    List<AccountDetailModel2.EarningListBean> list1 = new ArrayList<>();
    CommonAdapter<AccountDetailModel2.EarningListBean> mAdapter1;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper1;

    List<AccountDetailModel2.ExpenditureListBean> list2 = new ArrayList<>();
    CommonAdapter<AccountDetailModel2.ExpenditureListBean> mAdapter2;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper2;
    //头部一
    View headerView1;
    LinearLayout head1_linearLayout;
    TextView head1_textView1, head1_textView2, head1_textView3, head1_textView4, head1_textView5;

    //头部二-需要悬浮的布局
    View headerView2;
    LinearLayout head2_linearLayout1, head2_linearLayout2;
    TextView head2_textView1, head2_textView2;
    View head2_view1, head2_view2;

    View footerView;

    //悬浮部分
    LinearLayout invis;
    LinearLayout linearLayout1, linearLayout2;
    TextView textView1, textView2;
    View view1, view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountdetail2);
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                String string = "?token=" + localUserInfo.getToken();
                Request(string);
            }

            @Override
            public void onLoadmore() {

            }
        });

        //悬浮部分
        invis = findViewByID_My(R.id.invis);
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);

        //头部一
        headerView1 = View.inflate(AccountDetailActivity2.this, R.layout.head_accountdetail1_2, null);
        head1_linearLayout = headerView1.findViewById(R.id.head1_linearLayout);
        head1_textView1 = headerView1.findViewById(R.id.head1_textView1);
        head1_textView2 = headerView1.findViewById(R.id.head1_textView2);
        head1_textView3 = headerView1.findViewById(R.id.head1_textView3);
        head1_textView4 = headerView1.findViewById(R.id.head1_textView4);
        head1_textView5 = headerView1.findViewById(R.id.head1_textView5);

        //头部二
        headerView2 = View.inflate(AccountDetailActivity2.this, R.layout.head_accountdetail_2, null);
        head2_linearLayout1 = headerView2.findViewById(R.id.head2_linearLayout1);
        head2_linearLayout2 = headerView2.findViewById(R.id.head2_linearLayout2);
        head2_linearLayout1.setOnClickListener(this);
        head2_linearLayout2.setOnClickListener(this);
        head2_textView1 = headerView2.findViewById(R.id.head2_textView1);
        head2_textView2 = headerView2.findViewById(R.id.head2_textView2);
        head2_view1 = headerView2.findViewById(R.id.head2_view1);
        head2_view2 = headerView2.findViewById(R.id.head2_view2);

        footerView = View.inflate(AccountDetailActivity2.this, R.layout.foot_accountdetail, null);

        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(AccountDetailActivity2.this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        //listview 滑动监听
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mLinearLayoutManager.findFirstVisibleItemPosition() >= 1) {
                    invis.setVisibility(View.VISIBLE);
                    headerView2.setVisibility(View.GONE);
                } else {
                    invis.setVisibility(View.GONE);
                    headerView2.setVisibility(View.VISIBLE);
                }
            }
        });

        //动态设置linearLayout的高度为屏幕高度的1/2
        ViewGroup.LayoutParams lp = head1_linearLayout.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(AccountDetailActivity2.this) / 2;

    }

    @Override
    protected void initData() {
        requestServer();
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.AccountDetail2 + string, new OkHttpClientManager.ResultCallback<AccountDetailModel2>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(AccountDetailModel2 response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>账户详情2" + response);
                head1_textView1.setText(response.getCommon_usable_money());
                head1_textView2.setText(response.getPrincipal_money());
                head1_textView3.setText(response.getWin_money());
                head1_textView4.setText(response.getCommission_money());
                head1_textView5.setText(response.getInterest_money());

                list1 = response.getEarning_list();
                mAdapter1 = new CommonAdapter<AccountDetailModel2.EarningListBean>
                        (AccountDetailActivity2.this, R.layout.item_accountdetail, list1) {
                    @Override
                    protected void convert(ViewHolder holder, final AccountDetailModel2.EarningListBean model, int position) {
                        holder.setText(R.id.textView1, model.getTitle() + "：+" + model.getMoney());//标题
//                        holder.setText(R.id.textView2, getString(R.string.recharge_h21) + model.get);//流水号
                        holder.setText(R.id.textView3, getString(R.string.recharge_h22) + model.getCreated_at());//时间
                        holder.setText(R.id.textView4, model.getStatus());//状态

                    }
                };
                mHeaderAndFooterWrapper1 = new HeaderAndFooterWrapper(mAdapter1);
                mHeaderAndFooterWrapper1.addHeaderView(headerView1);
                mHeaderAndFooterWrapper1.addHeaderView(headerView2);
                mHeaderAndFooterWrapper1.addFootView(footerView);

                list2 = response.getExpenditure_list();
                mAdapter2 = new CommonAdapter<AccountDetailModel2.ExpenditureListBean>
                        (AccountDetailActivity2.this, R.layout.item_accountdetail, list2) {
                    @Override
                    protected void convert(ViewHolder holder, final AccountDetailModel2.ExpenditureListBean model, int position) {
                        holder.setText(R.id.textView1, model.getTitle() + "：-" + model.getMoney());//标题
//                        holder.setText(R.id.textView2, getString(R.string.recharge_h21) + model.getId());//流水号
                        holder.setText(R.id.textView3, getString(R.string.recharge_h22) + model.getCreated_at());//时间
                        holder.setText(R.id.textView4, model.getStatus());//状态
                    }
                };
                mHeaderAndFooterWrapper2 = new HeaderAndFooterWrapper(mAdapter2);
                mHeaderAndFooterWrapper2.addHeaderView(headerView1);
                mHeaderAndFooterWrapper2.addHeaderView(headerView2);
                mHeaderAndFooterWrapper2.addFootView(footerView);

                //弹出算力不足框
                if (Double.valueOf(response.getOverflow_commission_money()) > 0) {
                    dialog.contentView(R.layout.dialog_suanlibuzu)
                            .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT))
                            .animType(BaseDialog.AnimInType.CENTER)
                            .canceledOnTouchOutside(true)
                            .dimAmount(0.8f)
                            .show();
                    TextView textView1 = dialog.findViewById(R.id.textView1);
                    TextView textView2 = dialog.findViewById(R.id.textView2);

                    textView1.setText(response.getOverflow_commission_money());
                    textView2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            CommonUtil.gotoActivity(AccountDetailActivity2.this, BuyComputingPowerActivity.class, false);
                        }
                    });
                    dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }

                changeUI();
                hideProgress();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.linearLayout2:
                type = 2;
                changeUI();
                break;
            case R.id.head2_linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.head2_linearLayout2:
                type = 2;
                changeUI();
                break;
            case R.id.head1_linearLayout1:
                //充值
                CommonUtil.gotoActivity(AccountDetailActivity2.this, RechargeActivity.class, false);
                break;
            case R.id.head1_linearLayout2:
                //提现
                CommonUtil.gotoActivity(AccountDetailActivity2.this, TakeCashActivity.class, false);
                break;
            case R.id.textView3:
                //购买算力
                CommonUtil.gotoActivity(AccountDetailActivity2.this, BuyComputingPowerActivity.class, false);
                break;
            case R.id.textView4:
                //激活区块
                showProgress(true, getString(R.string.app_loading2));
                String string = "?token=" + localUserInfo.getToken();
                getBuy(string);
//                CommonUtil.gotoActivity(AccountDetailActivity2.this, .class, false);
                break;
        }
    }

    private void getBuy(String string) {
        OkHttpClientManager.getAsyn(AccountDetailActivity2.this, URLs.BuyQuKuai + string, new OkHttpClientManager.ResultCallback<GetBuyModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(final GetBuyModel response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>获取余额" + response);

                dialog.contentView(R.layout.dialog_jihuoqukuai)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.CENTER)
                        .canceledOnTouchOutside(true)
                        .dimAmount(0.8f)
                        .show();
                TextView textView1 = dialog.findViewById(R.id.textView1);
                final TextView textView2 = dialog.findViewById(R.id.textView2);
                final EditText editText = dialog.findViewById(R.id.editText);

                textView1.setText(getString(R.string.fragment2_h16) + response.getCommon_usable_money());
                editText.setHint(getString(R.string.fragment2_h17)
                        + "(" + response.getMin_block_money() + "-" +
                        response.getMax_block_money() + ")");
                textView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!editText.getText().toString().trim().equals("")) {
                            //激活区块
                            textView2.setClickable(false);
                            showProgress(true, getString(R.string.app_loading1));
                            HashMap<String, String> params = new HashMap<>();
                            params.put("money", editText.getText().toString().trim());//提现金额
                            params.put("token", localUserInfo.getToken());
                            RequestBuy(params, textView2);//激活区块
                        } else {
                            showToast(getString(R.string.fragment2_h17));
                        }
                    }
                });

                dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void RequestBuy(Map<String, String> params, final TextView textView) {
        OkHttpClientManager.postAsyn(AccountDetailActivity2.this, URLs.BuyQuKuai, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                textView.setClickable(true);
                hideProgress();
                if (!info.equals("")) {
                    if (info.contains(getString(R.string.password_h1))) {
                        showToast(getString(R.string.password_h2),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(AccountDetailActivity2.this, SetTransactionPasswordActivity.class, false);
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
                                        CommonUtil.gotoActivity(AccountDetailActivity2.this, SelectAddressActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    } else {
                        myToast(info);
                    }
                }
//                requestServer();
            }

            @Override
            public void onResponse(String response) {
                textView.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>购买区块" + response);
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    String info = jObj.getString("msg");
                    myToast(info);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                dialog.dismiss();
                requestServer();
//                Bundle bundle = new Bundle();
//                bundle.putString("id", response.getId());
//                CommonUtil.gotoActivityWithData(BuyComputingPowerActivity.this, TakeCashDetailActivity.class, bundle, true);
            }
        });
    }

    private void changeUI() {
        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.blue));
            textView2.setTextColor(getResources().getColor(R.color.black4));
            head2_textView1.setTextColor(getResources().getColor(R.color.blue));
            head2_textView2.setTextColor(getResources().getColor(R.color.black4));

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            head2_view1.setVisibility(View.VISIBLE);
            head2_view2.setVisibility(View.INVISIBLE);

            recyclerView.setAdapter(mHeaderAndFooterWrapper1);
            mHeaderAndFooterWrapper1.notifyDataSetChanged();

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.black4));
            textView2.setTextColor(getResources().getColor(R.color.blue));
            head2_textView1.setTextColor(getResources().getColor(R.color.black4));
            head2_textView2.setTextColor(getResources().getColor(R.color.blue));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            head2_view1.setVisibility(View.INVISIBLE);
            head2_view2.setVisibility(View.VISIBLE);

            recyclerView.setAdapter(mHeaderAndFooterWrapper2);
            mHeaderAndFooterWrapper2.notifyDataSetChanged();
        }
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        String string = "?token=" + localUserInfo.getToken();
        Request(string);
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.qukuai_h3));
    }
}
