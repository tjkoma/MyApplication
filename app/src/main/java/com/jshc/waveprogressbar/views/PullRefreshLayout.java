package com.jshc.waveprogressbar.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.jshc.waveprogressbar.R;

/**
 * 上下拉刷新
 * Created by JinT on 2018/3/23 0023.
 */

public class PullRefreshLayout extends ViewGroup {
    private ProgressBar header_prrgressBar;
    private int mContentHeight;
    private int lastChildIndex;
    private View headerView, footView;
    private float goY;

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
        mContentHeight = 0;
        for (int a = 0; a < getChildCount(); a++) {
            View childView = getChildAt(a);
            if (childView == headerView) {
                childView.layout(0, 0 - childView.getMeasuredHeight(), childView.getMeasuredWidth(), 0);
            } else if (childView == footView) {

            } else {
                childView.layout(0, mContentHeight, childView.getMeasuredWidth(), childView.getMeasuredHeight());
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float downX = 0, downY = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);

        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();
                float goX = moveX - downX;
                goY = Math.abs(moveY - downY);
                headerView.layout(0, 0, headerView.getMeasuredWidth(), (int) goY / 4);
                break;
            case MotionEvent.ACTION_UP:
                headerView.layout(0, 0, headerView.getMeasuredWidth(), headerView.getMeasuredHeight());
                break;
        }
        return true;
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        float downX = 0, downY = 0;
//        for (int i = 0; i < getChildCount(); i++) {
//            View childView = getChildAt(i);
//
//        }
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                downX = event.getX();
//                downY = event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float moveX = event.getX();
//                float moveY = event.getY();
//                float goX = moveX - downX;
//                goY = Math.abs(moveY - downY);
//                headerView.layout(0, 0, headerView.getMeasuredWidth(), (int) goY);
//                break;
//            case MotionEvent.ACTION_UP:
//
//                break;
//        }
//        return true;
//
//    }
}
