package com.jshc.waveprogressbar.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.modles.FmodelImpl;
import com.jshc.waveprogressbar.presenters.Fpresenter;
import com.jshc.waveprogressbar.presenters.FpresenterImpl;
import com.jshc.waveprogressbar.utils.HttpUrlConstant;
import com.jshc.waveprogressbar.utils.JNIUtil;
import com.jshc.waveprogressbar.views.Fview;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordActivity<T> extends AppCompatActivity implements Fview<T> {
    private static final String TAG = RecordActivity.class.getSimpleName();
    @BindView(R.id.getData_btn)
    Button getDataBtn;
    @BindView(R.id.data_textView)
    TextView dataTextView;
    @BindView(R.id.jni_textView)
    TextView jniTextView;

    private Fpresenter fpresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        JNIUtil jniUtil = new JNIUtil();
        jniTextView.setText(jniUtil.getMessage());
        fpresenter = new FpresenterImpl<>(this, new FmodelImpl());
    }

    @OnClick(R.id.getData_btn)
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.getData_btn:
                Map<String, Object> map = new HashMap<>();
                map.put("menu", "宫保鸡丁");
                map.put("key", "88b8e2d806fa3eb422fc0be166a8f452");
                map.put("dtype", "json");
                map.put("pn", "1");
                map.put("rn", "20");
                map.put("albums", 1);
                fpresenter.getRequstData(HttpUrlConstant.GETCOOK, map);
                break;
        }
    }

    @Override
    public void onSuccessed(T data) {
        dataTextView.setText(new Gson().toJson(data));
    }
}
