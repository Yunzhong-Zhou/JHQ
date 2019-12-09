package com.cfo.chocoin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cfo.chocoin.R;
import com.cfo.chocoin.activity.LeaderboardActivity;
import com.cfo.chocoin.activity.MainActivity;
import com.cfo.chocoin.activity.PastListActivity;
import com.cfo.chocoin.base.BaseFragment;
import com.cfo.chocoin.model.Fragment3Model;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.CommonUtil;
import com.cfo.chocoin.utils.GifLoadOneTimeGif;
import com.cfo.chocoin.utils.LocalUserInfo;
import com.cfo.chocoin.utils.MyLogger;
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

import pl.droidsonroids.gif.GifImageView;

import static com.cfo.chocoin.net.OkHttpClientManager.IMGHOST;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 大厅
 */
public class Fragment3 extends BaseFragment {
//    boolean isOver = false;
    Fragment3Model model;
    int type = 1;
    private RecyclerView recyclerView;
    List<Fragment3Model.LikeGameBean.LikeGameParticipationListBean> list1 = new ArrayList<>();
    CommonAdapter<Fragment3Model.LikeGameBean.LikeGameParticipationListBean> mAdapter1;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper1;

    List<Fragment3Model.MyLikeGameParticipationListBean> list2 = new ArrayList<>();
    CommonAdapter<Fragment3Model.MyLikeGameParticipationListBean> mAdapter2;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper2;
    //头部一
    View headerView1;
    TextView head1_textView1, head1_textView2, head1_textView3, head1_textView4, head1_textView5,
            head1_textView6, head1_textView7, head1_textView9, head1_textView10;
    EditText head1_editText;
    ImageView head1_imageView4, head1_imageView5;
    LinearLayout head1_imageView1, head1_linearLayout2, head1_linearLayout3, head1_linearLayout4;

    String bet_chain = "", money = "";//1.公链 2.侧链
    TimeCount time1 = null;
    TimeCount2 time2 = null;

    //头部二-需要悬浮的布局
    View headerView2;
    LinearLayout head2_linearLayout1, head2_linearLayout2;
    TextView head2_textView1, head2_textView2;
    View head2_view1, head2_view2;

    //悬浮部分
    LinearLayout invis;
    LinearLayout linearLayout1, linearLayout2;
    TextView textView1, textView2;
    View view1, view2;


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
                if (MainActivity.item == 4) {
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
                HashMap<String, String> params = new HashMap<>();
                params.put("token", LocalUserInfo.getInstance(getActivity()).getToken());
                request(params);
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
        headerView1 = View.inflate(getActivity(), R.layout.head_fragment3_1, null);
        head1_imageView1 = headerView1.findViewById(R.id.head1_imageView1);
        head1_imageView4 = headerView1.findViewById(R.id.head1_imageView4);
        head1_imageView5 = headerView1.findViewById(R.id.head1_imageView5);

        head1_textView1 = headerView1.findViewById(R.id.head1_textView1);
        head1_textView2 = headerView1.findViewById(R.id.head1_textView2);
        head1_textView3 = headerView1.findViewById(R.id.head1_textView3);
        head1_textView4 = headerView1.findViewById(R.id.head1_textView4);
        head1_textView5 = headerView1.findViewById(R.id.head1_textView5);
        head1_textView6 = headerView1.findViewById(R.id.head1_textView6);
        head1_textView7 = headerView1.findViewById(R.id.head1_textView7);
        head1_textView9 = headerView1.findViewById(R.id.head1_textView9);
        head1_textView10 = headerView1.findViewById(R.id.head1_textView10);

        head1_editText = headerView1.findViewById(R.id.head1_editText);
        head1_linearLayout2 = headerView1.findViewById(R.id.head1_linearLayout2);
        head1_linearLayout3 = headerView1.findViewById(R.id.head1_linearLayout3);
        head1_linearLayout4 = headerView1.findViewById(R.id.head1_linearLayout4);


        head1_imageView1.setOnClickListener(this);
        head1_textView1.setOnClickListener(this);
        head1_textView2.setOnClickListener(this);
        head1_textView10.setOnClickListener(this);


        head1_linearLayout3.setOnClickListener(this);
        head1_linearLayout4.setOnClickListener(this);

        //头部二
        headerView2 = View.inflate(getActivity(), R.layout.head_fragment3_2, null);
        head2_linearLayout1 = headerView2.findViewById(R.id.head2_linearLayout1);
        head2_linearLayout2 = headerView2.findViewById(R.id.head2_linearLayout2);
        head2_linearLayout1.setOnClickListener(this);
        head2_linearLayout2.setOnClickListener(this);
        head2_textView1 = headerView2.findViewById(R.id.head2_textView1);
        head2_textView2 = headerView2.findViewById(R.id.head2_textView2);
        head2_view1 = headerView2.findViewById(R.id.head2_view1);
        head2_view2 = headerView2.findViewById(R.id.head2_view2);

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


        /*ImageView imageView = headerView1.findViewById(R.id.imageView);

        //动态设置linearLayout的高度为屏幕高度的1/4
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(getActivity()) / 3;*/


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
        HashMap<String, String> params = new HashMap<>();
        params.put("token", LocalUserInfo.getInstance(getActivity()).getToken());
        request(params);
    }

