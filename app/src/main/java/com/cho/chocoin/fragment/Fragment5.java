package com.cho.chocoin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cho.chocoin.R;
import com.cho.chocoin.activity.AccountDetailActivity1;
import com.cho.chocoin.activity.AccountDetailActivity2;
import com.cho.chocoin.activity.InformationActivity;
import com.cho.chocoin.activity.MainActivity;
import com.cho.chocoin.activity.MyGameActivity;
import com.cho.chocoin.activity.MyPosterActivity;
import com.cho.chocoin.activity.MyProfileActivity;
import com.cho.chocoin.activity.MyQuKuaiActivity;
import com.cho.chocoin.activity.MyRechargeActivity;
import com.cho.chocoin.activity.MyTakeCashActivity;
import com.cho.chocoin.activity.OnlineServiceActivity;
import com.cho.chocoin.activity.QRCodeActivity;
import com.cho.chocoin.activity.ShareActivity;
import com.cho.chocoin.activity.SuanLiListActivity;
import com.cho.chocoin.activity.TakeCashActivity;
import com.cho.chocoin.base.BaseFragment;
import com.cho.chocoin.model.Fragment5Model;
import com.cho.chocoin.net.OkHttpClientManager;
import com.cho.chocoin.net.URLs;
import com.cho.chocoin.utils.CommonUtil;
import com.cho.chocoin.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import static com.cho.chocoin.net.OkHttpClientManager.IMGHOST;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 我的
 */
