package com.menuz.ui.Order.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.menuz.R;
import com.menuz.data.model.db.MenuModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private List<MenuModel> shiftModalArrayList;
    private Context context;
    private OnItemListener onItemListener;

    public MenuAdapter(List<MenuModel> shiftModalArrayList, Context context, OnItemListener onItemListener) {
        this.shiftModalArrayList = shiftModalArrayList;
        this.context = context;
        this.onItemListener = onItemListener;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shift_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        MenuModel menuModel = shiftModalArrayList.get(position);
        holder.shiftName.setText(menuModel.getGroupName());
        String image="http://82.81.11.210:12986/datasnap/rest/tservermethods1/downloadfile/c:%2fimagesAPI%2f/"+menuModel.getGroupImage();
        try {
            Glide.with(context).load(image).error(R.drawable.img_placeholder).into(holder.imgMenu);
            //Picasso.get().load(image).placeholder(R.drawable.img_placeholder).error(R.drawable.img_placeholder).into(holder.imgMenu);
            if (menuModel.isSelect) {
                holder.shiftName.setTextColor(context.getResources().getColor(R.color.white));
                holder.relativeLayout.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));


            } else {
                holder.shiftName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                holder.relativeLayout.setCardBackgroundColor(context.getResources().getColor(R.color.lightgray));

            }
        }catch (Exception e){e.printStackTrace();}

        /*new Thread(() -> {
           List<OrderMenuModel>orderMenuModels= getDataManager().loadAllorderMenu(ORDERID);
           List<OrderItemModel> selectedItemList =getDataManager().getAllorderItem(ORDERID);
            for (int i = 0; i <orderMenuModels.size() ; i++) {
                if (orderMenuModels.get(i).getGroupId().equals(shiftModalArrayList.get(position).getGroupId())){
                    handler.post(() -> {
                        holder.shiftName.setTextColor(context.getResources().getColor(R.color.white));
                        holder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

                    });
                } *//*if (selectedItemList.size()==0){
                    handler.post(() -> {
                        holder.shiftName.setTextColor(context.getResources().getColor(R.color.white));
                        holder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

                    });
                }*//*
            }
        }).start();*/


    }

    @Override
    public int getItemCount() {
        return shiftModalArrayList.size();
    }

    public interface OnItemListener {
        void position(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView shiftName;
        private CardView relativeLayout;
        private ImageView imgMenu;

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        ViewHolder(View itemView) {
            super(itemView);
            shiftName = itemView.findViewById(R.id.shiftName);
            imgMenu = itemView.findViewById(R.id.imgMenu);
            relativeLayout = itemView.findViewById(R.id.cardView);
            relativeLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            MenuModel tableModel = shiftModalArrayList.get(getAdapterPosition());
            switch (v.getId()) {
                case R.id.cardView:
                    for (int i = 0; i < shiftModalArrayList.size(); i++) {
                        shiftModalArrayList.get(i).isSelect = false;
                    }
                    tableModel.isSelect = true;
                    onItemListener.position(getAdapterPosition());
                    notifyDataSetChanged();
                    break;
            }
        }

    }

}


