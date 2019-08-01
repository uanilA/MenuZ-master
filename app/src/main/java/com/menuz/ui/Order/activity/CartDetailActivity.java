package com.menuz.ui.Order.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.menuz.R;
import com.menuz.Utils.ConnectionDetector;
import com.menuz.Utils.NoConnectionDialog;
import com.menuz.application.MenuZ;
import com.menuz.base.BaseActivity;
import com.menuz.data.model.db.ItemModel;
import com.menuz.data.model.db.NewOrderModel;
import com.menuz.data.model.db.OrderAddOnChild;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.data.model.db.OrderNewjsonModel;
import com.menuz.data.model.db.OrderPreparationAddonModel;
import com.menuz.data.model.db.OrderPreparationModel;
import com.menuz.data.model.db.OrderTableModel;
import com.menuz.data.model.db.PayMethodsModel;
import com.menuz.data.model.db.PaymentModel;
import com.menuz.network.HttpResponceListner;
import com.menuz.network.HttpTask;
import com.menuz.session.Session;
import com.menuz.ui.Home.CartDetailFragment;
import com.menuz.ui.Home.activity.HomeActivity;
import com.menuz.ui.Home.activity.SelectWaiterActivity;
import com.menuz.ui.Home.model.NavigationModel;
import com.menuz.ui.Payment.PaymentActivity;
import com.menuz.ui.currentorder.MenuOptionsAdapter;
import com.menuz.ui.currentorder.SplitTableActivity;
import com.menuz.ui.currentorder.activity.EditTableAndZoneActivity;
import com.menuz.ui.currentorder.activity.PrintNotesActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.menuz.application.MenuZ.getDataManager;

public class CartDetailActivity extends BaseActivity implements View.OnClickListener, MenuOptionsAdapter.Listener {

    RelativeLayout iv_newView;
    private List<OrderItemModel> selectedItemList = new ArrayList<>();
    String popuptitle = "";
    private List<OrderNewjsonModel.OrdersBean.ItemsBean.ItemprepsBean> itemsBeans = new ArrayList<>();
    private List<OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean.AddonprepsBean> addonprepsBeanArrayList = new ArrayList<>();
    //private NewOrderModel newOrderModel;
    private OrderNewjsonModel orderJsonModel1 = new OrderNewjsonModel();

    private ProgressBar progressBar;
    private TextView txtTotalCount;
    private TextView cartCountTxt;
    private ArrayList<NavigationModel> arrayList = new ArrayList<>();
    private TextView tvZone;
    private Handler handler = new Handler(Looper.getMainLooper());
    private String orderId = "";
    RelativeLayout rlGrandTotal;
    private NewOrderModel newOrderModel = new NewOrderModel();
    private ItemModel itemModel;
    private NavigationView navigation_view;
    private TextView tableOrderCount;
    private DrawerLayout drawer;
    private boolean isOrderId = true;
  /*private static String oldOrderId = "";
    static String oldTableId = "";*/

