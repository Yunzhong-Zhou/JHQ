package com.ghzk.ghzk.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.ghzk.ghzk.R;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.model.ContractListModel;
import com.ghzk.ghzk.net.OkHttpClientManager;
import com.ghzk.ghzk.net.URLs;
import com.ghzk.ghzk.utils.CommonUtil;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.ghzk.ghzk.net.OkHttpClientManager.HOST;

/**
 * Created by zyz on 2017/9/5.
 * 合同列表
 */

public class ContractListActivity extends BaseActivity {
    private RecyclerView recyclerView;
    List<ContractListModel.InvoiceListBean> list = new ArrayList<>();
    CommonAdapter<ContractListModel.InvoiceListBean> mAdapter;

    int page = 1;
    TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractlist);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initView() {
        recyclerView = findViewByID_My(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSpringViewMore(true);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                page = 1;
                String string = "?page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentList(string);
            }

            @Override
            public void onLoadmore() {
                page = page + 1;
                //加载更多
                String string = "?page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestMyInvestmentListMore(string);
            }
        });
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
    }

    @Override
    protected void initData() {
        requestServer();//获取数据
    }

    private void RequestMyInvestmentList(String string) {
        OkHttpClientManager.getAsyn(ContractListActivity.this, URLs.ContractList + string, new OkHttpClientManager.ResultCallback<ContractListModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                onHttpResult();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(ContractListModel response) {
                showContentPage();
                onHttpResult();
                textView1.setText(response.getInvoice_money());
                textView2.setText(response.getResidue_invoice_money());
                list = response.getInvoice_list();
                if (list.size() == 0) {
                    showEmptyPage();//空数据
                } else {
                    mAdapter = new CommonAdapter<ContractListModel.InvoiceListBean>
                            (ContractListActivity.this, R.layout.item_contractlist, list) {
                        @Override
                        protected void convert(ViewHolder holder, ContractListModel.InvoiceListBean model, int position) {
                            holder.setText(R.id.textView1, model.getShow_created_at());//时间
                            holder.setText(R.id.textView2, "¥" + model.getMoney());//金额
                            holder.setText(R.id.textView3, model.getType_title());

                            String Url = HOST + model.getElectronic_url();
                            holder.getView(R.id.iv_chakan).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (!model.getElectronic_url().equals("")) {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("url", Url);
                                        CommonUtil.gotoActivityWithData(ContractListActivity.this, ShowPDFActivity.class, bundle, false);
//                                        CommonUtil.gotoActivityWithData(ContractListActivity.this, WebContentActivity1.class, bundle, false);
                                    } else {
                                        myToast("暂无发票文件");
                                    }
                                }
                            });
                            holder.getView(R.id.ic_xiazai).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (!model.getElectronic_url().equals("")) {
                                        ProgressDialog mProgressDialog;
                                        if (Url.substring(Url.length() - 3).equals("pdf")) {
                                            //是pdf文件
                                            mProgressDialog = new ProgressDialog(ContractListActivity.this);
                                            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                            mProgressDialog.setCancelable(false);
                                            mProgressDialog.show();
                                            //截取最后14位 作为文件名
                                            String s = Url.substring(Url.length() - 14);
                                            //文件存储
                                            File file1 = new File(Environment.getExternalStorageDirectory(), getFileName(s));
                                            new Thread() {
                                                public void run() {
                                                    File haha = new File(file1.getAbsolutePath());
                                                    //判断是否有此文件
                                                    if (haha.exists()) {
                                                        //有缓存文件,拿到路径 直接打开
                                                        Message msg = Message.obtain();
                                                        msg.obj = haha;
                                                        msg.what = 1;
                                                        handler.sendMessage(msg);
                                                        mProgressDialog.dismiss();
                                                        return;
                                                    }
//              本地没有此文件 则从网上下载打开
                                                    File downloadfile = downLoad(Url, file1.getAbsolutePath(), mProgressDialog);
//              Log.i("Log",file1.getAbsolutePath());
                                                    Message msg = Message.obtain();
                                                    if (downloadfile != null) {
                                                        // 下载成功,安装....
                                                        msg.obj = downloadfile;
                                                        msg.what = 1;
                                                    } else {
                                                        // 提示用户下载失败.
                                                        msg.what = 2;
                                                    }
                                                    handler.sendMessage(msg);
                                                    mProgressDialog.dismiss();
                                                }
                                            }.start();
                                        } else {
                                            //不是pdf文件
                                        /*Intent intent = new Intent();
                                        intent.putExtra("url", Strname);
                                        intent.setClass(EquipmentDetailActivity.this, ShowSignatureActivity.class);
                                        startActivity(intent);*/
                                        }
                                    } else {
                                        myToast("暂无发票文件");
                                    }
                                }
                            });
                        }
                    };
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                                /*Bundle bundle1 = new Bundle();
                                bundle1.putString("id", list.get(position).getId());
                                CommonUtil.gotoActivityWithData(HuiGouListActivity.this, RechargeDetailActivity.class, bundle1, false);*/
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

    private void RequestMyInvestmentListMore(String string) {
        OkHttpClientManager.getAsyn(ContractListActivity.this, URLs.ContractList + string, new OkHttpClientManager.ResultCallback<ContractListModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                showErrorPage();
                onHttpResult();
                if (!info.equals("")) {
                    showToast(info);
                }
                page--;
            }

            @Override
            public void onResponse(ContractListModel response) {
//                showContentPage();
                onHttpResult();
                JSONObject jObj;
                List<ContractListModel.InvoiceListBean> list1 = new ArrayList<>();
                list1 = response.getInvoice_list();
                if (list1.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                    page--;
                } else {
                    list.addAll(list1);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
        }
    }

    @Override
    protected void updateView() {
//        titleView.setTitle(R.string.fragment4_h20);
        titleView.setVisibility(View.GONE);
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        page = 1;
        String string = "?page=" + page//当前页号
                + "&count=" + "10"//页面行数
                + "&token=" + localUserInfo.getToken();
        RequestMyInvestmentList(string);
    }

    public void onHttpResult() {
        hideProgress();
        springView.onFinishFreshAndLoad();

    }

    /**************************pdf文件下载及打开**************************************************/
    /**
     * 下载完成后  直接打开文件
     */
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:

                    /*File file = (File) msg.obj;
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.addCategory("android.intent.category.DEFAULT");
                    if (Build.VERSION.SDK_INT >= 24) {
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    } else {
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                    Uri uri = getUriForFile(ContractListActivity.this, file);
                    MyLogger.i(">>>>>>"+uri.getPath());
                    intent.setDataAndType(uri, "application/pdf");
//              startActivity(intent);
                    startActivity(Intent.createChooser(intent, "请选择PDF查看器打开"));*/
                    /**
                     * 弹出选择框   把本activity销毁
                     */
                    showToast("已下载完成，请到文件管理根目录查看");
                    break;
                case 2:
                    showToast("文件加载失败");
                    break;
            }
        }
    };

    private static Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context.getApplicationContext(), context.getPackageName() + ".fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }


    /**
     * 传入文件 url  文件路径  和 弹出的dialog  进行 下载文档
     */
    public static File downLoad(String serverpath, String savedfilepath, ProgressDialog pd) {
        try {
            URL url = new URL(serverpath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            if (conn.getResponseCode() == 200) {
                int max = conn.getContentLength();
                pd.setMax(max);
                InputStream is = conn.getInputStream();
                File file = new File(savedfilepath);
                FileOutputStream fos = new FileOutputStream(file);
                int len = 0;
                byte[] buffer = new byte[1024];
                int total = 0;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    total += len;
                    pd.setProgress(total);
                }
                fos.flush();
                fos.close();
                is.close();
                return file;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getFileName(String serverurl) {
        return serverurl.substring(serverurl.lastIndexOf("/") + 1);
    }
}
