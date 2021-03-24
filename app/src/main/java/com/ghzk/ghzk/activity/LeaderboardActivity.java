package com.ghzk.ghzk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ghzk.ghzk.R;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.model.LeaderboardModel;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
import com.ghzk.ghzk.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.ghzk.ghzk.net.OkHttpClientManager.IMGHOST;


/**
 * Created by zyz on 2019/5/26.
 * 排行榜
 */
public class LeaderboardActivity extends BaseActivity {
    ImageView iv_head;
    TextView tv_num, tv_name, tv_pinzhong, tv_kuangji, tv_money;

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

        iv_head = findViewByID_My(R.id.iv_head);
        tv_num = findViewByID_My(R.id.tv_num);
        tv_name = findViewByID_My(R.id.tv_name);
        tv_pinzhong = findViewByID_My(R.id.tv_pinzhong);
        tv_kuangji = findViewByID_My(R.id.tv_kuangji);
        tv_money = findViewByID_My(R.id.tv_money);

    }

    @Override
    protected void initData() {
        requestServer();
    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.Leaderboard + string, new OkHttpClientManager.ResultCallback<LeaderboardModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showEmptyPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(LeaderboardModel response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>排行榜" + response);
                //我自己
                tv_num.setText(response.getMy_rank().getRank());
                tv_name.setText(response.getMy_rank().getNickname());
                tv_money.setText(response.getMy_rank().getHashrate() + "TB");
                tv_pinzhong.setText(getString(R.string.fragment3_h28) + response.getMy_rank().getChange_game_win_time() + getString(R.string.app_ci));
                tv_kuangji.setText(getString(R.string.fragment3_h29) + response.getMy_rank().getChange_game_win_time() + getString(R.string.app_tai));

                Glide.with(LeaderboardActivity.this)
                        .load(IMGHOST + response.getMy_rank().getHead())
                        .centerCrop()
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.headimg)//加载失败
                        .into(iv_head);//加载图片

                //列表
                list = response.getRank_list();
                /*//去掉前三个
                    for (int i = 0; i < 3; i++) {
                        list.remove(0);
                    }*/
                if (response.getRank_list().size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<LeaderboardModel.RankListBean>(LeaderboardActivity.this,
                            R.layout.item_leaderboard, list) {
                        @Override
                        protected void convert(ViewHolder holder, final LeaderboardModel.RankListBean model, int position) {
                            TextView tv_num = holder.getView(R.id.tv_num);
                            switch (position) {
                                case 0:
                                    //第一名
                                    tv_num.setBackgroundResource(R.mipmap.ic_paihang_1);
                                    tv_num.setText("");
                                    break;
                                case 1:
                                    //第二名
                                    tv_num.setBackgroundResource(R.mipmap.ic_paihang_2);
                                    tv_num.setText("");
                                    break;
                                case 2:
                                    //第三名
                                    tv_num.setBackgroundResource(R.mipmap.ic_paihang_3);
                                    tv_num.setText("");
                                    break;
                                default:
                                    tv_num.setBackgroundResource(R.color.transparent);
                                    tv_num.setText(position + 1 + "");
                                    break;
                            }

                            holder.setText(R.id.tv_name, model.getNickname());//昵称
                            holder.setText(R.id.tv_money, model.getHashrate() + "TB");//累计奖金
                            holder.setText(R.id.tv_pinzhong, getString(R.string.fragment3_h28) + model.getChange_game_win_time() + getString(R.string.app_ci));
                            holder.setText(R.id.tv_kuangji, getString(R.string.fragment3_h29) + model.getChange_game_win_time() + getString(R.string.app_tai));

                            ImageView imageView = holder.getView(R.id.iv_head);
                            Glide.with(LeaderboardActivity.this)
                                    .load(IMGHOST + model.getHead())
                                    .centerCrop()
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView);//加载图片

                        }
                    };
                    recyclerView.setAdapter(mAdapter);

                } else {
                    showEmptyPage();
                }
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
