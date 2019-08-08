package com.menuz.ui.Order.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menuz.Demo.DemoSingleBreakFastActivity;
import com.menuz.R;
import com.menuz.Utils.MyToast;
import com.menuz.application.MenuZ;
import com.menuz.data.model.db.AddOnModel;
import com.menuz.data.model.db.ItemModel;
import com.menuz.data.model.db.ItemPreprationModel;
import com.menuz.data.model.db.MenuModel;
import com.menuz.data.model.db.OrderEmployeeModel;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.data.model.db.OrderTableModel;
import com.menuz.data.model.db.PreparationModel;
import com.menuz.data.model.db.PriceModel;
import com.menuz.data.model.db.PricevaluesModel;
import com.menuz.session.Session;
import com.menuz.ui.Order.adapter.CourseAdapter;
import com.menuz.ui.Order.adapter.ItemAdapter;
import com.menuz.ui.Order.adapter.KeyboardAdapter;
import com.menuz.ui.Order.adapter.MenuAdapter;
import com.menuz.ui.Order.adapter.PriceAdapter;
import com.menuz.ui.Order.model.CourseModel;
import com.menuz.ui.Order.model.KeyboardModel;
import com.menuz.ui.language.Language;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.menuz.Demo.DemoAddonFragment.getTimestamp;
import static com.menuz.application.MenuZ.getDataManager;
import static com.menuz.ui.currentorder.activity.EditEmployeeAndGuest.ACTIVITYEMPLOYEE;

