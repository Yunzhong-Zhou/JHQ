package com.ghzk.ghzk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ghzk.ghzk.R;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.model.KeyValueModel;
import com.ghzk.ghzk.model.MachineDetailModel;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
import com.ghzk.ghzk.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mr.Z on 2020/12/14.
 * 矿机详情
 */
public class MachineDetailActivity extends BaseActivity {
    String id = "";
    TextView textView1, textView2, textView3,
            tv_zhuanrang, tv_huigou, tv_shouhui, tv_title, tv_kay, tv_value;

    private RecyclerView recyclerView;
    List<MachineDetailModel.TopUpBean.EarningListBean> list = new ArrayList<>();
    CommonAdapter<MachineDetailModel.TopUpBean.EarningListBean> mAdapter;

    private RecyclerView rv_info;
    List<KeyValueModel> list_info = new ArrayList<>();
    CommonAdapter<KeyValueModel> mAdapter_info;

    StringBuilder sb_key = new StringBuilder();
    StringBuilder sb_value = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machinedetail);
        /*findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        findViewByID_My(R.id.left_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestServer();//获取数据
    }

    @Override
    protected void initView() {
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                String string = "?id=" + id
                        + "&token=" + localUserInfo.getToken();
                RequestDetail(string);
            }

            @Override
            public void onLoadmore() {

            }
        });
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);

        tv_title = findViewByID_My(R.id.tv_title);
        tv_zhuanrang = findViewByID_My(R.id.tv_zhuanrang);
        tv_huigou = findViewByID_My(R.id.tv_huigou);
        tv_shouhui = findViewByID_My(R.id.tv_shouhui);

        rv_info = findViewByID_My(R.id.rv_info);
        rv_info.setLayoutManager(new LinearLayoutManager(this));
        tv_kay = findViewByID_My(R.id.tv_kay);
        tv_value = findViewByID_My(R.id.tv_value);

//        ll_jieidan = findViewByID_My(R.id.ll_jieidan);
//        ll_chanzhi = findViewByID_My(R.id.ll_chanzhi);


    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
    }

    private void RequestDetail(String string) {
        OkHttpClientManager.getAsyn(MachineDetailActivity.this, URLs.MachineDetail + string, new OkHttpClientManager.ResultCallback<MachineDetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(MachineDetailModel response) {
//                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>详情" + response);
                if (response.getIs_transfer() == 1) {//不能转让
                    tv_zhuanrang.setVisibility(View.GONE);
                } else {
                    tv_zhuanrang.setVisibility(View.VISIBLE);
                }
                if (response.getIs_buy_back() == 1) {//不能回购
                    tv_huigou.setVisibility(View.GONE);
                } else {
                    tv_huigou.setVisibility(View.VISIBLE);
                }
                if (response.getIs_take_back() == 1) {//不能收回
                    tv_shouhui.setVisibility(View.GONE);
                } else {
                    tv_shouhui.setVisibility(View.VISIBLE);
                }

                textView1.setText(response.getTop_up().getEarning_money() + "");//已产收益
                textView2.setText(response.getTop_up().getEarning_ratio() + "%");//收益分成
                textView3.setText(response.getTop_up().getStatus_title() + "");//安装状态


                sb_key.setLength(0);
                sb_value.setLength(0);
//                sb_key.clear();
//                sb_value.clear();
                list_info.clear();
                if (!response.getTop_up().getGoods_sn().equals("")) {
                    list_info.add(new KeyValueModel(getString(R.string.fragment1_h83),response.getTop_up().getGoods_sn()));
                    sb_key.append(getString(R.string.fragment1_h83) + "\n");
                    sb_value.append(response.getTop_up().getGoods_sn() + "\n");
                }
                if (!response.getTop_up().getOperation_type_title().equals("")) {
                    list_info.add(new KeyValueModel(getString(R.string.fragment1_h84),response.getTop_up().getOperation_type_title()));

                    sb_key.append(getString(R.string.fragment1_h84) + "\n");
                    sb_value.append(response.getTop_up().getOperation_type_title() + "\n");
                }
                if (!response.getTop_up().getPut_business().equals("")) {
                    list_info.add(new KeyValueModel(getString(R.string.fragment1_h85),response.getTop_up().getPut_business()));

                    sb_key.append(getString(R.string.fragment1_h85) + "\n");
                    sb_value.append(response.getTop_up().getPut_business() + "\n");
                }
                if (!response.getTop_up().getGoods_brand().equals("")) {
                    list_info.add(new KeyValueModel(getString(R.string.fragment1_h86),response.getTop_up().getGoods_brand()));

                    sb_key.append(getString(R.string.fragment1_h86) + "\n");
                    sb_value.append(response.getTop_up().getGoods_brand() + "\n");
                }
                if (!response.getTop_up().getPut_title().equals("")) {
                    list_info.add(new KeyValueModel(getString(R.string.fragment1_h87),response.getTop_up().getPut_title()));

                    sb_key.append(getString(R.string.fragment1_h87) + "\n");
                    sb_value.append(response.getTop_up().getPut_title() + "\n");
                }
                if (!response.getTop_up().getPut_address().equals("")) {
                    list_info.add(new KeyValueModel(getString(R.string.fragment1_h88),response.getTop_up().getPut_address()));

                    sb_key.append(getString(R.string.fragment1_h88) + "\n");
                    sb_value.append(response.getTop_up().getPut_address() + "\n");
                }
                if (!response.getTop_up().getCreated_at().equals("")) {
                    list_info.add(new KeyValueModel(getString(R.string.fragment1_h89),response.getTop_up().getCreated_at()));

                    sb_key.append(getString(R.string.fragment1_h89) + "\n");
                    sb_value.append(response.getTop_up().getCreated_at() + "\n");
                }
                if (response.getTop_up().getInstall_at() != null && !response.getTop_up().getInstall_at().equals("")) {
                    list_info.add(new KeyValueModel(getString(R.string.fragment1_h90),response.getTop_up().getInstall_at()));

                    sb_key.append(getString(R.string.fragment1_h90) + "\n");
                    sb_value.append(response.getTop_up().getInstall_at() + "\n");
                }
                if (!response.getTop_up().getMoney().equals("")) {
                    list_info.add(new KeyValueModel(getString(R.string.fragment1_h92),response.getTop_up().getMoney()));

                    sb_key.append(getString(R.string.fragment1_h92) + "\n");
                    sb_value.append(response.getTop_up().getMoney() + "\n");
                }


                if (response.getTop_up().getBuy_back_apply_verify_at() != null && !response.getTop_up().getBuy_back_apply_verify_at().equals("")) {
                    list_info.add(new KeyValueModel(getString(R.string.fragment1_h105),response.getTop_up().getBuy_back_apply_verify_at()));

                    sb_key.append(getString(R.string.fragment1_h105) + "\n");
                    sb_value.append(response.getTop_up().getBuy_back_apply_verify_at() + "\n");
                }
                if (!response.getTop_up().getBuy_type_title().equals("")) {
                    list_info.add(new KeyValueModel(getString(R.string.fragment1_h94),response.getTop_up().getBuy_type_title()));

                    sb_key.append(getString(R.string.fragment1_h94) + "\n");
                    sb_value.append(response.getTop_up().getBuy_type_title() + "\n");
                }
                if (response.getTop_up().getBuy_money() != null &&!response.getTop_up().getBuy_money().equals("")) {
                    list_info.add(new KeyValueModel(getString(R.string.fragment1_h106),response.getTop_up().getBuy_money()));

                    sb_key.append(getString(R.string.fragment1_h106) + "\n");
                    sb_value.append(response.getTop_up().getBuy_money() + "\n");
                }
                if (response.getTop_up().getTransfer_out_at()!= null && !response.getTop_up().getTransfer_out_at().equals("")) {
                    list_info.add(new KeyValueModel(getString(R.string.fragment1_h107),response.getTop_up().getTransfer_out_at()));

                    sb_key.append(getString(R.string.fragment1_h107) + "\n");
                    sb_value.append(response.getTop_up().getTransfer_out_at() + "\n");
                }
                if (!response.getTop_up().getSource_type_title().equals("")) {
                    list_info.add(new KeyValueModel(getString(R.string.fragment1_h93),response.getTop_up().getSource_type_title()));

                    sb_key.append(getString(R.string.fragment1_h93) + "\n");
                    sb_value.append(response.getTop_up().getSource_type_title() + "\n");
                }
                if (response.getTop_up().getReturn_at() != null && !response.getTop_up().getReturn_at().equals("")) {
                    list_info.add(new KeyValueModel(getString(R.string.fragment1_h108),response.getTop_up().getReturn_at()));

                    sb_key.append(getString(R.string.fragment1_h108) + "\n");
                    sb_value.append(response.getTop_up().getReturn_at() + "\n");
                }

//                tv_kay.setText(sb_key);
//                tv_value.setText(sb_value);
                tv_kay.setText(CommonUtil.setNumColor(sb_key.toString()));
                tv_value.setText(CommonUtil.setNumColor(sb_value.toString()));


                mAdapter_info = new CommonAdapter<KeyValueModel>
                        (MachineDetailActivity.this, R.layout.item_keyvalue, list_info) {
                    @Override
                    protected void convert(ViewHolder holder, KeyValueModel model, int position) {
                        holder.setText(R.id.tv_kay, model.getKey());
                        holder.setText(R.id.tv_value, "" + model.getValue());
                    }
                };
                rv_info.setAdapter(mAdapter_info);


                list = response.getTop_up().getEarning_list();
                if (list.size() > 0) {
                    showContentPage();
                    mAdapter = new CommonAdapter<MachineDetailModel.TopUpBean.EarningListBean>
                            (MachineDetailActivity.this, R.layout.item_machinedetail, list) {
                        @Override
                        protected void convert(ViewHolder holder, MachineDetailModel.TopUpBean.EarningListBean model, int position) {
//                            holder.setText(R.id.textView1, model.getMember_nickname());//昵称
                            holder.setText(R.id.textView2, model.getCreated_at());//时间
                            holder.setText(R.id.textView3, "+" + model.getMoney());//金额
                        }
                    };

                } else {
                    showEmptyPage();
                }
                recyclerView.setAdapter(mAdapter);

            }
        });

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        String string = "?id=" + id
                + "&token=" + localUserInfo.getToken();
        RequestDetail(string);
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.fragment1_h50));
        titleView.showRightTextview(getString(R.string.fragment5_h92), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.gotoActivity(MachineDetailActivity.this, OrderListActivity.class, false);
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_zhuanrang:
                //转让
                Bundle bundle1 = new Bundle();
                bundle1.putString("id", id);
                CommonUtil.gotoActivityWithData(MachineDetailActivity.this, ZhuanRangActivity.class, bundle1, false);
                break;
            case R.id.tv_huigou:
                //回购
                Bundle bundle2 = new Bundle();
                bundle2.putString("id", id);
                CommonUtil.gotoActivityWithData(MachineDetailActivity.this, HuiGouActivity.class, bundle2, false);
                break;
            case R.id.tv_shouhui:
                //回购
                Bundle bundle3 = new Bundle();
                bundle3.putString("id", id);
                CommonUtil.gotoActivityWithData(MachineDetailActivity.this, ShouHuiActivity.class, bundle3, false);
                break;
        }
    }

    private void RequestUpData1(String params) {
        OkHttpClientManager.getAsyn(MachineDetailActivity.this, URLs.HuiGou + params, new OkHttpClientManager.ResultCallback<String>() {
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
                myToast(getString(R.string.fragment1_h78));
                requestServer();
            }
        });
    }

    private void RequestUpData2(String params) {
        OkHttpClientManager.getAsyn(MachineDetailActivity.this, URLs.HuiGou_cancel + params, new OkHttpClientManager.ResultCallback<String>() {
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
                myToast(getString(R.string.fragment1_h79));
                requestServer();
            }
        });
    }




}
