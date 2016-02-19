package com.example.junyizhou.imagehandledemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CropView extends BaseView {

    public CropView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                x_down = event.getX();
                y_down = event.getY();
                savedMatrix.set(mMatrixBitmap.matrix);
                mode = DRAG;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                savedMatrix.set(mMatrixBitmap.matrix);
                mode = ZOOM;
                oldDist = spacing(event);
                midPoint(mid, event);
                break;
            case MotionEvent.ACTION_MOVE:

                if (mode == ZOOM) {
                    tempMatrix.set(savedMatrix);
                    float newDist = spacing(event);
                    float scale = newDist / oldDist;
                    tempMatrix.postScale(scale, scale, mid.x, mid.y);// 縮放

                    mMatrixBitmap.matrix.set(tempMatrix);
                    invalidate();

                } else if (mode == DRAG) {
                    tempMatrix.set(savedMatrix);
                    tempMatrix.postTranslate(event.getX() - x_down, event.getY()
                            - y_down);// 平移

                    mMatrixBitmap.matrix.set(tempMatrix);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if(mMatrixBitmap.bitmap != null) {
                    matrixFix();
                }
                mode = NONE;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
        }
        return true;
    }

    private void matrixFix(){
        float[] f = new float[9];
        tempMatrix.getValues(f);

        float x1 = f[0] * 0 + f[1] * 0 + f[2];
        float y1 = f[3] * 0 + f[4] * 0 + f[5];
        float x2 = f[0] * mMatrixBitmap.bitmap.getWidth() + f[1] * 0 + f[2];
        float y3 = f[3] * 0 + f[4] * mMatrixBitmap.bitmap.getHeight() + f[5];

        if(mMatrixBitmap.bitmap.getWidth() <= mMatrixBitmap.bitmap.getHeight()){
            if((int)(f[0]*mMatrixBitmap.bitmap.getWidth()) < getWidth()) {
                tempMatrix.set(matrixBig);
            }
            if((int)(f[4]*mMatrixBitmap.bitmap.getHeight()) < getHeight()){
                tempMatrix.set(matrixSmall);
            }
        }else if(mMatrixBitmap.bitmap.getWidth() > mMatrixBitmap.bitmap.getHeight()){
            if((int)(f[4]*mMatrixBitmap.bitmap.getHeight()) < getHeight()) {
                tempMatrix.set(matrixBig);
            }
            if((int)(f[0]*mMatrixBitmap.bitmap.getWidth()) < getWidth()){
                tempMatrix.set(matrixSmall);
            }
        }

        if(!tempMatrix.equals(matrixBig) && !tempMatrix.equals(matrixSmall)) {
            if ((int) x1 >= mTargetRect.left) {
                tempMatrix.postTranslate(mTargetRect.left-x1, 0);
            }
            if ((int) x2 <= mTargetRect.left + getWidth()) {
                tempMatrix.postTranslate(getWidth() - x2, 0);
            }

            if ((int) y1 >= mTargetRect.top) {
                tempMatrix.postTranslate(0, mTargetRect.top-y1);
            }
            if ((int) y3 <= mTargetRect.top + getHeight()) {
                tempMatrix.postTranslate(0, mTargetRect.top + getHeight()-y3);
            }
        }

        mMatrixBitmap.matrix.set(tempMatrix);
        invalidate();
    }
}