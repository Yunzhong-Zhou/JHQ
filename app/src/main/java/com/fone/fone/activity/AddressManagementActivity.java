package com.fone.fone.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.AddressManagementModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.squareup.okhttp.Request;

/**
 * Created by zyz on 2020-03-02.
 * 币地址管理
 */
public class AddressManagementActivity extends BaseActivity {
    AddressManagementModel model;
    RelativeLayout relativeLayout1, relativeLayout2, relativeLayout3;
    LinearLayout linearLayout1, linearLayout2, linearLayout3;
    TextView type1_tv1, type1_tv2, type1_tv3, type2_tv1, type3_tv1;
//    View view_1,view_2,view_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressmanagement);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgress(true, getString(R.string.app_loading2));
        request("?token=" + localUserInfo.getToken());
    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.AddressManage + string,
                new OkHttpClientManager.ResultCallback<AddressManagementModel>() {
                    @Override
                    public void onError(Request request, String info, Exception e) {
                        hideProgress();
                        if (!info.equals("")) {
                            myToast(info);
                        }
                    }

                    @Override
                    public void onResponse(AddressManagementModel response) {
                        MyLogger.i(">>>>>>>>>地址" + response);
                        hideProgress();
                        model = response;
                        //是否显示是提币方式
                        /*if (response.getWithdrawal_cny_switch().equals("1")){//关闭
                            relativeLayout1.setVisibility(View.GONE);
                            linearLayout1.setVisibility(View.GONE);
                            view_1.setVisibility(View.GONE);
                        }else {

                            view_1.setVisibility(View.VISIBLE);
                            if (!model.getMember_bank_card_account().equals("")) {
                                relativeLayout1.setVisibility(View.GONE);
                                linearLayout1.setVisibility(View.VISIBLE);
                                type1_tv1.setText(model.getMember_bank_card_account());//银行卡号
                                type1_tv2.setText(model.getMember_bank_card_proceeds_name());//银行开户人
                                type1_tv3.setText(model.getMember_bank_title());//银行名称
                            } else {
                                relativeLayout1.setVisibility(View.VISIBLE);
                                linearLayout1.setVisibility(View.GONE);
                            }
                        }*/

                        if (model.getTrade_password().equals("")) {
                            showToast(getString(R.string.address_h25),
                                    getString(R.string.password_h5), getString(R.string.password_h6),
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            CommonUtil.gotoActivity(AddressManagementActivity.this, SetTransactionPasswordActivity.class, false);
                                        }
                                    }, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog.dismiss();
                                            finish();
                                        }
                                    });
                        }

                        if (!model.getUsdt_wallet_addr().equals("")) {
                            relativeLayout2.setVisibility(View.GONE);
                            linearLayout2.setVisibility(View.VISIBLE);
                            type2_tv1.setText(model.getUsdt_wallet_addr());//USDT地址
                        } else {
                            relativeLayout2.setVisibility(View.VISIBLE);
                            linearLayout2.setVisibility(View.GONE);
                        }

                        if (!model.getFil_wallet_addr().equals("")) {
                            relativeLayout3.setVisibility(View.GONE);
                            linearLayout3.setVisibility(View.VISIBLE);
                            type3_tv1.setText(model.getFil_wallet_addr());//FIL地址
                        } else {
                            relativeLayout3.setVisibility(View.VISIBLE);
                            linearLayout3.setVisibility(View.GONE);
                        }


                    }
                });
    }

    @Override
    protected void initView() {
        relativeLayout1 = findViewByID_My(R.id.relativeLayout1);
        relativeLayout2 = findViewByID_My(R.id.relativeLayout2);
        relativeLayout3 = findViewByID_My(R.id.relativeLayout3);
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        type1_tv1 = findViewByID_My(R.id.type1_tv1);
        type1_tv2 = findViewByID_My(R.id.type1_tv2);
        type1_tv3 = findViewByID_My(R.id.type1_tv3);
        type2_tv1 = findViewByID_My(R.id.type2_tv1);
        type3_tv1 = findViewByID_My(R.id.type3_tv1);
//        view_1 = findViewByID_My(R.id.view_1);
//        view_2 = findViewByID_My(R.id.view_2);
//        view_3 = findViewByID_My(R.id.view_3);

    }

    @Override
    protected void initData() {
//        model = (SelectAddressModel) getIntent().getSerializableExtra("SelectAddressModel");

    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        super.onClick(v);
        switch (v.getId()) {
            case R.id.relativeLayout1:
            case R.id.linearLayout1:
                //CNY
                /*if (!model.getMember_bank_card_account().equals("")) {
                    bundle.putInt("type", 1);
                    CommonUtil.gotoActivityWithData(this, TakeCashActivity.class, bundle, false);
                } else {
                    CommonUtil.gotoActivity(this, BankCardSettingActivity.class, true);
                }*/
//                CommonUtil.gotoActivity(this, BankCardSettingActivity.class, false);
                break;
            case R.id.relativeLayout2:
            case R.id.linearLayout2:
                //USDT
                /*if (!model.getUsdt_wallet_addr().equals("")) {
                    bundle.putInt("type", 2);
                    CommonUtil.gotoActivityWithData(this, TakeCashActivity.class, bundle, false);
                } else {
                    bundle.putInt("type", 2);
                    CommonUtil.gotoActivityWithData(this, SetAddressActivity.class, bundle, true);
                }*/
                bundle.putInt("type", 1);
                CommonUtil.gotoActivityWithData(this, SetAddressActivity.class, bundle, false);
                break;
            case R.id.relativeLayout3:
            case R.id.linearLayout3:
                //FIL
                /*if (!model.getBwin_wallet_addr().equals("")) {
                    bundle.putInt("type", 3);
                    CommonUtil.gotoActivityWithData(this, TakeCashActivity.class, bundle, false);
                } else {
                    bundle.putInt("type", 3);
                    CommonUtil.gotoActivityWithData(this, SetAddressActivity.class, bundle, true);
                }*/
                bundle.putInt("type", 2);
                CommonUtil.gotoActivityWithData(this, SetAddressActivity.class, bundle, false);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.addrmanage_h1));
    }
}
