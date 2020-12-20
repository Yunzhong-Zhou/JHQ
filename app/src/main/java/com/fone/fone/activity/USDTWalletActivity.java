package com.fone.fone.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cy.dialog.BaseDialog;
import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.USDTWalletModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

/**
 * Created by Mr.Z on 2020/12/14.
 * USDT钱包
 */
public class USDTWalletActivity extends BaseActivity {
    TextView tv_keyong,tv_shouyi,tv_yongjin,tv_shouru, tv_zhichu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usdtwallet);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();//获取数据
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                String string = "?token=" + localUserInfo.getToken();
                RequestWallet(string);
            }

            @Override
            public void onLoadmore() {

            }
        });
        tv_keyong = findViewByID_My(R.id.tv_keyong);
        tv_shouyi = findViewByID_My(R.id.tv_shouyi);
        tv_yongjin = findViewByID_My(R.id.tv_yongjin);
        tv_shouru = findViewByID_My(R.id.tv_shouru);
        tv_zhichu = findViewByID_My(R.id.tv_zhichu);


    }

    @Override
    protected void initData() {

    }
    private void RequestWallet(String string) {
        OkHttpClientManager.getAsyn(USDTWalletActivity.this, URLs.USDTWallet + string, new OkHttpClientManager.ResultCallback<USDTWalletModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(USDTWalletModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>USDT钱包" + response);
                tv_keyong.setText(response.getUsable_money());//可用USDT
                tv_shouyi.setText(response.getChange_game_win_money());//拼团收益
                tv_yongjin.setText(response.getCommission_money());//佣金
                tv_shouru.setText(response.getIn_money());//收入
                tv_zhichu.setText(response.getOut_money());//支出
            }
        });

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        String string = "?token=" + localUserInfo.getToken();
        RequestWallet(string);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.left_btn:
                finish();
                break;
            case R.id.tv_recharge1:
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
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("id", "");
                        CommonUtil.gotoActivityWithData(USDTWalletActivity.this, RechargeDetailActivity.class, bundle1, false);
                    }
                });
                break;
            case R.id.tv_takecash:
                //提现
                Bundle bundle = new Bundle();
                bundle.putInt("money_type",1);
                CommonUtil.gotoActivityWithData(USDTWalletActivity.this, TakeCashActivity.class,bundle,false);
                break;
            case R.id.tv_transfer:
                //划转
                CommonUtil.gotoActivity(USDTWalletActivity.this, TransferActivity.class);
                break;
            case R.id.ll_shouru:
                //收入记录
                CommonUtil.gotoActivity(USDTWalletActivity.this, ShouRuListActivity.class);
                break;
            case R.id.ll_zhichu:
                //支出记录
                CommonUtil.gotoActivity(USDTWalletActivity.this, ZhiChuListActivity.class);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
