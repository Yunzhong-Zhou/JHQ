package com.ofc.ofc.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.utils.ZxingUtils;

/**
 * Created by zyz on 2019-12-27.
 */
public class AddressActivity extends BaseActivity {
    String addr;
    ImageView imageView_addr,imageView_fuzhi;
    TextView textView_addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
    }

    @Override
    protected void initView() {
        imageView_addr = findViewByID_My(R.id.imageView_addr);
        imageView_fuzhi = findViewByID_My(R.id.imageView_fuzhi);
        textView_addr = findViewByID_My(R.id.textView_addr);
    }

    @Override
    protected void initData() {
        addr = getIntent().getStringExtra("addr");
        textView_addr.setText(addr);
        Bitmap mBitmap = ZxingUtils.createQRCodeBitmap(addr,
                480, 480);
        imageView_addr.setImageBitmap(mBitmap);
        imageView_fuzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label",addr);
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                myToast(getString(R.string.recharge_h34));
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle(R.string.address_h17);
    }
}
