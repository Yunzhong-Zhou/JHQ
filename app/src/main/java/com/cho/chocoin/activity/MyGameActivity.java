package com.cho.chocoin.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.cho.chocoin.R;
import com.cho.chocoin.base.BaseActivity;
import com.cho.chocoin.model.MyGameModel;
import com.cho.chocoin.net.OkHttpClientManager;
import com.cho.chocoin.net.URLs;
import com.cho.chocoin.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyz on 2019/5/26.
 */
public class MyGameActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<MyGameModel.LikeGameParticipationListBean> list = new ArrayList<>();
    CommonAdapter<MyGameModel.LikeGameParticipationListBean> mAdapter;

    TextView textView1, textView2, textView3;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygame);
    }

    @Override
    protected void initView() {
        setSpringViewMore(true);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                page = 1;
                String string = "?page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestList(string);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                String string = "?page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestListMore(string);
            }
        });

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);


        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    protected void initData() {
        requestServer();
    }

    private void RequestList(String string) {
        OkHttpClientManager.getAsyn(MyGameActivity.this, URLs.MyGame + string, new OkHttpClientManager.ResultCallback<MyGameModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(MyGameModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>我的游戏" + response);
                if (response != null) {
                    textView1.setText(response.getWin_money());
                    textView2.setText(response.getGame_num() + "");
                    textView3.setText(response.getWin_num() + "");

                    list = response.getLike_game_participation_list();
                    if (list.size() > 0) {
                        mAdapter = new CommonAdapter<MyGameModel.LikeGameParticipationListBean>(
                                MyGameActivity.this, R.layout.item_mygame, list) {
                            @Override
                            protected void convert(ViewHolder holder, MyGameModel.LikeGameParticipationListBean model, int position) {
                                holder.setText(R.id.textView1, model.getLike_game_period());
                                holder.setText(R.id.textView2, model.getMoney() + getString(R.string.app_ge));
                                holder.setText(R.id.textView3, model.getBureau_win_money() + getString(R.string.app_ge));
                            }
                        };
                        recyclerView.setAdapter(mAdapter);
                    } else {
                        showEmptyPage();
                    }

                }


            }
        });
    }

    private void RequestListMore(String string) {
        OkHttpClientManager.getAsyn(MyGameActivity.this, URLs.MyGame + string, new OkHttpClientManager.ResultCallback<MyGameModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(MyGameModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>我的游戏列表更多" + response);
                List<MyGameModel.LikeGameParticipationListBean> list1 = new ArrayList<>();
                    list1 = response.getLike_game_participation_list();
                    if (list1.size() == 0) {
                        myToast(getString(R.string.app_nomore));
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
//        showProgress(true, getString(R.string.app_loading2));
        page = 1;
        String string = "?page=" + page//当前页号
                + "&count=" + "10"//页面行数
                + "&token=" + localUserInfo.getToken();
        RequestList(string);
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.game_h1));
    }
}
