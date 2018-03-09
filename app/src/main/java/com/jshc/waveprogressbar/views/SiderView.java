package com.jshc.waveprogressbar.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.jshc.waveprogressbar.R;

/**
 * Created by JinT on 2018/3/9 0009.
 */

public class SiderView extends View {
    private String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private int width, height;
    private Paint textPaint;
    private OnTextSelectListener listener;

    public SiderView(Context context) {
        super(context);
    }

    public SiderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SiderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        textPaint = new Paint();
        textPaint.setColor(getResources().getColor(R.color.black));
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(40f);
        for (int i = 0; i < words.length; i++) {
            canvas.drawText(words[i], width / 2, (height / words.length) * (i + 1) - (height / words.length) / 2, textPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        float y;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(getResources().getColor(R.color.siderBackground));
                y = event.getRawY();
                int postion1 = (int) (y / (height / words.length));
                if (postion1 - 1 >= 0 && postion1 < words.length + 1) {
                    listener.onTextClick(1, words[postion1 - 1]);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                setBackgroundColor(getResources().getColor(R.color.siderBackground));
                y = event.getRawY();
                int postion2 = (int) (y / (height / words.length));
                if (postion2 - 1 >= 0 && postion2 < words.length + 1) {
                    listener.onTextClick(1, words[postion2 - 1]);
                }
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundColor(getResources().getColor(R.color.unselectSider));
                listener.onTextClick(-1, "");
                break;
        }
        return true;
    }

    public void setOnTextSelectListener(OnTextSelectListener listener) {
        this.listener = listener;
    }

    public interface OnTextSelectListener {
        void onTextClick(int index, String text);
    }

}
