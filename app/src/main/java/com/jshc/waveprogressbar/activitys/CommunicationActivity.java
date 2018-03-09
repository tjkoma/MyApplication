package com.jshc.waveprogressbar.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.views.SiderView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 通讯录界面
 */
public class CommunicationActivity extends BaseActivity implements SiderView.OnTextSelectListener {
    private static final String TAG = CommunicationActivity.class.getSimpleName();
    @BindView(R.id.siderView)
    SiderView siderView;
    @BindView(R.id.sider_text_tv)
    TextView siderTextTv;
    @BindView(R.id.sider_text_linearLayout)
    LinearLayout siderTextLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        siderView.setOnTextSelectListener(this);
    }

    @Override
    public void onTextClick(int index, String text) {
        switch (index) {
            case -1:
                siderTextLinearLayout.setVisibility(View.GONE);
                break;
            default:
                siderTextLinearLayout.setVisibility(View.VISIBLE);
                siderTextTv.setText(text);
                break;
        }
    }
}
