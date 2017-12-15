package com.jshc.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jshc.myapplication.R;
import com.jshc.myapplication.adapters.ShoppingCartAdapter;
import com.jshc.myapplication.beans.GoodBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by JinT on 2017/12/15 0015.
 */

public class ThirdFragment extends Fragment {
    private static final String TAG = ThirdFragment.class.getSimpleName();

    @BindView(R.id.third_recyclerView)
    RecyclerView thirdRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.base_title_textView)
    TextView baseTitleTextView;

    private ShoppingCartAdapter shoppingCartAdapter;
    private List<GoodBean> goodList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        baseTitleTextView.setText("购物车");
        goodList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            GoodBean bean = new GoodBean();
            bean.setGoodName("衣服" + (i + 1));
            goodList.add(bean);
        }
        shoppingCartAdapter = new ShoppingCartAdapter(getActivity(), goodList);
        thirdRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        thirdRecyclerView.setAdapter(shoppingCartAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
