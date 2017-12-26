package com.jshc.waveprogressbar.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jshc.waveprogressbar.R;
import com.jshc.waveprogressbar.activitys.GoodDetailsActivity;
import com.jshc.waveprogressbar.beans.FashionBean;
import com.jshc.waveprogressbar.beans.GoodBean;
import com.jshc.waveprogressbar.utils.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 混合布局的RecyclerView
 * Created by JinT on 2017/12/15 0015.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements GoodListAdapter.OnGoodItemClickListener {
    private Context context;
    private List<GoodBean> list;
    private List<String> bannerList;
    private List<Fragment> fragmentList;
    private List<FashionBean> fashionBeanList;
    private FragmentManager fragmentManager;
    private int bannerItem = 0;
    private int viewPagerItem = 0;
    private int gridItem = 0;
    private int recyclerItem = 0;

    private static final int BANNER_ITEM = 0;//banner图
    private static final int VIEWPAGER_ITEM = 1;//分类选择viewpager
    private static final int GRID_ITEM = 2;//分类选择gridview
    private static final int RECYCLER_ITEM = 3;//普通的条目

    public ShoppingCartAdapter(Context context, List<GoodBean> list, List<String> bannerList, List<Fragment> fragmentList, List<FashionBean> fashionBeanList, FragmentManager fragmentManager) {
        this.context = context;
        this.list = list;
        this.bannerList = bannerList;
        this.fragmentList = fragmentList;
        this.fashionBeanList = fashionBeanList;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return BANNER_ITEM;
            case 1:
                return VIEWPAGER_ITEM;
            case 2:
                return GRID_ITEM;
            default:
                return RECYCLER_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case BANNER_ITEM:
                View bannerView = LayoutInflater.from(context).inflate(R.layout.banner_item, parent, false);
                viewHolder = new BannerViewHolder(bannerView);
                break;
            case VIEWPAGER_ITEM:
                View viewpagerView = LayoutInflater.from(context).inflate(R.layout.viewpager_item, parent, false);
                viewHolder = new ViewPagerViewHolder(viewpagerView);
                break;
            case GRID_ITEM:
                View griditemView = LayoutInflater.from(context).inflate(R.layout.gridview_item, parent, false);
                viewHolder = new GridViewHolder(griditemView);
                break;
            case RECYCLER_ITEM:
                View view = LayoutInflater.from(context).inflate(R.layout.shopping_cart_item, parent, false);
                viewHolder = new CommonViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            ((BannerViewHolder) holder).shoppingBanner.setImages(bannerList).setImageLoader(new GlideImageLoader()).setDelayTime(3000).start();
        } else if (holder instanceof ViewPagerViewHolder) {

            CateViewPageAdapter cateViewPageAdapter = new CateViewPageAdapter(fragmentManager, context, fragmentList);
            ((ViewPagerViewHolder) holder).categoryViewPager.setAdapter(cateViewPageAdapter);
            //切换fragment的时候变换底部指示器的颜色
            ((ViewPagerViewHolder) holder).categoryViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 0) {
                        ((ViewPagerViewHolder) holder).categoryFirstView.setBackgroundResource(R.drawable.viewpager_selected_indicator);
                        ((ViewPagerViewHolder) holder).categorySecondView.setBackgroundResource(R.drawable.viewpager_unselected_indicator);
                    } else if (position == 1) {
                        ((ViewPagerViewHolder) holder).categoryFirstView.setBackgroundResource(R.drawable.viewpager_unselected_indicator);
                        ((ViewPagerViewHolder) holder).categorySecondView.setBackgroundResource(R.drawable.viewpager_selected_indicator);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        } else if (holder instanceof GridViewHolder) {
            FashionAdapter fashionAdapter = new FashionAdapter(context, fashionBeanList);
            ((GridViewHolder) holder).gridviewRecyclerView.setLayoutManager(new GridLayoutManager(context, 4));
            ((GridViewHolder) holder).gridviewRecyclerView.setAdapter(fashionAdapter);
        } else {
            GoodListAdapter goodListAdapter = new GoodListAdapter(context, list);
            //修改GridLayoutManager的LayoutParams，让视图填充完整
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2) {
                @Override
                public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                    return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                }
            };
            ((CommonViewHolder) holder).commonRecyclerView.setLayoutManager(gridLayoutManager);
            ((CommonViewHolder) holder).commonRecyclerView.setAdapter(goodListAdapter);
            goodListAdapter.setOnGoodItemClickListener(this);
        }
    }

    public int realPosition(int pos) {
        return pos - bannerItem - viewPagerItem - gridItem;
    }

    @Override
    public int getItemCount() {
        if (!bannerList.isEmpty()) {
            bannerItem = 1;
        }
        if (!fragmentList.isEmpty()) {
            viewPagerItem = 1;
        }
        if (!fashionBeanList.isEmpty()) {
            gridItem = 1;
        }
        if (!list.isEmpty()) {
            recyclerItem = 1;
        }
        return recyclerItem + bannerItem + viewPagerItem + gridItem;
    }

    /**
     * 条目点击事件
     *
     * @param str
     * @param index
     */
    @Override
    public void onGoodItemClick(String str, int index) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, GoodDetailsActivity.class);
        context.startActivity(intent);
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shopping_Banner)
        Banner shoppingBanner;

        public BannerViewHolder(View bannerItemView) {
            super(bannerItemView);
            ButterKnife.bind(this, bannerItemView);
        }
    }

    public class ViewPagerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.category_viewPager)
        ViewPager categoryViewPager;
        @BindView(R.id.category_first_view)
        View categoryFirstView;
        @BindView(R.id.category_second_view)
        View categorySecondView;

        public ViewPagerViewHolder(View viewpagerItemView) {
            super(viewpagerItemView);
            ButterKnife.bind(this, viewpagerItemView);
        }
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.newsflash_textView)
        TextView newsflashTextView;
        @BindView(R.id.gridview_recyclerView)
        RecyclerView gridviewRecyclerView;

        public GridViewHolder(View gridItemView) {
            super(gridItemView);
            ButterKnife.bind(this, gridItemView);
        }
    }

    public class CommonViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.common_recyclerView)
        RecyclerView commonRecyclerView;

        public CommonViewHolder(View item) {
            super(item);
            ButterKnife.bind(this, item);
        }
    }
}
