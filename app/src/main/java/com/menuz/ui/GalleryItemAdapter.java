package com.menuz.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.menuz.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryItemAdapter extends RecyclerView.Adapter<GalleryItemAdapter.ViewHolder> {

    private List<GalleryModel.ResultBean.GalleryBean.ItemsBean>itemBean;
    private galleyListener galleyListener;

    GalleryItemAdapter(List<GalleryModel.ResultBean.GalleryBean.ItemsBean> itemBean, galleyListener galleyListener){
        this.itemBean=itemBean;
        this.galleyListener=galleyListener;

    }
    public interface galleyListener{
        void onGallery(int position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GalleryModel.ResultBean.GalleryBean.ItemsBean itemsBean=itemBean.get(position);
        holder.itemName.setText(itemsBean.getItemName());
        String image="http://82.81.11.210:12986/datasnap/rest/tservermethods1/downloadfile/c:%2fimagesAPI%2f/"+itemsBean.getItemImage();
        Picasso.get().load(image).placeholder(R.drawable.img_placeholder).error(R.drawable.img_placeholder).into(holder.ivItemImg);
        Double price=Double.valueOf(itemsBean.getItemPrice());
        holder.txtPrice.setText(String.format("%s", price));


    }

    @Override
    public int getItemCount() {
        return itemBean.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivItemImg;
        LinearLayout llDetail;
        TextView itemName,txtPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            ivItemImg=itemView.findViewById(R.id.ivItemImg);
            itemName=itemView.findViewById(R.id.itemName);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            llDetail=itemView.findViewById(R.id.llDetail);
            llDetail.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.llDetail) {
                int pos = getAdapterPosition();
                galleyListener.onGallery(pos);
            }
        }
    }
}
