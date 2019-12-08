package com.cfo.chocoin.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.model.ShareModel;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.MyLogger;
import com.bumptech.glide.Glide;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.xw.repo.BubbleSeekBar;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import static com.cfo.chocoin.net.OkHttpClientManager.IMGHOST;


/**
 * Created by zyz on 2018/10/19.
 * 我的推广
 */
public class ShareActivity extends BaseActivity {
    ShareModel model;
    private RecyclerView recyclerView;
    List<ShareModel.PerformanceListBean> list1 = new ArrayList<>();
    CommonAdapter<ShareModel.PerformanceListBean> mAdapter1;
    HeaderAndFooterWrapper mHeaderAndFooterWrapper1;
    //头部一
    View headerView1;
    ImageView head1_imageView1;
    TextView head1_textView1, head1_textView2, head1_textView3, head1_textView4, head1_textView5, head1_textView6,
            head1_textView7, head1_textView8, head1_textView9, head1_textView10, head1_textView11,
            head1_textView12, head1_textView13,textView1_1,textView2_1,textView3_1,textView4_1,textView5_1,
            textView6_1,textView7_1,textView8_1,textView9_1,textView10_1;
    BubbleSeekBar seekBar;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView1_1, imageView2_1, imageView3_1, imageView4_1, imageView5_1;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10;

    //头部二-需要悬浮的布局
    View headerView2;

    //悬浮部分
    LinearLayout invis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
//        mImmersionBar.reset().init();
//        findViewById(R.id.headview).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void initView() {
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

        //头部一
        headerView1 = View.inflate(ShareActivity.this, R.layout.head_share_1, null);
        head1_imageView1 = headerView1.findViewById(R.id.head1_imageView1);
        head1_textView1 = headerView1.findViewById(R.id.head1_textView1);
        head1_textView2 = headerView1.findViewById(R.id.head1_textView2);
        head1_textView3 = headerView1.findViewById(R.id.head1_textView3);
        head1_textView4 = headerView1.findViewById(R.id.head1_textView4);
        head1_textView5 = headerView1.findViewById(R.id.head1_textView5);
        head1_textView6 = headerView1.findViewById(R.id.head1_textView6);
        head1_textView7 = headerView1.findViewById(R.id.head1_textView7);
        head1_textView8 = headerView1.findViewById(R.id.head1_textView8);
        head1_textView9 = headerView1.findViewById(R.id.head1_textView9);
        head1_textView10 = headerView1.findViewById(R.id.head1_textView10);
        head1_textView11 = headerView1.findViewById(R.id.head1_textView11);
        head1_textView12 = headerView1.findViewById(R.id.head1_textView12);
        head1_textView13 = headerView1.findViewById(R.id.head1_textView13);
        seekBar = headerView1.findViewById(R.id.seekBar);
        imageView1 = headerView1.findViewById(R.id.imageView1);
        imageView2 = headerView1.findViewById(R.id.imageView2);
        imageView3 = headerView1.findViewById(R.id.imageView3);
        imageView4 = headerView1.findViewById(R.id.imageView4);
        imageView5 = headerView1.findViewById(R.id.imageView5);
        imageView6 = headerView1.findViewById(R.id.imageView6);
        imageView7 = headerView1.findViewById(R.id.imageView7);
        imageView8 = headerView1.findViewById(R.id.imageView8);
        imageView9 = headerView1.findViewById(R.id.imageView9);
        imageView10 = headerView1.findViewById(R.id.imageView10);
        imageView1_1 = headerView1.findViewById(R.id.imageView1_1);
        imageView2_1 = headerView1.findViewById(R.id.imageView2_1);
        imageView3_1 = headerView1.findViewById(R.id.imageView3_1);
        imageView4_1 = headerView1.findViewById(R.id.imageView4_1);
        imageView5_1 = headerView1.findViewById(R.id.imageView5_1);
        textView1 = headerView1.findViewById(R.id.textView1);
        textView2 = headerView1.findViewById(R.id.textView2);
        textView3 = headerView1.findViewById(R.id.textView3);
        textView4 = headerView1.findViewById(R.id.textView4);
        textView5 = headerView1.findViewById(R.id.textView5);
        textView6 = headerView1.findViewById(R.id.textView6);
        textView7 = headerView1.findViewById(R.id.textView7);
        textView8 = headerView1.findViewById(R.id.textView8);
        textView9 = headerView1.findViewById(R.id.textView9);
        textView10 = headerView1.findViewById(R.id.textView10);

