package com.menuz.ui.Home;

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
import com.menuz.application.MenuZ;
import com.menuz.base.BaseFragment;
import com.menuz.data.model.db.AdddonChildModel;
import com.menuz.data.model.db.ItemModel;
import com.menuz.data.model.db.NewOrderModel;
import com.menuz.data.model.db.OrderAddOnChild;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.data.model.db.OrderPreparationAddonModel;
import com.menuz.data.model.db.OrderPreparationModel;
import com.menuz.ui.Order.activity.CartDetailActivity;
import com.menuz.ui.Order.adapter.ExpandableListAdapter;
import com.menuz.ui.Order.adapter.NonScrollExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.menuz.Demo.DemoAddonFragment.getTimestamp;
import static com.menuz.application.MenuZ.getDataManager;

public class CartDetailFragment extends BaseFragment {

    List<OrderItemModel> listDataHeader = new ArrayList<>();
    private List<OrderItemModel> selectedItemList = new ArrayList<>();
    HashMap<String, List<String>> listDataChild = new HashMap<>();
    private NonScrollExpandableListView itemRecyclerView;
    private float totalcost;
    private Handler handler = new Handler(Looper.getMainLooper());
    private TextView cartCountTxt;
    private TextView cartCountTxtItem;
    private int itemlistSize;
    private String orderId;
    private ItemModel itemModel = new ItemModel();
    private ExpandableListAdapter selectedItemAdpter;

    private TextView txtGrandTotal;
    private TextView txtTotalCount, txtTotal, txtItemCount;

