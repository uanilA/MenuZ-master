package com.menuz.ui.currentorder;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.menuz.Demo.DemoSingleBreakFastActivity;
import com.menuz.R;
import com.menuz.base.BaseFragment;
import com.menuz.data.model.db.OrderAddOnChild;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.data.model.db.OrderPreparationAddonModel;
import com.menuz.data.model.db.OrderPreparationModel;
import com.menuz.ui.Order.adapter.ExpandableListAdapter;
import com.menuz.ui.Order.adapter.ExpndableChildOrderAdapter;
import com.menuz.ui.Order.adapter.NonScrollExpandableListView;
import com.menuz.ui.currentorder.activity.OrderDetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDetailFragment extends BaseFragment {
    List<OrderItemModel> listDataHeader = new ArrayList<>();
    private List<OrderItemModel> selectedItemList = new ArrayList<>();
    HashMap<String, List<String>> listDataChild = new HashMap<>();
    private NonScrollExpandableListView itemRecyclerView;
    private float totalcost;
    private Handler handler = new Handler(Looper.getMainLooper());
    private TextView cartCountTxt;
    private int itemlistSize;
    private String orderId;
    private String tableId;
    private String employeeId;
    private String employeename;
    private ExpndableChildOrderAdapter selectedItemAdpter;

    private TextView  txtGrandTotal;
    private String nuofguest="";
    private TextView txtTotalCount,txtTotal,txtItemCount;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        initView(view);
        return view;

    }

    public static OrderDetailFragment newInstance(String orderId, String tableId, String employeename, String employeeId, String nuofguest) {
        OrderDetailFragment fragment = new OrderDetailFragment();
        Bundle args = new Bundle();
        fragment.setInstanceData(orderId, tableId, employeename, employeeId,nuofguest);
        fragment.setArguments(args);
        return fragment;
    }

    private void setInstanceData(String orderId, String tableId, String employeename, String employeeId, String nuofguest) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.employeeId =employeeId;
        this.nuofguest=nuofguest;
        this.employeename =employeename;
    }




    void initView(View view){
        itemRecyclerView =view.findViewById(R.id.itemRecyclerView);
        cartCountTxt =((OrderDetailActivity)mContext).findViewById(R.id.cartCountTxt);
        txtGrandTotal =view.findViewById(R.id.txtGrandTotal);
        txtTotalCount =((OrderDetailActivity)mContext).findViewById(R.id.txtTotalCount);
        txtTotal = view.findViewById(R.id.txtTotal);
        txtItemCount = ((OrderDetailActivity)mContext).findViewById(R.id.txtItemCount);
        setList();
    }


    @SuppressLint("SetTextI18n")
    void setList(){


        new Thread(() -> {

            selectedItemList = getDataManager().getSelectedItem(orderId);
            listDataHeader.addAll(selectedItemList);
            float newcost=0;

            totalcost = 0;
            for (OrderItemModel orderItemModel : selectedItemList) {
                List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(orderItemModel.getItemPrimaryKey(), orderItemModel.getOrderId());
                setItemsAddOn(orderItemModel.getItemPrimaryKey(), orderId,orderItemModel.getItemRemark());
                float cost;

                if (cartSeltedAddonList.size() != 0) {
                    int totalAddOnCost = 0;
                    for (OrderAddOnChild mainItemBean : cartSeltedAddonList) {

                        totalAddOnCost += Float.parseFloat(mainItemBean.getAddonPrice());
                        cost = (totalAddOnCost + Float.parseFloat(orderItemModel.getItemPrice())) * Float.parseFloat(orderItemModel.getCountPrice());
                        Log.d("total",""+totalAddOnCost);
                        newcost=cost;
                        orderItemModel.setTotalamount(String.valueOf(cost));

                    }

                    totalcost += (totalAddOnCost + Float.parseFloat(orderItemModel.getItemPrice())) * Float.parseFloat(orderItemModel.getCountPrice());
                } else {
                    if (!orderItemModel.getItemPrice().equals("") && !orderItemModel.getCountPrice().equals("")) {
                        if (newcost!=0){
                            float costn;
                            float CostNew;
                            CostNew=Float.parseFloat(orderItemModel.getItemPrice())*Float.parseFloat(orderItemModel.getCountPrice());
                            costn=newcost + CostNew;
                            totalcost = costn;
                            orderItemModel.setTotalamount(String.valueOf(CostNew));
                        }else {
                            float CostNew=Float.parseFloat(orderItemModel.getItemPrice())*Float.parseFloat(orderItemModel.getCountPrice());

                            totalcost += Float.parseFloat(orderItemModel.getItemPrice()) * Float.parseFloat(orderItemModel.getCountPrice());
                            orderItemModel.setTotalamount(String.valueOf(CostNew));
                        }
                    }
                }

            }




            handler.post(() -> {
                cartCountTxt.setText("" + listDataHeader.size());
                selectedItemAdpter = new ExpndableChildOrderAdapter(mContext, listDataHeader, listDataChild, new ExpandableListAdapter.OnItemCount() {
                    @Override
                    public void itemIncrease(int position) {

                        String price = selectedItemList.get(position).getItemPrice();
                        totalcost += Float.parseFloat(price);
                        txtGrandTotal.setText("" + "" + totalcost);
                        txtTotalCount.setText("" + "" + totalcost);
                        txtTotal.setText("" + "" + totalcost);
                        //cartTotaltxt.setText("" + "$" + totalcost + ".00");

                    }

                    @Override
                    public void onDeleteItem(int position) {
                        //deleteItem(position);

                    }

                    @Override
                    public void itemDecrease(int position) {
                        String price = selectedItemList.get(position).getItemPrice();
                        String count = selectedItemList.get(position).getCountPrice();
                        if (Integer.parseInt(count) > 0) {
                            totalcost -= Float.parseFloat(price);
                            txtTotal.setText("" + "$" + totalcost);
                            txtGrandTotal.setText("" + "$" + totalcost);
                            txtTotalCount.setText("" + "$" + totalcost);
                           // cartTotaltxt.setText("" + "$" + totalcost + ".00");
                            new Thread(() -> getDataManager().updateTotal("" + totalcost + ".00")).start();
                        }

                    }

                    @Override
                    public void editItem(int position) {
                        editItemPopup(position,listDataHeader.get(position).getOrderId());
                    }
                });
                itemRecyclerView.setAdapter(selectedItemAdpter);
                itemlistSize = selectedItemList.size();
                if (itemlistSize==0||itemlistSize==1){
                    txtItemCount.setText("" + itemlistSize + " " + "item");
                }else {
                    txtItemCount.setText("" + itemlistSize + " " + "items");
                }

                txtTotal.setText("" + "$" + totalcost);
                txtTotalCount.setText("" + "$" + totalcost);
                txtGrandTotal.setText("" + "$" + totalcost);
                //cartTotaltxt.setText("" + "$" + totalcost + ".00");

            });

        }).start();

    }
    public void editItemPopup(int position,String orderId){
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popuup_delete);
        TextView tvHeader = dialog.findViewById(R.id.tvHeader);
        TextView btnDone = dialog.findViewById(R.id.btnDone);
        TextView txtMessage = dialog.findViewById(R.id.txtMessage);
        tvHeader.setText(R.string.payment);
        txtMessage.setText(R.string.edit_message);
        OrderItemModel orderItemModel=listDataHeader.get(position);
        btnDone.setOnClickListener((View v) -> {
            dialog.dismiss();
            Intent intent = new Intent(mContext, DemoSingleBreakFastActivity.class);
            intent.putExtra("itemID", listDataHeader.get(position).getItemId());
            intent.putExtra("edit", "edit");
            intent.putExtra("orderId",orderId);
            intent.putExtra("from","order");
            intent.putExtra("navigation","neworder");
            intent.putExtra("orderItemModel",orderItemModel);
            intent.putExtra("itemName", listDataHeader.get(position).getItemName());
            intent.putExtra("Position", "");
            intent.putExtra("employeename",employeename);
            intent.putExtra("employeeId",employeeId);
            intent.putExtra("tableId",tableId);
            intent.putExtra("nuofguest",nuofguest);
            startActivity(intent);
        });
        ImageView img_cancel = dialog.findViewById(R.id.img_cancel);
        img_cancel.setOnClickListener((View v) -> dialog.dismiss());
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

    }



    public void setItemsAddOn(String itemPrimaryKey, String OrderId, String itemRemark) {
        ArrayList<String> arrayList = new ArrayList<>();

        /*For Prepration*/
        new Thread(() -> {
            List<OrderPreparationModel> orderPreparationModels;
            orderPreparationModels = getDataManager().loadAllSelectedPreprations(itemPrimaryKey, OrderId);
            StringBuilder prepBuilder = new StringBuilder(" ");
            for (OrderPreparationModel ignored : orderPreparationModels) {
                prepBuilder.append(" ");
            }
            if (!itemRemark.equals("")){
                prepBuilder.append(itemRemark).append(".");
                prepBuilder.append("\n");
                String finalStn = prepBuilder.toString().substring(0, prepBuilder.toString().length() - 1).concat("");
                arrayList.add(finalStn);

            }

            if (orderPreparationModels.size() > 0) {
                StringBuilder prepBuilderprep = new StringBuilder(" ");
                for (OrderPreparationModel ignored : orderPreparationModels) {
                    prepBuilderprep.append(" ");
                }



                if (orderPreparationModels.size()!=0){
                    prepBuilderprep.append("Preprations:-").append(" (");
                    for (OrderPreparationModel orderPreparationModel : orderPreparationModels) {
                        prepBuilderprep.append(orderPreparationModel.getPreparationName()).append(" ")
                                .append(orderPreparationModel.getPrefixName()).append(",");
                    }

                    final String concat1 = ")".concat(prepBuilderprep.toString().substring(0, prepBuilderprep.toString().length() - 1));
                    if (prepBuilder.toString().contains(",")) {
                        String concat;
                        concat = concat1;
                        arrayList.add(concat);
                    } else {
                        String concat;
                        concat = concat1;
                        arrayList.add(concat);
                    }
                }







            }

            /* For Addon*/
            List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(itemPrimaryKey, OrderId);
            for (OrderAddOnChild mainItemBean : cartSeltedAddonList) {
                List<OrderPreparationAddonModel> tempOrderPreparationAddonModels = getDataManager().loadallAddongroup(mainItemBean.getItemIdAddon(), mainItemBean.getAddonId(), mainItemBean.getOrderId(), itemPrimaryKey);
                StringBuilder builder = new StringBuilder(" ");
                if (!mainItemBean.getAddonRemark().equals("")){
                    builder.append(mainItemBean.getAddonName()).append(":").append(mainItemBean.getAddonRemark()).append(" " + "(");
                    for (OrderPreparationAddonModel prepAddOnBean : tempOrderPreparationAddonModels) {
                        builder.append(prepAddOnBean.getPreparationName()).append(" ")
                                .append(prepAddOnBean.getPrefixName()).append(",");
                    }

                }else {
                    builder.append(mainItemBean.getAddonName()).append(mainItemBean.getAddonRemark()).append(" " + "(");
                    for (OrderPreparationAddonModel prepAddOnBean : tempOrderPreparationAddonModels) {
                        builder.append(prepAddOnBean.getPreparationName()).append(" ")
                                .append(prepAddOnBean.getPrefixName()).append(",");
                    }
                }

                if (builder.toString().contains(",")) {
                    String substring = builder.toString().substring(0, builder.toString().length() - 1);
                    String finalStr = ")".concat(substring);
                    arrayList.add(finalStr);
                } else {
                    String finalStr = "".concat(builder.toString().substring(0, builder.toString().length() - 1));
                    arrayList.add(finalStr);
                }
            }

            listDataChild.put(itemPrimaryKey, arrayList);


        }).start();
    }


}
