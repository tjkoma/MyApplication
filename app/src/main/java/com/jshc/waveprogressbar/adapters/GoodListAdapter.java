package com.jshc.waveprogressbar.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.beans.GoodBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JinT on 2017/12/26 0026.
 */

public class GoodListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<GoodBean> list;
    private OnGoodItemClickListener listener;

    public GoodListAdapter(Context context, List<GoodBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.common_item, parent, false);
        GoodViewHolder viewHolder = new GoodViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getGoodImage()).into(((GoodViewHolder) holder).goodImageImageView);
        ((GoodViewHolder) holder).goodNameTextView.setText(list.get(position).getGoodName());
        ((GoodViewHolder) holder).goodLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onGoodItemClick(list.get(position).getGoodName(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GoodViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.good_LinearLayout)
        LinearLayout goodLinearLayout;
        @BindView(R.id.goodImage_imageView)
        ImageView goodImageImageView;
        @BindView(R.id.goodName_textView)
        TextView goodNameTextView;

        public GoodViewHolder(View GoodItemView) {
            super(GoodItemView);
            ButterKnife.bind(this, GoodItemView);
        }
    }

    public void setOnGoodItemClickListener(OnGoodItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnGoodItemClickListener {
        void onGoodItemClick(String str, int index);
    }
}
