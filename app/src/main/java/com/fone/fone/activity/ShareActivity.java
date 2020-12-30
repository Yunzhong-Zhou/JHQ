package com.fone.fone.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.ShareModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import static com.fone.fone.net.OkHttpClientManager.IMGHOST;


/**
 * Created by zyz on 2018/10/19.
 * 我的推广
 */
public class ShareActivity extends BaseActivity {
    ShareModel model;
    ImageView imageView1;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9,
            textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17,
            textView18, textView19, textView20, textView21;
    ProgressBar prograssBar1, prograssBar2;
    TextView tv_haicha, tv_tixing,
            tv_v1, tv_v2, tv_v3,
            tv_num0, tv_num1, tv_num2, tv_num3;
    View view_1, view_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

//        mImmersionBar.reset().init();

        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
//        CommonUtil.setMargins(findViewById(R.id.headview),0,(int) CommonUtil.getStatusBarHeight(this), 0, 0);
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

        imageView1 = findViewByID_My(R.id.imageView1);

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
        textView12 = findViewByID_My(R.id.textView12);
        textView13 = findViewByID_My(R.id.textView13);
        textView14 = findViewByID_My(R.id.textView14);
        textView15 = findViewByID_My(R.id.textView15);
        textView16 = findViewByID_My(R.id.textView16);
        textView17 = findViewByID_My(R.id.textView17);
        textView18 = findViewByID_My(R.id.textView18);
        textView19 = findViewByID_My(R.id.textView19);
        textView20 = findViewByID_My(R.id.textView20);
        textView21 = findViewByID_My(R.id.textView21);

        prograssBar1 = findViewByID_My(R.id.prograssBar1);
        prograssBar2 = findViewByID_My(R.id.prograssBar2);

        tv_haicha = findViewByID_My(R.id.tv_haicha);
        tv_tixing = findViewByID_My(R.id.tv_tixing);

        tv_v1 = findViewByID_My(R.id.tv_v1);
        tv_v2 = findViewByID_My(R.id.tv_v2);
        tv_v3 = findViewByID_My(R.id.tv_v3);
        view_1 = findViewByID_My(R.id.view_1);
        view_2 = findViewByID_My(R.id.view_1);

        tv_num0 = findViewByID_My(R.id.tv_num0);
        tv_num1 = findViewByID_My(R.id.tv_num1);
        tv_num2 = findViewByID_My(R.id.tv_num2);
        tv_num3 = findViewByID_My(R.id.tv_num3);


        /*imageView = findViewByID_My(R.id.imageView);
        //动态设置linearLayout的高度为屏幕高度的1/4
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(ShareActivity.this) / 4;*/

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
                            .placeholder(R.mipmap.loading)//加载站位图
                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片

                textView1.setText(response.getNickname() + "");//昵称
                textView2.setText(getString(R.string.share_h2) + response.getInvite_code());//邀请码
                textView3.setText(response.getCommission_running_proportion() + "%");//拼团分成
                textView4.setText(response.getCommission_money() + "");//累计佣金
                textView5.setText(response.getCommission_sell_proportion()+ "%");//销售分成
                textView6.setText(response.getCommission_same_level_proportion() + "%");//同级分成
                textView7.setText(response.getValid_direct_recommend());//累计直推有效人数
                textView8.setText(getString(R.string.share_h12) + response.getHold_start_at()
                        + "-" + response.getHold_end_at());//考核期：
                textView9.setText(response.getHold_current_money() + getString(R.string.app_type_usdt) + "/");//新增业绩
                textView10.setText(getString(R.string.share_h15) + response.getHold_target_money() + getString(R.string.app_type_usdt) + "");//目标
                if (response.getHold_current_money() != null && !response.getHold_current_money().equals("")
                        && response.getHold_target_money() != null && !response.getHold_target_money().equals("")) {
                    double max = Double.valueOf(response.getHold_target_money());
                    prograssBar1.setMax((int) max);
                    double progress = Double.valueOf(response.getHold_current_money());
                    prograssBar1.setProgress((int) progress);
                }

