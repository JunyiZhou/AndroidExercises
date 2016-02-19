package com.example.junyizhou.imagehandledemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BaseView extends View {

    public interface OnSizeChangeListener {
        void onSizeChanged(int w, int h, int oldw, int oldh);
    }

    public static final int NONE = 0;
    public static final int DRAG = 1;
    public static final int ZOOM = 2;

    protected int mode = NONE;

    protected float x_down = 0;
    protected float y_down = 0;
    protected float oldDist = 1f;
    protected float oldRotation = 0;

    protected PointF mid = new PointF();

    protected Matrix tempMatrix = new Matrix();
    protected Matrix savedMatrix = new Matrix();
    protected Matrix matrixBig = new Matrix();
    protected Matrix matrixSmall = new Matrix();

    protected Rect mTargetRect;

    protected boolean isFirst = true;

    protected OnSizeChangeListener mOnSizeChangedListener = null;

    protected MatrixBitmap mMatrixBitmap = new MatrixBitmap();

    protected final Paint mPaintForBitmap;

    public BaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        mPaintForBitmap = new Paint();
        mPaintForBitmap.setAntiAlias(true);
        mPaintForBitmap.setFilterBitmap(true);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            mTargetRect = new Rect(left, top, right, bottom);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mMatrixBitmap.bitmap != null) {
            canvas.drawBitmap(mMatrixBitmap.bitmap, mMatrixBitmap.matrix, mPaintForBitmap);
        }
    }

    // 触碰两点间距
    public float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float)Math.sqrt(x * x + y * y);
    }

    // 取手势中心点
    public void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    // 取旋转角
    public float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    public void CreatNewPhoto(String filePath, List<MatrixBitmap> matrixBitmapList) {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(),
                Bitmap.Config.ARGB_8888); // 背景图片
        Canvas canvas = new Canvas(bitmap); // 新建画布
        canvas.drawColor(Color.WHITE);

        if (mMatrixBitmap.bitmap != null && mMatrixBitmap.matrix != null) {
            canvas.drawBitmap(mMatrixBitmap.bitmap, mMatrixBitmap.matrix, mPaintForBitmap);
        }

        for (MatrixBitmap matrixBitmap : matrixBitmapList) {
            canvas.drawBitmap(matrixBitmap.bitmap, matrixBitmap.matrix, mPaintForBitmap);
        }

        canvas.save(Canvas.ALL_SAVE_FLAG); // 保存画布
        canvas.restore();

        Bitmap resultBitmap = Bitmap.createBitmap(bitmap, mTargetRect.left, mTargetRect.top, getWidth(), getWidth(),
                null, false);
        bitmap.recycle();

        File f = new File(filePath);

        try {
            resultBitmap.compress(Bitmap.CompressFormat.JPEG, 85, new FileOutputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        resultBitmap.recycle();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && isFirst) {
            isFirst = false;
            setBackgroundBitmap();
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        if (mOnSizeChangedListener != null) {
            mOnSizeChangedListener.onSizeChanged(w, h, oldw, oldh);
        }
        setBackgroundBitmap();
    }

    public void setBackgroundBitmap(){
        if(mMatrixBitmap.bitmap != null){
            setBackgroundBitmap(mMatrixBitmap.bitmap);
        }
    }

    public void setBackgroundBitmap(Bitmap bitmap) {
        mMatrixBitmap.bitmap = bitmap;
        if (mMatrixBitmap.matrix == null) {
            mMatrixBitmap.matrix = new Matrix();
        }
        mMatrixBitmap.matrix.reset();

        if(matrixBig != null && matrixSmall != null){
            matrixBig.reset();
            matrixSmall.reset();
        }

        float scale;
        float transY = (getHeight() - mMatrixBitmap.bitmap.getHeight()) / 2;
        float transX = (getWidth() - mMatrixBitmap.bitmap.getWidth()) / 2;

        matrixBig.postTranslate(transX, transY);
        if (mMatrixBitmap.bitmap.getHeight() <= mMatrixBitmap.bitmap.getWidth()) {
            scale = (float) getHeight() / mMatrixBitmap.bitmap.getHeight();
        } else {
            scale = (float) getWidth() / mMatrixBitmap.bitmap.getWidth();
        }
        matrixBig.postScale(scale, scale, getWidth() / 2, getHeight() / 2);

        matrixSmall.postTranslate(transX, transY);
        if (mMatrixBitmap.bitmap.getHeight() >= mMatrixBitmap.bitmap.getWidth()) {
            scale = (float) getWidth() / mMatrixBitmap.bitmap.getHeight();
        } else {
            scale = (float) getWidth() / mMatrixBitmap.bitmap.getWidth();
        }
        matrixSmall.postScale(scale, scale, getWidth() / 2, getHeight() / 2);

        mMatrixBitmap.matrix.set(matrixBig);

        invalidate();
    }

    public void setOnSizeChangeListener(OnSizeChangeListener listener) {
        mOnSizeChangedListener = listener;
    }

    public static class MatrixBitmap {
        public Bitmap bitmap;
        public Matrix matrix  = new Matrix();

        public void release(){
            if(bitmap != null) {
                bitmap.recycle();
            }
            if(matrix != null) {
                matrix.reset();
            }
        }
    }
}
