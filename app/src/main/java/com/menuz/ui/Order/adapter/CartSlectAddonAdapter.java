package com.menuz.ui.Order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.data.model.db.NewOrderModel;
import com.menuz.data.model.db.OrderAddOnChild;
import com.menuz.data.model.db.OrderPreparationAddonModel;
import com.menuz.data.model.db.RefreshData;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import static com.menuz.application.MenuZ.getDataManager;


public class CartSlectAddonAdapter extends RecyclerView.Adapter<CartSlectAddonAdapter.ViewHolder> {
    private List<OrderAddOnChild> cartAddOnList;
    private Context context;private NewOrderModel newOrderModel=new NewOrderModel();


    private android.os.Handler handler = new android.os.Handler();

    public CartSlectAddonAdapter(Context context, List<OrderAddOnChild> cartAddOnList) {
        this.cartAddOnList = cartAddOnList;
        this.context = context;

    }

    @NonNull
    @Override
    public CartSlectAddonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_addon_view, parent, false);
        return new CartSlectAddonAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartSlectAddonAdapter.ViewHolder holder, final int position) {
        OrderAddOnChild orderAddOnChild = cartAddOnList.get(position);
        holder.heading.setText(orderAddOnChild.getAddonName());
        new Thread(() -> {
            newOrderModel = getDataManager().geloadOrderId();
            List<OrderPreparationAddonModel> orderPreparationAddonModels = getDataManager().loadAllByAddonId(orderAddOnChild.getAddonsGroupId(), orderAddOnChild.getItemIdAddon(), orderAddOnChild.getAddonId(), true, newOrderModel.getOrderId());
            handler.post(() -> {

                Log.d("order","123"+orderPreparationAddonModels.size());
                holder.oreder_seceted_addon_rv.setAdapter(new OrderListAdapter(context, orderPreparationAddonModels));
            });
        }).start();

    }



    @Override
    public int getItemCount() {
        //return preparationModalArrayList.size();
        return cartAddOnList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView details;
        private TextView heading;
        private TextView cart_item_name;
        private ImageView delete;
        private RelativeLayout main_view;
        private RecyclerView oreder_seceted_addon_rv;

        ViewHolder(View itemView) {
            super(itemView);
            cart_item_name = itemView.findViewById(R.id.cart_item_name);
            heading = itemView.findViewById(R.id.heading);
            oreder_seceted_addon_rv = itemView.findViewById(R.id.oreder_seceted_addon_rv);
            delete = itemView.findViewById(R.id.delete);
            details = itemView.findViewById(R.id.details);
            main_view = itemView.findViewById(R.id.main_view);
            main_view.setOnClickListener(this);
            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            switch (view.getId()) {

                case R.id.main_view:
                    break;

                case R.id.delete:
                    OrderAddOnChild orderAddOnChild=cartAddOnList.get(getAdapterPosition());
                    new Thread(() -> {
                       // newOrderModel=getDataManager().loadnewOrderId();

                        getDataManager().updateAddonGroup(false, orderAddOnChild.getAddonsGroupId(), newOrderModel.getOrderId(), orderAddOnChild.getItemIdAddon());
                        newOrderModel = getDataManager().geloadOrderId();
                      //  getDataManager().update(false, orderAddOnChild.getAddonId(),ORDERID);

                        getDataManager().updatePreprationondelete(false, orderAddOnChild.getAddonsGroupId(), orderAddOnChild.getItemIdAddon(), orderAddOnChild.getAddonId(), newOrderModel.getOrderId());
                        getDataManager().updateAddons(false, orderAddOnChild.getItemIdAddon(), orderAddOnChild.getAddonsGroupId(), newOrderModel.getOrderId());
                        handler.post(() -> {
                            cartAddOnList.remove(cartAddOnList.get(ViewHolder.this.getAdapterPosition()));
                            notifyDataSetChanged();
                            EventBus.getDefault().post(new RefreshData(true));

                        });
                    }).start();


            }
        }
    }
}