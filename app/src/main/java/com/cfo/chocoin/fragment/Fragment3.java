package com.cfo.chocoin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cfo.chocoin.R;
import com.cfo.chocoin.activity.MainActivity;
import com.cfo.chocoin.activity.TransferActivity;
import com.cfo.chocoin.base.BaseFragment;
import com.cfo.chocoin.model.Fragment3Model;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.CommonUtil;
import com.cfo.chocoin.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.cfo.chocoin.net.OkHttpClientManager.IMGHOST;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 大厅
 */
public class Fragment3 extends BaseFragment {
    //    boolean isOver = false;
    Fragment3Model model;
    int type = 1;

    TextView tv_huazhuan, tv_money, tv_type, tv_yingli, tv_bucang;

    RelativeLayout ll_page1;
    private RecyclerView recyclerView;
    List<Fragment3Model.NewestContractListBean> list1 = new ArrayList<>();
    CommonAdapter<Fragment3Model.NewestContractListBean> mAdapter1;
    TextView tv_page1_1, tv_page1_2, tv_page1_3, tv_page1_4, tv_page1_5, tv_page1_6, tv_page1_7, tv_page1_8,
            tv_page1_9, tv_page1_10, tv_page1_11;
    ImageView iv_page1_1;

    RelativeLayout ll_page2;
    List<Fragment3Model.ContractTradingListBean> list2 = new ArrayList<>();
    CommonAdapter<Fragment3Model.ContractTradingListBean> mAdapter2;

    LinearLayout ll_page3;
    List<Fragment3Model.ContractCallMarginListBean> list3 = new ArrayList<>();
    CommonAdapter<Fragment3Model.ContractCallMarginListBean> mAdapter3;


    //悬浮部分
    LinearLayout linearLayout1, linearLayout2, linearLayout3, ll_type1, ll_type2, ll_type3;
    TextView textView1, textView2, textView3;
    View view1, view2, view3;

