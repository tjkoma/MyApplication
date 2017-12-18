package com.jshc.waveprogressbar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.views.MyProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.itangqi.waveloadingview.WaveLoadingView;

/**
 * Created by JinT on 2017/12/15 0015.
 */

public class FirstFragment extends Fragment {
    @BindView(R.id.myProgressBar)
    MyProgressBar myProgressBar;
    @BindView(R.id.waveLoadingView)
    WaveLoadingView waveLoadingView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        myProgressBar.setProgress(25);
    }

}