                textView11.setText(response.getHold_current_money_count() + getString(R.string.app_ge) + "/");//新增团队
                textView12.setText(getString(R.string.share_h15) + response.getHold_target_money_count() + getString(R.string.app_ge) + "");//目标
                if (response.getHold_current_money_count() != null && !response.getHold_current_money_count().equals("")
                        && response.getHold_target_money_count() != null && !response.getHold_target_money_count().equals("")) {
                    double max = Double.valueOf(response.getHold_target_money_count());
                    prograssBar2.setMax((int) max);
                    double progress = Double.valueOf(response.getHold_current_money_count());
                    prograssBar2.setProgress((int) progress);
                }
//                textView13.setText(response.get + "");//查看直推
                textView14.setText(response.getDirect_recommend() + "");//直推人数
                textView15.setText(response.getTeam_recommend() + "");//团队人数
                textView16.setText(response.getDirect_performance_money() + "");//直推业绩
                textView17.setText(response.getTeam_performance_money() + "");//团队业绩
                textView18.setText(response.getDirect_performance_buy_invest_money() + "");//直推算力
                textView19.setText(response.getTeam_performance_buy_invest_money() + "");//团队算力
                textView20.setText(response.getDirect_performance_all_invest_money() + "");//直推矿机
                textView21.setText(response.getTeam_performance_all_invest_money() + "");// 团队矿机

                /*tv_haicha.setText(getString(R.string.share_h6) + response.getTips_upgrade_money()
                        + getString(R.string.app_type_usdt)
                        + getString(R.string.share_h7));
                tv_tixing.setText(getString(R.string.share_h13) + response.getTips_hold_target_money() + "，"
                        + getString(R.string.share_h14) + response.getTips_hold_target_money_count() + getString(R.string.app_ren) + "，"
                        + getString(R.string.share_h17));*/
                tv_haicha.setText(response.getTips_upgrade());
                tv_tixing.setText(response.getTips_hold());

                switch (response.getGrade()) {
                    case 1:
                        tv_v1.setTextColor(getResources().getColor(R.color.black1));
                        tv_v1.setBackgroundResource(R.drawable.yuanxing_huise);
                        tv_v2.setTextColor(getResources().getColor(R.color.black1));
                        tv_v2.setBackgroundResource(R.drawable.yuanxing_huise);
                        tv_v3.setTextColor(getResources().getColor(R.color.black1));
                        tv_v3.setBackgroundResource(R.drawable.yuanxing_huise);

                        view_1.setBackgroundResource(R.color.white);
                        view_2.setBackgroundResource(R.color.white);
                        break;
                    case 2:
                        tv_v1.setTextColor(getResources().getColor(R.color.white));
                        tv_v1.setBackgroundResource(R.drawable.yuanxing_huangsejianbian);

                        tv_v2.setTextColor(getResources().getColor(R.color.black1));
                        tv_v2.setBackgroundResource(R.drawable.yuanxing_huise);
                        tv_v3.setTextColor(getResources().getColor(R.color.black1));
                        tv_v3.setBackgroundResource(R.drawable.yuanxing_huise);

                        view_1.setBackgroundResource(R.color.white);
                        view_2.setBackgroundResource(R.color.white);
                        break;
                    case 3:
                        tv_v1.setTextColor(getResources().getColor(R.color.white));
                        tv_v1.setBackgroundResource(R.drawable.yuanxing_huangsejianbian);
                        tv_v2.setTextColor(getResources().getColor(R.color.white));
                        tv_v2.setBackgroundResource(R.drawable.yuanxing_huangsejianbian);

                        tv_v3.setTextColor(getResources().getColor(R.color.black1));
                        tv_v3.setBackgroundResource(R.drawable.yuanxing_huise);

                        view_1.setBackgroundResource(R.color.yellow_1);
                        view_2.setBackgroundResource(R.color.white);
                        break;
                    case 4:
                        tv_v1.setTextColor(getResources().getColor(R.color.white));
                        tv_v1.setBackgroundResource(R.drawable.yuanxing_huangsejianbian);
                        tv_v2.setTextColor(getResources().getColor(R.color.white));
                        tv_v2.setBackgroundResource(R.drawable.yuanxing_huangsejianbian);
                        tv_v3.setTextColor(getResources().getColor(R.color.white));
                        tv_v3.setBackgroundResource(R.drawable.yuanxing_huangsejianbian);

                        view_1.setBackgroundResource(R.color.yellow_1);
                        view_2.setBackgroundResource(R.color.yellow_1);
                        break;

                }
                tv_num0.setText(response.getGrade_count_list().getGrade_1());
                tv_num1.setText(response.getGrade_count_list().getGrade_2());
                tv_num2.setText(response.getGrade_count_list().getGrade_3());
                tv_num3.setText(response.getGrade_count_list().getGrade_4());

            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.textView13:
                //直推会员
                CommonUtil.gotoActivity(ShareActivity.this, SharePeopleActivity.class, false);
                break;
            /*case R.id.tv_share:
            case R.id.imageView2:
                //立即分享//跳转海报
                CommonUtil.gotoActivity(ShareActivity.this, InvitationRewardActivity.class, false);
                break;*/

            case R.id.textView2:
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", model.getInvite_code());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                myToast(getString(R.string.recharge_h34));
                break;

        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle(R.string.share_h1);
        titleView.setVisibility(View.GONE);
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
