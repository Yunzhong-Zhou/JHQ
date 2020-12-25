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
            textView9, textView10, textView11, textView12, tv_confirm,tv_title;
    LinearLayout ll_pay;
    private RecyclerView recyclerView;
    List<MachineDetailModel.InvestBean.InterestListBean> list = new ArrayList<>();
    CommonAdapter<MachineDetailModel.InvestBean.InterestListBean> mAdapter;

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
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        tv_title = findViewByID_My(R.id.tv_title);
        ll_pay = findViewByID_My(R.id.ll_pay);
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
                textView1.setText(response.getInvest().getHashrate() + "TB");//算力大小
                textView2.setText(response.getInvest().getInterest_fil_money() + getString(R.string.app_ge));//已产FIL
                textView3.setText(response.getInvest().getStatus_title() + "");//状态
                textView4.setText(response.getInvest().getMill_computer_position() + "");//机房位置
                textView5.setText(response.getInvest().getMill_number() + "");//矿机编号
                textView6.setText(response.getInvest().getMill_node_number() + "");//节点编号
                textView7.setText(response.getInvest().getMill_mining_cycle() + getString(R.string.app_tian));//挖矿周期
                textView8.setText(response.getInvest().getMill_production_value_fil_money() + getString(R.string.app_ge) + getString(R.string.app_type_fil));//预期产值
                textView9.setText(response.getInvest().getMill_type_title() + "");//算力来源
                textView10.setText(response.getInvest().getStart_at() + "");//购买时间
                textView11.setText(response.getInvest().getEnd_at() + "");//结束时间
                textView12.setText(response.getInvest().getPay_type_title() + "");//支付方式

                if (response.getInvest().getMill_type() == 3) {
                    tv_title.setText(getString(R.string.fragment1_h50));
                    ll_pay.setVisibility(View.VISIBLE);

                    if (response.getInvest().getStatus() == -1) {
                        tv_confirm.setVisibility(View.VISIBLE);
                        tv_confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundle = new Bundle();
                                bundle.putString("id", response.getInvest().getId());
                                CommonUtil.gotoActivityWithData(MachineDetailActivity.this, PayDetailActivity.class, bundle);
                            }
                        });
                    } else {
                        tv_confirm.setVisibility(View.GONE);
                    }

                } else {
                    tv_title.setText(getString(R.string.fragment1_h29));
                    tv_confirm.setVisibility(View.GONE);
                    ll_pay.setVisibility(View.GONE);
                }

                /*if (response.getInvest().getStatus() == -1) {
                    tv_confirm.setVisibility(View.VISIBLE);
                    tv_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putString("id", response.getInvest().getId());
                            CommonUtil.gotoActivityWithData(MachineDetailActivity.this, PayDetailActivity.class, bundle);
                        }
                    });
                } else {
                    tv_confirm.setVisibility(View.GONE);
                }*/

                list = response.getInvest().getInterest_list();
                if (list.size()>0){
                    showContentPage();
                    mAdapter = new CommonAdapter<MachineDetailModel.InvestBean.InterestListBean>
                            (MachineDetailActivity.this, R.layout.item_machinedetail, list) {
                        @Override
                        protected void convert(ViewHolder holder, MachineDetailModel.InvestBean.InterestListBean model, int position) {
//                            holder.setText(R.id.textView1, model.getMember_nickname());//昵称
                            holder.setText(R.id.textView2, model.getCreated_at());//时间
                            holder.setText(R.id.textView3, "+"+model.getFil_money());//时间
                        }
                    };

                }else {
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

}
