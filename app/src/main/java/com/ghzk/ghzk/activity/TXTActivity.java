package com.ghzk.ghzk.activity;

import android.os.Bundle;

import com.ghzk.ghzk.R;
import com.ghzk.ghzk.base.BaseActivity;

/**
 * Created by Mr.Z on 2021/5/10.
 */
public class TXTActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txt);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.txt1));
    }
}
