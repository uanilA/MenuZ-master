package com.menuz.ui.currentorder.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.menuz.R;
import com.menuz.Utils.ConnectionDetector;
import com.menuz.Utils.NoConnectionDialog;
import com.menuz.application.MenuZ;
import com.menuz.data.model.db.AdddonChildModel;
import com.menuz.data.model.db.OrderAddOnChild;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.data.model.db.OrderPreparationAddonModel;
import com.menuz.data.model.db.OrderPreparationModel;
import com.menuz.data.model.db.PayMethodsModel;
import com.menuz.data.model.db.PaymentModel;
import com.menuz.data.model.db.PrefixModel;
import com.menuz.data.model.db.PreparationModel;
import com.menuz.data.model.db.Zonemodel;
import com.menuz.network.HttpResponceListner;
import com.menuz.network.HttpTask;
import com.menuz.ui.Payment.model.PaymentResponseModel;
import com.menuz.ui.currentorder.adapter.CurrentOrderAdapter;
import com.menuz.ui.currentorder.model.CurrentOrderModel;
import com.menuz.ui.currentorder.model.CurrentShowOrderModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.menuz.Demo.DemoAddonFragment.getTimestamp;
import static com.menuz.Utils.AppConstants.STATUSSETTABLE;
import static com.menuz.application.MenuZ.getDataManager;