    //交易中
    RecyclerView rv_jiaoyizhong;
    TextView tv_zhongzhi;
    List<Fragment3Model.BourseMatchingListBean> list4 = new ArrayList<>();
    CommonAdapter<Fragment3Model.BourseMatchingListBean> mAdapter4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        MyLogger.i(">>>>>>>>onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        MyLogger.i(">>>>>>>>onResume");
        if (MainActivity.item == 2) {
            requestServer();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        MyLogger.i(">>>>>>>>onHiddenChanged>>>" + hidden);

        /*if (MainActivity.item == 2) {
            requestServer();
        }*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        MyLogger.i(">>>>>>>>getUserVisibleHint()>>>" + getUserVisibleHint());
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 2) {
                    requestServer();
                }
            }
        }
    }

    @Override
    protected void initView(View view) {
//        findViewByID_My(R.id.headview).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                request("?token=" + localUserInfo.getToken());
            }

            @Override
            public void onLoadmore() {
            }
        });

        ll_page1 = findViewByID_My(R.id.ll_page1);
        ll_page2 = findViewByID_My(R.id.ll_page2);
        ll_page3 = findViewByID_My(R.id.ll_page3);
        tv_money = findViewByID_My(R.id.tv_money);
        tv_type = findViewByID_My(R.id.tv_type);
        tv_yingli = findViewByID_My(R.id.tv_yingli);
        tv_bucang = findViewByID_My(R.id.tv_bucang);

        tv_huazhuan = findViewByID_My(R.id.tv_huazhuan);
        tv_huazhuan.setOnClickListener(this);


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
        ll_type1 = findViewByID_My(R.id.ll_type1);
        ll_type2 = findViewByID_My(R.id.ll_type2);
        ll_type3 = findViewByID_My(R.id.ll_type3);


        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
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

        /*//动态设置linearLayout的高度为屏幕高度的1/4
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(getActivity()) / 3;*/

        //等待中
        tv_page1_1 = findViewByID_My(R.id.tv_page1_1);
        tv_page1_2 = findViewByID_My(R.id.tv_page1_2);
        tv_page1_3 = findViewByID_My(R.id.tv_page1_3);
        tv_page1_4 = findViewByID_My(R.id.tv_page1_4);
        tv_page1_5 = findViewByID_My(R.id.tv_page1_5);
        tv_page1_6 = findViewByID_My(R.id.tv_page1_6);
        tv_page1_7 = findViewByID_My(R.id.tv_page1_7);
        tv_page1_8 = findViewByID_My(R.id.tv_page1_8);
        tv_page1_9 = findViewByID_My(R.id.tv_page1_9);
        tv_page1_10 = findViewByID_My(R.id.tv_page1_10);
        tv_page1_10.setOnClickListener(this);
        tv_page1_11 = findViewByID_My(R.id.tv_page1_11);
        tv_page1_11.setOnClickListener(this);
        iv_page1_1 = findViewByID_My(R.id.iv_page1_1);

        //交易中
        rv_jiaoyizhong = findViewByID_My(R.id.rv_jiaoyizhong);
        LinearLayoutManager mLinearLayoutManager1 = new LinearLayoutManager(getActivity());
        rv_jiaoyizhong.setLayoutManager(mLinearLayoutManager1);

        tv_zhongzhi = findViewByID_My(R.id.tv_zhongzhi);
        tv_zhongzhi.setOnClickListener(this);
    }

    @Override
    protected void initData() {
//        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();

        showProgress(true, getString(R.string.app_loading));
        request("?token=" + localUserInfo.getToken());
    }

    @Override
    protected void updateView() {

    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment3 + string, new OkHttpClientManager.ResultCallback<Fragment3Model>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                MainActivity.isOver = true;
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(final Fragment3Model response) {
                MyLogger.i(">>>>>>>>>首页" + response);
                model = response;
                //合约余额
                tv_money.setText(response.getReality_money());
                //状态
                switch (response.getResult()) {
                    case 0:
                        //持平
                        tv_type.setText(getString(R.string.fragment3_h36));
                        tv_type.setBackgroundResource(R.drawable.yuanjiao_5_huise);
                        break;
                    case 1:
                        //盈利
                        tv_type.setText(getString(R.string.fragment3_h2));
                        tv_type.setBackgroundResource(R.drawable.yuanjiao_5_lvse);
                        break;
                    case 2:
                        //亏损
                        tv_type.setText(getString(R.string.fragment3_h21));
                        tv_type.setBackgroundResource(R.drawable.yuanjiao_5_juse);
                        break;
                }
                //累计盈利
                tv_yingli.setText(response.getProfit_money());
                //累计补仓
                tv_bucang.setText(response.getCall_margin_money());

                double money = Double.valueOf(response.getReality_money());
                if (money >0){
                    switch (response.getContract_status()) {
                        case 1:
                            //等待中
                            if (response.getContract_trading() != null) {
                                ll_page1.setVisibility(View.VISIBLE);
                                ll_page2.setVisibility(View.GONE);
                                ll_page3.setVisibility(View.GONE);

                                tv_page1_1.setText(response.getContract_trading().getBourse_title());//合约交易
                                tv_page1_2.setText(response.getContract_trading().getBourse_on_title());//合约类型
                                tv_page1_3.setText(response.getContract_trading().getDirection_title());//合约方向
                                tv_page1_4.setText(response.getContract_trading().getLever() + getString(R.string.app_bei));//合约杠杆
                                tv_page1_5.setText(response.getContract_trading().getBuy_price());//买入价格
                                tv_page1_6.setText(response.getContract_trading().getShow_buy_at());//买入时间
                                tv_page1_7.setText(response.getContract_trading().getBuy_price());//平仓价格
                                tv_page1_8.setText(response.getContract_trading().getShow_sell_at());//平仓时间
                                tv_page1_9.setText(response.getContract_trading().getEarning_money());//上期盈利

                            } else {
                                ll_page1.setVisibility(View.GONE);
                                ll_page2.setVisibility(View.GONE);
                                ll_page3.setVisibility(View.VISIBLE);
                            }
                            break;
                        case 2:
                            //交易中
                            ll_page1.setVisibility(View.GONE);
                            ll_page2.setVisibility(View.VISIBLE);
                            ll_page3.setVisibility(View.GONE);

                            list4 = response.getBourse_matching_list();
                            mAdapter4 = new CommonAdapter<Fragment3Model.BourseMatchingListBean>
                                    (getActivity(), R.layout.item_fragment3_4, list4) {
                                @Override
                                protected void convert(ViewHolder holder, Fragment3Model.BourseMatchingListBean model, int position) {
                                    holder.setText(R.id.textView1, model.getBourse_title());
                                    holder.setText(R.id.textView2, model.getBourse_on_title());
                                    TextView tv3 = holder.getView(R.id.textView3);
                                    tv3.setText(model.getStatus_title());
                                    if (model.getStatus() == 1) {
                                        tv3.setTextColor(getResources().getColor(R.color.green));
                                    } else {
                                        tv3.setTextColor(getResources().getColor(R.color.red));
                                    }
                                    ImageView iv = holder.getView(R.id.imageView1);
                                    if (!model.getBourse_icon().equals(""))
                                        Glide.with(getActivity()).load(IMGHOST + model.getBourse_icon())
                                                .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                                                .into(iv);//加载图片

                                }
                            };
                            rv_jiaoyizhong.setAdapter(mAdapter4);
                            break;
                        default:
                            //已结束
                            ll_page1.setVisibility(View.GONE);
                            ll_page2.setVisibility(View.GONE);
                            ll_page3.setVisibility(View.VISIBLE);
                            break;
                    }
                }else {
                    ll_page1.setVisibility(View.GONE);
                    ll_page2.setVisibility(View.GONE);
                    ll_page3.setVisibility(View.VISIBLE);
                }

                //合约动态
                list1 = response.getNewest_contract_list();
                mAdapter1 = new CommonAdapter<Fragment3Model.NewestContractListBean>
                        (getActivity(), R.layout.item_fragment3_1, list1) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment3Model.NewestContractListBean model, int position) {
                        holder.setText(R.id.textView1, model.getMember_nickname());//昵称
                        holder.setText(R.id.textView2, model.getMoney());//合约数
                        TextView tv3 = holder.getView(R.id.textView3);
                        if (model.getYield_rate() > 0) {
                            tv3.setText("+" + model.getYield_rate());
                            tv3.setBackgroundResource(R.drawable.yuanjiao_2_lvse);

                        } else {
                            tv3.setText("+" + model.getYield_rate());
                            tv3.setBackgroundResource(R.drawable.yuanjiao_2_red);
                        }
                        ImageView iv = holder.getView(R.id.imageView1);
                        if (!model.getMember_head().equals(""))
                            Glide.with(getActivity()).load(IMGHOST + model.getMember_head())
                                    .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                                    .into(iv);//加载图片

                    }
                };
                //我的交易
                list2 = response.getContract_trading_list();
                mAdapter2 = new CommonAdapter<Fragment3Model.ContractTradingListBean>
                        (getActivity(), R.layout.item_fragment3_2, list2) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment3Model.ContractTradingListBean model, int position) {
                        holder.setText(R.id.textView1, model.getBourse_on_title());//昵称
                        holder.setText(R.id.textView2, model.getMoney());//合约数
                        TextView tv3 = holder.getView(R.id.textView3);
                        double moeny = Double.valueOf(model.getEarning_money());
                        if (moeny > 0) {
                            tv3.setText("+" + moeny);
                            tv3.setBackgroundResource(R.drawable.yuanjiao_2_lvse);

                        } else {
                            tv3.setText("+" + moeny);
                            tv3.setBackgroundResource(R.drawable.yuanjiao_2_red);
                        }

                    }
                };
                //我的补仓
                list3 = response.getContract_call_margin_list();
                mAdapter3 = new CommonAdapter<Fragment3Model.ContractCallMarginListBean>
                        (getActivity(), R.layout.item_fragment3_3, list3) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment3Model.ContractCallMarginListBean model, int position) {
                        holder.setText(R.id.textView1, model.getMoney());
                        holder.setText(R.id.textView2, model.getShow_created_at());
                    }
                };

                changeUI();
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
            case R.id.linearLayout3:
                type = 3;
                changeUI();
                break;

            case R.id.tv_huazhuan:
            case R.id.tv_page1_10:
                //划转
                CommonUtil.gotoActivity(getActivity(), TransferActivity.class, false);
                break;
            case R.id.tv_page1_11:
                //转出
                showToast(getString(R.string.fragment3_h37),
                        getString(R.string.app_confirm),
                        getString(R.string.app_cancel),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
//                                showProgress(true, getString(R.string.app_loading));
                                requestCancel("?token=" + localUserInfo.getToken());
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
//                CommonUtil.gotoActivity(getActivity(), TransferActivity.class, false);
                break;
            case R.id.tv_zhongzhi:
                //终止
                showToast(getString(R.string.fragment3_h39),
                        getString(R.string.app_confirm),
                        getString(R.string.app_cancel),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
//                                showProgress(true, getString(R.string.app_loading));
                                requestCancel("?token=" + localUserInfo.getToken());
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                break;
        }
    }

    private void changeUI() {
        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.white));
            textView2.setTextColor(getResources().getColor(R.color.white2));
            textView3.setTextColor(getResources().getColor(R.color.white2));

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.INVISIBLE);

            ll_type1.setVisibility(View.VISIBLE);
            ll_type2.setVisibility(View.GONE);
            ll_type3.setVisibility(View.GONE);

            if (list1.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter1);
