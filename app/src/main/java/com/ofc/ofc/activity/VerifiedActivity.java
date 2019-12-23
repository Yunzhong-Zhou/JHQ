package com.ofc.ofc.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ofc.ofc.R;
import com.ofc.ofc.adapter.Pop_ListAdapter;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.VerifiedModel1;
import com.ofc.ofc.model.VerifiedModel2;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.MyLogger;
import com.ofc.ofc.view.FixedPopupWindow;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyz on 2019-12-22.
 * 实名认证
 */
public class VerifiedActivity extends BaseActivity {
    int type = 1;
    VerifiedModel1 model1;
    TextView textView1, textView2, textView3, textView4;
    EditText editText1, editText2;
    LinearLayout linearLayout1, linearLayout2, linearLayout3;
    int i1 = 0;
    String number = "", name = "", code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified);
    }

    @Override
    protected void initView() {
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);

        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 1);
        request1("?token=" + localUserInfo.getToken());
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
                                code = model1.getInland().get(0).getCode();
                                textView1.setText(model1.getInland().get(0).getTitle());
                            }

                        } else {
                            if (model1.getForeign().size() > 0) {
                                code = model1.getForeign().get(0).getCode();
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
                    HashMap<String, String> params = new HashMap<>();
                    params.put("token", localUserInfo.getToken());
                    params.put("type", code);
                    params.put("number", number);
                    params.put("name", name);
                    request2(params);//登录
                }

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
        return true;
    }

    private void request2(Map<String, String> params) {
        OkHttpClientManager.postAsyn(VerifiedActivity.this, URLs.Verified2, params,
                new OkHttpClientManager.ResultCallback<VerifiedModel2>() {
                    @Override
                    public void onError(Request request, String info, Exception e) {
                        hideProgress();
                        if (!info.equals("")) {
                            myToast(info);
                        }
                    }

                    @Override
                    public void onResponse(VerifiedModel2 response) {
                        MyLogger.i(">>>>>>>>>认证加载2" + response);
                        hideProgress();
                        //可以实名，去人脸识别

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
                    code = model1.getInland().get(i).getCode();
                } else {
                    code = model1.getForeign().get(i).getCode();
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
}
