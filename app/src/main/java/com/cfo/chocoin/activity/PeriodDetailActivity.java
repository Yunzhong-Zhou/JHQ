package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.model.PeriodDetailModel;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.MyLogger;
import com.cy.dialog.BaseDialog;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import static com.cfo.chocoin.net.OkHttpClientManager.IMGHOST;

/**
 * Created by zyz on 2019/6/13.
 * 期次详情
 */
public class PeriodDetailActivity extends BaseActivity {
    String id = "";
    boolean isOver = false;
    PeriodDetailModel model;
    int type = 1;
    private RecyclerView recyclerView;
    List<PeriodDetailModel.LikeGameBean.HistoryLikeGameParticipationListBean> list1 = new ArrayList<>();
    CommonAdapter<PeriodDetailModel.LikeGameBean.HistoryLikeGameParticipationListBean> mAdapter1;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper1;

    List<PeriodDetailModel.MyLikeGameParticipationListBean> list2 = new ArrayList<>();
    CommonAdapter<PeriodDetailModel.MyLikeGameParticipationListBean> mAdapter2;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper2;
    //头部一
    View headerView1;
    TextView head1_textView1, head1_textView2, head1_textView3, head1_textView4, head1_textView5,
            head1_textView6, head1_textView6_1, head1_textView6_2,
            head1_textView7, head1_textView7_1, head1_textView7_2,
            head1_textView9, head1_textView10,
            head1_textView11, head1_textView11_1, head1_textView11_2,
            head1_textView12, head1_textView12_1, head1_textView12_2;
    ImageView head1_imageView4, head1_imageView5;
    LinearLayout head1_imageView1,
            head1_linearLayout2, head1_linearLayout2_1, head1_linearLayout2_2,
            head1_linearLayout3, head1_linearLayout4;


    //头部二-需要悬浮的布局
    View headerView2;
    LinearLayout head2_linearLayout1, head2_linearLayout2;
    TextView head2_textView1, head2_textView2;
    View head2_view1, head2_view2;

