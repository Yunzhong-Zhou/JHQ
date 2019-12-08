package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
            textView9, textView11, textView12, textView13, textView14, textView16, textView17, textView18;
    LinearLayout linearLayout1, linearLayout_addr, linearLayout_bank,linearLayout_eth,linearLayout_cho,linearLayout_cny;
    TextView textView_bank_1, textView_bank_2, textView_bank_3,textView_eth,textView_cho,textView_cny;

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
        textView11 = findViewByID_My(R.id.textView11);
        textView12 = findViewByID_My(R.id.textView12);
        textView13 = findViewByID_My(R.id.textView13);
        textView14 = findViewByID_My(R.id.textView14);
        textView16 = findViewByID_My(R.id.textView16);
        textView17 = findViewByID_My(R.id.textView17);
        textView18 = findViewByID_My(R.id.textView18);
        linearLayout1 = findViewByID_My(R.id.linearLayout1);

        linearLayout_addr = findViewByID_My(R.id.linearLayout_addr);
        linearLayout_bank = findViewByID_My(R.id.linearLayout_bank);
        textView_bank_1 = findViewByID_My(R.id.textView_bank_1);
        textView_bank_2 = findViewByID_My(R.id.textView_bank_2);
        textView_bank_3 = findViewByID_My(R.id.textView_bank_3);

        linearLayout_eth = findViewByID_My(R.id.linearLayout_eth);
        linearLayout_cho = findViewByID_My(R.id.linearLayout_cho);
        linearLayout_cny = findViewByID_My(R.id.linearLayout_cny);
        textView_eth = findViewByID_My(R.id.textView_eth);
        textView_cho = findViewByID_My(R.id.textView_cho);
        textView_cny = findViewByID_My(R.id.textView_cny);

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
                textView1.setText(getString(R.string.takecash_h18) + "(" + getString(R.string.app_type_CHO) + ")");//提现个数
                textView2.setText("-" + response.getInput_money());//提现个数
                textView3.setText("" + response.getStatus_title());//提现状态

                textView5.setText("" + response.getShow_created_at());//提现处理中时间
                textView7.setText("" + response.getShow_updated_at());//提现完成时间
                textView8.setText(response.getWallet_addr());//提现地址
//                textView9.setText(response.getTxid());//txid

                textView11.setText(response.getMoney() + getString(R.string.app_ge) + response.getMoney_type_title());//实际到账
                textView18.setText(response.getService_charge_money() + getString(R.string.recharge_h23));//手续费
                textView12.setText(response.getCreated_at());//提现时间
                textView13.setText(response.getSn());//流水号
                textView14.setText(response.getStatus_title());//状态

                textView_eth.setText("$ " + response.getEth_price());
                textView_cho.setText("$ " + response.getCho_price());
                textView_cny.setText("¥ " + response.getCny_price());

                if (response.getMoney_type() == 1) {
                    linearLayout_eth.setVisibility(View.VISIBLE);
                    linearLayout_cho.setVisibility(View.VISIBLE);
                    linearLayout_cny.setVisibility(View.GONE);

                    textView16.setText(getString(R.string.takecash_h22));
//                    linearLayout1.setVisibility(View.VISIBLE);//实际到账

                    linearLayout_addr.setVisibility(View.VISIBLE);
                    linearLayout_bank.setVisibility(View.GONE);
                } else if (response.getMoney_type() == 2) {
                    linearLayout_eth.setVisibility(View.GONE);
                    linearLayout_cho.setVisibility(View.VISIBLE);
                    linearLayout_cny.setVisibility(View.GONE);

                    textView16.setText(getString(R.string.takecash_h23));
//                    linearLayout1.setVisibility(View.GONE);//实际到账

                    linearLayout_addr.setVisibility(View.VISIBLE);
                    linearLayout_bank.setVisibility(View.GONE);
                }else if (response.getMoney_type() == 3) {
                    linearLayout_eth.setVisibility(View.GONE);
                    linearLayout_cho.setVisibility(View.GONE);
                    linearLayout_cny.setVisibility(View.VISIBLE);

                    linearLayout_addr.setVisibility(View.GONE);
                    linearLayout_bank.setVisibility(View.VISIBLE);
                    textView_bank_1.setText(response.getMember_bank_title());
                    textView_bank_2.setText(response.getMember_bank_card_account());
                    textView_bank_3.setText(response.getMember_bank_card_proceeds_name());
                }


                if (response.getStatus() == 2) {
                    //通过
                    textView7.setVisibility(View.VISIBLE);
                    imageView2.setImageResource(R.mipmap.ic_rechargedetail3);
                    prograssBar.setProgress(100);
                    textView6.setText(getString(R.string.takecash_h19));
                    textView6.setTextColor(getResources().getColor(R.color.purple));
                    textView17.setVisibility(View.GONE);
                } else if (response.getStatus() == 3) {
                    //未通过
                    textView7.setVisibility(View.VISIBLE);
                    imageView2.setImageResource(R.mipmap.ic_rechargedetail4);
                    prograssBar.setProgress(100);
                    textView6.setText(getString(R.string.takecash_h27));
                    textView6.setTextColor(getResources().getColor(R.color.purple));
                    textView17.setVisibility(View.VISIBLE);
                    textView17.setText(getString(R.string.takecash_h28) + response.getStatus_rejected_cause());
                } else {
                    //进行中
                    textView7.setVisibility(View.GONE);
                    imageView2.setImageResource(R.mipmap.ic_rechargedetail2);
                    prograssBar.setProgress(50);
                    textView6.setText(getString(R.string.takecash_h19));
                    textView6.setTextColor(getResources().getColor(R.color.black2));
                    textView17.setVisibility(View.GONE);
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
