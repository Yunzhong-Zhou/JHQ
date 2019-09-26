package com.cho.chocoin.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cho.chocoin.R;
import com.cho.chocoin.model.MyPosterModel;
import com.cho.chocoin.net.OkHttpClientManager;
import com.cho.chocoin.net.URLs;
import com.cho.chocoin.utils.CommonUtil;
import com.cho.chocoin.utils.LocalUserInfo;
import com.cho.chocoin.utils.MyLogger;
import com.cho.chocoin.utils.ZxingUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.squareup.okhttp.Request;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.cho.chocoin.net.OkHttpClientManager.IMGHOST;


/**
 * Created by zyz on 2018/11/12.
 * 我的海报
 */
public class MyPosterActivity extends Activity {
    private ProgressDialog pd;

    ImageView imageView1, imageView2;
    TextView textView1, textView2;
    LinearLayout linearLayout;
    MyPosterModel model;

    Handler handler = new Handler();
    Uri uri = null;

    ImmersionBar mImmersionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myposter);

        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.reset().init();
        findViewById(R.id.headview).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);

        initView();
        initData();

        findViewById(R.id.linearLayout).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //截取图片存到本地
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        uri = printScreen(linearLayout, "chocionposter");
                    }
                });

                return true;
            }
        });
    }

    protected void initView() {
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        linearLayout = findViewById(R.id.linearLayout);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

       /* //动态设置linearLayout的高度为屏幕高度的2/3
        android.view.ViewGroup.LayoutParams lp = imageView1.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(MyPosterActivity.this) * 2 / 3;*/

    }

    protected void initData() {
        showProgress(true, getString(R.string.app_loading2));
        Request("?token=" + LocalUserInfo.getInstance(MyPosterActivity.this).getToken());
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(MyPosterActivity.this, URLs.MyPoster + string, new OkHttpClientManager.ResultCallback<MyPosterModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    Toast.makeText(MyPosterActivity.this, info, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onResponse(final MyPosterModel response) {
                MyLogger.i(">>>>>>>>>我的海报" + response);
                model = response;
                textView1.setText(response.getNickname());
                textView2.setText(response.getInvite_code());
                if (response != null) {
                    //生成二维码
                    Bitmap mBitmap = ZxingUtils.createQRCodeBitmap(response.getUrl(), 480, 480);
                    imageView2.setImageBitmap(mBitmap);
                }
                if (!response.getHead().equals(""))
                    Glide.with(MyPosterActivity.this).load(IMGHOST + response.getHead())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片
                else
                    imageView1.setImageResource(R.mipmap.headimg);

                /*//截取图片存到本地
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        uri = printScreen(linearLayout, "chocionposter");
                    }
                });*/

                hideProgress();
            }
        });

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.right_btn:
                if (model != null) {
                    Intent share_intent = new Intent();
                    share_intent.setAction(Intent.ACTION_SEND);
//                    share_intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    share_intent.setType("text/plain");
                    share_intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_h28) + "\n" + model.getUrl());

                    share_intent = Intent.createChooser(share_intent, getString(R.string.share_h27));
                    startActivity(share_intent);

                    /*String path = Environment.getExternalStorageDirectory() + File.separator;
                    Uri uri = Uri.fromFile(new File(path + "background.jpg"));
                    shareImg("分享",
                            "【U猜】首个区块链 + 社交去中心化的竞猜平台，注册即送价值70元",
                            HOST + model.getUrl(),
                            uri);*/

                } else {
                    Toast.makeText(MyPosterActivity.this, getString(R.string.share_h26), Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.textView3:
                /** * 分享图片 */
                if (!uri.equals("")) {
                    Intent share_intent = new Intent();
                    share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
//                    share_intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    share_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    share_intent.setType("image/*");  //设置分享内容的类型
                    //图片
                    share_intent.putExtra(Intent.EXTRA_STREAM, uri);
                    //文本
//                share_intent.setType("text/plain");
//                share_intent.putExtra(Intent.EXTRA_TEXT, "这是一段分享的文字");
//                share_intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                    share_intent = Intent.createChooser(share_intent, getString(R.string.share_h27));
                    startActivity(share_intent);
                } else {
                    Toast.makeText(MyPosterActivity.this, getString(R.string.share_h26), Toast.LENGTH_SHORT).show();
                    /*showToast("暂未获取到分享数据，请刷新重试", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            initData();
                        }
                    });*/
                }
                break;
        }
    }

    /**
     * 截取图片存到本地
     */
    public Uri printScreen(View view, String picName) {
        //图片地址
        String imgPath = Environment.getExternalStorageDirectory() + "/" + picName + ".png";
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

                Uri uri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", f);
//                Uri uri = Uri.fromFle(f);
                return uri;

                //压缩
                /*File newFile = null;
                try {
                    newFile = new Compressor(this).compressToFile(f);


                } catch (IOException e) {
                    e.printStackTrace();
                    myToast(getString(R.string.app_imgerr));
                }*/

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void showProgress(boolean flag, String message) {
        if (pd == null) {
            pd = new ProgressDialog(this);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setCancelable(flag);
            pd.setCanceledOnTouchOutside(false);
            pd.setMessage(message);
            pd.show();
        } else {
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setCancelable(flag);
            pd.setCanceledOnTouchOutside(false);
            pd.setMessage(message);
            pd.show();
        }
    }

    public void hideProgress() {
        if (pd == null) {
            return;
        } else {
            if (pd.isShowing()) {
                if (!isFinishing()) {
                    pd.dismiss();
                }
            }
        }
        /*if (dialog != null) {
            if (dialog.isShowing() == true) {
                dialog.dismiss();
            }
        }*/
    }
}
