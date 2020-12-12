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
import com.fone.fone.R;
import com.fone.fone.adapter.Pop_ListAdapter;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.USDTListModel1;
import com.fone.fone.model.USDTListModel2;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.fone.fone.view.FixedPopupWindow;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.fone.fone.net.OkHttpClientManager.IMGHOST;


/**
 * Created by Mr.Z on 2020/10/22.
 * DRVT交易
 */
public class USDTJiaoYiActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<USDTListModel1.UsdtDealListBean> list1 = new ArrayList<>();
    CommonAdapter<USDTListModel1.UsdtDealListBean> mAdapter1;
    List<USDTListModel2.UsdtDealListBean> list2 = new ArrayList<>();
    CommonAdapter<USDTListModel2.UsdtDealListBean> mAdapter2;
    //筛选
    private LinearLayout linearLayout1, linearLayout2;
    private TextView textView1, textView2;
    private View view1, view2;

    int page1 = 1, page2 = 1, type = 1;

    //筛选
    private LinearLayout linearLayout_1, linearLayout_2;
    private TextView textView_1, textView_2;

    private LinearLayout pop_view;
    String sort = "asc", field = "usdt_cny_price";
    int i1 = 0;
    int i2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usdtjiaoyi);
        findViewByID_My(R.id.title).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
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
                if (type == 1) {
                    page1 = 1;
                    String string = "?page=" + page1//当前页号
                            + "&count=" + "10"//页面行数
                            + "&sort=" + sort
                            + "&field=" + field
                            + "&token=" + localUserInfo.getToken();
                    RequestDRVTList1(string);
                } else {
                    page2 = 1;
                    String string = "?page=" + page2//当前页号
                            + "&count=" + "10"//页面行数
                            + "&sort=" + sort
                            + "&field=" + field
                            + "&token=" + localUserInfo.getToken();
                    RequestDRVTList2(string);
                }

            }

            @Override
            public void onLoadmore() {
                if (type == 1) {
                    page1 = page1 + 1;
                    //加载更多
                    String string = "?page=" + page1//当前页号
                            + "&count=" + "10"//页面行数
                            + "&sort=" + sort
                            + "&field=" + field
                            + "&token=" + localUserInfo.getToken();
                    RequestDRVTListMore1(string);
                } else {
                    page2 = page2 + 1;
                    //加载更多
                    String string = "?page=" + page2//当前页号
                            + "&count=" + "10"//页面行数
                            + "&sort=" + sort
                            + "&field=" + field
                            + "&token=" + localUserInfo.getToken();
                    RequestDRVTListMore2(string);
                }
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

        linearLayout_1 = findViewByID_My(R.id.linearLayout_1);
        linearLayout_2 = findViewByID_My(R.id.linearLayout_2);
        linearLayout_1.setOnClickListener(this);
        linearLayout_2.setOnClickListener(this);
        textView_1 = findViewByID_My(R.id.textView_1);
        textView_2 = findViewByID_My(R.id.textView_2);
        pop_view = findViewByID_My(R.id.pop_view);
    }

    @Override
    protected void initData() {

    }

    private void RequestDRVTList1(String string) {
        OkHttpClientManager.getAsyn(USDTJiaoYiActivity.this, URLs.USDTJiaoYiList1 + string, new OkHttpClientManager.ResultCallback<USDTListModel1>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(USDTListModel1 response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>USDT交易信息列表列表" + response);

                list1 = response.getUsdt_deal_list();
                if (list1.size() > 0) {
                    showContentPage();
                    mAdapter1 = new CommonAdapter<USDTListModel1.UsdtDealListBean>
                            (USDTJiaoYiActivity.this, R.layout.item_usdtjiaoyi1, list1) {
                        @Override
                        protected void convert(ViewHolder holder, USDTListModel1.UsdtDealListBean model, int position) {
                            holder.setText(R.id.tv_name, model.getBuy_member_nickname());//昵称
                            holder.setText(R.id.tv_num, model.getMoney() + "");//数量
                            holder.setText(R.id.tv_price, model.getUsdt_cny_price() + "CNY");//单价
                            holder.setText(R.id.tv_all, String.format("%.2f", Double.valueOf(model.getMoney()) * Double.valueOf(model.getUsdt_cny_price())) + "");//总量
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(USDTJiaoYiActivity.this)
                                    .load(IMGHOST + model.getBuy_member_head())
                                    .centerCrop()
//                    .placeholder(R.mipmap.headimg)//加载站位图
//                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片

                            holder.getView(R.id.tv_sell).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showToast(getString(R.string.qianbao_h152)
                                            , getString(R.string.app_confirm),
                                            getString(R.string.app_cancel), new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                    /*String string = "?id=" + model.getId()
                                                            + "&token=" + localUserInfo.getToken();*/
                                                    HashMap<String, String> params = new HashMap<>();
                                                    params.put("token", localUserInfo.getToken());
                                                    params.put("id", model.getId());
                                                    RequestSell(params);
                                                }
                                            }, new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                }
                                            });
                                }
                            });
                            holder.getView(R.id.iv_bank).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CommonUtil.gotoActivity(USDTJiaoYiActivity.this, BankCardSettingActivity.class, false);
                                }
                            });
                        }
                    };
                    recyclerView.setAdapter(mAdapter1);
