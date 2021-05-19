package com.ghzk.ghzk.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cretin.tools.fanpermission.FanPermissionListener;
import com.cretin.tools.fanpermission.FanPermissionUtils;
import com.ghzk.ghzk.R;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.model.HelpModel;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
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
                FanPermissionUtils.with(HelpCenterActivity.this)
                        //添加所有你需要申请的权限
//                .addPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)//写入
//                .addPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)//读取
//                        .addPermissions(Manifest.permission.ACCESS_COARSE_LOCATION)//定位
//                        .addPermissions(Manifest.permission.ACCESS_FINE_LOCATION)//定位
                .addPermissions(Manifest.permission.CALL_PHONE)//拨打电话
//                .addPermissions(Manifest.permission.READ_PHONE_STATE)//读取手机状态
//                .addPermissions(Manifest.permission.ACCESS_WIFI_STATE)//访问WiFi状态
//                .addPermissions(Manifest.permission.CAMERA)//相机

                        //添加权限申请回调监听 如果申请失败 会返回已申请成功的权限列表，用户拒绝的权限列表和用户点击了不再提醒的永久拒绝的权限列表
                        .setPermissionsCheckListener(new FanPermissionListener() {
                            @Override
                            public void permissionRequestSuccess() {
                                //所有权限授权成功才会回调这里
                                showToast("确认拨打 " + model.getService_hotline() + " 吗？", "确认", "取消",
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
                                                intent.setData(Uri.parse("tel:" + model.getService_hotline()));
                                                //开启打电话的意图
                                                startActivity(intent);
                                            }
                                        }, new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss();
                                            }
                                        });
                            }

                            @Override
                            public void permissionRequestFail(String[] grantedPermissions, String[] deniedPermissions, String[] forceDeniedPermissions) {
                                //当有权限没有被授权就会回调这里
                                //会返回已申请成功的权限列表（grantedPermissions）
                                //用户拒绝的权限列表（deniedPermissions）
                                //用户点击了不再提醒的永久拒绝的权限列表（forceDeniedPermissions）
                            }
                        })
                        //生成配置
                        .createConfig()
                        //配置是否强制用户授权才可以使用，当设置为true的时候，如果用户拒绝授权，会一直弹出授权框让用户授权
                        .setForceAllPermissionsGranted(false)
                        //配置当用户点击了不再提示的时候，会弹窗指引用户去设置页面授权，这个参数是弹窗里面的提示内容
                        .setForceDeniedPermissionTips("请前往设置->应用->【" + FanPermissionUtils.getAppName(HelpCenterActivity.this) + "】->权限中打开相关权限，否则功能无法正常运行！")
                        //构建配置并生效
                        .buildConfig()
                        //开始授权
                        .startCheckPermission();
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
                textView1.setText(getString(R.string.onlineservice_h3) + response.getService_hotline());
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
