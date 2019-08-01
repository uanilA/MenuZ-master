package com.menuz.ui.Order.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menuz.Demo.DemoSingleBreakFastActivity;
import com.menuz.R;
import com.menuz.application.MenuZ;
import com.menuz.base.BaseFragment;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.data.model.db.OrderPreparationModel;
import com.menuz.ui.Order.activity.CartDetailActivity;
import com.menuz.ui.Order.activity.NewOrderActivity;
import com.menuz.ui.Order.adapter.PreparationAdapter;

import java.util.ArrayList;
import java.util.List;


public class PreprationsFragment extends BaseFragment implements View.OnClickListener {
    public EditText edRemark;
    public TextView txtDone;
    private RecyclerView preparationRecyclerView;
    private List<OrderPreparationModel> preparationModalArrayList;
    private Context mcontext;
    private String selItem = "";
    private Handler handler = new Handler(Looper.getMainLooper());
    private TextView tvNodata;
    private String ItemID = "";
    private String OrderId = "";
    //private NewOrderModel newOrderModel=new NewOrderModel();

    public static PreprationsFragment newInstance() {
        PreprationsFragment fragment = new PreprationsFragment();
        Bundle args = new Bundle();
        // args.putSerializable("param1", item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_preprations, container, false);
        initView(view);
        return view;
    }

    @SuppressLint({"StaticFieldLeak", "SetTextI18n"})
    private void initView(View view) {

        preparationModalArrayList = new ArrayList<>();
        preparationRecyclerView = view.findViewById(R.id.preparationRecyclerView);
        tvNodata = view.findViewById(R.id.tvNodata);
        edRemark = view.findViewById(R.id.edRemark);
        txtDone = view.findViewById(R.id.txtDone);
        RelativeLayout rlCancel = view.findViewById(R.id.rlCancel);

        RelativeLayout rlParent = view.findViewById(R.id.rlParent);
        RelativeLayout rlAddCart = view.findViewById(R.id.rlAddCart);
        TextView txtAddCart = view.findViewById(R.id.txtAddCart);
        rlCancel.setOnClickListener(this);
        rlAddCart.setOnClickListener(this);
        String edit=((DemoSingleBreakFastActivity)mcontext).edit;
        if (!edit.equals("")){
            txtAddCart.setText("Update Cart");
        }else {
            txtAddCart.setText(mcontext.getResources().getString(R.string.add_to_cart));
        }

        ItemID = ((DemoSingleBreakFastActivity) mcontext).itemID;
        OrderId = ((DemoSingleBreakFastActivity) mcontext).orderId;


        // new Thread(() -> newOrderModel=  getDataManager().geloadOrderId()).start();

        new Thread(() -> {
            if (!OrderId.equals("")){
                preparationModalArrayList.clear();
                // String orderId=MenuZ.getInstance().getOrderId();
                preparationModalArrayList = getDataManager().loadallPrepByGroupId(ItemID, OrderId);
                Log.d("prep", "" + preparationModalArrayList.size());
                //handler.post(() -> updateuiPrep(preparationModalArrayList));
            }else {
                preparationModalArrayList.clear();
                // String orderId=MenuZ.getInstance().getOrderId();
                preparationModalArrayList = getDataManager().loadallPrepByGroupId(ItemID, MenuZ.getInstance().getOrderId());
                Log.d("prep", "" + preparationModalArrayList.size());
                //handler.post(() -> updateuiPrep(preparationModalArrayList));
            }



        }).start();







    }





    /*private void updateuiPrep(List<OrderPreparationModel> preparationModalArrayList) {
        if (preparationModalArrayList.size() == 0) {
            tvNodata.setVisibility(View.VISIBLE);
        } else {
            tvNodata.setVisibility(View.GONE);
        }
        PreparationAdapter preparationAdapter = new PreparationAdapter(mcontext, preparationModalArrayList, new PreparationAdapter.OnItemClick() {
            @Override
            public void position(int position) {
                String interest = preparationModalArrayList.get(position).getPreparationId();

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


        });
        preparationRecyclerView.setLayoutManager(new GridLayoutManager(mcontext, 3));

        preparationRecyclerView.setAdapter(preparationAdapter);
    }
*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mcontext = context;
    }

    int totalcost=0;


    @SuppressLint("SetTextI18n")
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
                if (!OrderId.equals("")){
                    new Thread(() -> {
                        getDataManager().updateitemRemark(edRemark.getText().toString(), ItemID, OrderId);
                        getDataManager().updateCart(true,OrderId);
                        List<OrderItemModel> getCount = getDataManager().getSelectedCart(OrderId, true);
                        for (OrderItemModel orderItemModel1 : getCount) {
                            totalcost += Integer.parseInt((orderItemModel1.getCountPrice()));}
                        int finalTotalcost = totalcost;
                        handler.post(() -> {
                            ((DemoSingleBreakFastActivity) mcontext).cartCountTxt.setText("" + finalTotalcost);
                            Intent intent = new Intent(mContext, CartDetailActivity.class);
                            intent.putExtra("orderId",OrderId);
                            startActivity(intent);

                        });
                    }).start();
                }else {
                    new Thread(() -> {
                        getDataManager().updateitemRemark(edRemark.getText().toString(), ItemID, MenuZ.getInstance().getOrderId());
                        getDataManager().updateCart(true,MenuZ.getInstance().getOrderId());
                        List<OrderItemModel> getCount = getDataManager().getSelectedCart(MenuZ.getInstance().getOrderId(), true);
                        for (OrderItemModel orderItemModel1 : getCount) {
                            totalcost += Integer.parseInt((orderItemModel1.getCountPrice()));}
                        int finalTotalcost = totalcost;
                        handler.post(() -> {
                            ((DemoSingleBreakFastActivity) mcontext).cartCountTxt.setText("" + finalTotalcost);
                            Intent intent = new Intent(mContext, CartDetailActivity.class);
                            intent.putExtra("orderId",OrderId);

                            startActivity(intent);

                        });
                    }).start();
                }


                break;


        }
    }
}
