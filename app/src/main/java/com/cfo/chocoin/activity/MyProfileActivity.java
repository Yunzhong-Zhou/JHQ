package com.cfo.chocoin.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.model.ChangeProfileModel;
import com.cfo.chocoin.model.MyProfileModel;
import com.cfo.chocoin.model.SmsCodeListModel;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.popupwindow.SelectLanguagePopupWindow;
import com.cfo.chocoin.utils.CommonUtil;
import com.cfo.chocoin.utils.FileUtil;
import com.cfo.chocoin.utils.MyChooseImages;
import com.cfo.chocoin.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.zelory.compressor.Compressor;

import static com.cfo.chocoin.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.cfo.chocoin.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;


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
    TextView textView1, textView2, textView3, textView4, textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

    }

    @Override
    protected void onResume() {
        super.onResume();
        textView1.setText(localUserInfo.getPhonenumber());
        textView2.setText(localUserInfo.getNickname());
        textView3.setText(localUserInfo.getInvuteCode());
//        textView4.setText(localUserInfo.getEmail());

        if (!localUserInfo.getUserImage().equals(""))
            Glide.with(MyProfileActivity.this)
                    .load(OkHttpClientManager.IMGHOST + localUserInfo.getUserImage())
                    .centerCrop()
//                    .placeholder(R.mipmap.headimg)//加载站位图
//                    .error(R.mipmap.headimg)//加载失败
                    .into(imageView1);//加载图片
        else
            imageView1.setImageResource(R.mipmap.headimg);

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
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);

    }

    @Override
    protected void initData() {
        //获取个人信息
        showProgress(true, getString(R.string.app_loading2));
        requestInfo("?token=" + localUserInfo.getToken());
        String string3 = "?lang_type=" + localUserInfo.getLanguage_Type();
        RequestSmsCodeList(string3);//手机号国家代码集合
    }

    private void requestInfo(String string) {
        OkHttpClientManager.getAsyn(MyProfileActivity.this, URLs.Info + string, new OkHttpClientManager.ResultCallback<MyProfileModel>() {
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
                if (!response.getHead().equals(""))
                    Glide.with(MyProfileActivity.this)
                            .load(OkHttpClientManager.IMGHOST + response.getHead())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片
                else
                    imageView1.setImageResource(R.mipmap.headimg);

                //手机号
                textView1.setText(response.getMobile());
                //昵称
                textView2.setText(response.getNickname());
                //邀请码
                textView3.setText(response.getInvite_code());
                //等级
                switch (response.getGrade()){
                    case 1:
                        textView4.setText("LP");
                        break;
                    case 2:
                        textView4.setText("IB");
                        break;
                    case 3:
                        textView4.setText("MIB");
                        break;
                    case 4:
                        textView4.setText("PIB");
                        break;
                }
                //服务码
                textView5.setText(response.getService_code());

                localUserInfo.setPhoneNumber(response.getMobile());
                localUserInfo.setNickname(response.getNickname());
                localUserInfo.setInvuteCode(response.getInvite_code());
//                localUserInfo.setEmail(response.getEmail());
                localUserInfo.setUserImage(response.getHead());

                hideProgress();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout1:
                //头像
                MyChooseImages.showPhotoDialog(MyProfileActivity.this);
                break;

            case R.id.linearLayout5:
                //邮箱
//                CommonUtil.gotoActivity(this, EmailActivity.class, false);
                break;

            case R.id.linearLayout6:
                //ETH地址管理
//                CommonUtil.gotoActivity(this, SelectAddressActivity.class, false);
                CommonUtil.gotoActivity(this, SetAddressActivity.class, false);

                break;
            case R.id.linearLayout10:
                //修改服务码
                CommonUtil.gotoActivity(this, ChangeServiceNumActivity.class, false);
//                CommonUtil.gotoActivity(this, BankCardSettingActivity.class, false);
                break;
            case R.id.linearLayout7:
                //交易密码
                CommonUtil.gotoActivity(this, SetTransactionPasswordActivity.class, false);
                break;
            case R.id.linearLayout8:
                //登录密码
                CommonUtil.gotoActivity(this, ChangePasswordActivity.class, false);
                break;
            case R.id.linearLayout12:
                //选择语言
                if (list.size() > 0) {
                    SelectLanguagePopupWindow popupwindow = new SelectLanguagePopupWindow(MyProfileActivity.this, LoginActivity.class, list);
                    popupwindow.showAtLocation(MyProfileActivity.this.findViewById(R.id.linearLayout12), Gravity.CENTER, 0, 0);
                } else {
                    String string3 = "?lang_type=" + localUserInfo.getLanguage_Type();
                    RequestSmsCodeList(string3);//手机号国家代码集合
                }
                break;
            case R.id.linearLayout11:
                //申请服务中心
                switch (model.getService_center_status()){
                    case 1:
                        //待申请
                        CommonUtil.gotoActivity(this, ServiceCenter_NoActivity.class, false);
                        break;
                    case 2:
                        //审核中
                        myToast(getString(R.string.myprofile_h32));
                        break;
                    case 3:
                        //已通过
                        CommonUtil.gotoActivity(this, ServiceCenter_YesActivity.class, false);
                        break;
                }

                break;
            case R.id.linearLayout9:
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
                                localUserInfo.setWalletaddr("");
                                localUserInfo.setEmail("");
                                localUserInfo.setUserImage("");
                                CommonUtil.gotoActivityWithFinishOtherAll(MyProfileActivity.this, LoginActivity.class, true);
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
        OkHttpClientManager.postAsyn(MyProfileActivity.this, URLs.ChangeProfile, fileKeys, files, params, new OkHttpClientManager.ResultCallback<ChangeProfileModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(ChangeProfileModel response) {
                MyLogger.i(">>>>>>>>>修改信息" + response);
                myToast(getString(R.string.myprofile_h13));
                localUserInfo.setUserImage(response.getHead());
                //头像
                if (!response.getHead().equals(""))
                    Glide.with(MyProfileActivity.this)
                            .load(OkHttpClientManager.IMGHOST + response.getHead())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片
                else
                    imageView1.setImageResource(R.mipmap.headimg);
                //邮箱
//                textView3.setText(response.getEmail());
                hideProgress();
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
                options.inSampleSize = 32;
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

                imageView1.setImageBitmap(bitmap);
                imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
                listFileNames = new ArrayList<>();
                listFileNames.add("head");

                Uri uri1 = Uri.parse("");
                uri1 = Uri.fromFile(new File(imagePath));
                File file1 = new File(FileUtil.getPath(this, uri1));
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
                    params.put("token", localUserInfo.getToken());
//                    params.put("email", "");
                    RequestChangeProfile(filenames, files, params);//修改
                } catch (IOException e) {
                    e.printStackTrace();
                    myToast(getString(R.string.app_imgerr));
                }

            }
        }

    }
}
