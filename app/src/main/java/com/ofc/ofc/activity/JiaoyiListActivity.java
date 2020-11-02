package com.ofc.ofc.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.JiaoyiListModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mr.Z on 2020/10/22.
 * DRVT交易记录
 */
public class JiaoyiListActivity extends BaseActivity {
    String id = "";
    private RecyclerView recyclerView;
    List<JiaoyiListModel.DoDrvtSellListBean> list = new ArrayList<>();
    CommonAdapter<JiaoyiListModel.DoDrvtSellListBean> mAdapter;

    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiaoyilist);
        findViewByID_My(R.id.title).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);

    }

    @Override
    protected void initView() {
        setSpringViewMore(true);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                String string = "?token=" + localUserInfo.getToken()
                        + "&drvt_buy_id=" + id
                        + "&page=" + page//当前页号
                        + "&count=" + "10";//页面行数;
                Request(string);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                String string = "?token=" + localUserInfo.getToken()
                        + "&drvt_buy_id=" + id
                        + "&page=" + page//当前页号
                        + "&count=" + "10";//页面行数
                RequestMore(string);
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        requestServer();
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.JiaoYiList + string, new OkHttpClientManager.ResultCallback<JiaoyiListModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(JiaoyiListModel response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>交易列表" + response);

                if (response.getDo_drvt_sell_list() != null) {

                    list = response.getDo_drvt_sell_list();
                    if (list.size() > 0) {
                        showContentPage();
                        mAdapter = new CommonAdapter<JiaoyiListModel.DoDrvtSellListBean>
                                (JiaoyiListActivity.this, R.layout.item_jiaoyilist, list) {
                            @Override
                            protected void convert(ViewHolder holder, JiaoyiListModel.DoDrvtSellListBean model, int position) {
                                TextView tv_title = holder.getView(R.id.tv_title);//标题
                                tv_title.setText("【"+model.getType_title()+"】"+model.getMoney()+"DRVT");

                                if (model.getType().equals("sell")){
                                    tv_title.setTextColor(getResources().getColor(R.color.red_1));
                                }else {
                                    tv_title.setTextColor(getResources().getColor(R.color.green_1));
                                }
                                holder.setText(R.id.tv_time, model.getCreated_at());//时间
                                holder.setText(R.id.tv_jiage,  model.getDrvt_price() + "");//价格
                                holder.setText(R.id.tv_zonge,model.getUsdt_money()+"");//交易总额
                                holder.setText(R.id.tv_nickneame, model.getOpposite_member_nickname());//昵称

                            }
                        };
                        recyclerView.setAdapter(mAdapter);
                    } else {
                        showEmptyPage();
                    }

                }

                hideProgress();
            }
        });
    }

    private void RequestMore(String string) {
        OkHttpClientManager.getAsyn(this, URLs.JiaoYiList + string, new OkHttpClientManager.ResultCallback<JiaoyiListModel>() {
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
            public void onResponse(JiaoyiListModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>DRVT列表更多" + response);
                List<JiaoyiListModel.DoDrvtSellListBean> list_1 = new ArrayList<>();
                list_1 = response.getDo_drvt_sell_list();
                if (list_1.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                    page--;
                } else {
                    list.addAll(list_1);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

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
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        page = 1;
        String string = "?token=" + localUserInfo.getToken()
                + "&drvt_buy_id=" + id
                + "&page=" + page//当前页号
                + "&count=" + "10";//页面行数;
        Request(string);
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
