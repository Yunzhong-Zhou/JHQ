package com.fone.fone.activity;

import android.os.Bundle;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;

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
