package com.ghzk.ghzk.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.cretin.tools.fanpermission.FanPermissionListener;
import com.cretin.tools.fanpermission.FanPermissionUtils;
import com.ghzk.ghzk.R;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.model.InvitationRewardModel;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
import com.ghzk.ghzk.utils.MyLogger;
import com.ghzk.ghzk.utils.ZxingUtils;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.core.content.FileProvider;


/**
 * Created by zyz on 2019-10-09.
 * 分享海报
 */
public class SharePosterActivity extends BaseActivity {
    ImageView imageView1, imageView2;
//    TextView textView1, textView2, textView3;

    LinearLayout linearLayout;
    ScrollView scrollView;
    Uri uri = null;
    Handler handler = new Handler();
    private static final int MSG_SUCCESS = 0;// 获取成功的标识
    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {// 此方法在ui线程运行
            switch (msg.what) {
                case MSG_SUCCESS:
                    hideProgress();
//                    showToast(getString(R.string.zxing_h21));
                    share(getString(R.string.share_h27), uri);
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareposter);
        /*mImmersionBar.reset()
                .fitsSystemWindows(true)
//                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
//                .addTag("common")
                .init();*/
    }

    @Override
    protected void initView() {
//        findViewByID_My(R.id.scrollView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        CommonUtil.setMargins(findViewByID_My(R.id.headView), 0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                String string = "?token=" + localUserInfo.getToken();
                Request(string);
            }

            @Override
            public void onLoadmore() {

            }
        });
//        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
//        textView1 = findViewByID_My(R.id.textView1);
//        textView2 = findViewByID_My(R.id.textView2);
//        textView3 = findViewByID_My(R.id.textView3);
        linearLayout = findViewByID_My(R.id.linearLayout);
        scrollView = findViewByID_My(R.id.scrollView);
    }

    @Override
    protected void initData() {
        showProgress(true, getString(R.string.app_loading2));
        Request("?token=" + localUserInfo.getToken());
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.InvitationReward + string, new OkHttpClientManager.ResultCallback<InvitationRewardModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(final InvitationRewardModel response) {
                MyLogger.i(">>>>>>>>>有奖邀请" + response);
                hideProgress();
                //头像
                /*if (!response.getHead().equals(""))
                    Glide.with(SharePosterActivity.this).load(IMGHOST + response.getHead())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片*/
                /*if (!response.getUrl().equals(""))
                    Glide.with(InvitationRewardActivity.this).load(IMGHOST + response.getUrl())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView2);//加载图片*/
                Bitmap mBitmap = ZxingUtils.createQRCodeBitmap(response.getUrl(), 480, 480);
                imageView2.setImageBitmap(mBitmap);

//                textView1.setText(response.getNickname() + "");//昵称
//                textView2.setText(response.getInvite_code() + "");//邀请码
//                textView3.setText(response.getProfit_money() + "");//合约金额
//                scrollView.fullScroll(View.FOCUS_DOWN);//滑动到最底部
            }

        });

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.right_btn:
                //分享
                FanPermissionUtils.with(this)
                        //添加所有你需要申请的权限
                        .addPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)//写入
                        .addPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)//读取
