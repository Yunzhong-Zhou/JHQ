package com.cho.chocoin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cho.chocoin.R;
import com.cho.chocoin.base.BaseActivity;
import com.cho.chocoin.model.WalletAddressModel;
import com.cho.chocoin.net.OkHttpClientManager;
import com.cho.chocoin.net.URLs;
import com.cho.chocoin.utils.CommonUtil;
import com.cho.chocoin.utils.MyLogger;
import com.squareup.okhttp.Request;

/**
 * Created by zyz on 2019/5/26.
 * 选择地址
 */
public class SelectAddressActivity extends BaseActivity {
    WalletAddressModel model;
    LinearLayout linearLayout1_0, linearLayout1_1, linearLayout2_0, linearLayout2_1;
    TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectaddress);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {
        linearLayout1_0 = findViewByID_My(R.id.linearLayout1_0);
        linearLayout1_1 = findViewByID_My(R.id.linearLayout1_1);
        linearLayout2_0 = findViewByID_My(R.id.linearLayout2_0);
        linearLayout2_1 = findViewByID_My(R.id.linearLayout2_1);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);

    }

    @Override
    protected void initData() {

    }
    private void Request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.WalletAddress + string, new OkHttpClientManager.ResultCallback<WalletAddressModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(WalletAddressModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>选择币地址" + response);
                model = response;
                if (!model.getEth_wallet_addr().equals("")){
                    //有ETH地址
                    linearLayout1_0.setVisibility(View.GONE);
                    linearLayout1_1.setVisibility(View.VISIBLE);
                    textView1.setText(model.getEth_wallet_addr());
                }else {
                    linearLayout1_0.setVisibility(View.VISIBLE);
                    linearLayout1_1.setVisibility(View.GONE);
                }
                if (!model.getCho_wallet_addr().equals("")){
                    //有GUS地址
                    linearLayout2_0.setVisibility(View.GONE);
                    linearLayout2_1.setVisibility(View.VISIBLE);
                    textView2.setText(model.getCho_wallet_addr());
                }else {
                    linearLayout2_0.setVisibility(View.VISIBLE);
                    linearLayout2_1.setVisibility(View.GONE);
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.relativeLayout1:
                //ETH地址
                bundle.putInt("type", 1);
                bundle.putString("address",model.getEth_wallet_addr());
                CommonUtil.gotoActivityWithData(SelectAddressActivity.this, SetAddressActivity.class, bundle, false);
                break;
            case R.id.relativeLayout2:
                //GUS地址
                bundle.putInt("type", 2);
                bundle.putString("address",model.getCho_wallet_addr());
                CommonUtil.gotoActivityWithData(SelectAddressActivity.this, SetAddressActivity.class, bundle, false);
                break;
        }
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        String string = "?token=" + localUserInfo.getToken();
        Request(string);
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.address_h12));
    }
}
