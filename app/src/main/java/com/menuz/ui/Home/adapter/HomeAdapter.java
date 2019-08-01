package com.menuz.ui.Home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.ui.Home.model.HomeModel;

import java.util.ArrayList;



public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private ArrayList<HomeModel>homeModelArrayList;
    private Listener listener;

    public HomeAdapter(Context context,ArrayList<HomeModel>homeModelArrayList,Listener listener){
        this.context=context;
        this.homeModelArrayList=homeModelArrayList;
        this.listener=listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeModel homeModel=homeModelArrayList.get(position);
      holder.tvItem.setText(homeModel.itemName);
      holder.imgItem.setImageResource(homeModel.itemImg);
        if (homeModel.isSelect) {
            holder.tvItem.setTextColor(context.getResources().getColor(R.color.white));
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            holder.imgItem.setImageResource(homeModel.itemImg);
        } else {
            holder.tvItem.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.white));
            holder.imgItem.setImageResource(homeModel.itemImgActive);
        }
    }

    @Override
    public int getItemCount() {
        return homeModelArrayList.size();
    }

    public interface Listener {
        void pos(int pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView card;
        private TextView tvItem;
        private ImageView imgItem;
        ViewHolder(View itemView) {
            super(itemView);
            card=itemView.findViewById(R.id.card);
            tvItem=itemView.findViewById(R.id.tvItem);
            imgItem=itemView.findViewById(R.id.imgItem);
            card.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            HomeModel navigationListModel = homeModelArrayList.get(getAdapterPosition());
            switch (v.getId()) {
                case R.id.card:
                    for (int i = 0; i < homeModelArrayList.size(); i++) {
                        homeModelArrayList.get(i).isSelect = false;
                    }
                    navigationListModel.isSelect = true;
                    listener.pos(getAdapterPosition());
                    notifyDataSetChanged();
                    break;
            }

        }
    }
}
