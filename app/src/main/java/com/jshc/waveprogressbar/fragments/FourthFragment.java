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
import com.jshc.waveprogressbar.activitys.CommunicationActivity;
import com.jshc.waveprogressbar.activitys.MyMapActivity;
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
            } else if (i == 3) {
                mineList.add("我的运动");
            }
            mineList.add(i + 1 + "");
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
            case 4:
                Intent intent = new Intent(getActivity(), MyMapActivity.class);
                startActivity(intent);
                break;
        }
    }
}
