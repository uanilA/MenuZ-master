package com.menuz.ui.Order.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.menuz.R;
import com.menuz.application.MenuZ;
import com.menuz.data.model.db.ItemModel;
import com.menuz.data.model.db.OrderItemModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.auth.AuthScope;
import cz.msebera.android.httpclient.auth.UsernamePasswordCredentials;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

import static com.menuz.application.MenuZ.getDataManager;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<OrderItemModel> selectedItemList;
    private List<ItemModel> shiftModalArrayList;
    private OnItemClick onItemClick;
    private Context context;
    private int count;
    private long mLastClickTime = 0;
    private String groupName = "";
    private Handler handler=new Handler(Looper.getMainLooper());


    public ItemAdapter(String groupName ,Context context, List<ItemModel> shiftModalArrayList, OnItemClick onItemClick) {
        this.shiftModalArrayList = shiftModalArrayList;
        this.context = context;
        this.onItemClick = onItemClick;
        this.groupName = groupName;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shift_item_view, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ItemModel itemModel = shiftModalArrayList.get(position);
        holder.itemName.setText(itemModel.getItemName());
        holder.itemPrice.setText(itemModel.getItemPrice() + ".00");
        holder.itemDes.setText(groupName);
        String image="http://82.81.11.210:12986/datasnap/rest/tservermethods1/downloadfile/c:%2fimagesAPI%2f/"+itemModel.getItemImage();
        try {
            System.out.println(image);
            Glide.with(context).load(image).error(R.drawable.img_placeholder).into(holder.imgItem);
            //Picasso.get().load(image).placeholder(R.drawable.img_placeholder).error(R.drawable.img_placeholder).into(holder.imgItem);
            if (itemModel.isSelect) {
                holder.check_box.setVisibility(View.GONE);
                holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                holder.itemName.setTextColor(context.getResources().getColor(R.color.white));
                holder.itemPrice.setTextColor(context.getResources().getColor(R.color.white));
                holder.llMinus.setBackground(context.getDrawable(R.drawable.bg_button_drawable));
                holder.llPlus.setBackground(context.getDrawable(R.drawable.bg_button_drawable));
                holder.tv_quntity.setTextColor(context.getResources().getColor(R.color.black));
                holder.txtMinus.setTextColor(context.getResources().getColor(R.color.white));
                holder.txtPlus.setTextColor(context.getResources().getColor(R.color.white));
                holder.llCount.setVisibility(View.VISIBLE);
                holder.llCount.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            } else {
                holder.check_box.setVisibility(View.GONE);
                holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                holder.itemName.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                holder.itemPrice.setTextColor(context.getResources().getColor(R.color.graynew1));
                holder.llCount.setVisibility(View.GONE);
                holder.llMinus.setBackground(context.getDrawable(R.drawable.bg_button_drawable));
                holder.llPlus.setBackground(context.getDrawable(R.drawable.bg_button_drawable));
                holder.tv_quntity.setTextColor(context.getResources().getColor(R.color.black));
                holder.txtMinus.setTextColor(context.getResources().getColor(R.color.white));
                holder.txtPlus.setTextColor(context.getResources().getColor(R.color.white));
                holder.tv_quntity.setTextColor(context.getResources().getColor(R.color.black));
                holder.llCount.setBackgroundColor(context.getResources().getColor(R.color.white));
            }
        }catch (Exception e){e.printStackTrace();}

        /*new Thread(() -> {
            //newOrderModel= getDataManager().geloadOrderId();
            selectedItemList = getDataManager().getAllorderItem(MenuZ.getInstance().getOrderId());
            handler.post(() -> {
                for (int j = 0; j < selectedItemList.size(); j++) {
                    if (selectedItemList.get(j).getItemId().equals(shiftModalArrayList.get(position).getItemId())) {
                        holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                        holder.itemName.setTextColor(context.getResources().getColor(R.color.white));
                        holder.txtMinus.setTextColor(context.getResources().getColor(R.color.white));
                        holder.txtPlus.setTextColor(context.getResources().getColor(R.color.white));
                        holder.llMinus.setBackground(context.getDrawable(R.drawable.bg_button_drawable));
                        holder.llPlus.setBackground(context.getDrawable(R.drawable.bg_button_drawable));
                        holder.tv_quntity.setTextColor(context.getResources().getColor(R.color.black));
                        holder.tv_quntity.setText(selectedItemList.get(j).getCountPrice());
                        holder.itemPrice.setTextColor(context.getResources().getColor(R.color.white));
                        holder.llCount.setVisibility(View.VISIBLE);

                        holder.llCount.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

                    }
                }
            });


        }).start();*/


    }

    @Override
    public int getItemCount() {
        return shiftModalArrayList.size();
    }

    public interface OnItemClick {
        void position(int position);
        void itemSelect();
        void itemIncrease(int position);

        void itemDecrease(int position);

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView itemName;
        private TextView itemPrice;
        private TextView itemDes;
        private ImageView check_box,imgItem;
        private CardView cardview;
        private TextView tv_quntity;
        private TextView txtMinus;
        private TextView txtPlus;
        private LinearLayout llCount;
        private RelativeLayout llPlus,llMinus;

        ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            tv_quntity = itemView.findViewById(R.id.tv_quntity);
            txtPlus = itemView.findViewById(R.id.txtPlus);
            txtMinus = itemView.findViewById(R.id.txtMinus);
            check_box = itemView.findViewById(R.id.check_box);
            llPlus = itemView.findViewById(R.id.llPlus);
            imgItem = itemView.findViewById(R.id.imgItem);
            cardview = itemView.findViewById(R.id.cardview);
            llCount = itemView.findViewById(R.id.llCount);
            llMinus = itemView.findViewById(R.id.llMinus);
            itemDes = itemView.findViewById(R.id.itemDes);
            cardview.setOnClickListener(this);
            llPlus.setOnClickListener(this);
            llMinus.setOnClickListener(this);

        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 700) {
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            ItemModel itemModel = shiftModalArrayList.get(getAdapterPosition());
            switch (v.getId()) {
                case R.id.cardview:

                    for (int i = 0; i < shiftModalArrayList.size(); i++) {
                        shiftModalArrayList.get(i).isSelect = false;
                    }
                    itemModel.isSelect = true;


                    onItemClick.position(getAdapterPosition());
                    onItemClick.itemSelect();

                    notifyDataSetChanged();
                    break;

                case R.id.llPlus:
                    if (getAdapterPosition() != -1){
                        new Thread(() -> {
                            selectedItemList = getDataManager().getAllorderItem(MenuZ.getInstance().getOrderId());
                            for (OrderItemModel orderItemModel : selectedItemList) {
                                if (orderItemModel.getItemId().equals(shiftModalArrayList.get(getAdapterPosition()).getItemId())) {
                                    count = Integer.parseInt(orderItemModel.getCountPrice()) + 1;

                                    getDataManager().updatecount(String.valueOf(count), itemModel.getItemId());
                                    orderItemModel.setCountPrice(String.valueOf(count));
                                    handler.post(() -> {
                                        tv_quntity.setText(""+count);
                                        if (count > 0)
                                            onItemClick.itemIncrease(getAdapterPosition());
                                    });
                                }


                            }


                        }).start();
                    }






                    break;

                case R.id.llMinus:
                    if (getAdapterPosition() != -1) {
                        new Thread(() -> {


                            selectedItemList = getDataManager().getAllorderItem(MenuZ.getInstance().getOrderId());
                            for (OrderItemModel orderItemModel : selectedItemList) {
                                if (orderItemModel.getItemId().equals(shiftModalArrayList.get(getAdapterPosition()).getItemId())) {
                                    count = Integer.parseInt(orderItemModel.getCountPrice()) - 1;

                                    getDataManager().updatecount(String.valueOf(count), itemModel.getItemId());

                                    // itemModel.setCountPrice(orderItemModel.getCountPrice());
                                    handler.post(() -> {
                                        if (String.valueOf(count).equals("0")) {
                                            tv_quntity.setText("1");
                                            orderItemModel.setCountPrice("1");
                                            new Thread(() -> getDataManager().updatecount(String.valueOf(orderItemModel.getCountPrice()), itemModel.getItemId())).start();

                                        } else {
                                            orderItemModel.setCountPrice(String.valueOf(count));
                                            tv_quntity.setText(""+count);

                                        }
                                        if (count > 0)
                                            onItemClick.itemIncrease(getAdapterPosition());
                                    });
                                }


                            }


                        }).start();
                    }


            }
            }
        }

   /* private void downloadFile(String stringURL, ImageView imageView)
    {
        Bitmap bmImg = null;

        try
        {
            DefaultHttpClient client = new DefaultHttpClient();
            client.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), new UsernamePasswordCredentials("admin", "admin"));
            HttpGet request = new HttpGet(stringURL);
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            bmImg = BitmapFactory.decodeStream(inputStream);
           imageView.setImageBitmap(bmImg);
     }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
    }





