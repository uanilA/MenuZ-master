package com.menuz.ui.Order.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.application.MenuZ;
import com.menuz.data.model.db.AddOnModel;

import java.util.List;

import static com.menuz.application.MenuZ.getDataManager;


public class AddOnAdapter  extends RecyclerView.Adapter<AddOnAdapter.ViewHolder> {
    //private ArrayList<PreparationModal> preparationModalArrayList;
    private List<AddOnModel> addOnList;
    private Context context;
    private  onItemClick onItemClick;
    private String navigation;
    //private NewOrderModel newOrderModel=new NewOrderModel();

    public AddOnAdapter(Context context, List<AddOnModel> addOnList,onItemClick onItemClick,String navigation) {
        this.addOnList = addOnList;
        this.context = context;
        this.onItemClick=onItemClick;
        this.navigation=navigation;
    }


    @NonNull
    @Override
    public AddOnAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addon_view, parent, false);
        return new AddOnAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final AddOnAdapter.ViewHolder holder, final int position) {
        AddOnModel addOnModel = addOnList.get(position);
        holder.addon_item_Name.setText(addOnModel.getAddonsGroupName());

         if (addOnModel.isSelect)  {
             if (navigation.equals("neworder")){
                 holder.addon_iv_cancle.setVisibility(View.VISIBLE);
                 holder.addon_item_prefixCount.setVisibility(View.GONE);
                 holder.pre_card_view.setBackground(context.getResources().getDrawable(R.drawable.addon_select_view));

             }else {
                 holder.addon_iv_cancle.setVisibility(View.GONE);
                 holder.addon_item_prefixCount.setVisibility(View.GONE);
                 holder.pre_card_view.setBackground(context.getResources().getDrawable(R.drawable.addon_select_view));

             }
         }else {
             holder.addon_iv_cancle.setVisibility(View.GONE);
             holder.pre_card_view.setBackground(context.getResources().getDrawable(R.drawable.addon_view));
             holder.addon_item_prefixCount.setVisibility(View.VISIBLE);
         }

        holder.addon_item_limit.setVisibility(View.GONE);
        if (addOnModel.getAddonsGroupIsMandatory().equals("0")){
         holder.rlCircle.setBackground(context.getResources().getDrawable(R.drawable.circular_blue));
            holder.addon_item_prefixCount.setVisibility(View.VISIBLE);
            holder.addon_item_prefixCount.setText(addOnModel.getAddonsGroupMax());
        }else {
            holder.rlCircle.setBackground(context.getResources().getDrawable(R.drawable.circular_view_red));
            holder.addon_item_prefixCount.setVisibility(View.VISIBLE);
            holder.addon_item_prefixCount.setText(addOnModel.getAddonsGroupMax());

        }
    }

    @Override
    public int getItemCount() {
        return addOnList.size();
    }

    public interface onItemClick {
        void position(int position);

        void cancel(int position);

        void isSelected(String isSelected);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView addon_item_Name;
        private RelativeLayout addon_main_view;
        private RelativeLayout pre_card_view;
        private ImageView addon_iv_cancle;
        private TextView addon_item_limit;
        private TextView addon_item_prefixCount;
        private RelativeLayout rlCircle;

        ViewHolder(View itemView) {
            super(itemView);
            addon_item_Name = itemView.findViewById(R.id.pre_item_Name);
            addon_main_view = itemView.findViewById(R.id.addon_main_view);
            addon_iv_cancle = itemView.findViewById(R.id.addon_iv_cancle);
            rlCircle = itemView.findViewById(R.id.rlCircle);
            addon_item_prefixCount = itemView.findViewById(R.id.addon_item_prefixCount);
            pre_card_view = itemView.findViewById(R.id.pre_card_view);
            addon_item_limit = itemView.findViewById(R.id.addon_item_prefix);
            addon_main_view.setOnClickListener(this);
            addon_iv_cancle.setOnClickListener(this);
            pre_card_view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            switch (view.getId()){

                case R.id.pre_card_view:
                    if (getAdapterPosition()!=-1){
                        if (navigation.equals("neworder")){
                            AddOnModel orderAddOnModel = addOnList.get(getAdapterPosition());
                            orderAddOnModel.isSelect = true;
                            onItemClick.position(getAdapterPosition());
                           // new Thread(() -> getDataManager().updateAddonsIsSelect(true,orderAddOnModel.getAddonsGroupPriId(),orderAddOnModel.getAddonsGroupId())).start();
                            onItemClick.isSelected("yes");
                            notifyDataSetChanged();
                        }else {
                            AddOnModel orderAddOnModel = addOnList.get(getAdapterPosition());
                            for (int i = 0; i < addOnList.size(); i++) {
                                addOnList.get(i).isSelect = false;
                            }
                            orderAddOnModel.isSelect = true;
                            onItemClick.position(getAdapterPosition());

                            notifyDataSetChanged();
                        }



                    }

                    break;

                case R.id.addon_iv_cancle:
                    if (getAdapterPosition()!=-1){
                        AddOnModel addOnModel = addOnList.get(getAdapterPosition());
                        if (!addOnModel.isSyncSelect()){
                            addOnModel.isSelect = false;

                            new Thread(() -> {
                                // newOrderModel=getDataManager().geloadOrderId();
                                getDataManager().updateAddonPrepration(false,addOnModel.getAddonsGroupId(),addOnModel.getOrderId(),addOnModel.getAddOnItemID());
                                getDataManager().updateAddonGroup(false, addOnModel.getAddonsGroupId(), MenuZ.getInstance().getOrderId(), addOnModel.getAddOnItemID());
                                getDataManager().updateAddons(false, addOnModel.getAddOnItemID(), addOnModel.getAddonsGroupId(), MenuZ.getInstance().getOrderId());

                            }).start();

                            onItemClick.cancel(getAdapterPosition());
                            notifyDataSetChanged();
                        }else {
                            onItemClick.cancel(getAdapterPosition());

                        }


                    }

            }
        }
    }

    public void  setAddOnList(List<AddOnModel>addOnList){
        this.addOnList=addOnList;
        notifyDataSetChanged();
    }
}

