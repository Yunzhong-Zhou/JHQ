package com.cfo.chocoin.fragment;

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

import com.bumptech.glide.Glide;
import com.cfo.chocoin.R;
import com.cfo.chocoin.activity.BankPaymentActivity;
import com.cfo.chocoin.activity.MainActivity;
import com.cfo.chocoin.activity.ScanCodePaymentActivity;
import com.cfo.chocoin.activity.SelectAddressActivity;
import com.cfo.chocoin.activity.SetTransactionPasswordActivity;
import com.cfo.chocoin.activity.WebContentActivity;
import com.cfo.chocoin.base.BaseFragment;
import com.cfo.chocoin.model.CreateRechargeModel;
import com.cfo.chocoin.model.Fragment4Model;
import com.cfo.chocoin.model.RechargeDetailModel;
import com.cfo.chocoin.net.OkHttpClientManager;
import com.cfo.chocoin.net.URLs;
import com.cfo.chocoin.utils.CommonUtil;
import com.cfo.chocoin.utils.MyLogger;
import com.cy.dialog.BaseDialog;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.cfo.chocoin.net.OkHttpClientManager.HOST;


/**
 * Created by zyz on 2016/1/6.
 * 充值
 */
public class Fragment4 extends BaseFragment {
    LinearLayout linearLayout_1, linearLayout_2;
    //充值
    Fragment4Model model;
    int type = 3;
    LinearLayout linearLayout1, linearLayout2, linearLayout3;
    TextView textView1, textView2, textView_3,textView_dazhe_1,textView_dazhe_2,textView_dazhe_3;
    View view1, view2, view3;

    String input_money = "", txid = "";
    TextView textView3, textView4, textView5, textView6, textView7;
    EditText editText1, editText2;

    LinearLayout linearLayout_addr, linearLayout_alipay, linearLayout_wechat, linearLayout_ewm, linearLayout_unionpay;
    TextView textView_num;


    //充值详情
    RechargeDetailModel detailModel;
    ProgressBar detail_prograssBar;
    ImageView detail_imageView, detail_imageView1, detail_imageView2;
    TextView detail_textView_title,detail_textView,detail_textView1, detail_textView2, detail_textView3, detail_textView4,
            detail_textView5, detail_textView6, detail_textView7, detail_textView8, detail_textView9,
            detail_textView11, detail_textView12, detail_textView13, detail_textView14, detail_textView16, detail_textView17;
    LinearLayout detail_linearLayout1;

