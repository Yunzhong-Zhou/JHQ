package com.fone.fone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fone.fone.R;
import com.fone.fone.activity.JoinDetailActivity;
import com.fone.fone.activity.JoinListActivity;
import com.fone.fone.activity.LeaderboardActivity;
import com.fone.fone.activity.MainActivity;
import com.fone.fone.base.BaseFragment;
import com.fone.fone.model.Fragment3Model;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.fone.fone.net.OkHttpClientManager.IMGHOST;


/**
 * Created by Mr.Z on 2016/1/6.
 * 拼团
 */
public class Fragment3 extends BaseFragment {
    //    boolean isOver = false;
    Fragment3Model model;
    int type = 1;

    private RecyclerView rv_join, recyclerView;
    List<Fragment3Model.ChangeGameListBean> list1 = new ArrayList<>();
    CommonAdapter<Fragment3Model.ChangeGameListBean> mAdapter1;

    List<Fragment3Model.MemberListBean> list2 = new ArrayList<>();
    CommonAdapter<Fragment3Model.MemberListBean> mAdapter2;

    List<Fragment3Model.ChangeGameListBean> list_join = new ArrayList<>();
    CommonAdapter<Fragment3Model.ChangeGameListBean> mAdapter_join;


    //悬浮部分
    LinearLayout linearLayout1, linearLayout2;
    TextView textView1, textView2;
    View view1, view2;

    TextView tv_title, tv_tab1, tv_tab2, tv_tab3, tv_tab4, tv_join, tv_more;

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
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
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

