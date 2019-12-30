package com.ofc.ofc.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyz on 2019-12-30.
 * 实名认证结果
 */
public class VerfiedResultActivity extends BaseActivity {
    String number = "", name = "", v_type = "";
    private ImageView iv_r;
    private TextView tv_r,tv_msg,bt_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifiedresult);
    }

    @Override
    protected void initView() {
        iv_r = findViewByID_My(R.id.iv_r);
        tv_r = findViewByID_My(R.id.tv_r);
        tv_msg = findViewByID_My(R.id.tv_msg);
        bt_close = findViewByID_My(R.id.bt_close);
        bt_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        name = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("cardNo");
        v_type = getIntent().getStringExtra("v_type");
        boolean succeed = getIntent().getBooleanExtra("result",false);
        String msg = getIntent().getStringExtra("msg");
        MyLogger.i(">>>>>>>返回数据"+msg);
        String hint = getIntent().getStringExtra("hint");

        iv_r = (ImageView) findViewById(R.id.iv_r);
        tv_r = (TextView) findViewById(R.id.tv_r);
        tv_msg = (TextView) findViewById(R.id.tv_msg);

        if(!succeed){
            //失败
            iv_r.setImageResource(R.mipmap.ic_shibai);
            tv_r.setText(getString(R.string.faceverified_h3));
            bt_close.setText(getString(R.string.faceverified_h4));
            tv_msg.setText(hint);//失败理由
        }else {
            //成功
            iv_r.setImageResource(R.mipmap.ic_chenggong);
            tv_r.setText(getString(R.string.faceverified_h2));
            bt_close.setText(getString(R.string.faceverified_h5));

            showProgress(false, getString(R.string.app_loading1));
            HashMap<String, String> params = new HashMap<>();
            params.put("token", localUserInfo.getToken());
            params.put("type", v_type);
            params.put("number", number);
            params.put("name", name);
            request3(params);
        }
    }

    private void request3(Map<String, String> params) {
        OkHttpClientManager.postAsyn(VerfiedResultActivity.this, URLs.Verified3, params,
                new OkHttpClientManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, String info, Exception e) {
                        hideProgress();
                        if (!info.equals("")) {
                            myToast(info);
                        }
                    }

                    @Override
                    public void onResponse(String response) {
                        MyLogger.i(">>>>>>>>>认证加载3" + response);
                        hideProgress();
                    }
                });

    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.faceverified_h1));
    }
}
