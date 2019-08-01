package com.menuz.Demo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.base.BaseFragment;
import com.menuz.data.model.db.ItemModel;
import com.menuz.data.model.db.ItemPreprationModel;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.data.model.db.OrderPreparationModel;
import com.menuz.data.model.db.PreparationModel;
import com.menuz.ui.Order.adapter.PreparationAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DemoPreprationFragment extends BaseFragment implements View.OnClickListener {
    public EditText edRemark;
    public TextView txtDone;
    private RecyclerView preparationRecyclerView;
    private List<ItemPreprationModel> preparationModalArrayList;
    private Context mcontext;
    private String selItem = "";
    private Handler handler = new Handler(Looper.getMainLooper());
    private TextView tvNodata;
    private ItemModel itemModel;
    private OrderItemModel orderItemModel;
    String remark = "";
    private String navigation = "";
    private PreparationAdapter preparationAdapter;
    private GetAddonandPrepSelectedListener getAddonandPrepSelectedListener;
    private String edit = "";
    private HashMap<String, ItemPreprationModel> itemPreprationModelHashMap;
    private ItemPreprationModel itemPreprationModel = new ItemPreprationModel();
    List<PreparationModel> preparationModels = new ArrayList<>();
    //private NewOrderModel newOrderModel=new NewOrderModel();

    public static DemoPreprationFragment newInstance(ItemModel itemModel, OrderItemModel orderItemModel, String edit, HashMap<String, ItemPreprationModel> tempItemPrep, String navigation) {
        DemoPreprationFragment fragment = new DemoPreprationFragment();
        fragment.setInstanceData(itemModel, orderItemModel, tempItemPrep, edit, navigation);
        return fragment;
    }

    private void setInstanceData(ItemModel itemModel, OrderItemModel orderItemModel, HashMap<String, ItemPreprationModel> tempItemPrep, String edit, String navigation) {
        this.itemModel = itemModel;
        this.orderItemModel = orderItemModel;
        this.itemPreprationModelHashMap = tempItemPrep;
        this.navigation = navigation;
        this.edit = edit;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            if ((getArguments().getString("param1") != null)) {
                ItemID = getArguments().getString("param1");
                itemModel = (ItemModel) getArguments().getSerializable("param2");
                orderItemModel = (OrderItemModel) getArguments().getSerializable("orderItemModel");
                price = getArguments().getString("param4");
                itemPreprationModelHashMap = (HashMap<String, ItemPreprationModel>) getArguments().getSerializable("itemlist");
                edit = getArguments().getString("edit");
                courseName = getArguments().getString("param3");
                nuofguest = getArguments().getString("param5");
                pricevalue = getArguments().getString("param6");
            }

        }*/

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preprations, container, false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        preparationModalArrayList = new ArrayList<>();
        preparationRecyclerView = view.findViewById(R.id.preparationRecyclerView);
        tvNodata = view.findViewById(R.id.tvNodata);
        LinearLayout llRemark = view.findViewById(R.id.llRemark);
       /* if (navigation.equals("neworder")) {
          llRemark.setVisibility(View.VISIBLE);
        }else {
            llRemark.setVisibility(View.GONE);
        }*/
        edRemark = view.findViewById(R.id.edRemark);
        txtDone = view.findViewById(R.id.txtDone);

        // String edit = ((DemoSingleBreakFastActivity) mcontext).edit;


        itemModel = ((DemoSingleBreakFastActivity) mcontext).itemModel;

        if (navigation.equals("neworder")){
            llRemark.setVisibility(View.VISIBLE);
        }else {
            llRemark.setVisibility(View.GONE);

        }
        new Thread(() -> {
            getDataManager().deletePrepration();
            List<String> myList = new ArrayList<>();
            try {
                if (edit.equals("edit")) {
                    JSONArray jsonArray = new JSONArray(orderItemModel.getPreparations());
                    for (int l = 0; l < jsonArray.length(); l++) {
                        String jsonObject = jsonArray.getString(l);
                        myList.add(jsonObject);

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
                    JSONArray jsonArray = new JSONArray(itemModel.getPreparations());
                    for (int l = 0; l < jsonArray.length(); l++) {
                        String jsonObject = jsonArray.getString(l);
                        myList.add(jsonObject);
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


            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (edit.equals("edit")){
                List<ItemPreprationModel> preparationModalArrayList = getDataManager().loadallPrepByItemId(orderItemModel.getItemId());
                handler.post(() -> updateuiPrep(preparationModalArrayList));

            }else {
                List<ItemPreprationModel> preparationModalArrayList = getDataManager().loadallPrepByItemId(itemModel.getItemId());
                handler.post(() -> updateuiPrep(preparationModalArrayList));

            }



            showSelectedPrep();



        }).start();




        edRemark.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (itemPreprationModel!=null){
                    remark = edRemark.getText().toString().trim();
                  //  itemPreprationModel.setItemRemark(remark);
                    getAddonandPrepSelectedListener.getRemarkItem(remark);
                    //new Thread(() -> getDataManager().updateitemRemark(remark,ItemID,OrderId)).start();
                    //itemPreprationModelHashMap.put(itemPreprationModel.getPreparationId(), itemPreprationModel);

                }

            }
        });


    }




    private void updateuiPrep(List<ItemPreprationModel> preparationModalArrayList) {

        if (itemPreprationModelHashMap != null) {
            for (ItemPreprationModel itemPreprationModel : itemPreprationModelHashMap.values()) {
                for (ItemPreprationModel itemPreprationModel1 : preparationModalArrayList)
                    if (itemPreprationModel.isSelect() && itemPreprationModel.getPreparationId().equals(itemPreprationModel1.getPreparationId())) {
                     /*   edRemark.setText(itemPreprationModel.getItemRemark());
                        remark=edRemark.getText().toString().trim();*/
                        itemPreprationModel1.setSelect(true);
                        // preparationModalArrayList.add(itemPreprationModel1);
                    }


            }
        }


        if (preparationModalArrayList.size() == 0) {
            tvNodata.setVisibility(View.VISIBLE);
        } else {
            tvNodata.setVisibility(View.GONE);
        }
        preparationAdapter = new PreparationAdapter(mcontext, preparationModalArrayList, new PreparationAdapter.OnItemClick() {
            @Override
            public void position(int position, String prefixName) {
                itemPreprationModel.setPrefixName(prefixName);
                itemPreprationModel = preparationModalArrayList.get(position);
                itemPreprationModel.setItemRemark(remark);
                itemPreprationModelHashMap.put(itemPreprationModel.getPreparationId(), itemPreprationModel);


                showSelectedRemark();
                String interest = preparationModalArrayList.get(position).getPreparationId();
                getAddonandPrepSelectedListener.getHashMapPreprationItem(itemPreprationModelHashMap);
                if (preparationModalArrayList.get(position).isSelect()) {
                    if (selItem.length() == 0) {
                        selItem = interest + selItem;
                    } else {
                        selItem = interest + "," + selItem;
                    }
                }

            }

            @Override
            public void isSelected(String selected) {
                // popupQuantity(prefixId);
            }

            @Override
            public void isCancelSelected(int position) {
                String interest = preparationModalArrayList.get(position).getPreparationId();
                if (selItem.contains(interest + ",")) {
                    selItem = selItem.replace(interest + ",", "");
                } else if (selItem.contains("," + interest)) {
                    selItem = selItem.replace("," + interest, "");

                } else if (selItem.contains(interest)) {
                    selItem = selItem.replace(interest, "");
                }
            }


        },navigation);
        preparationRecyclerView.setLayoutManager(new GridLayoutManager(mcontext, 3));
        preparationRecyclerView.setAdapter(preparationAdapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mcontext = context;
        getAddonandPrepSelectedListener = (GetAddonandPrepSelectedListener) context;

    }


    void showSelectedRemark() {
        if (edit.equals("edit")) {
            new Thread(() -> {
                List<OrderPreparationModel> orderPreparationModels;
                orderPreparationModels = getDataManager().loadAllSelectedPreprations(orderItemModel.getItemPrimaryKey(), orderItemModel.getOrderId());
                for (OrderPreparationModel orderPreparationModel : orderPreparationModels) {
                    for (ItemPreprationModel itemPreprationModel : preparationModalArrayList) {
                        if (itemPreprationModel.getPreparationName().equals(orderPreparationModel.getPreparationName())) {
                            handler.post(() -> edRemark.setText(orderPreparationModel.getItemRemark()));
                        }
                    }

                }

            }).start();
        }
    }


    @Override
    public void onClick(View v) {
        v.getId();/*case R.id.rlAddCart:
                if (edit.equals("edit")){
                    deleteCart();
                    addCart();
                }else {
                    addCart();
                }
                break;*/
    }


    private void showSelectedPrep() {
        if (edit.equals("edit")) {

                List<ItemPreprationModel> preparationModalArrayList = getDataManager().loadallPrepByItemId(orderItemModel.getItemId());

                List<OrderPreparationModel> orderPreparationModels;
                orderPreparationModels = getDataManager().loadAllSelectedPreprations(orderItemModel.getItemPrimaryKey(), orderItemModel.getOrderId());
                for (OrderPreparationModel orderPreparationModel : orderPreparationModels) {
                    for (ItemPreprationModel itemPreprationModel : preparationModalArrayList) {
                        if (itemPreprationModel.getPreparationName().equals(orderPreparationModel.getPreparationName())) {
                            itemPreprationModel.setSelect(true);
                            handler.post(() -> edRemark.setText(orderPreparationModel.getItemRemark()));
                            itemPreprationModel.setPrefixName(orderPreparationModel.getPrefixName());
                            itemPreprationModelHashMap.put(itemPreprationModel.getPreparationId(), itemPreprationModel);
                            handler.post(() -> preparationAdapter.notifyDataSetChanged());
                        }
                    }

                }


        }
    }

}
