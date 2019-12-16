package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.utils.CommonUtil;

/**
 * Created by zyz on 2019-12-14.
 * 修改服务代码
 */
public class ChangeServiceNumActivity extends BaseActivity {
    LinearLayout headView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeservicenum);

    }

    @Override
    protected void initView() {
        headView = findViewByID_My(R.id.headView);
        headView.setPadding(0, (int) CommonUtil.getStatusBarHeight(ChangeServiceNumActivity.this), 0, 0);
        /*//动态设置linearLayout的高度为屏幕高度的1/3
        ViewGroup.LayoutParams lp = headView.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(ChangeServiceNumActivity.this) / 3;*/
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.left_btn:
                finish();
                break;
        }
    }

    @Override
    protected void updateView() {
//        titleView.setTitle(getString(R.string.myprofile_h16));
        titleView.setVisibility(View.GONE);
    }
}
