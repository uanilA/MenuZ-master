package com.menuz.ui.Order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.Utils.MyToast;
import com.menuz.data.model.db.AdddonChildModel;

import java.util.List;


public class AddOnChildAdapter extends RecyclerView.Adapter<AddOnChildAdapter.ViewHolder> {
    private List<AdddonChildModel> addOnList;
    private Context context;
    private OnItemClick onItemClick;
    private String limit;
    private int count = 0;
    private String navigation;
    private String edit;


    public AddOnChildAdapter(Context context, List<AdddonChildModel> addOnList, OnItemClick onItemClick, String limit, String navigation, String edit) {
        this.addOnList = addOnList;
        this.context = context;
        this.onItemClick = onItemClick;
        this.navigation = navigation;
        this.edit = edit;
        this.limit = limit;

    }

    @NonNull
    @Override
    public AddOnChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addons_adapter, parent, false);
        return new AddOnChildAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddOnChildAdapter.ViewHolder holder, final int position) {
        AdddonChildModel adddonChildModel = addOnList.get(position);
        holder.addon_item_Name.setText(adddonChildModel.getAddonName());
        if (adddonChildModel.isSelect() && navigation.equals("neworder")) {
            holder.addon_iv_cancle.setVisibility(View.VISIBLE);
            holder.pre_card_view.setBackground(context.getResources().getDrawable(R.drawable.addon_select_view));
        } else {
            holder.addon_iv_cancle.setVisibility(View.GONE);
            holder.pre_card_view.setBackground(context.getResources().getDrawable(R.drawable.addon_view));
        }
        /*  }else {
            holder.addon_iv_cancle.setVisibility(View.GONE);
            holder.pre_card_view.setBackground(context.getResources().getDrawable(R.drawable.addon_view));

        }*/
        /*if (!adddonChildModel.getPreparations().equals("[]")) {
            holder.addonPlusIcon.setVisibility(View.VISIBLE);
        } else {
            holder.addonPlusIcon.setVisibility(View.GONE);
        }*/

        if (adddonChildModel.getPreparations()!=null){
            if (adddonChildModel.getPreparations().equals("[]")){
                holder.addonPlusIcon.setVisibility(View.VISIBLE);
            }else {

                if (!adddonChildModel.isSelect()) {
                    holder.addonPlusIcon.setVisibility(View.GONE);
                } else {
                    holder.addonPlusIcon.setVisibility(View.VISIBLE);
                }

            }
        }

       /* if (navigation.equals("neworder")){
          holder.addon_iv_cancle.setVisibility(View.VISIBLE);
        }else {
            holder.addon_iv_cancle.setVisibility(View.GONE);

        }*/

    }

    @Override
    public int getItemCount() {
        return addOnList.size();
    }

    private void showToast(String msg) {
        if (!TextUtils.isEmpty(msg))
            MyToast.getInstance(context).showDasuAlert(msg);
    }

    public interface OnItemClick {
        void position(int position);

        void cancel(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView addon_item_Name;
        private TextView addonPlusIcon;

        private RelativeLayout pre_card_view;
        private ImageView addon_iv_cancle;

        ViewHolder(View itemView) {
            super(itemView);
            addon_item_Name = itemView.findViewById(R.id.pre_item_Name);
            addonPlusIcon = itemView.findViewById(R.id.addonPlusIcon);

            pre_card_view = itemView.findViewById(R.id.pre_card_view);
            addon_iv_cancle = itemView.findViewById(R.id.addon_iv_cancle);

            pre_card_view.setOnClickListener(v -> {
                try {
                    AdddonChildModel adddonChildModel = addOnList.get(getAdapterPosition());
                    if (navigation.equals("neworder")) {
                        int count = 0;
                        for (AdddonChildModel addOnBean : addOnList) {
                            if (addOnBean.isSelect()) {
                                count++;
                            }
                        }
                        if (count >= Integer.parseInt(limit) && Integer.parseInt(limit) != 0 && !adddonChildModel.isSelect()) {
                            showToast("You can't select more than " + limit);
                            if (adddonChildModel.isSelect())
                                onItemClick.position(getAdapterPosition());
                        } else {
                            // AdddonChildModel adddonChildModel = addOnList.get(getAdapterPosition());
                            adddonChildModel.setSelect(true);
                            onItemClick.position(getAdapterPosition());
                            notifyDataSetChanged();
                        }
                    } else {
                        for (int i = 0; i < addOnList.size(); i++) {
                            addOnList.get(i).setSelect(false);
                        }
                        adddonChildModel.setSelect(true);
                        // adddonChildModel.setSyncSelect(true);
                        onItemClick.position(getAdapterPosition());
                        notifyDataSetChanged();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            addon_iv_cancle.setOnClickListener((View v) -> {
                AdddonChildModel adddonChildModel = addOnList.get(getAdapterPosition());
                if (getAdapterPosition() != -1) {
                    if (!adddonChildModel.isSelect()) {
                        count--;

                        AdddonChildModel addOnModel = addOnList.get(getAdapterPosition());
                        if (onItemClick != null) {
                            onItemClick.cancel(getAdapterPosition());
                        }

                        addOnModel.setSelect(false);
                        notifyDataSetChanged();
                    } else {
                        if (getAdapterPosition() != -1) {
                            onItemClick.cancel(getAdapterPosition());
                        }
                    }

                }


            });
        }

    }

    public void setItems(List<AdddonChildModel> addOnList) {
        this.addOnList = addOnList;
    }

    public void setlimit(String limit) {
        this.limit = limit;
    }


}


