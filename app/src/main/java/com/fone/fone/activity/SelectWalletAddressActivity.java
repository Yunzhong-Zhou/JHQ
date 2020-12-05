package com.fone.fone.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.WalletAddressModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.squareup.okhttp.Request;

/**
 * Created by zyz on 2019/5/26.
 * 选择币地址
 */
public class SelectWalletAddressActivity extends BaseActivity {
    WalletAddressModel model;
    LinearLayout linearLayout1_0, linearLayout1_1, linearLayout2_0, linearLayout2_1;
    TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectwalletaddress);
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
                if (!model.getUsdt_wallet_addr().equals("")){
                    //有ETH地址
                    linearLayout1_0.setVisibility(View.GONE);
                    linearLayout1_1.setVisibility(View.VISIBLE);
                    textView1.setText(model.getUsdt_wallet_addr());
                }else {
                    linearLayout1_0.setVisibility(View.VISIBLE);
                    linearLayout1_1.setVisibility(View.GONE);
                }
                if (!model.getOfc_wallet_addr().equals("")){
                    //有GUS地址
                    linearLayout2_0.setVisibility(View.GONE);
                    linearLayout2_1.setVisibility(View.VISIBLE);
                    textView2.setText(model.getOfc_wallet_addr());
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
                //USDT地址
                bundle.putInt("type", 1);
                bundle.putString("address",model.getUsdt_wallet_addr());
                CommonUtil.gotoActivityWithData(SelectWalletAddressActivity.this, SetAddressActivity.class, bundle, false);
                break;
            case R.id.relativeLayout2:
                //OFC地址
                bundle.putInt("type", 3);
                bundle.putString("address",model.getOfc_wallet_addr());
                CommonUtil.gotoActivityWithData(SelectWalletAddressActivity.this, OFCSetAddressActivity.class, bundle, false);
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
