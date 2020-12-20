package com.fone.fone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cy.dialog.BaseDialog;
import com.fone.fone.R;
import com.fone.fone.activity.AddressManagementActivity;
import com.fone.fone.activity.FILWalletActivity;
import com.fone.fone.activity.MainActivity;
import com.fone.fone.activity.QRCodeActivity;
import com.fone.fone.activity.RechargeDetailActivity;
import com.fone.fone.activity.SetTransactionPasswordActivity;
import com.fone.fone.activity.TakeCashActivity;
import com.fone.fone.activity.USDTWalletActivity;
import com.fone.fone.base.BaseFragment;
import com.fone.fone.model.Fragment4Model;
import com.fone.fone.model.RechargeDetailModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Mr.Z on 2016/1/6.
 * 钱包
 */
public class Fragment4 extends BaseFragment {
    TextView tv_keyong,tv_shouyi,tv_yongjin,tv_filmoney,
            tv_recharge1,tv_takecash,tv_transfer,tv_recharge2;
    LinearLayout ll_usdt,ll_fil;
    ImageView iv_usdt;
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
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                request("?token=" + localUserInfo.getToken());
            }

            @Override
            public void onLoadmore() {
            }
        });

        tv_keyong = findViewByID_My(R.id.tv_keyong);
        tv_shouyi = findViewByID_My(R.id.tv_shouyi);
        tv_yongjin = findViewByID_My(R.id.tv_yongjin);
        tv_filmoney = findViewByID_My(R.id.tv_filmoney);

        tv_recharge1 = findViewByID_My(R.id.tv_recharge1);
        tv_recharge1.setOnClickListener(this);
        tv_takecash = findViewByID_My(R.id.tv_takecash);
        tv_takecash.setOnClickListener(this);
        tv_transfer = findViewByID_My(R.id.tv_transfer);
        tv_transfer.setOnClickListener(this);
        tv_recharge2 = findViewByID_My(R.id.tv_recharge2);
        tv_recharge2.setOnClickListener(this);
        ll_usdt = findViewByID_My(R.id.ll_usdt);
        ll_usdt.setOnClickListener(this);
        ll_fil = findViewByID_My(R.id.ll_fil);
        ll_fil.setOnClickListener(this);
        iv_usdt = findViewByID_My(R.id.iv_usdt);
        iv_usdt.setOnClickListener(this);

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
                tv_keyong.setText(response.getUsable_money());//可用USDT
                tv_shouyi.setText(response.getChange_game_win_money());//拼团收益
                tv_yongjin.setText(response.getCommission_money());//佣金
                tv_filmoney.setText(response.getUsable_fil_money());//可用FIL

                hideProgress();
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
            case R.id.tv_recharge1:
            case R.id.tv_recharge2:
                //充值
                dialog.contentView(R.layout.dialog_recharge)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.BOTTOM)
                        .dimAmount(0.8f)
                        .show();
                EditText et_usdtmoney = dialog.findViewById(R.id.et_usdtmoney);
                TextView tv_confirm = dialog.findViewById(R.id.tv_confirm);
                tv_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!et_usdtmoney.getText().toString().trim().equals("")){
                            dialog.dismiss();

                            showProgress(true, getString(R.string.app_loading1));
                            HashMap<String, String> params = new HashMap<>();
                            params.put("input_money", et_usdtmoney.getText().toString().trim());//金额
                            params.put("money_type", "1");//类型（1.USDT 2.fil）
                            params.put("token", localUserInfo.getToken());
//                            params.put("hk", model.getHk());
                            RequestRecharge(params);

                        }else {
                            myToast(getString(R.string.fragment4_h13));
                        }

                    }
                });
                break;
            case R.id.tv_takecash:
                //提现
                Bundle bundle = new Bundle();
                bundle.putInt("money_type",1);
                CommonUtil.gotoActivityWithData(getActivity(), TakeCashActivity.class,bundle,false);
                break;
            case R.id.tv_transfer:
                //转账
                CommonUtil.gotoActivity(getActivity(), QRCodeActivity.class);
                break;
            case R.id.ll_usdt:
            case R.id.iv_usdt:
                //USDT
                CommonUtil.gotoActivity(getActivity(), USDTWalletActivity.class);
                break;
            case R.id.ll_fil:
                //FIL
                CommonUtil.gotoActivity(getActivity(), FILWalletActivity.class);
                break;
        }

    }

    //充值
    private void RequestRecharge(Map<String, String> params) {
        OkHttpClientManager.postAsyn(getActivity(), URLs.Recharge, params, new OkHttpClientManager.ResultCallback<RechargeDetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                textView7.setClickable(true);
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
                                        CommonUtil.gotoActivity(getActivity(), AddressManagementActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    } /*else if (info.contains(getString(R.string.password_h7))) {
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
                    }*/ else {
                        showToast(info);
                    }
                }

            }

            @Override
            public void onResponse(RechargeDetailModel response) {
//                textView7.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>充值" + response);
                myToast(getString(R.string.fragment4_h27));
                Bundle bundle = new Bundle();
                bundle.putString("id", response.getId());
                CommonUtil.gotoActivityWithData(getActivity(), RechargeDetailActivity.class, bundle, false);
            }
        },true);
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
