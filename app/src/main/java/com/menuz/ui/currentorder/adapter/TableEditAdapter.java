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
import com.menuz.data.model.db.TableModel;

import java.util.List;



public class TableEditAdapter extends RecyclerView.Adapter<TableEditAdapter.ViewHolder> {

    private List<TableModel> tableModels;
    private Context context;
    private Listener listener;
    private String tableId;


    public TableEditAdapter(Context context, List<TableModel> tableModelArrayList, Listener listener,String tableId) {
        this.tableModels = tableModelArrayList;
        this.context = context;
        this.listener = listener;
        this.tableId=tableId;
    }

    @NonNull
    @Override
    public TableEditAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false);
        return new TableEditAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TableEditAdapter.ViewHolder holder, int position) {
        TableModel tableModel = tableModels.get(position);
        holder.txtItem.setText(tableModel.getTableId());
        if (tableModel.isSelect) {
            holder.txtItem.setTextColor(context.getResources().getColor(R.color.white));
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            holder.imgItem.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);


        } else {
            holder.txtItem.setTextColor(context.getResources().getColor(R.color.darkgray));
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.white));
            holder.imgItem.setColorFilter(ContextCompat.getColor(context, R.color.darkgray), PorterDuff.Mode.SRC_IN);

        }


        if (tableId.equals(tableModel.getTableId())){
            holder.txtItem.setTextColor(context.getResources().getColor(R.color.white));
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            holder.imgItem.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);

        }
    }

    @Override
    public int getItemCount() {
        return tableModels.size();
    }

    public interface Listener {
        void position(int position);

        void selected(String selected);
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
            TableModel tableModel = tableModels.get(getAdapterPosition());
            if (v.getId() == R.id.cardview) {
                for (int i = 0; i < tableModels.size(); i++) {
                    tableModels.get(i).isSelect = false;
                }
                tableModel.isSelect = true;
                listener.position(getAdapterPosition());
                listener.selected("yes");
                tableId = "";
                notifyDataSetChanged();
            }
        }

    }
}

