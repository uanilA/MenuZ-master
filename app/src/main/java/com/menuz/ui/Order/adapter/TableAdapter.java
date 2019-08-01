package com.menuz.ui.Order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.data.model.db.AdddonChildModel;
import com.menuz.data.model.db.TableModel;
import com.menuz.ui.currentorder.activity.EditTableAndZoneActivity;

import java.util.List;

import static com.menuz.application.MenuZ.getDataManager;


public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    private List<TableModel> tableModels;
    private Context context;
    private String from = "";
    private Listener listener;
    private int lastSelectPos = -1;


    public TableAdapter(Context context, List<TableModel> tableModelArrayList, Listener listener, String from) {
        this.tableModels = tableModelArrayList;
        this.context = context;
        this.from = from;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableAdapter.ViewHolder holder, int position) {
        TableModel tableModel = tableModels.get(position);
        holder.txtItem.setText(tableModel.getTableId());
        switch (tableModel.isBusy) {
            case "true":
                holder.txtItem.setTextColor(context.getResources().getColor(R.color.black));
                //  holder.imgtableFree.setVisibility(View.VISIBLE);
                holder.txtStatus.setVisibility(View.VISIBLE);
                holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.darkgray));
                break;
            case "locked":
                holder.txtItem.setTextColor(context.getResources().getColor(R.color.black));
                //  holder.imgtableFree.setVisibility(View.VISIBLE);
                holder.txtStatus.setVisibility(View.VISIBLE);
                holder.txtStatus.setText(R.string.locked);
                holder.txtStatus.setTextColor(context.getResources().getColor(R.color.darkgreenn));
                holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.darkgray));
                break;
            default:
                holder.txtItem.setTextColor(context.getResources().getColor(R.color.black));
                holder.imgtableFree.setVisibility(View.GONE);
                holder.txtStatus.setVisibility(View.GONE);
                holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.lightgray));

                break;
        }

        if (tableModel.isSelect) {
            holder.txtItem.setTextColor(context.getResources().getColor(R.color.white));
            //  holder.imgtableFree.setVisibility(View.VISIBLE);
            holder.txtStatus.setVisibility(View.GONE);
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
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
        private ImageView imgtableFree;
        private TextView txtItem;
        private TextView txtStatus;

        ViewHolder(View itemView) {
            super(itemView);
            cardview = itemView.findViewById(R.id.card);
            txtItem = itemView.findViewById(R.id.txtItem);
            imgtableFree = itemView.findViewById(R.id.imgtableFree);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            imgItem = itemView.findViewById(R.id.imgItem);

            cardview.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            TableModel tableModel = tableModels.get(getAdapterPosition());
            switch (v.getId()) {

                case R.id.card:
                    if (getAdapterPosition() != -1
                            && !tableModel.isBusy.equals("true")
                            && !tableModel.isBusy.equals("locked")
                            && !from.equals("cartSplit")
                            &&!from.equals("cartTransfer")) {

                        if (lastSelectPos != -1) {
                            tableModels.get(lastSelectPos).isSelect = false;
                            notifyItemChanged(lastSelectPos);
                        }
                        tableModel.isSelect = true;
                        tableModel.setNewOrderId("yes");
                        listener.position(getAdapterPosition());
                        listener.selected("yes");
                        lastSelectPos = getAdapterPosition();
                        notifyItemChanged(lastSelectPos);
                        break;

                    } else if (from.equals("cartTransfer")) {
                        for (int i = 0; i < tableModels.size(); i++) {
                            tableModels.get(i).isSelect = false;
                        }
                        tableModel.isSelect = true;
                        listener.position(getAdapterPosition());
                        listener.selected("yes");

                        notifyDataSetChanged();

                    }else if (from.equals("cartSplit")&&tableModel.isBusy.equals("true")){
                        for (int i = 0; i < tableModels.size(); i++) {
                            tableModels.get(i).isSelect = false;
                        }
                        tableModel.isSelect = true;
                        listener.position(getAdapterPosition());
                        listener.selected("yes");

                        notifyDataSetChanged();
                    }else if (from.equals("cartSwitch")&&tableModel.isBusy.equals("true")){
                        for (int i = 0; i < tableModels.size(); i++) {
                            tableModels.get(i).isSelect = false;
                        }
                        tableModel.isSelect = true;
                        listener.position(getAdapterPosition());
                        listener.selected("yes");

                        notifyDataSetChanged();
                    }

            }
        }
    }

    public void setItems(List<TableModel> tableModels) {
        this.tableModels = tableModels;
    }

}
