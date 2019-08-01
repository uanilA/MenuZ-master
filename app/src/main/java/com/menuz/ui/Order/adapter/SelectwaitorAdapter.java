package com.menuz.ui.Order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.data.model.db.EmployeeModel;

import java.util.List;


public class SelectwaitorAdapter extends RecyclerView.Adapter<SelectwaitorAdapter.ViewHolder> {


    private List<EmployeeModel> waitorInfoArrayList;
    private Context context;
    private Listener listener;


    public SelectwaitorAdapter(Context context,List<EmployeeModel>tableModelArrayList,Listener listener){
        this.waitorInfoArrayList=tableModelArrayList;
        this.context=context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_waitor, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EmployeeModel zoneModel=waitorInfoArrayList.get(position);
        holder.txtItem.setText(zoneModel.getEmployeeName());

        if (zoneModel.isSelect) {
            holder.txtItem.setTextColor(context.getResources().getColor(R.color.black));
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));


        } else {
            holder.txtItem.setTextColor(context.getResources().getColor(R.color.black));
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.lightgray));

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
            cardview=itemView.findViewById(R.id.cardview);
            txtItem=itemView.findViewById(R.id.txtItem);
            imgItem=itemView.findViewById(R.id.imgItem);
            cardview.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            EmployeeModel navigationListModel = waitorInfoArrayList.get(getAdapterPosition());
            switch (v.getId()) {
                case R.id.cardview:
                    for (int i = 0; i < waitorInfoArrayList.size(); i++) {
                        waitorInfoArrayList.get(i).isSelect = false;
                    }
                    navigationListModel.isSelect = true;
                    listener.position(getAdapterPosition());
                    listener.isSelected("yes");
                    notifyDataSetChanged();
                    break;
            }
        }
    }

}
