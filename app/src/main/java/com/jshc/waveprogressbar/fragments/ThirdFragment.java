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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;


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
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.loading_LinearLayout)
    LinearLayout loadingLinearLayout;

    private ShoppingCartAdapter shoppingCartAdapter;
    private List<GoodBean> goodList;
    private List<String> bannerList;
    private ApiService apiService;
    private int PageNo = 10;

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
        bannerList = new ArrayList<>();
        bannerList.add("http://img2.niutuku.com/desk/1208/1924/ntk-1924-20355.jpg");
        bannerList.add("http://www.wallcoo.com/1440x900/1440x900_CG_design_wallpapers/wallpapers/1440x900/Free_High_resolution_wallpaper_00402_peacewhenthedayisdone_1440x900.jpg");
        bannerList.add("http://dl.bizhi.sogou.com/images/2012/01/27/31794.jpg");
        bannerList.add("http://img2.niutuku.com/desk/1208/1450/ntk-1450-9891.jpg");
        bannerList.add("http://img2.niutuku.com/desk/1208/1508/ntk-1508-32823.jpg");
        for (int i = 0; i < PageNo; i++) {
            GoodBean bean = new GoodBean();
            bean.setGoodName("衣服" + (i + 1));
            goodList.add(bean);
        }
        shoppingCartAdapter = new ShoppingCartAdapter(getActivity(), goodList,bannerList);
        thirdRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        thirdRecyclerView.setAdapter(shoppingCartAdapter);
//        getCookData();

        thirdRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case SCROLL_STATE_IDLE:
//                        if (isVisBottom(thirdRecyclerView)) {
//                            loadingLinearLayout.setVisibility(View.VISIBLE);
//                            PageNo = PageNo + 10;
//                        }
                        break;
                    case SCROLL_STATE_DRAGGING:

                        break;
                    case SCROLL_STATE_SETTLING:

                        break;
                }
            }
        });
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
        Observable<CookBean> call = apiService.getCookData(map);
        call.subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CookBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CookBean value) {
                        Log.e("TAG", new Gson().toJson(value));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 判断recyclerview是否滑动到底部
     *
     * @param recyclerView
     * @return
     */
    public static boolean isVisBottom(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //屏幕中最后一个可见子项的position
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        //当前屏幕所看到的子项个数
        int visibleItemCount = layoutManager.getChildCount();
        //当前RecyclerView的所有子项个数
        int totalItemCount = layoutManager.getItemCount();
        //RecyclerView的滑动状态
        int state = recyclerView.getScrollState();
        if (visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == SCROLL_STATE_IDLE) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
