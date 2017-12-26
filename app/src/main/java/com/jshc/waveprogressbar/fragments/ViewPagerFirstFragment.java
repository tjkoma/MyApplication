package com.jshc.waveprogressbar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

public class ViewPagerFirstFragment extends Fragment implements CategoryAdapter.OnItemClickListener {

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
            switch (i) {
                case 0:
                    CategoryBean bean0 = new CategoryBean("http://img2.niutuku.com/1312/0754/0754-niutuku.com-860.jpg", "超市");
                    cateList.add(bean0);
                    break;
                case 1:
                    CategoryBean bean1 = new CategoryBean("http://img2.niutuku.com/desk/1208/1712/ntk-1712-67346.jpg", "全球购");
                    cateList.add(bean1);
                    break;
                case 2:
                    CategoryBean bean2 = new CategoryBean("http://img2.niutuku.com/desk/1208/1307/ntk-1307-6459.jpg", "服饰");
                    cateList.add(bean2);
                    break;
                case 3:
                    CategoryBean bean3 = new CategoryBean("http://img2.niutuku.com/desk/1208/1420/ntk-1420-17603.jpg", "生鲜");
                    cateList.add(bean3);
                    break;
                case 4:
                    CategoryBean bean4 = new CategoryBean("http://img5.imgtn.bdimg.com/it/u=4117439175,1062059118&fm=11&gp=0.jpg", "到家");
                    cateList.add(bean4);
                    break;
                case 5:
                    CategoryBean bean5 = new CategoryBean("http://tupian.enterdesk.com/2015/mxy/6/14/12/3.jpg", "充值");
                    cateList.add(bean5);
                    break;
                case 6:
                    CategoryBean bean6 = new CategoryBean("http://img2.niutuku.com/desk/130126/24/24-niutuku.com-1488.jpg", "领券");
                    cateList.add(bean6);
                    break;
                case 7:
                    CategoryBean bean7 = new CategoryBean("http://img2.niutuku.com/desk/1208/2045/ntk-2045-8214.jpg", "会员");
                    cateList.add(bean7);
                    break;
            }
        }
        categoryAdapter = new CategoryAdapter(getActivity(), cateList);
        viewpagerFirstRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        viewpagerFirstRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 条目点击事件
     *
     * @param str
     * @param index
     */
    @Override
    public void itemClick(String str, int index) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }
}
