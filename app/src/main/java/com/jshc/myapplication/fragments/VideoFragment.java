package com.jshc.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jshc.myapplication.R;
import com.jshc.myapplication.services.CallBackDataService;
import com.jshc.myapplication.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by JinT on 2017/12/15 0015.
 */

public class VideoFragment extends Fragment {

    @BindView(R.id.video_textView)
    TextView videoTextView;
    Unbinder unbinder;

    private static final String url = "http://apis.juhe.cn/cook/query.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        Map<String, Object> map = new HashMap<>();
        map.put("menu","秘制红烧肉");
        map.put("key","88b8e2d806fa3eb422fc0be166a8f452");
        map.put("dtype","json");
        map.put("pn","1");
        map.put("rn","30");
        map.put("albums",1);
        HttpUtils.postData(url, map, new CallBackDataService() {
            @Override
            public void Successed(final Object object) {
                if (getActivity() == null) return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        videoTextView.setText(object.toString());
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
