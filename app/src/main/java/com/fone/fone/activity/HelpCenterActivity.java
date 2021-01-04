package com.fone.fone.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.HelpModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mr.Z on 2020/12/10.
 * 帮助中心
 */
public class HelpCenterActivity extends BaseActivity {
    HelpModel model;
    TextView textView1, textView2;
    RecyclerView recyclerView;
    List<HelpModel.ArticleListBean> list = new ArrayList<>();
    CommonAdapter<HelpModel.ArticleListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpcenter);
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                RequestHelpList("?token=" + localUserInfo.getToken());
            }

            @Override
            public void onLoadmore() {
                //加载更多
            }
        });
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);

        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.textView1:
                //拨打电话
                showToast("确认拨打 " + model.getLandline_number() + " 吗？", "确认", "取消",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                //创建打电话的意图
                                Intent intent = new Intent();
                                //设置拨打电话的动作
                                intent.setAction(Intent.ACTION_CALL);//直接拨出电话
//                               intent.setAction(Intent.ACTION_DIAL);//只调用拨号界面，不拨出电话
                                //设置拨打电话的号码
                                intent.setData(Uri.parse("tel:" + model.getLandline_number()));
                                //开启打电话的意图
                                startActivity(intent);
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                break;
            case R.id.textView2:
                //联系客服
                CommonUtil.gotoActivity(this, OnlineServiceActivity.class, false);
                break;
        }
    }

    @Override
    protected void initData() {
        requestServer();

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
//        showProgress(true, getString(R.string.app_loading2));
        //帮助列表
        RequestHelpList("?token=" + localUserInfo.getToken());
    }

    private void RequestHelpList(String string) {
        OkHttpClientManager.getAsyn(HelpCenterActivity.this, URLs.Help + string, new OkHttpClientManager.ResultCallback<HelpModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(HelpModel response) {
                showContentPage();
                hideProgress();
                model = response;
                textView1.setText(getString(R.string.onlineservice_h3) + response.getLandline_number());
                list = response.getArticle_list();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<HelpModel.ArticleListBean>
                            (HelpCenterActivity.this, R.layout.item_help, list) {
                        @Override
                        protected void convert(ViewHolder holder, HelpModel.ArticleListBean model, int position) {
                            holder.setText(R.id.textView1, model.getTitle());
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            Bundle bundle = new Bundle();
                            bundle.putString("url", list.get(position).getUrl());
                            CommonUtil.gotoActivityWithData(HelpCenterActivity.this, WebContentActivity.class, bundle, false);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.fragment5_h18));
    }
}
