package com.ghzk.ghzk.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.cy.dialog.BaseDialog;
import com.ghzk.ghzk.MyApplication;
import com.ghzk.ghzk.R;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.model.LoginModel;
import com.ghzk.ghzk.model.RegisteredModel;
import com.ghzk.ghzk.model.SmsCodeListModel;
import com.ghzk.ghzk.model.UpgradeModel;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
import com.ghzk.ghzk.utils.LocalUserInfo;
import com.ghzk.ghzk.utils.MyLogger;
import com.ghzk.ghzk.utils.changelanguage.LanguageType;
import com.ghzk.ghzk.utils.changelanguage.LanguageUtil;
import com.ghzk.ghzk.utils.changelanguage.SpUtil;
import com.ghzk.ghzk.utils.permission.PermissionsActivity;
import com.ghzk.ghzk.utils.permission.PermissionsChecker;
import com.lahm.library.EasyProtectorLib;
import com.lahm.library.EmulatorCheckCallback;
import com.maning.updatelibrary.InstallUtils;
import com.squareup.okhttp.Request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AlertDialog;


/**
 * Created by fafukeji01 on 2017/4/25.
 * 登录
 */
public class LoginActivity extends BaseActivity {
    int type = 1;
    LinearLayout linearLayout1, linearLayout2;
    View view1, view2;
    LinearLayout linearLayout_1, linearLayout_2, linearLayout_3, linearLayout_4, linearLayout_5, linearLayout_6;

    List<SmsCodeListModel.LangListBean> list = new ArrayList<>();
    List<SmsCodeListModel.MobileStateListBean> list1 = new ArrayList<>();

    private EditText editText1, editText2, editText3, editText4;
    private TextView textView, textView1, textView2, textView3,textView4, tv_language;
    private ImageView imageView1, imageView2, title_right;

    private String phonenum = "", password = "",code = "", num = "";
    private TimeCount time = null;

    boolean isgouxuan = true;
    ImageView imageView3;
    String register_agreement = "";

    //更新
    UpgradeModel model_up;

    private int REQUEST_CODE = 0; // 请求码
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            android.Manifest.permission.CALL_PHONE,
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            //定位
//            android.Manifest.permission.ACCESS_FINE_LOCATION
//            android.Manifest.permission.ACCESS_COARSE_LOCATION,
//            android.Manifest.permission.ACCESS_BACKGROUND_LOCATION

            /*Manifest.permission.RECORD_AUDIO,
            Manifest.permission.VIBRATE*/

            /*Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.WRITE_SETTINGS,
            Manifest.permission.VIBRATE*/
    };
    private PermissionsChecker mPermissionsChecker; // 权限检测器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* requestWindowFeature(Window.FEATURE_NO_TITLE);
        //add by able for soft keyboard show
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);*/

        setContentView(R.layout.activity_login);


