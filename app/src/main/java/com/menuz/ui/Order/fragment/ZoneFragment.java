package com.menuz.ui.Order.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.menuz.R;
import com.menuz.Utils.ConnectionDetector;
import com.menuz.Utils.MyToast;
import com.menuz.Utils.NoConnectionDialog;
import com.menuz.Utils.Progress;
import com.menuz.application.MenuZ;
import com.menuz.base.BaseFragment;
import com.menuz.data.model.db.NewOrderModel;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.data.model.db.OrderTableModel;
import com.menuz.data.model.db.OrderZoneModel;
import com.menuz.data.model.db.PayMethodsModel;
import com.menuz.data.model.db.PaymentModel;
import com.menuz.data.model.db.TableModel;
import com.menuz.data.model.db.Zonemodel;
import com.menuz.network.HttpResponceListner;
import com.menuz.network.HttpTask;
import com.menuz.session.Session;
import com.menuz.ui.Home.activity.SelectWaiterActivity;
import com.menuz.ui.Home.model.GetZoneAndTableModel;
import com.menuz.ui.Order.adapter.TableAdapter;
import com.menuz.ui.Order.adapter.ZoneAdapter;
import com.menuz.ui.Payment.model.PaymentResponseModel;
import com.menuz.ui.currentorder.SplitTableActivity;
import com.menuz.ui.currentorder.activity.CurrentOrderActivity;
import com.menuz.ui.language.Language;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static com.menuz.Utils.AppConstants.STATUSSETTABLE;


public class ZoneFragment extends BaseFragment implements View.OnClickListener, TableAdapter.Listener, ZoneAdapter.Listener {
    TableAdapter tableAdapter;
    NewOrderModel newOrderModel = new NewOrderModel();
    private List<Zonemodel> zoneModelArrayList = new ArrayList<>();
    private List<TableModel> tableModelArrayList = new ArrayList<>();
    private RecyclerView recyclerviewZone, recyclerviewSelectTable;
    private String tableSelect = "";
    private Handler handler = new Handler(Looper.getMainLooper());
    private Zonemodel zonemodel;
    private TableModel tableModel;
    private String statusTable = "0";
    private String tableId = "";
    private String terminalId = "";
    private String orderId = "";
    private String from = "";
    private List<OrderItemModel> orderItemModelList = new ArrayList<>();
    private List<TableModel> getAlltableList = new ArrayList<>();
    private List<GetZoneAndTableModel.ResultBean.ZonesBean.TablesBean> tablesBeanArrayList = new ArrayList<>();
    private ProgressBar progressBar;
    private long mLastClickTime = 0;

    public static ZoneFragment newInstance() {
        ZoneFragment fragment = new ZoneFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static ZoneFragment newInstance(String orderId, String tableId, String terminalId, String from, List<OrderItemModel> orderItemModelList) {
        ZoneFragment fragment = new ZoneFragment();
        Bundle args = new Bundle();
        fragment.setInstanceData(orderId, tableId, terminalId, from, orderItemModelList);

        fragment.setArguments(args);
        return fragment;
    }

    private void setInstanceData(String orderId, String tableId, String terminalId, String from, List<OrderItemModel> orderItemModelList) {
        this.orderId = orderId;
        this.terminalId = terminalId;
        this.tableId = tableId;
        this.from = from;
        this.orderItemModelList = orderItemModelList;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zone, container, false);
        checkLanguage();
        Progress.hide(mContext);
        new Thread(() -> getAlltableList = getDataManager().getalltable()).start();
        Log.d("getalllist", "" + getAlltableList.size());
        initView(view);
        getTableStatus(mContext);
        return view;
    }

