package com.menuz.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.menuz.R;
import com.menuz.Utils.ConnectionDetector;
import com.menuz.Utils.NoConnectionDialog;
import com.menuz.application.MenuZ;
import com.menuz.data.model.db.NewOrderModel;
import com.menuz.data.model.db.OrderAddOnChild;
import com.menuz.data.model.db.OrderEmployeeModel;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.data.model.db.OrderJsonModel;
import com.menuz.data.model.db.OrderNewjsonModel;
import com.menuz.data.model.db.OrderPreparationAddonModel;
import com.menuz.data.model.db.OrderPreparationModel;
import com.menuz.data.model.db.OrderTableModel;
import com.menuz.data.model.db.OrderZoneModel;
import com.menuz.network.HttpResponceListner;
import com.menuz.network.HttpTask;
import com.menuz.ui.Home.activity.HomeActivity;
import com.menuz.ui.authentication.LoginActivity;
import com.menuz.ui.authentication.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.menuz.application.MenuZ.getDataManager;


/**
 * Created by mindiii on 17/11/18.
 */

public class ParseOrderJson {
    OrderJsonModel.OrdersBean ordersBean = new OrderJsonModel.OrdersBean();
    OrderJsonModel.OrdersBean.ItemsBean itemsBean = new OrderJsonModel.OrdersBean.ItemsBean();
    OrderJsonModel.OrdersBean.ItemsBean.AddonsBean addonprepsBean = new OrderJsonModel.OrdersBean.ItemsBean.AddonsBean();
    private List<OrderNewjsonModel.OrdersBean.ItemsBean.ItemprepsBean> itemsBeans = new ArrayList<>();
    private List<OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean.AddonprepsBean> addonprepsBeanArrayList = new ArrayList<>();
    private String txtGrandtotal;
    private Context context;
    //private NewOrderModel newOrderModel;
    private OrderNewjsonModel orderJsonModel1 = new OrderNewjsonModel();


    public ParseOrderJson(String txtGrandTotal, Context context) {
        this.txtGrandtotal = txtGrandTotal;
        this.context=context;
    }


    @SuppressLint("StaticFieldLeak")
    public void parseData() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
               // newOrderModel = getDataManager().geloadOrderId();
                List<OrderItemModel> selectedItemList = getDataManager().getSelectedItem(MenuZ.getInstance().getOrderId());
                OrderZoneModel orderZoneModel = getDataManager().getallOrderZone(MenuZ.getInstance().getOrderId());
                OrderTableModel orderTableModel = getDataManager().getallOrderTable(MenuZ.getInstance().getOrderId());
                OrderEmployeeModel orderEmployeeModel = getDataManager().getAllOrderEmployee(MenuZ.getInstance().getOrderId());
                Log.d("selec",""+selectedItemList.size());
                @SuppressLint("HardwareIds") String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                OrderNewjsonModel.OrdersBean orderJsonModel = new OrderNewjsonModel.OrdersBean();
                orderJsonModel.setOrderID(MenuZ.getInstance().getOrderId());
                orderJsonModel.setOrderTableID(orderTableModel.getTableId());
                orderJsonModel.setOrderEmployeeID(orderEmployeeModel.getEmployeeId());
                orderJsonModel.setOrderStart("");
                orderJsonModel.setOrderDiscountPCT("");
                orderJsonModel.setOrderDiners("");
                orderJsonModel.setOrderRemark("");
                orderJsonModel.setOrderTerminalID(MenuZ.getInstance().getTerminalId());
                List<OrderNewjsonModel.OrdersBean> ordersBeans = new ArrayList<>();
                ordersBeans.add(orderJsonModel);
                orderJsonModel1.setOrders(ordersBeans);

