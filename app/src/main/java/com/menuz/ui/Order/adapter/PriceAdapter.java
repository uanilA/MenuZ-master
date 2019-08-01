package com.menuz.ui.Order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.data.model.db.PriceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mindiii on 20/12/18.
 */

public class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.ViewHolder> {

    private List<PriceModel> priceModels;
    private selectPrice selectPrice;
    Context context;

    public PriceAdapter(Context context,List<PriceModel>priceModelArrayList,selectPrice selectPrice){
        this.priceModels=priceModelArrayList;
        this.selectPrice=selectPrice;
        this.context=context;
    }

    public interface selectPrice{
        void getPrice(int position);
    }
    @NonNull
    @Override
    public PriceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.price_adapter, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PriceAdapter.ViewHolder holder, int position) {
      PriceModel priceModel=priceModels.get(position);
      if (priceModel.isSelect){
          holder.rlParent.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
          holder.txtPrice.setTextColor(context.getResources().getColor(R.color.white));
          holder.txtQuantity.setTextColor(context.getResources().getColor(R.color.white));
      }else {
          holder.rlParent.setBackgroundColor(context.getResources().getColor(R.color.white));
          holder.txtPrice.setTextColor(context.getResources().getColor(R.color.colorPrimary));
          holder.txtQuantity.setTextColor(context.getResources().getColor(R.color.black));

      }
      holder.txtQuantity.setText(priceModel.getPricelistName());
      holder.txtPrice.setText(priceModel.getPricevaluesPrice());

    }

    @Override
    public int getItemCount() {
        return priceModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RelativeLayout rlParent;
        TextView txtQuantity;
        TextView txtPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            txtQuantity=itemView.findViewById(R.id.txtQuantity);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            rlParent=itemView.findViewById(R.id.rlParent);
            rlParent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            PriceModel priceModel=priceModels.get(getAdapterPosition());
            switch (v.getId()){
                case R.id.rlParent:
                    for (int i = 0; i < priceModels.size(); i++) {
                        priceModels.get(i).isSelect = false;
                    }
                    priceModel.isSelect = true;
                    selectPrice.getPrice(getAdapterPosition());
                    notifyDataSetChanged();
                    break;
            }
        }
    }
}