//        mImmersionBar.reset().init();

        setSwipeBackEnable(false); //主 activity 可以调用该方法，禁止滑动删除

        mPermissionsChecker = new PermissionsChecker(this);

    }


    @Override
    protected void initView() {
//        findViewByID_My(R.id.ll_language).setPadding(0, (int) CommonUtil.getStatusBarHeight(LoginActivity.this), 0, 0);

        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        linearLayout_1 = findViewByID_My(R.id.linearLayout_1);
        linearLayout_2 = findViewByID_My(R.id.linearLayout_2);
        linearLayout_3 = findViewByID_My(R.id.linearLayout_3);
        linearLayout_4 = findViewByID_My(R.id.linearLayout_4);
        linearLayout_5 = findViewByID_My(R.id.linearLayout_5);
        linearLayout_6 = findViewByID_My(R.id.linearLayout_6);


        title_right = findViewByID_My(R.id.title_right);
        tv_language = findViewByID_My(R.id.tv_language);

        textView = findViewByID_My(R.id.textView);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        editText4 = findViewByID_My(R.id.editText4);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);

        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
        imageView3 = findViewByID_My(R.id.imageView3);


        //对et的输入状态进行监听
        editText1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    imageView1.setVisibility(View.GONE);
                } else {
                    imageView1.setVisibility(View.VISIBLE);
                }
            }
        });
        //对et的输入状态进行监听
        editText2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    imageView2.setVisibility(View.GONE);
                } else {
                    imageView2.setVisibility(View.VISIBLE);
                }
            }
        });
        /*editText2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE && type == 1) {
                    if (match()) {
//                        KeyboardUtils.hideSoftInput(editText2);//隐藏键盘

                        textView3.setClickable(false);
                        LoginActivity.this.showProgress(true, getString(R.string.login_h7));
                        params.put("uuid", CommonUtil.getIMEI(LoginActivity.this));//IMEI
                        params.put("mobile", phonenum);
                        params.put("password", password);
                        params.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                        RequestLogin(params);//登录
                    }
                }
                return true;
            }
        });*/

        changeUI();
        /*View view1 = findViewByID_My(R.id.view1);
        ViewGroup.LayoutParams lp = view1.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(this) / 5;*/
    }

    @Override
    protected void initData() {
        RequestUpgrade("?app_type=" + 1);//检查更新

        String string3 = "?lang_type=" + localUserInfo.getLanguage_Type();
        RequestSmsCodeList(string3);//手机号国家代码集合

        /*byte[] mBytes = null;
        String mString = "{阿达大as家阿sdf什顿附asd件好久}";
        AES mAes = new AES();
        try {
            mBytes = mString.getBytes("UTF-8");
        } catch (Exception e) {
            Log.i("qing", "MainActivity----catch");
        }
        String enString = mAes.encrypt(mBytes);
        MyLogger.i("加密后：" + enString);
        String deString = mAes.decrypt("P9ezA6lsRKVID383Rg5mwQ==");
        MyLogger.i("解密后：" + deString);*/
    }

    //手机号国家代码集合
    private void RequestSmsCodeList(String string) {
        OkHttpClientManager.getAsyn(LoginActivity.this, URLs.Login + string, new OkHttpClientManager.ResultCallback<SmsCodeListModel>() {
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

                register_agreement = response.getUrl();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.title_right:
            case R.id.linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.linearLayout2:
                type = 2;
                changeUI();
                break;
            case R.id.ll_language:
                //选择语言
                /*if (list.size() > 0) {
                    SelectLanguagePopupWindow popupwindow = new SelectLanguagePopupWindow(LoginActivity.this, LoginActivity.class, list);
                    popupwindow.showAtLocation(LoginActivity.this.findViewById(R.id.title_right), Gravity.CENTER, 0, 0);
                } else {
                    String string3 = "?lang_type=" + localUserInfo.getLanguage_Type();
                    RequestSmsCodeList(string3);//手机号国家代码集合
                }*/
                if (LocalUserInfo.getInstance(this).getLanguage_Type().equals("zh")) {
                    //保存语言
                    LocalUserInfo.getInstance(this).setLanguage_Type("en");
                } else {
                    LocalUserInfo.getInstance(this).setLanguage_Type("zh");
                }

                String language = null;
                switch (LocalUserInfo.getInstance(this).getLanguage_Type()) {
                    case "zh":
                        //设置为中文
                        language = LanguageType.CHINESE.getLanguage();

                        //显示与设置相反
                        title_right.setImageResource(R.mipmap.ic_guoqi_english);
                        tv_language.setText("English");
                        break;
                    case "en":
                        //设置为英文
                        language = LanguageType.ENGLISH.getLanguage();

                        //显示与设置相反
                        title_right.setImageResource(R.mipmap.ic_guoqi_chinese);
                        tv_language.setText("简体中文");

                        break;
                }

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    LanguageUtil.changeAppLanguage(MyApplication.getContext(), language);
                }
                SpUtil.getInstance(this).putString(SpUtil.LANGUAGE, language);

//                ActivityUtils.finishAllActivitiesExceptNewest();//结束除最新之外的所有 Activity
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                this.startActivity(intent);

                break;
            case R.id.textView:
                //选择国家代码
                if (list1.size() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list", (Serializable) list1);
                    CommonUtil.gotoActivityWithData(LoginActivity.this, SelectCountryActivity.class, bundle, false);
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
                //忘记密码
                CommonUtil.gotoActivity(LoginActivity.this, ForgetPasswordActivity.class, false);
                break;
            case R.id.textView2:
                //注册
                type = 2;
                changeUI();
                /*Bundle bundle1 = new Bundle();
                bundle1.putString("open_id", "");
                CommonUtil.gotoActivityWithData(LoginActivity.this, RegisteredActivity.class, bundle1, false);*/
                break;
            case R.id.textView3:
                //确认登录
                if (type == 1) {
                    if (match()) {
                        textView3.setClickable(false);
                        this.showProgress(true, getString(R.string.login_h7));
                        params.put("uuid", CommonUtil.getIMEI(LoginActivity.this));//IMEI
                        params.put("mobile", phonenum);
                        params.put("password", password);
                        params.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                        RequestLogin(params);//登录
                    }
                } else {
                    if (match2()) {
                        textView3.setClickable(false);
                        showProgress(true, getString(R.string.registered_h14));
                        HashMap<String, String> params = new HashMap<>();
                        params.put("thirdparty_open_id", "");//授权
                        params.put("mobile", phonenum);//手机号
                        params.put("password", password);//密码（不能小于6位数）
                        params.put("code", code);//手机验证码
//                    params.put("nickname", nickname);//昵称
//                    params.put("app_type", "1");//1、Android  2、iOS
                        params.put("invite_code", num);//邀请码
                        params.put("uuid", CommonUtil.getIMEI(LoginActivity.this));//IMEI
                        params.put("register_addr", "");//注册地址
                        params.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                        RequestRegistered(params);//注册
                    }
                }

//                CommonUtil.gotoActivity(LoginActivity.this, MainActivity.class, true);
                break;
            case R.id.imageView1:
                editText1.setText("");
                imageView1.setVisibility(View.GONE);
                break;
            case R.id.imageView2:
                editText2.setText("");
                imageView2.setVisibility(View.GONE);
                break;
            case R.id.textView4:
                //发送验证码
                phonenum = editText1.getText().toString().trim();

                if (TextUtils.isEmpty(phonenum)) {
                    myToast(getString(R.string.registered_h1));
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
                    RequestCode(params1,textView4);//获取验证码
                }
                break;
            case R.id.textView5:
                //用户注册协议
                /*if (LocalUserInfo.getInstance(this).getLanguage_Type().equals("1")) {
                    //英文
                    register_agreement = HOST + "/wechat/article/detail?id=9303693c9e34e02560fe2039f4ddd654";
                } else {
                    register_agreement = HOST + "/wechat/article/detail?id=9303693c9e34e02560fe2039f4ddd654";
                }*/
//                register_agreement = HOST + "/wechat/article/detail?id=9303693c9e34e02560fe2039f4ddd654";
                if (!register_agreement.equals("")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", register_agreement);
                    CommonUtil.gotoActivityWithData(LoginActivity.this, WebContentActivity.class, bundle, false);
                }
                break;
            case R.id.imageView3:
                //勾选协议
                isgouxuan = !isgouxuan;
                if (isgouxuan)
                    imageView3.setImageResource(R.mipmap.ic_xuanze);
                else
                    imageView3.setImageResource(R.mipmap.ic_weigouxuan);
                break;
        }
    }

    private void changeUI() {
        switch (type) {
            case 1:
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                linearLayout_2.setVisibility(View.GONE);
                linearLayout_4.setVisibility(View.GONE);
                linearLayout_5.setVisibility(View.VISIBLE);
                linearLayout_6.setVisibility(View.GONE);

                textView3.setText(getString(R.string.login_h5));
                break;
            case 2:
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);

                linearLayout_2.setVisibility(View.VISIBLE);
                linearLayout_4.setVisibility(View.VISIBLE);
                linearLayout_5.setVisibility(View.GONE);
                linearLayout_6.setVisibility(View.VISIBLE);

                textView3.setText(getString(R.string.registered_h9));
                break;
        }
    }

    //登录
    private void RequestLogin(Map<String, String> params) {
        OkHttpClientManager.postAsyn(LoginActivity.this, URLs.Login, params, new OkHttpClientManager.ResultCallback<LoginModel>() {
            @Override
            public void onError(final Request request, String info, Exception e) {
                hideProgress();
                textView3.setClickable(true);
//                myToast("密码错误，请重新输入");
                if (!info.equals("")) {
                    if (info.equals("100")) {
                        MyLogger.i(">>>>>>>>" + e.getMessage());
                        //特殊情况-登录后手机不是注册时的手机
                        dialog = new BaseDialog(LoginActivity.this);
                        dialog.contentView(R.layout.dialog_loginerror)
                                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT))
                                .animType(BaseDialog.AnimInType.CENTER)
                                .canceledOnTouchOutside(true)
                                .dimAmount(0.8f)
                                .show();
//                        TextView textView1 = dialog.findViewById(R.id.textView1);
//                        textView1.setText(e.getMessage());
                        final TextView tv2 = dialog.findViewById(R.id.textView2);

                        tv2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //发送验证码
                                LoginActivity.this.showProgress(true, getString(R.string.app_sendcode_hint1));
                                tv2.setClickable(false);
                                /*String string = "?mobile=" + phonenum +
                                        "&type=" + "10";//类型*/
                                HashMap<String, String> params = new HashMap<>();
                                params.put("mobile", phonenum);
                                params.put("type", "10");
                                params.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                                RequestCode(params, tv2);//获取验证码
                            }
                        });
                        final EditText editText1 = dialog.findViewById(R.id.editText1);
                        dialog.findViewById(R.id.textView3).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!editText1.getText().toString().trim().equals("")) {
                                    CommonUtil.hideSoftKeyboard_fragment(v, LoginActivity.this);
                                    dialog.dismiss();
                                    showProgress(true, getString(R.string.login_h10));
                                    HashMap<String, String> params = new HashMap<>();
                                    params.put("uuid", CommonUtil.getIMEI(LoginActivity.this));//IMEI
                                    params.put("code", editText1.getText().toString().trim());
                                    params.put("mobile", phonenum);
                                    params.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                                    RequestAuthorization(params);
                                } else {
                                    myToast(getString(R.string.login_h2));
                                }
                            }
                        });
                        dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    } else if (info.equals("102")) {
                        //该账户尚未激活，请完成人脸识别后进行操作
                        showToast(getString(R.string.login_h13), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                /*Bundle bundle1 = new Bundle();
                                bundle1.putString("mobile", phonenum);
                                CommonUtil.gotoActivityWithData(LoginActivity.this, RecordVideoActivity.class, bundle1);
                                dialog.dismiss();*/
                            }
                        });
                    } else {
                        myToast(info);
                    }
                }
            }

            @Override
            public void onResponse(final LoginModel response) {
                MyLogger.i(">>>>>>>>>登录" + response);
                textView3.setClickable(true);
                localUserInfo.setTime(System.currentTimeMillis() + "");
                if (response.getStatus() == 3) {
                    //封号
                    /*showToast(getString(R.string.login_h9),
                            getString(R.string.app_confirm),
                            getString(R.string.app_cancel), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //保存Token
//                                String token = jObj.getString("token");
                                    localUserInfo.setToken(response.getFresh_token());
                                    CommonUtil.gotoActivity(LoginActivity.this, OnlineServiceActivity.class, false);
                                    dialog.dismiss();
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });*/
                } else {
                    //保存Token
                    localUserInfo.setToken(response.getFresh_token());
                    //保存id
                    localUserInfo.setUserId(response.getId());
                    //保存邀请码
                    localUserInfo.setInvuteCode(response.getInvite_code());
                    //保存电话号码
                    localUserInfo.setPhoneNumber(response.getMobile());
                    //保存用户昵称
                    localUserInfo.setNickname(response.getNickname());
                    //保存头像
                    localUserInfo.setUserImage(response.getHead());
                    //保存地区代码
                    localUserInfo.setMobile_State_Code(response.getMobile_state_code());


                    //是否为商户
//                    localUserInfo.setMerchant(response.getMerchant() + "");
                    //是否开通支付
//                    localUserInfo.setPay(response.getPay() + "");
                    //是否开通收款
//                    localUserInfo.setGather(response.getGather() + "");
                    //保存钱包地址
//                        localUserInfo.setWalletaddr(walletaddr);
                    //保存邮箱
//                        localUserInfo.setEmail(email);
                    //保存姓名
//                    localUserInfo.setUserName(jObj1.getString("name"));

                    hideProgress();
                    MainActivity.isOver = false;

                    ActivityUtils.finishAllActivitiesExceptNewest();//结束除最新之外的所有 Activity
                    CommonUtil.gotoActivity(LoginActivity.this, MainActivity.class, true);

                }
            }
        }, true);

    }
    //注册
    private void RequestRegistered(Map<String, String> params) {
        OkHttpClientManager.postAsyn_Reg(LoginActivity.this, URLs.Registered, params, new OkHttpClientManager.ResultCallback<RegisteredModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                textView3.setClickable(true);
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(RegisteredModel response) {
                MyLogger.i(">>>>>>>>>注册" + response);
                textView3.setClickable(true);
                hideProgress();

                //保存Token
                localUserInfo.setToken(response.getFresh_token());
                //保存id
                localUserInfo.setUserId(response.getId());
                //保存邀请码
                localUserInfo.setInvuteCode(response.getInvite_code());
                //保存电话号码
                localUserInfo.setPhoneNumber(response.getMobile());
                //保存用户昵称
                localUserInfo.setNickname(response.getNickname());
                //保存头像
                localUserInfo.setUserImage(response.getHead());
                //保存地区代码
                localUserInfo.setMobile_State_Code(response.getMobile_state_code());


                //是否为商户
//                    localUserInfo.setMerchant(response.getMerchant() + "");
                //是否开通支付
//                    localUserInfo.setPay(response.getPay() + "");
                //是否开通收款
//                    localUserInfo.setGather(response.getGather() + "");
                //保存钱包地址
//                        localUserInfo.setWalletaddr(walletaddr);
                //保存邮箱
//                        localUserInfo.setEmail(email);
                //保存姓名
//                    localUserInfo.setUserName(jObj1.getString("name"));


                hideProgress();
                MainActivity.isOver = false;

                Bundle bundle = new Bundle();
//                    bundle.putInt("isShowAd", jObj1.getInt("experience"));
                bundle.putInt("isShowAd", 1);

                ActivityUtils.finishAllActivitiesExceptNewest();//结束除最新之外的所有 Activity
                CommonUtil.gotoActivityWithFinishOtherAllAndData(LoginActivity.this, MainActivity.class, bundle, true);

            }
        }, true);

    }

    //获取验证码
    private void RequestCode(HashMap<String, String> params, final TextView tv) {
        OkHttpClientManager.postAsyn(LoginActivity.this, URLs.Code, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                tv.setClickable(true);
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>验证码" + response);
                tv.setClickable(true);

                if (time != null) {
                    time.cancel();
                }
                time = new TimeCount(60000, 1000, tv);//构造CountDownTimer对象

                time.start();//开始计时
                myToast(getString(R.string.app_sendcode_hint));
            }
        }, true);

    }

    //设备授权
    private void RequestAuthorization(HashMap<String, String> params) {
        OkHttpClientManager.postAsyn(LoginActivity.this, URLs.Login_Authorization, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                MyLogger.i(">>>>>>>>>授权" + response);
                HashMap<String, String> params = new HashMap<>();
                params.put("uuid", CommonUtil.getIMEI(LoginActivity.this));//IMEI
                params.put("mobile", phonenum);
                params.put("password", password);
                params.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                RequestLogin(params);//登录
            }
        });
    }

    private boolean match() {
        phonenum = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(phonenum)) {
            myToast(getString(R.string.login_h1));
            return false;
        }
        password = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            myToast(getString(R.string.login_h2));
            return false;
        }

        /*if (!CommonUtil.notHasLightSensorManager(LoginActivity.this)){
            myToast(getString(R.string.app_abfooter_loading));
            return false;
        }*/

        boolean isMoNiQi = EasyProtectorLib.checkIsRunningInEmulator(this, new EmulatorCheckCallback() {
            @Override
            public void findEmulator(String emulatorInfo) {
                MyLogger.i("设备信息", emulatorInfo);
            }
        });
        if (isMoNiQi == true) {//是模拟器
            myToast(getString(R.string.login_h17));
            return false;
        }

        return true;
    }

    private boolean match2() {
        phonenum = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(phonenum)) {
            myToast(getString(R.string.registered_h1));
            return false;
        }
        code = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            myToast(getString(R.string.registered_h2));
            return false;
        }
        password= editText2.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            myToast(getString(R.string.registered_h3));
            return false;
        }
        /*password2 = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(password2)) {
            myToast(getString(R.string.registered_h4));
            return false;
        }
        if (!password1.equals(password2)) {
            myToast(getString(R.string.registered_h12));
            return false;
        }*/
        num = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(num)) {
            myToast(getString(R.string.registered_h5));
            return false;
        }

        /*nickname = editText6.getText().toString().trim();
        if (TextUtils.isEmpty(nickname)) {
            myToast(getString(R.string.registered_h11));
            return false;
        }*/

        if (!isgouxuan) {
            myToast(getString(R.string.registered_h15));
            return false;
        }

        /*boolean isMoNiQi = EasyProtectorLib.checkIsRunningInEmulator(this, new EmulatorCheckCallback() {
            @Override
            public void findEmulator(String emulatorInfo) {
                MyLogger.i("设备信息", emulatorInfo);
            }
        });
        if (isMoNiQi == true) {//是模拟器
            myToast(getString(R.string.login_h17));
            return false;
        }*/

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

    @Override
    protected void updateView() {
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
        TextView tv;

        public TimeCount(long millisInFuture, long countDownInterval, TextView tv) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
            this.tv = tv;
        }

        @Override
        public void onFinish() {//计时完毕时触发
            tv.setText(getString(R.string.app_reacquirecode));
            tv.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            tv.setClickable(false);
            tv.setText(millisUntilFinished / 1000 + getString(R.string.app_codethen));
        }
    }

    private void RequestUpgrade(String string) {
        /*OkhttpUtil.okHttpGet(HOST + URLs.Upgrade, new CallBackUtil<UpgradeModel>() {

            @Override
            public UpgradeModel onParseResponse(Call call, Response response) {
                MyLogger.i(">>>>>>"+response.toString());
                return null;
            }

            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(UpgradeModel response) {
                MyLogger.i(">>>>>>"+response.toString());
            }
        });*/

        OkHttpClientManager.getAsyn(LoginActivity.this, URLs.Upgrade + string, new OkHttpClientManager.ResultCallback<UpgradeModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                hideProgress();
            }

            @Override
            public void onResponse(UpgradeModel response) {
                MyLogger.i(">>>>>>>>>更新" + response);
//                hideProgress();
                model_up = response;
                if (Integer.valueOf(CommonUtil.getVersionCode(LoginActivity.this)) < Integer.valueOf(response.getVersion_code())) {
//                    handler1.sendEmptyMessage(0);
                    showUpdateDialog();
                } else {
//                    showToast("已经是最新版，无需更新");
                }
            }
        });
    }

    //显示是否要更新的对话框
    private void showUpdateDialog() {
        dialog.contentView(R.layout.dialog_upgrade)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.CENTER)
                .canceledOnTouchOutside(true)
                .dimAmount(0.8f)
                .show();
        TextView textView1 = dialog.findViewById(R.id.textView1);
        TextView textView2 = dialog.findViewById(R.id.textView2);
        TextView textView3 = dialog.findViewById(R.id.textView3);
        TextView textView4 = dialog.findViewById(R.id.textView4);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                        /*Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(model_up.getUrl());
                        intent.setData(content_url);
                        startActivity(intent);*/
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);    //进度条，在下载的时候实时更新进度，提高用户友好度
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setCancelable(false);//点击外部不消失，返回键没用
//        progressDialog.setCanceledOnTouchOutside(false);//点击外部不消失，返回键有用
                    progressDialog.setTitle(getString(R.string.update_hint3));
                    progressDialog.setMessage(getString(R.string.update_hint4));
                    progressDialog.setProgress(0);
                    progressDialog.show();

                    //下载APK
                    InstallUtils.with(LoginActivity.this)
                            //必须-下载地址
                            .setApkUrl(model_up.getDownload_url())
                            //非必须-下载保存的文件的完整路径+/name.apk，使用自定义路径需要获取读写权限
