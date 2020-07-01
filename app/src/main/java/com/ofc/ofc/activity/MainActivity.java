package com.ofc.ofc.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cy.dialog.BaseDialog;
import com.maning.updatelibrary.InstallUtils;
import com.mob.pushsdk.MobPush;
import com.mob.pushsdk.MobPushCustomMessage;
import com.mob.pushsdk.MobPushNotifyMessage;
import com.mob.pushsdk.MobPushReceiver;
import com.next.easynavigation.view.EasyNavigationBar;
import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.fragment.Fragment1;
import com.ofc.ofc.fragment.Fragment2;
import com.ofc.ofc.fragment.Fragment3;
import com.ofc.ofc.fragment.Fragment4;
import com.ofc.ofc.fragment.Fragment5;
import com.ofc.ofc.model.UpgradeModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.LocalUserInfo;
import com.ofc.ofc.utils.MyLogger;
import com.ofc.ofc.utils.permission.PermissionsActivity;
import com.ofc.ofc.utils.permission.PermissionsChecker;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class MainActivity extends BaseActivity {
//    public static BottomTabBar mBottomTabBar;
//    private BottomView bottomView;

    public static EasyNavigationBar navigationBar;
    private List<Fragment> fragments = new ArrayList<>();

    public static boolean isOver = false;
    public static int item = 2;
    int isShowAd = 0;//是否显示弹窗
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
//            android.Manifest.permission.ACCESS_COARSE_LOCATION
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

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //切换语言
        Resources resources = getResources();
        // 获取应用内语言
        Configuration configuration = resources.getConfiguration();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        switch (LocalUserInfo.getInstance(this).getLanguage_Type()) {
            case "zh":
                //设置为中文
                configuration.locale = new Locale("zh", "CN");
                break;
            case "en":
                //设置为英文
                configuration.locale = new Locale("en", "US");
                break;
            case "ja":
                //设置为日文
                configuration.locale = new Locale("ja", "JP");
                break;
            case "ko":
                //设置为韩文
                configuration.locale = new Locale("ko", "KR");
                break;
            case "vi":
                //设置为越南文
                configuration.locale = new Locale("vi", "VN");
//                configuration.setLocale(Locale.);
                break;
        }
        MyLogger.i(">>>>>>>>>>" + configuration.locale.toString());
        resources.updateConfiguration(configuration, displayMetrics);

        setContentView(R.layout.activity_main);

        setSwipeBackEnable(false); //主 activity 可以调用该方法，禁止滑动删除

        mPermissionsChecker = new PermissionsChecker(this);


        mImmersionBar.reset().init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        item = 2;
        isOver = false;
    }

    @Override
    protected void initView() {
        navigationBar = findViewByID_My(R.id.navigationBar);

        item = getIntent().getIntExtra("item", 2);

        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        fragments.add(new Fragment5());
        String[] tabText = {getString(R.string.fragment3) + "",
                getString(R.string.fragment2) + "",
                "",
                getString(R.string.fragment4) + "",
                getString(R.string.fragment5) + ""};
        //未选中icon
        int[] normalIcon = {R.mipmap.tab1_0, R.mipmap.tab2_0, R.mipmap.tab3_0, R.mipmap.tab4_0, R.mipmap.tab5_0};
        //选中时icon
        int[] selectIcon = {R.mipmap.tab1_1, R.mipmap.tab2_1, R.mipmap.tab3_0, R.mipmap.tab4_1, R.mipmap.tab5_1};

        navigationBar.titleItems(tabText)//文字
                .normalIconItems(normalIcon)//未选中
                .selectIconItems(selectIcon)//选中
                .normalTextColor(getResources().getColor(R.color.tab_black))//未选中字体颜色
                .selectTextColor(getResources().getColor(R.color.white))//选中字体颜色
                .tabTextTop(4)//Tab文字距Tab图标的距离
                .tabTextSize(10)//Tab文字大小
                .fragmentList(fragments)
                .anim(null)
                .addLayoutRule(EasyNavigationBar.RULE_BOTTOM)
                .addLayoutBottom(0)
                .addAlignBottom(true)
                .addAsFragment(true)
                .fragmentManager(getSupportFragmentManager())
                .onTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabClickEvent(View view, int position) {
                        switch (position) {
                            case 0:
                                MainActivity.item = 0;
                                mImmersionBar.reset()
//                                        .fitsSystemWindows(true)
//                                        .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
//                                        .statusBarColor(R.color.title_bg)
//                                        .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                                        .init();
                                break;
                            case 1:
                                MainActivity.item = 1;
                                mImmersionBar.reset()
                                        .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                                        .init();
                                break;
                            case 2:
                                MainActivity.item = 2;
//                                mImmersionBar.getTag("common").init();
                                mImmersionBar.reset()
//                                        .fitsSystemWindows(true)
//                                        .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
//                                        .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                                        .init();
                                break;
                            case 3:
                                MainActivity.item = 3;
                                mImmersionBar.reset()
//                                        .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                                        .init();
                                break;
                            case 4:
                                MainActivity.item = 4;
                                mImmersionBar.reset()
//                                        .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                                        .init();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                })
                .smoothScroll(false)  //点击Tab  Viewpager切换是否有动画
                .canScroll(false)    //Viewpager能否左右滑动
                .mode(EasyNavigationBar.MODE_ADD)
                .build();

//        item = 2;
        MyLogger.i("》》》》》》》》》" + item);
        navigationBar.selectTab(item);//设置显示的页面


        /*mBottomTabBar = findViewByID_My(R.id.bottom_tab_bar);
        mBottomTabBar
                .init(getSupportFragmentManager())//初始化方法，必须第一个调用；传入参数为V4包下的FragmentManager
//                .setImgSize(50,50)//设置ICON图片的尺寸
//                .setFontSize(8)//设置文字的尺寸
//                .setTabPadding(4,6,10)//设置ICON图片与上部分割线的间隔、图片与文字的间隔、文字与底部的间隔
                .setChangeColor(getResources().getColor(R.color.tab_blue), getResources().getColor(R.color.tab_black))//设置选中的颜色、未选中的颜色
                .addTabItem(getString(R.string.fragment1), R.mipmap.tab1_1, R.mipmap.tab1_0, Fragment1.class)//设置文字、选中图片、未选中图片、fragment
                .addTabItem(getString(R.string.fragment2), R.mipmap.tab2_1, R.mipmap.tab2_0, Fragment2.class)//设置文字、选中图片、未选中图片、fragment
                .addTabItem(getString(R.string.fragment3), R.mipmap.tab3_1, R.mipmap.tab3_0, Fragment3.class)//设置文字、选中图片、未选中图片、fragment
                .addTabItem(getString(R.string.fragment4), R.mipmap.tab4_1, R.mipmap.tab4_0, Fragment4.class)//设置文字、选中图片、未选中图片、fragment
                .addTabItem(getString(R.string.fragment5), R.mipmap.tab5_1, R.mipmap.tab5_0, Fragment5.class)//设置文字、选中图片、未选中图片、fragment

                .isShowDivider(false)//设置是否显示分割线
                .setTabBarBackgroundColor(Color.WHITE)//设置底部导航栏颜色
//                .setTabBarBackgroundResource(R.mipmap.ic_launcher)//设置底部导航栏的背景图片【与设置底部导航栏颜色方法不能同时使用，否则会覆盖掉前边设置的颜色】
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {
                        Log.i("TGA", "位置：" + position + "      选项卡的文字内容：" + name);
                        if (position == 1)
                            mBottomTabBar.setSpot(1, false);
                        switch (position) {
                            case 0:
                                MainActivity.item = 0;
                                mImmersionBar.reset()
//                                        .fitsSystemWindows(true)
//                                        .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
//                                        .statusBarColor(R.color.black_)
//                                        .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                                        .init();
                                break;
                            case 1:
                                MainActivity.item = 1;
                                mImmersionBar.reset()
//                                        .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                                        .init();
                                break;
                            case 2:
                                MainActivity.item = 2;
//                                mImmersionBar.getTag("common").init();
                                mImmersionBar.reset()
                                        .fitsSystemWindows(true)
                                        .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
//                                        .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                                        .init();
                                break;
                            case 3:
                                MainActivity.item = 3;
                                mImmersionBar.reset()
//                                        .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                                        .init();
                                break;
                            case 4:
                                MainActivity.item = 4;
                                mImmersionBar.reset()
//                                        .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                                        .init();
                                break;
                            default:
                                break;
                        }
                    }
                })
                .setCurrentTab(item);//设置当前选中的Tab，从0开始*/
    }

    @Override
    protected void initData() {

        MobPush.setAlias(localUserInfo.getUserId());//设置别名
//        MobPush.addTags();//设置标签
        //推送
        MobPush.setShowBadge(true); //默认是关闭的，设置true为打开显示角标，反之则为关闭显示角标
        MobPush.addPushReceiver(new MobPushReceiver() {
            @Override
            public void onCustomMessageReceive(Context context, MobPushCustomMessage message) {
                //接收自定义消息(透传)
                MyLogger.i("接收自定义消息(透传)onCustomMessageReceive:" + message.toString());
            }

            @Override
            public void onNotifyMessageReceive(Context context, MobPushNotifyMessage message) {
                //接收通知消息
                MyLogger.i("接收通知消息MobPush onNotifyMessageReceive:" + message.toString());

            }

            @Override
            public void onNotifyMessageOpenedReceive(Context context, MobPushNotifyMessage message) {
                //接收通知消息被点击事件
                MyLogger.i("接收通知消息被点击事件MobPush onNotifyMessageOpenedReceive:" + message.toString());
                Message msg = new Message();
//                msg.obj = "Click Message:" + message.toString();
//                msg.obj = "Click Message:" + message.getTitle();
//                msg.obj = "Click Message:" + message.getContent();
                switch (message.getExtrasMap().get("type")) {
                    case "1":
                        //网页
                        msg.what = 1;
                        msg.obj = message.getExtrasMap().get("url");
                        break;
                    case "2":
                        //订单详情
                        msg.what = 2;
                        msg.obj = message.getExtrasMap().get("symbol");
                        break;
                }
                handler.sendMessage(msg);

            }

            @Override
            public void onTagsCallback(Context context, String[] tags, int operation, int errorCode) {
                //接收tags的增改删查操作
                MyLogger.i("接收tags的增改删查操作onTagsCallback:" + operation + "  " + errorCode);
            }

            @Override
            public void onAliasCallback(Context context, String alias, int operation, int errorCode) {
                //接收alias的增改删查操作
                MyLogger.i("接收alias的增改删查操作onAliasCallback:" + alias + "  " + operation + "  " + errorCode);
            }
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Bundle bundle = new Bundle();
                switch (msg.what) {
                    case 1:
                        //网页
                        MyLogger.i(">>>>>>>>网页：" + msg.obj.toString());
                        Intent i = new Intent(MainActivity.this, WebContentActivity.class);
                        bundle.putString("url", msg.obj.toString());
                        i.putExtras(bundle);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        MainActivity.this.startActivity(i);
                        break;
                    case 2:
                        //订单详情
                        MyLogger.i(">>>>>>>>>symbol:" + msg.obj.toString());
//                        Intent i2 = new Intent(context, PredictionDetailActivity_MPChart.class);
                        Intent i2 = new Intent(MainActivity.this, PredictionDetailActivity.class);
                        bundle.putString("symbol", msg.obj.toString());
                        i2.putExtras(bundle);
                        i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        MainActivity.this.startActivity(i2);

                        break;
                    default:
                        break;
                }

                //当其它dialog未关闭的时候，再次显示dialog，会造成其他dialog无法dismiss的现象，建议使用toast
//			if(PushDeviceHelper.getInstance().isNotificationEnabled()) {
//				Toast.makeText(MainActivity.this, "回调信息\n" + (String) msg.OBJ, Toast.LENGTH_SHORT).show();
//			} else {//当做比通知栏后，toast是无法显示的
//				new DialogShell(MainActivity.this).autoDismissDialog(0, "回调信息\n" + (String)msg.OBJ, 2);
//			}

                return false;
            }
        });

        /*isShowAd = getIntent().getIntExtra("isShowAd", 0);
        if (isShowAd == 1) {
            //弹出广告窗 - 去完成
            final BaseDialog dialog = new BaseDialog(MainActivity.this);
            dialog.config(R.layout.dialog_showad, 0.8f, Gravity.CENTER,
                    BaseDialog.AnimInType.CENTER, WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT, true).show();
            dialog.findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
//                    MainActivity.mBottomTabBar.setCurrentTab(1);//跳转到借款

                    localUserInfo.setIsnewcomer("1");
                    //跳转到实名认证
                    RPCSDKManager.getInstance().setModifiedIdcardMsg(false, false, false);
                    List<LivenessTypeEnum> list = new ArrayList<>();
                    list.add(LivenessTypeEnum.Eye);
                    list.add(LivenessTypeEnum.Mouth);
                *//*list.add(LivenessTypeEnum.HeadLeft);
                list.add(LivenessTypeEnum.HeadRight);
                list.add(LivenessTypeEnum.HeadLeftOrRight);
                list.add(LivenessTypeEnum.HeadUp);
                list.add(LivenessTypeEnum.HeadDown);*//*
                    RPCSDKManager.getInstance().setLivenessTypeEnum(list);
                    RPCSDKManager.getInstance().setRPCLister(new RPCListener(MainActivity.this));

                    RPCSDKManager.getInstance().getTiker(new RPCSDKManager.TikerCallBack() {
                        @Override
                        public void onSuccess(String tiker) {
                            //获取tickterID成功，可启动实人认证方法
                            RPCSDKManager.getInstance().startAuthentication(MainActivity.this);
                        }

                        @Override
                        public void onFail(String code, String fail) {
                            //获取tickerID失败，不能执行实人认证操作
                            MyLogger.i(">>>>>>>>" + code + ">>>>>>" + fail);
                            myToast("获取tickerID失败，不能执行实人认证操作");
                        }
                    });
                }
            });
            dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }*/

        RequestUpgrade("?app_type=" + 1);//检查更新
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }

    /**
     * 双击退出函数
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            exitBy2Click();
            return false;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

    private Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, getString(R.string.app_out), Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            //退出
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isOver = false;
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            startPermissionsActivity();
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

        OkHttpClientManager.getAsyn(MainActivity.this, URLs.Upgrade + string, new OkHttpClientManager.ResultCallback<UpgradeModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                hideProgress();
            }

            @Override
            public void onResponse(UpgradeModel response) {
                MyLogger.i(">>>>>>>>>更新" + response);
//                hideProgress();
                model_up = response;
                if (Integer.valueOf(CommonUtil.getVersionCode(MainActivity.this)) < Integer.valueOf(response.getVersion_code())) {
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
                    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);    //进度条，在下载的时候实时更新进度，提高用户友好度
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setCancelable(false);//点击外部不消失，返回键没用
//        progressDialog.setCanceledOnTouchOutside(false);//点击外部不消失，返回键有用
                    progressDialog.setTitle(getString(R.string.update_hint3));
                    progressDialog.setMessage(getString(R.string.update_hint4));
                    progressDialog.setProgress(0);
                    progressDialog.show();

                    //下载APK
                    InstallUtils.with(MainActivity.this)
                            //必须-下载地址
                            .setApkUrl(model_up.getUrl())
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
                                    InstallUtils.checkInstallPermission(MainActivity.this, new InstallUtils.InstallPermissionCallBack() {
                                        @Override
                                        public void onGranted() {
                                            //去安装APK
                                            //一加手机8.0碰到了安装解析失败问题请添加下面判断
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                //先获取是否有安装未知来源应用的权限
                                                boolean haveInstallPermission = MainActivity.this.getPackageManager().canRequestPackageInstalls();
                                                if (!haveInstallPermission) {
                                                    //跳转设置开启允许安装
                                                    Uri packageURI = Uri.parse("package:" + MainActivity.this.getPackageName());
                                                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                                                    startActivityForResult(intent, 1000);
                                                    return;
                                                }
                                            }
                                            InstallUtils.installAPK(MainActivity.this, path, new InstallUtils.InstallCallBack() {
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
                                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                                    .setTitle(getString(R.string.update_hint7))
                                                    .setMessage(getString(R.string.update_hint8))
                                                    .setNegativeButton(getString(R.string.app_cancel), null)
                                                    .setPositiveButton(getString(R.string.update_hint9), new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            //打开设置页面
                                                            InstallUtils.openInstallPermissionSetting(MainActivity.this, new InstallUtils.InstallPermissionCallBack() {
                                                                @Override
                                                                public void onGranted() {
                                                                    //去安装APK
                                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                                        //先获取是否有安装未知来源应用的权限
                                                                        boolean haveInstallPermission = MainActivity.this.getPackageManager().canRequestPackageInstalls();
                                                                        if (!haveInstallPermission) {
                                                                            //跳转设置开启允许安装
                                                                            Uri packageURI = Uri.parse("package:" + MainActivity.this.getPackageName());
                                                                            Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                                                                            startActivityForResult(intent, 1000);
                                                                            return;
                                                                        }
                                                                    }
                                                                    InstallUtils.installAPK(MainActivity.this, path, new InstallUtils.InstallCallBack() {
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
                    Toast.makeText(MainActivity.this, getString(R.string.update_hint1),
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
}
