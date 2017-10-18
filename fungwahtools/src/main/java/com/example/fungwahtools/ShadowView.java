package com.example.fungwahtools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by ofw on 2017/10/17.
 */

public class ShadowView extends RelativeLayout {

    private Paint paint;

    public ShadowView(Context context) {
        super(context);
        initPaint();
    }

    public ShadowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShadowView);
        float radius = typedArray.getFloat(R.styleable.ShadowView_radius, 4);
        float offsetX = typedArray.getFloat(R.styleable.ShadowView_offsetX, 0);
        float offsetY = typedArray.getFloat(R.styleable.ShadowView_offsetY, 0);
        int color = typedArray.getColor(R.styleable.ShadowView_shadowColor, Color.BLACK);
        initPaint(radius, offsetX, offsetY, color);
    }

    public ShadowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    protected void initPaint() {
        initPaint(4, 0, 0, Color.BLACK);
    }

    protected void initPaint(float radius, float offsetX, float offsetY, int color) {
        paint = new Paint();
        paint.setShadowLayer(radius, offsetX, offsetY, color);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawPaint(paint);
        canvas.dra
        super.onDraw(canvas);
    }
}
