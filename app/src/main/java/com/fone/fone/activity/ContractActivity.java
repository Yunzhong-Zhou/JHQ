package com.fone.fone.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.utils.CommonUtil;
import com.liaoinstan.springview.widget.SpringView;

/**
 * Created by Mr.Z on 2020/12/15.
 * 合同
 */
public class ContractActivity extends BaseActivity {
    //筛选
    private LinearLayout linearLayout1, linearLayout2,linearLayout3,linearLayout_1, linearLayout_2,linearLayout_3;
    private TextView textView1, textView2,textView3;
    private View view1, view2,view3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);

    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                /*page = 1;
                String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                        + "&money_type=" + money_type
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentList(string);*/
            }

            @Override
            public void onLoadmore() {
                /*page = page + 1;
                //加载更多
                String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                        + "&money_type=" + money_type
                        + "&page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentListMore(string);*/
            }
        });
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout_1 = findViewByID_My(R.id.linearLayout_1);
        linearLayout_2 = findViewByID_My(R.id.linearLayout_2);
        linearLayout_3 = findViewByID_My(R.id.linearLayout_3);

        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);

        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);

    }

    @Override
    protected void initData() {
        requestServer();//获取数据
    }
    /*private void RequestList(String string) {
            OkHttpClientManager.getAsyn(MyMachineActivity.this, URLs.MyRecharge + string, new OkHttpClientManager.ResultCallback<String>() {
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
                    MyLogger.i(">>>>>>>>>充值记录列表" + response);
                    JSONObject jObj;
                    try {
                        jObj = new JSONObject(response);
                        JSONArray jsonArray = jObj.getJSONArray("data");
                        list = JSON.parseArray(jsonArray.toString(), MyRechargeModel.class);
                        if (list.size() == 0) {
                            showEmptyPage();//空数据
                        } else {
                            mAdapter = new CommonAdapter<MyRechargeModel>
                                    (MyRechargeActivity.this, R.layout.item_usdtwallet, list) {
                                @Override
                                protected void convert(ViewHolder holder, MyRechargeModel model, int position) {
                                    if (model.getType() == 1 || model.getType() == 3) {
                                        holder.setText(R.id.textView1, model.getType_title() + "：+" + model.getMoney());//标题
                                    } else {
                                        holder.setText(R.id.textView1, "USDT：+" + model.getMoney() +
                                                "(" + model.getType_title() + "：+" + model.getInput_money() + ")");//标题
                                    }

                                    holder.setText(R.id.textView2, model.getSn());//流水号
                                    holder.setText(R.id.textView3, MyRechargeActivity.this.getString(R.string.recharge_h22) + model.getCreated_at());//时间
                                    holder.setText(R.id.textView4, model.getStatus_title());//状态
                                }
                            };
                            recyclerView.setAdapter(mAdapter);
                            mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                                    Bundle bundle1 = new Bundle();
                                    bundle1.putString("id", list.get(position).getId());
                                    CommonUtil.gotoActivityWithData(MyRechargeActivity.this, RechargeDetailActivity.class, bundle1, false);
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
                    }
                }
            });

        }*/
    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        /*page = 1;
        String string = "?status=" + status//状态（1.待审核 2.通过 3.未通过）
                + "&money_type=" + money_type
                + "&page=" + page//当前页号
                + "&count=" + "10"//页面行数
                + "&token=" + localUserInfo.getToken();
        RequestMyInvestmentList(string);*/
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.left_btn:
                finish();
                break;
            case R.id.linearLayout:
                //开票记录
                CommonUtil.gotoActivity(ContractActivity.this,ContractListActivity.class);
                break;
            case R.id.linearLayout1:
                textView1.setTextColor(getResources().getColor(R.color.green));
                textView2.setTextColor(getResources().getColor(R.color.black1));
                textView3.setTextColor(getResources().getColor(R.color.black1));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                linearLayout_1.setVisibility(View.VISIBLE);
                linearLayout_2.setVisibility(View.GONE);
                linearLayout_3.setVisibility(View.GONE);
                break;
            case R.id.linearLayout2:
                textView1.setTextColor(getResources().getColor(R.color.black1));
                textView2.setTextColor(getResources().getColor(R.color.green));
                textView3.setTextColor(getResources().getColor(R.color.black1));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);
                linearLayout_1.setVisibility(View.GONE);
                linearLayout_2.setVisibility(View.VISIBLE);
                linearLayout_3.setVisibility(View.GONE);
                break;
            case R.id.linearLayout3:
                textView1.setTextColor(getResources().getColor(R.color.black1));
                textView2.setTextColor(getResources().getColor(R.color.black1));
                textView3.setTextColor(getResources().getColor(R.color.green));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);
                linearLayout_1.setVisibility(View.GONE);
                linearLayout_2.setVisibility(View.GONE);
                linearLayout_3.setVisibility(View.VISIBLE);
                break;
        }
    }
    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
