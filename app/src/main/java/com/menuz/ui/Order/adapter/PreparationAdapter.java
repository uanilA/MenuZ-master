package com.menuz.ui.Order.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
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
import com.menuz.data.model.db.ItemPreprationModel;
import com.menuz.data.model.db.OrderPreparationModel;
import com.menuz.data.model.db.PrefixModel;

import java.util.ArrayList;
import java.util.List;

import static com.menuz.application.MenuZ.getDataManager;


public class PreparationAdapter extends RecyclerView.Adapter<PreparationAdapter.ViewHolder> {

    //private ArrayList<PreparationModal> preparationModalArrayList;
    private List<ItemPreprationModel> preparationModalArrayList, selected;
    private Context context;
    private OnItemClick onItemClick;
    //private NewOrderModel newOrderModel=new NewOrderModel();
    private List<PrefixModel> prefixModelList = new ArrayList<>();
    private String selectedItemPrefix = "", selectedPrefix, SprefixId = "", navigation = "";

    public PreparationAdapter(Context context, List<ItemPreprationModel> preparationModalArrayList, OnItemClick onItemClick, String navigation) {
        this.preparationModalArrayList = preparationModalArrayList;
        this.context = context;
        this.navigation = navigation;
        this.onItemClick = onItemClick;
        this.selected = new ArrayList<>();
    }

    @NonNull
    @Override
    public PreparationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.preparation_view, parent, false);
        return new PreparationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PreparationAdapter.ViewHolder holder, final int position) {
        ItemPreprationModel preparationModal = preparationModalArrayList.get(position);
        if (preparationModal.isSelect()) {
            if (navigation.equals("neworder")) {
                holder.pre_iv_cancle.setVisibility(View.VISIBLE);
                holder.pre_card_view.setBackground(context.getResources().getDrawable(R.drawable.addon_select_view));

            } else {
                holder.pre_iv_cancle.setVisibility(View.GONE);
                holder.pre_card_view.setBackground(context.getResources().getDrawable(R.drawable.addon_select_view));

            }
        } else {
            holder.pre_iv_cancle.setVisibility(View.GONE);
            holder.pre_card_view.setBackground(context.getResources().getDrawable(R.drawable.addon_view));

        }
        holder.pre_item_Name.setText(preparationModal.getPreparationName());

        if (preparationModal.getPrefixName() != null) {
            if (preparationModal.getPrefixName().equals("")) {
                holder.addon_item_prefix.setVisibility(View.GONE);
            } else {
                holder.addon_item_prefix.setVisibility(View.VISIBLE);
                holder.addon_item_prefix.setText(preparationModal.getPrefixName());
            }
        }

       /* if (navigation.equals("neworder")){
            holder.pre_iv_cancle.setVisibility(View.VISIBLE);
        }else {
            holder.pre_iv_cancle.setVisibility(View.GONE);
        }*/


        holder.pre_card_view.setOnClickListener(v -> {
            ItemPreprationModel orderPreparationModel = preparationModalArrayList.get(position);
            if (navigation.equals("neworder")) {
                popupQuantity(orderPreparationModel.getPreparationIsPrefixed(), orderPreparationModel.getPreparationName(), orderPreparationModel, holder.addon_item_prefix, position);

            } else {

                for (int i = 0; i < preparationModalArrayList.size(); i++) {
                    preparationModalArrayList.get(i).setSelect(false);
                }
                orderPreparationModel.setSelect(true);
                onItemClick.position(position, "");
                notifyDataSetChanged();

            }

        });


    }

    @Override
    public int getItemCount() {
        return preparationModalArrayList.size();
    }

    @SuppressLint("StaticFieldLeak")
    private void popupQuantity(String prefixId, String preprationname, ItemPreprationModel orderPreparationModel, TextView textView, int position) {

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
                        selectedPrefix = selectedItem;
                        SprefixId = prefixId;

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
            preparationModalArrayList.get(position).setPrefixName(selectedPrefix);
            preparationModalArrayList.get(position).setPrefixId(SprefixId);
            onItemClick.position(position, selectedPrefix);
            preparationModalArrayList.get(position).setSelect(true);

              /*  new Thread(() -> {
                    //  newOrderModel=getDataManager().geloadOrderId();
                    //Todo HS
                    getDataManager().updateprepByItem(orderPreparationModel.getItemId(), orderPreparationModel.getPreparationId(), MenuZ.getInstance().getOrderId(), orderPreparationModel.getPrefixName(), orderPreparationModel.getPrefixId(), String.valueOf(orderPreparationModel.getItemPrimaryKey()));
onItemClick.position(position,selectedPrefix);                }).start();*/
            notifyDataSetChanged();
            selectedItemPrefix = "";
            dialog.dismiss();


        });
        ImageView img_cancel = dialog.findViewById(R.id.img_cancel);

        img_cancel.setOnClickListener((View v) -> {
            orderPreparationModel.setSelect(true);
            textView.setText(selectedPrefix);
            preparationModalArrayList.get(position).setPrefixName("");
            preparationModalArrayList.get(position).setPrefixId("");
            onItemClick.position(position, selectedPrefix);
            preparationModalArrayList.get(position).setSelect(true);
            notifyDataSetChanged();

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

    public interface OnItemClick {
        void position(int position, String prefixName);

        void isSelected(String selected);

        void isCancelSelected(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView pre_item_Name, addon_item_prefix;
        private RelativeLayout pre_main_view;
        private ImageView pre_iv_cancle;
        private RelativeLayout pre_card_view;

        ViewHolder(View itemView) {
            super(itemView);
            pre_item_Name = itemView.findViewById(R.id.pre_item_Name);
            pre_main_view = itemView.findViewById(R.id.pre_main_view);
            addon_item_prefix = itemView.findViewById(R.id.addon_item_prefix);
            pre_iv_cancle = itemView.findViewById(R.id.pre_iv_cancle);
            pre_card_view = itemView.findViewById(R.id.pre_card_view);

            pre_main_view.setOnClickListener(this);
            pre_iv_cancle.setOnClickListener(this);
            pre_card_view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ItemPreprationModel orderPreparationModel = preparationModalArrayList.get(getAdapterPosition());
            switch (view.getId()) {
                case R.id.pre_iv_cancle:
                    if (getAdapterPosition() != -1) {
                        orderPreparationModel.setSelect(false);
                        selectedPrefix = "";
                        new Thread(() -> {
                            //newOrderModel=getDataManager().geloadOrderId();
                            //addon_item_prefix.setVisibility(View.GONE);
                            //Todo HS
                            getDataManager().updateprepByItem(orderPreparationModel.getItemId(), orderPreparationModel.getPreparationId(), MenuZ.getInstance().getOrderId(), orderPreparationModel.getPrefixName(), orderPreparationModel.getPrefixId(), String.valueOf(orderPreparationModel.getItemPrimaryKey()));
                        }).start();
                        onItemClick.isCancelSelected(getAdapterPosition());
                        notifyDataSetChanged();

                    }


                    break;
            }
        }
    }

}

