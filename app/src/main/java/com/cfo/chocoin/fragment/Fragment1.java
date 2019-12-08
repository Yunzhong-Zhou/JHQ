package com.cfo.chocoin.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cfo.chocoin.R;
import com.cfo.chocoin.activity.BuyComputingPowerActivity;
import com.cfo.chocoin.activity.MainActivity;
import com.cfo.chocoin.activity.SuanLiListActivity;
import com.cfo.chocoin.base.BaseFragment;
import com.cfo.chocoin.model.Fragment1Model;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.CommonUtil;
import com.cfo.chocoin.utils.MyLogger;
import com.cfo.chocoin.view.MyArcView;
import com.cfo.chocoin.view.WaveProgress;
import com.bumptech.glide.Glide;
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
 * Created by fafukeji01 on 2016/1/6.
 * 算力
 */

public class Fragment1 extends BaseFragment {
    int type = 1;
    private RecyclerView recyclerView;
    List<Fragment1Model.NewestInvestListBean> list1 = new ArrayList<>();
    CommonAdapter<Fragment1Model.NewestInvestListBean> mAdapter1;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper1;

    List<Fragment1Model> list2 = new ArrayList<>();
    CommonAdapter<Fragment1Model> mAdapter2;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper2;
    //头部一
    View headerView1;
    LinearLayout head1_linearLayout1;
    TextView head1_textView1, head1_textView2, head1_textView3, head1_textView4, head1_textView5, head1_textView6;
    MyArcView myArcview;
    WaveProgress wave_progress_bar;
    LinearLayout head1_view1;
    ProgressBar progressBar;
    com.cfo.chocoin.view.wave.WaveProgress waveProgress;

    //头部二-需要悬浮的布局
    View headerView2;
    LinearLayout head2_linearLayout1, head2_linearLayout2;
    TextView head2_textView1, head2_textView2;
    View head2_view1, head2_view2;

    //头部三-说明
    View headerView3;
    TextView head3_textView;

    //悬浮部分
    LinearLayout invis;
    LinearLayout linearLayout1, linearLayout2;
    TextView textView1, textView2;
    View view1, view2;

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
        if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
            if (MainActivity.item == 0) {
                requestServer();
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
        headerView1 = View.inflate(getActivity(), R.layout.head_fragment1_1, null);
        head1_textView1 = headerView1.findViewById(R.id.head1_textView1);
        head1_textView2 = headerView1.findViewById(R.id.head1_textView2);
        head1_textView3 = headerView1.findViewById(R.id.head1_textView3);
        head1_textView4 = headerView1.findViewById(R.id.head1_textView4);
        head1_textView5 = headerView1.findViewById(R.id.head1_textView5);
        head1_textView6 = headerView1.findViewById(R.id.head1_textView6);
        head1_linearLayout1 = headerView1.findViewById(R.id.head1_linearLayout1);

        myArcview = headerView1.findViewById(R.id.myArcview);
        wave_progress_bar = headerView1.findViewById(R.id.wave_progress_bar);
        head1_view1 = headerView1.findViewById(R.id.head1_view1);
        progressBar = headerView1.findViewById(R.id.progressBar);
        head1_textView3.setOnClickListener(this);
        head1_linearLayout1.setOnClickListener(this);

        //水波纹
        waveProgress = headerView1.findViewById(R.id.waveProgress);
        waveProgress.setWaterLevelRatio(.8f);//水位比
        waveProgress.setAmplitudeRatio(.02f);//幅度比
        waveProgress.setWaveSpeed(1500);//水波滑动时间
        //waveView.setBorder(mBorderWidth, Color.argb(170,19,94,148));
        waveProgress.setBackgroundColor(getResources().getColor(R.color.white));//背景色
        waveProgress.setShapeType(com.cfo.chocoin.view.wave.WaveProgress.ShapeType.CIRCLE);//圆形

        //头部二
        headerView2 = View.inflate(getActivity(), R.layout.head_fragment1_2, null);
        head2_linearLayout1 = headerView2.findViewById(R.id.head2_linearLayout1);
        head2_linearLayout2 = headerView2.findViewById(R.id.head2_linearLayout2);
        head2_linearLayout1.setOnClickListener(this);
        head2_linearLayout2.setOnClickListener(this);
        head2_textView1 = headerView2.findViewById(R.id.head2_textView1);
        head2_textView2 = headerView2.findViewById(R.id.head2_textView2);
        head2_view1 = headerView2.findViewById(R.id.head2_view1);
        head2_view2 = headerView2.findViewById(R.id.head2_view2);

        //头部三
        headerView3 = View.inflate(getActivity(), R.layout.head_fragment2_3, null);
        head3_textView = headerView3.findViewById(R.id.head3_textView);

        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
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

    }

