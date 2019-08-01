package com.menuz.Demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.application.MenuZ;
import com.menuz.base.BaseActivity;
import com.menuz.data.model.db.AddOnModel;
import com.menuz.data.model.db.AdddonChildModel;
import com.menuz.data.model.db.AddonPreprationModel;
import com.menuz.data.model.db.ItemModel;
import com.menuz.data.model.db.ItemPreprationModel;
import com.menuz.data.model.db.OrderAddOnChild;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.data.model.db.OrderPreparationAddonModel;
import com.menuz.data.model.db.OrderPreparationModel;
import com.menuz.data.model.db.PreparationModel;
import com.menuz.ui.Order.activity.CartDetailActivity;
import com.menuz.ui.Order.adapter.ViewPagerAdapter;
import com.menuz.ui.currentorder.activity.OrderDetailActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.menuz.Demo.DemoAddonFragment.getTimestamp;

public class DemoSingleBreakFastActivity extends BaseActivity implements View.OnClickListener, GetAddonandPrepSelectedListener {
    public int position;
    public ItemModel itemModel = new ItemModel();
    public String itemID = "";
    public String itemName = "";
    public String quantity = "";
    public String edit = "";
    public String remark = "";
    public String orderId = "";
    public String isUpdated = "";
    public TextView cartCountTxt;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    private DemoAddonFragment demoAddonFragment;
    private TextView txtAddon;
    private TextView txtPrep;
    public LinearLayout llButton;
    public String pricevalue = "";
    private int currentTabId;
    public String nuofguest = "";
    public String navigation = "";
    public String tableId = "";
    public String price = "";
    int totalcost = 0;
    public String courseName = "";
    public String employeename = "";
    private OrderItemModel orderItemModel = new OrderItemModel();
    HashMap<String, AdddonChildModel> temAddonChildModelHashMap = new HashMap<>();
    HashMap<String, AddOnModel> addonList = new HashMap<>();
    HashMap<String, AddonPreprationModel> tempAddonPrep = new HashMap<>();
    HashMap<String, ItemPreprationModel> tempItemPrep = new HashMap<>();
    private Handler handler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null) {
            position = intent.getIntExtra("feedPosition", 0);
            itemID = intent.getStringExtra("itemID");
            navigation = intent.getStringExtra("navigation");
            tableId = intent.getStringExtra("tableId");
            itemName = intent.getStringExtra("itemName");
            employeename = intent.getStringExtra("employeename");
            orderItemModel = intent.getParcelableExtra("orderItemModel");
            quantity = intent.getStringExtra("quantity");
            pricevalue = intent.getStringExtra("pricevalue");
            nuofguest = intent.getStringExtra("nuofguest");
            courseName = intent.getStringExtra("courseName");
            price = intent.getStringExtra("price");
            edit = intent.getStringExtra("edit");
            orderId = intent.getStringExtra("orderId");
            itemModel = (ItemModel) intent.getSerializableExtra("itemdata");
        }

        setContentView(R.layout.activity_demo_single_break_fast);
        initView();
        currentTabId = R.id.llAddon;
    }

    @Override
    public void onClick(View v) {
        int id;
        switch (id = v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;

            case R.id.rlCart:
                if (getIntent() != null) {
                    if (getIntent().getStringExtra("from").equals("order")) {
                        Intent intent = new Intent(getApplicationContext(), OrderDetailActivity.class);
                        intent.putExtra("orderId", orderId);
                        intent.putExtra("employeename", "");
                        intent.putExtra("employeeId", "");
                        intent.putExtra("tableId", tableId);
                        intent.putExtra("employeename", employeename);
                        intent.putExtra("nuofguest", "");
                        intent.putExtra("itemdata", itemModel);
                        startActivityForResult(intent,101);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), CartDetailActivity.class);
                        intent.putExtra("orderId", "");
                        intent.putExtra("itemdata", itemModel);
                        startActivity(intent);
                    }
                }
                //finish();
                break;

            case R.id.txtAddon:
                if (currentTabId != R.id.llAddon) {
                    txtAddon.setTextColor(getResources().getColor(R.color.white));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        txtAddon.setBackground(getResources().getDrawable(R.drawable.button_active_addon));
                        txtPrep.setBackground(getResources().getDrawable(R.drawable.button_inactive_prep));
                    }
                    //   txtPrep.setBackground(getResources().getDrawable(R.drawable.button_white));
                    txtPrep.setTextColor(getResources().getColor(R.color.colorPrimary));
                     demoAddonFragment= DemoAddonFragment.newInstance(itemID, orderItemModel, edit, temAddonChildModelHashMap, tempAddonPrep, llButton, navigation, isUpdated);
                    addFragment(demoAddonFragment, false);

                }
                break;

            case R.id.txtPrep:

                if (currentTabId != R.id.llPrep) {
                    txtAddon.setTextColor(getResources().getColor(R.color.colorPrimary));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        txtPrep.setBackground(getResources().getDrawable(R.drawable.button_active_prep));
                        txtAddon.setBackground(getResources().getDrawable(R.drawable.button_inactive_addon));
                    }
                    txtPrep.setTextColor(getResources().getColor(R.color.white));
                    addFragment(DemoPreprationFragment.newInstance(itemModel, orderItemModel, edit, tempItemPrep, navigation), false);

                }
                break;

            case R.id.rlAddCart:
                if (edit.equals("edit")) {
                    deleteCart(orderItemModel);
                    doAddCart(temAddonChildModelHashMap, tempAddonPrep, tempItemPrep);

                } else {
                    doAddCart(temAddonChildModelHashMap, tempAddonPrep, tempItemPrep);


                }
                break;

            case R.id.rlCancel:
                deleteCart(orderItemModel);
                onBackPressed();
                // Toast.makeText(this, "Under Development according to new flow", Toast.LENGTH_SHORT).show();
                break;
        }

        currentTabId = id;
    }


    public void alertDialog(String msg, Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Alert")
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> new Thread(() -> {
                    List<AddOnModel> addOnModelArrayList;
                    if (!edit.equals("edit")){
                        addOnModelArrayList = getDataManager().loadallAddonByitems(itemID);

                    }else {
                        addOnModelArrayList = getDataManager().loadallAddonByitems(orderItemModel.getItemId());
                    }

                    List<AdddonChildModel>adddonChildModelArrayList = new ArrayList<>();


                    for (int i = 0; i < addOnModelArrayList.size(); i++) {
                        if (addOnModelArrayList.get(i).getAddonsGroupIsMandatory().equals("1")){
                            addOnModelArrayList.get(i).isSelect=true;
                            String addOnGroupId=addOnModelArrayList.get(i).getAddonsGroupId();
                           String addonItemid = addOnModelArrayList.get(position).getAddOnItemID();
                           adddonChildModelArrayList = getDataManager().loadallAddonsByitemId(addOnGroupId, addonItemid);
                                if (addOnModelArrayList.get(i).getAddonsGroupMax().equals("0")) {
                                    addonGroupname=addOnModelArrayList.get(i).getAddonsGroupName();


                                }
                            }




                    }

                    if (demoAddonFragment.addOnAdapter != null){

                        List<AdddonChildModel> finalAdddonChildModelArrayList = adddonChildModelArrayList;
                        handler.post(() -> {
                            demoAddonFragment.addOnChildAdapter.setItems(finalAdddonChildModelArrayList);
                            demoAddonFragment.addOnAdapter.setAddOnList(addOnModelArrayList);
                            demoAddonFragment.addOnAdapter.notifyDataSetChanged();
                            demoAddonFragment.addOnChildAdapter.notifyDataSetChanged();
                        });
                    }

                }).start())


                .show();

    }

    String addonGroupname = "";




    /*Delete Items*/

    private void deleteCart(OrderItemModel orderItemModel) {
        new Thread(() -> {
            getDataManager().DeleteItem(orderItemModel.getItemPrimaryKey());
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

        }).start();

    }

    boolean isSelectAddonGroup=false;

    /*Prepare add update cart*/

    private void doAddCart(HashMap<String, AdddonChildModel> temAddonChildModelHashMap, HashMap<String, AddonPreprationModel> addonPreprationModelCollection, HashMap<String, ItemPreprationModel> itemPreprationModelCollection) {
        new Thread(() -> {
            List<AddOnModel> addOnModelArrayList;
            if (!edit.equals("edit")){
               addOnModelArrayList = getDataManager().loadallAddonByitems(itemID);

            }else {
             addOnModelArrayList = getDataManager().loadallAddonByitems(orderItemModel.getItemId());
            }


            for (int i = 0; i < addOnModelArrayList.size(); i++) {
                AddOnModel addOnModel = addOnModelArrayList.get(i);
                if (addOnModel.getAddonsGroupIsMandatory().equals("1")){
                    isSelectAddonGroup=true;
                 //   break;
                }


            }

            if (isSelectAddonGroup){
                boolean isSelected=false;
                List<AddOnModel> list = new ArrayList<>(addonList.values());

                for (int i = 0; i < list.size(); i++) {
                    AddOnModel addOnModel = list.get(i);
                    for (AdddonChildModel addonChild:temAddonChildModelHashMap.values()) {
                        if (addOnModel.getAddonsGroupIsMandatory().equals("1")){
                            if (addOnModel.getAddonsGroupId().equals(addonChild.getAddOnItemIdchild())){
                                isSelected=true;
                               // break;
                            }
                        }
                    }


                }

                if (!isSelected){
                    handler.post(() -> alertDialog("You must select mandatory addon",DemoSingleBreakFastActivity.this));
                }else {
                    OrderItemModel orderItemModel1 = new OrderItemModel();
                    if (edit.equals("edit")) {
                        orderItemModel1.setItemAddonPrice(orderItemModel.getItemAddonPrice());
                        orderItemModel1.setItemGroupId(orderItemModel.getItemGroupId());
                        orderItemModel1.setItemId(orderItemModel.getItemId());
                        orderItemModel1.setOrderId(orderId);
                        orderItemModel1.setItemRemark(remark);
                        orderItemModel1.setItemPrimaryKey(getTimestamp());
                        orderItemModel1.setItemImage(orderItemModel.getItemImage());
                        orderItemModel1.setPricevalue(pricevalue);
                        orderItemModel1.setNuofguest(nuofguest);
                        orderItemModel1.setItemAutoID(orderItemModel.getItemAutoID());
                        orderItemModel1.setPrice(price);
                        orderItemModel1.setCoursename(courseName);
                        orderItemModel1.setCartSelected(false);
                        orderItemModel1.setItemPreprationRemark("");
                        orderItemModel1.setCountPrice("1");
                        orderItemModel1.setItemPrice(orderItemModel.getItemPrice());
                        orderItemModel1.setItemName(orderItemModel.getItemName());
                        orderItemModel1.setPricevalues(orderItemModel.getPricevalues());
                        orderItemModel1.setPreparations(orderItemModel.getPreparations());
                        getDataManager().insertOrderItem(orderItemModel1);
                    } else {
                        //orderItemModel1 = new OrderItemModel();
                        orderItemModel1.setItemAddonPrice(itemModel.getItemAddonPrice());
                        orderItemModel1.setItemGroupId(itemModel.getItemGroupId());
                        orderItemModel1.setItemId(itemModel.getItemId());
                        orderItemModel1.setOrderId(orderId);
                        orderItemModel1.setItemRemark(remark);
                        orderItemModel1.setItemPrimaryKey(getTimestamp());
                        orderItemModel1.setItemImage(itemModel.getItemImage());
                        orderItemModel1.setPricevalue(pricevalue);
                        orderItemModel1.setItemAutoID("0");
                        orderItemModel1.setNuofguest(nuofguest);
                        orderItemModel1.setPrice(price);
                        orderItemModel1.setCoursename(courseName);
                        orderItemModel1.setCartSelected(false);
                        orderItemModel1.setItemPreprationRemark("");
                        orderItemModel1.setCountPrice("1");
                        orderItemModel1.setItemPrice(itemModel.getItemPrice());
                        orderItemModel1.setItemName(itemModel.getItemName());
                        orderItemModel1.setPricevalues(itemModel.getPricevalues());
                        orderItemModel1.setPreparations(itemModel.getPreparations());
                        getDataManager().insertOrderItem(orderItemModel1);
                    }



                    /*insert addongroup*/
                    if (edit.equals("edit")) {
                        OrderAddOnChild orderAddOnChild = new OrderAddOnChild();
                        for (AdddonChildModel addonChiildModel : temAddonChildModelHashMap.values()) {
                            assert addonChiildModel != null;
                            assert addonChiildModel.getAddonPrice() != null;
                            orderAddOnChild.setItemPrimaryKey(orderItemModel1.getItemPrimaryKey());
                            orderAddOnChild.setAddOnPrimaryKey(getTimestamp());
                            orderAddOnChild.setAddonChoiceID(addonChiildModel.getAddOnItemIdchild());
                            orderAddOnChild.setAddonAutoID(addonChiildModel.getAddonAutoID());
                            orderAddOnChild.setItemIdAddon(addonChiildModel.getItemIdAddon());
                            orderAddOnChild.setAddonGroupId(addonChiildModel.getAddonGroupId());
                            orderAddOnChild.setAddonRemark(addonChiildModel.getAddonRemark());
                            orderAddOnChild.setAddonsGroupId(addonChiildModel.getAddOnItemIdchild());
                            orderAddOnChild.setAddonId(addonChiildModel.getAddonId());
                            orderAddOnChild.setOrderId(orderId);
                            orderAddOnChild.setAddonName(addonChiildModel.getAddonName());
                            orderAddOnChild.setAddonPrice(addonChiildModel.getAddonPrice());
                            orderAddOnChild.setItemPrice(orderItemModel1.getItemPrice());
                            orderAddOnChild.setPreparations(addonChiildModel.getPreparations());
                            getDataManager().insertAddonChild(orderAddOnChild);
                        }


                    } else {
                        OrderAddOnChild orderAddOnChild = new OrderAddOnChild();
                        for (AdddonChildModel addonChiildModel : temAddonChildModelHashMap.values()) {
                            assert addonChiildModel != null;
                            assert addonChiildModel.getAddonPrice() != null;
                            if (orderId.equals("")) {
                                orderAddOnChild.setOrderId(MenuZ.getInstance().getOrderId());

                            } else {
                                orderAddOnChild.setOrderId(orderId);
                            }

                            orderAddOnChild.setItemPrimaryKey(orderItemModel1.getItemPrimaryKey());
                            orderAddOnChild.setAddOnPrimaryKey(getTimestamp());
                            orderAddOnChild.setAddonAutoID("0");

                            orderAddOnChild.setItemIdAddon(addonChiildModel.getItemIdAddon());
                            orderAddOnChild.setAddonGroupId(addonChiildModel.getAddonGroupId());
                            orderAddOnChild.setAddonRemark(addonChiildModel.getAddonRemark());
                            orderAddOnChild.setAddonsGroupId(addonChiildModel.getAddOnItemIdchild());
                            orderAddOnChild.setAddonId(addonChiildModel.getAddonId());
                            orderAddOnChild.setAddonName(addonChiildModel.getAddonName());
                            orderAddOnChild.setAddonPrice(addonChiildModel.getAddonPrice());
                            orderAddOnChild.setItemPrice(orderItemModel1.getItemPrice());
                            orderAddOnChild.setPreparations(addonChiildModel.getPreparations());
                            getDataManager().insertAddonChild(orderAddOnChild);
                        }

                    }


                    if (addonPreprationModelCollection != null) {
                        if (edit.equals("edit")) {
                            for (AddonPreprationModel addonPrep : addonPreprationModelCollection.values()) {
                                OrderPreparationAddonModel orderPreparationAddonModel = new OrderPreparationAddonModel();
                                orderPreparationAddonModel.setItemPrimaryKey(orderItemModel1.getItemPrimaryKey());
                                orderPreparationAddonModel.setOrderPrepAddOnPrimaryKey(getTimestamp());

                                orderPreparationAddonModel.setOrderId(orderId);
                                orderPreparationAddonModel.setPreparationId(addonPrep.getPreparationId());
                                orderPreparationAddonModel.setPreparationIsPrefixed(addonPrep.getPreparationIsPrefixed());
                                orderPreparationAddonModel.setAddonId(addonPrep.getAddonId());
                                orderPreparationAddonModel.setItemRemark(addonPrep.getItemRemark());
                                orderPreparationAddonModel.setItemIdAddon(addonPrep.getItemIdAddon());
                                orderPreparationAddonModel.setAddonsGroupId(addonPrep.getAddonsGroupId());
                                orderPreparationAddonModel.setPrefixName(addonPrep.getPrefixName());
                                orderPreparationAddonModel.setPreparationName(addonPrep.getPreparationName());
                                orderPreparationAddonModel.setPreparationIsPrefixed(addonPrep.getPreparationIsPrefixed());
                                /*Insert addonprepration*/
                                getDataManager().insertOrderAddonPreparation(orderPreparationAddonModel);
                            }
                        } else {
                            for (AddonPreprationModel addonPrep : addonPreprationModelCollection.values()) {
                                OrderPreparationAddonModel orderPreparationAddonModel = new OrderPreparationAddonModel();
                                orderPreparationAddonModel.setItemPrimaryKey(orderItemModel1.getItemPrimaryKey());
                                orderPreparationAddonModel.setOrderPrepAddOnPrimaryKey(getTimestamp());
                                orderPreparationAddonModel.setOrderId(MenuZ.getInstance().getOrderId());
                                orderPreparationAddonModel.setAddonId(addonPrep.getAddonId());
                                orderPreparationAddonModel.setPreparationId(addonPrep.getPreparationId());
                                orderPreparationAddonModel.setPreparationIsPrefixed(addonPrep.getPreparationIsPrefixed());

                                orderPreparationAddonModel.setItemRemark(addonPrep.getItemRemark());
                                orderPreparationAddonModel.setItemIdAddon(addonPrep.getItemIdAddon());
                                orderPreparationAddonModel.setAddonsGroupId(addonPrep.getAddonsGroupId());
                                orderPreparationAddonModel.setPrefixName(addonPrep.getPrefixName());
                                orderPreparationAddonModel.setPreparationName(addonPrep.getPreparationName());
                                orderPreparationAddonModel.setPreparationIsPrefixed(addonPrep.getPreparationIsPrefixed());
                                /*Insert addonprepration*/
                                getDataManager().insertOrderAddonPreparation(orderPreparationAddonModel);
                            }

                        }


                    }


                    /*insert item prep*/

                    if (itemPreprationModelCollection != null) {
                        for (ItemPreprationModel itemPreprationModel : itemPreprationModelCollection.values()) {
                            OrderPreparationModel orderPreparationModel = new OrderPreparationModel();
                            if (edit.equals("edit")) {
                                orderPreparationModel.setItemId(orderItemModel1.getItemId());
                                orderPreparationModel.setItemPrimaryKey(orderItemModel1.getItemPrimaryKey());
                                orderPreparationModel.setItemRemark(itemPreprationModel.getItemRemark());
                                //orderPreparationModel.setItemRemark(orderItemModel.getItemRemark());
                                orderPreparationModel.setOrderId(orderId);
                                orderPreparationModel.setItemName(orderItemModel1.getItemName());

                                orderPreparationModel.setPrefixName(itemPreprationModel.getPrefixName());
                                orderPreparationModel.setPreparationName(itemPreprationModel.getPreparationName());
                                orderPreparationModel.setPreparationIsPrefixed(itemPreprationModel.getPreparationIsPrefixed());
                                getDataManager().insertOrderPrearation(orderPreparationModel);
                            } else {
                                orderPreparationModel.setItemId(itemModel.getItemId());
                                orderPreparationModel.setItemPrimaryKey(orderItemModel1.getItemPrimaryKey());
                                orderPreparationModel.setItemRemark(itemPreprationModel.getItemRemark());
                                //orderPreparationModel.setItemRemark(orderItemModel.getItemRemark());
                                orderPreparationModel.setOrderId(orderId);
                                orderPreparationModel.setItemName(itemModel.getItemName());
                                orderPreparationModel.setPrefixName(itemPreprationModel.getPrefixName());
                                orderPreparationModel.setPreparationName(itemPreprationModel.getPreparationName());
                                orderPreparationModel.setPreparationIsPrefixed(itemPreprationModel.getPreparationIsPrefixed());
                                getDataManager().insertOrderPrearation(orderPreparationModel);
                            }

                        }

                    }

                    if (temAddonChildModelHashMap.size() != 0) {
                        if (orderItemModel != null) {
                            List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(orderItemModel1.getItemPrimaryKey(), orderItemModel.getOrderId());
                            if (cartSeltedAddonList.size() == 0) {

                                if (orderItemModel1.getNuofguest() != null) {
                                    if (edit.equals("edit")) {
                                        getDataManager().updatecount(orderItemModel1.getNuofguest(), orderItemModel1.getItemId());
                                    }


                                } else {
                                    getDataManager().updatecount("1", orderItemModel1.getItemId());

                                }


                            } else {
                                getDataManager().updatecount("1", orderItemModel1.getItemId());
                            }


                        }

                    }

                    handler.post(() -> {
                        if (getIntent() != null) {
                            if (getIntent().getStringExtra("from").equals("order")) {
                                Intent intent = new Intent(getApplicationContext(), OrderDetailActivity.class);
                                intent.putExtra("orderId", orderId);
                                intent.putExtra("employeename", "");
                                intent.putExtra("employeeId", "");
                                intent.putExtra("isUpdated", isUpdated);
                                intent.putExtra("tableId", tableId);
                                intent.putExtra("employeename", employeename);
                                intent.putExtra("nuofguest", "");
                                intent.putExtra("itemdata", itemModel);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(getApplicationContext(), CartDetailActivity.class);
                                intent.putExtra("orderId", "");
                                intent.putExtra("itemdata", itemModel);
                                startActivity(intent);
                            }
                        }

                    });              }

                /*else {
                    handler.post(() -> alertDialog("You must Select Mandatory addon", this));

                }*/


            }else {
                OrderItemModel orderItemModel1 = new OrderItemModel();
                if (edit.equals("edit")) {
                    orderItemModel1.setItemAddonPrice(orderItemModel.getItemAddonPrice());
                    orderItemModel1.setItemGroupId(orderItemModel.getItemGroupId());
                    orderItemModel1.setItemId(orderItemModel.getItemId());
                    orderItemModel1.setOrderId(orderId);
                    orderItemModel1.setItemRemark(remark);
                    orderItemModel1.setItemPrimaryKey(getTimestamp());
                    orderItemModel1.setItemImage(orderItemModel.getItemImage());
                    orderItemModel1.setPricevalue(pricevalue);
                    orderItemModel1.setNuofguest(nuofguest);
                    orderItemModel1.setItemAutoID(orderItemModel.getItemAutoID());
                    orderItemModel1.setPrice(price);
                    orderItemModel1.setCoursename(courseName);
                    orderItemModel1.setCartSelected(false);
                    orderItemModel1.setItemPreprationRemark("");
                    orderItemModel1.setCountPrice("1");
                    orderItemModel1.setItemPrice(orderItemModel.getItemPrice());
                    orderItemModel1.setItemName(orderItemModel.getItemName());
                    orderItemModel1.setPricevalues(orderItemModel.getPricevalues());
                    orderItemModel1.setPreparations(orderItemModel.getPreparations());
                    getDataManager().insertOrderItem(orderItemModel1);
                } else {
                    //orderItemModel1 = new OrderItemModel();
                    orderItemModel1.setItemAddonPrice(itemModel.getItemAddonPrice());
                    orderItemModel1.setItemGroupId(itemModel.getItemGroupId());
                    orderItemModel1.setItemId(itemModel.getItemId());
                    orderItemModel1.setOrderId(orderId);
                    orderItemModel1.setItemRemark(remark);
                    orderItemModel1.setItemPrimaryKey(getTimestamp());
                    orderItemModel1.setItemImage(itemModel.getItemImage());
                    orderItemModel1.setPricevalue(pricevalue);
                    orderItemModel1.setItemAutoID("0");
                    orderItemModel1.setNuofguest(nuofguest);
                    orderItemModel1.setPrice(price);
                    orderItemModel1.setCoursename(courseName);
                    orderItemModel1.setCartSelected(false);
                    orderItemModel1.setItemPreprationRemark("");
                    orderItemModel1.setCountPrice("1");
                    orderItemModel1.setItemPrice(itemModel.getItemPrice());
                    orderItemModel1.setItemName(itemModel.getItemName());
                    orderItemModel1.setPricevalues(itemModel.getPricevalues());
                    orderItemModel1.setPreparations(itemModel.getPreparations());
                    getDataManager().insertOrderItem(orderItemModel1);
                }



                /*insert addongroup*/
                if (edit.equals("edit")) {
                    OrderAddOnChild orderAddOnChild = new OrderAddOnChild();
                    for (AdddonChildModel addonChiildModel : temAddonChildModelHashMap.values()) {
                        assert addonChiildModel != null;
                        assert addonChiildModel.getAddonPrice() != null;
                        orderAddOnChild.setItemPrimaryKey(orderItemModel1.getItemPrimaryKey());
                        orderAddOnChild.setAddOnPrimaryKey(getTimestamp());
                        orderAddOnChild.setAddonChoiceID(addonChiildModel.getAddOnItemIdchild());
                        orderAddOnChild.setAddonAutoID(addonChiildModel.getAddonAutoID());
                        orderAddOnChild.setItemIdAddon(addonChiildModel.getItemIdAddon());
                        orderAddOnChild.setAddonGroupId(addonChiildModel.getAddonGroupId());
                        orderAddOnChild.setAddonRemark(addonChiildModel.getAddonRemark());
                        orderAddOnChild.setAddonsGroupId(addonChiildModel.getAddOnItemIdchild());
                        orderAddOnChild.setAddonId(addonChiildModel.getAddonId());
                        orderAddOnChild.setOrderId(orderId);
                        orderAddOnChild.setAddonName(addonChiildModel.getAddonName());
                        orderAddOnChild.setAddonPrice(addonChiildModel.getAddonPrice());
                        orderAddOnChild.setItemPrice(orderItemModel1.getItemPrice());
                        orderAddOnChild.setPreparations(addonChiildModel.getPreparations());
                        getDataManager().insertAddonChild(orderAddOnChild);
                    }


                } else {
                    OrderAddOnChild orderAddOnChild = new OrderAddOnChild();
                    for (AdddonChildModel addonChiildModel : temAddonChildModelHashMap.values()) {
                        assert addonChiildModel != null;
                        assert addonChiildModel.getAddonPrice() != null;
                        if (orderId.equals("")) {
                            orderAddOnChild.setOrderId(MenuZ.getInstance().getOrderId());

                        } else {
                            orderAddOnChild.setOrderId(orderId);
                        }

                        orderAddOnChild.setItemPrimaryKey(orderItemModel1.getItemPrimaryKey());
                        orderAddOnChild.setAddOnPrimaryKey(getTimestamp());
                        orderAddOnChild.setAddonAutoID("0");

                        orderAddOnChild.setItemIdAddon(addonChiildModel.getItemIdAddon());
                        orderAddOnChild.setAddonGroupId(addonChiildModel.getAddonGroupId());
                        orderAddOnChild.setAddonRemark(addonChiildModel.getAddonRemark());
                        orderAddOnChild.setAddonsGroupId(addonChiildModel.getAddOnItemIdchild());
                        orderAddOnChild.setAddonId(addonChiildModel.getAddonId());
                        orderAddOnChild.setAddonName(addonChiildModel.getAddonName());
                        orderAddOnChild.setAddonPrice(addonChiildModel.getAddonPrice());
                        orderAddOnChild.setItemPrice(orderItemModel1.getItemPrice());
                        orderAddOnChild.setPreparations(addonChiildModel.getPreparations());
                        getDataManager().insertAddonChild(orderAddOnChild);
                    }

                }


                if (addonPreprationModelCollection != null) {
                    if (edit.equals("edit")) {
                        for (AddonPreprationModel addonPrep : addonPreprationModelCollection.values()) {
                            OrderPreparationAddonModel orderPreparationAddonModel = new OrderPreparationAddonModel();
                            orderPreparationAddonModel.setItemPrimaryKey(orderItemModel1.getItemPrimaryKey());
                            orderPreparationAddonModel.setOrderPrepAddOnPrimaryKey(getTimestamp());

                            orderPreparationAddonModel.setOrderId(orderId);
                            orderPreparationAddonModel.setPreparationId(addonPrep.getPreparationId());
                            orderPreparationAddonModel.setPreparationIsPrefixed(addonPrep.getPreparationIsPrefixed());
                            orderPreparationAddonModel.setAddonId(addonPrep.getAddonId());
                            orderPreparationAddonModel.setItemRemark(addonPrep.getItemRemark());
                            orderPreparationAddonModel.setItemIdAddon(addonPrep.getItemIdAddon());
                            orderPreparationAddonModel.setAddonsGroupId(addonPrep.getAddonsGroupId());
                            orderPreparationAddonModel.setPrefixName(addonPrep.getPrefixName());
                            orderPreparationAddonModel.setPreparationName(addonPrep.getPreparationName());
                            orderPreparationAddonModel.setPreparationIsPrefixed(addonPrep.getPreparationIsPrefixed());
                            /*Insert addonprepration*/
                            getDataManager().insertOrderAddonPreparation(orderPreparationAddonModel);
                        }
                    } else {
                        for (AddonPreprationModel addonPrep : addonPreprationModelCollection.values()) {
                            OrderPreparationAddonModel orderPreparationAddonModel = new OrderPreparationAddonModel();
                            orderPreparationAddonModel.setItemPrimaryKey(orderItemModel1.getItemPrimaryKey());
                            orderPreparationAddonModel.setOrderPrepAddOnPrimaryKey(getTimestamp());
                            orderPreparationAddonModel.setOrderId(MenuZ.getInstance().getOrderId());
                            orderPreparationAddonModel.setAddonId(addonPrep.getAddonId());
                            orderPreparationAddonModel.setPreparationId(addonPrep.getPreparationId());
                            orderPreparationAddonModel.setPreparationIsPrefixed(addonPrep.getPreparationIsPrefixed());

                            orderPreparationAddonModel.setItemRemark(addonPrep.getItemRemark());
                            orderPreparationAddonModel.setItemIdAddon(addonPrep.getItemIdAddon());
                            orderPreparationAddonModel.setAddonsGroupId(addonPrep.getAddonsGroupId());
                            orderPreparationAddonModel.setPrefixName(addonPrep.getPrefixName());
                            orderPreparationAddonModel.setPreparationName(addonPrep.getPreparationName());
                            orderPreparationAddonModel.setPreparationIsPrefixed(addonPrep.getPreparationIsPrefixed());
                            /*Insert addonprepration*/
                            getDataManager().insertOrderAddonPreparation(orderPreparationAddonModel);
                        }

                    }


                }


                /*insert item prep*/

                if (itemPreprationModelCollection != null) {
                    for (ItemPreprationModel itemPreprationModel : itemPreprationModelCollection.values()) {
                        OrderPreparationModel orderPreparationModel = new OrderPreparationModel();
                        if (edit.equals("edit")) {
                            orderPreparationModel.setItemId(orderItemModel1.getItemId());
                            orderPreparationModel.setItemPrimaryKey(orderItemModel1.getItemPrimaryKey());
                            orderPreparationModel.setItemRemark(itemPreprationModel.getItemRemark());
                            //orderPreparationModel.setItemRemark(orderItemModel.getItemRemark());
                            orderPreparationModel.setOrderId(orderId);
                            orderPreparationModel.setItemName(orderItemModel1.getItemName());

                            orderPreparationModel.setPrefixName(itemPreprationModel.getPrefixName());
                            orderPreparationModel.setPreparationName(itemPreprationModel.getPreparationName());
                            orderPreparationModel.setPreparationIsPrefixed(itemPreprationModel.getPreparationIsPrefixed());
                            getDataManager().insertOrderPrearation(orderPreparationModel);
                        } else {
                            orderPreparationModel.setItemId(itemModel.getItemId());
                            orderPreparationModel.setItemPrimaryKey(orderItemModel1.getItemPrimaryKey());
                            orderPreparationModel.setItemRemark(itemPreprationModel.getItemRemark());
                            //orderPreparationModel.setItemRemark(orderItemModel.getItemRemark());
                            orderPreparationModel.setOrderId(orderId);
                            orderPreparationModel.setItemName(itemModel.getItemName());
                            orderPreparationModel.setPrefixName(itemPreprationModel.getPrefixName());
                            orderPreparationModel.setPreparationName(itemPreprationModel.getPreparationName());
                            orderPreparationModel.setPreparationIsPrefixed(itemPreprationModel.getPreparationIsPrefixed());
                            getDataManager().insertOrderPrearation(orderPreparationModel);
                        }

                    }

                }

                if (temAddonChildModelHashMap.size() != 0) {
                    if (orderItemModel != null) {
                        List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(orderItemModel1.getItemPrimaryKey(), orderItemModel.getOrderId());
                        if (cartSeltedAddonList.size() == 0) {

                            if (orderItemModel1.getNuofguest() != null) {
                                if (edit.equals("edit")) {
                                    getDataManager().updatecount(orderItemModel1.getNuofguest(), orderItemModel1.getItemId());
                                }


                            } else {
                                getDataManager().updatecount("1", orderItemModel1.getItemId());

                            }


                        } else {
                            getDataManager().updatecount("1", orderItemModel1.getItemId());
                        }


                    }

                }
                isUpdated = "true";


                handler.post(() -> {
                    if (getIntent() != null) {
                        if (getIntent().getStringExtra("from").equals("order")) {
                            Intent intent = new Intent(getApplicationContext(), OrderDetailActivity.class);
                            intent.putExtra("orderId", orderId);
                            intent.putExtra("employeename", "");
                            intent.putExtra("employeeId", "");
                            intent.putExtra("isUpdated", isUpdated);
                            intent.putExtra("tableId", tableId);
                            intent.putExtra("employeename", employeename);
                            intent.putExtra("nuofguest", "");
                            intent.putExtra("itemdata", itemModel);
                            startActivityForResult(intent,101);
                        } else {
                            Intent intent = new Intent(getApplicationContext(), CartDetailActivity.class);
                            intent.putExtra("orderId", "");
                            intent.putExtra("itemdata", itemModel);
                            startActivityForResult(intent,101);
                        }
                    }

                    //finish();
                });
            }




        }).start();



    }

    @SuppressLint("SetTextI18n")
    public void initView() {
        ImageView btnBack = findViewById(R.id.btnBack);

        llButton = findViewById(R.id.llCartBtn);
        RelativeLayout rlCancel = findViewById(R.id.rlCancel);
        TextView txtAddCart = findViewById(R.id.txtAddCart);
        //llAddon = findViewById(R.id.llAddon);
        RelativeLayout rlAddCart = findViewById(R.id.rlAddCart);

        rlAddCart.setOnClickListener(this);


        // llPrep = findViewById(R.id.llPrep);
        RelativeLayout rlCart = findViewById(R.id.rlCart);
        cartCountTxt = findViewById(R.id.cartCountTxt);
        txtAddon = findViewById(R.id.txtAddon);
        txtPrep = findViewById(R.id.txtPrep);
        rlCart.setVisibility(View.VISIBLE);
        rlCart.setOnClickListener(this);
        txtPrep.setOnClickListener(this);
        txtAddon.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        rlCancel.setOnClickListener(this);

        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        TextView txtQuantity = findViewById(R.id.txtQuantity);
        txtQuantity.setVisibility(View.VISIBLE);

        //cartCountTxt.setText(quantity);
        if (navigation.equals("neworder")) {
            rlCart.setVisibility(View.VISIBLE);

        } else {

            rlCart.setVisibility(View.GONE);
        }

        if (edit.equals("edit")) {
            tvHeaderTitle.setText(orderItemModel.getItemName());
            txtAddCart.setText("Update Cart");
        } else {
            tvHeaderTitle.setText(itemModel.getItemName());
            txtAddCart.setText("Add to Cart");
        }


        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(1);
        if (navigation.equals("neworder")) {
            findViewById(R.id.rlParent).getViewTreeObserver().addOnGlobalLayoutListener(() -> {
                Rect r = new Rect();
                findViewById(R.id.rlParent).getWindowVisibleDisplayFrame(r);
                int screenHeight = findViewById(R.id.rlParent).getRootView().getHeight();

                int keypadHeight = screenHeight - r.bottom;


                if (keypadHeight > screenHeight * 0.15) {
                    // edRemark.setFocusable(true);
                    llButton.setVisibility(View.GONE);

                } else {

                    llButton.setVisibility(View.VISIBLE);


                }
            });

        } else {
            rlCancel.setVisibility(View.GONE);
            rlAddCart.setVisibility(View.GONE);
            llButton.setVisibility(View.GONE);
        }


        new Thread(() -> {
            List<OrderItemModel> selectedItemList = getDataManager().getAllorderItem(MenuZ.getInstance().getOrderId());
            for (OrderItemModel orderItemModel1 : selectedItemList) {
                if (!orderItemModel1.getCountPrice().equals("")) {
                    totalcost += Integer.parseInt((orderItemModel1.getCountPrice()));
                }


            }
            int finalTotalcost = totalcost;
            handler.post(() -> cartCountTxt.setText("" + finalTotalcost));

        }).start();

        /*Prepared item prepration list*/

        new Thread(() -> {
            if (edit.equals("edit")) {
                JSONArray jsonArray;
                ArrayList<String> myList = new ArrayList<>();
                List<PreparationModel> preparationModels = new ArrayList<>();
                try {
                    jsonArray = new JSONArray(orderItemModel.getPreparations());
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
                    itemPreprationModel.setItemId(orderItemModel.getItemId());
                    itemPreprationModel.setItemName(orderItemModel.getItemName());
                    itemPreprationModel.setPreparationName(preparationModel.getPreparationName());
                    itemPreprationModel.setPreparationIsPrefixed(preparationModel.getPreparationIsPrefixed());
                    itemPreprationModel.setPreparationId(preparationModel.getPreparationId());
                    getDataManager().insertItemPrep(itemPreprationModel);

                }

            } else {
                JSONArray jsonArray;
                ArrayList<String> myList = new ArrayList<>();
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
            }


            if (edit.equals("edit")) {

                List<ItemPreprationModel> preparationModalArrayList = getDataManager().loadallPrepByItemId(orderItemModel.getItemId());
                List<AddOnModel> orderAddOnModelList = getDataManager().loadallAddonByitems(orderItemModel.getItemId());
                Log.d("prep", "" + preparationModalArrayList.size());
                handler.post(() -> {
                    if (preparationModalArrayList.size() == 0 && orderAddOnModelList.size() > 0) {
                        txtAddon.setTextColor(getResources().getColor(R.color.white));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            txtAddon.setBackground(getResources().getDrawable(R.drawable.button_active_addon));
                            txtPrep.setBackground(getResources().getDrawable(R.drawable.button_inactive_prep));
                        }

                        //   txtPrep.setBackground(getResources().getDrawable(R.drawable.button_white));
                        txtPrep.setTextColor(getResources().getColor(R.color.colorPrimary));
                        demoAddonFragment= DemoAddonFragment.newInstance(itemID, orderItemModel, edit, temAddonChildModelHashMap, tempAddonPrep, llButton, navigation, isUpdated);
                        addFragment(demoAddonFragment, false);
                    } else if (orderAddOnModelList.size() == 0 && preparationModalArrayList.size() > 0) {
                        txtAddon.setTextColor(getResources().getColor(R.color.colorPrimary));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            txtPrep.setBackground(getResources().getDrawable(R.drawable.button_active_prep));
                            txtAddon.setBackground(getResources().getDrawable(R.drawable.button_inactive_addon));
                        }
                        txtPrep.setTextColor(getResources().getColor(R.color.white));
                        addFragment(DemoPreprationFragment.newInstance(itemModel, orderItemModel, edit, tempItemPrep, navigation), false);

                    } else if (orderAddOnModelList.size() > 0) {
                        txtAddon.setTextColor(getResources().getColor(R.color.white));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            txtAddon.setBackground(getResources().getDrawable(R.drawable.button_active_addon));
                            txtPrep.setBackground(getResources().getDrawable(R.drawable.button_inactive_prep));
                        }
                        //   txtPrep.setBackground(getResources().getDrawable(R.drawable.button_white));
                        txtPrep.setTextColor(getResources().getColor(R.color.colorPrimary));
                        demoAddonFragment= DemoAddonFragment.newInstance(itemID, orderItemModel, edit, temAddonChildModelHashMap, tempAddonPrep, llButton, navigation, isUpdated);
                        addFragment(demoAddonFragment, false);
                    } else {
                        orderAddOnModelList.size();
                        txtAddon.setTextColor(getResources().getColor(R.color.white));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            txtAddon.setBackground(getResources().getDrawable(R.drawable.button_active_addon));
                            txtPrep.setBackground(getResources().getDrawable(R.drawable.button_inactive_prep));
                        }
                        //   txtPrep.setBackground(getResources().getDrawable(R.drawable.button_white));
                        txtPrep.setTextColor(getResources().getColor(R.color.colorPrimary));
                        demoAddonFragment= DemoAddonFragment.newInstance(itemID, orderItemModel, edit, temAddonChildModelHashMap, tempAddonPrep, llButton, navigation, isUpdated);
                        addFragment(demoAddonFragment, false);
                    }

                });
            } else {
                List<ItemPreprationModel> preparationModalArrayList = getDataManager().loadallPrepByItemId(itemModel.getItemId());
                List<AddOnModel> orderAddOnModelList = getDataManager().loadallAddonByitems(itemID);
                Log.d("prep", "" + preparationModalArrayList.size());
                handler.post(() -> {
                    if (preparationModalArrayList.size() == 0 && orderAddOnModelList.size() > 0) {
                        txtAddon.setTextColor(getResources().getColor(R.color.white));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            txtAddon.setBackground(getResources().getDrawable(R.drawable.button_active_addon));
                            txtPrep.setBackground(getResources().getDrawable(R.drawable.button_inactive_prep));
                        }
                        //txtPrep.setBackgroundColor(getResources().getColor(R.color.white));
                        txtPrep.setTextColor(getResources().getColor(R.color.colorPrimary));

                        demoAddonFragment= DemoAddonFragment.newInstance(itemID, orderItemModel, edit, temAddonChildModelHashMap, tempAddonPrep, llButton, navigation, isUpdated);
                        addFragment(demoAddonFragment, false);
                    } else if (orderAddOnModelList.size() == 0 && preparationModalArrayList.size() > 0) {

                        txtAddon.setTextColor(getResources().getColor(R.color.colorPrimary));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            txtAddon.setBackground(getResources().getDrawable(R.drawable.button_inactive_addon));
                            txtPrep.setBackground(getResources().getDrawable(R.drawable.button_active_prep));
                        }
                        //  txtPrep.setBackground(getResources().getDrawable(R.drawable.button_active_prep));
                        txtPrep.setTextColor(getResources().getColor(R.color.white));
                        addFragment(DemoPreprationFragment.newInstance(itemModel, orderItemModel, edit, tempItemPrep, navigation), false);

                    } else if (orderAddOnModelList.size() > 0) {
                        txtAddon.setTextColor(getResources().getColor(R.color.white));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            txtAddon.setBackground(getResources().getDrawable(R.drawable.button_active_addon));
                            txtPrep.setBackground(getResources().getDrawable(R.drawable.button_inactive_prep));
                        }
                        //txtPrep.setBackgroundColor(getResources().getColor(R.color.white));
                        txtPrep.setTextColor(getResources().getColor(R.color.colorPrimary));

                        demoAddonFragment= DemoAddonFragment.newInstance(itemID, orderItemModel, edit, temAddonChildModelHashMap, tempAddonPrep, llButton, navigation, isUpdated);
                        addFragment(demoAddonFragment, false);
                    } else {
                        orderAddOnModelList.size();
                        txtAddon.setTextColor(getResources().getColor(R.color.white));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            txtAddon.setBackground(getResources().getDrawable(R.drawable.button_active_addon));
                            txtPrep.setBackground(getResources().getDrawable(R.drawable.button_inactive_prep));
                        }
                        //   txtPrep.setBackground(getResources().getDrawable(R.drawable.button_white));
                        txtPrep.setTextColor(getResources().getColor(R.color.colorPrimary));
                        demoAddonFragment= DemoAddonFragment.newInstance(itemID, orderItemModel, edit, temAddonChildModelHashMap, tempAddonPrep, llButton, navigation, isUpdated);
                        addFragment(demoAddonFragment, false);
                    }
                });
            }
        }).start();
        tabLayout.post(() -> tabLayout.setupWithViewPager(viewPager));
    }

    @Override
    public void getHashMapAddons(HashMap<String, AdddonChildModel> adddonChildModelHashMap) {
        temAddonChildModelHashMap = adddonChildModelHashMap;
    }

    @Override
    public void getHashMapPreprationAddons(HashMap<String, AddonPreprationModel> addonPreprationModelHashMap) {
        tempAddonPrep = addonPreprationModelHashMap;
    }

    @Override
    public void getHashMapPreprationItem(HashMap<String, ItemPreprationModel> addonPreprationModelHashMap) {
        tempItemPrep = addonPreprationModelHashMap;
    }

    @Override
    public void getRemarkItem(String remark) {
        this.remark = remark;
    }

    @Override
    public void getAddonGroup(HashMap<String, AddOnModel> addOnModelHashMap) {
        addonList=addOnModelHashMap;
    }


}
