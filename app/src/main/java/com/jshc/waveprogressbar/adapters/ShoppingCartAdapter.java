package com.jshc.waveprogressbar.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jshc.waveprogressbar.R;
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

public class ShoppingCartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<GoodBean> list;
    private List<String> bannerList;
    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;
    private static final int BANNER_ITEM = 0;
    private static final int VIEWPAGER_ITEM = 1;
    private static final int GRID_ITEM = 2;
    private static final int RECYCLER_ITEM = 3;

    public ShoppingCartAdapter(Context context, List<GoodBean> list, List<String> bannerList, List<Fragment> fragmentList, FragmentManager fragmentManager) {
        this.context = context;
        this.list = list;
        this.bannerList = bannerList;
        this.fragmentList = fragmentList;
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
        if (holder instanceof CommonViewHolder) {
            Glide.with(context).load("http://img3.redocn.com/20130602/Redocn_2013052815464777.jpg").into(((CommonViewHolder) holder).goodImageImageView);
            ((CommonViewHolder) holder).goodNameTextView.setText(list.get(realPosition(position)).getGoodName());
        } else if (holder instanceof BannerViewHolder) {
            ((BannerViewHolder) holder).shoppingBanner.setImages(bannerList).setImageLoader(new GlideImageLoader()).start();
        } else {

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

        }
    }

    public int realPosition(int pos) {
        return pos - 2;
    }

    @Override
    public int getItemCount() {
        if (bannerList.isEmpty() && fragmentList.isEmpty()) {
            return list.size();
        } else if (bannerList.isEmpty() && fragmentList.isEmpty()) {
            return list.size() + 1;
        } else {
            return list.size() + 2;
        }
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

    public class GridViewHolder extends RecyclerView.ViewHolder{

        public GridViewHolder(View gridItemView) {
            super(gridItemView);
        }
    }

    public class CommonViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goodImage_imageView)
        ImageView goodImageImageView;
        @BindView(R.id.goodName_textView)
        TextView goodNameTextView;

        public CommonViewHolder(View item) {
            super(item);
            ButterKnife.bind(this, item);
        }
    }
}