//                    mAdapter1.notifyDataSetChanged();
                } else {
                    showEmptyPage();
                }

//                changeUI();
            }
        });

    }

    private void RequestDRVTListMore1(String string) {
        OkHttpClientManager.getAsyn(USDTJiaoYiActivity.this, URLs.USDTJiaoYiList1 + string, new OkHttpClientManager.ResultCallback<USDTListModel1>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
                page1--;
            }

            @Override
            public void onResponse(USDTListModel1 response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>USDT交易信息列表更多" + response);

                List<USDTListModel1.UsdtDealListBean> list_1 = new ArrayList<>();
                list_1 = response.getUsdt_deal_list();
                if (list_1.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                    page1--;
                } else {
                    list1.addAll(list_1);
                    mAdapter1.notifyDataSetChanged();
                }
            }
        });

    }

    private void RequestDRVTList2(String string) {
        OkHttpClientManager.getAsyn(USDTJiaoYiActivity.this, URLs.USDTJiaoYiList2 + string, new OkHttpClientManager.ResultCallback<USDTListModel2>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(USDTListModel2 response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>USDT我的购买列表列表" + response);

                list2 = response.getUsdt_deal_list();
                if (list2.size() > 0) {
                    showContentPage();
                    mAdapter2 = new CommonAdapter<USDTListModel2.UsdtDealListBean>
                            (USDTJiaoYiActivity.this, R.layout.item_usdtjiaoyi2, list2) {
                        @Override
                        protected void convert(ViewHolder holder, USDTListModel2.UsdtDealListBean model, int position) {
                            holder.setText(R.id.tv_time, model.getCreated_at());//时间
                            holder.setText(R.id.tv_zongliang, model.getMoney() + "");//总量
                            holder.setText(R.id.tv_danjia, model.getUsdt_cny_price() + "CNY");//单价
//                            holder.setText(R.id.tv_zonge, model.getUsdt_money() + "USDT");//总额
//                            holder.setText(R.id.tv_yigou, model.getCurrent_money() + "");//已购

                            TextView tv_type = holder.getView(R.id.tv_type);
//                            TextView tv_zengjia = holder.getView(R.id.tv_zengjia);
                            TextView tv_quxiao = holder.getView(R.id.tv_quxiao);

                            tv_type.setText(model.getStatus_title());
                            tv_type.setVisibility(View.VISIBLE);
                            switch (model.getStatus()) {
                                //状态（1.待匹配 2.待付款 3.已付款 4.已完成 5.申诉中 6.申诉成功 7.申诉失败 8.取消）
                                case 1:
                                    //待匹配
                                case 2:
                                    //待付款
//                                    tv_zengjia.setVisibility(View.VISIBLE);
                                    tv_quxiao.setVisibility(View.VISIBLE);
//                                    tv_type.setText(getString(R.string.app_type_jinxingzhong));
                                    tv_type.setTextColor(getResources().getColor(R.color.orange_1));
                                    break;
                                case 3:
                                    //已付款
                                case 4:
                                    //已完成
//                                    tv_zengjia.setVisibility(View.GONE);
                                    tv_quxiao.setVisibility(View.GONE);
//                                    tv_type.setText(getString(R.string.qianbao_h114));
                                    tv_type.setTextColor(getResources().getColor(R.color.green_1));
                                    break;
                                default:
                                    tv_quxiao.setVisibility(View.GONE);
                                    tv_type.setTextColor(getResources().getColor(R.color.black3));
                                    break;
                            }

                            holder.getView(R.id.tv_jilu).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (model.getStatus() != 1) {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("id", model.getId());
//                                    CommonUtil.gotoActivityWithData(USDTJiaoYiActivity.this, USDTJiaoyiListActivity.class, bundle, false);
                                        CommonUtil.gotoActivityWithData(USDTJiaoYiActivity.this, USDTOrderInfoActivity.class, bundle, false);
                                    }

                                }
                            });
                            holder.getView(R.id.tv_zengjia).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CommonUtil.gotoActivity(USDTJiaoYiActivity.this, DRVTBuyActivity.class, false);
                                }
                            });
                            holder.getView(R.id.tv_quxiao).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showToast(getString(R.string.qianbao_h115)
                                            , getString(R.string.app_confirm),
                                            getString(R.string.app_cancel), new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                    String string = "?id=" + model.getId()
                                                            + "&token=" + localUserInfo.getToken();
                                                    RequestCancel(string);
                                                }
                                            }, new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                }
                                            });
                                }
                            });

                        }
                    };
                    mAdapter2.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            if (list2.get(i).getStatus() != 1) {
                                Bundle bundle = new Bundle();
                                bundle.putString("id", list2.get(i).getId());
//                                    CommonUtil.gotoActivityWithData(USDTJiaoYiActivity.this, USDTJiaoyiListActivity.class, bundle, false);
                                CommonUtil.gotoActivityWithData(USDTJiaoYiActivity.this, USDTOrderInfoActivity.class, bundle, false);
                            }
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    recyclerView.setAdapter(mAdapter2);
//                    mAdapter2.notifyDataSetChanged();
                } else {
                    showEmptyPage();
                }
