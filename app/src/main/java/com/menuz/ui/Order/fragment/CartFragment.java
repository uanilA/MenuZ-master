package com.menuz.ui.Order.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.menuz.R;
import com.menuz.data.model.db.NewOrderModel;
import com.menuz.data.model.db.OrderAddOnChild;
import com.menuz.data.model.db.OrderPreparationModel;
import com.menuz.data.model.db.RefreshData;
import com.menuz.ui.Order.adapter.CartSlectAddonAdapter;
import com.menuz.ui.Order.adapter.OrderPreAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.menuz.application.MenuZ.getDataManager;


@SuppressLint("ValidFragment")
public class CartFragment extends Fragment  {

    private RecyclerView selected_addon_rv;
    private List<OrderAddOnChild> cartSeltedAddonList;
    private List<OrderPreparationModel> orderPreparationModels = new ArrayList<>();

    private Context mcontext;

    private RecyclerView oreder_addon_rv;


    private Handler handler = new Handler(Looper.getMainLooper());




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        initView(view);
        EventBus.getDefault().register(this);


        return view;
    }


    private void initView(View view) {

        cartSeltedAddonList = new ArrayList<>();
        selected_addon_rv = view.findViewById(R.id.selected_addon_rv);
        oreder_addon_rv = view.findViewById(R.id.oreder_addon_rv);

    }

    public void updateCart() {
        new Thread(() -> {
            NewOrderModel newOrderModel = getDataManager().geloadOrderId();
            cartSeltedAddonList = getDataManager().loadAllItemSelected(true, newOrderModel.getOrderId());
            handler.post(() -> updateuicart(cartSeltedAddonList));
        }).start();

    }





    @SuppressLint("StaticFieldLeak")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventFromService(RefreshData event) {
        if (event.isIsrefresh()) {
            updateCart();

        }
        Log.v("IsRefreshCheck", "" + event.isIsrefresh());

    }


    private void updateuiorderprep(List<OrderPreparationModel> orderPreparationModels) {
        OrderPreAdapter orderListAdapter = new OrderPreAdapter(mcontext, orderPreparationModels);
        oreder_addon_rv.setAdapter(orderListAdapter);
    }

    private void updateuicart(List<OrderAddOnChild> cartSeltedAddonList) {
        CartSlectAddonAdapter cartSlectAddonAdapter = new CartSlectAddonAdapter(mcontext, cartSeltedAddonList);
        selected_addon_rv.setAdapter(cartSlectAddonAdapter);
        new Thread(() -> {
            //newOrderModel=getDataManager().loadnewOrderId();
            NewOrderModel newOrderModel = getDataManager().geloadOrderId();
            orderPreparationModels = getDataManager().loadAllprepSelected(true, newOrderModel.getOrderId());
            handler.post(() -> updateuiorderprep(orderPreparationModels));
        }).start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mcontext = context;
    }


    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            updateCart();
        }
    }
}