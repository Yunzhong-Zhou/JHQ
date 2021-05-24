package com.ghzk.ghzk.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cy.dialog.BaseDialog;
import com.ghzk.ghzk.MyApplication;
import com.ghzk.ghzk.R;
import com.ghzk.ghzk.model.SmsCodeListModel;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
import com.ghzk.ghzk.utils.LocalUserInfo;
import com.ghzk.ghzk.utils.changelanguage.LanguageType;
import com.ghzk.ghzk.utils.changelanguage.LanguageUtil;
import com.ghzk.ghzk.utils.changelanguage.SpUtil;
import com.squareup.okhttp.Request;
import com.zyinux.specialstring.relase.SpecialStringBuilder;
import com.zyinux.specialstring.relase.SpecialStyle;
import com.zyinux.specialstring.relase.style.ClickableStyle;


/**
 * Created by zyz on 2016/6/17.
 * Email：1125213018@qq.com
 * description：启动页
 */
public class HelloActivity extends Activity {
    private static final String SHARE_APP_TAG = "HelloActivity";
    String language = null;
    BaseDialog dialog = null;
    String register_agreement = "";
    @Override
    public void onDestroy() {
        super.onDestroy();
        /*if (mImmersionBar != null)
            mImmersionBar.destroy();*/
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*//在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现，建议该方法放在Application的初始化方法中
        SDKInitializer.initialize(getApplicationContext());*/
        //设置无标题
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        /*//设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        setContentView(R.layout.activity_hello);

        //语言切换
        switch (LocalUserInfo.getInstance(this).getLanguage_Type()) {
            case "zh":
                //设置为中文
                language = LanguageType.CHINESE.getLanguage();
                break;
            case "en":
                //设置为英文
                language = LanguageType.ENGLISH.getLanguage();
                break;
            case "ja":
                //设置为日文
                language = LanguageType.JAPANESE.getLanguage();
                break;
            case "ko":
                //设置为韩文
                language = LanguageType.KOREAN.getLanguage();
                break;
            case "vi":
                //设置为越南文
                language = LanguageType.VIETNAMESE.getLanguage();
                break;
        }

        //保存语言
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            LanguageUtil.changeAppLanguage(MyApplication.getContext(), language);
        }
        SpUtil.getInstance(this).putString(SpUtil.LANGUAGE, language);


        // 判断是否是第一次开启应用
        SharedPreferences setting = getSharedPreferences(SHARE_APP_TAG, 0);
        Boolean user_first = setting.getBoolean("FIRST", true);

        //判断是否为真机
       /* boolean isMoNiQi = EasyProtectorLib.checkIsRunningInEmulator(this, new EmulatorCheckCallback() {
            @Override
            public void findEmulator(String emulatorInfo) {
                MyLogger.i("设备信息", emulatorInfo);
            }
        });
        if (isMoNiQi == true) {//是模拟器
            finish();
        }*/

        // 如果是第一次启动，则先进入功能引导页
        if (user_first) {
            RequestSmsCodeList("?lang_type=zh");//手机号国家代码集合
            dialog = new BaseDialog(this);
            dialog.contentView(R.layout.dialog_yinsi)
                    .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT))
                    .animType(BaseDialog.AnimInType.BOTTOM)
                    .canceledOnTouchOutside(true)
                    .gravity(Gravity.CENTER)
                    .dimAmount(0.8f)
                    .show();
            //颜色变化
            TextView textView = dialog.findViewById(R.id.textView2);

            //构建SpecialStyle 用来设置样式的核心类
            SpecialStyle style=new SpecialStyle();
            SpecialStringBuilder sb=new SpecialStringBuilder();

            //设置文本颜色为黑色。第二个参数save的意思是代表该样式是否应用到下一段文字，如果不传则为true
            style.setColor(getResources().getColor(R.color.black1),false);
            //为文字设置样式
            sb.append(getString(R.string.txt23),style);

            //设置颜色背景和点击事件样式
            //点击事件默认为不应用于下一段文字
            style.setColor(getResources().getColor(R.color.green),false)
                    .setBackgroundColor(getResources().getColor(R.color.white),false)
                    .setClickable(new ClickableStyle.OnClick() {
                        @Override
                        public void onClick(View widget) {
                            if (register_agreement != null && !register_agreement.equals("")) {
                                Bundle bundle = new Bundle();
                                bundle.putString("url", register_agreement);
                                CommonUtil.gotoActivityWithData(HelloActivity.this, WebContentActivity.class, bundle, false);
                            }
                        }
                    });
            sb.append(getString(R.string.registered_h7),style);

            style.setColor(getResources().getColor(R.color.black1),false);
            sb.append("和",style);

            style.setColor(getResources().getColor(R.color.green),false)
                    .setBackgroundColor(getResources().getColor(R.color.white),false)

                    .setClickable(new ClickableStyle.OnClick() {
                        @Override
                        public void onClick(View widget) {
                            widget.setBackgroundResource(R.color.transparent);
                            CommonUtil.gotoActivity(HelloActivity.this, TXTActivity.class);
                        }
                    });
            sb.append(getString(R.string.registered_h16),style);

            style.setColor(getResources().getColor(R.color.black1),false);
            sb.append(getString(R.string.txt24),style);

            //为TextView设置刚刚构建的文本
            textView.setText(sb.getCharSequence());
            //如果为文字添加了点击事件，请添加这一句，否则点击事件不生效
            textView.setMovementMethod(LinkMovementMethod.getInstance());

            dialog.findViewById(R.id.textView3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    finish();
                }
            });
            dialog.findViewById(R.id.textView4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    setting.edit().putBoolean("FIRST", false).commit();
                    Intent intent = new Intent(HelloActivity.this, GuideActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        } else {
            // 如果不是第一次启动app，则正常显示启动屏
            setContentView(R.layout.viewpager_page);

            //启动页
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    enterHomeActivity();
                }
            }, 2000);
        }

    }

    /**
     * 如果是7.0以下，我们需要调用changeAppLanguage方法，
     * 如果是7.0及以上系统，直接把我们想要切换的语言类型保存在SharedPreferences中即可
     * 然后重新启动MainActivity
     */
    private void enterHomeActivity() {
        if (LocalUserInfo.getInstance(HelloActivity.this).getUserId().equals("")) {
            Intent intent = new Intent(this, LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
//            CommonUtil.gotoActivityWithFinishOtherAll(HelloActivity.this, LoginActivity.class, true);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
//            CommonUtil.gotoActivityWithFinishOtherAll(HelloActivity.this, MainActivity.class, true);
        }

        finish();

    }

    //手机号国家代码集合
    private void RequestSmsCodeList(String string) {
        OkHttpClientManager.getAsyn(HelloActivity.this, URLs.Registered + string, new OkHttpClientManager.ResultCallback<SmsCodeListModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {

            }

            @Override
            public void onResponse(SmsCodeListModel response) {

                register_agreement = response.getUrl();
            }
        });

    }
}