public class Fragment5 extends BaseFragment {
    RelativeLayout relativeLayout;
    TextView textView1, textView2, textView3, textView4, textView5, textView6;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5,
            linearLayout6, linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11,
            linearLayout12, linearLayout13, linearLayout14, linearLayout15;
    ImageView imageView1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment5, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        StatusBarUtil.setTransparent(getActivity());
    }

    @Override
    public void onStart() {
//        MyLogger.i(">>>>>>>>onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
//        MyLogger.i(">>>>>>>>onResume");
        if (MainActivity.item == 4) {
            requestServer();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        MyLogger.i(">>>>>>>>onHiddenChanged>>>" + hidden);
        /*if (MainActivity.item == 4) {
            requestServer();
        }*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        MyLogger.i(">>>>>>>>setUserVisibleHint>>>" + isVisibleToUser);
        if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
            if (MainActivity.item == 4) {
                requestServer();
            }
        }
    }

    @Override
    protected void initView(View view) {
//        findViewByID_My(R.id.headview).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                requestCenter("?token=" + localUserInfo.getToken());
            }

            @Override
            public void onLoadmore() {

            }
        });


        relativeLayout = findViewByID_My(R.id.relativeLayout);
        relativeLayout.setOnClickListener(this);
        imageView1 = findViewByID_My(R.id.imageView1);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);


        textView1.setText(localUserInfo.getNickname());
        textView2.setText(getString(R.string.fragment5_h1) + localUserInfo.getInvuteCode());
        if (!localUserInfo.getUserImage().equals(""))
            Glide.with(getActivity())
                    .load(IMGHOST + localUserInfo.getUserImage())
                    .centerCrop()
//                    .placeholder(R.mipmap.headimg)//加载站位图
//                    .error(R.mipmap.headimg)//加载失败
                    .into(imageView1);//加载图片
        else
            imageView1.setImageResource(R.mipmap.headimg);

        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout4 = findViewByID_My(R.id.linearLayout4);
        linearLayout5 = findViewByID_My(R.id.linearLayout5);
        linearLayout6 = findViewByID_My(R.id.linearLayout6);
        linearLayout7 = findViewByID_My(R.id.linearLayout7);
        linearLayout8 = findViewByID_My(R.id.linearLayout8);
        linearLayout9 = findViewByID_My(R.id.linearLayout9);
        linearLayout10 = findViewByID_My(R.id.linearLayout10);
        linearLayout11 = findViewByID_My(R.id.linearLayout11);
        linearLayout12 = findViewByID_My(R.id.linearLayout12);
        linearLayout13 = findViewByID_My(R.id.linearLayout13);
        linearLayout14 = findViewByID_My(R.id.linearLayout14);
        linearLayout15 = findViewByID_My(R.id.linearLayout15);

        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout4.setOnClickListener(this);
        linearLayout5.setOnClickListener(this);
        linearLayout6.setOnClickListener(this);
        linearLayout7.setOnClickListener(this);
        linearLayout8.setOnClickListener(this);
        linearLayout9.setOnClickListener(this);
        linearLayout10.setOnClickListener(this);
        linearLayout11.setOnClickListener(this);
        linearLayout12.setOnClickListener(this);
        linearLayout13.setOnClickListener(this);
        linearLayout14.setOnClickListener(this);
        linearLayout15.setOnClickListener(this);


        LinearLayout linearLayout = findViewByID_My(R.id.linearLayout);
        /*LinearLayout.LayoutParams sp_params = new LinearLayout.LayoutParams(
        RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        sp_params.height = CommonUtil.getScreenHeight(getActivity()) / 4;
        linearLayout.setLayoutParams(sp_params);*/

        //动态设置linearLayout的高度为屏幕高度的1/4
        ViewGroup.LayoutParams lp = linearLayout.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(getActivity()) / 3;

    }

    @Override
    protected void initData() {

    }

    private void requestCenter(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Center + string, new OkHttpClientManager.ResultCallback<Fragment5Model>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(Fragment5Model response) {
                MyLogger.i(">>>>>>>>>我的" + response);
                //昵称
                textView1.setText(response.getNickname());
                //邀请码
                textView2.setText(getString(R.string.fragment5_h1) + response.getInvite_code());
                //等级
                textView3.setText(response.getGrade_title() + "");

                //头像
                localUserInfo.setUserImage(response.getHead());
                if (!response.getHead().equals(""))
                    Glide.with(getActivity()).load(IMGHOST + response.getHead())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片
                else
                    imageView1.setImageResource(R.mipmap.headimg);


                textView4.setText("" + response.getCommon_usable_money());//账户余额
                textView5.setText("" + response.getInvest_usable_money());//我的算力
                textView6.setText("" + response.getBlock_award_usable_money());//区块奖励

                hideProgress();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relativeLayout:
                //个人资料
                CommonUtil.gotoActivity(getActivity(), MyProfileActivity.class, false);
                break;
           /* case R.id.textView:
                //签到
                localUserInfo.setIsnewcomer("1");
                showProgress(true, getString(R.string.app_loading2));
                String string2 = "?token=" + localUserInfo.getToken();
                RequestVerified(string2, 2);

//                CommonUtil.gotoActivity(getActivity(), NewcomerRewardActivity.class, false);
                break;*/
            case R.id.linearLayout1:
                //充值
                MainActivity.item = 3;
                MainActivity.navigationBar.selectTab(3);
//                CommonUtil.gotoActivity(getActivity(), RechargeActivity.class, false);
                break;
            case R.id.linearLayout2:
                //推广 - 改为流水
                CommonUtil.gotoActivity(getActivity(), ShareActivity.class, false);
                break;
            case R.id.linearLayout3:
                //提现
                CommonUtil.gotoActivity(getActivity(), TakeCashActivity.class, false);
                break;
            case R.id.linearLayout4:
                //账户余额
                CommonUtil.gotoActivity(getActivity(), AccountDetailActivity2.class, false);

                break;
            case R.id.linearLayout5:
                //我的算力
                MainActivity.item = 0;
                MainActivity.navigationBar.selectTab(0);
//                CommonUtil.gotoActivity(getActivity(), .class, false);

                break;
            case R.id.linearLayout6:
                //我的区块
                CommonUtil.gotoActivity(getActivity(), AccountDetailActivity1.class, false);

                break;
            case R.id.linearLayout7:
                //我的区块
                CommonUtil.gotoActivity(getActivity(), MyQuKuaiActivity.class, false);

                break;
            case R.id.linearLayout8:
                //资料管理 - 扫码转币
//                CommonUtil.gotoActivity(getActivity(), MyProfileActivity.class, false);
                CommonUtil.gotoActivity(getActivity(), QRCodeActivity.class, false);
                break;
            case R.id.linearLayout9:
                //我的充值
                CommonUtil.gotoActivity(getActivity(), MyRechargeActivity.class, false);

                break;
            case R.id.linearLayout10:
                //我的提现
                CommonUtil.gotoActivity(getActivity(), MyTakeCashActivity.class, false);

                break;
            case R.id.linearLayout11:
                //在线客服
                CommonUtil.gotoActivity(getActivity(), OnlineServiceActivity.class, false);

                break;
            case R.id.linearLayout12:
                //我的游戏
                CommonUtil.gotoActivity(getActivity(), MyGameActivity.class, false);

                break;
            case R.id.linearLayout13:
                //公告通知
                CommonUtil.gotoActivity(getActivity(), InformationActivity.class, false);

                break;
            case R.id.linearLayout14:
                //分享有奖
                CommonUtil.gotoActivity(getActivity(), MyPosterActivity.class, false);

                break;
            case R.id.linearLayout15:
                //我的算力
                CommonUtil.gotoActivity(getActivity(), SuanLiListActivity.class, false);

                break;

        }

    }

    @Override
    protected void updateView() {

    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        requestCenter("?token=" + localUserInfo.getToken());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
