package com.ghzk.ghzk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ghzk.ghzk.R;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.model.MyMachineModel;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
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
 * 我的矿机
 */
public class MyMachineActivity extends BaseActivity {
    TextView textView1, textView2,textView3,textView4,textView5,textView6;
    private RecyclerView recyclerView;
    List<MyMachineModel.OrderGoodsListBean> list = new ArrayList<>();
    CommonAdapter<MyMachineModel.OrderGoodsListBean> mAdapter;
    int page = 1;
    String mill_type = "3", status = "";//算力：1,2 矿机：3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymachine);

//        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
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
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);


    }

    @Override
    protected void initData() {
        requestServer();//获取数据
    }

    private void RequestList(String string) {
        OkHttpClientManager.getAsyn(MyMachineActivity.this, URLs.MachineList + string, new OkHttpClientManager.ResultCallback<MyMachineModel>() {
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
                textView1.setText(""+response.getEarning_money());//总收益
                textView2.setText(response.getHas_run_count() + getString(R.string.app_tai));//已安装
                textView3.setText(response.getWait_install_count()+"%");//分成比
                textView4.setText(response.getAmount_count()+"");//总数量
                textView5.setText(response.getAgency_count()+"");//代运营
                textView6.setText(response.getSelf_count()+"");//自运营
                list = response.getOrder_goods_list();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<MyMachineModel.OrderGoodsListBean>
                            (MyMachineActivity.this, R.layout.item_mymachine, list) {
                        @Override
                        protected void convert(ViewHolder holder, MyMachineModel.OrderGoodsListBean model, int position) {
                            TextView tv1 = holder.getView(R.id.tv1);
                            TextView tv2 = holder.getView(R.id.tv2);
                            TextView tv3 = holder.getView(R.id.tv3);
                            TextView tv4 = holder.getView(R.id.tv4);
                            TextView tv5 = holder.getView(R.id.tv5);
                            TextView tv6 = holder.getView(R.id.tv6);
                            TextView tv7 = holder.getView(R.id.tv7);
                            TextView tv8 = holder.getView(R.id.tv8);

                            tv1.setText(model.getSource_type_title());
                            tv2.setText(model.getPut_title());
                            tv3.setText("￥ "+model.getMoney());
                            tv4.setText(model.getCreated_at());
//                            tv5.setText(model.get);
                            tv6.setText(model.getPut_address());

                            tv7.setText(model.getStatus_title());
                            tv8.setText(model.getOperation_type_title());

                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            Bundle bundle1 = new Bundle();
                            bundle1.putString("id", list.get(position).getId());
                            CommonUtil.gotoActivityWithData(MyMachineActivity.this, MachineDetailActivity.class, bundle1, false);
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
        OkHttpClientManager.getAsyn(MyMachineActivity.this, URLs.MachineList + string, new OkHttpClientManager.ResultCallback<MyMachineModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
                page--;
            }

            @Override
            public void onResponse(MyMachineModel response) {
//                showContentPage();
                hideProgress();
                List<MyMachineModel.OrderGoodsListBean> list1 = new ArrayList<>();
                list1 = response.getOrder_goods_list();
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
           /* case R.id.left_btn:
                finish();
                break;*/
        }
    }

    @Override
    protected void updateView() {
//        titleView.setVisibility(View.GONE);
        titleView.setTitle(getString(R.string.myprofile_h37));
        titleView.showRightTextview(getString(R.string.fragment5_h92), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.gotoActivity(MyMachineActivity.this, MyOrderActivity.class, false);
            }
        });
    }
}
