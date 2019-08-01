package com.menuz.ui.Order.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.data.model.db.NewOrderModel;
import com.menuz.data.model.db.OrderPreparationModel;

import java.util.List;

import static com.menuz.application.MenuZ.getDataManager;


/**
 * Created by mindiii on 2/11/18.
 */

public class OrderPreAdapter extends RecyclerView.Adapter<OrderPreAdapter.ViewHolder> {
    private List<OrderPreparationModel> orderList;
    private Context context;
    private Handler handler=new Handler(Looper.getMainLooper());
    private NewOrderModel newOrderModel=new NewOrderModel();

    public OrderPreAdapter(Context context, List<OrderPreparationModel> orderList) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_preparation_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderPreparationModel preparationModal = orderList.get(position);
        holder.cart_item_name.setText(preparationModal.getPreparationName());

    }

   /* @Override
    public void onBindViewHolder(@NonNull final OrderListAdapter.ViewHolder holder, final int position) {
        OrderPreparationAddonModel preparationModal = orderList.get(position);
        holder.cart_item_name.setText(preparationModal.getPreparationName());
    }*/

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView cancle;
        private TextView cart_item_name;
        private RelativeLayout cart_view;

        ViewHolder(View itemView) {
            super(itemView);
            cancle = itemView.findViewById(R.id.cancle);
            cart_item_name = itemView.findViewById(R.id.cart_item_name);
            cart_view = itemView.findViewById(R.id.cart_view);

            cart_view.setOnClickListener(this);

            cancle.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.cart_view:
                    break;
                case R.id.cancle:
                    OrderPreparationModel orderPreparationAddonModel=orderList.get(getAdapterPosition());

                    new Thread(() -> {

                        newOrderModel = getDataManager().geloadOrderId();
                        getDataManager().updateprepByItem( orderPreparationAddonModel.getItemId(), orderPreparationAddonModel.getPreparationId(), newOrderModel.getOrderId(), "", "", String.valueOf(orderPreparationAddonModel.getItemPrimaryKey()));

                        handler.post(() -> {
                            orderList.remove(getAdapterPosition());
                            notifyDataSetChanged();
                        });

                    }).start();

                    break;


            }
        }
    }
}
