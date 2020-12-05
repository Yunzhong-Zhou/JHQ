package com.fone.fone.activity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chuanglan.cllc.CLLCSDKManager;
import com.chuanglan.cllc.listener.InitStateListener;
import com.fone.fone.R;
import com.fone.fone.adapter.Pop_ListAdapter;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.VerifiedModel1;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyChooseImages;
import com.fone.fone.utils.MyLogger;
import com.fone.fone.view.FixedPopupWindow;
import com.fone.fone.view.face.LivenessActivity;
import com.squareup.okhttp.Request;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fone.fone.net.OkHttpClientManager.HOST;
import static com.fone.fone.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.fone.fone.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;

/**
 * Created by zyz on 2019-12-22.
 * 实名认证
 */
public class VerifiedActivity extends BaseActivity {
    int type = 1;//  1、大陆 2、海外
    VerifiedModel1 model1;
    TextView textView1, textView2, textView3, textView4;
    EditText editText1, editText2;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout_huzhao2;
    RelativeLayout linearLayout_huzhao1;
    int i1 = 0;
    String number = "", name = "", v_type = "";
    public static String bendi_img = "";//Bundle的大小限制为1M

    boolean isgouxuan1 = true, isgouxuan2 = true;
    ImageView imageView1, imageView2, imageView_huzhao;

