package com.fone.fone.activity;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.fone.fone.R;
import com.fone.fone.adapter.Pop_ListAdapter;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.CityFriendListModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.view.FixedPopupWindow;
import com.liaoinstan.springview.widget.SpringView;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zyz on 2017/9/5.
 * 合作伙伴
 */

public class CityFriendListActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<CityFriendListModel.ServiceCenterListBean> list = new ArrayList<>();
    CommonAdapter<CityFriendListModel.ServiceCenterListBean> mAdapter;
    //筛选
    private LinearLayout linearLayout1, linearLayout2;
    private TextView textView1, textView2;
    private View view1, view2;

    private LinearLayout pop_view;
    int page = 1;
    String province = "", grade = "";
    int i1 = 0;
    int i2 = 0;

    //省市
    CityConfig cityConfig = null;
    CityPickerView mPicker = new CityPickerView();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityfriendlist);
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
                String string = "?province=" + province
                        + "&grade=" + grade
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentList(string);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                String string = "?province=" + province
                        + "&grade=" + grade
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

//预先加载仿iOS滚轮实现的全部数据
        mPicker.init(this);
        cityConfig = new CityConfig.Builder()
                .title(getString(R.string.myprofile_h13))//标题
                .titleTextSize(18)//标题文字大小
                .titleTextColor("#585858")//标题文字颜  色
                .titleBackgroundColor("#eaeaea")//标题栏背景色
                .confirTextColor("#10A589")//确认按钮文字颜色
                .confirmText(getString(R.string.app_confirm))//确认按钮文字
                .confirmTextSize(16)//确认按钮文字大小
                .cancelTextColor("#10A589")//取消按钮文字颜色
                .cancelText(getString(R.string.app_cancel))//取消按钮文字
                .cancelTextSize(16)//取消按钮文字大小
                .setCityWheelType(CityConfig.WheelType.PRO)//显示类，只显示省份一级，显示省市两级还是显示省市区三级
                .showBackground(true)//是否显示半透明背景
                .visibleItemsCount(7)//显示item的数量
                .province("北京市")//默认显示的省份
                .city("北京市")//默认显示省份下面的城市
                .district("朝阳区")//默认显示省市下面的区县数据
                .provinceCyclic(true)//省份滚轮是否可以循环滚动
                .cityCyclic(true)//城市滚轮是否可以循环滚动
                .districtCyclic(true)//区县滚轮是否循环滚动
                .setCustomItemLayout(R.layout.item_city)//自定义item的布局
                .setCustomItemTextViewId(R.id.textView1)//自定义item布局里面的textViewid
                .drawShadows(true)//滚轮不显示模糊效果
                .setLineColor("#80CDCDCE")//中间横线的颜色
                .setLineHeigh(1)//中间横线的高度
                .setShowGAT(true)//是否显示港澳台数据，默认不显示
                .build();

        //设置自定义的属性配置
        mPicker.setConfig(cityConfig);

        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province1, CityBean city1, DistrictBean district1) {
                //省份province//城市city//地区district
                province = province1.getName().toString();
//                city = city1.getName().toString();
//                district = district1.getName().toString();

                requestServer();
            }

            @Override
            public void onCancel() {
               /* textView1.setText(getString(R.string.myprofile_h14));
                textView2.setText(getString(R.string.myprofile_h15));
                textView3.setText(getString(R.string.myprofile_h16));*/
//                ToastUtils.showLongToast(this, "已取消");
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void RequestMyInvestmentList(String string) {
        OkHttpClientManager.getAsyn(CityFriendListActivity.this, URLs.CityFriendList + string, new OkHttpClientManager.ResultCallback<CityFriendListModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                onHttpResult();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(CityFriendListModel response) {
                showContentPage();
                onHttpResult();
                list = response.getService_center_list();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<CityFriendListModel.ServiceCenterListBean>
                            (CityFriendListActivity.this, R.layout.item_cityfriend, list) {
                        @Override
                        protected void convert(ViewHolder holder, CityFriendListModel.ServiceCenterListBean model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(CityFriendListActivity.this)
                                    .load(OkHttpClientManager.IMGHOST + localUserInfo.getUserImage())
                                    .fitCenter()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(CityFriendListActivity.this, 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView1);//加载图片

                            holder.setText(R.id.tv_name, model.getTitle());
                            holder.setText(R.id.tv_content, model.getProvince() + model.getCity() + model.getDistrict());
                            holder.setText(R.id.tv_addr, model.getAddress());
                            holder.setText(R.id.tv_num, model.getArea() + "㎡");

                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                   /* mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            Bundle bundle1 = new Bundle();
                            bundle1.putString("id", list.get(position).getId());
                            CommonUtil.gotoActivityWithData(CityFriendListActivity.this, RechargeDetailActivity.class, bundle1, false);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });*/
                }

            }
        });

    }

    private void RequestMyInvestmentListMore(String string) {
        OkHttpClientManager.getAsyn(CityFriendListActivity.this, URLs.CityFriendList + string, new OkHttpClientManager.ResultCallback<CityFriendListModel>() {
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
            public void onResponse(CityFriendListModel response) {
                showContentPage();
                onHttpResult();

                List<CityFriendListModel.ServiceCenterListBean> list1 = new ArrayList<>();
                list1 = response.getService_center_list();
                if (list1.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                    page--;
                } else {
                    list.addAll(list1);
                    mAdapter.notifyDataSetChanged();
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
//                showPopupWindow1(pop_view);
                mPicker.showCityPicker();
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
        titleView.setTitle(R.string.myprofile_h62);
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        String string = "?province=" + province
                + "&grade=" + grade
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
        final View contentView = LayoutInflater.from(CityFriendListActivity.this).inflate(
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
        list.add("重庆");
        list.add("上海");
        list.add("北京");
        final Pop_ListAdapter adapter = new Pop_ListAdapter(CityFriendListActivity.this, list);
        adapter.setSelectItem(i1);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                adapter.notifyDataSetChanged();
                i1 = i;
                if (i == 0) {
                    province = "";
                } else {
                    province = list.get(i);
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
        final View contentView = LayoutInflater.from(CityFriendListActivity.this).inflate(
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

        list.add(getString(R.string.app_type_quanbu));
        list.add("一级");
        list.add("二级");

        final Pop_ListAdapter adapter = new Pop_ListAdapter(CityFriendListActivity.this, list);
        adapter.setSelectItem(i2);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                i2 = i;
                adapter.notifyDataSetChanged();
                if (i == 0) {
                    grade = "";
                } else {
                    grade = i + "";
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
