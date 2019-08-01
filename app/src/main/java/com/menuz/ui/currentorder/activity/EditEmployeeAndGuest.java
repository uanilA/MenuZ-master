package com.menuz.ui.currentorder.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;
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
import com.menuz.data.model.db.EmployeeModel;
import com.menuz.data.model.db.NewOrderModel;
import com.menuz.data.model.db.TableModel;
import com.menuz.data.model.db.Zonemodel;
import com.menuz.session.Session;
import com.menuz.ui.Order.activity.NewOrderActivity;
import com.menuz.ui.Order.adapter.SelectwaitorAdapter;
import com.menuz.ui.Order.model.KeyboardModel;
import com.menuz.ui.currentorder.adapter.EditKeyboardAdapter;
import com.menuz.ui.currentorder.adapter.EditWaiterAdapter;
import com.menuz.ui.language.Language;

import java.util.ArrayList;
import java.util.List;

public class EditEmployeeAndGuest extends BaseActivity implements View.OnClickListener,EditKeyboardAdapter.onKeyBoardclick {
    private List<EmployeeModel> waitorInfoArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    String isSelected = "";
    private  String OrderId = "";
    private EmployeeModel employeeModel;
    ArrayList<KeyboardModel> arrayList;
    private TableModel tableModel;
    private Zonemodel zonemodel;
    private Handler handler=new Handler(Looper.getMainLooper());
    private  NewOrderModel newOrderModel = new NewOrderModel();
    private RecyclerView recycler;
    private String nuogGuest = "";

    public static final int ACTIVITYEMPLOYEE = 107;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLanguage();
        setContentView(R.layout.activity_edit_employee_and_guest);
        Intent intent=getIntent();
        if (intent!=null){
            OrderId=intent.getStringExtra("OrderId");
            tableModel=(TableModel) intent.getSerializableExtra("tableModel");
            zonemodel=(Zonemodel) intent.getSerializableExtra("zoneModel");
        }

        initView();
        addItem();

    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        Button btnNext = findViewById(R.id.btnNext);
        ImageView btnBack = findViewById(R.id.btnBack);
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText(getResources().getString(R.string.edit_order));
        btnNext.setOnClickListener(this);
        recyclerView = findViewById(R.id.recyclerview);
        recycler = findViewById(R.id.recycler);
        btnBack.setOnClickListener(this);




    }


    public void addKeyBoardNuofGuest(NewOrderModel newOrderModel) {

        arrayList = new ArrayList<>();
        KeyboardModel item;
        for (int i = 0; i < 10; i++) {
            item = new KeyboardModel();
            switch (i) {
                case 0:
                    item.itemName = "1";
                    item.isSelect = false;

                    break;
                case 1:
                    item.itemName = "2";
                    item.isSelect = false;

                    break;

                case 2:
                    item.itemName = "3";
                    item.isSelect = false;
                    break;

                case 3:
                    item.itemName = "4";
                    item.isSelect = false;
                    break;

                case 4:
                    item.itemName = "5";
                    item.isSelect = false;
                    break;


                case 5:
                    item.itemName = "6";
                    item.isSelect = false;
                    break;


                case 6:
                    item.itemName = "7";
                    item.isSelect = false;
                    break;

                case 7:
                    item.itemName = "8";
                    item.isSelect = false;
                    break;
                case 8:
                    item.itemName = "9";
                    item.isSelect = false;
                    break;
                case 9:
                    item.itemName = "0";
                    item.isSelect = false;
                    break;


            }
            arrayList.add(item);

        }


        LinearLayoutManager Grid
                = new GridLayoutManager(EditEmployeeAndGuest.this, 5);
        recycler.setLayoutManager(Grid);
        recycler.setAdapter(new EditKeyboardAdapter(arrayList, EditEmployeeAndGuest.this, this,newOrderModel.getNuofguest()));


    }

    @SuppressLint("StaticFieldLeak")
    public void addItem() {
        new Thread(() -> {
            newOrderModel= getDataManager().loadDataOnOrderId(OrderId);
            waitorInfoArrayList = getDataManager().getAllEmp();
            for (int i = 0; i <waitorInfoArrayList.size() ; i++) {
                employeeModel = waitorInfoArrayList.get(i);
            }
            addKeyBoardNuofGuest(newOrderModel);

        }).start();


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                waitorInfoArrayList = getDataManager().getAllEmp();

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                LinearLayoutManager Grid
                        = new GridLayoutManager(EditEmployeeAndGuest.this, 3);
                recyclerView.setLayoutManager(Grid);
                recyclerView.setAdapter(new EditWaiterAdapter(EditEmployeeAndGuest.this, waitorInfoArrayList, new SelectwaitorAdapter.Listener() {
                    @Override
                    public void isSelected(String selected) {
                        isSelected = selected;
                    }

                    @Override
                    public void position(int position) {
                        employeeModel = waitorInfoArrayList.get(position);
                    }
                },newOrderModel.getEmployeeName()));

            }
        }.execute();


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:

            if (nuogGuest.equals("0")) {
                    showToast();


                } else {
                    new Thread(() -> {
                        if (nuogGuest.equals("")){
                            nuogGuest=newOrderModel.getNuofguest();
                        }
                        getDataManager().updatenuofguestandEmployee(OrderId, employeeModel.getEmployeeName(), nuogGuest, zonemodel.getZoneName(), zonemodel.getZoneId(), tableModel.getTableId());
                        getDataManager().updateOrderinOrderEmployee(nuogGuest,OrderId);
                        getDataManager().updateOrderTable(OrderId,tableModel.getTableId());

                        handler.post(() -> {
                            Intent intent = new Intent(EditEmployeeAndGuest.this, NewOrderActivity.class);
                            intent.putExtra("OrderId",OrderId);
                            startActivityForResult(intent,ACTIVITYEMPLOYEE);

                        });


                    }).start();


                }
                break;


            case R.id.btnBack:
                onBackPressed();
                break;


        }
    }


    private void showToast() {
        if (!TextUtils.isEmpty("Number of guest should be greator than 0"))
            MyToast.getInstance(EditEmployeeAndGuest.this).showAlert("Number of guest should be greator than 0");
    }

    public void checkLanguage() {
        Session session = new Session(EditEmployeeAndGuest.this);
        String userselectedlanguage = session.getLanguage();
        switch (userselectedlanguage) {
            case "en":
                Language.SetLanguage(EditEmployeeAndGuest.this, "en");
                break;
            case "iw":
                Language.SetLanguage(EditEmployeeAndGuest.this, "iw");
                break;
            case "":
                Language.SetLanguage(EditEmployeeAndGuest.this, "en");
                break;
        }

    }


    @Override
    public void onClick(int position) {
        nuogGuest = arrayList.get(position).itemName;
    }
}