    //选择图片及上传
    ArrayList<String> listFileNames = new ArrayList<>();
    ArrayList<File> listFiles = new ArrayList<>();
    String img_type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bendi_img = "";
    }

    @Override
    protected void initView() {
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout_huzhao1 = findViewByID_My(R.id.linearLayout_huzhao1);
        linearLayout_huzhao2 = findViewByID_My(R.id.linearLayout_huzhao2);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);

        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);

        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
        imageView_huzhao = findViewByID_My(R.id.imageView_huzhao);

    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 1);
        request1("?token=" + localUserInfo.getToken());

        if (type == 1) {
            //大陆
            linearLayout_huzhao1.setVisibility(View.GONE);
        } else {
            //海外
            linearLayout_huzhao1.setVisibility(View.VISIBLE);
        }

    }

    private void request1(String string) {
        OkHttpClientManager.getAsyn(VerifiedActivity.this, URLs.Verified1 + string,
                new OkHttpClientManager.ResultCallback<VerifiedModel1>() {
                    @Override
                    public void onError(Request request, String info, Exception e) {
                        hideProgress();
                        if (!info.equals("")) {
                            myToast(info);
                        }
                    }

                    @Override
                    public void onResponse(VerifiedModel1 response) {
                        MyLogger.i(">>>>>>>>>认证加载1" + response);
                        hideProgress();
                        model1 = response;
                        if (type == 1) {
                            if (model1.getInland().size() > 0) {
                                v_type = model1.getInland().get(0).getCode();
                                textView1.setText(model1.getInland().get(0).getTitle());
                            }

                        } else {
                            if (model1.getForeign().size() > 0) {
                                v_type = model1.getForeign().get(0).getCode();
                                textView1.setText(model1.getForeign().get(0).getTitle());
                            }
                        }
                    }
                });
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.textView1:
                //证件
                showPopupWindow(textView1);
                break;
            case R.id.textView2:
                //人脸识别
                if (match()) {
                    showProgress(false, getString(R.string.app_loading1));
                    /*String[] filenames = new String[]{};
                    File[] files = new File[]{};
                    for (int i = 0; i < listFiles.size(); i++) {
                        filenames = listFileNames.toArray(new String[i]);
                        files = listFiles.toArray(new File[i]);
                    }*/
                    HashMap<String, String> params = new HashMap<>();
                    params.put("token", localUserInfo.getToken());
                    params.put("type", v_type);
                    params.put("number", number);
                    params.put("name", name);
                    request2(params);
                }
                break;

            case R.id.imageView1:
                //勾选协议
                isgouxuan1 = !isgouxuan1;
                if (isgouxuan1)
                    imageView1.setImageResource(R.mipmap.ic_gouxuan);
                else
                    imageView1.setImageResource(R.mipmap.ic_weigouxuan);
                break;
            case R.id.imageView2:
                //勾选协议
                isgouxuan2 = !isgouxuan2;
                if (isgouxuan2)
                    imageView1.setImageResource(R.mipmap.ic_gouxuan);
                else
                    imageView1.setImageResource(R.mipmap.ic_weigouxuan);
                break;

            case R.id.textView3:
                Bundle bundle = new Bundle();
                bundle.putString("url", HOST + "/wechat/article/detail?id=13a19f182849fa6440b88e4ee0a5e5e8");
                CommonUtil.gotoActivityWithData(VerifiedActivity.this, WebContentActivity.class, bundle, false);
                break;
            case R.id.textView4:
                Bundle bundle1 = new Bundle();
                bundle1.putString("url", HOST + "/wechat/article/detail?id=9f8358e8a49db94a5c7d98f970889a4f");
                CommonUtil.gotoActivityWithData(VerifiedActivity.this, WebContentActivity.class, bundle1, false);
                break;

            case R.id.imageView_huzhao:
            case R.id.linearLayout_huzhao2:
                //选择图片
                img_type = "pic";
                for (int i = 0; i < listFileNames.size(); i++) {
                    if (listFileNames.get(i).equals(type)) {
                        //删除
                        listFileNames.remove(i);
                        listFiles.remove(i);
                    }
                }
                MyChooseImages.showPhotoDialog(this);
                break;

        }
    }

    private boolean match() {
        name = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            myToast(getString(R.string.fragment5_h24));
            return false;
        }
        number = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(number)) {
            myToast(getString(R.string.fragment5_h26));
            return false;
        }

        if (!isgouxuan1) {
            myToast(getString(R.string.fragment5_h32));
            return false;
        }
        if (!isgouxuan2) {
            myToast(getString(R.string.fragment5_h33));
            return false;
        }

        if (type == 2) {
            if (TextUtils.isEmpty(bendi_img)) {
                myToast(getString(R.string.fragment5_h34));
                return false;
            }

        }
        return true;
    }


    private void request2(Map<String, String> params) {
        OkHttpClientManager.postAsyn(VerifiedActivity.this, URLs.Verified2, params,
                new OkHttpClientManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, String info, Exception e) {
                        hideProgress();
                        if (!info.equals("")) {
                            myToast(info);
                        }
                    }

                    @Override
                    public void onResponse(String response) {
                        MyLogger.i(">>>>>>>>>认证加载2" + response);
                        hideProgress();
                        CLLCSDKManager.getInstance().init(getApplicationContext(),
                                "k01UMTdN",//appid
                                "SGKuoaNz",//appkey
                                new InitStateListener() {
                                    @Override
                                    public void getInitStatus(int code, String msg) {
                                        if (code == 1000) {
                                            MyLogger.i(">>>>>>>>>人脸初始化成功");
                                            //可以实名，去人脸识别

                                            Bundle bundle = new Bundle();
                                            bundle.putString("name", name);
                                            bundle.putString("cardNo", number);
                                            bundle.putInt("type", type);
                                            bundle.putString("v_type", v_type);
                                            /*if (type == 2) {
                                                bundle.putString("bendi_img", bendi_img);
                                            }*/
                                            CommonUtil.gotoActivityWithData(VerifiedActivity.this, LivenessActivity.class, bundle, false);
                                        } else {
                                            MyLogger.i(">>>>>>>>>人脸初始化失败：" +
                                                    "\n>>>>>>>>>code:" + code
                                                    + "\n>>>>>>>>>msg:" + msg);
                                        }
                                    }
                                });

                    }
                });

    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.fragment5_h20));
    }

    private void showPopupWindow(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(VerifiedActivity.this).inflate(
                R.layout.pop_list_1, null);
        final FixedPopupWindow popupWindow = new FixedPopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        contentView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = contentView.findViewById(R.id.pop_listView).getTop();
                int height1 = contentView.findViewById(R.id.pop_listView).getBottom();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        popupWindow.dismiss();
                    }
                    if (y > height1) {
                        popupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        // 设置按钮的点击事件
        ListView pop_listView = (ListView) contentView.findViewById(R.id.pop_listView);
        final List<String> list = new ArrayList<String>();
        if (type == 1) {
            //内陆
            for (int i = 0; i < model1.getInland().size(); i++) {
                list.add(model1.getInland().get(i).getTitle());
            }
        } else {
            //海外
            for (int i = 0; i < model1.getForeign().size(); i++) {
                list.add(model1.getForeign().get(i).getTitle());
            }
        }

        final Pop_ListAdapter adapter = new Pop_ListAdapter(VerifiedActivity.this, list);
        adapter.setSelectItem(i1);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                adapter.notifyDataSetChanged();
                i1 = i;

                if (type == 1) {
                    v_type = model1.getInland().get(i).getCode();
                } else {
                    v_type = model1.getForeign().get(i).getCode();
                }
                textView1.setText(list.get(i));
                requestServer();
                popupWindow.dismiss();
            }
        });

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        ColorDrawable dw = new ColorDrawable(this.getResources().getColor(R.color.transparentblack2));
        // 设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);
    }


    /**
     * *****************************************选择图片********************************************
     */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = null;
            String imagePath = null;
            switch (requestCode) {
                case REQUEST_CODE_CAPTURE_CAMEIA:
                    //相机
                    uri = Uri.parse("");
                    uri = Uri.fromFile(new File(MyChooseImages.imagepath));
                    imagePath = uri.getPath();
                    break;
                case REQUEST_CODE_PICK_IMAGE:
                    //相册
                    uri = data.getData();
                    //处理得到的url
                    ContentResolver cr = this.getContentResolver();
                    Cursor cursor = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        cursor = cr.query(uri, null, null, null, null, null);
                    }
                    if (cursor != null) {
                        cursor.moveToFirst();
                        imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    } else {
                        imagePath = uri.getPath();
                    }
                    break;
            }
            if (uri != null) {
                MyLogger.i(">>>>>>>>>>获取到的图片路径1：" + imagePath);
                //图片过大解决方法
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;//按照比例（1 / inSampleSize）缩小bitmap的宽和高、降低分辨率
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
                bendi_img = bitmapToBase64(bitmap);
                MyLogger.i(">>>>>>>" + bendi_img);
                switch (img_type) {
                    case "pic":
                        imageView_huzhao.setImageBitmap(bitmap);
                        imageView_huzhao.setVisibility(View.VISIBLE);
                        linearLayout_huzhao2.setVisibility(View.GONE);
                        break;

                }

               /* //图片压缩及保存
                Uri uri1 = Uri.parse("");
                uri1 = Uri.fromFile(new File(imagePath));
                File file1 = new File(FileUtil.getPath(this, uri1));
//                listFiles.add(file1);
                listFileNames.add(img_type);
                File newFile = null;
                try {
                    newFile = new Compressor(this).compressToFile(file1);
                    listFiles.add(newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    myToast(getString(R.string.app_imgerr));
                }*/
            }
        }

    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


}
