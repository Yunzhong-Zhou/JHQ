package com.ofc.ofc.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.InvitationRewardModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.ofc.ofc.utils.ZxingUtils;
import com.squareup.okhttp.Request;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.ofc.ofc.net.OkHttpClientManager.IMGHOST;


/**
 * Created by zyz on 2019-10-09.
 * 有奖邀请
 */
public class InvitationRewardActivity extends BaseActivity {
    ImageView imageView1, imageView2;
    TextView textView1, textView2, textView3;

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
        setContentView(R.layout.activity_invitationreward);
    }

    @Override
    protected void initView() {
//        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
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
        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
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
                if (!response.getHead().equals(""))
                    Glide.with(InvitationRewardActivity.this).load(IMGHOST + response.getHead())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片
                /*if (!response.getUrl().equals(""))
                    Glide.with(InvitationRewardActivity.this).load(IMGHOST + response.getUrl())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView2);//加载图片*/
                Bitmap mBitmap = ZxingUtils.createQRCodeBitmap(response.getUrl(), 480, 480);
                imageView2.setImageBitmap(mBitmap);

                textView1.setText(response.getNickname() + "");//昵称
                textView2.setText(response.getInvite_code() + "");//邀请码
                textView3.setText(response.getProfit_money() + "");//合约金额

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
                showProgress(true, getString(R.string.share_h41));
                printScreen(scrollView, "OFC_share" + System.currentTimeMillis());
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
    public File printScreen(View view, String picName) {
        //图片地址
        //        String imgPath = FileUtil.getImageDownloadDir(MyPosterActivity.this) + picName + ".png";
//        String imgPath = Environment.getExternalStorageDirectory() + "/" + picName + ".png";//文件根目录
        String imgPath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;//相册

        //使控件可以进行缓存
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //获取缓存的 Bitmap
        Bitmap bitmap = view.getDrawingCache();

        File file = null;
        if (bitmap != null) {
            try {
                file = new File(imgPath, picName + ".png");
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, out);
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
}
