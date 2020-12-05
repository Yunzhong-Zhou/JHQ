package com.fone.fone.fragment;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.liaoinstan.springview.widget.SpringView;
import com.fone.fone.R;
import com.fone.fone.activity.DRVTBuyActivity;
import com.fone.fone.activity.DRVTJiaoYiActivity;
import com.fone.fone.activity.DRVTSellActivity;
import com.fone.fone.activity.DRVTSharePeopleActivity;
import com.fone.fone.activity.FenHongListActivity;
import com.fone.fone.activity.MainActivity;
import com.fone.fone.activity.OFCAccountDetailActivity;
import com.fone.fone.activity.OFCSharePeopleActivity;
import com.fone.fone.base.BaseFragment;
import com.fone.fone.model.FenHongModel;
import com.fone.fone.model.FenHong_ChartModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.okhttp.websocket.WebSocketManager;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.fone.fone.view.LineChartMarkView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.fone.fone.net.OkHttpClientManager.IMGHOST;


/**
 * Created by zyz on 2016/1/6.
 * 充值
 */
public class Fragment4_2 extends BaseFragment {
    FenHongModel model;
    TextView tv_usdt, tv_fenhongzhishu, tv_toal, tv_24h, tv_mairu, tv_jisuan, tv_faxingjia, tv_ofc_yue, tv_zengzhi,
            tv_heyue, tv_usdt_yue, tv_yifenhong, tv_keyong,tv_drvt1,tv_drvt2,tv_danjia,tv_zongliang,tv_more;
    ImageView iv_toal, iv_24h, iv_jian, iv_jia, iv_zengzhi,iv_goumai,iv_xinxi;
    EditText et_keyong;
    LinearLayout ll_ofc, ll_usdt, ll_tuiguang,ll_drvt_jiaoyi,ll_zhituijiaoyi;


    RecyclerView recyclerView;
    CommonAdapter<FenHongModel.DrvtBuyListBean> mAdapter;
    List<FenHongModel.DrvtBuyListBean> list = new ArrayList<>();

