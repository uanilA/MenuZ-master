package com.menuz.ui.Order.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.Utils.MyToast;
import com.menuz.application.MenuZ;
import com.menuz.data.model.db.AddonPreprationModel;
import com.menuz.data.model.db.NewOrderModel;
import com.menuz.data.model.db.OrderPreparationAddonModel;
import com.menuz.data.model.db.PrefixModel;

import java.util.ArrayList;
import java.util.List;

import static com.menuz.application.MenuZ.getDataManager;


public class AddOnPreAdapter  extends RecyclerView.Adapter<AddOnPreAdapter.ViewHolder> {
    private List<AddonPreprationModel> addOnList;
    private Context context;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Listener listener;
    private List<PrefixModel>prefixModelList=new ArrayList<>();
    private String selectedItemPrefix="",selectedPrefix="",SprefixId,navigation="",edit="";


    public AddOnPreAdapter(Context context, List<AddonPreprationModel> addOnList, Listener listener,String navigation,String edit) {
        this.edit=edit;
        this.addOnList = addOnList;
        this.navigation=navigation;
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public AddOnPreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addons_prepration, parent, false);
        return new AddOnPreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddOnPreAdapter.ViewHolder holder, final int position) {
        AddonPreprationModel preparationModal = addOnList.get(position);
        holder.addon_item_Name.setText(preparationModal.getPreparationName());

        if (preparationModal.isSelect()&&navigation.equals("neworder")){
            holder.addon_item_prefix.setVisibility(View.VISIBLE);
            holder.addon_iv_cancle.setVisibility(View.VISIBLE);
            holder.pre_card_view.setBackground(context.getResources().getDrawable(R.drawable.addon_select_view));
        }else {
            holder.addon_item_prefix.setText("");
            holder.addon_iv_cancle.setVisibility(View.GONE);
            holder.pre_card_view.setBackground(context.getResources().getDrawable(R.drawable.addon_view));
        }

        if (preparationModal.getPrefixName()!=null){
            if (preparationModal.getPrefixName().equals("")){
                holder.addon_item_prefix.setVisibility(View.GONE);
            }else {

                if (!preparationModal.isSelect()) {
                    holder.addon_item_prefix.setVisibility(View.GONE);
                    holder.addon_item_prefix.setText(preparationModal.getPrefixName());
                } else {
                    holder.addon_item_prefix.setVisibility(View.VISIBLE);
                    holder.addon_item_prefix.setText(preparationModal.getPrefixName());

                }

            }
        }


       /* if (navigation.equals("neworder")){
            holder.addon_iv_cancle.setVisibility(View.VISIBLE);
        }else {
            holder.addon_iv_cancle.setVisibility(View.GONE);
        }*/
        holder.pre_card_view.setOnClickListener(v -> {
            AddonPreprationModel orderPreparationModel = addOnList.get(position);

            if (navigation.equals("neworder")){
                popupQuantity(orderPreparationModel.getPreparationIsPrefixed(),orderPreparationModel.getPreparationName(),orderPreparationModel,holder.addon_item_prefix,position);

            }else {
                for (int i = 0; i < addOnList.size(); i++) {
                    addOnList.get(i).setSelect(false);
                }
                orderPreparationModel.setSelect(true);
                listener.position(position,"");
                notifyDataSetChanged();
            }


        });
    }

    @Override
    public int getItemCount() {
        return addOnList.size();
    }

    @SuppressLint("StaticFieldLeak")
    private void popupQuantity(String prefixId, String preprationname, AddonPreprationModel orderPreparationModel, TextView textView,int position) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popup_quantity);


        RecyclerView recyclerPrefix = dialog.findViewById(R.id.recyclerPrefix);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                prefixModelList = getDataManager().getAllPrefix();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                recyclerPrefix.setLayoutManager(new LinearLayoutManager(context));
                PrefixAdapter prefixAdapter = new PrefixAdapter(context, prefixModelList, new PrefixAdapter.OnClick() {
                    @Override
                    public void position(int position) {

                    }

                    @Override
                    public void selected(String isSelected) {
                        selectedItemPrefix = isSelected;
                    }

                    @Override
                    public void selectedItem(String selectedItem, String prefixId) {
                        selectedPrefix=selectedItem;
                        SprefixId=prefixId;

                    }

                });

                recyclerPrefix.setAdapter(prefixAdapter);

            }
        }.execute();

        TextView tvHeader = dialog.findViewById(R.id.tvHeader);
        TextView btnDone = dialog.findViewById(R.id.btnDone);

        tvHeader.setText(preprationname);
        btnDone.setOnClickListener(v -> {
            orderPreparationModel.setSelect(true);

                textView.setText(selectedPrefix);
                orderPreparationModel.setSelect(true);
                listener.position(position,selectedPrefix);
                addOnList.get(position).setPrefixName(selectedPrefix);
                notifyDataSetChanged();
                dialog.dismiss();



            /*if (selectedItemPrefix.equals("") && prefixId.equals("1")) {
                showToast("You must have select atleaset one prefix");
            } else {
                orderPreparationModel.setSelect(true);
                listener.position(position,selectedPrefix);

               *//* new Thread(() -> {
                   // newOrderModel=getDataManager().loadnewOrderId();
                    //NewOrderModel newOrderModel = getDataManager().geloadOrderId();
                    getDataManager().updatePreprationAddon( orderPreparationModel.getAddonsGroupId(), orderPreparationModel.getItemIdAddon(), orderPreparationModel.getAddonId(), orderPreparationModel.getPreparationId(), MenuZ.getInstance().getOrderId(), orderPreparationModel.getPrefixName(), orderPreparationModel.getPrefixId());
                    listener.position(position,selectedPrefix);
                }).start();*//*
                selectedItemPrefix = "";
                textView.setText(selectedPrefix);
                addOnList.get(position).setPrefixName(selectedPrefix);
                notifyDataSetChanged();
                dialog.dismiss();
            }*/


        });
        ImageView img_cancel = dialog.findViewById(R.id.img_cancel);

        img_cancel.setOnClickListener((View v) -> {
            orderPreparationModel.setSelect(true);
           notifyDataSetChanged();
            selectedPrefix="";
            listener.position(position,selectedPrefix);
            dialog.dismiss();
        });
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();


    }

    private void showToast(String msg) {
        if (!TextUtils.isEmpty(msg))
            MyToast.getInstance(context).showDasuAlert(msg);
        //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public interface Listener {
        void position(int position,String prefixName);

        void cancel(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView addon_item_Name;
        private TextView addon_item_prefix;
        private ImageView addon_iv_cancle;
        private RelativeLayout pre_card_view;

        ViewHolder(View itemView) {
            super(itemView);
            addon_item_Name = itemView.findViewById(R.id.pre_item_Name);
            addon_item_prefix = itemView.findViewById(R.id.addon_item_prefix);
            addon_iv_cancle = itemView.findViewById(R.id.addon_iv_cancle);
            pre_card_view = itemView.findViewById(R.id.pre_card_view);

            addon_iv_cancle.setOnClickListener(v -> {

                AddonPreprationModel orderPreparationAddonModel1 = addOnList.get(getAdapterPosition());
                if (!edit.equals("true")){
                    orderPreparationAddonModel1.setSelect(false);
                    listener.cancel(getAdapterPosition());
                    new Thread(() -> {
                        NewOrderModel newOrderModel = getDataManager().geloadOrderId();
                        if (newOrderModel!=null)
                            getDataManager().updatePreprationAddon( orderPreparationAddonModel1.getAddonsGroupId(), orderPreparationAddonModel1.getItemIdAddon(), orderPreparationAddonModel1.getAddonId(), orderPreparationAddonModel1.getPreparationId(), newOrderModel.getOrderId(), "", "");
                        handler.post(() -> {
                            addon_item_prefix.setVisibility(View.GONE);
                            notifyDataSetChanged();
                        });
                    }).start();

                }else {
                    listener.cancel(getAdapterPosition());
                }


            });

            pre_card_view.setOnClickListener(v -> {
                /*OrderPreparationAddonModel orderPreparationAddonModel1 = addOnList.get(getAdapterPosition());

                orderPreparationAddonModel1.setSelect(true);
                listener.cancel(getAdapterPosition());
                new Thread(() -> {
                    newOrderModel=getDataManager().loadnewOrderId();
                    getDataManager().updatePreprationAddon(true, orderPreparationAddonModel1.getAddonsGroupId(), orderPreparationAddonModel1.getItemIdAddon(), orderPreparationAddonModel1.getAddonId(), orderPreparationAddonModel1.getPreparationId(),ORDERID);
                }).start();
                notifyDataSetChanged();*/
            });
        }


    }

}

