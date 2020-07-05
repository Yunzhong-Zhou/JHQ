package com.ofc.ofc.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.tifezh.kchartlib.chart.BaseKChartView;
import com.github.tifezh.kchartlib.chart.KChartView;
import com.github.tifezh.kchartlib.chart.formatter.DateFormatter;
import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.activity.MainActivity;
import com.ofc.ofc.base.BaseFragment;
import com.ofc.ofc.model.SchoolModel;
import com.ofc.ofc.model.WebSocketModel;
import com.ofc.ofc.model.WebSocket_ListModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.okhttp.websocket.IReceiveMessage;
import com.ofc.ofc.okhttp.websocket.WebSocketManager;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.ofc.ofc.view.chart.DataHelper;
import com.ofc.ofc.view.chart.KChartAdapter;
import com.ofc.ofc.view.chart.KLineEntity;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 合约
 */

public class Fragment1 extends BaseFragment {
    int type = 1;
    //页面数据
    private RecyclerView recyclerView;
    List<SchoolModel.ArticleCategory1Bean.ArticleListBean> list1 = new ArrayList<>();
    CommonAdapter<SchoolModel.ArticleCategory1Bean.ArticleListBean> mAdapter1;

    List<SchoolModel.ArticleCategory2Bean.ArticleListBeanX> list2 = new ArrayList<>();
    CommonAdapter<SchoolModel.ArticleCategory2Bean.ArticleListBeanX> mAdapter2;

    List<SchoolModel.ArticleCategory2Bean.ArticleListBeanX> list3 = new ArrayList<>();
    CommonAdapter<SchoolModel.ArticleCategory2Bean.ArticleListBeanX> mAdapter3;

    TextView tv_qici, tv_zoushi, tv_daojishi, tv_zuokong, tv_zuoduo,
            tv_shouxufei_left, tv_shouxufei_right, tv_kanzhang, tv_kandie;
    ImageView iv_jian_left, iv_jia_left, iv_jian_right, iv_jia_right;
    EditText et_keyong_left, et_keyong_right;


    //悬浮部分
    LinearLayout linearLayout1, linearLayout2, linearLayout3;
    TextView textView1, textView2, textView3;
    View view1, view2, view3;

    //走势图
    RelativeLayout rl_1min, rl_5min, rl_30min, rl_1h, rl_1day, rl_1mon;
    TextView tv_1min, tv_5min, tv_30min, tv_1h, tv_1day, tv_1mon;
    boolean isShowOver = false, isNew = true;
    //wss://api.hadax.com/ws
    //wss://api.huobi.pro/ws
    //wss://api-aws.huobi.pro/ws
    String url = "wss://api.hadax.com/ws",
            fenshi = "1min", id = "btcusdt",
            sub = "market." + id + ".kline." + fenshi;

    long tempTime = 0, from = 0, to = 0, num = 720, time = 60 * num;
    KChartView mKChartView;
    private KChartAdapter mAdapter;
    List<KLineEntity> datas = new ArrayList<>();
    List<KLineEntity> newlist = new ArrayList<>();

    KLineEntity kLineEntity;
    Gson mGson = new Gson();

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
    public void onDestroy() {
        super.onDestroy();
        //关闭连接
        WebSocketManager.getInstance().close();
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

        //普通数据
        tv_qici = findViewByID_My(R.id.tv_qici);
        tv_zoushi = findViewByID_My(R.id.tv_zoushi);
        tv_daojishi = findViewByID_My(R.id.tv_daojishi);
        tv_zuokong = findViewByID_My(R.id.tv_zuokong);
        tv_zuoduo = findViewByID_My(R.id.tv_zuoduo);
        tv_shouxufei_left = findViewByID_My(R.id.tv_shouxufei_left);
        tv_shouxufei_right = findViewByID_My(R.id.tv_shouxufei_right);
        tv_kanzhang = findViewByID_My(R.id.tv_kanzhang);
        tv_kanzhang.setOnClickListener(this);
        tv_kandie = findViewByID_My(R.id.tv_kandie);
        tv_kandie.setOnClickListener(this);
        iv_jian_left = findViewByID_My(R.id.iv_jian_left);
        iv_jian_left.setOnClickListener(this);
        iv_jia_left = findViewByID_My(R.id.iv_jia_left);
        iv_jia_left.setOnClickListener(this);
        iv_jian_right = findViewByID_My(R.id.iv_jian_right);
        iv_jian_right.setOnClickListener(this);
        iv_jia_right = findViewByID_My(R.id.iv_jia_right);
        iv_jia_right.setOnClickListener(this);
        et_keyong_left = findViewByID_My(R.id.et_keyong_left);
        et_keyong_right = findViewByID_My(R.id.et_keyong_right);

        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);