    //折线图
    LineChart lineChart;
    Handler handler = new Handler();
    TextView tv_name, tv_value, tv_date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_addfenhong, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        /*if (MainActivity.item == 3) {
            requestServer();
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.item == 3) {
            requestServer();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //关闭连接
        WebSocketManager.getInstance().close();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if (MainActivity.item == 3) {
            requestServer();
        }*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 3) {
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
                RequestChart(string);
            }

            @Override
            public void onLoadmore() {
            }
        });
        //普通数据
        tv_usdt = findViewByID_My(R.id.tv_usdt);
        tv_fenhongzhishu = findViewByID_My(R.id.tv_fenhongzhishu);
        tv_toal = findViewByID_My(R.id.tv_toal);
        tv_24h = findViewByID_My(R.id.tv_24h);
        tv_mairu = findViewByID_My(R.id.tv_mairu);
        tv_jisuan = findViewByID_My(R.id.tv_jisuan);
        tv_faxingjia = findViewByID_My(R.id.tv_faxingjia);
        tv_ofc_yue = findViewByID_My(R.id.tv_ofc_yue);
        tv_zengzhi = findViewByID_My(R.id.tv_zengzhi);
        tv_heyue = findViewByID_My(R.id.tv_heyue);
        tv_usdt_yue = findViewByID_My(R.id.tv_usdt_yue);
        tv_yifenhong = findViewByID_My(R.id.tv_yifenhong);
        iv_toal = findViewByID_My(R.id.iv_toal);
        iv_24h = findViewByID_My(R.id.iv_24h);
        iv_jian = findViewByID_My(R.id.iv_jian);
        iv_jia = findViewByID_My(R.id.iv_jia);
        iv_zengzhi = findViewByID_My(R.id.iv_zengzhi);
        et_keyong = findViewByID_My(R.id.et_keyong);
        tv_keyong = findViewByID_My(R.id.tv_keyong);
        tv_keyong.setText(getString(R.string.qianbao_h60) + "0.00USDT");
        //输入监听
        et_keyong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (model.getOfc_price() != null) {
                    if (!et_keyong.getText().toString().trim().equals("")) {
//                        double shouxufei = Double.valueOf(et_keyong.getText().toString().trim()) * Double.valueOf(model.getUsdt_price()) / Double.valueOf(model.getOfc_price());
                        double shouxufei = Double.valueOf(et_keyong.getText().toString().trim()) * Double.valueOf(model.getOfc_price());
                        tv_jisuan.setText("=" + String.format("%.2f", shouxufei) + "USDT");
                    } else {
                        tv_jisuan.setText("=0USDT");
                    }
                }
            }
        });
        ll_ofc = findViewByID_My(R.id.ll_ofc);
        ll_usdt = findViewByID_My(R.id.ll_usdt);
        iv_jian.setOnClickListener(this);
        iv_jia.setOnClickListener(this);
        tv_mairu.setOnClickListener(this);
        ll_ofc.setOnClickListener(this);
        ll_usdt.setOnClickListener(this);
        ll_tuiguang = findViewByID_My(R.id.ll_tuiguang);
        ll_tuiguang.setOnClickListener(this);

        tv_drvt1 = findViewByID_My(R.id.tv_drvt1);
        tv_drvt2 = findViewByID_My(R.id.tv_drvt2);
        ll_drvt_jiaoyi = findViewByID_My(R.id.ll_drvt_jiaoyi);
        ll_drvt_jiaoyi.setOnClickListener(this);
        ll_zhituijiaoyi = findViewByID_My(R.id.ll_zhituijiaoyi);
        ll_zhituijiaoyi.setOnClickListener(this);

        tv_danjia = findViewByID_My(R.id.tv_danjia);
        tv_danjia.setOnClickListener(this);
        tv_zongliang = findViewByID_My(R.id.tv_zongliang);
        tv_zongliang.setOnClickListener(this);
        tv_more = findViewByID_My(R.id.tv_more);
        tv_more.setOnClickListener(this);
        iv_goumai = findViewByID_My(R.id.iv_goumai);
        iv_goumai.setOnClickListener(this);
        iv_xinxi = findViewByID_My(R.id.iv_xinxi);
        iv_xinxi.setOnClickListener(this);
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //折线图
        lineChart = findViewByID_My(R.id.lineChart);
        tv_name = findViewByID_My(R.id.tv_name);
        tv_value = findViewByID_My(R.id.tv_value);
        tv_date = findViewByID_My(R.id.tv_date);
    }

    @Override
    protected void initData() {
//        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        String string = "?token=" + localUserInfo.getToken();
        Request(string);

        RequestChart(string);
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.FenHong + string, new OkHttpClientManager.ResultCallback<FenHongModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(final FenHongModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>分红" + response);
                model = response;
                tv_usdt.setText(model.getOfc_price());
                tv_fenhongzhishu.setText(getString(R.string.qianbao_h34) + "：" + model.getOfc_index() + "USDT");

                tv_toal.setText("Total +" + model.getToal_appreciation() + "%");

                /*if (model.getToal_appreciation() >= 0) {
                    tv_toal.setTextColor(getResources().getColor(R.color.green_1));
                    tv_toal.setText("Toal +" + model.getToal_appreciation() + "%");
                } else {
                    tv_toal.setTextColor(getResources().getColor(R.color.red_1));
                    tv_toal.setText("Toal " + model.getToal_appreciation() + "%");
                }*/

