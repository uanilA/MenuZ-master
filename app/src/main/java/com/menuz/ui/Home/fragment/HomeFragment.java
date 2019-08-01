package com.menuz.ui.Home.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.Utils.CommonUtils;
import com.menuz.application.MenuZ;
import com.menuz.base.BaseFragment;
import com.menuz.data.ApiParse.ParseData;
import com.menuz.data.model.db.TableModel;
import com.menuz.session.Session;
import com.menuz.ui.Home.adapter.HomeAdapter;
import com.menuz.ui.Home.model.HomeModel;
import com.menuz.ui.Order.activity.NewOrderActivity;
import com.menuz.ui.Order.activity.OrderListActivity;
import com.menuz.ui.currentorder.activity.CurrentOrderActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment implements HomeAdapter.Listener, View.OnClickListener {

    private ArrayList<HomeModel> homeModelArrayList = new ArrayList<>();
    // public static String ORDERID="";
    private ImageView imgItemAdd;
    private ImageView imgItemcurrent;
    private ImageView imgItem;
    private TextView tvItemAdd;
    private TextView tvItemcurrent;
    private TextView tvItem;
    private CardView cardAdd;
    private CardView cardCurrent;
    private Handler handler=new Handler(Looper.myLooper());
    private CardView cardMenu;
    private SwipeRefreshLayout swipeContainer;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        addItems();
        CommonUtils.showDebugDBAddressLogToast();
        initView(view);
        Session session = new Session(mContext);

        if (!session.getUpdatedinDb().equals("updated")) {
            ParseData.getInstance().loginProcess(mContext, swipeContainer);
        }


        return view;
    }

    public void initView(View view) {
        RecyclerView recycler = view.findViewById(R.id.recycler);
        imgItemAdd = view.findViewById(R.id.imgItemAdd);
        imgItemcurrent = view.findViewById(R.id.imgItemcurrent);
        imgItem = view.findViewById(R.id.imgItem);
        swipeContainer=view.findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeColors(
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimary)
        );

        swipeContainer.setOnRefreshListener(() -> {
            new Thread(() -> getDataManager().clearAllTable()).start();
            ParseData.getInstance().loginProcess(mContext,swipeContainer);
        });
        tvItemAdd = view.findViewById(R.id.tvItemAdd);
        tvItemcurrent = view.findViewById(R.id.tvItemcurrent);
        tvItem = view.findViewById(R.id.tvItem);
        cardAdd = view.findViewById(R.id.cardAdd);
        cardCurrent = view.findViewById(R.id.cardCurrent);
        cardMenu = view.findViewById(R.id.cardMenu);
        HomeAdapter homeAdapter = new HomeAdapter(mContext, homeModelArrayList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(homeAdapter);
        cardMenu.setOnClickListener(this);
        cardCurrent.setOnClickListener(this);
        cardAdd.setOnClickListener(this);
        tvItemAdd.setTextColor(getResources().getColor(R.color.colorPrimary));
        tvItem.setTextColor(getResources().getColor(R.color.colorPrimary));
        tvItemcurrent.setTextColor(getResources().getColor(R.color.colorPrimary));
        imgItemAdd.setImageResource(R.drawable.new_order_icon_inactive);
        imgItem.setImageResource(R.drawable.menu_icon_inactive);
        imgItemcurrent.setImageResource(R.drawable.current_order_icon_inactive);
        cardAdd.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardCurrent.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardMenu.setCardBackgroundColor(getResources().getColor(R.color.white));
    }

    private void addItems() {
        HomeModel item;
        for (int i = 0; i < 3; i++) {
            item = new HomeModel();
            switch (i) {
                case 0:
                    item.itemName = getString(R.string.add_new_order);
                    item.itemImg = R.drawable.new_order_icon_active;
                    item.isSelect = false;
                    item.itemImgActive = R.drawable.new_order_icon_inactive;

                    break;
                case 1:
                    item.itemName = getString(R.string.current);
                    item.isSelect = false;
                    item.itemImg = R.drawable.current_order_icon_active;
                    item.itemImgActive = R.drawable.current_order_icon_inactive;

                    break;

                case 2:
                    item.itemName = getString(R.string.menu);
                    item.isSelect = false;
                    item.itemImg = R.drawable.menu_icon_active;
                    item.itemImgActive = R.drawable.menu_icon_inactive;
                    break;


            }
            homeModelArrayList.add(item);
        }
    }

    @Override
    public void pos(int pos) {
        switch (pos) {
            case 0:

                new Thread(() -> {
                    List<TableModel>tableModels=getDataManager().getalltable();
                    for (TableModel tableModel:tableModels){
                        getDataManager().updateTable("", tableModel.getTableId());
                    }

                    handler.post(() -> {
                        Intent intent = new Intent(mContext, OrderListActivity.class);
                        intent.putExtra("From","");
                        startActivity(intent);

                    });
                }).start();
                break;

            case 1:
                Intent intentCurrentOrder = new Intent(mContext, CurrentOrderActivity.class);
                startActivity(intentCurrentOrder);
                break;

            case 2:
                Intent intentMenu = new Intent(mContext, NewOrderActivity.class);
                intentMenu.putExtra("navigation","menu");
                intentMenu.putExtra("orderId",MenuZ.getInstance().getOrderId());
                intentMenu.putExtra("from","");
                startActivity(intentMenu);
                break;


        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardAdd:
                tvItemAdd.setTextColor(getResources().getColor(R.color.white));
                tvItem.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvItemcurrent.setTextColor(getResources().getColor(R.color.colorPrimary));
                imgItemAdd.setImageResource(R.drawable.new_order_icon_active);
                imgItem.setImageResource(R.drawable.menu_icon_inactive);
                imgItemcurrent.setImageResource(R.drawable.current_order_icon_inactive);
                cardAdd.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                cardCurrent.setCardBackgroundColor(getResources().getColor(R.color.white));
                cardMenu.setCardBackgroundColor(getResources().getColor(R.color.white));
                new Thread(() -> {
                    List<TableModel>tableModels=getDataManager().getalltable();
                    for (TableModel tableModel:tableModels){
                        getDataManager().updateTable("", tableModel.getTableId());
                    }
                    handler.post(() -> {
                        Intent intent = new Intent(mContext, OrderListActivity.class);
                        startActivity(intent);
                    });
                }).start();
                break;

            case R.id.cardCurrent:
                tvItemAdd.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvItem.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvItemcurrent.setTextColor(getResources().getColor(R.color.white));
                imgItemAdd.setImageResource(R.drawable.new_order_icon_inactive);
                imgItem.setImageResource(R.drawable.menu_icon_inactive);
                imgItemcurrent.setImageResource(R.drawable.current_order_icon_active);
                cardAdd.setCardBackgroundColor(getResources().getColor(R.color.white));
                cardCurrent.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                cardMenu.setCardBackgroundColor(getResources().getColor(R.color.white));
                Intent intentCurrentOrder = new Intent(mContext, CurrentOrderActivity.class);
                startActivity(intentCurrentOrder);
                break;

            case R.id.cardMenu:
                tvItemAdd.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvItem.setTextColor(getResources().getColor(R.color.white));
                tvItemcurrent.setTextColor(getResources().getColor(R.color.colorPrimary));
                imgItemAdd.setImageResource(R.drawable.new_order_icon_inactive);
                imgItem.setImageResource(R.drawable.menu_icon_active);
                imgItemcurrent.setImageResource(R.drawable.current_order_icon_inactive);
                cardAdd.setCardBackgroundColor(getResources().getColor(R.color.white));
                cardCurrent.setCardBackgroundColor(getResources().getColor(R.color.white));
                cardMenu.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                Intent intentMenu = new Intent(mContext, NewOrderActivity.class);
                intentMenu.putExtra("navigation","menu");
                intentMenu.putExtra("orderId",MenuZ.getInstance().getOrderId());
                intentMenu.putExtra("from","");
                startActivity(intentMenu);
                break;
        }
    }
}