package com.jaggadavid.drawing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class DrawingClass extends View {

    Paint bluePaint = new Paint(Color.BLUE);
    Paint whitePaint = new Paint(Color.WHITE);
    Random r = new Random();
    private int y;
    private int x;
    private int centerX;
    private int centerY;
    private float eyeX;
    private float eyeY;
    private int height;
    private int width;


    private int dy = r.nextInt(5) + 3;
    private int dx = r.nextInt(5) + 3;

    public DrawingClass(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        y = r.nextInt(getHeight());
        x = r.nextInt(getWidth());
        centerY = getHeight() / 2;
        centerX = getWidth() / 2;
        height = getHeight();
        width = getWidth();
        eyeX = centerX;
        eyeY = centerY;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);


        dx += (dx < 0) ? -0.5 : 0.5;
        dy += (dy < 0) ? -0.5 : 0.5;
        x += dx;
        y += dy;

        if (x >= getWidth() || x <= 0) {
            dx *= -1;
        }
        if (y >= getHeight() || y <= 0) {
            dy *= -1;
        }

        //eyeX = centerX+((centerX+(x-(getWidth()/2)))/centerX);
        //eyeY = centerY+((centerY+(y-getHeight()/2))/centerY);
        float changeX;
        float changeY;
        changeX = (-centerX + x) * (80f / width);
        changeY = (-centerY + y) * (80f / height);
        System.out.println("X: " + changeX);
        System.out.println("Y: " + changeY);

        whitePaint.setARGB(1000, 200, 200, 200);
        //canvas.drawCircle(centerX-80, centerY, 80f, whitePaint);
        //canvas.drawCircle(centerX+80, centerY, 80f, whitePaint);
        canvas.drawOval(centerX - 160, centerY + 120, centerX, centerY - 120, whitePaint);
        canvas.drawOval(centerX + 160, centerY + 120, centerX, centerY - 120, whitePaint);
        canvas.drawCircle(eyeX - 60 + changeX, eyeY + changeY, 40f, bluePaint);
        canvas.drawCircle(eyeX + 60 + changeX, eyeY + changeY, 40f, bluePaint);
        //bluePaint.setStrokeWidth(22);
        //bluePaint.setStyle(Paint.Style.STROKE);
        RectF rect = new RectF(centerX - 100, centerY + 100, centerX + 100, centerY + 200);
        canvas.drawArc(rect, 0, 180, true, bluePaint);

        bluePaint.setARGB(1000, 46, 94, 239);
        canvas.drawCircle(x, y, 105f, bluePaint);


        invalidate();


    }
}