    @SuppressLint("StaticFieldLeak")
    private void initView(View view) {
        Button btnNext = view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        recyclerviewZone = view.findViewById(R.id.recyclerview);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerviewSelectTable = view.findViewById(R.id.rlZone);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {

        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        switch (v.getId()) {
            case R.id.btnNext:
                switch (from) {
                    case "cartTransfer":
                        if (tableSelect.equals("yes")) {
                            JSONObject jObjectType = new JSONObject();
                            // put elements into the object as a key-value pair
                            try {
                                jObjectType.put("source", tableId);
                                jObjectType.put("orderid", orderId);
                                jObjectType.put("destination", tableModel.getTableId());
                                transferTable(jObjectType.toString().trim());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            showToast("Select Table");
                        }
                        break;
                    case "cartSplit":
                        Intent intent = new Intent(mContext, SplitTableActivity.class);
                        intent.putExtra("From", "cartSplit");
                        intent.putExtra("tableId", tableId);
                        intent.putExtra("destinationTableId",tableModel.getTableId());
                        intent.putParcelableArrayListExtra("itemList", (ArrayList<? extends Parcelable>) orderItemModelList);
                        intent.putExtra("terminalId", MenuZ.getInstance().getTerminalId());
                        intent.putExtra("orderId", orderId);
                        intent.putExtra("employeeId", "");
                        startActivity(intent);

                        break;

                    case "cartSwitch":
                        if (tableSelect.equals("yes")) {

                            JSONObject jObjectType = new JSONObject();
                            // put elements into the object as a key-value pair
                            try {
                                jObjectType.put("source", tableId);
                                jObjectType.put("orderid", orderId);
                                jObjectType.put("destination", tableModel.getTableId());
                                switchTable(jObjectType.toString().trim());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            showToast("Select Table");
                        }
                        break;

                    default:
                        if (tableSelect.equals("yes")) {

                            if (statusTable.equals("1")) {
                                statusTable = "0";
                            } else {
                                statusTable = "1";
                            }
                            getTerminalId(mContext, "e9879c86ff3e8535", tableModel.getTableId(), statusTable, "lock");

                        } else {
                            showToast("Select Table");
                        }
                        break;
                }

                break;

        }
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public void position(int position) {
        String zoneId = zoneModelArrayList.get(position).getZoneId();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                tableModelArrayList = getDataManager().getalltable(zoneId);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                tableAdapter.setItems(tableModelArrayList);
                tableAdapter.notifyDataSetChanged();
            }
        }.execute();


    }

    @Override
    public void selected(String selected) {
        tableSelect = selected;
    }


    @SuppressLint("StaticFieldLeak")
    public void addItem() {
        new Thread(() -> {
            zoneModelArrayList = getDataManager().getallZone();
            handler.post(() -> updateZoneUi(zoneModelArrayList));
        }).start();
    }

    private void updateZoneUi(List<Zonemodel> zoneModelArrayList) {
        if (zoneModelArrayList.size() > 0) {
            zoneModelArrayList.get(0).isSelect = true;
            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            recyclerviewZone.setLayoutManager(horizontalLayoutManagaer);
            LinearLayoutManager Grid
                    = new GridLayoutManager(mContext, 4);
            recyclerviewSelectTable.setLayoutManager(Grid);
            zonemodel = zoneModelArrayList.get(0);



            recyclerviewZone.setAdapter(new ZoneAdapter(mContext, position -> {
                String zoneId = zoneModelArrayList.get(position).getZoneId();
                zonemodel = zoneModelArrayList.get(position);

                tableSelect = "";
                new Thread(() -> {
                    tableModelArrayList = getDataManager().getalltable(zoneId);
                    handler.post(() -> {
                        new Thread(() -> tableModelArrayList = getDataManager().getalltable(zoneId)).start();
                        Collections.sort(tableModelArrayList, new TableCompare());
                        updateTableUi(tableModelArrayList);
                    });
                }).start();
            }, zoneModelArrayList));


            String ZoneId = zoneModelArrayList.get(0).getZoneId();
            new Thread(() -> {
                tableModelArrayList = getDataManager().getalltable(ZoneId);
                Collections.sort(tableModelArrayList, new TableCompare());
                handler.post(() -> {
                    LinearLayoutManager Grid1
                            = new GridLayoutManager(mContext, 4);

                    recyclerviewSelectTable.setLayoutManager(Grid1);

                    tableAdapter = new TableAdapter(mContext, tableModelArrayList, new TableAdapter.Listener() {
                        @Override
                        public void position(int position) {
                            tableModel = tableModelArrayList.get(position);
                        }

                        @Override
                        public void selected(String selected) {
                            tableSelect = selected;
                        }
                    }, from);

                    recyclerviewSelectTable.setAdapter(tableAdapter);

                });
            }).start();

        }


    }

    private void updateTableUi(List<TableModel> tableModelArrayList) {
        LinearLayoutManager Grid
                = new GridLayoutManager(mContext, 4);

        recyclerviewSelectTable.setLayoutManager(Grid);
        tableSelect = "";
        recyclerviewSelectTable.setAdapter(new TableAdapter(mContext, tableModelArrayList, new TableAdapter.Listener() {
            @Override
            public void position(int position) {
                tableModel = tableModelArrayList.get(position);
                tableAdapter.setItems(tableModelArrayList);
                tableAdapter.notifyDataSetChanged();

            }

            @Override
            public void selected(String selected) {
                tableSelect = selected;

            }
        }, from));


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


    public void getTableStatus(Context context) {
        progressBar.setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(context, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    getTableStatus(context);
                }

            }).show();
        }

        new HttpTask(new HttpTask.Builder(context, "newSyncServer/0/admin/admin/3/01-10-2017", new HttpResponceListner.Listener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(String response, String apiName) {
                try {
                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray = js.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONArray jsonArrayOrders = jsonObject.getJSONArray("zones");
                        for (int j = 0; j < jsonArrayOrders.length(); j++) {
                            JSONObject jsonObjectOrder = jsonArrayOrders.getJSONObject(j);
                            Gson gson = new Gson();
                            //ordersBeanList.add(resultBean);
                            JSONArray jsonArrayTable = jsonObjectOrder.getJSONArray("tables");
                            for (int k = 0; k < jsonArrayTable.length(); k++) {
                                JSONObject jsonObjectTable = jsonArrayTable.getJSONObject(k);
                                GetZoneAndTableModel.ResultBean.ZonesBean.TablesBean tablesBean = gson.fromJson(String.valueOf(jsonObjectTable), GetZoneAndTableModel.ResultBean.ZonesBean.TablesBean.class);
                                tablesBeanArrayList.add(tablesBean);
                            }
                        }
                        showTableStatus(tablesBeanArrayList);
                    }
                    // ((OrderListActivity) mContext).addFragment(SelectWaiterFragment.newInstance(), true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void ErrorListener(VolleyError error) {
                System.out.println("&&&&&&&&&&&&&&&&&&&&"+error);
                progressBar.setVisibility(View.GONE);
            }
        })
                .setBody(null, HttpTask.ContentType.APPLICATION_JSON)
                .setProgress(false
                ))
                .execute("");
    }

    private void showTableStatus(List<GetZoneAndTableModel.ResultBean.ZonesBean.TablesBean> ordersBeanList) {
        new Thread(() -> {
            //List<TableModel>tableModels=getDataManager().getalltable();
            for (GetZoneAndTableModel.ResultBean.ZonesBean.TablesBean tablesBean : ordersBeanList) {

                for (TableModel tableModel : getAlltableList) {
                    if (tablesBean.getTableId().equals(tableModel.getTableId())
                            && !tablesBean.getTableOrderId().equals("")
                            && tablesBean.getTableTerminalId().equals("") && !tablesBean.getTableOrderId().equals("0")) {
                        String tableId = tablesBean.getTableId();
                        getDataManager().updateTable("true", tableId);
                        break;

                    } else if (tablesBean.getTableId().equals(tableModel.getTableId())
                            && !tablesBean.getTableTerminalId().equals("")
                            && !tablesBean.getTableOrderId().equals("")
                            && !tablesBean.getTableOrderId().equals("0")) {
                        String tableId = tablesBean.getTableId();
                        getDataManager().updateTable("true", tableId);
                        break;

                    } else if (tablesBean.getTableId().equals(tableModel.getTableId())
                            && !tablesBean.getTableTerminalId().equals("") && tablesBean.getTableOrderId().equals("0")) {
                        String tableId = tablesBean.getTableId();
                        getDataManager().updateTable("locked", tableId);
                        break;


                    } else if (tablesBean.getTableId().equals(tableModel.getTableId())
                            && tablesBean.getTableTerminalId().equals("") && tablesBean.getTableOrderId().equals("0")) {
                        String tableId = tablesBean.getTableId();
                        getDataManager().updateTable("", tableId);
                    } else if ((tablesBean.getTableId().equals(tableModel.getTableId())
                            && !tablesBean.getTableTerminalId().equals("") && !tablesBean.getTableOrderId().equals("0") && !tableModel.getTableOrderId().equals(""))) {
                        String tableId = tablesBean.getTableId();
                        getDataManager().updateTable("true", tableId);
                        break;
                    }

                }


            }
            handler.post(() -> {
                progressBar.setVisibility(View.GONE);
                addItem();

            });


        }).start();

    }

    /*http://82.81.11.210:12985/datasnap/rest/tservermethods1/GetTerminalData/deviceid*/

    public void getTerminalId(Context context, String deviceId, String tableId, String statusTable, String lockUnlockstatus) {
        progressBar.setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(context, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    getTerminalId(context, deviceId, tableId, statusTable, lockUnlockstatus);
                }

            }).show();
        }

        new HttpTask(new HttpTask.Builder(context, "GetTerminalData/" + deviceId, new HttpResponceListner.Listener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(String response, String apiName) {
                try {
                    progressBar.setVisibility(View.GONE);
                    PaymentResponseModel.ResultBean resultBean;
                    List<PaymentResponseModel.ResultBean.PaymethodsBean> paymentsBeans = new ArrayList<>();
                    PaymentResponseModel.ResultBean.PaymethodsBean paymentsBean;

                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray = js.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String status = jsonObject.getString("success");
                        if (status.equals("true")) {
                            String termId = jsonObject.getString("termId");
                            MenuZ.getInstance().setTerminalId(termId);
                            Gson gson = new Gson();
                            resultBean = gson.fromJson(String.valueOf(jsonObject), PaymentResponseModel.ResultBean.class);
                            JSONArray jsonArrayPay = jsonObject.getJSONArray("paymethods");
                            for (int j = 0; j < jsonArrayPay.length(); j++) {
                                JSONObject jsonObjectPay = jsonArrayPay.getJSONObject(j);
                                paymentsBean = gson.fromJson(String.valueOf(jsonObjectPay), PaymentResponseModel.ResultBean.PaymethodsBean.class);
                                paymentsBeans.add(paymentsBean);
                            }


                            insertPaymethods(resultBean, paymentsBeans, mContext, termId, tableId, statusTable, lockUnlockstatus);


                        } else {
                            String errMes = jsonObject.getString("errMes");
                            showToast(errMes);
                        }
                    }


                    // ((OrderListActivity) mContext).addFragment(SelectWaiterFragment.newInstance(), true);

                } catch (Exception e) {
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


    private void showToast(String msg) {
        if (!TextUtils.isEmpty(msg))
            MyToast.getInstance(mContext).showDasuAlert(msg);
        //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    void insertPaymethods(PaymentResponseModel.ResultBean resultBean, List<PaymentResponseModel.ResultBean.PaymethodsBean> paymentsBean, Context mContext, String termId, String tableId, String statusTable, String lockUnlockstatus) {
        new Thread(() -> {
            getDataManager().deleteFromPayment();

            PaymentModel paymentModel = new PaymentModel();
            paymentModel.setCibus_PosId(resultBean.getCibus_PosId());
            paymentModel.setCibus_RestaurantID(resultBean.getCibus_RestaurantID());
            paymentModel.setEmvData(resultBean.getEmvData());
            paymentModel.setOrderId("");
            paymentModel.setTenBis_Password(resultBean.getTenBis_Password());
            paymentModel.setTenBis_User(resultBean.getTenBis_User());
            paymentModel.setTenBis_ResID(resultBean.getTenBis_ResID());
            paymentModel.setCardno("");
            paymentModel.setMonth("");
            paymentModel.setPayMethodFixValue("");
            paymentModel.setPayMethodName("");
            paymentModel.setId("");
            paymentModel.setPayMethodId("");
            paymentModel.setSecuritycode("");
            paymentModel.setPhone("");
            paymentModel.setYear("");
            paymentModel.setTermName(resultBean.getTermName());
            paymentModel.setTermId(resultBean.getTermId());

            getDataManager().insert(paymentModel);

            getDataManager().deletePaymethods();
            for (PaymentResponseModel.ResultBean.PaymethodsBean paymentsBean1 : paymentsBean) {
                PayMethodsModel payMethodsModel = new PayMethodsModel();
                payMethodsModel.setTermId(paymentModel.getTermId());
                payMethodsModel.setPayMethodFixValue(paymentsBean1.getPayMethodFixValue());
                payMethodsModel.setPayMethodName(paymentsBean1.getPayMethodName());
                payMethodsModel.setPayMethodId(paymentsBean1.getPayMethodId());
                payMethodsModel.setOrderId("");
                payMethodsModel.setPaidAmt("");
                payMethodsModel.setPayMethodType(paymentsBean1.getPayMethodType());


                getDataManager().insertPaymemt(payMethodsModel);
            }

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("cibus_restaurent_id",resultBean.getCibus_RestaurantID());
            editor.putString("cibus_post_id",resultBean.getCibus_PosId());
            editor.apply();

            handler.post(() -> lockTable(mContext, termId, tableId, statusTable, lockUnlockstatus));
        }).start();

    }


    /*Call api for lock table*/
    void lockTable(Context context, String terminalId, String tableID, String statusTable, String statusLock) {
        progressBar.setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(context, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    lockTable(context, terminalId, tableID, statusTable, statusLock);
                }

            }).show();
        }

        //String statusTable = "0";
        new HttpTask(new HttpTask.Builder(mContext, "SetTable/" + terminalId + "/" + tableID + "/" + statusTable, new HttpResponceListner.Listener() {
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
                            //MenuZ.getInstance().setOrderId(orderid);
                            new Thread(() -> getDataManager().updateTable("true", tableID)).start();
                            //showToast("Table lock Successfully.");
                            for (int j = 0; j < tableModelArrayList.size(); j++) {
                                tableModelArrayList.get(i).setNewOrderId("yes");
                                UUID uuid = UUID.randomUUID();
                                MenuZ.getInstance().setOrderId(uuid.toString());
                                insertOrderData(MenuZ.getInstance().getOrderId(), terminalId);
                            }
                            if (statusLock.equals("lock")) {

                               /* for (int k = 0; k < tableModelArrayList.size(); k++) {
                                  tableModelArrayList.get(i).setSelect(false);
                                  tableAdapter.notifyDataSetChanged();
                                }*/
                                Intent intent = new Intent(mContext, SelectWaiterActivity.class);
                                intent.putExtra("tableModel", tableModel);
                                intent.putExtra("From", "");
                                intent.putExtra("zonemodel", zonemodel);
                                intent.putExtra("newOrderModel", newOrderModel);
                                startActivityForResult(intent, STATUSSETTABLE);
                            } else {
                                getTableStatus(mContext);
                            }


                        } else {
                            showToast("You already select this table");
                        }

                        if (tableAdapter != null)
                            tableAdapter.notifyDataSetChanged();
                    }


                    // ((OrderListActivity) mContext).addFragment(SelectWaiterFragment.newInstance(), true);

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


    /*Call api for lock table*/


    /*Insert data of  and table into order*/
    private void insertOrderData(String OrderId, String terminalid) {
        new Thread(() -> {
            MenuZ.getInstance().setTableId(tableModel.getTableId());
            newOrderModel.setOrderId(OrderId);
            newOrderModel.setTerminalId(terminalid);
            getDataManager().insertNewOrder(newOrderModel);
            if (zonemodel != null) {

                OrderZoneModel orderZoneModel = new OrderZoneModel();
                orderZoneModel.setZoneId(zonemodel.getZoneId());
                orderZoneModel.setZoneName(zonemodel.getZoneName());
                orderZoneModel.setOrderId(OrderId);
                orderZoneModel.setZonePlace(zonemodel.getZonePlace());
                getDataManager().insertOrderZone(orderZoneModel);
            }

            OrderTableModel orderTableModel = new OrderTableModel();
            orderTableModel.setTableGuestName(tableModel.getTableGuestName());
            orderTableModel.setTableHeight(tableModel.getTableHeight());
            orderTableModel.setTableId(tableModel.getTableId());
            orderTableModel.setTableLeft(tableModel.getTableLeft());
            orderTableModel.setOrderId(OrderId);
            orderTableModel.setTableTerminalId(terminalid);
            orderTableModel.setTableGuestName(tableModel.getTableGuestName());
            orderTableModel.setTableOrderId(tableModel.getTableOrderId());
            orderTableModel.setTableTop(tableModel.getTableTop());
            orderTableModel.setTableWidth(tableModel.getTableWidth());
            orderTableModel.setTableOrderId(tableModel.getTableOrderId());
            orderTableModel.setTableTerminalId(tableModel.getTableTerminalId());

            getDataManager().insertOrderTable(orderTableModel);
            List<PaymentModel> getAllPayment = getDataManager().loadAllPayments();
            for (PaymentModel pay : getAllPayment) {
                getDataManager().updateOrderIdPayment(OrderId, pay.getTermId());
            }
            List<PayMethodsModel> getallPay = getDataManager().loadAllPayment();
            for (PayMethodsModel payMethodsModel : getallPay) {
                getDataManager().updateOrderId(OrderId, payMethodsModel.getTermId());
            }


            //handler.post(() -> ((OrderListActivity) mContext).addFragment(SelectWaiterFragment.newInstance(tableModel, zonemodel, newOrderModel), false));


        }).start();
    }

    /*Sorting of tables*/


    public class TableCompare implements Comparator<TableModel> {

        @Override
        public int compare(TableModel o1, TableModel o2) {
            int tableId = Integer.parseInt(o1.getTableId());
            int tableId1 = Integer.parseInt(o2.getTableId());
            return Integer.compare(tableId, tableId1);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case STATUSSETTABLE:
                new Thread(() -> {
                    OrderTableModel orderTableModel = getDataManager().getallOrderTable(MenuZ.getInstance().getOrderId());
                    handler.post(() -> {
                        if ( orderTableModel != null){
                            statusTable = "0";
                            lockTable(mContext, MenuZ.getInstance().getTerminalId(), orderTableModel.getTableId(), statusTable, "unlock");

                        }

                    });


                }).start();

        }
    }

    /*Switch table api*/

    private void switchTable(String data) {

        progressBar.setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(mContext, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    switchTable(data);
                }

            }).show();


        }

        new HttpTask(new HttpTask.Builder(mContext, "miscFunction/switchOrder/" + data, new HttpResponceListner.Listener() {
            @Override
            public void onResponse(String response, String apiName) {
                Log.e("LogInREsPonce", "onResponse: " + response);
                try {
                    progressBar.setVisibility(View.GONE);
                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray = js.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String status = jsonObject.getString("success");

                        if (status.equals("true")) {
                            lockTablenew(mContext, terminalId, tableId);

                        } else {
                            String errMes = jsonObject.getString("errMes");
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
            }
        })
                .setBody(null, HttpTask.ContentType.APPLICATION_JSON)
                .setProgress(false))
                .execute(this.getClass().getName());
    }



    /*Transfer table Api*/
    private void transferTable(String data) {
        //    OkHttpClient client = httpClient.build();

        progressBar.setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(mContext, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    transferTable(data);
                }

            }).show();


        }

        new HttpTask(new HttpTask.Builder(mContext, "miscFunction/transferOrder/" + data, new HttpResponceListner.Listener() {
            @Override
            public void onResponse(String response, String apiName) {
                Log.e("LogInREsPonce", "onResponse: " + response);
                try {
                    progressBar.setVisibility(View.GONE);
                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray = js.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String status = jsonObject.getString("success");

                        if (status.equals("true")) {
                            lockTablenew(mContext, terminalId, tableId);

                        } else {
                            String errMes = jsonObject.getString("errMes");
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
            }
        })
                .setBody(null, HttpTask.ContentType.APPLICATION_JSON)
                .setProgress(false))
                .execute(this.getClass().getName());
    }


    /*Call api for lock table*/
    void lockTablenew(Context context, String terminalId, String tableID) {
        progressBar.setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(context, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    lockTablenew(context, terminalId, tableID);
                }

            }).show();
        }

        //String statusTable = "0";
        new HttpTask(new HttpTask.Builder(mContext, "SetTable/" + terminalId + "/" + tableID + "/" + "0", new HttpResponceListner.Listener() {
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
                            Intent intent = new Intent(mContext, CurrentOrderActivity.class);
                            startActivity(intent);
                        } else {
                            String errMes = jsonObject.getString("errMes");
                            showToast(errMes);
                        }


                    }


                    // ((OrderListActivity) mContext).addFragment(SelectWaiterFragment.newInstance(), true);

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
