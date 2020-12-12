package com.fone.fone.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cy.dialog.BaseDialog;
import com.fone.fone.R;
import com.fone.fone.activity.MainActivity;
import com.fone.fone.base.BaseFragment;
import com.fone.fone.model.Fragment1Model;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;


/**
 * Created by Mr.Z on 2016/1/6.
 * 矿机
 */

public class Fragment1 extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        /*if (MainActivity.item == 0) {
            requestServer();
        }*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if (MainActivity.item == 0) {
            requestServer();
        }*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 0) {
                    requestServer();
                }
            }
        }
    }

    @Override
    protected void initView(View view) {
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
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
        findViewByID_My(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.contentView(R.layout.dialog_fragment1)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.BOTTOM)
                        .dimAmount(0.8f)
                        .show();
                ScrollView scrollView = dialog.findViewById(R.id.scrollView);
                //动态设置linearLayout的高度为屏幕高度的1/4
                ViewGroup.LayoutParams lp = scrollView.getLayoutParams();
                lp.height = (int) CommonUtil.getScreenHeight(getActivity()) * 2 / 3;
                LinearLayout linearLayout1 = dialog.findViewById(R.id.linearLayout1);
                LinearLayout linearLayout2 = dialog.findViewById(R.id.linearLayout2);
                LinearLayout ll_usdt = dialog.findViewById(R.id.ll_usdt);
                LinearLayout ll_zhuanzhang = dialog.findViewById(R.id.ll_zhuanzhang);
                TextView tv_h1 = dialog.findViewById(R.id.tv_h1);
                TextView tv_money1 = dialog.findViewById(R.id.tv_money1);
                TextView tv_d1 = dialog.findViewById(R.id.tv_d1);
                TextView tv_h2 = dialog.findViewById(R.id.tv_h2);
                TextView tv_money2 = dialog.findViewById(R.id.tv_money2);
                TextView tv_d2 = dialog.findViewById(R.id.tv_d2);
                TextView tv_confirm = dialog.findViewById(R.id.tv_confirm);
                ImageView iv_usdt = dialog.findViewById(R.id.iv_usdt);
                ImageView iv_zhuanzhang = dialog.findViewById(R.id.iv_zhuanzhang);
                linearLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //税前价格
                        linearLayout1.setBackgroundResource(R.mipmap.bg_fragment1_xuanze);
                        linearLayout2.setBackgroundResource(R.drawable.yuanjiaobiankuang_18_lvse);
                        tv_h1.setTextColor(getResources().getColor(R.color.white));
                        tv_money1.setTextColor(getResources().getColor(R.color.white));
                        tv_d1.setTextColor(getResources().getColor(R.color.white));
                        tv_h2.setTextColor(getResources().getColor(R.color.green_2));
                        tv_money2.setTextColor(getResources().getColor(R.color.green_2));
                        tv_d2.setTextColor(getResources().getColor(R.color.green_2));
                    }
                });
                linearLayout2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //税后价格
                        linearLayout1.setBackgroundResource(R.drawable.yuanjiaobiankuang_18_lvse);
                        linearLayout2.setBackgroundResource(R.mipmap.bg_fragment1_xuanze);
                        tv_h1.setTextColor(getResources().getColor(R.color.green_2));
                        tv_money1.setTextColor(getResources().getColor(R.color.green_2));
                        tv_d1.setTextColor(getResources().getColor(R.color.green_2));
                        tv_h2.setTextColor(getResources().getColor(R.color.white));
                        tv_money2.setTextColor(getResources().getColor(R.color.white));
                        tv_d2.setTextColor(getResources().getColor(R.color.white));

                    }
                });
                ll_usdt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //USDT
                        iv_usdt.setImageResource(R.mipmap.ic_gouxuan);
                        iv_zhuanzhang.setImageResource(R.drawable.yuanxingbiankuang_baise);
                    }
                });
                ll_zhuanzhang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //转账
                        iv_usdt.setImageResource(R.drawable.yuanxingbiankuang_baise);
                        iv_zhuanzhang.setImageResource(R.mipmap.ic_gouxuan);
                    }
                });
                tv_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }
        });
    }

    @Override
    protected void initData() {
//        requestServer();
    }


    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        if (isAdded()) {
            showProgress(true, getString(R.string.app_loading));
            /*String string = "?page=" + page//当前页号
                    + "&count=" + "10"//页面行数
                    + "&token=" + localUserInfo.getToken();
            Request(string);*/
        }
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.HeYue + string, new OkHttpClientManager.ResultCallback<Fragment1Model>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                MainActivity.isOver = true;
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(final Fragment1Model response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>合约" + response);

                MainActivity.isOver = true;

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    protected void updateView() {

    }


}
