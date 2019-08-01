package com.menuz.ui.Payment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.data.model.db.PayMethodsModel;
import com.menuz.data.model.db.PaymentModel;

import java.util.List;

/**
 * Created by mindiii on 5/12/18.
 */

public class PaymentTypeAdapter extends RecyclerView.Adapter<PaymentTypeAdapter.ViewHolder> {

    private Context context;
    private List<PaymentModel> paymentModels;
    private OnClickListener onClickListener;



    public PaymentTypeAdapter(Context context, List<PaymentModel> paymentModels) {
        this.context = context;
        this.paymentModels = paymentModels;

    }

    public interface OnClickListener{
        void getPosition(int position);
    }

    @NonNull
    @Override
    public PaymentTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_method_adapter, parent, false);
        return new PaymentTypeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentTypeAdapter.ViewHolder holder, int position) {
        PaymentModel paymentModel = paymentModels.get(position);
        holder.txtpayment.setText(paymentModel.getPayMethodName());
      /*  holder.txtRefNu.setText(paymentModel.getReference());
        holder.txtPrice.setText(paymentModel.getAmount());*/

    }

    @Override
    public int getItemCount() {
        return paymentModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtpayment;
        private TextView txtRefNu;
        private TextView txtPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            txtpayment = itemView.findViewById(R.id.txtpayment);
            txtRefNu = itemView.findViewById(R.id.txtRefNu);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }

        @Override
        public void onClick(View v) {


        }
    }
}


