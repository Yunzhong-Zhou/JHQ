package com.ofc.ofc.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.ofc.ofc.MyApplication;
import com.ofc.ofc.R;
import com.ofc.ofc.utils.LocalUserInfo;
import com.ofc.ofc.utils.changelanguage.LanguageType;
import com.ofc.ofc.utils.changelanguage.LanguageUtil;
import com.ofc.ofc.utils.changelanguage.SpUtil;


/**
 * Created by zyz on 2016/6/17.
 * Email：1125213018@qq.com
 * description：启动页
 */
public class HelloActivity extends Activity {
    private static final String SHARE_APP_TAG = "HelloActivity";
    String language = null;
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

        /*//切换语言
        Resources resources = getResources();
        // 获取应用内语言
        Configuration configuration = resources.getConfiguration();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        switch (LocalUserInfo.getInstance(this).getLanguage_Type()) {
            case "en":
                //设置为英文
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    configuration.setLocale(Locale.US);
                } else {
                    configuration.locale = new Locale("en", "US");
                }
                break;
            case "zh":
                //设置为中文
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    configuration.setLocale(Locale.SIMPLIFIED_CHINESE);
                } else {
                    configuration.locale = new Locale("zh", "CN");
                }
                break;
            case "ja":
                //设置为日文
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    configuration.setLocale(Locale.JAPAN);
                } else {
                    configuration.locale = new Locale("ja", "JP");
                }
                break;
            case "ko":
                //设置为韩文
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    configuration.setLocale(Locale.KOREA);
                } else {
                    configuration.locale = new Locale("ko", "KR");
                }
                break;
            case "vi":
                //设置为越南文
                configuration.locale = new Locale("vi", "VN");
//                configuration.setLocale(Locale.);
                break;
        }
        resources.updateConfiguration(configuration, displayMetrics);

        createConfigurationContext(configuration);*/


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

        // 判断是否是第一次开启应用
        SharedPreferences setting = getSharedPreferences(SHARE_APP_TAG, 0);
        Boolean user_first = setting.getBoolean("FIRST", true);


        // 如果是第一次启动，则先进入功能引导页
        if (user_first) {
            setting.edit().putBoolean("FIRST", false).commit();
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            finish();
        } else {
            // 如果不是第一次启动app，则正常显示启动屏
            setContentView(R.layout.viewpager_page);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    enterHomeActivity(language);
                }
            }, 2000);
        }

    }
    /**
     * 如果是7.0以下，我们需要调用changeAppLanguage方法，
     * 如果是7.0及以上系统，直接把我们想要切换的语言类型保存在SharedPreferences中即可
     * 然后重新启动MainActivity
     * @param language
     */
    private void enterHomeActivity(String language) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            LanguageUtil.changeAppLanguage(MyApplication.getContext(), language);
        }
        SpUtil.getInstance(this).putString(SpUtil.LANGUAGE, language);

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
        /*Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();*/


        /*// 杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);*/

        finish();


    }

}
