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
    private static final int RECYCLER_ITEM = 1;

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
            case RECYCLER_ITEM:
                View view = LayoutInflater.from(context).inflate(R.layout.shopping_cart_item, parent, false);
                viewHolder = new CommenViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommenViewHolder) {
            Glide.with(context).load("http://img3.redocn.com/20130602/Redocn_2013052815464777.jpg").into(((CommenViewHolder) holder).goodImageImageView);
            ((CommenViewHolder) holder).goodNameTextView.setText(list.get(realPosition(position)).getGoodName());
        } else {
            ((BannerViewHolder) holder).shoppingBanner.setImages(bannerList).setImageLoader(new GlideImageLoader()).start();
            CateViewPageAdapter cateViewPageAdapter = new CateViewPageAdapter(fragmentManager, context, fragmentList);
            ((BannerViewHolder) holder).shoppingViewPager.setAdapter(cateViewPageAdapter);
            //切换fragment的时候变换底部指示器的颜色
            ((BannerViewHolder) holder).shoppingViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 0) {
                        ((BannerViewHolder) holder).firstView.setBackgroundResource(R.drawable.viewpager_selected_indicator);
                        ((BannerViewHolder) holder).secondView.setBackgroundResource(R.drawable.viewpager_unselected_indicator);
                    } else if (position == 1) {
                        ((BannerViewHolder) holder).firstView.setBackgroundResource(R.drawable.viewpager_unselected_indicator);
                        ((BannerViewHolder) holder).secondView.setBackgroundResource(R.drawable.viewpager_selected_indicator);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        }
    }

    public int realPosition(int pos) {
        return pos - 1;
    }

    @Override
    public int getItemCount() {
        if (bannerList.isEmpty()) return list.size();
        else
            return list.size() + 1;
    }

    public class CommenViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goodImage_imageView)
        ImageView goodImageImageView;
        @BindView(R.id.goodName_textView)
        TextView goodNameTextView;

        public CommenViewHolder(View item) {
            super(item);
            ButterKnife.bind(this, item);
        }
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shopping_Banner)
        Banner shoppingBanner;
        @BindView(R.id.shopping_viewPager)
        ViewPager shoppingViewPager;
        @BindView(R.id.first_view)
        View firstView;
        @BindView(R.id.second_view)
        View secondView;

        public BannerViewHolder(View bannerItemView) {
            super(bannerItemView);
            ButterKnife.bind(this, bannerItemView);
        }
    }
}
