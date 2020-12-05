package com.fone.fone.view.face.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

/**
 * Copyright (c) 2017-2018 LINKFACE Corporation. All rights reserved.
 **/
public class OcrMediaPlayer {
    public static final String TAG = "LFMediaPlayer";

    private MediaPlayer mMediaPlayer;

    public void setMediaSource(Context context, String mediaName, boolean restart) {
        synchronized (this) {
            release();
            AssetManager am = context.getAssets();
            try {
                AssetFileDescriptor fileDescriptor = am.openFd(mediaName);
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),
                        fileDescriptor.getStartOffset(),
                        fileDescriptor.getLength());
                if (restart) {
                    restartPrepareAndPlay();
                } else {
                    prepareAndPlay();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void prepareAndPlay() {
        try {
            mMediaPlayer.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
    }

    public void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void start() {
        mMediaPlayer.start();
        mMediaPlayer.setLooping(true);
    }

    private void restartPrepareAndPlay() {
        Log.i(TAG, "restartPrepareAndPlay");
        try {
            mMediaPlayer.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.start();
        mMediaPlayer.setLooping(true);
    }
}