//                                    .setApkPath(Constants.APK_SAVE_PATH)
                            //非必须-下载回调
                            .setCallBack(new InstallUtils.DownloadCallBack() {
                                @Override
                                public void onStart() {
                                    //下载开始
                                }

                                @Override
                                public void onComplete(final String path) {
                                    progressDialog.cancel();
                                    //下载完成
                                    //先判断有没有安装权限---适配8.0
                                    //如果不想用封装好的，可以自己去实现8.0适配
                                    InstallUtils.checkInstallPermission(LoginActivity.this, new InstallUtils.InstallPermissionCallBack() {
                                        @Override
                                        public void onGranted() {
                                            //去安装APK
                                            //一加手机8.0碰到了安装解析失败问题请添加下面判断
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                //先获取是否有安装未知来源应用的权限
                                                boolean haveInstallPermission = LoginActivity.this.getPackageManager().canRequestPackageInstalls();
                                                if (!haveInstallPermission) {
                                                    //跳转设置开启允许安装
                                                    Uri packageURI = Uri.parse("package:" + LoginActivity.this.getPackageName());
                                                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                                                    startActivityForResult(intent, 1000);
                                                    return;
                                                }
                                            }
                                            InstallUtils.installAPK(LoginActivity.this, path, new InstallUtils.InstallCallBack() {
                                                @Override
                                                public void onSuccess() {
                                                    myToast(getString(R.string.update_hint5));
                                                }

                                                @Override
                                                public void onFail(Exception e) {
                                                    myToast(getString(R.string.update_hint6) + e.toString());
                                                }
                                            });
                                        }

                                        @Override
                                        public void onDenied() {
                                            //弹出弹框提醒用户
                                            AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this)
                                                    .setTitle(getString(R.string.update_hint7))
                                                    .setMessage(getString(R.string.update_hint8))
                                                    .setNegativeButton(getString(R.string.app_cancel), null)
                                                    .setPositiveButton(getString(R.string.update_hint9), new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            //打开设置页面
                                                            InstallUtils.openInstallPermissionSetting(LoginActivity.this, new InstallUtils.InstallPermissionCallBack() {
                                                                @Override
                                                                public void onGranted() {
                                                                    //去安装APK
                                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                                        //先获取是否有安装未知来源应用的权限
                                                                        boolean haveInstallPermission = LoginActivity.this.getPackageManager().canRequestPackageInstalls();
                                                                        if (!haveInstallPermission) {
                                                                            //跳转设置开启允许安装
                                                                            Uri packageURI = Uri.parse("package:" + LoginActivity.this.getPackageName());
                                                                            Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                                                                            startActivityForResult(intent, 1000);
                                                                            return;
                                                                        }
                                                                    }
                                                                    InstallUtils.installAPK(LoginActivity.this, path, new InstallUtils.InstallCallBack() {
                                                                        @Override
                                                                        public void onSuccess() {
                                                                            myToast(getString(R.string.update_hint5));
                                                                        }

                                                                        @Override
                                                                        public void onFail(Exception e) {
                                                                            myToast(getString(R.string.update_hint6) + e.toString());
                                                                        }
                                                                    });
                                                                }

                                                                @Override
                                                                public void onDenied() {
                                                                    //还是不允许咋搞？
                                                                    finish();
//                                                                            Toast.makeText(MainActivity.this, "不允许安装咋搞？强制更新就退出应用程序吧！", Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                        }
                                                    })
                                                    .create();
                                            alertDialog.show();
                                        }
                                    });

                                }

                                @Override
                                public void onLoading(long total, long current) {
                                    //下载中
                                    progressDialog.setMax((int) total);
                                    progressDialog.setProgress((int) current);
                                }

                                @Override
                                public void onFail(Exception e) {
                                    //下载失败
                                }

                                @Override
                                public void cancle() {
                                    //下载取消
                                }
                            })
                            //开始下载
                            .startDownload();
                } else {
                    Toast.makeText(LoginActivity.this, getString(R.string.update_hint1),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        /*showToast(getString(R.string.update_hint) + model_up.getVersion_title(),
                getString(R.string.app_confirm_true),
                getString(R.string.app_cancel), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
//                        finish();
                    }
                });*/
    }


    @Override
    protected void onResume() {
        super.onResume();
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            startPermissionsActivity();
        }

        textView.setText("+" + localUserInfo.getMobile_State_Code());
       /* switch (localUserInfo.getMobile_State_Code().length()) {
            case 2:
                editText1.setPadding(CommonUtil.dip2px(LoginActivity.this, 60), 0, 0, 0);
                break;
            case 3:
                editText1.setPadding(CommonUtil.dip2px(LoginActivity.this, 70), 0, 0, 0);
                break;
            case 4:
                editText1.setPadding(CommonUtil.dip2px(LoginActivity.this, 75), 0, 0, 0);
                break;
            case 5:
                editText1.setPadding(CommonUtil.dip2px(LoginActivity.this, 80), 0, 0, 0);
                break;

        }*/

        switch (LocalUserInfo.getInstance(this).getLanguage_Type()) {
            case "zh":
                //设置为中文

                //显示与设置相反
                title_right.setImageResource(R.mipmap.ic_guoqi_english);
                tv_language.setText("English");
                break;
            case "en":
                //设置为英文

                //显示与设置相反
                title_right.setImageResource(R.mipmap.ic_guoqi_chinese);
                tv_language.setText("简体中文");

                break;
        }

    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }
}
