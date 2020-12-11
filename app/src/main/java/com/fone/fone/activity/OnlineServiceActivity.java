package com.fone.fone.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.fone.fone.R;
import com.fone.fone.adapter.HelpAdapter;
import com.fone.fone.adapter.OnlineServiceAdapter;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.HelpModel;
import com.fone.fone.model.OnlineServiceModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.FileUtil;
import com.fone.fone.utils.MyChooseImages;
import com.fone.fone.utils.MyLogger;
import com.cy.dialog.BaseDialog;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.zelory.compressor.Compressor;

import static com.fone.fone.utils.MyChooseImages.REQUEST_CODE_CAPTURE_CAMEIA;
import static com.fone.fone.utils.MyChooseImages.REQUEST_CODE_PICK_IMAGE;


/**
 * Created by fafukeji01 on 2017/5/11.
 * 在线客服
 */

public class OnlineServiceActivity extends BaseActivity {
    private ListView listView;
    List<OnlineServiceModel.LeaveMessageListBean> list;
    OnlineServiceAdapter adapter;
    EditText editText;
    ImageView imageView;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlineservice);
    }

    @Override
    protected void initView() {

        listView = findViewByID_My(R.id.listView);
        editText = findViewByID_My(R.id.editText);
        imageView = findViewByID_My(R.id.imageView);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“发送”键*/
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    MyLogger.i(">>>>>>>输入的内容" + editText.getText().toString().trim());
                    if (!editText.getText().toString().trim().equals("")) {
                        /*隐藏软键盘*/
                        InputMethodManager imm = (InputMethodManager) v
                                .getContext().getSystemService(
                                        Context.INPUT_METHOD_SERVICE);
                        if (imm.isActive()) {
                            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                        }

                        showProgress(true, getString(R.string.app_loading1));
                        String[] filenames = new String[]{};
                        File[] files = new File[]{};
                        params.put("content", editText.getText().toString().trim());
                        params.put("token", localUserInfo.getToken());
                        params.put("software_version", CommonUtil.getVersionName(OnlineServiceActivity.this));
                        params.put("facility", getString(R.string.onlineservice_system1) + CommonUtil.getDeviceBrand()
                                + getString(R.string.onlineservice_system2) + CommonUtil.getSystemModel()
                                + getString(R.string.onlineservice_system3) + CommonUtil.getSystemVersion() +
                                getString(R.string.onlineservice_system4) + CommonUtil.getVersionName(OnlineServiceActivity.this));
                        RequestAddMessage(filenames, files, params);//创建留言
                    } else {
                        myToast(getString(R.string.onlineservice_hint));
                    }
                    return true;
                }
                return false;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyChooseImages.showPhotoDialog(OnlineServiceActivity.this);
            }
        });

        setSpringViewMore(true);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                page = page + 1;
                String string = "?page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestOnlineServiceMore(string);
            }

            @Override
            public void onLoadmore() {
                //加载更多
                page = 1;
                String string = "?page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestOnlineService(string);
//                pullview.onFooterLoadFinish();
            }
        });
        /*pullview.setOnHeaderRefreshListener(new AbPullToRefreshView.OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(AbPullToRefreshView view) {
                //刷新
                page = page + 1;
                String string = "?page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestOnlineServiceMore(string);
            }
        });
        pullview.setOnFooterLoadListener(new AbPullToRefreshView.OnFooterLoadListener() {
            @Override
            public void onFooterLoad(AbPullToRefreshView view) {
                //加载更多
                page = 1;
                String string = "?page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestOnlineService(string);
//                pullview.onFooterLoadFinish();
            }
        });*/
    }

    @Override
    protected void initData() {
        requestServer();
        //帮助列表
//        RequestHelpList("?token=" + localUserInfo.getToken());
    }

    private void RequestHelpList(String string) {
        OkHttpClientManager.getAsyn(OnlineServiceActivity.this, URLs.Help + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
//                showErrorPage();
//                onHttpResult();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
//                showContentPage();
//                onHttpResult();
                MyLogger.i(">>>>>>>>>帮助列表" + response);

                JSONObject jObj;
                List<HelpModel> list_h = new ArrayList<HelpModel>();
                try {
                    jObj = new JSONObject(response);
                    JSONArray jsonArray = jObj.getJSONArray("data");
                    list_h = JSON.parseArray(jsonArray.toString(), HelpModel.class);
                    if (list_h.size() == 0) {
//                        showEmptyPage();//空数据
                    } else {
//                        adapter = new MyRechargeAdapter(OnlineServiceActivity.this, list_h);
//                        listView.setAdapter(adapter);
                        dialog = new BaseDialog(OnlineServiceActivity.this);
                        dialog.contentView(R.layout.dialog_help)
                                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT))
                                .animType(BaseDialog.AnimInType.CENTER)
                                .canceledOnTouchOutside(true)
                                .dimAmount(0.8f)
                                .show();
                        ListView listView_h = dialog.findViewById(R.id.listView);
                        HelpAdapter adapter_h = new HelpAdapter(OnlineServiceActivity.this,list_h);
                        listView_h.setAdapter(adapter_h);
                        final List<HelpModel> finalList_h = list_h;
                        listView_h.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                dialog.dismiss();
                                Bundle bundle = new Bundle();
                                bundle.putString("url", finalList_h.get(position).getUrl());
                                CommonUtil.gotoActivityWithData(OnlineServiceActivity.this, WebContentActivity.class, bundle, false);

                            }
                        });
                        dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
    }


    //对话列表
    private void RequestOnlineService(String string) {
        OkHttpClientManager.getAsyn(OnlineServiceActivity.this, URLs.OnlineService + string, new OkHttpClientManager.ResultCallback<OnlineServiceModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                onHttpResult();
                showErrorPage();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(OnlineServiceModel response) {
                onHttpResult();
                showContentPage();
                MyLogger.i(">>>>>>>>>留言板列表" + response);
                JSONObject jObj;
                List<OnlineServiceModel.LeaveMessageListBean> list1 = new ArrayList<OnlineServiceModel.LeaveMessageListBean>();
                list = new ArrayList<>();
                list1 = response.getLeave_message_list();
                if (list1.size() == 0) {
                    showEmptyPage();
                } else {
                    //反转数据
                    for (int i = list1.size() - 1; i >= 0; i--) {
                        list.add(list1.get(i));
                    }
                    adapter = new OnlineServiceAdapter(OnlineServiceActivity.this, list);
                    listView.setAdapter(adapter);
                    listView.post(new Runnable() {
                        @Override
                        public void run() {
                            // Select the last row so it will scroll into view...
                            listView.setSelection(adapter.getCount() - 1);
                        }
                    });
                }
            }
        });
    }

    private void RequestOnlineServiceMore(String string) {
        OkHttpClientManager.getAsyn(OnlineServiceActivity.this, URLs.OnlineService + string, new OkHttpClientManager.ResultCallback<OnlineServiceModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                onHttpResult();
                showErrorPage();
                if (!info.equals("")) {
                    showToast(info);
                }
                page--;
            }

            @Override
            public void onResponse(OnlineServiceModel response) {
                onHttpResult();
                showContentPage();
                MyLogger.i(">>>>>>>>>留言板列表" + response);
                JSONObject jObj;
                List<OnlineServiceModel.LeaveMessageListBean> list1_1 = new ArrayList<OnlineServiceModel.LeaveMessageListBean>();
                list1_1 = response.getLeave_message_list();
                if (list1_1.size() == 0) {
                    myToast(getString(R.string.app_nomore));
                    page--;
                } else {
                    //反转数据
                    List<OnlineServiceModel.LeaveMessageListBean> list1 = new ArrayList<OnlineServiceModel.LeaveMessageListBean>();
                    for (int i = list1_1.size() - 1; i >= 0; i--) {
                        list1.add(list1_1.get(i));
                    }
                    list1.addAll(list);
                    adapter = new OnlineServiceAdapter(OnlineServiceActivity.this, list1);
                    listView.setAdapter(adapter);
                        /*listView.post(new Runnable() {
                            @Override
                            public void run() {
                                // Select the last row so it will scroll into view...
                                listView.setSelection(adapter.getCount() - 1);
                            }
                        });*/
                }
            }
        });
    }

    //创建留言
    private void RequestAddMessage(String[] fileKeys, File[] files, HashMap<String, String> params) {
        OkHttpClientManager.postAsyn(OnlineServiceActivity.this, URLs.AddMessage, fileKeys, files, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                onHttpResult();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                MyLogger.i(">>>>>>>>>添加留言" + response);
                editText.setText("");
                page = 1;
                String string = "?page=" + page//当前页号
                        + "&count=" + "10"//页面行数
                        + "&token=" + localUserInfo.getToken();
                RequestOnlineService(string);
            }
        });

    }


    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.onlineservice_title));
    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
