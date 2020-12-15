package com.fone.fone.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;

/**
 * Created by Mr.Z on 2020/12/14.
 * 支付详情
 */
public class PayDetailActivity extends BaseActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paydetail);
    }

    @Override
    protected void initView() {
        textView = findViewByID_My(R.id.textView);

        /*String wholeStr = getString(R.string.fragment1_h44)+"（";
        String highlightStr = getString(R.string.fragment1_h45)+"";

        StringFormatUtil spanStr = new StringFormatUtil(this, wholeStr,
                highlightStr, R.color.green).fillColor();
        textView.setText(spanStr.getResult());*/

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
