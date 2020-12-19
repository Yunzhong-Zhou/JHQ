package com.fone.fone.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.TransferModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyz on 2019-12-17.
 * 划转
 */
public class TransferActivity extends BaseActivity {
    TransferModel model;
    ImageView imageView1,imageView2;
    TextView textView1, textView2, textView3,textView4,textView5,tv_confirm;
    EditText editText1,editText2;

    Handler handler = new Handler();
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(TransferActivity.this), 0, 0);
        findViewByID_My(R.id.left_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                    Log.v("tag", "---" + s.toString());
                }
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (!editText1.getText().toString().trim().equals("")){
                            double money = Double.valueOf(editText1.getText().toString().trim());
                            editText2.setText(String.format("%.2f", (money*0.8)));
                        }else {
//                            myToast(getString(R.string.myprofile_h48));
                            editText2.setText("0");
                        }
                    }
                };
                Log.v("tag", "(((((" + s.toString());
                handler.postDelayed(runnable, 1000);
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
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        request("?token=" + localUserInfo.getToken());
    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(TransferActivity.this, URLs.Transfer+ string, new OkHttpClientManager.ResultCallback<TransferModel>() {
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
                model = response;
                textView3.setText(getString(R.string.scavengingpayment_h3)+response.getCommon_usable_money() + "");
                textView5.setText(response.getCommon_usable_money()+"usdt");
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
            case R.id.tv_confirm:
                //确定
                if (!editText1.getText().toString().trim().equals("")) {
                    tv_confirm.setClickable(false);
                    showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("money", editText1.getText().toString().trim());
                    params.put("token", localUserInfo.getToken());
                    params.put("hk", model.getHk());
                    RequestAdd(params);
                } else {
                    myToast(getString(R.string.scavengingpayment_h2));
                }
                break;
        }
    }

    private void RequestAdd(Map<String, String> params) {
        OkHttpClientManager.postAsyn(TransferActivity.this, URLs.Transfer, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                tv_confirm.setClickable(true);
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(String response) {
                tv_confirm.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>划转提交" + response);

                showToast(getString(R.string.scavengingpayment_h10), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        finish();
//                requestServer();
//                        Bundle bundle = new Bundle();
//                        bundle.putInt("item", 2);
//                        CommonUtil.gotoActivityWithFinishOtherAllAndData(TransferActivity.this, MainActivity.class, bundle, true);

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
