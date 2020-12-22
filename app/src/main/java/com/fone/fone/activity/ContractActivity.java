package com.fone.fone.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.base.BaseActivity;
import com.fone.fone.model.ContractModel;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.liaoinstan.springview.widget.SpringView;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.squareup.okhttp.Request;

/**
 * Created by Mr.Z on 2020/12/15.
 * 合同
 */
public class ContractActivity extends BaseActivity {
    //筛选
    private LinearLayout linearLayout1, linearLayout2,linearLayout3,linearLayout_1, linearLayout_2,linearLayout_3;
    private TextView textView1, textView2,textView3,tv_moeny;
    private View view1, view2,view3;
    /**
     * 主体信息
     */
    ImageView iv_geren,iv_qiye;
    EditText editText1_1,editText2_1,editText3_1,editText4_1,editText5_1,editText6_1;
    CityConfig cityConfig = null;
    //开户行控件 申明对象
    CityPickerView mPicker = new CityPickerView();
    String address_temp = "",type="1",title="",number="",name="",mobile="",addr="";
    /**
     * 电子合同
     */
    EditText editText1_2,editText2_2,editText3_2;
    /**
     * 申请开票
     */
    ImageView iv_dianzi,iv_zhizhi;
    EditText editText1_3,editText2_3,editText3_3,editText4_3,editText5_3,editText6_3,editText7_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);
        findViewById(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(this), 0, 0);

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
                address_temp = province.getName().toString() + "#" +
                        city.getName().toString() + "#" +
                        district.getName().toString();
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
        editText1_3 = findViewByID_My(R.id.editText1_3);
        editText2_3 = findViewByID_My(R.id.editText2_3);
        editText3_3 = findViewByID_My(R.id.editText3_3);
        editText4_3 = findViewByID_My(R.id.editText4_3);
        editText5_3 = findViewByID_My(R.id.editText5_3);
        editText6_3 = findViewByID_My(R.id.editText6_3);
        editText7_3 = findViewByID_My(R.id.editText7_3);

    }

    @Override
    protected void initData() {
        requestServer();//获取数据
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
                    tv_moeny.setText("¥"+response.getResidue_invoice_cny_money());
                    /**
                     *  主体信息
                     */
                    editText1_1.setText(response.getTitle());//主体姓名
                    editText2_1.setText(response.getNumber());//身份证号
                    editText3_1.setText(response.getName());//收件姓名
                    editText4_1.setText(response.getMobile());//手机号
//                    editText5_1.setText(response.get);//省市县
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
                    editText5_3.setText(response.getName());//姓名
                    editText6_3.setText(response.getMobile());//手机号
                    editText7_3.setText(response.getAddr());//详细地址
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
        switch (v.getId()){
            case R.id.left_btn:
                finish();
                break;
            case R.id.linearLayout:
                //开票记录
                CommonUtil.gotoActivity(ContractActivity.this,ContractListActivity.class);
                break;
            /**
             * 主体信息
             */
            case R.id.rl_geren:
                //个人
                type = "1";//类型（1.个人 2.企业）
                iv_geren.setVisibility(View.VISIBLE);
                iv_qiye.setVisibility(View.INVISIBLE);
                break;
            case R.id.rl_qiye:
                //企业
                type="2";
                iv_geren.setVisibility(View.INVISIBLE);
                iv_qiye.setVisibility(View.VISIBLE);
                break;
            case R.id.editText5_1:
                //选择地址
                mPicker.showCityPicker();
                break;
            case R.id.tv_confirm_1:
                if (match1()){
                    showToast(getString(R.string.contract_h45)
                            , getString(R.string.app_confirm), getString(R.string.app_cancel),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();

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
             * 电子合同
             */
            case R.id.tv_dianzi:
                //电子合同

                break;
            case R.id.tv_zhizhi:
                //纸质合同

                break;
            /**
             * 申请开票
             */
            case R.id.rl_dianzi:
                //电子
                iv_dianzi.setVisibility(View.VISIBLE);
                iv_zhizhi.setVisibility(View.INVISIBLE);
                break;
            case R.id.rl_zhizhi:
                //纸质
                iv_dianzi.setVisibility(View.INVISIBLE);
                iv_zhizhi.setVisibility(View.VISIBLE);
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
            myToast(getString(R.string.contract_h11));
            return false;
        }
        number = editText2_1.getText().toString().trim();
        if (TextUtils.isEmpty(number)) {
            myToast(getString(R.string.contract_h13));
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

        addr = editText6_1.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            myToast(getString(R.string.contract_h19));
            return false;
        }
        if (!address_temp.equals("")) {
            addr = address_temp;
            /*if (editText7.getText().toString().trim().equals("")) {
                bank_address = address_temp + "#" + "支行";
            } else {
                bank_address = address_temp + "#" + editText7.getText().toString().trim();
            }*/
        } else {
//            bank_address = "";
            myToast(getString(R.string.takecash_h27));
            return false;
        }
        return true;
    }
    @Override
    protected void updateView() {
        titleView.setVisibility(View.GONE);
    }
}
