package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.model.BuyComputingPowerModel;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.CommonUtil;
import com.cfo.chocoin.utils.MyLogger;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyz on 2019/5/27.
 * 购买算力
 */
public class BuyComputingPowerActivity extends BaseActivity {
    BuyComputingPowerModel model;
    EditText editText1;
    TextView textView1, textView2, textView3, textView4, textView5;
    String money = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buycomputingpower);
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
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
                    money = editText1.getText().toString().trim();
                    MyLogger.i(">>>>>输入币数>>>>>" + money);
                    MyLogger.i(">>>>>算力等级>>>>>" + getGrade(Integer.valueOf(money)));
                    MyLogger.i(">>>>>算力倍数>>>>>" + getBonusMultiple(Integer.valueOf(money)));
                    MyLogger.i(">>>>>算力产值>>>>>" + Integer.valueOf(money) * getBonusMultiple(Integer.valueOf(money)));
                    //倍数
                    if (Integer.valueOf(money) >= 100 ){
                        textView2.setText("" + getGrade(Integer.valueOf(money)));
                        textView3.setText("" + Integer.valueOf(money) * getBonusMultiple(Integer.valueOf(money)));
                        textView4.setText("" + getBonusMultiple(Integer.valueOf(money)));
                    }else {
                        textView2.setText("0");
                        textView3.setText("0");
                        textView4.setText("0");
                    }

                } else {
                    textView2.setText("0");
                    textView3.setText("0");
                    textView4.setText("0");

                }
            }
        });
    }

    private int getGrade(int money) {
        if (money >= 100 && money <= 1000) {
            return 1;
        } else if (money >= 1001 && money <= 5000) {
            return 2;
        } else if (money >= 5001 && money <= 10000) {
            return 3;
        } else if (money >= 10001 && money <= 20000) {
            return 4;
        } else {
            return 5;
        }
    }

    private double getBonusMultiple(int money) {
        if (money >= 100 && money <= 1000) {
            return 1.5;
        } else if (money >= 1001 && money <= 5000) {
            return 2;
        } else if (money >= 5001 && money <= 10000) {
            return 2.5;
        } else if (money >= 10001 && money <= 20000) {
            return 3;
        } else {
            return 3.5;
        }
    }


    @Override
    protected void initData() {
        requestServer();
    }

    //可用余额
    private void Request(String string) {
        OkHttpClientManager.getAsyn(BuyComputingPowerActivity.this, URLs.BuyComputingPower + string, new OkHttpClientManager.ResultCallback<BuyComputingPowerModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(BuyComputingPowerModel response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>可用余额" + response);

                model = response;
                textView1.setText(getString(R.string.recharge_h2) + response.getUsable_money2());
                editText1.setHint(getString(R.string.recharge_h9) + "(" + response.getMin_invest_money()
                        + "-" + response.getMax_invest_money() + ")");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView5:
                money = editText1.getText().toString().trim();
                if (!money.equals("")) {
                    textView5.setClickable(false);
                    showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("money", money);//提现金额
                    params.put("token", localUserInfo.getToken());
                    params.put("hk", model.getHk());
                    RequestBuy(params);//提现
                } else {
                    myToast(getString(R.string.recharge_h9));
                }
                break;
        }
    }

    private void RequestBuy(Map<String, String> params) {
        OkHttpClientManager.postAsyn(BuyComputingPowerActivity.this, URLs.BuyComputingPower, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                textView5.setClickable(true);
                hideProgress();
                if (!info.equals("")) {
                    if (info.contains(getString(R.string.password_h1))) {
                        showToast(getString(R.string.password_h2),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(BuyComputingPowerActivity.this, SetTransactionPasswordActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    } else if (info.contains(getString(R.string.password_h3))) {
                        showToast(getString(R.string.password_h4),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(BuyComputingPowerActivity.this, SelectAddressActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    } else {
                        showToast(info);
                    }
                }
                requestServer();
            }

            @Override
            public void onResponse(String response) {
                textView5.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>购买算力" + response);
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    String info = jObj.getString("msg");
                    myToast(info);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                finish();
                /*Bundle bundle = new Bundle();
                bundle.putString("id", response.getId());
                CommonUtil.gotoActivityWithData(BuyComputingPowerActivity.this, TakeCashDetailActivity.class, bundle, true);*/
            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        String string = "?token=" + localUserInfo.getToken();
        Request(string);//获取可用币数
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.recharge_h3));
    }
}
