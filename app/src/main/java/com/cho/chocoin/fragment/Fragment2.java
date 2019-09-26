package com.cho.chocoin.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cho.chocoin.R;
import com.cho.chocoin.activity.MainActivity;
import com.cho.chocoin.activity.MyQuKuaiActivity;
import com.cho.chocoin.activity.SelectAddressActivity;
import com.cho.chocoin.activity.SetTransactionPasswordActivity;
import com.cho.chocoin.base.BaseFragment;
import com.cho.chocoin.model.Fragment2Model;
import com.cho.chocoin.model.GetBuyModel;
import com.cho.chocoin.net.OkHttpClientManager;
import com.cho.chocoin.net.URLs;
import com.cho.chocoin.utils.CommonUtil;
import com.cho.chocoin.utils.MyLogger;
import com.bumptech.glide.Glide;
import com.cy.dialog.BaseDialog;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cho.chocoin.net.OkHttpClientManager.IMGHOST;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 区块
 */
public class Fragment2 extends BaseFragment {
    Fragment2Model model;
    int type = 1;
    private RecyclerView recyclerView;
    List<Fragment2Model.NewestBlockAllBean> list1 = new ArrayList<>();
    CommonAdapter<Fragment2Model.NewestBlockAllBean> mAdapter1;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper1;
    List<Fragment2Model> list2 = new ArrayList<>();
    CommonAdapter<Fragment2Model> mAdapter2;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper2;

    //头部一
    View headerView1;
    TextView head1_textView1, head1_textView2, head1_textView3, head1_textView4,
            head1_textView5, head1_textView6, head1_textView7;
    LinearLayout linearLayout_add1;
    LinearLayout head1_linearLayout1, linearLayout_empty;

    //头部二-需要悬浮的布局
    View headerView2;
    LinearLayout head2_linearLayout1, head2_linearLayout2;
    TextView head2_textView1, head2_textView2;
    View head2_view1, head2_view2;

