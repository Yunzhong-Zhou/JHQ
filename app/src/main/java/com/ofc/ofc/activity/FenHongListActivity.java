package com.ofc.ofc.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.FenHongListModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.MyLogger;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mr.Z on 2020/7/15.
 * 分红记录
 */
public class FenHongListActivity extends BaseActivity {
    String id = "";
    TextView textView1, textView2, textView3, textView4, textView5, textView6;
    private RecyclerView recyclerView;
    List<FenHongListModel.InvestBean.InterestListBean> list = new ArrayList<>();
    CommonAdapter<FenHongListModel.InvestBean.InterestListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenhonglist);
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                String string = "?token=" + localUserInfo.getToken() +
                        "&id=" + id;
                Request(string);
            }

            @Override
            public void onLoadmore() {

            }
        });
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);

        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        requestServer();
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.FenHongDetail + string, new OkHttpClientManager.ResultCallback<FenHongListModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(FenHongListModel response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>分红详情" + response);

                if (response.getInvest() !=null){
                    textView1.setText(response.getInvest().getOfc_money());//币数量
                    textView2.setText(response.getInvest().getAppreciation() + "%");//已增值
                    textView3.setText(response.getInvest().getInterest_money());//已分红
                    textView4.setText(response.getInvest().getOfc_price());//买入价
                    textView5.setText(response.getInvest().getType_title());//币来源
                    textView6.setText(response.getInvest().getUnfreeze_at());//解冻时间

                    list = response.getInvest().getInterest_list();
                    if (list.size() > 0) {
                        showContentPage();
                        mAdapter = new CommonAdapter<FenHongListModel.InvestBean.InterestListBean>
                                (FenHongListActivity.this, R.layout.item_fenhonglist, list) {
                            @Override
                            protected void convert(ViewHolder holder, final FenHongListModel.InvestBean.InterestListBean model, int position) {
                                holder.setText(R.id.textView1, "+"+model.getRate() );//指数
                                holder.setText(R.id.textView2, model.getMoney() + "USDT");//标题
//                            holder.setText(R.id.textView3, );//时间
                                holder.setText(R.id.textView4, model.getCreated_at());//状态

                            }
                        };
                        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
//                        CommonUtil.gotoActivity(AccountDetailActivity.this,FenHongListActivity.class,false);
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

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        String string = "?token=" + localUserInfo.getToken() +
                "&id=" + id;
        Request(string);
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.qianbao_h26));
    }
}
