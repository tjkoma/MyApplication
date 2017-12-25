package com.jshc.waveprogressbar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.adapters.CategoryAdapter;
import com.jshc.waveprogressbar.beans.CategoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by JinT on 2017/12/25 0025.
 */

public class ViewPagerFirstFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.viewpager_first_recyclerView)
    RecyclerView viewpagerFirstRecyclerView;
    private CategoryAdapter categoryAdapter;
    private List<CategoryBean> cateList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_first_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        cateList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            CategoryBean bean = new CategoryBean();
            bean.setImage("http://img2.niutuku.com/1312/0754/0754-niutuku.com-860.jpg");
            bean.setName("热卖");
            cateList.add(bean);
        }
        categoryAdapter = new CategoryAdapter(getActivity(), cateList);
        viewpagerFirstRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        viewpagerFirstRecyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
