package com.fone.fone.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cy.dialog.BaseDialog;
import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.utils.CommonUtil;
import com.liaoinstan.springview.widget.SpringView;

/**
 * Created by Mr.Z on 2020/12/14.
 * USDT钱包
 */
public class USDTWalletActivity extends BaseActivity {
//    private RecyclerView recyclerView;
//    List<MyRechargeModel> list = new ArrayList<>();
//    CommonAdapter<MyRechargeModel> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usdtwallet);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void initView() {
//        recyclerView = findViewByID_My(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

    /*private void RequestListMore(String string) {
        OkHttpClientManager.getAsyn(MyMachineActivity.this, URLs.MyRecharge + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
                page--;
            }

            @Override
            public void onResponse(String response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>充值记录列表更多" + response);
                JSONObject jObj;
                List<MyRechargeModel> list1 = new ArrayList<>();
                try {
                    jObj = new JSONObject(response);
                    JSONArray jsonArray = jObj.getJSONArray("data");
                    list1 = JSON.parseArray(jsonArray.toString(), MyRechargeModel.class);
                    if (list1.size() == 0) {
                        myToast(getString(R.string.app_nomore));
                        page--;
                    } else {
                        list.addAll(list1);
                        mAdapter.notifyDataSetChanged();
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
            case R.id.tv_recharge1:
                //充值
                dialog.contentView(R.layout.dialog_recharge)
                        .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT))
                        .animType(BaseDialog.AnimInType.BOTTOM)
                        .canceledOnTouchOutside(true)
                        .gravity(Gravity.BOTTOM)
                        .dimAmount(0.8f)
                        .show();
                EditText et_usdtmoney = dialog.findViewById(R.id.et_usdtmoney);
                TextView tv_confirm = dialog.findViewById(R.id.tv_confirm);
                tv_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("id", "");
                        CommonUtil.gotoActivityWithData(USDTWalletActivity.this, RechargeDetailActivity.class, bundle1, false);
                    }
                });
                break;
            case R.id.tv_takecash:
                //提现
                CommonUtil.gotoActivity(USDTWalletActivity.this, TakeCashActivity.class);
                break;
            case R.id.tv_transfer:
                //划转
                CommonUtil.gotoActivity(USDTWalletActivity.this, TransferActivity.class);
                break;
            case R.id.ll_shouru:
                //收入记录
                CommonUtil.gotoActivity(USDTWalletActivity.this, ShouRuListActivity.class);
                break;
            case R.id.ll_zhichu:
                //支出记录
                CommonUtil.gotoActivity(USDTWalletActivity.this, ZhiChuListActivity.class);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