//                mAdapter1.notifyDataSetChanged();
            } else {
                showEmptyPage();
            }

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.white2));
            textView2.setTextColor(getResources().getColor(R.color.white));
            textView3.setTextColor(getResources().getColor(R.color.white2));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            view3.setVisibility(View.INVISIBLE);

            ll_type1.setVisibility(View.GONE);
            ll_type2.setVisibility(View.VISIBLE);
            ll_type3.setVisibility(View.GONE);

            if (list2.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter2);
//                mAdapter2.notifyDataSetChanged();
            } else {
                showEmptyPage();
            }
        } else if (type == 3) {
            textView1.setTextColor(getResources().getColor(R.color.white2));
            textView2.setTextColor(getResources().getColor(R.color.white2));
            textView3.setTextColor(getResources().getColor(R.color.white));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.VISIBLE);

            ll_type1.setVisibility(View.GONE);
            ll_type2.setVisibility(View.GONE);
            ll_type3.setVisibility(View.VISIBLE);

            if (list3.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter3);
//                mAdapter3.notifyDataSetChanged();
            } else {
                showEmptyPage();
            }
        }

    }

    private void requestCancel(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Transfer_Cancel + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(String response) {
                MyLogger.i(">>>>>>>>>终止合约" + response);
                showToast(getString(R.string.fragment3_h38));
                requestServer();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
