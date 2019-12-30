package com.ofc.ofc.view.face;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chuanglan.cllc.CLLCSDKManager;
import com.chuanglan.cllc.constant.LivenessState;
import com.chuanglan.cllc.listener.LivenessListener;
import com.chuanglan.cllc.modle.CLLCImageResult;
import com.chuanglan.cllc.modle.CLLCParameter;
import com.chuanglan.cllc.tool.CLLCSensorManager;
import com.ofc.ofc.R;
import com.ofc.ofc.activity.VerfiedResultActivity;
import com.ofc.ofc.base.BaseActivity;
import com.ofc.ofc.utils.CommonUtil;
import com.ofc.ofc.utils.MyLogger;
import com.ofc.ofc.view.face.listener.OcrAlertDialogListener;
import com.ofc.ofc.view.face.utils.CLLCMediaPlayer;
import com.ofc.ofc.view.face.view.CLLCAlertDialog;
import com.ofc.ofc.view.face.view.LFGifView;
import com.ofc.ofc.view.face.view.timeview.CircleTimeView;
import com.ofc.ofc.view.face.view.timeview.contoller.TimeViewContoller;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class LivenessActivity extends BaseActivity {
    int type = 1;//  1、大陆 2、海外
    String number = "", name = "", v_type = "";
    byte[] huotiimg = null;
    //选择图片及上传
    ArrayList<String> listFileNames = new ArrayList<>();
    ArrayList<File> listFiles = new ArrayList<>();

    private static final int CURRENT_ANIMATION = -1;

    private Context mContext;

    private LFGifView mGvView;
    private TimeViewContoller mTimeViewContoller;
    private CircleTimeView mTimeView;
    private TextView mNoteTextView;
    private ViewGroup mVGBottomDots;

    private int[] mDetectList = null;//动作序列

    private boolean mSoundNoticeOrNot = true;   //声音控制true为打开, false为关闭。
    private CLLCMediaPlayer mMediaPlayer = new CLLCMediaPlayer();
    private CLLCAlertDialog cllcAlertDialog;

    private CLLCSensorManager cllcSensorManager;

    private ProgressDialog m_Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liveness);
        mContext = this;
    }

    @Override
    protected void initView() {
        mGvView = (LFGifView) findViewById(R.id.id_gv_play_action);
        mTimeView = (CircleTimeView) findViewById(R.id.t_view);
        mTimeViewContoller = new TimeViewContoller(mTimeView);
        mNoteTextView = (TextView) findViewById(R.id.noteText);

        /**
         *  动作序列
         */

        mDetectList = new int[4];
        mDetectList[0] = LivenessState.BLINK;
        mDetectList[1] = LivenessState.MOUTH;
        mDetectList[2] = LivenessState.NOD;
        mDetectList[3] = LivenessState.YAW;
        /*List<String> sequenceDataList = new ArrayList<>();
        sequenceDataList.add("BLINK");//眨眼
        sequenceDataList.add("MOUTH");//张嘴
        sequenceDataList.add("NOD");//点头
        sequenceDataList.add("YAW");//摇头
        mDetectList = new int[sequenceDataList.size()];
        for (int i = 0; i < sequenceDataList.size(); i++) {
            mDetectList[i] = LivenessState.BLINK;
            mDetectList[i] = LivenessState.MOUTH;
            mDetectList[i] = LivenessState.NOD;
            mDetectList[i] = LivenessState.YAW;
        }*/
        type = getIntent().getIntExtra("type", 1);
        name = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("cardNo");
        v_type = getIntent().getStringExtra("v_type");
        /**
         *  设置检测动作
         */
        CLLCParameter parameter = new CLLCParameter();
        //动作序列
        parameter.setMotionList(mDetectList);
        //输出类型
        parameter.setOutputType("singleImg");//singleImg 单图， multiImg 多图，video 低质量视频
        //难易程度
        parameter.setComplexity("normal");//easy 容易， normal 正常，hard 困难，hell 究极
        //姓名
        parameter.setName(name);
        //身份证号
        parameter.setCardNo(getIntent().getStringExtra("cardNo"));

        CLLCSDKManager.getInstance().setCLLCParameter(parameter);
        CLLCSDKManager.getInstance().setLivenessListener(new LivenessListener() {
            /**
             *  活体检测流程回调
             */
            @Override
            public void onLivenessDetect(int value, int status, byte[] livenessEncryptResult, byte[] videoResult, CLLCImageResult[] imageResult) {
                onLivenessDetectCallBack(value, status, livenessEncryptResult, videoResult, imageResult);
            }

            /**
             *  后台开始人证对比
             */
            @Override
            public void startVerify() {
                showDialog();
            }

            /**
             *  人证对比返回()
             */
            @Override
            public void verifyResult(int code, String result) {
                dismissDialog();
                if (code == 0) {
                    /**
                     *  result 的解析仅限 默认URL 请求回调数据解析  自定义URL 数据非此解析方案
                     */
                    dataAnalysis(result);
                } else {
                    skipResultActivity(false, result, result);
                }
            }
        }, null);


        mVGBottomDots = (ViewGroup) findViewById(R.id.viewGroup);
        if (mDetectList.length >= 1) {
            for (int i = 0; i < mDetectList.length; i++) {
                TextView tvBottomCircle = new TextView(this);
                tvBottomCircle.setBackgroundResource(R.drawable.yuanjiao_5_lvse);
                tvBottomCircle.setEnabled(true);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(CommonUtil.dip2px(LivenessActivity.this, 6),
                        CommonUtil.dip2px(LivenessActivity.this, 6));
                layoutParams.leftMargin = CommonUtil.dip2px(LivenessActivity.this, 6);
                mVGBottomDots.addView(tvBottomCircle, layoutParams);
            }
        }

        cllcSensorManager = new CLLCSensorManager(this);

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void updateView() {
        titleView.setTitle(getString(R.string.faceverified_h7));
    }


    private void startAnimation(int animation) {
        if (animation != CURRENT_ANIMATION) {
            mGvView.setMovieResource(animation);
            if (isDialogShowing(null)) {
                return;
            }
        }
        mTimeViewContoller.start();
        mTimeViewContoller.setCallBack(new TimeViewContoller.CallBack() {
            @Override
            public void onTimeEnd() {
                setLivenessState(true);
                showDialog(getString(R.string.faceverified_h15));
            }
        });
    }

    private void updateUi(int stringId, int animationId, int number) {
        mNoteTextView.setText(getStringWithID(stringId));
        if (animationId != 0) {
            startAnimation(animationId);
        }
        if (number - 2 >= 0) {
            View childAt = mVGBottomDots.getChildAt(number - 2);
            childAt.setEnabled(false);
        }
        playSoundNotice(number);
    }


    private void playSoundNotice(int step) {
        if (step > 0) {
            if (mDetectList[step - 1] == 0) {
                if (mSoundNoticeOrNot) {
                    mMediaPlayer.setMediaSource(mContext, "notice_blink.mp3", true);
                }
            } else if (mDetectList[step - 1] == 1) {
                if (mSoundNoticeOrNot) {
                    mMediaPlayer.setMediaSource(mContext, "notice_nod.mp3", true);
                }
            } else if (mDetectList[step - 1] == 2) {
                if (mSoundNoticeOrNot) {
                    mMediaPlayer.setMediaSource(mContext, "notice_mouth.mp3", true);
                }
            } else if (mDetectList[step - 1] == 3) {
                if (mSoundNoticeOrNot) {
                    mMediaPlayer.setMediaSource(mContext, "notice_yaw.mp3", true);
                }
            }
        }
    }

    private String getStringWithID(int id) {
        return getResources().getString(id);
    }

    private boolean isDialogShowing(String msg) {
        if (cllcAlertDialog != null && cllcAlertDialog.isShowing()) {
            if (msg != null) {
                cllcAlertDialog.setMsg(msg);
            }
            return true;
        }
        return false;
    }


    private void onLivenessDetectCallBack(final int value, final int status, final byte[] livenessEncryptResult, final byte[] videoResult, final CLLCImageResult[] imageResult) {

        if (value == LivenessState.BLINK) {       //眨眼睛
            Log.e("sdsdfafdsfdsa", "--------眨眼睛----------->" + value);
            updateUi(R.string.note_blink, R.raw.raw_detect_blink, status + 1);
        } else if (value == LivenessState.NOD) {    //请点点头
            Log.e("sdsdfafdsfdsa", "--------请点点头----------->" + value);
            updateUi(R.string.note_nod, R.raw.raw_detect_nod, status + 1);
        } else if (value == LivenessState.MOUTH) {  //请张张嘴
            Log.e("sdsdfafdsfdsa", "--------请张张嘴----------->" + value);
            updateUi(R.string.note_mouth, R.raw.raw_detect_mouth, status + 1);
        } else if (value == LivenessState.YAW) {    //请瑶瑶头
            Log.e("sdsdfafdsfdsa", "--------请瑶瑶头----------->" + value);
            updateUi(R.string.note_yaw, R.raw.raw_detect_yaw, status + 1);
        } else if (value == LivenessState.SUCCESS) { //成功
            updateTheLastStepUI(mVGBottomDots);
            /**
             *  处理图片（用户自己可以处理图片 视频保存）
             */
            saveFinalEncrytFile(livenessEncryptResult, videoResult, imageResult);
        } else if (value == LivenessState.TRACKING_MISSED) { //采集失败，再试一次吧（追踪目标丢失）
            showDialog(getString(R.string.faceverified_h16));
        } else if (value == LivenessState.PREPARE) {
            //准备
        } else if (value == LivenessState.START) { //开始
            removeDetectWaitUI();
        } else if (value == LivenessState.ERROR) { //SDK异常
            String msg;
            switch (status) {
                case 1002:
                    msg = getString(R.string.faceverified_h17);
                    break;
                case 1003:
                    msg = getString(R.string.faceverified_h18);
                    break;
                case 1004:
                    msg = getString(R.string.faceverified_h19);
                    break;
                case 1005:
                    msg = getString(R.string.faceverified_h20);
                    break;
                case 1006:
                    msg = getString(R.string.faceverified_h21);
                    break;
                case 1007:
                    msg = getString(R.string.faceverified_h22);
                    break;
                default:
                    msg = getString(R.string.faceverified_h23);
                    break;
            }

            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

            onErrorHappen();
        }
    }


    public void onErrorHappen() {
        finish();
    }

    private void hideIndicateView() {
        if (mGvView != null) {
            mGvView.setVisibility(View.GONE);
        }
        if (mVGBottomDots != null) {
            mVGBottomDots.setVisibility(View.GONE);
        }
        if (mNoteTextView != null) {
            mNoteTextView.setVisibility(View.GONE);
        }
    }

    private void showIndicateView() {
        if (mGvView != null) {
            mGvView.setVisibility(View.VISIBLE);
        }
        if (mVGBottomDots != null) {
            mVGBottomDots.setVisibility(View.VISIBLE);
        }
        if (mNoteTextView != null) {
            mNoteTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cllcSensorManager.registerListener(mSensorEventListener);
        if (isDialogShowing(null)) {
            mMediaPlayer.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        cllcSensorManager.unregisterListener(mSensorEventListener);
        mMediaPlayer.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimeViewContoller != null) {
            mTimeViewContoller.remove();
            mTimeViewContoller = null;
        }
        ocrAlertDialogClose();
        dismissDialog();
    }

    private void updateTheLastStepUI(ViewGroup viewGroup) {
        mMediaPlayer.release();

        hideTimeContoller();

        hideIndicateView();
    }


    private void showDialog(String msg) {
        if (isDialogShowing(msg)) {
            return;
        }

        if (mDetectList.length >= 1) {
            for (int i = 0; i < mDetectList.length; i++) {
                View childAt = mVGBottomDots.getChildAt(i);
                if (childAt != null) {
                    childAt.setEnabled(true);
                }
            }
        }

        hideTimeContoller();

        hideIndicateView();

        setLivenessState(true);

        cllcAlertDialog = new CLLCAlertDialog(mContext, msg, new OcrAlertDialogListener() {
            @Override
            public void onClick(View view) {
                /**
                 *  设置动画恢复
                 *
                 *  showIndicateView 动画UI显示
                 *  restartAnimationAndLiveness  重置检测判断逻辑
                 */
                showIndicateView();

                restartAnimationAndLiveness();
            }
        });
        if (((Activity) mContext).isFinishing()) {
            return;
        }
        cllcAlertDialog.show();
        mMediaPlayer.release();
    }

    public void ocrAlertDialogClose() {
        if (cllcAlertDialog != null) {
            if (cllcAlertDialog.isShowing()) {
                cllcAlertDialog.dismiss();
            }
            cllcAlertDialog = null;
        }
    }

    private void hideTimeContoller() {
        if (mTimeViewContoller != null) {
            mTimeViewContoller.hide();
        }
    }


    private void restartAnimationAndLiveness() {
        setLivenessState(false);
        if (mDetectList.length >= 1) {
            View childAt = mVGBottomDots.getChildAt(0);
            childAt.setEnabled(false);
        }
        mMediaPlayer.release();
        removeDetectWaitUI();
    }


    private void setLivenessState(boolean pause) {
        if (pause) {
            CLLCSDKManager.getInstance().stopLiveness();
        } else {
            CLLCSDKManager.getInstance().startLiveness();
        }
    }


    /**
     * 传感器状态监听
     */
    SensorEventListener mSensorEventListener = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            /**
             *  添加传感器检测
             */
            CLLCSDKManager.getInstance().addSequentialInfo(event.sensor.getType(), event.values);
        }
    };

    public void saveFinalEncrytFile(byte[] livenessEncryptResult, byte[] videoResult, CLLCImageResult[] imageResult) {
        /**
         * 用户自己处理图片或视频数据
         */
        for (int i = 0; i < imageResult.length; i++) {
            //            MyLogger.i(">>>>活体照片imageResult>>>"+imageResult[i].image);
            huotiimg = imageResult[i].image;
        }
       /* for (int i = 0; i < videoResult.length; i++) {
            MyLogger.i(">>>>活体视频videoResult>>>"+videoResult[i]);
        }*/
    }

    private void skipResultActivity(boolean result, String hint, String msg) {
        if (type == 1) {
            //大陆
            Intent intent = new Intent(LivenessActivity.this, VerfiedResultActivity.class);
            intent.putExtra("result", result);
            intent.putExtra("msg", msg);

            intent.putExtra("name", name);
            intent.putExtra("cardNo", number);
            intent.putExtra("v_type", v_type);

            if (!result) {
                intent.putExtra("hint", hint);
            }
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(LivenessActivity.this, VerfiedResultActivity.class);
            intent.putExtra("result", result);
            intent.putExtra("msg", msg);

            intent.putExtra("name", name);
            intent.putExtra("cardNo", number);
            intent.putExtra("v_type", v_type);

            if (!result) {
                intent.putExtra("hint", hint);
            }
            startActivity(intent);
            finish();
//            BitmapFactory.decodeByteArray(huotiimg, 0, huotiimg.length);
            //海外
//            showProgress(false, getString(R.string.app_loading1));

           /* String[] filenames = new String[]{};
            File[] files = new File[]{};
            for (int i = 0; i < listFiles.size(); i++) {
                filenames = listFileNames.toArray(new String[i]);
                files = listFiles.toArray(new File[i]);
            }*/

            /*Map<String, String> params = new HashMap<>();
            params.put("appId", "k01UMTdN");
            params.put("appKey", "SGKuoaNz");
//            params.put("liveImage", huotiimg);
//            params.put("idCardImage", );
            params.put("imageType", "file");
            request4(result, hint, msg, params);

            OkhttpUtil.okHttpUploadFile(URLs.Verified4,
                    params,
                    listFiles,
                    );*/
        }


    }

    private void request4(boolean result, String hint, String msg, Map<String, String> params) {
        /*OkHttpClientManager.postAsyn(LivenessActivity.this, URLs.Verified4,params,
                new OkHttpClientManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, String info, Exception e) {
                        hideProgress();
                        if (!info.equals("")) {
                            myToast(info);
                        }
                    }

                    @Override
                    public void onResponse(String response) {
                        MyLogger.i(">>>>>>>>>认证加载4 - 海外" + response);
                        hideProgress();

                    }
                });*/

    }

    private void removeDetectWaitUI() {
        switch (mDetectList[0]) {
            case LivenessState.BLINK:
                updateUi(R.string.note_blink, R.raw.raw_detect_blink, 1);
                break;
            case LivenessState.NOD:
                updateUi(R.string.note_nod, R.raw.raw_detect_nod, 1);
                break;
            case LivenessState.MOUTH:
                updateUi(R.string.note_mouth, R.raw.raw_detect_mouth, 1);
                break;
            case LivenessState.YAW:
                updateUi(R.string.note_yaw, R.raw.raw_detect_yaw, 1);
                break;
        }
    }

    private void dataAnalysis(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            if (jsonObject.getString("code").equals("200000")) {
                JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                String status = jsonObject1.getString("status");
                if (status.equals("OK")) {
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("identity");
                    if (jsonObject2.getBoolean("validity")) {
                        if (jsonObject1.has("confidence")) {
                            if (jsonObject1.getDouble("confidence") > 0.7) {
                                /**
                                 * confidence 置信度。值为 0~1，值越大表示两张照片属于同一个人的可能性越大
                                 *  0.4  十分之一错误率
                                 *  0.5  百分之一错误率
                                 *  0.6  千分之一错误率
                                 *  0.7  万分之一错误率
                                 *  0.8  十万分之一错误率
                                 *  0.9  百万分之一错误率
                                 */

                                skipResultActivity(true, getString(R.string.faceverified_h2), result);

                                MyLogger.i(">>>>>>>>>>>>成功数据" + result);

                            } else {
                                skipResultActivity(false, getString(R.string.faceverified_h24), result);
                            }
                        } else {
                            skipResultActivity(false, getErrorMsg(jsonObject2.getString("reason")), result);
                        }
                    } else {
                        skipResultActivity(false, getString(R.string.faceverified_h25), result);
                    }
                } else {
                    switch (status) {
                        case "PHOTO_SERVICE_ERROR":
                            skipResultActivity(false, getErrorMsg(jsonObject1.getString("reason")), result);
                            break;
                        case "ENCODING_ERROR":
                            skipResultActivity(false, getString(R.string.faceverified_h26), result);
                            break;
                        case "IMAGE_ID_NOT_EXIST":
                            skipResultActivity(false, getString(R.string.faceverified_h27), result);
                            break;
                        case "IMAGE_FILE_SIZE_TOO_BIG":
                            skipResultActivity(false, getString(R.string.faceverified_h28), result);
                            break;
                        case "NO_FACE_DETECTED":
                            skipResultActivity(false, getString(R.string.faceverified_h29), result);
                            break;
                        case "CORRUPT_IMAGE":
                            skipResultActivity(false, getString(R.string.faceverified_h30), result);
                            break;
                        case "INVALID_IMAGE_FORMAT_OR_SIZE":
                            skipResultActivity(false, getString(R.string.faceverified_h31), result);
                            break;
                        case "INVALID_ARGUMENT":
                            skipResultActivity(false, getErrorMsg(jsonObject1.getString("reason")), result);
                            break;
                        case "RATE_LIMIT_EXCEEDED":
                            skipResultActivity(false, getString(R.string.faceverified_h32), result);
                            break;
                        case "OUT_OF_QUOTA":
                            skipResultActivity(false, getString(R.string.faceverified_h33), result);
                            break;
                        case "NOT_FOUND":
                            skipResultActivity(false, getString(R.string.faceverified_h34), result);
                            break;
                        case "INTERNAL_ERROR":
                            skipResultActivity(false, getString(R.string.faceverified_h35), result);
                            break;
                        default:
                            skipResultActivity(false, getString(R.string.faceverified_h36), result);
                            break;
                    }
                }
            } else {
                skipResultActivity(false, jsonObject.getString("message"), result);
            }
        } catch (JSONException e) {
            skipResultActivity(false, e.toString(), result);
        }
    }

    private String getErrorMsg(String reason) {
        String msg;
        switch (reason) {
            case "Gongan photo doesn’t exist":
                msg = getString(R.string.faceverified_h37);
                break;
            case "Name and idcard number doesn’t match":
                msg = getString(R.string.faceverified_h38);
                break;
            case "Invalid idcard number":
                msg = getString(R.string.faceverified_h39);
                break;
            case "Gongan service timeout":
                msg = getString(R.string.faceverified_h40);
                break;
            case "Gongan service is unavailable temporarily":
                msg = getString(R.string.faceverified_h41);
                break;
            case "Network error":
                msg = getString(R.string.faceverified_h42);
                break;
            case "Invalid name":
                msg = getString(R.string.faceverified_h43);
                break;
            default:
                msg = getString(R.string.faceverified_h44);
                break;
        }
        return msg;
    }

    private void showDialog() {
        if (m_Dialog == null) {
            m_Dialog = ProgressDialog.show(LivenessActivity.this, getString(R.string.faceverified_h45), getString(R.string.faceverified_h46), true);
        } else {
            if (!m_Dialog.isShowing()) {
                m_Dialog.dismiss();
                m_Dialog = null;
                showDialog();
            }
        }
    }

    private void dismissDialog() {
        if (m_Dialog != null) {
            m_Dialog.dismiss();
            m_Dialog = null;
        }
    }
}


