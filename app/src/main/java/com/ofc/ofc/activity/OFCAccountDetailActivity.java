package com.ofc.ofc.activity;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.adapter.Pop_ListAdapter;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.AccountDetailModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.ofc.ofc.view.FixedPopupWindow;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zyz on 2019/5/28.
 * 我的钱包
 */
public class OFCAccountDetailActivity extends BaseActivity {
    int type = 3;
    AccountDetailModel model;
    private RecyclerView recyclerView;
    List<AccountDetailModel.EarningListBean> list1 = new ArrayList<>();
    CommonAdapter<AccountDetailModel.EarningListBean> mAdapter1;

    List<AccountDetailModel.ExpenditureListBean> list2 = new ArrayList<>();
    CommonAdapter<AccountDetailModel.ExpenditureListBean> mAdapter2;

    List<AccountDetailModel.InvestListBean> list3 = new ArrayList<>();
    CommonAdapter<AccountDetailModel.InvestListBean> mAdapter3;
    //头部一
//    View headerView1;
    RelativeLayout head1_relativeLayout;
    TextView head1_textView1, head1_textView2, head1_textView3, head1_textView4, head1_textView5;

    private LinearLayout pop_view;
    TextView tv_paixu, tv_zhuangtai;
    String money_type = "", status = "";
    int i1 = 0;
    int i2 = 0;

