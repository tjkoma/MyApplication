package com.jshc.waveprogressbar.activitys;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.fragments.FirstFragment;
import com.jshc.waveprogressbar.fragments.FourthFragment;
import com.jshc.waveprogressbar.fragments.SecondFragment;
import com.jshc.waveprogressbar.fragments.ThirdFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.replace_linearLayout)
    LinearLayout replaceLinearLayout;
    @BindView(R.id.first_textView)
    TextView firstTextView;
    @BindView(R.id.second_textView)
    TextView secondTextView;
    @BindView(R.id.third_textView)
    TextView thirdTextView;
    @BindView(R.id.forth_textView)
    TextView forthTextView;
    private AlertDialog mAlertDialog;

    private android.support.v4.app.FragmentManager fragmentManager;
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private FourthFragment fourthFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        firstTextView.performClick();
//        initView();
    }

    private void initView() {
        mAlertDialog = new AlertDialog.Builder(this).create();
        View view = LayoutInflater.from(this).inflate(R.layout.alertdialog, null);
        mAlertDialog.setView(view);
        mAlertDialog.show();
        mAlertDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
    }

    @OnClick({R.id.first_textView, R.id.second_textView, R.id.third_textView, R.id.forth_textView})
    public void onViewClick(View view) {
        fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);
        resetTextViewState();
        switch (view.getId()) {
            case R.id.first_textView:
                firstTextView.setTextColor(getResources().getColor(R.color.yellow));
                firstTextView.setBackgroundColor(getResources().getColor(R.color.gray));
                if (firstFragment == null) {
                    firstFragment = new FirstFragment();
                    transaction.add(R.id.replace_linearLayout, firstFragment);
                } else {
                    transaction.show(firstFragment);
                }
                break;
            case R.id.second_textView:
                secondTextView.setTextColor(getResources().getColor(R.color.yellow));
                secondTextView.setBackgroundColor(getResources().getColor(R.color.gray));
                if (secondFragment == null) {
                    secondFragment = new SecondFragment();
                    transaction.add(R.id.replace_linearLayout, secondFragment);
                } else {
                    transaction.show(secondFragment);
                }
                break;
            case R.id.third_textView:
                thirdTextView.setTextColor(getResources().getColor(R.color.yellow));
                thirdTextView.setBackgroundColor(getResources().getColor(R.color.gray));
                if (thirdFragment == null) {
                    thirdFragment = new ThirdFragment();
                    transaction.add(R.id.replace_linearLayout, thirdFragment);
                } else {
                    transaction.show(thirdFragment);
                }
                break;
            case R.id.forth_textView:
                forthTextView.setTextColor(getResources().getColor(R.color.yellow));
                forthTextView.setBackgroundColor(getResources().getColor(R.color.gray));
                if (fourthFragment == null) {
                    fourthFragment = new FourthFragment();
                    transaction.add(R.id.replace_linearLayout, fourthFragment);
                } else {
                    transaction.show(fourthFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏所有的Fragment
     *
     * @param fragmentTransaction
     */
    private void hideAllFragment(android.support.v4.app.FragmentTransaction fragmentTransaction) {
        if (firstFragment != null) {
            fragmentTransaction.hide(firstFragment);
        }
        if (secondFragment != null) {
            fragmentTransaction.hide(secondFragment);
        }
        if (thirdFragment != null) {
            fragmentTransaction.hide(thirdFragment);
        }
        if (fourthFragment != null) {
            fragmentTransaction.hide(fourthFragment);
        }
    }

    /**
     * 回复初始状态
     */
    private void resetTextViewState() {
        firstTextView.setTextColor(getResources().getColor(R.color.buttomText));
        secondTextView.setTextColor(getResources().getColor(R.color.buttomText));
        thirdTextView.setTextColor(getResources().getColor(R.color.buttomText));
        forthTextView.setTextColor(getResources().getColor(R.color.buttomText));
        firstTextView.setBackgroundColor(getResources().getColor(R.color.white));
        secondTextView.setBackgroundColor(getResources().getColor(R.color.white));
        thirdTextView.setBackgroundColor(getResources().getColor(R.color.white));
        forthTextView.setBackgroundColor(getResources().getColor(R.color.white));
    }

}
