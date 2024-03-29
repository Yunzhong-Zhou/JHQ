package com.ghzk.ghzk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liaoinstan.springview.widget.SpringView;
import com.ghzk.ghzk.R;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.model.InformationModel;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
import com.ghzk.ghzk.utils.MyLogger;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by zyz on 2017/9/16.
 * 资讯
 */
public class InformationActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<InformationModel.ArticleCategory1Bean.ArticleListBean> list1 = new ArrayList<>();
    CommonAdapter<InformationModel.ArticleCategory1Bean.ArticleListBean> adapter1;

    List<InformationModel.ArticleCategory2Bean.ArticleListBeanX> list2 = new ArrayList<>();
    CommonAdapter<InformationModel.ArticleCategory2Bean.ArticleListBeanX> adapter2;

//    List<InformationModel.BusinessNewsBean> list2 = new ArrayList<>();
//    CommonAdapter<InformationModel.BusinessNewsBean> adapter2;

    private LinearLayout linearLayout1, linearLayout2;
    private TextView textView1, textView2;
    private View view1, view2;
    int type = 1;

    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }

    @Override
    protected void initView() {
        //刷新
        setSpringViewMore(true);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                String string = "?page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                Request(string);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                String string = "?page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMore(string);
            }
        });

        recyclerView = findViewByID_My(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);


        /*alistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (type == 1) {
                    //公告
                    Bundle bundle = new Bundle();
                    bundle.putString("url", list1.get(i).getUrl());
                    CommonUtil.gotoActivityWithData(InformationActivity.this, WebContentActivity.class, bundle, false);
                } else if (type == 2) {
                    //行业新闻
                    Bundle bundle = new Bundle();
                    bundle.putString("url", list2.get(i).getUrl());
                    CommonUtil.gotoActivityWithData(InformationActivity.this, WebContentActivity.class, bundle, false);
                } else if (type == 3) {
                    //其他
                    Bundle bundle = new Bundle();
                    bundle.putString("url", list3.get(i).getUrl());
                    CommonUtil.gotoActivityWithData(InformationActivity.this, WebContentActivity.class, bundle, false);
                }
            }
        });*/
    }

    @Override
    protected void initData() {
        requestServer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.linearLayout2:
                type = 2;
                changeUI();
                break;
            case R.id.linearLayout3:
                type = 3;
                changeUI();
                break;
        }
    }

    private void changeUI() {
        switch (type) {
            case 1:
                textView1.setTextColor(getResources().getColor(R.color.green));
                textView2.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                if (list1.size() > 0) {
                    showContentPage();
                    recyclerView.setAdapter(adapter1);
                    adapter1.notifyDataSetChanged();
                } else {
                    showEmptyPage();//空数据
                }
                break;
            case 2:
                textView1.setTextColor(getResources().getColor(R.color.black3));
                textView2.setTextColor(getResources().getColor(R.color.green));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);

                if (list2.size() > 0) {
                    showContentPage();
                    recyclerView.setAdapter(adapter2);
                    adapter2.notifyDataSetChanged();
                } else {
                    showEmptyPage();//空数据
                }
                break;
            /*case 3:
                textView1.setTextColor(getResources().getColor(R.color.black3));
                textView2.setTextColor(getResources().getColor(R.color.black3));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);

                if (list3.size() > 0) {
                    showContentPage();
                    recyclerView.setAdapter(adapter3);
                    adapter3.notifyDataSetChanged();
                } else {
                    showEmptyPage();//空数据
                }
                break;*/
        }

    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(InformationActivity.this, URLs.Notice + string, new OkHttpClientManager.ResultCallback<InformationModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                onHttpResult();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(InformationModel response) {
                showContentPage();
                onHttpResult();
                MyLogger.i(">>>>>>>>>公告列表" + response);
                textView1.setText(response.getArticle_category_1().getTitle());
                textView2.setText(response.getArticle_category_2().getTitle());
                list1 = response.getArticle_category_1().getArticle_list();
                adapter1 = new CommonAdapter<InformationModel.ArticleCategory1Bean.ArticleListBean>
                        (InformationActivity.this, R.layout.item_information, list1) {
                    @Override
                    protected void convert(ViewHolder holder, InformationModel.ArticleCategory1Bean.ArticleListBean model, int position) {
                        holder.setText(R.id.textView1, model.getTitle());
//                        holder.setText(R.id.textView2, model.getDigest());
                        holder.setText(R.id.textView2, model.getCreated_at());
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        if (!model.getCover().equals(""))
                            Glide.with(InformationActivity.this)
                                    .load(OkHttpClientManager.IMGHOST + model.getCover())
//                                    .placeholder(R.mipmap.headimg)//加载站位图
//                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片
                        else
                            imageView1.setImageResource(R.mipmap.headimg);
                    }
                };
                adapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url", list1.get(position).getUrl());
                        CommonUtil.gotoActivityWithData(InformationActivity.this, WebContentActivity.class, bundle, false);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });
                list2 = response.getArticle_category_2().getArticle_list();
                adapter2 = new CommonAdapter<InformationModel.ArticleCategory2Bean.ArticleListBeanX>
                        (InformationActivity.this, R.layout.item_information, list2) {
                    @Override
                    protected void convert(ViewHolder holder, InformationModel.ArticleCategory2Bean.ArticleListBeanX model, int position) {
                        holder.setText(R.id.textView1, model.getTitle());
                        holder.setText(R.id.textView2, model.getDigest());
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        Glide.with(InformationActivity.this)
                                .load(OkHttpClientManager.IMGHOST + model.getCover())
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.headimg)//加载失败
                                .into(imageView1);//加载图片
                    }
                };
                adapter2.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url", list2.get(position).getUrl());
                        CommonUtil.gotoActivityWithData(InformationActivity.this, WebContentActivity.class, bundle, false);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });

                /*list3 = response.getBusiness_news();
                adapter3 = new CommonAdapter<InformationModel.BusinessNewsBean>
                        (InformationActivity.this, R.layout.item_information, list3) {
                    @Override
                    protected void convert(ViewHolder holder, InformationModel.BusinessNewsBean model, int position) {
                        holder.setText(R.id.textView1,model.getTitle());
                        holder.setText(R.id.textView2,model.getDigest());
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        if (!model.getCover().equals(""))
                            Glide.with(InformationActivity.this)
                                    .load(OkHttpClientManager.IMGHOST+model.getCover())
                                    .placeholder(R.mipmap.headimg)//加载站位图
                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片
                        else
                            imageView1.setImageResource(R.mipmap.headimg);
                    }
                };
                adapter3.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url", list3.get(position).getUrl());
                        CommonUtil.gotoActivityWithData(InformationActivity.this, WebContentActivity.class, bundle, false);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });*/


                changeUI();

            }
        });

    }

    private void RequestMore(String string) {
        OkHttpClientManager.getAsyn(InformationActivity.this, URLs.Notice + string, new OkHttpClientManager.ResultCallback<InformationModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                onHttpResult();
                if (!info.equals("")) {
                    showToast(info);
                }
                page--;
            }

            @Override
            public void onResponse(InformationModel response) {
                showContentPage();
                onHttpResult();
                MyLogger.i(">>>>>>>>>列表更多" + response);

                List<InformationModel.ArticleCategory1Bean.ArticleListBean> list1_1 = new ArrayList<>();
                list1_1 = response.getArticle_category_1().getArticle_list();

                List<InformationModel.ArticleCategory2Bean.ArticleListBeanX> list2_1 = new ArrayList<>();
                list2_1 = response.getArticle_category_2().getArticle_list();

                if (list1_1.size() == 0 && list2_1.size() == 0) {
                    page--;
                    myToast(getString(R.string.app_nomore));
                } else {
                    if (list1_1.size() != 0) {
                        list1.addAll(list1_1);
                        adapter1.notifyDataSetChanged();
                    }
                    if (list2_1.size() != 0) {
                        list2.addAll(list2_1);
                        adapter2.notifyDataSetChanged();
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
        Request("?token=" + localUserInfo.getToken());

    }

    public void onHttpResult() {
        hideProgress();
        springView.onFinishFreshAndLoad();
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.information_title));
    }
}
