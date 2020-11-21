package com.ofc.ofc.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.ofc.ofc.R;
import com.ofc.ofc.adapter.Pop_ListAdapter;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.model.CollectionSettingModel;
import com.ofc.ofc.model.SetAddressModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.ofc.ofc.view.FixedPopupWindow;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by zyz on 2017/9/16.
 * 银行卡设置
 */

public class BankCardSettingActivity extends BaseActivity {
    LinearLayout linearLayout_wu, linearLayout_you;

    CollectionSettingModel model;
    //    String qk = "";
    EditText editText1, editText2, editText3, editText4;
    TextView textView1, textView2, textView3, textView4, textView5, tv_banknum, tv_kaihuname, tv_bankname;
    private TimeCount time;
    String bank_address = "", bank_card_account = "", bank_card_proceeds_name = "", bank_title = "", code = "", password = "";

    List<CollectionSettingModel.BankListBean> banklist = new ArrayList<>();
    int i1 = 0;

    CityConfig cityConfig = null;
    //开户行控件 申明对象
    CityPickerView mPicker = new CityPickerView();
    String bank_address_temp = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankcardsetting);

        //预先加载仿iOS滚轮实现的全部数据
        mPicker.init(this);
        cityConfig = new CityConfig.Builder()
                .title(getString(R.string.takecash_h27))//标题
                .titleTextSize(18)//标题文字大小
                .titleTextColor("#585858")//标题文字颜  色
                .titleBackgroundColor("#eaeaea")//标题栏背景色
                .confirTextColor("#AF8848")//确认按钮文字颜色
                .confirmText(getString(R.string.app_confirm))//确认按钮文字
                .confirmTextSize(16)//确认按钮文字大小
                .cancelTextColor("#AF8848")//取消按钮文字颜色
                .cancelText(getString(R.string.app_cancel))//取消按钮文字
                .cancelTextSize(16)//取消按钮文字大小
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)//显示类，只显示省份一级，显示省市两级还是显示省市区三级
                .showBackground(true)//是否显示半透明背景
                .visibleItemsCount(7)//显示item的数量
                .province("北京市")//默认显示的省份
                .city("北京市")//默认显示省份下面的城市
                .district("朝阳区")//默认显示省市下面的区县数据
                .provinceCyclic(true)//省份滚轮是否可以循环滚动
                .cityCyclic(true)//城市滚轮是否可以循环滚动
                .districtCyclic(true)//区县滚轮是否循环滚动
                .setCustomItemLayout(R.layout.item_city)//自定义item的布局
                .setCustomItemTextViewId(R.id.textView1)//自定义item布局里面的textViewid
                .drawShadows(true)//滚轮不显示模糊效果
                .setLineColor("#80CDCDCE")//中间横线的颜色
                .setLineHeigh(1)//中间横线的高度
                .setShowGAT(true)//是否显示港澳台数据，默认不显示
                .build();

        //设置自定义的属性配置
        mPicker.setConfig(cityConfig);

        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                //省份province//城市city//地区district
                textView2.setText(province.getName().toString() +
                        city.getName().toString() +
                        district.getName().toString());
                bank_address_temp = province.getName().toString() + "#" +
                        city.getName().toString() + "#" +
                        district.getName().toString();
            }

            @Override
            public void onCancel() {
//                ToastUtils.showLongToast(this, "已取消");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgress(true, getString(R.string.app_loading2));
        //获取收款设置
        RequestGetCollection("?token=" + localUserInfo.getToken());
    }

    @Override
    protected void initView() {
        linearLayout_wu = findViewByID_My(R.id.linearLayout_wu);
        linearLayout_you = findViewByID_My(R.id.linearLayout_you);

        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        editText3 = findViewByID_My(R.id.editText3);
        editText4 = findViewByID_My(R.id.editText4);


        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView3.setText("+" + localUserInfo.getMobile_State_Code() + "  " + localUserInfo.getPhonenumber());
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);

        tv_banknum = findViewByID_My(R.id.tv_banknum);
        tv_kaihuname = findViewByID_My(R.id.tv_kaihuname);
        tv_bankname = findViewByID_My(R.id.tv_bankname);

    }

    @Override
    protected void initData() {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象

//        qk = getIntent().getStringExtra("qk");

    }

    //获取收款设置
    private void RequestGetCollection(String string) {
        OkHttpClientManager.getAsyn(BankCardSettingActivity.this, URLs.Collection + string, new OkHttpClientManager.ResultCallback<CollectionSettingModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(CollectionSettingModel response) {
                hideProgress();
                MyLogger.i(">>>>>>>>银行卡设置" + response);
                model = response;

                if (response.getMember().getTrade_password().equals("")) {
                    showToast(getString(R.string.address_h25),
                            getString(R.string.password_h5), getString(R.string.password_h6),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    CommonUtil.gotoActivity(BankCardSettingActivity.this, SetTransactionPasswordActivity.class, false);
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                }
                banklist = response.getBank_list();
                if (!response.getMember().getBank_card_account().equals("")) {
                    linearLayout_wu.setVisibility(View.GONE);
                    linearLayout_you.setVisibility(View.VISIBLE);
                    textView1.setText(response.getMember().getBank_title());//开户行
                    editText1.setText(response.getMember().getBank_card_proceeds_name());//开户名
                    editText2.setText(response.getMember().getBank_card_account());//银行卡号

                    tv_banknum.setText(response.getMember().getBank_card_account());//银行卡号
                    tv_kaihuname.setText(response.getMember().getBank_title());//开户行
                    tv_bankname.setText(response.getMember().getBank_card_proceeds_name());//开户名
                } else {
                    linearLayout_wu.setVisibility(View.VISIBLE);
                    linearLayout_you.setVisibility(View.GONE);
                }


                //显示开户行
                /*String[] strings = response.getMember().getBank_address().split("#");
                if (strings.length > 2) {
                    textView2.setText(strings[0] + strings[1] + strings[2]);
//                    editText7.setText(strings[2]);
                    bank_address_temp = strings[0] + "#" + strings[1] + "#" + strings[2];
                } else if (strings.length == 2) {
                    textView2.setText(strings[0] + strings[1]);
//                    editText7.setText("");
                    bank_address_temp = strings[0] + "#" + strings[1];
                }*/

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView1:
                //选择银行
                MyLogger.i(">>>>>>");
                showPopupWindow1(textView1);
                break;
            case R.id.textView4:
                //获取验证码
                showProgress(false, getString(R.string.app_sendcode_hint1));
                textView4.setClickable(false);
                HashMap<String, String> params1 = new HashMap<>();
                params1.put("mobile", localUserInfo.getPhonenumber());
                params1.put("type", "7");
                params1.put("mobile_state_code", localUserInfo.getMobile_State_Code());
                RequestCode(params1);//获取验证码
                break;
            case R.id.textView5:
                //提交
                if (match()) {
                    showProgress(false, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
//                    params.put("qk", qk);
                    params.put("bank_card_account", bank_card_account);//银行卡账号
                    params.put("bank_card_proceeds_name", bank_card_proceeds_name);//银行卡收款人姓名
                    params.put("bank_title", bank_title);//银行名称
                    params.put("code", code);//手机验证码
                    params.put("token", localUserInfo.getToken());
                    params.put("trade_password", password);//交易密码（不能小于6位数）
                    params.put("bank_address", bank_address + "");//开户行
                    RequestCollectionSetting(params);//
                }
                break;
            case R.id.textView2:
                //开户行地址
                mPicker.showCityPicker();
                break;

            case R.id.iv_edit:
                //编辑银行卡
                linearLayout_wu.setVisibility(View.VISIBLE);
                linearLayout_you.setVisibility(View.GONE);
                break;
        }
    }

    //发送验证码
    private void RequestCode(HashMap<String, String> params) {
        OkHttpClientManager.postAsyn(BankCardSettingActivity.this, URLs.Collection_code, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                textView2.setClickable(true);
                if (!info.equals("")) {
                    showToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                time.start();
                MyLogger.i(">>>>>>>>>发送验证码" + response);
                myToast(getString(R.string.app_sendcode_hint));
            }
        }, true);

    }

    //收款设置
    private void RequestCollectionSetting(HashMap<String, String> params) {
        OkHttpClientManager.postAsyn(BankCardSettingActivity.this, URLs.Collection, params, new OkHttpClientManager.ResultCallback<SetAddressModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    if (!info.equals("")) {
                        if (info.contains(getString(R.string.password_h1))) {
                            showToast(getString(R.string.password_h2),
                                    getString(R.string.password_h5), getString(R.string.password_h6),
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog.dismiss();
                                            CommonUtil.gotoActivity(BankCardSettingActivity.this, SetTransactionPasswordActivity.class, false);
                                        }
                                    }, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog.dismiss();
                                        }
                                    });
                        } else {
                            showToast(info);
                        }
                    }
                }
            }

            @Override
            public void onResponse(SetAddressModel response) {
                hideProgress();
                MyLogger.i(">>>>>>>>>收款设置" + response);
                if (response != null && response.getCode() == 1) {
                    showToast(getString(R.string.password_h2),
                            getString(R.string.password_h5), getString(R.string.password_h6),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    CommonUtil.gotoActivity(BankCardSettingActivity.this, SetTransactionPasswordActivity.class, false);
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                } else {
                    myToast(getString(R.string.qianbao_h148));
                    finish();
                    /*Bundle bundle = new Bundle();
                    bundle.putInt("type", 1);
                    CommonUtil.gotoActivityWithData(BankCardSettingActivity.this, TakeCashActivity.class, bundle, true);*/
                }

            }
        }, true);

    }

    private boolean match() {
//        bank_title = editText1.getText().toString().trim();
        bank_title = textView1.getText().toString().trim();
        if (TextUtils.isEmpty(bank_title)) {
            myToast(getString(R.string.qianbao_h144));
            return false;
        }
        bank_card_proceeds_name = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(bank_card_proceeds_name)) {
            myToast(getString(R.string.qianbao_h145));
            return false;
        }
        bank_card_account = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(bank_card_account)) {
            myToast(getString(R.string.qianbao_h146));
            return false;
        }

        code = editText3.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            myToast(getString(R.string.settransactionpassword_h5));
            return false;
        }
        password = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            myToast(getString(R.string.settransactionpassword_h7));
            return false;
        }

       /* if (!bank_address_temp.equals("")) {
            bank_address = bank_address_temp;
            *//*if (editText7.getText().toString().trim().equals("")) {
                bank_address = bank_address_temp + "#" + "支行";
            } else {
                bank_address = bank_address_temp + "#" + editText7.getText().toString().trim();
            }*//*
        } else {
//            bank_address = "";
            myToast(getString(R.string.takecash_h27));
            return false;
        }*/

        return true;
    }


    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.qianbao_h140));
    }

    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            textView4.setText(getString(R.string.app_reacquirecode));
            textView4.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            textView4.setClickable(false);
            textView4.setText(millisUntilFinished / 1000 + getString(R.string.app_codethen));
        }
    }


    private void showPopupWindow1(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(BankCardSettingActivity.this).inflate(
                R.layout.pop_list_1, null);
        final FixedPopupWindow popupWindow = new FixedPopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        contentView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = contentView.findViewById(R.id.pop_listView).getTop();
                int height1 = contentView.findViewById(R.id.pop_listView).getBottom();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        popupWindow.dismiss();
                    }
                    if (y > height1) {
                        popupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        // 设置按钮的点击事件
        ListView pop_listView = (ListView) contentView.findViewById(R.id.pop_listView);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < banklist.size(); i++) {
            list.add(banklist.get(i).getTitle());
        }
        final Pop_ListAdapter adapter = new Pop_ListAdapter(BankCardSettingActivity.this, list);
        adapter.setSelectItem(i1);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                i1 = i;
                adapter.setSelectItem(i);
                adapter.notifyDataSetChanged();

                bank_title = banklist.get(i).getTitle();
                textView1.setText(banklist.get(i).getTitle());
                popupWindow.dismiss();

            }
        });

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        ColorDrawable dw = new ColorDrawable(this.getResources().getColor(R.color.transparent));
        // 设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);
    }
}
