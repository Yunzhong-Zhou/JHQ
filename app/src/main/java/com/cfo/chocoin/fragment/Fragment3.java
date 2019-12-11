package com.cfo.chocoin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cfo.chocoin.R;
import com.cfo.chocoin.activity.MainActivity;
import com.cfo.chocoin.base.BaseFragment;
import com.cfo.chocoin.model.Fragment3Model;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.LocalUserInfo;
import com.cfo.chocoin.utils.MyLogger;
import com.cy.dialog.BaseDialog;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 大厅
 */
public class Fragment3 extends BaseFragment {
    //    boolean isOver = false;
    Fragment3Model model;
    int type = 1;
    private RecyclerView recyclerView;
    List<Fragment3Model.LikeGameBean.LikeGameParticipationListBean> list1 = new ArrayList<>();
    CommonAdapter<Fragment3Model.LikeGameBean.LikeGameParticipationListBean> mAdapter1;

    List<Fragment3Model.MyLikeGameParticipationListBean> list2 = new ArrayList<>();
    CommonAdapter<Fragment3Model.MyLikeGameParticipationListBean> mAdapter2;

    //悬浮部分
    LinearLayout linearLayout1, linearLayout2, linearLayout3;
    TextView textView1, textView2, textView3;
    View view1, view2, view3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        MyLogger.i(">>>>>>>>onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        MyLogger.i(">>>>>>>>onResume");
        if (MainActivity.item == 2) {
            requestServer();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        MyLogger.i(">>>>>>>>onHiddenChanged>>>" + hidden);

        /*if (MainActivity.item == 2) {
            requestServer();
        }*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        MyLogger.i(">>>>>>>>getUserVisibleHint()>>>" + getUserVisibleHint());
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 4) {
                    requestServer();
                }
            }
        }
    }

    @Override
    protected void initView(View view) {
//        findViewByID_My(R.id.headview).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                HashMap<String, String> params = new HashMap<>();
                params.put("token", LocalUserInfo.getInstance(getActivity()).getToken());
                request(params);
            }

            @Override
            public void onLoadmore() {
            }
        });

        //悬浮部分
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);
        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);
        /*//listview 滑动监听
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
        });*/