        //悬浮部分
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);


        rv_join = findViewByID_My(R.id.rv_join);
        rv_join.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);

        tv_title = findViewByID_My(R.id.tv_title);
        tv_tab1 = findViewByID_My(R.id.tv_tab1);
        tv_tab2 = findViewByID_My(R.id.tv_tab2);
        tv_tab3 = findViewByID_My(R.id.tv_tab3);
        tv_tab4 = findViewByID_My(R.id.tv_tab4);
        tv_join = findViewByID_My(R.id.tv_join);
        tv_join.setOnClickListener(this);
        tv_more = findViewByID_My(R.id.tv_more);
        tv_more.setOnClickListener(this);

        //设置图片宽度为屏幕宽度，高度自适应-android:adjustViewBounds="true"
        /*ImageView iv_head_bg = findViewByID_My(R.id.iv_head_bg);
        ViewGroup.LayoutParams lp = iv_head_bg.getLayoutParams();
        lp.width = CommonUtil.getScreenWidth(getActivity());
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        iv_head_bg.setLayoutParams(lp);
        iv_head_bg.setMaxWidth(lp.width);
        iv_head_bg.setMaxHeight(lp.height);*/

    }

    @Override
    protected void initData() {
//        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        if (isAdded()) {
            showProgress(true, getString(R.string.app_loading));
            request("?token=" + localUserInfo.getToken());
        }
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
//                showContentPage();
                if (response != null) {
                    model = response;
                    //加入拼团
                    list_join = response.getChange_game_list();
                    mAdapter_join = new CommonAdapter<Fragment3Model.ChangeGameListBean>
                            (getActivity(), R.layout.item_fragment3_join, list_join) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment3Model.ChangeGameListBean model, int position) {
                           /* holder.setText(R.id.textView1, model.getMoney());
                            holder.setText(R.id.textView2, model.getShow_created_at());*/
                        }
                    };
                    rv_join.setAdapter(mAdapter_join);

                    //完成拼团
                    list1 = response.getChange_game_list();
                    mAdapter1 = new CommonAdapter<Fragment3Model.ChangeGameListBean>
                            (getActivity(), R.layout.item_fragment3_1, list1) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment3Model.ChangeGameListBean model, int position) {
                            /*holder.setText(R.id.tv_num, model.getMember_nickname());//昵称
                            holder.setText(R.id.textView2, model.getMoney());//合约数
                            TextView tv3 = holder.getView(R.id.textView3);
                            if (model.getProfit_money() > 0) {
                                tv3.setText("+" + model.getProfit_money());
                                tv3.setBackgroundResource(R.drawable.yuanjiao_0_lvse);

                            } else if (model.getProfit_money() < 0) {
                                tv3.setText("-" + model.getProfit_money());
                                tv3.setBackgroundResource(R.drawable.yuanjiao_0_red);
                            } else {
                                tv3.setText("" + model.getProfit_money());
                                tv3.setBackgroundResource(R.drawable.yuanjiao_0_red);
                            }
                            ImageView iv = holder.getView(R.id.imageView1);
                            if (!model.getMember_head().equals(""))
                                Glide.with(getActivity()).load(IMGHOST + model.getMember_head())
                                        .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                                        .into(iv);//加载图片*/

                        }
                    };
                    mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putString("id", list1.get(i).getPeriod());
                            CommonUtil.gotoActivityWithData(getActivity(), JoinDetailActivity.class, bundle);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                            return false;
                        }
                    });
                    //算力排行
                    list2 = response.getMember_list();
                    mAdapter2 = new CommonAdapter<Fragment3Model.MemberListBean>
                            (getActivity(), R.layout.item_fragment3_2, list2) {
                        @Override
                        protected void convert(ViewHolder holder, Fragment3Model.MemberListBean model, int position) {
                            TextView tv_num = holder.getView(R.id.tv_num);
                            switch (position) {
                                case 0:
                                    //第一名
                                    tv_num.setBackgroundResource(R.mipmap.ic_paihang_1);
                                    tv_num.setText("");
                                    break;
                                case 1:
                                    //第二名
                                    tv_num.setBackgroundResource(R.mipmap.ic_paihang_2);
                                    tv_num.setText("");
                                    break;
                                case 2:
                                    //第三名
                                    tv_num.setBackgroundResource(R.mipmap.ic_paihang_3);
                                    tv_num.setText("");
                                    break;
                                default:
                                    tv_num.setBackgroundResource(R.color.transparent);
                                    tv_num.setText(position + 1 + "");
                                    break;
                            }

                            holder.setText(R.id.tv_name, model.getNickname());//昵称
                            holder.setText(R.id.tv_money, model.getHashrate() + "TB");//累计奖金
                            holder.setText(R.id.tv_pinzhong, getString(R.string.fragment3_h28) + model.getChange_game_win_time() + getString(R.string.app_ci));
                            holder.setText(R.id.tv_kuangji, getString(R.string.fragment3_h29) + model.getChange_game_win_time() + getString(R.string.app_tai));

                            ImageView imageView = holder.getView(R.id.iv_head);
                            Glide.with(getActivity())
                                    .load(IMGHOST + model.getHead())
                                    .centerCrop()
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView);//加载图片

                        }
                    };

                    changeUI();
                    hideProgress();

                    MainActivity.isOver = true;
                }
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
            case R.id.tv_more:
                if (type == 1) {
                    CommonUtil.gotoActivity(getActivity(), JoinListActivity.class);
                } else {
                    CommonUtil.gotoActivity(getActivity(), LeaderboardActivity.class);
                }
                break;
            case R.id.tv_join:
                //加入拼团
                showToast(getString(R.string.fragment3_h24)
                        , getString(R.string.app_confirm)
                        , getString(R.string.app_cancel), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                showProgress(true, getString(R.string.app_loading1));
                                HashMap<String, String> params = new HashMap<>();
                                params.put("hk", model.getHk());//金额
                                params.put("change_game_id", model.getChange_game().getId());
                                params.put("token", localUserInfo.getToken());
//                            params.put("hk", model.getHk());
                                RequestJoin(params);
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                /*dialog.contentView(R.layout.dialog_fragment3)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.CENTER)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.CENTER)
                        .dimAmount(0.8f)
                        .show();
                TextView textView1 = dialog.findViewById(R.id.textView1);
                dialog.findViewById(R.id.textView2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("id", "");
                        CommonUtil.gotoActivityWithData(getActivity(), JoinDetailActivity.class, bundle1, false);
                    }
                });
                dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });*/
                break;

        }
    }

    private void changeUI() {
        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.black1));
            textView2.setTextColor(getResources().getColor(R.color.black3));

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);

            if (list1.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter1);
                mAdapter1.notifyDataSetChanged();

                tv_more.setVisibility(View.VISIBLE);
            } else {
                showEmptyPage();
                tv_more.setVisibility(View.GONE);
            }

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.black3));
            textView2.setTextColor(getResources().getColor(R.color.black1));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);

            if (list2.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter2);
                mAdapter2.notifyDataSetChanged();
                tv_more.setVisibility(View.VISIBLE);
            } else {
                showEmptyPage();
                tv_more.setVisibility(View.GONE);
            }
        }

    }

    //加入拼团
    private void RequestJoin(Map<String, String> params) {
        OkHttpClientManager.postAsyn(getActivity(), URLs.Join, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

                requestServer();
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                myToast(getString(R.string.fragment3_h25));
                requestServer();
            }
        }, true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
