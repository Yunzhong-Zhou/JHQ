package com.ofc.ofc.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.ofc.ofc.R;
import com.ofc.ofc.utils.MyLogger;

/**
 * Created by zyz on 2019/5/16.
 * 自定义圆弧
 */
public class MyArcView extends View {
    Context mContext;
    //圆心
    private Point mCenterPoint;
    //半径
    private float mRadius;
    //圆的外接矩形
    private RectF mRectF;

    //绘制圆弧
    private Paint mArcPaint;

    //绘制虚线圆弧
    private Paint mDottedLinePaint;

    //绘制小圆
    private Paint mCirclePaint;

    //绘制图片
    private Paint mBitPaint;
    Bitmap bitmap;
    private double angdeg = 180;

    //绘制文字
    private Paint mTextPaint;


    public MyArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();//初始化画笔
    }

    //初始化画笔
    private void initPaint() {
        mCenterPoint = new Point();
        mRectF = new RectF();

        //圆弧画笔
        mArcPaint = new Paint();
        mArcPaint.setStyle(Paint.Style.STROKE);//设置画圆弧的画笔的属性为描边(空心)
        mArcPaint.setColor(Color.WHITE);
        mArcPaint.setAntiAlias(true);//取消锯齿
        mArcPaint.setStrokeWidth(20);//线宽

        //虚线画笔
        mDottedLinePaint = new Paint();
        mDottedLinePaint.setAntiAlias(true);
        mDottedLinePaint.setStyle(Paint.Style.STROKE);
        mDottedLinePaint.setColor(mContext.getResources().getColor(R.color.orange));
        //设置虚线
        PathEffect effects = new DashPathEffect(new float[]{5, 5, 5, 5}, 2);
        mDottedLinePaint.setPathEffect(effects);

        //小圆画笔
        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(mContext.getResources().getColor(R.color.orange));
        mCirclePaint.setAntiAlias(true);//取消锯齿
        mCirclePaint.setStrokeWidth(8);//设置画笔粗细

        //图片画笔
        bitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitPaint.setFilterBitmap(true);
        mBitPaint.setDither(true);

        //文字
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.parseColor("#FFFFFF"));
        mTextPaint.setTextSize(28);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //半径
        int minSize = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - 2 * (int) 20,
                getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - 2 * (int) 20);
        mRadius = minSize / 2;


        //圆心坐标
        mCenterPoint.x = getMeasuredWidth() / 2;
        mCenterPoint.y = getMeasuredHeight() / 2;

        //绘制圆弧的边界
        mRectF.left = mCenterPoint.x - mRadius - 20 / 2 + 80;
        mRectF.top = mCenterPoint.y - mRadius - 20 / 2 + 80;
        mRectF.right = mCenterPoint.x + mRadius + 20 / 2 - 80;
        mRectF.bottom = mCenterPoint.y + mRadius + 20 / 2 - 80;
        MyLogger.i("onSizeChanged: 控件大小 = " + "(" + getMeasuredWidth() + ", " + getMeasuredHeight() + ")"
                + ";圆心坐标 = " + mCenterPoint.toString()
                + ";圆半径 = " + mRadius
                + ";圆的外接矩形 = " + mRectF.toString());
        //画圆弧
        canvas.drawArc(mRectF, 0, 180, false, mArcPaint);//画圆弧，这个时候，绘制没有经过圆心

        //画虚线
        canvas.drawArc(mRectF, 0, 180, false, mDottedLinePaint);//画圆弧，这个时候，绘制没有经过圆心

        //画小圆
        drawSmallCircle(canvas, 0, (mRadius + 10 - 80));
        drawSmallCircle(canvas, 45, (mRadius + 10 - 80));
        drawSmallCircle(canvas, 90, (mRadius + 10 - 80));
        drawSmallCircle(canvas, 135, (mRadius + 10 - 80));
        drawSmallCircle(canvas, 180, (mRadius + 10 - 80));

        //画文字
        drawText(canvas, 180, (mRadius - 0), "100");
        drawText(canvas, 140, (mRadius + 15), "1000");
        drawText(canvas, 97, (mRadius - 15), "5000");
        drawText(canvas, 50, (mRadius - 30), "10000");
        drawText(canvas, 0, (mRadius - 50), "20000");

        //画进度图片
        drawProgress(canvas, angdeg, mRadius + 10 - 80);
        /*drawProgress(canvas, 170, mRadius + 10 - 80);
        drawProgress(canvas, 145, mRadius + 10 - 80);
        drawProgress(canvas, 95, mRadius + 10 - 80);
        drawProgress(canvas, 65, mRadius + 10 - 80);
        drawProgress(canvas, 30, mRadius + 10 - 80);*/
    }

    //画小圆
    private void drawSmallCircle(Canvas canvas, double angdeg, double radius) {
        double radian = Math.toRadians(angdeg);//弧度
        double x = mCenterPoint.x + Math.cos(radian) * radius;//圆心所在的X坐标+Math.cos(radian)*圆的半径
        double y = mCenterPoint.y + Math.sin(radian) * radius;//圆心所在Y坐标+Math.cos(radian)*圆的半径
        canvas.drawCircle(Float.valueOf(x + ""), Float.valueOf(y + ""), 10, mCirclePaint);//左边圆形
    }

    //画文字
    private void drawText(Canvas canvas, double angdeg, double radius, String text) {
        double radian = Math.toRadians(angdeg);//弧度
        double x = mCenterPoint.x + Math.cos(radian) * radius;//圆心所在的X坐标+Math.cos(radian)*圆的半径
        double y = mCenterPoint.y + Math.sin(radian) * radius;//圆心所在Y坐标+Math.cos(radian)*圆的半径
        canvas.drawText(text, Float.valueOf(x + ""), Float.valueOf(y + ""), mTextPaint);
    }

    //画进度
    private void drawProgress(Canvas canvas, double angdeg, double radius) {
        double radian_bit = Math.toRadians(angdeg);//弧度
        double x_bit = mCenterPoint.x - bitmap.getWidth() / 2 + Math.cos(radian_bit) * radius;//圆心所在的X坐标+Math.cos(radian)*圆的半径
        double y_bit = mCenterPoint.y - bitmap.getHeight() / 2 + Math.sin(radian_bit) * radius;//圆心所在Y坐标+Math.cos(radian)*圆的半径
        canvas.drawBitmap(bitmap, Float.valueOf(x_bit + ""), Float.valueOf(y_bit + ""), mBitPaint);
    }


    /**
     * 设置当前值
     *
     * @param value
     */
    public void setValue(double value) {

        angdeg = value;

    }

}
