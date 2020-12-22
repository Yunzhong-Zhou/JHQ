package com.fone.fone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.fone.fone.R;
import com.fone.fone.activity.MachineDetailActivity;
import com.fone.fone.activity.MainActivity;
import com.fone.fone.base.BaseFragment;
import com.fone.fone.model.Fragment2BuyModel;
import com.fone.fone.model.Fragment2Model;
import com.fone.fone.net.OkHttpClientManager;
import com.fone.fone.net.URLs;
import com.fone.fone.utils.CommonUtil;
import com.fone.fone.utils.MyLogger;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by fafukeji01 on 2016/1/6.
 * 算力
 */
public class Fragment2 extends BaseFragment {
    Fragment2Model model;
    int num = 1;
    ImageView imageView1, imageView2;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, tv_confirm;
    SeekBar seekBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        StatusBarUtil.setTransparent(getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        /*if (MainActivity.item == 1) {
            requestServer();
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.item == 1) {
            requestServer();
        }
    }

    /*@Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (MainActivity.item == 1) {
            requestServer();
        }
    }*/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (MainActivity.isOver) {
            if (getUserVisibleHint()) {//此处不能用isVisibleToUser进行判断，因为setUserVisibleHint会执行多次，而getUserVisibleHint才是判断真正是否可见的
                if (MainActivity.item == 1) {
                    requestServer();
                }
            }
        }
    }

    @Override
    protected void initView(View view) {
        findViewByID_My(R.id.headView).setPadding(0, (int) CommonUtil.getStatusBarHeight(getActivity()), 0, 0);
        setSpringViewMore(false);//需要加载更多
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                String string = "?token=" + localUserInfo.getToken();
                Request(string);
            }

            @Override
            public void onLoadmore() {

            }
        });
        imageView1 = findViewByID_My(R.id.imageView1);
        imageView1.setOnClickListener(this);
        imageView2 = findViewByID_My(R.id.imageView2);
        imageView2.setOnClickListener(this);
        textView1 = findViewByID_My(R.id.textView1);
        textView2 = findViewByID_My(R.id.textView2);
        textView3 = findViewByID_My(R.id.textView3);
        textView4 = findViewByID_My(R.id.textView4);
        textView5 = findViewByID_My(R.id.textView5);
        textView6 = findViewByID_My(R.id.textView6);
        textView7 = findViewByID_My(R.id.textView7);
        textView8 = findViewByID_My(R.id.textView8);
        tv_confirm = findViewByID_My(R.id.tv_confirm);
        tv_confirm.setOnClickListener(this);
        seekBar = findViewByID_My(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < 1) {
                    num = 1;
                } else {
                    num = progress;
                }
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void initData() {
//        requestServer();
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.imageView1:
                //减
                if (num > 1) {
                    num--;
                }
                calculate();
                break;
            case R.id.imageView2:
                //加
                if (num < Integer.valueOf(model.getUsable_hashrate())) {
                    num++;
                }
                calculate();
                break;
            case R.id.tv_confirm:
                //加入拼团
                showToast(getString(R.string.fragment2_h16)
                        , getString(R.string.app_confirm)
                        , getString(R.string.app_cancel), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                showProgress(true, getString(R.string.app_loading1));
                                HashMap<String, String> params = new HashMap<>();
                                params.put("hk", model.getHk());
                                params.put("mill_id", model.getMill_id());
                                params.put("token", localUserInfo.getToken());
                                params.put("hashrate", num + "");
                                RequestBuy(params);
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                break;
        }
    }

    //计算
    private void calculate() {
        seekBar.setProgress(num);
        textView2.setText(num + "TB");
        textView7.setText(String.format("%.2f",
                num * Double.valueOf(model.getHashrate_price()))
                + getString(R.string.app_type_usdt));//算力费用
        textView8.setText(String.format("%.2f",
                num * Double.valueOf(model.getMill_production_value_fil_money()))
                + getString(R.string.app_ge) + getString(R.string.app_type_fil));//预计产值
    }

    @Override
    public void requestServer() {
        super.requestServer();
//        this.showLoadingPage();
        showProgress(true, getString(R.string.app_loading));
        String string = "?token=" + localUserInfo.getToken();
        Request(string);
    }

    private void Request(String string) {
        OkHttpClientManager.getAsyn(getActivity(), URLs.Fragment2 + string, new OkHttpClientManager.ResultCallback<Fragment2Model>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                MainActivity.isOver = true;
                showErrorPage();
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }

            }

            @Override
            public void onResponse(Fragment2Model response) {
                showContentPage();
                MyLogger.i(">>>>>>>>>算力" + response);
                model = response;
                textView1.setText(getString(R.string.fragment2_h7) + "（" + response.getHashrate_price() + "usdt/TB）");//选择算力
                textView3.setText(getString(R.string.fragment2_h8) + "1TB");//最小
                textView4.setText(getString(R.string.fragment2_h9) + response.getUsable_hashrate() + "TB");//最大
                seekBar.setMax(Integer.valueOf(response.getUsable_hashrate()));
                textView5.setText(response.getMill_number());//矿机编号
                textView6.setText(response.getMill_mining_cycle() + getString(R.string.app_tian));//挖矿周期
//                textView7.setText(response.getMill_production_value_fil_money()+getString(R.string.app_type_usdt));//算力费用
//                textView8.setText(response.getMill_production_value_fil_money() + getString(R.string.app_ge) + getString(R.string.app_type_fil));//预计产值
                hideProgress();
                MainActivity.isOver = true;
            }
        });
    }

    //加入拼团
    private void RequestBuy(Map<String, String> params) {
        OkHttpClientManager.postAsyn(getActivity(), URLs.Fragment2, params, new OkHttpClientManager.ResultCallback<Fragment2BuyModel>() {
            @Override
            public void onError(Request request, String info, Exception e) {
                hideProgress();
                if (!info.equals("")) {
                    showToast(info);
                }
                requestServer();
            }

            @Override
            public void onResponse(Fragment2BuyModel response) {
                hideProgress();
                myToast(getString(R.string.fragment2_h15));
                Bundle bundle = new Bundle();
                bundle.putString("id", response.getId());
                CommonUtil.gotoActivityWithData(getActivity(), MachineDetailActivity.class, bundle);

            }
        }, true);
    }

}
