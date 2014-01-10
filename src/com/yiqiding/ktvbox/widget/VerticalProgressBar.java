package com.yiqiding.ktvbox.widget;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.yiqiding.ktvbox.R;

/**
 * Created by culm on 13-12-11.
 */
public class VerticalProgressBar extends ProgressBar {

    public static final int MODE_TOP=0x001;
    public static final int MODE_BOTTOM=0x002;

    private int curr_mode=MODE_BOTTOM;

    public VerticalProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public VerticalProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalProgressBar(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {

        switch (curr_mode)
        {
            case MODE_BOTTOM:
                canvas.rotate(-90);
                canvas.translate(-canvas.getHeight(), 0);
                super.onDraw(canvas);
                break;
            case MODE_TOP:
                canvas.rotate(90,canvas.getWidth(),0);
                canvas.translate(10,0);
                super.onDraw(canvas);
                break;
        }
    }

    public void setCurrMode(int mode)
    {
        curr_mode=mode;
        invalidate();
    }

}