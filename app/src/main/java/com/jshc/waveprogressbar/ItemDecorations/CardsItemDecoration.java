package com.jshc.waveprogressbar.ItemDecorations;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by JinT on 2018/3/16 0016.
 */

public class CardsItemDecoration extends RecyclerView.ItemDecoration {
    private int margin;

    public CardsItemDecoration(int margin) {
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildLayoutPosition(view) != 0) {
            outRect.top = margin;
        }
    }
}
