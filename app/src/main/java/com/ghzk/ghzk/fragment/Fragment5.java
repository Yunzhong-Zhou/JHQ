package com.ghzk.ghzk.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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
import com.ghzk.ghzk.R;
import com.ghzk.ghzk.activity.AccountDetailActivity;
import com.ghzk.ghzk.activity.CityFriendActivity;
import com.ghzk.ghzk.activity.CityFriendListActivity;
import com.ghzk.ghzk.activity.CityFriend_NOActivity;
import com.ghzk.ghzk.activity.ContractActivity;
import com.ghzk.ghzk.activity.HelpCenterActivity;
import com.ghzk.ghzk.activity.InformationActivity;
import com.ghzk.ghzk.activity.MainActivity;
import com.ghzk.ghzk.activity.MyMachineActivity;
import com.ghzk.ghzk.activity.MyOrderActivity;
import com.ghzk.ghzk.activity.MyProfileActivity;
import com.ghzk.ghzk.activity.MyTakeCashActivity;
import com.ghzk.ghzk.activity.SetUpActivity;
import com.ghzk.ghzk.activity.ShareActivity;
import com.ghzk.ghzk.activity.SharePosterActivity;
import com.ghzk.ghzk.base.BaseFragment;
import com.ghzk.ghzk.model.AddCityFriendModel;
import com.ghzk.ghzk.model.Fragment5Model;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
import com.ghzk.ghzk.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import static com.ghzk.ghzk.net.OkHttpClientManager.IMGHOST;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 我的
 */
public class Fragment5 extends BaseFragment {
    Fragment5Model model;
    RelativeLayout relativeLayout;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5,
            linearLayout6, linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11,
            linearLayout12, linearLayout13, linearLayout14, linearLayout15, linearLayout16, linearLayout17, ll_sebei;
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
        if (MainActivity.item == 2) {
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
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 2) {
                    requestServer();
                }
            }
        }
    }

    @Override
    protected void initView(View view) {
        findViewByID_My(R.id.linearLayout).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
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

        imageView1 = findViewByID_My(R.id.imageView1);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView2.setOnClickListener(this);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        textView8 = findViewByID_My(R.id.textView8);
        textView9 = findViewByID_My(R.id.textView9);
        textView9.setOnClickListener(this);

        textView1.setText(localUserInfo.getNickname());
        textView2.setText(getString(R.string.fragment5_h1) + localUserInfo.getInvuteCode());
//        if (!localUserInfo.getUserImage().equals(""))
        Glide.with(getActivity())
                .load(IMGHOST + localUserInfo.getUserImage())
                .centerCrop()
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.headimg)//加载失败
                .into(imageView1);//加载图片

        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
//        linearLayout4 = findViewByID_My(R.id.linearLayout4);
//        linearLayout5 = findViewByID_My(R.id.linearLayout5);
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
        linearLayout16 = findViewByID_My(R.id.linearLayout16);
        linearLayout17 = findViewByID_My(R.id.linearLayout17);
        ll_sebei = findViewByID_My(R.id.ll_sebei);

        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
