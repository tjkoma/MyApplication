package com.jshc.waveprogressbar.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.beans.FriendBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JinT on 2018/3/9 0009.
 */

public class FriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<FriendBean> list;

    public FriendAdapter(Context context, List<FriendBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.friend_item_layout, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FriendViewHolder) holder).friendTitleTextView.setText(list.get(position).getTextName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.friend_title_textView)
        TextView friendTitleTextView;

        public FriendViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
