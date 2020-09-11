package com.ofc.ofc.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.ZhiYaOFCModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.MyLogger;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fafukeji01 on 2017/4/25.
 * 质押OFC
 */

public class ZhiYaOFCActivity extends BaseActivity {
    TextView textView1, textView2, textView3;
    EditText editText;

    ZhiYaOFCModel model;
    String input_money = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhiyaofc);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initView() {
        editText = findViewByID_My(R.id.editText);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);

    }

    @Override
    protected void initData() {
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
//        showProgress(true, getString(R.string.app_loading));
        Request("?token=" + localUserInfo.getToken());
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(ZhiYaOFCActivity.this, URLs.ZhiYaOFC + string, new OkHttpClientManager.ResultCallback<ZhiYaOFCModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(ZhiYaOFCModel response) {
//                showContentPage();
                model = response;
                hideProgress();
                textView1.setText(response.getInvest_cycle() + getString(R.string.app_tian));
                textView2.setText(response.getOfc_index() + "USDT");
                textView3.setText(response.getOfc_price() + "USDT");
                editText.setHint(getString(R.string.qianbao_h59) + "(" + getString(R.string.qianbao_h60) + response.getOfc_usable_money() + ")");
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout:
                //确认
                if (match()) {
                    this.showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("hk", model.getHk());
                    params.put("ofc_money", input_money);
                    params.put("token", localUserInfo.getToken());
                    RequestConfrim(params);
                }
                break;
        }
    }

    private boolean match() {
        input_money = editText.getText().toString().trim();
        if (TextUtils.isEmpty(input_money)) {
            myToast(getString(R.string.qianbao_h59));
            return false;
        }
        return true;
    }

    private void RequestConfrim(Map<String, String> params) {
        OkHttpClientManager.postAsyn(ZhiYaOFCActivity.this, URLs.ZhiYaOFC, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
                requestServer();
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>质押OFC" + response);
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    String info = jObj.getString("msg");
                    myToast(info);
                    finish();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//
            }
        }, false);

    }


    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.qianbao_h62));
    }
}
