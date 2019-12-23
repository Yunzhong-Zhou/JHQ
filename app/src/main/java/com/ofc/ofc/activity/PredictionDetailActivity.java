package com.ofc.ofc.activity;

import android.os.Bundle;

import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;

/**
 * Created by zyz on 2019-12-23.
 * 预测详情
 */
public class PredictionDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictiondetail);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.fragment2_h3));
    }
}
