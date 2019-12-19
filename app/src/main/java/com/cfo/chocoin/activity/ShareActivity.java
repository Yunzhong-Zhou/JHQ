package com.cfo.chocoin.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.model.ShareModel;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.CommonUtil;
import com.cfo.chocoin.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import static com.cfo.chocoin.net.OkHttpClientManager.IMGHOST;


/**
 * Created by zyz on 2018/10/19.
 * 我的推广
 */
public class ShareActivity extends BaseActivity {
    ShareModel model;
    ImageView imageView, imageView1, imageView2;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9,
            textView10, textView11, textView12, textView13, textView14, textView15;

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

        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
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


        imageView = findViewByID_My(R.id.imageView);
        //动态设置linearLayout的高度为屏幕高度的1/4
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(ShareActivity.this) / 4;

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
                            .into(imageView1);//加载图片

                textView1.setText(response.getNickname() + "");//昵称
                textView2.setText(getString(R.string.share_h2) + response.getInvite_code());//邀请码
                textView3.setText(response.getCommission_money() + "");//合约金额
                textView4.setText(response.getGrade_title() + "");//等级
                textView5.setText(response.getCommission_param().getContract_trading_service_charge() + "");//手续费
                textView6.setText(response.getCommission_param().getContract_trading_profit() + "");//团队利益
                textView7.setText(response.getCommission_param().getContract_trading_same_level() + "");//直推利益
                textView8.setText(response.getDirect_performance_money() + "");//直推业绩
                textView9.setText(response.getDirect_recommend()+ "");//直推人数
//                textView10.setText(response.get + "");//直推用户
                textView11.setText(response.getTeam_performance_money() + "");//团队业绩
                textView12.setText(response.getTeam_recommend() + "");//团队人数
                textView13.setText(response.getDirect_recommend_IB() + "");//直推IB
                textView14.setText(response.getDirect_recommend_MIB() + "");//直推MIB
                textView15.setText(response.getDirect_recommend_PIB() + "");//直推PIB
            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.textView10:
                //直推会员
                CommonUtil.gotoActivity(ShareActivity.this, SharePeopleActivity.class, false);
                break;
            case R.id.imageView2:
                //二维码

                break;
        }
    }

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
