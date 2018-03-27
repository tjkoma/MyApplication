package com.jshc.waveprogressbar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.activitys.CardsActivity;
import com.jshc.waveprogressbar.activitys.CommunicationActivity;
import com.jshc.waveprogressbar.activitys.MyMapActivity;
import com.jshc.waveprogressbar.activitys.PullreFreshActivity;
import com.jshc.waveprogressbar.activitys.RecordActivity;
import com.jshc.waveprogressbar.adapters.MineAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我的界面
 * Created by JinT on 2017/12/15 0015.
 */

public class FourthFragment extends Fragment implements MineAdapter.OnMineItemClickListener {
    @BindView(R.id.mine_RecyclerView)
    RecyclerView mineRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.mine_collapsingToolbarLayout)
    CollapsingToolbarLayout mineCollapsingToolbarLayout;

    private List<String> mineList;
    private MineAdapter mineAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fourth_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mineCollapsingToolbarLayout.setTitle("我的");
        mineList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i == 1) {
                mineList.add("通讯录");
                continue;
            } else if (i == 3) {
                mineList.add("我的运动");
            } else if (i == 5) {
                mineList.add("数据");
            } else if (i == 7) {
                mineList.add("卡片");
            } else if (i == 8) {
                mineList.add("自定义上下拉刷新");
            } else {
                mineList.add(i + 1 + "");
            }

        }
        mineAdapter = new MineAdapter(getActivity(), mineList);
        mineRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mineRecyclerView.setAdapter(mineAdapter);
        mineAdapter.setOnMineItemClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 条目点击事件
     *
     * @param index
     */
    @Override
    public void onItemClick(int index) {
        switch (index) {
            case 1:
                Intent communIntent = new Intent(getActivity(), CommunicationActivity.class);
                startActivity(communIntent);
                break;
            case 3:
                Intent intent = new Intent(getActivity(), MyMapActivity.class);
                startActivity(intent);
                break;
            case 5:
                Intent dataIntent = new Intent(getActivity(), RecordActivity.class);
                startActivity(dataIntent);
                break;
            case 7:
                Intent cardIntent = new Intent(getActivity(), CardsActivity.class);
                startActivity(cardIntent);
                break;
            case 8:
                Intent pullIntent = new Intent(getActivity(), PullreFreshActivity.class);
                startActivity(pullIntent);
                break;
        }
    }
}
