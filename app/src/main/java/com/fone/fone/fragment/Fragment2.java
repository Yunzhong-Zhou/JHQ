package com.fone.fone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fone.fone.R;
import com.fone.fone.activity.MainActivity;
import com.fone.fone.base.BaseFragment;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.squareup.okhttp.Request;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 算力
 */
public class Fragment2 extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        StatusBarUtil.setTransparent(getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        /*if (MainActivity.item == 1) {
            requestServer();
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        /*if (MainActivity.item == 1) {
            requestServer();
        }*/
    }

    /*@Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (MainActivity.item == 1) {
            requestServer();
        }
    }*/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 1) {
                    requestServer();
                }
            }
        }
    }

    @Override
    protected void initView(View view) {
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        /*setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                String string = "?token=" + localUserInfo.getToken();
                Request(string);
            }

            @Override
            public void onLoadmore() {

            }
        });*/


    }

    @Override
    protected void initData() {
//        requestServer();
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        String string = "?token=" + localUserInfo.getToken();
        Request(string);
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment2 + string, new OkHttpClientManager.ResultCallback<String>() {
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
            public void onResponse(String response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>算力" + response);

                hideProgress();
                MainActivity.isOver = true;
            }
        });
    }

}
