package com.menuz.ui.currentorder.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.data.model.db.Zonemodel;
import com.menuz.ui.Order.adapter.ZoneAdapter;

import java.util.List;



public class ZoneEditAdapter extends RecyclerView.Adapter<ZoneEditAdapter.ViewHolder> {

    private Context context;
    private List<Zonemodel> zoneModelArrayList;
    private ZoneAdapter.Listener listener;
    private String zoneId;


    public ZoneEditAdapter(Context context, ZoneAdapter.Listener listener, List<Zonemodel> zoneModelArrayList,String zoneId) {
        this.context = context;
        this.zoneModelArrayList = zoneModelArrayList;
        this.zoneId=zoneId;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ZoneEditAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zone, parent, false);
        return new ZoneEditAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ZoneEditAdapter.ViewHolder holder, int position) {
        Zonemodel zoneModel = zoneModelArrayList.get(position);
        holder.txtItem.setText(zoneModel.getZoneName());
        if (zoneModel.isSelect) {
            holder.txtItem.setTextColor(context.getResources().getColor(R.color.white));
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            holder.imgItem.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);


        } else {
            holder.txtItem.setTextColor(context.getResources().getColor(R.color.darkgray));
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.white));
            holder.imgItem.setColorFilter(ContextCompat.getColor(context, R.color.darkgray), PorterDuff.Mode.SRC_IN);

        }

        if (zoneId.equals(zoneModel.getZoneId())){
            holder.txtItem.setTextColor(context.getResources().getColor(R.color.white));
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            holder.imgItem.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);

        }
    }

    @Override
    public int getItemCount() {
        return zoneModelArrayList.size();
    }

    public interface Listener {
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
            Zonemodel navigationListModel = zoneModelArrayList.get(getAdapterPosition());
            if (v.getId() == R.id.cardview) {
                for (int i = 0; i < zoneModelArrayList.size(); i++) {
                    zoneModelArrayList.get(i).isSelect = false;
                }
                navigationListModel.isSelect = true;
                listener.position(getAdapterPosition());
                zoneId = "";
                notifyDataSetChanged();
            }
        }
    }

}