//                .addPermissions(Manifest.permission.ACCESS_COARSE_LOCATION)//定位
//                .addPermissions(Manifest.permission.ACCESS_FINE_LOCATION)//定位
//                .addPermissions(Manifest.permission.CALL_PHONE)//拨打电话
//                .addPermissions(Manifest.permission.READ_PHONE_STATE)//读取手机状态
//                .addPermissions(Manifest.permission.ACCESS_WIFI_STATE)//访问WiFi状态
//                        .addPermissions(Manifest.permission.CAMERA)//相机

                        //添加权限申请回调监听 如果申请失败 会返回已申请成功的权限列表，用户拒绝的权限列表和用户点击了不再提醒的永久拒绝的权限列表
                        .setPermissionsCheckListener(new FanPermissionListener() {
                            @Override
                            public void permissionRequestSuccess() {
                                //所有权限授权成功才会回调这里
                                showProgress(true, getString(R.string.share_h41));
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        printScreen(scrollView, getString(R.string.app_name) + "_share");
                                    }
                                });
                            }

                            @Override
                            public void permissionRequestFail(String[] grantedPermissions, String[] deniedPermissions, String[] forceDeniedPermissions) {
                                //当有权限没有被授权就会回调这里
                                //会返回已申请成功的权限列表（grantedPermissions）
                                //用户拒绝的权限列表（deniedPermissions）
                                //用户点击了不再提醒的永久拒绝的权限列表（forceDeniedPermissions）
                            }
                        })
                        //生成配置
                        .createConfig()
                        //配置是否强制用户授权才可以使用，当设置为true的时候，如果用户拒绝授权，会一直弹出授权框让用户授权
                        .setForceAllPermissionsGranted(false)
                        //配置当用户点击了不再提示的时候，会弹窗指引用户去设置页面授权，这个参数是弹窗里面的提示内容
                        .setForceDeniedPermissionTips("请前往设置->应用->【" + FanPermissionUtils.getAppName(this) + "】->权限中打开相关权限，否则功能无法正常运行！")
                        //构建配置并生效
                        .buildConfig()
                        //开始授权
                        .startCheckPermission();
                break;
        }
    }

    @Override
    protected void updateView() {
//        titleView.setTitle("有奖邀请");
        titleView.setVisibility(View.GONE);
    }

    private void share(String content, Uri uri) {
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
//                    share_intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        share_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (uri != null) {
            //图片
            share_intent.putExtra(Intent.EXTRA_STREAM, uri);
            share_intent.setType("image/*");  //设置分享内容的类型
        } else {
            //文本
            share_intent.setType("text/plain");
            share_intent.putExtra(Intent.EXTRA_TEXT, content);
            //当用户选择短信时使用sms_body取得文字
            share_intent.putExtra("sms_body", content);
        }
        share_intent = Intent.createChooser(share_intent, getString(R.string.share_h27));
        startActivity(share_intent);
    }

    /**
     * 截取图片存到本地
     */
    public File printScreen(ScrollView scrollView, String picName) {
        //图片地址
        //        String imgPath = FileUtil.getImageDownloadDir(MyPosterActivity.this) + picName + ".png";
//        String imgPath = Environment.getExternalStorageDirectory() + "/" + picName + ".png";//文件根目录
        String imgPath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;//相册

        //截取长图
        int h = 0;
        Bitmap bitmap;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        /*//使控件可以进行缓存
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //获取缓存的 Bitmap
        Bitmap bitmap = view.getDrawingCache();*/

        File file = null;
        if (bitmap != null) {
            try {
                file = new File(imgPath, picName + ".png");
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();

                /*//通知相册更新
                MediaStore.Images.Media.insertImage(getContentResolver(), BitmapFactory.decodeFile(f.getAbsolutePath()), f.getName(), null);
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//                Uri uri1 = Uri.fromFile(f);
                Uri uri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", f);
                intent.setData(uri);
                sendBroadcast(intent);*/

                /*//把文件插入到系统图库
                try {
                    MediaStore.Images.Media.insertImage(this.getContentResolver(), f.getAbsolutePath(), f.getName(), null);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/
                // 通知图库更新
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    MediaScannerConnection.scanFile(this, new String[]{file.getAbsolutePath()}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                public void onScanCompleted(String path, Uri uri) {
                                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                                    mediaScanIntent.setData(uri);
                                    sendBroadcast(mediaScanIntent);
                                }
                            });
                } else {
                    String relationDir = file.getParent();
                    File file1 = new File(relationDir);
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.fromFile(file1.getAbsoluteFile())));
                }

                uri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", file);
//                Uri uri = Uri.fromFle(f);
                MyLogger.i(">>>>>>>>" + uri);

                MyLogger.i(">>>>>>" + file);

                mHandler.obtainMessage(MSG_SUCCESS)// 获取信息
                        .sendToTarget(); //发送信息
                return file;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 截取scrollview的屏幕
     **/
    public static Bitmap getScrollViewBitmap(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }
}