//        linearLayout4.setOnClickListener(this);
//        linearLayout5.setOnClickListener(this);
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
        linearLayout16.setOnClickListener(this);
        linearLayout17.setOnClickListener(this);
        ll_sebei.setOnClickListener(this);

        relativeLayout = findViewByID_My(R.id.relativeLayout);
        relativeLayout.setOnClickListener(this);

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
                MainActivity.isOver = true;
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(Fragment5Model response) {
                MyLogger.i(">>>>>>>>>我的" + response);
                if (response != null) {
                    model = response;
                    //昵称
                    textView1.setText(response.getNickname());
                    //邀请码
                    textView2.setText(getString(R.string.fragment5_h1) + response.getInvite_code());
                    //等级
                    textView3.setText(response.getGrade_title());

                    //头像
                    localUserInfo.setUserImage(response.getHead());
                    Glide.with(getActivity()).load(IMGHOST + response.getHead())
                            .centerCrop()
                            .placeholder(R.mipmap.loading)//加载站位图
                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片

                    textView5.setText("" + response.getUsable_money());//余额
                    textView6.setText("" + response.getEarning_money());//销售分成
                    textView7.setText("" + response.getCommission_money());//收益分成
                    textView8.setText("" + response.getOrder_goods_count() + getString(R.string.app_tai));//台数

//                    localUserInfo.setPhoneNumber(response.getMobile());
                    localUserInfo.setNickname(response.getNickname());
                    localUserInfo.setInvuteCode(response.getInvite_code());
//                    localUserInfo.setEmail(response.getEmail());
                    localUserInfo.setUserImage(response.getHead());

                }

                hideProgress();

                MainActivity.isOver = true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView2:
                //获取剪贴板管理器：
                if (!localUserInfo.getInvuteCode().equals("")) {
                    ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    // 创建普通字符型ClipData
                    ClipData mClipData = ClipData.newPlainText("Label", localUserInfo.getInvuteCode());
                    // 将ClipData内容放到系统剪贴板里。
                    cm.setPrimaryClip(mClipData);
                    myToast(getString(R.string.recharge_h34));
                }

                break;

            case R.id.relativeLayout:
                //个人资料
                CommonUtil.gotoActivity(getActivity(), MyProfileActivity.class, false);
                break;
            case R.id.linearLayout1:
                //余额
//                CommonUtil.gotoActivity(getActivity(), USDTJiaoYiActivity.class, false);
                break;
            case R.id.linearLayout2:
                //销售分成
//                CommonUtil.gotoActivity(getActivity(), TransferActivity.class, false);
                break;
            case R.id.linearLayout3:
                //利益分成
//                CommonUtil.gotoActivity(getActivity(), MyJoinListActivity.class, false);
                break;
            case R.id.textView9:
            case R.id.ll_sebei:
                //查看设备
                CommonUtil.gotoActivity(getActivity(), MyMachineActivity.class, false);
                break;

            case R.id.linearLayout6:
                //钱包
                CommonUtil.gotoActivity(getActivity(), AccountDetailActivity.class, false);
                break;
            case R.id.linearLayout7:
                //团队
//                if (model.getGrade()>1){
                CommonUtil.gotoActivity(getActivity(), ShareActivity.class, false);
               /* }else {
                    myToast(getString(R.string.fragment5_h30));
                }*/
                break;
            case R.id.linearLayout8:
                //合同
                CommonUtil.gotoActivity(getActivity(), ContractActivity.class, false);
                break;
            case R.id.linearLayout9:
                //合作
//                CommonUtil.gotoActivity(getActivity(), CityFriendActivity.class, false);
//                CommonUtil.gotoActivity(getActivity(), CityFriend_NOActivity.class, false);
                showProgress(true, getString(R.string.app_loading2));
                requestCityFriend("?token=" + localUserInfo.getToken());
                break;
            case R.id.linearLayout10:
                //我的设备
                CommonUtil.gotoActivity(getActivity(), MyMachineActivity.class, false);
                break;
            case R.id.linearLayout11:
                //提现记录
                CommonUtil.gotoActivity(getActivity(), MyTakeCashActivity.class, false);
                break;
            case R.id.linearLayout12:
                //城市伙伴
                CommonUtil.gotoActivity(getActivity(), CityFriendListActivity.class, false);
                break;
            case R.id.linearLayout13:
                //设置中心
                CommonUtil.gotoActivity(getActivity(), SetUpActivity.class, false);
                break;
            case R.id.linearLayout14:
                //订单中心
                CommonUtil.gotoActivity(getActivity(), MyOrderActivity.class, false);
                break;
            case R.id.linearLayout15:
                //分享海报
                CommonUtil.gotoActivity(getActivity(), SharePosterActivity.class, false);
                break;
            case R.id.linearLayout16:
                //消息快讯
                CommonUtil.gotoActivity(getActivity(), InformationActivity.class, false);
                break;
            case R.id.linearLayout17:
                //帮助中心
                CommonUtil.gotoActivity(getActivity(), HelpCenterActivity.class, false);
                break;
        }

    }

    private void requestCityFriend(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.AddCityFriend + string, new OkHttpClientManager.ResultCallback<AddCityFriendModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(AddCityFriendModel response) {
                hideProgress();
                //-1、待申请 1、待审核 2、通过 3、未通过
                Bundle bundle = new Bundle();
                bundle.putInt("status",response.getStatus());
                bundle.putString("status_rejected_cause",response.getStatus_rejected_cause());
                if (response.getStatus() == 2) {
                    CommonUtil.gotoActivityWithData(getActivity(), CityFriendActivity.class,bundle, false);
                } else {
                    CommonUtil.gotoActivityWithData(getActivity(), CityFriend_NOActivity.class,bundle, false);
                }

            }
        });
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
