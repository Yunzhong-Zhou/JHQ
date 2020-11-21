package com.ofc.ofc.activity;

import android.os.Bundle;

import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;

/**
 * Created by Mr.Z on 2020/11/16.
 * 银行卡
 */
public class BankCardActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankcard);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.qianbao_h140));
    }
}
