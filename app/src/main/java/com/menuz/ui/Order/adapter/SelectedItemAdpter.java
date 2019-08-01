package com.menuz.ui.Order.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.menuz.Demo.DemoSingleBreakFastActivity;
import com.menuz.R;
import com.menuz.data.model.db.OrderItemModel;

import java.util.List;

import static com.menuz.application.MenuZ.getDataManager;


public class SelectedItemAdpter extends RecyclerView.Adapter<SelectedItemAdpter.ViewHolder> {
    private List<OrderItemModel> selectedList;
    private Handler handler = new Handler(Looper.getMainLooper());
    private int count = 1;
    private OrderItemModel orderGetCount = new OrderItemModel();
    private Context context;
    private OnItemCount onItemCount;
    private boolean isSelected = true;
    private View lastselected;

    public SelectedItemAdpter(List<OrderItemModel> selectedList, Context context, OnItemCount onItemCount) {
        this.selectedList = selectedList;
        this.context = context;
        this.onItemCount = onItemCount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_item_view, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        OrderItemModel orderItemModel = selectedList.get(position);
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        // Drag From Left
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, holder.swipeLayout.findViewById(R.id.edit_post));

        // Drag From Right
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.delete_post));
        lastselected = holder.llItem;
        for (int i = 0; i < orderItemModel.getArrayList().size(); i++) {
            TextView textView_addon = new TextView(context);
            textView_addon.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            textView_addon.setGravity(Gravity.START);
            textView_addon.setText(orderItemModel.getArrayList().get(i));
            textView_addon.setBackgroundColor(context.getResources().getColor(R.color.lightgray)); // hex color 0xAARRGGBB
            textView_addon.setPadding(20, 5, 20, 20); // in pixels (left, top, right, bottom)
            holder.llItem.addView(textView_addon);

        }


        holder.tv_itemName.setText(orderItemModel.getItemName());
        String amt =orderItemModel.getTotalamount().isEmpty()?orderItemModel.getItemPrice():orderItemModel.getTotalamount();
        holder.tv_price.setText("$" + " " + amt + ".00");
        if (!(orderItemModel.getCountPrice() == null)) {
            handler.post(() -> holder.tv_quntity.setText(orderItemModel.getCountPrice()));
        } else {
            holder.tv_quntity.setText("" + count);
        }

        int positionItem = position + 1;

        holder.tv_itemCount.setText("" + positionItem);


    }

    @Override
    public int getItemCount() {
        return selectedList.size();
    }

    public void removeItem(int position) {
        selectedList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, selectedList.size());
    }

    public interface OnItemCount {
        void itemIncrease(int position);

        void onDeleteItem(int position);

        void itemDecrease(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_itemName, tv_quntity;
        private TextView tv_price;
        private TextView tv_itemCount;
        private RelativeLayout llPlus, llMinus;
        private LinearLayout delete_post;
        private LinearLayout edit_post;
        private LinearLayout llItem;
        private LinearLayout llAddonPrepView;
        // private LinearLayout llPrep;

        private SwipeLayout swipeLayout;

        ViewHolder(View itemView) {
            super(itemView);
            tv_itemName = itemView.findViewById(R.id.tv_itemName);
            edit_post = itemView.findViewById(R.id.edit_post);
            llItem = itemView.findViewById(R.id.llItem);
            llAddonPrepView = itemView.findViewById(R.id.llAddonPrepView);
            //llPrep = itemView.findViewById(R.id.llPrep);

            delete_post = itemView.findViewById(R.id.delete_post);
            swipeLayout = itemView.findViewById(R.id.selectedItem_view);
            llMinus = itemView.findViewById(R.id.llMinus);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_quntity = itemView.findViewById(R.id.tv_quntity);
            tv_itemCount = itemView.findViewById(R.id.tv_itemCount);
            llPlus = itemView.findViewById(R.id.llPlus);
            llPlus.setOnClickListener(this);
            llMinus.setOnClickListener(this);
            delete_post.setOnClickListener(this);
            edit_post.setOnClickListener(this);
            swipeLayout.setOnClickListener(this);

        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            OrderItemModel orderItemModel = selectedList.get(getAdapterPosition());

            switch (v.getId()) {
                case R.id.llPlus:
                    if (getAdapterPosition() != -1) new Thread(() -> {

                        count = Integer.parseInt(orderItemModel.getCountPrice()) + 1;
                        getDataManager().updatecount(String.valueOf(count), orderItemModel.getItemId());
                        orderGetCount = getDataManager().loadCount(orderItemModel.getItemId());
                        selectedList.get(getAdapterPosition()).setCountPrice(orderGetCount.getCountPrice());
                        handler.post(() -> {
                            tv_quntity.setText(orderGetCount.getCountPrice());
                            if (count > 0) onItemCount.itemIncrease(getAdapterPosition());
                        });


                    }).start();


                    break;

                case R.id.llMinus:
                    if (getAdapterPosition() != -1) {

                        new Thread(() -> {
                            count = Integer.parseInt(orderItemModel.getCountPrice()) - 1;
                            if (count == 0) {
                                getDataManager().updatecount(String.valueOf("1"), orderItemModel.getItemId());
                                orderGetCount = getDataManager().loadCount(orderItemModel.getItemId());

                            } else {
                                getDataManager().updatecount(String.valueOf(count), orderItemModel.getItemId());
                                orderGetCount = getDataManager().loadCount(orderItemModel.getItemId());

                            }
                            // orderItemModel.setCountPrice(orderGetCount.getCountPrice());
                            selectedList.get(getAdapterPosition()).setCountPrice(orderGetCount.getCountPrice());
                            handler.post(() -> {
                                if (String.valueOf(count).equals("0")) {
                                    tv_quntity.setText("1");
                                    orderItemModel.setCountPrice("1");
                                } else {
                                    tv_quntity.setText(orderGetCount.getCountPrice());

                                }

                                if (count > 0) onItemCount.itemDecrease(getAdapterPosition());

                            });


                        }).start();

                    }


                    break;

                case R.id.selectedItem_view:
                    if (lastselected != null) {
                        llItem.setVisibility(View.GONE);
                        llAddonPrepView.setVisibility(View.GONE);
                    } else {
                        llItem.setVisibility(View.VISIBLE);
                        llAddonPrepView.setVisibility(View.VISIBLE);

                        //llAddonPrepView.setVisibility(View.VISIBLE);

                    }


                  /*  if (isSelected) {
                        llItem.setVisibility(View.VISIBLE);
                        llAddonPrepView.setVisibility(View.VISIBLE);
                        isSelected = false;
                    } else {
                        isSelected = true;
                        llAddonPrepView.setVisibility(View.GONE);
                        llItem.setVisibility(View.GONE);
                        llItem.removeAllViews();


                    }
*/

                    break;


                case R.id.delete_post:
                    if (getAdapterPosition() != -1) {
                        swipeLayout.close(true);
                        new Thread(() -> {
                            getDataManager().deleteByItemId(orderItemModel.getItemId());
                            getDataManager().deleteByAddonChild(orderItemModel.getItemId());
                            getDataManager().deleteByAddon(orderItemModel.getItemId());
                            getDataManager().deleteByAddonPrep(orderItemModel.getItemId());
                            handler.post(() -> {
                                onItemCount.onDeleteItem(getAdapterPosition());
                                selectedList.remove(getAdapterPosition());

                                notifyDataSetChanged();
                            });
                        }).start();

                    }

                    break;
                case R.id.edit_post:
                    if (getAdapterPosition() != -1) {
                        Intent intent = new Intent(context, DemoSingleBreakFastActivity.class);

                        intent.putExtra("itemID", selectedList.get(getAdapterPosition()).getItemId());
                        intent.putExtra("Position", "");
                        context.startActivity(intent);


                    }

                    break;


            }
        }
    }
}