    @Override
    protected void updateView() {

    }

    private void request(HashMap<String, String> params) {
        OkHttpClientManager.postAsyn(getActivity(), URLs.Fragment3, params, new OkHttpClientManager.ResultCallback<Fragment3Model>() {
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
                showContentPage();
                MyLogger.i(">>>>>>>>>首页" + response);
                model = response;
                //期次
                head1_textView3.setText(getString(R.string.fragment3_h3) + "  " + response.getLike_game().getPeriod());
                //总投
                head1_textView5.setText(response.getLike_game().getAmount_money());

                //公链
//                head1_textView5.setText(response.getLike_game().getAmount_money_1());
                //公链已投
                head1_textView6.setText(response.getPublic_chain_money());
                //侧链
//                head1_textView7.setText(response.getLike_game().getAmount_money_2());

                //侧链已投
                head1_textView7.setText(response.getBroadside_chain_money());
                //可用余额
                head1_textView9.setText(response.getUsable_money());

                //是否参与
                if (!response.getWin_prompt().getWin_member_id().equals("")) {
                    //是我中奖
                    if (localUserInfo.getUserId().equals(response.getWin_prompt().getWin_member_id()) &&
                            Double.valueOf(response.getWin_prompt().getWin_money()) > 0) {
                        //没有中奖标识 或者 标识不相同
                        if (localUserInfo.getWinnum().equals("") || !localUserInfo.getWinnum().equals(response.getWin_prompt().getWin_member_id() + response.getWin_prompt().getPeriod())) {
                            //显示中奖
                            showWin(response.getWin_prompt());
                        }
                    } else {
                        //没有中奖
                        /*if (localUserInfo.getLosenum().equals("") || !localUserInfo.getLosenum().equals(response.getWin_prompt().getWin_member_id() + response.getWin_prompt().getPeriod())) {
                            //显示未中奖
                            showLose(response.getWin_prompt());
                        }*/
                    }

                }

                //倒计时
                if (response.getCount_down() > 0) {
                    showTime();
                } else {

                }
                list1 = response.getLike_game().getLike_game_participation_list();
                list2 = response.getMy_like_game_participation_list();
                //参与动态
                mAdapter1 = new CommonAdapter<Fragment3Model.LikeGameBean.LikeGameParticipationListBean>
                        (getActivity(), R.layout.item_fragment3, list1) {
                    @Override
                    protected void convert(ViewHolder holder, final Fragment3Model.LikeGameBean.LikeGameParticipationListBean model, int position) {
                        holder.setText(R.id.textView1, model.getMember_nickname());
                        holder.setText(R.id.textView2, model.getMoney() + getString(R.string.app_ge));
                        holder.setText(R.id.textView3, model.getShow_created_at());
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

                //我的参与
                mAdapter2 = new CommonAdapter<Fragment3Model.MyLikeGameParticipationListBean>
                        (getActivity(), R.layout.item_fragment3, list2) {
                    @Override
                    protected void convert(ViewHolder holder, final Fragment3Model.MyLikeGameParticipationListBean model, int position) {
                        holder.setText(R.id.textView1, model.getMember_nickname());
                        holder.setText(R.id.textView2, model.getMoney() + getString(R.string.app_ge));
                        holder.setText(R.id.textView3, model.getShow_created_at());
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
                mHeaderAndFooterWrapper2 = new HeaderAndFooterWrapper(mAdapter2);
                mHeaderAndFooterWrapper2.addHeaderView(headerView1);
                mHeaderAndFooterWrapper2.addHeaderView(headerView2);
                changeUI();
                hideProgress();

                MainActivity.isOver = true;
            }
        }, true);
    }

    //显示中奖弹框
    private void showWin(Fragment3Model.WinPromptBean bean) {
        localUserInfo.setWinnum(bean.getWin_member_id() + bean.getPeriod());
        dialog.contentView(R.layout.dialog_win)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.CENTER)
                .canceledOnTouchOutside(true)
                .dimAmount(0.8f)
                .show();
        TextView textView1 = dialog.findViewById(R.id.textView1);
        TextView textView2 = dialog.findViewById(R.id.textView2);

        textView1.setText(bean.getWin_money());
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //分享
//                dialog.dismiss();
                Intent share_intent = new Intent();
                share_intent.setAction(Intent.ACTION_SEND);
                share_intent.setType("text/plain");
                share_intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_h28) + "\n" + model.getUrl());
                share_intent = Intent.createChooser(share_intent, "分享");
                startActivity(share_intent);
            }
        });

        dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
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
            case R.id.head1_textView1:
                //往期栏
                CommonUtil.gotoActivity(getActivity(), PastListActivity.class, false);

                break;
            case R.id.head1_textView2:
                //排行榜
                CommonUtil.gotoActivity(getActivity(), LeaderboardActivity.class, false);

                break;
            case R.id.head1_linearLayout3:
                //公链
                bet_chain = "1";
                head1_imageView4.setImageResource(R.mipmap.bg_fragment3_gonglian_1);
                head1_imageView5.setImageResource(R.mipmap.bg_fragment3_celian_0);

                break;
            case R.id.head1_linearLayout4:
                //侧链
                bet_chain = "2";
                head1_imageView4.setImageResource(R.mipmap.bg_fragment3_gonglian_0);
                head1_imageView5.setImageResource(R.mipmap.bg_fragment3_celian_1);
                break;

            case R.id.head1_textView10:
                //立即下注
                if (match()) {
                    head1_textView10.setClickable(false);
                    showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("token", LocalUserInfo.getInstance(getActivity()).getToken());
                    params.put("bet_chain", bet_chain);
                    params.put("money", money);
                    params.put("like_game_id", model.getLike_game().getId());
                    requestAdd(params);
                }
                break;

            case R.id.head1_imageView1:
                //竞猜规则
                dialog.contentView(R.layout.dialog_jingcaiguize)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.CENTER)
                        .canceledOnTouchOutside(true)
                        .dimAmount(0.8f)
                        .show();
                dialog.findViewById(R.id.linearLayout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
        }
    }

    private boolean match() {
        money = head1_editText.getText().toString().trim();
        if (TextUtils.isEmpty(money)) {
            myToast(getString(R.string.fragment3_h5));
            return false;
        }
        if (bet_chain.equals("")) {
            myToast(getString(R.string.fragment3_h18));
            return false;
        }
        return true;
    }

    private void requestAdd(HashMap<String, String> params) {
        OkHttpClientManager.postAsyn(getActivity(), URLs.Game_Add, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    //是否包含"币数不足"
                    if (info.contains(getString(R.string.fragment3_h19))) {
                        showToast(info, getString(R.string.fragment3_h20),
                                getString(R.string.fragment3_h21),
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
                head1_textView10.setClickable(true);
                requestServer();
            }

            @Override
            public void onResponse(final String response) {
                head1_textView10.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>游戏参与" + response);
                head1_editText.setText("");
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

    private void changeUI() {
        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.white));
            textView2.setTextColor(getResources().getColor(R.color.white1));
            head2_textView1.setTextColor(getResources().getColor(R.color.white));
            head2_textView2.setTextColor(getResources().getColor(R.color.white1));

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            head2_view1.setVisibility(View.VISIBLE);
            head2_view2.setVisibility(View.INVISIBLE);

            recyclerView.setAdapter(mHeaderAndFooterWrapper1);
            mHeaderAndFooterWrapper1.notifyDataSetChanged();

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.white1));
            textView2.setTextColor(getResources().getColor(R.color.white));
            head2_textView1.setTextColor(getResources().getColor(R.color.white1));
            head2_textView2.setTextColor(getResources().getColor(R.color.white));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            head2_view1.setVisibility(View.INVISIBLE);
            head2_view2.setVisibility(View.VISIBLE);

            recyclerView.setAdapter(mHeaderAndFooterWrapper2);
            mHeaderAndFooterWrapper2.notifyDataSetChanged();
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showTime() {
        MyLogger.i(">>>>>>" + (model.getCount_down() * 1000));
        if (time1 != null) {
            time1.cancel();
        }
        time1 = new TimeCount(model.getCount_down() * 1000, 1000, head1_textView4);//构造CountDownTimer对象
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
            textView.setText("0s");
            if (MainActivity.item == 2) {
                dialog.contentView(R.layout.dialog_gifimg)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.CENTER)
                        .canceledOnTouchOutside(true)
                        .dimAmount(0.8f)
                        .show();

                GifImageView gifImageView = (GifImageView) dialog.findViewById(R.id.imageView);
//                gifImageView.setScaleType(ImageView.ScaleType.CENTER);
                GifLoadOneTimeGif.loadOneTimeGif(getActivity(), R.mipmap.gifimg, gifImageView, 1, new GifLoadOneTimeGif.GifListener() {
                    @Override
                    public void gifPlayComplete() {
                        dialog.dismiss();
                        showProgress(true, getString(R.string.fragment3_h23));
                        HashMap<String, String> params = new HashMap<>();
                        params.put("token", localUserInfo.getToken());
                        request(params);
                    }
                });

                dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                /*if (time2 != null) {
                    time2.cancel();
                }
                time2 = new TimeCount2(6 * 1000, 1000,);//构造CountDownTimer对象
                time2.start();//开始计时
                TextView textView2 = dialog.findViewById(R.id.textView2);
                textView2.setText(getString(R.string.fragment3_h23));
                TextView textView3 = dialog.findViewById(R.id.textView3);*/

            }

        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
//            textView.setText(CommonUtil.timedate3(millisUntilFinished) + "s");//秒计时
            textView.setText(CommonUtil.timedate4(millisUntilFinished, getActivity()));//时分秒倒计时

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (time1 != null) {
            time1.cancel();
        }
        if (time2 != null) {
            time2.cancel();
        }
    }

    class TimeCount2 extends CountDownTimer {
//        TextView textView;

        public TimeCount2(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
//            this.textView = textView;
        }

        @Override
        public void onFinish() {//计时完毕时触发
//            textView.setText(getString(R.string.fragment3_h22));
            dialog.dismiss();
            if (MainActivity.item == 2) {
                showProgress(true, getString(R.string.fragment3_h23));
                HashMap<String, String> params = new HashMap<>();
                params.put("token", localUserInfo.getToken());
                request(params);
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
//            textView.setText(CommonUtil.timedate3(millisUntilFinished) + "s");
        }
    }
}
