package com.jshc.waveprogressbar.activitys;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.adapters.FriendAdapter;
import com.jshc.waveprogressbar.beans.FriendBean;
import com.jshc.waveprogressbar.views.SiderView;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.friend_recyclerView)
    RecyclerView friendRecyclerView;

    private String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private FriendAdapter friendAdapter;
    private List<FriendBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        list = new ArrayList<>();
        siderView.setOnTextSelectListener(this);
        for (int i = 0; i < words.length; i++) {
            FriendBean bean = new FriendBean(words[i]);
            list.add(bean);
        }
        friendAdapter = new FriendAdapter(this, list);
        friendRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        friendRecyclerView.setAdapter(friendAdapter);
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
                for (int i = 0; i < words.length; i++) {
                    if (text.equals(words[i])) {
                        friendRecyclerView.scrollToPosition(i);
                        LinearLayoutManager manager = (LinearLayoutManager) friendRecyclerView.getLayoutManager();
                        manager.scrollToPositionWithOffset(i, 0);
                    }
                }
                break;
        }
    }
}
