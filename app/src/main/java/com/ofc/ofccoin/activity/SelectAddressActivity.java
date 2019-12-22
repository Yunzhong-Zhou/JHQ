package com.ofc.ofccoin.activity;

import android.os.Bundle;
import android.view.View;

import com.ofc.ofccoin.R;
import com.ofc.ofccoin.base.BaseActivity;
import com.ofc.ofccoin.model.WalletAddressModel;
import com.ofc.ofccoin.utils.CommonUtil;

/**
 * Created by zyz on 2019/5/26.
 * 选择地址
 */
public class SelectAddressActivity extends BaseActivity {
    int type = 1;//1、服务中心 2、实名认证
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
        type= getIntent().getIntExtra("type",1);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.relativeLayout1:
                //大陆
                bundle.putInt("type", 1);
                if (type ==1){
                    CommonUtil.gotoActivityWithData(SelectAddressActivity.this, SetServiceCenterActivity.class, bundle, true);
                }else {
                    CommonUtil.gotoActivityWithData(SelectAddressActivity.this, VerifiedActivity.class, bundle, true);
                }
                break;
            case R.id.relativeLayout2:
                //海外
                bundle.putInt("type", 2);
                if (type ==1){
                    CommonUtil.gotoActivityWithData(SelectAddressActivity.this, SetServiceCenterActivity.class, bundle, true);
                }else {
                    CommonUtil.gotoActivityWithData(SelectAddressActivity.this, VerifiedActivity.class, bundle, true);
                }
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.address_h14));
    }
}
