package com.cho.chocoin.activity;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cho.chocoin.R;
import com.cho.chocoin.adapter.Pop_ListAdapter;
import com.cho.chocoin.base.BaseActivity;
import com.cho.chocoin.model.SuanLiListModel;
import com.cho.chocoin.net.OkHttpClientManager;
import com.cho.chocoin.net.URLs;
import com.cho.chocoin.utils.MyLogger;
import com.cho.chocoin.view.FixedPopupWindow;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyz on 2017/9/5.
 * 算力明细
 */

public class SuanLiListActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<SuanLiListModel.InvestListBean> list = new ArrayList<>();
    CommonAdapter<SuanLiListModel.InvestListBean> mAdapter;
    //筛选
    private LinearLayout linearLayout1, linearLayout2;
    private TextView textView1, textView2;
    private View view1, view2;
    private LinearLayout pop_view;
    int page = 1;
    String sort = "desc", status = "";
    int i1 = 0;
    int i2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suanlilist);
    }

    @Override
    protected void initView() {
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSpringViewMore(true);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                page = 1;
                String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                        + "&sort=" + sort
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentList(string);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                        + "&sort=" + sort
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentListMore(string);
            }
        });
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        pop_view = findViewByID_My(R.id.pop_view);


    }

    @Override
    protected void initData() {
        requestServer();//获取数据

    }

    private void RequestMyInvestmentList(String string) {
        OkHttpClientManager.getAsyn(SuanLiListActivity.this, URLs.SuanLiList + string, new OkHttpClientManager.ResultCallback<SuanLiListModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                onHttpResult();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(SuanLiListModel response) {
                showContentPage();
                onHttpResult();
                MyLogger.i(">>>>>>>>>算力列表" + response);
                list = response.getInvest_list();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<SuanLiListModel.InvestListBean>
                            (SuanLiListActivity.this, R.layout.item_suanlilist, list) {
                        @Override
                        protected void convert(ViewHolder holder, SuanLiListModel.InvestListBean model, int position) {
                            LinearLayout linearLayout = holder.getView(R.id.linearLayout);
                            TextView textView1 = holder.getView(R.id.textView1);
                            TextView textView2 = holder.getView(R.id.textView2);
                            TextView textView3 = holder.getView(R.id.textView3);
                            TextView textView4 = holder.getView(R.id.textView4);
                            TextView textView5 = holder.getView(R.id.textView5);
                            TextView textView6 = holder.getView(R.id.textView6);
                            TextView textView7 = holder.getView(R.id.textView7);
                            TextView textView8 = holder.getView(R.id.textView8);
                            TextView textView9 = holder.getView(R.id.textView9);
                            textView1.setText(model.getMax_bonus_money());//总值
                            textView2.setText(model.getAmount_bonus_money());//已产
                            textView3.setText(model.getStatus_title());//状态
                            textView4.setText(getString(R.string.game_h11)+"  "+model.getInterest_bonus_money());//分红
                            textView5.setText(getString(R.string.game_h13)+"  "+model.getMoney());//算力
                            textView6.setText(getString(R.string.game_h12)+"  "+model.getCommission_bonus_money());//收益
                            textView7.setText(getString(R.string.game_h14)+"  "+model.getCreated_at());//时间

                            if (model.getStatus() == 1){
                                //进行中
                                linearLayout.setBackgroundResource(R.mipmap.bg_suanlilist2);
                                textView3.setBackgroundResource(R.drawable.yuanjiaobiankuang_5_huise);
                                textView1.setTextColor(getResources().getColor(R.color.black1));
                                textView2.setTextColor(getResources().getColor(R.color.purple));
                                textView3.setTextColor(getResources().getColor(R.color.black1));
                                textView4.setTextColor(getResources().getColor(R.color.black1));
                                textView5.setTextColor(getResources().getColor(R.color.black1));
                                textView6.setTextColor(getResources().getColor(R.color.black1));
                                textView7.setTextColor(getResources().getColor(R.color.black1));
                                textView8.setTextColor(getResources().getColor(R.color.black1));
                                textView9.setTextColor(getResources().getColor(R.color.purple));

                            }else {
                                linearLayout.setBackgroundResource(R.mipmap.bg_suanlilist1);
                                textView3.setBackgroundResource(R.drawable.yuanjiao_5_baise);
                                textView1.setTextColor(getResources().getColor(R.color.white));
                                textView2.setTextColor(getResources().getColor(R.color.white));
                                textView3.setTextColor(getResources().getColor(R.color.black2));
                                textView4.setTextColor(getResources().getColor(R.color.white));
                                textView5.setTextColor(getResources().getColor(R.color.white));
                                textView6.setTextColor(getResources().getColor(R.color.white));
                                textView7.setTextColor(getResources().getColor(R.color.white));
                                textView8.setTextColor(getResources().getColor(R.color.white1));
                                textView9.setTextColor(getResources().getColor(R.color.white1));

                            }


                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                Bundle bundle1 = new Bundle();
//                bundle1.putString("TakeCashDetail",list.get(i).getId());
//                CommonUtil.gotoActivityWithData(MyTakeCashActivity.this, TakeCashDetailActivity.class,bundle1,false);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });
                }

            }
        });

    }

    private void RequestMyInvestmentListMore(String string) {
        OkHttpClientManager.getAsyn(SuanLiListActivity.this, URLs.SuanLiList + string, new OkHttpClientManager.ResultCallback<SuanLiListModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                onHttpResult();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(SuanLiListModel response) {
                showContentPage();
                onHttpResult();
                MyLogger.i(">>>>>>>>>算力列表更多" + response);
                List<SuanLiListModel.InvestListBean> list1 = new ArrayList<>();

                list1 = response.getInvest_list();
                if (list1.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                } else {
                    list.addAll(list1);
                    mAdapter.notifyDataSetChanged();
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        Drawable drawable1 = getResources().getDrawable(R.mipmap.down_blue);//选中-蓝色
        Drawable drawable2 = getResources().getDrawable(R.mipmap.down_black);//未选-灰色
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        switch (v.getId()) {
            case R.id.linearLayout1:
                textView1.setTextColor(getResources().getColor(R.color.blue));
                textView2.setTextColor(getResources().getColor(R.color.black2));
                textView1.setCompoundDrawables(null, null, drawable1, null);
                textView2.setCompoundDrawables(null, null, drawable2, null);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                showPopupWindow1(pop_view);
                break;
            case R.id.linearLayout2:
                textView1.setTextColor(getResources().getColor(R.color.black2));
                textView2.setTextColor(getResources().getColor(R.color.blue));
                textView1.setCompoundDrawables(null, null, drawable2, null);
                textView2.setCompoundDrawables(null, null, drawable1, null);
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                showPopupWindow2(pop_view);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle(R.string.fragment1_h9);
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                + "&sort=" + sort
                + "&page=" + page//当前页号
                + "&count=" + "10"//页面行数
                + "&token=" + localUserInfo.getToken();
        RequestMyInvestmentList(string);
    }

    public void onHttpResult() {
        hideProgress();
        springView.onFinishFreshAndLoad();

    }

    private void showPopupWindow1(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(SuanLiListActivity.this).inflate(
                R.layout.pop_list2, null);
        final FixedPopupWindow popupWindow = new FixedPopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        contentView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = contentView.findViewById(R.id.pop_listView).getTop();
                int height1 = contentView.findViewById(R.id.pop_listView).getBottom();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        popupWindow.dismiss();
                    }
                    if (y > height1) {
                        popupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        // 设置按钮的点击事件
        ListView pop_listView = (ListView) contentView.findViewById(R.id.pop_listView1);
        contentView.findViewById(R.id.pop_listView2).setVisibility(View.INVISIBLE);
        final List<String> list = new ArrayList<String>();
        list.add(getString(R.string.app_type_quanbu));
        list.add(getString(R.string.app_type_jinxingzhong));
        list.add(getString(R.string.app_type_yiwancheng));
        final Pop_ListAdapter adapter = new Pop_ListAdapter(SuanLiListActivity.this, list);
        adapter.setSelectItem(i1);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                adapter.notifyDataSetChanged();
                i1 = i;
                if (i == 0) {
                    status = "";
                } else {
                    status = i + "";
                }
                textView1.setText(list.get(i));
                requestServer();
                popupWindow.dismiss();
            }
        });

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        ColorDrawable dw = new ColorDrawable(this.getResources().getColor(R.color.transparentblack2));
        // 设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);
    }

    private void showPopupWindow2(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(SuanLiListActivity.this).inflate(
                R.layout.pop_list2, null);
        final FixedPopupWindow popupWindow = new FixedPopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        contentView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = contentView.findViewById(R.id.pop_listView).getTop();
                int height1 = contentView.findViewById(R.id.pop_listView).getBottom();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        popupWindow.dismiss();
                    }
                    if (y > height1) {
                        popupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        // 设置按钮的点击事件
        contentView.findViewById(R.id.pop_listView1).setVisibility(View.INVISIBLE);
        ListView pop_listView = (ListView) contentView.findViewById(R.id.pop_listView2);
        final List<String> list = new ArrayList<String>();
        list.add(getString(R.string.app_type_ansuanli));
        list.add(getString(R.string.app_type_anshijian));

        final Pop_ListAdapter adapter = new Pop_ListAdapter(SuanLiListActivity.this, list);
        adapter.setSelectItem(i2);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                i2 = i;
                adapter.notifyDataSetChanged();

                if (i == 0) {
                    sort = "desc";
                } else {
                    sort = "asc";
                }
                textView2.setText(list.get(i));
                requestServer();
                popupWindow.dismiss();
            }
        });

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        ColorDrawable dw = new ColorDrawable(this.getResources().getColor(R.color.transparentblack1));
        // 设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);
    }
}
