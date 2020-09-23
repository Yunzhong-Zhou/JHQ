package com.ofc.ofc.fragment;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.activity.MainActivity;
import com.ofc.ofc.activity.PredictionDetailActivity;
import com.ofc.ofc.base.BaseFragment;
import com.ofc.ofc.model.Fragment2Model;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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

    Handler handler = new Handler();
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
                MyLogger.i(">>>>>>>>>预测" + response);
                JSONObject jObj;
                list1 = new ArrayList<Fragment2Model>();
                try {
                    jObj = new JSONObject(response);
                    JSONArray jsonArray = jObj.getJSONArray("data");
                    list1 = JSON.parseArray(jsonArray.toString(), Fragment2Model.class);
                    if (list1.size() == 0) {
                        showEmptyPage();//空数据
                    } else {
                        mAdapter1 = new CommonAdapter<Fragment2Model>
                                (getActivity(), R.layout.item_fragment2, list1) {
                            @Override
                            protected void convert(ViewHolder holder, final Fragment2Model model, int position) {
                                LineChart lineChart = holder.getView(R.id.lineChart);
                                initChart(lineChart);
                                /*new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                showLineChart(model.getKline_list(), "", getResources().getColor(R.color.black1), lineChart,
                                                        holder.getView(R.id.textView3));
                                                //显示线条渐变色
                                                Drawable drawable = getResources().getDrawable(R.drawable.huisejianbian);
                                                setChartFillDrawable(lineChart, drawable);
                                            }
                                        });
                                    }
                                }).start();*/
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        showLineChart(model.getKline_list(), "", getResources().getColor(R.color.black1), lineChart,
                                                holder.getView(R.id.textView3));
                                        //显示线条渐变色
                                        Drawable drawable = getResources().getDrawable(R.drawable.huisejianbian);
                                        setChartFillDrawable(lineChart, drawable);
                                    }
                                });
                                lineChart.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Bundle bundle1 = new Bundle();
                                        bundle1.putString("symbol", model.getSymbol());
                                        CommonUtil.gotoActivityWithData(getActivity(), PredictionDetailActivity.class, bundle1, false);
                                    }
                                });

                                if (model.getTrading_point() != null && model.getKline_list().size() > 0) {
                                    TextView textView1 = holder.getView(R.id.textView1);
                                    TextView textView2 = holder.getView(R.id.textView2);
                                    TextView textView5 = holder.getView(R.id.textView5);
                                    holder.setText(R.id.textView1, model.getSymbol() + "/");//name
//                                holder.setText(R.id.textView3, model.get+"");//1H交易量
                                    holder.setText(R.id.textView5, model.getTrading_point().getPrice() + "");//币价
                                    switch (model.getSymbol()) {
                                        case "XRP":
                                        case "TRX":
                                            holder.setText(R.id.textView6, String.format("%.6f", 7 * Double.valueOf(model.getKline_list().get(0).getClose())) + "");//
                                            break;
                                        default:
                                            holder.setText(R.id.textView6, String.format("%.2f", 7 * Double.valueOf(model.getKline_list().get(0).getClose())) + "");//
                                            break;
                                    }
                                    holder.setText(R.id.textView4, CommonUtil.timedate3(model.getTrading_point().getTimestamp() + ""));//时间
                                    TextView textView7 = holder.getView(R.id.textView7);
                                    if (model.getTrading_point().getStatus() == 1) {
                                        //买入
                                        textView7.setText(getString(R.string.fragment2_h5));
                                        textView7.setBackgroundResource(R.drawable.yuanjiao_5_lvse1);
                                        textView1.setTextColor(getResources().getColor(R.color.txt_green));
                                        textView2.setTextColor(getResources().getColor(R.color.txt_green));
                                        textView5.setTextColor(getResources().getColor(R.color.txt_green));
                                    } else {
                                        textView7.setText(getString(R.string.fragment2_h4));
                                        textView7.setBackgroundResource(R.drawable.yuanjiao_5_hongse);
                                        textView1.setTextColor(getResources().getColor(R.color.red));
                                        textView2.setTextColor(getResources().getColor(R.color.red));
                                        textView5.setTextColor(getResources().getColor(R.color.red));
                                    }
                                }

                            }
                        };
                        recyclerView.setAdapter(mAdapter1);
                        mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("symbol", list1.get(position).getSymbol());
                                CommonUtil.gotoActivityWithData(getActivity(), PredictionDetailActivity.class, bundle1, false);
