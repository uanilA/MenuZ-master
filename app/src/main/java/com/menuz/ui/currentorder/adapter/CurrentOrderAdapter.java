package com.menuz.ui.currentorder.adapter;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.ui.currentorder.model.CurrentShowOrderModel;

import java.util.List;

import static com.menuz.application.MenuZ.getDataManager;



public class CurrentOrderAdapter extends RecyclerView.Adapter<CurrentOrderAdapter.ViewHolder> {
    private List<CurrentShowOrderModel> newOrderModelArrayList;
    private getOrderIdClick getOrderId;
    private android.os.Handler handler=new android.os.Handler(Looper.getMainLooper());


    public CurrentOrderAdapter(List<CurrentShowOrderModel> newOrderModelArrayList, getOrderIdClick getOrderId) {
        this.newOrderModelArrayList = newOrderModelArrayList;
        this.getOrderId = getOrderId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_adapter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CurrentShowOrderModel newOrderModel = newOrderModelArrayList.get(position);
        holder.tvTableCount.setText(newOrderModel.tableId);
        holder.tvZoneName.setText(newOrderModel.zoneName);
        holder.tvWaitorName.setText(newOrderModel.waiterName);
        holder.tvGuest.setText(newOrderModel.nuofGuest);
    }

    @Override
    public int getItemCount() {
        return newOrderModelArrayList.size();
    }

    public interface getOrderIdClick {
        void getOrderId(String orderId);
        void getposition(int position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTableCount, tvZoneName, tvWaitorName, tvGuest;
        private LinearLayout llParent;
        private ImageView deleteImg;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTableCount = itemView.findViewById(R.id.tvTableCount);
            tvZoneName = itemView.findViewById(R.id.tvZoneName);
            tvWaitorName = itemView.findViewById(R.id.tvWaitorName);
            tvGuest = itemView.findViewById(R.id.tvGuest);
            llParent = itemView.findViewById(R.id.llParent);
            deleteImg = itemView.findViewById(R.id.ImgDelete);
            llParent.setOnClickListener(this);
            deleteImg.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            CurrentShowOrderModel newOrderModel = newOrderModelArrayList.get(getAdapterPosition());
            switch (v.getId()) {
                case R.id.llParent:
                    if (getAdapterPosition() != -1)
                        getOrderId.getOrderId(newOrderModel.OrderId);
                        getOrderId.getposition(getAdapterPosition());
                    break;

                case R.id.ImgDelete:
                    if (getAdapterPosition() != -1){
                        new Thread(() -> {
                            getDataManager().deleteByOrderId(newOrderModel.OrderId);
                            handler.post(() -> {
                             newOrderModelArrayList.remove(getAdapterPosition());
                             notifyDataSetChanged();
                            });

                        }).start();
                    }


                    break;


            }
        }
    }
}