//                changeUI();
            }
        });

    }

    private void RequestDRVTListMore2(String string) {
        OkHttpClientManager.getAsyn(USDTJiaoYiActivity.this, URLs.USDTJiaoYiList2 + string, new OkHttpClientManager.ResultCallback<USDTListModel2>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
                page2--;
            }

            @Override
            public void onResponse(USDTListModel2 response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>USDT我的购买列表更多" + response);
                List<USDTListModel2.UsdtDealListBean> list_2 = new ArrayList<>();
                list_2 = response.getUsdt_deal_list();
                if (list_2.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                    page2--;
                } else {
                    list2.addAll(list_2);
                    mAdapter2.notifyDataSetChanged();
                }
            }
        });

    }

    private void RequestCancel(String string) {
        OkHttpClientManager.getAsyn(USDTJiaoYiActivity.this, URLs.USDTCancel + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>USDT购买取消" + response);
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    String info = jObj.getString("msg");
                    MyLogger.i(">>>>>>>"+info);
                    showToast(info);

                    requestServer();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//
            }
        }, false);

    }

    private void RequestSell(Map<String, String> params) {
        OkHttpClientManager.postAsyn(USDTJiaoYiActivity.this, URLs.USDTSell, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    if (info.contains(getString(R.string.qianbao_h140))) {
                        showToast(getString(R.string.qianbao_h181),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(USDTJiaoYiActivity.this, BankCardSettingActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    } /*else if (info.contains(getString(R.string.password_h3))) {
                        showToast(getString(R.string.password_h4),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(TakeCashActivity.this, SetAddressActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    }*/ /*else if (info.contains(getString(R.string.password_h7))) {
                        showToast(getString(R.string.password_h8),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(TakeCashActivity.this, SetAddressActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    } */ else {
                        showToast(info);
                    }
                }
                requestServer();
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>USDT出售" + response);
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    String info = jObj.getString("msg");
                    myToast(info);
//                    requestServer();

                    JSONObject jObj1 = jObj.getJSONObject("data");
                    Bundle bundle = new Bundle();
                    bundle.putString("id", jObj1.getString("id"));
                    CommonUtil.gotoActivityWithData(USDTJiaoYiActivity.this, USDTOrderInfoActivity.class, bundle, false);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//
            }
        }, false);

    }


    @Override
    public void onClick(View v) {
        Drawable drawable1 = getResources().getDrawable(R.mipmap.down_green);//选中-蓝色
        Drawable drawable2 = getResources().getDrawable(R.mipmap.down_black);//未选-灰色
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.tv_buy:
                //购买
                CommonUtil.gotoActivity(USDTJiaoYiActivity.this, USDTBuyActivity.class, false);
                break;
            case R.id.imageView:
                //记录
                Bundle bundle = new Bundle();
                bundle.putString("id", "");
                CommonUtil.gotoActivityWithData(USDTJiaoYiActivity.this, USDTJiaoyiListActivity.class, bundle, false);
                break;
            case R.id.linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.linearLayout2:
                type = 2;
                changeUI();
                break;

            case R.id.linearLayout_1:
                field = "usdt_cny_price";
//                textView_1.setTextColor(getResources().getColor(R.color.green));
//                textView_2.setTextColor(getResources().getColor(R.color.black3));
//                textView_1.setCompoundDrawables(null, null, drawable1, null);
//                textView_2.setCompoundDrawables(null, null, drawable2, null);
                showPopupWindow1(pop_view);
                break;
            case R.id.linearLayout_2:
                field = "money";
//                textView_1.setTextColor(getResources().getColor(R.color.black3));
//                textView_2.setTextColor(getResources().getColor(R.color.green));
//                textView_1.setCompoundDrawables(null, null, drawable2, null);
//                textView_2.setCompoundDrawables(null, null, drawable1, null);
                showPopupWindow2(pop_view);
                break;

        }
    }

    private void changeUI() {
         /*Drawable drawable1 = getResources().getDrawable(R.mipmap.down_green);//选中-蓝色
        Drawable drawable2 = getResources().getDrawable(R.mipmap.down_black);//未选-灰色
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());*/
        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.black1));
            textView2.setTextColor(getResources().getColor(R.color.black3));