        //k线图
        mKChartView = findViewByID_My(R.id.mKChartView);
        mAdapter = new KChartAdapter();
        mKChartView.setAdapter(mAdapter);
        mKChartView.setDateTimeFormatter(new DateFormatter());
        //设置表格行数
        mKChartView.setGridRows(4);
        //设置表格列数
        mKChartView.setGridColumns(4);
        mKChartView.setOnSelectedChangedListener(new BaseKChartView.OnSelectedChangedListener() {
            @Override
            public void onSelectedChanged(BaseKChartView view, Object point, int index) {
//                PredictionDetailModel.KlineListBean data = (PredictionDetailModel.KlineListBean) point;
//               MyLogger("index:" + index + " closePrice:" + data.getClose());
                MyLogger.i(">>>>>>onSelectedChanged>");
            }
        });
        mKChartView.showLoading();//这里有调用onLoadMoreBegin，会加载一次数据
        mKChartView.setRefreshListener(new KChartView.KChartRefreshListener() {
            @Override
            public void onLoadMoreBegin(KChartView chart) {
                if (isShowOver) {
                    MyLogger.i(">>>加载更多");
                    //获取历史数据
                    isShowOver = false;
                    isNew = false;
                    to = from;
                    from = (Long) (to - time);
                    showHistory(from, to);
                }
            }
        });
        //分时
        tv_1min = findViewByID_My(R.id.tv_1min);
        rl_1min = findViewByID_My(R.id.rl_1min);
        rl_1min.setOnClickListener(this);

        tv_5min = findViewByID_My(R.id.tv_5min);
        rl_5min = findViewByID_My(R.id.rl_5min);
        rl_5min.setOnClickListener(this);

        tv_30min = findViewByID_My(R.id.tv_30min);
        rl_30min = findViewByID_My(R.id.rl_30min);
        rl_30min.setOnClickListener(this);

        tv_1h = findViewByID_My(R.id.tv_1h);
        rl_1h = findViewByID_My(R.id.rl_1h);
        rl_1h.setOnClickListener(this);

        tv_1day = findViewByID_My(R.id.tv_1day);
        rl_1day = findViewByID_My(R.id.rl_1day);
        rl_1day.setOnClickListener(this);

