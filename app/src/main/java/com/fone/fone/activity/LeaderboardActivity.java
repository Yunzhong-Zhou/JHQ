package com.fone.fone.activity;

import android.os.Bundle;
import android.view.View;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.LeaderboardModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by zyz on 2019/5/26.
 * 排行榜
 */
public class LeaderboardActivity extends BaseActivity {
    /*ImageView imageView1, imageView2, imageView3, imageView4;
    TextView textView1_1, textView1_2,
            textView2_1, textView2_2,
            textView3_1, textView3_2,
            textView4_0, textView4_1, textView4_2;*/

    RecyclerView recyclerView;
    List<LeaderboardModel.RankListBean> list = new ArrayList<>();
    CommonAdapter<LeaderboardModel.RankListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        findViewByID_My(R.id.left_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

       /* imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
        imageView3 = findViewByID_My(R.id.imageView3);
        imageView4 = findViewByID_My(R.id.imageView4);
        textView1_1 = findViewByID_My(R.id.textView1_1);
        textView1_2 = findViewByID_My(R.id.textView1_2);
        textView2_1 = findViewByID_My(R.id.textView2_1);
        textView2_2 = findViewByID_My(R.id.textView2_2);
        textView3_1 = findViewByID_My(R.id.textView3_1);
        textView3_2 = findViewByID_My(R.id.textView3_2);
        textView4_0 = findViewByID_My(R.id.textView4_0);
        textView4_1 = findViewByID_My(R.id.textView4_1);
        textView4_2 = findViewByID_My(R.id.textView4_2);*/
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
                /*if (response.getRank_list().size() > 0) {
                    textView1_1.setText(response.getRank_list().get(0).getNickname());
                    textView1_2.setText(response.getRank_list().get(0).getWin_money());
                    if (!response.getRank_list().get(0).getHead().equals(""))
                        Glide.with(LeaderboardActivity.this).load(IMGHOST + response.getRank_list().get(0).getHead())
                                .centerCrop().into(imageView1);//加载图片
                }

                //第二名
                if (response.getRank_list().size() > 1) {
                    textView2_1.setText(response.getRank_list().get(1).getNickname());
                    textView2_2.setText(response.getRank_list().get(1).getWin_money());
                    if (!response.getRank_list().get(1).getHead().equals(""))
                        Glide.with(LeaderboardActivity.this).load(IMGHOST + response.getRank_list().get(1).getHead())
                                .centerCrop().into(imageView2);//加载图片
                }
                //第三名
                if (response.getRank_list().size() > 2) {
                    textView3_1.setText(response.getRank_list().get(2).getNickname());
                    textView3_2.setText(response.getRank_list().get(2).getWin_money());

                    if (!response.getRank_list().get(2).getHead().equals(""))
                        Glide.with(LeaderboardActivity.this).load(IMGHOST + response.getRank_list().get(2).getHead())
                                .centerCrop().into(imageView3);//加载图片
                }

                //我自己
                textView4_0.setText(response.getMy_rank().getRank());
                textView4_1.setText(response.getMy_rank().getNickname());
                textView4_2.setText(response.getMy_rank().getWin_money());
                if (!response.getMy_rank().getHead().equals(""))
                    Glide.with(LeaderboardActivity.this)
                            .load(IMGHOST + response.getMy_rank().getHead())
                            .centerCrop().into(imageView4);//加载图片*/

                //列表
                /*list = response.getRank_list();
                if (response.getRank_list().size() > 3) {
                    //去掉前三个
                    for (int i = 0; i < 3; i++) {
                        list.remove(0);
                    }
                    mAdapter = new CommonAdapter<LeaderboardModel.RankListBean>(LeaderboardActivity.this, R.layout.item_leaderboard, list) {
                        @Override
                        protected void convert(ViewHolder holder, final LeaderboardModel.RankListBean model, int position) {

                            holder.setText(R.id.textView_0, position + 4 + "");//排名
                            holder.setText(R.id.textView_1, model.getNickname());//昵称
                            holder.setText(R.id.textView_2, model.getWin_money());//累计奖金
                            ImageView imageView = holder.getView(R.id.imageView);
                            if (!model.getHead().equals(""))
                                Glide.with(LeaderboardActivity.this).load(IMGHOST + model.getHead())
                                        .centerCrop().into(imageView);//加载图片

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
                }*/
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
        }
    }


    @Override
    public void requestServer() {
        super.requestServer();
        showProgress(true, getString(R.string.app_loading));
        request("?token=" + localUserInfo.getToken());

    }

    @Override
    protected void updateView() {
//        titleView.setTitle(getString(R.string.fragment3_h2));
        titleView.setVisibility(View.GONE);
    }
}
