package com.ofc.ofc.activity;

import android.os.Bundle;
import android.os.Handler;
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
                //第一次加载
                if (page == 1) {
                    //（开盘价- 收盘价）/开盘价
                    double zhangdie = (model.getKline().getOpen() - model.getKline().getClose()) / model.getKline().getOpen() * 100;
                    textView2.setText(String.format("%.2f", zhangdie) + "%");
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
                                List<KLineEntity> datas = new ArrayList<>();
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

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });


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