    LinearLayout detail_linearLayout, detail_linearLayout_1, detail_linearLayout_2, detail_linearLayout_3,
            detail_linearLayout_alipay, detail_linearLayout_wechat,detail_linearLayout_ewm,detail_linearLayout_unionpay,
            detail_linearLayout_addr,detail_linearLayout_eth,detail_linearLayout_cho,detail_linearLayout_cny;
    TextView detail_textView3_2, detail_textView3_3, detail_textView3_4,detail_textView_eth,detail_textView_cho,detail_textView_cny;
    File file = null;
    private Thread mThread;
    private static final int MSG_SUCCESS = 0;// 获取成功的标识
    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {// 此方法在ui线程运行
            switch (msg.what) {
                case MSG_SUCCESS:
                    showToast(getString(R.string.zxing_h23));
                    detail_textView.setClickable(true);
                    /*if (file != null) {
                        showToast("二维码已经保存到相册");
                        textView.setClickable(true);

                    } else {
                        showToast("图片保存失败", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                textView.setClickable(true);
                                dialog.dismiss();
                                initData();
                            }
                        });
                    }*/
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
                if (MainActivity.item == 4) {
                    requestServer();
                }
            }
        }
    }

    @Override
    protected void initView(View view) {
        linearLayout_1 = findViewByID_My(R.id.linearLayout_1);
        linearLayout_2 = findViewByID_My(R.id.linearLayout_2);
//        findViewByID_My(R.id.headview).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
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
        textView_num = findViewByID_My(R.id.textView_num);
        linearLayout_alipay = findViewByID_My(R.id.linearLayout_alipay);
        linearLayout_wechat = findViewByID_My(R.id.linearLayout_wechat);
        linearLayout_ewm = findViewByID_My(R.id.linearLayout_ewm);
        linearLayout_unionpay = findViewByID_My(R.id.linearLayout_unionpay);

        linearLayout_alipay.setOnClickListener(this);
        linearLayout_wechat.setOnClickListener(this);
        linearLayout_ewm.setOnClickListener(this);
        linearLayout_unionpay.setOnClickListener(this);

        linearLayout1 = findViewByID_My(R.id.linearLayout1);
        linearLayout2 = findViewByID_My(R.id.linearLayout2);
        linearLayout3 = findViewByID_My(R.id.linearLayout3);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);

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
        textView5.setText(getString(R.string.fragment4_h6) + "0" + getString(R.string.app_ge));
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
        textView7.setOnClickListener(this);

        //输入监听
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (type == 1) {
                    if (!editText1.getText().toString().trim().equals("")) {
                        input_money = editText1.getText().toString().trim();
                        MyLogger.i(">>>>>输入币数>>>>>" + input_money);

                        //实际到账  =  eth个数  *  eth_price  /  cho_price
                        double real_money = Double.valueOf(input_money) * Double.valueOf(model.getEth().getPrice()) / Double.valueOf(model.getCho().getPrice());
                        MyLogger.i(">>>>>实际到账>>>>>" + real_money);

                        textView5.setText(getString(R.string.fragment4_h6) + String.format("%.2f", real_money) + getString(R.string.app_ge));
                    } else {
                        textView5.setText(getString(R.string.fragment4_h6) + "0" + getString(R.string.app_ge));
                    }
                } else if (type == 3) {
                    if (!editText1.getText().toString().trim().equals("")) {
                        input_money = editText1.getText().toString().trim();
                        MyLogger.i(">>>>>输入币数>>>>>" + input_money);

                        //实际到账  =  eth个数   /  cho_price
                        double real_money = Double.valueOf(input_money) / Double.valueOf(model.getCny().getPrice());
                        MyLogger.i(">>>>>实际到账>>>>>" + real_money);

                        textView5.setText(getString(R.string.fragment4_h6) + String.format("%.2f", real_money) + getString(R.string.app_ge));
                    } else {
                        textView5.setText(getString(R.string.fragment4_h6) + "0" + getString(R.string.app_ge));
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
         * 充值详情
         */
        detail_prograssBar = findViewByID_My(R.id.detail_prograssBar);
        detail_imageView1 = findViewByID_My(R.id.detail_imageView1);
        detail_imageView2 = findViewByID_My(R.id.detail_imageView2);

        detail_textView_title = findViewByID_My(R.id.detail_textView_title);
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
        detail_textView11 = findViewByID_My(R.id.detail_textView11);
        detail_textView12 = findViewByID_My(R.id.detail_textView12);
        detail_textView13 = findViewByID_My(R.id.detail_textView13);
        detail_textView14 = findViewByID_My(R.id.detail_textView14);
        detail_textView16 = findViewByID_My(R.id.detail_textView16);
        detail_textView17 = findViewByID_My(R.id.detail_textView17);
        detail_linearLayout1 = findViewByID_My(R.id.detail_linearLayout1);
        detail_textView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消充币
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
                                        + "&id=" + detailModel.getTop_up().getId());

                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //取消
                                dialog.dismiss();
                            }
                        });
            }
        });

        detail_linearLayout = findViewByID_My(R.id.detail_linearLayout);
        detail_linearLayout_1 = findViewByID_My(R.id.detail_linearLayout_1);
        detail_linearLayout_2 = findViewByID_My(R.id.detail_linearLayout_2);
        detail_linearLayout_3 = findViewByID_My(R.id.detail_linearLayout_3);
        detail_imageView = findViewByID_My(R.id.detail_imageView);
        detail_textView3_2 = findViewByID_My(R.id.detail_textView3_2);
        detail_textView3_3 = findViewByID_My(R.id.detail_textView3_3);
        detail_textView3_4 = findViewByID_My(R.id.detail_textView3_4);

        detail_linearLayout_alipay = findViewByID_My(R.id.detail_linearLayout_alipay);
        detail_linearLayout_wechat = findViewByID_My(R.id.detail_linearLayout_wechat);
        detail_linearLayout_ewm = findViewByID_My(R.id.detail_linearLayout_ewm);
        detail_linearLayout_unionpay = findViewByID_My(R.id.detail_linearLayout_unionpay);
        detail_linearLayout_addr = findViewByID_My(R.id.detail_linearLayout_addr);
        detail_linearLayout_alipay.setOnClickListener(this);
        detail_linearLayout_wechat.setOnClickListener(this);
        detail_linearLayout_ewm.setOnClickListener(this);
        detail_linearLayout_unionpay.setOnClickListener(this);


        detail_linearLayout_eth = findViewByID_My(R.id.detail_linearLayout_eth);
        detail_linearLayout_cho = findViewByID_My(R.id.detail_linearLayout_cho);
        detail_linearLayout_cny = findViewByID_My(R.id.detail_linearLayout_cny);
        detail_textView_eth = findViewByID_My(R.id.detail_textView_eth);
        detail_textView_cho = findViewByID_My(R.id.detail_textView_cho);
        detail_textView_cny = findViewByID_My(R.id.detail_textView_cny);

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

                if (response.getId().equals("")) {
                    hideProgress();

                    linearLayout_1.setVisibility(View.VISIBLE);
                    linearLayout_2.setVisibility(View.GONE);
                    detail_textView_title.setVisibility(View.GONE);

                    textView3.setText(response.getCommon_usable_money());
                    changeUI();
                    //首次充币
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
                    }

                } else {
                    //加载充币详情
                    linearLayout_1.setVisibility(View.GONE);
                    linearLayout_2.setVisibility(View.VISIBLE);
                    detail_textView_title.setVisibility(View.VISIBLE);

                    //加载充值详情
                    requestDetail("?token=" + localUserInfo.getToken()
                            + "&id=" + model.getId());
                }
                MainActivity.isOver = true;
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
                detail_textView1.setText(getString(R.string.recharge_h11) + "(" + response.getTop_up().getMoney_type_title() + ")");//充值个数
                detail_textView2.setText("+" + response.getTop_up().getInput_money());//充值个数
                detail_textView3.setText("" + response.getTop_up().getStatus_title());//充值状态

                detail_textView5.setText("" + response.getTop_up().getShow_created_at());//充值处理中时间
                detail_textView7.setText("" + response.getTop_up().getShow_updated_at());//充值完成时间
                detail_textView8.setText(response.getTop_up().getWallet_addr());//充币地址
                detail_textView9.setText(response.getTop_up().getTxid());//txid

                detail_textView_eth.setText("$ " + response.getTop_up().getEth_price());
                detail_textView_cho.setText("$ " + response.getTop_up().getCho_price());
                detail_textView_cny.setText("¥ " + response.getTop_up().getCny_price());

                detail_textView11.setText(response.getTop_up().getMoney() + getString(R.string.recharge_h23));//实际到账
                detail_textView12.setText(response.getTop_up().getCreated_at());//充值时间
                detail_textView13.setText(response.getTop_up().getSn());//流水号
                detail_textView14.setText(response.getTop_up().getStatus_title());//状态

                if (response.getTop_up().getMoney_type() == 1) {//eth
                    detail_linearLayout_eth.setVisibility(View.VISIBLE);
                    detail_linearLayout_cho.setVisibility(View.VISIBLE);
                    detail_linearLayout_cny.setVisibility(View.GONE);

                    detail_linearLayout1.setVisibility(View.VISIBLE);//实际到账
                    detail_linearLayout.setVisibility(View.GONE);//支付方式布局
                    detail_linearLayout_addr.setVisibility(View.VISIBLE);//充币地址
                } else if (response.getTop_up().getMoney_type() == 2) {//cho
                    detail_linearLayout_eth.setVisibility(View.GONE);
                    detail_linearLayout_cho.setVisibility(View.VISIBLE);
                    detail_linearLayout_cny.setVisibility(View.GONE);

                    detail_linearLayout1.setVisibility(View.GONE);//实际到账
                    detail_linearLayout.setVisibility(View.GONE);//支付方式布局
                    detail_linearLayout_addr.setVisibility(View.VISIBLE);//充币地址

                } else if (response.getTop_up().getMoney_type() == 3) {//cny
                    detail_textView1.setText(getString(R.string.recharge_h31) + "(" + response.getTop_up().getMoney_type_title() + ")");//充值个数

                    detail_linearLayout_eth.setVisibility(View.GONE);
                    detail_linearLayout_cho.setVisibility(View.GONE);
                    detail_linearLayout_cny.setVisibility(View.VISIBLE);

                    detail_linearLayout1.setVisibility(View.VISIBLE);//实际到账
                    detail_linearLayout.setVisibility(View.VISIBLE);//支付方式布局
                    detail_linearLayout_addr.setVisibility(View.GONE);//充币地址

                }

                //进度条
                if (response.getTop_up().getStatus() == 2) {
                    //通过
                    detail_textView7.setVisibility(View.VISIBLE);
                    detail_imageView2.setImageResource(R.mipmap.ic_rechargedetail3);
                    detail_prograssBar.setProgress(100);
                    detail_textView6.setText(getString(R.string.recharge_h12));
                    detail_textView6.setTextColor(getResources().getColor(R.color.purple));
                    detail_textView16.setVisibility(View.GONE);

                } else if (response.getTop_up().getStatus() == 3) {
                    //未通过
                    detail_textView7.setVisibility(View.VISIBLE);
                    detail_imageView2.setImageResource(R.mipmap.ic_rechargedetail4);
                    detail_prograssBar.setProgress(100);
                    detail_textView6.setText(getString(R.string.recharge_h26));
                    detail_textView6.setTextColor(getResources().getColor(R.color.purple));
                    detail_textView16.setVisibility(View.VISIBLE);
                    detail_textView16.setText(getString(R.string.recharge_h27) + response.getTop_up().getStatus_rejected_cause());

                } else {
                    //其他状态-审核中
                    detail_textView7.setVisibility(View.GONE);
                    detail_imageView2.setImageResource(R.mipmap.ic_rechargedetail2);
                    detail_prograssBar.setProgress(50);
                    detail_textView6.setText(getString(R.string.recharge_h12));
                    detail_textView6.setTextColor(getResources().getColor(R.color.black2));
                    detail_textView16.setVisibility(View.GONE);

                }

                //显示取消充币-支付方式
                if (response.getTop_up().getStatus() == 1) {
                    //进行中
                    detail_textView17.setVisibility(View.VISIBLE);//取消按钮
                    switch (response.getTop_up().getPay_detail_type()) {
                        case 1:
                        case 2:
                            //显示支付布局
                            detail_linearLayout_1.setVisibility(View.VISIBLE);
                            detail_linearLayout_2.setVisibility(View.GONE);
                            detail_linearLayout_3.setVisibility(View.GONE);
                            if (!response.getAlipay().equals("")) {
                                detail_linearLayout_alipay.setVisibility(View.VISIBLE);
                            } else {
                                detail_linearLayout_alipay.setVisibility(View.GONE);
                            }
                            if (!response.getWechat().equals("")) {
                                detail_linearLayout_wechat.setVisibility(View.VISIBLE);
                            } else {
                                detail_linearLayout_wechat.setVisibility(View.GONE);
                            }
                            if (!response.getEwm().equals("")) {
                                detail_linearLayout_ewm.setVisibility(View.VISIBLE);
                            } else {
                                detail_linearLayout_ewm.setVisibility(View.GONE);
                            }
                            if (!response.getUnionpay().equals("")) {
                                detail_linearLayout_unionpay.setVisibility(View.VISIBLE);
                            } else {
                                detail_linearLayout_unionpay.setVisibility(View.GONE);
                            }
                            break;
                        case 3:
                            //显示银联支付
                            showToast(getString(R.string.zxing_h29));
                            detail_linearLayout_1.setVisibility(View.GONE);
                            detail_linearLayout_2.setVisibility(View.GONE);
                            detail_linearLayout_3.setVisibility(View.VISIBLE);
                            detail_textView3_2.setText(response.getBank_title());//银行
                            detail_textView3_3.setText(response.getBank_card_account());//卡号
                            detail_textView3_4.setText(response.getBank_card_proceeds_name());//账户
                            break;
                        case 4:
                            //显示二维码布局
                            showToast(getString(R.string.zxing_h29));
                            detail_linearLayout_1.setVisibility(View.GONE);
                            detail_linearLayout_2.setVisibility(View.VISIBLE);
                            detail_linearLayout_3.setVisibility(View.GONE);
                        /*//生成二维码
                        Bitmap mBitmap = ZxingUtils.createQRCodeBitmap(response.getEwm_qrcode(), 480, 480);
                        imageView.setImageBitmap(mBitmap);*/
                            if (!response.getEwm_qrcode().equals(""))
                                Glide.with(getActivity()).load(OkHttpClientManager.IMGHOST + response.getEwm_qrcode())
                                        .centerCrop().into(detail_imageView);//加载图片
                            else
                                detail_imageView.setImageResource(R.mipmap.headimg);
                            break;
                    }

                } else {
                    detail_textView17.setVisibility(View.GONE);//取消按钮
                    detail_linearLayout_1.setVisibility(View.GONE);
                    detail_linearLayout_2.setVisibility(View.GONE);
                    detail_linearLayout_3.setVisibility(View.GONE);
                }


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
                    params.put("money_type", type + "");
                    params.put("input_money", input_money);
                    params.put("pay_type", "");
