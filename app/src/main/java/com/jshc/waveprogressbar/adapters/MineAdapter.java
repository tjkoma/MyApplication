package com.jshc.waveprogressbar.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jshc.waveprogressbar.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JinT on 2017/12/20 0020.
 */

public class MineAdapter extends RecyclerView.Adapter<MineAdapter.ViewHolder> {

    private Context context;
    private List<String> list;
    private OnMineItemClickListener listener;

    public MineAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mine_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mineItemTextView.setText(list.get(position));
        holder.mineItemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mine_item_LinearLayout)
        LinearLayout mineItemLinearLayout;
        @BindView(R.id.mine_item_textView)
        TextView mineItemTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnMineItemClickListener(OnMineItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnMineItemClickListener {
        void onItemClick(int index);
    }
}
