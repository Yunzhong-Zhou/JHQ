package com.ofc.ofccoin.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ofc.ofccoin.R;
import com.ofc.ofccoin.base.BaseActivity;
import com.ofc.ofccoin.model.DirectMemberModel;
import com.ofc.ofccoin.net.OkHttpClientManager;
import com.ofc.ofccoin.net.URLs;
import com.ofc.ofccoin.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.ofc.ofccoin.net.OkHttpClientManager.IMGHOST;


/**
 * Created by zyz on 2019/1/7.
 * 直推会员
 */
public class SharePeopleActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<DirectMemberModel.DirectRecommendListBean> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharepeople);
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
        OkHttpClientManager.getAsyn(this, URLs.DirectMember + string, new OkHttpClientManager.ResultCallback<DirectMemberModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(DirectMemberModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>直推会员" + response);
                if (response != null) {
                    list = response.getDirect_recommend_list();
                    if (list.size()>0){
                        CommonAdapter<DirectMemberModel.DirectRecommendListBean> mAdapter = new CommonAdapter<DirectMemberModel.DirectRecommendListBean>(
                                SharePeopleActivity.this, R.layout.item_directmember, list) {
                            @Override
                            protected void convert(ViewHolder holder, DirectMemberModel.DirectRecommendListBean model, int position) {
                                holder.setText(R.id.textView1, model.getNickname());
                                holder.setText(R.id.textView2, model.getContract_money()+"");
                                holder.setText(R.id.textView3, model.getGrade_title());
                                ImageView imageView = holder.getView(R.id.imageView1);
                                if (!model.getHead().equals(""))
                                    Glide.with(SharePeopleActivity.this).load(IMGHOST + model.getHead()).centerCrop().into(imageView);//加载图片
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
        titleView.setTitle(getString(R.string.share_h33));
    }
}
