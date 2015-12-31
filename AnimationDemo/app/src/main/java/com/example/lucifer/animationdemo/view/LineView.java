package com.example.lucifer.animationdemo.view;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by JunyiZhou on 2015/12/30.
 */
public class LineView extends View{

    private Paint paintNormal;
    private float x1, y1, x2, y2, x, y;

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintNormal = new Paint();
        paintNormal.setAntiAlias(true);
        paintNormal.setColor(Color.RED);
    }

    public void setCoordinate(float x1, float y1, float x2, float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        startAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(x1, y1, x, y, paintNormal);
    }

    public class FloatEvaluator implements TypeEvaluator<Float> {
        @Override
        public Float evaluate(float fraction, Float startValue, Float endValue) {
            return (endValue - startValue) * fraction;
        }
    }

    private void startAnimation() {
        ValueAnimator anim = ValueAnimator.ofObject(new FloatEvaluator(), x1, x2);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                x = (float) animation.getAnimatedValue() + x1;
                y = getTempY(x, x1, y1, x2, y2);
                invalidate();
            }
        });
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(2000);
        anim.start();
    }

    private float getTempY(float x, float x1, float y1, float x2, float y2) {
        return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
    }
}
