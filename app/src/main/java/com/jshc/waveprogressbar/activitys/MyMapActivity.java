package com.jshc.waveprogressbar.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jshc.waveprogressbar.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMapActivity extends AppCompatActivity {

    @BindView(R.id.base_title_textView)
    TextView baseTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_map);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        baseTitleTextView.setText("我的地图");
    }
}
