package com.jshc.myapplication.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jshc.myapplication.R;

/**
 * Created by JinT on 2017/12/11 0011.
 */

public class MyProgressBar extends View {
    private RectF oval;
    //画笔
    private Paint paint;
    //圆环的颜色
    private int roundColor;
    //圆环进度的颜色
    private int roundProgressColor;
    //中间进度百分比字符串的颜色
    private int textColor;
    //中间进度字符串的字体大小
    private float textSize;
    //圆环的宽度
    private float roundWidth;
    //最大进度
    private int max;
    //当前进度
    private int progress;
    //是否显示中间的进度
    private boolean textIsDisplayable;
    //进度的风格 实心还是空心
    private int style;

    private static final int STROKE = 0;
    private static final int FILL = 1;

    public MyProgressBar(Context context) {
        this(context, null);
    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyProgressBar);
        roundColor = typedArray.getColor(R.styleable.MyProgressBar_roundColor, Color.RED);
        roundProgressColor = typedArray.getColor(R.styleable.MyProgressBar_roundProgressColor, Color.GREEN);
        textColor = typedArray.getColor(R.styleable.MyProgressBar_textColor, Color.GREEN);
        textSize = typedArray.getDimension(R.styleable.MyProgressBar_textSize, 15);
        roundWidth = typedArray.getDimension(R.styleable.MyProgressBar_roundWidth, 5);
        max = typedArray.getInteger(R.styleable.MyProgressBar_max, 100);
        textIsDisplayable = typedArray.getBoolean(R.styleable.MyProgressBar_textIsDisplayable, true);
        style = typedArray.getInt(R.styleable.MyProgressBar_style, 0);

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 画最外层的大圆环
         */
        paint = new Paint();
        int center = getWidth() / 2; //获取圆心的X轴坐标
        int radius = (int) (center - roundWidth / 2);//获取圆环的半径
        paint.setColor(roundColor);//设置画笔的颜色
        paint.setStyle(Paint.Style.STROKE);//设置画笔为空心
        paint.setStrokeWidth(roundWidth);
        paint.setAntiAlias(true);//设置抗锯齿
        //画出圆
        canvas.drawCircle(center, center, radius, paint);

        /**
         * 画进度百分比
         */
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD);//设置字体
        int percent = (int) (((float) progress / (float) max) * 100);//中间的进度百分比，先转换成float在进行除法运算，不然都为0
        float textWidth = paint.measureText(percent + "%"); //测量字体宽度，我们需要根据字体的宽度设置在圆环中间
        if (textIsDisplayable && percent != 0 && style == STROKE) {
            canvas.drawText(percent + "%", center - textWidth / 2, center + textSize / 2, paint); //画出进度百分比
        }

        /**
         * 画圆弧 ，画圆环的进度
         */
        //设置进度是实心还是空心
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度
        paint.setColor(roundProgressColor);  //设置进度的颜色
        oval = new RectF(center - radius, center - radius, center
                + radius, center + radius);  //用于定义的圆弧的形状和大小的界限

        switch (style) {
            case STROKE: {
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(oval, -90, 360 * percent / max, false, paint);  //根据进度画圆弧
                break;
            }
            case FILL: {
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (progress != 0)
                    canvas.drawArc(oval, -90, 360 * percent / max, true, paint);  //根据进度画圆弧
                break;
            }

        }

    }


    public synchronized int getMax() {
        return max;
    }

    /**
     * 设置进度的最大值
     *
     * @param max
     */
    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }

    /**
     * 获取进度.需要同步
     *
     * @return
     */
    public synchronized int getProgress() {
        return progress;
    }

    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
     * 刷新界面调用postInvalidate()能在非UI线程刷新
     *
     * @param progress
     */
    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }

    }


    public int getCricleColor() {
        return roundColor;
    }

    public void setCricleColor(int cricleColor) {
        this.roundColor = cricleColor;
    }

    public int getCricleProgressColor() {
        return roundProgressColor;
    }

    public void setCricleProgressColor(int cricleProgressColor) {
        this.roundProgressColor = cricleProgressColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

}
