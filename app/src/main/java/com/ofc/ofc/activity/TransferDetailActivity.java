package com.ofc.ofc.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.TransferDetailModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyz on 2019-12-18.
 * 合约详情
 */
public class TransferDetailActivity extends BaseActivity {
    String id = "";
    int type = 1;
    LinearLayout linearLayout_1, linearLayout_2;
    TextView textView_1, textView_2;
    View view_1, view_2;

    private RecyclerView recyclerView;
    List<TransferDetailModel.ContractTradingListBean> list1 = new ArrayList<>();
    CommonAdapter<TransferDetailModel.ContractTradingListBean> mAdapter1;

    List<TransferDetailModel.ContractCallMarginListBean> list2 = new ArrayList<>();
    CommonAdapter<TransferDetailModel.ContractCallMarginListBean> mAdapter2;

    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferdetail);
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                request("?token=" + localUserInfo.getToken()
                        + "&id=" + id);
            }

            @Override
            public void onLoadmore() {

            }
        });

        linearLayout_1 = findViewByID_My(R.id.linearLayout_1);
        linearLayout_2 = findViewByID_My(R.id.linearLayout_2);
        textView_1 = findViewByID_My(R.id.textView_1);
        textView_2 = findViewByID_My(R.id.textView_2);
        view_1 = findViewByID_My(R.id.view_1);
        view_2 = findViewByID_My(R.id.view_2);
        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(TransferDetailActivity.this);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);

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
        showProgress(true, getString(R.string.app_loading));
        request("?token=" + localUserInfo.getToken()
                + "&id=" + id);
    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(TransferDetailActivity.this, URLs.Transfer_Detail + string,
                new OkHttpClientManager.ResultCallback<TransferDetailModel>() {
                    @Override
                    public void onError(Request request, String info, Exception e) {
                        hideProgress();
                        if (!info.equals("")) {
                            myToast(info);
                        }
                    }

                    @Override
                    public void onResponse(TransferDetailModel response) {
                        MyLogger.i(">>>>>>>>>划转详情" + response);
                        textView1.setText(response.getReality_money() + "");//合约额度
                        textView2.setText(response.getProfit_money() + "");//合约盈利
                        textView3.setText(response.getStatus_title() + "");//合约状态
                        textView4.setText(response.getTrading_count() + "");//交易次数
                        textView5.setText(response.getShow_created_at() + "");//开始时间
                        textView6.setText(response.getReality_count() + "");//盈利次数
                        textView7.setText(response.getShow_updated_at() + "");//结束时间

                        list1 = response.getContract_trading_list();
                        mAdapter1 = new CommonAdapter<TransferDetailModel.ContractTradingListBean>
                                (TransferDetailActivity.this, R.layout.item_transferdetail, list1) {
                            @Override
                            protected void convert(ViewHolder holder, TransferDetailModel.ContractTradingListBean model, int position) {
                                TextView textView1 = holder.getView(R.id.textView1);
                                TextView textView11 = holder.getView(R.id.textView11);
                                textView11.setText(model.getResult_title() + "");//交易结果
                                textView1.setText(model.getEarning_money() + "");//合约盈利
                                if (model.getResult() == 1){
                                    //盈利
                                    textView11.setTextColor(getResources().getColor(R.color.green));
                                    textView1.setTextColor(getResources().getColor(R.color.green));
                                }else {
                                    //亏损
                                    textView11.setTextColor(getResources().getColor(R.color.red));
                                    textView1.setTextColor(getResources().getColor(R.color.red));
                                }

                                holder.setText(R.id.textView10, model.getBourse_title() + "");//合约交易
                                holder.setText(R.id.textView2, model.getBourse_on_title() + "");//合约类型
                                holder.setText(R.id.textView3, model.getDirection_title() + "");//合约方向
                                holder.setText(R.id.textView4, model.getLever() + getString(R.string.app_bei));//合约杠杆
                                holder.setText(R.id.textView5, "$ " + model.getBuy_price() + "");//买入价格
                                holder.setText(R.id.textView6, "$ " + model.getSell_price() + "");//卖出价格

                                holder.setText(R.id.textView8, model.getBuy_at() + "");//买入时间
                                holder.setText(R.id.textView9, model.getSell_at() + "");//卖出时间
                                holder.setText(R.id.textView7, model.getSn() + "");//交易单号

                            }
                        };
                        list2 = response.getContract_call_margin_list();
                        mAdapter2 = new CommonAdapter<TransferDetailModel.ContractCallMarginListBean>
                                (TransferDetailActivity.this, R.layout.item_transferdetail_1, list2) {
                            @Override
                            protected void convert(ViewHolder holder, TransferDetailModel.ContractCallMarginListBean model, int position) {

                                holder.setText(R.id.textView1, model.getMoney() + "");//合约盈利
                                holder.setText(R.id.textView2, model.getCreated_at() + "");//合约类型
                            }
                        };
                        changeUI();

                        hideProgress();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.linearLayout_1:
                type = 1;
                changeUI();
                break;
            case R.id.linearLayout_2:
                type = 2;
                changeUI();
                break;
            /*case R.id.left_btn:
                finish();
                break;
            case R.id.textView3:
                //确定
                if (!editText1.getText().toString().trim().equals("")) {
                    textView3.setClickable(false);
                    showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("money", editText1.getText().toString().trim());
                    params.put("token", localUserInfo.getToken());
                    RequestAdd(params);//充值
                } else {
                    myToast(getString(R.string.scavengingpayment_h15));
                }
                break;*/
        }
    }

    private void changeUI() {
        if (type == 1) {
            //交易记录
            textView_1.setTextColor(getResources().getColor(R.color.green));
            textView_2.setTextColor(getResources().getColor(R.color.black4));

            view_1.setVisibility(View.VISIBLE);
            view_2.setVisibility(View.INVISIBLE);

            if (list1.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter1);
            } else {
                showEmptyPage();
            }

        } else if (type == 2) {
            //交易记录
            textView_1.setTextColor(getResources().getColor(R.color.black4));
            textView_2.setTextColor(getResources().getColor(R.color.green));

            view_1.setVisibility(View.INVISIBLE);
            view_2.setVisibility(View.VISIBLE);

            if (list2.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter2);
            } else {
                showEmptyPage();
            }

        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.scavengingpayment_h24));
    }
}
