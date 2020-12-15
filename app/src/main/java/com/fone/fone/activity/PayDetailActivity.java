package com.fone.fone.activity;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

/**
 * Created by Mr.Z on 2020/12/14.
 * 支付详情
 */
public class PayDetailActivity extends BaseActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paydetail);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        findViewByID_My(R.id.left_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initView() {

        setSpringViewMore(true);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                /*page = 1;
                String string = "?sort_field1=" + sort_field1//状态（1.待审核 2.通过 3.未通过）
                        + "&sort_field2=" + sort_field2
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentList(string);*/
            }

            @Override
            public void onLoadmore() {
                /*page = page + 1;
                //加载更多
                String string = "?sort_field1=" + sort_field1//状态（1.待审核 2.通过 3.未通过）
                        + "&sort_field2=" + sort_field2
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentListMore(string);*/
            }
        });

        textView = findViewByID_My(R.id.textView);

        /*String wholeStr = getResources().getString(R.string.fragment1_h44) + "（";
        String highlightStr = getResources().getString(R.string.fragment1_h45) + "";

        StringFormatUtil spanStr = new StringFormatUtil(PayDetailActivity.this, wholeStr,
                highlightStr, R.color.green).fillColor();
        textView.setText(spanStr.getResult());*/

        String text = String.format(getString(R.string.fragment1_h44), getString(R.string.fragment1_h45));
        int index[] = new int[1];
        index[0] = text.indexOf(getString(R.string.fragment1_h45));

        SpannableStringBuilder style=new SpannableStringBuilder(text);
        style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.green)),
                index[0],
                index[0]+getString(R.string.fragment1_h45).length(),
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(style);

    }

    @Override
    protected void initData() {
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
                MyLogger.i(">>>>>>>>>支出列表" + response);
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
        String string = "?token=" + localUserInfo.getToken();
        Request(string);
    }
    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