                tv_24h.setText("24H +" + model.getLast_appreciation() + "%");
//                tv_zengzhi.setText(model.getAppreciation() + "%");
                tv_zengzhi.setText(model.getInterest_money() + "");
                tv_keyong.setText(getString(R.string.qianbao_h60) + model.getCommon_usable_money() + "USDT");//可用
                /*if (model.getLast_appreciation() >= 0) {
                    tv_24h.setTextColor(getResources().getColor(R.color.green_1));
                    tv_24h.setText("24H +" + model.getLast_appreciation() + "%");

                    tv_zengzhi.setTextColor(getResources().getColor(R.color.green_1));
                    tv_zengzhi.setText(model.getLast_appreciation() + "%");
                } else {
                    tv_24h.setTextColor(getResources().getColor(R.color.red_1));
                    tv_24h.setText("24H " + model.getLast_appreciation() + "%");

                    tv_zengzhi.setTextColor(getResources().getColor(R.color.red_1));
                    tv_zengzhi.setText(model.getLast_appreciation() + "%");
                }*/
//                et_keyong.setHint(getString(R.string.fragment1_h10) + model.getCommon_usable_money());
                tv_ofc_yue.setText(model.getOfc_amount_money());
                tv_usdt_yue.setText(model.getTeam_performance_ofc_money());

                tv_faxingjia.setText(getString(R.string.qianbao_h36) + model.getOfc_issue_price() + "USDT");

                tv_heyue.setText("" + model.getDirect_performance_ofc_money());
                tv_yifenhong.setText(model.getInterest_money());

                tv_drvt1.setText(model.getDirect_performance_drvt_buy_money());
                tv_drvt2.setText(model.getTeam_performance_drvt_buy_money());

                tv_danjia.setText(model.getDrvt_buy_max_drvt_price());
                tv_zongliang.setText(model.getDrvt_buy_amount_money());

                list = response.getDrvt_buy_list();
                if (list.size()>0){
                    mAdapter = new CommonAdapter<FenHongModel.DrvtBuyListBean>(
                            getActivity(), R.layout.item_drvtjiaoyi_fragment4, list) {
                        @Override
                        protected void convert(ViewHolder holder, FenHongModel.DrvtBuyListBean model, int position) {
                            holder.setText(R.id.tv_name, model.getMember_nickname());//昵称
                            holder.setText(R.id.tv_num, String.format("%.2f", Double.valueOf(model.getAmount_money())-Double.valueOf(model.getCurrent_money())) +"");//数量
                            holder.setText(R.id.tv_price, model.getDrvt_price()+"USDT");//单价
                            holder.setText(R.id.tv_all, model.getAmount_money());//总量
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(getActivity())
                                    .load(IMGHOST + model.getMember_head())
                                    .centerCrop()
//                    .placeholder(R.mipmap.headimg)//加载站位图
//                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片

                            holder.getView(R.id.tv_sell).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", model.getId());
                                    CommonUtil.gotoActivityWithData(getActivity(), DRVTSellActivity.class, bundle, false);
                                }
                            });
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                }else {
                    showEmptyPage();
                }

            }
        });
    }

    private void RequestChart(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.FenHong_Chart + string, new OkHttpClientManager.ResultCallback<FenHong_ChartModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(final FenHong_ChartModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>分红Chart" + response);
                //折线图
                initChart(lineChart);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        showLineChart(response.getOfc_price_list(), "", getResources().getColor(R.color.green_1), lineChart, response.getMin(), response.getMax());
                        //显示线条渐变色
                        Drawable drawable = getResources().getDrawable(R.drawable.lvsejianbian);
                        setChartFillDrawable(lineChart, drawable);
                    }
                });

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_jian:
                //买涨-减
                double tempMoney_jian_left = 0;
                if (!et_keyong.getText().toString().trim().equals("")) {
                    tempMoney_jian_left = Double.valueOf(et_keyong.getText().toString().trim());
                }
                if (tempMoney_jian_left > 10) {
                    tempMoney_jian_left -= 10;
                    et_keyong.setText((int) tempMoney_jian_left + "");
                } else {
                    tempMoney_jian_left = 0;
                    et_keyong.setText("");
                }

                //计算手续费 输入币数 * usdt_price / ofc_price;
