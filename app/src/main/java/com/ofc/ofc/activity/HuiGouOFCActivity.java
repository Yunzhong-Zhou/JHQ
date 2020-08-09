package com.ofc.ofc.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.MyLogger;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by fafukeji01 on 2017/4/25.
 * 回购OFC
 */

public class HuiGouOFCActivity extends BaseActivity {
    TextView textView1, textView2;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huigouofc);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initView() {
        editText = findViewByID_My(R.id.editText);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout:
                //确认
                if (match()) {
                    this.showProgress(true, getString(R.string.app_loading1));
                    /*HashMap<String, String> params = new HashMap<>();
                    params.put("new_password", password1);//密码（不能小于6位数）
                    params.put("token", localUserInfo.getToken());
                    params.put("old_password", oldpassword);
                    RequestConfrim(params);*/
                }
                break;
        }
    }

    private boolean match() {
       /* oldpassword = editText.getText().toString().trim();
        if (TextUtils.isEmpty(oldpassword)) {
            myToast(getString(R.string.qianbao_h59));
            return false;
        }*/
        return true;
    }

    private void RequestConfrim(Map<String, String> params) {
        OkHttpClientManager.postAsyn(HuiGouOFCActivity.this, URLs.ChangePassword, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>回购OFC" + response);
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    String info = jObj.getString("msg");
                    myToast(info);
                    finish();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//
            }
        }, true);

    }


    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.qianbao_h56));
    }
}
