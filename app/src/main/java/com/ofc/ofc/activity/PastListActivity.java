package com.ofc.ofc.activity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.ofc.ofc.R;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.PastListModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zyz on 2019/1/7.
 * 往期列表
 */
public class PastListActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<PastListModel.LikeGameListBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastlist);
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
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    protected void initData() {
        requestServer();
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(this, URLs.PastList + string, new OkHttpClientManager.ResultCallback<PastListModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(PastListModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>直推会员" + response);
                if (response != null) {
                    list = response.getLike_game_list();
                    if (list.size() > 0) {
                        CommonAdapter<PastListModel.LikeGameListBean> mAdapter = new CommonAdapter<PastListModel.LikeGameListBean>(
                                PastListActivity.this, R.layout.item_pastlist, list) {
                            @Override
                            protected void convert(ViewHolder holder, PastListModel.LikeGameListBean model, int position) {
                                holder.setText(R.id.textView1, model.getPeriod());//期次号
                                holder.setText(R.id.textView2, model.getWin_chain_title() + "");//胜利方
                                holder.setText(R.id.textView3, model.getAmount_money() + getString(R.string.app_ge));//竞猜额
                                /*ImageView imageView = holder.getView(R.id.imageView1);
                                if (!model.getHead().equals(""))
                                    Glide.with(PastListActivity.this).load(OkHttpClientManager.IMGHOST + model.getHead()).centerCrop().into(imageView);//加载图片
                                else
                                    imageView.setImageResource(R.mipmap.headimg);*/

                            }
                        };
                        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                                Bundle bundle = new Bundle();
                                bundle.putString("id", list.get(position).getId());
//                                CommonUtil.gotoActivityWithData(PastListActivity.this, PeriodDetailActivity.class, bundle, false);
                            }

                            @Override
                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                                return false;
                            }
                        });
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
        this.showLoadingPage();
//        showProgress(true, getString(R.string.app_loading));
        String string = "?token=" + localUserInfo.getToken();
        Request(string);
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.fragment3_h16));
    }
}
