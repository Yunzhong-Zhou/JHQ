package com.ofc.ofc.view.face.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.ofc.ofc.utils.CommonUtil;


/**
 * 人脸检测区域View
 */
public class FaceDetectRoundView extends View {

    private static final String TAG = FaceDetectRoundView.class.getSimpleName();

    public static final int COLOR_blue = Color.parseColor("#506ef8");
    public static final int COLOR_gray = Color.parseColor("#e3e4e8");
    private Paint paint;


    public static final float SURFACE_HEIGHT = 1000f;
    //chenyw@253.com increase the size of preview space on 2019.6.4
    public static final float WIDTH_SPACE_RATIO = 0.25f;//0.18f;
    public static final float HEIGHT_RATIO = 0.25f; //position
    public static final float HEIGHT_EXT_RATIO = 0.2f;
    public static final int CIRCLE_SPACE = 5;
    public static final int PATH_SPACE = 16;
    public static final int PATH_SMALL_SPACE = 12;
    public static final int PATH_WIDTH = 4;

    public static final int COLOR_BG = Color.parseColor("#FFFFFF");
    public static final int COLOR_RECT = Color.parseColor("#FFFFFF");
    public static final int COLOR_ROUND = Color.parseColor("#FFFFFF");


    private PathEffect mFaceRoundPathEffect = null;
    // new DashPathEffect(new float[]{PATH_SPACE, PATH_SPACE}, 1);
    private Paint mBGPaint;
    private Paint mPathPaint;
    private Paint mFaceRectPaint;
    private Paint mFaceRoundPaint;
    private Rect mFaceRect;
    private Rect mFaceDetectRect;

    private float mX;
    private float mY;
    private float mR;
    private boolean mIsDrawDash = true;

    private Context context;

    public FaceDetectRoundView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        this.context = context;

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        float pathSpace = CommonUtil.dip2px(context, PATH_SPACE);
        float pathSmallSpace = CommonUtil.dip2px(context, PATH_SMALL_SPACE);
        float pathWidth = CommonUtil.dip2px(context, PATH_WIDTH);
        mFaceRoundPathEffect = new DashPathEffect(
                new float[]{pathSpace, dm.heightPixels < SURFACE_HEIGHT
                        ? pathSmallSpace : pathSpace}, 1);

        mBGPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBGPaint.setColor(COLOR_BG);
        mBGPaint.setStyle(Paint.Style.FILL);
        mBGPaint.setAntiAlias(true);
        mBGPaint.setDither(true);

        mPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPathPaint.setColor(COLOR_ROUND);
        mPathPaint.setStrokeWidth(pathWidth);
        mPathPaint.setStyle(Paint.Style.STROKE);
        mPathPaint.setAntiAlias(true);
        mPathPaint.setDither(true);
        mFaceRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFaceRectPaint.setColor(COLOR_RECT);
        mFaceRectPaint.setStrokeWidth(pathWidth);
        mFaceRectPaint.setStyle(Paint.Style.STROKE);
        mFaceRectPaint.setAntiAlias(true);
        mFaceRectPaint.setDither(true);
        mFaceRoundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFaceRoundPaint.setColor(COLOR_ROUND);
        mFaceRoundPaint.setStyle(Paint.Style.FILL);
        mFaceRoundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mFaceRoundPaint.setAntiAlias(true);
        mFaceRoundPaint.setDither(true);


        paint = new Paint();
        paint.setStrokeWidth(pathWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

    }

    public void processDrawState(boolean isDrawDash) {
        mIsDrawDash = isDrawDash;
        postInvalidate();
    }

    public float getRound() {
        return mR;
    }

    public Rect getFaceRoundRect() {
        if (mFaceRect != null) {
            Log.e(TAG, mFaceRect.toString());
        }
        return mFaceRect;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        float canvasWidth = right - left;

        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);

        //设置圆心居中
        float x = canvasWidth / 2;

        //设置圆心高度
        float y = canvasWidth * 2 / 3 - height;
        //设置圆半径
        float r = canvasWidth / 3;

        if (mFaceRect == null) {
            mFaceRect = new Rect((int) (x - r),
                    (int) (y - r),
                    (int) (x + r),
                    (int) (y + r));
        }
        if (mFaceDetectRect == null) {
            float hr = r + (r * HEIGHT_EXT_RATIO);
            mFaceDetectRect = new Rect((int) (x - r),
                    (int) (y - hr),
                    (int) (x + r),
                    (int) (y + hr));
        }
        mX = x;
        mY = y;
        mR = r;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawPaint(mBGPaint);
        if (mIsDrawDash) {
            mPathPaint.setPathEffect(mFaceRoundPathEffect);
        } else {
            mPathPaint.setPathEffect(null);
        }
        canvas.drawCircle(mX, mY, mR + CIRCLE_SPACE, mPathPaint);
//        canvas.drawCircle(mX, mY, mR, mFaceRoundPaint);
        canvas.drawCircle(mX, mY, mR, mFaceRoundPaint);
        paint.setColor(COLOR_gray);
        paint.setStrokeWidth(4);
        paint.setPathEffect(new DashPathEffect(new float[]{4, 4}, 0));
        canvas.drawCircle(mX, mY, mR + 75, paint);//max
        paint.setColor(COLOR_blue);
        canvas.drawCircle(mX, mY, mR, paint); //min
        paint.setPathEffect(null);
        paint.setStrokeWidth(8);
        canvas.drawCircle(mX, mY, mR + 30, paint);


    }

    public static Rect getPreviewDetectRect(int w, int pw, int ph) {
        float round = (w / 2) - ((w / 2) * WIDTH_SPACE_RATIO * 1.8f);
        float x = pw / 2;
        float y = (ph / 2) - ((ph / 2) * HEIGHT_RATIO);
        float r = (pw / 2) > round ? round : (pw / 2);
        float hr = r + (r * HEIGHT_EXT_RATIO);
        Rect rect = new Rect((int) (x - r),
                (int) (y - hr),
                (int) (x + r),
                (int) (y + hr));
        return rect;
    }

}