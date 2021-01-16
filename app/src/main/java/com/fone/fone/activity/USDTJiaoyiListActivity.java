package com.fone.fone.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cy.dialog.BaseDialog;
import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.USDTJiaoyiListModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
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
 * Created by Mr.Z on 2020/10/22.
 * USDT交易记录
 */
public class USDTJiaoyiListActivity extends BaseActivity {
    String id = "";
    private RecyclerView recyclerView;
    List<USDTJiaoyiListModel.DoUsdtDealListBean> list = new ArrayList<>();
    CommonAdapter<USDTJiaoyiListModel.DoUsdtDealListBean> mAdapter;

    int page = 1;
    String type = "", status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usdtjiaoyilist);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);

    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
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
                        + "&status=" + status
                        + "&type=" + type
                        + "&page=" + page//当前页号
                        + "&count=" + "10";//页面行数;
                Request(string);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                String string = "?token=" + localUserInfo.getToken()
                        + "&drvt_buy_id=" + id
                        + "&status=" + status
                        + "&type=" + type
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
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.USDTJiaoYiList + string, new OkHttpClientManager.ResultCallback<USDTJiaoyiListModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(USDTJiaoyiListModel response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>交易列表" + response);

                if (response.getDo_usdt_deal_list() != null) {

                    list = response.getDo_usdt_deal_list();
                    if (list.size() > 0) {
                        showContentPage();
                        mAdapter = new CommonAdapter<USDTJiaoyiListModel.DoUsdtDealListBean>
                                (USDTJiaoyiListActivity.this, R.layout.item_usdtjiaoyilist, list) {
                            @Override
                            protected void convert(ViewHolder holder, USDTJiaoyiListModel.DoUsdtDealListBean model, int position) {
                                TextView tv_title = holder.getView(R.id.tv_title);//标题
//                                tv_title.setText("【" + model.getType_title() + "】" + model.getMoney() + "DRVT");
                                tv_title.setText("" + model.getType_title() + "");
                                if (model.getType().equals("sell")) {
                                    tv_title.setTextColor(getResources().getColor(R.color.red_1));
                                } else {
                                    tv_title.setTextColor(getResources().getColor(R.color.green_1));
                                }
//                                holder.setText(R.id.tv_time, model.getCreated_at());//时间
                                holder.setText(R.id.tv_jiage, model.getUsdt_money()+ "");//价格
                                holder.setText(R.id.tv_zonge, model.getStatus_title() + "");//交易总额
                                holder.setText(R.id.tv_nickneame, model.getCreated_at());//昵称

                            }
                        };
                        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                if (list.get(i).getStatus() != 1) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", list.get(i).getId());
                                    CommonUtil.gotoActivityWithData(USDTJiaoyiListActivity.this, USDTOrderInfoActivity.class, bundle, false);
                                }
                            }

                            @Override
                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                return false;
                            }
                        });
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
        OkHttpClientManager.getAsyn(this, URLs.USDTJiaoYiList + string, new OkHttpClientManager.ResultCallback<USDTJiaoyiListModel>() {
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
            public void onResponse(USDTJiaoyiListModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>DRVT列表更多" + response);
                List<USDTJiaoyiListModel.DoUsdtDealListBean> list_1 = new ArrayList<>();
                list_1 = response.getDo_usdt_deal_list();
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
            case R.id.right_btn:
                //筛选
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
                rv.setLayoutManager(new LinearLayoutManager(USDTJiaoyiListActivity.this));
                List<String> list = new ArrayList<>();
                list.add(getString(R.string.app_type_quanbu));
                list.add(getString(R.string.fragment5_h70));
                list.add(getString(R.string.fragment5_h71));
                list.add(getString(R.string.fragment5_h72));
                list.add(getString(R.string.fragment5_h73));
                list.add(getString(R.string.fragment5_h74));
                list.add(getString(R.string.fragment5_h75));
                list.add(getString(R.string.fragment5_h76));
                list.add(getString(R.string.fragment5_h77));
                list.add(getString(R.string.fragment5_h78));
                list.add(getString(R.string.fragment5_h79));
                CommonAdapter<String> adapter = new CommonAdapter<String>
                        (USDTJiaoyiListActivity.this, R.layout.item_shaixuan, list) {
                    @Override
                    protected void convert(ViewHolder holder, String model, int position) {
                        holder.setText(R.id.tv, model);
                    }
                };
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        dialog.dismiss();
                        switch (i) {
                            case 0:
                                //全部
                                status = "";
                                type = "";
                                break;
                            case 1:
                                //买入
                                status = "";
                                type = "buy";
                                break;
                            case 2:
                                //卖出
                                status = "";
                                type = "sell";
                                break;
                            default:
                                status = (i - 2) + "";
                                type = "";
                                break;
                        }

                        requestServer();
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                rv.setAdapter(adapter);
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
                + "&status=" + status
                + "&type=" + type
                + "&page=" + page//当前页号
                + "&count=" + "10";//页面行数;
        Request(string);
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