//                                CommonUtil.gotoActivityWithData(getActivity(), PredictionDetailActivity_MPChart.class, bundle1, false);
                            }

                            @Override
                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                                return false;
                            }
                        });

                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                hideProgress();
                MainActivity.isOver = true;
            }
        });
    }

    /**
     * ************************************初始化图表***********************************************
     */
    private LineChart lineChart;
    private XAxis xAxis;                //X轴
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYaxis;           //右侧Y轴
    private Legend legend;              //图例
    private LimitLine limitLine;        //限制线

    private void initChart(LineChart lineChart) {
        /***图表设置***/
        //背景色
        lineChart.setBackgroundColor(Color.WHITE);
        //无数据时
        lineChart.setNoDataText(getString(R.string.app_loading2));//如果没有数据的时候，会显示这个
        lineChart.setNoDataTextColor(getResources().getColor(R.color.green));
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(false);
        //是否可以拖动
        lineChart.setDragEnabled(true);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        // 设置 是否可以缩放
        lineChart.setScaleEnabled(true);
        // 比例缩放
        lineChart.setPinchZoom(true);
        //设置XY轴动画效果
        lineChart.animateY(1000);
        lineChart.animateX(2500);

        lineChart.setAutoScaleMinMaxEnabled(true);//自适应最大最小值

        //右下角还有一个描述标签 Descripition Lable 需要在LineChart初始化时设置
        Description description = new Description();
//        description.setText("需要展示的内容");
        description.setEnabled(false);
        lineChart.setDescription(description);


        /***XY轴的设置***/
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
//        leftYAxis.setAxisMinimum(0f);
//        rightYaxis.setAxisMinimum(0f);
        //禁用网格线
        xAxis.setDrawGridLines(false);
        rightYaxis.setDrawGridLines(false);
        leftYAxis.setDrawGridLines(false);
        //去掉右侧Y轴
        rightYaxis.setEnabled(false);
        leftYAxis.setEnabled(false);
        xAxis.setEnabled(false);

//        xAxis.setLabelCount(6,false);//设置数量
        xAxis.setGranularity(20f);//设置x轴间距


        /***折线图例 标签 设置***/
        legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
        //不显示标签
        legend.setEnabled(false);

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
        //是否显示点
        lineDataSet.setDrawCircles(false);
        //是否显示值
        lineDataSet.setDrawValues(false);
        //是否显示辅助线
        lineDataSet.setHighlightEnabled(false);

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
    public void showLineChart(List<Fragment2Model.KlineListBean> dataList, String name, int color, LineChart lineChart, TextView textView) {
        //反转数据
//        Collections.reverse(dataList);
        List<Entry> entries = new ArrayList<>();
        List<Float> floatList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            Fragment2Model.KlineListBean data = dataList.get(i);
            /**
             * 在此可查看 Entry构造方法，可发现 可传入数值 Entry(float x, float y)
             * 也可传入Drawable， Entry(float x, float y, Drawable icon) 可在XY轴交点 设置Drawable图像展示
             */
            Entry entry = new Entry(i, Float.valueOf(data.getClose()));
            entries.add(entry);

            floatList.add(Float.valueOf(data.getClose()));

            if (i == dataList.size() - 1) {
                textView.setText(getString(R.string.fragment2_h2) + data.getVolume());//1H交易量
            }
        }
        //最大值
        /*leftYAxis.setAxisMinimum(Collections.min(floatList));
        leftYAxis.setAxisMaximum(Collections.max(floatList));
        rightYaxis.setAxisMinimum(Collections.min(floatList));
        rightYaxis.setAxisMaximum(Collections.max(floatList));*/
//        MyLogger.i(">>>>>>>最大>"+Collections.max(floatList));
//        MyLogger.i(">>>>>>>最小>"+Collections.min(floatList));


        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);


        Matrix matrix = new Matrix();
        matrix.postScale(1.0F, 1.0F);
        lineChart.getViewPortHandler().refresh(matrix, lineChart, false);

        lineChart.setData(lineData);
        lineChart.moveViewToX((dataList.size() - 1));//移动到最后一个值

    }

    /**
     * 设置线条填充背景颜色
     *
     * @param drawable
     */
    public void setChartFillDrawable(LineChart lineChart, Drawable drawable) {
        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
            LineDataSet lineDataSet = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            //避免在 initLineDataSet()方法中 设置了 lineDataSet.setDrawFilled(false); 而无法实现效果
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFillDrawable(drawable);
            lineChart.invalidate();
        }
    }
}