//                    params.put("txid", txid);//txid
                    params.put("token", localUserInfo.getToken());
//                    params.put("trade_password", password);//交易密码（不能小于6位数）
                    RequestRecharge(params);//充值
                }
                break;
            case R.id.linearLayout_alipay:
                toPay(model.getAlipay());
                break;
            case R.id.linearLayout_wechat:
                toPay(model.getWechat());
                break;
            case R.id.linearLayout_ewm:
                toPay(model.getEwm());
                break;
            case R.id.linearLayout_unionpay:
                toPay(model.getUnionpay());
                break;

            case R.id.detail_linearLayout_alipay:
                //支付宝
                toPay_detail(detailModel.getAlipay());
                break;
            case R.id.detail_linearLayout_wechat:
                //微信
                toPay_detail(detailModel.getWechat());
                break;
            case R.id.detail_linearLayout_ewm:
                //扫码
                toPay_detail(detailModel.getEwm());
                break;
            case R.id.detail_linearLayout_unionpay:
                //银联
                toPay_detail(detailModel.getUnionpay());
                break;
            case R.id.detail_textView:
                //保存二维码
                detail_textView.setClickable(false);

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        //这是新的线程，可在这儿处理耗时的操作，更新不了UI界面的操作的
                        file = printScreen(detail_linearLayout_2, "ScanQRCode" + System.currentTimeMillis());
                    }
                };

                if (mThread == null) {
                    mThread = new Thread(runnable);
                    mThread.start();// 线程启动
                }
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
                                        CommonUtil.gotoActivity(getActivity(), SelectAddressActivity.class, false);
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

                requestServer();
                /*Bundle bundle = new Bundle();
                bundle.putString("id", response.getId());
                CommonUtil.gotoActivityWithData(getActivity(), RechargeDetailActivity.class, bundle, false);*/
            }
        });
    }

    private void toPay(String pay_type) {
        if (match()) {
            //去充值
            this.showProgress(true, getString(R.string.fragment4_h21));
            Map<String, String> params = new HashMap<>();
            params.put("money_type", type + "");
            params.put("pay_type", pay_type + "");
            params.put("input_money", input_money + "");
            params.put("token", localUserInfo.getToken());
            requestPay(params, pay_type);
        }
    }
    private void toPay_detail(String pay_type) {
        //请求支付网页
        Bundle bundle = new Bundle();
        bundle.putString("url", HOST + "/pay?top_up_id=" + detailModel.getTop_up().getId() + "&pay_type=" + pay_type);
        CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle, false);
    }

    private void requestPay(Map<String, String> params, final String pay_type) {
        OkHttpClientManager.postAsyn(getActivity(), URLs.Fragment4, params, new OkHttpClientManager.ResultCallback<CreateRechargeModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
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
                                        CommonUtil.gotoActivity(getActivity(), SelectAddressActivity.class, false);
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
            public void onResponse(CreateRechargeModel response) {
                MyLogger.i(">>>>>>>>>充值提交1>>>>" + response);
                hideProgress();
                editText1.setText("");
                editText2.setText("");
                switch (response.getPay_detail_type()) {
                    case 1:
                    case 2:
                        //请求支付网页
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("url", HOST + "/pay?top_up_id=" + response.getId() + "&pay_type=" + pay_type);
                        CommonUtil.gotoActivityWithData(getActivity(), WebContentActivity.class, bundle1, false);
                        break;
                    case 4:
                       /* myToast("充值提交成功");
                    Bundle bundle = new Bundle();
                    bundle.putString("RechargeDetail", response.getId());
                    CommonUtil.gotoActivityWithData(getActivity(), RechargeDetailActivity.class, bundle, false);*/
                        //跳转 扫码支付
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("id", response.getId());
                        CommonUtil.gotoActivityWithData(getActivity(), ScanCodePaymentActivity.class, bundle3, false);
                        break;
                    case 3:
                        /*myToast("充值提交成功");
                    Bundle bundle = new Bundle();
                    bundle.putString("RechargeDetail", response.getId());
                    CommonUtil.gotoActivityWithData(getActivity(), RechargeDetailActivity.class, bundle, false);*/
                        //跳转 银联支付
                        Bundle bundle4 = new Bundle();
                        bundle4.putString("id", response.getId());
                        CommonUtil.gotoActivityWithData(getActivity(), BankPaymentActivity.class, bundle4, false);
                        break;
                }

            }
        });
    }

    private boolean match() {
        input_money = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(input_money)) {
            if (type == 3) {
                myToast(getString(R.string.fragment4_h16));
            } else {
                myToast(getString(R.string.fragment4_h4));
            }

            return false;
        }
