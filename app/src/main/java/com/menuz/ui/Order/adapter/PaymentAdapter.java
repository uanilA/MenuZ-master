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
 * Created by mindiii on 14/11/18.
 */

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {
    private Context context;
    private List<String> prefixModelList;
    private OnClick onClick;
    private int lastSelectedPosition=-1;


    public PaymentAdapter(Context context,List<String>prefixModelList,OnClick onClick){
        this.context=context;
        this.prefixModelList=prefixModelList;
        this.onClick=onClick;
    }

    public interface OnClick{
        void position(int position);
        void selected(String isSelected);
    }
    @NonNull
    @Override
    public PaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prefix_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtPrefix.setText(prefixModelList.get(position));
        holder.radioButtonPrefix.setChecked(position == lastSelectedPosition);
    }



    @Override
    public int getItemCount() {
        return prefixModelList.size();
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


        }

        @Override
        public void onClick(View v) {

            String item=prefixModelList.get(getAdapterPosition());
            switch (v.getId()) {

                case R.id.rlParent:
                    lastSelectedPosition = getAdapterPosition();
                    notifyItemRangeChanged(0, prefixModelList.size());
                    onClick.position(getAdapterPosition());


                    break;

                case R.id.radioButtonPrefix:
                    lastSelectedPosition = getAdapterPosition();
                    notifyItemRangeChanged(0, prefixModelList.size());
                    onClick.position(getAdapterPosition());

                    break;


            }
        }


    }
}
