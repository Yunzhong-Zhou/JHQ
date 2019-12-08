package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.model.AccountDetailModel1;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.CommonUtil;
import com.cfo.chocoin.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyz on 2019/5/28.
 */
public class AccountDetailActivity1 extends BaseActivity {
    int type = 1;
    private RecyclerView recyclerView;
    List<AccountDetailModel1.EarningListBean> list1 = new ArrayList<>();
    CommonAdapter<AccountDetailModel1.EarningListBean> mAdapter1;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper1;

    List<AccountDetailModel1.ExpenditureListBean> list2 = new ArrayList<>();
    CommonAdapter<AccountDetailModel1.ExpenditureListBean> mAdapter2;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper2;
    //头部一
    View headerView1;
    LinearLayout head1_linearLayout;
    TextView head1_textView1, head1_textView2, head1_textView3, head1_textView4;

    //头部二-需要悬浮的布局
    View headerView2;
    LinearLayout head2_linearLayout1, head2_linearLayout2;
    TextView head2_textView1, head2_textView2;
    View head2_view1, head2_view2;

    //悬浮部分
    LinearLayout invis;
    LinearLayout linearLayout1, linearLayout2;
    TextView textView1, textView2;
    View view1, view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountdetail1);
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
        headerView1 = View.inflate(AccountDetailActivity1.this, R.layout.head_accountdetail1_1, null);
        head1_linearLayout = headerView1.findViewById(R.id.head1_linearLayout);
        head1_textView1 = headerView1.findViewById(R.id.head1_textView1);
        head1_textView2 = headerView1.findViewById(R.id.head1_textView2);
        head1_textView3 = headerView1.findViewById(R.id.head1_textView3);
        head1_textView4 = headerView1.findViewById(R.id.head1_textView4);

        //头部二
        headerView2 = View.inflate(AccountDetailActivity1.this, R.layout.head_accountdetail_2, null);
        head2_linearLayout1 = headerView2.findViewById(R.id.head2_linearLayout1);
        head2_linearLayout2 = headerView2.findViewById(R.id.head2_linearLayout2);
        head2_linearLayout1.setOnClickListener(this);
        head2_linearLayout2.setOnClickListener(this);
        head2_textView1 = headerView2.findViewById(R.id.head2_textView1);
        head2_textView2 = headerView2.findViewById(R.id.head2_textView2);
        head2_view1 = headerView2.findViewById(R.id.head2_view1);
        head2_view2 = headerView2.findViewById(R.id.head2_view2);

        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(AccountDetailActivity1.this);
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
        lp.height = (int) CommonUtil.getScreenHeight(AccountDetailActivity1.this) / 3;
    }

    @Override
    protected void initData() {
        requestServer();
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.AccountDetail1 + string, new OkHttpClientManager.ResultCallback<AccountDetailModel1>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(AccountDetailModel1 response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>账户详情1" + response);
                head1_textView1.setText(response.getBlock_award_usable_money());
                head1_textView2.setText(response.getWait_block_award_money());
                head1_textView3.setText(response.getBlock_award_money());

                list1 = response.getEarning_list();
                mAdapter1 = new CommonAdapter<AccountDetailModel1.EarningListBean>
                        (AccountDetailActivity1.this, R.layout.item_accountdetail, list1) {
                    @Override
                    protected void convert(ViewHolder holder, final AccountDetailModel1.EarningListBean model, int position) {
                        holder.setText(R.id.textView1, model.getTitle() + "：+" + model.getMoney());//标题
//                        holder.setText(R.id.textView2, getString(R.string.recharge_h21) + model.getId());//流水号
                        holder.setText(R.id.textView3, getString(R.string.recharge_h22) + model.getCreated_at());//时间
                        holder.setText(R.id.textView4, model.getStatus());//状态

                    }
                };
                mHeaderAndFooterWrapper1 = new HeaderAndFooterWrapper(mAdapter1);
                mHeaderAndFooterWrapper1.addHeaderView(headerView1);
                mHeaderAndFooterWrapper1.addHeaderView(headerView2);

                list2 = response.getExpenditure_list();
                mAdapter2 = new CommonAdapter<AccountDetailModel1.ExpenditureListBean>
                        (AccountDetailActivity1.this, R.layout.item_accountdetail, list2) {
                    @Override
                    protected void convert(ViewHolder holder, final AccountDetailModel1.ExpenditureListBean model, int position) {
                        holder.setText(R.id.textView1, model.getTitle() + "：-" + model.getMoney());//标题
//                        holder.setText(R.id.textView2, getString(R.string.recharge_h21) + model.getId());//流水号
                        holder.setText(R.id.textView3, getString(R.string.recharge_h22) + model.getCreated_at());//时间
                        holder.setText(R.id.textView4, model.getStatus());//状态

                    }
                };
                mHeaderAndFooterWrapper2 = new HeaderAndFooterWrapper(mAdapter2);
                mHeaderAndFooterWrapper2.addHeaderView(headerView1);
                mHeaderAndFooterWrapper2.addHeaderView(headerView2);

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
            case R.id.head1_textView4:
                //购买算力
                CommonUtil.gotoActivity(AccountDetailActivity1.this, BuyComputingPowerActivity.class, false);
                break;
        }
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
        titleView.setTitle(getString(R.string.qukuai_h22));
    }
}
