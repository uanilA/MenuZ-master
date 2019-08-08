package com.menuz.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.ui.Home.activity.HomeActivity;
import com.menuz.ui.splash.ViewPagerAdapterGallery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GalleryDetailActivity extends AppCompatActivity implements View.OnClickListener, GalleryMenuAdapter.onItemClick {
    private List<GalleryModel.ResultBean.GalleryBean> galleryBeanList = new ArrayList<>();
    private List<GalleryModel.ResultBean.GalleryBean.ItemsBean> itemsBeans = new ArrayList<>();
    private int position = 0;
    private int groupPosition = 0;
    private TextView txtNodata;
    private TextView tvitemTitle;
    private ViewPager viewPager;
    private ViewPagerAdapterGallery galleryItemAdapter;
    List<GalleryModel.ResultBean.GalleryBean.ItemsBean> itemsBeansTemp = new ArrayList<>();
    GalleryModel.ResultBean.GalleryBean.ItemsBean itemsBeann;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail);
        initView();
    }

    public void initView() {
        RecyclerView recycleMenu = findViewById(R.id.recycleMenu);
        ImageView ivForward = findViewById(R.id.ivForward);
        ImageView ivBack = findViewById(R.id.ivBack);
        LinearLayout llHome = findViewById(R.id.llHome);
        tvitemTitle = findViewById(R.id.tvitemTitle);
        ivForward.setVisibility(View.GONE);
        ivBack.setVisibility(View.GONE);
        txtNodata = findViewById(R.id.txtNodata);
        viewPager = findViewById(R.id.viewPager);
        ivForward.setOnClickListener(this);
        llHome.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        if (getIntent() != null) {
            galleryBeanList = getIntent().getParcelableArrayListExtra("galleryMenu");
            itemsBeans = getIntent().getParcelableArrayListExtra("galleryIten");
            position = Objects.requireNonNull(getIntent().getExtras()).getInt("position");
            itemsBeann = getIntent().getParcelableExtra("itemsBean");
            groupPosition = Objects.requireNonNull(getIntent().getExtras()).getInt("groupPosition");

        }
        setAdapter(recycleMenu);




    }

    private void setAdapter(RecyclerView recycleMenu){
        GalleryMenuAdapter galleryMenuAdapter = new GalleryMenuAdapter(GalleryDetailActivity.this,galleryBeanList, this);
        recycleMenu.setLayoutManager(new LinearLayoutManager(GalleryDetailActivity.this));
        recycleMenu.setAdapter(galleryMenuAdapter);
        galleryBeanList.get(position).setSelect(true);
        String groupId = galleryBeanList.get(groupPosition).getGroupId();
        tvitemTitle.setText(itemsBeann.getItemName());
        for (int i = 0; i < itemsBeans.size(); i++)
            if (groupId.equals(itemsBeans.get(i).getItemGroupId())) {
                GalleryModel.ResultBean.GalleryBean.ItemsBean itemsBean = itemsBeans.get(i);
                itemsBeansTemp.add(itemsBean);
            }
        for (int i = 0; i < itemsBeansTemp.size(); i++) {
            if (itemsBeansTemp.get(i).getItemName().equals(itemsBeann.getItemName())) {
                itemsBeansTemp.remove(i);
            }

        }
        itemsBeansTemp.add(0, itemsBeann);
        galleryItemAdapter = new ViewPagerAdapterGallery(GalleryDetailActivity.this, itemsBeansTemp);
        viewPager.setAdapter(galleryItemAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tvitemTitle.setText(itemsBeansTemp.get(position).getItemName());
            }

            @Override
            public void onPageSelected(int position) {
                tvitemTitle.setText(itemsBeansTemp.get(position).getItemName());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.llHome) {
            Intent intent = new Intent(GalleryDetailActivity.this, HomeActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onItemPosition(int position) {
        itemsBeansTemp.clear();
        for (int i = 0; i < itemsBeans.size(); i++) {
            if (itemsBeans.get(i).getItemGroupId().equals(galleryBeanList.get(position).getGroupId())) {
                GalleryModel.ResultBean.GalleryBean.ItemsBean itemsBean = itemsBeans.get(i);
                itemsBeansTemp.add(itemsBean);
            }
        }


        galleryItemAdapter = new ViewPagerAdapterGallery(GalleryDetailActivity.this, itemsBeansTemp);
        viewPager.setAdapter(galleryItemAdapter);

        if (itemsBeansTemp.size() == 0) {
            tvitemTitle.setText(galleryBeanList.get(position).getGroupName());
            txtNodata.setVisibility(View.VISIBLE);
        } else {
            tvitemTitle.setText(galleryBeanList.get(position).getGroupName());
            txtNodata.setVisibility(View.GONE);
        }
    }
}
