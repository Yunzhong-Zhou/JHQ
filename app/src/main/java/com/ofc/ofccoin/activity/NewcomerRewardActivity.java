package com.ofc.ofccoin.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ofc.ofccoin.R;
import com.ofc.ofccoin.base.BaseActivity;
import com.ofc.ofccoin.model.NewcomerRewardModel;
import com.ofc.ofccoin.net.OkHttpClientManager;
import com.ofc.ofccoin.net.URLs;
import com.ofc.ofccoin.utils.CommonUtil;
import com.ofc.ofccoin.utils.MyLogger;
import com.ofc.ofccoin.view.calendarview.widget.CalendarView;
import com.ofc.ofccoin.view.calendarview.widget.WeekView;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by zyz on 2019-10-07.
 * 新人领取
 */
public class NewcomerRewardActivity extends BaseActivity {
    TextView textView1,textView2;

    int status = 1;
    TextView tv_time, tv_qiandao;
    WeekView weekView;
    CalendarView mCalendarView;
    List<String> dates_y = new ArrayList<>();
    List<String> dates_n = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcomerreward);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
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


        tv_qiandao = findViewByID_My(R.id.tv_qiandao);
        tv_time = findViewByID_My(R.id.tv_time);

        weekView = findViewByID_My(R.id.weekView);
        mCalendarView = findViewByID_My(R.id.calendarView);
        // 指定显示的日期, 如当前月的下个月
        Calendar calendar1 = mCalendarView.getCalendar();
        calendar1.add(Calendar.MONTH, 0);
        mCalendarView.setCalendar(calendar1);
        // 设置字体
        mCalendarView.setTypeface(Typeface.SERIF);
        // 设置是否能够改变日期状态
        mCalendarView.setChangeDateStatus(false);
        // 设置是否能够点击
        mCalendarView.setClickable(false);
        setCurDate();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_btn:
                //返回上一页
                finish();
                break;
            case R.id.tv_qiandao:
                //签到
                if (status == 1){
                    Calendar calendar = Calendar.getInstance(Locale.CHINA);
                    SimpleDateFormat sdf = new SimpleDateFormat(mCalendarView.getDateFormatPattern(), Locale.CHINA);
                    sdf.format(calendar.getTime());
                    MyLogger.i(">>>>>>>" + sdf.format(calendar.getTime()));//当前日期
                    dates_y.add(sdf.format(calendar.getTime()));
                    // 设置已选的日期
                    mCalendarView.setSelectDate(dates_y);

                    tv_qiandao.setClickable(false);
                    showProgress(true, "签到中...");
                    HashMap<String, String> params = new HashMap<>();
                    params.put("token", localUserInfo.getToken());
                    requestAdd(params);
                }


                break;
            case R.id.iv_left:
                //上一个月
                mCalendarView.lastMonth();
                setCurDate();
                break;
            case R.id.iv_right:
                //下一个月
                mCalendarView.nextMonth();
                setCurDate();
                break;

        }
    }
    private void requestAdd(HashMap<String, String> params) {
        OkHttpClientManager.postAsyn(this, URLs.NewcomerReward, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
                tv_qiandao.setClickable(true);
                requestServer();
            }

            @Override
            public void onResponse(final String response) {
                tv_qiandao.setClickable(true);
                hideProgress();
                requestServer();
            }
        }, false);
    }
    private void request(String string) {
        OkHttpClientManager.getAsyn(NewcomerRewardActivity.this, URLs.NewcomerReward + string, new OkHttpClientManager.ResultCallback<NewcomerRewardModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(NewcomerRewardModel response) {
                showContentPage();
                hideProgress();
                MyLogger.i(">>>>>>>>>新人红包" + response);
                textView1.setText(response.getToday_sign_money());//今日
                textView2.setText(response.getAmount_sign_money());//全部

                //日历
                dates_n.clear();
                dates_y.clear();
                for (int i = 0; i < response.getSign_list().size(); i++) {
                    if (response.getSign_list().get(i).getStatus() == 1) {
                        //未签到
                        dates_n.add(response.getSign_list().get(i).getDate());//添加选中日期数据
                    } else {
                        //已签到
                        dates_y.add(response.getSign_list().get(i).getDate());//添加选中日期数据
                    }
                    //获取最后一天状态
                    if (i == (response.getSign_list().size() - 1)) {
                        status = response.getSign_list().get(i).getStatus();
                    }
                }
                MyLogger.i(">>>>>>>>>" + status);
                if (status == 1){
                    tv_qiandao.setClickable(true);
                    tv_qiandao.setBackgroundResource(R.mipmap.bg_newcomerreward2);
                }else {
                    tv_qiandao.setClickable(false);
                    tv_qiandao.setBackgroundResource(R.mipmap.bg_newcomerreward2_0);
                }

                mCalendarView.setSelectDate2(dates_n);
                mCalendarView.setSelectDayBackground2(getResources().getDrawable(R.drawable.yuanxing_huise));
//                        mCalendarView.setSelectTextColor(getResources().getColor(R.color.white));
                mCalendarView.setSelectDate(dates_y);
                mCalendarView.setSelectDayBackground(getResources().getDrawable(R.drawable.yuanxing_hongse));
//                        mCalendarView.setSelectTextColor(getResources().getColor(R.color.white));
            }
        });
    }


    @Override
    protected void initData() {
        requestServer();
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();

        showProgress(true, getString(R.string.app_loading2));
        request("?token=" + localUserInfo.getToken());
    }

    @Override
    protected void updateView() {
//        titleView.setTitle("领取新人红包");
        titleView.setVisibility(View.GONE);
    }

    //显示月份
    private void setCurDate() {
        tv_time.setText(mCalendarView.getYear() + "年" + (mCalendarView.getMonth() + 1) + "月");
    }
}
