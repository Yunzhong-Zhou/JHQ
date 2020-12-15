package com.fone.fone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.activity.MainActivity;
import com.fone.fone.base.BaseFragment;
import com.fone.fone.model.Fragment3Model;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by Mr.Z on 2016/1/6.
 * 拼团
 */
public class Fragment3 extends BaseFragment {
    //    boolean isOver = false;
    Fragment3Model model;
    int type = 1;

    private RecyclerView rv_join,recyclerView;
    List<Fragment3Model.NewestContractListBean> list1 = new ArrayList<>();
    CommonAdapter<Fragment3Model.NewestContractListBean> mAdapter1;

    List<Fragment3Model.ContractTradingListBean> list2 = new ArrayList<>();
    CommonAdapter<Fragment3Model.ContractTradingListBean> mAdapter2;

    List<Fragment3Model.ContractCallMarginListBean> list_join = new ArrayList<>();
    CommonAdapter<Fragment3Model.ContractCallMarginListBean> mAdapter_join;


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
                if (MainActivity.item == 2) {
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
                request("?token=" + localUserInfo.getToken());
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


        rv_join = findViewByID_My(R.id.rv_join);
        rv_join.setLayoutManager(new GridLayoutManager(getActivity(),5));
        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);


        //设置图片宽度为屏幕宽度，高度自适应-android:adjustViewBounds="true"
        /*ImageView iv_head_bg = findViewByID_My(R.id.iv_head_bg);
        ViewGroup.LayoutParams lp = iv_head_bg.getLayoutParams();
        lp.width = CommonUtil.getScreenWidth(getActivity());
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        iv_head_bg.setLayoutParams(lp);
        iv_head_bg.setMaxWidth(lp.width);
        iv_head_bg.setMaxHeight(lp.height);*/

    }

    @Override
    protected void initData() {
//        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        /*if (isAdded()) {
            showProgress(true, getString(R.string.app_loading));
            request("?token=" + localUserInfo.getToken());
        }*/
    }

    @Override
    protected void updateView() {

    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment3 + string, new OkHttpClientManager.ResultCallback<Fragment3Model>() {
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
                MyLogger.i(">>>>>>>>>首页" + response);
                if (response != null) {
                    model = response;
                    //加入拼团
                    list_join = response.getContract_call_margin_list();
                    mAdapter_join = new CommonAdapter<Fragment3Model.ContractCallMarginListBean>
                            (getActivity(), R.layout.item_fragment3_join, list_join) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment3Model.ContractCallMarginListBean model, int position) {
                           /* holder.setText(R.id.textView1, model.getMoney());
                            holder.setText(R.id.textView2, model.getShow_created_at());*/
                        }
                    };


                    //完成拼团
                    list1 = response.getNewest_contract_list();
                    mAdapter1 = new CommonAdapter<Fragment3Model.NewestContractListBean>
                            (getActivity(), R.layout.item_fragment3_1, list1) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment3Model.NewestContractListBean model, int position) {
                            /*holder.setText(R.id.textView1, model.getMember_nickname());//昵称
                            holder.setText(R.id.textView2, model.getMoney());//合约数
                            TextView tv3 = holder.getView(R.id.textView3);
                            if (model.getProfit_money() > 0) {
                                tv3.setText("+" + model.getProfit_money());
                                tv3.setBackgroundResource(R.drawable.yuanjiao_0_lvse);

                            } else if (model.getProfit_money() < 0) {
                                tv3.setText("-" + model.getProfit_money());
                                tv3.setBackgroundResource(R.drawable.yuanjiao_0_red);
                            } else {
                                tv3.setText("" + model.getProfit_money());
                                tv3.setBackgroundResource(R.drawable.yuanjiao_0_red);
                            }
                            ImageView iv = holder.getView(R.id.imageView1);
                            if (!model.getMember_head().equals(""))
                                Glide.with(getActivity()).load(IMGHOST + model.getMember_head())
                                        .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                                        .into(iv);//加载图片*/

                        }
                    };
                    //算力排行
                    list2 = response.getContract_trading_list();
                    mAdapter2 = new CommonAdapter<Fragment3Model.ContractTradingListBean>
                            (getActivity(), R.layout.item_fragment3_2, list2) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment3Model.ContractTradingListBean model, int position) {
                            /*holder.setText(R.id.textView1, model.getBourse_on_title());//昵称
                            holder.setText(R.id.textView2, model.getMoney());//合约数
                            TextView tv3 = holder.getView(R.id.textView3);
                            double moeny = Double.valueOf(model.getEarning_money());
                            if (model.getResult() == 1) {
                                tv3.setText("+" + moeny);
                                tv3.setBackgroundResource(R.drawable.yuanjiao_0_lvse);

                            } else {
                                tv3.setText("-" + moeny);
                                tv3.setBackgroundResource(R.drawable.yuanjiao_0_red);
                            }*/

                        }
                    };

                    changeUI();
                    hideProgress();

                    MainActivity.isOver = true;
                }
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
            textView1.setTextColor(getResources().getColor(R.color.black1));
            textView2.setTextColor(getResources().getColor(R.color.black3));
            textView3.setTextColor(getResources().getColor(R.color.black3));

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.INVISIBLE);

            if (list1.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter1);
//                mAdapter1.notifyDataSetChanged();
            } else {
                showEmptyPage();
            }

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.black3));
            textView2.setTextColor(getResources().getColor(R.color.black1));
            textView3.setTextColor(getResources().getColor(R.color.black3));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            view3.setVisibility(View.INVISIBLE);

            if (list2.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter2);
//                mAdapter2.notifyDataSetChanged();
            } else {
                showEmptyPage();
            }
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
