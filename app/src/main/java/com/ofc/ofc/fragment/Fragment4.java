package com.ofc.ofc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.activity.AccountDetailActivity;
import com.ofc.ofc.activity.MainActivity;
import com.ofc.ofc.activity.RechargeDetailActivity;
import com.ofc.ofc.activity.SelectAddressActivity;
import com.ofc.ofc.activity.SetAddressActivity;
import com.ofc.ofc.activity.SetTransactionPasswordActivity;
import com.ofc.ofc.base.BaseFragment;
import com.ofc.ofc.model.Fragment4Model;
import com.ofc.ofc.model.RechargeDetailModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by zyz on 2016/1/6.
 * 充值
 */
public class Fragment4 extends BaseFragment {
    //充值
    Fragment4Model model;
    int type = 1;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout_keyong;
    TextView textView1, textView2, textView_3, textView_dazhe_1, textView_dazhe_2, textView_dazhe_3;
    View view1, view2, view3;

    String input_money = "", txid = "";
    TextView textView3, textView4, textView5, textView6, textView7, textView_addr, textView_moeny, textView_shouxufei;
    EditText editText1, editText2;

    LinearLayout linearLayout_addr, linearLayout_num, linearLayout_money, linearLayout_daozhang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        /*if (MainActivity.item == 3) {
            requestServer();
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.item == 3) {
            requestServer();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if (MainActivity.item == 3) {
            requestServer();
        }*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 3) {
                    requestServer();
                }
            }
        }
    }

    @Override
    protected void initView(View view) {
//        findViewByID_My(R.id.linearLayout).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                String string = "?token=" + localUserInfo.getToken();
                request(string);
            }

            @Override
            public void onLoadmore() {

            }
        });
        linearLayout_addr = findViewByID_My(R.id.linearLayout_addr);
        textView_addr = findViewByID_My(R.id.textView_addr);
        linearLayout_num = findViewByID_My(R.id.linearLayout_num);
        linearLayout_money = findViewByID_My(R.id.linearLayout_money);
        textView_moeny = findViewByID_My(R.id.textView_moeny);
        linearLayout_daozhang = findViewByID_My(R.id.linearLayout_daozhang);
        linearLayout_keyong = findViewByID_My(R.id.linearLayout_keyong);

        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout_keyong.setOnClickListener(this);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView_3 = findViewByID_My(R.id.textView_3);
        textView_dazhe_1 = findViewByID_My(R.id.textView_dazhe_1);
        textView_dazhe_2 = findViewByID_My(R.id.textView_dazhe_2);
        textView_dazhe_3 = findViewByID_My(R.id.textView_dazhe_3);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);


        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        textView_shouxufei = findViewByID_My(R.id.textView_shouxufei);
        textView7.setOnClickListener(this);

        //输入监听
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (type == 2) {
                    if (!editText2.getText().toString().trim().equals("")) {
                        input_money = editText2.getText().toString().trim();
                        if (Double.valueOf(input_money) >= 50) {
                            MyLogger.i(">>>>>输入币数>>>>>" + input_money);
                            //实际到账  =  个数 -50 *  汇率
                            double real_money = (Double.valueOf(input_money) - 50) * Double.valueOf(model.getAud_conver_usd());
                            MyLogger.i(">>>>>实际到账>>>>>" + real_money);

                            textView_moeny.setText(String.format("%.2f", real_money));
                        }else {
                            textView_moeny.setText("0");
                        }

                    } else {
                        textView_moeny.setText("0");
                    }
                }

            }
        });

        LinearLayout linearLayout = findViewByID_My(R.id.linearLayout);
        /*LinearLayout.LayoutParams sp_params = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        sp_params.height = CommonUtil.getScreenHeight(getActivity()) / 4;
        linearLayout.setLayoutParams(sp_params);*/