public class CurrentOrderActivity extends AppCompatActivity implements View.OnClickListener {
    private Handler handler = new Handler(Looper.getMainLooper());
    List<CurrentOrderModel.ResultBean.OrdersBean> currentOrderModels = new ArrayList<>();
    List<CurrentOrderModel.ResultBean.OrdersBean.ItemsBean> itemsBeanArrayList = new ArrayList<>();
    List<CurrentOrderModel.ResultBean.OrdersBean.ItemsBean.AddonsBean> addonsBeanArrayList = new ArrayList<>();
    List<CurrentShowOrderModel> currentShowOrderModels = new ArrayList<>();
    private CurrentOrderAdapter currentOrderAdapter;
    private TextView txt_noOrder;
    private String termID = "";
    private String tableId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        initView();
        currentOrder(CurrentOrderActivity.this);
    }

    public void initView() {
        ImageView btnBack = findViewById(R.id.btnBack);
        RecyclerView recyclerCurrentOrder = findViewById(R.id.recyclerCurrentOrder);
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        txt_noOrder = findViewById(R.id.txt_noOrder);
        tvHeaderTitle.setText(R.string.current_order);
        btnBack.setOnClickListener(this);


        recyclerCurrentOrder.setLayoutManager(new LinearLayoutManager(CurrentOrderActivity.this));
        currentOrderAdapter = new CurrentOrderAdapter(currentShowOrderModels, new CurrentOrderAdapter.getOrderIdClick() {
            @Override
            public void getOrderId(String orderId) {
            }

            @Override
            public void getposition(int position) {
                new Thread(() -> {
                    tableId = currentShowOrderModels.get(position).tableId;
                    handler.post(() -> getTerminalId(CurrentOrderActivity.this, "e9879c86ff3e8535", currentShowOrderModels.get(position).tableId, currentShowOrderModels.get(position).OrderId, currentShowOrderModels.get(position).waiterName, currentShowOrderModels.get(position).employeeId, currentShowOrderModels.get(position).nuofGuest));

                }).start();


            }
        });
        recyclerCurrentOrder.setAdapter(currentOrderAdapter);


    }

    private void createItemOrder(String orderId) {
        List<OrderItemModel> orderItemModels = getDataManager().getSelectedItem(orderId);

        for (OrderItemModel orderItemModel : orderItemModels) {

            List<OrderItemModel> list = getDataManager().getSelectedItem(orderId);
            Log.d("list", "" + list.size());
            List<OrderAddOnChild> orderAddOnChildren = getDataManager().getAddonsByItemPrimary(orderItemModel.getItemPrimaryKey());
            List<OrderPreparationAddonModel> getAddonPrep = getDataManager().getOrderAddonsPrep(orderItemModel.getItemPrimaryKey());
            List<OrderPreparationModel> getOrderItemPrep = getDataManager().getAllOrderItemPrep(orderItemModel.getItemPrimaryKey());
            for (OrderAddOnChild orderAddonChild : orderAddOnChildren) {
                getDataManager().DeleteAddons(orderAddonChild.getAddOnPrimaryKey());
            }

            for (OrderPreparationAddonModel orderPreparationAddonModel : getAddonPrep) {
                getDataManager().deleteAddonPrepration(orderPreparationAddonModel.getOrderPrepAddOnPrimaryKey());

            }
            for (OrderPreparationModel orderPreparationModel : getOrderItemPrep) {
                getDataManager().deletePreps(orderPreparationModel.getItemPrimaryKey());
            }

            getDataManager().DeleteAll();
        }


        for (CurrentOrderModel.ResultBean.OrdersBean.ItemsBean itemBea : itemsBeanArrayList) {

            OrderItemModel orderItemModel1 = new OrderItemModel();
            orderItemModel1.setItemAddonPrice("");
            orderItemModel1.setItemGroupId("");
            orderItemModel1.setItemId(itemBea.getItemID());
            orderItemModel1.setOrderId(itemBea.getItemOrderID());
            orderItemModel1.setItemRemark(itemBea.getItemRemark());
            orderItemModel1.setItemPrimaryKey(getTimestamp());
            orderItemModel1.setItemImage("");
            orderItemModel1.setItemRemark(itemBea.getItemRemark());
            orderItemModel1.setPricevalue("");
            orderItemModel1.setNuofguest("");
            orderItemModel1.setItemAutoID(itemBea.getItemAutoID());
            orderItemModel1.setPrice("");
            orderItemModel1.setCoursename("");
            orderItemModel1.setCartSelected(false);
            orderItemModel1.setItemPreprationRemark("");
            orderItemModel1.setCountPrice("1");
            orderItemModel1.setItemPrice(itemBea.getItemPrice());
            orderItemModel1.setItemName(itemBea.getItemName());
            orderItemModel1.setPricevalues("");
            orderItemModel1.setPreparations("");
            getDataManager().insertOrderItem(orderItemModel1);
            if (itemBea.getItempreps()!=null){
                for (CurrentOrderModel.ResultBean.OrdersBean.ItemsBean.ItemprepsBean itemprepsBean:itemBea.getItempreps()){
                    PreparationModel itemPreprationModel=getDataManager().loadByPrepId(itemprepsBean.getIpreparationID());
                    PrefixModel prefixModel=getDataManager().getDetailPrefix(itemprepsBean.getIpreparationPrefixID());
                    if (itemPreprationModel!=null){
                        OrderPreparationModel orderPreparationModel = new OrderPreparationModel();
                        orderPreparationModel.setItemId(orderItemModel1.getItemId());
                        orderPreparationModel.setItemPrimaryKey(orderItemModel1.getItemPrimaryKey());
                        orderPreparationModel.setOrderId(orderId);
                        orderPreparationModel.setItemName(orderItemModel1.getItemName());
                        if (prefixModel!=null){
                            orderPreparationModel.setPrefixName(prefixModel.getPrefixName());
                        }
                        orderPreparationModel.setPreparationName(itemPreprationModel.getPreparationName());
                        orderPreparationModel.setPreparationIsPrefixed(itemPreprationModel.getPreparationIsPrefixed());
                        getDataManager().insertOrderPrearation(orderPreparationModel);
                    }
                }

            }




            for (CurrentOrderModel.ResultBean.OrdersBean.ItemsBean.AddonsBean addonsBean : itemBea.getAddons()) {
                AdddonChildModel adddonChildModel = getDataManager().getDetail(addonsBean.getAddonID());
                if (adddonChildModel != null) {
                    OrderAddOnChild orderAddOnChild = new OrderAddOnChild();
                    orderAddOnChild.setItemPrimaryKey(orderItemModel1.getItemPrimaryKey());
                    orderAddOnChild.setAddOnPrimaryKey(getTimestamp());
                    orderAddOnChild.setAddonChoiceID(addonsBean.getAddonChoiceID());
                    orderAddOnChild.setItemIdAddon(orderItemModel1.getItemId());
                    orderAddOnChild.setAddonGroupId("");
                    orderAddOnChild.setSyncSelect(true);
                    orderAddOnChild.setAddonRemark("");
                    orderAddOnChild.setAddonAutoID(addonsBean.getAddonAutoID());
                    orderAddOnChild.setAddonsGroupId(adddonChildModel.getAddOnItemIdchild());
                    orderAddOnChild.setAddonId(addonsBean.getAddonID());
                    orderAddOnChild.setOrderId(orderItemModel1.getOrderId());
                    orderAddOnChild.setAddonName(adddonChildModel.getAddonName());
                    orderAddOnChild.setAddonPrice(adddonChildModel.getAddonPrice());
                    orderAddOnChild.setItemPrice(orderItemModel1.getItemPrice());
                    orderAddOnChild.setPreparations(adddonChildModel.getPreparations());
                    getDataManager().insertAddonChild(orderAddOnChild);
                }
                if (addonsBean.getAddonpreps()!=null){
                    for (CurrentOrderModel.ResultBean.OrdersBean.ItemsBean.AddonsBean.AddonprepsBean addonprepsBean:addonsBean.getAddonpreps()){
                        PreparationModel itemPreprationModel=getDataManager().loadByPrepId(addonprepsBean.getApreparationID());
                        PrefixModel prefixModel=getDataManager().getDetailPrefix(addonprepsBean.getApreparationPrefixID());
                        if(itemPreprationModel!=null){
                            OrderPreparationAddonModel orderPreparationAddonModel = new OrderPreparationAddonModel();
                            orderPreparationAddonModel.setPreparationId(itemPreprationModel.getPreparationId());
                            orderPreparationAddonModel.setItemPrimaryKey(orderItemModel1.getItemPrimaryKey());
                            orderPreparationAddonModel.setOrderPrepAddOnPrimaryKey(getTimestamp());
                            orderPreparationAddonModel.setAddOnItemIdchild(orderItemModel1.getItemId());
                            orderPreparationAddonModel.setAddonId(addonsBean.getAddonID());
                            orderPreparationAddonModel.setOrderId(orderId);

                            orderPreparationAddonModel.setItemIdAddon(orderItemModel1.getItemId());
                            orderPreparationAddonModel.setPreparationIsPrefixed(itemPreprationModel.getPreparationIsPrefixed());
                            orderPreparationAddonModel.setPreparationName(itemPreprationModel.getPreparationName());

                            if (prefixModel!=null){
                                orderPreparationAddonModel.setPrefixName(prefixModel.getPrefixName());
                            }



                            getDataManager().insertOrderAddonPreparation(orderPreparationAddonModel);

                        }

                    }
                }



            }

        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnBack) {
            onBackPressed();
        }
    }


    public void currentOrder(Context context) {

        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(context, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    currentOrder(context);
                }

            }).show();
        }

        new HttpTask(new HttpTask.Builder(context, "newSyncServer/0/admin/admin/2/01-10-2017", new HttpResponceListner.Listener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(String response, String apiName) {
                try {
                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray = js.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String status = jsonObject.getString("success");
                        if (status.equals("true")) {
                            itemsBeanArrayList.clear();
                            currentOrderModels.clear();
                            currentShowOrderModels.clear();
                            addonsBeanArrayList.clear();
                            JSONArray jsonArrayOrder = jsonObject.getJSONArray("orders");
                            for (int j = 0; j < jsonArrayOrder.length(); j++) {
                                JSONObject jsonObjectOrder = jsonArrayOrder.getJSONObject(j);
                                Gson gson = new Gson();
                                CurrentOrderModel.ResultBean.OrdersBean currentOrderModel = gson.fromJson(String.valueOf(jsonObjectOrder), CurrentOrderModel.ResultBean.OrdersBean.class);
                                currentOrderModels.add(currentOrderModel);
                                Iterator<String> iter = jsonObjectOrder.keys();
                                while (iter.hasNext()) {
                                    String key = iter.next();
                                    if (key.equals("items")) {
                                        JSONArray jsonArrayitem = jsonObjectOrder.getJSONArray("items");
                                        for (int k = 0; k < jsonArrayitem.length(); k++) {
                                            JSONObject jsonObjectitem = jsonArrayitem.getJSONObject(k);
                                            CurrentOrderModel.ResultBean.OrdersBean.ItemsBean itemsBean = gson.fromJson(String.valueOf(jsonObjectitem), CurrentOrderModel.ResultBean.OrdersBean.ItemsBean.class);
                                            itemsBeanArrayList.add(itemsBean);
                                            Iterator<String> iter1 = jsonObjectitem.keys();

                                            while (iter1.hasNext()) {
                                                String key1 = iter1.next();
                                                if (key1.equals("addons")) {
                                                    JSONArray jsonArrayaddon = jsonObjectitem.getJSONArray("addons");
                                                    for (int l = 0; l < jsonArrayaddon.length(); l++) {
                                                        JSONObject jsonObjectAddon = jsonArrayaddon.getJSONObject(l);
                                                        CurrentOrderModel.ResultBean.OrdersBean.ItemsBean.AddonsBean addonsBean = gson.fromJson(String.valueOf(jsonObjectAddon), CurrentOrderModel.ResultBean.OrdersBean.ItemsBean.AddonsBean.class);
                                                        addonsBeanArrayList.add(addonsBean);
                                                        jsonObjectAddon.has("addonpreps");


                                                    }
                                                }


                                            }


                                        }
                                    }

                                }


                            }

                            createOrder(currentOrderModels);

                        }

                        // currentOrderAdapter.notifyDataSetChanged();


                    }


                    // ((OrderListActivity) mContext).addFragment(SelectWaiterFragment.newInstance(), true);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void ErrorListener(VolleyError error) {

            }


        })
                .setBody(null, HttpTask.ContentType.APPLICATION_JSON)
                .setProgress(true))
                .execute("");
    }

    CurrentShowOrderModel currentShowOrderModel = new CurrentShowOrderModel();

    private void createOrder(List<CurrentOrderModel.ResultBean.OrdersBean> ordersBeans) {
        new Thread(() -> {

            for (CurrentOrderModel.ResultBean.OrdersBean ordersBean : ordersBeans) {

                List<Zonemodel> zonemodels = getDataManager().getZoneDetail(ordersBean.getOrderTableID());
                for (Zonemodel zonemodel : zonemodels) {
                    String nuofguest = ordersBean.getOrderGuest();
                    String tableNo = ordersBean.getOrderTableID();
                    String waiterName = ordersBean.getOrderEmployeeName();
                    String zoneName = zonemodel.getZoneName();
                    currentShowOrderModel = new CurrentShowOrderModel();
                    currentShowOrderModel.nuofGuest = nuofguest;
                    currentShowOrderModel.employeeId = ordersBean.getOrderEmployeeID();
                    currentShowOrderModel.terminalID = ordersBean.getOrderTerminalID();
                    currentShowOrderModel.OrderId = ordersBean.getOrderID();
                    currentShowOrderModel.tableId = tableNo;
                    currentShowOrderModel.zoneName = zoneName;
                    currentShowOrderModel.waiterName = waiterName;


                }

                if (currentShowOrderModel != null) {
                    //currentShowOrderModels.clear();
                    currentShowOrderModels.add(currentShowOrderModel);
                }


                if (currentOrderModels.size() == 0) {
                    txt_noOrder.setVisibility(View.VISIBLE);
                } else {
                    txt_noOrder.setVisibility(View.GONE);
                }
                handler.post(() -> currentOrderAdapter.notifyDataSetChanged());


            }


        }).start();


    }

    public void getTerminalId(Context context, String deviceId, String tableId, String OrderId, String employeename, String employeeId, String nuofguest) {
        findViewById(R.id.progress).setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(context, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    getTerminalId(context, deviceId, tableId, OrderId, employeename, employeeId, nuofguest);
                }

            }).show();
        }

        new HttpTask(new HttpTask.Builder(context, "GetTerminalData/" + deviceId, new HttpResponceListner.Listener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(String response, String apiName) {
                try {
                    findViewById(R.id.progress).setVisibility(View.GONE);
                    PaymentResponseModel.ResultBean resultBean;
                    List<PaymentResponseModel.ResultBean.PaymethodsBean> paymentsBeans = new ArrayList<>();
                    PaymentResponseModel.ResultBean.PaymethodsBean paymentsBean;

                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray = js.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String status = jsonObject.getString("success");
                        if (status.equals("true")) {
                            termID = jsonObject.getString("termId");
                            MenuZ.getInstance().setTerminalId(termID);
                            Gson gson = new Gson();
                            resultBean = gson.fromJson(String.valueOf(jsonObject), PaymentResponseModel.ResultBean.class);
                            JSONArray jsonArrayPay = jsonObject.getJSONArray("paymethods");
                            for (int j = 0; j < jsonArrayPay.length(); j++) {
                                JSONObject jsonObjectPay = jsonArrayPay.getJSONObject(j);
                                paymentsBean = gson.fromJson(String.valueOf(jsonObjectPay), PaymentResponseModel.ResultBean.PaymethodsBean.class);
                                paymentsBeans.add(paymentsBean);
                            }
                            insertPaymethods(resultBean, paymentsBeans, context, termID, tableId, status, employeename, employeeId, OrderId, nuofguest);


                        } else {
                            String errMes = jsonObject.getString("errMes");
                            Toast.makeText(context, "" + errMes, Toast.LENGTH_SHORT).show();

                        }
                    }


                    // ((OrderListActivity) mContext).addFragment(SelectWaiterFragment.newInstance(), true);

                } catch (Exception e) {
                    findViewById(R.id.progress).setVisibility(View.GONE);
                    e.printStackTrace();
                }


            }

            @Override
            public void ErrorListener(VolleyError error) {
                findViewById(R.id.progress).setVisibility(View.GONE);

            }
        })
                .setBody(null, HttpTask.ContentType.APPLICATION_JSON)
                .setProgress(false))
                .execute("");
    }

    void lockTable(Context context, String terminalId, String tableID, String statusTable, String employeename, String employeeId, String OrderId, String nuofguest, String statusnew) {
        findViewById(R.id.progress).setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(context, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    lockTable(context, terminalId, tableID, statusTable, employeename, employeeId, OrderId, nuofguest, statusnew);
                }

            }).show();
        }

        //String statusTable = "0";
        new HttpTask(new HttpTask.Builder(context, "SetTable/" + terminalId + "/" + tableID + "/" + statusTable, new HttpResponceListner.Listener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(String response, String apiName) {
                try {
                    //Progress.hide(mContext);
                    //progressBar.setVisibility(View.GONE);
                    findViewById(R.id.progress).setVisibility(View.GONE);
                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray = js.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String status = jsonObject.getString("success");
                        if (status.equals("true")) {
                            String orderid = jsonObject.getString("orderid");
                            MenuZ.getInstance().setOrderId(orderid);

                            if (statusnew.equals("lock")) {
                                Intent intent = new Intent(CurrentOrderActivity.this, OrderDetailActivity.class);
                                intent.putExtra("orderId", OrderId);
                                intent.putExtra("terminalId", terminalId);
                                intent.putExtra("employeename", employeename);
                                intent.putExtra("employeeId", employeeId);
                                intent.putExtra("isUpdated","");
                                intent.putExtra("tableId", tableID);
                                intent.putExtra("nuofguest", nuofguest);

                                startActivityForResult(intent, STATUSSETTABLE);

                            } else {
                                currentOrder(CurrentOrderActivity.this);
                            }


                        } else {
                            Toast.makeText(context, "This table is already busy Please try again later!", Toast.LENGTH_SHORT).show();
                            // showToast("You already select this table");
                        }

                        //
                    }


                    // ((OrderListActivity) mContext).addFragment(SelectWaiterFragment.newInstance(), true);

                } catch (Exception e) {
                    findViewById(R.id.progress).setVisibility(View.GONE);
                    e.printStackTrace();
                }


            }

            @Override
            public void ErrorListener(VolleyError error) {
                findViewById(R.id.progress).setVisibility(View.GONE);

            }
        })
                .setBody(null, HttpTask.ContentType.APPLICATION_JSON)
                .setProgress(false))
                .execute("");
    }

    void insertPaymethods(PaymentResponseModel.ResultBean resultBean, List<PaymentResponseModel.ResultBean.PaymethodsBean> paymentsBean, Context context, String termId, String tableId, String status, String employeename, String employeeId, String orderId, String nuofguest) {
        new Thread(() -> {
            List<PaymentModel> getAllPayment = getDataManager().loadAllPayments();
            if (getAllPayment.size() == 0) {
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
                paymentModel.setOrderId(orderId);
                paymentModel.setPayMethodFixValue("");
                paymentModel.setPayMethodName("");
                paymentModel.setId("");
                paymentModel.setPayMethodId("");
                paymentModel.setSecuritycode("");
                paymentModel.setPhone("");
                paymentModel.setYear("");
                paymentModel.setTermName(resultBean.getTermName());
                paymentModel.setTermId(resultBean.getTermId());
                getDataManager().deleteFromPayment();
                getDataManager().insert(paymentModel);
            } else {
                for (PaymentModel pay : getAllPayment) {
                    if (!pay.getOrderId().equals(orderId)) {
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
                        paymentModel.setOrderId(orderId);
                        paymentModel.setPayMethodFixValue("");
                        paymentModel.setPayMethodName("");
                        paymentModel.setId("");
                        paymentModel.setPayMethodId("");
                        paymentModel.setSecuritycode("");
                        paymentModel.setPhone("");
                        paymentModel.setYear("");
                        paymentModel.setTermName(resultBean.getTermName());
                        paymentModel.setTermId(resultBean.getTermId());
                        getDataManager().deleteFromPayment();
                        getDataManager().insert(paymentModel);
                    }
                }
            }

            getDataManager().deletePaymethods();
            for (PaymentResponseModel.ResultBean.PaymethodsBean paymentsBean1 : paymentsBean) {
                PayMethodsModel payMethodsModeln = new PayMethodsModel();
                payMethodsModeln.setTermId(termId);
                payMethodsModeln.setPayMethodFixValue(paymentsBean1.getPayMethodFixValue());
                payMethodsModeln.setPayMethodName(paymentsBean1.getPayMethodName());
                payMethodsModeln.setPayMethodId(paymentsBean1.getPayMethodId());
                payMethodsModeln.setOrderId(orderId);
                payMethodsModeln.setPaidAmt("");
                payMethodsModeln.setPayMethodType(paymentsBean1.getPayMethodType());

                getDataManager().insertPaymemt(payMethodsModeln);
            }

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CurrentOrderActivity.this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("cibus_restaurent_id",resultBean.getCibus_RestaurantID());
            editor.putString("cibus_post_id",resultBean.getCibus_PosId());
            editor.apply();

            createItemOrder(orderId);

            handler.post(() -> lockTable(context, termId, tableId, status, employeename, employeeId, orderId, nuofguest, "lock"));

        }).start();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STATUSSETTABLE) {
            lockTable(this, termID, tableId, "0", "", "", "", "", "");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
      /*  Intent intent=new Intent(CurrentOrderActivity.this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);*/
    }


}