//        showProgress(true, getString(R.string.app_loading2));
        page = 1;
        String string = "?page=" + page//当前页号
                + "&count=" + "10"//页面行数
                + "&token=" + localUserInfo.getToken();
        RequestOnlineService(string);
    }

    public void onHttpResult() {
        hideProgress();
        springView.onFinishFreshAndLoad();

    }

    /**
     * *****************************************选择图片********************************************
     */
    //选择图片及上传
    ArrayList<String> listFileNames;
    ArrayList<File> listFiles;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = null;
            String imagePath = null;
            switch (requestCode) {
                case REQUEST_CODE_CAPTURE_CAMEIA:
                    //相机
                    uri = Uri.parse("");
                    uri = Uri.fromFile(new File(MyChooseImages.imagepath));
                    imagePath = uri.getPath();
                    break;
                case REQUEST_CODE_PICK_IMAGE:
                    //相册
                    uri = data.getData();
                    //处理得到的url
                    ContentResolver cr = this.getContentResolver();
                    Cursor cursor = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        cursor = cr.query(uri, null, null, null, null, null);
                        if (cursor != null) {
                            cursor.moveToFirst();
                            try {
                                imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                            } catch (Exception e) {
                                e.printStackTrace();
//                                myToast(getString(R.string.app_error));
                            } finally {
                                if (cursor != null)
                                    cursor.close();
                            }
                        }

                    } else {
                        imagePath = uri.getPath();
                    }
                    break;
            }
            if (imagePath != null) {
                MyLogger.i(">>>>>>>>>>获取到的图片路径1：" + imagePath);
//                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                //                imageView1.setImageBitmap(bitmap);
                listFileNames = new ArrayList<>();
                listFileNames.add("pic");
                Uri uri1 = Uri.parse("");
                uri1 = Uri.fromFile(new File(imagePath));
                File file1 = new File(FileUtil.getPath(this, uri1));
                listFiles = new ArrayList<>();

                File newFile = null;
                try {
                    newFile = new Compressor(this).compressToFile(file1);
                    listFiles.add(newFile);
                    MyLogger.i(">>>>>选择图片结果>>>>>>>>>" + listFileNames.toString() + ">>>>>>" + listFiles.toString());
                    String[] filenames = new String[]{};
                    File[] files = new File[]{};
                    for (int i = 0; i < listFiles.size(); i++) {
                        filenames = listFileNames.toArray(new String[i]);
                        files = listFiles.toArray(new File[i]);
                    }
                    this.showProgress(true, getString(R.string.app_loading1));
                    params.put("content", editText.getText().toString().trim());
                    params.put("token", localUserInfo.getToken());
                    params.put("software_version", CommonUtil.getVersionName(OnlineServiceActivity.this));
                    params.put("facility", getString(R.string.onlineservice_system1) + CommonUtil.getDeviceBrand()
                            + getString(R.string.onlineservice_system2) + CommonUtil.getSystemModel()
                            + getString(R.string.onlineservice_system3) + CommonUtil.getSystemVersion() +
                            getString(R.string.onlineservice_system4) + CommonUtil.getVersionName(OnlineServiceActivity.this));

                    RequestAddMessage(filenames, files, params);//创建留言
                } catch (IOException e) {
                    e.printStackTrace();
                    myToast(getString(R.string.app_imgerr));
                }
            }
        }

    }


}
