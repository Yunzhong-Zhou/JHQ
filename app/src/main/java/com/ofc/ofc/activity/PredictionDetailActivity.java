package com.ofc.ofc.activity;

import android.os.Bundle;
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
import com.ofc.ofc.utils.websocket.WebSocketManager;
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
public class PredictionDetailActivity extends BaseActivity {
    PredictionDetailModel model;
    List<PredictionDetailModel.KlineListBean> list = new ArrayList<>();

    String symbol = "";
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8,
            textView9, textView10;
    ImageView imageView1, imageView2;
    int page = 0;


    KChartView mKChartView;
    private KChartAdapter mAdapter;
    List<KLineEntity> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictiondetail);

        setSwipeBackEnable(false); //主 activity 可以调用该方法，禁止滑动删除
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //关闭连接
        WebSocketManager.getInstance().close();
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
                page = page + 1;
                MyLogger.i(">>>>>>>>>" + page);
                showProgress(true, getString(R.string.app_loading));
                String string = "?token=" + localUserInfo.getToken()
                        + "&page=" + page//当前页号
                        + "&count=" + "168"//页面行数
                        + "&symbol=" + symbol;
                Request(string);

            }
        });

    }

    @Override
    protected void initData() {
        //发送信息
//        WebSocketManager.getInstance().sendMessage("客户端发送");
        //关闭连接
//        WebSocketManager.getInstance().close();

        //开始连接
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                //wss://api.hadax.com/ws
                //wss://api.huobi.pro/ws
                WebSocketManager.getInstance().init("wss://api.hadax.com/ws", new IReceiveMessage() {
                    @Override
                    public void onConnectSuccess() {
                        MyLogger.i(">>>>>>连接成功");
                        try {
                            //发送订阅
                            JSONObject jObj_dingyue = new JSONObject();
                            jObj_dingyue.put("sub","market.btcusdt.kline.1min");
                            jObj_dingyue.put("id","btcusdt");
                            jObj_dingyue.put("from",(Long)System.currentTimeMillis()/1000 - 600);
                            jObj_dingyue.put("to",(Long)System.currentTimeMillis()/1000);
                            WebSocketManager.getInstance().sendMessage(jObj_dingyue.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onConnectFailed() {
                        MyLogger.i(">>>>>>连接失败");
                    }

                    @Override
                    public void onClose() {
                        MyLogger.i(">>>>>>关闭成功");
                    }

                    @Override
                    public void onMessage(String text) {
                        MyLogger.i("接收消息",text);

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
                                jObj_pong.put("pong",ping);
                                WebSocketManager.getInstance().sendMessage(jObj_pong.toString());
                            }else {



                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();*/

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
//                showErrorPage();
                hideProgress();
                mKChartView.refreshEnd();//刷新完成，没有数据
                if (!info.equals("")) {
                    myToast(info);
                }
                if (page > 1) {
                    page = page - 1;
                }

            }

            @Override
            public void onResponse(PredictionDetailModel response) {
//                showContentPage();
                MyLogger.i(">>>>>>>>>预测详情" + response);
                model = response;
                //第一次加载
                if (page == 1) {
                    switch (symbol) {
                        case "XRP":
                        case "TRX":
                            //压力位
                            textView7.setText(String.format("%.6f", model.getKline().getResistence()) + " $");
                            //支撑位
                            textView6.setText(String.format("%.6f", model.getKline().getSupport()) + " $");
                            break;
                        default:
                            //压力位
                            textView7.setText(String.format("%.2f", model.getKline().getResistence()) + " $");
                            //支撑位
                            textView6.setText(String.format("%.2f", model.getKline().getSupport()) + " $");
                            break;
                    }
                    //（收盘价 - 开盘价）/开盘价
                    double zhangdie = (model.getKline().getClose() - model.getKline().getOpen()) / model.getKline().getOpen() * 100;
                    if (zhangdie > 0) {
                        //上涨
                        textView2.setText("+" + String.format("%.2f", zhangdie) + "%");
                        textView2.setTextColor(getResources().getColor(R.color.green));
                        imageView2.setImageResource(R.mipmap.ic_jiantou_up);
                    } else {
                        textView2.setText(String.format("%.2f", zhangdie) + "%");
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

                    //建议时机
                    textView8.setText(CommonUtil.timedate(model.getTrading_point().getTimestamp() + ""));
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //反转数据
                                Collections.reverse(response.getKline_list());
//                                list.addAll(0,response.getKline_list());
                                list = response.getKline_list();
                                datas.clear();
                                for (PredictionDetailModel.KlineListBean bean : list) {
                                    KLineEntity kLineEntity = new KLineEntity(
                                            bean.getTimestamp(),
                                            (float) bean.getOpen(),
                                            (float) bean.getHigh(),
                                            (float) bean.getLow(),
                                            (float) bean.getClose(),
                                            (float) bean.getVolume(), 0, 0, 0, 0, 0, 0,
                                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                            bean.getStatus()
                                    );
                                    datas.add(kLineEntity);
                                }
                                DataHelper.calculate(datas);
                                mAdapter.addFooterData(datas);
                                mKChartView.refreshComplete();//加载完成
                            }
                        });
                    }
                }).start();


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

    //屏蔽返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            return true;//不执行父类点击事件
        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }
}