    public CartDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        initView(view);
        return view;
    }




    public static CartDetailFragment newInstance(String orderId, ItemModel itemModel) {
        CartDetailFragment fragment = new CartDetailFragment();
        Bundle args = new Bundle();
        fragment.setInstanceData(orderId, itemModel);
        fragment.setArguments(args);
        return fragment;
    }

    private void setInstanceData(String orderId, ItemModel itemModel) {
        this.orderId = orderId;
        this.itemModel = itemModel;

    }


    void initView(View view) {
        itemRecyclerView = view.findViewById(R.id.itemRecyclerView);
        cartCountTxt = ((CartDetailActivity) mContext).findViewById(R.id.cartCountTxt);
        cartCountTxtItem = ((CartDetailActivity) mContext).findViewById(R.id.cartCountTxtItem);
        txtGrandTotal = view.findViewById(R.id.txtGrandTotal);
        txtTotalCount = ((CartDetailActivity) mContext).findViewById(R.id.txtTotalCount);
        txtTotal = view.findViewById(R.id.txtTotal);
        txtItemCount = ((CartDetailActivity) mContext).findViewById(R.id.txtItemCount);
        setList();
    }

    @SuppressLint("SetTextI18n")

    /*Set list into adapter*/
    private void setList() {
        new Thread(() -> {
            if (null != orderId && !orderId.equals("")) {
                selectedItemList = getDataManager().getSelectedItem(orderId);
                listDataHeader.addAll(selectedItemList);

                totalcost = 0;
                for (OrderItemModel orderItemModel : selectedItemList) {
                    List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(orderItemModel.getItemPrimaryKey(), orderItemModel.getOrderId());
                    setItemsAddOn(orderItemModel.getItemPrimaryKey(), orderId, orderItemModel.getItemRemark());
                    float cost;
                    float totalCost = 0;
                    if (cartSeltedAddonList.size() != 0) {
                        for (OrderAddOnChild mainItemBean : cartSeltedAddonList) {
                            if (!mainItemBean.getAddonPrice().equals("")) {
                                totalCost = Float.parseFloat(mainItemBean.getAddonPrice()) + Float.parseFloat(mainItemBean.getItemPrice());
                                cost = totalCost * Float.parseFloat(orderItemModel.getCountPrice());

                                orderItemModel.setTotalamount(String.valueOf(cost));
                            }
                        }

                        totalcost += totalCost * Float.parseFloat(orderItemModel.getCountPrice());
                    } else {
                        if (!orderItemModel.getItemPrice().equals("") && !orderItemModel.getCountPrice().equals("")) {
                            if (!orderItemModel.getTotalamount().equals("")) {
                                totalcost += Float.parseFloat(orderItemModel.getItemPrice()) + Float.parseFloat(orderItemModel.getTotalamount()) * Integer.parseInt(orderItemModel.getCountPrice());
                                orderItemModel.setTotalamount(String.valueOf(totalcost));
                            } else {
                                totalcost += Float.parseFloat(orderItemModel.getItemPrice()) * Float.parseFloat(orderItemModel.getCountPrice());
                                orderItemModel.setTotalamount(String.valueOf(totalcost));
                            }


                        }
                    }

                }

            } else {
                selectedItemList = getDataManager().getSelectedItem(MenuZ.getInstance().getOrderId());
                listDataHeader.addAll(selectedItemList);
                float newcost = 0;
                totalcost = 0;
                for (OrderItemModel orderItemModel : selectedItemList) {
                    List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(orderItemModel.getItemPrimaryKey(), orderItemModel.getOrderId());
                    setItemsAddOn(orderItemModel.getItemPrimaryKey(), MenuZ.getInstance().getOrderId(), orderItemModel.getItemRemark());
                    float cost;

                    if (cartSeltedAddonList.size() != 0) {
                        int totalAddOnCost = 0;
                        for (OrderAddOnChild mainItemBean : cartSeltedAddonList) {
                            totalAddOnCost += Float.parseFloat(mainItemBean.getAddonPrice());
                            cost = (totalAddOnCost + Float.parseFloat(orderItemModel.getItemPrice())) * Float.parseFloat(orderItemModel.getCountPrice());
                            Log.d("total", "" + totalAddOnCost);
                            newcost = cost;
                            orderItemModel.setTotalamount(String.valueOf(cost));
                        }
                        totalcost += (totalAddOnCost + Float.parseFloat(orderItemModel.getItemPrice())) * Float.parseFloat(orderItemModel.getCountPrice());
                    } else {
                        if (!orderItemModel.getItemPrice().equals("") && !orderItemModel.getCountPrice().equals("")) {
                            if (newcost != 0) {
                                float costn;
                                float CostNew;
                                CostNew = Integer.parseInt(orderItemModel.getItemPrice()) * Float.parseFloat(orderItemModel.getCountPrice());
                                costn = newcost + CostNew;
                                totalcost = costn;
                                orderItemModel.setTotalamount(String.valueOf(CostNew));
                            } else {
                                float CostNew = Float.parseFloat(orderItemModel.getItemPrice()) * Float.parseFloat(orderItemModel.getCountPrice());
                                totalcost += Float.parseFloat(orderItemModel.getItemPrice()) * Float.parseFloat(orderItemModel.getCountPrice());
                                orderItemModel.setTotalamount(String.valueOf(CostNew));
                            }
                        }
                    }
                }
            }

            handler.post(() -> {
                selectedItemAdpter = new ExpandableListAdapter(mContext, listDataHeader, listDataChild, new ExpandableListAdapter.OnItemCount() {
                    @Override
                    public void itemIncrease(int position) {

                        String price = listDataHeader.get(position).getItemPrice();
                        totalcost += Float.parseFloat(price);
                        txtGrandTotal.setText("" + totalcost);
                        txtTotalCount.setText(""+ totalcost);
                        txtTotal.setText("" +totalcost);
                    }

                    @Override
                    public void onDeleteItem(int position) {
                        OrderItemModel headerModel = listDataHeader.get(position);
                        deleteItem(position, headerModel);
                    }

                    @Override
                    public void itemDecrease(int position) {
                        String price = listDataHeader.get(position).getTotalamount();
                        String count = listDataHeader.get(position).getCountPrice();
                        if (Integer.parseInt(count) > 0) {
                            totalcost -= Float.parseFloat(price);
                            txtTotal.setText("" + totalcost);
                            txtGrandTotal.setText("" +  totalcost);
                            txtTotalCount.setText("" + totalcost);
                            new Thread(() -> getDataManager().updateTotal("" + totalcost)).start();
                        }
                    }

                    @Override
                    public void editItem(int position) {
                        editItemPopup(position);
                    }
                });
                itemRecyclerView.setAdapter(selectedItemAdpter);
                itemlistSize = listDataHeader.size();
                cartCountTxt.setText("" + itemlistSize);
                txtTotal.setText("" + +totalcost);
                txtTotalCount.setText("" + +totalcost);
                txtGrandTotal.setText("" + totalcost);
                //cartTotaltxt.setText("$" + totalcost);

            });

        }).start();
    }

    /*Set addon prepration in child list*/
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
            if (!itemRemark.equals("")) {
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

                if (orderPreparationModels.size() != 0) {
                    prepBuilderprep.append("Preprations:-").append(" (");
                    for (OrderPreparationModel orderPreparationModel : orderPreparationModels) {
                        prepBuilderprep.append(orderPreparationModel.getPreparationName()).append(" ")
                                .append(orderPreparationModel.getPrefixName()).append(",");
                    }
                    String substring1 = prepBuilderprep.toString().substring(0, prepBuilderprep.toString().length() - 1);
                    if (prepBuilder.toString().contains(",")) {
                        String finalStr = substring1.concat(")");
                        arrayList.add(finalStr);
                    } else {
                        String finalStr = substring1.concat(")");
                        arrayList.add(finalStr);
                    }
                }

            }

            /* For Addon*/
            List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(itemPrimaryKey, OrderId);
            for (OrderAddOnChild mainItemBean : cartSeltedAddonList) {
                List<OrderPreparationAddonModel> tempOrderPreparationAddonModels = getDataManager().loadallAddongroup(mainItemBean.getItemIdAddon(), mainItemBean.getAddonId(), mainItemBean.getOrderId(), itemPrimaryKey);
                StringBuilder builder = new StringBuilder(" ");
                if (!mainItemBean.getAddonRemark().equals("")) {
                    builder.append(mainItemBean.getAddonName()).append(":").append(mainItemBean.getAddonRemark()).append(" " + "(");
                    for (OrderPreparationAddonModel prepAddOnBean : tempOrderPreparationAddonModels) {
                        builder.append(prepAddOnBean.getPreparationName()).append(" ")
                                .append(prepAddOnBean.getPrefixName()).append(",");
                    }
                } else {
                    builder.append(mainItemBean.getAddonName()).append(mainItemBean.getAddonRemark()).append(" " + "(");
                    for (OrderPreparationAddonModel prepAddOnBean : tempOrderPreparationAddonModels) {
                        builder.append(prepAddOnBean.getPreparationName()).append(" ")
                                .append(prepAddOnBean.getPrefixName()).append(",");
                    }
                }
                String substring1 = builder.toString().substring(0, builder.toString().length() - 1);
                if (builder.toString().contains(",")) {
                    String finalStr = substring1.concat(")");
                    arrayList.add(finalStr);
                } else {
                    String finalStr = "".concat(substring1);
                    arrayList.add(finalStr);
                }
            }
            listDataChild.put(itemPrimaryKey, arrayList);
        }).start();
    }

    @SuppressLint("SetTextI18n")
    /*Delete items addon prepration from queries*/
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
            handler.post(() -> cartCountTxtItem.setText("" + listDataHeader.size()));
        }).start();
    }

    @SuppressLint("SetTextI18n")

    /*Delete Item*/
    public void deleteItem(int position, OrderItemModel orderItemModel) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popuup_delete);
        TextView tvHeader = dialog.findViewById(R.id.tvHeader);
        TextView btnDone = dialog.findViewById(R.id.btnDone);
        TextView txtMessage = dialog.findViewById(R.id.txtMessage);
        tvHeader.setText(R.string.payment);
        txtMessage.setText(R.string.delete_popup);
        btnDone.setOnClickListener(v -> new Thread(() -> {
            deleteCart(orderItemModel);
            handler.post(() -> {
                String price = selectedItemList.get(position).getTotalamount();
                String count = selectedItemList.get(position).getCountPrice();
                if (count.equals("0")) {
                    count = "0";
                }
                itemlistSize = itemlistSize - 1;
                if (itemlistSize == 0 || itemlistSize == 1) {
                    txtItemCount.setText("" + itemlistSize + " " + "item");
                } else {
                    txtItemCount.setText("" + itemlistSize + " " + "items");
                }
                totalcost -= Float.parseFloat(price) * Float.parseFloat(count);
                txtTotalCount.setText("" + totalcost);
                txtTotal.setText("" + totalcost);
                txtGrandTotal.setText("" + +totalcost);
                //cartTotaltxt.setText("$" + +totalcost);
                new Thread(() -> getDataManager().updateTotal("" + totalcost)).start();
                listDataHeader.remove(position);
                cartCountTxtItem.setText("" + itemlistSize);
                selectedItemAdpter.notifyDataSetChanged();
                dialog.dismiss();
            });
        }).start());
        ImageView img_cancel = dialog.findViewById(R.id.img_cancel);
        img_cancel.setOnClickListener((View v) -> dialog.dismiss());
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    /*Edit the item*/
    public void editItemPopup(int position) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popuup_delete);
        TextView tvHeader = dialog.findViewById(R.id.tvHeader);
        TextView btnDone = dialog.findViewById(R.id.btnDone);
        TextView txtMessage = dialog.findViewById(R.id.txtMessage);
        tvHeader.setText(R.string.payment);
        txtMessage.setText(R.string.edit_message);
        OrderItemModel orderItemModel = listDataHeader.get(position);
        orderId = orderItemModel.getOrderId();

        btnDone.setOnClickListener((View v) -> {
            OrderAddOnChild orderAddOnChild=new OrderAddOnChild();
            new Thread(() -> {
                for (int i = 0; i<listDataHeader.size(); i++){
                    OrderItemModel orderItemModel1= listDataHeader.get(i);
                    orderAddOnChild.setItemPrimaryKey(orderItemModel1.getItemPrimaryKey());
                    orderAddOnChild.setAddonId(orderItemModel1.getItemId());
                    getDataManager().update(orderAddOnChild);
                }
            }).start();

            Intent intent = new Intent(mContext, DemoSingleBreakFastActivity.class);
            intent.putExtra("itemID", orderItemModel.getItemId());
            intent.putExtra("itemName", orderItemModel.getItemName());
            intent.putExtra("itemdata", itemModel);
            intent.putExtra("navigation", "neworder");
            intent.putExtra("orderItemModel", orderItemModel);
            intent.putExtra("orderId", orderId);
            intent.putExtra("from", "");
            intent.putExtra("edit", "edit");
            dialog.dismiss();
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
}