package com.ofc.ofc.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cy.dialog.BaseDialog;
import com.github.tifezh.kchartlib.chart.BaseKChartView;
import com.github.tifezh.kchartlib.chart.KChartView;
import com.github.tifezh.kchartlib.chart.formatter.DateFormatter;
import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.activity.Fragment1DeatilActivity;
import com.ofc.ofc.activity.MainActivity;
import com.ofc.ofc.base.BaseFragment;
import com.ofc.ofc.model.Fragment1Model;
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
import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 合约
 */

public class Fragment1 extends BaseFragment {
    int type = 1, page = 1, item = -1;
    Fragment1Model model;
    //页面数据
    private RecyclerView recyclerView;
    List<Fragment1Model.MyCurrentChangeGameParticipationListBean> list1 = new ArrayList<>();
    CommonAdapter<Fragment1Model.MyCurrentChangeGameParticipationListBean> mAdapter1;

    List<Fragment1Model.MyAllChangeGameParticipationListBean> list2 = new ArrayList<>();
    CommonAdapter<Fragment1Model.MyAllChangeGameParticipationListBean> mAdapter2;

    List<Fragment1Model.HistoryChangeGameListBean> list3 = new ArrayList<>();
    CommonAdapter<Fragment1Model.HistoryChangeGameListBean> mAdapter3;

    TextView tv_qici, tv_zoushi, tv_daojishi, tv_zuokong, tv_zuoduo,
            tv_shouxufei_left, tv_shouxufei_right, tv_kanzhang, tv_kandie;
    ImageView iv_jian_left, iv_jia_left, iv_jian_right, iv_jia_right;
    EditText et_keyong_left, et_keyong_right;

