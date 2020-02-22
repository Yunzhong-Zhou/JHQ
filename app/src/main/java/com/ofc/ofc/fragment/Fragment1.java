package com.ofc.ofc.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.activity.MainActivity;
import com.ofc.ofc.activity.WebContentActivity;
import com.ofc.ofc.base.BaseFragment;
import com.ofc.ofc.model.Fragment1Model;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.LocalUserInfo;
import com.ofc.ofc.utils.MyLogger;
import com.squareup.okhttp.Request;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.ofc.ofc.net.OkHttpClientManager.IMGHOST;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 学院
 */

public class Fragment1 extends BaseFragment {
    Banner banner;
    List<String> images = new ArrayList<>();
    int type = 1;
    private RecyclerView recyclerView;
    List<Fragment1Model.ArticleCategory1Bean.ArticleListBean> list1 = new ArrayList<>();
    CommonAdapter<Fragment1Model.ArticleCategory1Bean.ArticleListBean> mAdapter1;

    List<Fragment1Model.ArticleCategory2Bean.ArticleListBeanX> list2 = new ArrayList<>();
    CommonAdapter<Fragment1Model.ArticleCategory2Bean.ArticleListBeanX> mAdapter2;

    List<Fragment1Model.ArticleCategory3Bean.ArticleListBeanXX> list3 = new ArrayList<>();
    CommonAdapter<Fragment1Model.ArticleCategory3Bean.ArticleListBeanXX> mAdapter3;

    //悬浮部分
    LinearLayout linearLayout1, linearLayout2, linearLayout3;
    TextView textView1, textView2, textView3;
    View view1, view2, view3;