    //悬浮部分
    LinearLayout invis;
    LinearLayout linearLayout1, linearLayout2, linearLayout3;
    TextView textView1, textView2, textView3;
    View view1, view2, view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofcaccountdetail);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {
//        findViewByID_My(R.id.titleView).setPadding(0, (int) CommonUtil.getStatusBarHeight(AccountDetailActivity.this), 0, 0);
        CommonUtil.setMargins(findViewByID_My(R.id.titleView), 0, (int) CommonUtil.getStatusBarHeight(OFCAccountDetailActivity.this), 0, 0);

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
        invis = findViewByID_My(R.id.invis);
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

        pop_view = findViewByID_My(R.id.pop_view);
        tv_paixu = findViewByID_My(R.id.tv_paixu);
        tv_zhuangtai = findViewByID_My(R.id.tv_zhuangtai);


        head1_relativeLayout = findViewByID_My(R.id.head1_relativeLayout);

        head1_textView1 = findViewByID_My(R.id.head1_textView1);
        head1_textView2 = findViewByID_My(R.id.head1_textView2);
        head1_textView3 = findViewByID_My(R.id.head1_textView3);
        head1_textView4 = findViewByID_My(R.id.head1_textView4);
        head1_textView5 = findViewByID_My(R.id.head1_textView5);
        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(OFCAccountDetailActivity.this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        /*//listview 滑动监听
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mLinearLayoutManager.findFirstVisibleItemPosition() >= 1) {
                    invis.setVisibility(View.VISIBLE);
                    headerView2.setVisibility(View.GONE);
                } else {
                    invis.setVisibility(View.GONE);
                    headerView2.setVisibility(View.VISIBLE);
                }
            }
        });*/
        //动态设置linearLayout的高度为屏幕高度的1/2
        ViewGroup.LayoutParams lp = head1_relativeLayout.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(OFCAccountDetailActivity.this) * 384 / 895;
    }

    @Override
    protected void initData() {

    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.OFCAccountDetail + string, new OkHttpClientManager.ResultCallback<AccountDetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(AccountDetailModel response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>账户详情" + response);
                model = response;

                head1_textView1.setText(response.getOfc_amount_money());//总资产
//                head1_textView2.setText(response.getOfc_invest_money());//解冻
                head1_textView2.setText(response.getOfc_usable_money());//可用
                head1_textView3.setText(response.getInterest_money());//分红
                head1_textView4.setText(response.getOfc_commission_money());//佣金
                head1_textView5.setText(response.getAppreciation() + "%");//增值

                list3 = response.getInvest_list();
                mAdapter3 = new CommonAdapter<AccountDetailModel.InvestListBean>
                        (OFCAccountDetailActivity.this, R.layout.item_ofcaccountdetail, list3) {
                    @Override
                    protected void convert(ViewHolder holder, final AccountDetailModel.InvestListBean model, int position) {
                        holder.setText(R.id.textView1, model.getType_title());//类型
                        holder.setText(R.id.textView2, model.getOfc_money());//币数量
                        holder.setText(R.id.textView3, model.getOfc_price());//买入价
                        holder.setText(R.id.textView4, model.getAppreciation() + "%");//已增值
                        holder.setText(R.id.textView5, model.getUnfreeze_at());//时间
                        holder.setText(R.id.textView6, model.getInterest_money());//已分红
                    }
                };
                mAdapter3.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", list3.get(i).getId());
                        CommonUtil.gotoActivityWithData(OFCAccountDetailActivity.this, FenHongListActivity.class, bundle, false);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                        return false;
                    }
                });

                list1 = response.getEarning_list();
                mAdapter1 = new CommonAdapter<AccountDetailModel.EarningListBean>
                        (OFCAccountDetailActivity.this, R.layout.item_accountdetail, list1) {
                    @Override
                    protected void convert(ViewHolder holder, final AccountDetailModel.EarningListBean model, int position) {
                        holder.setText(R.id.textView1, model.getTitle() + "：+" + model.getMoney());//标题
//                        holder.setText(R.id.textView2, getString(R.string.recharge_h21) + model.get);//流水号
                        holder.setText(R.id.textView3, getString(R.string.recharge_h22) + model.getCreated_at());//时间
                        holder.setText(R.id.textView4, model.getStatus());//状态
                    }
                };

                list2 = response.getExpenditure_list();
                mAdapter2 = new CommonAdapter<AccountDetailModel.ExpenditureListBean>
                        (OFCAccountDetailActivity.this, R.layout.item_accountdetail, list2) {
                    @Override
                    protected void convert(ViewHolder holder, final AccountDetailModel.ExpenditureListBean model, int position) {
                        holder.setText(R.id.textView1, model.getTitle() + "：-" + model.getMoney());//标题
//                        holder.setText(R.id.textView2, getString(R.string.recharge_h21) + model.getId());//流水号
                        holder.setText(R.id.textView3, getString(R.string.recharge_h22) + model.getCreated_at());//时间
                        holder.setText(R.id.textView4, model.getStatus());//状态
                    }
                };
                changeUI();

                hideProgress();
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
            case R.id.left_btn:
                finish();
                break;
            case R.id.linearLayout3:
                type = 3;
                changeUI();
                break;
            case R.id.linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.linearLayout2:
                type = 2;
                changeUI();
                break;

            case R.id.head1_linearLayout1:
                //充值
                CommonUtil.gotoActivity(this, OFCRechargeActivity.class, false);
                /*Bundle bundle = new Bundle();
                bundle.putInt("item", 3);
                CommonUtil.gotoActivityWithFinishOtherAllAndData(this, MainActivity.class, bundle, true);*/
                break;
            case R.id.head1_linearLayout2:
                //划转
//                CommonUtil.gotoActivity(this, TransferActivity.class, false);
                //分红
                //关闭连接
                /*WebSocketManager.getInstance().close();
                CommonUtil.gotoActivity(this, AddFenHongActivity.class, false);*/

//                Bundle bundle1 = new Bundle();
//                bundle1.putInt("item", 0);
//                CommonUtil.gotoActivityWithFinishOtherAllAndData(this, MainActivity.class,bundle1, true);

//                CommonUtil.gotoActivity(this, FenHongOFCActivity.class, false);

                //质押
                CommonUtil.gotoActivity(this, ZhiYaOFCActivity.class, false);
                break;
            case R.id.head1_linearLayout4:
                //回购
                CommonUtil.gotoActivity(this, HuiGouOFCActivity.class, false);
                break;

            case R.id.head1_linearLayout3:
                //提现
                CommonUtil.gotoActivity(this, OFCTakeCashActivity.class, false);
                break;
            case R.id.head1_textView5:
                //币地址
