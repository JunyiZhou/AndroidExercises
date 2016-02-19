package com.example.junyizhou.imagehandledemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.junyizhou.imagehandledemo.R;

import java.util.ArrayList;
import java.util.List;

public class DecalView extends BaseView {

    private final Paint mPaintForLineAndCircle;

    private int checkResultForMove = 0, checkResultForScaleAndRotate = 0, checkResultForDelete = 0;

    private Bitmap deleteIcon;

    protected List<MatrixBitmap> matrixBitmapList = new ArrayList<>();

    private boolean isWorking = true;

    public DecalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        deleteIcon = BitmapFactory.decodeResource(getResources(), R.drawable.clock_cexit);
        mPaintForLineAndCircle = new Paint();
        mPaintForLineAndCircle.setAntiAlias(true);
        mPaintForLineAndCircle.setColor(Color.BLACK);
        mPaintForLineAndCircle.setAlpha(170);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (MatrixBitmap matrixBitmap : matrixBitmapList) {
            float[] f = new float[9];
            matrixBitmap.matrix.getValues(f);
            // 图片4个顶点的坐标
            float x1 = f[0] * 0 + f[1] * 0 + f[2];
            float y1 = f[3] * 0 + f[4] * 0 + f[5];
            float x2 = f[0] * matrixBitmap.bitmap.getWidth() + f[1] * 0 + f[2];
            float y2 = f[3] * matrixBitmap.bitmap.getWidth() + f[4] * 0 + f[5];
            float x3 = f[0] * 0 + f[1] * matrixBitmap.bitmap.getHeight() + f[2];
            float y3 = f[3] * 0 + f[4] * matrixBitmap.bitmap.getHeight() + f[5];
            float x4 = f[0] * matrixBitmap.bitmap.getWidth() + f[1] * matrixBitmap.bitmap.getHeight() + f[2];
            float y4 = f[3] * matrixBitmap.bitmap.getWidth() + f[4] * matrixBitmap.bitmap.getHeight() + f[5];

            if (isWorking) {
                canvas.drawLine(x1, y1, x2, y2, mPaintForLineAndCircle);
                canvas.drawLine(x2, y2, x4, y4, mPaintForLineAndCircle);
                canvas.drawLine(x4, y4, x3, y3, mPaintForLineAndCircle);
                canvas.drawLine(x3, y3, x1, y1, mPaintForLineAndCircle);
                canvas.drawCircle(x2, y2, 40, mPaintForLineAndCircle);
                canvas.drawBitmap(deleteIcon, x2 - deleteIcon.getWidth() / 2, y2 - deleteIcon.getHeight() / 2, mPaintForBitmap);
            }

            canvas.drawBitmap(matrixBitmap.bitmap, matrixBitmap.matrix, mPaintForBitmap);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                x_down = event.getX();
                y_down = event.getY();
                checkResultForMove = decalCheck(x_down, y_down);
                checkResultForDelete = deleteCheck(x_down, y_down);
                if (checkResultForMove != -1 && checkResultForDelete == -1) {
                    savedMatrix.set(matrixBitmapList.get(checkResultForMove).matrix);
                    mode = DRAG;
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                checkResultForMove = decalCheck(event.getX(0), event.getY(0));
                checkResultForScaleAndRotate = decalCheck(event.getX(1), event.getY(1));
                if (checkResultForMove != -1 && checkResultForScaleAndRotate == checkResultForMove && checkResultForDelete == -1) {
                    savedMatrix.set(matrixBitmapList.get(checkResultForMove).matrix);
                    mode = ZOOM;
                }
                oldDist = spacing(event);
                oldRotation = rotation(event);

                midPoint(mid, event);
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == ZOOM) {
                    tempMatrix.set(savedMatrix);
                    float rotation = rotation(event) - oldRotation;
                    float newDist = spacing(event);
                    float scale = newDist / oldDist;
                    tempMatrix.postScale(scale, scale, mid.x, mid.y);// 縮放
                    tempMatrix.postRotate(rotation, mid.x, mid.y);// 旋轉
                    if (checkResultForMove != -1) {
                        matrixBitmapList.get(checkResultForMove).matrix.set(tempMatrix);
                    }
                    invalidate();
                } else if (mode == DRAG) {
                    tempMatrix.set(savedMatrix);
                    tempMatrix.postTranslate(event.getX() - x_down, event.getY()
                            - y_down);// 平移
                    if (checkResultForMove != -1) {
                        matrixBitmapList.get(checkResultForMove).matrix.set(tempMatrix);
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (checkResultForDelete != -1) {
                    matrixBitmapList.remove(checkResultForDelete).release();
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

    private boolean pointCheck(MatrixBitmap matrixBitmap, float x_down, float y_down) {
        float[] f = new float[9];
        matrixBitmap.matrix.getValues(f);
        // 图片4个顶点的坐标
        float x1 = f[0] * 0 + f[1] * 0 + f[2];
        float y1 = f[3] * 0 + f[4] * 0 + f[5];
        float x2 = f[0] * matrixBitmap.bitmap.getWidth() + f[1] * 0 + f[2];
        float y2 = f[3] * matrixBitmap.bitmap.getWidth() + f[4] * 0 + f[5];
        float x3 = f[0] * 0 + f[1] * matrixBitmap.bitmap.getHeight() + f[2];
        float y3 = f[3] * 0 + f[4] * matrixBitmap.bitmap.getHeight() + f[5];
        float x4 = f[0] * matrixBitmap.bitmap.getWidth() + f[1] * matrixBitmap.bitmap.getHeight() + f[2];
        float y4 = f[3] * matrixBitmap.bitmap.getWidth() + f[4] * matrixBitmap.bitmap.getHeight() + f[5];

        float edge = (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        if ((2 + Math.sqrt(2)) * edge >= Math.sqrt(Math.pow(x_down - x1, 2) + Math.pow(y_down - y1, 2))
                + Math.sqrt(Math.pow(x_down - x2, 2) + Math.pow(y_down - y2, 2))
                + Math.sqrt(Math.pow(x_down - x3, 2) + Math.pow(y_down - y3, 2))
                + Math.sqrt(Math.pow(x_down - x4, 2) + Math.pow(y_down - y4, 2))) {
            return true;
        }
        return false;
    }

    private boolean circleCheck(MatrixBitmap matrixBitmap, float x_down, float y_down) {
        float[] f = new float[9];
        matrixBitmap.matrix.getValues(f);
        // 图片右上角顶点的坐标
        float x2 = f[0] * matrixBitmap.bitmap.getWidth() + f[1] * 0 + f[2];
        float y2 = f[3] * matrixBitmap.bitmap.getWidth() + f[4] * 0 + f[5];

        int checkDis = (int) Math.sqrt(Math.pow (x_down - x2, 2) + Math.pow (y_down - y2, 2));

        if (checkDis < 40) {
            return true;
        }
        return false;
    }

    private int deleteCheck(float x_down, float y_down) {
        for (int i = 0; i < matrixBitmapList.size(); i++) {
            if (circleCheck(matrixBitmapList.get(i), x_down, y_down)) {
                return i;
            }
        }
        return -1;
    }

    private int decalCheck(float x_down, float y_down) {
        for (int i = 0; i < matrixBitmapList.size(); i++) {
            if (pointCheck(matrixBitmapList.get(i), x_down, y_down)) {
                return i;
            }
        }
        return -1;
    }

    public void addDecal(Bitmap bitmap) {
        MatrixBitmap matrixBitmapTemp = new MatrixBitmap();
        matrixBitmapTemp.bitmap = bitmap;
        if (matrixBitmapTemp.matrix == null) {
            matrixBitmapTemp.matrix = new Matrix();
        }
        float transX = (getWidth() - matrixBitmapTemp.bitmap.getWidth()) / 2;
        float transY = (getHeight() - matrixBitmapTemp.bitmap.getHeight()) / 2;
        matrixBitmapTemp.matrix.postTranslate(transX, transY);
        matrixBitmapTemp.matrix.postScale(0.5f, 0.5f, getWidth() / 2, getHeight() / 2);
        matrixBitmapList.add(matrixBitmapTemp);

        invalidate();
    }

    public void setWorkingState (boolean isWorking) {
        this.isWorking = isWorking;
        invalidate();
    }

    public void CreatNewPhoto(String filePath) {
        super.CreatNewPhoto(filePath, matrixBitmapList);
    }
}