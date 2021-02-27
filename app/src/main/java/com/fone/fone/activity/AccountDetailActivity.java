package com.fone.fone.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.AccountDetailModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zyz on 2019/5/28.
 * 我的钱包
 */
public class AccountDetailActivity extends BaseActivity {
    int type = 1;
    AccountDetailModel model1;
    private RecyclerView recyclerView;
    List<AccountDetailModel.InMoneyListBean> list1 = new ArrayList<>();
    CommonAdapter<AccountDetailModel.InMoneyListBean> mAdapter1;

    List<AccountDetailModel.OutMoneyListBean> list2 = new ArrayList<>();
    CommonAdapter<AccountDetailModel.OutMoneyListBean> mAdapter2;
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

        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
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

    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.AccountDetail + string, new OkHttpClientManager.ResultCallback<AccountDetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(AccountDetailModel response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>账户详情1" + response);
                model1 = response;
                /*if (!response.getTop_up_usdt_wallet_addr().equals("")) {
                    head1_textView5.setVisibility(View.VISIBLE);
                } else {
                    head1_textView5.setVisibility(View.GONE);
                }*/

                head1_textView1.setText(response.getUsable_money());
                head1_textView2.setText(response.getCommission_money());
                head1_textView3.setText(response.getEarning_money());
                head1_textView4.setText(response.getCommission_money());

                list1 = response.getIn_money_list();
                mAdapter1 = new CommonAdapter<AccountDetailModel.InMoneyListBean>
                        (AccountDetailActivity.this, R.layout.item_accountdetail, list1) {
                    @Override
                    protected void convert(ViewHolder holder, final AccountDetailModel.InMoneyListBean model, int position) {
                        holder.setText(R.id.textView1, model.getTitle() + "：+" + model.getMoney());//标题
//                        holder.setText(R.id.textView2, getString(R.string.recharge_h21) + model.get);//流水号
                        holder.setText(R.id.textView3, model.getCreated_at());//时间
                        holder.setText(R.id.textView4, model.getStatus());//状态
                    }
                };

                list2 = response.getOut_money_list();
                mAdapter2 = new CommonAdapter<AccountDetailModel.OutMoneyListBean>
                        (AccountDetailActivity.this, R.layout.item_accountdetail, list2) {
                    @Override
                    protected void convert(ViewHolder holder, final AccountDetailModel.OutMoneyListBean model, int position) {
                        holder.setText(R.id.textView1, model.getTitle() + "：-" + model.getMoney());//标题
//                        holder.setText(R.id.textView2, getString(R.string.recharge_h21) + model.getId());//流水号
                        holder.setText(R.id.textView3, model.getCreated_at());//时间
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
                //提现
                CommonUtil.gotoActivity(this, TakeCashActivity.class, false);
                break;
            case R.id.head1_linearLayout2:
                //设备
                CommonUtil.gotoActivity(this, MyMachineActivity.class, false);
                /*Bundle bundle = new Bundle();
                bundle.putInt("item", 3);
                CommonUtil.gotoActivityWithFinishOtherAllAndData(this, MainActivity.class, bundle, true);*/
//                CommonUtil.gotoActivity(this, RechargeActivity.class, false);
                break;
        }
    }

    private void changeUI() {
        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.green));
            textView2.setTextColor(getResources().getColor(R.color.black2));
//            head2_textView1.setTextColor(getResources().getColor(R.color.blue));
//            head2_textView2.setTextColor(getResources().getColor(R.color.black4));

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
//            head2_view1.setVisibility(View.VISIBLE);
//            head2_view2.setVisibility(View.INVISIBLE);

            if (list1.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter1);
//                mAdapter1.notifyDataSetChanged();
            } else {
                showEmptyPage();
            }

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.black2));
            textView2.setTextColor(getResources().getColor(R.color.green));
//            head2_textView1.setTextColor(getResources().getColor(R.color.black4));
//            head2_textView2.setTextColor(getResources().getColor(R.color.blue));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
//            head2_view1.setVisibility(View.INVISIBLE);
//            head2_view2.setVisibility(View.VISIBLE);
            if (list2.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter2);
//                mAdapter2.notifyDataSetChanged();
            } else {
                showEmptyPage();
            }

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
//        titleView.setTitle(getString(R.string.qianbao_h1));
        titleView.setVisibility(View.GONE);
    }
}
