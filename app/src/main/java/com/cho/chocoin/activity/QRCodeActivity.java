package com.cho.chocoin.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cho.chocoin.R;
import com.cho.chocoin.base.BaseActivity;
import com.cho.chocoin.utils.CommonUtil;
import com.cho.chocoin.utils.MyLogger;
import com.cho.chocoin.utils.ZxingUtils;
import com.cho.chocoin.view.zxing.CaptureActivity;
import com.cho.chocoin.view.zxing.Constant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.cho.chocoin.net.OkHttpClientManager.IMGHOST;


/**
 * Created by zyz on 2018/5/31.
 * 生成二维码
 */

public class QRCodeActivity extends BaseActivity {
    RelativeLayout relativeLayout;
    ImageView imageView1, imageView2;
    TextView textView1, textView2, textView3, textView4;
    Handler handler = new Handler();
    Uri uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
    }

    @Override
    protected void initView() {
        relativeLayout = findViewByID_My(R.id.relativeLayout);
        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
    }

    @Override
    protected void initData() {
//        showProgress(true, getString(R.string.zxing_h7));
        //昵称
        textView1.setText(localUserInfo.getNickname());
        //头像
        if (!localUserInfo.getUserImage().equals(""))
            Glide.with(QRCodeActivity.this).load(IMGHOST + localUserInfo.getUserImage()).centerCrop().into(imageView1);//加载图片
        else
            imageView1.setImageResource(R.mipmap.headimg);
        //生成二维码
        Bitmap mBitmap = ZxingUtils.createQRCodeBitmap(localUserInfo.getUserId(), 480, 480);
        imageView2.setImageBitmap(mBitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView2:
                //扫一扫
                startQrCode();
                break;
            case R.id.textView3:
                //转币记录
                CommonUtil.gotoActivity(QRCodeActivity.this, TransferRecordActivity.class, false);

                /*//分享给好友
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        uri = printScreen(relativeLayout, "btworldqrcode");
                        *//** * 分享图片 *//*
                        if (!uri.equals("")){
                            Intent share_intent = new Intent();
                            share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
                            share_intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            share_intent.setType("image/*");  //设置分享内容的类型
                            //图片
                            share_intent.putExtra(Intent.EXTRA_STREAM, uri);
                            //文本
//                share_intent.setType("text/plain");
//                share_intent.putExtra(Intent.EXTRA_TEXT, "这是一段分享的文字");
//                share_intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                            startActivity(Intent.createChooser(share_intent, getString(R.string.share_h1)));

                        }else {
                            showToast("暂未获取到分享数据，请刷新重试", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    initData();
                                }
                            });
                        }
                    }
                });*/

                break;
            case R.id.textView4:
                //保存到相册
                //截取图片存到本地
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        printScreen(relativeLayout, "CHO_qrcode" + System.currentTimeMillis());
                    }
                });
                showToast(getString(R.string.zxing_h21));
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.zxing_h5));
    }

    // 开始扫码
    private void startQrCode() {
        if (ContextCompat.checkSelfPermission(QRCodeActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(QRCodeActivity.this, new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
            return;
        }
        // 二维码扫码
        /*Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, Constant.REQ_QR_CODE);*/

        Intent intent1 = new Intent(QRCodeActivity.this, CaptureActivity.class);
        startActivityForResult(intent1, Constant.REQ_QR_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        /*if (requestCode == 10086) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
//                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("number", result);
                    CommonUtil.gotoActivityWithData(getActivity(), EquipmentDetailActivity.class, bundle1, false);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }*/
        //扫描结果回调
        if (requestCode == Constant.REQ_QR_CODE) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                String scanResult = bundle.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN);
                MyLogger.i(">>>扫码返回>>>>" + scanResult);
                if (scanResult != null && !scanResult.equals("")) {
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("id", scanResult);
                    CommonUtil.gotoActivityWithData(QRCodeActivity.this, ScavengingPaymentActivity.class, bundle1, true);
                }
            }

        }
    }

    /**
     * 截取图片存到本地
     */
    public void printScreen(View view, String picName) {
        //图片地址
        //        String imgPath = FileUtil.getImageDownloadDir(MyPosterActivity.this) + picName + ".png";
//        String imgPath = Environment.getExternalStorageDirectory() + "/" + picName + ".png";//文件根目录
        String imgPath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;//相册
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        if (bitmap != null) {
            try {
                File f = new File(imgPath);
                if (!f.exists()) {
                    f.getParentFile().mkdirs();
                    f.createNewFile();
                }
                FileOutputStream out = new FileOutputStream(f);
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
                    MediaScannerConnection.scanFile(this, new String[]{f.getAbsolutePath()}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                public void onScanCompleted(String path, Uri uri) {
                                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                                    mediaScanIntent.setData(uri);
                                    sendBroadcast(mediaScanIntent);
                                }
                            });
                } else {
                    String relationDir = f.getParent();
                    File file1 = new File(relationDir);
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.fromFile(file1.getAbsoluteFile())));
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveImageToGallery(Bitmap bitmap) {
        // 首先保存图片
        File file = null;
        String fileName = System.currentTimeMillis() + ".jpg";
        File root = new File(Environment.getExternalStorageDirectory(), getPackageName());
        File dir = new File(root, "images");
        if (dir.mkdirs() || dir.isDirectory()) {
            file = new File(dir, fileName);
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(this.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
    }

}
