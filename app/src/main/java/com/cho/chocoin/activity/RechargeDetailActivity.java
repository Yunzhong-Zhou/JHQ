package com.cho.chocoin.activity;

import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cho.chocoin.R;
import com.cho.chocoin.base.BaseActivity;
import com.cho.chocoin.model.RechargeDetailModel;
import com.cho.chocoin.net.OkHttpClientManager;
import com.cho.chocoin.net.URLs;
import com.cho.chocoin.utils.CommonUtil;
import com.cho.chocoin.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.cho.chocoin.net.OkHttpClientManager.HOST;

/**
 * Created by zyz on 2019/5/27.
 */
public class RechargeDetailActivity extends BaseActivity {
    RechargeDetailModel detailModel;
    String id = "";
    ProgressBar prograssBar;
    ImageView imageView, imageView1, imageView2;
    TextView textView,textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8,
            textView9, textView11, textView12, textView13, textView14, textView16, textView17;
    LinearLayout linearLayout1;

    LinearLayout linearLayout, linearLayout_1, linearLayout_2, linearLayout_3,
            linearLayout_alipay, linearLayout_wechat,linearLayout_ewm,linearLayout_unionpay,
            linearLayout_addr,linearLayout_eth,linearLayout_cho,linearLayout_cny;
    TextView textView3_2, textView3_3, textView3_4,textView_eth,textView_cho,textView_cny;

    File file = null;
    private Thread mThread;
    private static final int MSG_SUCCESS = 0;// 获取成功的标识
    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {// 此方法在ui线程运行
            switch (msg.what) {
                case MSG_SUCCESS:
                    showToast(getString(R.string.zxing_h23));
                    textView.setClickable(true);
                    /*if (file != null) {
                        showToast("二维码已经保存到相册");
                        textView.setClickable(true);

                    } else {
                        showToast("图片保存失败", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                textView.setClickable(true);
                                dialog.dismiss();
                                initData();
                            }
                        });
                    }*/
                    break;

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechargedetail);
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                request("?token=" + localUserInfo.getToken()
                        + "&id=" + id);
            }

