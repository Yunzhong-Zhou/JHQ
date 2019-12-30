package com.ofc.ofc.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.WalletAddressModel;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.LocalUserInfo;

/**
 * Created by zyz on 2019/5/26.
 * 选择地址
 */
public class SelectAddressActivity extends BaseActivity {
    int type = 1;//1、服务中心 2、实名认证
    WalletAddressModel model;

    ImageView imageView1;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectaddress);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();
    }

    @Override
    protected void initView() {
        textView1 = findViewByID_My(R.id.textView1);
        imageView1 = findViewByID_My(R.id.imageView1);
        switch (LocalUserInfo.getInstance(this).getLanguage_Type()) {
            case "zh":
                //设置为中文
                imageView1.setImageResource(R.mipmap.ic_dalu);
                textView1.setText(getString(R.string.address_h15));
                break;
            case "en":
                //设置为英文
                imageView1.setImageResource(R.mipmap.ic_aozhou);
                textView1.setText(getString(R.string.address_h21));
                break;
            case "ja":
                //设置为日文
                imageView1.setImageResource(R.mipmap.ic_riben);
                textView1.setText(getString(R.string.address_h22));
                break;
            case "ko":
                //设置为韩文
                imageView1.setImageResource(R.mipmap.ic_hanguo);
                textView1.setText(getString(R.string.address_h23));
                break;
            case "vi":
                //设置为越南文
                imageView1.setImageResource(R.mipmap.ic_yuenan);
                textView1.setText(getString(R.string.address_h24));
                break;
        }
    }

    @Override
    protected void initData() {
        type= getIntent().getIntExtra("type",1);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.relativeLayout1:
                //大陆
                if (LocalUserInfo.getInstance(this).getLanguage_Type().equals("zh")){
                    bundle.putInt("type", 1);
                }else {
                    bundle.putInt("type", 2);
                }
                if (type ==1){
                    CommonUtil.gotoActivityWithData(SelectAddressActivity.this, SetServiceCenterActivity.class, bundle, true);
                }else {
                    CommonUtil.gotoActivityWithData(SelectAddressActivity.this, VerifiedActivity.class, bundle, true);
                }
                break;
            case R.id.relativeLayout2:
                //海外
                bundle.putInt("type", 2);
                if (type ==1){
                    CommonUtil.gotoActivityWithData(SelectAddressActivity.this, SetServiceCenterActivity.class, bundle, true);
                }else {
                    CommonUtil.gotoActivityWithData(SelectAddressActivity.this, VerifiedActivity.class, bundle, true);
                }
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.address_h14));
    }
}
