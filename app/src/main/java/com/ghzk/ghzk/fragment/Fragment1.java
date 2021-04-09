package com.ghzk.ghzk.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.ghzk.ghzk.R;
import com.ghzk.ghzk.activity.CooperativeBrandActivity;
import com.ghzk.ghzk.activity.CooperativeShopActivity;
import com.ghzk.ghzk.activity.MainActivity;
import com.ghzk.ghzk.base.BaseFragment;
import com.ghzk.ghzk.model.Fragment1Model;
import com.ghzk.ghzk.model.Fragment1Model_P;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
import com.ghzk.ghzk.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.ghzk.ghzk.net.OkHttpClientManager.IMGHOST;


/**
 * Created by Mr.Z on 2016/1/6.
 * 合作品牌
 */

public class Fragment1 extends BaseFragment {
    int type = 1;
    TextView textView1, textView2, textView3, textView4, textView5;

    Fragment1Model model;

    private RecyclerView recyclerView;
    List<Fragment1Model_P> list1 = new ArrayList<>();
    CommonAdapter<Fragment1Model_P> mAdapter1;

    List<Fragment1Model.CooperationShopListBean> list2 = new ArrayList<>();
    CommonAdapter<Fragment1Model.CooperationShopListBean> mAdapter2;

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
    public void onDestroy() {
        super.onDestroy();

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
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
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

        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);

    }

    @Override
    protected void initData() {
//        requestServer();

    }


    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        if (isAdded()) {
            showProgress(true, getString(R.string.app_loading));
            String string = "?token=" + localUserInfo.getToken();
            Request(string);
        }
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
            public void onResponse(Fragment1Model response) {
                showContentPage();
                hideProgress();
                model = response;

                list1.clear();
                List<String> img1 = new ArrayList<>();
                List<String> img2 = new ArrayList<>();
                List<String> img3 = new ArrayList<>();
                for (int i = 0; i < response.getCooperation_brand_list().size(); i++) {
                    switch (i % 3) {
                        case 0:
                            img1.add(response.getCooperation_brand_list().get(i).getLogo());
                            break;
                        case 1:
                            img2.add(response.getCooperation_brand_list().get(i).getLogo());
                            break;
                        case 2:
                            img3.add(response.getCooperation_brand_list().get(i).getLogo());
                            break;
                    }
                }

                for (int i = 0; i < img1.size(); i++) {
                    String img_1 = img1.get(i);
                    String img_2 = "";
                    if (img2.size() > i) {
                        img_2 = img2.get(i);
                    }
                    String img_3 = "";
                    if (img3.size() > i) {
                        img_3 = img3.get(i);
                    }
                    list1.add(new Fragment1Model_P(img_1, img_2, img_3));
                }

                MyLogger.i(">>>>>>>>>>" + list1.toString());

                mAdapter1 = new CommonAdapter<Fragment1Model_P>
                        (getActivity(), R.layout.item_fragment1_1_copy, list1) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1Model_P model, int position) {
                        //第一张
                        ImageView img1 = holder.getView(R.id.img1);
                        Glide.with(getActivity()).load(IMGHOST + model.getImg1())
//                                .centerCrop()
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(img1);//加载图片

                        //第二张
                        ImageView img2 = holder.getView(R.id.img2);
                        if (!model.getImg2().equals("")) {
                            holder.getView(R.id.view1).setVisibility(View.VISIBLE);
                            Glide.with(getActivity()).load(IMGHOST + model.getImg2())
//                                    .centerCrop()
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(img2);//加载图片
                        } else {
                            holder.getView(R.id.view1).setVisibility(View.GONE);
                        }

                        //第三张
                        ImageView img3 = holder.getView(R.id.img3);
                        if (!model.getImg3().equals("")) {
                            holder.getView(R.id.view2).setVisibility(View.VISIBLE);
                            Glide.with(getActivity()).load(IMGHOST + model.getImg3())
//                                    .centerCrop()
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(img3);//加载图片
                        } else {
                            holder.getView(R.id.view2).setVisibility(View.GONE);
                        }

                    }
                };

                list2 = response.getCooperation_shop_list();
                mAdapter2 = new CommonAdapter<Fragment1Model.CooperationShopListBean>
                        (getActivity(), R.layout.item_fragment1_2, list2) {
                    @Override
                    protected void convert(ViewHolder holder, Fragment1Model.CooperationShopListBean model, int position) {
                        ImageView imageView1 = holder.getView(R.id.imageView1);
                        Glide.with(getActivity())
                                .load(OkHttpClientManager.IMGHOST + model.getCover())
//                                .fitCenter()
                                .apply(RequestOptions.bitmapTransform(new
                                        RoundedCorners(CommonUtil.dip2px(getActivity(), 10))))
                                .placeholder(R.mipmap.loading)//加载站位图
                                .error(R.mipmap.zanwutupian)//加载失败
                                .into(imageView1);//加载图片
                        ImageView imageView2 = holder.getView(R.id.imageView2);
                        if (model.getStatus() == 1){
                            //待安装
                            imageView2.setImageResource(R.mipmap.bg_anzhuangzhong);
                        }else {
                            imageView2.setImageResource(R.mipmap.bg_yianzhuang);
                        }

                        holder.setText(R.id.tv_name,model.getTitle());
//                        holder.setText(R.id.tv_content,model.getProvince()+model.getCity()+model.getDistrict());
                        holder.setText(R.id.tv_content,model.getIndustry_title());
                        holder.setText(R.id.tv_addr,model.getAddress());
                        holder.setText(R.id.tv_num,model.getNum()+"");

                    }
                };
                changeUI();
                textView1.setText(list1.size()+"");
                textView2.setText(list2.size()+"");


                MainActivity.isOver = true;

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView3:
                //合作品牌
                type = 1;
                changeUI();

                break;
            case R.id.textView4:
                //合作门店
                type = 2;
                changeUI();

                break;
            case R.id.textView5:
                //查看更多
                if (type == 1) {
                    //品牌
                    CommonUtil.gotoActivity(getActivity(), CooperativeBrandActivity.class, false);
                } else {
                    //门店
                    CommonUtil.gotoActivity(getActivity(), CooperativeShopActivity.class, false);
                }
                break;

        }
    }

    private void changeUI() {
        if (type == 1) {
            textView3.setTextColor(getResources().getColor(R.color.white));
            textView3.setBackgroundResource(R.drawable.yuanjiao_20_huise2);
            textView4.setTextColor(getResources().getColor(R.color.green));
            textView4.setBackgroundResource(R.drawable.yuanjiao_20_baise);

            if (list1.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter1);
//                mAdapter1.notifyDataSetChanged();
            } else {
                showEmptyPage();
            }

        } else if (type == 2) {
            textView3.setTextColor(getResources().getColor(R.color.green));
            textView3.setBackgroundResource(R.drawable.yuanjiao_20_baise);
            textView4.setTextColor(getResources().getColor(R.color.white));
            textView4.setBackgroundResource(R.drawable.yuanjiao_20_huise2);

            if (list2.size() > 0) {
                showContentPage();
                recyclerView.setAdapter(mAdapter2);
//                mAdapter2.notifyDataSetChanged();
            } else {
                showEmptyPage();
            }

        }
    }

    @Override
    protected void updateView() {

    }
}
