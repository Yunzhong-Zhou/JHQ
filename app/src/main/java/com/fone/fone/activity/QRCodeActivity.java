package com.fone.fone.activity;

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
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.TransferRecordModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.fone.fone.utils.ZxingUtils;
import com.fone.fone.view.zxing.CaptureActivity;
import com.fone.fone.view.zxing.Constant;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.fone.fone.net.OkHttpClientManager.IMGHOST;


/**
 * Created by zyz on 2018/5/31.
 * 生成二维码
 */

public class QRCodeActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<TransferRecordModel> list = new ArrayList<>();
    CommonAdapter<TransferRecordModel> mAdapter;
    //筛选
    private LinearLayout linearLayout1, linearLayout2;
    private TextView textView1, textView2;
    private View view1, view2;

    int page = 1;
    String sort = "desc", type = "";
    int i1 = 0;
    int i2 = 0;


    ImageView imageView1, imageView2;
    TextView tv_name, tv_scan, tv_save;

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
        setContentView(R.layout.activity_qrcode);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        findViewByID_My(R.id.left_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
        tv_name = findViewByID_My(R.id.tv_name);
        tv_scan = findViewByID_My(R.id.tv_scan);
        tv_save = findViewByID_My(R.id.tv_save);

        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(QRCodeActivity.this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        setSpringViewMore(true);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                page = 1;
                String string = "?type=" + type//状态（1.待审核 2.通过 3.未通过）
                        + "&sort=" + sort
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentList(string);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                String string = "?type=" + type//状态（1.待审核 2.通过 3.未通过）
                        + "&sort=" + sort
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentListMore(string);
            }
        });
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
    }

    @Override
    protected void initData() {
//        showProgress(true, getString(R.string.zxing_h7));
        //昵称
        tv_name.setText(localUserInfo.getNickname());
        //头像
        if (!localUserInfo.getUserImage().equals(""))
            Glide.with(QRCodeActivity.this).load(IMGHOST + localUserInfo.getUserImage()).centerCrop().into(imageView1);//加载图片
        else
            imageView1.setImageResource(R.mipmap.headimg);
        //生成二维码
        Bitmap mBitmap = ZxingUtils.createQRCodeBitmap(localUserInfo.getUserId(), 480, 480);
        imageView2.setImageBitmap(mBitmap);

        requestServer();//获取数据
    }
    private void RequestMyInvestmentList(String string) {
        OkHttpClientManager.getAsyn(QRCodeActivity.this, URLs.TransferRecord + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>转币记录列表" + response);
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    JSONArray jsonArray = jObj.getJSONArray("data");
                    list = JSON.parseArray(jsonArray.toString(), TransferRecordModel.class);
                    if (list.size() == 0) {
                        showEmptyPage();//空数据
                    } else {
                        mAdapter = new CommonAdapter<TransferRecordModel>
                                (QRCodeActivity.this, R.layout.item_myrecharge, list) {
                            @Override
                            protected void convert(ViewHolder holder, TransferRecordModel model, int position) {


                                if (model.getType() == 1){
                                    //转出
                                    holder.setText(R.id.textView1, model.getType_title() + "：-" + model.getMoney());//标题
                                }else {
                                    //转入
                                    holder.setText(R.id.textView1, model.getType_title() + "：+" + model.getMoney());//标题
                                }
                                holder.setText(R.id.textView2, model.getShow_created_at());//流水号
                                holder.setText(R.id.textView3, QRCodeActivity.this.getString(R.string.transferrecord_h4) + model.getNickname());//时间
                                holder.setText(R.id.textView4, model.getStatus_title());//状态
                            }
                        };
                        recyclerView.setAdapter(mAdapter);

                        /*mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("id", list.get(position).getId());
                                CommonUtil.gotoActivityWithData(TransferRecordActivity.this, RechargeDetailActivity.class, bundle1, false);
                            }

                            @Override
                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                                return false;
                            }
                        });*/
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });

    }
    private void RequestMyInvestmentListMore(String string) {
        OkHttpClientManager.getAsyn(QRCodeActivity.this, URLs.MyRecharge + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
                page--;
            }

            @Override
            public void onResponse(String response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>转币记录列表更多" + response);
                JSONObject jObj;
                List<TransferRecordModel> list1 = new ArrayList<>();
                try {
                    jObj = new JSONObject(response);
                    JSONArray jsonArray = jObj.getJSONArray("data");
                    list1 = JSON.parseArray(jsonArray.toString(), TransferRecordModel.class);
                    if (list1.size() == 0) {
                        myToast(getString(R.string.app_nomore));
                        page--;
                    } else {
                        list.addAll(list1);
                        mAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_scan:
                //扫一扫
                startQrCode();
                break;
            case R.id.tv_save:
                //保存到相册
                //截取图片存到本地
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        printScreen(imageView2, getString(R.string.app_name)+"_qrcode" + System.currentTimeMillis());
                    }
                });
//                showToast(getString(R.string.zxing_h21));
                break;
            case R.id.linearLayout1:
                textView1.setTextColor(getResources().getColor(R.color.green));
                textView2.setTextColor(getResources().getColor(R.color.black3));
//                textView1.setCompoundDrawables(null, null, drawable1, null);
//                textView2.setCompoundDrawables(null, null, drawable2, null);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
//                showPopupWindow1(pop_view);
                break;
            case R.id.linearLayout2:
                textView1.setTextColor(getResources().getColor(R.color.black3));
                textView2.setTextColor(getResources().getColor(R.color.green));
//                textView1.setCompoundDrawables(null, null, drawable2, null);
//                textView2.setCompoundDrawables(null, null, drawable1, null);
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
//                showPopupWindow2(pop_view);
                break;
        }
    }
    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        String string = "?type=" + type//状态（1.待审核 2.通过 3.未通过）
                + "&sort=" + sort
                + "&page=" + page//当前页号
                + "&count=" + "10"//页面行数
                + "&token=" + localUserInfo.getToken();
        RequestMyInvestmentList(string);
    }
    @Override
    protected void updateView() {
//        titleView.setTitle(getString(R.string.zxing_h25));
        titleView.setVisibility(View.GONE);
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
                    CommonUtil.gotoActivityWithData(QRCodeActivity.this, ScavengingPaymentActivity.class, bundle1, false);
                }
            }

        }
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
