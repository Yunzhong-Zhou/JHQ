package com.fone.fone.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.utils.CommonUtil;

/**
 * Created by zyz on 2019-12-14.
 * 服务中心
 */
public class ServiceCenter_NoActivity extends BaseActivity {
    int status = 1;
    String cause = "";
    LinearLayout headView;
    TextView tv_shenqing, textView_liyou;

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
        textView_liyou = findViewByID_My(R.id.textView_liyou);
    }

    @Override
    protected void initData() {
        status = getIntent().getIntExtra("status", 1);
        cause = getIntent().getStringExtra("cause");
        switch (status) {
            case -1:
                //待申请 -可点击  - 不显示理由
                tv_shenqing.setSelected(true);
                tv_shenqing.setBackgroundResource(R.drawable.btn_img_login);
                tv_shenqing.setText(getString(R.string.myprofile_h31));//立即申请
                textView_liyou.setVisibility(View.GONE);
                break;
            case 1:
                //待审核 - 不可点击 - 不显示理由
                tv_shenqing.setSelected(false);
                tv_shenqing.setBackgroundResource(R.mipmap.bg_btn6);
                tv_shenqing.setText(getString(R.string.myprofile_h52));//已申请
                textView_liyou.setVisibility(View.GONE);
                break;
            case 3:
                //未通过 - 可点击 - 显示理由
                tv_shenqing.setSelected(true);
                tv_shenqing.setBackgroundResource(R.drawable.btn_img_login);
                tv_shenqing.setText(getString(R.string.myprofile_h31));//立即申请
                textView_liyou.setVisibility(View.VISIBLE);
                textView_liyou.setText(getString(R.string.myprofile_h51) + cause);
                break;
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
                if (status != 1){
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 1);//1、服务中心 2、实名认证
                    CommonUtil.gotoActivityWithData(ServiceCenter_NoActivity.this, SelectAddressActivity.class, bundle, true);
                }
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
