package com.menuz.ui.currentorder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.ui.Order.model.KeyboardModel;

import java.util.ArrayList;


public class EditKeyboardAdapter extends RecyclerView.Adapter<EditKeyboardAdapter.ViewHolder> {
    private ArrayList<KeyboardModel> arrayList;
    private Context context;
    private onKeyBoardclick onKeyBoardclick;
    private String nuofguest;

    public EditKeyboardAdapter(ArrayList<KeyboardModel> arrayList, Context context, onKeyBoardclick onKeyBoardclick,String nuofguest) {
        this.arrayList = arrayList;
        this.context = context;
        this.onKeyBoardclick = onKeyBoardclick;
        this.nuofguest=nuofguest;

    }

    @NonNull
    @Override
    public EditKeyboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.keyboard_adapter, parent, false);
        return new EditKeyboardAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EditKeyboardAdapter.ViewHolder holder, int position) {
        KeyboardModel homeModel = arrayList.get(position);
        holder.tv_1.setText(homeModel.itemName);
        if (homeModel.isSelect) {
            holder.tv_1.setTextColor(context.getResources().getColor(R.color.white));
            holder.tv_1.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

        } else {
            holder.tv_1.setTextColor(context.getResources().getColor(R.color.black));
            holder.tv_1.setBackgroundColor(context.getResources().getColor(R.color.lightgray));
        }

        if (nuofguest.equals(homeModel.itemName)){
            holder.tv_1.setTextColor(context.getResources().getColor(R.color.white));
            holder.tv_1.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface onKeyBoardclick {
        void onClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_1;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_1 = itemView.findViewById(R.id.tv_1);

            tv_1.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.tv_1) {
                KeyboardModel navigationListModel = arrayList.get(getAdapterPosition());
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList.get(i).isSelect = false;
                }
                navigationListModel.isSelect = true;
                onKeyBoardclick.onClick(getAdapterPosition());
                nuofguest = "";

                notifyDataSetChanged();

            }
        }
    }

}