        //动态设置linearLayout的高度为屏幕高度的1/4
        ViewGroup.LayoutParams lp = linearLayout.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(getActivity()) / 4;

    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment4 + string, new OkHttpClientManager.ResultCallback<Fragment4Model>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                MainActivity.isOver = true;
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(Fragment4Model response) {
                MyLogger.i(">>>>>>>>>充值" + response);
                model = response;
                hideProgress();
                textView3.setText(response.getUsable_money());//可用余币
                textView5.setText(getString(R.string.fragment4_h6) + response.getUsdt_price());//可用余币
                    /*//首次充币
                    if (Double.valueOf(response.getPrincipal_money()) == 0) {
                        dialog.contentView(R.layout.dialog_firstrecharge)
                                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT))
                                .animType(BaseDialog.AnimInType.CENTER)
                                .canceledOnTouchOutside(true)
                                .dimAmount(0.8f)
                                .show();
                        TextView textView1 = dialog.findViewById(R.id.textView1);
                        textView1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }*/

                changeUI();
                MainActivity.isOver = true;
            }
        });
    }

    @Override
    protected void initData() {
//        requestServer();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.linearLayout2:
                type = 2;
                changeUI();
                break;
            case R.id.linearLayout3:
                type = 3;
                changeUI();
                break;
            case R.id.textView7:
                //充值
                if (match()) {
                    textView7.setClickable(false);
                    showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
//                    params.put("qk", qk);
                    /*if (type == 1) {
                        if (model.getEth().getWallet_addr() != null)
                            params.put("wallet_addr_id", model.getEth().getWallet_addr().getId());
                    } else {
                        if (model.getCho().getWallet_addr() != null)
                            params.put("wallet_addr_id", model.getCho().getWallet_addr().getId());
                    }*/
                    params.put("type", type + "");
                    params.put("input_money", input_money);
//                    params.put("pay_type", "");
//                    params.put("txid", txid);//txid
                    params.put("token", localUserInfo.getToken());
//                    params.put("trade_password", password);//交易密码（不能小于6位数）
                    RequestRecharge(params);//充值
                }
                break;
            case R.id.linearLayout_keyong:
                //可用 - 跳转我的钱包
                CommonUtil.gotoActivity(getActivity(), AccountDetailActivity.class, false);
                break;
        }

    }

    //充值
    private void RequestRecharge(Map<String, String> params) {
        OkHttpClientManager.postAsyn(getActivity(), URLs.Fragment4, params, new OkHttpClientManager.ResultCallback<RechargeDetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                textView7.setClickable(true);
                hideProgress();
                if (!info.equals("")) {
                    if (info.contains(getString(R.string.password_h1))) {
                        showToast(getString(R.string.password_h2),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(getActivity(), SetTransactionPasswordActivity.class, false);
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
                                        CommonUtil.gotoActivity(getActivity(), SetAddressActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    } else if (info.contains(getString(R.string.password_h7))) {
                        showToast(getString(R.string.password_h8),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("type", 2);//1、服务中心 2、实名认证
                                        CommonUtil.gotoActivityWithData(getActivity(), SelectAddressActivity.class, bundle, false);
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

            }

            @Override
            public void onResponse(RechargeDetailModel response) {
                textView7.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>充值" + response);
                myToast(getString(R.string.fragment4_h9));
                editText1.setText("");
                editText2.setText("");

//                requestServer();
                Bundle bundle = new Bundle();
                bundle.putString("id", response.getId());
                CommonUtil.gotoActivityWithData(getActivity(), RechargeDetailActivity.class, bundle, false);
            }
        });
    }

    private boolean match() {
        input_money = "";
        if (type == 1) {
            input_money = editText1.getText().toString().trim();
            if (TextUtils.isEmpty(input_money)) {
                myToast(getString(R.string.fragment4_h4));
                return false;
            }
        } else {
            input_money = editText2.getText().toString().trim();
            if (TextUtils.isEmpty(input_money)) {
                myToast(getString(R.string.fragment4_h5));
                return false;
            }
        }


        return true;
    }

    private void changeUI() {
//        editText1.setText("");
//        editText2.setText("");
        if (type == 1) {
            //USDT - 显示充币地址-显示个数-隐藏金额-隐藏到账-隐藏USDT价格
            textView1.setTextColor(getResources().getColor(R.color.green));
            textView2.setTextColor(getResources().getColor(R.color.black4));
            textView_3.setTextColor(getResources().getColor(R.color.black4));

            textView_dazhe_1.setBackgroundResource(R.mipmap.bg_dazhe_1);
            textView_dazhe_2.setBackgroundResource(R.mipmap.bg_dazhe_0);
            textView_dazhe_3.setBackgroundResource(R.mipmap.bg_dazhe_0);

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.INVISIBLE);

            linearLayout_addr.setVisibility(View.VISIBLE);//显示充币地址
            textView_addr.setText(getString(R.string.fragment4_h2));//充币地址
            textView4.setText(getString(R.string.fragment4_h14));//请在提交后，查看充币地址
            linearLayout_num.setVisibility(View.VISIBLE);//显示个数
            linearLayout_money.setVisibility(View.GONE);//隐藏金额
            linearLayout_daozhang.setVisibility(View.GONE);//隐藏到账
            textView5.setVisibility(View.GONE);//隐藏USDT价格
            textView_shouxufei.setVisibility(View.GONE);//隐藏手续费

            textView7.setText(getString(R.string.fragment4_h8));
            textView6.setText(getString(R.string.fragment4_h7));
            editText1.setHint(getString(R.string.fragment4_h4)
                    + "(" + model.getUsdt_top_up_min_money() + "-" +
                    model.getUsdt_top_up_max_money() + ")");

            textView7.setVisibility(View.VISIBLE);

        } else if (type == 2) {
            //澳元电汇- 显示电汇信息-隐藏个数-显示金额-显示到账-显示USDT价格
            textView1.setTextColor(getResources().getColor(R.color.black4));
            textView2.setTextColor(getResources().getColor(R.color.green));
            textView_3.setTextColor(getResources().getColor(R.color.black4));

            textView_dazhe_1.setBackgroundResource(R.mipmap.bg_dazhe_0);
            textView_dazhe_2.setBackgroundResource(R.mipmap.bg_dazhe_1);
            textView_dazhe_3.setBackgroundResource(R.mipmap.bg_dazhe_0);

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            view3.setVisibility(View.INVISIBLE);

            linearLayout_addr.setVisibility(View.VISIBLE);//显示电汇信息
            textView_addr.setText(getString(R.string.fragment4_h16));//电汇信息
            textView4.setText(getString(R.string.fragment4_h18));//请在提交后，查看电汇账号
            linearLayout_num.setVisibility(View.GONE);//隐藏个数
            linearLayout_money.setVisibility(View.VISIBLE);//显示金额
            linearLayout_daozhang.setVisibility(View.VISIBLE);//显示到账
            textView5.setVisibility(View.VISIBLE);//显示USDT价格
            textView_shouxufei.setVisibility(View.VISIBLE);//显示手续费

            textView7.setText(getString(R.string.fragment4_h29));
            textView6.setText(getString(R.string.fragment4_h13));
            editText2.setHint(getString(R.string.fragment4_h19));

            textView7.setVisibility(View.VISIBLE);

        } else if (type == 3) {
            /*textView1.setTextColor(getResources().getColor(R.color.black3));
            textView2.setTextColor(getResources().getColor(R.color.black3));
            textView_3.setTextColor(getResources().getColor(R.color.green));

            textView_dazhe_1.setBackgroundResource(R.mipmap.bg_dazhe_0);
            textView_dazhe_2.setBackgroundResource(R.mipmap.bg_dazhe_0);
            textView_dazhe_3.setBackgroundResource(R.mipmap.bg_dazhe_1);

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.VISIBLE);

            linearLayout_addr.setVisibility(View.GONE);

//            textView4.setText(model.getFiat().getWallet_addr());
            *//*if (model.getCho().getWallet_addr() != null)
                textView4.setText(model.getCho().getWallet_addr().getWallet_addr());//地址
            else
                textView4.setText("");//地址*//*

            textView5.setVisibility(View.VISIBLE);
            textView6.setText(getString(R.string.fragment4_h27));
            textView_num.setText(getString(R.string.fragment4_h15));
            editText1.setHint(getString(R.string.fragment4_h16)
                    + "(" + model.getCny().getTop_up_min_money() + "-" +
                    model.getCny().getTop_up_max_money() + ")");

            textView7.setVisibility(View.GONE);*/

        }
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void requestServer() {
        super.requestServer();
        showProgress(true, getString(R.string.app_loading));
        request("?token=" + localUserInfo.getToken());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