public class NewOrderActivity extends AppCompatActivity implements View.OnClickListener, MenuAdapter.OnItemListener {
    List<OrderItemModel> selectedItemList = new ArrayList<>();
    int totalcost = 0;
    String quantity = "";
    private List<MenuModel> menuModelArrayList = new ArrayList<>();
    private List<ItemModel> ItemModelArraylist = new ArrayList<>();
    private RecyclerView menuRecycler, itemRecycler;
    private ItemModel itemModel;
    int selectedPosition;
    private TextView tv_quntity;
    private String itemID = "";
    private OrderTableModel orderTableModel = new OrderTableModel();
    private OrderEmployeeModel orderEmployeeModel = new OrderEmployeeModel();
    private TextView tvTableno;
    private String ORDERID;
    private TextView tvTablenoCountItem;
    private TextView txtNodata;
    private String OrderId = "";
    private TextView tvHeaderTitle;
    private String price = "";
    private String pricevalue = "";
    private String nuofguest = "";
    private String guestStatus = "";
    private String courseStatus = "";
    private String courseName = "";
    private String tableId="";
    private String employeename="";
    private String isUpdated="";
    private String navigation="";
    private TextView cartCountTxt;
    private Handler handler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLanguage();
        ORDERID = MenuZ.getInstance().getOrderId();
        Session session = new Session(NewOrderActivity.this);
        guestStatus = session.getGuest();
        courseStatus = session.getCourse();
        Intent intent = getIntent();
        if (intent != null) {
            OrderId = intent.getStringExtra("orderId");
            navigation = intent.getStringExtra("navigation");
            tableId = intent.getStringExtra("tableId");
            employeename = intent.getStringExtra("employeename");
            nuofguest = intent.getStringExtra("nuofguest");
            isUpdated = intent.getStringExtra("isUpdated");
        }
        setContentView(R.layout.activity_new_order);
        inItView();
    }

    @SuppressLint({"StaticFieldLeak", "SetTextI18n"})
    private void inItView() {
        //Shift View
        menuRecycler = findViewById(R.id.menuRecycler);
        txtNodata = findViewById(R.id.txtNodata);
        itemRecycler = findViewById(R.id.itemRecycler);

        tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        tvTablenoCountItem = findViewById(R.id.tvTablenoCountItem);
        tvTableno = findViewById(R.id.tvTableno);
        tvTableno.setVisibility(View.VISIBLE);
        LinearLayout ll_continue = findViewById(R.id.ll_continue);
        TextView tvIcon = findViewById(R.id.tvIcon);
        tvIcon.setVisibility(View.VISIBLE);
        RelativeLayout llPlus = findViewById(R.id.llPlus);
        llPlus.setOnClickListener(this);
        RelativeLayout llMinus = findViewById(R.id.llMinus);
        llMinus.setOnClickListener(this);
        LinearLayout llCount = findViewById(R.id.llCount);
        llCount.setVisibility(View.VISIBLE);
        ll_continue.setOnClickListener(this);
        ll_continue.setVisibility(View.VISIBLE);
        llCount.setOnClickListener(this);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        RelativeLayout rlCart = findViewById(R.id.rlCart);
        cartCountTxt = findViewById(R.id.cartCountTxt);
        TextView txtQuantity = findViewById(R.id.txtQuantity);
        txtQuantity.setVisibility(View.GONE);
        if (!navigation.equals("neworder")) {
            tvHeaderTitle.setText("Menu");
        }
        new Thread(() -> {
            selectedItemList = getDataManager().getAllorderItem(MenuZ.getInstance().getOrderId());
            for (OrderItemModel orderItemModel1 : selectedItemList) {
                if (!orderItemModel1.getCountPrice().equals("")){
                    totalcost += Integer.parseInt((orderItemModel1.getCountPrice()));
                }
            }
            if (navigation.equals("neworder")){
                int finalTotalcost = totalcost;
                handler.post(() -> cartCountTxt.setText("" + finalTotalcost));
            }else {
                handler.post(() -> {
                    llCount.setVisibility(View.GONE);
                    tvIcon.setVisibility(View.GONE);
                    ll_continue.setVisibility(View.GONE);
                    rlCart.setVisibility(View.GONE);
                    cartCountTxt.setVisibility(View.GONE);
                });

            }

        }).start();
        tv_quntity = findViewById(R.id.tv_quntity);
        tv_quntity.setVisibility(View.VISIBLE);
        tvHeaderTitle.setTextSize(16);
        rlCart.setVisibility(View.VISIBLE);
        tvTablenoCountItem.setVisibility(View.VISIBLE);
        rlCart.setOnClickListener(this);
        updateUi();
        updateMenuandItem();


    }

    private void updateMenuandItem() {
        new Thread(() -> {
            menuModelArrayList = getDataManager().getallMenu();
           // Collections.sort(menuModelArrayList, (o1, o2) -> o2.getGroupName().compareToIgnoreCase(o1.getGroupName()));
            handler.post(() -> {
                if (menuModelArrayList.size()>0){
                    menuModelArrayList.get(0).isSelect = true;
                }
                menuRecycler.setAdapter(new MenuAdapter(menuModelArrayList, NewOrderActivity.this, (int position) -> new Thread(() -> {
                    ItemModelArraylist.clear();
                    menuModelArrayList = getDataManager().getallMenu();
                    String groupId = menuModelArrayList.get(position).getGroupId();
                    String groupName = menuModelArrayList.get(position).getGroupName();

                    ItemModelArraylist = getDataManager().getallItem(groupId);

                    selectedItemList = getDataManager().getAllorderItem(MenuZ.getInstance().getOrderId());

                    handler.post(() -> {
                        if (ItemModelArraylist.size() == 0) {
                            txtNodata.setVisibility(View.VISIBLE);
                        } else {
                            txtNodata.setVisibility(View.GONE);
                        }
                        //Collections.sort(ItemModelArraylist, (p1, p2) -> p1.getItemName().compareTo(p2.getItemName()));
                        ItemAdapter itemAdapter = new ItemAdapter(groupName,NewOrderActivity.this, ItemModelArraylist, new ItemAdapter.OnItemClick() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void position(int position1) {
                                //new Thread(() -> getDataManager().clearPriceValueData()).start();
                                itemModel = ItemModelArraylist.get(position1);
                                itemID = ItemModelArraylist.get(position1).getItemId();
                                getData();
                            }

                            @Override
                            public void itemSelect() {
                            }

                            @Override
                            public void itemIncrease(int position1) {
                            }

                            @Override
                            public void itemDecrease(int position1) {
                            }
                        });

                        itemRecycler.setAdapter(itemAdapter);

                    });

                }).start()));
            });
        }).start();
        new Thread(() -> {
            menuModelArrayList = getDataManager().getallMenu();
            String groupName = null;
            if (menuModelArrayList.size()>0){
                String groupId = menuModelArrayList.get(0).getGroupId();
                groupName = menuModelArrayList.get(0).getGroupName();
                ItemModelArraylist = getDataManager().getallItem(groupId);
            }
            Collections.sort(ItemModelArraylist, (p1, p2) -> p1.getItemName().compareTo(p2.getItemName()));
            String finalGroupName = groupName;
            handler.post(() -> {
                ItemAdapter itemAdapter = new ItemAdapter(finalGroupName,NewOrderActivity.this, ItemModelArraylist, new ItemAdapter.OnItemClick() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void position(int position) {
                        //new Thread(() -> getDataManager().clearPriceValueData()).start();
                        itemModel = ItemModelArraylist.get(position);
                        itemID = ItemModelArraylist.get(position).getItemId();
                        getData();
                    }
                    @Override
                    public void itemSelect() {
                    }
                    @Override
                    public void itemIncrease(int position) {
                    }
                    @Override
                    public void itemDecrease(int position) {
                    }
                });
                itemRecycler.setAdapter(itemAdapter);
            });
        }).start();
    }

    @SuppressLint("SetTextI18n")
    private synchronized void getData() {
        try {
            JSONArray jsonArray1 = new JSONArray(itemModel.getPricevalues());
            List<PricevaluesModel> tempList = new ArrayList<>();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject = jsonArray1.getJSONObject(i);
                PricevaluesModel pricevaluesModel = new PricevaluesModel();
                pricevaluesModel.setPricevaluesId(jsonObject.getString("pricevaluesId"));
                pricevaluesModel.setPricevaluesKind(jsonObject.getString("pricevaluesKind"));
                pricevaluesModel.setPricevaluesPrice(jsonObject.getString("pricevaluesPrice"));
                pricevaluesModel.setPricevaluesPriceId(jsonObject.getString("pricevaluesPriceId"));
                tempList.add(pricevaluesModel);
            }
            new Thread(() -> {
                getDataManager().clearPriceValueData();
                getDataManager().inserPriceValue(tempList);
                //List<PriceModel> priceModels = getDataManager().loadPrice();

                List<PriceModel> priceModels = getDataManager().loadPriceData();
                // TODO: 12/1/19 changes
                handler.post(() -> {
                    if (navigation.equals("neworder")){
                        if (priceModels.size() > 0) {
                            popupQuantity(itemModel.getItemName());
                        } else {

                            if (guestStatus.equals("1")) {
                                popupGuest(tv_quntity.getText().toString(), itemModel.getItemName());
                            } else {
                                if (courseStatus.equals("1")) {
                                    popupCourse(itemModel.getItemName());
                                } else {
                                    insertOrder();
                                }
                            }
                        }
                    }else {
                        insertOrder();
                    }
                });
            }).start();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateUi() {

        if (navigation.equals("neworder")){
            new Thread(() -> {
                if (OrderId != null) {
                    ORDERID = OrderId;
                    orderEmployeeModel = getDataManager().getAllOrderEmployee(MenuZ.getInstance().getOrderId());
                    handler.post(() -> {
                        if (orderEmployeeModel != null)
                            tv_quntity.setText(orderEmployeeModel.getNuofguest());
                    });

                } else {
                    orderEmployeeModel = getDataManager().getAllOrderEmployee(MenuZ.getInstance().getOrderId());
                    handler.post(() -> {
                        if (orderEmployeeModel != null)
                            tv_quntity.setText(orderEmployeeModel.getNuofguest());
                    });
                }


            }).start();
        }else {
            tv_quntity.setVisibility(View.GONE);
        }

        if (navigation.equals("neworder")){
            new Thread(() -> {
                if (OrderId != null) {
                    ORDERID = OrderId;
                    orderTableModel = getDataManager().getallOrderTable(ORDERID);
                    handler.post(() -> {
                        if (orderTableModel != null) {
                            tvTableno.setText("Table");
                            tvTablenoCountItem.setText(orderTableModel.getTableId());
                        }else {
                            tvTablenoCountItem.setText(tableId);
                        }
                    });
                } else {
                    orderTableModel = getDataManager().getallOrderTable(ORDERID);
                    handler.post(() -> {
                        if (orderTableModel != null) {
                            tvTableno.setText("Table");
                            tvTablenoCountItem.setText(orderTableModel.getTableId());
                        }
                    });

                }

            }).start();
            tvHeaderTitle.setText("Guest");

        }else {
            tvTablenoCountItem.setVisibility(View.GONE);
            tvTableno.setVisibility(View.GONE);

        }
    }
    int finalTotalcost =0;
    @SuppressLint({"StaticFieldLeak", "SetTextI18n"})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.ll_continue:
                if (itemModel!=null){
                    assert OrderId != null;
                    ORDERID = OrderId;
                    new Thread(() -> {
                        OrderItemModel orderItemModel = new OrderItemModel();
                        orderItemModel.setItemAddonPrice(itemModel.getItemAddonPrice());
                        orderItemModel.setItemGroupId(itemModel.getItemGroupId());
                        orderItemModel.setItemId(itemModel.getItemId());
                        orderItemModel.setOrderId(ORDERID);
                        orderItemModel.setItemRemark("");
                        orderItemModel.setItemImage(itemModel.getItemImage());
                        orderItemModel.setPricevalue("");
                        orderItemModel.setNuofguest("");
                        orderItemModel.setItemPrimaryKey(getTimestamp());
                        orderItemModel.setPrice("");
                        orderItemModel.setCoursename(courseName);
                        orderItemModel.setCartSelected(false);
                        orderItemModel.setItemPreprationRemark("");
                        orderItemModel.setCountPrice("1");
                        orderItemModel.setItemPrice(itemModel.getItemPrice());
                        orderItemModel.setItemName(itemModel.getItemName());
                        orderItemModel.setPricevalues(itemModel.getPricevalues());
                        orderItemModel.setPreparations(itemModel.getPreparations());
                        getDataManager().insertOrderItem(orderItemModel);

                        List<OrderItemModel>  selectedItemList = getDataManager().getAllorderItem(MenuZ.getInstance().getOrderId());
                        for (OrderItemModel orderItemModel1 : selectedItemList) {
                            if (!orderItemModel1.getCountPrice().equals("")){
                                finalTotalcost=Integer.parseInt(orderItemModel1.getCountPrice());
                            }
                        }
                        handler.post(() -> cartCountTxt.setText("" + finalTotalcost));
                        //getDataManager().updateCart(true, ORDERID);

                    }).start();
                }
                break;

            case R.id.rlCart:

                Intent intentCart = new Intent(NewOrderActivity.this, CartDetailActivity.class);
                intentCart.putExtra("orderId",OrderId);
                startActivity(intentCart);
                finish();
                break;

            case R.id.llPlus:
                new Thread(() -> {
                    if (OrderId != null) {
                        ORDERID = OrderId;
                    }
                    int noofguest = Integer.parseInt(orderEmployeeModel.getNuofguest()) + 1;
                    getDataManager().updateOrder(ORDERID, String.valueOf(noofguest));
                    getDataManager().updateOrderinOrderEmployee(String.valueOf(noofguest), ORDERID);
                    orderEmployeeModel = getDataManager().getAllOrderEmployee(ORDERID);
                    orderEmployeeModel.setNuofguest(orderEmployeeModel.getNuofguest());
                    handler.post(() -> tv_quntity.setText("" + noofguest));
                }).start();

                break;

            case R.id.llMinus:
                new Thread(() -> {
                    ORDERID = MenuZ.getInstance().getOrderId();
                    if (OrderId != null) {
                        ORDERID = OrderId;
                    }
                    int noofguest = Integer.parseInt(orderEmployeeModel.getNuofguest()) - 1;
                    getDataManager().updateOrder(ORDERID, String.valueOf(noofguest));
                    getDataManager().updateOrderinOrderEmployee(String.valueOf(noofguest), ORDERID);
                    orderEmployeeModel = getDataManager().getAllOrderEmployee(ORDERID);
                    orderEmployeeModel.setNuofguest(orderEmployeeModel.getNuofguest());
                    if (orderEmployeeModel.getNuofguest().equals("0")) {
                        getDataManager().updateOrder(ORDERID, "1");
                        getDataManager().updateOrderinOrderEmployee("1", ORDERID);
                        orderEmployeeModel = getDataManager().getAllOrderEmployee(ORDERID);
                        orderEmployeeModel.setNuofguest(orderEmployeeModel.getNuofguest());
                    }
                    handler.post(() -> tv_quntity.setText("" + orderEmployeeModel.getNuofguest()));
                }).start();
                break;
        }
    }



    public void checkLanguage() {
        Session session = new Session(NewOrderActivity.this);
        String userselectedlanguage = session.getLanguage();
        switch (userselectedlanguage) {
            case "en":
                Language.SetLanguage(NewOrderActivity.this, "en");
                break;
            case "iw":
                Language.SetLanguage(NewOrderActivity.this, "iw");
                break;
            case "":
                Language.SetLanguage(NewOrderActivity.this, "en");
                break;
        }

    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void position(int position) {
        String groupId = menuModelArrayList.get(position).getGroupId();
        String groupName = menuModelArrayList.get(position).getGroupName();
        new Thread(() -> {
            ItemModelArraylist = getDataManager().getallItem(groupId);
            Collections.sort(ItemModelArraylist, (p1, p2) -> p1.getItemName().compareTo(p2.getItemName()));

            handler.post(() -> {
                ItemAdapter itemAdapter = new ItemAdapter(groupName,NewOrderActivity.this, ItemModelArraylist, new ItemAdapter.OnItemClick() {
                    @Override
                    public void position(int position1) {
                        itemModel = ItemModelArraylist.get(position1);
                        itemID = ItemModelArraylist.get(position).getItemId();
                        getData();
                    }

                    @Override
                    public void itemSelect() {
                    }

                    @Override
                    public void itemIncrease(int position1) {
                    }

                    @Override
                    public void itemDecrease(int position1) {

                    }
                });
                itemRecycler.setAdapter(itemAdapter);

            });
        }).start();


    }

    @SuppressLint("SetTextI18n")
    private void popupQuantity(String itemname) {
        final Dialog dialog = new Dialog(NewOrderActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popup_price);
        RecyclerView recyclerPrefix = dialog.findViewById(R.id.recyclerPrefix);
        TextView tvHeader = dialog.findViewById(R.id.tvHeader);
        TextView btnDone = dialog.findViewById(R.id.btnDone);
        tvHeader.setText(itemname + " " + "-" + "PriceList");
        new Thread(() -> {
            List<PriceModel> priceModels = getDataManager().loadPriceData();
            handler.post(() -> {
                recyclerPrefix.setLayoutManager(new LinearLayoutManager(NewOrderActivity.this));
                recyclerPrefix.setAdapter(new PriceAdapter(NewOrderActivity.this, priceModels, position -> {
                    price = priceModels.get(position).getPricevaluesPrice();
                    pricevalue = priceModels.get(position).getPricelistName();
                }));
            });
        }).start();
        btnDone.setOnClickListener(v -> {
             if (guestStatus.equals("1")){
                if (pricevalue.equals("")) {
                    showToast("Select PriceListName");
                } else {
                    popupGuest(tv_quntity.getText().toString().trim(), itemname);
                    dialog.dismiss();
                }
            }else if (pricevalue.equals("")){
                 showToast("Select PriceListName");
             } else if (courseStatus.equals("1")){
                dialog.dismiss();
                popupCourse(itemname);
            }else {
                 dialog.dismiss();
                 insertOrder();
            }
        });
        ImageView img_cancel = dialog.findViewById(R.id.img_cancel);

        img_cancel.setOnClickListener((View v) -> {
            pricevalue = "";
            price = "";
            dialog.dismiss();
        });
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    private void showToast(String msg) {
        if (!TextUtils.isEmpty(msg))
            MyToast.getInstance(NewOrderActivity.this).showAlert(msg);
    }

    /*Pop up for guest*/

    @SuppressLint("SetTextI18n")
    private void popupGuest(String guest, String itemname) {
        final Dialog dialog = new Dialog(NewOrderActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popup_guest);
        RecyclerView recyclerPrefix = dialog.findViewById(R.id.recyclerPrefix);
        TextView tvHeader = dialog.findViewById(R.id.tvHeader);
        TextView btnDone = dialog.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(v -> {

            if (nuofguest.equals("")) {
                showToast("Select guest");
            } else {
                //nuofguest="";
                dialog.dismiss();

                if (courseStatus.equals("1")) {
                    popupCourse(itemname);
                }else{

                    insertOrder();
                }
            }
        });
        tvHeader.setText(itemname + " " + "-" + "Choose Guest");
        ArrayList<KeyboardModel> arrayList = new ArrayList<>();
        int noofguest = Integer.parseInt(guest);
        KeyboardModel item;
        for (int i = 0; i < noofguest; i++) {
            item = new KeyboardModel();
            int itemName = i + 1;
            item.itemName = String.valueOf(itemName);
            item.isSelect = false;
            arrayList.add(item);

        }

        recyclerPrefix.setLayoutManager(new GridLayoutManager(NewOrderActivity.this, 5));
        recyclerPrefix.setAdapter(new KeyboardAdapter(arrayList, NewOrderActivity.this, position -> nuofguest = arrayList.get(position).itemName));
        ImageView img_cancel = dialog.findViewById(R.id.img_cancel);
        img_cancel.setOnClickListener((View v) -> {
            nuofguest = "";
            pricevalue = "";
            price = "";
            dialog.dismiss();
        });
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    /*pop up for course*/

    @SuppressLint("SetTextI18n")
    private void popupCourse(String itemname) {
        final Dialog dialog = new Dialog(NewOrderActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popup_price);
        RecyclerView recyclerPrefix = dialog.findViewById(R.id.recyclerPrefix);
        TextView tvHeader = dialog.findViewById(R.id.tvHeader);
        TextView btnDone = dialog.findViewById(R.id.btnDone);
        ArrayList<CourseModel> arrayList = new ArrayList<>();
        CourseModel item;
        for (int i = 0; i < 5; i++) {
            item = new CourseModel();
            switch (i) {
                case 0:
                    item.itemName = "Immediate";
                    item.isSelect = false;
                    item.itemImg=R.drawable.ic_flash;
                    item.itemImgActive=R.drawable.inactive_immediate;

                    break;
                case 1:
                    item.itemName = "First Course";
                    item.isSelect = false;
                    item.itemImg=R.drawable.inactive_first_course;
                    item.itemImgActive=R.drawable.active_first_course;

                    break;
                case 2:
                    item.itemName = "Second Course";
                    item.itemImg=R.drawable.inactive_second_course;
                    item.itemImgActive=R.drawable.active_second_cours;
                    item.isSelect = false;
                    break;

                case 3:
                    item.itemName = "Dessert";
                    item.itemImg=R.drawable.inactive_dessert;
                    item.itemImgActive=R.drawable.active_dessert;

                    item.isSelect = false;
                    break;

                case 4:
                    item.itemName = "Take away";
                    item.itemImg=R.drawable.inactive_take_away;
                    item.itemImgActive=R.drawable.active_take_away;
                    item.isSelect = false;
                    break;


            }
            arrayList.add(item);
        }

        btnDone.setOnClickListener(v -> {
            if (courseName.equals("")) {
                showToast("Select Course");
            } else{
                dialog.dismiss();
                insertOrder();
            }



        });
        tvHeader.setText(itemname + " " + "-" + "Course");
        recyclerPrefix.setLayoutManager(new LinearLayoutManager(NewOrderActivity.this));
        recyclerPrefix.setAdapter(new CourseAdapter(arrayList, NewOrderActivity.this, position -> courseName = arrayList.get(position).itemName));

        ImageView img_cancel = dialog.findViewById(R.id.img_cancel);

        img_cancel.setOnClickListener((View v) -> {
            courseName = "";
            nuofguest = "";
            dialog.dismiss();
        });
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ACTIVITYEMPLOYEE) {
            updateUi();
        }
    }

    @SuppressLint("SetTextI18n")
    private void insertOrder(){
        new Thread(() -> {
            List<AddOnModel> addOnModelArrayList = getDataManager().loadallAddonByitems(itemModel.getItemId());
            JSONArray jsonArray;
            ArrayList<String>myList=new ArrayList<>();
            List<PreparationModel> preparationModels = new ArrayList<>();
            try {
                jsonArray = new JSONArray(itemModel.getPreparations());
                for (int l = 0; l < jsonArray.length(); l++) {
                    String jsonObject = jsonArray.getString(l);
                    myList.add(jsonObject);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            preparationModels.clear();
            preparationModels = getDataManager().getallDatabtId(myList);

            for (int k = 0; k < preparationModels.size(); k++) {
                // List<PreparationModel> preparationModelList = getDataManager().getallDatabtId(myList.get(k));
                PreparationModel preparationModel = preparationModels.get(k);
                ItemPreprationModel itemPreprationModel = new ItemPreprationModel();
                itemPreprationModel.setItemId(itemModel.getItemId());
                itemPreprationModel.setItemName(itemModel.getItemName());
                itemPreprationModel.setPreparationName(preparationModel.getPreparationName());
                itemPreprationModel.setPreparationIsPrefixed(preparationModel.getPreparationIsPrefixed());
                itemPreprationModel.setPreparationId(preparationModel.getPreparationId());
                getDataManager().insertItemPrep(itemPreprationModel);

            }


            List<ItemPreprationModel> preparationModalArrayList = getDataManager().loadallPrepByItemId(itemModel.getItemId());
            handler.post(() -> {
                if (addOnModelArrayList.size()==0&&preparationModalArrayList.size()!=0){
                    Intent intent = new Intent(NewOrderActivity.this, DemoSingleBreakFastActivity.class);
                    intent.putExtra("itemdata", itemModel);
                    intent.putExtra("itemID", itemID);
                    intent.putExtra("pricevalue",pricevalue);
                    intent.putExtra("navigation",navigation);
                    intent.putExtra("nuofguest",nuofguest);
                    intent.putExtra("employeename", employeename);
                    intent.putExtra("tableId",tableId);
                    intent.putExtra("isUpdated",isUpdated);
                    intent.putExtra("edit","");
                    OrderItemModel orderItemModel=new OrderItemModel();
                    intent.putExtra("orderItemModel",orderItemModel);
                    intent.putExtra("price",price);
                    intent.putExtra("courseName",courseName);
                    String from=getIntent().getStringExtra("from");
                    if (from.equals("")){
                        intent.putExtra("from", "cart");
                    }else {
                        intent.putExtra("from", "order");
                    }
                    intent.putExtra("orderId", OrderId);
                    intent.putExtra("quantity", quantity);
                    intent.putExtra("Position", selectedPosition);
                    //Todo done by HS
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    //finish();
                }else if (preparationModalArrayList.size()==0&&addOnModelArrayList.size()!=0){
                    Intent intent = new Intent(NewOrderActivity.this, DemoSingleBreakFastActivity.class);
                    intent.putExtra("itemdata", itemModel);
                    intent.putExtra("itemID", itemID);
                    intent.putExtra("pricevalue",pricevalue);
                    intent.putExtra("nuofguest",nuofguest);
                    intent.putExtra("tableId",tableId);
                    intent.putExtra("isUpdated",isUpdated);
                    intent.putExtra("navigation",navigation);
                    intent.putExtra("edit","");
                    OrderItemModel orderItemModel=new OrderItemModel();
                    intent.putExtra("orderItemModel",orderItemModel);
                    intent.putExtra("price",price);
                    intent.putExtra("courseName",courseName);
                    intent.putExtra("employeename", employeename);
                    String from=getIntent().getStringExtra("from");
                    if (from.equals("")){
                        intent.putExtra("from", "cart");
                    }else {
                        intent.putExtra("from", "order");
                    }

                    intent.putExtra("orderId", OrderId);
                    intent.putExtra("quantity", quantity);
                    intent.putExtra("Position", selectedPosition);
                    //Todo done by HS
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                   // finish();
                }else if (preparationModalArrayList.size()!=0){
                    Intent intent = new Intent(NewOrderActivity.this, DemoSingleBreakFastActivity.class);
                    intent.putExtra("itemdata", itemModel);
                    intent.putExtra("itemID", itemID);
                    intent.putExtra("navigation",navigation);
                    intent.putExtra("employeename", employeename);
                    intent.putExtra("pricevalue",pricevalue);
                    intent.putExtra("isUpdated",isUpdated);
                    intent.putExtra("nuofguest",nuofguest);
                    intent.putExtra("tableId",tableId);
                    intent.putExtra("edit","");
                    OrderItemModel orderItemModel=new OrderItemModel();
                    intent.putExtra("orderItemModel",orderItemModel);
                    intent.putExtra("price",price);
                    intent.putExtra("courseName",courseName);
                    String from=getIntent().getStringExtra("from");
                    if (from.equals("")){
                        intent.putExtra("from", "cart");
                    }else {
                        intent.putExtra("from", "order");
                    }

                    intent.putExtra("orderId", OrderId);
                    intent.putExtra("quantity", quantity);
                    intent.putExtra("Position", selectedPosition);
                    //Todo done by HS
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                   // finish();
                }
            });
        }).start();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
/*
        Intent intent=new Intent(NewOrderActivity.this,SelectWaiterActivity.class);
        startActivity(intent);
*/
    }
}
