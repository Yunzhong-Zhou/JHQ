package com.ofc.ofc.activity;

import android.os.Bundle;

import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;

/**
 * Created by Mr.Z on 2020/7/15.
 * 分红记录
 */
public class FenHongListActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenhonglist);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.qianbao_h26));
    }
}
