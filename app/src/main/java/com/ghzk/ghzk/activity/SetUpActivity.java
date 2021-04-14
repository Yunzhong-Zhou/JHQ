package com.ghzk.ghzk.activity;

import android.os.Bundle;
import android.view.View;

import com.ghzk.ghzk.R;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.model.SmsCodeListModel;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
import com.ghzk.ghzk.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Z on 2020/12/10.
 * 设置中心
 */
public class SetUpActivity extends BaseActivity {
    List<SmsCodeListModel.LangListBean> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_setup);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String string3 = "?lang_type=" + localUserInfo.getLanguage_Type();
        RequestSmsCodeList(string3);//手机号国家代码集合
    }
    //手机号国家代码集合
    private void RequestSmsCodeList(String string) {
        OkHttpClientManager.getAsyn(SetUpActivity.this, URLs.Login + string, new OkHttpClientManager.ResultCallback<SmsCodeListModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(SmsCodeListModel response) {
                MyLogger.i(">>>>>>>>>手机号国家代码集合" + response);
                list = response.getLang_list();
//                list1 = response.getMobile_state_list();
//                mobile_state_code = model.getMobile_state_code_list().get(0).getCode() + "";
//                textView.setText(model.getMobile_state_code_list().get(0).getTitle());
            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView1:
                //绑定银行卡
                CommonUtil.gotoActivity(this, BankCardSettingActivity.class, false);
                break;
            case R.id.textView2:
                //交易密码
                CommonUtil.gotoActivity(this, SetTransactionPasswordActivity.class, false);
                break;
            case R.id.textView3:
                //登录密码
                CommonUtil.gotoActivity(this, ChangePasswordActivity.class, false);
                break;
            case R.id.textView4:
                //个人资料
                CommonUtil.gotoActivity(this, MyProfileActivity.class, false);

                //选择语言
                /*if (list.size() > 0) {
                    SelectLanguagePopupWindow popupwindow = new SelectLanguagePopupWindow(SetUpActivity.this, LoginActivity.class, list);
                    popupwindow.showAtLocation(SetUpActivity.this.findViewById(R.id.textView3), Gravity.CENTER, 0, 0);
                } else {
                    String string3 = "?lang_type=" + localUserInfo.getLanguage_Type();
                    RequestSmsCodeList(string3);//手机号国家代码集合
                }*/
                break;
            case R.id.textView5:
                //关于我们
                CommonUtil.gotoActivity(this, AboutActivity.class, false);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.setup_title));
    }
}