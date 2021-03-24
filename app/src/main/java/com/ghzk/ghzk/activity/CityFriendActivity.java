package com.ghzk.ghzk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.ghzk.ghzk.R;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.model.CityFriendModel;
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
 * Created by zyz on 2019-12-14.
 * 城市合作伙伴-已申请
 */
public class CityFriendActivity extends BaseActivity {
    RecyclerView recyclerView;
    List<CityFriendModel.ServiceCenterListBean> list = new ArrayList<>();
    CommonAdapter<CityFriendModel.ServiceCenterListBean> mAdapter;
    TextView textView1, textView2, textView3, textView4, textView5, textView6;
    ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityfriend);

        mImmersionBar.reset()
                .fitsSystemWindows(true)
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
//        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void initView() {
        /*relativeLayout = findViewByID_My(R.id.relativeLayout);
        //动态设置linearLayout的高度为屏幕高度的1/2
        ViewGroup.LayoutParams lp = relativeLayout.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(this) / 2;*/
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                request("?token=" + localUserInfo.getToken());
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

        imageView1 = findViewByID_My(R.id.imageView1);
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
        showProgress(true, getString(R.string.app_loading2));
        request("?token=" + localUserInfo.getToken());
    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(CityFriendActivity.this, URLs.CityFriend + string, new OkHttpClientManager.ResultCallback<CityFriendModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(CityFriendModel response) {
                MyLogger.i(">>>>>>>>>服务中心" + response);
                hideProgress();
                Glide.with(CityFriendActivity.this).load(IMGHOST + response.getHead())
                        .centerCrop()
                        .placeholder(R.mipmap.loading)//加载站位图
                        .error(R.mipmap.zanwutupian)//加载失败
                        .into(imageView1);//加载图片

                textView1.setText(response.getNickname());//昵称
                textView2.setText(getString(R.string.myprofile_h24) + response.getService_code());//服务码
                textView3.setText(response.getService_count());//绑定人数
                textView4.setText(response.getService_performance_num());//销售设备
                textView5.setText(response.getUse_earning_money());//收益分成
                textView6.setText("¥ " + response.getSell_earning_money());//销售分成

                list = response.getService_center_list();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<CityFriendModel.ServiceCenterListBean>
                            (CityFriendActivity.this, R.layout.item_cityfriend, list) {
                        @Override
                        protected void convert(ViewHolder holder, CityFriendModel.ServiceCenterListBean model, int position) {
                            ImageView imageView1 = holder.getView(R.id.imageView1);
                            Glide.with(CityFriendActivity.this)
                                    .load(OkHttpClientManager.IMGHOST + model.getPic1())
                                    .fitCenter()
                                    .apply(RequestOptions.bitmapTransform(new
                                            RoundedCorners(CommonUtil.dip2px(CityFriendActivity.this, 10))))
                                    .placeholder(R.mipmap.loading)//加载站位图
                                    .error(R.mipmap.zanwutupian)//加载失败
                                    .into(imageView1);//加载图片

                            holder.setText(R.id.tv_name, model.getTitle());
                            holder.setText(R.id.tv_content, model.getProvince() + model.getCity() + model.getDistrict());
                            holder.setText(R.id.tv_addr, model.getAddress());
                            holder.setText(R.id.tv_num, model.getArea() + "㎡");

                        }
                    };

                    showContentPage();
                    recyclerView.setAdapter(mAdapter);
//                mAdapter1.notifyDataSetChanged();
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.textView7:
                //查看更多
//                CommonUtil.gotoActivity(this, ServiceCenter_MoreActivity.class, false);
                break;
            case R.id.linearLayout:
                //服务码
//                CommonUtil.gotoActivity(this, ChangeServiceNumActivity.class, false);
                break;
        }
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