    //悬浮部分
    LinearLayout invis;
    LinearLayout linearLayout1, linearLayout2;
    TextView textView1, textView2;
    View view1, view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perioddetail);
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                request("?token=" + localUserInfo.getToken()
                        + "&id=" + id);
            }

            @Override
            public void onLoadmore() {

            }
        });

        //悬浮部分
        invis = findViewByID_My(R.id.invis);
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);

        //头部一
        headerView1 = View.inflate(PeriodDetailActivity.this, R.layout.head_perioddetail_1, null);
        head1_imageView1 = headerView1.findViewById(R.id.head1_imageView1);
        head1_imageView4 = headerView1.findViewById(R.id.head1_imageView4);
        head1_imageView5 = headerView1.findViewById(R.id.head1_imageView5);

        head1_textView1 = headerView1.findViewById(R.id.head1_textView1);
        head1_textView2 = headerView1.findViewById(R.id.head1_textView2);
        head1_textView3 = headerView1.findViewById(R.id.head1_textView3);
        head1_textView4 = headerView1.findViewById(R.id.head1_textView4);
        head1_textView5 = headerView1.findViewById(R.id.head1_textView5);
        head1_textView6 = headerView1.findViewById(R.id.head1_textView6);
        head1_textView6_1 = headerView1.findViewById(R.id.head1_textView6_1);
        head1_textView6_2 = headerView1.findViewById(R.id.head1_textView6_2);

        head1_textView7 = headerView1.findViewById(R.id.head1_textView7);
        head1_textView7_1 = headerView1.findViewById(R.id.head1_textView7_1);
        head1_textView7_2 = headerView1.findViewById(R.id.head1_textView7_2);

        head1_textView9 = headerView1.findViewById(R.id.head1_textView9);
        head1_textView10 = headerView1.findViewById(R.id.head1_textView10);
        head1_textView11 = headerView1.findViewById(R.id.head1_textView11);
        head1_textView11_1 = headerView1.findViewById(R.id.head1_textView11_1);
        head1_textView11_2 = headerView1.findViewById(R.id.head1_textView11_2);

        head1_textView12 = headerView1.findViewById(R.id.head1_textView12);
        head1_textView12_1 = headerView1.findViewById(R.id.head1_textView12_1);
        head1_textView12_2 = headerView1.findViewById(R.id.head1_textView12_2);

        head1_linearLayout2 = headerView1.findViewById(R.id.head1_linearLayout2);
        head1_linearLayout2_1 = headerView1.findViewById(R.id.head1_linearLayout2_1);
        head1_linearLayout2_2 = headerView1.findViewById(R.id.head1_linearLayout2_2);

        head1_linearLayout3 = headerView1.findViewById(R.id.head1_linearLayout3);
        head1_linearLayout4 = headerView1.findViewById(R.id.head1_linearLayout4);


        head1_imageView1.setOnClickListener(this);
        head1_textView1.setOnClickListener(this);
        head1_textView2.setOnClickListener(this);
        head1_linearLayout3.setOnClickListener(this);
        head1_linearLayout4.setOnClickListener(this);

        //头部二
        headerView2 = View.inflate(PeriodDetailActivity.this, R.layout.head_fragment3_2, null);
        head2_linearLayout1 = headerView2.findViewById(R.id.head2_linearLayout1);
        head2_linearLayout2 = headerView2.findViewById(R.id.head2_linearLayout2);
        head2_linearLayout1.setOnClickListener(this);
        head2_linearLayout2.setOnClickListener(this);
        head2_textView1 = headerView2.findViewById(R.id.head2_textView1);
        head2_textView2 = headerView2.findViewById(R.id.head2_textView2);
        head2_view1 = headerView2.findViewById(R.id.head2_view1);
        head2_view2 = headerView2.findViewById(R.id.head2_view2);

        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(PeriodDetailActivity.this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        //listview 滑动监听
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mLinearLayoutManager.findFirstVisibleItemPosition() >= 1) {
                    invis.setVisibility(View.VISIBLE);
                    headerView2.setVisibility(View.GONE);
                } else {
                    invis.setVisibility(View.GONE);
                    headerView2.setVisibility(View.VISIBLE);
                }
            }
        });


        /*ImageView imageView = headerView1.findViewById(R.id.imageView);

        //动态设置linearLayout的高度为屏幕高度的1/4
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(getActivity()) / 3;*/

    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        requestServer();
    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(PeriodDetailActivity.this, URLs.GameDetail + string, new OkHttpClientManager.ResultCallback<PeriodDetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(final PeriodDetailModel response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>首页" + response);
                model = response;
                //期次
                head1_textView3.setText(getString(R.string.fragment3_h3) + "  " + response.getLike_game().getPeriod());
                //总投
                head1_textView5.setText(response.getLike_game().getAmount_money());

                //公链
                head1_textView11.setText(response.getLike_game().getAmount_money_1());
                head1_textView11_1.setText(response.getLike_game().getAmount_money_1());
                head1_textView11_2.setText(response.getLike_game().getAmount_money_1());

                //公链已投
                head1_textView6.setText(response.getPublic_chain_money());
                head1_textView6_1.setText(response.getPublic_chain_money());
                head1_textView6_2.setText(response.getPublic_chain_money());

                //侧链
                head1_textView12.setText(response.getLike_game().getAmount_money_2());
                head1_textView12_1.setText(response.getLike_game().getAmount_money_2());
                head1_textView12_2.setText(response.getLike_game().getAmount_money_2());

                //侧链已投
                head1_textView7.setText(response.getBroadside_chain_money());
                head1_textView7_1.setText(response.getBroadside_chain_money());
                head1_textView7_2.setText(response.getBroadside_chain_money());

                //我的奖金
                head1_textView9.setText(response.getBureau_win_money());

                //状态
                if (response.getLike_game().getStatus() == 2) {
                    head1_textView4.setText(getString(R.string.fragment3_h26));
                } else {
                    head1_textView4.setText(getString(R.string.fragment3_h28));
                }


                if (response.getLike_game().getWin_chain() == 1) {
                    //公链赢
                    head1_linearLayout2.setVisibility(View.GONE);
                    head1_linearLayout2_1.setVisibility(View.VISIBLE);
                    head1_linearLayout2_2.setVisibility(View.GONE);


                    /*head1_linearLayout2.setRotation(5f);//右倾斜
                    head1_imageView4.setVisibility(View.VISIBLE);
                    head1_imageView5.setVisibility(View.GONE);*/



                    /*head1_linearLayout1.setBackgroundResource(R.drawable.yuanjiao_5_lvse);
                    head1_textView5.setTextColor(getResources().getColor(R.color.white));
                    head1_view1.setBackgroundResource(R.color.white);
                    head1_textView11.setTextColor(getResources().getColor(R.color.white1));
                    head1_textView6.setTextColor(getResources().getColor(R.color.white));

                    head1_linearLayout2.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_qianlanse);
                    head1_textView7.setTextColor(getResources().getColor(R.color.qianblue));
                    head1_view2.setBackgroundResource(R.color.qianblue);
                    head1_textView12.setTextColor(getResources().getColor(R.color.qianblue));
                    head1_textView8.setTextColor(getResources().getColor(R.color.qianblue));*/
                } else if (response.getLike_game().getWin_chain() == 2) {
                    //侧链赢
                    head1_linearLayout2.setVisibility(View.GONE);
                    head1_linearLayout2_1.setVisibility(View.GONE);
                    head1_linearLayout2_2.setVisibility(View.VISIBLE);

                    /*head1_linearLayout2.setRotation(-5);//左倾斜
                    head1_imageView4.setVisibility(View.GONE);
                    head1_imageView5.setVisibility(View.VISIBLE);*/


                    /*head1_linearLayout1.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_hongse);
                    head1_textView5.setTextColor(getResources().getColor(R.color.red));
                    head1_view1.setBackgroundResource(R.color.red);
                    head1_textView11.setTextColor(getResources().getColor(R.color.red));
                    head1_textView6.setTextColor(getResources().getColor(R.color.red));

                    head1_linearLayout2.setBackgroundResource(R.drawable.yuanjiao_5_qianlanse);
                    head1_textView7.setTextColor(getResources().getColor(R.color.white));
                    head1_view2.setBackgroundResource(R.color.white);
                    head1_textView12.setTextColor(getResources().getColor(R.color.white1));
                    head1_textView8.setTextColor(getResources().getColor(R.color.white));*/
                } else {
                    head1_linearLayout2.setVisibility(View.VISIBLE);
                    head1_linearLayout2_1.setVisibility(View.GONE);
                    head1_linearLayout2_2.setVisibility(View.GONE);
                }

                list1 = response.getLike_game().getHistory_like_game_participation_list();
                list2 = response.getMy_like_game_participation_list();
                //参与动态
                mAdapter1 = new CommonAdapter<PeriodDetailModel.LikeGameBean.HistoryLikeGameParticipationListBean>
                        (PeriodDetailActivity.this, R.layout.item_perioddetail, list1) {
                    @Override
                    protected void convert(ViewHolder holder, final PeriodDetailModel.LikeGameBean.HistoryLikeGameParticipationListBean model, int position) {
                        holder.setText(R.id.textView1, model.getMember_nickname());
                        holder.setText(R.id.textView2, model.getMoney() + getString(R.string.app_ge));
                        holder.setText(R.id.textView3, model.getShow_created_at());
                        holder.setText(R.id.textView4, model.getBet_chain_title());

                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        if (!model.getMember_head().equals(""))
                            Glide.with(PeriodDetailActivity.this)
                                    .load(IMGHOST + model.getMember_head())
                                    .centerCrop()
//                                    .placeholder(R.mipmap.headimg)//加载站位图
//                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片
                        else
                            imageView1.setImageResource(R.mipmap.headimg);

                    }
                };
                mHeaderAndFooterWrapper1 = new HeaderAndFooterWrapper(mAdapter1);
                mHeaderAndFooterWrapper1.addHeaderView(headerView1);
                mHeaderAndFooterWrapper1.addHeaderView(headerView2);

                //我的参与
                mAdapter2 = new CommonAdapter<PeriodDetailModel.MyLikeGameParticipationListBean>
                        (PeriodDetailActivity.this, R.layout.item_perioddetail, list2) {
                    @Override
                    protected void convert(ViewHolder holder, final PeriodDetailModel.MyLikeGameParticipationListBean model, int position) {
                        holder.setText(R.id.textView1, model.getMember_nickname());
                        holder.setText(R.id.textView2, model.getMoney() + getString(R.string.app_ge));
                        holder.setText(R.id.textView3, model.getShow_created_at());
                        holder.setText(R.id.textView4, model.getBet_chain_title());
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        if (!model.getMember_head().equals(""))
                            Glide.with(PeriodDetailActivity.this)
                                    .load(IMGHOST + model.getMember_head())
                                    .centerCrop()
//                                    .placeholder(R.mipmap.headimg)//加载站位图
//                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片
                        else
                            imageView1.setImageResource(R.mipmap.headimg);

                    }
                };
                mHeaderAndFooterWrapper2 = new HeaderAndFooterWrapper(mAdapter2);
                mHeaderAndFooterWrapper2.addHeaderView(headerView1);
                mHeaderAndFooterWrapper2.addHeaderView(headerView2);
                changeUI();
                hideProgress();

                isOver = true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.linearLayout2:
                type = 2;
                changeUI();
                break;
            case R.id.head2_linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.head2_linearLayout2:
                type = 2;
                changeUI();
                break;
            case R.id.head1_textView1:
                //往期栏
                finish();
//                CommonUtil.gotoActivity(PeriodDetailActivity.this, PastListActivity.class, false);
                break;
            case R.id.head1_textView2:
                //排行榜
//                CommonUtil.gotoActivity(PeriodDetailActivity.this, LeaderboardActivity.class, false);
                break;
            case R.id.head1_imageView1:
                //竞猜规则
                dialog.contentView(R.layout.dialog_jingcaiguize)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.CENTER)
                        .canceledOnTouchOutside(true)
                        .dimAmount(0.8f)
                        .show();
                dialog.findViewById(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
        }
    }

    private void changeUI() {
        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.white));
            textView2.setTextColor(getResources().getColor(R.color.white1));
            head2_textView1.setTextColor(getResources().getColor(R.color.white));
            head2_textView2.setTextColor(getResources().getColor(R.color.white1));

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            head2_view1.setVisibility(View.VISIBLE);
            head2_view2.setVisibility(View.INVISIBLE);

            recyclerView.setAdapter(mHeaderAndFooterWrapper1);
            mHeaderAndFooterWrapper1.notifyDataSetChanged();

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.white1));
            textView2.setTextColor(getResources().getColor(R.color.white));
            head2_textView1.setTextColor(getResources().getColor(R.color.white1));
            head2_textView2.setTextColor(getResources().getColor(R.color.white));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            head2_view1.setVisibility(View.INVISIBLE);
            head2_view2.setVisibility(View.VISIBLE);

            recyclerView.setAdapter(mHeaderAndFooterWrapper2);
            mHeaderAndFooterWrapper2.notifyDataSetChanged();
        }

    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();

        showProgress(true, getString(R.string.app_loading2));
        request("?token=" + localUserInfo.getToken()
                + "&id=" + id);
    }
}