                List<OrderNewjsonModel.OrdersBean.ItemsBean> itemsBeanList = new ArrayList<>();
                for (int i = 0; i < selectedItemList.size(); i++) {
                    OrderItemModel orderItemModel = selectedItemList.get(i);
                    OrderNewjsonModel.OrdersBean.ItemsBean itemsBean = new OrderNewjsonModel.OrdersBean.ItemsBean();
                    itemsBean.setItemChoiceID("");
                    itemsBean.setItemEmployeeID(orderEmployeeModel.getEmployeeId());
                    itemsBean.setItemID(orderItemModel.getItemId());
                    itemsBean.setItemDiner("");
                    itemsBean.setItemAutoID(orderItemModel.getItemAutoID());
                    itemsBean.setItemPrice(orderItemModel.getItemPrice());
                    itemsBean.setItemPriceID("");
                    itemsBean.setItemOrderID(MenuZ.getInstance().getOrderId());
                    itemsBean.setItemTerminalID(MenuZ.getInstance().getTerminalId());
                    itemsBean.setItemRemark("");
                    itemsBean.setItemQuantity(orderItemModel.getCountPrice());


                    for (OrderItemModel orderItemModel1:selectedItemList) {
                        List<OrderPreparationModel> orderPreparationModels =  getDataManager().loadAllSelectedPreprations(orderItemModel1.getItemPrimaryKey(), orderItemModel1.getOrderId());
                        Log.d("orderPreparationModels",""+orderPreparationModels.size());
                        OrderNewjsonModel.OrdersBean.ItemsBean.ItemprepsBean itemprepsBean = new OrderNewjsonModel.OrdersBean.ItemsBean.ItemprepsBean();

                        for (int j = 0; j < orderPreparationModels.size(); j++) {
                            OrderPreparationModel orderPreparationModel = orderPreparationModels.get(j);
                            itemprepsBean.setIpreparationPrefixID(orderPreparationModel.getPrefixId());
                            itemprepsBean.setIpreparationID(orderPreparationModel.getPreparationId());
                            itemsBeans.add(itemprepsBean);
                            itemsBean.setItempreps(itemsBeans);
                        }
                        itemsBeanList.add(itemsBean);
                    }
                    // itemsBean1.setItempreps(itemsBeans);

                    for (OrderItemModel orederItem:selectedItemList) {
                        List<OrderAddOnChild> orderAddOnChildren = getDataManager().loadAllSelectedAddons(orederItem.getItemPrimaryKey(), orederItem.getOrderId());
                        List<OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean> addonsBeans=new ArrayList<>();
                        for (int k = 0; k < orderAddOnChildren.size(); k++) {
                            OrderAddOnChild orderAddOnChild = orderAddOnChildren.get(k);
                            OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean addonsBean = new OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean();
                            addonsBean.setAddonChoiceID("");
                            addonsBean.setAddonCourse("");
                            addonsBean.setAddonAutoID(orderAddOnChild.getAddonAutoID());
                            addonsBean.setAddonDiner("");
                            addonsBean.setAddonEmployeeID(orderEmployeeModel.getEmployeeId());
                            addonsBean.setAddonID(orderAddOnChild.getAddonId());
                            addonsBean.setAddonOrderID(MenuZ.getInstance().getOrderId());
                            addonsBean.setAddonQuantity("");
                            addonsBean.setAddonRemark("");
                            addonsBean.setAddonTerminalID(MenuZ.getInstance().getTerminalId());
                            addonsBean.setAddonPrice("");

                            List<OrderPreparationAddonModel> orderPreparationAddonModels = getDataManager().loadAllByAddonId(orderAddOnChild.getAddonsGroupId(), orderAddOnChild.getItemIdAddon(), orderAddOnChild.getAddonId(), true, MenuZ.getInstance().getOrderId());

                            for (int l = 0; l < orderPreparationAddonModels.size(); l++) {
                                OrderPreparationAddonModel orderPreparationAddonModel = orderPreparationAddonModels.get(l);
                                OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean.AddonprepsBean addonprepsBean = new  OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean.AddonprepsBean();
                                addonprepsBean.setApreparationID(orderPreparationAddonModel.getPreparationId());
                                addonprepsBean.setApreparationPrefixID(orderPreparationAddonModel.getPrefixId());
                                addonprepsBeanArrayList.add(addonprepsBean);
                                addonsBean.setAddonpreps(addonprepsBeanArrayList);
                            }
                            addonsBeans.add(addonsBean);
                        }
                        itemsBean.setAddons(addonsBeans);
                        orderJsonModel.setItems(itemsBeanList);
                    }
                }

                List<OrderJsonModel.OrdersBean.PaymentsBean> paymentsBeans = new ArrayList<>();
                OrderJsonModel.OrdersBean.PaymentsBean paymentsBean = new OrderJsonModel.OrdersBean.PaymentsBean();
                paymentsBean.setPaymentApproved("");
                paymentsBean.setPaymentID("");
               if (txtGrandtotal.contains("$")){
                   String da = txtGrandtotal.replace("$", "");
                   paymentsBean.setPaymentPrice(da);
                   paymentsBean.setPaymentTerminalID(MenuZ.getInstance().getTerminalId());
                   paymentsBeans.add(paymentsBean);
                   orderJsonModel.setPayments(paymentsBeans);
               }
               return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Gson gson = new Gson();
                String jsonString = gson.toJson(orderJsonModel1);
                try {
                    JSONObject request = new JSONObject(jsonString);
                    Log.d("json",""+request);
                    String json=request.toString().trim();
                    sendJson(json);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    private void sendJson(String jsonObject){
        //    OkHttpClient client = httpClient.build();
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(context, (dialog, isConnected) -> {
                if(isConnected){
                    dialog.dismiss();
                    sendJson(jsonObject);
                }

            }).show();


        }
        new HttpTask(new HttpTask.Builder(context, "syncclient/0/admin/admin/"+jsonObject, new HttpResponceListner.Listener() {
            @Override
            public void onResponse(String response, String apiName) {
                Log.e("LogInREsPonce", "onResponse: "+response);
                try {
                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray=js.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String status=jsonObject.getString("success");
                        if (status.equals("true")){
                            Toast.makeText(context, "Order Submit Successfully.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void ErrorListener(VolleyError error) {
            }})
                .setBody(null, HttpTask.ContentType.APPLICATION_JSON)
                .setProgress(true))
                .execute(this.getClass().getName());
    }

}
