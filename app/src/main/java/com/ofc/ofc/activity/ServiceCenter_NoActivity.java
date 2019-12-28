package com.ofc.ofc.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.utils.CommonUtil;

/**
 * Created by zyz on 2019-12-14.
 * 服务中心
 */
public class ServiceCenter_NoActivity extends BaseActivity {
    int status = 1;
    LinearLayout headView;
    TextView tv_shenqing;
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
        tv_shenqing = findViewByID_My(R.id.tv_shenqing);
    }

    @Override
    protected void initData() {
        status = getIntent().getIntExtra("status",1);
        if (status ==2){
            //审核中
            tv_shenqing.setSelected(false);
//            tv_shenqing.setBackgroundResource(R.drawable.yuanjiao_50_huise);
        }else {
            tv_shenqing.setSelected(true);
            tv_shenqing.setBackgroundResource(R.drawable.btn_img_login);
        }
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
                Bundle bundle = new Bundle();
                bundle.putInt("type",1);//1、服务中心 2、实名认证
                CommonUtil.gotoActivityWithData(ServiceCenter_NoActivity.this, SelectAddressActivity.class,bundle, false);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
