package com.fone.fone.popupwindow;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.fone.fone.MyApplication;
import com.fone.fone.R;
import com.fone.fone.activity.LoginActivity;
import com.fone.fone.activity.MainActivity;
import com.fone.fone.model.SmsCodeListModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.utils.LocalUserInfo;
import com.fone.fone.utils.changelanguage.LanguageType;
import com.fone.fone.utils.changelanguage.LanguageUtil;
import com.fone.fone.utils.changelanguage.SpUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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
        /*if (list.size() ==2){
            list.remove(1);
        }*/
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
//                            .placeholder(R.mipmap.ic_guoqi_english)//加载站位图
//                            .error(R.mipmap.ic_guoqi_english)//加载失败
                            .into(imageView);//加载图片
                else
                    imageView.setImageResource(R.mipmap.ic_guoqi_chinese);

                ImageView imageView2 = holder.getView(R.id.imageView2);
                LinearLayout linearLayout = holder.getView(R.id.linearLayout);
                if (model.getLang_type().equals(LocalUserInfo.getInstance(mContext).getLanguage_Type())){
                    imageView2.setVisibility(View.VISIBLE);
                    linearLayout.setBackgroundResource(R.drawable.yuanjiao_5_baise_lvsebiankuang);
                }else {
                    imageView2.setVisibility(View.GONE);
                    linearLayout.setBackgroundResource(R.drawable.yuanjiao_5_baise);
                }

            }
        };

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Resources resources = mContext.getResources();
                /*// 获取应用内语言
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
                        //中文
                        configuration.locale = new Locale("zh", "CN");
                        break;
                    case "en":
                        //英文
                        configuration.locale = new Locale("en", "US");
                        break;
                    case "ja":
                        //设置为日文
                        configuration.locale = new Locale("ja", "JP");
                        break;
                    case "ko":
                        //设置为韩文
                        configuration.locale = new Locale("ko", "KR");
                        break;
                    case "vi":
                        //设置为越南文
                        configuration.locale = new Locale("vi", "VN");
                        break;
                }

                mContext.getResources().updateConfiguration(configuration, displayMetrics);
//                CommonUtil.gotoActivityWithFinishOtherAll(mContext, HelloActivity.class,true);
                if (LocalUserInfo.getInstance(mContext).getUserId().equals("")) {
                    CommonUtil.gotoActivityWithFinishOtherAll(mContext, LoginActivity.class, true);
                } else {
                    CommonUtil.gotoActivityWithFinishOtherAll(mContext, MainActivity.class, true);
                }*/

                //语言切换
                //保存语言
                LocalUserInfo.getInstance(mContext).setLanguage_Type(list.get(position).getLang_type());
                //保存国家图片
                LocalUserInfo.getInstance(mContext).setCountry_IMG(list.get(position).getIcon());
                String language = null;
                switch (LocalUserInfo.getInstance(mContext).getLanguage_Type()) {
                    case "zh":
                        //设置为中文
                        language = LanguageType.CHINESE.getLanguage();
                        break;
                    case "en":
                        //设置为英文
                        language = LanguageType.ENGLISH.getLanguage();
                        break;
                    case "ja":
                        //设置为日文
                        language = LanguageType.JAPANESE.getLanguage();
                        break;
                    case "ko":
                        //设置为韩文
                        language = LanguageType.KOREAN.getLanguage();
                        break;
                    case "vi":
                        //设置为越南文
                        language = LanguageType.VIETNAMESE.getLanguage();
                        break;
                }
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    LanguageUtil.changeAppLanguage(MyApplication.getContext(), language);
                }
                SpUtil.getInstance(mContext).putString(SpUtil.LANGUAGE, language);
                if (LocalUserInfo.getInstance(mContext).getUserId().equals("")) {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }

                dismiss();

                /*// 杀死该应用进程
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);*/
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recyclerView.setAdapter(mAdapter);
    }
}
