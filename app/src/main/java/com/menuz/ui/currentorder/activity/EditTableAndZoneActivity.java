package com.menuz.ui.currentorder.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.Utils.MyToast;
import com.menuz.base.BaseActivity;
import com.menuz.data.model.db.TableModel;
import com.menuz.data.model.db.Zonemodel;
import com.menuz.ui.Order.adapter.TableAdapter;
import com.menuz.ui.currentorder.adapter.TableEditAdapter;
import com.menuz.ui.currentorder.adapter.ZoneEditAdapter;

import java.util.ArrayList;
import java.util.List;

public class EditTableAndZoneActivity extends BaseActivity implements View.OnClickListener,TableAdapter.Listener {
    public String zonename = "";
    public String zoneId = "";
    TableAdapter tableAdapter;
    String tableSelect = "";
    private List<Zonemodel> zoneModelArrayList = new ArrayList<>();
    private List<TableModel> tableModelArrayList = new ArrayList<>();
    private RecyclerView recyclerview, recyclerviewSelect;
    private  String OrderId = "";
    private   String tableId = "";
    private Handler handler = new Handler(Looper.getMainLooper());
    private Zonemodel zonemodel=new Zonemodel();
    private TableModel tableModel=new TableModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);
        Intent intent=getIntent();
        if (intent!=null){
           tableId=intent.getStringExtra("tableId");
            zoneId=intent.getStringExtra("zoneId");
            OrderId=intent.getStringExtra("orderId");
            zonename=intent.getStringExtra("zonename");
        }
        initView();
        addItem();
    }

    @SuppressLint({"StaticFieldLeak", "SetTextI18n"})
    private void initView() {
        Button btnNext = findViewById(R.id.btnNext);
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnNext.setOnClickListener(this);
        tvHeaderTitle.setText(R.string.edit_order);
        recyclerview = findViewById(R.id.recyclerview);
        recyclerviewSelect =findViewById(R.id.rlZone);
        LinearLayoutManager Grid
                = new GridLayoutManager(EditTableAndZoneActivity.this, 4);
        recyclerviewSelect.setLayoutManager(Grid);

        tableAdapter=new TableAdapter(EditTableAndZoneActivity.this,tableModelArrayList,this,"");
        recyclerviewSelect.setAdapter(tableAdapter);
        btnBack.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                if (tableSelect.equals("")){
                 showToast();
                }else {
                    Intent intent=new Intent(EditTableAndZoneActivity.this,EditEmployeeAndGuest.class);
                    intent.putExtra("OrderId",OrderId);
                    intent.putExtra("tableModel",tableModel);
                    intent.putExtra("zoneModel",zonemodel);
                    startActivity(intent);

                }
                break;

            case R.id.btnBack:
                onBackPressed();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void position(int position) {
        String zoneId = zoneModelArrayList.get(position).getZoneId();

        new Thread(() -> {
            tableModelArrayList = getDataManager().getalltable(zoneId);

            handler.post(() -> {
                LinearLayoutManager Grid
                        = new GridLayoutManager(EditTableAndZoneActivity.this, 4);
                recyclerviewSelect.setLayoutManager(Grid);

                recyclerviewSelect.setAdapter(new TableAdapter(EditTableAndZoneActivity.this, tableModelArrayList, new TableAdapter.Listener() {
                    @Override
                    public void position(int position1) {
                        tableModel = tableModelArrayList.get(position1);



                    }

                    @Override
                    public void selected(String selected) {
                        tableSelect = selected;
                    }
                },""));
            });
        }).start();






    }

    @SuppressLint("StaticFieldLeak")
    public void addItem() {
        new Thread(() -> {
            zoneModelArrayList = getDataManager().getallZone();
            zonemodel.setZoneId(zoneId);
            zonemodel.setZoneName(zonename);
            tableModel.setTableId(tableId);


            handler.post(() -> updateZoneUi(zoneModelArrayList));

        }).start();
    }

    @Override
    public void selected(String selected) {
        tableSelect = selected;
    }
    private void updateZoneUi(List<Zonemodel> zoneModelArrayList) {

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerview.setLayoutManager(horizontalLayoutManagaer);
        LinearLayoutManager Grid
                = new GridLayoutManager(this, 4);
        recyclerview.setLayoutManager(horizontalLayoutManagaer);
        recyclerviewSelect.setLayoutManager(Grid);

        recyclerviewSelect.setAdapter(new TableEditAdapter(this, tableModelArrayList, new TableEditAdapter.Listener() {
            @Override
            public void position(int position) {
                tableModel=tableModelArrayList.get(position);
            }

            @Override
            public void selected(String selected) {
                tableSelect = selected;
            }
        },tableId));

        new Thread(() -> {
            tableModelArrayList = EditTableAndZoneActivity.this.getDataManager().getalltable(zoneId);
            for (int i = 0; i <tableModelArrayList.size() ; i++) {
                tableModel=tableModelArrayList.get(i);
                tableSelect = "yes";
            }
        }).start();



        recyclerview.setAdapter(new ZoneEditAdapter(this, position -> {
            String zoneId = zoneModelArrayList.get(position).getZoneId();
            zonemodel = zoneModelArrayList.get(position);

            new Thread(() -> {
                tableModelArrayList = EditTableAndZoneActivity.this.getDataManager().getalltable(zoneId);
                handler.post(() -> {
                    new Thread(() -> tableModelArrayList = EditTableAndZoneActivity.this.getDataManager().getalltable(zoneId)).start();

                    EditTableAndZoneActivity.this.updateTableUi(tableModelArrayList);
                });
            }).start();
        }, zoneModelArrayList,zoneId));


        String ZoneId = zoneId;
        new Thread(() -> {
            tableModelArrayList = getDataManager().getalltable(ZoneId);
            handler.post(() -> {
                LinearLayoutManager Grid1
                        = new GridLayoutManager(this, 4);

                recyclerviewSelect.setLayoutManager(Grid1);

                recyclerviewSelect.setAdapter(new TableEditAdapter(this, tableModelArrayList, new TableEditAdapter.Listener() {
                    @Override
                    public void position(int position) {
                        tableModel=tableModelArrayList.get(position);
                    }

                    @Override
                    public void selected(String selected) {
                        tableSelect = selected;
                    }
                },tableId));


            });
        }).start();


    }

    private void showToast() {
        if (!TextUtils.isEmpty("Please Select table"))
            MyToast.getInstance(EditTableAndZoneActivity.this).showAlert("Please Select table");
    }

    private void updateTableUi(List<TableModel> tableModelArrayList) {
        LinearLayoutManager Grid
                = new GridLayoutManager(this, 4);

        recyclerviewSelect.setLayoutManager(Grid);
        tableSelect = "";
        recyclerviewSelect.setAdapter(new TableEditAdapter(this, tableModelArrayList, new TableEditAdapter.Listener() {
            @Override
            public void position(int position) {
                tableModel=tableModelArrayList.get(position);
            }

            @Override
            public void selected(String selected) {
                tableSelect = selected;
            }
        },tableId));



    }


}
