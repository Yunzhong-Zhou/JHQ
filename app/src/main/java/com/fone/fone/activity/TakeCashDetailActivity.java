package com.fone.fone.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.TakeCashDetailModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

/**
 * Created by zyz on 2019/5/27.
 * 提现详情
 */
public class TakeCashDetailActivity extends BaseActivity {
    String id = "";
    ProgressBar prograssBar;
    ImageView imageView1, imageView2,imageView3;
    TextView textView,textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8,
            textView14, textView15, textView16,textView17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takecashdetail);
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
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                request("?token=" + localUserInfo.getToken()
                        + "&id=" + id);
            }

            @Override
            public void onLoadmore() {

            }
        });

        prograssBar = findViewByID_My(R.id.prograssBar);
        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
        imageView3 = findViewByID_My(R.id.imageView3);

        textView = findViewByID_My(R.id.textView);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        textView8 = findViewByID_My(R.id.textView8);


        textView14 = findViewByID_My(R.id.textView14);
        textView15 = findViewByID_My(R.id.textView15);
        textView16 = findViewByID_My(R.id.textView16);
        textView17 = findViewByID_My(R.id.textView17);

    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        requestServer();
    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(TakeCashDetailActivity.this, URLs.TakeCashDetail + string, new OkHttpClientManager.ResultCallback<TakeCashDetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(TakeCashDetailModel response) {
                MyLogger.i(">>>>>>>>>提现详情" + response);
                hideProgress();
                if (response != null) {
                    textView2.setText("" + response.getMoney());//提现个数
//                    textView5.setText("" + response.getShow_created_at());//提现处理中时间
//                    textView7.setText("" + response.getShow_updated_at());//提现完成时间

                    textView8.setText(response.getMoney_wallet_addr());//提现地址

                    textView.setText(response.getSn());//单号
                    if (response.getMoney_type() ==1){
                        imageView3.setImageResource(R.mipmap.ic_usdt_green);
                        textView16.setText(response.getService_charge_money() + getString(R.string.app_type_usdt));//手续费
                    }else {
                        imageView3.setImageResource(R.mipmap.ic_fil_green);
                        textView16.setText(response.getService_charge_money() + getString(R.string.app_type_fil));//手续费
                    }

                    textView1.setText(response.getCreated_at());//提现时间
                    textView14.setText(response.getVerify_at());//到账时间
                    textView15.setText(response.getStatus_title());//状态

                    if (response.getStatus() == 2) {
                        //通过
                        imageView2.setImageResource(R.mipmap.ic_rechargedetail3);
                        prograssBar.setProgress(100);
                        textView6.setText(getString(R.string.takecash_h19));
                        textView6.setTextColor(getResources().getColor(R.color.shengreen));

                        textView17.setVisibility(View.GONE);
                    } else if (response.getStatus() == 3) {
                        //未通过
                        imageView2.setImageResource(R.mipmap.ic_rechargedetail5);
                        prograssBar.setProgress(100);
                        textView6.setText(getString(R.string.takecash_h27));
                        textView6.setTextColor(getResources().getColor(R.color.shengreen));

                        textView17.setVisibility(View.VISIBLE);
                        textView17.setText(getString(R.string.takecash_h28) + response.getStatus_rejected_cause());
                    } else {
                        //进行中
                        imageView2.setImageResource(R.mipmap.ic_rechargedetail2);
                        prograssBar.setProgress(50);
                        textView6.setText(getString(R.string.takecash_h19));
                        textView6.setTextColor(getResources().getColor(R.color.white1));

                        textView17.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
        showProgress(true, getString(R.string.app_loading2));
        request("?token=" + localUserInfo.getToken()
                + "&id=" + id);
    }

    @Override
    protected void updateView() {
//        titleView.setTitle(getString(R.string.takecash_h25));
        titleView.setVisibility(View.GONE);
    }
}
