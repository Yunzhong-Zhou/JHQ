package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.view.View;

import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.model.WalletAddressModel;
import com.cfo.chocoin.utils.CommonUtil;

/**
 * Created by zyz on 2019/5/26.
 * 选择地址
 */
public class SelectAddressActivity extends BaseActivity {
    WalletAddressModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectaddress);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.relativeLayout1:
                //大陆
                bundle.putInt("type", 1);
                CommonUtil.gotoActivityWithData(SelectAddressActivity.this, SetServiceCenterActivity.class, bundle, true);
                break;
            case R.id.relativeLayout2:
                //海外
                bundle.putInt("type", 2);
                CommonUtil.gotoActivityWithData(SelectAddressActivity.this, SetServiceCenterActivity.class, bundle, true);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.address_h14));
    }
}
