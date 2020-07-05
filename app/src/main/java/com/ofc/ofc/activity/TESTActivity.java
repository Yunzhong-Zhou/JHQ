package com.ofc.ofc.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.github.tifezh.kchartlib.chart.BaseKChartView;
import com.github.tifezh.kchartlib.chart.KChartView;
import com.github.tifezh.kchartlib.chart.formatter.DateFormatter;
import com.google.gson.Gson;
import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.WebSocketModel;
import com.ofc.ofc.model.WebSocket_ListModel;
import com.ofc.ofc.utils.MyLogger;
import com.ofc.ofc.okhttp.websocket.IReceiveMessage;
import com.ofc.ofc.okhttp.websocket.WebSocketManager;
import com.ofc.ofc.view.chart.DataHelper;
import com.ofc.ofc.view.chart.KChartAdapter;
import com.ofc.ofc.view.chart.KLineEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyz on 2020/6/28.
 */
public class TESTActivity extends BaseActivity {
    boolean isOver = false;
    //wss://api.hadax.com/ws
    //wss://api.huobi.pro/ws
    //wss://api-aws.huobi.pro/ws
    String url = "wss://api.hadax.com/ws",
            longtime = "1min", id = "btcusdt",
            sub = "market." + id + ".kline." + longtime;
    long page = 1, time = 60 * 100, tempTime = 0;
    KChartView mKChartView;
    private KChartAdapter mAdapter;
    List<KLineEntity> datas = new ArrayList<>();

    Gson mGson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictiondetail_test);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //关闭连接
        WebSocketManager.getInstance().close();
    }

    @Override
    protected void initView() {
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
                if (isOver) {
                    page++;
                    MyLogger.i(">>>>>>>>>" + page);
                    try {
                        //发送订阅
                        JSONObject jObj_dingyue = new JSONObject();
                        jObj_dingyue.put("req", sub);
                        jObj_dingyue.put("id", id);
                        jObj_dingyue.put("from", (Long) (System.currentTimeMillis() / 1000 - (time * page)));
                        jObj_dingyue.put("to", (Long) System.currentTimeMillis() / 1000);
                        WebSocketManager.getInstance().sendMessage(jObj_dingyue.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    @Override
    protected void initData() {
        //开始连接
        new Thread(new Runnable() {
            @Override
            public void run() {
                WebSocketManager.getInstance().init(url, new IReceiveMessage() {
                    @Override
                    public void onConnectSuccess() {
                        MyLogger.i(">>>>>>连接成功");
                        try {
                            JSONObject jObj_lishi = new JSONObject();
                            jObj_lishi.put("req", sub);
                            jObj_lishi.put("id", id);
                            jObj_lishi.put("from", (Long) (System.currentTimeMillis() / 1000 - (time * page)));
                            jObj_lishi.put("to", (Long) System.currentTimeMillis() / 1000);
                            WebSocketManager.getInstance().sendMessage(jObj_lishi.toString());
                            MyLogger.i(">>>>>>>历史数据提交：" + jObj_lishi.toString());

                            //发送订阅
                            JSONObject jObj_dingyue = new JSONObject();
                            jObj_dingyue.put("sub", sub);
                            jObj_dingyue.put("id", id);
                            WebSocketManager.getInstance().sendMessage(jObj_dingyue.toString());
                            MyLogger.i(">>>>>>>订阅提交：" + jObj_dingyue.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onConnectFailed() {
                        isOver = false;
                        MyLogger.i(">>>>>>连接失败");
                    }

                    @Override
                    public void onClose() {
                        isOver = false;
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
                                MyLogger.i("接收消息-历史记录", text);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //解析数据
                                        WebSocket_ListModel model = mGson.fromJson(text, WebSocket_ListModel.class);
                                        for (WebSocket_ListModel.DataBean bean : model.getData()) {
                                            if (bean != null) {
                                                KLineEntity kLineEntity = new KLineEntity(
                                                        bean.getId() + "",
                                                        (float) bean.getOpen(),
                                                        (float) bean.getHigh(),
                                                        (float) bean.getLow(),
                                                        (float) bean.getClose(),
                                                        (float) bean.getVol(), 0, 0, 0, 0, 0, 0,
                                                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                        "-1"
                                                );
                                                datas.add(kLineEntity);
                                            }
                                        }
                                        mAdapter.addFooterData(datas);//添加尾部数据
                                        mAdapter.notifyDataSetChanged();
                                        DataHelper.calculate(datas);//计算MA BOLL RSI KDJ MACD
                                        mKChartView.refreshComplete();//加载完成
                                        isOver = true;

                                        /*if (isOver == false) {
                                            tempTime = model.getData().get(datas.size() - 1).getId();
                                            mAdapter.addHeaderData1(datas);//添加头部数据
                                            DataHelper.calculate(datas);//计算MA BOLL RSI KDJ MACD
                                            mKChartView.refreshComplete();//加载完成
                                            isOver = true;
                                        } else {
                                            mAdapter.addFooterData(datas);//添加尾部数据
                                            mAdapter.notifyDataSetChanged();
                                            DataHelper.calculate(datas);//计算MA BOLL RSI KDJ MACD
                                            mKChartView.refreshComplete();//加载完成
                                        }*/
                                    }
                                });

                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //解析数据
                                        WebSocketModel model = mGson.fromJson(text, WebSocketModel.class);
                                        if (model != null && model.getTick() != null) {
//                                            MyLogger.i(">>>>>" + CommonUtil.timedate(model.getTick().getId() + ""));
                                            KLineEntity kLineEntity = new KLineEntity(
                                                    model.getTick().getId() + "",
                                                    (float) model.getTick().getOpen(),
                                                    (float) model.getTick().getHigh(),
                                                    (float) model.getTick().getLow(),
                                                    (float) model.getTick().getClose(),
                                                    (float) model.getTick().getVol(), 0, 0, 0, 0, 0, 0,
                                                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                    "-1"
                                            );

//                                            datas.clear();
                                            if (tempTime != model.getTick().getId()) {
                                                tempTime = model.getTick().getId();

                                                datas.add(kLineEntity);
                                                mAdapter.changeData(datas);//添加头部数据
                                            } else {
                                                datas.set(datas.size() - 1, kLineEntity);
                                                mAdapter.changeItem(datas.size() - 1, kLineEntity);//改变某个值
                                            }

//                                            MyLogger.i(">>>>>"+datas.size());
//                                            mAdapter.notifyDataSetChanged();

                                            DataHelper.calculate(datas);//计算MA BOLL RSI KDJ MACD
                                            mKChartView.refreshComplete();//加载完成
                                        }

                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
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

    //屏蔽返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            return true;//不执行父类点击事件
        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }
}
