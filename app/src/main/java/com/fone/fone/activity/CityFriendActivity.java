package com.fone.fone.activity;

import android.os.Bundle;
import android.view.View;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.liaoinstan.springview.widget.SpringView;


/**
 * Created by zyz on 2019-12-14.
 * 城市合作伙伴-已申请
 */
public class CityFriendActivity extends BaseActivity {
    /*RelativeLayout relativeLayout;
    TextView textView1, textView2, textView3, textView4, textView5,textView4_1, textView5_1,textView4_2, textView5_2, textView6, textView7, textView8, textView9, textView10;
    ImageView imageView1, imageView2;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityfriend);

        mImmersionBar.reset()
                .fitsSystemWindows(true)
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
//        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void initView() {
        /*relativeLayout = findViewByID_My(R.id.relativeLayout);
        //动态设置linearLayout的高度为屏幕高度的1/2
        ViewGroup.LayoutParams lp = relativeLayout.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(this) / 2;*/
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                request("?token=" + localUserInfo.getToken());
            }

            @Override
            public void onLoadmore() {

            }
        });

        /*textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView4_1 = findViewByID_My(R.id.textView4_1);
        textView5_1= findViewByID_My(R.id.textView5_1);
        textView4_2 = findViewByID_My(R.id.textView4_2);
        textView5_2= findViewByID_My(R.id.textView5_2);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        textView8 = findViewByID_My(R.id.textView8);
        textView9 = findViewByID_My(R.id.textView9);
        textView10 = findViewByID_My(R.id.textView10);

        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);*/
    }

    @Override
    protected void initData() {
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        showProgress(true, getString(R.string.app_loading2));
        request("?token=" + localUserInfo.getToken());
    }

    private void request(String string) {
        /*OkHttpClientManager.getAsyn(ServiceCenter_YesActivity.this, URLs.ServiceCenter + string, new OkHttpClientManager.ResultCallback<ServiceCenterModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(ServiceCenterModel response) {
                MyLogger.i(">>>>>>>>>服务中心" + response);
                hideProgress();
                if (!response.getHead().equals(""))
                    Glide.with(ServiceCenter_YesActivity.this).load(IMGHOST + response.getHead())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片
                textView1.setText(response.getService_code());//服务码
                textView2.setText(getString(R.string.myprofile_h53) + response.getNickname());//昵称
                textView3.setText(response.getService_count());//服务人数
                textView4.setText(response.getService_performance());//服务业绩
                textView5.setText(response.getAmount_earning());//累计分红

                textView4_1.setText(response.getOfc_service_performance());//服务业绩
                textView5_1.setText(response.getOfc_amount_earning());//累计分红

                textView4_2.setText(response.getDrvt_deal_performance());//交易业绩
                textView5_2.setText(response.getDrvt_deal_bonus());//交易分红

                textView6.setText(getString(R.string.myprofile_h37) + response.getService_center().getShow_created_at());//申请时间

                textView8.setText(response.getService_center().getAddr());//地址
                textView9.setText(response.getService_center().getAddr_detail() + "  "
                        + response.getService_center().getArea() + "㎡");//地址
                textView10.setText(response.getService_center().getCode());//代码

                if (!response.getService_center().getPic1().equals(""))
                    Glide.with(ServiceCenter_YesActivity.this)
                            .load(IMGHOST + response.getService_center().getPic1())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView2);//加载图片
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.textView7:
                //查看更多
//                CommonUtil.gotoActivity(this, ServiceCenter_MoreActivity.class, false);
                break;
            case R.id.linearLayout:
                //服务码
//                CommonUtil.gotoActivity(this, ChangeServiceNumActivity.class, false);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
