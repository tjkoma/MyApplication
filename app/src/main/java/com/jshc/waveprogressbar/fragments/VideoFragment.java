package com.jshc.waveprogressbar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.adapters.VideoAdapter;
import com.jshc.waveprogressbar.beans.CookBean;
import com.jshc.waveprogressbar.services.CallBackDataService;
import com.jshc.waveprogressbar.utils.HttpUrlConstant;
import com.jshc.waveprogressbar.utils.HttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by JinT on 2017/12/15 0015.
 */

public class VideoFragment extends Fragment {
    private static final String TAG = VideoFragment.class.getSimpleName();

    Unbinder unbinder;
    @BindView(R.id.video_recyclerView)
    RecyclerView videoRecyclerView;

    private VideoAdapter videoAdapter;
    private List<CookBean.ResultBean.DataBean> cookList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        cookList = new ArrayList<>();
        videoAdapter = new VideoAdapter(getActivity(), cookList);
        videoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        videoRecyclerView.setAdapter(videoAdapter);

        Map<String, Object> map = new HashMap<>();
        map.put("menu", "宫保鸡丁");
        map.put("key", "88b8e2d806fa3eb422fc0be166a8f452");
        map.put("dtype", "json");
        map.put("pn", "1");
        map.put("rn", "30");
        map.put("albums", 1);
        HttpUtils.postData(HttpUrlConstant.COOK, map, new CallBackDataService() {
            @Override
            public void Successed(final Object object) {
                if (getActivity() == null) return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, object.toString());
                        CookBean cookBean = new Gson().fromJson(object.toString(), CookBean.class);
                        if ("200".equals(cookBean.getResultcode())) {
                            CookBean.ResultBean resultBean = cookBean.getResult();
                            cookList = resultBean.getData();
                            videoAdapter.addData(cookList);
                        }
                    }
                });
            }

            @Override
            public void Failured(Exception e) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
