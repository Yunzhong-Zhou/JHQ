package com.fone.fone.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.PayDetailModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mr.Z on 2020/12/14.
 * 支付详情
 */
public class PayDetailActivity extends BaseActivity {
    String id = "",pay_name="",pay_order_id="";
    TextView textView,textView1,textView2,textView3,textView4;
    EditText editText1,editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paydetail);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                String string = "?id=" + id
                        + "&token=" + localUserInfo.getToken();
                Request(string);
            }

            @Override
            public void onLoadmore() {
            }
        });

        //颜色变化
        textView = findViewByID_My(R.id.textView);
//        String text = String.format(getString(R.string.fragment1_h44), getString(R.string.fragment1_h45));
        String text = String.format(getString(R.string.fragment1_h63), getString(R.string.fragment1_h45));
        int index[] = new int[1];
        index[0] = text.indexOf(getString(R.string.fragment1_h45));

        SpannableStringBuilder style=new SpannableStringBuilder(text);
        style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.green)),
                index[0],
                index[0]+getString(R.string.fragment1_h45).length(),
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(style);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);

    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        requestServer();//获取数据
    }
    private void Request(String string) {
        OkHttpClientManager.getAsyn(PayDetailActivity.this, URLs.PayDetail + string, new OkHttpClientManager.ResultCallback<PayDetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(PayDetailModel response) {
                showContentPage();
                hideProgress();
                textView1.setText(response.getBank().getBank_card_proceeds_name());//银行账号
                textView2.setText(response.getBank().getBank_card_account());//银行户名
                textView3.setText(response.getBank().getBank_title());//银行名称
                textView4.setText("¥ "+response.getMoney());//金额
            }
        });

    }
    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        String string = "?id=" + id
                + "&token=" + localUserInfo.getToken();
        Request(string);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.left_btn:
                finish();
                break;
            case R.id.tv_confirm:
                //确定付款
                if (match()){
                    showToast(getString(R.string.fragment1_h68)
                            , getString(R.string.app_confirm), getString(R.string.app_cancel),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    showProgress(true, getString(R.string.app_loading1));
                                    HashMap<String, String> params = new HashMap<>();
                                    params.put("id", id);
                                    params.put("pay_name", pay_name);
                                    params.put("pay_order_id", pay_order_id);
                                    params.put("token", localUserInfo.getToken());
                                    RequestUpData(params);
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();

                                }
                            });
                }
                break;
            case R.id.tv_fuzhi:
                //复制
                if (!textView1.getText().toString().trim().equals("")) {
                    ClipboardManager cm = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
                    // 创建普通字符型ClipData
                    ClipData mClipData = ClipData.newPlainText("Label", textView1.getText().toString().trim());
                    // 将ClipData内容放到系统剪贴板里。
                    cm.setPrimaryClip(mClipData);
                    myToast(getString(R.string.recharge_h34));
                }
                break;
            case R.id.tv_fuzhi2:
                //复制
                if (!textView2.getText().toString().trim().equals("")) {
                    ClipboardManager cm = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
                    // 创建普通字符型ClipData
                    ClipData mClipData = ClipData.newPlainText("Label", textView1.getText().toString().trim());
                    // 将ClipData内容放到系统剪贴板里。
                    cm.setPrimaryClip(mClipData);
                    myToast(getString(R.string.recharge_h34));
                }
                break;
        }
    }
    private boolean match() {
        pay_name = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(pay_name)) {
            myToast(getString(R.string.fragment1_h53));
            return false;
        }
        pay_order_id = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(pay_order_id)) {
            myToast(getString(R.string.fragment1_h55));
            return false;
        }
        return true;
    }
    private void RequestUpData(Map<String, String> params) {
        OkHttpClientManager.postAsyn(PayDetailActivity.this, URLs.PayDetail, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
//                requestServer();
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                myToast(getString(R.string.fragment1_h69));
                finish();
            }
        }, false);
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
