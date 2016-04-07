package com.example.junyizhou.rxjavademo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by JunyiZhou on 2016/3/9.
 */
public class TestView extends View {
    public static final String TAG = TestView.class.getSimpleName();
    private Bitmap bitmap;

    public TestView(Context context) {
        super(context);
        setLog("TestView(Context context)");
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLog("TestView(Context context, AttributeSet attrs)");
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLog("TestView(Context context, AttributeSet attrs, int defStyleAttr)");
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TestView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setLog("TestView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setLog("onFinishInflate()");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setLog("onMeasure(int widthMeasureSpec, int heightMeasureSpec) widthMeasureSpec = " + widthMeasureSpec + " heightMeasureSpec = " + heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        setLog("onLayout(boolean changed, int left, int top, int right, int bottom) changed = " + changed + " left = " + left + " top = " + top + " right = " + right + " bottom = " + bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setLog("onSizeChanged(int w, int h, int oldw, int oldh) w = " + w + " h = " + h + " oldw = " + oldw + " oldh" + oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        super.onDraw(canvas);
//
//        for (int i = 0; i < 360; i = i + 6) {
//            canvas.save();
//            canvas.rotate(i, 100, 100);
//            canvas.drawLine(100, 0, 100, 10, new Paint());
//            canvas.restore();
//        }

        Matrix m = new Matrix();
        m.postTranslate(100f, 100f);

        float[] src = {
                0, 0,
                0, bitmap.getHeight(),
                bitmap.getWidth(), 0,
                bitmap.getWidth(), bitmap.getHeight()
        };
        float[] dst = new float[8];

        m.mapPoints(dst, src);
        canvas.drawBitmap(bitmap, m, new Paint());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        setLog("onKeyDown(int keyCode, KeyEvent event) keyCode = " + keyCode + " event = " + event);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        setLog("onKeyUp(int keyCode, KeyEvent event) keyCode = " + keyCode + " event = " + event);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        setLog("onTrackballEvent(MotionEvent event) event = " + event);
        return super.onTrackballEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        setLog("onTouchEvent(MotionEvent event) event = " + event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        setLog("onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) gainFocus = " + gainFocus + " direction = " + direction + " previouslyFocusedRect = " + previouslyFocusedRect);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        setLog("onWindowFocusChanged(boolean hasWindowFocus) hasWindowFocus = " + hasWindowFocus);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setLog("onAttachedToWindow()");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setLog("onDetachedFromWindow()");
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        setLog("onVisibilityChanged(View changedView, int visibility) changedView = " + changedView + " visibility = " + visibility);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        setLog("onWindowVisibilityChanged(int visibility) visibility = " + visibility);
    }

    public void setLog(String msg) {
        Log.i(TAG, msg);
    }
}
