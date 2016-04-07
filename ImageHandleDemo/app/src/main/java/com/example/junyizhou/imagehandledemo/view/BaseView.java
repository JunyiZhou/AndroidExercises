package com.example.junyizhou.imagehandledemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public abstract class BaseView extends View {

    public interface OnSizeChangeListener {
        void onSizeChanged(int w, int h, int oldw, int oldh);
    }

    protected static final int NONE = 0;
    protected static final int DRAG = 1;
    protected static final int ZOOM = 2;

    protected int mode = NONE;

    protected float anchorX = 0;
    protected float anchorY = 0;
    protected float oldDistance = 1f;
    protected float oldRotation = 0;

    protected PointF midPoint = new PointF();

    protected Matrix moveMatrix = new Matrix();
    protected Matrix downMatrix = new Matrix();
    protected Matrix matrixBig = new Matrix();
    protected Matrix matrixSmall = new Matrix();

    protected RectF targetRect;

    protected boolean isFirst = true;

    protected OnSizeChangeListener mOnSizeChangedListener = null;

    protected ImageGroup mCropImageGroup = new ImageGroup();

    protected final Paint mPaintForBitmap;

    public BaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        mPaintForBitmap = new Paint();
        mPaintForBitmap.setAntiAlias(true);
        mPaintForBitmap.setFilterBitmap(true);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            targetRect = new RectF(left, top, right, bottom);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mCropImageGroup.bitmap != null) {
            canvas.drawBitmap(mCropImageGroup.bitmap, mCropImageGroup.matrix, mPaintForBitmap);
        }
    }

    // 触碰两点间距
    public float getDistance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    // 取手势中心点
    public PointF midPoint(MotionEvent event) {
        PointF point = new PointF();
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);

        return point;
    }

    // 取旋转角
    public float getRotation(MotionEvent event) {
        double x = event.getX(0) - event.getX(1);
        double y = event.getY(0) - event.getY(1);
        double radians = Math.atan2(y, x);
        return (float) Math.toDegrees(radians);
    }

    public void CreatNewPhoto(String filePath, List<ImageGroup> imageGroupList) {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(),
                Bitmap.Config.ARGB_8888); // 背景图片
        Canvas canvas = new Canvas(bitmap); // 新建画布
        canvas.drawColor(Color.WHITE);

        if (mCropImageGroup.bitmap != null && mCropImageGroup.matrix != null) {
            canvas.drawBitmap(mCropImageGroup.bitmap, mCropImageGroup.matrix, mPaintForBitmap);
        }

        for (ImageGroup imageGroup : imageGroupList) {
            canvas.drawBitmap(imageGroup.bitmap, imageGroup.matrix, mPaintForBitmap);
        }

        canvas.save(Canvas.ALL_SAVE_FLAG); // 保存画布
        canvas.restore();

        Bitmap resultBitmap = Bitmap.createBitmap(bitmap, (int) targetRect.left, (int) targetRect.top, getWidth(), getWidth(), null, false);
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

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mOnSizeChangedListener != null) {
            mOnSizeChangedListener.onSizeChanged(w, h, oldw, oldh);
        }
        setBackgroundBitmap();
    }

    public void setBackgroundBitmap() {
        if (mCropImageGroup.bitmap != null) {
            setBackgroundBitmap(mCropImageGroup.bitmap);
        }
    }

    public void setBackgroundBitmap(Bitmap bitmap) {
        mCropImageGroup.bitmap = bitmap;
        if (mCropImageGroup.matrix == null) {
            mCropImageGroup.matrix = new Matrix();
        }
        mCropImageGroup.matrix.reset();

        if (matrixBig != null && matrixSmall != null) {
            matrixBig.reset();
            matrixSmall.reset();
        }

        float scale;
        float transY = (getHeight() - mCropImageGroup.bitmap.getHeight()) / 2;
        float transX = (getWidth() - mCropImageGroup.bitmap.getWidth()) / 2;

        matrixBig.postTranslate(transX, transY);
        if (mCropImageGroup.bitmap.getHeight() <= mCropImageGroup.bitmap.getWidth()) {
            scale = (float) getHeight() / mCropImageGroup.bitmap.getHeight();
        } else {
            scale = (float) getWidth() / mCropImageGroup.bitmap.getWidth();
        }
        matrixBig.postScale(scale, scale, getWidth() / 2, getHeight() / 2);

        matrixSmall.postTranslate(transX, transY);
        if (mCropImageGroup.bitmap.getHeight() >= mCropImageGroup.bitmap.getWidth()) {
            scale = (float) getWidth() / mCropImageGroup.bitmap.getHeight();
        } else {
            scale = (float) getWidth() / mCropImageGroup.bitmap.getWidth();
        }
        matrixSmall.postScale(scale, scale, getWidth() / 2, getHeight() / 2);

        mCropImageGroup.matrix.set(matrixBig);

        invalidate();
    }

    protected float[] getBitmapPoints(ImageGroup imageGroup) {
        return getBitmapPoints(imageGroup.bitmap, imageGroup.matrix);
    }

    protected float[] getBitmapPoints(Bitmap bitmap, Matrix matrix) {
        float[] dst = new float[8];
        float[] src = new float[]{
                0, 0,
                bitmap.getWidth(), 0,
                0, bitmap.getHeight(),
                bitmap.getWidth(), bitmap.getHeight()
        };

        matrix.mapPoints(dst, src);
        return dst;
    }

    public void setOnSizeChangeListener(OnSizeChangeListener listener) {
        mOnSizeChangedListener = listener;
    }

    public static class ImageGroup {
        public Bitmap bitmap;
        public Matrix matrix = new Matrix();

        public void release() {
            if (bitmap != null) {
                bitmap.recycle();
                bitmap = null;
            }

            if (matrix != null) {
                matrix.reset();
                matrix = null;
            }
        }
    }
}
