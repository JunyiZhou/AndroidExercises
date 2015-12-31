package com.example.lucifer.animationdemo.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import com.example.lucifer.animationdemo.R;

public class WatchDrawable extends Drawable {
    private Paint paintNormal, paintScore;

    private int circleRadius_big, circleRadius_small, circleRadius;
    private int centerX, centerY;

    private float unitAngle;

    private float proportion;

    private Shader mBitmapShader;

    private Bitmap bitmap;

    public WatchDrawable(Context context, float proportion) {
        bitmap = ((BitmapDrawable) ResourcesCompat.getDrawable(context.getResources(), R.mipmap.checkin_score_bg, context.getTheme())).getBitmap();
        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        this.proportion = proportion;

        circleRadius = context.getResources().getDimensionPixelSize(R.dimen.circle_radius);

        paintNormal = new Paint();
        paintNormal.setAntiAlias(true);
        paintNormal.setStrokeWidth(4);
        paintNormal.setAlpha(200);
        paintNormal.setColor(Color.GRAY);

        paintScore = new Paint();
        paintScore.setShader(mBitmapShader);
        paintScore.setAntiAlias(true);
        paintScore.setStrokeWidth(4);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        circleRadius_big = bounds.width() / 2;
        circleRadius_small = circleRadius_big - circleRadius;

        centerX = bounds.centerX();
        centerY = bounds.centerY();

        unitAngle = (float) Math.PI / 60;
    }

    @Override
    public void draw(Canvas canvas) {
        float totalAngle = 0f;
        while (totalAngle < 2 * Math.PI) {
            float startX, startY, stopX, stopY;

            startX = circleRadius_big + (float) Math.sin(totalAngle) * circleRadius_small;
            startY = circleRadius_big - (float) Math.cos(totalAngle) * circleRadius_small;

            stopX = circleRadius_big + (float) Math.sin(totalAngle) * circleRadius_big;
            stopY = circleRadius_big - (float) Math.cos(totalAngle) * circleRadius_big;

            if (totalAngle < proportion * 2 * Math.PI) {
                canvas.drawLine(startX, startY, stopX, stopY, paintScore);
            } else {
                canvas.drawLine(startX, startY, stopX, stopY, paintNormal);
            }

            totalAngle += unitAngle;
        }
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
