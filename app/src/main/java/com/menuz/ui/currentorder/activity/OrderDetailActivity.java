package com.menuz.ui.currentorder.activity;

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
import com.menuz.data.model.db.NewOrderModel;
import com.menuz.data.model.db.OrderAddOnChild;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.data.model.db.OrderJsonModel;
import com.menuz.data.model.db.OrderNewjsonModel;
import com.menuz.data.model.db.OrderPreparationAddonModel;
import com.menuz.data.model.db.OrderPreparationModel;
import com.menuz.network.HttpResponceListner;
import com.menuz.network.HttpTask;
import com.menuz.session.Session;
import com.menuz.ui.Home.activity.HomeActivity;
import com.menuz.ui.Home.activity.SelectWaiterActivity;
import com.menuz.ui.Home.model.NavigationModel;
import com.menuz.ui.Order.activity.NewOrderActivity;
import com.menuz.ui.Order.activity.OrderListActivity;
import com.menuz.ui.Order.adapter.ExpandableListAdapter;
import com.menuz.ui.Order.adapter.PaymentAdapter;
import com.menuz.ui.ParseOrderJson;
import com.menuz.ui.Payment.PaymentActivity;
import com.menuz.ui.currentorder.MenuOptionsAdapter;
import com.menuz.ui.currentorder.OrderDetailFragment;
import com.menuz.ui.currentorder.SplitTableActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends BaseActivity implements View.OnClickListener, ExpandableListAdapter.OnItemCount,MenuOptionsAdapter.Listener {

    RelativeLayout iv_newView;
    private List<OrderItemModel> selectedItemList = new ArrayList<>();
    private NewOrderModel newOrderModel=new NewOrderModel();
    private TextView txtGrandTotal;
    private List<OrderNewjsonModel.OrdersBean.ItemsBean.ItemprepsBean> itemsBeans = new ArrayList<>();
    private List<OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean.AddonprepsBean> addonprepsBeanArrayList = new ArrayList<>();
    Context context;
    private String tableId="";
    private TextView cartCountTxt;
    private String nuofguest="";

    //private NewOrderModel newOrderModel;
    private OrderNewjsonModel orderJsonModel1 = new OrderNewjsonModel();

    private TextView txtTotal;
    private ProgressBar progressBar;
    private ArrayList<NavigationModel>arrayList=new ArrayList<>();
    private DrawerLayout drawer;

    private String selectedItem = "";
    private String employeeId = "";
    private TextView txtTotalCount;
    private NavigationView navigation_view;

    private String employeename="";
    private TextView tvZone;
    private TextView txtPaymentStatus;
    private TextView txtContinue;
    private Handler handler = new Handler(Looper.getMainLooper());
    private RelativeLayout rlTotal, rlTax, rlGrandTotal;
    private TextView tableOrderCount;

    private LinearLayout llPlaceOrder;
    private String orderId="";
    private String isUpdated="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_detail_new);
        Intent intent = getIntent();
        if (intent != null) {
            orderId = intent.getStringExtra("orderId");
            tableId = intent.getStringExtra("tableId");
            employeename = intent.getStringExtra("employeename");
            employeeId = intent.getStringExtra("employeeId");
            nuofguest = intent.getStringExtra("nuofguest");
            isUpdated = intent.getStringExtra("isUpdated");
        }

        addFragment(OrderDetailFragment.newInstance(orderId, tableId,employeename,employeeId,nuofguest), false);



        initView();

    }




    @SuppressLint({"SetTextI18n", "StaticFieldLeak"})
    public void initView() {

        ImageView btnPlus = findViewById(R.id.btnPlus);
        RecyclerView recyclerMenu = findViewById(R.id.recyclerMenu);
        navigation_view=findViewById(R.id.navigation_view);
        tableOrderCount=findViewById(R.id.tableOrderCount);
        navigation_view.setOnClickListener(this);

        LinearLayout llAddMore = findViewById(R.id.llAddMore);
        LinearLayout llPrint = findViewById(R.id.llPrint);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setVisibility(View.GONE);
        ImageView icBack = findViewById(R.id.icBack);
        ImageView menuImg = findViewById(R.id.menuImg);
        txtTotalCount = findViewById(R.id.txtTotalCount);
        cartCountTxt = findViewById(R.id.cartCountTxtItem);
        progressBar = findViewById(R.id.progress);
        LinearLayout llDone = findViewById(R.id.llDone);
        menuImg.setVisibility(View.VISIBLE);
        txtContinue = findViewById(R.id.txtContinue);
        drawer=findViewById(R.id.drawer);
        drawer.setScrimColor(Color.TRANSPARENT);
        drawer.closeDrawers();
        txtPaymentStatus = findViewById(R.id.txtPaymentStatus);
        iv_newView = findViewById(R.id.iv_newView);
        rlTotal = findViewById(R.id.rlTotal);
        rlTax = findViewById(R.id.rlTax);
        rlGrandTotal = findViewById(R.id.rlGrandTotal);
        llPlaceOrder = findViewById(R.id.llPlaceOrder);
        tvZone = findViewById(R.id.tvZoneName);
        txtTotal = findViewById(R.id.txtTotal);
        txtGrandTotal = findViewById(R.id.txtGrandTotal);
        btnBack.setOnClickListener(this);
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText(R.string.your_cart);
        btnPlus.setVisibility(View.VISIBLE);
        btnPlus.setOnClickListener(this);
        llPrint.setOnClickListener(this);
        //card.setOnClickListener(this);
        llAddMore.setOnClickListener(this);
        icBack.setOnClickListener(this);
        menuImg.setOnClickListener(this);

        llDone.setOnClickListener(this);

        new Thread(() -> {
            selectedItemList = getDataManager().getSelectedItem(orderId);
            handler.post(() -> cartCountTxt.setText(""+selectedItemList.size()));
        }).start();

        addItemsiDrawer();
        LinearLayoutManager layoutManager=new LinearLayoutManager(OrderDetailActivity.this);
        recyclerMenu.setLayoutManager(layoutManager);
        MenuOptionsAdapter navigationAdapter = new MenuOptionsAdapter(arrayList, this);
        recyclerMenu.setAdapter(navigationAdapter);

        if (employeename.equals("")&&newOrderModel.getTableId()!=null){
           new Thread(() -> newOrderModel=getDataManager().loadDataOnOrderId(orderId)).start();
           handler.post(() -> tableOrderCount.setText("Order ID:"+" "+orderId+"/"+"Table No:"+" " + newOrderModel.getTableId()+"/"+"Name:"+" " +newOrderModel.getEmployeeName()));
       }else {
           tableOrderCount.setText("Order ID:"+" "+orderId+"/"+"Table No:"+" " + tableId+"/"+"Name:"+" " +employeename);

       }



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;

            case R.id.bottom:
                if (selectedItem.equals("")) {
                    popupPayment();
                } else {
                    ParseOrderJson parseOrderJson = new ParseOrderJson(txtGrandTotal.getText().toString(), OrderDetailActivity.this);
                    parseOrderJson.parseData();
                }

                break;

            case R.id.btnPlus:
                lockTable(OrderDetailActivity.this,MenuZ.getInstance().getTerminalId(),tableId);
                /*Intent intent = new Intent(OrderDetailActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();*/
                break;


            case R.id.card:
                Intent intentEditTable = new Intent(OrderDetailActivity.this, EditTableAndZoneActivity.class);
                intentEditTable.putExtra("zonename", tvZone.getText().toString());
                intentEditTable.putExtra("orderId", orderId);
                intentEditTable.putExtra("zoneId", newOrderModel.getZoneId());
                intentEditTable.putExtra("tableId", newOrderModel.getTableId());
                startActivity(intentEditTable);
                break;

            case R.id.llDone:
                //paymentDne();

                if (MenuZ.getInstance().getOrderId()!=null){
                    parseData(cartCountTxt.getText().toString());
                }else {
                    getTerminalId(OrderDetailActivity.this, "e9879c86ff3e8535", tableId, "getterminal");

                }

/*

                ParseOrderJson parseOrderJson = new ParseOrderJson(cartCountTxt.getText().toString(), OrderDetailActivity.this);
                parseOrderJson.parseData();
*/


                break;

            case R.id.llAddMore:
                Intent intentItem=new Intent(OrderDetailActivity.this,NewOrderActivity.class);
                intentItem.putExtra("orderId",orderId);
                intentItem.putExtra("edit","");
                intentItem.putExtra("from","order");
                intentItem.putExtra("navigation","neworder");
                intentItem.putExtra("employeename",employeename);
                intentItem.putExtra("employeeId",employeeId);
                intentItem.putExtra("tableId",tableId);
                intentItem.putExtra("isUpdated",isUpdated);
                intentItem.putExtra("nuofguest",nuofguest);
                startActivity(intentItem);
                break;

            case R.id.llPrint:
                if (MenuZ.getInstance().getOrderId()!=null){
                    parseData(cartCountTxt.getText().toString());
                }else {
                    getTerminalId(OrderDetailActivity.this, "e9879c86ff3e8535", tableId, "getterminal");

                }

                //lockTable(OrderDetailActivity.this,terminalId,tableId);
                break;


            case R.id.menuImg:
               /* int[] point = new int[2];

                // Get the x, y location and store it in the location[] array
                // location[0] = x, location[1] = y.
                menuImg.getLocationOnScreen(point);

                //Initialize the Point with x, and y positions
                Display display = getWindowManager().getDefaultDisplay();
                Point p = new Point();
                display.getSize(p);
                p.x = point[0];
                p.y = point[1];
                showPopup(p);*/

                //popupFilter(p);

                if (drawer.isDrawerOpen(navigation_view)) {
                    drawer.closeDrawers();
                }
                else {
                    drawer.openDrawer(navigation_view);
                }
                break;


            case R.id.icBack:

                if (drawer.isDrawerOpen(navigation_view)) {
                    drawer.closeDrawers();
                }
                else {
                    drawer.openDrawer(navigation_view);
                }

                break;

        }
    }

    public void getTerminalId(Context context, String deviceId, String tableId, String lockUnlockstatus) {
        progressBar.setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(context, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    getTerminalId(context, deviceId, tableId, lockUnlockstatus);
                }

            }).show();
        }

        new HttpTask(new HttpTask.Builder(context, "GetTerminalData/" + deviceId, new HttpResponceListner.Listener() {
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
                            String termId = jsonObject.getString("termId");
                            MenuZ.getInstance().setTerminalId(termId);
                            if (lockUnlockstatus.equals("getterminal")){
                                parseData(txtTotal.getText().toString().trim());
                            }else {
                                lockTable(OrderDetailActivity.this, termId, tableId);
                            }



                        } else {
                            String errMes = jsonObject.getString("errMes");
                            Toast.makeText(context, ""+errMes, Toast.LENGTH_SHORT).show();
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


    @SuppressLint("SetTextI18n")
    @Override
    public void itemIncrease(int position) {

       /* String price = selectedItemList.get(position).getItemPrice();
        totalcost += Integer.parseInt(price);
        txtGrandTotal.setText("" + "$" + totalcost + ".00");
        txtTotalCount.setText("" + "$" + totalcost + ".00");
        txtTotal.setText("" + "$" + totalcost + ".00");
        cartTotaltxt.setText("" + "$" + totalcost + ".00");*/


    }

    @SuppressLint("SetTextI18n")
    @Override
    /*listener for delete item*/
    public void onDeleteItem(int position) {
      //  deleteItem(position);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void itemDecrease(int position) {
        /*  String count = selectedItemList.get(position).getCountPrice();
        if (Integer.parseInt(count) > 0) {
            totalcost -= Integer.parseInt(price);
            txtTotal.setText("" + "$" + totalcost + ".00");
            txtGrandTotal.setText("" + "$" + totalcost + ".00");
            txtTotalCount.setText("" + "$" + totalcost + ".00");
            cartTotaltxt.setText("" + "$" + totalcost + ".00");
            new Thread(() -> getDataManager().updateTotal("" + totalcost + ".00")).start();
        }*/


    }

/*    listener for edit item
 */
    @Override
    public void editItem(int position) {
     //editItemPopup(position,listDataHeader.get(position).getOrderId());
    }


    private void addItemsiDrawer(){
        NavigationModel item;
        for(int i=0;i<6;i++) {
            item = new NavigationModel();
            switch (i) {
                case 0:
                    item.itemName = "Change Waiter";
                    item.itemImg = R.drawable.ic_waiter_icon;
                    item.isSelect=true;
                  //  item.itemImgActive=R.drawable.home_active;

                    break;
                case 1:
                    item.itemName ="Transfer Table";
                    item.isSelect=false;
                    item.itemImg = R.drawable.ic_transfer_table;
                    //item.itemImgActive=R.drawable.gallery_active;

                    break;

                case 2:
                    item.itemName = "Split Table";
                    item.isSelect=false;
                    item.itemImg = R.drawable.ic_split_table;
                   // item.itemImgActive=R.drawable.language_active;
                    break;

                case 3:
                    item.itemName = "Switch Table";
                    item.isSelect=false;
                    item.itemImg = R.drawable.switch_table;
                    //item.itemImgActive=R.drawable.logout_active;
                    break;

                case 4:
                    item.itemName = "Print Notes";
                    item.isSelect=false;
                    item.itemImg = R.drawable.ic_print_notes;
                   // item.itemImgActive=R.drawable.active_setting_icon;
                    break;

                case 5:
                    item.itemName = "Discount";
                    item.isSelect=false;
                    item.itemImg = R.drawable.ic_discount;
                    // item.itemImgActive=R.drawable.active_setting_icon;
                    break;





            }
            arrayList.add(item);
        }
    }




    public void paymentDne() {
        final Dialog dialog = new Dialog(OrderDetailActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popuup_home_and_payment);
        LinearLayout llHome = dialog.findViewById(R.id.llHome);
        LinearLayout llPayment = dialog.findViewById(R.id.llPayment);
        ImageView img_cancel = dialog.findViewById(R.id.img_cancel);
        llHome.setOnClickListener(v -> {
            lockTable(OrderDetailActivity.this,MenuZ.getInstance().getTerminalId(),tableId);
           /* Intent intent = new Intent(OrderDetailActivity.this, HomeActivity.class);
            startActivity(intent);*/
        });
        llPayment.setOnClickListener(v -> {
            Intent intent = new Intent(OrderDetailActivity.this, PaymentActivity.class);
            intent.putExtra("paymentamomt",txtTotalCount.getText().toString().trim());
            intent.putExtra("orderId",orderId);
            intent.putExtra("tableId",tableId);
            dialog.dismiss();
            startActivity(intent);
        });
        img_cancel.setOnClickListener((View v) -> dialog.dismiss());
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

    }

    @SuppressLint({"StaticFieldLeak", "SetTextI18n"})
    public void popupPayment() {
        final Dialog dialog = new Dialog(OrderDetailActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popup_quantity);
        RecyclerView recyclerPrefix = dialog.findViewById(R.id.recyclerPrefix);
        TextView tvHeader = dialog.findViewById(R.id.tvHeader);
        TextView btnDone = dialog.findViewById(R.id.btnDone);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.clear();
        arrayList.add("Cash");
        arrayList.add("10bis");
        arrayList.add("Sodexo");
        arrayList.add("Credix");
        arrayList.add("Goodi");
        arrayList.add("Valuecard");
        arrayList.add("BusinessLogic");
        recyclerPrefix.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this));
        recyclerPrefix.setAdapter(new PaymentAdapter(OrderDetailActivity.this, arrayList, new PaymentAdapter.OnClick() {
            @Override
            public void position(int position) {
                selectedItem = arrayList.get(position);

            }

            @Override
            public void selected(String isSelected) {

            }
        }));


        tvHeader.setText(R.string.payment);
        btnDone.setOnClickListener(v -> {
            dialog.dismiss();
            txtPaymentStatus.setVisibility(View.VISIBLE);
            txtContinue.setVisibility(View.VISIBLE);
            llPlaceOrder.setVisibility(View.GONE);
            rlTotal.setVisibility(View.GONE);
            iv_newView.setVisibility(View.VISIBLE);
            rlTax.setVisibility(View.GONE);
            rlGrandTotal.setVisibility(View.GONE);
            txtPaymentStatus.setText("Payment Method: " + selectedItem);


        });
        ImageView img_cancel = dialog.findViewById(R.id.img_cancel);
        img_cancel.setOnClickListener((View v) -> dialog.dismiss());
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();


    }

  /*  Call api for lock table*/
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
                            Intent intent=new Intent(OrderDetailActivity.this,HomeActivity.class);
                            startActivity(intent);

                        }


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
                .setProgress(false))
                .execute("");
    }


    @SuppressLint("StaticFieldLeak")
    public void parseData(String txtGrandtotal) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                // newOrderModel = getDataManager().geloadOrderId();
                List<OrderItemModel> selectedItemList = getDataManager().getAllorderItem(orderId);
                // OrderTableModel orderTableModel = getDataManager().getallOrderTable(MenuZ.getInstance().getOrderId());
                //OrderEmployeeModel orderEmployeeModel = getDataManager().getAllOrderEmployee(MenuZ.getInstance().getOrderId());
                Log.d("selec",""+selectedItemList.size());
                // @SuppressLint("HardwareIds") String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                OrderNewjsonModel.OrdersBean orderJsonModel = new OrderNewjsonModel.OrdersBean();
                orderJsonModel.setOrderID(orderId);
                orderJsonModel.setOrderTableID(tableId);
                orderJsonModel.setOrderEmployeeID(employeeId);
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
                    if (orderItemModel.getItemRemark().equals("")){
                        itemsBean.setItemRemark("");
                    }else {
                        itemsBean.setItemRemark(orderItemModel.getItemRemark());

                    }
                    itemsBean.setItemChoiceID("");
                    itemsBean.setItemEmployeeID(employeeId);
                    itemsBean.setItemID(orderItemModel.getItemId());
                    itemsBean.setItemDiner("");
                    itemsBean.setItemCourse("");
                    itemsBean.setItemAutoID(orderItemModel.getItemAutoID());

                    itemsBean.setItemPrice(orderItemModel.getItemPrice());
                    itemsBean.setItemPriceID("");
                    itemsBean.setItemOrderID(orderId);
                    itemsBean.setItemTerminalID(MenuZ.getInstance().getTerminalId());

                    itemsBean.setItemQuantity(orderItemModel.getCountPrice());

                    List<OrderPreparationModel> orderPreparationModels = getDataManager().loadAllSelectedPreprations(MenuZ.getInstance().getOrderId(), orderItemModel.getItemPrimaryKey());
                    Log.d("orderPreparationModels",""+orderPreparationModels.size());
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


                    List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons( orderItemModel.getItemPrimaryKey(), orderId);
                    List<OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean> addonsBeans=new ArrayList<>();
                    for (int k = 0; k < cartSeltedAddonList.size(); k++) {
                        OrderAddOnChild orderAddOnChild = cartSeltedAddonList.get(k);
                        OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean addonsBean = new OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean();
                        addonsBean.setAddonChoiceID(orderAddOnChild.getAddonsGroupId());
                        addonsBean.setAddonCourse("");

                        addonsBean.setAddonDiner("");
                        if (orderAddOnChild.getAddonAutoID()!=null){
                            addonsBean.setAddonAutoID(orderAddOnChild.getAddonAutoID());
                        }else {
                            addonsBean.setAddonAutoID("0");
                        }

                        addonsBean.setAddonEmployeeID(employeeId);
                        addonsBean.setAddonID(orderAddOnChild.getAddonId());
                        addonsBean.setAddonOrderID(orderId);
                        addonsBean.setAddonQuantity("");
                        addonsBean.setAddonRemark(orderAddOnChild.getAddonRemark());
                        addonsBean.setAddonTerminalID(MenuZ.getInstance().getTerminalId());
                        addonsBean.setAddonPrice("");

                        List<OrderPreparationAddonModel> orderPreparationAddonModels = getDataManager().loadallAddongroup(orderAddOnChild.getItemIdAddon(), orderAddOnChild.getAddonId(), MenuZ.getInstance().getOrderId(), orderAddOnChild.getItemPrimaryKey());
                        for (int l = 0; l < orderPreparationAddonModels.size(); l++) {
                            OrderPreparationAddonModel orderPreparationAddonModel = orderPreparationAddonModels.get(l);
                            OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean.AddonprepsBean addonprepsBean = new  OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean.AddonprepsBean();
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
                List<OrderJsonModel.OrdersBean.PaymentsBean> paymentsBeans = new ArrayList<>();
                OrderJsonModel.OrdersBean.PaymentsBean paymentsBean = new OrderJsonModel.OrdersBean.PaymentsBean();
                paymentsBean.setPaymentApproved("");
                paymentsBean.setPaymentID("");
                //paymentsBean.setPaymentIdentification("");
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
                new Thread(() -> {
                    try {
                        JSONObject request = new JSONObject(jsonString);
                        Log.d("json",""+request);
                        String json=request.toString().trim();
                        List<OrderItemModel> selectedItemList = getDataManager().getAllorderItem(MenuZ.getInstance().getOrderId());
                        for (int i = 0; i <selectedItemList.size() ; i++) {
                            OrderItemModel orderItemModel=selectedItemList.get(i);
                            List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(orderItemModel.getItemPrimaryKey(), MenuZ.getInstance().getOrderId());
                            for (int j = 0; j <cartSeltedAddonList.size() ; j++) {
                                OrderAddOnChild orderAddOnChild=cartSeltedAddonList.get(j);
                                getDataManager().updateIsSyncInAddons(true,orderAddOnChild.getAddonId(),orderAddOnChild.getItemIdAddon(),orderAddOnChild.getAddonGroupId());

                            }
                        }

                        handler.post(() -> sendJson(json));



                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }).start();
            }
        }.execute();
    }


    private void sendJson(String jsonObject){
        //    OkHttpClient client = httpClient.build();

        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(OrderDetailActivity.this, (dialog, isConnected) -> {
                if(isConnected){
                    dialog.dismiss();
                    sendJson(jsonObject);
                }

            }).show();


        }


        new HttpTask(new HttpTask.Builder(OrderDetailActivity.this, "syncclient/0/admin/admin/"+jsonObject, new HttpResponceListner.Listener() {
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
                            paymentDne();
                            Toast.makeText(OrderDetailActivity.this, "Order Submit Successfully.", Toast.LENGTH_SHORT).show();
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


    private void passwordPopup(String popuptitle){
        final Dialog dialog = new Dialog(OrderDetailActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_password);
        ImageView imgClose=dialog.findViewById(R.id.imgClose);
        TextView txtTitle=dialog.findViewById(R.id.txtTitle);
        txtTitle.setText(popuptitle);
        TextView btnDone=dialog.findViewById(R.id.btnDone);
        EditText edpw=dialog.findViewById(R.id.edpw);
        btnDone.setOnClickListener(v -> {
            Session session=new Session(OrderDetailActivity.this);
            String password=session.getPasswordUser();
          if (TextUtils.isEmpty(edpw.getText().toString().trim()))    {
              Toast.makeText(OrderDetailActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
          }else if (!password.equals(edpw.getText().toString().trim())){
              Toast.makeText(OrderDetailActivity.this, "Please Enter Correct  Password", Toast.LENGTH_SHORT).show();

          }else {
              switch (popuptitle) {
                  case "Change Waiter": {
                      dialog.dismiss();

                      Intent intent = new Intent(OrderDetailActivity.this, SelectWaiterActivity.class);
                      intent.putExtra("From", "cart");
                      intent.putExtra("tableId", tableId);
                      intent.putExtra("terminalId", MenuZ.getInstance().getTerminalId());
                      intent.putExtra("orderId", orderId);
                      intent.putExtra("employeeId", employeeId);
                      startActivity(intent);
                      break;
                  }
                  case "Transfer Table": {
                      Intent intent = new Intent(OrderDetailActivity.this, OrderListActivity.class);
                      intent.putExtra("From", "cartTransfer");
                      intent.putExtra("tableId", tableId);
                      intent.putExtra("terminalId", MenuZ.getInstance().getTerminalId());
                      intent.putExtra("orderId", orderId);
                      intent.putExtra("employeeId", employeeId);
                      startActivity(intent);
                      break;
                  }
                  case "Split Table": {
                      dialog.dismiss();
                      Intent intent = new Intent(OrderDetailActivity.this, OrderListActivity.class);
                      intent.putExtra("From", "cartSplit");
                      intent.putExtra("tableId", tableId);
                      intent.putParcelableArrayListExtra("itemList", (ArrayList<? extends Parcelable>) selectedItemList);
                      intent.putExtra("terminalId", MenuZ.getInstance().getTerminalId());
                      intent.putExtra("orderId", orderId);
                      intent.putExtra("employeeId", employeeId);
                      startActivity(intent);
                      break;
                  }

                  case "Switch Table": {
                      dialog.dismiss();
                      Intent intent = new Intent(OrderDetailActivity.this, OrderListActivity.class);
                      intent.putExtra("From", "cartSwitch");
                      intent.putExtra("tableId", tableId);
                      intent.putExtra("terminalId", MenuZ.getInstance().getTerminalId());
                      intent.putExtra("orderId", orderId);
                      intent.putExtra("employeeId", employeeId);
                      startActivity(intent);
                      break;
                  }

                  case "Discount":{
                      dialog.dismiss();
                      Intent intent = new Intent(OrderDetailActivity.this,SplitTableActivity.class);
                      intent.putExtra("From", "cartDiscount");
                      intent.putExtra("tableId", tableId);
                      intent.putParcelableArrayListExtra("itemList", (ArrayList<? extends Parcelable>) selectedItemList);
                      intent.putExtra("terminalId", MenuZ.getInstance().getTerminalId());
                      intent.putExtra("orderId", orderId);
                      intent.putExtra("employeeId", employeeId);
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

    String popuptitle="";
    @Override
    public void pos(int pos) {
     switch (pos){

         case 0:
             popuptitle=arrayList.get(pos).itemName;
             drawer.closeDrawers();
             passwordPopup(popuptitle);
             break;

         case 1:
             popuptitle=arrayList.get(pos).itemName;
             drawer.closeDrawers();
             passwordPopup(popuptitle);
             break;

         case 2:
             popuptitle=arrayList.get(pos).itemName;
             drawer.closeDrawers();
             passwordPopup(popuptitle);
             break;

         case 3:
             popuptitle=arrayList.get(pos).itemName;
             drawer.closeDrawers();
             passwordPopup(popuptitle);
             break;

         case 4:
             drawer.closeDrawers();
             Intent intent=new Intent(OrderDetailActivity.this,PrintNotesActivity.class);
             intent.putExtra("orderId", orderId);
             startActivity(intent);
             break;

         case 5:
             popuptitle=arrayList.get(pos).itemName;
             drawer.closeDrawers();
             passwordPopup(popuptitle);
             break;



     }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
