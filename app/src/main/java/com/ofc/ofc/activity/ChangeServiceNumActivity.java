package com.ofc.ofc.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.ChangeServiceNumModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.ofc.ofc.net.OkHttpClientManager.IMGHOST;

/**
 * Created by zyz on 2019-12-14.
 * 修改服务代码
 */
public class ChangeServiceNumActivity extends BaseActivity {
    String num = "";
    ChangeServiceNumModel model;
    LinearLayout headView,linearLayout;
    TextView textView1, textView2, textView3;
    ImageView imageView1,imageView_bianji;
    EditText editText1;

    Handler handler = new Handler();
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeservicenum);

    }
    // 触摸其他区域，输入法界面消失
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                MyLogger.i(">>>>>>>>>"+editText1.getText().toString().trim());
                if (!editText1.getText().toString().trim().equals("")){
                    showProgress(false, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("service_code", editText1.getText().toString().trim());
                    params.put("token", localUserInfo.getToken());
                    RequestSet(params);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    @Override
    protected void initView() {
        linearLayout = findViewByID_My(R.id.linearLayout);
        headView = findViewByID_My(R.id.headView);
        headView.setPadding(0, (int) CommonUtil.getStatusBarHeight(ChangeServiceNumActivity.this), 0, 0);
        /*//动态设置linearLayout的高度为屏幕高度的1/3
        ViewGroup.LayoutParams lp = headView.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(ChangeServiceNumActivity.this) / 3;*/
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        imageView1 = findViewByID_My(R.id.imageView1);
        editText1 = findViewByID_My(R.id.editText1);

        /*editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                    Log.v("tag", "---" + s.toString());
                }
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        MyLogger.i(">>>>>>>>>"+editText1.getText().toString().trim());
                        if (!editText1.getText().toString().trim().equals("")){
                            showProgress(false, getString(R.string.app_loading1));
                            HashMap<String, String> params = new HashMap<>();
                            params.put("service_code", editText1.getText().toString().trim());
                            params.put("token", localUserInfo.getToken());
                            RequestSet(params);
                        }
                    }
                };
                Log.v("tag", "(((((" + s.toString());
                handler.postDelayed(runnable, 2000);
            }
        });*/
        editText1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    MyLogger.i(">>>>>>>>>"+editText1.getText().toString().trim());
                    if (!editText1.getText().toString().trim().equals("")){
                        showProgress(false, getString(R.string.app_loading1));
                        HashMap<String, String> params = new HashMap<>();
                        params.put("service_code", editText1.getText().toString().trim());
                        params.put("token", localUserInfo.getToken());
                        RequestSet(params);
                    }else {
//                        myToast(getString(R.string.myprofile_h48));
                        editText1.setText(model.getService_center().getCode());
                    }
                }
                return true;
            }
        });

        //失去焦点时触发
        editText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                    MyLogger.i(">>>>>>>>>"+editText1.getText().toString().trim());
                    editText1.setText(" ");
                    editText1.setSelection(editText1.getText().length());
                    //得到焦点
                    editText1.requestFocus();
                    /*if (!editText1.getText().toString().trim().equals("")){
                        showProgress(false, getString(R.string.app_loading1));
                        HashMap<String, String> params = new HashMap<>();
                        params.put("service_code", editText1.getText().toString().trim());
                        params.put("token", localUserInfo.getToken());
                        RequestSet(params);
                    }*/
                }
            }
        });
        imageView_bianji = findViewByID_My(R.id.imageView_bianji);
        imageView_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editText1.setText("");
                editText1.setSelection(editText1.getText().length());
                //得到焦点
                editText1.requestFocus();
                //弹出键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText1, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }
    private void RequestSet(HashMap<String, String> params) {
        OkHttpClientManager.postAsyn(ChangeServiceNumActivity.this, URLs.ServiceCenter_Change, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
                editText1.setText(num);
                //得到焦点
                editText1.requestFocus();
                editText1.setSelection(editText1.getText().length());
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>添加服务中心" + response);
//                myToast(getString(R.string.app_submit_true));
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    myToast(jObj.getString("msg"));
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                requestServer();
//                finish();
            }
        }, false);

    }
    @Override
    protected void initData() {
        requestServer();
    }
    @Override
    public void requestServer() {
        super.requestServer();
        showProgress(true, getString(R.string.app_loading2));
        request("?token=" + localUserInfo.getToken());
    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(ChangeServiceNumActivity.this, URLs.ServiceCenter_Change + string, new OkHttpClientManager.ResultCallback<ChangeServiceNumModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(ChangeServiceNumModel response) {
                MyLogger.i(">>>>>>>>>服务中心" + response);
                hideProgress();
                model = response;
                if (response.getService_center_grade() == 0){
                    //可编辑
                    editText1.setEnabled(true);
                    //得到焦点
                    editText1.requestFocus();
                    editText1.setSelection(editText1.getText().length());
                }else {
                    //不可编辑
                    editText1.setEnabled(false);
                }
                if (response.getService_center() != null){
                    linearLayout.setVisibility(View.VISIBLE);
                    editText1.setText(response.getService_center().getCode());
                    textView1.setText(response.getService_center().getAddr());//地址
                    textView2.setText(response.getService_center().getAddr_detail() + "  "
                            + response.getService_center().getArea() + "㎡");//地址
                    textView3.setText(response.getService_center().getCode());//代码

                    if (!response.getService_center().getPic1().equals(""))
                        Glide.with(ChangeServiceNumActivity.this)
                                .load(IMGHOST + response.getService_center().getPic1())
                                .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                                .into(imageView1);//加载图片
                }else {
                    linearLayout.setVisibility(View.GONE);
                }

                num = editText1.getText().toString().trim();

            }
        });
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
