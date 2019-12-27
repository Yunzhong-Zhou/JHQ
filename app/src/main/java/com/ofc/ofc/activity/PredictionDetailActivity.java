package com.ofc.ofc.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.tifezh.kchartlib.chart.BaseKChartView;
import com.github.tifezh.kchartlib.chart.KChartView;
import com.github.tifezh.kchartlib.chart.formatter.DateFormatter;
import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.PredictionDetailModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.ofc.ofc.view.chart.DataHelper;
import com.ofc.ofc.view.chart.KChartAdapter;
import com.ofc.ofc.view.chart.KLineEntity;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zyz on 2019-12-23.
 * 预测详情
 */
public class PredictionDetailActivity extends BaseActivity implements KChartView.KChartRefreshListener {
    PredictionDetailModel model;
    List<PredictionDetailModel.KlineListBean> list = new ArrayList<>();
//    List<PredictionDetailModel.BSBean> list_bs = new ArrayList<>();

    String symbol = "";
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8,
            textView9, textView10;
    ImageView imageView1, imageView2;
    int page = 1;

   /* CandleStickChart cc;
    BarChart bc;
    private CoupleChartGestureListener ccGesture;
    private CoupleChartGestureListener bcGesture;*/

    KChartView mKChartView;
    private KChartAdapter mAdapter;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictiondetail);

        setSwipeBackEnable(false); //主 activity 可以调用该方法，禁止滑动删除
    }

    @Override
    protected void initView() {
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                String string = "?token=" + localUserInfo.getToken()
                        + "&page=" + page//当前页号
                        + "&count=" + "168"//页面行数
                        + "&symbol=" + symbol;
                Request(string);
            }

            @Override
            public void onLoadmore() {

            }
        });

        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        textView8 = findViewByID_My(R.id.textView8);
        textView9 = findViewByID_My(R.id.textView9);
        textView10 = findViewByID_My(R.id.textView10);


        mKChartView = findViewByID_My(R.id.mKChartView);
        mAdapter = new KChartAdapter();
        mKChartView.setAdapter(mAdapter);
        mKChartView.setDateTimeFormatter(new DateFormatter());
        mKChartView.setGridRows(4);
        mKChartView.setGridColumns(4);
        mKChartView.setOnSelectedChangedListener(new BaseKChartView.OnSelectedChangedListener() {
            @Override
            public void onSelectedChanged(BaseKChartView view, Object point, int index) {
                PredictionDetailModel.KlineListBean data = (PredictionDetailModel.KlineListBean) point;
                Log.i("onSelectedChanged", "index:" + index + " closePrice:" + data.getClose());
            }
        });
        mKChartView.setRefreshListener(this);
//        onLoadMoreBegin(mKChartView);


