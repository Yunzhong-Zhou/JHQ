package com.ofc.ofc.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.DRVTDirectMemberModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.MyLogger;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.ofc.ofc.net.OkHttpClientManager.IMGHOST;


/**
 * Created by zyz on 2019/1/7.
 * 直推会员-OFC
 */
public class DRVTSharePeopleActivity extends BaseActivity {
    private RecyclerView recyclerView;
    CommonAdapter<DRVTDirectMemberModel.DirectRecommendListBean> mAdapter;
    List<DRVTDirectMemberModel.DirectRecommendListBean> list = new ArrayList<>();

    int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drvtsharepeople);
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                String string = "?token=" + localUserInfo.getToken()
                        + "&page=" + page//当前页号
                        + "&count=" + "10";//页面行数;
                Request(string);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                String string = "?token=" + localUserInfo.getToken()
                        + "&page=" + page//当前页号
                        + "&count=" + "10";//页面行数
                RequestMore(string);
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
        OkHttpClientManager.getAsyn(this, URLs.DirectMember_DRVT + string, new OkHttpClientManager.ResultCallback<DRVTDirectMemberModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(DRVTDirectMemberModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>直推会员" + response);
                if (response != null) {
                    list = response.getDirect_recommend_list();
                    if (list.size()>0){
                        mAdapter = new CommonAdapter<DRVTDirectMemberModel.DirectRecommendListBean>(
                                DRVTSharePeopleActivity.this, R.layout.item_directmember_drvt, list) {
                            @Override
                            protected void convert(ViewHolder holder, DRVTDirectMemberModel.DirectRecommendListBean model, int position) {
                                holder.setText(R.id.textView1, model.getNickname());
                                holder.setText(R.id.textView2, model.getDrvt_valid_buy_money()+"");
                                holder.setText(R.id.textView3, model.getGrade_title());
                                ImageView imageView = holder.getView(R.id.imageView1);
                                if (!model.getHead().equals(""))
                                    Glide.with(DRVTSharePeopleActivity.this).load(IMGHOST + model.getHead()).centerCrop().into(imageView);//加载图片
                                else
                                    imageView.setImageResource(R.mipmap.headimg);

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
    private void RequestMore(String string) {
        OkHttpClientManager.getAsyn(this, URLs.DirectMember_DRVT + string, new OkHttpClientManager.ResultCallback<DRVTDirectMemberModel>() {
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
            public void onResponse(DRVTDirectMemberModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>DRVT列表更多" + response);
                List<DRVTDirectMemberModel.DirectRecommendListBean> list_1 = new ArrayList<>();
                list_1 = response.getDirect_recommend_list();
                if (list_1.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                    page--;
                } else {
                    list.addAll(list_1);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }
    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        page = 1;
        String string = "?token=" + localUserInfo.getToken()
                + "&page=" + page//当前页号
                + "&count=" + "10";//页面行数;
        Request(string);
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.share_h33));
    }
}
