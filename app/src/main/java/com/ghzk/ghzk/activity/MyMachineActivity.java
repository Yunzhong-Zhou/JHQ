package com.ghzk.ghzk.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cy.dialog.BaseDialog;
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
    TextView textView1, textView2, textView3, textView4, textView5, textView6;
    private RecyclerView recyclerView;
    List<MyMachineModel.OrderGoodsListBean> list = new ArrayList<>();
    CommonAdapter<MyMachineModel.OrderGoodsListBean> mAdapter;
    int page = 1;
    String buy_type = "", status = "", operation_type = "", source_type = "";//算力：1,2 矿机：3

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
                        + "&buy_type=" + buy_type
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
                        + "&buy_type=" + buy_type
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
                textView1.setText("" + response.getEarning_money());//总收益
                textView2.setText(response.getHas_run_count() + getString(R.string.app_tai));//已安装
                textView3.setText(response.getWait_install_count() + "");//待结算
                textView4.setText(response.getAmount_count() + "");//总数量
                textView5.setText(response.getAgency_count() + "");//代运营
                textView6.setText(response.getSelf_count() + "");//自运营
                list = response.getOrder_goods_list();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<MyMachineModel.OrderGoodsListBean>
                            (MyMachineActivity.this, R.layout.item_mymachine, list) {
                        @Override
                        protected void convert(ViewHolder holder, MyMachineModel.OrderGoodsListBean model, int position) {
                            LinearLayout linearLayout = holder.getView(R.id.linearLayout);
                            TextView tv1 = holder.getView(R.id.tv1);
                            TextView tv2 = holder.getView(R.id.tv2);
                            TextView tv3 = holder.getView(R.id.tv3);
                            TextView tv4 = holder.getView(R.id.tv4);
                            TextView tv5 = holder.getView(R.id.tv5);
                            TextView tv6 = holder.getView(R.id.tv6);
                            TextView tv7 = holder.getView(R.id.tv7);
                            TextView tv8 = holder.getView(R.id.tv8);

                            tv1.setText(model.getSource_type_title());
                            if (model.getSource_type() ==1){
                                tv1.setVisibility(View.INVISIBLE);
                            }else {
                                tv1.setVisibility(View.VISIBLE);
                            }

                            if (model.getSource_type() ==6 || model.getSource_type() ==9){
                                linearLayout.setBackgroundResource(R.drawable.yuanjiao_10_huise3);
                            }else {
                                linearLayout.setBackgroundResource(R.drawable.yuanjiao_10_baise);
                            }

                            tv2.setText(model.getPut_title());
                            tv3.setText("￥ " + model.getEarning_money());
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
                + "&buy_type=" + buy_type
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
        titleView.setRightBtn(R.mipmap.ic_shaixuan, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.contentView(R.layout.dialog_shaixuan)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.BOTTOM)
                        .dimAmount(0.6f)
                        .show();
                //标签
                RecyclerView rv = dialog.findViewById(R.id.rv);
                rv.setLayoutManager(new LinearLayoutManager(MyMachineActivity.this));
                List<String> list = new ArrayList<>();
                list.add(getString(R.string.app_type_quanbu));
                list.add(getString(R.string.app_type1));
                list.add(getString(R.string.app_type12));
                list.add(getString(R.string.app_type2));
                list.add(getString(R.string.app_type3));
                list.add(getString(R.string.app_type4));
                list.add(getString(R.string.app_type5));
                list.add(getString(R.string.app_type6));
                list.add(getString(R.string.app_type7));
                list.add(getString(R.string.app_type8));
                list.add(getString(R.string.app_type9));
                list.add(getString(R.string.app_type10));
                list.add(getString(R.string.app_type11));

                CommonAdapter<String> adapter = new CommonAdapter<String>
                        (MyMachineActivity.this, R.layout.item_shaixuan, list) {
                    @Override
                    protected void convert(ViewHolder holder, String model, int position) {
                        holder.setText(R.id.tv, model);
                    }
                };
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        dialog.dismiss();
                        if (i == 0){
                            status = "";
                        }else {
                            status = i+"";
                        }

                        requestServer();
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv.setAdapter(adapter);
            }
        });
    }
}