        /*//动态设置linearLayout的高度为屏幕高度的1/4
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(getActivity()) / 3;*/


    }

    @Override
    protected void initData() {
//        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();

        showProgress(true, getString(R.string.app_loading));
        HashMap<String, String> params = new HashMap<>();
        params.put("token", LocalUserInfo.getInstance(getActivity()).getToken());
        request(params);
    }

    @Override
    protected void updateView() {

    }

    private void request(HashMap<String, String> params) {
        OkHttpClientManager.postAsyn(getActivity(), URLs.Fragment3, params, new OkHttpClientManager.ResultCallback<Fragment3Model>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                MainActivity.isOver = true;
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(final Fragment3Model response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>首页" + response);
                /*model = response;
                //期次
                head1_textView3.setText(getString(R.string.fragment3_h3) + "  " + response.getLike_game().getPeriod());
                //总投
                head1_textView5.setText(response.getLike_game().getAmount_money());

                //公链
//                head1_textView5.setText(response.getLike_game().getAmount_money_1());
                //公链已投
                head1_textView6.setText(response.getPublic_chain_money());
                //侧链
//                head1_textView7.setText(response.getLike_game().getAmount_money_2());

                //侧链已投
                head1_textView7.setText(response.getBroadside_chain_money());
                //可用余额
                head1_textView9.setText(response.getUsable_money());

                //是否参与
                if (!response.getWin_prompt().getWin_member_id().equals("")) {
                    //是我中奖
                    if (localUserInfo.getUserId().equals(response.getWin_prompt().getWin_member_id()) &&
                            Double.valueOf(response.getWin_prompt().getWin_money()) > 0) {
                        //没有中奖标识 或者 标识不相同
                        if (localUserInfo.getWinnum().equals("") || !localUserInfo.getWinnum().equals(response.getWin_prompt().getWin_member_id() + response.getWin_prompt().getPeriod())) {
                            //显示中奖
                            showWin(response.getWin_prompt());
                        }
                    } else {
                        //没有中奖
                        *//*if (localUserInfo.getLosenum().equals("") || !localUserInfo.getLosenum().equals(response.getWin_prompt().getWin_member_id() + response.getWin_prompt().getPeriod())) {
                            //显示未中奖
                            showLose(response.getWin_prompt());
                        }*//*
                    }

                }

                //倒计时
                if (response.getCount_down() > 0) {
                    showTime();
                } else {

                }
                list1 = response.getLike_game().getLike_game_participation_list();
                list2 = response.getMy_like_game_participation_list();
                //参与动态
                mAdapter1 = new CommonAdapter<Fragment3Model.LikeGameBean.LikeGameParticipationListBean>
                        (getActivity(), R.layout.item_fragment3, list1) {
                    @Override
                    protected void convert(ViewHolder holder, final Fragment3Model.LikeGameBean.LikeGameParticipationListBean model, int position) {
                        holder.setText(R.id.textView1, model.getMember_nickname());
                        holder.setText(R.id.textView2, model.getMoney() + getString(R.string.app_ge));
                        holder.setText(R.id.textView3, model.getShow_created_at());
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        if (!model.getMember_head().equals(""))
                            Glide.with(getActivity())
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
                mAdapter2 = new CommonAdapter<Fragment3Model.MyLikeGameParticipationListBean>
                        (getActivity(), R.layout.item_fragment3, list2) {
                    @Override
                    protected void convert(ViewHolder holder, final Fragment3Model.MyLikeGameParticipationListBean model, int position) {
                        holder.setText(R.id.textView1, model.getMember_nickname());
                        holder.setText(R.id.textView2, model.getMoney() + getString(R.string.app_ge));
                        holder.setText(R.id.textView3, model.getShow_created_at());
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        if (!model.getMember_head().equals(""))
                            Glide.with(getActivity())
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
                mHeaderAndFooterWrapper2.addHeaderView(headerView2);*/
                changeUI();
                hideProgress();

                MainActivity.isOver = true;
            }
        }, true);
    }

    //显示中奖弹框
    private void showWin(Fragment3Model.WinPromptBean bean) {
        localUserInfo.setWinnum(bean.getWin_member_id() + bean.getPeriod());
        dialog.contentView(R.layout.dialog_win)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.CENTER)
                .canceledOnTouchOutside(true)
                .dimAmount(0.8f)
                .show();
        TextView textView1 = dialog.findViewById(R.id.textView1);
        TextView textView2 = dialog.findViewById(R.id.textView2);

        textView1.setText(bean.getWin_money());
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //分享
//                dialog.dismiss();
                Intent share_intent = new Intent();
                share_intent.setAction(Intent.ACTION_SEND);
                share_intent.setType("text/plain");
                share_intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_h28) + "\n" + model.getUrl());
                share_intent = Intent.createChooser(share_intent, "分享");
                startActivity(share_intent);
            }
        });

        dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.linearLayout2:
                type = 2;
                changeUI();
                break;
            case R.id.linearLayout3:
                type = 3;
                changeUI();
                break;
        }
    }

    private void changeUI() {
        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.white));
            textView2.setTextColor(getResources().getColor(R.color.white2));
            textView3.setTextColor(getResources().getColor(R.color.white2));

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.INVISIBLE);

//            recyclerView.setAdapter(mAdapter1);

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.white2));
            textView2.setTextColor(getResources().getColor(R.color.white));
            textView3.setTextColor(getResources().getColor(R.color.white2));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            view3.setVisibility(View.INVISIBLE);

//            recyclerView.setAdapter(mAdapter2);
        } else if (type == 3) {
            textView1.setTextColor(getResources().getColor(R.color.white2));
            textView2.setTextColor(getResources().getColor(R.color.white2));
            textView3.setTextColor(getResources().getColor(R.color.white));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.VISIBLE);

//            recyclerView.setAdapter(mAdapter3);
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
