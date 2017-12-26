package com.jshc.waveprogressbar.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.beans.CategoryBean;
import com.jshc.waveprogressbar.utils.RoundBitmapTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by JinT on 2017/12/25 0025.
 */

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CategoryBean> list;
    private OnItemClickListener listener;

    public CategoryAdapter(Context context, List<CategoryBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        CateItemViewHolder viewHolder = new CateItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        RoundBitmapTransform roundBitmapTransform = new RoundBitmapTransform(context, 10);
        Glide.with(context).load(list.get(position).getImage()).transform(roundBitmapTransform).into(((CateItemViewHolder) holder).categoryCircleImageView);
        ((CateItemViewHolder) holder).categoryTextView.setText(list.get(position).getName());
        ((CateItemViewHolder) holder).categoryLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemClick(list.get(position).getName(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CateItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.category_LinearLayout)
        LinearLayout categoryLinearLayout;
        @BindView(R.id.category_circleImageView)
        CircleImageView categoryCircleImageView;
        @BindView(R.id.category_textView)
        TextView categoryTextView;

        public CateItemViewHolder(View cateItemView) {
            super(cateItemView);
            ButterKnife.bind(this, cateItemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(String str, int index);
    }
}
