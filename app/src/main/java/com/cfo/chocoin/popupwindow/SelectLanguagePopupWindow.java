package com.cfo.chocoin.popupwindow;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.cfo.chocoin.R;
import com.cfo.chocoin.activity.LoginActivity;
import com.cfo.chocoin.activity.MainActivity;
import com.cfo.chocoin.model.SmsCodeListModel;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.utils.CommonUtil;
import com.cfo.chocoin.utils.LocalUserInfo;
import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by fafukeji01 on 2017/4/1.
 * 选择语言
 */

public class SelectLanguagePopupWindow extends PopupWindow {
    private Context mContext;
    private Class<?> targetActivity;
    private View view;
    RecyclerView recyclerView;
    List<SmsCodeListModel.LangListBean> list = new ArrayList<>();

    public SelectLanguagePopupWindow(Context mContext, Class<?> targetActivity, List<SmsCodeListModel.LangListBean> list) {
        this.view = LayoutInflater.from(mContext).inflate(R.layout.pop_selectlanguage, null);
        this.mContext = mContext;
        this.targetActivity = targetActivity;
        this.list = list;
        initView(view);
        initData();

        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();
                int height1 = view.findViewById(R.id.pop_layout).getBottom();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                    if (y > height1) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_pop_anim);
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLinearLayoutManager);


        /*

        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //中文

            }
        });
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //英文
                //保存语言
                LocalUserInfo.getInstance(mContext).setLanguage_Type("1");
                configuration.locale = new Locale("en","US");
                mContext.getResources().updateConfiguration(configuration,displayMetrics);
//                CommonUtil.gotoActivityWithFinishOtherAll(mContext, HelloActivity.class,true);
                if (LocalUserInfo.getInstance(mContext).getUserId().equals("")) {
                    CommonUtil.gotoActivityWithFinishOtherAll(mContext, LoginActivity.class,true);
                } else {
                    CommonUtil.gotoActivityWithFinishOtherAll(mContext, MainActivity.class,true);
                }
                dismiss();
            }
        });*/
    }

    private void initData() {
        CommonAdapter<SmsCodeListModel.LangListBean> mAdapter = new CommonAdapter<SmsCodeListModel.LangListBean>(
                mContext, R.layout.item_selectlanguage, list) {
            @Override
            protected void convert(ViewHolder holder, SmsCodeListModel.LangListBean model, int position) {
                holder.setText(R.id.textView1, model.getTitle());
//                holder.setText(R.id.textView2, "+" + model.getMobile_state_code());
                ImageView imageView = holder.getView(R.id.imageView);
                if (!model.getIcon().equals(""))
                    Glide.with(mContext)
                            .load(OkHttpClientManager.IMGHOST + model.getIcon())
                            .centerCrop()
//                            .placeholder(R.mipmap.ic_guoqi)//加载站位图
//                            .error(R.mipmap.ic_guoqi)//加载失败
                            .into(imageView);//加载图片
                else
                    imageView.setImageResource(R.mipmap.ic_guoqi);

            }
        };

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Resources resources = mContext.getResources();
                // 获取应用内语言
                final Configuration configuration = resources.getConfiguration();
//        Locale locale=configuration.locale;
                final DisplayMetrics displayMetrics = resources.getDisplayMetrics();

                //保存语言
                LocalUserInfo.getInstance(mContext).setLanguage_Type(list.get(position).getLang_type());
                //保存国家图片
                LocalUserInfo.getInstance(mContext).setCountry_IMG(list.get(position).getIcon());

//                LocalUserInfo.getInstance(mContext).setMobile_State_Code(list.get(position).getMobile_state_code());
                switch (list.get(position).getLang_type()) {
                    case "zh":
                        configuration.locale = new Locale("zh", "CN");
                        break;
                    case "en":
                        configuration.locale = new Locale("en", "US");
                        break;

                }

                mContext.getResources().updateConfiguration(configuration, displayMetrics);
//                CommonUtil.gotoActivityWithFinishOtherAll(mContext, HelloActivity.class,true);

                if (LocalUserInfo.getInstance(mContext).getUserId().equals("")) {
                    CommonUtil.gotoActivityWithFinishOtherAll(mContext, LoginActivity.class, true);
                } else {
                    CommonUtil.gotoActivityWithFinishOtherAll(mContext, MainActivity.class, true);
                }
                dismiss();

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recyclerView.setAdapter(mAdapter);
    }
}
