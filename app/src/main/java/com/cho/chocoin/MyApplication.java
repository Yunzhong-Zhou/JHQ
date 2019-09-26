package com.cho.chocoin;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.support.multidex.MultiDex;

import com.cho.chocoin.base.ScreenAdaptation;
import com.cho.chocoin.utils.MyLogger;
import com.hjq.toast.ToastUtils;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by zyz on 2018/1/18.
 */

public class MyApplication extends Application {
    // 上下文菜单
    private Context mContext;

    private static MyApplication myApplication;

    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        mContext = this;
        myApplication = this;

        //腾讯bugly 异常上报初始化-建议在测试阶段建议设置成true，发布时设置为false。
        CrashReport.initCrashReport(getApplicationContext(), "789711ef96", false);

        //toast初始化
        ToastUtils.init(this);


        //极光推送
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);
//        JPushInterface.setAlias(this, 0, LocalUserInfo.getInstance(this).getUserId());
        /*// 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, LocalUserInfo.getInstance(this).getUserId()));
        JPushInterface.setAlias(this, //上下文对象
                LocalUserInfo.getInstance(this).getUserId(), //别名
                new TagAliasCallback() {//回调接口,i=0表示成功,其它设置失败
                    @Override
                    public void gotResult(int i, String s, Set<String> set) {
                        Log.d("alias", "设置别名结果为" + i);
                    }
                });*/

        new ScreenAdaptation(this,828,1792).register();
//        new ScreenAdaptation(this,720,1280).register();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

//        MultiDex.install(this);//方法数超过64k
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "极光推送 别名设置成功";
                    MyLogger.i(logs);
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。

                    break;
                case 6002:
                    logs = "由于超时而无法设置别名和标签。 60秒后再试。";
                    MyLogger.i(logs);
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    MyLogger.i(logs);
                    break;
            }
//            ExampleUtil.showToast(logs, getApplicationContext());
        }
    };
    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    MyLogger.i("Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getApplicationContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
                    MyLogger.i("Unhandled msg - " + msg.what);
                    break;
            }
        }
    };

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }
}
