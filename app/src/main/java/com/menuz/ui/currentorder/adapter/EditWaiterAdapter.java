package com.menuz.ui.currentorder.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.data.model.db.EmployeeModel;
import com.menuz.ui.Order.adapter.SelectwaitorAdapter;

import java.util.List;


public class EditWaiterAdapter extends RecyclerView.Adapter<EditWaiterAdapter.ViewHolder> {


    private List<EmployeeModel> waitorInfoArrayList;
    private Context context;
    private SelectwaitorAdapter.Listener listener;
    private String employeename;


    public EditWaiterAdapter(Context context, List<EmployeeModel> tableModelArrayList, SelectwaitorAdapter.Listener listener,String Emoloyeename) {
        this.waitorInfoArrayList = tableModelArrayList;
        this.context = context;
        this.listener = listener;
        this.employeename=Emoloyeename;
    }

    @NonNull
    @Override
    public EditWaiterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_waitor, parent, false);
        return new EditWaiterAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EditWaiterAdapter.ViewHolder holder, int position) {
        EmployeeModel zoneModel = waitorInfoArrayList.get(position);
        holder.txtItem.setText(zoneModel.getEmployeeName());

        if (zoneModel.isSelect) {
            holder.txtItem.setTextColor(context.getResources().getColor(R.color.white));
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            holder.imgItem.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);


        } else {
            holder.txtItem.setTextColor(context.getResources().getColor(R.color.darkgray));
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.white));
            holder.imgItem.setColorFilter(ContextCompat.getColor(context, R.color.darkgray), PorterDuff.Mode.SRC_IN);

        }

        if (zoneModel.getEmployeeName().equals(employeename)){
            holder.txtItem.setTextColor(context.getResources().getColor(R.color.white));
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            holder.imgItem.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);

        }
    }

    @Override
    public int getItemCount() {
        return waitorInfoArrayList.size();
    }

    public interface Listener {
        void isSelected(String selected);

        void position(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView cardview;
        private ImageView imgItem;
        private TextView txtItem;

        ViewHolder(View itemView) {
            super(itemView);
            cardview = itemView.findViewById(R.id.cardview);
            txtItem = itemView.findViewById(R.id.txtItem);
            imgItem = itemView.findViewById(R.id.imgItem);
            cardview.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            EmployeeModel navigationListModel = waitorInfoArrayList.get(getAdapterPosition());
            if (v.getId() == R.id.cardview) {
                for (int i = 0; i < waitorInfoArrayList.size(); i++) {
                    waitorInfoArrayList.get(i).isSelect = false;
                }
                navigationListModel.isSelect = true;
                listener.position(getAdapterPosition());
                employeename = "";
                listener.isSelected("yes");
                notifyDataSetChanged();
            }
        }
    }
}
