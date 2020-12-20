package com.fone.fone.activity;

import android.os.Bundle;
import android.view.View;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.JoinListModel;
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
 * Created by zyz on 2019/1/7.
 * 加入拼团列表
 */
public class JoinListActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<JoinListModel.ChangeGameListBean> list = new ArrayList<>();
    CommonAdapter<JoinListModel.ChangeGameListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinlist);
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

        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    protected void initData() {
        requestServer();
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.JoinList + string, new OkHttpClientManager.ResultCallback<JoinListModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(JoinListModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>拼团列表" + response);
                if (response != null) {
                    list = response.getChange_game_list();
                    if (list.size() > 0) {
                        mAdapter = new CommonAdapter<JoinListModel.ChangeGameListBean>(
                                JoinListActivity.this, R.layout.item_joinlist, list) {
                            @Override
                            protected void convert(ViewHolder holder, JoinListModel.ChangeGameListBean model, int position) {
                                holder.setText(R.id.tv_num, model.getPeriod());//期次号
                                holder.setText(R.id.tv_time, model.getCreated_at() + "");//时间
                                holder.setText(R.id.tv_title, model.getStatus_title());//标题
//                                holder.setText(R.id.tv_money, model.get);//金额
//                                holder.setText(R.id.tv_name, model.get);//name
//                                holder.setText(R.id.tv_type, model.get);//name

                                /*ImageView imageView = holder.getView(R.id.imageView1);
                                Glide.with(JoinListActivity.this)
                                        .load(OkHttpClientManager.IMGHOST + model.getWin_member().)
                                        .centerCrop()
                                        .placeholder(R.mipmap.loading)//加载站位图
                                        .error(R.mipmap.headimg)//加载失败
                                        .into(imageView);//加载图片*/


                            }
                        };
                        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                                Bundle bundle = new Bundle();
                                bundle.putString("id", list.get(position).getPeriod());
                                CommonUtil.gotoActivityWithData(JoinListActivity.this, JoinDetailActivity.class, bundle, false);
                            }

                            @Override
                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                                return false;
                            }
                        });
                        recyclerView.setAdapter(mAdapter);
                    } else {
                        showEmptyPage();
                    }

                }


            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
//        showProgress(true, getString(R.string.app_loading));
        String string = "?token=" + localUserInfo.getToken();
        Request(string);
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.fragment3_h27));
    }
}
