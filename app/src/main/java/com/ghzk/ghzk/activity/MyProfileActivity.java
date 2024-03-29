package com.ghzk.ghzk.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.cy.dialog.BaseDialog;
import com.ghzk.ghzk.R;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.model.MyProfileModel;
import com.ghzk.ghzk.model.SmsCodeListModel;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
import com.ghzk.ghzk.utils.MyChooseImages;
import com.ghzk.ghzk.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.zelory.compressor.Compressor;

import static com.ghzk.ghzk.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.ghzk.ghzk.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;


/**
 * Created by fafukeji01 on 2017/5/8.
 * 我的资料
 */

public class MyProfileActivity extends BaseActivity {
    MyProfileModel model;
    List<SmsCodeListModel.LangListBean> list = new ArrayList<>();
    //选择图片及上传
    ArrayList<String> listFileNames;
    ArrayList<File> listFiles;

    ImageView imageView1;
    TextView textView1;
    EditText editText1, editText2, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                requestInfo("?token=" + localUserInfo.getToken());
            }

            @Override
            public void onLoadmore() {

            }
        });

        imageView1 = findViewByID_My(R.id.imageView1);
        textView1 = findViewByID_My(R.id.textView1);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);

       /* editText1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    MyLogger.i(">>>>>>>>>" + editText1.getText().toString().trim());
                    if (!editText1.getText().toString().trim().equals("")) {
                        showProgress(false, getString(R.string.app_loading1));
                        String[] filenames = new String[]{};
                        File[] files = new File[]{};
                        HashMap<String, String> params = new HashMap<>();
                        params.put("token", localUserInfo.getToken());
                        params.put("nickname", editText1.getText().toString().trim());
                        params.put("email", editText2.getText().toString().trim());
                        params.put("service_code", editText3.getText().toString().trim());
                        RequestChangeProfile(filenames, files, params);//修改
                    }
                }
                return true;
            }
        });

        editText2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    MyLogger.i(">>>>>>>>>" + editText2.getText().toString().trim());
                    if (!editText2.getText().toString().trim().equals("")) {
                        showProgress(false, getString(R.string.app_loading1));
                        String[] filenames = new String[]{};
                        File[] files = new File[]{};
                        HashMap<String, String> params = new HashMap<>();
                        params.put("token", localUserInfo.getToken());
                        params.put("nickname", editText1.getText().toString().trim());
                        params.put("email", editText2.getText().toString().trim());
                        params.put("service_code", editText3.getText().toString().trim());
                        RequestChangeProfile(filenames, files, params);//修改
                    }
                }
                return true;
            }
        });
        editText3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    MyLogger.i(">>>>>>>>>" + editText3.getText().toString().trim());
                    if (!editText3.getText().toString().trim().equals("")) {
                        showProgress(false, getString(R.string.app_loading1));
                        String[] filenames = new String[]{};
                        File[] files = new File[]{};
                        HashMap<String, String> params = new HashMap<>();
                        params.put("token", localUserInfo.getToken());
                        params.put("nickname", editText1.getText().toString().trim());
                        params.put("email", editText2.getText().toString().trim());
                        params.put("service_code", editText3.getText().toString().trim());
                        RequestChangeProfile(filenames, files, params);//修改
                    }
                }
                return true;
            }
        });*/


    }

    @Override
    protected void initData() {
        /*String string3 = "?lang_type=" + localUserInfo.getLanguage_Type();
        RequestSmsCodeList(string3);//手机号国家代码集合*/
        textView1.setText("+" + localUserInfo.getMobile_State_Code() + "  " + localUserInfo.getPhonenumber());
        editText1.setText(localUserInfo.getNickname());
//        editText2.setText(localUserInfo.getInvuteCode());
//        editText2.setText(localUserInfo.getEmail());

        Glide.with(MyProfileActivity.this)
                .load(OkHttpClientManager.IMGHOST + localUserInfo.getUserImage())
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new
                        RoundedCorners(CommonUtil.dip2px(this, 10))))
                .placeholder(R.mipmap.loading)//加载站位图
                .error(R.mipmap.headimg)//加载失败
                .into(imageView1);//加载图片

        //获取个人信息
        showProgress(true, getString(R.string.app_loading2));
        requestInfo("?token=" + localUserInfo.getToken());
    }

    private void requestInfo(String string) {
        OkHttpClientManager.getAsyn(MyProfileActivity.this, URLs.ChangeProfile + string, new OkHttpClientManager.ResultCallback<MyProfileModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(MyProfileModel response) {
                MyLogger.i(">>>>>>>>>个人信息" + response);
                model = response;
                //头像
                Glide.with(MyProfileActivity.this)
                        .load(OkHttpClientManager.IMGHOST + response.getHead())
                        .centerCrop()
                        .apply(RequestOptions.bitmapTransform(new
                                RoundedCorners(CommonUtil.dip2px(MyProfileActivity.this, 10))))
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.headimg)//加载失败
                        .into(imageView1);//加载图片

                //手机号
                textView1.setText("+" + localUserInfo.getMobile_State_Code() + "  " + response.getMobile());
                //昵称
                editText1.setText(response.getNickname());
                //邮箱
                editText2.setText(response.getEmail());
                //分公司码
                editText3.setText(response.getService_code());

                localUserInfo.setPhoneNumber(response.getMobile());
                localUserInfo.setNickname(response.getNickname());
//                localUserInfo.setInvuteCode(response.getInvite_code());
                localUserInfo.setEmail(response.getEmail());
                localUserInfo.setUserImage(response.getHead());

                hideProgress();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.textView3:
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", model.getInvite_code());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                myToast(getString(R.string.recharge_h34));
                break;*/
            case R.id.editText1:
                if (model.getNickname_update() == 1) {
                    dialog_edit("修改昵称", getString(R.string.myprofile_h4), editText1);
                }
                break;
            case R.id.editText2:
                dialog_edit("修改邮箱", getString(R.string.myprofile_h6), editText2);
                break;
            case R.id.editText3:
                dialog_edit("修改分公司码", getString(R.string.myprofile_h71), editText3);
                break;
            case R.id.linearLayout1:
                //头像
                MyChooseImages.showPhotoDialog(MyProfileActivity.this);
                break;
            case R.id.tv_confirm:
                //退出登录
                showToast(getString(R.string.myprofile_h11),
                        getString(R.string.app_confirm),
                        getString(R.string.app_cancel), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                localUserInfo.setUserId("");
                                localUserInfo.setToken("");
                                localUserInfo.setPhoneNumber("");
                                localUserInfo.setNickname("");
                                localUserInfo.setInvuteCode("");
                                localUserInfo.setWalletaddr("");
                                localUserInfo.setEmail("");
                                localUserInfo.setUserImage("");

                                ActivityUtils.finishAllActivitiesExceptNewest();//结束除最新之外的所有 Activity
                                CommonUtil.gotoActivity(MyProfileActivity.this, LoginActivity.class, true);
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                break;
        }
    }

    //修改信息
    private void RequestChangeProfile(String[] fileKeys, File[] files, HashMap<String, String> params) {
        OkHttpClientManager.postAsyn(MyProfileActivity.this, URLs.ChangeProfile, fileKeys, files, params, new OkHttpClientManager.ResultCallback<MyProfileModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
                requestInfo("?token=" + localUserInfo.getToken());
            }

            @Override
            public void onResponse(MyProfileModel response) {
                MyLogger.i(">>>>>>>>>修改信息" + response);
                myToast(getString(R.string.myprofile_h7));
                requestInfo("?token=" + localUserInfo.getToken());
            }
        });
    }

    //手机号国家代码集合
    private void RequestSmsCodeList(String string) {
        OkHttpClientManager.getAsyn(MyProfileActivity.this, URLs.Login + string, new OkHttpClientManager.ResultCallback<SmsCodeListModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(SmsCodeListModel response) {
                MyLogger.i(">>>>>>>>>手机号国家代码集合" + response);
                list = response.getLang_list();
//                list1 = response.getMobile_state_list();
//                mobile_state_code = model.getMobile_state_code_list().get(0).getCode() + "";
//                textView.setText(model.getMobile_state_code_list().get(0).getTitle());
            }
        });

    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.myprofile_h1));
    }

    /**
     * *****************************************选择图片********************************************
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        cursor = cr.query(uri, null, null, null, null, null);
                        if (cursor != null) {
                            cursor.moveToFirst();
                            try {
                                imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                            } catch (Exception e) {
                                e.printStackTrace();
//                                myToast(getString(R.string.app_error));
                            } finally {
                                if (cursor != null)
                                    cursor.close();
                            }
                        }

                    } else {
                        imagePath = uri.getPath();
                    }
                    break;
            }
            if (imagePath != null) {
                MyLogger.i(">>>>>>>>>>获取到的图片路径1：" + imagePath);
                //图片过大解决方法
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

                imageView1.setImageBitmap(bitmap);
                imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
                listFileNames = new ArrayList<>();
                listFileNames.add("head");

                /*Uri uri1 = Uri.parse("");
                uri1 = Uri.fromFile(new File(imagePath));
                File file1 = new File(FileUtil.getPath(this, uri1));*/
                File file1 = new File(imagePath);
                listFiles = new ArrayList<>();
                File newFile = null;
                try {
                    newFile = new Compressor(this).compressToFile(file1);
                    listFiles.add(newFile);
                    MyLogger.i(">>>>>选择图片结果>>>>>>>>>" + listFileNames.toString() + ">>>>>>" + listFiles.toString());
                    String[] filenames = new String[]{};
                    File[] files = new File[]{};
                    for (int i = 0; i < listFiles.size(); i++) {
                        filenames = listFileNames.toArray(new String[i]);
                        files = listFiles.toArray(new File[i]);
                    }
                    this.showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("token", localUserInfo.getToken());
                    params.put("nickname", editText1.getText().toString().trim());
                    params.put("email", editText2.getText().toString().trim());
                    params.put("service_code", editText3.getText().toString().trim());
                    RequestChangeProfile(filenames, files, params);//修改
                } catch (IOException e) {
                    e.printStackTrace();
                    myToast(getString(R.string.app_imgerr));
                }

            }
        }

    }

    private void dialog_edit(String txt, String hint, EditText editText) {
        dialog.contentView(R.layout.dialog_edit)
                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))
                .animType(BaseDialog.AnimInType.CENTER)
                .gravity(Gravity.CENTER)
                .canceledOnTouchOutside(true)
                .dimAmount(0.8f)
                .show();
        TextView textView1 = dialog.findViewById(R.id.textView1);
        textView1.setText(txt);
        EditText textView2 = dialog.findViewById(R.id.textView2);
        textView2.setHint(hint);
        TextView textView3 = dialog.findViewById(R.id.textView3);
        TextView textView4 = dialog.findViewById(R.id.textView4);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView2.getText().toString().trim().equals("")) {
                    hideSoftKeyboard(textView1, MyProfileActivity.this);
                    editText.setText(textView2.getText().toString().trim());
                    dialog.dismiss();

                    showProgress(false, getString(R.string.app_loading1));
                    String[] filenames = new String[]{};
                    File[] files = new File[]{};
                    HashMap<String, String> params = new HashMap<>();
                    params.put("token", localUserInfo.getToken());
                    params.put("nickname", editText1.getText().toString().trim());
                    params.put("email", editText2.getText().toString().trim());
                    params.put("service_code", editText3.getText().toString().trim());
                    RequestChangeProfile(filenames, files, params);//修改
                } else {
                    myToast(hint);
                }
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textView1, MyProfileActivity.this);
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textView1, MyProfileActivity.this);
                dialog.dismiss();
            }
        });
    }

    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    public static void hideSoftKeyboard(View view, Activity activity) {
//        View view = activity.getCurrentFocus();
        if (view != null) {
//            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
//            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
        }
    }
}
