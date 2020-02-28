package com.ofc.ofc.view.face.frameLayout;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chuanglan.cllc.CLLCSDKManager;
import com.ofc.ofc.R;
import com.ofc.ofc.view.face.LivenessActivity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.fragment.app.Fragment;

/**
 * 相机Fragment
 **/
public class CameraOverlapFragment extends Fragment {
    private static final String TAG = "CameraOverlapFragment";
    protected Camera mCamera = null;
    protected CameraInfo mCameraInfo = null;
    protected boolean mIsCameraInit = false;
    protected SurfaceView mSurfaceview = null;
    protected SurfaceHolder mSurfaceHolder = null;
    protected int mCameraFacing = CameraInfo.CAMERA_FACING_FRONT;
    private boolean mHasFrontCamera;
    private static int PREVIEW_HEIGHT = 640;
    private static int PREVIEW_WIDTH = 640;

    private byte[] mPreviewCallbackData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.linkface_fragment_camera_overlap, container,
                false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mIsCameraInit && mCamera == null) {
            openCamera();
        }
    }

    @Override
    public void onPause() {
        releaseCamera();
        super.onPause();
    }

    private void openCamera() {
        releaseCamera();
        mHasFrontCamera = false;
        openCameraFront();
        if (!mHasFrontCamera) {
            openCamera(true);
        }
        try {
            mCamera.setPreviewDisplay(mSurfaceHolder);
            initCameraParameters();
            initPreviewCallbackData();
            addPreviewCallbackBuffer();
        } catch (Exception ex) {
            releaseCamera();
            onOpenCameraError();
        }
    }

    private void initPreviewCallbackData() {
        if (mPreviewCallbackData == null) {
            mPreviewCallbackData = new byte[PREVIEW_WIDTH * PREVIEW_HEIGHT * 3 / 2];
        }
    }

    protected void addPreviewCallbackBuffer() {
        if (mCamera != null) {
            mCamera.addCallbackBuffer(mPreviewCallbackData);
        }
    }

    private void openCameraFront() {
        openCamera(false);
    }

    private void openCamera(boolean any) {
        CameraInfo info = new CameraInfo();
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            Camera.getCameraInfo(i, info);
            boolean front = info.facing == mCameraFacing;
            if (any || front) {
                if (front) {
                    mHasFrontCamera = true;
                }
                try {
                    mCamera = Camera.open(i);
                    mCameraInfo = info;
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (mCamera != null) {
                        mCamera.release();
                        mCamera = null;
                    }
                    continue;
                }
                break;
            }
        }
    }

    private void onOpenCameraError() {

        Toast.makeText(getActivity().getApplication(),getString(R.string.faceverified_h6),Toast.LENGTH_LONG).show();
        onErrorHappen();
    }

    protected void onErrorHappen() {
        if (getActivity() == null) {
            return;
        }
        ((LivenessActivity) getActivity()).onErrorHappen();
    }

    private void initView(View view) {
        mSurfaceview = (SurfaceView) view.findViewById(R.id.surfaceViewCamera);
        setSurfaceviewSize(mSurfaceview);

        mSurfaceHolder = mSurfaceview.getHolder();
        mSurfaceHolder.addCallback(mSurfaceViewCallBack);
    }

    /**
     *  设置预览区域的大小和位置
     */
    private void setSurfaceviewSize(SurfaceView surfaceView){
        Resources resources = getActivity().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = wm.getDefaultDisplay().getWidth();
        int screenHeight = wm.getDefaultDisplay().getHeight() - height;

        int x = screenWidth / 2;
        //设置圆心高度
        int y = screenWidth * 2 / 3 - height;
        //设置圆半径
        int r = screenWidth/3;

        RelativeLayout.LayoutParams para1 = (RelativeLayout.LayoutParams) mSurfaceview.getLayoutParams();

        para1.leftMargin = x-r;
        para1.topMargin = y-r;
        para1.height = r*2;
        para1.width = r*2;
        surfaceView.setLayoutParams(para1);
    }

    //设置预览尺寸
    private void setSurfaceviewSize(boolean across){
        RelativeLayout.LayoutParams para1 = (RelativeLayout.LayoutParams) mSurfaceview.getLayoutParams();
        if(across){
            if(PREVIEW_WIDTH > PREVIEW_HEIGHT){
                int margintop = para1.topMargin + para1.height/2 - (PREVIEW_WIDTH/PREVIEW_HEIGHT*para1.width)/2;
                para1.topMargin = margintop;
                para1.height = PREVIEW_WIDTH/PREVIEW_HEIGHT*para1.width;
            }else {
                int marginLeft = para1.leftMargin + para1.width/2 - (PREVIEW_HEIGHT/PREVIEW_WIDTH*para1.height)/2;
                para1.leftMargin = marginLeft;
                para1.width = PREVIEW_WIDTH/PREVIEW_HEIGHT*para1.height;
            }
        }else {
            if(PREVIEW_WIDTH > PREVIEW_HEIGHT){
                int marginLeft = para1.leftMargin + para1.width/2 - (PREVIEW_WIDTH/PREVIEW_HEIGHT*para1.height)/2;
                para1.leftMargin = marginLeft;
                para1.height = PREVIEW_HEIGHT*para1.width/para1.height;
            }else {
                int margintop = para1.topMargin + para1.height/2 - (PREVIEW_HEIGHT/PREVIEW_WIDTH*para1.width)/2;
                para1.topMargin = margintop;
                para1.width = PREVIEW_HEIGHT/PREVIEW_WIDTH*para1.width;
            }
        }
        mSurfaceview.setLayoutParams(para1);
    }


    private SurfaceHolder.Callback mSurfaceViewCallBack = new SurfaceHolder.Callback() {
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format,
                                   int width, int height) {
            initCameraParameters();
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mCamera = null;
            openCamera();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            releaseCamera();
            mIsCameraInit = false;
        }
    };

    protected void initCameraParameters() {
        mIsCameraInit = true;
        if (mCamera == null) {
            return;
        }

        try {
            Camera.Parameters parameters = mCamera.getParameters();
            parameters.setPreviewFormat(ImageFormat.NV21);

            List<Size> previewSizes = parameters.getSupportedPreviewSizes();

            Size size = getNearestRatioSize(previewSizes,720,720);

            PREVIEW_WIDTH = size.width;
            PREVIEW_HEIGHT = size.height;

            parameters.setPreviewSize(PREVIEW_WIDTH, PREVIEW_HEIGHT);

            if (parameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            }
            parameters.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);
            if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                parameters.set("orientation", "portrait");
                parameters.set("rotation", 90);
                if (mCameraInfo.facing == CameraInfo.CAMERA_FACING_FRONT && mCameraInfo.orientation == 90) {
                    mCamera.setDisplayOrientation(270);
                } else {
                    mCamera.setDisplayOrientation(90);
                }
                //横屏调整
                setSurfaceviewSize(true);
            } else {
                parameters.set("orientation", "landscape");
                mCamera.setDisplayOrientation(0);
                //竖屏调整
                setSurfaceviewSize(false);
            }

            mCamera.setParameters(parameters);
            mCamera.setPreviewCallbackWithBuffer(mPreviewCallback);
            mCamera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void releaseCamera() {
        if (null == mCamera) {
            return;
        }
        try {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
        } catch (Exception e) {
        }
        mCamera = null;
    }

   /* Camera.PreviewCallback mPreviewCallback = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
            MyLogger.i(">>>>>>>"+PREVIEW_WIDTH+">>>>>>"+PREVIEW_HEIGHT+"》》data"+data.length);
            CLLCSDKManager.getInstance().setPicData(data,camera,PREVIEW_WIDTH,PREVIEW_HEIGHT,mCameraInfo.orientation);


        }
    };*/
    private int i = 0;
    private byte[] mCurPreviewBuffer = null;

    Camera.PreviewCallback mPreviewCallback = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
            if(i % 5 == 0) {
                CLLCSDKManager.getInstance().setPicData(data, camera, PREVIEW_WIDTH, PREVIEW_HEIGHT, mCameraInfo.orientation);
            }else {
                if (mCurPreviewBuffer == null) {
                    int previewFormat = ImageFormat.NV21; // the default.
                    if (camera != null) {
                        Camera.Parameters parameters = camera.getParameters();
                        previewFormat = parameters.getPreviewFormat();
                    }
                    int bytesPerPixel = ImageFormat.getBitsPerPixel(previewFormat) / 8;
                    int bufferSize = PREVIEW_WIDTH * PREVIEW_HEIGHT * bytesPerPixel * 3 / 2;

                    mCurPreviewBuffer = new byte[bufferSize];
                }
                camera.addCallbackBuffer(mCurPreviewBuffer);
            }
            i++;
        }
    };


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPreviewCallbackData != null) {
            mPreviewCallbackData = null;
        }
        CLLCSDKManager.getInstance().remove();
    }


    /**
     * 拍照需要的，最贴显示区域的比例)
     *
     * @param sizeList
     * @param screenWidth
     * @param screenHeight
     * @return
     */
    public static Size getNearestRatioSize(List<Size> sizeList,final int screenWidth,final int screenHeight) {
        Collections.sort(sizeList, new Comparator<Size>() {
            @Override
            public int compare(Size lhs, Size rhs) {
                float w1 = lhs.width > lhs.height ? lhs.height : lhs.width;
                float w2 = rhs.width > rhs.height ? rhs.height : rhs.width;
                float h1 = lhs.height > lhs.width ? lhs.height : lhs.width;
                float h2 = rhs.height > rhs.width ? rhs.height : rhs.width;

                float diff1 = Math.abs(w1 / h1 - screenWidth / (float) screenHeight);
                float diff2 = Math.abs(w2 / h2 - screenWidth / (float) screenHeight);

                if(diff1 > diff2){
                    return 1;
                }else if(diff1 == diff2){
                    float diff3 = Math.abs(w1 - screenWidth);
                    float diff4 = Math.abs(w2 - screenWidth);
                    if(diff3 > diff4){
                        return 1;
                    }else if(diff3 == diff4){
                        if(w1 > w2){
                            return 1;
                        }else {
                            return -1;
                        }
                    }else {
                        return -1;
                    }
                }else {
                    return -1;
                }
            }
        });
        return sizeList.get(0);
    }

    public void resetDetector(){
        if (mIsCameraInit && mCamera == null) {
            openCamera();
            CLLCSDKManager.getInstance().resetDetector();
        }
    }

    public void stopDetector(){
        releaseCamera();
    }
}

