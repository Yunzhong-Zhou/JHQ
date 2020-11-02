package com.ofc.ofc.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.DRVTListModel1;
import com.ofc.ofc.model.DRVTListModel2;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.ofc.ofc.net.OkHttpClientManager.IMGHOST;

/**
 * Created by Mr.Z on 2020/10/22.
 * DRVT交易
 */
public class DRVTJiaoYiActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<DRVTListModel1.DrvtBuyListBean> list1 = new ArrayList<>();
    CommonAdapter<DRVTListModel1.DrvtBuyListBean> mAdapter1;
    List<DRVTListModel2.DrvtBuyListBean> list2 = new ArrayList<>();
    CommonAdapter<DRVTListModel2.DrvtBuyListBean> mAdapter2;
    //筛选
    private LinearLayout linearLayout1, linearLayout2;
    private TextView textView1, textView2;
    private View view1, view2;

    int page1 = 1, page2 = 1, type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drvtjiaoyi);
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
                            + "&token=" + localUserInfo.getToken();
                    RequestDRVTList1(string);
                } else {
                    page2 = 1;
                    String string = "?page=" + page2//当前页号
                            + "&count=" + "10"//页面行数
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
                            + "&token=" + localUserInfo.getToken();
                    RequestDRVTListMore1(string);
                } else {
                    page2 = page2 + 1;
                    //加载更多
                    String string = "?page=" + page2//当前页号
                            + "&count=" + "10"//页面行数
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
    }

    @Override
    protected void initData() {

    }

    private void RequestDRVTList1(String string) {
        OkHttpClientManager.getAsyn(DRVTJiaoYiActivity.this, URLs.DRVTJiaoYiList1 + string, new OkHttpClientManager.ResultCallback<DRVTListModel1>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(DRVTListModel1 response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>DRVT列表列表" + response);

                list1 = response.getDrvt_buy_list();
                if (list1.size() > 0) {
                    showContentPage();
                    mAdapter1 = new CommonAdapter<DRVTListModel1.DrvtBuyListBean>
                            (DRVTJiaoYiActivity.this, R.layout.item_drvtjiaoyi1, list1) {
                        @Override
                        protected void convert(ViewHolder holder, DRVTListModel1.DrvtBuyListBean model, int position) {
                            holder.setText(R.id.tv_name, model.getMember_nickname());//昵称
                            holder.setText(R.id.tv_num, String.format("%.2f", Double.valueOf(model.getAmount_money())-Double.valueOf(model.getCurrent_money())) +"");//数量
                            holder.setText(R.id.tv_price, model.getDrvt_price()+"USDT");//单价
                            holder.setText(R.id.tv_all, model.getAmount_money());//总量
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(DRVTJiaoYiActivity.this)
                                    .load(IMGHOST + model.getMember_head())
                                    .centerCrop()
//                    .placeholder(R.mipmap.headimg)//加载站位图
//                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片

                            holder.getView(R.id.tv_sell).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", model.getId());
                                    CommonUtil.gotoActivityWithData(DRVTJiaoYiActivity.this, DRVTSellActivity.class, bundle, false);
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
        OkHttpClientManager.getAsyn(DRVTJiaoYiActivity.this, URLs.DRVTJiaoYiList1 + string, new OkHttpClientManager.ResultCallback<DRVTListModel1>() {
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
            public void onResponse(DRVTListModel1 response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>DRVT列表更多" + response);

                List<DRVTListModel1.DrvtBuyListBean> list_1 = new ArrayList<>();
                list_1 = response.getDrvt_buy_list();
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
        OkHttpClientManager.getAsyn(DRVTJiaoYiActivity.this, URLs.DRVTJiaoYiList2 + string, new OkHttpClientManager.ResultCallback<DRVTListModel2>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(DRVTListModel2 response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>DRVT列表列表" + response);

                list2 = response.getDrvt_buy_list();
                if (list2.size() > 0) {
                    showContentPage();
                    mAdapter2 = new CommonAdapter<DRVTListModel2.DrvtBuyListBean>
                            (DRVTJiaoYiActivity.this, R.layout.item_drvtjiaoyi2, list2) {
                        @Override
                        protected void convert(ViewHolder holder, DRVTListModel2.DrvtBuyListBean model, int position) {
                            holder.setText(R.id.tv_time, model.getCreated_at());//时间
                            holder.setText(R.id.tv_zongliang, model.getAmount_money()+"");//总量
                            holder.setText(R.id.tv_danjia, model.getDrvt_price() + "USDT");//单价
                            holder.setText(R.id.tv_zonge, model.getUsdt_money() + "USDT");//总额
                            holder.setText(R.id.tv_yigou, model.getCurrent_money()+"");//已购

                            TextView tv_type = holder.getView(R.id.tv_type);
//                            TextView tv_zengjia = holder.getView(R.id.tv_zengjia);
                            TextView tv_quxiao = holder.getView(R.id.tv_quxiao);

                            tv_type.setText(model.getStatus_title());
                            switch (model.getStatus()) {
                                case 1:
                                    //进行中
                                    tv_type.setVisibility(View.VISIBLE);
//                                    tv_zengjia.setVisibility(View.VISIBLE);
                                    tv_quxiao.setVisibility(View.VISIBLE);

//                                    tv_type.setText(getString(R.string.app_type_jinxingzhong));
                                    tv_type.setTextColor(getResources().getColor(R.color.orange_1));

                                    break;
                                case 2:
                                    //已完成
                                    tv_type.setVisibility(View.VISIBLE);
//                                    tv_zengjia.setVisibility(View.GONE);
                                    tv_quxiao.setVisibility(View.GONE);

//                                    tv_type.setText(getString(R.string.app_type_yiwancheng));
                                    tv_type.setTextColor(getResources().getColor(R.color.green_1));

                                    break;
                                case 3:
                                    //已取消
                                    tv_type.setVisibility(View.VISIBLE);
//                                    tv_zengjia.setVisibility(View.GONE);
                                    tv_quxiao.setVisibility(View.GONE);

//                                    tv_type.setText(getString(R.string.qianbao_h114));
                                    tv_type.setTextColor(getResources().getColor(R.color.black3));

                                    break;
                            }

                            holder.getView(R.id.tv_jilu).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", model.getId());
                                    CommonUtil.gotoActivityWithData(DRVTJiaoYiActivity.this, JiaoyiListActivity.class, bundle, false);

                                }
                            });
                            holder.getView(R.id.tv_zengjia).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CommonUtil.gotoActivity(DRVTJiaoYiActivity.this, DRVTBuyActivity.class, false);
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
        OkHttpClientManager.getAsyn(DRVTJiaoYiActivity.this, URLs.DRVTJiaoYiList2 + string, new OkHttpClientManager.ResultCallback<DRVTListModel2>() {
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
            public void onResponse(DRVTListModel2 response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>DRVT列表更多" + response);
                List<DRVTListModel2.DrvtBuyListBean> list_2 = new ArrayList<>();
                list_2 = response.getDrvt_buy_list();
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
        OkHttpClientManager.getAsyn(DRVTJiaoYiActivity.this, URLs.DRVTCancel + string, new OkHttpClientManager.ResultCallback<String>() {
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
                MyLogger.i(">>>>>>>>>DRVT购买取消" + response);
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    String info = jObj.getString("msg");
                    myToast(info);
                    requestServer();
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
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.tv_buy:
                //购买
                CommonUtil.gotoActivity(DRVTJiaoYiActivity.this, DRVTBuyActivity.class, false);
                break;
            case R.id.imageView:
                //记录
                Bundle bundle = new Bundle();
                bundle.putString("id", "");
                CommonUtil.gotoActivityWithData(DRVTJiaoYiActivity.this, JiaoyiListActivity.class, bundle, false);
                break;
            case R.id.linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.linearLayout2:
                type = 2;
                changeUI();
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
                    + "&token=" + localUserInfo.getToken();
            RequestDRVTList1(string);
        } else {
            page2 = 1;
            String string = "?page=" + page2//当前页号
                    + "&count=" + "10"//页面行数
                    + "&token=" + localUserInfo.getToken();
            RequestDRVTList2(string);
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