    public CartDetailActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_detail);
        Intent intent = getIntent();
        if (intent != null) {
            orderId = intent.getStringExtra("orderId");
            orderId = orderId == null ? "" : orderId;
            itemModel = (ItemModel) intent.getSerializableExtra("itemdata");
        }
        initView();
        addFragment(CartDetailFragment.newInstance(orderId, itemModel), false);
    }

    /*Create drawer list*/

    private void addItemsiDrawer() {
        NavigationModel item;
        for (int i = 0; i < 6; i++) {
            item = new NavigationModel();
            switch (i) {
                case 0:
                    item.itemName = "Change Waiter";
                    item.itemImg = R.drawable.ic_waiter_icon;
                    item.isSelect = true;
                    //  item.itemImgActive=R.drawable.home_active;

                    break;
                case 1:
                    item.itemName = "Transfer Table";
                    item.isSelect = false;
                    item.itemImg = R.drawable.ic_transfer_table;
                    //item.itemImgActive=R.drawable.gallery_active;

                    break;

                case 2:
                    item.itemName = "Split Table";
                    item.isSelect = false;
                    item.itemImg = R.drawable.ic_split_table;
                    // item.itemImgActive=R.drawable.language_active;
                    break;

                case 3:
                    item.itemName = "Switch Table";
                    item.isSelect = false;
                    item.itemImg = R.drawable.switch_table;
                    //item.itemImgActive=R.drawable.logout_active;
                    break;

                case 4:
                    item.itemName = "Print Notes";
                    item.isSelect = false;
                    item.itemImg = R.drawable.ic_print_notes;
                    // item.itemImgActive=R.drawable.active_setting_icon;
                    break;

                case 5:
                    item.itemName = "Discount";
                    item.isSelect = false;
                    item.itemImg = R.drawable.ic_discount;
                    // item.itemImgActive=R.drawable.active_setting_icon;
                    break;


            }
            arrayList.add(item);
        }
    }

    @SuppressLint({"SetTextI18n", "StaticFieldLeak"})
    public void initView() {

        ImageView btnPlus = findViewById(R.id.btnPlus);
        RecyclerView recyclerMenu = findViewById(R.id.recyclerMenu);
        navigation_view = findViewById(R.id.navigation_view);
        tableOrderCount = findViewById(R.id.tableOrderCount);
        navigation_view.setOnClickListener(this);

        LinearLayout llAddMore = findViewById(R.id.llAddMore);
        LinearLayout llPrint = findViewById(R.id.llPrint);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setVisibility(View.VISIBLE);
        ImageView icBack = findViewById(R.id.icBack);
        ImageView menuImg = findViewById(R.id.menuImg);
        menuImg.setVisibility(View.VISIBLE);
        txtTotalCount = findViewById(R.id.txtTotalCount);
        cartCountTxt = findViewById(R.id.cartCountTxtItem);
        progressBar = findViewById(R.id.progress);
        LinearLayout llDone = findViewById(R.id.llDone);
        btnBack.setVisibility(View.GONE);
        drawer = findViewById(R.id.drawer);
        drawer.setScrimColor(Color.TRANSPARENT);
        drawer.closeDrawers();
        iv_newView = findViewById(R.id.iv_newView);
        rlGrandTotal = findViewById(R.id.rlGrandTotal);
        tvZone = findViewById(R.id.tvZoneName);
        btnBack.setOnClickListener(this);
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText(R.string.your_cart);
        btnPlus.setVisibility(View.VISIBLE);
        btnPlus.setOnClickListener(this);
        llPrint.setOnClickListener(this);
        llAddMore.setOnClickListener(this);
        icBack.setOnClickListener(this);
        menuImg.setOnClickListener(this);

        llDone.setOnClickListener(this);

        new Thread(() -> {
            selectedItemList = getDataManager().getSelectedItem(MenuZ.getInstance().getOrderId());
            handler.post(() -> cartCountTxt.setText("" + selectedItemList.size()));
        }).start();

        addItemsiDrawer();
        LinearLayoutManager layoutManager = new LinearLayoutManager(CartDetailActivity.this);
        recyclerMenu.setLayoutManager(layoutManager);
        MenuOptionsAdapter navigationAdapter = new MenuOptionsAdapter(arrayList, this);
        recyclerMenu.setAdapter(navigationAdapter);
        new Thread(() -> {
            newOrderModel = getDataManager().loadDataOnOrderId(MenuZ.getInstance().getOrderId());
            PayMethodsModel payMethodsModel = getDataManager().loadSinglePayment(orderId);
            if (newOrderModel != null)
                handler.post(() -> {
                    if (payMethodsModel != null && !payMethodsModel.getPaidAmt().isEmpty()) {
                        tableOrderCount.setText("Order ID:" + " " + payMethodsModel.getOrderId() + "/" + "Table No:" + " " + newOrderModel.getTableId() + "/" + "Name:" + " " + newOrderModel.getEmployeeName());
                        String valueAmt = txtTotalCount.getText().toString().trim();
                        if (valueAmt.contains("$")) {
                            String da = valueAmt.replace("$", "");
                            float val = Float.parseFloat(da);
                            float newval = Float.parseFloat(valueAmt);
                            float Totalval = val - newval;
                            txtTotalCount.setText(String.valueOf(Totalval));
                        }
                    } else
                        tableOrderCount.setText("Order ID:" + " " + "NA" + "/" + "Table No:" + " " + newOrderModel.getTableId() + "/" + "Name:" + " " + newOrderModel.getEmployeeName());
                });
        }).start();

    }


    @Override
    protected void onResume() {
        super.onResume();
        new Thread(() -> {
            PayMethodsModel payMethodsModel = getDataManager().loadSinglePayment(orderId);
            if (payMethodsModel != null)
                handler.post(() -> {
                    if (payMethodsModel != null && !payMethodsModel.getPaidAmt().isEmpty()) {
                        tableOrderCount.setText("Order ID:" + " " + payMethodsModel.getOrderId() + "/" + "Table No:" + " " + newOrderModel.getTableId() + "/" + "Name:" + " " + newOrderModel.getEmployeeName());
                        String valueAmt = txtTotalCount.getText().toString().trim();
                        float val = Float.parseFloat(valueAmt);
                        float newval = Float.parseFloat(payMethodsModel.getPaidAmt());
                        float Totalval = val - newval;
                        txtTotalCount.setText(String.valueOf(Totalval));
                    }
                });
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;

            case R.id.bottom:
                break;

            case R.id.btnPlus:
                if (newOrderModel != null) {
                    lockTable(CartDetailActivity.this, newOrderModel.getTerminalId(), newOrderModel.getTableId());

                }
                break;


            case R.id.card:
                Intent intentEditTable = new Intent(CartDetailActivity.this, EditTableAndZoneActivity.class);
                intentEditTable.putExtra("zonename", tvZone.getText().toString());
                intentEditTable.putExtra("orderId", newOrderModel.getOrderId());
                intentEditTable.putExtra("zoneId", newOrderModel.getZoneId());
                intentEditTable.putExtra("tableId", newOrderModel.getTableId());
                intentEditTable.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentEditTable);
                break;

            case R.id.llDone:
                parseData();
                break;

            case R.id.llAddMore:
                orderId = MenuZ.getInstance().getOrderId();
                Intent intentAdd = new Intent(CartDetailActivity.this, NewOrderActivity.class);
                intentAdd.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intentAdd.putExtra("edit", "");
                intentAdd.putExtra("orderId", orderId);
                intentAdd.putExtra("navigation", "neworder");
                intentAdd.putExtra("from", "");
                startActivity(intentAdd);
                break;

            case R.id.llPrint:
                parseData();

                break;

            case R.id.menuImg:
                if (drawer.isDrawerOpen(navigation_view)) {
                    drawer.closeDrawers();
                } else {
                    drawer.openDrawer(navigation_view);
                }

                break;

            case R.id.icBack:
                if (drawer.isDrawerOpen(navigation_view)) {
                    drawer.closeDrawers();
                } else {
                    drawer.openDrawer(navigation_view);
                }

                break;
        }
    }

    @SuppressLint("StaticFieldLeak")
    /*Create model to json*/
    public void parseData() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
               /* if(oldOrderId.equals("")){
                    oldOrderId = MenuZ.getInstance().getOrderId();
                }

                if(oldTableId.equals("")){
                    oldTableId = MenuZ.getInstance().getTableId();
                }*/

                List<OrderItemModel> selectedItemList = getDataManager().getAllorderItem(MenuZ.getInstance().getOrderId());
                Log.d("selec", "" + selectedItemList.size());

                OrderNewjsonModel.OrdersBean orderJsonModel = new OrderNewjsonModel.OrdersBean();

             /**
              *
             * */
                if (isOrderId){
                    orderJsonModel.setOrderID("0");
                    isOrderId = false;
                }else {
                    orderJsonModel.setOrderID(orderId);
                }
                orderJsonModel.setOrderTableID(MenuZ.getInstance().getTableId());
                orderJsonModel.setOrderEmployeeID(MenuZ.getInstance().getEmployeeId());
                orderJsonModel.setOrderStart("");
                orderJsonModel.setOrderDiscountPCT("");
                orderJsonModel.setOrderDiners("");
                orderJsonModel.setOrderRemark("");
                orderJsonModel.setOrderTerminalID(MenuZ.getInstance().getTerminalId());
                List<OrderNewjsonModel.OrdersBean> ordersBeans = new ArrayList<>();
                ordersBeans.add(orderJsonModel);

                List<OrderNewjsonModel.OrdersBean.ItemsBean> itemsBeanList = new ArrayList<>();
                for (int i = 0; i < selectedItemList.size(); i++) {
                    OrderItemModel orderItemModel = selectedItemList.get(i);
                    OrderNewjsonModel.OrdersBean.ItemsBean itemsBean = new OrderNewjsonModel.OrdersBean.ItemsBean();
                    itemsBean.setItemChoiceID("");
                    itemsBean.setItemEmployeeID(MenuZ.getInstance().getEmployeeId());
                    itemsBean.setItemID(orderItemModel.getItemId());
                    itemsBean.setItemDiner("");
                    itemsBean.setItemCourse("");
                    itemsBean.setItemAutoID("0");
                    itemsBean.setItemPrice(orderItemModel.getItemPrice());
                    itemsBean.setItemPriceID("");
                    itemsBean.setItemOrderID(orderItemModel.getItemRemark());
                    itemsBean.setItemTerminalID(MenuZ.getInstance().getTerminalId());
                    itemsBean.setItemRemark(orderItemModel.getItemRemark());
                    itemsBean.setItemQuantity(orderItemModel.getCountPrice());

                    List<OrderPreparationModel> orderPreparationModels = getDataManager().loadAllSelectedPreprations(MenuZ.getInstance().getOrderId(), orderItemModel.getItemPrimaryKey());
                    Log.d("orderPreparationModels", "" + orderPreparationModels.size());
                    OrderNewjsonModel.OrdersBean.ItemsBean.ItemprepsBean itemprepsBean = new OrderNewjsonModel.OrdersBean.ItemsBean.ItemprepsBean();

                    for (int j = 0; j < orderPreparationModels.size(); j++) {
                        OrderPreparationModel orderPreparationModel = orderPreparationModels.get(j);
                        itemprepsBean.setIpreparationPrefixID(orderPreparationModel.getPrefixId());
                        itemprepsBean.setIpreparationID(orderPreparationModel.getPreparationId());
                        itemsBeans.add(itemprepsBean);

                    }
                    itemsBean.setItempreps(itemsBeans);
                    itemsBeanList.add(itemsBean);
                    // itemsBean1.setItempreps(itemsBeans);

                    List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(orderItemModel.getItemPrimaryKey(), MenuZ.getInstance().getOrderId());
                    List<OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean> addonsBeans = new ArrayList<>();
                    for (int k = 0; k < cartSeltedAddonList.size(); k++) {
                        OrderAddOnChild orderAddOnChild = cartSeltedAddonList.get(k);
                        OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean addonsBean = new OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean();
                        addonsBean.setAddonChoiceID(orderAddOnChild.getAddonsGroupId());
                        addonsBean.setAddonCourse("");
                        addonsBean.setAddonAutoID("0");
                        addonsBean.setAddonDiner("");
                        addonsBean.setAddonEmployeeID(MenuZ.getInstance().getEmployeeId());
                        addonsBean.setAddonID(orderAddOnChild.getAddonId());
                        addonsBean.setAddonOrderID("");
                        addonsBean.setAddonQuantity("");
                        addonsBean.setAddonRemark("");
                        addonsBean.setAddonTerminalID(MenuZ.getInstance().getTerminalId());
                        addonsBean.setAddonPrice("");

                        List<OrderPreparationAddonModel> orderPreparationAddonModels = getDataManager().loadallAddongroup(orderAddOnChild.getItemIdAddon(), orderAddOnChild.getAddonId(), orderAddOnChild.getOrderId(), orderItemModel.getItemPrimaryKey());
                        for (int l = 0; l < orderPreparationAddonModels.size(); l++) {
                            OrderPreparationAddonModel orderPreparationAddonModel = orderPreparationAddonModels.get(l);
                            OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean.AddonprepsBean addonprepsBean = new OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean.AddonprepsBean();
                            addonprepsBean.setApreparationID(orderPreparationAddonModel.getPreparationId());
                            addonprepsBean.setApreparationPrefixID(orderPreparationAddonModel.getPreparationIsPrefixed());
                            addonprepsBeanArrayList.add(addonprepsBean);
                            addonsBean.setAddonpreps(addonprepsBeanArrayList);
                        }
                        addonsBeans.add(addonsBean);
                    }
                    itemsBean.setAddons(addonsBeans);
                    orderJsonModel.setItems(itemsBeanList);


                }
                orderJsonModel1.setOrders(ordersBeans);
                /*List<OrderJsonModel.OrdersBean.PaymentsBean> paymentsBeans = new ArrayList<>();
                OrderJsonModel.OrdersBean.PaymentsBean paymentsBean = new OrderJsonModel.OrdersBean.PaymentsBean();
                paymentsBean.setPaymentApproved("");
                paymentsBean.setPaymentID("");
                paymentsBean.setPaymentIdentification("");
                if (txtGrandtotal.contains("$")){
                    String da = txtGrandtotal.replace("$", "");
                    paymentsBean.setPaymentPrice(da);
                    paymentsBean.setPaymentTerminalID(MenuZ.getInstance().getTerminalId());
                    paymentsBeans.add(paymentsBean);
                    orderJsonModel.setPayments(paymentsBeans);

                }*/
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Gson gson = new Gson();
                String jsonString = gson.toJson(orderJsonModel1);
                new Thread(() -> {
                    try {
                        JSONObject request = new JSONObject(jsonString);
                        Log.d("json", "" + request);
                        String json = request.toString().trim();
                        List<OrderItemModel> selectedItemList = getDataManager().getAllorderItem(MenuZ.getInstance().getOrderId());
                        for (int i = 0; i < selectedItemList.size(); i++) {
                            OrderItemModel orderItemModel = selectedItemList.get(i);
                            List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(orderItemModel.getItemPrimaryKey(), MenuZ.getInstance().getOrderId());
                            for (int j = 0; j < cartSeltedAddonList.size(); j++) {
                                OrderAddOnChild orderAddOnChild = cartSeltedAddonList.get(j);
                                getDataManager().updateIsSyncInAddons(true, orderAddOnChild.getAddonId(), orderAddOnChild.getItemIdAddon(),
                                        orderAddOnChild.getAddonGroupId());

                            }
                        }
                        handler.post(() -> sendJson(json));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }.execute();
    }

    /*Send json to server*/
    private void sendJson(String jsonObject) {

        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(CartDetailActivity.this, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    sendJson(jsonObject);
                }
            }).show();
        }

        new HttpTask(new HttpTask.Builder(CartDetailActivity.this, "syncclient/0/admin/admin/" + jsonObject, new HttpResponceListner.Listener() {
            @Override
            public void onResponse(String response, String apiName) {
                Log.e("LogInREsPonce", "onResponse: " + response);
                try {

                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray = js.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String status = jsonObject.getString("success");
                        if (status.equals("true")) {
                            JSONObject jsonObjectOrder = jsonObject.getJSONObject("orders");
                            String orderID = jsonObjectOrder.getString("orderID");
                            orderId = orderID;

                            new Thread(() -> {
                                List<PayMethodsModel> getallPay = getDataManager().loadAllPayment();
                                for (PayMethodsModel payMethodsModel : getallPay) {
                                    getDataManager().updateOrderId(orderID, payMethodsModel.getTermId());
                                }

                                List<OrderTableModel> getAllTable = getDataManager().loadAllTable();
                                for (OrderTableModel orderTableModel : getAllTable) {
                                    getDataManager().updateOrderTable(orderID, orderTableModel.getTableId());
                                }

                                List<OrderItemModel> selectedItemList = getDataManager().getAllorderItem(MenuZ.getInstance().getOrderId());

                                for (OrderItemModel orderItemModel : selectedItemList) {
                                    getDataManager().updateOrderIdinItem(orderID, orderItemModel.getItemId());
                                    List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(orderItemModel.getItemPrimaryKey(), MenuZ.getInstance().getOrderId());
                                    for (OrderAddOnChild orderAddOnChild : cartSeltedAddonList) {
                                        getDataManager().UpdateOrderId(orderID, orderAddOnChild.getAddonId());
                                        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + orderAddOnChild.getOrderId());
                                        List<OrderPreparationAddonModel> orderPreparationAddonModels = getDataManager().loadallAddongroup(orderAddOnChild.getItemIdAddon(), orderAddOnChild.getAddonId(), orderAddOnChild.getOrderId(), orderItemModel.getItemPrimaryKey());
                                        for (OrderPreparationAddonModel orderPreparationAddonModel : orderPreparationAddonModels) {
                                            getDataManager().upadteOrderId(orderPreparationAddonModel.getAddonsGroupId(), orderPreparationAddonModel.getItemIdAddon(), orderPreparationAddonModel.getAddonId(), orderID);
                                        }
                                    }

                                    List<OrderPreparationModel> orderPreparationModels = getDataManager().loadAllSelectedPreprations(MenuZ.getInstance().getOrderId(), orderItemModel.getItemPrimaryKey());
                                    for (OrderPreparationModel orderPre : orderPreparationModels) {
                                        getDataManager().updatePrepration(orderID, orderPre.getPreparationId());
                                        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + orderPre.getOrderId());
                                    }
                                }

                                List<PaymentModel> getAllPayment = getDataManager().loadAllPayments();
                                for (PaymentModel pay : getAllPayment) {
                                    getDataManager().updateOrderIdPayment(orderID, pay.getTermId());
                                }


                            }).start();

                            tableOrderCount.setText("Order ID:" + " " + orderId + "/" + "Table No:" + " " + newOrderModel.getTableId() + "/" + "Name:" + " " + newOrderModel.getEmployeeName());
                            MenuZ.getInstance().setOrderId(orderId);

                            paymentDne();
                            Toast.makeText(CartDetailActivity.this, "Order Submit Successfully.", Toast.LENGTH_SHORT).show();
                        } else {
                            String errorMessage = jsonObject.getString("errorMessage");
                            Toast.makeText(CartDetailActivity.this, "" + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
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


    public void paymentDne() {
        final Dialog dialog = new Dialog(CartDetailActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popuup_home_and_payment);
        LinearLayout llHome = dialog.findViewById(R.id.llHome);
        LinearLayout llPayment = dialog.findViewById(R.id.llPayment);
        ImageView img_cancel = dialog.findViewById(R.id.img_cancel);
        llHome.setOnClickListener(v -> {
            lockTable(CartDetailActivity.this, MenuZ.getInstance().getTerminalId(), newOrderModel.getTableId());
            dialog.dismiss();
           /* Intent intent = new Intent(CartDetailActivity.this, HomeActivity.class);
            startActivity(intent);

            dialog.dismiss();*/
        });
        llPayment.setOnClickListener(v -> {
            Intent intent = new Intent(CartDetailActivity.this, PaymentActivity.class);
            intent.putExtra("paymentamomt", txtTotalCount.getText().toString().trim());
            intent.putExtra("tableId", newOrderModel.getTableId());
            intent.putExtra("orderId", MenuZ.getInstance().getOrderId());
            startActivity(intent);
            dialog.dismiss();


        });
        img_cancel.setOnClickListener((View v) -> dialog.dismiss());
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

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

        new HttpTask(new HttpTask.Builder(context, "SetTable/" + terminalId + "/" + tableID + "/" + "0", new HttpResponceListner.Listener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(String response, String apiName) {
                try {
                    progressBar.setVisibility(View.GONE);
                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray = js.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String status = jsonObject.getString("success");
                        if (status.equals("true")) {
                            Intent intent = new Intent(CartDetailActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void ErrorListener(VolleyError error) {

            }
        })
                .setBody(null, HttpTask.ContentType.APPLICATION_JSON)
                .setProgress(false))
                .execute("");
    }



    /*Password popup*/

    private void passwordPopup(String popuptitle) {
        final Dialog dialog = new Dialog(CartDetailActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_password);
        ImageView imgClose = dialog.findViewById(R.id.imgClose);
        TextView txtTitle = dialog.findViewById(R.id.txtTitle);
        txtTitle.setText(popuptitle);
        TextView btnDone = dialog.findViewById(R.id.btnDone);
        EditText edpw = dialog.findViewById(R.id.edpw);
        btnDone.setOnClickListener(v -> {
            Session session = new Session(CartDetailActivity.this);
            String password = session.getPasswordUser();
            if (TextUtils.isEmpty(edpw.getText().toString().trim())) {
                Toast.makeText(CartDetailActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(edpw.getText().toString().trim())) {
                Toast.makeText(CartDetailActivity.this, "Please Enter Correct  Password", Toast.LENGTH_SHORT).show();

            } else {
                switch (popuptitle) {
                    case "Change Waiter": {
                        dialog.dismiss();

                        Intent intent = new Intent(CartDetailActivity.this, SelectWaiterActivity.class);
                        intent.putExtra("From", "cart");
                        intent.putExtra("tableId", newOrderModel.getTableId());
                        intent.putExtra("terminalId", MenuZ.getInstance().getTerminalId());
                        intent.putExtra("orderId", orderId);
                        intent.putExtra("employeeId", newOrderModel.getEmployeeId());
                        startActivity(intent);
                        break;
                    }
                    case "Transfer Table": {
                        Intent intent = new Intent(CartDetailActivity.this, OrderListActivity.class);
                        intent.putExtra("From", "cartTransfer");
                        intent.putExtra("tableId", newOrderModel.getTableId());
                        intent.putExtra("terminalId", MenuZ.getInstance().getTerminalId());
                        intent.putExtra("orderId", orderId);
                        intent.putExtra("employeeId", newOrderModel.getEmployeeId());
                        startActivity(intent);
                        break;
                    }
                    case "Split Table": {
                        dialog.dismiss();
                        Intent intent = new Intent(CartDetailActivity.this, OrderListActivity.class);
                        intent.putExtra("From", "cartSplit");
                        intent.putExtra("tableId", newOrderModel.getTableId());
                        intent.putParcelableArrayListExtra("itemList", (ArrayList<? extends Parcelable>) selectedItemList);
                        intent.putExtra("terminalId", MenuZ.getInstance().getTerminalId());
                        intent.putExtra("orderId", orderId);
                        intent.putExtra("employeeId", newOrderModel.getEmployeeId());
                        startActivity(intent);
                        break;
                    }

                    case "Switch Table": {
                        dialog.dismiss();
                        Intent intent = new Intent(CartDetailActivity.this, OrderListActivity.class);
                        intent.putExtra("From", "cartSwitch");
                        intent.putExtra("tableId", newOrderModel.getTableId());
                        intent.putExtra("terminalId", MenuZ.getInstance().getTerminalId());
                        intent.putExtra("orderId", orderId);
                        intent.putExtra("employeeId", newOrderModel.getEmployeeId());
                        startActivity(intent);
                        break;
                    }

                    case "Discount": {
                        dialog.dismiss();
                        Intent intent = new Intent(CartDetailActivity.this, SplitTableActivity.class);
                        intent.putExtra("From", "cartDiscount");
                        intent.putExtra("tableId", newOrderModel.getTableId());
                        intent.putParcelableArrayListExtra("itemList", (ArrayList<? extends Parcelable>) selectedItemList);
                        intent.putExtra("terminalId", MenuZ.getInstance().getTerminalId());
                        intent.putExtra("orderId", orderId);
                        intent.putExtra("employeeId", newOrderModel.getEmployeeId());
                        startActivity(intent);
                        break;
                    }
                }

            }
        });
        imgClose.setOnClickListener(v -> dialog.dismiss());

        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    /*Click of list of drawer*/

    @Override
    public void pos(int pos) {
        switch (pos) {

            case 0:
                popuptitle = arrayList.get(pos).itemName;
                drawer.closeDrawers();
                passwordPopup(popuptitle);
                break;

            case 1:
                popuptitle = arrayList.get(pos).itemName;
                drawer.closeDrawers();
                passwordPopup(popuptitle);
                break;

            case 2:
                popuptitle = arrayList.get(pos).itemName;
                drawer.closeDrawers();
                passwordPopup(popuptitle);
                break;

            case 3:
                popuptitle = arrayList.get(pos).itemName;
                drawer.closeDrawers();
                passwordPopup(popuptitle);
                break;

            case 4:
                drawer.closeDrawers();
                Intent intent = new Intent(CartDetailActivity.this, PrintNotesActivity.class);
                intent.putExtra("orderId", orderId);
                startActivity(intent);
                break;

            case 5:
                popuptitle = arrayList.get(pos).itemName;
                drawer.closeDrawers();
                passwordPopup(popuptitle);
                break;
        }
    }

}
