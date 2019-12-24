package com.ofc.ofc.activity;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.model.GradientColor;
import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.PredictionDetailModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.squareup.okhttp.Request;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zyz on 2019-12-23.
 * 预测详情
 */
public class PredictionDetailActivity extends BaseActivity {
    PredictionDetailModel model;
    List<PredictionDetailModel.KlineListBean> list = new ArrayList<>();
    List<PredictionDetailModel.BSBean> list_bs = new ArrayList<>();

    String symbol = "";
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8;
    ImageView imageView1, imageView2;
    int page = 1;

    CandleStickChart cc;
    BarChart bc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictiondetail);
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

        initChart();
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
                    showToast(info);
                }

            }

            @Override
            public void onResponse(PredictionDetailModel response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>预测详情" + response);
                model = response;


                //反转数据
                Collections.reverse(response.getKline_list());
                list = response.getKline_list();
                Collections.reverse(response.getBS());
                list_bs = response.getBS();

                showLineChart(list, list_bs);
                cc.moveViewToX((list.size() - 1));//移动到最后一个值
                bc.moveViewToX((list.size() - 1));//移动到最后一个值

                hideProgress();
            }
        });
    }

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
                hideProgress();
                MyLogger.i(">>>>>>>>>更多" + response);
                JSONObject jObj;
                List<PredictionDetailModel.KlineListBean> list1 = new ArrayList<>();
                list1 = response.getKline_list();
                List<PredictionDetailModel.BSBean> list_bs1 = new ArrayList<>();
                list_bs1 = response.getBS();
                if (list1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    //反转数据
                    Collections.reverse(list1);
                    list.addAll(0, list1);
                    Collections.reverse(list_bs1);
                    list_bs.addAll(0, list_bs1);

                    //重新加载数据
                    showLineChart(list, list_bs);

                    //移动到指定位置
                    cc.moveViewToX(list1.size());
                    cc.notifyDataSetChanged();

                }
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
     * ************************************初始化图表***********************************************
     */
    private void initChart() {
        /**
         * K线
         * */
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
        cc.animateY(2500);
        cc.animateX(1500);
        // 比例缩放
//        cc.setPinchZoom(true);
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
        /*yac.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {//只显示部分标签
                int index = getIndexY(value, axis.getAxisMinimum(), axis.getAxisMaximum());
                return index == 0 || index == 2 ? format4p.format(value) : "";//不显示的标签不能返回null
            }
        });*/
        //右Y轴 - 隐藏
        cc.getAxisRight().setEnabled(false);


        cc.setOnChartGestureListener(new OnChartGestureListener() {
            @Override
            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

            }

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
//                MyLogger.i(">>>>>>>>>>" + cc.getViewPortHandler().getTransX());
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

            }

            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY) {

            }
        });

        /**
         * 柱状图
         */
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
        //自适应最大最小值
        bc.setAutoScaleMinMaxEnabled(true);


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

    }

    /**
     * 曲线初始化设置
     */

    private void initLineDataSet(CandleDataSet candleSet, BarDataSet barSet) {
        /**
         * 蜡烛图
         */
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

        //图标
        candleSet.setDrawIcons(true);

        candleSet.setDrawValues(false);

        /**
         * 柱状图
         */
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

    private void showLineChart(List<PredictionDetailModel.KlineListBean> list, List<PredictionDetailModel.BSBean> list_bs) {
        //添加数据
        //蜡烛图数据
        ArrayList<CandleEntry> candleEntrie = new ArrayList<>();
        //柱状图
        ArrayList<BarEntry> barEntrie = new ArrayList<>();
        //设置新的数据
        for (int i = 0; i < list.size(); i++) {
            //蜡烛图数据
            for (int j = 0; j < list_bs.size(); j++) {
                //判断年月日 小时 相同
                if (CommonUtil.timedate2(list.get(i).getTimestamp()).equals(CommonUtil.timedate2(list_bs.get(j).getTimestamp()))) {
                    if (list_bs.get(j).getStatus() == 1) {
                        //买入 -b
                        MyLogger.i(">>>>>>买入 -b"+i);
                        candleEntrie.add(new CandleEntry(
                                        i,
                                        (float) list.get(i).getHigh(),
                                        (float) list.get(i).getLow(),
                                        (float) list.get(i).getOpen(),
                                        (float) list.get(i).getClose(),
                                        getResources().getDrawable(R.mipmap.ic_b)
                                )
                        );
                    } else {
                        //卖出-s
                        MyLogger.i(">>>>>>卖出-s"+i);
                        candleEntrie.add(new CandleEntry(
                                        i,
                                        (float) list.get(i).getHigh(),
                                        (float) list.get(i).getLow(),
                                        (float) list.get(i).getOpen(),
                                        (float) list.get(i).getClose(),
                                        getResources().getDrawable(R.mipmap.ic_s)
                                )
                        );
                    }
                } else {
                    candleEntrie.add(new CandleEntry(
                                    i,
                                    (float) list.get(i).getHigh(),
                                    (float) list.get(i).getLow(),
                                    (float) list.get(i).getOpen(),
                                    (float) list.get(i).getClose(),
                                    getResources().getDrawable(R.color.transparent)
                            )
                    );
                }

            }


            //柱状图
            barEntrie.add(new BarEntry(
                    i,
                    (float) list.get(i).getVolume()
            ));
        }

        CandleDataSet candleSet = new CandleDataSet(candleEntrie, "");
        BarDataSet barSet = new BarDataSet(barEntrie, "");
        initLineDataSet(candleSet, barSet);

        /**
         * 蜡烛图
         */
        cc.resetTracking();
        CandleData data = new CandleData(candleSet);
        cc.setData(data);
        cc.invalidate();

        Matrix matrix = new Matrix();

        matrix.postScale(6 * page, 1.0f);//以指定的比例对矩阵进行后期处理 M'= S（sx，sy）* M
        cc.setScaleMinima(1.0f, 1.0f);
        cc.getViewPortHandler().refresh(matrix, cc, false);

        /**
         * 柱状图
         */
        bc.resetTracking();
        BarData barData = new BarData(barSet);
        bc.setData(barData);
        bc.invalidate();

        Matrix matrix1 = new Matrix();
        matrix1.postScale(6 * page, 1.0f);//以指定的比例对矩阵进行后期处理 M'= S（sx，sy）* M
        bc.setScaleMinima(1.0f, 1.0f);
        bc.getViewPortHandler().refresh(matrix1, bc, false);
    }

}
