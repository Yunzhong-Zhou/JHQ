package com.fone.fone.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.DirectMemberModel;
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

import static com.fone.fone.net.OkHttpClientManager.IMGHOST;


/**
 * Created by zyz on 2019/1/7.
 * 直推会员
 */
public class SharePeopleActivity extends BaseActivity {
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8;
    private RecyclerView recyclerView;
    List<DirectMemberModel.DirectRecommendListBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharepeople);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
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

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        textView8 = findViewByID_My(R.id.textView8);

    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
        }
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
                    textView1.setText(response.getGrade_1()+getString(R.string.app_ren));
                    textView3.setText(response.getGrade_2()+getString(R.string.app_ren));
                    textView5.setText(response.getGrade_3()+getString(R.string.app_ren));
                    textView7.setText(response.getGrade_4()+getString(R.string.app_ren));

                    list = response.getDirect_recommend_list();
                    if (list.size() > 0) {
                        CommonAdapter<DirectMemberModel.DirectRecommendListBean> mAdapter = new CommonAdapter<DirectMemberModel.DirectRecommendListBean>(
                                SharePeopleActivity.this, R.layout.item_sharepeople, list) {
                            @Override
                            protected void convert(ViewHolder holder, DirectMemberModel.DirectRecommendListBean model, int position) {
//                                holder.setText(R.id.tv_num, model.getGrade_title());
                                holder.setText(R.id.tv_name, model.getNickname());
//                                holder.setText(R.id.tv_code, "（"+getString(R.string.fragment5_h1)+model.getInvite_code()+")");
                                holder.setText(R.id.tv_money, model.getOrder_goods_count()+getString(R.string.app_tai));
                                holder.setText(R.id.tv_pintuan, model.getGrade_title());

                                ImageView iv_head = holder.getView(R.id.iv_head);
                                Glide.with(SharePeopleActivity.this)
                                        .load(IMGHOST + model.getHead())
                                        .centerCrop()
                                        .placeholder(R.mipmap.loading)//加载站位图
                                        .error(R.mipmap.headimg)//加载失败
                                        .into(iv_head);//加载图片

                            }
                        };
                        recyclerView.setAdapter(mAdapter);
                    } else {
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
//        titleView.setTitle(getString(R.string.share_h56));
        titleView.setVisibility(View.GONE);
    }
}
