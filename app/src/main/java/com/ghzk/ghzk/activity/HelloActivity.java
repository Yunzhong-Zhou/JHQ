package com.ghzk.ghzk.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.ghzk.ghzk.MyApplication;
import com.ghzk.ghzk.R;
import com.ghzk.ghzk.utils.LocalUserInfo;
import com.ghzk.ghzk.utils.MyLogger;
import com.ghzk.ghzk.utils.changelanguage.LanguageType;
import com.ghzk.ghzk.utils.changelanguage.LanguageUtil;
import com.ghzk.ghzk.utils.changelanguage.SpUtil;
import com.lahm.library.EasyProtectorLib;
import com.lahm.library.EmulatorCheckCallback;


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
        /*if (!CommonUtil.notHasLightSensorManager(HelloActivity.this)){
            finish();
        }*/

        boolean isMoNiQi = EasyProtectorLib.checkIsRunningInEmulator(this, new EmulatorCheckCallback() {
            @Override
            public void findEmulator(String emulatorInfo) {
                MyLogger.i("设备信息", emulatorInfo);
            }
        });
        if (isMoNiQi == true) {//是模拟器
            finish();
        }


        // 如果是第一次启动，则先进入功能引导页
        if (user_first) {
            setting.edit().putBoolean("FIRST", false).commit();
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            finish();
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

}
