package com.menuz.ui.Home.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.menuz.R;
import com.menuz.Utils.CommonUtils;
import com.menuz.Utils.ConnectionDetector;
import com.menuz.Utils.MyToast;
import com.menuz.Utils.NoConnectionDialog;
import com.menuz.application.MenuZ;
import com.menuz.base.BaseActivity;
import com.menuz.data.model.db.EmployeeModel;
import com.menuz.data.model.db.NewOrderModel;
import com.menuz.data.model.db.OrderEmployeeModel;
import com.menuz.data.model.db.TableModel;
import com.menuz.data.model.db.Zonemodel;
import com.menuz.network.HttpResponceListner;
import com.menuz.network.HttpTask;
import com.menuz.session.Session;
import com.menuz.ui.Order.activity.NewOrderActivity;
import com.menuz.ui.Order.adapter.KeyboardAdapter;
import com.menuz.ui.Order.adapter.SelectwaitorAdapter;
import com.menuz.ui.Order.model.KeyboardModel;
import com.menuz.ui.currentorder.activity.CurrentOrderActivity;
import com.menuz.ui.language.Language;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectWaiterActivity extends BaseActivity implements SelectwaitorAdapter.Listener, View.OnClickListener, KeyboardAdapter.onKeyBoardclick {
    ArrayList<KeyboardModel> arrayList;
    private List<EmployeeModel> waitorInfoArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private String isSelected = "";
    private EmployeeModel employeeModel;
    private Handler handler = new Handler(Looper.getMainLooper());

    private Zonemodel zonemodel;
    private TableModel tableModel;
    private TextView txtGuest;

    private NewOrderModel newOrderModel=new NewOrderModel();

    private RecyclerView recycler;
    private EditText edNumberofguest;

    private String nuogGuest = "";
    private String from = "";
    private ProgressBar progressBar;
    private String tableId = "";
    private String terminalId = "";
    private String orderId = "";
    private String employeeId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLanguage();
        setContentView(R.layout.activity_select_waiter);
        Intent intent=getIntent();
        if (intent!= null) {
            from = intent.getStringExtra("From");
            if (!from.equals("cart")){
                zonemodel = (Zonemodel) intent.getSerializableExtra("zonemodel");
                newOrderModel = (NewOrderModel) intent.getSerializableExtra("newOrderModel");
                tableModel = (TableModel) intent.getSerializableExtra("tableModel");

            }else {

                tableId=intent.getStringExtra("tableId");
                terminalId=intent.getStringExtra("terminalId");
                orderId=intent.getStringExtra("orderId");
                employeeId=intent.getStringExtra("employeeId");
            }
        }
        initView();
        addItem();
        addKeyBoardNuofGuest();
    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        Button btnNext = findViewById(R.id.btnNext);
        RelativeLayout rlGuest = findViewById(R.id.rlGuest);
        TextView selectGuest = findViewById(R.id.selectGuest);
        View view = findViewById(R.id.view);
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        TextView tvTablenoCountNew = findViewById(R.id.tvTablenoCountNew);
        TextView tvN = findViewById(R.id.tvN);
         txtGuest = findViewById(R.id.txtGuest);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnNext.setOnClickListener(this);
        recyclerView =findViewById(R.id.recyclerview);
        progressBar =findViewById(R.id.progressBar);
        RelativeLayout rlPlus = findViewById(R.id.rlPlus);
        RelativeLayout rlMinus = findViewById(R.id.rlMinus);
        rlPlus.setOnClickListener(this);
        rlMinus.setOnClickListener(this);
        edNumberofguest = findViewById(R.id.edNumberofguest);
        tvN.setVisibility(View.VISIBLE);
        tvTablenoCountNew.setVisibility(View.VISIBLE);
        recycler =findViewById(R.id.recycler);
        tvTablenoCountNew.setVisibility(View.VISIBLE);
        if (from.equals("cart")){
            tvHeaderTitle.setText("Change Waiter");
            tvTablenoCountNew.setText(tableId);
            selectGuest.setVisibility(View.GONE);
            rlGuest.setVisibility(View.GONE);
            CommonUtils.hideKeyboard(SelectWaiterActivity.this);

            view.setVisibility(View.GONE);
        }else {
            tvTablenoCountNew.setText(tableModel.getTableId());
            selectGuest.setVisibility(View.VISIBLE);
            rlGuest.setVisibility(View.VISIBLE);
            CommonUtils.showKeyboard(SelectWaiterActivity.this,edNumberofguest);
            edNumberofguest.requestFocus();
            view.setVisibility(View.VISIBLE);
            tvHeaderTitle.setText("Table No");

        }



        btnBack.setOnClickListener(this);
        edNumberofguest.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
             txtGuest.setText(edNumberofguest.getText().toString().trim());

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {





            }

            @Override
            public void afterTextChanged(Editable s) {
                txtGuest.setText(edNumberofguest.getText().toString().trim());

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                if (!from.equals("cart")){

                if (isSelected.equals("")) {
                    showToast(getString(R.string.select_waiter));

                } else if (TextUtils.isEmpty(edNumberofguest.getText())) {
                    showToast(getString(R.string.enter_guest));


                } else if (nuogGuest.equals("0")) {
                    showToast("Number of guest should be greator than 0");


                } else {

                    /*Insert data of employee into order*/

                        new Thread(() -> {
                            OrderEmployeeModel orderEmployeeModel = new OrderEmployeeModel();
                            orderEmployeeModel.setOrderId(MenuZ.getInstance().getOrderId());
                            orderEmployeeModel.setEmployeeId(employeeModel.getEmployeeId());
                            orderEmployeeModel.setEmployeeName(employeeModel.getEmployeeName());
                            orderEmployeeModel.setNuofguest(edNumberofguest.getText().toString());
                            new Thread(() -> getDataManager().insertOrderEmployee(orderEmployeeModel)).start();
                            newOrderModel.setOrderId(MenuZ.getInstance().getOrderId());
                            MenuZ.getInstance().setEmployeeId(employeeModel.getEmployeeId());
                            newOrderModel.setNuofguest(edNumberofguest.getText().toString());
                            newOrderModel.setZoneId(zonemodel.getZoneId());
                            newOrderModel.setZoneName(zonemodel.getZoneName());
                            newOrderModel.setEmployeeId(employeeModel.getEmployeeId());
                            newOrderModel.setTableId(tableModel.getTableId());
                            newOrderModel.setEmployeeName(employeeModel.getEmployeeName());
                            getDataManager().insertNewOrder(newOrderModel);
                            handler.post(() -> {
                                Intent intent = new Intent(SelectWaiterActivity.this, NewOrderActivity.class);
                                intent.putExtra("orderId",MenuZ.getInstance().getOrderId());
                                intent.putExtra("from","");
                                intent.putExtra("isUpdated","");
                                intent.putExtra("navigation","neworder");
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            });
                        }).start();
                    }


                }else {
                    if (isSelected.equals("")) {
                        showToast(getString(R.string.select_waiter));

                    } else {
                        JSONObject jObjectType = new JSONObject();

                        // put elements into the object as a key-value pair
                        try {
                            jObjectType.put("source", employeeId);
                            jObjectType.put("orderid",orderId );
                            jObjectType.put("destination", employeeModel.getEmployeeId());
                            changeWaiter(jObjectType.toString().trim());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;

            case R.id.btnBack:
                onBackPressed();
                break;

            case R.id.rlPlus:
                String nuofguest=edNumberofguest.getText().toString();
                int no=Integer.parseInt(nuofguest)+1;
                edNumberofguest.setText(String.valueOf(no));
                txtGuest.setText(String.valueOf(no));
                break;

            case R.id.rlMinus:
                String nuofguestM=edNumberofguest.getText().toString();
                if (Integer.parseInt(nuofguestM)>0){
                    int noM=Integer.parseInt(nuofguestM)-1;
                    edNumberofguest.setText(String.valueOf(noM));
                    txtGuest.setText(String.valueOf(noM));
                }

                break;


        }
    }

    /*Change Waiter api*/

    private void changeWaiter(String data){
        //    OkHttpClient client = httpClient.build();

        progressBar.setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(SelectWaiterActivity.this, (dialog, isConnected) -> {
                if(isConnected){
                    dialog.dismiss();
                    changeWaiter(data);
                }

            }).show();


        }

        new HttpTask(new HttpTask.Builder(this, "miscFunction/changeEmployee/"+data, new HttpResponceListner.Listener() {
            @Override
            public void onResponse(String response, String apiName) {
                Log.e("LogInREsPonce", "onResponse: "+response);
                try {
                    progressBar.setVisibility(View.GONE);
                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray=js.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String status=jsonObject.getString("success");

                        if (status.equals("true")){
                            lockTable(SelectWaiterActivity.this,terminalId,tableId);

                        }else{
                            String errMes=jsonObject.getString("errMes");
                                showToast(errMes);
                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void ErrorListener(VolleyError error) {
                progressBar.setVisibility(View.GONE);
            }})
                .setBody(null, HttpTask.ContentType.APPLICATION_JSON)
                .setProgress(false))
                .execute(this.getClass().getName());
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
                        = new GridLayoutManager(SelectWaiterActivity.this, 3);
                recyclerView.setLayoutManager(Grid);
                recyclerView.setAdapter(new SelectwaitorAdapter(SelectWaiterActivity.this, waitorInfoArrayList, new SelectwaitorAdapter.Listener() {
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

    /*Prepare guest keyboard*/

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
                = new GridLayoutManager(SelectWaiterActivity.this, 5);
        recycler.setLayoutManager(Grid);
        recycler.setAdapter(new KeyboardAdapter(arrayList, SelectWaiterActivity.this, this));


    }

    @Override
    public void onClick(int position) {
        nuogGuest = arrayList.get(position).itemName;
    }

    @Override
    public void isSelected(String selected) {
        isSelected = selected;
    }

    @Override
    public void position(int position) {

    }

    private void showToast(String msg) {
        if (!TextUtils.isEmpty(msg))
            MyToast.getInstance(SelectWaiterActivity.this).showAlert(msg);
        //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /*Language check */

    public void checkLanguage() {
        Session session = new Session(SelectWaiterActivity.this);
        String userselectedlanguage = session.getLanguage();
        switch (userselectedlanguage) {
            case "en":
                Language.SetLanguage(SelectWaiterActivity.this, "en");
                break;
            case "iw":
                Language.SetLanguage(SelectWaiterActivity.this, "iw");
                break;
            case "":
                Language.SetLanguage(SelectWaiterActivity.this, "en");
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }



    /*Call api for lock table*/
    void lockTable(Context context, String terminalId, String tableID) {
        progressBar.setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(context, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    lockTable(context, terminalId, tableID);
                }

            }).show();
        }

        //String statusTable = "0";
        new HttpTask(new HttpTask.Builder(SelectWaiterActivity.this, "SetTable/" + terminalId + "/" + tableID + "/" + "0", new HttpResponceListner.Listener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(String response, String apiName) {
                try {
                    //Progress.hide(mContext);
                    progressBar.setVisibility(View.GONE);
                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray = js.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String status = jsonObject.getString("success");
                        if (status.equals("true")) {
                            Intent intent=new Intent(SelectWaiterActivity.this,CurrentOrderActivity.class);
                            startActivity(intent);
                        } else {
                            showToast("You already select this table");
                        }


                    }
                    } catch (Exception e) {
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }


            }

            @Override
            public void ErrorListener(VolleyError error) {
                progressBar.setVisibility(View.GONE);

            }
        })
                .setBody(null, HttpTask.ContentType.APPLICATION_JSON)
                .setProgress(false))
                .execute("");
    }


}
