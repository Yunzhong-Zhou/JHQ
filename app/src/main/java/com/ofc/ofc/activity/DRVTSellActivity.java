package com.ofc.ofc.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.DRVTSellModel;
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
 * Created by Mr.Z on 2020/10/25.
 * DRVT出售
 */
public class DRVTSellActivity extends BaseActivity {
    EditText editText1, editText2;
    TextView textView1, textView2, textView3;
    LinearLayout linearLayout;

    DRVTSellModel model;
    String drvt_buy_id = "",input_money = "", drvt_price = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drvtsell);
        findViewByID_My(R.id.title).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        linearLayout = findViewByID_My(R.id.linearLayout);

        //输入监听
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editText1.getText().toString().trim().equals("")) {
                    input_money = editText1.getText().toString().trim();
//                    if (!editText2.getText().toString().trim().equals("")){
                        if (Double.valueOf(input_money) > 0) {
                            MyLogger.i(">>>>>输入币数>>>>>" + input_money);
                            //实际到账  =  个数  *  汇率
                            double real_money = Double.valueOf(input_money) * Double.valueOf(model.getOfc_price());
//                            double real_money = Double.valueOf(input_money) * Double.valueOf(editText2.getText().toString().trim());
                            MyLogger.i(">>>>>实际到账>>>>>" + real_money);

                            textView1.setText(String.format("%.2f", real_money) + "USDT");
                        } else {
                            textView1.setText("0USDT");
                        }

//                    }else {
//                        textView1.setText("0USDT");
////                        myToast(getString(R.string.qianbao_h104));
//                    }

                } else {
                    textView1.setText("0USDT");
                }

            }
        });
    }

    @Override
    protected void initData() {
        drvt_buy_id = getIntent().getStringExtra("id");
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
//        showProgress(true, getString(R.string.app_loading));
        Request("?token=" + localUserInfo.getToken()+
                "&drvt_buy_id="+drvt_buy_id);
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(DRVTSellActivity.this, URLs.DRVTSell + string, new OkHttpClientManager.ResultCallback<DRVTSellModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(DRVTSellModel response) {
//                showContentPage();
                model = response;
                hideProgress();

                drvt_price = response.getOfc_price();

//                editText2.setHint(getString(R.string.qianbao_h113) + response.getOfc_price());
                editText2.setText(response.getOfc_price());
                textView2.setText(getString(R.string.qianbao_h111) +":"+ response.getResidue_money());
                textView3.setText(getString(R.string.qianbao_h107) + response.getOfc_usable_money()+"DRVT");
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.linearLayout:
                //购买
                if (match()) {
                    this.showProgress(true, getString(R.string.app_loading1));
                    linearLayout.setClickable(false);
                    HashMap<String, String> params = new HashMap<>();
                    params.put("hk", model.getHk());
                    params.put("drvt_buy_id", drvt_buy_id);
                    params.put("money", input_money);
//                    params.put("drvt_price", drvt_price);
                    params.put("token", localUserInfo.getToken());
                    RequestConfrim(params);
                }
                break;

        }
    }

    private boolean match() {
        input_money = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(input_money)) {
            myToast(getString(R.string.qianbao_h102));
            return false;
        }
        /*drvt_price = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(drvt_price)) {
            myToast(getString(R.string.qianbao_h104));
            return false;
        }*/
        return true;
    }

    private void RequestConfrim(Map<String, String> params) {
        OkHttpClientManager.postAsyn(DRVTSellActivity.this, URLs.DRVTSell, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
                linearLayout.setClickable(true);
                requestServer();
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>DRVT出售" + response);
                linearLayout.setClickable(true);
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
        titleView.setVisibility(View.GONE);
    }
}
