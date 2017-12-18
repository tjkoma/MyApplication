package com.jshc.waveprogressbar.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.beans.CookBean;

import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JinT on 2017/12/18 0018.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private Context context;
    private List<CookBean.ResultBean.DataBean> list;

    public VideoAdapter(Context context, List<CookBean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_fragment_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getAlbums().get(0)).into(holder.videoMainImageView);
        holder.titleTextView.setText(list.get(position).getTitle());
        holder.tagsTextView.setText(list.get(position).getTags());
        holder.ingredientsTextView.setText(list.get(position).getIngredients());
        holder.burdenTextView.setText(list.get(position).getBurden());
        VideoItemAdapter videoItemAdapter = new VideoItemAdapter(context, list.get(position).getSteps());
        holder.videoStepRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.videoStepRecyclerView.setAdapter(videoItemAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(Collection<? extends CookBean.ResultBean.DataBean> collection) {
        list.addAll(collection);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.video_main_imageView)
        ImageView videoMainImageView;
        @BindView(R.id.title_textView)
        TextView titleTextView;
        @BindView(R.id.tags_textView)
        TextView tagsTextView;
        @BindView(R.id.ingredients_textView)
        TextView ingredientsTextView;
        @BindView(R.id.burden_textView)
        TextView burdenTextView;
        @BindView(R.id.video_step_recyclerView)
        RecyclerView videoStepRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class VideoItemAdapter extends RecyclerView.Adapter<VideoItemAdapter.ViewHolder> {
        private Context mContext;
        private List<CookBean.ResultBean.DataBean.StepsBean> mList;

        public VideoItemAdapter(Context mContext, List<CookBean.ResultBean.DataBean.StepsBean> mList) {
            this.mContext = mContext;
            this.mList = mList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View mView = LayoutInflater.from(mContext).inflate(R.layout.cook_item_step, parent, false);
            ViewHolder mViewHolder = new ViewHolder(mView);
            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Glide.with(mContext).load(mList.get(position).getImg()).into(holder.stepImageView);
            holder.stepTextView.setText(mList.get(position).getStep());
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.step_imageView)
            ImageView stepImageView;
            @BindView(R.id.step_textView)
            TextView stepTextView;

            public ViewHolder(View mItemView) {
                super(mItemView);
                ButterKnife.bind(this, mItemView);
            }
        }
    }
}