    @Override
    protected void initData() {
//        requestServer();

    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment1 + string, new OkHttpClientManager.ResultCallback<Fragment1Model>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(final Fragment1Model response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>算力" + response);
                //总算力
                head1_textView1.setText(response.getInvest_money());
                //总产值
                head1_textView4.setText(response.getMax_bonus_money());
                //算力等级
                head1_textView2.setText(getString(R.string.fragment1_h2) + response.getGrade_title());
                //已产
                head1_textView5.setText(response.getAmount_bonus_money());

                final float max = Float.valueOf(response.getMax_bonus_money());
                MyLogger.i(">>>>>>>>>最大：" + max);
                final float progress = Float.valueOf(response.getAmount_bonus_money());
                MyLogger.i(">>>>>>>>>当前值：" + progress);

                //波浪进度条
//                wave_progress_bar.setMaxValue(max);
//                wave_progress_bar.setValue(progress);

//                final float progress1 = Float.valueOf(response.getInvest_money());
                waveProgress.setProgressMax((int) max);
                waveProgress.setProgress((int) progress);
                /*waveProgress.setWaveColor(Color.parseColor("#E8CCF2"),//后面的颜色
                        Color.parseColor("#AE4DE0"));//前面的颜色
                int mBorderColor = Color.parseColor("#AE4DE0");
                waveProgress.setBorder(5, mBorderColor);*/

                //圆弧进度条
                if (response.getGrade() == 0) {
                    myArcview.setValue(180);//角度
                } else {
                    myArcview.setValue(180 - 45 * (response.getGrade() - 1));//角度
                }

                //progressBar
                progressBar.setMax((int) max);
                progressBar.setProgress((int) progress);
                //进度条
                progressBar.post(new Runnable() {
                    @Override
                    public void run() {
                        int p_height = progressBar.getWidth();
                        MyLogger.i(">>>>>>>>>>进度条宽度：" + progressBar.getWidth());
                        MyLogger.i(">>>>>>>>>>布局宽度的一半：" + head1_view1.getWidth() / 2);

                        int v_height = 0;
                        if (max > 0) {
                            v_height = (p_height * (int) progress / (int) max) - (head1_view1.getWidth() / 2);
                        }

                        MyLogger.i(">>>>>>>>>>view离右边宽度：" + v_height);

                       /* LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) head1_view1.getLayoutParams();
                        // 取控件aaa当前的布局参数
                        linearParams.height = v_height;
                        head1_view1.setLayoutParams(linearParams);*/
                        if (v_height > 0 && v_height < (p_height - (head1_view1.getWidth() / 2))) {
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) head1_view1.getLayoutParams();
                            layoutParams.rightMargin = v_height;
//                        layoutParams.topMargin = mGirl.getTop()+100;
                            head1_view1.setLayoutParams(layoutParams);
                        } else if (v_height <= 0) {
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) head1_view1.getLayoutParams();
                            layoutParams.rightMargin = 0;
//                        layoutParams.topMargin = mGirl.getTop()+100;
                            head1_view1.setLayoutParams(layoutParams);
                        } else if (v_height >= (p_height - (head1_view1.getWidth() / 2))) {
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) head1_view1.getLayoutParams();
                            layoutParams.rightMargin = (p_height - (head1_view1.getWidth() * 4 / 5));
//                        layoutParams.topMargin = mGirl.getTop()+100;
                            head1_view1.setLayoutParams(layoutParams);
                        }
                    }
                });


                //升级动态
                list1 = response.getNewest_invest_list();
                mAdapter1 = new CommonAdapter<Fragment1Model.NewestInvestListBean>
                        (getActivity(), R.layout.item_fragment2, list1) {
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
                mHeaderAndFooterWrapper1 = new HeaderAndFooterWrapper(mAdapter1);
                mHeaderAndFooterWrapper1.addHeaderView(headerView1);
                mHeaderAndFooterWrapper1.addHeaderView(headerView2);

                //说明
//                head3_textView.setText(response.getInvest_explain());
                if (!response.getInvest_explain().equals("")) {
                    head3_textView.setText(response.getInvest_explain().replace("\\n", "\n"));
                }
                mAdapter2 = new CommonAdapter<Fragment1Model>
                        (getActivity(), R.layout.item_fragment2, list2) {
                    @Override
                    protected void convert(ViewHolder holder, final Fragment1Model model, int position) {
                /*holder.setText(R.id.textView1, model.getMember_nickname());
                holder.setText(R.id.textView2, "¥ " + model.getMoney());
                holder.setText(R.id.textView3, model.getCreated_at());
                holder.setText(R.id.textView4, model.getPackage_title());
                ImageView imageView1 = holder.getView(R.id.imageView1);
                if (!model.getMember_head().equals(""))
                    Glide.with(getActivity()).load(IMGHOST + model.getMember_head())
                            .centerCrop().into(imageView1);//加载图片
                else
                    imageView1.setImageResource(R.mipmap.headimg);*/

                    }
                };
                mHeaderAndFooterWrapper2 = new HeaderAndFooterWrapper(mAdapter2);
                mHeaderAndFooterWrapper2.addHeaderView(headerView1);
                mHeaderAndFooterWrapper2.addHeaderView(headerView2);
                mHeaderAndFooterWrapper2.addHeaderView(headerView3);

                //弹出算力不足框
                if (Double.valueOf(response.getOverflow_commission_money()) > 0) {
                    dialog.contentView(R.layout.dialog_suanlibuzu)
                            .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT))
                            .animType(BaseDialog.AnimInType.CENTER)
                            .canceledOnTouchOutside(true)
                            .dimAmount(0.8f)
                            .show();
                    TextView textView1 = dialog.findViewById(R.id.textView1);
                    TextView textView2 = dialog.findViewById(R.id.textView2);

                    textView1.setText(response.getOverflow_commission_money());
                    textView2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            CommonUtil.gotoActivity(getActivity(), BuyComputingPowerActivity.class, false);
                        }
                    });
                    dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }

                changeUI();

                hideProgress();


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
            case R.id.head2_linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.head2_linearLayout2:
                type = 2;
                changeUI();
                break;

            case R.id.head1_textView3:
                //算力明细
                CommonUtil.gotoActivity(getActivity(), SuanLiListActivity.class, false);
                break;
            case R.id.head1_linearLayout1:
                //购买算力
                CommonUtil.gotoActivity(getActivity(), BuyComputingPowerActivity.class, false);
                break;
        }
    }

    private void changeUI() {
        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.blue));
            textView2.setTextColor(getResources().getColor(R.color.black4));
            head2_textView1.setTextColor(getResources().getColor(R.color.blue));
            head2_textView2.setTextColor(getResources().getColor(R.color.black4));

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            head2_view1.setVisibility(View.VISIBLE);
            head2_view2.setVisibility(View.INVISIBLE);

            recyclerView.setAdapter(mHeaderAndFooterWrapper1);
            mHeaderAndFooterWrapper1.notifyDataSetChanged();

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.black4));
            textView2.setTextColor(getResources().getColor(R.color.blue));
            head2_textView1.setTextColor(getResources().getColor(R.color.black4));
            head2_textView2.setTextColor(getResources().getColor(R.color.blue));

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
