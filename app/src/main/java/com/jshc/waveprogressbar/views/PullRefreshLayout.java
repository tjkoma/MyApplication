package com.jshc.waveprogressbar.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.jshc.waveprogressbar.R;

import java.util.logging.Handler;

/**
 * 上下拉刷新
 * Created by JinT on 2018/3/23 0023.
 */

public class PullRefreshLayout extends ViewGroup {
    private ProgressBar header_prrgressBar;
    private TextView header_textView;
    private int mLayoutContentHeight, mEffectiveHeaderHeight, mEffictiveFooterHeight;
    private int lastChildIndex;
    private View headerView, footView;
    private float goY = 0;

    public PullRefreshLayout(Context context) {
        super(context);
        initView(context);
    }

    public PullRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PullRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View parentView = LayoutInflater.from(context).inflate(R.layout.pull_refresh_layout, null);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        lastChildIndex = getChildCount() - 1;
        addHeaderView();
        addFootView();
    }

    private void addHeaderView() {
        headerView = LayoutInflater.from(getContext()).inflate(R.layout.pull_refresh_layout, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(headerView, params);
        header_prrgressBar = headerView.findViewById(R.id.header_prrgressBar);
        header_textView = headerView.findViewById(R.id.header_textView);
    }

    private void addFootView() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        mLayoutContentHeight = 0;
        Log.e("child", getChildCount() + "");
        for (int a = 0; a < getChildCount(); a++) {
            View child = getChildAt(a);
            if (child == headerView) {
                child.layout(0, 0 - child.getMeasuredHeight(), child.getMeasuredWidth(), 0);
                mEffectiveHeaderHeight = child.getHeight();
            } else if (child == footView) {
                child.layout(0, mLayoutContentHeight, child.getMeasuredWidth(), mLayoutContentHeight + child.getMeasuredHeight());
                mEffictiveFooterHeight = child.getHeight();
            } else {
                child.layout(0, mLayoutContentHeight, child.getMeasuredWidth(), mLayoutContentHeight + child.getMeasuredHeight());
                mLayoutContentHeight += child.getMeasuredHeight();
            }
        }

    }

    private int mLastMoveY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMoveY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int dy = mLastMoveY - y;
                scrollBy(0, dy / 2);
                break;
            case MotionEvent.ACTION_UP:
                int dyu_up = mLastMoveY - y;
                scrollBy(0, -dyu_up / 2);
                // dy < 0代表是针对下拉刷新的操作
//                if (dyu_up < 0) {
//                    if (Math.abs(getScrollY()) <= headerView.getMeasuredHeight() / 2) {
//                        scrollBy(0, dyu_up);
//                        if (Math.abs(getScrollY()) >= headerView.getMeasuredHeight() * 2) {
//                            header_textView.setText("松开刷新");
//                        }
//                    }
//                }
                break;
        }
        mLastMoveY = y;
        return true;
    }

}
