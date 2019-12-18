package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.model.TransferModel;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.CommonUtil;
import com.cfo.chocoin.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyz on 2019-12-17.
 * 划转
 */
public class TransferActivity extends BaseActivity {
    TextView textView1, textView2, textView3;
    EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        findViewByID_My(R.id.titleView).setPadding(0, (int) CommonUtil.getStatusBarHeight(TransferActivity.this), 0, 0);

    }

    @Override
    protected void initView() {
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);

        editText1 = findViewByID_My(R.id.editText1);
    }

    @Override
    protected void initData() {
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        request("?token=" + localUserInfo.getToken());
    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(TransferActivity.this, URLs.Transfer_Add + string, new OkHttpClientManager.ResultCallback<TransferModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(TransferModel response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>划转" + response);
                textView1.setText(response.getCommon_usable_money() + "");
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
            case R.id.textView3:
                //确定
                if (!editText1.getText().toString().trim().equals("")) {
                    textView3.setClickable(false);
                    showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("money", editText1.getText().toString().trim());
                    params.put("token", localUserInfo.getToken());
                    RequestAdd(params);//充值
                } else {
                    myToast(getString(R.string.scavengingpayment_h15));
                }
                break;
        }
    }

    private void RequestAdd(Map<String, String> params) {
        OkHttpClientManager.postAsyn(TransferActivity.this, URLs.Transfer_Add, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                textView3.setClickable(true);
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(String response) {
                textView3.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>划转提交" + response);
                showToast(getString(R.string.scavengingpayment_h20), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
//                requestServer();
                        Bundle bundle = new Bundle();
                        bundle.putInt("item", 2);
                        CommonUtil.gotoActivityWithFinishOtherAllAndData(TransferActivity.this, MainActivity.class, bundle, true);

                    }
                });

            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
