package com.ghzk.ghzk.activity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghzk.ghzk.R;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
import com.ghzk.ghzk.utils.FileUtil;
import com.ghzk.ghzk.utils.MyChooseImages;
import com.ghzk.ghzk.utils.MyLogger;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.cityjd.JDCityConfig;
import com.lljjcoder.style.cityjd.JDCityPicker;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import id.zelory.compressor.Compressor;

import static com.ghzk.ghzk.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.ghzk.ghzk.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;


/**
 * Created by zyz on 2019-12-19.
 * 申请城市合作伙伴
 */
public class AddCityFriendActivity extends BaseActivity {
    //选择图片及上传
    ArrayList<String> listFileNames = new ArrayList<>();
    ArrayList<File> listFiles = new ArrayList<>();
    String img_type = "";
    TextView textView1, textView2, textView3;
    EditText editText1, editText2,editText3;
    ImageView imageView1, imageView2, imageView3;
    String province = "", city = "", district = "", address = "", area = "",title="";
    //省市
    CityConfig cityConfig = null;
    CityPickerView mPicker = new CityPickerView();

    JDCityPicker cityPicker = new JDCityPicker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcityfriend);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void initView() {
        //预先加载仿iOS滚轮实现的全部数据
        mPicker.init(this);
        cityConfig = new CityConfig.Builder()
                .title(getString(R.string.myprofile_h13))//标题
                .titleTextSize(18)//标题文字大小
                .titleTextColor("#585858")//标题文字颜  色
                .titleBackgroundColor("#eaeaea")//标题栏背景色
                .confirTextColor("#10A589")//确认按钮文字颜色
                .confirmText(getString(R.string.app_confirm))//确认按钮文字
                .confirmTextSize(16)//确认按钮文字大小
                .cancelTextColor("#10A589")//取消按钮文字颜色
                .cancelText(getString(R.string.app_cancel))//取消按钮文字
                .cancelTextSize(16)//取消按钮文字大小
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)//显示类，只显示省份一级，显示省市两级还是显示省市区三级
                .showBackground(false)//是否显示半透明背景
                .visibleItemsCount(7)//显示item的数量
                .province("北京市")//默认显示的省份
                .city("北京市")//默认显示省份下面的城市
                .district("朝阳区")//默认显示省市下面的区县数据
                .provinceCyclic(false)//省份滚轮是否可以循环滚动
                .cityCyclic(false)//城市滚轮是否可以循环滚动
                .districtCyclic(false)//区县滚轮是否循环滚动
                .setCustomItemLayout(R.layout.item_city)//自定义item的布局
                .setCustomItemTextViewId(R.id.textView1)//自定义item布局里面的textViewid
                .drawShadows(false)//滚轮不显示模糊效果
                .setLineColor("#80CDCDCE")//中间横线的颜色
                .setLineHeigh(2)//中间横线的高度
                .setShowGAT(false)//是否显示港澳台数据，默认不显示
                .build();


        //设置自定义的属性配置
        mPicker.setConfig(cityConfig);

        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province1, CityBean city1, DistrictBean district1) {
                //省份province//城市city//地区district
                province = province1.getName().toString();
                city = city1.getName().toString();
                district = district1.getName().toString();

                textView1.setText(province1.getName().toString());
                textView2.setText(city1.getName().toString());
                textView3.setText(district1.getName().toString());
            }

            @Override
            public void onCancel() {
            }
        });


        JDCityConfig jdCityConfig = new JDCityConfig.Builder().build();
        jdCityConfig.setShowType(JDCityConfig.ShowType.PRO_CITY_DIS);
        cityPicker.init(this);
        cityPicker.setConfig(jdCityConfig);
        cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province1, CityBean city1, DistrictBean district1) {
                province = province1.getName().toString();
                city = city1.getName().toString();
                district = district1.getName().toString();

                textView1.setText(province1.getName().toString());
                textView2.setText(city1.getName().toString());
                textView3.setText(district1.getName().toString());
            }

            @Override
            public void onCancel() {
            }
        });
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
        imageView3 = findViewByID_My(R.id.imageView3);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.linearLayout1:
