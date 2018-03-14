package com.jshc.waveprogressbar.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jshc.waveprogressbar.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordActivity extends AppCompatActivity {
    private static final String TAG = RecordActivity.class.getSimpleName();
    @BindView(R.id.getData_btn)
    Button getDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);
    }

    @OnClick (R.id.getData_btn)
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.getData_btn:

                break;
        }
    }
}
