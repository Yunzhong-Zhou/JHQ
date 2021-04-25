package com.ghzk.ghzk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ghzk.ghzk.R;
import com.ghzk.ghzk.base.BaseActivity;
import com.ghzk.ghzk.utils.MyLogger;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

/**
 * Created by Mr.Z on 2021/4/25.
 * 查看pdf文件
 */
public class ShowPDFActivity extends BaseActivity implements DownloadFile.Listener {
    private RemotePDFViewPager remotePDFViewPager;
    private PDFPagerAdapter adapter;
    private RelativeLayout remotePdfRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showpdf);
    }

    @Override
    protected void initView() {
        remotePdfRoot = findViewByID_My(R.id.remote_pdf_root);
        remotePdfRoot.setVisibility(View.GONE);

    }

    @Override
    protected void initData() {
        String url = getIntent().getStringExtra("url");
        MyLogger.i(">>>>>>"+url);
        showProgress(true, getString(R.string.app_loading2));
        setDownloadListener(url);
    }

    @Override
    protected void updateView() {
        titleView.setTitle("查看PDF");
    }

    /*设置监听*/
    protected void setDownloadListener(String url) {
        final DownloadFile.Listener listener = this;
        remotePDFViewPager = new RemotePDFViewPager(this, url, listener);
        remotePDFViewPager.setId(R.id.pdfViewPager);
    }

    /*加载成功调用*/
    @Override
    public void onSuccess(String url, String destinationPath) {
        hideProgress();
        remotePdfRoot.setVisibility(View.VISIBLE);

        adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(adapter);
        updateLayout();
    }

    /*更新视图*/
    private void updateLayout() {
        remotePdfRoot.removeAllViewsInLayout();
        remotePdfRoot.addView(remotePDFViewPager, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    /*加载失败调用*/
    @Override
    public void onFailure(Exception e) {
        hideProgress();
        myToast("数据加载错误");
    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }
}