//                mPicker.showCityPicker();
                cityPicker.showCityPicker();
                break;
            case R.id.imageView1:
                img_type = "pic1";
                for (int i = 0; i < listFileNames.size(); i++) {
                    if (listFileNames.get(i).equals(img_type)) {
                        //删除
                        listFileNames.remove(i);
                        listFiles.remove(i);
                    }
                }
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.imageView2:
                img_type = "pic2";
                for (int i = 0; i < listFileNames.size(); i++) {
                    if (listFileNames.get(i).equals(img_type)) {
                        //删除
                        listFileNames.remove(i);
                        listFiles.remove(i);
                    }
                }
                MyChooseImages.showPhotoDialog(this);
                break;
            case R.id.imageView3:
                img_type = "pic3";
                for (int i = 0; i < listFileNames.size(); i++) {
                    if (listFileNames.get(i).equals(img_type)) {
                        //删除
                        listFileNames.remove(i);
                        listFiles.remove(i);
                    }
                }
                MyChooseImages.showPhotoDialog(this);
                break;

            case R.id.tv_confirm:
                if (match()) {
                    showProgress(false, getString(R.string.app_loading1));
                    String[] filenames = new String[]{};
                    File[] files = new File[]{};
                    for (int i = 0; i < listFiles.size(); i++) {
                        filenames = listFileNames.toArray(new String[i]);
                        files = listFiles.toArray(new File[i]);
                    }
                    HashMap<String, String> params = new HashMap<>();
                    params.put("province", province);
                    params.put("city", city);
                    params.put("district", district);
                    params.put("address", address);
                    params.put("area", area);
                    params.put("token", localUserInfo.getToken());
                    params.put("title", title);

                    RequestSet(filenames, files, params);//
                }
                break;
        }
    }

    private void RequestSet(String[] fileKeys, File[] files, HashMap<String, String> params) {
        OkHttpClientManager.postAsyn(AddCityFriendActivity.this, URLs.AddCityFriend, fileKeys, files, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>添加服务中心" + response);
//                myToast(getString(R.string.app_submit_true));
                JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    myToast(jObj.getString("msg"));
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finish();
//                Bundle bundle = new Bundle();
//                bundle.putInt("item", 4);
//                CommonUtil.gotoActivityWithFinishOtherAllAndData(SetServiceCenterActivity.this, MainActivity.class, bundle, true);
//                CommonUtil.gotoActivityWithFinishOtherAll(SetServiceCenterActivity.this, MyProfileActivity.class, true);
            }
        }, false);

    }

    private boolean match() {
        title = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            myToast(getString(R.string.myprofile_h67));
            return false;
        }
        area = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(area)) {
            myToast(getString(R.string.myprofile_h21));
            return false;
        }
        address = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            myToast(getString(R.string.myprofile_h22));
            return false;
        }

        if (TextUtils.isEmpty(province)) {
            myToast(getString(R.string.myprofile_h23));
            return false;
        }
        if (TextUtils.isEmpty(city)) {
            myToast(getString(R.string.myprofile_h23));
            return false;
        }
        if (TextUtils.isEmpty(district)) {
            myToast(getString(R.string.myprofile_h23));
            return false;
        }

        /*if (listFiles.size() != 3) {
            myToast(getString(R.string.myprofile_h47));
            return false;
        }*/
        return true;
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
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

                switch (img_type) {
                    case "pic1":
                        imageView1.setImageBitmap(bitmap);

                        break;
                    case "pic2":
                        imageView2.setImageBitmap(bitmap);

                        break;
                    case "pic3":
                        imageView3.setImageBitmap(bitmap);

                        break;
                }

                //图片压缩及保存
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
                }
            }
        }

    }
}
