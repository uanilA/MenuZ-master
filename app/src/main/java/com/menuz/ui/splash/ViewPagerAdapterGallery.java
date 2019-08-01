package com.menuz.ui.splash;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.menuz.R;
import com.menuz.ui.GalleryModel;

import java.util.List;

public class ViewPagerAdapterGallery extends PagerAdapter {
    private List<GalleryModel.ResultBean.GalleryBean.ItemsBean> itemsBeanList;
    private LayoutInflater layoutInflater;

    Context context;
    public ViewPagerAdapterGallery(Context context, List<GalleryModel.ResultBean.GalleryBean.ItemsBean> itemsBeanList) {
        this.context=context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemsBeanList = itemsBeanList;
    }

    @Override
    public int getCount() {
        return itemsBeanList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @SuppressLint("CheckResult")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        GalleryModel.ResultBean.GalleryBean.ItemsBean itemsBean=itemsBeanList.get(position);
        View view = this.layoutInflater.inflate(R.layout.pager_list_items, container, false);
        ImageView ivItemImg = view.findViewById(R.id.ivItemImg);
        ProgressBar progressBar=view.findViewById(R.id.progress);
        TextView txtItemName = view.findViewById(R.id.txtItemName);
        TextView txtPrice = view.findViewById(R.id.txtPrice);
        txtItemName.setText(itemsBean.getItemName());
        txtPrice.setText(itemsBean.getItemPrice());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.placeholder_img);
        requestOptions.error(R.drawable.placeholder_img);

        String image="http://82.81.11.210:12986/datasnap/rest/tservermethods1/downloadfile/c:%2fimagesAPI%2f/"+itemsBean.getItemImage();
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(image).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, com.bumptech.glide.request.target.Target<Drawable> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);

                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, com.bumptech.glide.request.target.Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);

                return false;
            }
        }).into(ivItemImg);


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
