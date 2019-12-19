package com.cfo.chocoin.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cfo.chocoin.R;
import com.cfo.chocoin.base.BaseActivity;
import com.cfo.chocoin.model.ServiceCenter_MoreModel;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.cfo.chocoin.net.OkHttpClientManager.IMGHOST;


/**
 * Created by zyz on 2019/1/7.
 * 服务中心列表
 */
public class ServiceCenter_MoreActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<ServiceCenter_MoreModel.ServiceCenterListBean> list = new ArrayList<>();
    CommonAdapter<ServiceCenter_MoreModel.ServiceCenterListBean> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicecenter_more);
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                String string = "?token=" + localUserInfo.getToken();
                Request(string);
            }

            @Override
            public void onLoadmore() {

            }
        });

        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    protected void initData() {
        requestServer();
    }
    private void Request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.ServiceCenter_List + string, new OkHttpClientManager.ResultCallback<ServiceCenter_MoreModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(ServiceCenter_MoreModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>服务中心列表" + response);
                if (response != null) {
                     list = response.getService_center_list();
                   if (list.size()>0){
                       mAdapter = new CommonAdapter<ServiceCenter_MoreModel.ServiceCenterListBean>(
                                ServiceCenter_MoreActivity.this, R.layout.item_servicecenter_more, list) {
                            @Override
                            protected void convert(ViewHolder holder, ServiceCenter_MoreModel.ServiceCenterListBean model, int position) {
                                holder.setText(R.id.textView1, model.getAddr());
                                holder.setText(R.id.textView2, model.getAddr_detail() +"  "
                                        + model.getArea()+"㎡");
                                holder.setText(R.id.textView3, model.getCode());
                                ImageView imageView = holder.getView(R.id.imageView1);
                                if (!model.getPic1().equals(""))
                                    Glide.with(ServiceCenter_MoreActivity.this)
                                            .load(IMGHOST + model.getPic1())
                                            .centerCrop().into(imageView);//加载图片

                            }
                        };
                        recyclerView.setAdapter(mAdapter);
                    }else {
                        showEmptyPage();
                    }

                }


            }
        });
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        String string = "?token=" + localUserInfo.getToken();
        Request(string);
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.myprofile_h39));
    }
}
