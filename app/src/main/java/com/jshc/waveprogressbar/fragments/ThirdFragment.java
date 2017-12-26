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
import com.jshc.waveprogressbar.beans.FashionBean;
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
    private int PageNo = 50;
    private List<Fragment> fragmentList;
    private List<FashionBean> fashionBeanList;

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
        fragmentList = new ArrayList<>();
        fashionBeanList = new ArrayList<>();
        bannerList.add("http://img2.niutuku.com/desk/1208/1924/ntk-1924-20355.jpg");
        bannerList.add("http://www.wallcoo.com/1440x900/1440x900_CG_design_wallpapers/wallpapers/1440x900/Free_High_resolution_wallpaper_00402_peacewhenthedayisdone_1440x900.jpg");
        bannerList.add("http://dl.bizhi.sogou.com/images/2012/01/27/31794.jpg");
        bannerList.add("http://img2.niutuku.com/desk/1208/1450/ntk-1450-9891.jpg");
        bannerList.add("http://img2.niutuku.com/desk/1208/1508/ntk-1508-32823.jpg");
        fragmentList.add(new ViewPagerFirstFragment());
        fragmentList.add(new ViewPagerSecondFragment());
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    FashionBean fashionBean0 = new FashionBean(i, "时尚手机", "http://pic.58pic.com/58pic/13/58/79/01m58PICyN5_1024.jpg");
                    fashionBeanList.add(fashionBean0);
                    break;
                case 1:
                    FashionBean fashionBean1 = new FashionBean(i, "欧美风", "http://img5.duitang.com/uploads/item/201407/31/20140731115255_AMZQT.png");
                    fashionBeanList.add(fashionBean1);
                    break;
                case 2:
                    FashionBean fashionBean2 = new FashionBean(i, "爱宝宝", "http://a0.att.hudong.com/52/06/28300001051406137281063561335_950.jpg");
                    fashionBeanList.add(fashionBean2);
                    break;
                case 3:
                    FashionBean fashionBean3 = new FashionBean(i, "爱美丽", "http://img0.imgtn.bdimg.com/it/u=2274895555,3989399360&fm=214&gp=0.jpg");
                    fashionBeanList.add(fashionBean3);
                    break;
                case 4:
                    FashionBean fashionBean4 = new FashionBean(i, "爱吃", "http://img.redocn.com/sheying/20160711/woaichixiguaertonghua_6682037.jpg");
                    fashionBeanList.add(fashionBean4);
                    break;
                case 5:
                    FashionBean fashionBean5 = new FashionBean(i, "精选", "http://pic25.photophoto.cn/20121028/0017030070382546_b.jpg");
                    fashionBeanList.add(fashionBean5);
                    break;
                case 6:
                    FashionBean fashionBean6 = new FashionBean(i, "首发", "http://image20.it168.com/201503_800x800/2136/c3a2a77ce78e2031.jpg");
                    fashionBeanList.add(fashionBean6);
                    break;
                case 7:
                    FashionBean fashionBean7 = new FashionBean(i, "闪购", "http://img.25pp.com/uploadfile/app/icon/20160625/1466837378743751.jpg");
                    fashionBeanList.add(fashionBean7);
                    break;
            }
        }
        for (int i = 0; i < PageNo; i++) {
            GoodBean bean = null;
            if (i % 2 == 0) {
                bean = new GoodBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514283263480&di=365008e79218598a07d14a57f5719ebd&imgtype=0" +
                        "&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F0df3d7ca7bcb0a4667e41afa6163f6246a60af2b.jpg", "时尚潮裙" + (i + 1));
            } else {
                bean = new GoodBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514279410337&di=b1a04023d1ef0ee1ab74cc45d8177306&imgtype=0" +
                        "&src=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F140706%2F318739-140F60I23854.jpg", "时尚潮裙" + (i + 1));
            }
            goodList.add(bean);
        }
        shoppingCartAdapter = new ShoppingCartAdapter(getActivity(), goodList, bannerList, fragmentList, fashionBeanList, getChildFragmentManager());
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
