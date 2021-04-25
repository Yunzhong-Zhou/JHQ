package com.ghzk.ghzk.activity;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ghzk.ghzk.R;
import com.ghzk.ghzk.adapter.Pop_ListAdapter;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.model.Fragment1Model;
import com.ghzk.ghzk.model.Fragment1Model_P;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.MyLogger;
import com.ghzk.ghzk.view.FixedPopupWindow;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.ghzk.ghzk.net.OkHttpClientManager.IMGHOST;

/**
 * Created by zyz on 2017/9/5.
 * 合作品牌
 */

public class CooperativeBrandActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<Fragment1Model.CooperationBrandListBean> list1_tmep = new ArrayList<>();
    List<Fragment1Model_P> list1 = new ArrayList<>();
    CommonAdapter<Fragment1Model_P> mAdapter1;
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
        setContentView(R.layout.activity_cooperativebrand);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();//获取数据
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
                        + "&count=" + "20"//页面行数
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
                        + "&count=" + "20"//页面行数
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

    }

    private void RequestMyInvestmentList(String string) {
        OkHttpClientManager.getAsyn(CooperativeBrandActivity.this, URLs.Fragment1_Brand + string,
                new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                onHttpResult();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                showContentPage();
                onHttpResult();
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    JSONArray jsonArray = jObj.getJSONArray("data");
                    list1_tmep = JSON.parseArray(jsonArray.toString(), Fragment1Model.CooperationBrandListBean.class);
                    if (list1_tmep.size() == 0) {
                        showEmptyPage();//空数据
                    } else {
                        list1.clear();
                        List<String> img1 = new ArrayList<>();
                        List<String> img2 = new ArrayList<>();
                        List<String> img3 = new ArrayList<>();
                        for (int i = 0; i < list1_tmep.size(); i++) {
                            switch (i % 3) {
                                case 0:
                                    img1.add(list1_tmep.get(i).getLogo());
                                    break;
                                case 1:
                                    img2.add(list1_tmep.get(i).getLogo());
                                    break;
                                case 2:
                                    img3.add(list1_tmep.get(i).getLogo());
                                    break;
                            }
                        }

                        for (int i = 0; i < img1.size(); i++) {
                            String img_1 = img1.get(i);
                            String img_2 = "";
                            if (img2.size() > i) {
                                img_2 = img2.get(i);
                            }
                            String img_3 = "";
                            if (img3.size() > i) {
                                img_3 = img3.get(i);
                            }
                            list1.add(new Fragment1Model_P(img_1, img_2, img_3));
                        }

                        MyLogger.i(">>>>>>>>>>" + list1.toString());

                        mAdapter1 = new CommonAdapter<Fragment1Model_P>
                                (CooperativeBrandActivity.this, R.layout.item_fragment1_1, list1) {
                            @Override
                            protected void convert(ViewHolder holder, Fragment1Model_P model, int position) {
                                //第一张
                                ImageView img1 = holder.getView(R.id.img1);
                                Glide.with(CooperativeBrandActivity.this).load(IMGHOST + model.getImg1())
                                        .centerCrop()
                                        .placeholder(R.mipmap.loading)//加载站位图
                                        .error(R.mipmap.zanwutupian)//加载失败
                                        .into(img1);//加载图片

                                //第二张
                                ImageView img2 = holder.getView(R.id.img2);
                                if (!model.getImg2().equals("")) {
                                    holder.getView(R.id.view1).setVisibility(View.VISIBLE);
                                    Glide.with(CooperativeBrandActivity.this).load(IMGHOST + model.getImg2())
                                            .centerCrop()
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(img2);//加载图片
                                } else {
                                    holder.getView(R.id.view1).setVisibility(View.GONE);
                                }

                                //第三张
                                ImageView img3 = holder.getView(R.id.img3);
                                if (!model.getImg3().equals("")) {
                                    holder.getView(R.id.view2).setVisibility(View.VISIBLE);
                                    Glide.with(CooperativeBrandActivity.this).load(IMGHOST + model.getImg3())
                                            .centerCrop()
                                            .placeholder(R.mipmap.loading)//加载站位图
                                            .error(R.mipmap.zanwutupian)//加载失败
                                            .into(img3);//加载图片
                                } else {
                                    holder.getView(R.id.view2).setVisibility(View.GONE);
                                }

                            }
                        };

                        showContentPage();
                        recyclerView.setAdapter(mAdapter1);
//                mAdapter1.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

    }

    private void RequestMyInvestmentListMore(String string) {
        OkHttpClientManager.getAsyn(CooperativeBrandActivity.this,
                URLs.Fragment1_Brand + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                onHttpResult();
                if (!info.equals("")) {
                    showToast(info);
                }
                page--;
            }

            @Override
            public void onResponse(String response) {
                showContentPage();
                onHttpResult();
                JSONObject jObj;
                List<Fragment1Model.CooperationBrandListBean> list1_temp1 = new ArrayList<>();
                try {
                    jObj = new JSONObject(response);
                    JSONArray jsonArray = jObj.getJSONArray("data");
                    list1_temp1 = JSON.parseArray(jsonArray.toString(), Fragment1Model.CooperationBrandListBean.class);
                    if (list1_temp1.size() == 0) {
                        myToast(getString(R.string.app_nomore));
                        page--;
                    } else {
                        list1_tmep.addAll(list1_temp1);
                        list1.clear();
                        List<String> img1 = new ArrayList<>();
                        List<String> img2 = new ArrayList<>();
                        List<String> img3 = new ArrayList<>();
                        for (int i = 0; i < list1_tmep.size(); i++) {
                            switch (i % 3) {
                                case 0:
                                    img1.add(list1_tmep.get(i).getLogo());
                                    break;
                                case 1:
                                    img2.add(list1_tmep.get(i).getLogo());
                                    break;
                                case 2:
                                    img3.add(list1_tmep.get(i).getLogo());
                                    break;
                            }
                        }

                        for (int i = 0; i < img1.size(); i++) {
                            String img_1 = img1.get(i);
                            String img_2 = "";
                            if (img2.size() > i) {
                                img_2 = img2.get(i);
                            }
                            String img_3 = "";
                            if (img3.size() > i) {
                                img_3 = img3.get(i);
                            }
                            list1.add(new Fragment1Model_P(img_1, img_2, img_3));
                        }

                        MyLogger.i(">>>>>>>>>>" + list1.toString());

                        mAdapter1.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        Drawable drawable1 = getResources().getDrawable(R.mipmap.down_green);//选中-蓝色
        Drawable drawable2 = getResources().getDrawable(R.mipmap.down_black);//未选-灰色
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        switch (v.getId()) {
            case R.id.linearLayout1:
                textView1.setTextColor(getResources().getColor(R.color.green));
                textView2.setTextColor(getResources().getColor(R.color.black3));
                textView1.setCompoundDrawables(null, null, drawable1, null);
                textView2.setCompoundDrawables(null, null, drawable2, null);
//                view1.setVisibility(View.VISIBLE);
//                view2.setVisibility(View.INVISIBLE);
                showPopupWindow1(pop_view);
                break;
            case R.id.linearLayout2:
                textView1.setTextColor(getResources().getColor(R.color.black3));
                textView2.setTextColor(getResources().getColor(R.color.green));
                textView1.setCompoundDrawables(null, null, drawable2, null);
                textView2.setCompoundDrawables(null, null, drawable1, null);
//                view1.setVisibility(View.INVISIBLE);
//                view2.setVisibility(View.VISIBLE);
                showPopupWindow2(pop_view);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle(R.string.fragment1_h2);
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                + "&sort=" + sort
                + "&page=" + page//当前页号
                + "&count=" + "20"//页面行数
                + "&token=" + localUserInfo.getToken();
        RequestMyInvestmentList(string);
    }

    public void onHttpResult() {
        hideProgress();
        springView.onFinishFreshAndLoad();

    }

    private void showPopupWindow1(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(CooperativeBrandActivity.this).inflate(
                R.layout.pop_list3, null);
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
//        list.add(getString(R.string.app_type_quanbu));
        list.add(getString(R.string.app_type_jiangxu));
        list.add(getString(R.string.app_type_shengxu));
        final Pop_ListAdapter adapter = new Pop_ListAdapter(CooperativeBrandActivity.this, list);
        adapter.setSelectItem(i1);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                adapter.notifyDataSetChanged();
                i1 = i;
                if (i == 0) {
                    sort = "desc";
                } else {
                    sort = "asc";
                }
//                textView1.setText(list.get(i));
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
        final View contentView = LayoutInflater.from(CooperativeBrandActivity.this).inflate(
                R.layout.pop_list3, null);
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

        list.add(getString(R.string.app_type_quanbu));
        list.add(getString(R.string.app_type_daishenhe));
        list.add(getString(R.string.app_type_yitongguo));
        list.add(getString(R.string.app_type_weitongguo));
        list.add(getString(R.string.app_cancel));
        final Pop_ListAdapter adapter = new Pop_ListAdapter(CooperativeBrandActivity.this, list);
        adapter.setSelectItem(i2);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                i2 = i;
                adapter.notifyDataSetChanged();
                if (i == 0) {
                    status = "";
                } else {
                    status = i + "";
                }
//                textView2.setText(list.get(i));
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
