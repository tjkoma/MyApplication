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

public class ViewPagerSecondFragment extends Fragment {

    @BindView(R.id.viewpager_second_recyclerView)
    RecyclerView viewpagerSecondRecyclerView;
    Unbinder unbinder;

    private CategoryAdapter categoryAdapter;
    private List<CategoryBean> cateList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_second_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        cateList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    CategoryBean bean0 = new CategoryBean("http://img2.niutuku.com/desk/1207/1144/bizhi-1144-9250.jpg", "排行榜");
                    cateList.add(bean0);
                    break;
                case 1:
                    CategoryBean bean1 = new CategoryBean("http://img2.niutuku.com/1312/0827/0827-niutuku.com-10498.jpg", "二手");
                    cateList.add(bean1);
                    break;
                case 2:
                    CategoryBean bean2 = new CategoryBean("http://img3.imgtn.bdimg.com/it/u=3217005988,3952794557&fm=214&gp=0.jpg", "火车票");
                    cateList.add(bean2);
                    break;
                case 3:
                    CategoryBean bean3 = new CategoryBean("http://img.zcool.cn/community/038f05258916a3aa801219c7778e446.jpg", "电影票");
                    cateList.add(bean3);
                    break;
                case 4:
                    CategoryBean bean4 = new CategoryBean("http://img2.niutuku.com/desk/130220/44/44-niutuku.com-2088.jpg", "拍卖");
                    cateList.add(bean4);
                    break;
                case 5:
                    CategoryBean bean5 = new CategoryBean("http://dl.bizhi.sogou.com/images/2014/01/03/476572.jpg", "回收");
                    cateList.add(bean5);
                    break;
                case 6:
                    CategoryBean bean6 = new CategoryBean("http://www.wallcoo.com/cartoon/kitsunenoir_design_illustration_v/wallpapers/1920x1200/kim-holtermand-reflections.jpg", "智能");
                    cateList.add(bean6);
                    break;
                case 7:
                    CategoryBean bean7 = new CategoryBean("http://dl.bizhi.sogou.com/images/2012/03/01/80265.jpg", "全部");
                    cateList.add(bean7);
                    break;
            }
        }
        categoryAdapter = new CategoryAdapter(getActivity(), cateList);
        viewpagerSecondRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        viewpagerSecondRecyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
