package com.fone.fone.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.MachineDetailModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
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
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8,
            textView9, textView10, textView11, textView12,textView13,textView14,textView15,
            tv_zhuanrang,tv_huigou,tv_shouhui, tv_title;
    private RecyclerView recyclerView;
    List<MachineDetailModel.TopUpBean.EarningListBean> list = new ArrayList<>();
    CommonAdapter<MachineDetailModel.TopUpBean.EarningListBean> mAdapter;

    LinearLayout ll_jieidan, ll_chanzhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machinedetail);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        findViewByID_My(R.id.left_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        textView8 = findViewByID_My(R.id.textView8);
        textView9 = findViewByID_My(R.id.textView9);
        textView10 = findViewByID_My(R.id.textView10);
        textView11 = findViewByID_My(R.id.textView11);
        textView12 = findViewByID_My(R.id.textView12);
        textView13 = findViewByID_My(R.id.textView13);
        textView14 = findViewByID_My(R.id.textView14);
        textView15 = findViewByID_My(R.id.textView15);

        tv_title = findViewByID_My(R.id.tv_title);
        tv_zhuanrang = findViewByID_My(R.id.tv_zhuanrang);
        tv_huigou = findViewByID_My(R.id.tv_huigou);
        tv_shouhui = findViewByID_My(R.id.tv_shouhui);

        ll_jieidan = findViewByID_My(R.id.ll_jieidan);
        ll_chanzhi = findViewByID_My(R.id.ll_chanzhi);
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
                if (response.getIs_transfer() ==1){//不能转让
                    tv_zhuanrang.setVisibility(View.GONE);
                }else {
                    tv_zhuanrang.setVisibility(View.VISIBLE);
                }
                if (response.getIs_buy_back() ==1){//不能回购
                    tv_huigou.setVisibility(View.GONE);
                }else {
                    tv_huigou.setVisibility(View.VISIBLE);
                }
                if (response.getIs_take_back()==1){//不能收回
                    tv_shouhui.setVisibility(View.GONE);
                }else {
                    tv_shouhui.setVisibility(View.VISIBLE);
                }

                textView1.setText(response.getTop_up().getEarning_money() + "");//已产收益
                textView2.setText(response.getTop_up().getEarning_ratio() + "%");//收益分成
                textView3.setText(response.getTop_up().getStatus_title() + "");//安装状态
                textView4.setText(response.getTop_up().getGoods_sn()+ "");//设备ID
                textView5.setText(response.getTop_up().getOperation_type_title() + "");//运营方式
                textView6.setText(response.getTop_up().getPut_business() + "");//设备行业
                textView7.setText(response.getTop_up().getGoods_brand() + "");//设备品牌
                textView8.setText(response.getTop_up().getPut_title() + "");//设备门店
                textView9.setText(response.getTop_up().getPut_address() + "");//设备位置
                textView10.setText(response.getTop_up().getCreated_at() + "");//购买时间
                textView11.setText(response.getTop_up().getInstall_at() + "");//安装时间
                textView12.setText(response.getTop_up().getSource_type_title() + "");//回购状态
                textView13.setText(response.getTop_up().getMoney() + "");//购买价格
                textView14.setText(response.getTop_up().getSource_type_title() + "");//转让类型
                textView15.setText(response.getTop_up().getBuy_type_title() + "");//回购状态

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
        titleView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
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