    TimeCount time1 = null;
    TimeCount2 time2 = null;

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
            requestWebSocket();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //关闭连接
        WebSocketManager.getInstance().close();
        if (time1 != null) {
            time1.cancel();
        }
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
                    //关闭连接
                    WebSocketManager.getInstance().close();
                    requestServer();
                    requestWebSocket();
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
                page = 1;
                String string = "?page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                Request(string);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                String string = "?page=" + page//当前页号
                        + "&count=" + "10"//页面行数
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
        //输入监听
        et_keyong_left.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!et_keyong_left.getText().toString().trim().equals("")) {
                    double shouxufei = Double.valueOf(model.getService_charge()) / 100 * Double.valueOf(et_keyong_left.getText().toString().trim());
                    tv_shouxufei_left.setText(String.format("%.2f", shouxufei) + "USDT");
                }
            }
        });
        et_keyong_right.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!et_keyong_right.getText().toString().trim().equals("")) {
                    double shouxufei = Double.valueOf(model.getService_charge()) / 100 * Double.valueOf(et_keyong_right.getText().toString().trim());
                    tv_shouxufei_right.setText(String.format("%.2f", shouxufei) + "USDT");
                }
            }
        });

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
                /*KLineEntity data = (KLineEntity) point;
                MyLogger.i("index:" + index + " closePrice:" + data.getClosePrice());*/
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
//        requestServer();
        requestWebSocket();

    }


    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        if (isAdded()) {
            showProgress(true, getString(R.string.app_loading));
            page = 1;
            String string = "?page=" + page//当前页号
                    + "&count=" + "10"//页面行数
                    + "&token=" + localUserInfo.getToken();
            Request(string);
        }
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.HeYue + string, new OkHttpClientManager.ResultCallback<Fragment1Model>() {
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
                hideProgress();
                MyLogger.i(">>>>>>>>>合约" + response);
                model = response;

                et_keyong_left.setText("");
                tv_shouxufei_left.setText("0USDT");
                et_keyong_right.setText("");
                tv_shouxufei_right.setText("0USDT");
                if (isAdded()) {
                    tv_qici.setText(getText(R.string.fragment1_h4) + model.getChange_game().getPeriod() + getText(R.string.fragment1_h5));//期次
                    tv_zoushi.setText(model.getChange_game().getInit_at() + "-" + model.getChange_game().getWin_at() + getText(R.string.fragment1_h6));//价格走势

                    if (model.getChange_game().getStatus() != 1) {
                        tv_zuokong.setText(model.getChange_game().get_$2_amount_money());//做空
                        tv_zuoduo.setText(model.getChange_game().get_$1_amount_money());//做多
                    } else {
                        tv_zuokong.setText("--");//做空
                        tv_zuoduo.setText("--");//做多
                    }
                /*//手续费
                tv_shouxufei_left.setText(model.getService_charge() + "USDT");
                tv_shouxufei_right.setText(model.getService_charge() + "USDT");*/

                    et_keyong_left.setHint(getString(R.string.fragment1_h10) + model.getUsable_money());
                    et_keyong_right.setHint(getString(R.string.fragment1_h10) + model.getUsable_money());

                    //倒计时
                /*if (model.getChange_game().getStatus() == 1) {//状态（1.进行中 2.待开奖 3.已结束）
                    tv_daojishi.setText(getText(R.string.fragment3_h54));//待开奖
                } else {
                    tv_daojishi.setText(getText(R.string.fragment3_h46));//已结束
                }*/
                    tv_daojishi.setText(model.getChange_game().getStatus_title());//已交割
                    if (response.getCount_down() > 0) {
                        showTime();
                    }

                    //持有仓位
                    list1 = model.getMy_current_change_game_participation_list();
                    mAdapter1 = new CommonAdapter<Fragment1Model.MyCurrentChangeGameParticipationListBean>
                            (getActivity(), R.layout.item_fragment1_1, list1) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment1Model.MyCurrentChangeGameParticipationListBean bean, int position) {
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
                    };
                    //我的交易
                    list2 = model.getMy_all_change_game_participation_list();
                    mAdapter2 = new CommonAdapter<Fragment1Model.MyAllChangeGameParticipationListBean>
                            (getActivity(), R.layout.item_fragment1_2, list2) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment1Model.MyAllChangeGameParticipationListBean bean, int position) {
                            LinearLayout ll_bg = holder.getView(R.id.ll_bg);
                            if (item == position) {
                                ll_bg.setBackgroundResource(R.color.bg_1);
                            } else {
                                ll_bg.setBackgroundResource(R.color.bg_0);
                            }

                            holder.setText(R.id.tv1, getText(R.string.fragment1_h4) + bean.getChange_gameX().getPeriod() + getText(R.string.fragment1_h5));

                            TextView tv4 = holder.getView(R.id.tv4);
                            TextView tv5 = holder.getView(R.id.tv5);
                            if (bean.getChange_gameX().getStatus() == 3) {
                                holder.setText(R.id.tv2, getText(R.string.fragment1_h31) + "  " + bean.getChange_gameX().getInit_at() + "(" + bean.getChange_gameX().getInit_num() + ")"
                                        + "—" + bean.getChange_gameX().getWin_at() + "(" + bean.getChange_gameX().getWin_num() + ")");

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
                                holder.setText(R.id.tv2, getText(R.string.fragment1_h31) + "  " + bean.getChange_gameX().getInit_at() + "(~~)"
                                        + "—" + bean.getChange_gameX().getWin_at() + "(~~)");
                                tv4.setTextColor(getResources().getColor(R.color.white2));
                                tv4.setText(bean.getChange_gameX().getStatus_title());
                                tv5.setVisibility(View.GONE);

                            }

                            holder.setText(R.id.tv3, bean.getChange_gameX().get_$1_amount_money() + "");//看涨、做多
                            holder.setText(R.id.tv6, bean.getChange_gameX().get_$2_amount_money() + "");//看跌、做空

                            holder.setText(R.id.tv7, bean.getMoney() + "");//仓位
                            holder.setText(R.id.tv9, bean.getService_charge_money() + "");//手续费
                            holder.setText(R.id.tv10, bean.getChange_gameX().getStatus_title());//状态

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
                    mAdapter3 = new CommonAdapter<Fragment1Model.HistoryChangeGameListBean>
                            (getActivity(), R.layout.item_fragment1_3, list3) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment1Model.HistoryChangeGameListBean bean, int position) {
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
                            //关闭连接
                            WebSocketManager.getInstance().close();
                            if (time1 != null) {
                                time1.cancel();
                            }
                            Bundle bundle = new Bundle();
                            bundle.putString("history_id", list3.get(i).getId());
                            CommonUtil.gotoActivityWithData(getActivity(), Fragment1DeatilActivity.class, bundle, false);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });

                    changeUI();
                    MainActivity.isOver = true;
                }
            }
        });
    }

    private void RequestMore(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.HeYue + string, new OkHttpClientManager.ResultCallback<Fragment1Model>() {
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
            public void onResponse(Fragment1Model response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>合约更多" + response);
                List<Fragment1Model.MyCurrentChangeGameParticipationListBean> list1_1 = response.getMy_current_change_game_participation_list();

                List<Fragment1Model.MyAllChangeGameParticipationListBean> list2_1 = response.getMy_all_change_game_participation_list();

                List<Fragment1Model.HistoryChangeGameListBean> list3_1 = response.getHistory_change_game_list();
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

    private void requestAdd(HashMap<String, String> params) {
        OkHttpClientManager.postAsyn(getActivity(), URLs.HeYue_Add, params, new OkHttpClientManager.ResultCallback<String>() {
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
                tv_kanzhang.setClickable(true);
                tv_kandie.setClickable(true);

                requestServer();
            }

            @Override
            public void onResponse(final String response) {
                tv_kanzhang.setClickable(true);
                tv_kandie.setClickable(true);
//                hideProgress();
                MyLogger.i(">>>>>>>>>合约买入" + response);

                requestServer();
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    String info = jObj.getString("msg");
                    myToast(info);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }, true);
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
            case R.id.iv_jian_left:
                //买涨-减
                double tempMoney_jian_left = 0;
                if (!et_keyong_left.getText().toString().trim().equals("")) {
                    tempMoney_jian_left = Double.valueOf(et_keyong_left.getText().toString().trim());
                }
                if (tempMoney_jian_left > 10) {
                    tempMoney_jian_left -= 10;
                    et_keyong_left.setText((int) tempMoney_jian_left + "");
                } else {
                    tempMoney_jian_left = 0;
                    et_keyong_left.setText("");
                }

                //计算手续费
                double shouxufei1 = Double.valueOf(model.getService_charge()) / 100 * tempMoney_jian_left;
                tv_shouxufei_left.setText(String.format("%.2f", shouxufei1) + "USDT");

                break;
            case R.id.iv_jia_left:
                //买涨-加
                double tempMoney_jia_left = 0;
                if (!et_keyong_left.getText().toString().trim().equals("")) {
                    tempMoney_jia_left = Double.valueOf(et_keyong_left.getText().toString().trim());
                }
                if (Double.valueOf(model.getUsable_money()) > tempMoney_jia_left) {
                    if ((Double.valueOf(model.getUsable_money()) - tempMoney_jia_left) >= 10) {
                        tempMoney_jia_left += 10;
                    } else {
                        tempMoney_jia_left = Double.valueOf(model.getUsable_money());
                    }
                }
                et_keyong_left.setText((int) tempMoney_jia_left + "");

                //计算手续费
                double shouxufei2 = Double.valueOf(model.getService_charge()) / 100 * tempMoney_jia_left;
                tv_shouxufei_left.setText(String.format("%.2f", shouxufei2) + "USDT");
                break;
            case R.id.iv_jian_right:
                //买跌-减
                double tempMoney_jian_right = 0;
                if (!et_keyong_right.getText().toString().trim().equals("")) {
                    tempMoney_jian_right = Double.valueOf(et_keyong_right.getText().toString().trim());
                }
                if (tempMoney_jian_right > 10) {
                    tempMoney_jian_right -= 10;
                    et_keyong_right.setText((int) tempMoney_jian_right + "");
                } else {
                    tempMoney_jian_right = 0;
                    et_keyong_right.setText("");
                }
                //计算手续费
                double shouxufei3 = Double.valueOf(model.getService_charge()) / 100 * tempMoney_jian_right;
                tv_shouxufei_right.setText(String.format("%.2f", shouxufei3) + "USDT");
                break;
            case R.id.iv_jia_right:
                //买跌-加
                double tempMoney_jia_right = 0;
                if (!et_keyong_right.getText().toString().trim().equals("")) {
                    tempMoney_jia_right = Double.valueOf(et_keyong_right.getText().toString().trim());
                }
                if (Double.valueOf(model.getUsable_money()) > tempMoney_jia_right) {
                    if ((Double.valueOf(model.getUsable_money()) - tempMoney_jia_right) >= 10) {
                        tempMoney_jia_right += 10;
                    } else {
                        tempMoney_jia_right = Double.valueOf(model.getUsable_money());
                    }
                }
                et_keyong_right.setText((int) tempMoney_jia_right + "");

                //计算手续费
                double shouxufei4 = Double.valueOf(model.getService_charge()) / 100 * tempMoney_jia_right;
                tv_shouxufei_right.setText(String.format("%.2f", shouxufei4) + "USDT");

                break;
            case R.id.tv_kanzhang:
                //买涨
                if (!et_keyong_left.getText().toString().trim().equals("")) {
                    if (Double.valueOf(et_keyong_left.getText().toString().trim()) >= 10) {
                        tv_kanzhang.setClickable(false);
                        showProgress(true, getString(R.string.app_loading1));
                        HashMap<String, String> params = new HashMap<>();
                        params.put("hk", model.getHk());
                        params.put("change_game_id", model.getChange_game().getId());
                        params.put("type", "1");//类型（1.看涨 2.看跌）
                        params.put("money", et_keyong_left.getText().toString().trim());
                        params.put("token", localUserInfo.getToken());
                        requestAdd(params);

                    } else {
                        myToast(getString(R.string.fragment1_h26));
                    }
                } else {
                    myToast(getString(R.string.fragment1_h25));
                }
                break;
            case R.id.tv_kandie:
                //买跌
                if (!et_keyong_right.getText().toString().trim().equals("")) {
                    if (Double.valueOf(et_keyong_right.getText().toString().trim()) >= 10) {
                        tv_kandie.setClickable(false);
                        showProgress(true, getString(R.string.app_loading1));
                        HashMap<String, String> params = new HashMap<>();
                        params.put("hk", model.getHk());
                        params.put("change_game_id", model.getChange_game().getId());
                        params.put("type", "2");//类型（1.看涨 2.看跌）
                        params.put("money", et_keyong_right.getText().toString().trim());
                        params.put("token", localUserInfo.getToken());
                        requestAdd(params);
                    } else {
                        myToast(getString(R.string.fragment1_h26));
                    }
                } else {
                    myToast(getString(R.string.fragment1_h25));
                }
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

    private void showTime() {
        MyLogger.i(">>>>>>" + (model.getCount_down() * 1000));
        if (time1 != null) {
            time1.cancel();
        }
        time1 = new TimeCount(model.getCount_down() * 1000, 1000, tv_daojishi);//构造CountDownTimer对象
        time1.start();//开始计时
    }

    class TimeCount extends CountDownTimer {
        TextView textView;

        public TimeCount(long millisInFuture, long countDownInterval, TextView textView) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
            this.textView = textView;
        }

        @Override
        public void onFinish() {//计时完毕时触发
//            textView.setText(getString(R.string.fragment3_h54));
            textView.setText(getText(R.string.fragment1_h39));

            if (MainActivity.item == 0) {
//                requestServer();

                //GIF图显示
               /* dialog.contentView(R.layout.dialog_gifimg)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.CENTER)
                        .canceledOnTouchOutside(true)
                        .dimAmount(0.8f)
                        .show();

                GifImageView gifImageView = (GifImageView) dialog.findViewById(R.id.imageView);
                GifLoadOneTimeGif.loadOneTimeGif(getActivity(), R.mipmap.gifimg, gifImageView, 1, new GifLoadOneTimeGif.GifListener() {
                    @Override
                    public void gifPlayComplete() {
                        dialog.dismiss();

                        requestServer();
                    }
                });

                dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });*/


                //弹窗
                dialog.contentView(R.layout.dialog_daojishi)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.CENTER)
                        .canceledOnTouchOutside(false)
                        .dimAmount(0.8f)
                        .show();
                TextView textView2 = dialog.findViewById(R.id.textView2);

                if (time2 != null) {
                    time2.cancel();
                }
                time2 = new TimeCount2(6 * 1000, 1000, textView2);//构造CountDownTimer对象
                time2.start();//开始计时
            }


        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
//            textView.setText(CommonUtil.timedate3(millisUntilFinished) + "s");//秒计时
            textView.setText(CommonUtil.timedate4(millisUntilFinished, getActivity()));//时分秒倒计时
        }

    }

    class TimeCount2 extends CountDownTimer {
        TextView textView;

        public TimeCount2(long millisInFuture, long countDownInterval, TextView textView) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
            this.textView = textView;
        }

        @Override
        public void onFinish() {//计时完毕时触发
//            textView.setText(getString(R.string.fragment3_h22));
            if (MainActivity.item == 0) {
                dialog.dismiss();
                requestServer();

                /*showProgress(true, getString(R.string.app_loading));
                String string = "?token=" + localUserInfo.getToken()
                        + "&type=" + "2"
                        + "&page=" + page//当前页号
                        + "&count=" + "20"//页面行数
                        + "&period_count=" + period_count;
                request(string);*/
            }
            /*textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });*/

        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            textView.setText(getString(R.string.fragment1_h38) + CommonUtil.timedate3(millisUntilFinished) + "s");
        }
    }

    /**
     * 连接websocket、解析、展示
     */
    private void requestWebSocket() {
        isNew = true;
        MyLogger.i(">>>是否连接" + WebSocketManager.getInstance().isConnect());
        if (!WebSocketManager.getInstance().isConnect()) {//是否连接
            //没有连接时，开始连接
            new Thread(new Runnable() {
                @Override
                public void run() {
                    /*try {
                        synchronized (this) {
                            wait(2000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
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
                            WebSocketManager.getInstance().reconnect();//重连
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

    }


}