//                Bundle bundle1 = new Bundle();
//                bundle1.putString("addr",model1.getTop_up_usdt_wallet_addr());
//                CommonUtil.gotoActivityWithData(this, AddressActivity.class, bundle1,false);
                break;
            case R.id.ll_paixu:
                tv_paixu.setTextColor(getResources().getColor(R.color.green));
                tv_paixu.setTextColor(getResources().getColor(R.color.black3));
                tv_zhuangtai.setCompoundDrawables(null, null, drawable1, null);
                tv_zhuangtai.setCompoundDrawables(null, null, drawable2, null);
                showPopupWindow1(pop_view);
                break;
            case R.id.ll_zhuangtai:
                tv_paixu.setTextColor(getResources().getColor(R.color.black3));
                tv_paixu.setTextColor(getResources().getColor(R.color.green));
                tv_zhuangtai.setCompoundDrawables(null, null, drawable2, null);
                tv_zhuangtai.setCompoundDrawables(null, null, drawable1, null);
                showPopupWindow2(pop_view);
                break;
        }
    }

    private void changeUI() {
        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.green));
            textView2.setTextColor(getResources().getColor(R.color.black4));
            textView3.setTextColor(getResources().getColor(R.color.black4));
//            head2_textView1.setTextColor(getResources().getColor(R.color.blue));
//            head2_textView2.setTextColor(getResources().getColor(R.color.black4));

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.INVISIBLE);
//            head2_view1.setVisibility(View.VISIBLE);
//            head2_view2.setVisibility(View.INVISIBLE);

            if (list1.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter1);
                mAdapter1.notifyDataSetChanged();
            } else {
                showEmptyPage();
            }

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.black4));
            textView2.setTextColor(getResources().getColor(R.color.green));
            textView3.setTextColor(getResources().getColor(R.color.black4));
//            head2_textView1.setTextColor(getResources().getColor(R.color.black4));
//            head2_textView2.setTextColor(getResources().getColor(R.color.blue));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            view3.setVisibility(View.INVISIBLE);
//            head2_view1.setVisibility(View.INVISIBLE);
//            head2_view2.setVisibility(View.VISIBLE);

            if (list2.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter2);
                mAdapter2.notifyDataSetChanged();
            } else {
                showEmptyPage();
            }
        } else if (type == 3) {
            textView1.setTextColor(getResources().getColor(R.color.black4));
            textView2.setTextColor(getResources().getColor(R.color.black4));
            textView3.setTextColor(getResources().getColor(R.color.green));
//            head2_textView1.setTextColor(getResources().getColor(R.color.black4));
//            head2_textView2.setTextColor(getResources().getColor(R.color.blue));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.VISIBLE);
//            head2_view1.setVisibility(View.INVISIBLE);
//            head2_view2.setVisibility(View.VISIBLE);

            if (list3.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter3);
                mAdapter3.notifyDataSetChanged();
            } else {
                showEmptyPage();
            }
        }
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        String string = "?token=" + localUserInfo.getToken();
        Request(string);
    }

    @Override
    protected void updateView() {
//        titleView.setTitle(getString(R.string.qianbao_h1));
        titleView.setVisibility(View.GONE);
    }

    private void showPopupWindow1(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(OFCAccountDetailActivity.this).inflate(
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
        list.add(getString(R.string.app_type_USDT));
        list.add(getString(R.string.app_type_AY));

        final Pop_ListAdapter adapter = new Pop_ListAdapter(OFCAccountDetailActivity.this, list);
        adapter.setSelectItem(i1);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                adapter.notifyDataSetChanged();
                i1 = i;
                if (i == 0) {
                    money_type = "";
                } else {
                    money_type = i + "";
                }
                tv_paixu.setText(list.get(i));
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
        final View contentView = LayoutInflater.from(OFCAccountDetailActivity.this).inflate(
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
        list.add(getString(R.string.app_type_daishenhe));
        list.add(getString(R.string.app_type_yitongguo));
        list.add(getString(R.string.app_type_weitongguo));
        final Pop_ListAdapter adapter = new Pop_ListAdapter(OFCAccountDetailActivity.this, list);
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
                tv_zhuangtai.setText(list.get(i));
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
