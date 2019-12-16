package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyz on 2019/5/28.
 * 我的钱包
 */
public class AccountDetailActivity extends BaseActivity {
    int type = 1;
    private RecyclerView recyclerView;
    List<AccountDetailModel1.EarningListBean> list1 = new ArrayList<>();
    CommonAdapter<AccountDetailModel1.EarningListBean> mAdapter1;

    List<AccountDetailModel1.ExpenditureListBean> list2 = new ArrayList<>();
    CommonAdapter<AccountDetailModel1.ExpenditureListBean> mAdapter2;
    //头部一
//    View headerView1;
    RelativeLayout head1_relativeLayout;
    TextView head1_textView1, head1_textView2, head1_textView3, head1_textView4;

    //悬浮部分
    LinearLayout invis;
    LinearLayout linearLayout1, linearLayout2;
    TextView textView1, textView2;
    View view1, view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountdetail);
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


        head1_relativeLayout = findViewByID_My(R.id.head1_relativeLayout);
//        head1_relativeLayout.setPadding(0, (int) CommonUtil.getStatusBarHeight(AccountDetailActivity.this), 0, 0);

        head1_textView1 = findViewByID_My(R.id.head1_textView1);
        head1_textView2 = findViewByID_My(R.id.head1_textView2);
        head1_textView3 = findViewByID_My(R.id.head1_textView3);
        head1_textView4 = findViewByID_My(R.id.head1_textView4);

        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(AccountDetailActivity.this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        /*//listview 滑动监听
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
        });*/
        //动态设置linearLayout的高度为屏幕高度的1/2
        ViewGroup.LayoutParams lp = head1_relativeLayout.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(AccountDetailActivity.this) * 384 / 895;
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
                head1_textView1.setText(response.getCommon_usable_money());
                head1_textView2.setText(response.getInsurance_usable_money());
                head1_textView3.setText(response.getWin_money());
                head1_textView4.setText(response.getCommission_money());

                list1 = response.getEarning_list();
                mAdapter1 = new CommonAdapter<AccountDetailModel1.EarningListBean>
                        (AccountDetailActivity.this, R.layout.item_accountdetail, list1) {
                    @Override
                    protected void convert(ViewHolder holder, final AccountDetailModel1.EarningListBean model, int position) {
                        holder.setText(R.id.textView1, model.getTitle() + "：+" + model.getMoney());//标题
//                        holder.setText(R.id.textView2, getString(R.string.recharge_h21) + model.getId());//流水号
                        holder.setText(R.id.textView3, getString(R.string.recharge_h22) + model.getCreated_at());//时间
                        holder.setText(R.id.textView4, model.getStatus());//状态

                    }
                };

                list2 = response.getExpenditure_list();
                mAdapter2 = new CommonAdapter<AccountDetailModel1.ExpenditureListBean>
                        (AccountDetailActivity.this, R.layout.item_accountdetail, list2) {
                    @Override
                    protected void convert(ViewHolder holder, final AccountDetailModel1.ExpenditureListBean model, int position) {
                        holder.setText(R.id.textView1, model.getTitle() + "：-" + model.getMoney());//标题
//                        holder.setText(R.id.textView2, getString(R.string.recharge_h21) + model.getId());//流水号
                        holder.setText(R.id.textView3, getString(R.string.recharge_h22) + model.getCreated_at());//时间
                        holder.setText(R.id.textView4, model.getStatus());//状态
                    }
                };

                changeUI();
                hideProgress();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.linearLayout2:
                type = 2;
                changeUI();
                break;

            case R.id.head1_linearLayout1:
                //充值
                Bundle bundle = new Bundle();
                bundle.putInt("item", 3);
                CommonUtil.gotoActivityWithFinishOtherAllAndData(this, MainActivity.class, bundle, true);
                break;
            case R.id.head1_linearLayout2:
                //划转
                Bundle bundle1 = new Bundle();
                bundle1.putInt("item", 0);
//                CommonUtil.gotoActivityWithFinishOtherAllAndData(this, MainActivity.class,bundle1, true);
                break;
            case R.id.head1_linearLayout3:
                //提现
                CommonUtil.gotoActivity(this, TakeCashActivity.class, false);
                break;
            case R.id.head1_textView5:
                //币地址
                CommonUtil.gotoActivity(this, SelectAddressActivity.class, false);
                break;
        }
    }

    private void changeUI() {
        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.green));
            textView2.setTextColor(getResources().getColor(R.color.black4));
//            head2_textView1.setTextColor(getResources().getColor(R.color.blue));
//            head2_textView2.setTextColor(getResources().getColor(R.color.black4));

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
//            head2_view1.setVisibility(View.VISIBLE);
//            head2_view2.setVisibility(View.INVISIBLE);

            recyclerView.setAdapter(mAdapter1);
            mAdapter1.notifyDataSetChanged();
        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.black4));
            textView2.setTextColor(getResources().getColor(R.color.green));
//            head2_textView1.setTextColor(getResources().getColor(R.color.black4));
//            head2_textView2.setTextColor(getResources().getColor(R.color.blue));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
//            head2_view1.setVisibility(View.INVISIBLE);
//            head2_view2.setVisibility(View.VISIBLE);

            recyclerView.setAdapter(mAdapter2);
            mAdapter2.notifyDataSetChanged();
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
        titleView.setTitle(getString(R.string.qianbao_h1));
//        titleView.setVisibility(View.GONE);
    }
}
