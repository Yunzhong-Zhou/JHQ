package com.fone.fone.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.FILWalletModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

/**
 * Created by Mr.Z on 2020/12/14.
 * FIL钱包
 */
public class FILWalletActivity extends BaseActivity {
    TextView tv_keyong,tv_chanzhi,tv_yichan,tv_shouru, tv_zhichu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtwallet);
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
        tv_yichan = findViewByID_My(R.id.tv_yichan);
        tv_chanzhi = findViewByID_My(R.id.tv_chanzhi);
        tv_shouru = findViewByID_My(R.id.tv_shouru);
        tv_zhichu = findViewByID_My(R.id.tv_zhichu);

    }

    @Override
    protected void initData() {

    }
    private void RequestWallet(String string) {
        OkHttpClientManager.getAsyn(FILWalletActivity.this, URLs.FILWallet + string, new OkHttpClientManager.ResultCallback<FILWalletModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(FILWalletModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>USDT钱包" + response);
                tv_keyong.setText(response.getUsable_fil_money());//可用FIL
                tv_yichan.setText(response.getFil_money());//已产FIL
                tv_chanzhi.setText(response.getHashrate()+"TB");//有效算力
                tv_shouru.setText(response.getIn_fil_money());//收入
                tv_zhichu.setText(response.getOut_fil_money());//支出
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
            case R.id.tv_takecash:
                //提现
                Bundle bundle = new Bundle();
                bundle.putInt("money_type",2);
                CommonUtil.gotoActivityWithData(FILWalletActivity.this, TakeCashActivity.class,bundle,false);
                break;
            case R.id.tv_transfer:
                //划转
                CommonUtil.gotoActivity(FILWalletActivity.this, TransferActivity.class);
                break;
            case R.id.ll_shouru:
                //收入记录
                CommonUtil.gotoActivity(FILWalletActivity.this, FILShouRuListActivity.class);
                break;
            case R.id.ll_zhichu:
                //支出记录
                CommonUtil.gotoActivity(FILWalletActivity.this, FILZhiChuListActivity.class);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