            @Override
            public void onLoadmore() {

            }
        });

        prograssBar = findViewByID_My(R.id.prograssBar);
        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);

        textView = findViewByID_My(R.id.textView);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        textView8 = findViewByID_My(R.id.textView8);
        textView9 = findViewByID_My(R.id.textView9);
        textView11 = findViewByID_My(R.id.textView11);
        textView12 = findViewByID_My(R.id.textView12);
        textView13 = findViewByID_My(R.id.textView13);
        textView14 = findViewByID_My(R.id.textView14);
        textView16 = findViewByID_My(R.id.textView16);
        textView17 = findViewByID_My(R.id.textView17);
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        textView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消充币
                showToast(getString(R.string.recharge_h29),
                        getString(R.string.app_yes),
                        getString(R.string.app_no),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //确定
                                dialog.dismiss();
                                showProgress(true, getString(R.string.app_loading1));
                                requestCancel("?token=" + localUserInfo.getToken()
                                        + "&id=" + id);

                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //取消
                                dialog.dismiss();
                            }
                        });
            }
        });

        linearLayout = findViewByID_My(R.id.linearLayout);
        linearLayout_1 = findViewByID_My(R.id.linearLayout_1);
        linearLayout_2 = findViewByID_My(R.id.linearLayout_2);
        linearLayout_3 = findViewByID_My(R.id.linearLayout_3);
        imageView = findViewByID_My(R.id.imageView);
        textView3_2 = findViewByID_My(R.id.textView3_2);
        textView3_3 = findViewByID_My(R.id.textView3_3);
        textView3_4 = findViewByID_My(R.id.textView3_4);

        linearLayout_alipay = findViewByID_My(R.id.linearLayout_alipay);
        linearLayout_wechat = findViewByID_My(R.id.linearLayout_wechat);
        linearLayout_ewm = findViewByID_My(R.id.linearLayout_ewm);
        linearLayout_unionpay = findViewByID_My(R.id.linearLayout_unionpay);
        linearLayout_addr = findViewByID_My(R.id.linearLayout_addr);
        linearLayout_alipay.setOnClickListener(this);
        linearLayout_wechat.setOnClickListener(this);
        linearLayout_ewm.setOnClickListener(this);
        linearLayout_unionpay.setOnClickListener(this);


        linearLayout_eth = findViewByID_My(R.id.linearLayout_eth);
        linearLayout_cho = findViewByID_My(R.id.linearLayout_cho);
        linearLayout_cny = findViewByID_My(R.id.linearLayout_cny);
        textView_eth = findViewByID_My(R.id.textView_eth);
        textView_cho = findViewByID_My(R.id.textView_cho);
        textView_cny = findViewByID_My(R.id.textView_cny);

    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        requestServer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout_alipay:
                //支付宝
                toPay(detailModel.getAlipay());
                break;
            case R.id.linearLayout_wechat:
                //微信
                toPay(detailModel.getWechat());
                break;
            case R.id.linearLayout_ewm:
                //扫码
                toPay(detailModel.getEwm());
                break;
            case R.id.linearLayout_unionpay:
                //银联
                toPay(detailModel.getUnionpay());
                break;
            case R.id.textView:
                //保存二维码
                textView.setClickable(false);

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        //这是新的线程，可在这儿处理耗时的操作，更新不了UI界面的操作的
                        file = printScreen(linearLayout_2, "ScanQRCode" + System.currentTimeMillis());
                    }
                };

                if (mThread == null) {
                    mThread = new Thread(runnable);
                    mThread.start();// 线程启动
                }
                break;
        }
    }

    private void toPay(String pay_type) {
        //请求支付网页
        Bundle bundle = new Bundle();
        bundle.putString("url", HOST + "/pay?top_up_id=" + detailModel.getTop_up().getId() + "&pay_type=" + pay_type);
        CommonUtil.gotoActivityWithData(RechargeDetailActivity.this, WebContentActivity.class, bundle, false);
    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(RechargeDetailActivity.this, URLs.RechargeDetail + string, new OkHttpClientManager.ResultCallback<RechargeDetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(RechargeDetailModel response) {
                MyLogger.i(">>>>>>>>>充值详情" + response);
                hideProgress();
                detailModel = response;
                textView1.setText(getString(R.string.recharge_h11) + "(" + response.getTop_up().getMoney_type_title() + ")");//充值个数
                textView2.setText("+" + response.getTop_up().getInput_money());//充值个数
                textView3.setText("" + response.getTop_up().getStatus_title());//充值状态

                textView5.setText("" + response.getTop_up().getShow_created_at());//充值处理中时间
                textView7.setText("" + response.getTop_up().getShow_updated_at());//充值完成时间
                textView8.setText(response.getTop_up().getWallet_addr());//充币地址
                textView9.setText(response.getTop_up().getTxid());//txid

                textView_eth.setText("$ " + response.getTop_up().getEth_price());
                textView_cho.setText("$ " + response.getTop_up().getCho_price());
                textView_cny.setText("¥ " + response.getTop_up().getCny_price());

                textView11.setText(response.getTop_up().getMoney() + getString(R.string.recharge_h23));//实际到账
                textView12.setText(response.getTop_up().getCreated_at());//充值时间
                textView13.setText(response.getTop_up().getSn());//流水号
                textView14.setText(response.getTop_up().getStatus_title());//状态

                if (response.getTop_up().getMoney_type() == 1) {//eth
                    linearLayout_eth.setVisibility(View.VISIBLE);
                    linearLayout_cho.setVisibility(View.VISIBLE);
                    linearLayout_cny.setVisibility(View.GONE);

                    linearLayout1.setVisibility(View.VISIBLE);//实际到账
                    linearLayout.setVisibility(View.GONE);//支付方式布局
                    linearLayout_addr.setVisibility(View.VISIBLE);//充币地址
                } else if (response.getTop_up().getMoney_type() == 2) {//cho
                    linearLayout_eth.setVisibility(View.GONE);
                    linearLayout_cho.setVisibility(View.VISIBLE);
                    linearLayout_cny.setVisibility(View.GONE);

                    linearLayout1.setVisibility(View.GONE);//实际到账
                    linearLayout.setVisibility(View.GONE);//支付方式布局
                    linearLayout_addr.setVisibility(View.VISIBLE);//充币地址

                } else if (response.getTop_up().getMoney_type() == 3) {//cny
                    textView1.setText(getString(R.string.recharge_h31) + "(" + response.getTop_up().getMoney_type_title() + ")");//充值个数

                    linearLayout_eth.setVisibility(View.GONE);
                    linearLayout_cho.setVisibility(View.GONE);
                    linearLayout_cny.setVisibility(View.VISIBLE);

                    linearLayout1.setVisibility(View.VISIBLE);//实际到账
                    linearLayout.setVisibility(View.VISIBLE);//支付方式布局
                    linearLayout_addr.setVisibility(View.GONE);//充币地址

                }

                //进度条
                if (response.getTop_up().getStatus() == 2) {
                    //通过
                    textView7.setVisibility(View.VISIBLE);
                    imageView2.setImageResource(R.mipmap.ic_rechargedetail3);
                    prograssBar.setProgress(100);
                    textView6.setText(getString(R.string.recharge_h12));
                    textView6.setTextColor(getResources().getColor(R.color.purple));
                    textView16.setVisibility(View.GONE);

                } else if (response.getTop_up().getStatus() == 3) {
                    //未通过
                    textView7.setVisibility(View.VISIBLE);
                    imageView2.setImageResource(R.mipmap.ic_rechargedetail4);
                    prograssBar.setProgress(100);
                    textView6.setText(getString(R.string.recharge_h26));
                    textView6.setTextColor(getResources().getColor(R.color.purple));
                    textView16.setVisibility(View.VISIBLE);
                    textView16.setText(getString(R.string.recharge_h27) + response.getTop_up().getStatus_rejected_cause());

                } else {
                    //其他状态-审核中
                    textView7.setVisibility(View.GONE);
                    imageView2.setImageResource(R.mipmap.ic_rechargedetail2);
                    prograssBar.setProgress(50);
                    textView6.setText(getString(R.string.recharge_h12));
                    textView6.setTextColor(getResources().getColor(R.color.black2));
                    textView16.setVisibility(View.GONE);

                }

                //显示取消充币-支付方式
                if (response.getTop_up().getStatus() == 1) {
                    //进行中
                    textView17.setVisibility(View.VISIBLE);//取消按钮
                    switch (response.getTop_up().getPay_detail_type()) {
                        case 1:
                        case 2:
                            //显示支付布局
                            linearLayout_1.setVisibility(View.VISIBLE);
                            linearLayout_2.setVisibility(View.GONE);
                            linearLayout_3.setVisibility(View.GONE);
                            if (!response.getAlipay().equals("")) {
                                linearLayout_alipay.setVisibility(View.VISIBLE);
                            } else {
                                linearLayout_alipay.setVisibility(View.GONE);
                            }
                            if (!response.getWechat().equals("")) {
                                linearLayout_wechat.setVisibility(View.VISIBLE);
                            } else {
                                linearLayout_wechat.setVisibility(View.GONE);
                            }
                            if (!response.getEwm().equals("")) {
                                linearLayout_ewm.setVisibility(View.VISIBLE);
                            } else {
                                linearLayout_ewm.setVisibility(View.GONE);
                            }
                            if (!response.getUnionpay().equals("")) {
                                linearLayout_unionpay.setVisibility(View.VISIBLE);
                            } else {
                                linearLayout_unionpay.setVisibility(View.GONE);
                            }
                            break;
                        case 3:
                            //显示银联支付
                            showToast(getString(R.string.zxing_h29));
                            linearLayout_1.setVisibility(View.GONE);
                            linearLayout_2.setVisibility(View.GONE);
                            linearLayout_3.setVisibility(View.VISIBLE);
                            textView3_2.setText(response.getBank_title());//银行
                            textView3_3.setText(response.getBank_card_account());//卡号
                            textView3_4.setText(response.getBank_card_proceeds_name());//账户
                            break;
                        case 4:
                            //显示二维码布局
                            showToast(getString(R.string.zxing_h29));
                            linearLayout_1.setVisibility(View.GONE);
                            linearLayout_2.setVisibility(View.VISIBLE);
                            linearLayout_3.setVisibility(View.GONE);
                        /*//生成二维码
                        Bitmap mBitmap = ZxingUtils.createQRCodeBitmap(response.getEwm_qrcode(), 480, 480);
                        imageView.setImageBitmap(mBitmap);*/
                            if (!response.getEwm_qrcode().equals(""))
                                Glide.with(RechargeDetailActivity.this).load(OkHttpClientManager.IMGHOST + response.getEwm_qrcode())
                                        .centerCrop().into(imageView);//加载图片
                            else
                                imageView.setImageResource(R.mipmap.headimg);
                            break;
                    }

                } else {
                    textView17.setVisibility(View.GONE);//取消按钮
                    linearLayout_1.setVisibility(View.GONE);
                    linearLayout_2.setVisibility(View.GONE);
                    linearLayout_3.setVisibility(View.GONE);
                }

            }
        });
    }

    private void requestCancel(String string) {
        OkHttpClientManager.getAsyn(RechargeDetailActivity.this, URLs.RechargeDetail_Cancel + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                MyLogger.i(">>>>>>>>>充值详情-取消" + response);
                hideProgress();
                myToast(getString(R.string.recharge_h30));
                finish();
            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
        showProgress(true, getString(R.string.app_loading2));
        request("?token=" + localUserInfo.getToken()
                + "&id=" + id);
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.recharge_h10));
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
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        File file = null;
        if (bitmap != null) {
            try {
                file = new File(imgPath, picName + ".png");
//                file = new File(imgPath);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();

                /*Uri uri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", file);
                //通知相册更新
                MediaStore.Images.Media.insertImage(ScanCodePaymentActivity.this.getContentResolver(), bitmap, file.toString(), null);
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri1 = Uri.fromFile(file);
                intent.setData(uri1);
                ScanCodePaymentActivity.this.sendBroadcast(intent);*/


                /*//把文件插入到系统图库(内部存储/Pictures)
                try {
                    MediaStore.Images.Media.insertImage(this.getContentResolver(), file.getAbsolutePath(), file.getName(), null);
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

                mHandler.obtainMessage(MSG_SUCCESS)// 获取信息
                        .sendToTarget(); //发送信息

                MyLogger.i(">>>>>>"+file);
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
