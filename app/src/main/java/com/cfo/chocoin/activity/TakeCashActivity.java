package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.model.AvailableAmountModel;
import com.cfo.chocoin.model.TakeCashModel;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.CommonUtil;
import com.cfo.chocoin.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by zyz on 2017/9/4.
 * 提币
 */

public class TakeCashActivity extends BaseActivity {
    int money_type = 3;
    LinearLayout linearLayout1, linearLayout2, linearLayout_3;
    TextView textView1, textView2, textView_3;
    View view1, view2, view_3;

    TextView textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11;
    EditText editText1, editText2;
    LinearLayout linearLayout3;
    String money = "", password = "", code = "";
    AvailableAmountModel model;

    String input_money = "";

    LinearLayout linearLayout_addr, linearLayout_bank1, linearLayout_bank2;
    TextView textView_bank_1, textView_bank_2, textView_bank_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takecash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                String string = "?token=" + localUserInfo.getToken()
                        + "&money_type=" + money_type;
                RequestAvailableAmount(string);//获取可用币数
            }

            @Override
            public void onLoadmore() {

            }
        });
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout_3 = findViewByID_My(R.id.linearLayout_3);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout_3.setOnClickListener(this);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView_3 = findViewByID_My(R.id.textView_3);

        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view_3 = findViewByID_My(R.id.view_3);

        linearLayout_addr = findViewByID_My(R.id.linearLayout_addr);
        linearLayout_bank1 = findViewByID_My(R.id.linearLayout_bank1);
        linearLayout_bank2 = findViewByID_My(R.id.linearLayout_bank2);
        linearLayout_bank1.setOnClickListener(this);
        linearLayout_bank2.setOnClickListener(this);
        textView_bank_1 = findViewByID_My(R.id.textView_bank_1);
        textView_bank_2 = findViewByID_My(R.id.textView_bank_2);
        textView_bank_3 = findViewByID_My(R.id.textView_bank_3);

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
        textView10.setText(getString(R.string.takecash_h6) + "0" + getString(R.string.app_ge) + getString(R.string.app_type_CHO));

        textView11 = findViewByID_My(R.id.textView11);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
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
                    MyLogger.i(">>>>>输入币数>>>>>" + input_money);
                    //手续费 = 输入币数 * 手续费率 /100
                    double service_money = Double.valueOf(input_money) * Double.valueOf(model.getWithdrawal_service_charge()) / 100;
                    MyLogger.i(">>>>>手续费>>>>>" + service_money);
                    //实际到账 =  (输入币数 - 手续费)  *  cho_price  /  eth_price
                    double real_money = (Double.valueOf(input_money) - service_money)
                            * Double.valueOf(model.getCho_price())
                            / Double.valueOf(model.getEth_price());
                    MyLogger.i(">>>>>实际到账>>>>>" + real_money);
                    double real_money3 = (Double.valueOf(input_money) - service_money)
                            * Double.valueOf(model.getCny_price());
                    if (money_type == 1) {
                        textView7.setText(String.format("%.2f", real_money) + getString(R.string.app_ge) + getString(R.string.app_type_ETH));//实际到账
                    } else if (money_type == 3) {
                        textView7.setText("¥ " + String.format("%.2f", real_money3));//实际到账
                    }
                    //手续费
                    textView10.setText(getString(R.string.takecash_h6)
                            + String.format("%.2f", service_money)
                            + getString(R.string.app_ge)
                            + getString(R.string.app_type_CHO));

                } else {
                    textView7.setText("");//实际到账
                    //手续费
                    textView10.setText(getString(R.string.takecash_h6) + "0"
                            + getString(R.string.app_ge) + getString(R.string.app_type_CHO));
                }
            }
        });