//                textView1.setCompoundDrawables(null, null, drawable1, null);
//                textView2.setCompoundDrawables(null, null, drawable2, null);
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);

            requestServer();
        } else {
            textView1.setTextColor(getResources().getColor(R.color.black3));
            textView2.setTextColor(getResources().getColor(R.color.black1));
//                textView1.setCompoundDrawables(null, null, drawable2, null);
//                textView2.setCompoundDrawables(null, null, drawable1, null);
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);

            requestServer();
        }
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        if (type == 1) {
            page1 = 1;
            String string = "?page=" + page1//当前页号
                    + "&count=" + "10"//页面行数
                    + "&sort=" + sort
                    + "&field=" + field
                    + "&token=" + localUserInfo.getToken();
            RequestDRVTList1(string);
        } else {
            page2 = 1;
            String string = "?page=" + page2//当前页号
                    + "&count=" + "10"//页面行数
                    + "&sort=" + sort
                    + "&field=" + field
                    + "&token=" + localUserInfo.getToken();
            RequestDRVTList2(string);
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }

    private void showPopupWindow1(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(USDTJiaoYiActivity.this).inflate(
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
        list.add(getString(R.string.app_type_shengxu));
        list.add(getString(R.string.app_type_jiangxu));
        final Pop_ListAdapter adapter = new Pop_ListAdapter(USDTJiaoYiActivity.this, list);
        adapter.setSelectItem(i1);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                adapter.notifyDataSetChanged();
                i1 = i;
                if (i == 0) {
                    sort = "asc";
                } else {
                    sort = "desc";
                }
//                textView_1.setText(list.get(i));
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
        final View contentView = LayoutInflater.from(USDTJiaoYiActivity.this).inflate(
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

        list.add(getString(R.string.app_type_shengxu));
        list.add(getString(R.string.app_type_jiangxu));

        final Pop_ListAdapter adapter = new Pop_ListAdapter(USDTJiaoYiActivity.this, list);
        adapter.setSelectItem(i2);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                i2 = i;
                adapter.notifyDataSetChanged();
                if (i == 0) {
                    sort = "asc";
                } else {
                    sort = "desc";
                }
                /*if (i == 0) {
                    field = "usdt_cny_price";
                } else {
                    field = "money";
                }*/
//                textView_2.setText(list.get(i));
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