//                double shouxufei1 = tempMoney_jian_left * Double.valueOf(model.getUsdt_price()) / Double.valueOf(model.getOfc_price());
                double shouxufei1 = tempMoney_jian_left * Double.valueOf(model.getOfc_price());
                tv_jisuan.setText("=" + String.format("%.2f", shouxufei1) + "USDT");

                break;
            case R.id.iv_jia:
                //买涨-加
                double tempMoney_jia_left = 0;
                if (!et_keyong.getText().toString().trim().equals("")) {
                    tempMoney_jia_left = Double.valueOf(et_keyong.getText().toString().trim());
                }
                /*if (Double.valueOf(model.getCommon_usable_money()) > tempMoney_jia_left) {
                    if ((Double.valueOf(model.getCommon_usable_money()) - tempMoney_jia_left) >= 10) {
                        tempMoney_jia_left += 10;
                    } else {
                        tempMoney_jia_left = Double.valueOf(model.getCommon_usable_money());
                    }
                }*/
                tempMoney_jia_left += 10;

                et_keyong.setText((int) tempMoney_jia_left + "");

                //计算手续费 输入币数 * usdt_price / ofc_price;
//                double shouxufei2 = tempMoney_jia_left * Double.valueOf(model.getUsdt_price()) / Double.valueOf(model.getOfc_price());
                double shouxufei2 = tempMoney_jia_left * Double.valueOf(model.getOfc_price());
                tv_jisuan.setText("=" + String.format("%.2f", shouxufei2) + "USDT");

                break;
            case R.id.tv_mairu:
                //买入
                if (!et_keyong.getText().toString().trim().equals("")) {
                    if (Double.valueOf(et_keyong.getText().toString().trim()) >= 1) {
                        tv_mairu.setClickable(false);
                        showProgress(true, getString(R.string.app_loading1));
                        HashMap<String, String> params = new HashMap<>();
                        params.put("hk", model.getHk());
//                        params.put("change_game_id", model.getChange_game().getId());
//                        params.put("type", "1");//类型（1.看涨 2.看跌）
                        params.put("ofc_money", et_keyong.getText().toString().trim());
                        params.put("token", localUserInfo.getToken());
                        requestAdd(params);

                    } else {
                        myToast(getString(R.string.qianbao_h77));
                    }
                } else {
                    myToast(getString(R.string.fragment1_h25));
                }
                break;
            case R.id.ll_ofc:
                //ofc
                CommonUtil.gotoActivity(getActivity(), OFCAccountDetailActivity.class, false);
                break;
            case R.id.ll_usdt:
                //usdt
