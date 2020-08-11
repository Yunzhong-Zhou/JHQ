package com.ofc.ofc.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.HuiGouOFCModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fafukeji01 on 2017/4/25.
 * 回购OFC
 */

public class HuiGouOFCActivity extends BaseActivity {
    TextView textView1, textView2;
    EditText editText;

    HuiGouOFCModel model;
    String input_money = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huigouofc);

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

        //输入监听
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editText.getText().toString().trim().equals("")) {
                    input_money = editText.getText().toString().trim();
                    if (Double.valueOf(input_money) > 0) {
                        MyLogger.i(">>>>>输入币数>>>>>" + input_money);
                        //实际到账  =  个数  *  汇率
                        double real_money = Double.valueOf(input_money) * Double.valueOf(model.getOfc_price());
                        MyLogger.i(">>>>>实际到账>>>>>" + real_money);

                        textView2.setText(String.format("%.2f", real_money)+"USDt");
                    } else {
                        textView2.setText("0USDt");
                    }

                } else {
                    textView2.setText("0USDt");
                }

            }
        });
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
        OkHttpClientManager.getAsyn(HuiGouOFCActivity.this, URLs.HuiGouOFC + string, new OkHttpClientManager.ResultCallback<HuiGouOFCModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(HuiGouOFCModel response) {
//                showContentPage();
                model = response;
                hideProgress();
                textView1.setText(response.getOfc_price() + "USDT");
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
                    params.put("money", input_money);
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
        OkHttpClientManager.postAsyn(HuiGouOFCActivity.this, URLs.HuiGouOFC, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>回购OFC" + response);
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    String info = jObj.getString("msg");
                    myToast(info);
//                    finish();
                    CommonUtil.gotoActivity(HuiGouOFCActivity.this, HuiGouListActivity.class, false);
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
        titleView.setTitle(getString(R.string.qianbao_h56));
        titleView.showRightTextview(getString(R.string.qianbao_h66), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.gotoActivity(HuiGouOFCActivity.this, HuiGouListActivity.class, false);
            }
        });
    }
}