    //头部三-说明
    View headerView3;
    TextView head3_textView;
    //悬浮部分
    LinearLayout invis;
    LinearLayout linearLayout1, linearLayout2;
    TextView textView1, textView2;
    View view1, view2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        StatusBarUtil.setTransparent(getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        /*if (MainActivity.item == 1) {
            requestServer();
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.item == 1) {
            requestServer();
        }
    }

    /*@Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (MainActivity.item == 1) {
            requestServer();
        }
    }*/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
            if (MainActivity.item == 1) {
                requestServer();
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
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);

        //头部一
        headerView1 = View.inflate(getActivity(), R.layout.head_fragment2_1, null);
        head1_textView1 = headerView1.findViewById(R.id.head1_textView1);
        head1_textView2 = headerView1.findViewById(R.id.head1_textView2);
        head1_textView3 = headerView1.findViewById(R.id.head1_textView3);
        head1_textView4 = headerView1.findViewById(R.id.head1_textView4);
        head1_textView5 = headerView1.findViewById(R.id.head1_textView5);
        head1_textView6 = headerView1.findViewById(R.id.head1_textView6);
        head1_textView7 = headerView1.findViewById(R.id.head1_textView7);
        head1_textView2.setOnClickListener(this);
        head1_textView7.setOnClickListener(this);
        linearLayout_add1 = headerView1.findViewById(R.id.linearLayout_add1);
        head1_linearLayout1 = headerView1.findViewById(R.id.head1_linearLayout1);
        linearLayout_empty = headerView1.findViewById(R.id.linearLayout_empty);
        head1_linearLayout1.setOnClickListener(this);

        //头部二
        headerView2 = View.inflate(getActivity(), R.layout.head_fragment2_2, null);
        head2_linearLayout1 = headerView2.findViewById(R.id.head2_linearLayout1);
        head2_linearLayout2 = headerView2.findViewById(R.id.head2_linearLayout2);
        head2_linearLayout1.setOnClickListener(this);
        head2_linearLayout2.setOnClickListener(this);
        head2_textView1 = headerView2.findViewById(R.id.head2_textView1);
        head2_textView2 = headerView2.findViewById(R.id.head2_textView2);
        head2_view1 = headerView2.findViewById(R.id.head2_view1);
        head2_view2 = headerView2.findViewById(R.id.head2_view2);

        //头部三
        headerView3 = View.inflate(getActivity(), R.layout.head_fragment2_3, null);
        head3_textView = headerView3.findViewById(R.id.head3_textView);

        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);
        //listview 滑动监听
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
        });

    }

    @Override
    protected void initData() {
//        requestServer();

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

    private void Request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment2 + string, new OkHttpClientManager.ResultCallback<Fragment2Model>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(final Fragment2Model response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>区块" + response);
                //待激活
                head1_textView1.setText(response.getBlock_wait_active_money());
                //总区块
                head1_textView3.setText(response.getBlock_money());
                //已激活
                head1_textView4.setText(response.getBlock_active_money());
                //已奖励
                head1_textView5.setText(response.getBlock_award_money());
                //未奖励
                head1_textView6.setText(response.getWait_block_award_money());

                //参与记录
                linearLayout_add1.removeAllViews();
                if (response.getMy_block_list().size() > 0) {
                    linearLayout_add1.setVisibility(View.VISIBLE);
                    linearLayout_empty.setVisibility(View.GONE);
                    for (int i = 0; i < response.getMy_block_list().size(); i++) {
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View view = inflater.inflate(R.layout.item_myqukuai, null, false);
                        view.setLayoutParams(lp);

                        //进度条
                        RatingBar add_ratingBar = view.findViewById(R.id.add_ratingBar);
                        float max = Float.valueOf(response.getMy_block_list().get(i).getMoney());

                        MyLogger.i(">>>>最大>" + (int) max + ">>>>>当前>" + Float.valueOf(response.getMy_block_list().get(i).getHas_money()));
                        add_ratingBar.setNumStars((int) max);
                        add_ratingBar.setRating(Float.valueOf(response.getMy_block_list().get(i).getHas_money()));

                        LinearLayout add_ll1 = view.findViewById(R.id.add_ll1);
                        TextView add_tv1 = view.findViewById(R.id.add_tv1);
                        TextView add_tv2 = view.findViewById(R.id.add_tv2);
                        TextView add_tv3 = view.findViewById(R.id.add_tv3);
                        TextView add_tv4 = view.findViewById(R.id.add_tv4);
                        TextView add_tv5 = view.findViewById(R.id.add_tv5);
                        if (response.getMy_block_list().get(i).getStatus() == 2) {
                            //已激活
                            add_ll1.setBackgroundResource(R.drawable.yuanjiaobiankuang_10_huise);
                            add_tv3.setTextColor(getResources().getColor(R.color.green));
                        } else {
                            add_ll1.setBackgroundResource(R.drawable.yuanjiao_10_huise);
                            add_tv3.setTextColor(getResources().getColor(R.color.black1));
                        }
                        add_tv1.setText(getString(R.string.fragment2_h21) + (i + 1));
                        add_tv2.setText(response.getMy_block_list().get(i).getCreated_at());
                        add_tv3.setText(response.getMy_block_list().get(i).getStatus_title());
                        add_tv4.setText(response.getMy_block_list().get(i).getMoney());
                        add_tv5.setText(response.getMy_block_list().get(i).getHas_money());

                        linearLayout_add1.addView(view);
                    }
                } else {
                    linearLayout_add1.setVisibility(View.GONE);
                    linearLayout_empty.setVisibility(View.VISIBLE);
                }
                //激活动态
                list1 = response.getNewest_block_all();
                mAdapter1 = new CommonAdapter<Fragment2Model.NewestBlockAllBean>
                        (getActivity(), R.layout.item_fragment2, list1) {
                    @Override
                    protected void convert(ViewHolder holder, final Fragment2Model.NewestBlockAllBean model, int position) {
                        holder.setText(R.id.textView1, model.getMember_nickname());
                        holder.setText(R.id.textView2, model.getMoney() + getString(R.string.app_ge));
                        holder.setText(R.id.textView3, model.getCreated_at());
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        if (!model.getMember_head().equals(""))
                            Glide.with(getActivity())
                                    .load(IMGHOST + model.getMember_head())
                                    .centerCrop()
//                                    .placeholder(R.mipmap.headimg)//加载站位图
//                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片
                        else
                            imageView1.setImageResource(R.mipmap.headimg);

                    }
                };
                mHeaderAndFooterWrapper1 = new HeaderAndFooterWrapper(mAdapter1);
                mHeaderAndFooterWrapper1.addHeaderView(headerView1);
                mHeaderAndFooterWrapper1.addHeaderView(headerView2);


                //说明
//                head3_textView.setText(response.getBlock_explain());
                if (!response.getBlock_explain().equals("")){
                    head3_textView.setText(response.getBlock_explain().replace("\\n", "\n"));
                }
                mAdapter2 = new CommonAdapter<Fragment2Model>
                        (getActivity(), R.layout.item_fragment2, list2) {
                    @Override
                    protected void convert(ViewHolder holder, final Fragment2Model model, int position) {
                /*holder.setText(R.id.textView1, model.getMember_nickname());
                holder.setText(R.id.textView2, "¥ " + model.getMoney());
                holder.setText(R.id.textView3, model.getCreated_at());
                holder.setText(R.id.textView4, model.getPackage_title());
                ImageView imageView1 = holder.getView(R.id.imageView1);
                if (!model.getMember_head().equals(""))
                    Glide.with(getActivity()).load(IMGHOST + model.getMember_head())
                            .centerCrop().into(imageView1);//加载图片
                else
                    imageView1.setImageResource(R.mipmap.headimg);*/

                    }
                };
                mHeaderAndFooterWrapper2 = new HeaderAndFooterWrapper(mAdapter2);
                mHeaderAndFooterWrapper2.addHeaderView(headerView1);
                mHeaderAndFooterWrapper2.addHeaderView(headerView2);
                mHeaderAndFooterWrapper2.addHeaderView(headerView3);


                changeUI();

                hideProgress();
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
            case R.id.head2_linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.head2_linearLayout2:
                type = 2;
                changeUI();
                break;
            case R.id.head1_textView2:
                //购买算力
//                CommonUtil.gotoActivity(getActivity(), BuyComputingPowerActivity.class, false);
                //竞猜
                MainActivity.item = 2;
                MainActivity.navigationBar.selectTab(2);

                break;
            case R.id.head1_textView7:
                //查看所有
                CommonUtil.gotoActivity(getActivity(), MyQuKuaiActivity.class, false);
                break;
            case R.id.head1_linearLayout1:
                //激活区块
                showProgress(true, getString(R.string.app_loading2));
                String string = "?token=" + localUserInfo.getToken();
                getBuy(string);
                break;
        }
    }

    private void getBuy(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.BuyQuKuai + string, new OkHttpClientManager.ResultCallback<GetBuyModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(final GetBuyModel response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>获取余额" + response);

                dialog.contentView(R.layout.dialog_jihuoqukuai)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.CENTER)
                        .canceledOnTouchOutside(true)
                        .dimAmount(0.8f)
                        .show();
                TextView textView1 = dialog.findViewById(R.id.textView1);
                final TextView textView2 = dialog.findViewById(R.id.textView2);
                final EditText editText = dialog.findViewById(R.id.editText);

                textView1.setText(getString(R.string.fragment2_h16) + response.getCommon_usable_money());
                editText.setHint(getString(R.string.fragment2_h17)
                        + "(" + response.getMin_block_money() + "-" +
                        response.getMax_block_money() + ")");
                textView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!editText.getText().toString().trim().equals("")) {
                            //激活区块
                            textView2.setClickable(false);
                            showProgress(true, getString(R.string.app_loading1));
                            HashMap<String, String> params = new HashMap<>();
                            params.put("money", editText.getText().toString().trim());//提现金额
                            params.put("token", localUserInfo.getToken());
                            RequestBuy(params, textView2);//激活区块
                        } else {
                            showToast(getString(R.string.fragment2_h17));
                        }
                    }
                });

                dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void RequestBuy(Map<String, String> params, final TextView textView) {
        OkHttpClientManager.postAsyn(getActivity(), URLs.BuyQuKuai, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                textView.setClickable(true);
                hideProgress();
                if (!info.equals("")) {
                    if (info.contains(getString(R.string.password_h1))) {
                        showToast(getString(R.string.password_h2),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(getActivity(), SetTransactionPasswordActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    } else if (info.contains(getString(R.string.password_h3))) {
                        showToast(getString(R.string.password_h4),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(getActivity(), SelectAddressActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    } else {
                        myToast(info);
                    }
                }
//                requestServer();
            }

            @Override
            public void onResponse(String response) {
                textView.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>购买区块" + response);
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    String info = jObj.getString("msg");
                    myToast(info);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                dialog.dismiss();
                requestServer();
//                Bundle bundle = new Bundle();
//                bundle.putString("id", response.getId());
//                CommonUtil.gotoActivityWithData(BuyComputingPowerActivity.this, TakeCashDetailActivity.class, bundle, true);
            }
        });
    }

    private void changeUI() {
        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.blue));
            textView2.setTextColor(getResources().getColor(R.color.black4));
            head2_textView1.setTextColor(getResources().getColor(R.color.blue));
            head2_textView2.setTextColor(getResources().getColor(R.color.black4));

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            head2_view1.setVisibility(View.VISIBLE);
            head2_view2.setVisibility(View.INVISIBLE);

            recyclerView.setAdapter(mHeaderAndFooterWrapper1);
            mHeaderAndFooterWrapper1.notifyDataSetChanged();

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.black4));
            textView2.setTextColor(getResources().getColor(R.color.blue));
            head2_textView1.setTextColor(getResources().getColor(R.color.black4));
            head2_textView2.setTextColor(getResources().getColor(R.color.blue));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            head2_view1.setVisibility(View.INVISIBLE);
            head2_view2.setVisibility(View.VISIBLE);

            recyclerView.setAdapter(mHeaderAndFooterWrapper2);
            mHeaderAndFooterWrapper2.notifyDataSetChanged();
        }
    }

}