//                CommonUtil.gotoActivity(getActivity(), RechargeActivity.class, false);
                break;
            case R.id.ll_tuiguang:
                //推广
                CommonUtil.gotoActivity(getActivity(), OFCSharePeopleActivity.class, false);
                break;
            case R.id.ll_drvt_jiaoyi:
                CommonUtil.gotoActivity(getActivity(), DRVTJiaoYiActivity.class, false);
                break;
            case R.id.ll_zhituijiaoyi:
                CommonUtil.gotoActivity(getActivity(), DRVTSharePeopleActivity.class, false);
                break;

            case R.id.iv_goumai:
                CommonUtil.gotoActivity(getActivity(), DRVTBuyActivity.class, false);
                break;
            case R.id.iv_xinxi:
                CommonUtil.gotoActivity(getActivity(), DRVTJiaoYiActivity.class, false);
                /*Bundle bundle = new Bundle();
                bundle.putString("id", "");
                CommonUtil.gotoActivityWithData(getActivity(), JiaoyiListActivity.class, bundle, false);*/
                break;

            case R.id.tv_more:
                //更多
                CommonUtil.gotoActivity(getActivity(), DRVTJiaoYiActivity.class, false);
                break;
        }
    }

    private void requestAdd(HashMap<String, String> params) {
        OkHttpClientManager.postAsyn(getActivity(), URLs.AddFenHong, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    //是否包含"余币不足"
                    if (info.contains(getString(R.string.fragment1_h27))) {
                        showToast(info, getString(R.string.fragment1_h28),
                                getString(R.string.app_cancel),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //充值
                                        dialog.dismiss();
                                        MainActivity.item = 3;
                                        MainActivity.navigationBar.selectTab(3);
//                                        CommonUtil.gotoActivity(getActivity(), RechargeActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //取消
                                        dialog.dismiss();
                                    }
                                });
                    } else {
                        showToast(info);
                    }

                }
                tv_mairu.setClickable(true);

                requestServer();
            }

            @Override
            public void onResponse(final String response) {
                tv_mairu.setClickable(true);
//                hideProgress();
                MyLogger.i(">>>>>>>>>合约买入" + response);

                et_keyong.setText("");
                tv_jisuan.setText("=0USDT");

                requestServer();
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    String info = jObj.getString("msg");
                    myToast(info);

                    JSONObject jObj1 = new JSONObject(jObj.getString("data"));
                    String id = jObj1.getString("id");
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id);
                    CommonUtil.gotoActivityWithData(getActivity(), FenHongListActivity.class, bundle, false);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, false);
    }

    @Override
    protected void updateView() {

    }


    /**
     * ************************************初始化图表***********************************************
     */
    private XAxis xAxis;                //X轴
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYaxis;           //右侧Y轴
    private Legend legend;              //图例
    private LimitLine limitLine;        //限制线

    private void initChart(LineChart lineChart) {
        /***图表设置***/
        //背景色
        lineChart.setBackgroundColor(Color.TRANSPARENT);
        //无数据时
        lineChart.setNoDataText(getString(R.string.app_loading2));//如果没有数据的时候，会显示这个
        lineChart.setNoDataTextColor(getResources().getColor(R.color.green_1));
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(false);
        //是否可以拖动
        lineChart.setDragEnabled(true);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        // 设置 是否可以缩放
        lineChart.setScaleEnabled(false);
        // 比例缩放
        lineChart.setPinchZoom(true);
        //设置 是否可以展示弹窗
        lineChart.setDrawMarkers(true);
        //设置XY轴动画效果
        lineChart.animateY(1000);
        lineChart.animateX(2500);
        //是否 自适应最大最小值
        lineChart.setAutoScaleMinMaxEnabled(false);

//        lineChart.getLegend().setEnabled(false);// 不显示图例


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
        rightYaxis.setEnabled(true);
        leftYAxis.setEnabled(false);
        xAxis.setEnabled(true);

        //设置X、Y轴
        rightYaxis.setTextColor(getResources().getColor(R.color.white));//右侧Y轴文字颜色
        rightYaxis.setAxisLineColor(getResources().getColor(R.color.transparent));//X轴线的颜色
        xAxis.setTextColor(getResources().getColor(R.color.white));//X轴文字颜色
        xAxis.setAxisLineColor(getResources().getColor(R.color.transparent));//X轴线的颜色
        xAxis.setLabelCount(6, false);//设置数量
//        xAxis.setGranularity(20f);//设置x轴间距


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
        lineDataSet.setDrawIcons(true);
        //是否显示值
        lineDataSet.setDrawValues(false);
        //是否可以点击显示高亮（辅助线）
        lineDataSet.setHighlightEnabled(true);
        //设置辅助线颜色
//        lineDataSet.setHighLightColor(getResources().getColor(R.color.green_1));
        lineDataSet.setHighLightColor(getResources().getColor(R.color.transparent));

        // 点击圆点显示高亮
//        lineDataSet.setDrawHighlightIndicators(false);

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
    public void showLineChart(List<FenHong_ChartModel.OfcPriceListBean> dataList, String name, int color, LineChart lineChart, String min, String max) {
        //反转数据
        Collections.reverse(dataList);
        List<Entry> entries = new ArrayList<>();
//        List<Float> floatList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            FenHong_ChartModel.OfcPriceListBean data = dataList.get(i);
            /**
             * 在此可查看 Entry构造方法，可发现 可传入数值 Entry(float x, float y)
             * 也可传入Drawable， Entry(float x, float y, Drawable icon) 可在XY轴交点 设置Drawable图像展示
             */
            Entry entry = new Entry(i, Float.valueOf(data.getPrice()));
//            Entry entry = new Entry(i, Float.valueOf(data.getPrice()),getResources().getDrawable(R.mipmap.ic_xuanze));

            entries.add(entry);

            //取最后一条数据展示
            if (i == dataList.size() - 1) {
                tv_name.setVisibility(View.VISIBLE);
                tv_value.setVisibility(View.VISIBLE);
                tv_date.setVisibility(View.VISIBLE);
                tv_value.setText(dataList.get(i).getPrice());
                tv_date.setText(formatDate1(dataList.get(i).getCreated_at()));
            }

//            floatList.add(Float.valueOf(data.getPrice()));
        }
        //显示x轴的值
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                String tradeDate = dataList.get((int) value % dataList.size()).getCreated_at();
                return formatDate(tradeDate);
            }
        });

        //显示MarkerView
