package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.utils.CommonUtil;

/**
 * Created by zyz on 2019-12-14.
 * 服务中心
 */
public class ServiceCenter_NoActivity extends BaseActivity {
    LinearLayout headView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicecenter_no);
    }

    @Override
    protected void initView() {
        headView = findViewByID_My(R.id.headView);
//        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(LoginActivity.this), 0, 0);
        CommonUtil.setMargins(headView, 0, (int) CommonUtil.getStatusBarHeight(ServiceCenter_NoActivity.this), 0, 0);

    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.tv_shenqing:
                //申请
                CommonUtil.gotoActivity(ServiceCenter_NoActivity.this, SelectAddressActivity.class, false);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
