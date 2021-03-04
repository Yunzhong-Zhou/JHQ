package com.filter.filter.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.filter.filter.R;
import com.filter.filter.base.BaseActivity;
import com.filter.filter.model.ContractModel;
import com.filter.filter.net.OkHttpClientManager;
import com.filter.filter.net.URLs;
import com.filter.filter.utils.CommonUtil;
import com.filter.filter.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mr.Z on 2020/12/15.
 * 合同
 */
public class ContractActivity extends BaseActivity {
    ContractModel model;
    //筛选
    private LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout_1, linearLayout_2, linearLayout_3;
    private TextView textView1, textView2, textView3, tv_moeny;
    private View view1, view2, view3;
    /**
     * 主体信息
     */
    ImageView iv_geren, iv_qiye;
    TextView tv_title_1, tv_num_1;
    EditText editText1_1, editText2_1, editText3_1, editText4_1, editText5_1, editText6_1;
    CityConfig cityConfig = null;
    //开户行控件 申明对象
    CityPickerView mPicker = new CityPickerView();
    String address_temp = "", type1 = "1", title = "", number = "", name = "", mobile = "", addr = "";
    /**
     * 电子合同
     */
    EditText editText1_2, editText2_2, editText3_2;
    /**
     * 申请开票
     */
    TextView tv_title_3, tv_num_3;
    ImageView iv_dianzi, iv_zhizhi;
    EditText editText1_3, editText2_3, editText3_3, editText4_3, editText5_3, editText6_3, editText7_3;
    String cny_money = "", type3 = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);

    }


    @Override
    protected void onResume() {
        super.onResume();
        requestServer();//获取数据
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                String string = "?token=" + localUserInfo.getToken();
                Request(string);
            }

            @Override
            public void onLoadmore() {
            }
        });
        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout_1 = findViewByID_My(R.id.linearLayout_1);
        linearLayout_2 = findViewByID_My(R.id.linearLayout_2);
        linearLayout_3 = findViewByID_My(R.id.linearLayout_3);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);
        tv_moeny = findViewByID_My(R.id.tv_moeny);

        /**
         *  主体信息
         */
        iv_geren = findViewByID_My(R.id.iv_geren);
        iv_qiye = findViewByID_My(R.id.iv_qiye);
        tv_title_1 = findViewByID_My(R.id.tv_title_1);
        tv_num_1 = findViewByID_My(R.id.tv_num_1);
        editText1_1 = findViewByID_My(R.id.editText1_1);
        editText2_1 = findViewByID_My(R.id.editText2_1);
        editText3_1 = findViewByID_My(R.id.editText3_1);
        editText4_1 = findViewByID_My(R.id.editText4_1);
        editText5_1 = findViewByID_My(R.id.editText5_1);
        editText6_1 = findViewByID_My(R.id.editText6_1);

        //预先加载仿iOS滚轮实现的全部数据
        mPicker.init(this);
        cityConfig = new CityConfig.Builder()
                .title(getString(R.string.contract_h17))//标题
                .titleTextSize(17)//标题文字大小
                .titleTextColor("#585858")//标题文字颜  色
                .titleBackgroundColor("#eaeaea")//标题栏背景色
                .confirTextColor("#02B399")//确认按钮文字颜色
                .confirmText(getString(R.string.app_confirm))//确认按钮文字
                .confirmTextSize(15)//确认按钮文字大小
                .cancelTextColor("#02B399")//取消按钮文字颜色
                .cancelText(getString(R.string.app_cancel))//取消按钮文字
                .cancelTextSize(15)//取消按钮文字大小
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
                editText5_1.setText(province.getName().toString() +
                        city.getName().toString() +
                        district.getName().toString());
               /* address_temp = province.getName().toString() + "#" +
                        city.getName().toString() + "#" +
                        district.getName().toString();*/
            }

            @Override
            public void onCancel() {
//                ToastUtils.showLongToast(this, "已取消");
            }
        });

        /**
         * 电子合同
         */
        editText1_2 = findViewByID_My(R.id.editText1_2);
        editText2_2 = findViewByID_My(R.id.editText2_2);
        editText3_2 = findViewByID_My(R.id.editText3_2);

        /**
         * 申请开票
         */
        iv_dianzi = findViewByID_My(R.id.iv_dianzi);
        iv_zhizhi = findViewByID_My(R.id.iv_zhizhi);
        tv_title_3 = findViewByID_My(R.id.tv_title_3);
        tv_num_3 = findViewByID_My(R.id.tv_num_3);
        editText1_3 = findViewByID_My(R.id.editText1_3);
        editText2_3 = findViewByID_My(R.id.editText2_3);
        editText3_3 = findViewByID_My(R.id.editText3_3);
        editText4_3 = findViewByID_My(R.id.editText4_3);
        editText5_3 = findViewByID_My(R.id.editText5_3);
        editText6_3 = findViewByID_My(R.id.editText6_3);
        editText7_3 = findViewByID_My(R.id.editText7_3);
        //输入监听
        editText3_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editText3_3.getText().toString().trim().equals("")) {
                    cny_money = editText3_3.getText().toString().trim();
                    MyLogger.i(">>>>>输入币数>>>>>" + cny_money);
                    //手续费 = 输入币数 * 手续费率 /100
//                    double service_money = Double.valueOf(model.getWithdrawal_service_charge());
//                    MyLogger.i(">>>>>手续费>>>>>" + service_money);
                    //实际到账 =  (输入币数 - 手续费)
//                    double real_money = (Double.valueOf(cny_money) * 0.13 / Double.valueOf(model.getUsdt_cny_price()));
                    double real_money = (Double.valueOf(cny_money) * 0.13);
                    MyLogger.i(">>>>>实际到账>>>>>" + real_money);

                    editText4_3.setText("¥ " + String.format("%.2f", real_money));//实际到账

                } else {
                    editText4_3.setText("¥ 0");//实际到账
                }
            }
        });

    }

    @Override
    protected void initData() {

    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(ContractActivity.this, URLs.Contract + string,
                new OkHttpClientManager.ResultCallback<ContractModel>() {
                    @Override
                    public void onError(Request request, String info, Exception e) {
                        showErrorPage();
                        hideProgress();
                        if (!info.equals("")) {
                            showToast(info);
                        }
                    }

                    @Override
                    public void onResponse(ContractModel response) {
                        showContentPage();
                        hideProgress();
                        model = response;
                        tv_moeny.setText("¥" + response.getResidue_invoice_money());
                        if (!response.getType().equals("0")) {
                            editText1_1.setFocusable(false);
                            editText2_1.setFocusable(false);
                            editText3_1.setFocusable(false);
                            editText4_1.setFocusable(false);
//                            editText5_1.setFocusable(false);
                            editText6_1.setFocusable(false);

                            if (response.getType().equals("2")) {//公司
                                type1 = "2";
                                iv_geren.setVisibility(View.INVISIBLE);
                                iv_qiye.setVisibility(View.VISIBLE);

                                tv_title_1.setText(getString(R.string.contract_h27));
                                tv_num_1.setText(getString(R.string.contract_h28));
                                editText1_1.setHint(getResources().getString(R.string.contract_h36));
                                editText2_1.setHint(getResources().getString(R.string.contract_h37));

                                tv_title_3.setText(getString(R.string.contract_h27));
                                tv_num_3.setText(getString(R.string.contract_h28));
                                editText1_3.setHint(getResources().getString(R.string.contract_h36));
                                editText2_3.setHint(getResources().getString(R.string.contract_h37));

                            } else {
                                type1 = "1";//类型（1.个人 2.企业）
                                iv_geren.setVisibility(View.VISIBLE);
                                iv_qiye.setVisibility(View.INVISIBLE);

                                tv_title_1.setText(getString(R.string.contract_h10));
                                tv_num_1.setText(getString(R.string.contract_h12));
                                editText1_1.setHint(getResources().getString(R.string.contract_h11));
                                editText2_1.setHint(getResources().getString(R.string.contract_h13));

                                tv_title_3.setText(getString(R.string.contract_h10));
                                tv_num_3.setText(getString(R.string.contract_h12));
                                editText1_3.setHint(getResources().getString(R.string.contract_h11));
                                editText2_3.setHint(getResources().getString(R.string.contract_h13));
                            }
                            /**
                             *  主体信息
                             */
                            editText1_1.setText(response.getTitle());//主体姓名
                            editText2_1.setText(response.getNumber());//身份证号
                            editText3_1.setText(response.getName());//收件姓名
                            editText4_1.setText(response.getMobile());//手机号
                            editText5_1.setText(response.getAddr());//省市县
                     /*//显示省市县
                    String[] strings = response.getMember().getBank_address().split("#");
                    if (strings.length > 2) {
                        textView2.setText(strings[0] + strings[1] + strings[2]);
//                    editText7.setText(strings[2]);
                        address_temp = strings[0] + "#" + strings[1] + "#" + strings[2];
                    } else if (strings.length == 2) {
                        textView2.setText(strings[0] + strings[1]);
//                    editText7.setText("");
                        address_temp = strings[0] + "#" + strings[1];
                    }*/
                            editText6_1.setText(response.getAddr());//详细地址

                            /**
                             * 电子合同
                             */
                            editText1_2.setText(response.getName());//姓名
                            editText2_2.setText(response.getMobile());//手机号
                            editText3_2.setText(response.getAddr());//详细地址

                            /**
                             * 申请开票
                             */
                            editText1_3.setText(response.getTitle());//主体姓名
                            editText2_3.setText(response.getNumber());//身份证号
                            editText5_3.setText(response.getName());//姓名
                            editText6_3.setText(response.getMobile());//手机号
                            editText7_3.setText(response.getAddr());//详细地址
                        } else {
                            editText1_1.setFocusable(true);
                            editText2_1.setFocusable(true);
                            editText3_1.setFocusable(true);
                            editText4_1.setFocusable(true);
//                            editText5_1.setFocusable(true);
                            editText6_1.setFocusable(true);
                        }

                    }
                });

    }

    @Override
    public void requestServer() {
        super.requestServer();
        this.showLoadingPage();
        String string = "?token=" + localUserInfo.getToken();
        Request(string);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.linearLayout:
                //开票记录
                CommonUtil.gotoActivity(ContractActivity.this, ContractListActivity.class);
                break;
            /**
             * 主体信息
             */
            case R.id.rl_geren:
                //个人
                if (model.getType().equals("0")) {
                    type1 = "1";//类型（1.个人 2.企业）
                    iv_geren.setVisibility(View.VISIBLE);
                    iv_qiye.setVisibility(View.INVISIBLE);

                    tv_title_1.setText(getString(R.string.contract_h10));
                    tv_num_1.setText(getString(R.string.contract_h12));
                    editText1_1.setHint(getResources().getString(R.string.contract_h11));
                    editText2_1.setHint(getResources().getString(R.string.contract_h13));

                    tv_title_3.setText(getString(R.string.contract_h10));
                    tv_num_3.setText(getString(R.string.contract_h12));
                    editText1_3.setHint(getResources().getString(R.string.contract_h11));
                    editText2_3.setHint(getResources().getString(R.string.contract_h13));
                }

                break;
            case R.id.rl_qiye:
                //企业
                if (model.getType().equals("0")) {
                    type1 = "2";
                    iv_geren.setVisibility(View.INVISIBLE);
                    iv_qiye.setVisibility(View.VISIBLE);

                    tv_title_1.setText(getString(R.string.contract_h27));
                    tv_num_1.setText(getString(R.string.contract_h28));
                    editText1_1.setHint(getResources().getString(R.string.contract_h36));
                    editText2_1.setHint(getResources().getString(R.string.contract_h37));

                    tv_title_3.setText(getString(R.string.contract_h27));
                    tv_num_3.setText(getString(R.string.contract_h28));
                    editText1_3.setHint(getResources().getString(R.string.contract_h36));
                    editText2_3.setHint(getResources().getString(R.string.contract_h37));
                }
                break;
            case R.id.editText5_1:
                //选择地址
                if (model.getType().equals("0")) {
                    mPicker.showCityPicker();
                }
                break;
            case R.id.tv_confirm_1:
                if (model.getType().equals("0")) {
                    if (match1()) {
                        showToast(getString(R.string.contract_h45)
                                , getString(R.string.app_confirm), getString(R.string.app_cancel),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        showProgress(true, getString(R.string.app_loading1));
                                        HashMap<String, String> params = new HashMap<>();
                                        params.put("type", type1);
                                        params.put("title", title);
                                        params.put("number", number);
                                        params.put("name", name);
                                        params.put("mobile", mobile);
                                        params.put("addr", address_temp + addr);
                                        params.put("token", localUserInfo.getToken());
                                        RequestUpData(params);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();

                                    }
                                });
                    }
                } else {
                    myToast(getString(R.string.contract_h47));
                }
                break;

            /**
             * 电子合同
             */
            case R.id.tv_dianzi:
                //查阅电子合同
                if (!model.getType().equals("0")) {
                    if (!model.getUrl().equals("")){
                        Bundle bundle = new Bundle();
                        bundle.putString("url", model.getUrl());
                        CommonUtil.gotoActivityWithData(ContractActivity.this, WebContentActivity1.class, bundle, false);
                    }else {
                        myToast(getString(R.string.contract_h53));
                    }

                } else {
                    myToast(getString(R.string.contract_h46));
                }
                break;
            case R.id.tv_zhizhi:
                //申请纸质合同
                if (!model.getType().equals("0")) {
                    showToast(getString(R.string.contract_h51)
                            , getString(R.string.app_confirm), getString(R.string.app_cancel),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    showProgress(true, getString(R.string.app_loading1));
                                    String string = "?token=" + localUserInfo.getToken()
                                            + "&id=" + model.getId();
                                    RequestUpData2(string);
                                    /*HashMap<String, String> params = new HashMap<>();
                                    params.put("id", model.getId());
                                    params.put("token", localUserInfo.getToken());
                                    RequestUpData2(params);*/
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();

                                }
                            });

                } else {
                    myToast(getString(R.string.contract_h46));
                }
                break;
            /**
             * 申请开票
             */
            case R.id.rl_dianzi:
                //电子
                type3 = "1";
                iv_dianzi.setVisibility(View.VISIBLE);
                iv_zhizhi.setVisibility(View.INVISIBLE);
                break;
            case R.id.rl_zhizhi:
                //纸质
                type3 = "2";
                iv_dianzi.setVisibility(View.INVISIBLE);
                iv_zhizhi.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_confirm_3:
                //申请开票
                if (match3()) {
                    showToast(getString(R.string.contract_h48)
                            , getString(R.string.app_confirm), getString(R.string.app_cancel),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    showProgress(true, getString(R.string.app_loading1));
                                    HashMap<String, String> params = new HashMap<>();
                                    params.put("type", type3);
                                    params.put("money", cny_money);
                                    params.put("token", localUserInfo.getToken());
                                    params.put("contract_id", model.getId());
                                    RequestUpData3(params);
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();

                                }
                            });
                }
                break;

            /**
             * tab切换
             */
            case R.id.linearLayout1:
                textView1.setTextColor(getResources().getColor(R.color.green));
                textView2.setTextColor(getResources().getColor(R.color.black1));
                textView3.setTextColor(getResources().getColor(R.color.black1));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
                linearLayout_1.setVisibility(View.VISIBLE);
                linearLayout_2.setVisibility(View.GONE);
                linearLayout_3.setVisibility(View.GONE);
                break;
            case R.id.linearLayout2:
                textView1.setTextColor(getResources().getColor(R.color.black1));
                textView2.setTextColor(getResources().getColor(R.color.green));
                textView3.setTextColor(getResources().getColor(R.color.black1));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.INVISIBLE);
                linearLayout_1.setVisibility(View.GONE);
                linearLayout_2.setVisibility(View.VISIBLE);
                linearLayout_3.setVisibility(View.GONE);
                break;
            case R.id.linearLayout3:
                textView1.setTextColor(getResources().getColor(R.color.black1));
                textView2.setTextColor(getResources().getColor(R.color.black1));
                textView3.setTextColor(getResources().getColor(R.color.green));
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.VISIBLE);
                linearLayout_1.setVisibility(View.GONE);
                linearLayout_2.setVisibility(View.GONE);
                linearLayout_3.setVisibility(View.VISIBLE);
                break;

        }
    }

    private boolean match1() {
        title = editText1_1.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            if (type1.equals("1")) {
                myToast(getString(R.string.contract_h11));
            } else {
                myToast(getString(R.string.contract_h36));
            }

            return false;
        }
        number = editText2_1.getText().toString().trim();
        if (TextUtils.isEmpty(number)) {
            if (type1.equals("1")) {
                myToast(getString(R.string.contract_h13));
            } else {
                myToast(getString(R.string.contract_h37));
            }

            return false;
        }
        name = editText3_1.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            myToast(getString(R.string.contract_h11));
            return false;
        }
        mobile = editText4_1.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            myToast(getString(R.string.contract_h21));
            return false;
        }
        address_temp = editText5_1.getText().toString().trim();
        if (TextUtils.isEmpty(address_temp)) {
            myToast(getString(R.string.contract_h17));
            return false;
        }

        addr = editText6_1.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            myToast(getString(R.string.contract_h19));
            return false;
        }

        return true;
    }

    private boolean match3() {
        if (model.getType().equals("0")) {
            myToast(getString(R.string.contract_h46));
            return false;
        }
        cny_money = editText3_3.getText().toString().trim();
        if (TextUtils.isEmpty(cny_money)) {
            myToast(getString(R.string.contract_h31));
            return false;
        }
        /*cny_money = editText4_3.getText().toString().trim();
        if (TextUtils.isEmpty(cny_money)) {
            myToast(getString(R.string.contract_h38));
            return false;
        }*/

        return true;
    }

    private void RequestUpData(Map<String, String> params) {
        OkHttpClientManager.postAsyn(ContractActivity.this, URLs.ContractCreate, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
//                requestServer();
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                myToast(getString(R.string.contract_h49));
                requestServer();
            }
        }, true);
    }

    private void RequestUpData2(String params) {
        OkHttpClientManager.getAsyn(ContractActivity.this, URLs.ContractShenQing + params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
//                requestServer();
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
                myToast(getString(R.string.contract_h52));
                requestServer();
            }
        });
    }

    private void RequestUpData3(Map<String, String> params) {
        OkHttpClientManager.postAsyn(ContractActivity.this, URLs.InvoiceCreate, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
//                requestServer();
            }

            @Override
            public void onResponse(String response) {
                hideProgress();
//                requestServer();
                editText3_3.setText("");
                editText4_3.setText("¥ 0");//实际到账
                myToast(getString(R.string.contract_h50));
//                requestServer();
                CommonUtil.gotoActivity(ContractActivity.this, ContractListActivity.class);
            }
        }, true);
    }

    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
