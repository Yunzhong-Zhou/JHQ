package com.ofc.ofc.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.tifezh.kchartlib.chart.BaseKChartView;
import com.github.tifezh.kchartlib.chart.KChartView;
import com.github.tifezh.kchartlib.chart.formatter.DateFormatter;
import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.Fragment1DetailModel;
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
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zyz on 2020/7/5.
 */
public class Fragment1DeatilActivity extends BaseActivity {
    String history_id = "";
    Fragment1DetailModel model;
    int type = 2, page = 1, item = -1;
    //悬浮部分
    LinearLayout linearLayout1, linearLayout2, linearLayout3;
    TextView textView1, textView2, textView3;
    View view1, view2, view3;

    //页面数据
    private RecyclerView recyclerView;
    List<Fragment1DetailModel.MyChangeGameParticipationListBean> list1 = new ArrayList<>();
    CommonAdapter<Fragment1DetailModel.MyChangeGameParticipationListBean> mAdapter1;

    List<Fragment1DetailModel.MyChangeGameParticipationListBean> list2 = new ArrayList<>();
    CommonAdapter<Fragment1DetailModel.MyChangeGameParticipationListBean> mAdapter2;

    List<Fragment1DetailModel.HistoryChangeGameListBean> list3 = new ArrayList<>();
    CommonAdapter<Fragment1DetailModel.HistoryChangeGameListBean> mAdapter3;

