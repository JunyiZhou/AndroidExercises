package com.example.songyang.healthmanager.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.example.songyang.healthmanager.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by JunyiZhou on 2016/3/24.
 */
public class LineDiagramView extends View {

    private Paint axisPaint;// 坐标轴轴线画笔
    private Paint yUniteTextPaint;// 绘制单位文本的画笔
    private Paint xUniteTextPaint;// 绘制单位文本的画笔
    private Paint valueTextPaint;// 绘制坐标值文本的画笔
    private Paint brokenLinePaint;// 折线画笔
    private Paint bitmapPaint;// bitmap画笔

    private float[] mValues;//象限内显示的坐标值
    private String[] mValuesStr;//坐标值String
    private Bitmap mPointBitmap;//圆点bitmap
    private String[] mXParams;//x轴横坐标
    private DiagramAnimation diagramAnimation;//折线动画
    private int[] mYParams;//y轴纵坐标标量
    private String[] mYParamsStr;//y轴纵坐标标量String
    private float mActualX;//折线动画x轴坐标变量

    private float TEXT_SIZE = sp2px(11);//字号 11dp
    private float VERTICAL_SPACE = dp2px(40);//网格垂直间距 40dp
    private float LINE_WIDTH = dp2px(0.5f);//线条粗细 0.5dp
    private float X_PART_NUM = 3;//x轴等分数

    private float X_OFFSET;//x轴偏移
    private float Y_OFFSET = dp2px(0.25f);//y轴偏移
    private float X_TEXT_OFFSET = TEXT_SIZE + dp2px(15);//x轴坐标标量偏移
    private float Y_TEXT_OFFSET = dp2px(2);//y轴坐标标量偏移
    private float X_POINT_OFFSET;//折线图坐标值圆点x轴方向偏移
    private float Y_POINT_OFFSET;//折线图坐标值圆点y轴方向偏移
    private float X_BUBBLE_OFFSET;//折线图坐标值气泡x轴方向偏移
    private float Y_BUBBLE_OFFSET;//折线图坐标值气泡y轴方向偏移
    private float Y_VALUE_OFFSET = dp2px(16);//折线图坐标值y轴方向偏移

    private float VIEW_HEIGHT = VERTICAL_SPACE * 5;//view的高度
    private float HORIZONTAL_EFFECTIVE_SPACE = VERTICAL_SPACE * 3;//坐标值有效的高度
    private float VIEW_WIDTH;//view的宽度
    private float HORIZONTAL_SPACE;//网格水平间距

    private List<PointF> brokenLineTotalPoints = new ArrayList<PointF>();//折线上所有坐标点
    private List<PointF> brokenLineActualPoints = new ArrayList<PointF>();//折线上需要绘制的坐标点
    private List<Float> brokenLineGradients = new ArrayList<Float>();//折线各段斜率

    public LineDiagramView(Context context) {
        super(context);
        initAnimation();
        initPaint();
        initBitmap();
    }

