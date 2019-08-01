package com.menuz.ui.Order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.ui.Home.model.HomeModel;
import com.menuz.ui.Order.model.KeyboardModel;

import java.util.ArrayList;

/**
 * Created by mindiii on 23/11/18.
 */

public class KeyboardAdapter extends RecyclerView.Adapter<KeyboardAdapter.ViewHolder> {
    private ArrayList<KeyboardModel>arrayList;
    private Context context;
    private onKeyBoardclick onKeyBoardclick;

    public KeyboardAdapter(ArrayList<KeyboardModel>arrayList,Context context,onKeyBoardclick onKeyBoardclick){
        this.arrayList=arrayList;
        this.context=context;
        this.onKeyBoardclick=onKeyBoardclick;

    }

    public interface  onKeyBoardclick{
        void onClick(int position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.keyboard_adapter, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KeyboardModel homeModel=arrayList.get(position);
      holder.tv_1.setText(homeModel.itemName);
        if (homeModel.isSelect) {
            holder.tv_1.setTextColor(context.getResources().getColor(R.color.white));
            holder.tv_1.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

        } else {
            holder.tv_1.setTextColor(context.getResources().getColor(R.color.black));
            holder.tv_1.setBackgroundColor(context.getResources().getColor(R.color.lightgray));
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_1;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_1=itemView.findViewById(R.id.tv_1);
            tv_1.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_1:
                    if (getAdapterPosition()!=-1){
                        KeyboardModel navigationListModel = arrayList.get(getAdapterPosition());
                        switch (v.getId()) {
                            case R.id.tv_1:
                                for (int i = 0; i < arrayList.size(); i++) {
                                    arrayList.get(i).isSelect = false;
                                }
                                navigationListModel.isSelect = true;
                                onKeyBoardclick.onClick(getAdapterPosition());

                                notifyDataSetChanged();
                                break;
                    }


                    }
            }
        }
    }
}
