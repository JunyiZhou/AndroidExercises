package com.example.junyizhou.imagehandledemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CropView extends BaseView {

    public CropView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                anchorX = event.getX();
                anchorY = event.getY();
                savedMatrix.set(mCropImageGroup.matrix);
                mode = DRAG;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                savedMatrix.set(mCropImageGroup.matrix);
                mode = ZOOM;
                oldDist = spacing(event);
                midPoint = midPoint(event);
                break;

            case MotionEvent.ACTION_MOVE:
                if (mode == ZOOM) {
                    tempMatrix.set(savedMatrix);
                    float newDist = spacing(event);
                    float scale = newDist / oldDist;
                    tempMatrix.postScale(scale, scale, midPoint.x, midPoint.y);// 縮放
                    mCropImageGroup.matrix.set(tempMatrix);
                    invalidate();

                } else if (mode == DRAG) {
                    tempMatrix.set(savedMatrix);
                    tempMatrix.postTranslate(event.getX() - anchorX, event.getY() - anchorY);// 平移
                    mCropImageGroup.matrix.set(tempMatrix);
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_UP:
                if (mCropImageGroup.bitmap != null) {
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

    private void matrixFix() {
        float[] points = getBitmapPoints(mCropImageGroup.bitmap, tempMatrix);
        float x1 = points[0];
        float y1 = points[1];
        float x2 = points[2];
        float y3 = points[5];

        if (mCropImageGroup.bitmap.getWidth() <= mCropImageGroup.bitmap.getHeight()) {
            if ((x2 - x1) < getWidth()) {
                tempMatrix.set(matrixBig);
            }

            if ((y3 - y1) < getHeight()) {
                tempMatrix.set(matrixSmall);
            }
        } else if (mCropImageGroup.bitmap.getWidth() > mCropImageGroup.bitmap.getHeight()) {
            if ((y3 - y1) < getHeight()) {
                tempMatrix.set(matrixBig);
            }

            if ((x2 - x1) < getWidth()) {
                tempMatrix.set(matrixSmall);
            }
        }

        if (!tempMatrix.equals(matrixBig) && !tempMatrix.equals(matrixSmall)) {
            if (x1 >= targetRect.left) {
                tempMatrix.postTranslate(targetRect.left - x1, 0);
            }

            if (x2 <= targetRect.left + getWidth()) {
                tempMatrix.postTranslate(getWidth() - x2, 0);
            }

            if (y1 >= targetRect.top) {
                tempMatrix.postTranslate(0, targetRect.top - y1);
            }

            if (y3 <= targetRect.top + getHeight()) {
                tempMatrix.postTranslate(0, targetRect.top + getHeight() - y3);
            }
        }

        mCropImageGroup.matrix.set(tempMatrix);
        invalidate();
    }
}