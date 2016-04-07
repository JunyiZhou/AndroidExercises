package com.example.junyizhou.imagehandledemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.junyizhou.imagehandledemo.R;

import java.util.ArrayList;
import java.util.List;

public class DecalView extends BaseView {

    private final Paint mPaintForLineAndCircle;

    private int moveTag = 0, transformTag = 0, deleteTag = 0;

    private Bitmap deleteIcon;

    private List<ImageGroup> mDecalImageGroupList = new ArrayList<>();

    public DecalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        deleteIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_close);
        mPaintForLineAndCircle = new Paint();
        mPaintForLineAndCircle.setAntiAlias(true);
        mPaintForLineAndCircle.setColor(Color.BLACK);
        mPaintForLineAndCircle.setAlpha(170);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (ImageGroup imageGroup : mDecalImageGroupList) {
            float[] points = getBitmapPoints(imageGroup);
            float x1 = points[0];
            float y1 = points[1];
            float x2 = points[2];
            float y2 = points[3];
            float x3 = points[4];
            float y3 = points[5];
            float x4 = points[6];
            float y4 = points[7];

            canvas.drawLine(x1, y1, x2, y2, mPaintForLineAndCircle);
            canvas.drawLine(x2, y2, x4, y4, mPaintForLineAndCircle);
            canvas.drawLine(x4, y4, x3, y3, mPaintForLineAndCircle);
            canvas.drawLine(x3, y3, x1, y1, mPaintForLineAndCircle);
            canvas.drawCircle(x2, y2, 40, mPaintForLineAndCircle);
            canvas.drawBitmap(deleteIcon, x2 - deleteIcon.getWidth() / 2, y2 - deleteIcon.getHeight() / 2, mPaintForBitmap);

            canvas.drawBitmap(imageGroup.bitmap, imageGroup.matrix, mPaintForBitmap);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                anchorX = event.getX();
                anchorY = event.getY();
                moveTag = decalCheck(anchorX, anchorY);
                deleteTag = deleteCheck(anchorX, anchorY);
                if (moveTag != -1 && deleteTag == -1) {
                    downMatrix.set(mDecalImageGroupList.get(moveTag).matrix);
                    mode = DRAG;
                }
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                moveTag = decalCheck(event.getX(0), event.getY(0));
                transformTag = decalCheck(event.getX(1), event.getY(1));
                if (moveTag != -1 && transformTag == moveTag && deleteTag == -1) {
                    downMatrix.set(mDecalImageGroupList.get(moveTag).matrix);
                    mode = ZOOM;
                }
                oldDistance = getDistance(event);
                oldRotation = getRotation(event);

                midPoint = midPoint(event);
                break;

            case MotionEvent.ACTION_MOVE:
                if (mode == ZOOM) {
                    moveMatrix.set(downMatrix);
                    float newRotation = getRotation(event) - oldRotation;
                    float newDistance = getDistance(event);
                    float scale = newDistance / oldDistance;
                    moveMatrix.postScale(scale, scale, midPoint.x, midPoint.y);// 縮放
                    moveMatrix.postRotate(newRotation, midPoint.x, midPoint.y);// 旋轉
                    if (moveTag != -1) {
                        mDecalImageGroupList.get(moveTag).matrix.set(moveMatrix);
                    }
                    invalidate();
                } else if (mode == DRAG) {
                    moveMatrix.set(downMatrix);
                    moveMatrix.postTranslate(event.getX() - anchorX, event.getY() - anchorY);// 平移
                    if (moveTag != -1) {
                        mDecalImageGroupList.get(moveTag).matrix.set(moveMatrix);
                    }
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_UP:
                if (deleteTag != -1) {
                    mDecalImageGroupList.remove(deleteTag).release();
                    invalidate();
                }
                mode = NONE;
                break;

            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
        }
        return true;
    }

    private boolean pointCheck(ImageGroup imageGroup, float x, float y) {
        float[] points = getBitmapPoints(imageGroup);
        float x1 = points[0];
        float y1 = points[1];
        float x2 = points[2];
        float y2 = points[3];
        float x3 = points[4];
        float y3 = points[5];
        float x4 = points[6];
        float y4 = points[7];

        float edge = (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        if ((2 + Math.sqrt(2)) * edge >= Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2))
                + Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2))
                + Math.sqrt(Math.pow(x - x3, 2) + Math.pow(y - y3, 2))
                + Math.sqrt(Math.pow(x - x4, 2) + Math.pow(y - y4, 2))) {
            return true;
        }
        return false;
    }

    private boolean circleCheck(ImageGroup imageGroup, float x, float y) {
        float[] points = getBitmapPoints(imageGroup);
        float x2 = points[2];
        float y2 = points[3];

        int checkDis = (int) Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));

        if (checkDis < 40) {
            return true;
        }
        return false;
    }

    private int deleteCheck(float x, float y) {
        for (int i = 0; i < mDecalImageGroupList.size(); i++) {
            if (circleCheck(mDecalImageGroupList.get(i), x, y)) {
                return i;
            }
        }
        return -1;
    }

    private int decalCheck(float x, float y) {
        for (int i = 0; i < mDecalImageGroupList.size(); i++) {
            if (pointCheck(mDecalImageGroupList.get(i), x, y)) {
                return i;
            }
        }
        return -1;
    }

    public void addDecal(Bitmap bitmap) {
        ImageGroup imageGroupTemp = new ImageGroup();
        imageGroupTemp.bitmap = bitmap;
        if (imageGroupTemp.matrix == null) {
            imageGroupTemp.matrix = new Matrix();
        }
        float transX = (getWidth() - imageGroupTemp.bitmap.getWidth()) / 2;
        float transY = (getHeight() - imageGroupTemp.bitmap.getHeight()) / 2;
        imageGroupTemp.matrix.postTranslate(transX, transY);
        imageGroupTemp.matrix.postScale(0.5f, 0.5f, getWidth() / 2, getHeight() / 2);
        mDecalImageGroupList.add(imageGroupTemp);

        invalidate();
    }
}