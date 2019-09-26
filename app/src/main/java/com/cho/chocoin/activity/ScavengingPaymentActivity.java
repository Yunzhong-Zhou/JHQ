package com.cho.chocoin.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cho.chocoin.R;
import com.cho.chocoin.base.BaseActivity;
import com.cho.chocoin.model.ScavengingPaymentModel;
import com.cho.chocoin.net.OkHttpClientManager;
import com.cho.chocoin.net.URLs;
import com.cho.chocoin.utils.CommonUtil;
import com.cho.chocoin.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

import static com.cho.chocoin.net.OkHttpClientManager.IMGHOST;


/**
 * Created by zyz on 2018/5/31.
 * 扫码付款
 */

public class ScavengingPaymentActivity extends BaseActivity {
    String to_member_id = "";
    ImageView imageView1;
    TextView textView1, textView2, textView3, textView4;
    EditText editText1, editText2;
    ScavengingPaymentModel model;
    String money = "", password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scavengingpayment);
    }

    @Override
    protected void initView() {
        setSpringViewMore(false);//不需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Request("?token=" + localUserInfo.getToken()
                        + "&to_member_id=" + to_member_id);
            }

            @Override
            public void onLoadmore() {

            }
        });
        imageView1 = findViewByID_My(R.id.imageView1);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        editText1 = findViewByID_My(R.id.editText1);
        editText2 = findViewByID_My(R.id.editText2);
    }

    @Override
    protected void initData() {
        to_member_id = getIntent().getStringExtra("id");

        showProgress(true, getString(R.string.app_loading2));
        Request("?token=" + localUserInfo.getToken()
                + "&to_member_id=" + to_member_id);

    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(ScavengingPaymentActivity.this, URLs.Transfer + string, new OkHttpClientManager.ResultCallback<ScavengingPaymentModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                onHttpResult();
                if (!info.equals("")) {
                    showToast(info);

                }
            }

            @Override
            public void onResponse(final ScavengingPaymentModel response) {
                onHttpResult();
                MyLogger.i(">>>>>>>>>对方信息" + response);
                if (response != null) {
                    model = response;
//                    hk = response.getHk();
                    textView2.setText(response.getCommon_usable_money());//可用余额
//                    account_type = response.getAccount_type_list().get(0).getCode();
//                    textView2.setText(response.getUsable_interest_money() + "");
                    //昵称
                    textView1.setText(response.getNickname());
                    //头像
                    if (!response.getHead().equals(""))
                        Glide.with(ScavengingPaymentActivity.this)
                                .load(IMGHOST + response.getHead())
                                .centerCrop()
                                .into(imageView1);//加载图片
                    else
                        imageView1.setImageResource(R.mipmap.headimg);


                }
            }
        });
    }

    private void RequestScavengingPayment(Map<String, String> params) {
        OkHttpClientManager.postAsyn(ScavengingPaymentActivity.this, URLs.Transfer, params, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(final Request request, String info, Exception e) {
                hideProgress();
                textView4.setClickable(true);
                if (!info.equals("")) {
                    if (info.contains(getString(R.string.password_h1))) {
                        showToast(getString(R.string.password_h2),
                                getString(R.string.password_h5), getString(R.string.password_h6),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        CommonUtil.gotoActivity(ScavengingPaymentActivity.this, SetTransactionPasswordActivity.class, false);
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
                                        CommonUtil.gotoActivity(ScavengingPaymentActivity.this, SelectAddressActivity.class, false);
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
                Request("?token=" + localUserInfo.getToken()
                        + "&to_member_id=" + to_member_id);
            }

            @Override
            public void onResponse(String response) {
                textView4.setClickable(true);
                hideProgress();
                MyLogger.i(">>>>>>>>>转账" + response);
                showToast(getString(R.string.scavengingpayment_h5), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        finish();
                        CommonUtil.gotoActivity(ScavengingPaymentActivity.this, TransferRecordActivity.class, true);
                    }
                });

            }
        }, true);

    }

    private boolean match() {
        money = editText1.getText().toString().trim();
        if (TextUtils.isEmpty(money)) {
            myToast(getString(R.string.scavengingpayment_h4));
            return false;
        }
        password = editText2.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            myToast(getString(R.string.scavengingpayment_h6));
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView3:
                //选择卖出账户
//                showPopupWindow1(textView3);
                break;
            case R.id.textView4:
                //确认转账
                if (match()) {
                    textView4.setClickable(false);
                    showProgress(true, getString(R.string.app_loading1));
                    HashMap<String, String> params = new HashMap<>();
                    params.put("token", localUserInfo.getToken());
                    params.put("to_member_id", to_member_id + "");
//                    params.put("account_type", account_type + "");
                    params.put("money", money);
                    params.put("trade_password", password);
                    params.put("hk", model.getHk());
                    RequestScavengingPayment(params);
                } else {

                }
                break;
        }
    }

    public void onHttpResult() {
        hideProgress();
        // 刷新完成
//        pullRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.zxing_h12));
    }

    /*private void showPopupWindow1(View v) {
        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(ScavengingPaymentActivity.this).inflate(
                R.layout.pop_list_fragment1, null);
        final FixedPopupWindow popupWindow = new FixedPopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
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
        final List<String> list = new ArrayList<String>();
        for (int i = 0; i < model.getAccount_type_list().size(); i++) {
            list.add(model.getAccount_type_list().get(i).getTitle());
        }
        final Pop_ListAdapter adapter = new Pop_ListAdapter(ScavengingPaymentActivity.this, list);
        adapter.setSelectItem(i1);
        pop_listView.setAdapter(adapter);
        pop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                i1 = i;
                adapter.setSelectItem(i);
                adapter.notifyDataSetChanged();
                account_type = model.getAccount_type_list().get(i).getCode();
                textView3.setText(list.get(i).toString());
                switch (i) {
                    case 0:
                        //利息
                        textView2.setText(model.getUsable_interest_money() + "");
                        break;
                    case 1:
                        //佣金
                        textView2.setText(model.getUsable_commission_money() + "");
                        break;
                    case 2:
                        //奖励
                        textView2.setText(model.getUsable_award_money() + "");
                        break;
                    case 3:
                        //预测
                        textView2.setText(model.getUsable_forecast_money() + "");
                        break;
                    case 4:
                        //充值
                        textView2.setText(model.getUsable_top_up_money() + "");
                        break;
                    case 5:
                        //购买
                        textView2.setText(model.getUsable_buy_money() + "");
                        break;
                    case 6:
                        //转币
                        textView2.setText(model.getUsable_transfer_money() + "");
                        break;
                    case 7:
                        //贷币
                        textView2.setText(model.getUsable_loan_money() + "");
                        break;
                }
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
    }*/
}
