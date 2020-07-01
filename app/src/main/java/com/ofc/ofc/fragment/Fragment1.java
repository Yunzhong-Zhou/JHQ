package com.ofc.ofc.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.tifezh.kchartlib.chart.BaseKChartView;
import com.github.tifezh.kchartlib.chart.KChartView;
import com.github.tifezh.kchartlib.chart.formatter.DateFormatter;
import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.activity.MainActivity;
import com.ofc.ofc.base.BaseFragment;
import com.ofc.ofc.model.Fragment1Model;
import com.ofc.ofc.model.TESTModel;
import com.ofc.ofc.model.TEST_ListModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.ofc.ofc.utils.websocket.IReceiveMessage;
import com.ofc.ofc.utils.websocket.WebSocketManager;
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
 * 学院
 */

public class Fragment1 extends BaseFragment {
    int type = 1;
    private RecyclerView recyclerView;
    List<Fragment1Model.ArticleCategory1Bean.ArticleListBean> list1 = new ArrayList<>();
    CommonAdapter<Fragment1Model.ArticleCategory1Bean.ArticleListBean> mAdapter1;

    List<Fragment1Model.ArticleCategory2Bean.ArticleListBeanX> list2 = new ArrayList<>();
    CommonAdapter<Fragment1Model.ArticleCategory2Bean.ArticleListBeanX> mAdapter2;

    //悬浮部分
    LinearLayout linearLayout1, linearLayout2;
    TextView textView1, textView2;
    View view1, view2;


