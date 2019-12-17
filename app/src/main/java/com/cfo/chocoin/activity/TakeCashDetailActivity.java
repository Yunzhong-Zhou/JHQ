package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.model.TakeCashDetailModel;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

/**
 * Created by zyz on 2019/5/27.
 * 提现详情
 */
public class TakeCashDetailActivity extends BaseActivity {
    String id = "";
    ProgressBar prograssBar;
    ImageView imageView1, imageView2;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8,
            textView9, textView10, textView11, textView12, textView13, textView14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takecashdetail);
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
//                textView1.setText(getString(R.string.takecash_h18) + "(" + getString(R.string.app_type_CHO) + ")");//提现个数
                textView2.setText("-" + response.getInput_money());//提现个数
//                textView3.setText("" + response.getStatus_title());//提现个数

                textView5.setText("" + response.getShow_created_at());//提现处理中时间
                textView7.setText("" + response.getShow_updated_at());//提现完成时间

                textView8.setText(response.getMember_usdt_wallet_addr());//提现地址
//                textView9.setText(response.getTxid());//txid

                textView9.setText(response.getMoney() + getString(R.string.recharge_h32));//实际到账
                textView10.setText(response.getService_charge_money() + getString(R.string.recharge_h32));//手续费
                textView11.setText(response.getCreated_at());//提现时间
                textView12.setText(response.getSn());//流水号
                textView13.setText(response.getStatus_title());//状态

                if (response.getStatus() == 2) {
                    //通过
                    textView7.setVisibility(View.VISIBLE);
                    imageView2.setImageResource(R.mipmap.ic_rechargedetail3);
                    prograssBar.setProgress(100);
                    textView6.setText(getString(R.string.takecash_h19));

                    textView6.setTextColor(getResources().getColor(R.color.green));
                    textView14.setVisibility(View.GONE);
                } else if (response.getStatus() == 3) {
                    //未通过
                    textView7.setVisibility(View.VISIBLE);
                    imageView2.setImageResource(R.mipmap.ic_rechargedetail4);
                    prograssBar.setProgress(100);
                    textView6.setText(getString(R.string.takecash_h27));
                    textView6.setTextColor(getResources().getColor(R.color.green));

                    textView14.setVisibility(View.VISIBLE);
                    textView14.setText(getString(R.string.takecash_h28) + response.getStatus_rejected_cause());
                } else {
                    //进行中
                    textView7.setVisibility(View.GONE);
                    imageView2.setImageResource(R.mipmap.ic_rechargedetail2);
                    prograssBar.setProgress(50);
                    textView6.setText(getString(R.string.takecash_h19));
                    textView6.setTextColor(getResources().getColor(R.color.black2));

                    textView14.setVisibility(View.GONE);
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
        titleView.setTitle(getString(R.string.takecash_h25));
    }
}