    ImageView iv_change;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.item == 0) {
            requestServer();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if (MainActivity.item == 0) {
            requestServer();
        }*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 0) {
                    requestServer();
                }
            }
        }
    }

    @Override
    protected void initView(View view) {
        banner = findViewByID_My(R.id.banner);
//        findViewByID_My(R.id.headview).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
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

        //悬浮部分
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);

        //列表
        recyclerView = findViewByID_My(R.id.recyclerView);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);
        /*//listview 滑动监听
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mLinearLayoutManager.findFirstVisibleItemPosition() >= 1) {
                    invis.setVisibility(View.VISIBLE);
                    headerView2.setVisibility(View.GONE);
                } else {
                    invis.setVisibility(View.GONE);
                    headerView2.setVisibility(View.VISIBLE);
                }
            }
        });*/

        /*//banner
        images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
        images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
        images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
        images.add("http://file02.16sucai.com/d/file/2014/0825/dcb017b51479798f6c60b7b9bd340728.jpg");
        *//*images.clear();
        for (int i = 0; i < response.getBanner().size(); i++) {
            images.add(OkHttpClientManager.IMGHOST+response.getBanner().get(i).getUrl());
        }*//*
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                Bundle bundle = new Bundle();
//                bundle.putInt("type", response.getBanner().get(position).getType());
//                CommonUtil.gotoActivityWithData(JiFenShangChengActivity.this, JiFenLieBiaoActivity.class, bundle, false);
            }
        });*/

        iv_change = findViewByID_My(R.id.iv_change);
        if (LocalUserInfo.getInstance(getActivity()).getLanguage_Type().equals("zh")){
            iv_change.setImageResource(R.mipmap.bg_fragment1_zh);
        }else {
            iv_change.setImageResource(R.mipmap.bg_fragment1);
        }

    }

    @Override
    protected void initData() {
//        requestServer();

    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment1 + string, new OkHttpClientManager.ResultCallback<Fragment1Model>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                MainActivity.isOver = true;
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(final Fragment1Model response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>学院" + response);
                textView1.setText(response.getArticle_category_1().getTitle());
                textView2.setText(response.getArticle_category_2().getTitle());
                textView3.setText(response.getArticle_category_3().getTitle());
                //基础知识
                list1 = response.getArticle_category_1().getArticle_list();
                mAdapter1 = new CommonAdapter<Fragment1Model.ArticleCategory1Bean.ArticleListBean>
                        (getActivity(), R.layout.item_fragment1, list1) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1Model.ArticleCategory1Bean.ArticleListBean model, int position) {
                        holder.setText(R.id.textView1, model.getTitle());
                        holder.setText(R.id.textView2, model.getCreated_at());
                        holder.setText(R.id.textView3, model.getRead() + "");
                        holder.setText(R.id.textView4, model.getDigest() + "");
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        if (!model.getCover().equals(""))
                            Glide.with(getActivity())
                                    .load(IMGHOST + model.getCover())
                                    .centerCrop()
//                                    .placeholder(R.mipmap.headimg)//加载站位图
//                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片
                    }
                };
                mAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url", list1.get(position).getUrl());
                        CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle, false);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });

                list2 = response.getArticle_category_2().getArticle_list();
                mAdapter2 = new CommonAdapter<Fragment1Model.ArticleCategory2Bean.ArticleListBeanX>
                        (getActivity(), R.layout.item_fragment1, list2) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1Model.ArticleCategory2Bean.ArticleListBeanX model, int position) {
                        holder.setText(R.id.textView1, model.getTitle());
                        holder.setText(R.id.textView2, model.getCreated_at());
                        holder.setText(R.id.textView3, model.getRead() + "");
                        holder.setText(R.id.textView4, model.getDigest() + "");
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        if (!model.getCover().equals(""))
                            Glide.with(getActivity())
                                    .load(IMGHOST + model.getCover())
                                    .centerCrop()
//                                    .placeholder(R.mipmap.headimg)//加载站位图
//                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片

                    }
                };
                mAdapter2.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url", list2.get(position).getUrl());
                        CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle, false);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });

                list3 = response.getArticle_category_3().getArticle_list();
                mAdapter3 = new CommonAdapter<Fragment1Model.ArticleCategory3Bean.ArticleListBeanXX>
                        (getActivity(), R.layout.item_fragment1, list3) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1Model.ArticleCategory3Bean.ArticleListBeanXX model, int position) {
                        holder.setText(R.id.textView1, model.getTitle());
                        holder.setText(R.id.textView2, model.getCreated_at());
                        holder.setText(R.id.textView3, model.getRead() + "");
                        holder.setText(R.id.textView4, model.getDigest() + "");
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        if (!model.getCover().equals(""))
                            Glide.with(getActivity())
                                    .load(IMGHOST + model.getCover())
                                    .centerCrop()
//                                    .placeholder(R.mipmap.headimg)//加载站位图
//                                    .error(R.mipmap.headimg)//加载失败
                                    .into(imageView1);//加载图片

                    }
                };
                mAdapter3.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url", list3.get(position).getUrl());
                        CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle, false);
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });

                changeUI();

                hideProgress();

                MainActivity.isOver = true;
            }
        });
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
        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.green));
            textView2.setTextColor(getResources().getColor(R.color.black4));
            textView3.setTextColor(getResources().getColor(R.color.black4));
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.INVISIBLE);

            if (list1.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter1);
            } else {
                showEmptyPage();
            }

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.black4));
            textView2.setTextColor(getResources().getColor(R.color.green));
            textView3.setTextColor(getResources().getColor(R.color.black4));
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            view3.setVisibility(View.INVISIBLE);

            if (list2.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter2);
            } else {
                showEmptyPage();
            }
        } else if (type == 3) {
            textView1.setTextColor(getResources().getColor(R.color.black4));
            textView2.setTextColor(getResources().getColor(R.color.black4));
            textView3.setTextColor(getResources().getColor(R.color.green));
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.VISIBLE);

            if (list3.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter3);
            } else {
                showEmptyPage();
            }
        }
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        String string = "?token=" + localUserInfo.getToken();
        Request(string);
    }
    /*@Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }


    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }*/

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            //Glide 加载图片简单用法
            Glide.with(context)
                    .load(path)
                    .into(imageView);

            //Picasso 加载图片简单用法
//            Picasso.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
//            Uri uri = Uri.parse((String) path);
//            imageView.setImageURI(uri);
        }
    }
}