    TextView tv_1min, tv_5min, tv_30min, tv_1h, tv_1day, tv_1mon;
    boolean isOver = false;
    //wss://api.hadax.com/ws
    //wss://api.huobi.pro/ws
    //wss://api-aws.huobi.pro/ws
    String url = "wss://api.hadax.com/ws",
            fenshi = "1min", id = "btcusdt",
            sub = "market." + id + ".kline." + fenshi;
    long page = 1, time = 60 * 60 * 12, tempTime = 0, from = 0, to = 0;
    KChartView mKChartView;
    private KChartAdapter mAdapter;
    List<KLineEntity> datas = new ArrayList<>();
    List<KLineEntity> newlist = new ArrayList<>();

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
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);

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
                if (isOver) {
                    page++;
                    MyLogger.i(">>>>>>>>>" + page);
                    try {
                        //发送订阅
                        isOver = false;
                        to = from;
//                        from = (Long) (to - (time * page));
                        from = (Long) (to - time);
                        JSONObject jObj_dingyue = new JSONObject();
                        jObj_dingyue.put("req", sub);
                        jObj_dingyue.put("id", id);
                        jObj_dingyue.put("from", from);
                        jObj_dingyue.put("to", to);
                        WebSocketManager.getInstance().sendMessage(jObj_dingyue.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        //分时
        tv_1min = findViewByID_My(R.id.tv_1min);
        tv_1min.setOnClickListener(this);
        tv_5min = findViewByID_My(R.id.tv_5min);
        tv_5min.setOnClickListener(this);
        tv_30min = findViewByID_My(R.id.tv_30min);
        tv_30min.setOnClickListener(this);
        tv_1h = findViewByID_My(R.id.tv_1h);
        tv_1h.setOnClickListener(this);
        tv_1day = findViewByID_My(R.id.tv_1day);
        tv_1day.setOnClickListener(this);
        tv_1mon = findViewByID_My(R.id.tv_1mon);
        tv_1mon.setOnClickListener(this);
    }

    @Override
    protected void initData() {
//        requestServer();
        //开始连接
        new Thread(new Runnable() {
            @Override
            public void run() {
                WebSocketManager.getInstance().init(url, new IReceiveMessage() {
                    @Override
                    public void onConnectSuccess() {
                        MyLogger.i(">>>>>>连接成功");
                        try {
                            from = (Long) (System.currentTimeMillis() / 1000 - (time * page));
                            to = (Long) System.currentTimeMillis() / 1000;

                            JSONObject jObj_lishi = new JSONObject();
                            jObj_lishi.put("req", sub);
                            jObj_lishi.put("id", id);
                            jObj_lishi.put("from", from);
                            jObj_lishi.put("to", to);
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
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //解析数据
                                        TEST_ListModel model = mGson.fromJson(text, TEST_ListModel.class);
                                        newlist.clear();
                                        for (TEST_ListModel.DataBean bean : model.getData()) {
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
                                                newlist.add(kLineEntity);
                                            }
                                        }

                                        tempTime = model.getData().get(newlist.size() - 1).getId();
                                        datas.addAll(0, newlist);
                                        mAdapter.addFooterData(newlist);//添加尾部数据
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
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //解析数据
                                        TESTModel model = mGson.fromJson(text, TESTModel.class);
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
                                            newlist.clear();
                                            newlist.add(kLineEntity);

                                            if (tempTime != model.getTick().getId()) {
                                                tempTime = model.getTick().getId();

                                                datas.add(kLineEntity);
                                                mAdapter.addHeaderData(newlist);//添加头部数据
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

    private void Request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment1 + string, new OkHttpClientManager.ResultCallback<Fragment1Model>() {
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
            public void onResponse(final Fragment1Model response) {
                showContentPage();
                /*MyLogger.i(">>>>>>>>>" + response);
                //基础知识
                list1 = response.getArticle_category_1().getArticle_list();
                mAdapter1 = new CommonAdapter<Fragment1Model.ArticleCategory1Bean.ArticleListBean>
                        (getActivity(), R.layout.item_fragment1, list1) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1Model.ArticleCategory1Bean.ArticleListBean model, int position) {
                        holder.setText(R.id.textView1, model.getTitle());
                        holder.setText(R.id.textView2, model.getCreated_at());
                        holder.setText(R.id.textView3, model.getRead() + "");
                        holder.setText(R.id.textView4, model.getDigest() + "");
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        if (!model.getCover().equals(""))
                            Glide.with(getActivity())
                                    .load(IMGHOST + model.getCover())
                                    .centerCrop()
//                                    .placeholder(R.mipmap.headimg)//加载站位图
//                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片
                    }
                };
                mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url", list1.get(position).getUrl());
                        CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle, false);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });

                list2 = response.getArticle_category_2().getArticle_list();
                mAdapter2 = new CommonAdapter<Fragment1Model.ArticleCategory2Bean.ArticleListBeanX>
                        (getActivity(), R.layout.item_fragment1, list2) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1Model.ArticleCategory2Bean.ArticleListBeanX model, int position) {
                        holder.setText(R.id.textView1, model.getTitle());
                        holder.setText(R.id.textView2, model.getCreated_at());
                        holder.setText(R.id.textView3, model.getRead() + "");
                        holder.setText(R.id.textView4, model.getDigest() + "");
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        if (!model.getCover().equals(""))
                            Glide.with(getActivity())
                                    .load(IMGHOST + model.getCover())
                                    .centerCrop()
//                                    .placeholder(R.mipmap.headimg)//加载站位图
//                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片

                    }
                };
                mAdapter2.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url", list2.get(position).getUrl());
                        CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle, false);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });

                list3 = response.getArticle_category_3().getArticle_list();
                mAdapter3 = new CommonAdapter<Fragment1Model.ArticleCategory3Bean.ArticleListBeanXX>
                        (getActivity(), R.layout.item_fragment1, list3) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1Model.ArticleCategory3Bean.ArticleListBeanXX model, int position) {
                        holder.setText(R.id.textView1, model.getTitle());
                        holder.setText(R.id.textView2, model.getCreated_at());
                        holder.setText(R.id.textView3, model.getRead() + "");
                        holder.setText(R.id.textView4, model.getDigest() + "");
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        if (!model.getCover().equals(""))
                            Glide.with(getActivity())
                                    .load(IMGHOST + model.getCover())
                                    .centerCrop()
//                                    .placeholder(R.mipmap.headimg)//加载站位图
//                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片

                    }
                };
                mAdapter3.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url", list3.get(position).getUrl());
                        CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle, false);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });

                changeUI();*/

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
            case R.id.tv_1min:
                //1分钟
                //关闭连接
                WebSocketManager.getInstance().close();
                datas.clear();
                fenshi = "1min";
                id = "btcusdt";
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * 60 * 12;
                initData();
                break;
            case R.id.tv_5min:
                //5分钟
                //关闭连接
                WebSocketManager.getInstance().close();
                datas.clear();
                fenshi = "5min";
                id = "btcusdt";
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * 60 * 12 * 5;
                initData();
                break;
            case R.id.tv_30min:
                //30分钟
                //关闭连接
                WebSocketManager.getInstance().close();
                datas.clear();
                fenshi = "30min";
                id = "btcusdt";
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * 60 * 12 * 30;
                initData();
                break;
            case R.id.tv_1h:
                //60分钟
                //关闭连接
                WebSocketManager.getInstance().close();
                datas.clear();
                fenshi = "60min";
                id = "btcusdt";
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * 60 * 12 * 60;
                initData();
                break;
            case R.id.tv_1day:
                //1天
                //关闭连接
                WebSocketManager.getInstance().close();
                datas.clear();
                fenshi = "1day";
                id = "btcusdt";
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * 60 * 12 * 60;
                initData();
                break;
            case R.id.tv_1mon:
                //1月
                //关闭连接
                WebSocketManager.getInstance().close();
                datas.clear();
                fenshi = "1week";
                id = "btcusdt";
                sub = "market." + id + ".kline." + fenshi;
                time = 60 * 60 * 12 * 60;
                initData();
                break;
        }
    }

    private void changeUI() {
        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.white));
            textView2.setTextColor(getResources().getColor(R.color.white2));
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            if (list1.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter1);
            } else {
                showEmptyPage();
            }

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.white2));
            textView2.setTextColor(getResources().getColor(R.color.white));
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);

            if (list2.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter2);
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
