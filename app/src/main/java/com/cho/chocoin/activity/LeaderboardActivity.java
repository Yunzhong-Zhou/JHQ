package com.cho.chocoin.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cho.chocoin.R;
import com.cho.chocoin.base.BaseActivity;
import com.cho.chocoin.model.LeaderboardModel;
import com.cho.chocoin.net.OkHttpClientManager;
import com.cho.chocoin.net.URLs;
import com.cho.chocoin.utils.MyLogger;
import com.bumptech.glide.Glide;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyz on 2019/5/26.
 * 排行榜
 */
public class LeaderboardActivity extends BaseActivity {
    ImageView imageView1, imageView2, imageView3, imageView4;
    TextView textView1_1, textView1_2, textView1_3,
            textView2_1, textView2_2, textView2_3,
            textView3_1, textView3_2, textView3_3,
            textView4_0, textView4_1, textView4_2, textView4_3;

    RecyclerView recyclerView;
    List<LeaderboardModel.RankListBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

    }

    @Override
    protected void initView() {
        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                String string = "?token=" + localUserInfo.getToken();
                request(string);
            }

            @Override
            public void onLoadmore() {

            }
        });

        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
        imageView3 = findViewByID_My(R.id.imageView3);
        imageView4 = findViewByID_My(R.id.imageView4);
        textView1_1 = findViewByID_My(R.id.textView1_1);
        textView1_2 = findViewByID_My(R.id.textView1_2);
        textView1_3 = findViewByID_My(R.id.textView1_3);

        textView2_1 = findViewByID_My(R.id.textView2_1);
        textView2_2 = findViewByID_My(R.id.textView2_2);
        textView2_3 = findViewByID_My(R.id.textView2_3);

        textView3_1 = findViewByID_My(R.id.textView3_1);
        textView3_2 = findViewByID_My(R.id.textView3_2);
        textView3_3 = findViewByID_My(R.id.textView3_3);

        textView4_0 = findViewByID_My(R.id.textView4_0);
        textView4_1 = findViewByID_My(R.id.textView4_1);
        textView4_2 = findViewByID_My(R.id.textView4_2);
        textView4_3 = findViewByID_My(R.id.textView4_3);
    }

    @Override
    protected void initData() {
        requestServer();
    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.Leaderboard + string, new OkHttpClientManager.ResultCallback<LeaderboardModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(LeaderboardModel response) {
                MyLogger.i(">>>>>>>>>排行榜" + response);
                //第一名
                if (response.getRank_list().size() > 0) {
                    textView1_1.setText(response.getRank_list().get(0).getNickname());
                    textView1_2.setText(response.getRank_list().get(0).getWin_money());
                    textView1_3.setText(getString(R.string.fragment2_h20) + response.getRank_list().get(0).getWin_num() + getString(R.string.app_chang));
                    if (!response.getRank_list().get(0).getHead().equals(""))
                        Glide.with(LeaderboardActivity.this).load(OkHttpClientManager.IMGHOST + response.getRank_list().get(0).getHead())
                                .centerCrop().into(imageView1);//加载图片
                    else
                        imageView1.setImageResource(R.mipmap.headimg);
                }

                //第二名
                if (response.getRank_list().size() > 1) {
                    textView2_1.setText(response.getRank_list().get(1).getNickname());
                    textView2_2.setText(response.getRank_list().get(1).getWin_money());
                    textView2_3.setText(getString(R.string.fragment2_h20) + response.getRank_list().get(1).getWin_num() + getString(R.string.app_chang));
                    if (!response.getRank_list().get(1).getHead().equals(""))
                        Glide.with(LeaderboardActivity.this).load(OkHttpClientManager.IMGHOST + response.getRank_list().get(1).getHead())
                                .centerCrop().into(imageView2);//加载图片
                    else
                        imageView2.setImageResource(R.mipmap.headimg);
                }
                //第三名
                if (response.getRank_list().size() > 2) {
                    textView3_1.setText(response.getRank_list().get(2).getNickname());
                    textView3_2.setText(response.getRank_list().get(2).getWin_money());
                    textView3_3.setText(getString(R.string.fragment2_h20) + response.getRank_list().get(2).getWin_num() + getString(R.string.app_chang));
                    if (!response.getRank_list().get(2).getHead().equals(""))
                        Glide.with(LeaderboardActivity.this).load(OkHttpClientManager.IMGHOST + response.getRank_list().get(2).getHead())
                                .centerCrop().into(imageView3);//加载图片
                    else
                        imageView3.setImageResource(R.mipmap.headimg);
                }

                //我自己
                textView4_0.setText(response.getMy_rank().getRank() + "");
                textView4_1.setText(response.getMy_rank().getNickname());
                textView4_2.setText(response.getMy_rank().getWin_money());
                textView4_3.setText(getString(R.string.fragment2_h20) + response.getMy_rank().getWin_num() + getString(R.string.app_chang));
                if (!response.getMy_rank().getHead().equals(""))
                    Glide.with(LeaderboardActivity.this)
                            .load(OkHttpClientManager.IMGHOST + response.getMy_rank().getHead())
                            .centerCrop()
                            .into(imageView4);//加载图片
                else
                    imageView4.setImageResource(R.mipmap.headimg);
                /*for (int i = 0; i < response.getRank_list().size(); i++) {
                    if (response.getRank_list().get(i).getNickname().equals(localUserInfo.getNickname())) {

                    }
                }*/
                //列表
                list = response.getRank_list();
                if (response.getRank_list().size() > 3) {
                    //去掉前三个
                    for (int i = 0; i < 3; i++) {
                        list.remove(0);
                    }
                    CommonAdapter<LeaderboardModel.RankListBean> mAdapter = new CommonAdapter<LeaderboardModel.RankListBean>
                            (LeaderboardActivity.this, R.layout.item_leaderboard, list) {
                        @Override
                        protected void convert(ViewHolder holder, final LeaderboardModel.RankListBean model, int position) {

                            holder.setText(R.id.textView_0, position + 4 + "");//排名
                            holder.setText(R.id.textView_1, model.getNickname());//昵称
                            holder.setText(R.id.textView_2, model.getWin_money());//累计奖金
                            holder.setText(R.id.textView_3, getString(R.string.fragment2_h20) + model.getWin_num() + getString(R.string.app_chang));//等级

                            ImageView imageView = holder.getView(R.id.imageView);
                            if (!model.getHead().equals(""))
                                Glide.with(LeaderboardActivity.this).load(OkHttpClientManager.IMGHOST + model.getHead())
                                        .centerCrop().into(imageView);//加载图片
                            else
                                imageView.setImageResource(R.mipmap.headimg);

                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });
                }


                hideProgress();
            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
        showProgress(true, getString(R.string.app_loading));
        request("?token=" + localUserInfo.getToken());

    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.fragment3_h2));
    }
}
