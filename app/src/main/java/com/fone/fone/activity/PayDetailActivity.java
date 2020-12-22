package com.fone.fone.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

/**
 * Created by Mr.Z on 2020/12/14.
 * 支付详情
 */
public class PayDetailActivity extends BaseActivity {
    String id = "";
    TextView textView,textView1,textView2,textView3;
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
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);

    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        requestServer();//获取数据
    }
    private void Request(String string) {
        OkHttpClientManager.getAsyn(PayDetailActivity.this, URLs.PayDetail + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(String response) {
                showContentPage();
                hideProgress();
                /*JSONObject jObj;
                try {
                    jObj = new JSONObject(response);
                    JSONArray jsonArray = jObj.getJSONArray("data");
                    list = JSON.parseArray(jsonArray.toString(), ShouRuListModel.class);
                    if (list.size() == 0) {
                        showEmptyPage();//空数据
                    } else {
                        mAdapter = new CommonAdapter<ShouRuListModel>
                                (MachineDetailActivity.this, R.layout.item_shourulist, list) {
                            @Override
                            protected void convert(ViewHolder holder, ShouRuListModel model, int position) {
                                holder.setText(R.id.textView1, "DRVT：-" + model.getMoney());//标题
                                holder.setText(R.id.textView2, model.getCreated_at());//时间
                                holder.setText(R.id.textView3, getString(R.string.qianbao_h79) + ":" + model.getOfc_price() + "usdt");
                            }
                        };
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                                *//*Bundle bundle1 = new Bundle();
                                bundle1.putString("id", list.get(position).getId());
                                CommonUtil.gotoActivityWithData(HuiGouListActivity.this, RechargeDetailActivity.class, bundle1, false);*//*
                            }

                            @Override
                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                                return false;
                            }
                        });
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/
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
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
