package com.jshc.waveprogressbar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.adapters.ShoppingCartAdapter;
import com.jshc.waveprogressbar.beans.CookBean;
import com.jshc.waveprogressbar.beans.GoodBean;
import com.jshc.waveprogressbar.services.ApiService;
import com.jshc.waveprogressbar.utils.RetrofitUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


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
    private ApiService apiService;

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
//        getCookData();
    }

    private void getCookData() {
        Map<String, Object> map = new HashMap<>();
        map.put("menu", "宫保鸡丁");
        map.put("key", "88b8e2d806fa3eb422fc0be166a8f452");
        map.put("dtype", "json");
        map.put("pn", "1");
        map.put("rn", "30");
        map.put("albums", 1);
        apiService = RetrofitUtils.getInstance().create(ApiService.class);
        Observable<CookBean> call =  apiService.getCookData(map);
        call.subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CookBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CookBean value) {
                        Log.e("TAG",new Gson().toJson(value));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
