package com.example.fungwahtools.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.fungwahtools.R;

import static android.content.ContentValues.TAG;

/**
 * Created by ofw on 2017/10/17.
 */

public class ShadowView extends View {

    private Paint paint;
    private float radius;
    private float offsetX;
    private float offsetY;
    private int color;
    private int backgroundColor = Color.parseColor("#FFFFFF");

    public ShadowView(Context context) {
        super(context);
        initPaint();
    }

    public ShadowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);

    }

    public ShadowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
    }

    protected void initPaint() {
        initPaint(4, 0, 0, Color.WHITE);
    }

    protected void initPaint(float radius, float offsetX, float offsetY, int color) {
        paint = new Paint();
        paint.setColor(backgroundColor);
        paint.setShadowLayer(radius, offsetX, offsetY, color);
        paint.setAntiAlias(true);
    }

    protected void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShadowView);
        radius = typedArray.getFloat(R.styleable.ShadowView_radius, 4);
        offsetX = typedArray.getFloat(R.styleable.ShadowView_offsetX, 0);
        offsetY = typedArray.getFloat(R.styleable.ShadowView_offsetY, 0);
        try {
            color = Color.parseColor(typedArray.getString(R.styleable.ShadowView_shadowColor));
        }catch (Exception e){

        }
        try {
            backgroundColor = Color.parseColor(typedArray.getString(R.styleable.ShadowView_backgroundColor));
        } catch (Exception e) {

        }
        initPaint(radius, offsetX, offsetY, color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        RectF rectf = new RectF(getLeft(), getTop(), getRight(), getBottom() - radius / 2);
        canvas.drawRect(rectf, paint);

    }
}
