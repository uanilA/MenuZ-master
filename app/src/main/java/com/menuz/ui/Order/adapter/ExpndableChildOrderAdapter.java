package com.menuz.ui.Order.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.menuz.R;
import com.menuz.data.model.db.OrderItemModel;

import java.util.HashMap;
import java.util.List;

import static com.menuz.application.MenuZ.getDataManager;

public class ExpndableChildOrderAdapter extends BaseExpandableListAdapter{
    private Handler handler = new Handler(Looper.getMainLooper());
    private int count = 1;
    private OrderItemModel orderGetCount = new OrderItemModel();
    private Context context;
    private ExpandableListAdapter.OnItemCount onItemCount;
    private List<OrderItemModel> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    public ExpndableChildOrderAdapter(Context context, List<OrderItemModel> listDataHeader, HashMap<String, List<String>> listDataChild, ExpandableListAdapter.OnItemCount onItemCount) {
        this.listDataHeader = listDataHeader;
        this.listDataChild=listDataChild;
        this.onItemCount = onItemCount;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (this.listDataChild.get(this.listDataHeader.get(groupPosition).getItemPrimaryKey()).size() != 0)
            return 1;
        else return 0;

    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition).getItemPrimaryKey());

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        OrderItemModel orderItemModel = (OrderItemModel) getGroup(groupPosition);
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert infalInflater != null;
            convertView = infalInflater.inflate(R.layout.selected_item_view_order, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_itemName = convertView.findViewById(R.id.tv_itemName);
        holder.edit_post = convertView.findViewById(R.id.edit_post);
       // holder.delete_post = convertView.findViewById(R.id.delete_post);
        holder.swipeLayout = convertView.findViewById(R.id.selectedItem_view);
        holder.llMinus = convertView.findViewById(R.id.llMinus);
        holder.llPlus = convertView.findViewById(R.id.llPlus);
        holder.tv_price = convertView.findViewById(R.id.tv_price);
        holder.tv_quntity = convertView.findViewById(R.id.tv_quntity);
        holder.tv_itemCount = convertView.findViewById(R.id.tv_itemCount);
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        // Drag From Left
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, holder.swipeLayout.findViewById(R.id.edit_post));
        // Drag From Right

        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.delete_post));
        holder.tv_itemName.setText(orderItemModel.getItemName());
        holder.tv_price.setText("$" + " " + orderItemModel.getTotalamount());

        holder.tv_quntity.setText(orderItemModel.getCountPrice());


        int positionItem = groupPosition + 1;

        holder.tv_itemCount.setText("" + positionItem);
        holder.llPlus.setOnClickListener(v -> {
            if (positionItem != -1) new Thread(() -> {

                count = Integer.parseInt(orderItemModel.getCountPrice()) + 1;
                getDataManager().updatecount(String.valueOf(count), orderItemModel.getItemId());
                orderGetCount = getDataManager().loadCount(orderItemModel.getItemId());
                listDataHeader.get(groupPosition).setCountPrice(orderGetCount.getCountPrice());
                handler.post(() -> {
                    holder.tv_quntity.setText(orderGetCount.getCountPrice());
                    if (count > 0) onItemCount.itemIncrease(groupPosition);
                });


            }).start();

        });
        holder.llMinus.setOnClickListener(v -> {
            if (groupPosition != -1) {

                new Thread(() -> {
                    count = Integer.parseInt(orderItemModel.getCountPrice()) - 1;
                    if (count == 0) {
                        getDataManager().updatecount(String.valueOf("1"), orderItemModel.getItemId());
                        orderGetCount = getDataManager().loadCount(orderItemModel.getItemId());
                    } else {
                        getDataManager().updatecount(String.valueOf(count), orderItemModel.getItemId());
                        orderGetCount = getDataManager().loadCount(orderItemModel.getItemId());

                    }
                    listDataHeader.get(groupPosition).setCountPrice(orderGetCount.getCountPrice());
                    handler.post(() -> {
                        if (String.valueOf(count).equals("0")) {
                            holder.tv_quntity.setText("1");
                            orderItemModel.setCountPrice("1");
                        } else {
                            holder.tv_quntity.setText(orderGetCount.getCountPrice());
                        }
                        if (count > 0) onItemCount.itemDecrease(groupPosition);
                    });


                }).start();

            }

        });

       /* holder.delete_post.setOnClickListener(v -> {
            if (groupPosition != -1) {
                holder.swipeLayout.close(true);
                onItemCount.onDeleteItem(groupPosition);


            }

        });*/


        holder.edit_post.setOnClickListener(v -> {
            if (groupPosition != -1) {
                holder.swipeLayout.close(true);
                onItemCount.editItem(groupPosition);

                /*Intent intent = new Intent(context, SingleBreakFastActivity.class);
                intent.putExtra("itemID", listDataHeader.get(groupPosition).getItemId());
                intent.putExtra("Position", "");
                context.startActivity(intent);*/


            }

        });

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ViewHolder2 holder;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert infalInflater != null;
            convertView = infalInflater.inflate(R.layout.item_subcategory, null);
            holder = new ViewHolder2();
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder2) convertView.getTag();
        }
        holder.recyclerView = convertView.findViewById(R.id.recyclerView);
        holder.view = convertView.findViewById(R.id.view);
        holder.llAddonPrepView = convertView.findViewById(R.id.llAddonPrepView);
        holder.txtNuofGuest = convertView.findViewById(R.id.txtNuofGuest);
        final List<String> childTextList = (List<String>) getChild(groupPosition, childPosition);
        if (childTextList.size()!=0) {
            holder.recyclerView.setVisibility(View.VISIBLE);
            holder.txtNuofGuest.setVisibility(View.VISIBLE);
            holder.txtNuofGuest.setText(listDataHeader.get(groupPosition).getNuofguest());
            holder.view.setVisibility(View.VISIBLE);
            holder.llAddonPrepView.setVisibility(View.VISIBLE);
            convertView.setVisibility(View.VISIBLE);
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
            holder.recyclerView.setAdapter(new ExpandableChildAdapter(childTextList,context));
        } else {
            holder.recyclerView.setVisibility(View.GONE);
            holder.llAddonPrepView.setVisibility(View.GONE);
            convertView.setVisibility(View.GONE);
            holder.txtNuofGuest.setVisibility(View.GONE);
            holder.view.setVisibility(View.GONE);
        }


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    private class ViewHolder {
        private TextView tv_itemName, tv_quntity;
        private TextView tv_price;
        private TextView tv_itemCount;
        private RelativeLayout llPlus, llMinus;
        //private LinearLayout delete_post;
        private LinearLayout edit_post;
        private SwipeLayout swipeLayout;
    }

    private class ViewHolder2 {
        //  ImageView checkbox2;
        LinearLayout llAddonPrepView;
        private View view;
        private RecyclerView recyclerView;
        private TextView txtNuofGuest;

    }

}
