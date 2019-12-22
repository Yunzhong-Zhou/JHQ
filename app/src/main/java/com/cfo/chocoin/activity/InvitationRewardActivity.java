package com.cfo.chocoin.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.model.InvitationRewardModel;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.CommonUtil;
import com.cfo.chocoin.utils.MyLogger;
import com.cfo.chocoin.utils.ZxingUtils;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import static com.cfo.chocoin.net.OkHttpClientManager.IMGHOST;


/**
 * Created by zyz on 2019-10-09.
 * 有奖邀请
 */
public class InvitationRewardActivity extends BaseActivity {
    ImageView imageView1, imageView2;
    TextView textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitationreward);
    }

    @Override
    protected void initView() {
//        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        CommonUtil.setMargins(findViewByID_My(R.id.headView), 0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
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

    }

    @Override
    protected void initData() {
        showProgress(true, getString(R.string.app_loading2));
        Request("?token=" + localUserInfo.getToken());
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.InvitationReward + string, new OkHttpClientManager.ResultCallback<InvitationRewardModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(final InvitationRewardModel response) {
                MyLogger.i(">>>>>>>>>有奖邀请" + response);
                hideProgress();
                //头像
                if (!response.getHead().equals(""))
                    Glide.with(InvitationRewardActivity.this).load(IMGHOST + response.getHead())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片
                /*if (!response.getUrl().equals(""))
                    Glide.with(InvitationRewardActivity.this).load(IMGHOST + response.getUrl())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView2);//加载图片*/
                Bitmap mBitmap = ZxingUtils.createQRCodeBitmap(response.getUrl(), 480, 480);
                imageView2.setImageBitmap(mBitmap);

                textView1.setText(response.getNickname() + "");//昵称
                textView2.setText(response.getInvite_code() + "");//邀请码
                textView3.setText(response.getProfit_money() + "");//合约金额
            }

        });

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
        }
    }

    @Override
    protected void updateView() {
//        titleView.setTitle("有奖邀请");
        titleView.setVisibility(View.GONE);
    }
}