        tv_1mon = findViewByID_My(R.id.tv_1mon);
        rl_1mon = findViewByID_My(R.id.rl_1mon);
        rl_1mon.setOnClickListener(this);
    }

    @Override
    protected void initData() {
//        requestServer();
        isNew = true;
        //开始连接
        new Thread(new Runnable() {
            @Override
            public void run() {
                WebSocketManager.getInstance().init(url, new IReceiveMessage() {
                    @Override
                    public void onConnectSuccess() {
                        MyLogger.i(">>>>>>连接成功");
                        changeKChart();
                    }

                    @Override
                    public void onConnectFailed() {
                        isShowOver = false;
                        MyLogger.i(">>>>>>连接失败");
                    }

                    @Override
                    public void onClose() {
                        isShowOver = false;
                        MyLogger.i(">>>>>>关闭成功");
                    }

                    @Override
                    public void onMessage(String text) {
//                        MyLogger.i("接收消息", text);
                        //得到心跳 {"ping":1592998031971}，发送心跳{"pong":1592998031971}
                        JSONObject jObj;
                        String ping = "";
                        try {
                            //解析数据
                            if (text.indexOf("ping") != -1) {
                                //TODO 判断有无ping数据
                                jObj = new JSONObject(text);
                                ping = jObj.getString("ping");

                                JSONObject jObj_pong = new JSONObject();
                                //发送心跳
                                jObj_pong.put("pong", ping);
                                WebSocketManager.getInstance().sendMessage(jObj_pong.toString());
                            } else if (text.indexOf("data") != -1) {
                                //TODO 判断有无data数据
//                                MyLogger.i("接收消息-历史记录", text);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //解析数据
                                        WebSocket_ListModel model = mGson.fromJson(text, WebSocket_ListModel.class);
                                        newlist.clear();
                                        for (WebSocket_ListModel.DataBean bean : model.getData()) {
                                            if (bean != null) {
                                                kLineEntity = new KLineEntity(
                                                        bean.getId() + "",
                                                        (float) bean.getOpen(),
                                                        (float) bean.getHigh(),
                                                        (float) bean.getLow(),
                                                        (float) bean.getClose(),
                                                        (float) bean.getVol(), 0, 0, 0, 0, 0, 0,
                                                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                        "-1"
                                                );
                                                newlist.add(kLineEntity);
                                            }
                                        }
                                        datas.addAll(0, newlist);
                                        if (isNew) {//是新数据
//                                            MyLogger.i(">>>>>>历史新数据");
                                            tempTime = model.getData().get(newlist.size() - 1).getId();
//                                            mKChartView.setAdapter(mAdapter);
                                            mAdapter.changeData(datas);//更新数据
                                            isNew = false;
                                        } else {//不是新数据
//                                            MyLogger.i(">>>>>>历史更多数据");
                                            mAdapter.addFooterData(newlist);//添加尾部数据
                                        }
                                        mAdapter.notifyDataSetChanged();
                                        DataHelper.calculate(datas);//计算MA BOLL RSI KDJ MACD
                                        mKChartView.refreshComplete();//加载完成
                                        isShowOver = true;
//                                        MyLogger.i(">>>>>>"+mKChartView.getWidth());
                                    }
                                });

                            } else {
//                                MyLogger.i("接收消息-订阅数据", text);
                                if (isNew ==false){//有了历史数据后展示最新数据
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //解析数据
                                            WebSocketModel model = mGson.fromJson(text, WebSocketModel.class);
                                            if (model != null && model.getTick() != null) {
//                                            MyLogger.i(">>>>>" + CommonUtil.timedate(model.getTick().getId() + ""));
                                                kLineEntity = new KLineEntity(
                                                        model.getTick().getId() + "",
                                                        (float) model.getTick().getOpen(),
                                                        (float) model.getTick().getHigh(),
                                                        (float) model.getTick().getLow(),
                                                        (float) model.getTick().getClose(),
                                                        (float) model.getTick().getVol(), 0, 0, 0, 0, 0, 0,
                                                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                        "-1"
                                                );
                                                newlist.clear();
                                                newlist.add(kLineEntity);
                                                if (tempTime != model.getTick().getId()) {
                                                    tempTime = model.getTick().getId();
                                                    datas.add(kLineEntity);
                                                    mAdapter.addHeaderData(newlist);//添加头部数据
                                                } else {
                                                    if (datas.size() > 0) {
                                                        datas.set(datas.size() - 1, kLineEntity);
                                                        mAdapter.changeItem(datas.size() - 1, kLineEntity);//改变某个值
                                                    }
                                                }
//                                            MyLogger.i(">>>>>"+datas.size());
//                                            mAdapter.notifyDataSetChanged();

                                                DataHelper.calculate(datas);//计算MA BOLL RSI KDJ MACD
                                                mKChartView.refreshComplete();//加载完成
                                            }

                                        }
                                    });
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.HeYue + string, new OkHttpClientManager.ResultCallback<SchoolModel>() {
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
            public void onResponse(final SchoolModel response) {
                showContentPage();

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
            case R.id.rl_1min:
                //1分钟
                tv_1min.setBackgroundResource(R.drawable.yuanjiao_10_heise);
                tv_5min.setBackgroundResource(R.color.transparent);
                tv_30min.setBackgroundResource(R.color.transparent);
                tv_1h.setBackgroundResource(R.color.transparent);
                tv_1day.setBackgroundResource(R.color.transparent);
                tv_1mon.setBackgroundResource(R.color.transparent);

                cancelDingYue();//先取消订阅-再订阅最新
                fenshi = "1min";
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * num;//60*12条
                changeKChart();
                break;
            case R.id.rl_5min:
                //5分钟
                tv_1min.setBackgroundResource(R.color.transparent);
                tv_5min.setBackgroundResource(R.drawable.yuanjiao_10_heise);
                tv_30min.setBackgroundResource(R.color.transparent);
                tv_1h.setBackgroundResource(R.color.transparent);
                tv_1day.setBackgroundResource(R.color.transparent);
                tv_1mon.setBackgroundResource(R.color.transparent);

                cancelDingYue();//先取消订阅-再订阅最新
                fenshi = "5min";
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * 5 * num;//60*12条
                changeKChart();
                break;
            case R.id.rl_30min:
                //30分钟
                tv_1min.setBackgroundResource(R.color.transparent);
                tv_5min.setBackgroundResource(R.color.transparent);
                tv_30min.setBackgroundResource(R.drawable.yuanjiao_10_heise);
                tv_1h.setBackgroundResource(R.color.transparent);
                tv_1day.setBackgroundResource(R.color.transparent);
                tv_1mon.setBackgroundResource(R.color.transparent);

                cancelDingYue();//先取消订阅-再订阅最新
                fenshi = "30min";
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * 30 * num;//60*12条
                changeKChart();
                break;
            case R.id.rl_1h:
                //1小时
                tv_1min.setBackgroundResource(R.color.transparent);
                tv_5min.setBackgroundResource(R.color.transparent);
                tv_30min.setBackgroundResource(R.color.transparent);
                tv_1h.setBackgroundResource(R.drawable.yuanjiao_10_heise);
                tv_1day.setBackgroundResource(R.color.transparent);
                tv_1mon.setBackgroundResource(R.color.transparent);

                cancelDingYue();//先取消订阅-再订阅最新
                fenshi = "60min";
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * 60 * num;//60*12条
                changeKChart();
                break;
            case R.id.rl_1day:
                //1天
                tv_1min.setBackgroundResource(R.color.transparent);
                tv_5min.setBackgroundResource(R.color.transparent);
                tv_30min.setBackgroundResource(R.color.transparent);
                tv_1h.setBackgroundResource(R.color.transparent);
                tv_1day.setBackgroundResource(R.drawable.yuanjiao_10_heise);
                tv_1mon.setBackgroundResource(R.color.transparent);

                cancelDingYue();//先取消订阅-再订阅最新
                fenshi = "1day";
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * 60 * 24 * num;//60*12条
                changeKChart();
                break;
            case R.id.rl_1mon:
                //1月
                tv_1min.setBackgroundResource(R.color.transparent);
                tv_5min.setBackgroundResource(R.color.transparent);
                tv_30min.setBackgroundResource(R.color.transparent);
                tv_1h.setBackgroundResource(R.color.transparent);
                tv_1day.setBackgroundResource(R.color.transparent);
                tv_1mon.setBackgroundResource(R.drawable.yuanjiao_10_heise);

                cancelDingYue();//先取消订阅-再订阅最新
                fenshi = "1week";
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * 60 * 24 * 7 * 60;//60条
                changeKChart();
                break;
        }
    }


    /**
     * 选择分时，更改k线图
     */
    private void changeKChart() {
        //关闭连接
//                WebSocketManager.getInstance().close();
        datas.clear();
        isShowOver = false;
        isNew = true;
        mKChartView.showLoading();//这里有调用onLoadMoreBegin，会加载一次数据
        from = (Long) (System.currentTimeMillis() / 1000 - time);
        to = (Long) System.currentTimeMillis() / 1000;

        showHistory(from, to);
    }

    /**
     * 取消订阅
     */
    private void cancelDingYue() {
        try {
            JSONObject jObj_dingyue = new JSONObject();
            jObj_dingyue.put("unsub", sub);
            jObj_dingyue.put("id", id);
            WebSocketManager.getInstance().sendMessage(jObj_dingyue.toString());
            MyLogger.i(">>>>>>>取消订阅：" + jObj_dingyue.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /**
     * 查看历史数据
     */
    private void showHistory(Long from, Long to) {
        isShowOver = false;
        mKChartView.showLoading();//这里有调用onLoadMoreBegin，会加载一次数据
        try {
            JSONObject jObj_lishi = new JSONObject();
            jObj_lishi.put("req", sub);
            jObj_lishi.put("id", id);
            jObj_lishi.put("from", from);
            jObj_lishi.put("to", to);
            WebSocketManager.getInstance().sendMessage(jObj_lishi.toString());
            MyLogger.i(">>>>>>>历史数据提交：" + jObj_lishi.toString());

            //订阅前需要先取消订阅
            JSONObject jObj_dingyue = new JSONObject();
            jObj_dingyue.put("sub", sub);
            jObj_dingyue.put("id", id);
            WebSocketManager.getInstance().sendMessage(jObj_dingyue.toString());
            MyLogger.i(">>>>>>>订阅提交：" + jObj_dingyue.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void changeUI() {
        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.white));
            textView2.setTextColor(getResources().getColor(R.color.white2));
            textView3.setTextColor(getResources().getColor(R.color.white2));
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.INVISIBLE);
            if (list1.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter1);
            } else {
                showEmptyPage();
            }

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.white2));
            textView2.setTextColor(getResources().getColor(R.color.white));
            textView3.setTextColor(getResources().getColor(R.color.white2));
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            view3.setVisibility(View.INVISIBLE);
            if (list2.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter2);
            } else {
                showEmptyPage();
            }
        } else if (type == 3) {
            textView1.setTextColor(getResources().getColor(R.color.white2));
            textView2.setTextColor(getResources().getColor(R.color.white2));
            textView3.setTextColor(getResources().getColor(R.color.white));
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.VISIBLE);
            if (list3.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter3);
            } else {
                showEmptyPage();
            }
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
