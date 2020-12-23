package com.fone.fone.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.JoinDetailModel;
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
 * Created by Mr.Z on 2020/12/14.
 * 加入拼团详情
 */
public class JoinDetailActivity extends BaseActivity {
    String id = "";
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11,tv_confirm;
    ImageView imageView1,imageView2;
    private RecyclerView recyclerView;
    List<JoinDetailModel> list = new ArrayList<>();
    CommonAdapter<JoinDetailModel> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joindetail);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
        findViewByID_My(R.id.left_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        imageView1 = findViewByID_My(R.id.imageView1);
        imageView2 = findViewByID_My(R.id.imageView2);
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        requestServer();//获取数据
    }
    private void RequestDetail(String string) {
        OkHttpClientManager.getAsyn(JoinDetailActivity.this, URLs.JoinDetail + string, new OkHttpClientManager.ResultCallback<JoinDetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(JoinDetailModel response) {
                showContentPage();
                hideProgress();
//                textView1.setText(response.getChange_game().get);//fil价格
//                textView2.setText(response.getChange_game().get);//加入时间
                textView3.setText(response.getChange_game().getStatus_title());//拼团状态
//                textView4.setText(response.getChange_game().get);//结束时间
                textView5.setText(response.getChange_game().getPeriod());//拼团单号
//                textView6.setText(response.getChange_game().get);//算团大小
//                textView7.setText(response.getChange_game().get);//挖矿周期
//                textView9.setText(response.getChange_game().get);//拼中用户name
//                textView10.setText(response.getChange_game().get);//拼中时间
//                textView11.setText(response.getChange_game().get);//拼中排名

                /*Glide.with(JoinDetailActivity.this)
                        .load(OkHttpClientManager.IMGHOST + response.getChange_game().get)
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.headimg)//加载失败
                        .into(imageView1);//加载图片*/
                /*Glide.with(JoinDetailActivity.this)
                        .load(OkHttpClientManager.IMGHOST + response.getChange_game().get)
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.headimg)//加载失败
                        .into(imageView2);//加载图片*/


//                list = response.getChange_game();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<JoinDetailModel>
                            (JoinDetailActivity.this, R.layout.item_joindetaillist, list) {
                        @Override
                        protected void convert(ViewHolder holder, JoinDetailModel model, int position) {
                                /*holder.setText(R.id.textView1, "DRVT：-" + model.getMoney());//标题
                            holder.setText(R.id.textView2, model.getCreated_at());//时间
                            holder.setText(R.id.textView3, getString(R.string.qianbao_h79) + ":" + model.getOfc_price() + "usdt");*/
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

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