        textView1_1 = headerView1.findViewById(R.id.textView1_1);
        textView2_1 = headerView1.findViewById(R.id.textView2_1);
        textView3_1 = headerView1.findViewById(R.id.textView3_1);
        textView4_1 = headerView1.findViewById(R.id.textView4_1);
        textView5_1 = headerView1.findViewById(R.id.textView5_1);
        textView6_1 = headerView1.findViewById(R.id.textView6_1);
        textView7_1 = headerView1.findViewById(R.id.textView7_1);
        textView8_1 = headerView1.findViewById(R.id.textView8_1);
        textView9_1 = headerView1.findViewById(R.id.textView9_1);
        textView10_1 = headerView1.findViewById(R.id.textView10_1);

        //头部二
        headerView2 = View.inflate(ShareActivity.this, R.layout.head_share_2, null);

        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(ShareActivity.this);
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
        showProgress(true, getString(R.string.app_loading2));
        Request("?token=" + localUserInfo.getToken());
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.Share + string, new OkHttpClientManager.ResultCallback<ShareModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(final ShareModel response) {
                MyLogger.i(">>>>>>>>>分享" + response);
                hideProgress();
                model = response;
                //头像
                if (!response.getHead().equals(""))
                    Glide.with(ShareActivity.this).load(IMGHOST + response.getHead())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(head1_imageView1);//加载图片
                else
                    head1_imageView1.setImageResource(R.mipmap.headimg);
                head1_textView1.setText(response.getNickname() + "");//昵称
                head1_textView2.setText(getString(R.string.share_h5) + response.getInvite_code());//邀请码
//                head1_textView3.setText(response.getGrade() + "");//会员等级标题-英文
                head1_textView4.setText(response.getGrade_title() + "");//会员等级标题-中文
                head1_textView5.setText(getString(R.string.share_h11) + response.getGrade_title() + "");
                head1_textView6.setText(response.getCurrent_direct_performance_money() + "");//直推流水
                head1_textView7.setText(getString(R.string.share_h13)+response.getDifference_direct_performance_money()+ getString(R.string.share_h14));//还差
                head1_textView8.setText(response.getDirect_recommend() + "");//直推人数
                head1_textView9.setText(response.getTeam_recommend() + "");//团队人数
                head1_textView10.setText(response.getDirect_performance_money() + "");//直推流水
                head1_textView11.setText(response.getTeam_performance_money() + "");//团队流水
                head1_textView12.setText(response.getDirect_invest_money() + "");//直推算力
                head1_textView13.setText(response.getTeam_invest_money() + "");//团队算力

                textView1.setText(response.getGrade_performance_1() + "");
                textView2.setText(response.getGrade_performance_2() + "");
                textView3.setText(response.getGrade_performance_3() + "");
                textView4.setText(response.getGrade_performance_4() + "");
                textView5.setText(response.getGrade_performance_5() + "");

                textView6.setText(response.getGrade_count_list().getGrade_1() + "");
                textView7.setText(response.getGrade_count_list().getGrade_2() + "");
                textView8.setText(response.getGrade_count_list().getGrade_3() + "");
                textView9.setText(response.getGrade_count_list().getGrade_4() + "");
                textView10.setText(response.getGrade_count_list().getGrade_5() + "");

//                textView1_1.setText(response.getGrade_count_list().getGrade_1() + "");

                seekBar.setProgress(response.getGrade());
                switch (response.getGrade()) {
                    case 1:
                        imageView1_1.setVisibility(View.VISIBLE);
                        imageView2_1.setVisibility(View.GONE);
                        imageView3_1.setVisibility(View.GONE);
                        imageView4_1.setVisibility(View.GONE);
                        imageView5_1.setVisibility(View.GONE);
                        imageView1.setBackgroundResource(R.mipmap.bg_share3);
                        imageView2.setBackgroundResource(R.color.transparent);
                        imageView3.setBackgroundResource(R.color.transparent);
                        imageView4.setBackgroundResource(R.color.transparent);
                        imageView5.setBackgroundResource(R.color.transparent);

                        imageView6.setBackgroundResource(R.mipmap.bg_share3);
                        imageView7.setBackgroundResource(R.color.transparent);
                        imageView8.setBackgroundResource(R.color.transparent);
                        imageView9.setBackgroundResource(R.color.transparent);
                        imageView10.setBackgroundResource(R.color.transparent);
                        break;
                    case 2:
                        imageView1_1.setVisibility(View.GONE);
                        imageView2_1.setVisibility(View.VISIBLE);
                        imageView3_1.setVisibility(View.GONE);
                        imageView4_1.setVisibility(View.GONE);
                        imageView5_1.setVisibility(View.GONE);

                        imageView1.setBackgroundResource(R.color.transparent);
                        imageView2.setBackgroundResource(R.mipmap.bg_share3);
                        imageView3.setBackgroundResource(R.color.transparent);
                        imageView4.setBackgroundResource(R.color.transparent);
                        imageView5.setBackgroundResource(R.color.transparent);

                        imageView6.setBackgroundResource(R.color.transparent);
                        imageView7.setBackgroundResource(R.mipmap.bg_share3);
                        imageView8.setBackgroundResource(R.color.transparent);
                        imageView9.setBackgroundResource(R.color.transparent);
                        imageView10.setBackgroundResource(R.color.transparent);
                        break;
                    case 3:
                        imageView1_1.setVisibility(View.GONE);
                        imageView2_1.setVisibility(View.GONE);
                        imageView3_1.setVisibility(View.VISIBLE);
                        imageView4_1.setVisibility(View.GONE);
                        imageView5_1.setVisibility(View.GONE);
                        imageView1.setBackgroundResource(R.color.transparent);
                        imageView2.setBackgroundResource(R.color.transparent);
                        imageView3.setBackgroundResource(R.mipmap.bg_share3);
                        imageView4.setBackgroundResource(R.color.transparent);
                        imageView5.setBackgroundResource(R.color.transparent);

                        imageView6.setBackgroundResource(R.color.transparent);
                        imageView7.setBackgroundResource(R.color.transparent);
                        imageView8.setBackgroundResource(R.mipmap.bg_share3);
                        imageView9.setBackgroundResource(R.color.transparent);
                        imageView10.setBackgroundResource(R.color.transparent);
                        break;
                    case 4:
                        imageView1_1.setVisibility(View.GONE);
                        imageView2_1.setVisibility(View.GONE);
                        imageView3_1.setVisibility(View.GONE);
                        imageView4_1.setVisibility(View.VISIBLE);
                        imageView5_1.setVisibility(View.GONE);
                        imageView1.setBackgroundResource(R.color.transparent);
                        imageView2.setBackgroundResource(R.color.transparent);
                        imageView3.setBackgroundResource(R.color.transparent);
                        imageView4.setBackgroundResource(R.mipmap.bg_share3);
                        imageView5.setBackgroundResource(R.color.transparent);

                        imageView6.setBackgroundResource(R.color.transparent);
                        imageView7.setBackgroundResource(R.color.transparent);
                        imageView8.setBackgroundResource(R.color.transparent);
                        imageView9.setBackgroundResource(R.mipmap.bg_share3);
                        imageView10.setBackgroundResource(R.color.transparent);
                        break;

                    case 5:
                        imageView1_1.setVisibility(View.GONE);
                        imageView2_1.setVisibility(View.GONE);
                        imageView3_1.setVisibility(View.GONE);
                        imageView4_1.setVisibility(View.GONE);
                        imageView5_1.setVisibility(View.VISIBLE);
                        imageView1.setBackgroundResource(R.color.transparent);
                        imageView2.setBackgroundResource(R.color.transparent);
                        imageView3.setBackgroundResource(R.color.transparent);
                        imageView4.setBackgroundResource(R.color.transparent);
                        imageView5.setBackgroundResource(R.mipmap.bg_share3);

                        imageView6.setBackgroundResource(R.color.transparent);
                        imageView7.setBackgroundResource(R.color.transparent);
                        imageView8.setBackgroundResource(R.color.transparent);
                        imageView9.setBackgroundResource(R.color.transparent);
                        imageView10.setBackgroundResource(R.mipmap.bg_share3);
                        break;
                }

                list1 = response.getPerformance_list();
                mAdapter1 = new CommonAdapter<ShareModel.PerformanceListBean>(ShareActivity.this, R.layout.item_share, list1) {
                    @Override
                    protected void convert(ViewHolder holder, final ShareModel.PerformanceListBean model, int position) {
                        holder.setText(R.id.textView1, model.getStart_at() + "-" + model.getEnd_at());
                        holder.setText(R.id.textView2, model.getPerformance_money() + getString(R.string.app_ge));
                        holder.setText(R.id.textView3, model.getStatus_title());

                    }
                };
                mHeaderAndFooterWrapper1 = new HeaderAndFooterWrapper(mAdapter1);
                mHeaderAndFooterWrapper1.addHeaderView(headerView1);
                mHeaderAndFooterWrapper1.addHeaderView(headerView2);
                recyclerView.setAdapter(mHeaderAndFooterWrapper1);
            }

        });

    }

    /*@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.textView1:
                //直推会员
                CommonUtil.gotoActivity(ShareActivity.this, SharePeopleActivity.class, false);
                break;
            case R.id.textView6:
                //立即邀请
                if (model != null) {
                    Intent share_intent = new Intent();
                    share_intent.setAction(Intent.ACTION_SEND);
//                    share_intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    share_intent.setType("text/plain");
                    share_intent.putExtra(Intent.EXTRA_TEXT, "【U猜】首个区块链 + 社交去中心化的竞猜平台，注册即送70元\n" + HOST + model.getUrl());

                    share_intent = Intent.createChooser(share_intent, "分享");
                    startActivity(share_intent);

                    *//*String path = Environment.getExternalStorageDirectory() + File.separator;
                    Uri uri = Uri.fromFile(new File(path + "background.jpg"));
                    shareImg("分享",
                            "【U猜】首个区块链 + 社交去中心化的竞猜平台，注册即送价值70元",
                            HOST + model.getUrl(),
                            uri);*//*

                } else {
                    showToast("暂未获取到推广链接");
                }
                break;
            case R.id.textView7:
                //业绩佣金
                CommonUtil.gotoActivity(ShareActivity.this, CommissionAccountActivity.class, false);
                break;
        }
    }*/

    @Override
    protected void updateView() {
        titleView.setTitle(R.string.share_h1);
    }

    /**
     * 分享图片和文字内容
     *
     * @param dlgTitle 分享对话框标题
     * @param subject  主题
     * @param content  分享内容（文字）
     * @param uri      图片资源URI
     */
    private void shareImg(String dlgTitle, String subject, String content,
                          Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        if (subject != null && !"".equals(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        if (content != null && !"".equals(content)) {
            intent.putExtra(Intent.EXTRA_TEXT, content);
        }

        // 设置弹出框标题
        if (dlgTitle != null && !"".equals(dlgTitle)) { // 自定义标题
            startActivity(Intent.createChooser(intent, dlgTitle));
        } else { // 系统默认标题
            startActivity(intent);
        }
    }

}
