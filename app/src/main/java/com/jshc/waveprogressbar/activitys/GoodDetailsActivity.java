package com.jshc.waveprogressbar.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jshc.waveprogressbar.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodDetailsActivity extends BaseActivity {

    @BindView(R.id.base_imageView)
    ImageView baseImageView;
    @BindView(R.id.base_title_textView)
    TextView baseTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_details);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        baseImageView.setImageResource(R.mipmap.back);
        baseTitleTextView.setText("商品详情");
    }

    @OnClick({R.id.base_imageView})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.base_imageView:
                finish();
                break;
        }
    }
}
