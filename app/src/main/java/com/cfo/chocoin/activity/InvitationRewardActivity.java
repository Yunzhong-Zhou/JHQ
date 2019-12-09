package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.utils.CommonUtil;


/**
 * Created by zyz on 2019-10-09.
 * 有奖邀请
 */
public class InvitationRewardActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitationreward);
    }

    @Override
    protected void initView() {
        findViewByID_My(R.id.titleView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        //动态设置linearLayout的高度为屏幕高度的1/4
        ViewGroup.LayoutParams lp = findViewByID_My(R.id.headView).getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(this) / 2;
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
            case R.id.textView1:
                //立即邀请
                break;
            case R.id.textView2:
                //推广海报
                CommonUtil.gotoActivity(this, MyPosterActivity.class, false);
                break;
            case R.id.textView3:
                //去提现
                CommonUtil.gotoActivity(this, TakeCashActivity.class, false);
                break;
        }
    }

    @Override
    protected void updateView() {
//        titleView.setTitle("有奖邀请");
        titleView.setVisibility(View.GONE);
    }
}