//        eth_wallet_addr = editText2.getText().toString().trim();
        /*if (TextUtils.isEmpty(password1)) {
            myToast(getString(R.string.settransactionpassword_pwd));
            return false;
        }*/
//        xrp_wallet_addr = editText3.getText().toString().trim();
        /*if (TextUtils.isEmpty(password2)) {
            myToast(getString(R.string.settransactionpassword_pwd1));
            return false;
        }*/
        txid = editText2.getText().toString().trim();
        /*if (TextUtils.isEmpty(txid)) {
            myToast(getString(R.string.fragment4_h5));
            return false;
        }*/
        return true;
    }

    private void changeUI() {
        editText1.setText("");
        textView5.setText(getString(R.string.fragment4_h6) + "0" + getString(R.string.app_ge));

        if (type == 1) {
            textView1.setTextColor(getResources().getColor(R.color.green));
            textView2.setTextColor(getResources().getColor(R.color.black3));
            textView_3.setTextColor(getResources().getColor(R.color.black3));

            textView_dazhe_1.setBackgroundResource(R.mipmap.bg_dazhe_1);
            textView_dazhe_2.setBackgroundResource(R.mipmap.bg_dazhe_0);
            textView_dazhe_3.setBackgroundResource(R.mipmap.bg_dazhe_0);

            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.INVISIBLE);

            linearLayout_addr.setVisibility(View.VISIBLE);

            textView4.setText(model.getEth().getWallet_addr());
           /* if (model.getEth().getWallet_addr() != null)
                textView4.setText(model.getEth().getWallet_addr().getWallet_addr());//地址
            else
                textView4.setText("");//地址*/

            textView5.setVisibility(View.VISIBLE);
            textView6.setText(getString(R.string.fragment4_h7));
            textView_num.setText(getString(R.string.fragment4_h3));
            editText1.setHint(getString(R.string.fragment4_h4)
                    + "(" + model.getEth().getTop_up_min_money() + "-" +
                    model.getEth().getTop_up_max_money() + ")");

            textView7.setVisibility(View.VISIBLE);
            linearLayout_alipay.setVisibility(View.GONE);
            linearLayout_wechat.setVisibility(View.GONE);
            linearLayout_ewm.setVisibility(View.GONE);
            linearLayout_unionpay.setVisibility(View.GONE);

        } else if (type == 2) {
            textView1.setTextColor(getResources().getColor(R.color.black3));
            textView2.setTextColor(getResources().getColor(R.color.green));
            textView_3.setTextColor(getResources().getColor(R.color.black3));

            textView_dazhe_1.setBackgroundResource(R.mipmap.bg_dazhe_0);
            textView_dazhe_2.setBackgroundResource(R.mipmap.bg_dazhe_1);
            textView_dazhe_3.setBackgroundResource(R.mipmap.bg_dazhe_0);

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            view3.setVisibility(View.INVISIBLE);

            linearLayout_addr.setVisibility(View.VISIBLE);

            textView4.setText(model.getCho().getWallet_addr());
            /*if (model.getCho().getWallet_addr() != null)
                textView4.setText(model.getCho().getWallet_addr().getWallet_addr());//地址
            else
                textView4.setText("");//地址*/
            textView5.setVisibility(View.GONE);
            textView6.setText(getString(R.string.fragment4_h13));
            textView_num.setText(getString(R.string.fragment4_h3));
            editText1.setHint(getString(R.string.fragment4_h4)
                    + "(" + model.getCho().getTop_up_min_money() + "-" +
                    model.getCho().getTop_up_max_money() + ")");

            textView7.setVisibility(View.VISIBLE);
            linearLayout_alipay.setVisibility(View.GONE);
            linearLayout_wechat.setVisibility(View.GONE);
            linearLayout_ewm.setVisibility(View.GONE);
            linearLayout_unionpay.setVisibility(View.GONE);

        } else if (type == 3) {
            textView1.setTextColor(getResources().getColor(R.color.black3));
            textView2.setTextColor(getResources().getColor(R.color.black3));
            textView_3.setTextColor(getResources().getColor(R.color.green));

            textView_dazhe_1.setBackgroundResource(R.mipmap.bg_dazhe_0);
            textView_dazhe_2.setBackgroundResource(R.mipmap.bg_dazhe_0);
            textView_dazhe_3.setBackgroundResource(R.mipmap.bg_dazhe_1);

            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.VISIBLE);

            linearLayout_addr.setVisibility(View.GONE);