//        LinearLayout linearLayout = findViewByID_My(R.id.linearLayout);
        /*LinearLayout.LayoutParams sp_params = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        sp_params.height = CommonUtil.getScreenHeight(getActivity()) / 4;
        linearLayout.setLayoutParams(sp_params);*/

        //动态设置linearLayout的高度为屏幕高度的1/4
        ViewGroup.LayoutParams lp = linearLayout_addr.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(TakeCashActivity.this) / 4;


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout1:
                money_type = 1;
                requestServer();
                break;
            case R.id.linearLayout2:
                money_type = 2;
                requestServer();
                break;
            case R.id.linearLayout_3:
                money_type = 3;
                requestServer();
                break;
            case R.id.textView9:
                if (match()) {
                    textView9.setClickable(false);
                    showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("money_type", money_type + "");
                    params.put("trade_password", password);//交易密码（不能小于6位数）
                    params.put("input_money", money);//提现金额
                    params.put("token", localUserInfo.getToken());
                    params.put("hk", model.getHk());
                    RequestTakeCash(params);//提现
                }
                break;
            case R.id.textView11:
                CommonUtil.gotoActivity(TakeCashActivity.this, SelectAddressActivity.class);
                break;
            case R.id.linearLayout_bank1:
                CommonUtil.gotoActivity(TakeCashActivity.this, BankCardSettingActivity.class);
                break;
            case R.id.linearLayout_bank2:
                CommonUtil.gotoActivity(TakeCashActivity.this, BankCardSettingActivity.class);
                break;
        }
    }

    private void changeUI() {
        editText1.setText("");
        textView7.setText("");
        if (money_type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.blue));
            textView2.setTextColor(getResources().getColor(R.color.black4));
            textView_3.setTextColor(getResources().getColor(R.color.black4));

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view_3.setVisibility(View.INVISIBLE);

            textView4.setText(getString(R.string.takecash_h2));
            textView6.setVisibility(View.VISIBLE);
            linearLayout3.setVisibility(View.VISIBLE);

            linearLayout_addr.setVisibility(View.VISIBLE);
            linearLayout_bank1.setVisibility(View.GONE);
            linearLayout_bank2.setVisibility(View.GONE);

        } else if (money_type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.black4));
            textView2.setTextColor(getResources().getColor(R.color.blue));
            textView_3.setTextColor(getResources().getColor(R.color.black4));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            view_3.setVisibility(View.INVISIBLE);

            textView4.setText(getString(R.string.takecash_h17));
            textView6.setVisibility(View.GONE);
            linearLayout3.setVisibility(View.GONE);

            linearLayout_addr.setVisibility(View.VISIBLE);
            linearLayout_bank1.setVisibility(View.GONE);
            linearLayout_bank2.setVisibility(View.GONE);

        } else if (money_type == 3) {
            textView1.setTextColor(getResources().getColor(R.color.black4));
            textView2.setTextColor(getResources().getColor(R.color.black4));
            textView_3.setTextColor(getResources().getColor(R.color.blue));

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view_3.setVisibility(View.VISIBLE);

            textView4.setText(getString(R.string.takecash_h2));
            textView6.setVisibility(View.GONE);
            linearLayout3.setVisibility(View.VISIBLE);

            linearLayout_addr.setVisibility(View.GONE);
            if (!model.getBank_card_account().equals("")) {
                linearLayout_bank1.setVisibility(View.VISIBLE);
                linearLayout_bank2.setVisibility(View.GONE);
            } else {
                linearLayout_bank1.setVisibility(View.GONE);
                linearLayout_bank2.setVisibility(View.VISIBLE);
            }

        }
    }

    //可用余额
    private void RequestAvailableAmount(String string) {
        OkHttpClientManager.getAsyn(TakeCashActivity.this, URLs.TakeCash + string, new OkHttpClientManager.ResultCallback<AvailableAmountModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(AvailableAmountModel response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>可用余额" + response);

                model = response;
                if (model.getWallet_addr() != null && !model.getWallet_addr().equals("")) {
                    textView3.setVisibility(View.VISIBLE);
                    textView11.setVisibility(View.GONE);
                    textView3.setText(response.getWallet_addr());//地址
                } else {
                    textView3.setVisibility(View.GONE);
                    textView11.setVisibility(View.VISIBLE);
                }
                editText1.setHint(getString(R.string.takecash_h8)
                        + "(" + model.getMin_withdrawal_money() + "-" +
                        model.getMax_withdrawal_money() + ")");

                textView5.setText(getString(R.string.takecash_h3) + response.getCommon_usable_money() + " " + getString(R.string.app_type_CHO));//可用余额
                textView6.setText(getString(R.string.takecash_h4) + "$" + response.getEth_price());//ETH价格
                textView8.setText(response.getWithdrawal_service_charge() + "%");//手续费

                if (!model.getBank_card_account().equals("")) {
                    textView_bank_1.setText(model.getBank_title());
                    textView_bank_2.setText(model.getBank_card_account());
                    textView_bank_3.setText(model.getBank_card_proceeds_name());
                }

                changeUI();

            }
        });
    }

    //提现
    private void RequestTakeCash(Map<String, String> params) {
        OkHttpClientManager.postAsyn(TakeCashActivity.this, URLs.TakeCash, params, new OkHttpClientManager.ResultCallback<TakeCashModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                textView9.setClickable(true);
                hideProgress();
                if (!info.equals("")) {
                    if (info.contains(getString(R.string.password_h1))) {
                        showToast(getString(R.string.password_h2),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(TakeCashActivity.this, SetTransactionPasswordActivity.class, false);
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
                                        CommonUtil.gotoActivity(TakeCashActivity.this, SelectAddressActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    }else if (info.contains(getString(R.string.password_h7))) {
                        showToast(getString(R.string.password_h8),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(TakeCashActivity.this, SelectAddressActivity.class, false);
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
            public void onResponse(TakeCashModel response) {
                textView9.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>提现" + response);
                myToast(getString(R.string.takecash_h13));
                Bundle bundle = new Bundle();
                bundle.putString("id", response.getId());
                CommonUtil.gotoActivityWithData(TakeCashActivity.this, TakeCashDetailActivity.class, bundle, true);
            }
        }, true);
    }

    private boolean match() {
        money = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(money)) {
            myToast(getString(R.string.takecash_h8));
            return false;
        }
        password = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            myToast(getString(R.string.takecash_h10));
            return false;
        }
        return true;
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading2));
        String string = "?token=" + localUserInfo.getToken()
                + "&money_type=" + money_type;
        RequestAvailableAmount(string);//获取可用币数
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.takecash_h1));
    }

}