    public LineDiagramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAnimation();
        initPaint();
        initBitmap();
    }

    /**
     * 初始化折线动画
     */
    private void initAnimation() {
        diagramAnimation = new DiagramAnimation();
        diagramAnimation.setDuration(4000);
    }

    /**
     * 初始化bitmap
     */
    private void initBitmap() {
        mPointBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.linechart_point);
        X_POINT_OFFSET = mPointBitmap.getWidth() / 2;
        Y_POINT_OFFSET = mPointBitmap.getHeight() / 2;
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        brokenLinePaint = new Paint();
        brokenLinePaint.setAntiAlias(true);
        brokenLinePaint.setColor(Color.parseColor("#72abf2"));
        brokenLinePaint.setStyle(Paint.Style.STROKE);
        brokenLinePaint.setStrokeWidth(LINE_WIDTH);

        axisPaint = new Paint();
        axisPaint.setAntiAlias(true);
        axisPaint.setTextAlign(Align.CENTER);
        axisPaint.setColor(Color.parseColor("#e2f1ff"));
        axisPaint.setStyle(Paint.Style.STROKE);
        axisPaint.setStrokeWidth(LINE_WIDTH);

        yUniteTextPaint = new Paint();
        yUniteTextPaint.setColor(Color.parseColor("#7da7bc"));
        yUniteTextPaint.setTextAlign(Align.LEFT);
        yUniteTextPaint.setTextSize(TEXT_SIZE);
        yUniteTextPaint.setAntiAlias(true);

        xUniteTextPaint = new Paint();
        xUniteTextPaint.setColor(Color.parseColor("#7da7bc"));
        xUniteTextPaint.setTextAlign(Align.CENTER);
        xUniteTextPaint.setTextSize(TEXT_SIZE);
        xUniteTextPaint.setAntiAlias(true);
        xUniteTextPaint.setStyle(Paint.Style.FILL);

        valueTextPaint = new Paint();
        valueTextPaint.setColor(Color.parseColor("#FFFFFF"));// 字体颜色
        valueTextPaint.setTextAlign(Align.CENTER);
        valueTextPaint.setTextSize(TEXT_SIZE);// 字体大小
        valueTextPaint.setAntiAlias(true);// 抗锯齿效果
        valueTextPaint.setStyle(Paint.Style.FILL);

        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);
    }

    /**
     * 初始化绘制所需数据
     */
    private void initData() {
        VIEW_WIDTH = getWidth();
        X_OFFSET = dp2px(8.25f) + getTextWidth(yUniteTextPaint, mYParamsStr[mYParamsStr.length - 1]);
        X_PART_NUM = mValuesStr.length;
        HORIZONTAL_SPACE = (VIEW_WIDTH - LINE_WIDTH - X_OFFSET) / (X_PART_NUM + 1);
        float X_START_POINT = X_OFFSET;
        float Y_START_POINT = VERTICAL_SPACE;
        float X_END_POINT = VIEW_WIDTH;
        float Y_END_POINT = VIEW_HEIGHT;

        brokenLineTotalPoints.clear();
        brokenLineTotalPoints.add(new PointF(X_START_POINT, Y_START_POINT));
        for (int i = 0; i < mValues.length; i++) {
            brokenLineTotalPoints.add(new PointF((i + 1) * HORIZONTAL_SPACE + X_OFFSET, getYforValuePoint(mValues[i])));
        }
        brokenLineTotalPoints.add(new PointF(X_END_POINT, Y_END_POINT));

        brokenLineGradients.clear();
        for (int i = 0; i < brokenLineTotalPoints.size() - 1; i++) {
            PointF startPoint = brokenLineTotalPoints.get(i);
            PointF endPoint = brokenLineTotalPoints.get(i + 1);
            float gradient = (startPoint.y - endPoint.y) / (startPoint.x - endPoint.x);
            brokenLineGradients.add(gradient);
        }
    }

    /**
     * 开启动画
     */
    public void show() {
        this.startAnimation(diagramAnimation);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            initData();
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制网格
        {
            for (int i = 0; i < 6; i++) {
                if (i == 0) {
                    canvas.drawLine(X_OFFSET, Y_OFFSET, VIEW_WIDTH, Y_OFFSET, axisPaint);
                } else {
                    canvas.drawLine(X_OFFSET, i * VERTICAL_SPACE, VIEW_WIDTH, i * VERTICAL_SPACE, axisPaint);
                }
            }

            for (int i = 0; i < X_PART_NUM + 2; i++) {
                canvas.drawLine(i * HORIZONTAL_SPACE + X_OFFSET, Y_OFFSET, i * HORIZONTAL_SPACE + X_OFFSET, VIEW_HEIGHT, axisPaint);
            }
        }

        //绘制坐标标量
        {
            for (int i = 0; i < mYParamsStr.length; i++) {
                canvas.drawText(mYParamsStr[mYParamsStr.length - 1 - i], 0, (i + 1) * VERTICAL_SPACE + Y_TEXT_OFFSET, yUniteTextPaint);
            }

            for (int i = 0; i < mXParams.length; i++) {
                canvas.drawText(mXParams[i], (i + 1) * HORIZONTAL_SPACE + X_OFFSET, VIEW_HEIGHT + X_TEXT_OFFSET, xUniteTextPaint);
            }
        }

        //绘制折线、坐标值
        {
            Path brokenLine = new Path();
            for (int i = 0; i < brokenLineActualPoints.size(); i++) {
                if (i == 0) {
                    brokenLine.moveTo(brokenLineActualPoints.get(i).x, brokenLineActualPoints.get(i).y);
                }
                brokenLine.lineTo(brokenLineActualPoints.get(i).x, brokenLineActualPoints.get(i).y);
            }
            canvas.drawPath(brokenLine, brokenLinePaint);
            for (int i = 0; i < brokenLineActualPoints.subList(1, brokenLineActualPoints.size() - 1).size(); i++) {
                PointF point = brokenLineActualPoints.subList(1, brokenLineActualPoints.size() - 1).get(i);
                canvas.drawBitmap(mPointBitmap, point.x - X_POINT_OFFSET, point.y - Y_POINT_OFFSET, bitmapPaint);
                canvas.drawText(mValuesStr[i], point.x, point.y - Y_POINT_OFFSET - Y_VALUE_OFFSET, valueTextPaint);
            }
        }
    }

    /**
     * 获取y轴坐标值
     * @param pointValue
     * @return
     */
    public float getYforValuePoint(float pointValue) {
        float totalDValue = mYParams[mYParams.length - 1] - mYParams[0];
        float pointDValue = pointValue - mYParams[0];
        return VIEW_HEIGHT - HORIZONTAL_EFFECTIVE_SPACE * pointDValue / totalDValue - VERTICAL_SPACE;
    }

    /**
     * dp转px
     * @param value
     * @return
     */
    private float dp2px(float value) {
        float v = getContext().getResources().getDisplayMetrics().density;
        return v * value;
    }

    /**
     * sp转px
     * @param value
     * @return
     */
    private float sp2px(float value) {
        float v = getContext().getResources().getDisplayMetrics().scaledDensity;
        return v * value;
    }

    /**
     * 获取字符串长度
     * @param paint
     * @param str
     * @return
     */
    private float getTextWidth(Paint paint, String str) {
        Rect bounds = new Rect();
        paint.getTextBounds(str, 0, str.length(), bounds);
        return bounds.width();
    }

    /**
     * 折线动画类
     */
    private class DiagramAnimation extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            brokenLineActualPoints.clear();
            mActualX = getWidth() * interpolatedTime + X_OFFSET;
            for (int i = 0; i < brokenLineTotalPoints.size(); i++) {
                PointF point = brokenLineTotalPoints.get(i);
                if (mActualX >= point.x) {
                    brokenLineActualPoints.add(point);
                } else {
                    PointF lastPoint = new PointF();
                    lastPoint.x = mActualX;
                    lastPoint.y = brokenLineGradients.get(i - 1) * (lastPoint.x - brokenLineTotalPoints.get(i - 1).x) + brokenLineTotalPoints.get(i - 1).y;
                    brokenLineActualPoints.add(lastPoint);
                    break;
                }
            }
            invalidate();
        }
    }

    /**
     * 设置控件显示数据并刷新
     *
     * @param yParams：y坐标
     * @param values：象限内显示数据
     * @param xParams：x轴横坐标
     * @param unite: 单位
     */
    public void setParams(int[] yParams, float[] values, String[] xParams, String unite) {
        int yParamsLength = yParams.length;
        if (yParamsLength <= 0) {
            return;
        }
        String[] yParamsStr = new String[yParamsLength];
        for (int i = 0; i < yParamsLength; i++) {
            yParamsStr[i] = String.valueOf(yParams[i]) + unite;
        }

        int valuesLength = values.length;
        if (valuesLength <= 0) {
            return;
        }
        String[] valuesStr = new String[valuesLength];
        for (int i = 0; i < valuesLength; i++) {
            valuesStr[i] = String.valueOf(values[i]) + unite;
        }

        mYParams = yParams;
        mYParamsStr = yParamsStr;
        mValues = values;
        mValuesStr = valuesStr;
        mXParams = xParams;

        initData();
        invalidate();
    }

}