package com.menuz.ui.currentorder.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.data.model.db.OrderItemModel;

import java.util.List;

public class SplitItemAdapter extends RecyclerView.Adapter<SplitItemAdapter.ViewHolder> {
    private List<OrderItemModel>orderItemModelList;
    private Context context;
    private Listener listener;


    public SplitItemAdapter(Context context, List<OrderItemModel>orderItemModelList,Listener listener){
     this.context=context;
     this.orderItemModelList=orderItemModelList;
     this.listener=listener;
    }

    public interface Listener{
        void getPosition(int position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_split, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     OrderItemModel orderItemModel=orderItemModelList.get(position);
     holder.txtItem.setText(orderItemModel.getItemName());
     holder.switchItem.setOnCheckedChangeListener((buttonView, isChecked) -> {
       if (isChecked){
           listener.getPosition(position);
       }
     });
    }

    @Override
    public int getItemCount() {
        return orderItemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Switch switchItem;
        TextView txtItem;
        public ViewHolder(View itemView) {
            super(itemView);
            switchItem=itemView.findViewById(R.id.switchItem);
            txtItem=itemView.findViewById(R.id.txtItem);

        }
    }
}
