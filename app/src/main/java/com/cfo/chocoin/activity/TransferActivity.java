package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.view.View;

import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;

/**
 * Created by zyz on 2019-12-17.
 */
public class TransferActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
