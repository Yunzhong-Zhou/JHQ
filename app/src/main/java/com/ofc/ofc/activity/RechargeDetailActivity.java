package com.ofc.ofc.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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

import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.RechargeDetailModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.MyLogger;
import com.ofc.ofc.utils.ZxingUtils;
import com.squareup.okhttp.Request;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zyz on 2019/5/27.
 */
public class RechargeDetailActivity extends BaseActivity {
    RechargeDetailModel detailModel;
    String id = "";
    ProgressBar prograssBar;
    ImageView imageView1, imageView2,imageView_addr,imageView_fuzhi;
    TextView textView, textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8,
            textView9, textView10, textView11, textView12, textView13, textView14, textView15,
            textView16, textView17, textView18,textView_baocun;
    LinearLayout linearLayout_addr, linearLayout_bank, linearLayout_shiji, linearLayout_jiage;

    Handler handler = new Handler();
    private static final int MSG_SUCCESS = 0;// 获取成功的标识
    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {// 此方法在ui线程运行
            switch (msg.what) {
                case MSG_SUCCESS:
                    showToast(getString(R.string.zxing_h21));
//                    textView.setClickable(true);
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
        imageView_addr = findViewByID_My(R.id.imageView_addr);
        imageView_fuzhi = findViewByID_My(R.id.imageView_fuzhi);
        imageView_fuzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", detailModel.getTop_up().getWallet_addr());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                myToast(getString(R.string.recharge_h34));
            }
        });
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
        textView10 = findViewByID_My(R.id.textView10);
        textView11 = findViewByID_My(R.id.textView11);
        textView12 = findViewByID_My(R.id.textView12);
        textView13 = findViewByID_My(R.id.textView13);
        textView14 = findViewByID_My(R.id.textView14);
        textView15 = findViewByID_My(R.id.textView15);
        textView16 = findViewByID_My(R.id.textView16);
        textView17 = findViewByID_My(R.id.textView17);
        textView18 = findViewByID_My(R.id.textView18);
        textView_baocun = findViewByID_My(R.id.textView_baocun);

        linearLayout_addr = findViewByID_My(R.id.linearLayout_addr);
        linearLayout_bank = findViewByID_My(R.id.linearLayout_bank);
        linearLayout_shiji = findViewByID_My(R.id.linearLayout_shiji);
        linearLayout_jiage = findViewByID_My(R.id.linearLayout_jiage);
        textView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消充币
                if (detailModel.getTop_up().getType() == 1) {
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
                } else {
                    showToast(getString(R.string.recharge_h25),
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

            }
        });
        textView_baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printScreen(imageView_addr, "OFC_qrcode" + System.currentTimeMillis());
            }
        });

    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        requestServer();
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
//                textView1.setText(getString(R.string.recharge_h11) + "(" + response.getTop_up().getMoney_type_title() + ")");//充值个数


                if (response.getTop_up().getType() == 1) {
                    //USDT
                    imageView_addr.setVisibility(View.VISIBLE);
                    Bitmap mBitmap = ZxingUtils.createQRCodeBitmap(response.getTop_up().getWallet_addr(),
                            480, 480);
                    imageView_addr.setImageBitmap(mBitmap);

                    linearLayout_addr.setVisibility(View.VISIBLE);//显示充币地址
                    linearLayout_bank.setVisibility(View.GONE);//隐藏银行信息
                    linearLayout_jiage.setVisibility(View.GONE);//隐藏USDT价格
                    linearLayout_shiji.setVisibility(View.GONE);//隐藏实际到账

                    textView2.setText("+" + response.getTop_up().getMoney());//充值个数
                    textView3.setText("" + getString(R.string.recharge_h11));//充币个数（USDT）

                    textView4.setText(getString(R.string.recharge_h13));//充币处理中

                    textView5.setText("" + response.getTop_up().getShow_created_at());//充值处理中时间
                    textView7.setText("" + response.getTop_up().getShow_updated_at());//充值完成时间

                    textView8.setText(response.getTop_up().getWallet_addr());//充币地址

                    textView14.setText(response.getTop_up().getCreated_at());//充值时间
                    textView15.setText(response.getTop_up().getSn());//流水号
                    textView16.setText(response.getTop_up().getStatus_title());//状态

                    textView18.setText(getString(R.string.recharge_h28));//取消充币

                } else {
                    //澳元电汇
                    linearLayout_addr.setVisibility(View.GONE);//隐藏充币地址
                    linearLayout_bank.setVisibility(View.VISIBLE);//显示银行信息
                    linearLayout_jiage.setVisibility(View.VISIBLE);//显示USDT价格
                    linearLayout_shiji.setVisibility(View.VISIBLE);//显示实际到账

                    textView2.setText("+" + response.getTop_up().getInput_money());//充值个数
                    textView3.setText("" + getString(R.string.recharge_h3));//电汇金额（澳元）

                    textView4.setText(getString(R.string.recharge_h4));//电汇处理中

                    textView5.setText("" + response.getTop_up().getShow_created_at());//充值处理中时间
                    textView7.setText("" + response.getTop_up().getShow_updated_at());//充值完成时间

                    textView9.setText("" + response.getAud_wire_transfer().getBank_title());//银行名称
                    textView10.setText("" + response.getAud_wire_transfer().getBank_card_proceeds_name());//收款人姓名
                    textView11.setText("" + response.getAud_wire_transfer().getBank_card_account());//收款人帐号
                    textView12.setText("" + response.getAud_wire_transfer().getBank_swift_code());//银行电汇SWIFT代码
                    textView13.setText("" + response.getAud_wire_transfer().getBank_aba_code());//银行代码ABA#

                    textView.setText(response.getTop_up().getUsdt_price());//USDT价格
                    textView1.setText(response.getTop_up().getMoney() + getString(R.string.recharge_h32));//实际到账
                    textView14.setText(response.getTop_up().getCreated_at());//充值时间
                    textView15.setText(response.getTop_up().getSn());//流水号
                    textView16.setText(response.getTop_up().getStatus_title());//状态

                    textView18.setText(getString(R.string.recharge_h6));//取消电汇
                }

                //进度条
                if (response.getTop_up().getStatus() == 2) {
                    //通过
                    textView7.setVisibility(View.VISIBLE);
                    imageView2.setImageResource(R.mipmap.ic_rechargedetail3);
                    prograssBar.setProgress(100);
                    if (response.getTop_up().getType() == 1) {
                        //USDT
                        textView6.setText(getString(R.string.recharge_h12));
                    } else {
                        textView6.setText(getString(R.string.recharge_h5));
                    }
                    textView6.setTextColor(getResources().getColor(R.color.green));
                    textView17.setVisibility(View.GONE);

                } else if (response.getTop_up().getStatus() == 3) {
                    //未通过
                    textView7.setVisibility(View.VISIBLE);
                    imageView2.setImageResource(R.mipmap.ic_rechargedetail4);
                    prograssBar.setProgress(100);
                    if (response.getTop_up().getType() == 1) {
                        //USDT
                        textView6.setText(getString(R.string.recharge_h26));
                    } else {
                        textView6.setText(getString(R.string.recharge_h24));
                    }
                    textView6.setTextColor(getResources().getColor(R.color.green));
                    textView17.setVisibility(View.VISIBLE);
                    textView17.setText(getString(R.string.recharge_h27) + response.getTop_up().getStatus_rejected_cause());

                } else {
                    //其他状态-审核中
                    textView7.setVisibility(View.GONE);
                    imageView2.setImageResource(R.mipmap.ic_rechargedetail2);
                    prograssBar.setProgress(50);
                    if (response.getTop_up().getType() == 1) {
                        //USDT
                        textView6.setText(getString(R.string.recharge_h12));
                    } else {
                        textView6.setText(getString(R.string.recharge_h5));
                    }
                    textView6.setTextColor(getResources().getColor(R.color.black2));
                    textView17.setVisibility(View.GONE);

                }

                //显示取消充币
                if (response.getTop_up().getStatus() == 1) {
                    //进行中
                    textView18.setVisibility(View.VISIBLE);//取消按钮
                } else {
                    textView18.setVisibility(View.GONE);//取消按钮

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
                mHandler.obtainMessage(MSG_SUCCESS)// 获取信息
                        .sendToTarget(); //发送信息

                MyLogger.i(">>>>>>" + file);
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
