package com.menuz.Demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.application.MenuZ;
import com.menuz.base.BaseFragment;
import com.menuz.data.model.db.AddOnModel;
import com.menuz.data.model.db.AdddonChildModel;
import com.menuz.data.model.db.AddonPreprationModel;
import com.menuz.data.model.db.OrderAddOnChild;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.data.model.db.OrderPreparationAddonModel;
import com.menuz.data.model.db.PreparationModel;
import com.menuz.data.model.db.RefreshData;
import com.menuz.ui.Order.activity.NewOrderActivity;
import com.menuz.ui.Order.adapter.AddOnAdapter;
import com.menuz.ui.Order.adapter.AddOnChildAdapter;
import com.menuz.ui.Order.adapter.AddOnPreAdapter;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DemoAddonFragment extends BaseFragment implements View.OnClickListener, AddOnAdapter.onItemClick {
    public List<AdddonChildModel> adddonChildModelArrayList = new ArrayList<>();

    String ItemID = "";
    String edit = "";
    private RecyclerView addOnRecyclerView;
    private RecyclerView addonSubCatRecyclerView;
    private RecyclerView addonPrepRecyclerView;
    public List<AddOnModel> addOnModelArrayList = new ArrayList<>();
    private AddOnPreAdapter addOnPreAdapter;
    public AddOnAdapter addOnAdapter;
    public AddOnChildAdapter addOnChildAdapter;
    private String addonGroupname = "";
    String remark = "";
    private TextView txtLimit;
    private TextView preprationName;
    private Handler handler = new Handler(Looper.myLooper());
    private String selItem = "";
    List<AddonPreprationModel> preprationModelList = new ArrayList<>();
    private String selectedAddonId = "";
    private LinearLayout llRemark;
    private EditText edRemark;
    private OrderAddOnChild orderAddOnChild = new OrderAddOnChild();
    private AdddonChildModel adddonChildModelN = new AdddonChildModel();
    private AddonPreprationModel addonPreprationModel = new AddonPreprationModel();
    public TextView txtAddons;
    private OrderItemModel orderItemModel;
    HashMap<String, AdddonChildModel> adddonChildModelHashMap = new HashMap<>();
    HashMap<String, AddOnModel> addOnModelHashMap = new HashMap<>();
    HashMap<String, AddonPreprationModel> addonPreprationModelHashMap = new HashMap<>();
    private String navigation = "";
    private String addOnGroupId = "";
    private LinearLayout linearLayout;
    private String addonItemid = "";
    private String limit = "";
    private String isUpdated = "";
    List<PreparationModel> preparationModelArrayList = new ArrayList<>();
    private GetAddonandPrepSelectedListener getAddonandPrepSelectedListener;

    public DemoAddonFragment() {
    }

    public static DemoAddonFragment newInstance(String itemID, OrderItemModel orderItemModel, String edit, HashMap<String, AdddonChildModel> temAddonChildModelHashMap, HashMap<String, AddonPreprationModel> tempAddonPrep, LinearLayout llButton, String navigation, String isUpdated) {
        DemoAddonFragment fragment = new DemoAddonFragment();
        Bundle args = new Bundle();
        fragment.setInstanceData(itemID, orderItemModel, temAddonChildModelHashMap, tempAddonPrep, edit,
                llButton, navigation, isUpdated);
        fragment.setArguments(args);
        return fragment;
    }

    private void setInstanceData(String itemID, OrderItemModel orderItemModel, HashMap<String, AdddonChildModel> temAddonChildModelHashMap, HashMap<String, AddonPreprationModel> tempAddonPrep, String edit, LinearLayout linearLayout, String navigation, String isUpdated) {
        this.ItemID = itemID;
        this.orderItemModel = orderItemModel;
        this.adddonChildModelHashMap = temAddonChildModelHashMap;
        this.addonPreprationModelHashMap = tempAddonPrep;
        this.navigation = navigation;
        this.linearLayout = linearLayout;
        this.isUpdated = isUpdated;
        this.edit = edit;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_on, container, false);
        initView(view);
        //getAddonandPrepSelectedListener=((DemoSingleBreakFastActivity)mcontext);
        return view;
    }

    private void initView(View view) {
        //First Category
        addOnRecyclerView = view.findViewById(R.id.addOnRecyclerView);
        llRemark = view.findViewById(R.id.llRemark);
        preprationName = view.findViewById(R.id.preprationName);
        txtLimit = view.findViewById(R.id.txtLimit);
        TextView txtNoAddon = view.findViewById(R.id.txtNoAddon);
        TextView txtDone = view.findViewById(R.id.txtDone);
        RelativeLayout rlParent = view.findViewById(R.id.rlParent);
        txtDone.setOnClickListener(this);
        edRemark = view.findViewById(R.id.edRemark);

        txtAddons = view.findViewById(R.id.txtAddons);
        addonSubCatRecyclerView = view.findViewById(R.id.addonSubCatRecyclerView);
        addonPrepRecyclerView = view.findViewById(R.id.addonPrepRecyclerView);
       /* if (navigation.equals("neworder")){
         view.findViewById(R.id.llRemark).setVisibility(View.VISIBLE);
        }else {
            view.findViewById(R.id.llRemark).setVisibility(View.GONE);
            //view.findViewById(R.id.llButton).setVisibility(View.GONE);

       }*/

        new Thread(() -> {
            if (edit.equals("edit")) {
                addOnModelArrayList = getDataManager().loadallAddonByitems(orderItemModel.getItemId());
                // handler.post(() -> txtAddCart.setText(R.string.update_cart));
            } else {
                addOnModelArrayList = getDataManager().loadallAddonByitems(ItemID);
                // handler.post(() -> txtAddCart.setText(R.string.add_to_cart));
            }

            handler.post(() -> {
                if (addOnModelArrayList.size() == 0) {
                    txtNoAddon.setVisibility(View.VISIBLE);
                } else {
                    txtNoAddon.setVisibility(View.GONE);
                }
                updateUiAddonGroup(addOnModelArrayList);
                showSelectedAddonGroup();
            });


        }).start();
        rlParent.getViewTreeObserver().addOnGlobalLayoutListener(() -> {

            Rect r = new Rect();
            rlParent.getWindowVisibleDisplayFrame(r);
            int screenHeight = rlParent.getRootView().getHeight();
            int keypadHeight = screenHeight - r.bottom;
            try {
                if (keypadHeight > screenHeight * 0.15) {
                    edRemark.setFocusable(true);
                    linearLayout.setVisibility(View.GONE);
                } else {
                    linearLayout.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        edRemark.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                remark = edRemark.getText().toString();
                try {
                    if (adddonChildModelN.getAddonPrice() != null) {
                        adddonChildModelN.setAddonRemark(remark);
                        if (!selectedAddonId.equals("")) {
                            adddonChildModelHashMap.put(selectedAddonId, adddonChildModelN);
                        }

                    }
                }catch (Exception e){e.printStackTrace();}
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rlCancel:
                new Thread(() -> {
                    getDataManager().deleteByItemId(ItemID);
                    getDataManager().deleteByAddonChild(ItemID);
                    getDataManager().deleteByAddon(ItemID);
                    getDataManager().deleteByAddonPrep(ItemID);
                    handler.post(() -> {
                        Intent intent = new Intent(mContext, NewOrderActivity.class);
                        startActivity(intent);
                    });

                }).start();
                break;

            case R.id.rlAddCart:
               /* if (edit.equals("edit")){
                    deleteCart();
                    addCart();
                }else {
                    addCart();
                }*/
                break;
        }
    }

    public static String getTimestamp() {
        return String.valueOf(new Date().getTime());
    }


    /*Show AddonGroup*/
    private void updateUiAddonGroup(List<AddOnModel> addOnModels) {
        setData(addOnModels);
        //addOnModels.get(0).isSelect=true;
        if (addOnModels.size() > 0) {
            AddOnModel addOnModel = addOnModels.get(0);

            addOnModelHashMap.put(addOnModels.get(0).getAddonsGroupName(), addOnModel);
            getAddonandPrepSelectedListener.getAddonGroup(addOnModelHashMap);
            addOnAdapter = new AddOnAdapter(mContext, addOnModels, new AddOnAdapter.onItemClick() {
                @Override
                public void position(int position) {

                    new Thread(() -> {
                        try {
                            AddOnModel addOnModel1 = addOnModels.get(position);
                            addOnGroupId = addOnModels.get(position).getAddonsGroupId();
                            addonItemid = addOnModels.get(position).getAddOnItemID();
                            limit = addOnModels.get(position).getAddonsGroupMax();
                            //addOnModel1.set(addOnModels.get(position));
                            addOnModelHashMap.put(addOnModel1.getAddonsGroupName(), addOnModel1);
                            getAddonandPrepSelectedListener.getAddonGroup(addOnModelHashMap);

                            addonGroupname = addOnModels.get(position).getAddonsGroupName();

                            adddonChildModelArrayList.clear();
                            adddonChildModelArrayList.addAll(getDataManager().loadallAddonsByitemId(addOnGroupId, addonItemid));

                            addOnChildAdapter.setItems(adddonChildModelArrayList);
                            addOnChildAdapter.setlimit(limit);
                            addOnChildAdapter.notifyDataSetChanged();

                            handler.post(() -> {
                                if (navigation.equals("neworder")) {
                                    llRemark.setVisibility(View.GONE);
                                } else {
                                    llRemark.setVisibility(View.GONE);
                                }
                                preprationName.setVisibility(View.GONE);
                                addonPrepRecyclerView.setVisibility(View.GONE);
                                txtAddons.setVisibility(View.VISIBLE);
                                txtLimit.setVisibility(View.VISIBLE);
                                addonSubCatRecyclerView.setVisibility(View.VISIBLE);
                                if (addOnModels.get(position).getAddonsGroupIsMandatory().equals("1")) {
                                    if (addOnModels.get(position).getAddonsGroupMax().equals("0")) {
                                        txtAddons.setText(String.format("%s - ", addonGroupname));
                                        txtLimit.setText(R.string.no_limit);
                                    } else {
                                        txtAddons.setText(String.format("%s - ", addonGroupname));
                                        txtLimit.setText(String.format("Must Choose  |%s limit", addOnModels.get(position).getAddonsGroupMax()));
                                    }
                                } else {
                                    txtAddons.setText(String.format("%s-", addonGroupname));
                                    txtLimit.setText(String.format("%s limit", addOnModels.get(position).getAddonsGroupMax()));
                                }

                                showSelectedAddons(adddonChildModelArrayList, limit);
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();


                  /* final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("TAG","test data");
                            new Thread(() -> {
                                adddonChildModelArrayList.clear();
                                adddonChildModelArrayList.addAll(getDataManager().loadallAddonsByitemId(addOnGroupId, addonItemid));
                                addOnChildAdapter.setItems(adddonChildModelArrayList);
                                addOnChildAdapter.setlimit(limit);
                            }).start();

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.e("TAG","test data runnable");
                                    addOnChildAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }, 200);*/

                }

                @Override
                public void cancel(int position) {

                    new Thread(() -> {
                        adddonChildModelArrayList = getDataManager().loadallAddonsByitemId(addOnGroupId, addonItemid);
                        for (int i = 0; i < adddonChildModelArrayList.size(); i++) {
                            MenuZ.getDataManager().updateMySelectionAddonChild(false, adddonChildModelArrayList.get(i).getAddonId());
                        }
                    }).start();

                    AddOnModel addOnModel = addOnModels.get(position);
                    if (addOnModel.isSyncSelect()) {
                        alertDialog("Please contact the administrator to cancel any addon or preparation", mContext);
                    } else {
                        txtAddons.setVisibility(View.GONE);
                        txtLimit.setVisibility(View.GONE);
                        if (navigation.equals("neworder")) {
                            llRemark.setVisibility(View.GONE);
                        } else {
                            llRemark.setVisibility(View.GONE);
                        }
                        addonPrepRecyclerView.setVisibility(View.GONE);
                        addOnModelHashMap.remove(addOnModel.getAddonsGroupName());
                        getAddonandPrepSelectedListener.getAddonGroup(addOnModelHashMap);
                        adddonChildModelArrayList.clear();
                        addOnChildAdapter.notifyDataSetChanged();
                        preprationName.setVisibility(View.GONE);
                        preprationModelList.clear();
                        if (addOnPreAdapter != null) {
                            addOnPreAdapter.notifyDataSetChanged();
                        }
                    }
                    //  assert addOnPreAdapter!=null;
                }

                @Override
                public void isSelected(String isSelected) {

                }
            }, navigation);
            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            addOnRecyclerView.setLayoutManager(horizontalLayoutManagaer);
            addOnRecyclerView.setAdapter(addOnAdapter);

            for (AdddonChildModel adddonChildModel1 : adddonChildModelHashMap.values()) {
                for (int i = 0; i < addOnModels.size(); i++) {
                    if (addOnModels.get(i).getAddonsGroupId().equals(adddonChildModel1.getAddOnItemIdchild())) {
                        addOnModels.get(i).isSelect = true;
                        addOnAdapter.notifyItemChanged(i);
                    }
                }
            }
        }
    }

    void showSelectedAddonGroup() {
        if (edit.equals("edit")) {
            new Thread(() -> {
                List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(orderItemModel.getItemPrimaryKey(), orderItemModel.getOrderId());
                // addOnModelArrayList = getDataManager().loadallAddonByitems(orderItemModel.getItemId());
                for (AddOnModel addonModel : addOnModelArrayList) {
                    for (OrderAddOnChild orderAddOnChild : cartSeltedAddonList) {
                        if (addonModel.getAddonsGroupId().equals(orderAddOnChild.getAddonsGroupId())) {
                            addonModel.isSelect = true;
                            addonModel.setSyncSelect(orderAddOnChild.isSyncSelect());
                            setpreSelectAddoninHashmap(orderAddOnChild);
                            handler.post(() -> addOnAdapter.notifyDataSetChanged());
                           // new Thread(() -> {
                                adddonChildModelArrayList = getDataManager().loadallAddonsByitemId(addonModel.getAddonsGroupId(), addonModel.getAddOnItemID());
                          //  }).start();
                            showSelectedAddons(adddonChildModelArrayList, addonModel.getAddonsGroupMax());
                            break;
                        }
                    }
                }
            }).start();
        }
    }

    public void showSelectedAddons(List<AdddonChildModel> list, String limit) {
        if (edit.equals("edit")) {
            try {
                new Thread(() -> {
                    List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(orderItemModel.getItemPrimaryKey(), orderItemModel.getOrderId());
                    for (OrderAddOnChild orderAddonsChild : cartSeltedAddonList) {
                        int size = list.size();
                        for (int i = 0; i < size; i++) {
                            AdddonChildModel adddonChildModel = list.get(i);
                            if (orderAddonsChild.getAddonId().equals(adddonChildModel.getAddonId())) {
                                /* adddonChildModel.isSelect = true;*/
                                adddonChildModel.setMySelect(true);
                               // adddonChildModel.setMySelect(orderAddonsChild.isSelect());
                                selectedAddonId = orderAddonsChild.getAddonId();
                                //adddonChildModelN=orderAddonsChild.getAddonId();
                                handler.post(() -> edRemark.setText(adddonChildModel.getAddonRemark()));
                                adddonChildModel.setAddonRemark(remark);
                                adddonChildModel.setAddonAutoID(orderAddonsChild.getAddonAutoID());
                                adddonChildModelHashMap.put(adddonChildModel.getAddonId(), adddonChildModel);
                                getAddonandPrepSelectedListener.getHashMapAddons(adddonChildModelHashMap);
                                setPreSelectedPreprationinHashMap(adddonChildModelArrayList, preprationModelList);
                            }
                        }
                    }
                    handler.post(() -> {
                        addOnChildAdapter.setItems(list);
                        addOnChildAdapter.setlimit(limit);
                        addOnChildAdapter.notifyDataSetChanged();
                    });
                }).start();
              /*addOnChildAdapter.setItems(list);
                addOnChildAdapter.setlimit(limit);
                addOnChildAdapter.notifyDataSetChanged();*/
            }catch (Exception e){e.printStackTrace();}
        }
    }

    private void setpreSelectAddoninHashmap(OrderAddOnChild orderAddOnChild) {
        // List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(orderItemModel.getItemPrimaryKey(), orderItemModel.getOrderId());
        List<AdddonChildModel> adddonChildModelArrayList = getDataManager().loadallAddonsByitemId(orderAddOnChild.getAddonsGroupId(), orderAddOnChild.getItemIdAddon()); //addonItemid
        List<AddonPreprationModel> preprationModelList = getDataManager().loadAllpreprationByAddon(orderItemModel.getItemId(), orderAddOnChild.getAddonsGroupId(), orderAddOnChild.getAddonId());
        int size = adddonChildModelArrayList.size();
        for (int i = 0; i < size; i++) {
            AdddonChildModel adddonChildModel = adddonChildModelArrayList.get(i);
            adddonChildModelN = adddonChildModelArrayList.get(i);
            if (orderAddOnChild.getAddonId().equals(adddonChildModel.getAddonId())) {
                selectedAddonId = adddonChildModel.getAddonId();
                adddonChildModel.setAddonRemark(remark);
                adddonChildModel.setMySelect(orderAddOnChild.isSelect());
                adddonChildModelHashMap.put(adddonChildModel.getAddonId(), adddonChildModel);
                getAddonandPrepSelectedListener.getHashMapAddons(adddonChildModelHashMap);
                setPreSelectedPreprationinHashMap(adddonChildModelArrayList, preprationModelList);
            }
        }
    }

    private void setPreSelectedPreprationinHashMap(List<AdddonChildModel> adddonChildModelArrayList, List<AddonPreprationModel> preprationModelList) {
        List<OrderPreparationAddonModel> tempOrderPreparationAddonModels = getDataManager().loadallAddongroup(orderItemModel.getItemId(), selectedAddonId, orderItemModel.getOrderId(), orderItemModel.getItemPrimaryKey());
        for (AddonPreprationModel addonPreprationModel : preprationModelList) {
            int size = tempOrderPreparationAddonModels.size();
            for (int i = 0; i < size; i++) {
                for (AdddonChildModel adddonChildModel : adddonChildModelArrayList) {
                    OrderPreparationAddonModel orderPreparationAddonModel = tempOrderPreparationAddonModels.get(i);
                    if (addonPreprationModel.getPreparationName().equals(orderPreparationAddonModel.getPreparationName()) && adddonChildModel.getAddonId().equals(selectedAddonId)) {
                       try {
                           selectedAddonId = orderAddOnChild.getAddonId();
                           addonPreprationModel.setPrefixName(orderPreparationAddonModel.getPrefixName());
                           handler.post(() -> edRemark.setText(adddonChildModel.getAddonRemark()));
                           addonPreprationModelHashMap.put(addonPreprationModel.getPreparationId(), addonPreprationModel);
                           getAddonandPrepSelectedListener.getHashMapPreprationAddons(addonPreprationModelHashMap);
                       }catch (Exception e){e.printStackTrace();}
                    }
                }
            }
        }
        selectedAddonId = "";
    }

    /*Show Addons*/
    @SuppressLint("SetTextI18n")
    private void setData(List<AddOnModel> addOnModels) {
        if (addOnModels.size() > 0) {
            addOnGroupId = addOnModels.get(0).getAddonsGroupId();
            addonGroupname = addOnModels.get(0).getAddonsGroupName();
            addonItemid = addOnModels.get(0).getAddOnItemID();
            /*    if (edit.equals("edit")) {
                new Thread(() -> {
                    List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(orderItemModel.getItemPrimaryKey(), orderItemModel.getOrderId());
                    for (OrderAddOnChild orderAddonsChild : cartSeltedAddonList) {
                        int size = adddonChildModelArrayList.size();
                        for (int i = 0; i < size; i++) {
                            AdddonChildModel adddonChildModel = adddonChildModelArrayList.get(i);
                            if (orderAddonsChild.getAddonId().equals(adddonChildModel.getAddonId())) {
                                adddonChildModel.isSelect = true;
                                selectedAddonId = orderAddonsChild.getAddonId();
                                adddonChildModel.isSyncSelect=orderAddonsChild.isSyncSelect();
                                //adddonChildModelN=orderAddonsChild.getAddonId();
                                handler.post(() -> edRemark.setText(adddonChildModel.getAddonRemark()));
                                adddonChildModel.setAddonRemark(remark);
                                adddonChildModel.setAddonAutoID(orderAddonsChild.getAddonAutoID());
                                adddonChildModelHashMap.put(adddonChildModel.getAddonId(), adddonChildModel);
                                getAddonandPrepSelectedListener.getHashMapAddons(adddonChildModelHashMap);

                               setPreSelectedPreprationinHashMap(adddonChildModelArrayList, preprationModelList);
                       }
                        }
                    }
                }).start();
            }
*/
            String finalLimit = addOnModels.get(0).getAddonsGroupMax();
            new Thread(() -> {
                adddonChildModelArrayList = getDataManager().loadallAddonsByitemId(addOnGroupId, addonItemid);
                handler.post(() -> {
                    if (addOnModels.get(0).getAddonsGroupIsMandatory().equals("1")) {
                        if (addOnModels.get(0).getAddonsGroupMax().equals("0")) {
                            txtAddons.setText(addonGroupname + " " + "-" + " ");
                            txtLimit.setText("No limit");
                        } else {
                            txtAddons.setText(addonGroupname + " " + "-" + " ");
                            txtLimit.setText("Must Choose" + "  " + "|" + addOnModels.get(0).getAddonsGroupMax() + " " + "limit");
                        }
                    } else {
                        txtAddons.setText(addonGroupname + "-");
                        txtLimit.setText(addOnModels.get(0).getAddonsGroupMax() + " " + "limit");
                    }
                    addOnChildAdapter = new AddOnChildAdapter(mContext, adddonChildModelArrayList, new AddOnChildAdapter.OnItemClick() {
                        @Override
                        public void position(int position) {
                            if (position != -1) {
                                adddonChildModelN = adddonChildModelArrayList.get(position);
                                String addonGroupId = adddonChildModelArrayList.get(position).getAddOnItemIdchild();
                                String itemId = adddonChildModelArrayList.get(position).getItemIdAddon();
                                String preprations = adddonChildModelArrayList.get(position).getPreparations();
                                insertPrep(position, itemId, addonGroupId, preprations);
                                addonPrepRecyclerView.setVisibility(View.VISIBLE);
                                if (navigation.equals("neworder")) {
                                    llRemark.setVisibility(View.VISIBLE);
                                } else {
                                    llRemark.setVisibility(View.GONE);
                                }
                                preprationName.setVisibility(View.VISIBLE);
                                selectedAddonId = adddonChildModelN.getAddonId();
                                remark = edRemark.getText().toString();
                                edRemark.setText(remark);
                                adddonChildModelN.setAddonRemark(remark);
                                edRemark.setText(adddonChildModelN.getAddonRemark());
                                adddonChildModelHashMap.put(adddonChildModelN.getAddonId(), adddonChildModelN);
                                showAddonRemark();
                                getAddonandPrepSelectedListener.getHashMapAddons(adddonChildModelHashMap);
                                addOnChildAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void cancel(int position) {
                            try {
                                adddonChildModelN = adddonChildModelArrayList.get(position);
                                if (adddonChildModelN.isSyncSelect()) {
                                    alertDialog("Please contact the administrator to cancel any addon or preparation", mContext);
                                } else {
                                    adddonChildModelN = adddonChildModelArrayList.get(position);
                                    adddonChildModelHashMap.remove(adddonChildModelN.getAddonId());
                                    getAddonandPrepSelectedListener.getHashMapAddons(adddonChildModelHashMap);
                                    for (int i = 0; i < adddonChildModelArrayList.size(); i++) {
                                        AdddonChildModel adddonChildModel = adddonChildModelArrayList.get(i);
                                        if (adddonChildModel.isMySelect()) {
                                            insertPrep(position, adddonChildModel.getItemIdAddon(), adddonChildModel.getAddOnItemIdchild(), adddonChildModel.getPreparations());
                                            break;
                                        } else {
                                            preprationName.setVisibility(View.GONE);
                                            // preprationModelList.clear();
                                            if (addOnPreAdapter != null) {
                                                addOnPreAdapter.notifyDataSetChanged();
                                                adddonChildModelArrayList.get(position).prepId = "";
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            addOnChildAdapter.notifyDataSetChanged();
                        }
                    }, finalLimit, navigation, isUpdated);
                    addonSubCatRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
                    addonSubCatRecyclerView.setAdapter(addOnChildAdapter);
                });
            }).start();
        }
    }

    void showAddonRemark() {
        if (edit.equals("edit")) {
            new Thread(() -> {
                List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(orderItemModel.getItemPrimaryKey(), orderItemModel.getOrderId());
                // addOnModelArrayList = getDataManager().loadallAddonByitems(orderItemModel.getItemId());
                for (AddOnModel addonModel : addOnModelArrayList) {
                    for (OrderAddOnChild orderAddOnChild : cartSeltedAddonList) {
                        if (addonModel.getAddonsGroupId().equals(orderAddOnChild.getAddonsGroupId())) {
                            // addonModel.isSelect = true;
                            handler.post(() -> edRemark.setText(orderAddOnChild.getAddonRemark()));
                        }
                    }
                }
            }).start();
        }

    }

    private void insertPrep(int position, String itemID, String addonsGroupId, String preprations) {
        if (position != -1)
            new Thread(() -> {
                getDataManager().deleteAddonPrepration();
                List<String> myList = new ArrayList<>();
                myList.clear();
                try {
                    JSONArray jsonArray = new JSONArray(preprations);
                    for (int l = 0; l < jsonArray.length(); l++) {
                        String jsonObject = jsonArray.getString(l);
                        myList.add(jsonObject);
                    }

                    preparationModelArrayList.clear();
                    preparationModelArrayList = getDataManager().getallDatabtId(myList);
                    try {
                    for (int k = 0; k < preparationModelArrayList.size(); k++) {
                        PreparationModel preparationModel = preparationModelArrayList.get(k);
                        AddonPreprationModel orderPreparationAddonModel = new AddonPreprationModel();
                        orderPreparationAddonModel.setAddonId(adddonChildModelArrayList.get(position).getAddonId());
                        orderPreparationAddonModel.setAddonGroupId(adddonChildModelArrayList.get(position).getAddonGroupId());
                        orderPreparationAddonModel.setAddonsGroupId(adddonChildModelArrayList.get(position).getAddOnItemIdchild());
                        orderPreparationAddonModel.setItemIdAddon(adddonChildModelArrayList.get(position).getItemIdAddon());
                        orderPreparationAddonModel.setPreparationName(preparationModel.getPreparationName());
                        orderPreparationAddonModel.setPreparationIsPrefixed(preparationModel.getPreparationIsPrefixed());
                        orderPreparationAddonModel.setAddonName(adddonChildModelArrayList.get(position).getAddonName());
                        orderPreparationAddonModel.setPreparationId(myList.get(k));
                        getDataManager().insertAddonPrep(orderPreparationAddonModel);
                    }
                    }catch (IndexOutOfBoundsException e){e.printStackTrace();}

                    String addonId = adddonChildModelArrayList.get(position).getAddonId();
                    String addonName = adddonChildModelArrayList.get(position).getAddonName();
                    updateUiPrep(itemID, addonsGroupId, addonId, addonName);
                } catch (JSONException e) {
                }
            }).start();
    }

    /*Update ui of preprations*/

    private void updateUiPrep(String itemId, String addonsGroupId, String addonId, String addonName) {
        new Thread(() -> {
            List<AddonPreprationModel> tmpPrepList = getDataManager().loadAllpreprationByAddon(itemId, addonsGroupId, addonId);
            preprationModelList.clear();
            for (AddonPreprationModel tmpBean : tmpPrepList) {
                if (tmpBean.getPreparationId().equals(adddonChildModelN.prepId) &&
                        tmpBean.getAddonId().equals(adddonChildModelN.getAddonId())) {
                    tmpBean.setSelect(true);
                } else {
                    tmpBean.setSelect(false);
                }
                /// preprationModelList.clear();
                preprationModelList.add(tmpBean);
            }

            handler.post(() -> {
                if (preprationModelList.size() > 0) {
                    preprationName.setVisibility(View.VISIBLE);
                    preprationName.setText(addonName);
                } else {
                    preprationName.setVisibility(View.GONE);
                }

                addOnPreAdapter = new AddOnPreAdapter(mContext, preprationModelList, new AddOnPreAdapter.Listener() {
                    @Override
                    public void position(int position, String prefixName) {
                        addonPreprationModel = preprationModelList.get(position);
                        addonPreprationModel.setPrefixName(prefixName);
                        addonPreprationModelHashMap.put(addonPreprationModel.getPreparationId(), addonPreprationModel);
                        EventBus.getDefault().post(new RefreshData(true));
                        addonPreprationModel.setAddonId(addonId);
                        getAddonandPrepSelectedListener.getHashMapPreprationAddons(addonPreprationModelHashMap);
                        //for preselect prep
                        adddonChildModelN.prepId = addonPreprationModel.getPreparationId();
                    }

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void cancel(int position) {
                        if (isUpdated.equals("true")) {
                            alertDialog("Please contact the administrator to cancel any addon or preparation", mContext);
                        } else {
                            addonPreprationModel = preprationModelList.get(position);
                            String interest = preprationModelList.get(position).getPreparationId();
                            adddonChildModelN.prepId = "";
                            try {
                                String removedId = addonPreprationModel.getPreparationId();
                                //Object myObj     = addonPreprationModel;
                                addonPreprationModelHashMap.remove(removedId);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            getAddonandPrepSelectedListener.getHashMapPreprationAddons(addonPreprationModelHashMap);
                            if (selItem.contains(interest + ",")) {
                                selItem = selItem.replace(interest + ",", "");
                            } else if (selItem.contains("," + interest)) {
                                selItem = selItem.replace("," + interest, "");

                            } else if (selItem.contains(interest)) {
                                selItem = selItem.replace(interest, "");
                            }
                            EventBus.getDefault().post(new RefreshData(true));
                        }

                    }
                }, navigation, isUpdated);

                addonPrepRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
                addonPrepRecyclerView.setAdapter(addOnPreAdapter);
                for (AddonPreprationModel addonPreprationModel : addonPreprationModelHashMap.values()) {
                    for (AddonPreprationModel addonPreprationModel1 : preprationModelList) {
                        if (addonPreprationModel.getPreparationName().equals(addonPreprationModel1.getPreparationName()) && addonPreprationModel1.getAddonId().equals(addonPreprationModel.getAddonId())) {
                            addonPreprationModel.setSelect(true);
                            addOnPreAdapter.notifyDataSetChanged();
                        }
                    }
                }
                showSelectedPrep();
            });
        }).start();
    }


    private void showSelectedPrep() {
        if (edit.equals("edit")) {
            new Thread(() -> {
                List<OrderPreparationAddonModel> tempOrderPreparationAddonModels = getDataManager().loadallAddongroup(orderItemModel.getItemId(), selectedAddonId, orderItemModel.getOrderId(), orderItemModel.getItemPrimaryKey());
                for (AddonPreprationModel addonPreprationModel : preprationModelList) {
                    int size = tempOrderPreparationAddonModels.size();
                    for (int i = 0; i < size; i++) {
                        for (AdddonChildModel adddonChildModel : adddonChildModelArrayList) {
                            OrderPreparationAddonModel orderPreparationAddonModel = tempOrderPreparationAddonModels.get(i);
                            if (addonPreprationModel.getPreparationName().equals(orderPreparationAddonModel.getPreparationName()) && adddonChildModel.getAddonId().equals(selectedAddonId)) {
                                addonPreprationModel.setSelect(true);
                                addonPreprationModel.setPrefixName(orderPreparationAddonModel.getPrefixName());
                                addonPreprationModelHashMap.put(addonPreprationModel.getPreparationId(), addonPreprationModel);
                                getAddonandPrepSelectedListener.getHashMapPreprationAddons(addonPreprationModelHashMap);
                                int finalI = i;
                                handler.post(() -> addOnPreAdapter.notifyItemChanged(finalI));
                            }
                        }
                    }
                }
            }).start();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void position(int position) {

    }

    @Override
    public void cancel(int position) {

    }

    @Override
    public void isSelected(String isSelected) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getAddonandPrepSelectedListener = (GetAddonandPrepSelectedListener) context;
    }

    public void alertDialog(String msg, Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Alert")
                .setMessage(msg)
                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    // Continue with delete operation
                })
                // A null listener allows the button to dismiss the dialog and take no further action.
                .show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            new Thread(() -> {
                if (edit.equals("edit")) {
                    addOnModelArrayList = getDataManager().loadallAddonByitems(orderItemModel.getItemId());
                    // handler.post(() -> txtAddCart.setText(R.string.update_cart));
                } else {
                    addOnModelArrayList = getDataManager().loadallAddonByitems(ItemID);
                    // handler.post(() -> txtAddCart.setText(R.string.add_to_cart));
                }

                handler.post(() -> {
                    addOnModelArrayList.size();//txtNoAddon.setVisibility(View.VISIBLE);
                    //t//xtNoAddon.setVisibility(View.GONE);
                    updateUiAddonGroup(addOnModelArrayList);
                    showSelectedAddonGroup();
                });
            }).start();
        }
    }
}