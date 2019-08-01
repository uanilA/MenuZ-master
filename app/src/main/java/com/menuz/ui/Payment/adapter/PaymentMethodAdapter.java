package com.menuz.ui.Payment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.data.model.db.PayMethodsModel;
import com.menuz.ui.Home.model.HomeModel;

import java.util.List;

/**
 * Created by mindiii on 5/12/18.
 */

public class
PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.ViewHolder> {

    private Context context;
    private List<PayMethodsModel> paymentModels;
    private OnCarDClick onCarDClick;


    public PaymentMethodAdapter(Context context, List<PayMethodsModel> paymentModels, OnCarDClick onCarDClick) {
        this.context = context;
        this.paymentModels = paymentModels;
        this.onCarDClick = onCarDClick;
    }

    @NonNull
    @Override
    public PaymentMethodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_type_adapter, parent, false);
        return new PaymentMethodAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodAdapter.ViewHolder holder, int position) {
        PayMethodsModel paymentModel = paymentModels.get(position);
        holder.txtMethod.setText(paymentModel.getPayMethodName());

    }

    @Override
    public int getItemCount() {
        return paymentModels.size();
    }

    public interface OnCarDClick {
        void getPos(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtMethod;
        private CardView cardPayment;

        public ViewHolder(View itemView) {
            super(itemView);
            txtMethod = itemView.findViewById(R.id.txtMethod);
            cardPayment = itemView.findViewById(R.id.cardPayment);
            cardPayment.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cardPayment:
                    onCarDClick.getPos(getAdapterPosition());
                    break;
            }

        }
    }
}
