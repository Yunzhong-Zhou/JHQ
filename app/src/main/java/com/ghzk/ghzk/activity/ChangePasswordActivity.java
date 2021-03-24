package com.ghzk.ghzk.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ghzk.ghzk.R;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
import com.ghzk.ghzk.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fafukeji01 on 2017/4/25.
 * 修改密码
 */

public class ChangePasswordActivity extends BaseActivity {
    EditText editText1, editText2, editText3;
    String oldpassword = "", password1 = "", password2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initView() {
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                //确认
                if (match()) {
                    this.showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("new_password", password1);//密码（不能小于6位数）
                    params.put("token", localUserInfo.getToken());
                    params.put("old_password", oldpassword);
                    Request(params);
                }
                break;
        }
    }

    private boolean match() {
        oldpassword = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(oldpassword)) {
            myToast(getString(R.string.changepassword_h5));
            return false;
        }
        password1 = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(password1)) {
            myToast(getString(R.string.changepassword_h6));
            return false;
        }
        password2 = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(password2)) {
            myToast(getString(R.string.changepassword_h7));
            return false;
        }
        if (!password1.equals(password2)) {
            myToast(getString(R.string.changepassword_h8));
            return false;
        }
        return true;
    }

    private void Request(Map<String, String> params) {
        OkHttpClientManager.postAsyn(ChangePasswordActivity.this, URLs.ChangePassword, params, new OkHttpClientManager.ResultCallback<String>() {
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
                MyLogger.i(">>>>>>>>>修改登录密码" + response);
                myToast(getString(R.string.changepassword_h9));
//                finish();
                localUserInfo.setUserId("");
                localUserInfo.setToken("");
                localUserInfo.setPhoneNumber("");
                localUserInfo.setNickname("");
                localUserInfo.setWalletaddr("");
                localUserInfo.setEmail("");
                localUserInfo.setUserImage("");

                CommonUtil.gotoActivityWithFinishOtherAll(ChangePasswordActivity.this, LoginActivity.class, true);
                finish();
            }
        },true);

    }


    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.changepassword_h1));
    }
}