//        initChart();
        /*//动态设置linearLayout的高度为屏幕高度的1/4
        ViewGroup.LayoutParams lp = linearLayout.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(this) / 3;*/
    }

    @Override
    protected void initData() {
        symbol = getIntent().getStringExtra("symbol");
        textView1.setText(symbol);
        switch (symbol) {
            case "BTC":
                imageView1.setImageResource(R.mipmap.ic_btc);
                break;
            case "ETH":
                imageView1.setImageResource(R.mipmap.ic_eth);
                break;
            case "LTC":
                imageView1.setImageResource(R.mipmap.ic_ltc);
                break;
            case "BCH":
                imageView1.setImageResource(R.mipmap.ic_bch);
                break;
            case "EOS":
                imageView1.setImageResource(R.mipmap.ic_eos);
                break;
            case "XRP":
                imageView1.setImageResource(R.mipmap.ic_xrp);
                break;
            case "TRX":
                imageView1.setImageResource(R.mipmap.ic_trx);
                break;
            case "ETC":
                imageView1.setImageResource(R.mipmap.ic_etc);
                break;
            case "BSV":
                imageView1.setImageResource(R.mipmap.ic_bsv);
                break;
        }
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        page = 1;
        showProgress(true, getString(R.string.app_loading));
        String string = "?token=" + localUserInfo.getToken()
                + "&page=" + page//当前页号
                + "&count=" + "168"//页面行数
                + "&symbol=" + symbol;
        Request(string);
    }


    private void Request(String string) {
        OkHttpClientManager.getAsyn(PredictionDetailActivity.this, URLs.PredictionDetail + string, new OkHttpClientManager.ResultCallback<PredictionDetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                MainActivity.isOver = true;
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                }

            }

            @Override
            public void onResponse(PredictionDetailModel response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>预测详情" + response);
                model = response;
                //（开盘价- 收盘价）/开盘价
                double zhangdie = (model.getKline().getOpen() - model.getKline().getClose()) / model.getKline().getOpen() * 100;
                textView2.setText(String.format("%.2f", zhangdie) + "%");
                switch (symbol) {
                    case "XRP":
                    case "TRX":
                        //压力位
                        textView7.setText(String.format("%.6f", model.getKline().getResistence()) + " $");
                        break;
                    default:
                        //压力位
                        textView7.setText(String.format("%.2f", model.getKline().getResistence()) + " $");
                        break;
                }

                if (zhangdie > 0) {
                    //上涨
                    textView2.setTextColor(getResources().getColor(R.color.green));
                    imageView2.setImageResource(R.mipmap.ic_jiantou_up);
                } else {
                    textView2.setTextColor(getResources().getColor(R.color.red));
                    imageView2.setImageResource(R.mipmap.ic_jiantou_down);
                }

                //建议买入卖出
                textView4.setText(model.getTrading_point().getPrice() + " $");//建议价格
                //当前价格
                textView5.setText(model.getKline().getClose() + " $");
                if (model.getTrading_point().getStatus() == 1) {
                    //买入
                    textView3.setText(getString(R.string.fragment2_h5));
                    textView3.setBackgroundResource(R.color.green);
                    textView4.setTextColor(getResources().getColor(R.color.green));
                    textView9.setText(getString(R.string.fragment2_h7));
                    textView10.setText(getString(R.string.fragment2_h12));
                } else {
                    textView3.setText(getString(R.string.fragment2_h4));
                    textView3.setBackgroundResource(R.color.red);
                    textView4.setTextColor(getResources().getColor(R.color.red));
                    textView9.setText(getString(R.string.fragment2_h6));
                    textView10.setText(getString(R.string.fragment2_h11));
                }

                //支撑位
                textView6.setText(model.getKline().getSupport() + " $");

                //建议时机
                textView8.setText(CommonUtil.timedate(model.getTrading_point().getTimestamp() + ""));


                mKChartView.showLoading();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //反转数据
                        Collections.reverse(response.getKline_list());
                        list = response.getKline_list();
                        List<KLineEntity> datas = new ArrayList<>();
                        for (PredictionDetailModel.KlineListBean bean : list) {
                            KLineEntity kLineEntity = new KLineEntity(
                                    bean.getTimestamp(),
                                    (float) bean.getOpen(),
                                    (float) bean.getHigh(),
                                    (float) bean.getLow(),
                                    (float) bean.getClose(),
                                    (float) bean.getVolume(), 0, 0, 0, 0, 0, 0,
                                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                    0
                            );
                            datas.add(kLineEntity);
                        }
                        DataHelper.calculate(datas);
                        mAdapter.addFooterData(datas);
                        mKChartView.startAnimation();
                        mKChartView.refreshEnd();

                        /*//反转数据
                        Collections.reverse(response.getKline_list());
                        list = response.getKline_list();
                *//*Collections.reverse(response.getBS());
                list_bs = response.getBS();*//*

                        showLineChart(list);
                        cc.moveViewToX((list.size() - 1));//移动到最后一个值
                        bc.moveViewToX((list.size() - 1));//移动到最后一个值*/
                    }
                });


                hideProgress();
            }
        });
    }

    List<PredictionDetailModel.KlineListBean> list1 = new ArrayList<>();

    private void RequestMore(String string) {
        MyLogger.i(">>>" + string);
        OkHttpClientManager.getAsyn(PredictionDetailActivity.this, URLs.PredictionDetail + string, new OkHttpClientManager.ResultCallback<PredictionDetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                page--;
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(PredictionDetailModel response) {
                MyLogger.i(">>>>>>>>>更多" + response);
                list1 = response.getKline_list();
                /*List<PredictionDetailModel.BSBean> list_bs1 = new ArrayList<>();
                list_bs1 = response.getBS();*/
                if (list1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mKChartView.showLoading();
                            //反转数据
                            Collections.reverse(list1);
                            List<KLineEntity> datas = new ArrayList<>();
                            for (PredictionDetailModel.KlineListBean bean : list1) {
                                KLineEntity kLineEntity = new KLineEntity(
                                        bean.getTimestamp(),
                                        (float) bean.getOpen(),
                                        (float) bean.getHigh(),
                                        (float) bean.getLow(),
                                        (float) bean.getClose(),
                                        (float) bean.getVolume(), 0, 0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                        0
                                );
                                datas.add(kLineEntity);
                            }
                            DataHelper.calculate(datas);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //第一次加载时开始动画
                                    if (mAdapter.getCount() == 0) {
                                        mKChartView.startAnimation();
                                    }
                                    mAdapter.addFooterData(datas);
                                    //加载完成，还有更多数据
                                    if (datas.size() > 0) {
                                        mKChartView.refreshComplete();
                                    }
                                    //加载完成，没有更多数据
                                    else {
                                        mKChartView.refreshEnd();
                                    }
                                }
                            });
                        }
                    }).start();


                    /*//反转数据
                    Collections.reverse(list1);
                    list.addAll(0, list1);
//                    Collections.reverse(list_bs1);
//                    list_bs.addAll(0, list_bs1);

                    //重新加载数据
                    showLineChart(list);

                    //移动到指定位置
                    cc.moveViewToX(list1.size());
                    cc.notifyDataSetChanged();*/
                }

                hideProgress();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
        }
    }

    @Override
    protected void updateView() {
//        titleView.setTitle(getString(R.string.fragment2_h3));
        titleView.setVisibility(View.GONE);
    }


    /**
     * ************************************图表***********************************************
     */



    /*private void initChart() {
     *//**
     * K线
     * *//*
        cc = findViewByID_My(R.id.chart1);
        //无数据时
        cc.setNoDataText(getString(R.string.app_loading2));//如果没有数据的时候，会显示这个
        cc.setNoDataTextColor(getResources().getColor(R.color.green));
        //是否展示网格线
        cc.setDrawGridBackground(false);
        //是否显示边界
        cc.setDrawBorders(false);
        //是否可以拖动
        cc.setDragEnabled(true);
        //是否有触摸事件
        cc.setTouchEnabled(true);
        // 设置 是否可以缩放
        cc.setScaleEnabled(true);
        //设置XY轴动画效果
        cc.animateY(1000);
        cc.animateX(2500);
        // 比例缩放
        cc.setPinchZoom(true);
        //取消描述
        cc.setDescription(null);
        //取消图例
        cc.getLegend().setEnabled(false);
        //不允许甩动惯性滑动  和moveView方法有冲突 设置为false
        cc.setDragDecelerationEnabled(false);
        //设置外边缘偏移量
        cc.setMinOffset(0);
        cc.setExtraBottomOffset(6);//设置底部外边缘偏移量 便于显示X轴


        cc.setAutoScaleMinMaxEnabled(true);//自适应最大最小值
//        cc.setDrawOrder(new CombinedChart.DrawOrder[]{CombinedChart.DrawOrder.CANDLE,
//                CombinedChart.DrawOrder.LINE});

        //X轴
        XAxis xac = cc.getXAxis();
        //X轴设置显示位置在底部
        xac.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xac.setAxisMinimum(1f);//设置离左边位置0.5个柱状图的宽度
        xac.setGranularity(1f);
        //网格线颜色
        xac.setGridColor(Color.WHITE);
        //标签颜色
        xac.setTextColor(Color.WHITE);
        //标签字体大小
        xac.setTextSize(8);
        //轴线颜色
        xac.setAxisLineColor(Color.WHITE);
        //取消轴线虚线设置
        xac.disableAxisLineDashedLine();
        //避免首尾端标签被裁剪
        xac.setAvoidFirstLastClipping(false);
        //禁用网格线
        xac.setDrawGridLines(false);
        //标签数量
        xac.setLabelCount(5, false);
        xac.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                int i = (int) value % list.size();
                if (i >= list.size()) {
                    return "";
                } else {
                    return CommonUtil.timedate1(list.get(i).getTimestamp() + "");
                }

            }
        });

        //左Y轴
        YAxis yac = cc.getAxisLeft();
        //保证Y轴从0开始，不然会上移一点
//        yac.setAxisMinimum(0f);
        //标签显示在内侧
        yac.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        //禁用网格线
        yac.setDrawGridLines(false);
        yac.setGridColor(Color.WHITE);
        yac.setTextColor(Color.WHITE);
        yac.setTextSize(8);


//        yac.setLabelCount(5, true);
//        yac.enableGridDashedLine(5, 4, 0);//横向网格线设置为虚线
        *//*yac.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {//只显示部分标签
                int index = getIndexY(value, axis.getAxisMinimum(), axis.getAxisMaximum());
                return index == 0 || index == 2 ? format4p.format(value) : "";//不显示的标签不能返回null
            }
        });*//*
        //右Y轴 - 隐藏
        cc.getAxisRight().setEnabled(false);


        *//*cc.setOnChartGestureListener(new OnChartGestureListener() {
            @Override
            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
//                MyLogger.i(">>>>>>>>>>开始" + me.getX());
            }

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
//                MyLogger.i(">>>>>>>>>>结束" + me.getX());
                if (cc.getViewPortHandler().getTransX() == 0) {
                    //加载更多数据的操作
                    page = page + 1;
                    showProgress(true, getString(R.string.app_loading));
                    String string = "?token=" + localUserInfo.getToken()
                            + "&page=" + page//当前页号
                            + "&count=" + "168"//页面行数
                            + "&symbol=" + symbol;
                    RequestMore(string);
                }
            }

            @Override
            public void onChartLongPressed(MotionEvent me) {

            }

            @Override
            public void onChartDoubleTapped(MotionEvent me) {

            }

            @Override
            public void onChartSingleTapped(MotionEvent me) {

            }

            @Override
            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
            }

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
//                MyLogger.i(">>>>>>>>>>onChartScale" + scaleY);
            }

            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY) {
//                MyLogger.i(">>>>>>>>>>onChartTranslate" + dY);

            }
        });*//*

     *//**
     * 柱状图
     *//*
        bc = findViewByID_My(R.id.chart2);
        bc.setNoDataText(getString(R.string.app_loading2));//如果没有数据的时候，会显示这个
        bc.setNoDataTextColor(getResources().getColor(R.color.green));
        //取消描述
        bc.setDescription(null);
        //取消图例
        bc.getLegend().setEnabled(false);
        //不允许甩动惯性滑动
        bc.setDragDecelerationEnabled(false);
        //设置外边缘偏移量
        bc.setMinOffset(0);
        bc.setExtraBottomOffset(6);//设置底部外边缘偏移量 便于显示X轴
        //可缩放
        bc.setScaleEnabled(true);
        // 比例缩放
        bc.setPinchZoom(true);
        //自适应最大最小值
        bc.setAutoScaleMinMaxEnabled(true);
        //设置XY轴动画效果
        bc.animateY(1000);
        bc.animateX(2500);

        //不显示X轴
        XAxis xab = bc.getXAxis();
        //X轴设置显示位置在底部
        xab.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xab.setAxisMinimum(1f);//设置离左边位置0.5个柱状图的宽度
        xab.setGranularity(1f);
        //网格线颜色
        xab.setGridColor(Color.WHITE);
        //标签颜色
        xab.setTextColor(Color.WHITE);
        //标签字体大小
        xab.setTextSize(8);
        //轴线颜色
        xab.setAxisLineColor(Color.WHITE);
        //取消轴线虚线设置
        xab.disableAxisLineDashedLine();
        //避免首尾端标签被裁剪
        xab.setAvoidFirstLastClipping(true);
        //禁用网格线
        xab.setDrawGridLines(false);
        //标签数量
        xab.setLabelCount(5, false);

        xab.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
//                int i = (int) value;
                int i = (int) value % list.size();
                if (i >= list.size()) {
                    return "";
                } else {
                    return CommonUtil.timedate1(list.get(i).getTimestamp() + "");
                }
            }
        });
        //不显示左侧Y轴
        bc.getAxisLeft().setEnabled(false);
        //不显示右侧Y轴
        bc.getAxisRight().setEnabled(false);


        ccGesture = new CoupleChartGestureListener(this, cc, bc) {//设置成全局变量，后续要用到
            @Override
            public void chartDoubleTapped(MotionEvent me) {
            }
        };
        cc.setOnChartGestureListener(ccGesture);//设置手势联动监听
        bcGesture = new CoupleChartGestureListener(this, bc, cc) {
            @Override
            public void chartDoubleTapped(MotionEvent me) {
            }
        };
        bc.setOnChartGestureListener(bcGesture);
    }

    *//**
     * 曲线初始化设置
     *//*

    private void initLineDataSet(CandleDataSet candleSet, BarDataSet barSet) {
        *//**
     * 蜡烛图
     *//*
        candleSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        candleSet.setDrawHorizontalHighlightIndicator(false);

        //高亮线
        candleSet.setHighlightLineWidth(0.5f);
        candleSet.setHighLightColor(Color.WHITE);
        candleSet.setHighlightEnabled(false);

        //阴影
        candleSet.setShadowColor(Color.YELLOW);
        candleSet.setShadowWidth(0.7f);
        candleSet.setShadowColorSameAsCandle(true);//阴影颜色与蜡烛相同

        //上涨
        candleSet.setIncreasingColor(getResources().getColor(R.color.txt_green));
        candleSet.setIncreasingPaintStyle(Paint.Style.FILL);

        //下跌
        candleSet.setDecreasingColor(getResources().getColor(R.color.txt_red));
        candleSet.setDecreasingPaintStyle(Paint.Style.FILL);

        //中立
        candleSet.setNeutralColor(Color.WHITE);

        //空白间隙 0.1（10%)
        candleSet.setBarSpace(0.1f);
        //设置是否显示蜡烛图上的文字
        candleSet.setDrawValues(false);

        //图标
        candleSet.setDrawIcons(true);
        *//*MPPointF offsetDp = new MPPointF(0,50);
        candleSet.setIconsOffset(offsetDp);*//*


     *//**
     * 柱状图
     *//*
        //高亮线
        barSet.setHighLightColor(Color.WHITE);
        barSet.setHighlightEnabled(false);
        //柱状颜色
        List<GradientColor> gradientColors = new ArrayList<>();
        gradientColors.add(new GradientColor(getResources().getColor(R.color.txt_green)//开始颜色
                , getResources().getColor(R.color.txt_green)));//结束颜色
        gradientColors.add(new GradientColor(getResources().getColor(R.color.txt_red)
                , getResources().getColor(R.color.txt_red)));
        barSet.setGradientColors(gradientColors);
        barSet.setDrawValues(false);

    }

    private void showLineChart(List<PredictionDetailModel.KlineListBean> list) {
        //添加数据
        //蜡烛图数据
        ArrayList<CandleEntry> candleEntrie = new ArrayList<>();
        //柱状图
        ArrayList<BarEntry> barEntrie = new ArrayList<>();

        *//*for (PredictionDetailModel.KlineListBean model : list) {
            for (PredictionDetailModel.BSBean model_bs :list_bs){
                if (CommonUtil.timedate2(model.getTimestamp()).equals(model_bs.getTimestamp())){
                    MyLogger.i(">>>>>>>");
                }
            }
        }*//*

        //设置新的数据
        for (int i = 0; i < list.size(); i++) {
            //蜡烛图数据
            switch (list.get(i).getStatus()) {
                case "1":
                    //买入
                    candleEntrie.add(new CandleEntry(
                                    i,
                                    (float) list.get(i).getHigh(),
                                    (float) list.get(i).getLow(),
                                    (float) list.get(i).getOpen(),
                                    (float) list.get(i).getClose(),
                                    getResources().getDrawable(R.mipmap.ic_b),
                                    list.get(i).getStatus()
                            )
                    );


                    break;
                case "0":
                    //卖出
                    candleEntrie.add(new CandleEntry(
                                    i,
                                    (float) list.get(i).getHigh(),
                                    (float) list.get(i).getLow(),
                                    (float) list.get(i).getOpen(),
                                    (float) list.get(i).getClose(),
                                    getResources().getDrawable(R.mipmap.ic_s),
                                    list.get(i).getStatus()
                            )
                    );
                    break;
                default:
                    //买入
                    candleEntrie.add(new CandleEntry(
                                    i,
                                    (float) list.get(i).getHigh(),
                                    (float) list.get(i).getLow(),
                                    (float) list.get(i).getOpen(),
                                    (float) list.get(i).getClose(),
                                    getResources().getDrawable(R.color.transparent),
                                    list.get(i).getStatus()
                            )
                    );
                    break;
            }


            //柱状图
            barEntrie.add(new BarEntry(
                    i,
                    (float) list.get(i).getVolume()
            ));
        }

        //设置数据
        CandleDataSet candleSet = new CandleDataSet(candleEntrie, "");

        BarDataSet barSet = new BarDataSet(barEntrie, "");
        initLineDataSet(candleSet, barSet);

        *//**
     * 蜡烛图
     *//*
        cc.resetTracking();
        CandleData data = new CandleData(candleSet);
        cc.setData(data);
        cc.invalidate();

        Matrix matrix = new Matrix();
        matrix.postScale(6 * page, 1.0f);//以指定的比例对矩阵进行后期处理 M'= S（sx，sy）* M
        cc.setScaleMinima(1.0f, 1.0f);
        cc.getViewPortHandler().refresh(matrix, cc, false);


        */

    /**
     * 柱状图
     *//*
        bc.resetTracking();
        BarData barData = new BarData(barSet);
        bc.setData(barData);
        bc.invalidate();


        Matrix matrix1 = new Matrix();
        matrix1.postScale(6 * page, 1.0f);//以指定的比例对矩阵进行后期处理 M'= S（sx，sy）* M
        bc.setScaleMinima(1.0f, 1.0f);
        bc.getViewPortHandler().refresh(matrix1, bc, false);
    }

    @Override
    public void edgeLoad(float x, boolean left) {
        MyLogger.i(">>>>>>>" + x + ">>>" + left + ">>>>" + cc.getViewPortHandler().getTransX());
        if (left) {
            //加载更多数据的操作
            page = page + 1;
            showProgress(false, getString(R.string.app_loading));
            String string = "?token=" + localUserInfo.getToken()
                    + "&page=" + page//当前页号
                    + "&count=" + "168"//页面行数
                    + "&symbol=" + symbol;
            RequestMore(string);
        }

    }*/

    //屏蔽返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            return true;//不执行父类点击事件
        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }

    @Override
    public void onLoadMoreBegin(KChartView chart) {
        //加载更多
        //加载更多数据的操作
        page = page + 1;
        showProgress(false, getString(R.string.app_loading));
        String string = "?token=" + localUserInfo.getToken()
                + "&page=" + page//当前页号
                + "&count=" + "168"//页面行数
                + "&symbol=" + symbol;
        RequestMore(string);
    }
}
