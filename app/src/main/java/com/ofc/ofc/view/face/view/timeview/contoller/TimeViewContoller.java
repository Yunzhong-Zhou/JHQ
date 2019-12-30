package com.ofc.ofc.view.face.view.timeview.contoller;

import android.os.Handler;

import com.ofc.ofc.view.face.view.timeview.Interface.ITimeViewBase;

/**
 * Copyright (c) 2017-2018 LINKFACE Corporation. All rights reserved.
 **/
public class TimeViewContoller implements Runnable{
    public static final String TAG = "TimeViewContoller";

    private ITimeViewBase mTimeView;
    private Handler mHandler = new Handler();
    private float mCurrentTime;
    private int mMaxTime;
    private boolean mStop;
    private CallBack mCallBack;

    public interface CallBack{
        void onTimeEnd();
    }

    public TimeViewContoller(ITimeViewBase view){
        mTimeView = view;
        mMaxTime = mTimeView.getMaxTime();
    }

    @Override
    public void run() {
        if(mStop){
            return;
        }
        if(mCurrentTime > mMaxTime){
            onTimeEnd();
            return ;
        }
        if(mHandler != null)
            mHandler.postDelayed(this, 50);

        mCurrentTime = mCurrentTime + 0.05f;
        if(mTimeView != null)
            mTimeView.setProgress(mCurrentTime);
    }

    public void stop(){
        mStop = true;
        if(mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    public void start(){
        start(true);
    }

    public void start(boolean again){
        if(!again){
            if(!mStop){
                return;
            }
            mStop = false;
            if(mCurrentTime > mMaxTime){
                onTimeEnd();
                return;
            }
            if(mHandler != null) {
                mHandler.removeCallbacksAndMessages(null);
                mHandler.post(this);
            }
        }else{
            reset();
        }
    }

    private void onTimeEnd() {
        hide();
        if(null != mCallBack){
            mCallBack.onTimeEnd();
        }
    }

    public void setCallBack(CallBack callback){
        mCallBack = callback;
    }

    private void reset(){
        show();
        mCurrentTime = 0;
        if(mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler.post(this);
        }
    }

    public void hide(){
       stop();
        mTimeView.hide();
    }

    public void show(){
        mStop = false;
        mTimeView.show();
    }

    public void remove(){
        stop();
        mTimeView = null;
        mCallBack = null;
        mHandler = null;
    }
}

