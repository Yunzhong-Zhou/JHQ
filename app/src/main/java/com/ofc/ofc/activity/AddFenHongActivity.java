package com.ofc.ofc.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.tifezh.kchartlib.chart.BaseKChartView;
import com.github.tifezh.kchartlib.chart.KChartView;
import com.github.tifezh.kchartlib.chart.formatter.DateFormatter;
import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.FenHongModel;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zyz on 2020/7/5.
 */
public class AddFenHongActivity extends BaseActivity {
    FenHongModel model;
    TextView tv_usdt, tv_fenhongzhishu, tv_toal, tv_24h, tv_mairu, tv_jisuan, tv_faxingjia, tv_ofc_yue, tv_zengzhi,
            tv_heyue, tv_usdt_yue, tv_yifenhong;
    ImageView iv_toal, iv_24h, iv_jian, iv_jia, iv_zengzhi;
    EditText et_keyong;

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
        setContentView(R.layout.activity_addfenhong);

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
                String string = "?token=" + localUserInfo.getToken();
                Request(string);
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
                        double shouxufei = Double.valueOf(et_keyong.getText().toString().trim()) * Double.valueOf(model.getUsdt_price()) / Double.valueOf(model.getOfc_price());
                        tv_jisuan.setText("=" + String.format("%.2f", shouxufei) + "USDT");
                    }
                }
            }
        });
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
//        history_id = getIntent().getStringExtra("history_id");
        requestServer();
        requestWebSocket();
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
        OkHttpClientManager.getAsyn(this, URLs.FenHong + string, new OkHttpClientManager.ResultCallback<FenHongModel>() {
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
                tv_usdt.setText(model.getOfc_money());
                tv_fenhongzhishu.setText(getString(R.string.qianbao_h34) + "：" + model.getOfc_index() + "USDT");
                if (model.getToal_appreciation() >= 0) {
                    tv_toal.setTextColor(getResources().getColor(R.color.green_1));
                    tv_toal.setText("Toal +" + model.getToal_appreciation() + "%");
                } else {
                    tv_toal.setTextColor(getResources().getColor(R.color.red_1));
                    tv_toal.setText("Toal -" + model.getToal_appreciation() + "%");
                }
                if (model.getLast_appreciation() >= 0) {
                    tv_24h.setTextColor(getResources().getColor(R.color.green_1));
                    tv_24h.setText("24H +" + model.getLast_appreciation() + "%");

                    tv_zengzhi.setTextColor(getResources().getColor(R.color.green_1));
                    tv_zengzhi.setText(model.getLast_appreciation() + "%");
                } else {
                    tv_24h.setTextColor(getResources().getColor(R.color.red_1));
                    tv_24h.setText("24H -" + model.getLast_appreciation() + "%");

                    tv_zengzhi.setTextColor(getResources().getColor(R.color.red_1));
                    tv_zengzhi.setText(model.getLast_appreciation() + "%");
                }
                et_keyong.setHint(getString(R.string.fragment1_h10) + model.getCommon_usable_money());
                tv_ofc_yue.setText(model.getOfc_money());
                tv_usdt_yue.setText(model.getCommon_usable_money());

                tv_faxingjia.setText(getString(R.string.qianbao_h36) + model.getOfc_issue_price());


                tv_heyue.setText(getString(R.string.qianbao_h39) + model.getContract_money());
                tv_yifenhong.setText(model.getInterest_money());


                changeUI();
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
                double shouxufei1 = tempMoney_jian_left * Double.valueOf(model.getUsdt_price()) / Double.valueOf(model.getOfc_price());
                tv_jisuan.setText("=" + String.format("%.2f", shouxufei1) + "USDT");

                break;
            case R.id.iv_jia:
                //买涨-加
                double tempMoney_jia_left = 0;
                if (!et_keyong.getText().toString().trim().equals("")) {
                    tempMoney_jia_left = Double.valueOf(et_keyong.getText().toString().trim());
                }
                if (Double.valueOf(model.getCommon_usable_money()) > tempMoney_jia_left) {
                    if ((Double.valueOf(model.getCommon_usable_money()) - tempMoney_jia_left) >= 10) {
                        tempMoney_jia_left += 10;
                    } else {
                        tempMoney_jia_left = Double.valueOf(model.getCommon_usable_money());
                    }
                }
                et_keyong.setText((int) tempMoney_jia_left + "");

                //计算手续费 输入币数 * usdt_price / ofc_price;
                double shouxufei2 = tempMoney_jia_left * Double.valueOf(model.getUsdt_price()) / Double.valueOf(model.getOfc_price());
                tv_jisuan.setText("=" + String.format("%.2f", shouxufei2) + "USDT");

                break;
            case R.id.tv_mairu:
                //买入
                if (!et_keyong.getText().toString().trim().equals("")) {
                    if (Double.valueOf(et_keyong.getText().toString().trim()) >= 10) {
                        tv_mairu.setClickable(false);
                        showProgress(true, getString(R.string.app_loading1));
                        HashMap<String, String> params = new HashMap<>();
//                        params.put("hk", model.getHk());
//                        params.put("change_game_id", model.getChange_game().getId());
//                        params.put("type", "1");//类型（1.看涨 2.看跌）
                        params.put("money", et_keyong.getText().toString().trim());
                        params.put("token", localUserInfo.getToken());
                        requestAdd(params);

                    } else {
                        myToast(getString(R.string.fragment1_h26));
                    }
                } else {
                    myToast(getString(R.string.fragment1_h25));
                }
                break;
            case R.id.ll_ofc:
                //ofc
                CommonUtil.gotoActivity(AddFenHongActivity.this, OFCAccountDetailActivity.class, false);
                break;
            case R.id.ll_usdt:
                //usdt
                CommonUtil.gotoActivity(AddFenHongActivity.this, RechargeActivity.class, false);

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

    private void requestAdd(HashMap<String, String> params) {
        OkHttpClientManager.postAsyn(AddFenHongActivity.this, URLs.AddFenHong, params, new OkHttpClientManager.ResultCallback<String>() {
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
                    CommonUtil.gotoActivityWithData(AddFenHongActivity.this, FenHongListActivity.class, bundle, false);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }, true);
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
