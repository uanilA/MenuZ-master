package com.menuz.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menuz.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryMenuAdapter extends RecyclerView.Adapter<GalleryMenuAdapter.ViewHolder> {
    private List<GalleryModel.ResultBean.GalleryBean>itemsBeanList;
    private onItemClick onItemClick;

    GalleryMenuAdapter(List<GalleryModel.ResultBean.GalleryBean> itemsBeanList, onItemClick onItemClick){
        this.itemsBeanList=itemsBeanList;
        this.onItemClick=onItemClick;
    }

    public interface onItemClick{
        void onItemPosition(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_menu_adapter, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GalleryModel.ResultBean.GalleryBean galleryBean=itemsBeanList.get(position);
        holder.ivName.setText(galleryBean.getGroupName());
        String image="http://82.81.11.210:12986/datasnap/rest/tservermethods1/downloadfile/c:%2fimagesAPI%2f/"+galleryBean.getGroupImage();
        Picasso.get().load(image).placeholder(R.drawable.img_placeholder).error(R.drawable.img_placeholder).into(holder.ivImg);


    }

    @Override
    public int getItemCount() {
        return itemsBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivImg;
        RelativeLayout rlData;
        TextView ivName;
        public ViewHolder(View itemView) {
            super(itemView);
            ivImg=itemView.findViewById(R.id.ivImg);
            rlData=itemView.findViewById(R.id.rlData);
            ivName=itemView.findViewById(R.id.ivName);
            rlData.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.rlData) {/*             GalleryModel.ResultBean.GalleryBean galleryBean=itemsBeanList.get(getAdapterPosition());
                  for (int i = 0; i < itemsBeanList.size(); i++) {
                      itemsBeanList.get(i).setSelect(false);
                  }
                  galleryBean.setSelect(true);
                  notifyDataSetChanged();*/
                onItemClick.onItemPosition(getAdapterPosition());
            }
        }
    }
}