//        setMarkerView();

        //最大值
//        leftYAxis.setAxisMinimum(Float.valueOf(min)/2);
//        leftYAxis.setAxisMaximum(Float.valueOf(max)*2);
//        rightYaxis.setAxisMinimum(Float.valueOf(min)/2);
//        rightYaxis.setAxisMaximum(Float.valueOf(max)*2);
//        leftYAxis.setAxisMinimum(Float.valueOf(min));
//        leftYAxis.setAxisMaximum(Float.valueOf(max));
        rightYaxis.setAxisMinimum(Float.valueOf(min));
        rightYaxis.setAxisMaximum(Float.valueOf(max));

//        MyLogger.i(">>>>>>>最大>"+Collections.max(floatList));
//        MyLogger.i(">>>>>>>最小>"+Collections.min(floatList));

        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.CUBIC_BEZIER);
        LineData lineData = new LineData(lineDataSet);


        Matrix matrix = new Matrix();
        matrix.postScale(1.0F, 1.0F);
        lineChart.getViewPortHandler().refresh(matrix, lineChart, false);

        lineChart.setData(lineData);
        lineChart.moveViewToX((dataList.size() - 1));//移动到最后一个值

        //点击某个值的监听
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                tv_name.setVisibility(View.VISIBLE);
                tv_value.setVisibility(View.VISIBLE);
                tv_date.setVisibility(View.VISIBLE);
                tv_value.setText(dataList.get((int) e.getX()).getPrice());
                tv_date.setText(formatDate1(dataList.get((int) e.getX()).getCreated_at()));

                for (int i = 0; i < entries.size(); i++) {
                    if (i == (int) e.getX()) {
                        e.setIcon(getResources().getDrawable(R.mipmap.ic_chartdian));
                    } else {
                        entries.get(i).setIcon(null);
                    }
                }
                lineChart.getData().notifyDataChanged();
                lineChart.notifyDataSetChanged();
                lineChart.invalidate();


//                MyLogger.i(">>>>>>"+entries.get((int) e.getX()).getIcon().toString());
            }

            @Override
            public void onNothingSelected() {//没有选择的时候
                tv_name.setVisibility(View.GONE);
                tv_value.setVisibility(View.GONE);
                tv_date.setVisibility(View.GONE);
                tv_value.setText("");
                tv_date.setText("");

                for (int i = 0; i < entries.size(); i++) {
                    entries.get(i).setIcon(null);
                }
                lineChart.getData().notifyDataChanged();
                lineChart.notifyDataSetChanged();
                lineChart.invalidate();

//                lineChart.notifyDataSetChanged();
//                lineChart.invalidate();//刷新
            }
        });

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

    /**
     * 转换X轴时间显示
     *
     * @param str
     * @return
     */
    private String formatDate(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//接收格式
        SimpleDateFormat sf2 = new SimpleDateFormat("MM-dd");//转换格式
        String formatStr = "";
        try {
            formatStr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatStr;
    }

    /**
     * 转换X轴时间显示
     *
     * @param str
     * @return
     */
    private String formatDate1(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//接收格式
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");//转换格式
        String formatStr = "";
        try {
            formatStr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatStr;
    }


    /**
     * 设置 可以显示X Y 轴自定义值的 MarkerView
     */
    public void setMarkerView() {
        LineChartMarkView mv = new LineChartMarkView(getActivity(), xAxis.getValueFormatter());
        mv.setChartView(lineChart);
        lineChart.setMarker(mv);
        lineChart.invalidate();
    }
}
