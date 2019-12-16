package com.cfo.chocoin.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cfo.chocoin.R;
import com.cfo.chocoin.activity.MainActivity;
import com.cfo.chocoin.base.BaseFragment;
import com.cfo.chocoin.model.Fragment2Model;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.MyLogger;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 区块
 */
public class Fragment2 extends BaseFragment {
    private RecyclerView recyclerView;
    List<Fragment2Model> list1 = new ArrayList<>();
    CommonAdapter<Fragment2Model> mAdapter1;


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
        if (MainActivity.item == 1) {
            requestServer();
        }
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
                String string = "?token=" + localUserInfo.getToken();
                Request(string);
            }

            @Override
            public void onLoadmore() {

            }
        });

        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);

        for (int i = 0; i < 10; i++) {
            list1.add(new Fragment2Model());
        }
        mAdapter1 = new CommonAdapter<Fragment2Model>
                (getActivity(), R.layout.item_fragment2, list1) {
            @Override
            protected void convert(ViewHolder holder, final Fragment2Model model, int position) {
                /*LineChart lineChart = holder.getView(R.id.lineChart);
                initChart(lineChart);
                showLineChart(model.getMoneylist(),"",getResources().getColor(R.color.white),lineChart);*/

                /*holder.setText(R.id.textView1, model.getMember_nickname());
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
                    imageView1.setImageResource(R.mipmap.headimg);*/

            }
        };
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
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment2 + string, new OkHttpClientManager.ResultCallback<Fragment2Model>() {
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
            public void onResponse(final Fragment2Model response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>区块" + response);
                /*//待激活
                head1_textView1.setText(response.getBlock_wait_active_money());
                //总区块
                head1_textView3.setText(response.getBlock_money());
                //已激活
                head1_textView4.setText(response.getBlock_active_money());
                //已奖励
                head1_textView5.setText(response.getBlock_award_money());
                //未奖励
                head1_textView6.setText(response.getWait_block_award_money());

                //参与记录
                linearLayout_add1.removeAllViews();
                if (response.getMy_block_list().size() > 0) {
                    linearLayout_add1.setVisibility(View.VISIBLE);
                    linearLayout_empty.setVisibility(View.GONE);
                    for (int i = 0; i < response.getMy_block_list().size(); i++) {
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View view = inflater.inflate(R.layout.item_myqukuai, null, false);
                        view.setLayoutParams(lp);

                        //进度条
                        RatingBar add_ratingBar = view.findViewById(R.id.add_ratingBar);
                        float max = Float.valueOf(response.getMy_block_list().get(i).getMoney());

                        MyLogger.i(">>>>最大>" + (int) max + ">>>>>当前>" + Float.valueOf(response.getMy_block_list().get(i).getHas_money()));
                        add_ratingBar.setNumStars((int) max);
                        add_ratingBar.setRating(Float.valueOf(response.getMy_block_list().get(i).getHas_money()));

                        LinearLayout add_ll1 = view.findViewById(R.id.add_ll1);
                        TextView add_tv1 = view.findViewById(R.id.add_tv1);
                        TextView add_tv2 = view.findViewById(R.id.add_tv2);
                        TextView add_tv3 = view.findViewById(R.id.add_tv3);
                        TextView add_tv4 = view.findViewById(R.id.add_tv4);
                        TextView add_tv5 = view.findViewById(R.id.add_tv5);
                        if (response.getMy_block_list().get(i).getStatus() == 2) {
                            //已激活
                            add_ll1.setBackgroundResource(R.drawable.yuanjiaobiankuang_10_huise);
                            add_tv3.setTextColor(getResources().getColor(R.color.green));
                        } else {
                            add_ll1.setBackgroundResource(R.drawable.yuanjiao_10_huise);
                            add_tv3.setTextColor(getResources().getColor(R.color.black1));
                        }
                        add_tv1.setText(getString(R.string.fragment2_h21) + (i + 1));
                        add_tv2.setText(response.getMy_block_list().get(i).getCreated_at());
                        add_tv3.setText(response.getMy_block_list().get(i).getStatus_title());
                        add_tv4.setText(response.getMy_block_list().get(i).getMoney());
                        add_tv5.setText(response.getMy_block_list().get(i).getHas_money());

                        linearLayout_add1.addView(view);
                    }
                } else {
                    linearLayout_add1.setVisibility(View.GONE);
                    linearLayout_empty.setVisibility(View.VISIBLE);
                }
                //激活动态
                list1 = response.getNewest_block_all();
                mAdapter1 = new CommonAdapter<Fragment2Model.NewestBlockAllBean>
                        (getActivity(), R.layout.item_fragment2, list1) {
                    @Override
                    protected void convert(ViewHolder holder, final Fragment2Model.NewestBlockAllBean model, int position) {
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
//                head3_textView.setText(response.getBlock_explain());
                if (!response.getBlock_explain().equals("")){
                    head3_textView.setText(response.getBlock_explain().replace("\\n", "\n"));
                }
                mAdapter2 = new CommonAdapter<Fragment2Model>
                        (getActivity(), R.layout.item_fragment2, list2) {
                    @Override
                    protected void convert(ViewHolder holder, final Fragment2Model model, int position) {
                *//*holder.setText(R.id.textView1, model.getMember_nickname());
                holder.setText(R.id.textView2, "¥ " + model.getMoney());
                holder.setText(R.id.textView3, model.getCreated_at());
                holder.setText(R.id.textView4, model.getPackage_title());
                ImageView imageView1 = holder.getView(R.id.imageView1);
                if (!model.getMember_head().equals(""))
                    Glide.with(getActivity()).load(IMGHOST + model.getMember_head())
                            .centerCrop().into(imageView1);//加载图片
                else
                    imageView1.setImageResource(R.mipmap.headimg);*//*

                    }
                };
                mHeaderAndFooterWrapper2 = new HeaderAndFooterWrapper(mAdapter2);
                mHeaderAndFooterWrapper2.addHeaderView(headerView1);
                mHeaderAndFooterWrapper2.addHeaderView(headerView2);
                mHeaderAndFooterWrapper2.addHeaderView(headerView3);*/

                hideProgress();

                MainActivity.isOver = true;
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }



    /**
     * 初始化图表
     */
    private void initChart(LineChart lineChart) {
        /***图表设置***/
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(true);
        //是否可以拖动
        lineChart.setDragEnabled(false);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        //设置XY轴动画效果
        lineChart.animateY(2500);
        lineChart.animateX(1500);

        /***XY轴的设置***/
        /*xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(0f);
        rightYaxis.setAxisMinimum(0f);*/

        /***折线图例 标签 设置***/
        /*legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);*/
    }

    /**
     * 曲线初始化设置 一个LineDataSet 代表一条曲线
     *
     * @param lineDataSet 线条
     * @param color       线条颜色
     * @param mode
     */
    private void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        //设置折线图填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }
    }

    /**
     * 展示曲线
     *
     * @param dataList 数据集合
     * @param name     曲线名称
     * @param color    曲线颜色
     */
    public void showLineChart(List<Fragment2Model.MoneylistBean> dataList, String name, int color,LineChart lineChart) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < dataList.size(); i++) {
            arrayList.add(new Entry(i, dataList.get(i).getMoney()));
        }
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(arrayList, name);
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
    }


}
