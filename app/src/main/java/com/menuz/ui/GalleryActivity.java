package com.menuz.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.menuz.R;
import com.menuz.Utils.ConnectionDetector;
import com.menuz.Utils.NoConnectionDialog;
import com.menuz.network.HttpResponceListner;
import com.menuz.network.HttpTask;
import com.menuz.ui.Home.activity.HomeActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity implements GalleryMenuAdapter.onItemClick, View.OnClickListener, GalleryItemAdapter.galleyListener {
    private ProgressBar progress;
    private List<GalleryModel.ResultBean.GalleryBean> galleryBeanList = new ArrayList<>();
    private List<GalleryModel.ResultBean.GalleryBean.ItemsBean> itemsBeans = new ArrayList<>();
    private GalleryMenuAdapter galleryMenuAdapter;
    private RecyclerView recyclerItem;
    private int tempPos = 0;
    private TextView txtNodata;
    private TextView tvitemTitle;
    private int groupPosition = 0;
    private SwipeRefreshLayout swipeRefreshLayout;
    ImageView ivForward,ivBack;
    private GalleryItemAdapter galleryItemAdapter;
    List<GalleryModel.ResultBean.GalleryBean.ItemsBean> itemsBeansTemp = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        initView();
    }

    public void initView() {
        RecyclerView recycleMenu = findViewById(R.id.recycleMenu);
        LinearLayout llHome = findViewById(R.id.llHome);
        ivForward = findViewById(R.id.ivForward);
         ivBack = findViewById(R.id.ivBack);
        tvitemTitle = findViewById(R.id.tvitemTitle);
        recyclerItem = findViewById(R.id.recyclerItem);
        txtNodata = findViewById(R.id.txtNodata);
        progress = findViewById(R.id.progress);
        ivForward.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        llHome.setOnClickListener(this);
        getGalleryData();
        swipeRefreshLayout=findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimary)
        );
        swipeRefreshLayout.setOnRefreshListener(this::getGalleryData);

        galleryMenuAdapter = new GalleryMenuAdapter(GalleryActivity.this,galleryBeanList, this);
        recycleMenu.setLayoutManager(new LinearLayoutManager(GalleryActivity.this));
        recycleMenu.setAdapter(galleryMenuAdapter);


    }

    private void getGalleryData() {
        //    OkHttpClient client = httpClient.build();

        progress.setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(GalleryActivity.this, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    getGalleryData();
                }

            }).show();


        }


        new HttpTask(new HttpTask.Builder(this, "getMenuGallery", new HttpResponceListner.Listener() {
            @Override
            public void onResponse(String response, String apiName) {
                Log.e("LogInREsPonce", "onResponse: " + response);
                try {
                    swipeRefreshLayout.setRefreshing(false);
                    galleryBeanList.clear();
                    itemsBeans.clear();
                    itemsBeansTemp.clear();
                    progress.setVisibility(View.GONE);
                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray = js.getJSONArray("result");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONArray jsonArrayGallery = jsonObject.getJSONArray("gallery");
                        Gson gson = new Gson();
                        for (int j = 0; j < jsonArrayGallery.length(); j++) {
                            JSONObject jsonObjectGallery = jsonArrayGallery.getJSONObject(j);
                            GalleryModel.ResultBean.GalleryBean galleryBean = gson.fromJson(String.valueOf(jsonObjectGallery), GalleryModel.ResultBean.GalleryBean.class);
                            galleryBeanList.add(galleryBean);
                            JSONArray jsonArrayitems = jsonObjectGallery.getJSONArray("items");
                            for (int k = 0; k < jsonArrayitems.length(); k++) {
                                JSONObject jsonObjectItems = jsonArrayitems.getJSONObject(k);
                                GalleryModel.ResultBean.GalleryBean.ItemsBean itemsBean = gson.fromJson(String.valueOf(jsonObjectItems), GalleryModel.ResultBean.GalleryBean.ItemsBean.class);
                                itemsBeans.add(itemsBean);

                            }

                        }


                    }

                    if (galleryBeanList.size()==1){
                        ivForward.setVisibility(View.GONE);
                        ivBack.setVisibility(View.GONE);
                    }else {
                        ivForward.setVisibility(View.VISIBLE);
                        ivBack.setVisibility(View.VISIBLE);

                    }
                    galleryBeanList.get(0).setSelect(true);
                    String groupId = galleryBeanList.get(0).getGroupId();
                    tvitemTitle.setText(galleryBeanList.get(0).getGroupName());


                    for (int i = 0; i < itemsBeans.size(); i++) {
                        if (groupId.equals(itemsBeans.get(i).getItemGroupId())) {
                            GalleryModel.ResultBean.GalleryBean.ItemsBean itemsBean = itemsBeans.get(i);
                            itemsBeansTemp.add(itemsBean);
                        }
                    }
                    galleryItemAdapter = new GalleryItemAdapter(GalleryActivity.this,itemsBeansTemp, GalleryActivity.this);

                    recyclerItem.setLayoutManager(new LinearLayoutManager(GalleryActivity.this));
                    recyclerItem.setAdapter(galleryItemAdapter);

                    galleryMenuAdapter.notifyDataSetChanged();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void ErrorListener(VolleyError error) {
                progress.setVisibility(View.GONE);
            }
        })
                .setBody(null, HttpTask.ContentType.APPLICATION_JSON)
                .setProgress(false))
                .execute(this.getClass().getName());
    }

    @Override
    public void onItemPosition(int position) {
        itemsBeansTemp.clear();

        for (int i = 0; i < itemsBeans.size(); i++) {
            if (itemsBeans.get(i).getItemGroupId().equals(galleryBeanList.get(position).getGroupId())) {
                GalleryModel.ResultBean.GalleryBean.ItemsBean itemsBean = itemsBeans.get(i);
                itemsBeansTemp.add(itemsBean);
                groupPosition = position;
            }
            tempPos=position;
        }


        galleryItemAdapter.notifyDataSetChanged();
        if (itemsBeansTemp.size() == 0) {
            tvitemTitle.setText(galleryBeanList.get(position).getGroupName());
            txtNodata.setVisibility(View.VISIBLE);
        } else {
            tvitemTitle.setText(galleryBeanList.get(position).getGroupName());
            txtNodata.setVisibility(View.GONE);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivForward:
                itemsBeansTemp.clear();
                tempPos = tempPos + 1;
                int size = galleryBeanList.size();
                if (size != tempPos) {

                    for (int i = 0; i < itemsBeans.size(); i++) {
                        if (itemsBeans.get(i).getItemGroupId().equals(galleryBeanList.get(tempPos).getGroupId())) {
                            GalleryModel.ResultBean.GalleryBean.ItemsBean itemsBean = itemsBeans.get(i);
                            itemsBeansTemp.add(itemsBean);
                            groupPosition = tempPos;
                            galleryBeanList.get(tempPos).setSelect(true);
                            tvitemTitle.setText(galleryBeanList.get(tempPos).getGroupName());
                        }
                    }

                    galleryItemAdapter.notifyDataSetChanged();
                    if (itemsBeansTemp.size() == 0) {
                        tvitemTitle.setText(galleryBeanList.get(tempPos).getGroupName());
                        txtNodata.setVisibility(View.VISIBLE);
                    } else {

                        txtNodata.setVisibility(View.GONE);
                    }
                } else {
                    tempPos = 0;
                    itemsBeansTemp.clear();
                    galleryBeanList.get(0).setSelect(true);
                    String groupId = galleryBeanList.get(0).getGroupId();
                    tvitemTitle.setText(galleryBeanList.get(0).getGroupName());


                    for (int i = 0; i < itemsBeans.size(); i++) {
                        if (groupId.equals(itemsBeans.get(i).getItemGroupId())) {
                            GalleryModel.ResultBean.GalleryBean.ItemsBean itemsBean = itemsBeans.get(i);
                            itemsBeansTemp.add(itemsBean);
                        }
                    }

                    galleryItemAdapter.notifyDataSetChanged();

                    //ivForward.setVisibility(View.GONE);

                }

                break;

            case R.id.ivBack:
                ivForward.setVisibility(View.VISIBLE);
                itemsBeansTemp.clear();
                tempPos = tempPos - 1;
                if (tempPos != -1) {

                    for (int i = 0; i < itemsBeans.size(); i++) {
                        if (itemsBeans.get(i).getItemGroupId().equals(galleryBeanList.get(tempPos).getGroupId())) {
                            GalleryModel.ResultBean.GalleryBean.ItemsBean itemsBean = itemsBeans.get(i);
                            itemsBeansTemp.add(itemsBean);
                            groupPosition = tempPos;
                            galleryBeanList.get(tempPos).setSelect(true);
                            tvitemTitle.setText(galleryBeanList.get(tempPos).getGroupName());
                        }
                    }

                    galleryItemAdapter.notifyDataSetChanged();
                    if (itemsBeansTemp.size() == 0) {
                        tvitemTitle.setText(galleryBeanList.get(tempPos).getGroupName());
                        txtNodata.setVisibility(View.VISIBLE);
                    } else {
                        txtNodata.setVisibility(View.GONE);
                    }
                } else {
                    tempPos = 0;
                    tempPos = galleryBeanList.size()-1;
                    for (int i = 0; i < itemsBeans.size(); i++) {
                        if (itemsBeans.get(i).getItemGroupId().equals(galleryBeanList.get(tempPos).getGroupId())) {
                            GalleryModel.ResultBean.GalleryBean.ItemsBean itemsBean = itemsBeans.get(i);
                            itemsBeansTemp.add(itemsBean);
                            groupPosition = tempPos;
                            galleryBeanList.get(tempPos).setSelect(true);
                            tvitemTitle.setText(galleryBeanList.get(tempPos).getGroupName());
                        }
                    }

                    galleryItemAdapter.notifyDataSetChanged();
                    if (itemsBeansTemp.size() == 0) {
                        tvitemTitle.setText(galleryBeanList.get(tempPos).getGroupName());
                        txtNodata.setVisibility(View.VISIBLE);
                    } else {
                        txtNodata.setVisibility(View.GONE);
                    }
                }

                break;

            case R.id.llHome:

                Intent intent=new Intent(GalleryActivity.this,HomeActivity.class);
                startActivity(intent);

                break;
        }
    }

    @Override
    public void onGallery(int position) {
        GalleryModel.ResultBean.GalleryBean.ItemsBean itemsBean = itemsBeansTemp.get(position);
        Intent intent = new Intent(GalleryActivity.this, GalleryDetailActivity.class);
        intent.putExtra("itemsBean", itemsBean);
        intent.putExtra("position", position);
        intent.putExtra("groupPosition", groupPosition);
        intent.putParcelableArrayListExtra("galleryMenu", (ArrayList<? extends Parcelable>) galleryBeanList);
        intent.putParcelableArrayListExtra("galleryIten", (ArrayList<? extends Parcelable>) itemsBeans);
        startActivity(intent);

    }
}
