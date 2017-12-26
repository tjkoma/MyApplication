package com.jshc.waveprogressbar.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.beans.FashionBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JinT on 2017/12/26 0026.
 */

public class FashionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<FashionBean> fashionBeanList;

    public FashionAdapter(Context context, List<FashionBean> fashionBeanList) {
        this.context = context;
        this.fashionBeanList = fashionBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fashion_item, parent, false);
        FashionViewHolder fashionViewHolder = new FashionViewHolder(view);
        return fashionViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(fashionBeanList.get(position).getImage()).into(((FashionViewHolder) holder).fashionImageView);
        ((FashionViewHolder) holder).fashionTextView.setText("--" + fashionBeanList.get(position).getName() + "--");
    }

    @Override
    public int getItemCount() {
        return fashionBeanList.size();
    }

    public class FashionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fashion_imageView)
        ImageView fashionImageView;
        @BindView(R.id.fashion_textView)
        TextView fashionTextView;

        public FashionViewHolder(View fashionItemView) {
            super(fashionItemView);
            ButterKnife.bind(this, fashionItemView);
        }
    }
}
