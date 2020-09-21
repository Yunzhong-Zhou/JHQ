package com.ofc.ofc.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.ofc.ofc.R;
import com.ofc.ofc.activity.OFCAccountDetailActivity;
import com.ofc.ofc.activity.MainActivity;
import com.ofc.ofc.activity.RechargeDetailActivity;
import com.ofc.ofc.activity.SelectAddressActivity;
import com.ofc.ofc.activity.SetAddressActivity;
import com.ofc.ofc.activity.SetTransactionPasswordActivity;
import com.ofc.ofc.base.BaseFragment;
import com.ofc.ofc.model.Fragment4Model;
import com.ofc.ofc.model.RechargeDetailModel;
import com.ofc.ofc.net.OkHttpClientManager;
import com.ofc.ofc.net.URLs;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.ofc.ofc.utils.ZxingUtils;
import com.squareup.okhttp.Request;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zyz on 2016/1/6.
 * 充值
 */
public class Fragment4_1 extends BaseFragment {
    LinearLayout linearLayout_1, linearLayout_2;
    //充值
    Fragment4Model model;
    int type = 1;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout_keyong;
    TextView textView1, textView2, textView_3, textView_dazhe_1, textView_dazhe_2, textView_dazhe_3;
    View view1, view2, view3;
    String input_money = "";
    TextView textView3, textView4, textView5, textView6, textView7, textView_addr, textView_moeny, textView_shouxufei;
    EditText editText1, editText2;
    LinearLayout linearLayout_addr, linearLayout_num, linearLayout_money, linearLayout_daozhang;

    //详情
    RechargeDetailModel detailModel;
    String id = "";
    ProgressBar detail_prograssBar;
    ImageView detail_imageView1, detail_imageView2, detail_imageView_addr, detail_imageView_fuzhi;
    TextView detail_textView_title, detail_textView, detail_textView1, detail_textView2, detail_textView3, detail_textView4,
            detail_textView5, detail_textView6, detail_textView7, detail_textView8,
            detail_textView9, detail_textView10, detail_textView11, detail_textView12, detail_textView13,
            detail_textView14, detail_textView15, detail_textView16, detail_textView17, detail_textView18, detail_textView_baocun;
    LinearLayout detail_linearLayout_addr, detail_linearLayout_bank, detail_linearLayout_shiji, detail_linearLayout_jiage;

