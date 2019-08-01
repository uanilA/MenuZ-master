package com.menuz.ui.Order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.data.model.db.PrefixModel;

import java.util.List;

/**
 * Created by mindiii on 26/10/18.
 */

public class PrefixAdapter extends RecyclerView.Adapter<PrefixAdapter.ViewHolder> {
    private Context context;
    private List<PrefixModel>prefixModelList;
    private OnClick onClick;
    private int lastSelectedPosition=-1;


    PrefixAdapter(Context context, List<PrefixModel> prefixModelList, OnClick onClick) {
        this.context=context;
        this.prefixModelList=prefixModelList;
        this.onClick=onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prefix_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      PrefixModel prefixModel=prefixModelList.get(position);
      holder.txtPrefix.setText(prefixModel.getPrefixName());

        holder.radioButtonPrefix.setChecked(position == lastSelectedPosition);



    }

    @Override
    public int getItemCount() {
        return prefixModelList.size();
    }

    public interface OnClick {
        void position(int position);

        void selected(String isSelected);

        void selectedItem(String selectedItem, String prefixId);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RadioButton radioButtonPrefix;
        private TextView txtPrefix;
        private RelativeLayout rlParent;

        public ViewHolder(View itemView) {
            super(itemView);
            radioButtonPrefix=itemView.findViewById(R.id.radioButtonPrefix);
            rlParent=itemView.findViewById(R.id.rlParent);
            txtPrefix=itemView.findViewById(R.id.txtPrefix);
            rlParent.setOnClickListener(this);
            radioButtonPrefix.setOnClickListener(this);
            rlParent.setOnClickListener(this);
         /*   View.OnClickListener l = v -> {
                lastSelectedPosition = getAdapterPosition();
                notifyItemRangeChanged(0, prefixModelList.size());
            };

            radioButtonPrefix.setOnClickListener(l);

            rlParent.setOnClickListener(l);*/

        }

        @Override
        public void onClick(View v) {

            PrefixModel tableModel = prefixModelList.get(getAdapterPosition());
                switch (v.getId()) {
                    case R.id.rlParent:
                        lastSelectedPosition = getAdapterPosition();
                        for (int i = 0; i < prefixModelList.size(); i++) {
                            prefixModelList.get(i).isSelect = false;
                        }
                        tableModel.isSelect = true;
                        onClick.selected("yes");
                        onClick.selectedItem(tableModel.getPrefixName(),tableModel.getPrefixId());

                        notifyDataSetChanged();
                        break;

                    case R.id.radioButtonPrefix:
                        lastSelectedPosition = getAdapterPosition();
                        notifyItemRangeChanged(0, prefixModelList.size());
                        for (int i = 0; i < prefixModelList.size(); i++) {
                            prefixModelList.get(i).isSelect = false;
                        }
                        tableModel.isSelect = true;

                        onClick.selected("yes");
                        onClick.selectedItem(tableModel.getPrefixName(),tableModel.getPrefixId());
                        break;

                }
            }


        }
    }

