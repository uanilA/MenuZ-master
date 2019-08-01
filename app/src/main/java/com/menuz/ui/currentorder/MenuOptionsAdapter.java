package com.menuz.ui.currentorder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.ui.Home.model.NavigationModel;

import java.util.List;

public class MenuOptionsAdapter extends RecyclerView.Adapter<MenuOptionsAdapter.ViewHolder> {
    private List<NavigationModel> navigationItems;
    private Listener listener;

    public interface Listener {
        void pos(int pos);
    }
    public MenuOptionsAdapter(List<NavigationModel> navigationItems, Listener listener) {
        this.listener=listener;
        this.navigationItems = navigationItems;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NavigationModel navigationModel=navigationItems.get(position);
        holder.txtHome.setText(navigationModel.itemName);
        holder.ivMenu.setImageResource(navigationModel.itemImg);


    }

    @Override
    public int getItemCount() {
        return navigationItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivMenu;
        private TextView txtHome;
        private RelativeLayout rlParent;
        public ViewHolder(View itemView) {
            super(itemView);

            ivMenu=itemView.findViewById(R.id.ivMenu);
            txtHome=itemView.findViewById(R.id.txtHome);

            rlParent=itemView.findViewById(R.id.rlParent);
            rlParent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            NavigationModel navigationListModel = navigationItems.get(getAdapterPosition());
            if (v.getId() == R.id.rlParent) {
                for (int i = 0; i < navigationItems.size(); i++) {
                    navigationItems.get(i).isSelect = false;
                }
                navigationListModel.isSelect = true;
                listener.pos(getAdapterPosition());
                notifyDataSetChanged();
            }

        }
    }
}