//            textView4.setText(model.getFiat().getWallet_addr());
            /*if (model.getCho().getWallet_addr() != null)
                textView4.setText(model.getCho().getWallet_addr().getWallet_addr());//地址
            else
                textView4.setText("");//地址*/

            textView5.setVisibility(View.VISIBLE);
            textView6.setText(getString(R.string.fragment4_h27));
            textView_num.setText(getString(R.string.fragment4_h15));
            editText1.setHint(getString(R.string.fragment4_h16)
                    + "(" + model.getCny().getTop_up_min_money() + "-" +
                    model.getCny().getTop_up_max_money() + ")");

            textView7.setVisibility(View.GONE);

            if (!model.getAlipay().equals("")) {
                linearLayout_alipay.setVisibility(View.VISIBLE);
            } else {
                linearLayout_alipay.setVisibility(View.GONE);

            }
            if (!model.getWechat().equals("")) {
                linearLayout_wechat.setVisibility(View.VISIBLE);
            } else {
                linearLayout_wechat.setVisibility(View.GONE);

            }
            if (!model.getEwm().equals("")) {
                linearLayout_ewm.setVisibility(View.VISIBLE);
            } else {
                linearLayout_ewm.setVisibility(View.GONE);
            }
            if (!model.getUnionpay().equals("")) {
                linearLayout_unionpay.setVisibility(View.VISIBLE);
            } else {
                linearLayout_unionpay.setVisibility(View.GONE);
            }

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
//                file = new File(imgPath);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();

                /*Uri uri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", file);
                //通知相册更新
                MediaStore.Images.Media.insertImage(ScanCodePaymentActivity.this.getContentResolver(), bitmap, file.toString(), null);
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri1 = Uri.fromFile(file);
                intent.setData(uri1);
                ScanCodePaymentActivity.this.sendBroadcast(intent);*/


                /*//把文件插入到系统图库(内部存储/Pictures)
                try {
                    MediaStore.Images.Media.insertImage(this.getContentResolver(), file.getAbsolutePath(), file.getName(), null);
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

                MyLogger.i(">>>>>>"+file);
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
