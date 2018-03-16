package com.jshc.waveprogressbar.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.beans.CardBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JinT on 2018/3/16 0016.
 */

public class CardsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CardBean> list;

    public CardsAdapter(Context context, List<CardBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item_layout, parent, false);
        CardViewHolder viewHolder = new CardViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CardViewHolder cardViewHolder = (CardViewHolder) holder;
        if (position % 2 == 0) {
            cardViewHolder.cardsLinearLayout.setBackground(context.getResources().getDrawable(R.drawable.card2_background));
        }else{
            cardViewHolder.cardsLinearLayout.setBackground(context.getResources().getDrawable(R.drawable.card_background));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cards_linearLayout)
        LinearLayout cardsLinearLayout;

        public CardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
