package com.example.lucifer.animationdemo.drawable;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

import com.example.lucifer.animationdemo.R;

public class CirclePercentDrawable extends Drawable {
    private Paint paintNormal, paintScore, paintPoint;

    private int circleRadius_big, circleRadius_small, circleRadius;
    private int centerX, centerY;

    private float mProportion;
    private long mDuration;

    private RectF arcRectF = new RectF();

    public CirclePercentDrawable(Context context, float proportion, String colorNormal, String colorScore, long duration) {
        mProportion = proportion;
        mDuration = duration;

        circleRadius = context.getResources().getDimensionPixelSize(R.dimen.circle_radius) / 2;

        paintNormal = new Paint();
        paintNormal.setAntiAlias(true);
        paintNormal.setStrokeWidth(circleRadius * 2);
        paintNormal.setColor(Color.parseColor(colorNormal));
        paintNormal.setStyle(Paint.Style.STROKE);

        paintScore = new Paint();
        paintScore.setColor(Color.parseColor(colorScore));
        paintScore.setAntiAlias(true);
        paintScore.setStrokeWidth(circleRadius * 2);
        paintScore.setStyle(Paint.Style.STROKE);

        paintPoint = new Paint();
        paintPoint.setAntiAlias(true);
        paintPoint.setColor(Color.parseColor(colorScore));
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);

        circleRadius_big = bounds.width() / 2;
        arcRectF.set(bounds.left + circleRadius, bounds.top + circleRadius, bounds.right - circleRadius, bounds.bottom - circleRadius);
        circleRadius_small = circleRadius_big - circleRadius;

        centerX = bounds.centerX();
        centerY = bounds.centerY();

    }

    @Override
    public void draw(Canvas canvas) {
        if (sweepAngle == -1) {
            startAnimation();
        } else {
            canvas.drawCircle(centerX, centerY, circleRadius_small, paintNormal);
            canvas.drawArc(arcRectF, startAngle, sweepAngle, false, paintScore);
            canvas.drawCircle(circleRadius_big, circleRadius, circleRadius, paintPoint);
            canvas.drawCircle(circleRadius_big + (float) Math.sin((double) sweepAngle * Math.PI / 180) * circleRadius_small,
                    circleRadius_big - (float) Math.cos((double) sweepAngle * Math.PI / 180) * circleRadius_small, circleRadius, paintPoint);
        }
    }

    private float startAngle = 270f, sweepAngle = -1f;

    public class FloatEvaluator implements TypeEvaluator<Float> {
        @Override
        public Float evaluate(float fraction, Float startValue, Float endValue) {
            return (endValue - startValue) * fraction;
        }
    }

    private void startAnimation() {
        ValueAnimator anim = ValueAnimator.ofObject(new FloatEvaluator(), 0f, mProportion * 360f);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                sweepAngle = (float) animation.getAnimatedValue();
                invalidateSelf();
            }
        });
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(mDuration);
        anim.start();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
