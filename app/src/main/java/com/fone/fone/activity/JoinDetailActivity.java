package com.fone.fone.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cy.dialog.BaseDialog;
import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.JoinDetailModel;
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
import java.util.Collections;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mr.Z on 2020/12/14.
 * 加入拼团详情
 */
public class JoinDetailActivity extends BaseActivity {
    JoinDetailModel model;
    String id = "";
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, tv_confirm;
    ImageView imageView1, imageView2;
    private RecyclerView recyclerView;
    List<JoinDetailModel.ChangeGameBean.ChangeGameParticipationListBean> list = new ArrayList<>();
    CommonAdapter<JoinDetailModel.ChangeGameBean.ChangeGameParticipationListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joindetail);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        findViewByID_My(R.id.left_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                String string = "?id=" + id
                        + "&token=" + localUserInfo.getToken();
                RequestDetail(string);
            }

            @Override
            public void onLoadmore() {

            }
        });
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        textView8 = findViewByID_My(R.id.textView8);
        textView9 = findViewByID_My(R.id.textView9);
        textView10 = findViewByID_My(R.id.textView10);
        textView11 = findViewByID_My(R.id.textView11);
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        requestServer();//获取数据
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        String string = "?id=" + id
                + "&token=" + localUserInfo.getToken();
        RequestDetail(string);
    }

    private void RequestDetail(String string) {
        OkHttpClientManager.getAsyn(JoinDetailActivity.this, URLs.JoinDetail + string, new OkHttpClientManager.ResultCallback<JoinDetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(JoinDetailModel response) {
                showContentPage();
                hideProgress();
                model = response;
                //是我中奖
                if (localUserInfo.getUserId().equals(response.getChange_game().getWin_member_id())) {
                    //显示中奖
                    if (localUserInfo.getWinnum1().equals("") ||
                            !localUserInfo.getWinnum1().equals(response.getChange_game().getWin_member_id()
                                    + response.getChange_game().getPeriod())) {
                        //显示中奖
                        showWin(response.getChange_game());
                    }

                } else {
                    //没有中奖
//                 showLose(response.getWin_prompt());
                }

                //fil价格
                if (response.getChange_game().getStatus() == 3) {//已结束
                    textView1.setText(response.getChange_game().getWin_num());//fil价格
                } else {
                    textView1.setText("???");//fil价格
                }
                textView2.setText(response.getChange_game().getWin_at());//FIL价格时间

                if (response.getChange_game().getStatus() == 2){//处理中
                    //显示倒计时
                    if (response.getCount_down() > 0) {
                        showTime();
                    }else {
                        textView3.setText(response.getChange_game().getStatus_title());//拼团状态
                    }
                }else {
                    textView3.setText(response.getChange_game().getStatus_title());//拼团状态
                }

                textView4.setText(response.getChange_game().getLast_participation_at());//结束时间
                textView5.setText(response.getChange_game().getPeriod());//拼团单号
                if (response.getChange_game().getMill() != null) {
//                    textView6.setText(response.getChange_game().getMill().getHashrate() + "TB");//算团大小
                    textView7.setText(response.getChange_game().getMill().getMining_cycle() + getString(R.string.app_tian));//挖矿周期
                }

                if (response.getChange_game().getWin_member() != null) {
                    textView8.setVisibility(View.VISIBLE);
                    textView10.setVisibility(View.VISIBLE);
                    textView11.setVisibility(View.VISIBLE);

                    textView9.setText(response.getChange_game().getWin_member().getNickname());//拼中用户name
                    textView10.setText(response.getChange_game().getWin_member().getChange_game_participation_created_at());//拼中时间
                    textView11.setText(response.getChange_game().getWin_member().getChange_game_participation_index() + "");//拼中排名
                    Glide.with(JoinDetailActivity.this)
                            .load(OkHttpClientManager.IMGHOST + response.getChange_game().getWin_member().getHead())
                            .placeholder(R.mipmap.loading)//加载站位图
                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片
                    Glide.with(JoinDetailActivity.this)
                            .load(OkHttpClientManager.IMGHOST + response.getChange_game().getWin_member().getHead())
                            .placeholder(R.mipmap.loading)//加载站位图
                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView2);//加载图片
                } else {
                    textView8.setVisibility(View.GONE);
                    textView9.setText(getString(R.string.fragment3_h32));
                    textView10.setVisibility(View.GONE);
                    textView11.setVisibility(View.GONE);
                    imageView1.setImageResource(R.mipmap.ic_wenhao_gray2);
                    imageView2.setImageResource(R.mipmap.ic_wenhao_gray2);
                }


                list = response.getChange_game().getChange_game_participation_list();
                Collections.reverse(list);//倒序
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<JoinDetailModel.ChangeGameBean.ChangeGameParticipationListBean>
                            (JoinDetailActivity.this, R.layout.item_joindetaillist, list) {
                        @Override
                        protected void convert(ViewHolder holder, JoinDetailModel.ChangeGameBean.ChangeGameParticipationListBean model, int position) {
                            holder.setText(R.id.textView1, model.getMember_nickname());
                            holder.setText(R.id.textView2, model.getCreated_at());//时间
                            holder.setText(R.id.textView3, model.getIndex() + "");
                            ImageView imageView = holder.getView(R.id.imageView);
                            Glide.with(JoinDetailActivity.this)
                                    .load(OkHttpClientManager.IMGHOST + model.getMember_head())
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView);//加载图片
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

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

    TimeCount time1 = null;

    private void showTime() {
        MyLogger.i(">>>>>>" + (model.getCount_down() * 1000));
        if (time1 != null) {
            time1.cancel();
        }
        time1 = new TimeCount(model.getCount_down() * 1000, 1000, textView3);//构造CountDownTimer对象
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
//            textView.setText(getString(R.string.fragment3_h54));
            textView.setText("00:00");
            requestServer();
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
//            textView.setText(CommonUtil.timedate3(millisUntilFinished) + "s");//秒计时
            textView.setText(CommonUtil.timedate4(millisUntilFinished, JoinDetailActivity.this));//时分秒倒计时

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (time1 != null) {
            time1.cancel();
        }
    }

    //显示中奖弹框
    private void showWin(JoinDetailModel.ChangeGameBean bean) {
        localUserInfo.setWinnum1(bean.getWin_member_id() + bean.getPeriod());
        dialog.contentView(R.layout.dialog_fragment3)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.CENTER)
                .canceledOnTouchOutside(true)
                .gravity(Gravity.CENTER)
                .dimAmount(0.8f)
                .show();
        TextView textView1 = dialog.findViewById(R.id.textView1);
//        textView1.setText(bean.get + "TB");
        dialog.findViewById(R.id.textView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                /*Bundle bundle1 = new Bundle();
                bundle1.putString("id", model.getChange_game().getId());
                CommonUtil.gotoActivityWithData(getActivity(), JoinDetailActivity.class, bundle1, false);*/
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
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }

}