    private static final int MSG_SUCCESS = 0;// 获取成功的标识
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {// 此方法在ui线程运行
            switch (msg.what) {
                case MSG_SUCCESS:
                    showToast(getString(R.string.zxing_h21));
                    break;

            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        /*if (MainActivity.item == 3) {
            requestServer();
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.item == 3) {
            requestServer();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if (MainActivity.item == 3) {
            requestServer();
        }*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 3) {
                    requestServer();
                }
            }
        }
    }

    @Override
    protected void initView(View view) {
        linearLayout_1 = findViewByID_My(R.id.linearLayout_1);
        linearLayout_2 = findViewByID_My(R.id.linearLayout_2);

        /**
         * *************************************充值************************************************
         * */
//        findViewByID_My(R.id.linearLayout).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        //刷新
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                String string = "?token=" + localUserInfo.getToken();
                request(string);
            }

            @Override
            public void onLoadmore() {

            }
        });
        linearLayout_addr = findViewByID_My(R.id.linearLayout_addr);
        textView_addr = findViewByID_My(R.id.textView_addr);
        linearLayout_num = findViewByID_My(R.id.linearLayout_num);
        linearLayout_money = findViewByID_My(R.id.linearLayout_money);
        textView_moeny = findViewByID_My(R.id.textView_moeny);
        linearLayout_daozhang = findViewByID_My(R.id.linearLayout_daozhang);
        linearLayout_keyong = findViewByID_My(R.id.linearLayout_keyong);

        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout_keyong.setOnClickListener(this);

        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView_3 = findViewByID_My(R.id.textView_3);
        textView_dazhe_1 = findViewByID_My(R.id.textView_dazhe_1);
        textView_dazhe_2 = findViewByID_My(R.id.textView_dazhe_2);
        textView_dazhe_3 = findViewByID_My(R.id.textView_dazhe_3);
        view1 = findViewByID_My(R.id.view1);
        view2 = findViewByID_My(R.id.view2);
        view3 = findViewByID_My(R.id.view3);


        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        textView_shouxufei = findViewByID_My(R.id.textView_shouxufei);
        textView7.setOnClickListener(this);

        //输入监听
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (type == 2) {
                    if (!editText2.getText().toString().trim().equals("")) {
                        input_money = editText2.getText().toString().trim();
                        if (Double.valueOf(input_money) >= 50) {
                            MyLogger.i(">>>>>输入币数>>>>>" + input_money);
                            //实际到账  =  个数 -50 *  汇率
                            double real_money = (Double.valueOf(input_money) - 50) * Double.valueOf(model.getAud_conver_usd());
                            MyLogger.i(">>>>>实际到账>>>>>" + real_money);

                            textView_moeny.setText(String.format("%.2f", real_money));
                        } else {
                            textView_moeny.setText("0");
                        }

                    } else {
                        textView_moeny.setText("0");
                    }
                }

            }
        });

        LinearLayout linearLayout = findViewByID_My(R.id.linearLayout);
        /*LinearLayout.LayoutParams sp_params = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        sp_params.height = CommonUtil.getScreenHeight(getActivity()) / 4;
        linearLayout.setLayoutParams(sp_params);*/

        //动态设置linearLayout的高度为屏幕高度的1/4
        ViewGroup.LayoutParams lp = linearLayout.getLayoutParams();
        lp.height = (int) CommonUtil.getScreenHeight(getActivity()) / 4;

        /**
         * *************************************详情************************************************
         * */
        detail_prograssBar = findViewByID_My(R.id.detail_prograssBar);
        detail_imageView1 = findViewByID_My(R.id.detail_imageView1);
        detail_imageView2 = findViewByID_My(R.id.detail_imageView2);
        detail_imageView_addr = findViewByID_My(R.id.detail_imageView_addr);
        detail_imageView_fuzhi = findViewByID_My(R.id.detail_imageView_fuzhi);
        detail_imageView_fuzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", detailModel.getTop_up().getWallet_addr());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                myToast(getString(R.string.recharge_h34));
            }
        });
        detail_textView_title = findViewByID_My(R.id.detail_textView_title);
        CommonUtil.setMargins(findViewByID_My(R.id.detail_textView_title), 0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
//        findViewByID_My(R.id.detail_textView_title).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        detail_textView = findViewByID_My(R.id.detail_textView);
        detail_textView1 = findViewByID_My(R.id.detail_textView1);
        detail_textView2 = findViewByID_My(R.id.detail_textView2);
        detail_textView3 = findViewByID_My(R.id.detail_textView3);
        detail_textView4 = findViewByID_My(R.id.detail_textView4);
        detail_textView5 = findViewByID_My(R.id.detail_textView5);
        detail_textView6 = findViewByID_My(R.id.detail_textView6);
        detail_textView7 = findViewByID_My(R.id.detail_textView7);
        detail_textView8 = findViewByID_My(R.id.detail_textView8);
        detail_textView9 = findViewByID_My(R.id.detail_textView9);
        detail_textView10 = findViewByID_My(R.id.detail_textView10);
        detail_textView11 = findViewByID_My(R.id.detail_textView11);
        detail_textView12 = findViewByID_My(R.id.detail_textView12);
        detail_textView13 = findViewByID_My(R.id.detail_textView13);
        detail_textView14 = findViewByID_My(R.id.detail_textView14);
        detail_textView15 = findViewByID_My(R.id.detail_textView15);
        detail_textView16 = findViewByID_My(R.id.detail_textView16);
        detail_textView17 = findViewByID_My(R.id.detail_textView17);
        detail_textView18 = findViewByID_My(R.id.detail_textView18);
        detail_textView_baocun = findViewByID_My(R.id.detail_textView_baocun);

        detail_linearLayout_addr = findViewByID_My(R.id.detail_linearLayout_addr);
        detail_linearLayout_bank = findViewByID_My(R.id.detail_linearLayout_bank);
        detail_linearLayout_shiji = findViewByID_My(R.id.detail_linearLayout_shiji);
        detail_linearLayout_jiage = findViewByID_My(R.id.detail_linearLayout_jiage);
        detail_textView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消充币
                if (detailModel.getTop_up().getType() == 1) {
                    showToast(getString(R.string.recharge_h29),
                            getString(R.string.app_yes),
                            getString(R.string.app_no),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //确定
                                    dialog.dismiss();
                                    showProgress(true, getString(R.string.app_loading1));
                                    requestCancel("?token=" + localUserInfo.getToken()
                                            + "&id=" + id);

                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //取消
                                    dialog.dismiss();
                                }
                            });
                } else {
                    showToast(getString(R.string.recharge_h25),
                            getString(R.string.app_yes),
                            getString(R.string.app_no),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //确定
                                    dialog.dismiss();
                                    showProgress(true, getString(R.string.app_loading1));
                                    requestCancel("?token=" + localUserInfo.getToken()
                                            + "&id=" + id);

                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //取消
                                    dialog.dismiss();
                                }
                            });
                }

            }
        });
        detail_textView_baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printScreen(detail_imageView_addr, "OFC_qrcode" + System.currentTimeMillis());
            }
        });

    }

    private void request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment4 + string, new OkHttpClientManager.ResultCallback<Fragment4Model>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                MainActivity.isOver = true;
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(Fragment4Model response) {
                MyLogger.i(">>>>>>>>>充值" + response);
                model = response;
                if (response.getId_1().equals("") && response.getId_2().equals("")) {
                    hideProgress();
                    linearLayout_1.setVisibility(View.VISIBLE);
                    linearLayout_2.setVisibility(View.GONE);
                    detail_textView_title.setVisibility(View.GONE);

                    textView3.setText(response.getUsable_money());//可用余币
                    textView5.setText(getString(R.string.fragment4_h6) + response.getUsdt_price());//可用余币
                    /*//首次充币
                    if (Double.valueOf(response.getPrincipal_money()) == 0) {
                        dialog.contentView(R.layout.dialog_firstrecharge)
                                .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT))
                                .animType(BaseDialog.AnimInType.CENTER)
                                .canceledOnTouchOutside(true)
                                .dimAmount(0.8f)
                                .show();
                        TextView textView1 = dialog.findViewById(R.id.textView1);
                        textView1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }*/
                    changeUI();
                    MainActivity.isOver = true;
                } else {
                    if (!response.getId_1().equals("")){
                        id = model.getId_1();
                    }
                    if (!response.getId_2().equals("")){
                        id = model.getId_2();
                    }
                    //加载充币详情
                    linearLayout_1.setVisibility(View.GONE);
                    linearLayout_2.setVisibility(View.VISIBLE);
                    detail_textView_title.setVisibility(View.VISIBLE);

                    //加载充值详情
                    requestDetail("?token=" + localUserInfo.getToken()
                            + "&id=" + id);
                }

            }
        });
    }

    private void requestDetail(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.RechargeDetail + string, new OkHttpClientManager.ResultCallback<RechargeDetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(RechargeDetailModel response) {
                MyLogger.i(">>>>>>>>>充值详情" + response);
                hideProgress();
                detailModel = response;
//                textView1.setText(getString(R.string.recharge_h11) + "(" + response.getTop_up().getMoney_type_title() + ")");//充值个数

                if (response.getTop_up().getType() == 1) {
                    //USDT
                    detail_imageView_addr.setVisibility(View.VISIBLE);
                    Bitmap mBitmap = ZxingUtils.createQRCodeBitmap(response.getTop_up().getWallet_addr(),
                            480, 480);
                    detail_imageView_addr.setImageBitmap(mBitmap);

                    detail_linearLayout_addr.setVisibility(View.VISIBLE);//显示充币地址
                    detail_linearLayout_bank.setVisibility(View.GONE);//隐藏银行信息
                    detail_linearLayout_jiage.setVisibility(View.GONE);//隐藏USDT价格
                    detail_linearLayout_shiji.setVisibility(View.GONE);//隐藏实际到账
                    detail_imageView_addr.setVisibility(View.VISIBLE);//显示二维码
                    detail_textView_baocun.setVisibility(View.VISIBLE);//显示保存二维码

                    detail_textView2.setText("+" + response.getTop_up().getMoney());//充值个数
                    detail_textView3.setText("" + getString(R.string.recharge_h11));//充币个数（USDT）

                    detail_textView4.setText(getString(R.string.recharge_h13));//充币处理中

                    detail_textView5.setText("" + response.getTop_up().getShow_created_at());//充值处理中时间
                    detail_textView7.setText("" + response.getTop_up().getShow_updated_at());//充值完成时间

                    detail_textView8.setText(response.getTop_up().getWallet_addr());//充币地址

                    detail_textView14.setText(response.getTop_up().getCreated_at());//充值时间
                    detail_textView15.setText(response.getTop_up().getSn());//流水号
                    detail_textView16.setText(response.getTop_up().getStatus_title());//状态

                    detail_textView18.setText(getString(R.string.recharge_h28));//取消充币

                } else {
                    //澳元电汇
                    detail_linearLayout_addr.setVisibility(View.GONE);//隐藏充币地址
                    detail_linearLayout_bank.setVisibility(View.VISIBLE);//显示银行信息
                    detail_linearLayout_jiage.setVisibility(View.VISIBLE);//显示USDT价格
                    detail_linearLayout_shiji.setVisibility(View.VISIBLE);//显示实际到账
                    detail_imageView_addr.setVisibility(View.GONE);//隐藏二维码
                    detail_textView_baocun.setVisibility(View.GONE);//隐藏保存二维码

                    detail_textView2.setText("+" + response.getTop_up().getInput_money());//充值个数
                    detail_textView3.setText("" + getString(R.string.recharge_h3));//电汇金额（澳元）

                    detail_textView4.setText(getString(R.string.recharge_h4));//电汇处理中

                    detail_textView5.setText("" + response.getTop_up().getShow_created_at());//充值处理中时间
                    detail_textView7.setText("" + response.getTop_up().getShow_updated_at());//充值完成时间

                    detail_textView9.setText("" + response.getAud_wire_transfer().getBank_title());//银行名称
                    detail_textView10.setText("" + response.getAud_wire_transfer().getBank_card_proceeds_name());//收款人姓名
                    detail_textView11.setText("" + response.getAud_wire_transfer().getBank_card_account());//收款人帐号
                    detail_textView12.setText("" + response.getAud_wire_transfer().getBank_swift_code());//银行电汇SWIFT代码
                    detail_textView13.setText("" + response.getAud_wire_transfer().getBank_aba_code());//银行代码ABA#

                    detail_textView.setText("$"+response.getTop_up().getUsdt_price());//USDT价格
                    detail_textView1.setText(response.getTop_up().getMoney() + getString(R.string.recharge_h32));//实际到账
                    detail_textView14.setText(response.getTop_up().getCreated_at());//充值时间
                    detail_textView15.setText(response.getTop_up().getSn());//流水号
                    detail_textView16.setText(response.getTop_up().getStatus_title());//状态

                    detail_textView18.setText(getString(R.string.recharge_h6));//取消电汇
                }

                //进度条
                if (response.getTop_up().getStatus() == 2) {
                    //通过
                    detail_textView7.setVisibility(View.VISIBLE);
                    detail_imageView2.setImageResource(R.mipmap.ic_rechargedetail3);
                    detail_prograssBar.setProgress(100);
                    if (response.getTop_up().getType() == 1) {
                        //USDT
                        detail_textView6.setText(getString(R.string.recharge_h12));
                    } else {
                        detail_textView6.setText(getString(R.string.recharge_h5));
                    }
                    detail_textView6.setTextColor(getResources().getColor(R.color.green));
                    detail_textView17.setVisibility(View.GONE);

                } else if (response.getTop_up().getStatus() == 3) {
                    //未通过
                    detail_textView7.setVisibility(View.VISIBLE);
                    detail_imageView2.setImageResource(R.mipmap.ic_rechargedetail4);
                    detail_prograssBar.setProgress(100);
                    if (response.getTop_up().getType() == 1) {
                        //USDT
                        detail_textView6.setText(getString(R.string.recharge_h26));
                    } else {
                        detail_textView6.setText(getString(R.string.recharge_h24));
                    }
                    detail_textView6.setTextColor(getResources().getColor(R.color.green));
                    detail_textView17.setVisibility(View.VISIBLE);
                    detail_textView17.setText(getString(R.string.recharge_h27) + response.getTop_up().getStatus_rejected_cause());

                } else {
                    //其他状态-审核中
                    detail_textView7.setVisibility(View.GONE);
                    detail_imageView2.setImageResource(R.mipmap.ic_rechargedetail2);
                    detail_prograssBar.setProgress(50);
                    if (response.getTop_up().getType() == 1) {
                        //USDT
                        detail_textView6.setText(getString(R.string.recharge_h12));
                    } else {
                        detail_textView6.setText(getString(R.string.recharge_h5));
                    }
                    detail_textView6.setTextColor(getResources().getColor(R.color.black2));
                    detail_textView17.setVisibility(View.GONE);

                }

                //显示取消充币
                if (response.getTop_up().getStatus() == 1) {
                    //进行中
                    detail_textView18.setVisibility(View.VISIBLE);//取消按钮
                } else {
                    detail_textView18.setVisibility(View.GONE);//取消按钮

                }
                MainActivity.isOver = true;
            }
        });
    }

    private void requestCancel(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.RechargeDetail_Cancel + string, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    myToast(info);
                }
            }

            @Override
            public void onResponse(String response) {
                MyLogger.i(">>>>>>>>>充值详情-取消" + response);
                hideProgress();
                myToast(getString(R.string.recharge_h30));

                requestServer();
            }
        });
    }

    @Override
    protected void initData() {
//        requestServer();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout1:
                type = 1;
                changeUI();
                break;
            case R.id.linearLayout2:
                type = 2;
                changeUI();
                break;
            case R.id.linearLayout3:
                type = 3;
                changeUI();
                break;
            case R.id.textView7:
                //充值
                if (match()) {
                    textView7.setClickable(false);
                    showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
//                    params.put("qk", qk);
                    /*if (type == 1) {
                        if (model.getEth().getWallet_addr() != null)
                            params.put("wallet_addr_id", model.getEth().getWallet_addr().getId());
                    } else {
                        if (model.getCho().getWallet_addr() != null)
                            params.put("wallet_addr_id", model.getCho().getWallet_addr().getId());
                    }*/
                    params.put("type", type + "");
                    params.put("input_money", input_money);
//                    params.put("pay_type", "");
//                    params.put("txid", txid);//txid
                    params.put("token", localUserInfo.getToken());
//                    params.put("trade_password", password);//交易密码（不能小于6位数）
                    RequestRecharge(params);//充值
                }
                break;
            case R.id.linearLayout_keyong:
                //可用 - 跳转我的钱包
                CommonUtil.gotoActivity(getActivity(), OFCAccountDetailActivity.class, false);
                break;
        }

    }

    //充值
    private void RequestRecharge(Map<String, String> params) {
        OkHttpClientManager.postAsyn(getActivity(), URLs.Fragment4, params, new OkHttpClientManager.ResultCallback<RechargeDetailModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                textView7.setClickable(true);
                hideProgress();
                if (!info.equals("")) {
                    if (info.contains(getString(R.string.password_h1))) {
                        showToast(getString(R.string.password_h2),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(getActivity(), SetTransactionPasswordActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    } else if (info.contains(getString(R.string.password_h3))) {
                        showToast(getString(R.string.password_h4),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(getActivity(), SetAddressActivity.class, false);
                                    }
                                }, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                    } else if (info.contains(getString(R.string.password_h7))) {
                        showToast(getString(R.string.password_h8),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("type", 2);//1、服务中心 2、实名认证
                                        CommonUtil.gotoActivityWithData(getActivity(), SelectAddressActivity.class, bundle, false);
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

            @Override
            public void onResponse(RechargeDetailModel response) {
                textView7.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>充值" + response);
                myToast(getString(R.string.fragment4_h9));
                editText1.setText("");
                editText2.setText("");

//                requestServer();
                Bundle bundle = new Bundle();
                bundle.putString("id", response.getId());
                CommonUtil.gotoActivityWithData(getActivity(), RechargeDetailActivity.class, bundle, false);
            }
        });
    }

    private boolean match() {
        input_money = "";
        if (type == 1) {
            input_money = editText1.getText().toString().trim();
            if (TextUtils.isEmpty(input_money)) {
                myToast(getString(R.string.fragment4_h4));
                return false;
            }
        } else {
            input_money = editText2.getText().toString().trim();
            if (TextUtils.isEmpty(input_money)) {
                myToast(getString(R.string.fragment4_h5));
                return false;
            }
        }


        return true;
    }

    private void changeUI() {
//        editText1.setText("");
//        editText2.setText("");
        if (type == 1) {
            //USDT - 显示充币地址-显示个数-隐藏金额-隐藏到账-隐藏USDT价格
            textView1.setTextColor(getResources().getColor(R.color.green));
            textView2.setTextColor(getResources().getColor(R.color.black4));
            textView_3.setTextColor(getResources().getColor(R.color.black4));

            textView_dazhe_1.setBackgroundResource(R.mipmap.bg_dazhe_1);
            textView_dazhe_2.setBackgroundResource(R.mipmap.bg_dazhe_0);
            textView_dazhe_3.setBackgroundResource(R.mipmap.bg_dazhe_0);

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.INVISIBLE);

            linearLayout_addr.setVisibility(View.VISIBLE);//显示充币地址
            textView_addr.setText(getString(R.string.fragment4_h2));//充币地址
            textView4.setText(getString(R.string.fragment4_h14));//请在提交后，查看充币地址
            linearLayout_num.setVisibility(View.VISIBLE);//显示个数
            linearLayout_money.setVisibility(View.GONE);//隐藏金额
            linearLayout_daozhang.setVisibility(View.GONE);//隐藏到账
            textView5.setVisibility(View.GONE);//隐藏USDT价格
            textView_shouxufei.setVisibility(View.GONE);//隐藏手续费

            textView7.setText(getString(R.string.fragment4_h8));
            textView6.setText(getString(R.string.fragment4_h7));
            if (model != null) {
                editText1.setHint(getString(R.string.fragment4_h4)
                        + "(" + model.getUsdt_top_up_min_money() + "-" +
                        model.getUsdt_top_up_max_money() + ")");
            }

            textView7.setVisibility(View.VISIBLE);

        } else if (type == 2) {
            //澳元电汇- 显示电汇信息-隐藏个数-显示金额-显示到账-显示USDT价格
            textView1.setTextColor(getResources().getColor(R.color.black4));
            textView2.setTextColor(getResources().getColor(R.color.green));
            textView_3.setTextColor(getResources().getColor(R.color.black4));

            textView_dazhe_1.setBackgroundResource(R.mipmap.bg_dazhe_0);
            textView_dazhe_2.setBackgroundResource(R.mipmap.bg_dazhe_1);
            textView_dazhe_3.setBackgroundResource(R.mipmap.bg_dazhe_0);

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            view3.setVisibility(View.INVISIBLE);

            linearLayout_addr.setVisibility(View.VISIBLE);//显示电汇信息
            textView_addr.setText(getString(R.string.fragment4_h16));//电汇信息
            textView4.setText(getString(R.string.fragment4_h18));//请在提交后，查看电汇账号
            linearLayout_num.setVisibility(View.GONE);//隐藏个数
            linearLayout_money.setVisibility(View.VISIBLE);//显示金额
            linearLayout_daozhang.setVisibility(View.VISIBLE);//显示到账
            textView5.setVisibility(View.VISIBLE);//显示USDT价格
            textView_shouxufei.setVisibility(View.VISIBLE);//显示手续费

            textView7.setText(getString(R.string.fragment4_h29));
            textView6.setText(getString(R.string.fragment4_h13));

            textView7.setVisibility(View.VISIBLE);

        }
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void requestServer() {
        super.requestServer();
        showProgress(true, getString(R.string.app_loading));
        request("?token=" + localUserInfo.getToken());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * 截取图片存到本地
     */
    public File printScreen(View view, String picName) {
        //图片地址
        //        String imgPath = FileUtil.getImageDownloadDir(MyPosterActivity.this) + picName + ".png";
//        String imgPath = Environment.getExternalStorageDirectory() + "/" + picName + ".png";//文件根目录
        String imgPath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;//相册
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        File file = null;
        if (bitmap != null) {
            try {
                file = new File(imgPath, picName + ".png");
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();

                /*//通知相册更新
                MediaStore.Images.Media.insertImage(getContentResolver(), BitmapFactory.decodeFile(f.getAbsolutePath()), f.getName(), null);
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//                Uri uri1 = Uri.fromFile(f);
                Uri uri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", f);
                intent.setData(uri);
                sendBroadcast(intent);*/

                /*//把文件插入到系统图库
                try {
                    MediaStore.Images.Media.insertImage(this.getContentResolver(), f.getAbsolutePath(), f.getName(), null);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/
                // 通知图库更新
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    MediaScannerConnection.scanFile(getActivity(), new String[]{file.getAbsolutePath()}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                public void onScanCompleted(String path, Uri uri) {
                                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                                    mediaScanIntent.setData(uri);
                                    getActivity().sendBroadcast(mediaScanIntent);
                                }
                            });
                } else {
                    String relationDir = file.getParent();
                    File file1 = new File(relationDir);
                    getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.fromFile(file1.getAbsoluteFile())));
                }
                mHandler.obtainMessage(MSG_SUCCESS)// 获取信息
                        .sendToTarget(); //发送信息

                MyLogger.i(">>>>>>" + file);
                return file;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
