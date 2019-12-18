package com.cfo.chocoin.fragment;

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
import com.cfo.chocoin.model.Fragment1Model;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 学院
 */

public class Fragment1 extends BaseFragment {
    int type = 1;
    private RecyclerView recyclerView;
    List<Fragment1Model.NewestInvestListBean> list1 = new ArrayList<>();
    CommonAdapter<Fragment1Model.NewestInvestListBean> mAdapter1;

    List<Fragment1Model> list2 = new ArrayList<>();
    CommonAdapter<Fragment1Model> mAdapter2;

    //悬浮部分
    LinearLayout linearLayout1, linearLayout2, linearLayout3;
    TextView textView1, textView2, textView3;
    View view1, view2, view3;

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
        if (MainActivity.item == 0) {
            requestServer();
        }
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
//        findViewByID_My(R.id.headview).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
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

    }

    @Override
    protected void initData() {
//        requestServer();

    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment1 + string, new OkHttpClientManager.ResultCallback<Fragment1Model>() {
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
                MyLogger.i(">>>>>>>>>学院" + response);
                /*//基础知识
                list1 = response.getNewest_invest_list();
                mAdapter1 = new CommonAdapter<Fragment1Model.NewestInvestListBean>
                        (getActivity(), R.layout.item_fragment1, list1) {
                    @Override
                    protected void convert(ViewHolder holder, final Fragment1Model.NewestInvestListBean model, int position) {
                        holder.setText(R.id.textView1, model.getMember_nickname());
                        holder.setText(R.id.textView2, model.getMoney() + getString(R.string.app_ge));
                        holder.setText(R.id.textView3, model.getCreated_at());
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

                mAdapter2 = new CommonAdapter<Fragment1Model>
                        (getActivity(), R.layout.item_fragment2, list2) {
                    @Override
                    protected void convert(ViewHolder holder, final Fragment1Model model, int position) {
                        holder.setText(R.id.textView1, model.getMember_nickname());
                        holder.setText(R.id.textView2, "¥ " + model.getMoney());
                        holder.setText(R.id.textView3, model.getCreated_at());
                        holder.setText(R.id.textView4, model.getPackage_title());
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        if (!model.getMember_head().equals(""))
                            Glide.with(getActivity()).load(IMGHOST + model.getMember_head())
                                    .centerCrop().into(imageView1);//加载图片
                        else
                            imageView1.setImageResource(R.mipmap.headimg);

                    }
                };*/

                changeUI();

                hideProgress();

                MainActivity.isOver = true;
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
            textView1.setTextColor(getResources().getColor(R.color.green));
            textView2.setTextColor(getResources().getColor(R.color.black3));
            textView3.setTextColor(getResources().getColor(R.color.black3));
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.INVISIBLE);

            /*recyclerView.setAdapter(mAdapter1);
            mAdapter1.notifyDataSetChanged();*/

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.black3));
            textView2.setTextColor(getResources().getColor(R.color.green));
            textView3.setTextColor(getResources().getColor(R.color.black3));
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            view3.setVisibility(View.INVISIBLE);

           /* if (list1.size()>0){
                showContentPage();
                recyclerView.setAdapter(mAdapter1);
            }else {
                showEmptyPage();
            }*/
        }else if (type == 3) {
            textView1.setTextColor(getResources().getColor(R.color.black3));
            textView2.setTextColor(getResources().getColor(R.color.black3));
            textView3.setTextColor(getResources().getColor(R.color.green));
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.VISIBLE);

            /*if (list2.size()>0){
                showContentPage();
                recyclerView.setAdapter(mAdapter2);
            }else {
                showEmptyPage();
            }*/
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
        String string = "?token=" + localUserInfo.getToken();
        Request(string);
    }
}
