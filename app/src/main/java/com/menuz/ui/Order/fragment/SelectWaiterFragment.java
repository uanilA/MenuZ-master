package com.menuz.ui.Order.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.menuz.R;
import com.menuz.Utils.MyToast;
import com.menuz.application.MenuZ;
import com.menuz.base.BaseFragment;
import com.menuz.data.model.db.EmployeeModel;
import com.menuz.data.model.db.NewOrderModel;
import com.menuz.data.model.db.OrderEmployeeModel;
import com.menuz.data.model.db.OrderTableModel;
import com.menuz.data.model.db.TableModel;
import com.menuz.data.model.db.Zonemodel;
import com.menuz.session.Session;
import com.menuz.ui.Order.activity.NewOrderActivity;
import com.menuz.ui.Order.activity.OrderListActivity;
import com.menuz.ui.Order.adapter.KeyboardAdapter;
import com.menuz.ui.Order.adapter.SelectwaitorAdapter;
import com.menuz.ui.Order.model.KeyboardModel;
import com.menuz.ui.language.Language;

import java.util.ArrayList;
import java.util.List;


public class SelectWaiterFragment extends BaseFragment implements SelectwaitorAdapter.Listener, View.OnClickListener, KeyboardAdapter.onKeyBoardclick {
    ArrayList<KeyboardModel> arrayList;
    private List<EmployeeModel> waitorInfoArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private String isSelected = "";
    private EmployeeModel employeeModel;
    private OrderTableModel orderTableModel = new OrderTableModel();
    private Handler handler = new Handler(Looper.getMainLooper());

    private Zonemodel zonemodel;
    private TableModel tableModel;
    private NewOrderModel newOrderModel;

    private RecyclerView recycler;
    private EditText edNumberofguest;
    private String nuogGuest = "";
    private long mLastClickTime = 0;


    public static SelectWaiterFragment newInstance(TableModel tableModel, Zonemodel zonemodel, NewOrderModel newOrderModel) {
        SelectWaiterFragment fragment = new SelectWaiterFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("tableModel", tableModel);
        bundle.putSerializable("newOrderModel", newOrderModel);
        bundle.putSerializable("zonemodel", zonemodel);
        fragment.setArguments(bundle);
     /*   newOrderModel.setZoneId(zonemodel.getZoneId());
        newOrderModel.setZoneName(zonemodel.getZoneName());
        newOrderModel.setTableId(tableModel.getTableId());*/

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        checkLanguage();
        View view = inflater.inflate(R.layout.fragment_selectwaitor, container, false);
        if (getArguments() != null) {
            zonemodel = (Zonemodel) getArguments().getSerializable("zonemodel");
            newOrderModel = (NewOrderModel) getArguments().getSerializable("newOrderModel");
            tableModel = (TableModel) getArguments().getSerializable("tableModel");
        }

        initView(view);
        addItem();
        addKeyBoardNuofGuest();

        return view;

    }

    @SuppressLint("SetTextI18n")
    private void initView(View view) {
        Button btnNext = view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        recyclerView = view.findViewById(R.id.recyclerview);
        edNumberofguest = view.findViewById(R.id.edNumberofguest);

        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        edNumberofguest.requestFocus();

        recycler = view.findViewById(R.id.recycler);
        new Thread(() -> {
            //  newOrderModel=  getDataManager().geloadOrderId();
            if (orderTableModel != null)
                orderTableModel = getDataManager().getallOrderTable(MenuZ.getInstance().getOrderId());
            handler.post(() -> {
                ((OrderListActivity) mContext).tvHeaderTitle.setText("Table No");
                ((OrderListActivity) mContext).tvTablenoCount.setVisibility(View.VISIBLE);
                ((OrderListActivity) mContext).tvTablenoCount.setText(orderTableModel.getTableId());

            });
        }).start();


    }

    public void addKeyBoardNuofGuest() {

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
                = new GridLayoutManager(mContext, 5);
        recycler.setLayoutManager(Grid);
        recycler.setAdapter(new KeyboardAdapter(arrayList, mContext, this));


    }

    @SuppressLint("StaticFieldLeak")
    public void addItem() {
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
                        = new GridLayoutManager(mContext, 3);
                recyclerView.setLayoutManager(Grid);
                recyclerView.setAdapter(new SelectwaitorAdapter(mContext, waitorInfoArrayList, new SelectwaitorAdapter.Listener() {
                    @Override
                    public void isSelected(String selected) {
                        isSelected = selected;
                    }

                    @Override
                    public void position(int position) {
                        employeeModel = waitorInfoArrayList.get(position);
                    }
                }));

            }
        }.execute();


    }

    @Override
    public void isSelected(String selected) {
        isSelected = selected;
    }

    @Override
    public void position(int position) {

    }

    @Override
    public void onClick(View v) {

        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (v.getId()) {
            case R.id.btnNext:

                if (isSelected.equals("")) {
                    showToast(getString(R.string.select_waiter));

                } else if (TextUtils.isEmpty(edNumberofguest.getText())) {
                    showToast(getString(R.string.enter_guest));


                } else if (nuogGuest.equals("0")) {
                    showToast("Number of guest should be greator than 0");


                } else {
                    Intent intent = new Intent(mContext, NewOrderActivity.class);
                    new Thread(() -> {
                        OrderEmployeeModel orderEmployeeModel = new OrderEmployeeModel();
                        orderEmployeeModel.setOrderId(MenuZ.getInstance().getOrderId());
                        orderEmployeeModel.setEmployeeId(employeeModel.getEmployeeId());
                        orderEmployeeModel.setEmployeeName(employeeModel.getEmployeeName());
                        orderEmployeeModel.setNuofguest(edNumberofguest.getText().toString());
                        new Thread(() -> getDataManager().insertOrderEmployee(orderEmployeeModel)).start();
                        newOrderModel.setOrderId(MenuZ.getInstance().getOrderId());
                        newOrderModel.setNuofguest(edNumberofguest.getText().toString());
                        newOrderModel.setZoneId(zonemodel.getZoneId());
                        newOrderModel.setEmployeeId(employeeModel.getEmployeeId());
                        newOrderModel.setZoneName(zonemodel.getZoneName());
                        newOrderModel.setTableId(tableModel.getTableId());
                        newOrderModel.setEmployeeName(employeeModel.getEmployeeName());
                        getDataManager().insertNewOrder(newOrderModel);
                    }).start();

                    startActivity(intent);
                }
                break;


        }
    }


    private void showToast(String msg) {
        if (!TextUtils.isEmpty(msg))
            MyToast.getInstance(mContext).showAlert(msg);
        //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void checkLanguage() {
        Session session = new Session(mContext);
        String userselectedlanguage = session.getLanguage();
        switch (userselectedlanguage) {
            case "en":
                Language.SetLanguage(mContext, "en");
                break;
            case "iw":
                Language.SetLanguage(mContext, "iw");
                break;
            case "":
                Language.SetLanguage(mContext, "en");
                break;
        }

    }


    @Override
    public void onClick(int position) {
        nuogGuest = arrayList.get(position).itemName;
    }
}
