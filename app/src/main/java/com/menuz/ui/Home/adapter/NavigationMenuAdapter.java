package com.menuz.ui.Home.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.ui.Home.model.NavigationModel;

import java.util.List;


public class NavigationMenuAdapter extends RecyclerView.Adapter<NavigationMenuAdapter.ViewHolder> {
    private Context context;
    private List<NavigationModel> navigationItems;
    private Listener listener;


    public NavigationMenuAdapter(Activity context, List<NavigationModel> navigationItems, Listener listener) {
        this.context = context;
        this.listener=listener;
        this.navigationItems = navigationItems;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nav_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NavigationModel navigationModel=navigationItems.get(position);
        holder.txtHome.setText(navigationModel.itemName);
        holder.ivMenu.setImageResource(navigationModel.itemImg);

        if (navigationModel.isSelect) {
            holder.txtHome.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.llHome.setBackgroundColor(context.getResources().getColor(R.color.lightgray));
            holder.view.setVisibility(View.VISIBLE);
            holder.ivMenu.setImageResource(navigationModel.itemImgActive);
        } else {
            holder.txtHome.setTextColor(context.getResources().getColor(R.color.text));
            holder.llHome.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.view.setVisibility(View.GONE);

            holder.ivMenu.setImageResource(navigationModel.itemImg);
        }
    }

    @Override
    public int getItemCount() {
        return navigationItems.size();
    }

    public interface Listener {
        void pos(int pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivMenu;
        private TextView txtHome;
        private LinearLayout llHome;
        private View view;
        private RelativeLayout rlParent;

        ViewHolder(View itemView) {
            super(itemView);
            ivMenu=itemView.findViewById(R.id.ivMenu);
            txtHome=itemView.findViewById(R.id.txtHome);
            llHome=itemView.findViewById(R.id.llHome);
            view=itemView.findViewById(R.id.view);
            rlParent=itemView.findViewById(R.id.rlParent);
            rlParent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            NavigationModel navigationListModel = navigationItems.get(getAdapterPosition());
            switch (v.getId()) {
                case R.id.rlParent:
                    for (int i = 0; i < navigationItems.size(); i++) {
                        navigationItems.get(i).isSelect = false;
                    }
                    navigationListModel.isSelect = true;
                    listener.pos(getAdapterPosition());
                    notifyDataSetChanged();
                    break;
            }

        }
    }
}
