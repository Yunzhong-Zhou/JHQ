package com.ofc.ofc.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ofc.ofc.R;
import com.ofc.ofc.adapter.Pop_ListAdapter;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.SmsCodeListModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.popupwindow.SelectLanguagePopupWindow;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ofc.ofc.net.OkHttpClientManager.HOST;
import static com.ofc.ofc.net.OkHttpClientManager.IMGHOST;


/**
 * Created by fafukeji01 on 2017/4/25.
 * 注册
 */

public class RegisteredActivity extends BaseActivity {
    List<SmsCodeListModel.LangListBean> list = new ArrayList<>();
    List<SmsCodeListModel.MobileStateListBean> list1 = new ArrayList<>();
    ImageView title_right;
    private TextView textView, textView1, textView2, textView3;
    private EditText editText1, editText2, editText3, editText4, editText5, editText6;

    String phonenum = "", password1 = "", password2 = "", code = "", num = "", nickname = "", register_addr = "";
    private TimeCount time;

    boolean isNickName = false;


    String open_id = "";


    String register_agreement = "";

    boolean isgouxuan = true;
    ImageView imageView1;
    /**
     * 定位
     */
    // 定位相关
    /*public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        /*mImmersionBar.reset()
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .init();*/
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        /*// 定位初始化
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        initLocation();
        mLocationClient.start();*/

    }

    @Override
    protected void onResume() {
        super.onResume();

        String string3 = "?lang_type=" + localUserInfo.getLanguage_Type();
        RequestSmsCodeList(string3);//手机号国家代码集合

        textView.setText("+" + localUserInfo.getMobile_State_Code());

        if (!localUserInfo.getCountry_IMG().equals(""))
            Glide.with(RegisteredActivity.this)
                    .load(IMGHOST + localUserInfo.getCountry_IMG())
                    .centerCrop()
//                    .placeholder(R.mipmap.ic_guoqi)//加载站位图
//                    .error(R.mipmap.ic_guoqi)//加载失败
                    .into(title_right);//加载图片
        else
            title_right.setImageResource(R.mipmap.ic_guoqi);
    }

    @Override
    protected void initView() {
        title_right = findViewByID_My(R.id.title_right);
        textView = findViewByID_My(R.id.textView);
//        textView.setText("+"+localUserInfo.getMobile_State_Code());
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);

        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        editText4 = findViewByID_My(R.id.editText4);
        editText5 = findViewByID_My(R.id.editText5);
        editText6 = findViewByID_My(R.id.editText6);

        imageView1 = findViewByID_My(R.id.imageView1);

        /*//失去焦点时触发
        editText6.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                    MyLogger.i(">>>>>>>>>>" + editText6.getText().toString().trim());
                    if (!editText6.getText().toString().trim().equals("")) {
                        String string = "?nickname=" + editText6.getText().toString().trim();
                        RequestNickName(string);//检测昵称是否可用
                    }
                }
            }
        });*/

    }

    @Override
    protected void initData() {
        open_id = getIntent().getStringExtra("open_id");

//        request(captchaURL);

    }

    //手机号国家代码集合
    private void RequestSmsCodeList(String string) {
        OkHttpClientManager.getAsyn(RegisteredActivity.this, URLs.Registered + string, new OkHttpClientManager.ResultCallback<SmsCodeListModel>() {
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
            case R.id.title_left:
                //返回登录
                finish();
                break;
            case R.id.title_right:
                //选择语言
                if (list.size() > 0) {
                    SelectLanguagePopupWindow popupwindow = new SelectLanguagePopupWindow(RegisteredActivity.this, RegisteredActivity.class, list);
                    popupwindow.showAtLocation(RegisteredActivity.this.findViewById(R.id.title_right), Gravity.CENTER, 0, 0);
                } else {
                    String string3 = "?lang_type=" + localUserInfo.getLanguage_Type();
                    RequestSmsCodeList(string3);//手机号国家代码集合
                }
                break;
            case R.id.textView:
                //选择国家
                if (list1.size() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list", (Serializable) list1);
                    CommonUtil.gotoActivityWithData(RegisteredActivity.this, SelectCountryActivity.class, bundle, false);
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
                    Toast.makeText(this, getString(R.string.registered_h1), Toast.LENGTH_SHORT).show();
                } else {
//                    customVerity();//极验

                    /*showProgress(true, "正在生成图形验证码...");
                    HashMap<String, String> params = new HashMap<>();
                    params.put("mobile", phonenum);
                    params.put("type", "1");
                    RequestPicCode(params);*/
                    showProgress(true, getString(R.string.app_sendcode_hint1));
                    HashMap<String, String> params1 = new HashMap<>();
                    params1.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                    params1.put("mobile", phonenum);
                    params1.put("type", "1");
                    RequestCode(params1);//获取验证码
                }
                break;
            case R.id.textView2:
                //用户注册协议
                /*if (LocalUserInfo.getInstance(this).getLanguage_Type().equals("1")) {
                    //英文
                    register_agreement = HOST + "/wechat/article/detail?id=9303693c9e34e02560fe2039f4ddd654";
                } else {
                    register_agreement = HOST + "/wechat/article/detail?id=9303693c9e34e02560fe2039f4ddd654";
                }*/
                register_agreement = HOST + "/wechat/article/detail?id=9303693c9e34e02560fe2039f4ddd654";
                if (!register_agreement.equals("")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", register_agreement);
                    CommonUtil.gotoActivityWithData(RegisteredActivity.this, WebContentActivity.class, bundle, false);
                }
                break;
            case R.id.textView3:
                //确认注册
//                MyLogger.i(">>>>>>" + CommonUtil.isRealMachine() + CommonUtil.getIMEI(RegisteredActivity.this));
//                if (CommonUtil.isRealMachine()){
                //是真机
                if (match()) {
                    textView3.setClickable(false);
                    showProgress(true, getString(R.string.registered_h14));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("thirdparty_open_id", open_id);//授权
                    params.put("mobile", phonenum);//手机号
                    params.put("password", password1);//密码（不能小于6位数）
                    params.put("code", code);//手机验证码
                    params.put("nickname", nickname);//昵称
                    params.put("invite_code", num);//邀请码
                    params.put("uuid", CommonUtil.getIMEI(RegisteredActivity.this));//IMEI
                    params.put("register_addr", register_addr);//注册地址
                    params.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                    RequestRegistered(params);//注册
                }
//                }else {
//                    //不是真机
//                    myToast("该设备不能进行注册");
//                }
                break;

            case R.id.imageView1:
                //勾选协议
                isgouxuan = !isgouxuan;
                if (isgouxuan)
                    imageView1.setImageResource(R.mipmap.ic_gouxuan);
                else
                    imageView1.setImageResource(R.mipmap.ic_weigouxuan);
                break;
        }
    }

    private boolean match() {
        phonenum = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(phonenum)) {
            myToast(getString(R.string.registered_h1));
            return false;
        }
        code = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            myToast(getString(R.string.registered_h2));
            return false;
        }
        password1 = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(password1)) {
            myToast(getString(R.string.registered_h3));
            return false;
        }
        password2 = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(password2)) {
            myToast(getString(R.string.registered_h4));
            return false;
        }
        if (!password1.equals(password2)) {
            myToast(getString(R.string.registered_h12));
            return false;
        }
        num = editText5.getText().toString().trim();
        if (TextUtils.isEmpty(num)) {
            myToast(getString(R.string.registered_h5));
            return false;
        }

        nickname = editText6.getText().toString().trim();
        if (TextUtils.isEmpty(nickname)) {
            myToast(getString(R.string.registered_h11));
            return false;
        }

        if (!isgouxuan) {
            myToast(getString(R.string.registered_h15));
            return false;
        }
        /*if (isNickName==false){
            myToast("昵称不可用");
            return false;
        }*/
        /*if (register_addr.equals("")) {
            showToast(getString(R.string.registered_position_hint),
                    getString(R.string.app_confirm),
                    getString(R.string.app_cancel), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            register_addr = getString(R.string.registered_noposition) +"";
                            dialog.dismiss();
                        }
                    });
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisteredActivity.this);
            builder.setMessage(getString(R.string.registered_position_hint));
            builder.setTitle(getString(R.string.app_prompt));
            builder.setPositiveButton(getString(R.string.app_confirm), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                }
            });
            builder.setNegativeButton(getString(R.string.app_cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    register_addr = getString(R.string.registered_noposition);
                    dialog.dismiss();
                }
            });
            builder.create().show();
            return false;
        }*/

        return true;
    }

    /*//检测昵称
    private void RequestNickName(String params) {
        OkHttpClientManager.getAsyn(RegisteredActivity.this, URLs.Registered_NickName + params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                isNickName = false;
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                MyLogger.i(">>>>>>>>>检测昵称" + response);
                isNickName = true;
                myToast(getString(R.string.registered_h13));
            }
        });

    }
    //获取图形验证码
    private void RequestPicCode(HashMap<String, String> params) {
        OkHttpClientManager.postAsyn(RegisteredActivity.this, URLs.RegisteredPicCode, params, new OkHttpClientManager.ResultCallback<PicCodeModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(PicCodeModel response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>验证码" + response);
               *//* time.start();//开始计时
                myToast(getString(R.string.app_sendcode_hint));*//*
                dialog.config(R.layout.dialog_baseedit, 0.8f, Gravity.CENTER,
                        BaseDialog.AnimInType.CENTER, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT, true).show();
                TextView textView2 = dialog.findViewById(R.id.textView2);
                textView2.setText("请输入图中图形验证码");
                ImageView imageView1 = dialog.findViewById(R.id.imageView1);
                imageView1.setImageBitmap(CommonUtil.stringtoBitmap(response.getCode_url()));
                final EditText editText = dialog.findViewById(R.id.editText);
                dialog.findViewById(R.id.textView3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String piccode = editText.getText().toString().trim();
                        if (!piccode.equals("")) {
                            dialog.dismiss();
                            showProgress(true, "正在获取短信验证码...");
                            HashMap<String, String> params = new HashMap<>();
                            params.put("mobile", phonenum);
                            params.put("type", "1");
                            params.put("code", piccode);
                            RequestCode(params);//获取验证码
                        } else {
                            myToast("请输入图形验证码");
                        }


                    }
                });
                imageView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //重新获取图片验证码
                        dialog.dismiss();
                        showProgress(true, "正在重新生成图形验证码...");
                        HashMap<String, String> params = new HashMap<>();
                        params.put("mobile", phonenum);
                        RequestPicCode(params);
                    }
                });
                dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


            }
        }, false);

    }*/

    //获取验证码
    private void RequestCode(Map<String, String> params) {
        OkHttpClientManager.postAsyn(RegisteredActivity.this, URLs.Code, params, new OkHttpClientManager.ResultCallback<String>() {
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
                MyLogger.i(">>>>>>>>>验证码" + response);
                if (time != null) {
                    time.cancel();
                }
                time = new TimeCount(60000, 1000);//构造CountDownTimer对象
                time.start();//开始计时
                myToast(getString(R.string.app_sendcode_hint));
            }
        }, true);
    }

    //注册
    private void RequestRegistered(Map<String, String> params) {
        OkHttpClientManager.postAsyn(RegisteredActivity.this, URLs.Registered, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                textView3.setClickable(true);
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(final String response) {
                MyLogger.i(">>>>>>>>>注册" + response);
                textView3.setClickable(true);
//                localUserInfo.setTime(System.currentTimeMillis() + "");
                /*showToast("该账户尚未激活，请完成人脸识别后进行操作", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("mobile",phonenum);
                        CommonUtil.gotoActivityWithData(RegisteredActivity.this,
                                RecordVideoActivity.class,bundle1);
                        dialog.dismiss();
                    }
                });
                hideProgress();*/
                hideProgress();
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);

                    JSONObject jObj1 = jObj.getJSONObject("data");
                    //保存Token
                    String token = jObj1.getString("fresh_token");
                    localUserInfo.setToken(token);
                    //保存用户id
                    final String id = jObj1.getString("id");

                    //保存电话号码
                    String mobile = jObj1.getString("mobile");
                    localUserInfo.setPhoneNumber(mobile);
//                    localUserInfo.setPhoneNumber(phonenum);
                    //保存用户昵称
                    String nickname = jObj1.getString("nickname");
                    localUserInfo.setNickname(nickname);

                    localUserInfo.setUserId(id);

                    Bundle bundle = new Bundle();
//                    bundle.putInt("isShowAd", jObj1.getInt("experience"));
                    bundle.putInt("isShowAd", 1);
                    CommonUtil.gotoActivityWithFinishOtherAllAndData(RegisteredActivity.this, MainActivity.class, bundle, true);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                }

            }
        }, true);

    }

    @Override
    protected void updateView() {
//        titleView.setTitle(getString(R.string.registered_title));
        titleView.setVisibility(View.GONE);
    }

    //屏蔽返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            return true;//不执行父类点击事件
        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }

    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            textView1.setText(RegisteredActivity.this.getString(R.string.app_reacquirecode) + "");
            textView1.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            textView1.setClickable(false);
            textView1.setText(millisUntilFinished / 1000 + RegisteredActivity.this.getString(R.string.app_codethen) + "");
        }
    }


    private void showPopupWindow(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(RegisteredActivity.this).inflate(
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
        final Pop_ListAdapter adapter = new Pop_ListAdapter(RegisteredActivity.this, list1);
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
