package com.ofc.ofc.activity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.SmsCodeListModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.utils.LocalUserInfo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyz on 2019/6/11.
 */
public class SelectCountryActivity extends BaseActivity {
    RecyclerView recyclerView;
    List<SmsCodeListModel.MobileStateListBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcountry);
    }

    @Override
    protected void initView() {
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(SelectCountryActivity.this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    protected void initData() {
        list = (List<SmsCodeListModel.MobileStateListBean>) getIntent().getSerializableExtra("list");
        CommonAdapter<SmsCodeListModel.MobileStateListBean> mAdapter = new CommonAdapter<SmsCodeListModel.MobileStateListBean>(
                SelectCountryActivity.this, R.layout.item_selectlanguage, list) {
            @Override
            protected void convert(ViewHolder holder, SmsCodeListModel.MobileStateListBean model, int position) {
                holder.setText(R.id.textView1, model.getTitle());
                holder.setText(R.id.textView2, "+" + model.getCode());
                ImageView imageView = holder.getView(R.id.imageView);
                if (!model.getIcon().equals(""))
                    Glide.with(mContext)
                            .load(OkHttpClientManager.IMGHOST + model.getIcon())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView);//加载图片
                else
                    imageView.setImageResource(R.mipmap.headimg);

            }
        };

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                LocalUserInfo.getInstance(SelectCountryActivity.this).setMobile_State_Code(list.get(position).getCode());
                finish();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.login_h16));

    }
}
