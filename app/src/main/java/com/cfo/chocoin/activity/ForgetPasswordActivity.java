package com.cfo.chocoin.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.cfo.chocoin.R;
import com.cfo.chocoin.adapter.Pop_ListAdapter;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.model.SmsCodeListModel;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.CommonUtil;
import com.cfo.chocoin.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by fafukeji01 on 2017/4/25.
 * 忘记密码
 */

public class ForgetPasswordActivity extends BaseActivity {
    List<SmsCodeListModel.LangListBean> list = new ArrayList<>();
    List<SmsCodeListModel.MobileStateListBean> list1 = new ArrayList<>();
    private TextView textView, textView1, textView2;
    private EditText editText1, editText2, editText3, editText4;

    private TimeCount time;
    String phonenum = "", password1 = "", password2 = "", code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

//        mImmersionBar.reset().init();
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);

    }

    @Override
    protected void onResume() {
        super.onResume();

        String string3 = "?lang_type=" + localUserInfo.getLanguage_Type();
        RequestSmsCodeList(string3);//手机号国家代码集合

        textView.setText("+" + localUserInfo.getMobile_State_Code());
    }

    @Override
    protected void initView() {
        textView = findViewByID_My(R.id.textView);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);

        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        editText4 = findViewByID_My(R.id.editText4);
    }

    @Override
    protected void initData() {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
    }

    //手机号国家代码集合
    private void RequestSmsCodeList(String string) {
        OkHttpClientManager.getAsyn(ForgetPasswordActivity.this, URLs.ForgetPassword + string, new OkHttpClientManager.ResultCallback<SmsCodeListModel>() {
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
                list1 = response.getMobile_state_list();
//                mobile_state_code = model.getMobile_state_code_list().get(0).getCode() + "";
//                textView.setText(model.getMobile_state_code_list().get(0).getTitle());
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView:
                //选择国家
                if (list1.size() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list", (Serializable) list1);
                    CommonUtil.gotoActivityWithData(ForgetPasswordActivity.this, SelectCountryActivity.class, bundle, false);
                } else {
                    String string3 = "?lang_type=" + localUserInfo.getLanguage_Type();
                    RequestSmsCodeList(string3);//手机号国家代码集合
                }
                /*if (model != null) {
                    showPopupWindow(textView);
                } else {
                    String string3 = "?lang_type=" + localUserInfo.getLanguage_Type();
                    RequestSmsCodeList(string3);//手机号国家代码集合
                }*/
                break;
            case R.id.textView1:
                //发送验证码
                phonenum = editText1.getText().toString().trim();
                if (TextUtils.isEmpty(phonenum)) {
                    Toast.makeText(this, getString(R.string.forgetpassword_h1), Toast.LENGTH_SHORT).show();
                } else {
                        /*String string = "?mobile=" + phonenum +
                                "&type=" + "2" +
                                "&mobile_state_code=" + mobile_state_code;*/
                    textView1.setClickable(false);
                    this.showProgress(true, getString(R.string.app_sendcode_hint1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("mobile", phonenum);
                    params.put("type", "2");
                    params.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                    RequestCode(params);//获取验证码
                }
                break;
            case R.id.textView2:
                //确认
                if (match()) {
                    this.showProgress(true, getString(R.string.forgetpassword_h9));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("mobile", phonenum);//手机号
                    params.put("password", password1);//密码（不能小于6位数）
                    params.put("mobile_state_code", localUserInfo.getMobile_State_Code());
//                    params.put("uuid", CommonUtil.getIMEI(ForgetPasswordActivity.this));
                    params.put("code", code);//手机验证码
                    Request(params);
                }
                break;
            case R.id.title_left:
                finish();
                break;
        }
    }

    private boolean match() {
        phonenum = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(phonenum)) {
            myToast(getString(R.string.forgetpassword_h1));
            return false;
        }
        code = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            myToast(getString(R.string.forgetpassword_h2));
            return false;
        }
        password1 = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(password1)) {
            myToast(getString(R.string.forgetpassword_h3));
            return false;
        }
        password2 = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(password2)) {
            myToast(getString(R.string.forgetpassword_h4));
            return false;
        }
        if (!password1.equals(password2)) {
            myToast(getString(R.string.forgetpassword_h7));
            return false;
        }
        return true;
    }

    private void RequestCode(Map<String, String> params) {
        OkHttpClientManager.postAsyn(ForgetPasswordActivity.this, URLs.Code, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                textView1.setClickable(true);
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                textView1.setClickable(true);
                MyLogger.i(">>>>>>>>>验证码" + response);
                time.start();//开始计时
                myToast(getString(R.string.app_sendcode_hint));

            }
        }, true);

    }

    private void Request(Map<String, String> params) {
        OkHttpClientManager.postAsyn(ForgetPasswordActivity.this, URLs.ForgetPassword, params, new OkHttpClientManager.ResultCallback<String>() {
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
                MyLogger.i(">>>>>>>>>重置密码" + response);
                myToast(getString(R.string.forgetpassword_h8));
                finish();
            }
        }, true);

    }

    @Override
    protected void updateView() {
//        titleView.setTitle(getString(R.string.forgetpwd_title));
        titleView.setVisibility(View.GONE);
    }

    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            textView1.setText(getString(R.string.app_reacquirecode));
            textView1.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            textView1.setClickable(false);
            textView1.setText(millisUntilFinished / 1000 + getString(R.string.app_codethen));
        }
    }


    private void showPopupWindow(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(ForgetPasswordActivity.this).inflate(
                R.layout.pop_list_1, null);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        contentView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = contentView.findViewById(R.id.pop_listView).getTop();
                int height1 = contentView.findViewById(R.id.pop_listView).getBottom();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        popupWindow.dismiss();
                    }
                    if (y > height1) {
                        popupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        // 设置按钮的点击事件
        ListView pop_listView = (ListView) contentView.findViewById(R.id.pop_listView);
        List<String> list1 = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            list1.add(list.get(i).getTitle());
        }
        final Pop_ListAdapter adapter = new Pop_ListAdapter(ForgetPasswordActivity.this, list1);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                adapter.notifyDataSetChanged();

//                mobile_state_code = model.getMobile_state_code_list().get(i).getCode() + "";
//                textView.setText(model.getMobile_state_code_list().get(i).getTitle());
                popupWindow.dismiss();

            }
        });

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        ColorDrawable dw = new ColorDrawable(this.getResources().getColor(R.color.transparent));
        // 设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);
    }
}