    TextView tv_qici, tv_zoushi, tv_zhuangtai, tv_zuokong, tv_zuoduo, tv_zuokong_baifenbi, tv_zuoduo_baifenbi,
            tv_money_left, tv_time_left, tv_money_right, tv_time_right, tv_jieguo;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment1detail);

        setSwipeBackEnable(false); //主 activity 可以调用该方法，禁止滑动删除
    }

    /*@Override
    public void onDestroy() {
        super.onDestroy();
        //关闭连接
        WebSocketManager.getInstance().close();
    }*/

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            //关闭连接
            WebSocketManager.getInstance().close();
            finish();
            /*MainActivity.isOver = false;
            Bundle bundle = new Bundle();
            bundle.putInt("item", 0);
            CommonUtil.gotoActivityWithFinishOtherAllAndData(Fragment1DeatilActivity.this, MainActivity.class, bundle, true);*/
            return false;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

    @Override
    protected void initView() {
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        findViewByID_My(R.id.guanbi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭连接
                WebSocketManager.getInstance().close();
                finish();
                /*MainActivity.isOver = false;
                Bundle bundle = new Bundle();
                bundle.putInt("item", 0);
                CommonUtil.gotoActivityWithFinishOtherAllAndData(Fragment1DeatilActivity.this, MainActivity.class, bundle, true);*/
            }
        });
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                String string = "?page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&id=" + history_id
                        + "&token=" + localUserInfo.getToken();
                Request(string);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                String string = "?page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&id=" + history_id
                        + "&token=" + localUserInfo.getToken();
                RequestMore(string);
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
        tv_zhuangtai = findViewByID_My(R.id.tv_zhuangtai);
        tv_zuokong = findViewByID_My(R.id.tv_zuokong);
        tv_zuoduo = findViewByID_My(R.id.tv_zuoduo);
        tv_zuokong_baifenbi = findViewByID_My(R.id.tv_zuokong_baifenbi);
        tv_zuoduo_baifenbi = findViewByID_My(R.id.tv_zuoduo_baifenbi);
        tv_money_left = findViewByID_My(R.id.tv_money_left);
        tv_time_left = findViewByID_My(R.id.tv_time_left);
        tv_money_right = findViewByID_My(R.id.tv_money_right);
        tv_time_right = findViewByID_My(R.id.tv_time_right);
        tv_jieguo = findViewByID_My(R.id.tv_jieguo);

        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
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

        changeUI();
    }

    @Override
    protected void initData() {
        history_id = getIntent().getStringExtra("history_id");
        requestServer();
        requestWebSocket();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        page = 1;
        String string = "?page=" + page//当前页号
                + "&count=" + "10"//页面行数
                + "&id=" + history_id
                + "&token=" + localUserInfo.getToken();
        Request(string);
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.HeYue_Detail + string, new OkHttpClientManager.ResultCallback<Fragment1DetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(final Fragment1DetailModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>合约详情" + response);
                model = response;
                tv_qici.setText(getText(R.string.fragment1_h4) + model.getChange_game().getPeriod() + getText(R.string.fragment1_h5));//期次
                tv_zoushi.setText(model.getChange_game().getInit_at() + "-" + model.getChange_game().getWin_at() + getText(R.string.fragment1_h6));//价格走势

                if (model.getChange_game().getStatus() != 1) {
                    tv_zuokong.setText(model.getChange_game().get_$2_amount_money());//做空
                    tv_zuoduo.setText(model.getChange_game().get_$1_amount_money());//做多
                } else {
                    tv_zuokong.setText("--");//做空
                    tv_zuoduo.setText("--");//做多
                }
                tv_zhuangtai.setText(model.getChange_game().getStatus_title());
                //做空百分比
                tv_zuokong_baifenbi.setText(model.getChange_game().getFall_ratio() + "%");
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                params.weight = Float.valueOf(model.getChange_game().getFall_ratio());//在此处设置weight
                tv_zuokong_baifenbi.setLayoutParams(params);

                //做多百分比
                tv_zuoduo_baifenbi.setText(model.getChange_game().getRise_ratio() + "%");
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                params1.weight = Float.valueOf(model.getChange_game().getRise_ratio());//在此处设置weight
                tv_zuoduo_baifenbi.setLayoutParams(params1);

                tv_money_left.setText(model.getChange_game().getInit_num());
                tv_time_left.setText(model.getChange_game().getInit_at());
                tv_money_right.setText(model.getChange_game().getWin_num());
                tv_time_right.setText(model.getChange_game().getWin_at());
                if (model.getChange_game().getWin_rise_fall() == 1) {////看涨、做多
                    tv_jieguo.setText(getString(R.string.fragment1_h9));
                    tv_jieguo.setTextColor(getResources().getColor(R.color.green_1));
                } else {
                    tv_jieguo.setText(getString(R.string.fragment1_h8));
                    tv_jieguo.setTextColor(getResources().getColor(R.color.red_1));
                }


                //持有仓位
                /*list1 = model.getMy_change_game_participation_list();
                mAdapter1 = new CommonAdapter<Fragment1DetailModel.MyChangeGameParticipationListBean>
                        (Fragment1DeatilActivity.this, R.layout.item_fragment1_1, list1) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1DetailModel.MyChangeGameParticipationListBean bean, int position) {
                        holder.setText(R.id.tv1, bean.getMoney() + "");
                        holder.setText(R.id.tv3, bean.getService_charge_money() + "");
                        TextView tv2 = holder.getView(R.id.tv2);
                        if (bean.getType() == 1) {//看涨、做多
                            tv2.setText(getString(R.string.fragment1_h9));
                            tv2.setTextColor(getResources().getColor(R.color.green_1));
                        } else {
                            tv2.setText(getString(R.string.fragment1_h8));
                            tv2.setTextColor(getResources().getColor(R.color.red_1));
                        }
                    }
                };*/
                //我的交易
                list2 = model.getMy_change_game_participation_list();
                mAdapter2 = new CommonAdapter<Fragment1DetailModel.MyChangeGameParticipationListBean>
                        (Fragment1DeatilActivity.this, R.layout.item_fragment1_2, list2) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1DetailModel.MyChangeGameParticipationListBean bean, int position) {
                        LinearLayout ll_bg = holder.getView(R.id.ll_bg);
                        if (item == position) {
                            ll_bg.setBackgroundResource(R.color.bg_1);
                        } else {
                            ll_bg.setBackgroundResource(R.color.bg_0);
                        }

                        holder.setText(R.id.tv1, getText(R.string.fragment1_h4) + model.getChange_game().getPeriod() + getText(R.string.fragment1_h5));

                        TextView tv4 = holder.getView(R.id.tv4);
                        TextView tv5 = holder.getView(R.id.tv5);

                        if (model.getChange_game().getStatus() == 3) {
                            holder.setText(R.id.tv2, getText(R.string.fragment1_h31) + "  " + model.getChange_game().getInit_at() + "(" + model.getChange_game().getInit_num() + ")"
                                    + "—" + model.getChange_game().getWin_at() + "(" + model.getChange_game().getWin_num() + ")");
                            tv5.setVisibility(View.VISIBLE);
                            if (Double.valueOf(bean.getBureau_win_money()) > 0) {//盈利
                                tv4.setTextColor(getResources().getColor(R.color.green_1));
                                tv4.setText(getString(R.string.fragment1_h16) + "+" + bean.getBureau_win_money());
                                tv5.setTextColor(getResources().getColor(R.color.green_1));
                            } else {
                                tv4.setTextColor(getResources().getColor(R.color.red_1));
                                tv4.setText(getString(R.string.fragment1_h35) + "-" + bean.getMoney());
                                tv5.setTextColor(getResources().getColor(R.color.red_1));
                            }
                        } else {
                            holder.setText(R.id.tv2, getText(R.string.fragment1_h31) + "  " + model.getChange_game().getInit_at() + "(~~)"
                                    + "—" + model.getChange_game().getWin_at() + "(~~)");

                            tv4.setTextColor(getResources().getColor(R.color.white2));
                            tv4.setText(model.getChange_game().getStatus_title());//状态
                            tv5.setVisibility(View.GONE);

                        }

                        holder.setText(R.id.tv3, model.getChange_game().get_$1_amount_money() + "");//看涨、做多
                        holder.setText(R.id.tv6, model.getChange_game().get_$2_amount_money() + "");//看跌、做空

                        holder.setText(R.id.tv7, bean.getMoney() + "");//仓位
                        holder.setText(R.id.tv9, bean.getService_charge_money() + "");//手续费
                        holder.setText(R.id.tv10, model.getChange_game().getStatus_title());//状态
                        TextView tv8 = holder.getView(R.id.tv8);
                        if (bean.getType() == 1) {//看涨、做多
                            tv8.setText(getString(R.string.fragment1_h9));
                            tv8.setTextColor(getResources().getColor(R.color.green_1));
                        } else {
                            tv8.setText(getString(R.string.fragment1_h8));
                            tv8.setTextColor(getResources().getColor(R.color.red_1));
                        }
                    }
                };
                mAdapter2.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        if (item != i) {
                            item = i;
                        } else {
                            item = -1;
                        }
                        mAdapter2.notifyDataSetChanged();
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });
                //历史
                list3 = model.getHistory_change_game_list();
                mAdapter3 = new CommonAdapter<Fragment1DetailModel.HistoryChangeGameListBean>
                        (Fragment1DeatilActivity.this, R.layout.item_fragment1_3, list3) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1DetailModel.HistoryChangeGameListBean bean, int position) {
                        holder.setText(R.id.tv1, getText(R.string.fragment1_h4) + bean.getPeriod() + getText(R.string.fragment1_h5));

                        TextView tv4 = holder.getView(R.id.tv4);
                        tv4.setText(bean.getStatus_title());
                        if (bean.getStatus() != 1) {//进行中
                            holder.setText(R.id.tv2, getText(R.string.fragment1_h31) + "  " + bean.getInit_at() + "(" + bean.getInit_num() + ")"
                                    + "—" + bean.getWin_at() + "(" + bean.getWin_num() + ")");
                        } else {
                            holder.setText(R.id.tv2, getText(R.string.fragment1_h31) + "  " + bean.getInit_at() + "(~~)"
                                    + "—" + bean.getWin_at() + "(~~)");
                        }

                        holder.setText(R.id.tv3, bean.get_$1_amount_money() + "");//看涨、做多
                        holder.setText(R.id.tv6, bean.get_$2_amount_money() + "");//看跌、做空
                    }
                };
                mAdapter3.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        history_id = list3.get(i).getId();
                        requestServer();
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });

                changeUI();

            }
        });
    }

    private void RequestMore(String string) {
        OkHttpClientManager.getAsyn(this, URLs.HeYue_Detail + string, new OkHttpClientManager.ResultCallback<Fragment1DetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
                page--;
            }

            @Override
            public void onResponse(Fragment1DetailModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>合约更多" + response);
                List<Fragment1DetailModel.MyChangeGameParticipationListBean> list1_1 = response.getMy_change_game_participation_list();

                List<Fragment1DetailModel.MyChangeGameParticipationListBean> list2_1 = response.getMy_change_game_participation_list();

                List<Fragment1DetailModel.HistoryChangeGameListBean> list3_1 = response.getHistory_change_game_list();
                if (list1_1.size() == 0 && list2_1.size() == 0 && list3_1.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                    page--;
                } else {
                    if (list1_1.size() > 0) {
                        list1.addAll(list1_1);
                        mAdapter1.notifyDataSetChanged();
                    }
                    if (list2_1.size() > 0) {
                        list2.addAll(list2_1);
                        mAdapter2.notifyDataSetChanged();
                    }
                    if (list3_1.size() > 0) {
                        list3.addAll(list3_1);
                        mAdapter3.notifyDataSetChanged();
                    }
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

            case R.id.rl_1min:
                //1分钟
                cancelDingYue();//先取消订阅-再订阅最新
                fenshi = "1min";
                changeUI();
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * num;//60*12条
                changeKChart();
                break;
            case R.id.rl_5min:
                //5分钟
                cancelDingYue();//先取消订阅-再订阅最新
                fenshi = "5min";
                changeUI();
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * 5 * num;//60*12条
                changeKChart();
                break;
            case R.id.rl_30min:
                //30分钟
                cancelDingYue();//先取消订阅-再订阅最新
                fenshi = "30min";
                changeUI();
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * 30 * num;//60*12条
                changeKChart();
                break;
            case R.id.rl_1h:
                //1小时
                cancelDingYue();//先取消订阅-再订阅最新
                fenshi = "60min";
                changeUI();
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * 60 * num;//60*12条
                changeKChart();
                break;
            case R.id.rl_1day:
                //1天
                cancelDingYue();//先取消订阅-再订阅最新
                fenshi = "1day";
                changeUI();
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * 60 * 24 * num;//60*12条
                changeKChart();
                break;
            case R.id.rl_1mon:
                //1月
                cancelDingYue();//先取消订阅-再订阅最新
                fenshi = "1mon";
                changeUI();
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * 60 * 24 * 30 * 60;//60条
                changeKChart();
                break;
        }
    }


    /**
     * 连接websocket、解析、展示
     */
    private void requestWebSocket() {
        isNew = true;
//        MyLogger.i(">>>是否连接" + WebSocketManager.getInstance().isConnect());
        if (!WebSocketManager.getInstance().isConnect()) {//是否连接
            //没有连接时，开始连接
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
//                            hideProgress();
//                            finish();
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
                                    runOnUiThread(new Runnable() {
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
                                                            (float) bean.getVol(),
                                                            (float) bean.getAmount(), 0, 0, 0, 0, 0, 0,
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
//                                    MyLogger.i("接收消息-订阅数据", text);
                                    if (isNew == false) {//有了历史数据后展示最新数据
                                        runOnUiThread(new Runnable() {
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
                                                            (float) model.getTick().getVol(),
                                                            (float) model.getTick().getAmount(), 0, 0, 0, 0, 0, 0,
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
        } else {
            //有连接-加载历史
            cancelDingYue();//先取消订阅-再订阅最新
            changeKChart();
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
            textView1.setTextColor(getResources().getColor(R.color.white1));
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
            textView2.setTextColor(getResources().getColor(R.color.white1));
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
            textView3.setTextColor(getResources().getColor(R.color.white1));
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

        switch (fenshi) {
            case "1min":
                tv_1min.setBackgroundResource(R.drawable.yuanjiao_10_heise);
                tv_5min.setBackgroundResource(R.color.transparent);
                tv_30min.setBackgroundResource(R.color.transparent);
                tv_1h.setBackgroundResource(R.color.transparent);
                tv_1day.setBackgroundResource(R.color.transparent);
                tv_1mon.setBackgroundResource(R.color.transparent);
                break;
            case "5min":
                tv_1min.setBackgroundResource(R.color.transparent);
                tv_5min.setBackgroundResource(R.drawable.yuanjiao_10_heise);
                tv_30min.setBackgroundResource(R.color.transparent);
                tv_1h.setBackgroundResource(R.color.transparent);
                tv_1day.setBackgroundResource(R.color.transparent);
                tv_1mon.setBackgroundResource(R.color.transparent);
                break;
            case "30min":
                tv_1min.setBackgroundResource(R.color.transparent);
                tv_5min.setBackgroundResource(R.color.transparent);
                tv_30min.setBackgroundResource(R.drawable.yuanjiao_10_heise);
                tv_1h.setBackgroundResource(R.color.transparent);
                tv_1day.setBackgroundResource(R.color.transparent);
                tv_1mon.setBackgroundResource(R.color.transparent);
                break;
            case "60min":
                tv_1min.setBackgroundResource(R.color.transparent);
                tv_5min.setBackgroundResource(R.color.transparent);
                tv_30min.setBackgroundResource(R.color.transparent);
                tv_1h.setBackgroundResource(R.drawable.yuanjiao_10_heise);
                tv_1day.setBackgroundResource(R.color.transparent);
                tv_1mon.setBackgroundResource(R.color.transparent);
                break;
            case "1day":
                tv_1min.setBackgroundResource(R.color.transparent);
                tv_5min.setBackgroundResource(R.color.transparent);
                tv_30min.setBackgroundResource(R.color.transparent);
                tv_1h.setBackgroundResource(R.color.transparent);
                tv_1day.setBackgroundResource(R.drawable.yuanjiao_10_heise);
                tv_1mon.setBackgroundResource(R.color.transparent);
                break;
            case "1mon":
                tv_1min.setBackgroundResource(R.color.transparent);
                tv_5min.setBackgroundResource(R.color.transparent);
                tv_30min.setBackgroundResource(R.color.transparent);
                tv_1h.setBackgroundResource(R.color.transparent);
                tv_1day.setBackgroundResource(R.color.transparent);
                tv_1mon.setBackgroundResource(R.drawable.yuanjiao_10_heise);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
