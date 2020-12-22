package com.fone.fone.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.MyMachineModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mr.Z on 2020/12/12.
 * 我的算力
 */
public class MyCalculateActivity extends BaseActivity {
    TextView textView1, textView2;
    private RecyclerView recyclerView;
    List<MyMachineModel.InvestListBean> list = new ArrayList<>();
    CommonAdapter<MyMachineModel.InvestListBean> mAdapter;
    int page = 1;
    String mill_type = "1,2", status = "";//算力：1,2 矿机：3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycalculate);

        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
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
                        + "&mill_type=" + mill_type
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestList(string);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                        + "&mill_type=" + mill_type
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestListMore(string);
            }
        });
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
    }

    @Override
    protected void initData() {
        requestServer();//获取数据
    }

    private void RequestList(String string) {
        OkHttpClientManager.getAsyn(MyCalculateActivity.this, URLs.MachineList + string, new OkHttpClientManager.ResultCallback<MyMachineModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(MyMachineModel response) {
                showContentPage();
                hideProgress();
                textView1.setText(response.getHashrate() + "TB");
                textView2.setText(response.getFil_money() + getString(R.string.app_ge));
                list = response.getInvest_list();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<MyMachineModel.InvestListBean>
                            (MyCalculateActivity.this, R.layout.item_mycalculate, list) {
                        @Override
                        protected void convert(ViewHolder holder, MyMachineModel.InvestListBean model, int position) {
                            holder.setText(R.id.tv_type1, model.getStatus_title());//状态
                            holder.setText(R.id.tv_suanli, model.getHashrate() + "TB");//算力
                            holder.setText(R.id.tv_chanzhi, model.getHashrate() + getString(R.string.app_ge));//产值
                            holder.setText(R.id.tv_type2, model.getMill_type_title());//状态
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            Bundle bundle1 = new Bundle();
                            bundle1.putString("id", list.get(position).getId());
                            CommonUtil.gotoActivityWithData(MyCalculateActivity.this, MachineDetailActivity.class, bundle1, false);
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

    private void RequestListMore(String string) {
        OkHttpClientManager.getAsyn(MyCalculateActivity.this, URLs.MachineList + string, new OkHttpClientManager.ResultCallback<MyMachineModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
                page--;
            }

            @Override
            public void onResponse(MyMachineModel response) {
                showContentPage();
                hideProgress();
                List<MyMachineModel.InvestListBean> list1 = new ArrayList<>();
                list1 = response.getInvest_list();
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

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                + "&mill_type=" + mill_type
                + "&page=" + page//当前页号
                + "&count=" + "10"//页面行数
                + "&token=" + localUserInfo.getToken();
        RequestList(string);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
