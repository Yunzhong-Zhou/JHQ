package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.model.ChangeServiceNumModel;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.CommonUtil;
import com.cfo.chocoin.utils.MyLogger;
import com.squareup.okhttp.Request;

import java.util.HashMap;

import static com.cfo.chocoin.net.OkHttpClientManager.IMGHOST;

/**
 * Created by zyz on 2019-12-14.
 * 修改服务代码
 */
public class ChangeServiceNumActivity extends BaseActivity {
    LinearLayout headView;
    TextView textView1, textView2, textView3;
    ImageView imageView1;
    EditText editText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeservicenum);

    }

    @Override
    protected void initView() {
        headView = findViewByID_My(R.id.headView);
        headView.setPadding(0, (int) CommonUtil.getStatusBarHeight(ChangeServiceNumActivity.this), 0, 0);
        /*//动态设置linearLayout的高度为屏幕高度的1/3
        ViewGroup.LayoutParams lp = headView.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(ChangeServiceNumActivity.this) / 3;*/
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        imageView1 = findViewByID_My(R.id.imageView1);
        editText1 = findViewByID_My(R.id.editText1);
        editText1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    MyLogger.i(">>>>>>>>>"+editText1.getText().toString().trim());
                    if (!editText1.getText().toString().trim().equals("")){
                        showProgress(false, getString(R.string.app_loading1));
                        HashMap<String, String> params = new HashMap<>();
                        params.put("service_code", editText1.getText().toString().trim());
                        params.put("token", localUserInfo.getToken());
                        RequestSet(params);
                    }else {
                        myToast(getString(R.string.myprofile_h48));
                    }
                }
                return true;
            }
        });
    }
    private void RequestSet(HashMap<String, String> params) {
        OkHttpClientManager.postAsyn(ChangeServiceNumActivity.this, URLs.ServiceCenter_Change, params, new OkHttpClientManager.ResultCallback<String>() {
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
                myToast(getString(R.string.app_submit_true));
                finish();
            }
        }, true);

    }
    @Override
    protected void initData() {
        requestServer();
    }
    @Override
    public void requestServer() {
        super.requestServer();
        showProgress(true, getString(R.string.app_loading2));
        request("?token=" + localUserInfo.getToken());
    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(ChangeServiceNumActivity.this, URLs.ServiceCenter_Change + string, new OkHttpClientManager.ResultCallback<ChangeServiceNumModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(ChangeServiceNumModel response) {
                MyLogger.i(">>>>>>>>>服务中心" + response);
                hideProgress();
                editText1.setText(response.getService_center().getCode());
                /*if (response.getService_center_grade() == 0){
                    //可编辑
                    editText1.setEnabled(true);
                }else {
                    //不可编辑
                    editText1.setEnabled(false);
                }*/

                textView1.setText(response.getService_center().getAddr());//地址
                textView2.setText(response.getService_center().getAddr_detail() + "  "
                        + response.getService_center().getArea() + "㎡");//地址
                textView3.setText(response.getService_center().getCode());//代码

                if (!response.getService_center().getPic1().equals(""))
                    Glide.with(ChangeServiceNumActivity.this)
                            .load(IMGHOST + response.getService_center().getPic1())
                            .centerCrop()
//                            .placeholder(R.mipmap.headimg)//加载站位图
//                            .error(R.mipmap.headimg)//加载失败
                            .into(imageView1);//加载图片
            }
        });
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.left_btn:
                finish();
                break;
        }
    }

    @Override
    protected void updateView() {
//        titleView.setTitle(getString(R.string.myprofile_h16));
        titleView.setVisibility(View.GONE);
    }
}
