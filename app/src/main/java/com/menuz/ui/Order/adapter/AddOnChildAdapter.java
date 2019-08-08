package com.menuz.ui.Order.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.Utils.MyToast;
import com.menuz.application.MenuZ;
import com.menuz.data.model.db.AdddonChildModel;
import com.menuz.ui.Order.activity.NewOrderActivity;

import java.util.ArrayList;
import java.util.List;


public class AddOnChildAdapter extends RecyclerView.Adapter<AddOnChildAdapter.ViewHolder> {
    private List<AdddonChildModel> addOnList;
    private List<String> allOfChieldSelected;
    private Context context;
    private OnItemClick onItemClick;
    private String limit;
    private int count = 0;
    private String navigation;
    private String edit;
    private String addOnGroupId;


    public AddOnChildAdapter(Context context, List<AdddonChildModel> addOnList, OnItemClick onItemClick, String limit, String navigation, String edit) {
        this.addOnList = addOnList;
        this.context = context;
        this.onItemClick = onItemClick;
        this.navigation = navigation;
        this.edit = edit;
        this.limit = limit;
        allOfChieldSelected = new ArrayList<>();
    }

    @NonNull
    @Override
    public AddOnChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addons_adapter, parent, false);
        return new AddOnChildAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddOnChildAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        AdddonChildModel adddonChildModel = addOnList.get(position);
        holder.addon_item_Name.setText(adddonChildModel.getAddonName());
       try {
           if (adddonChildModel.isMySelect()) {  //&& adddonChildModel.getAddOnItemIdchild().equals(adddonChildModel.getAddonSelectedGroupId())
               if (navigation.equals("neworder")) {
                   holder.addon_iv_cancle.setVisibility(View.VISIBLE);
                   holder.pre_card_view.setBackground(context.getResources().getDrawable(R.drawable.addon_select_view));
               } else {
                   holder.addon_iv_cancle.setVisibility(View.VISIBLE);
                   holder.pre_card_view.setBackground(context.getResources().getDrawable(R.drawable.addon_select_view));
               }
           } else {
               holder.addon_iv_cancle.setVisibility(View.GONE);
               holder.pre_card_view.setBackground(context.getResources().getDrawable(R.drawable.addon_view));
           }
           if (!adddonChildModel.getPreparations().equals("[]")) {
               holder.addonPlusIcon.setVisibility(View.VISIBLE);
           } else {
               holder.addonPlusIcon.setVisibility(View.GONE);
           }
       }catch (Exception e){e.printStackTrace();}
   /* if (navigation.equals("neworder")){
            holder.addon_iv_cancle.setVisibility(View.VISIBLE);
        }else {
            holder.addon_iv_cancle.setVisibility(View.GONE);

        }*/

        holder.pre_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != -1) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                AdddonChildModel adddonChildModel = addOnList.get(position);
                                if (navigation.equals("neworder")) {
                                    int count = 0;
                                    for (AdddonChildModel addOnBean : addOnList) {
                                        if (addOnBean.isMySelect()) {
                                            count++;
                                        }
                                    }
                                    if (count >= Integer.parseInt(limit) && Integer.parseInt(limit) != 0 && !adddonChildModel.isMySelect()) {
                                        showToast("You can't select more than " + limit);
                                        if (adddonChildModel.isMySelect())
                                            onItemClick.position(position);


                                    } else {
                                     // AdddonChildModel adddonChildModel = addOnList.get(getAdapterPosition());
                                        adddonChildModel.setMySelect(true);
                                        addOnList.set(position, adddonChildModel);
                                        onItemClick.position(position);
                                        //  allOfChieldSelected.add(adddonChildModel.getAddonId());
                                        new Thread(() -> {
                                            MenuZ.getDataManager().updateMySelectionAddonChild(true, adddonChildModel.getAddonId());
                                            //  notifyDataSetChanged();
                                        }).start();

                                    }
                                } else {
                                    for (int i = 0; i < addOnList.size(); i++) {
                                        addOnList.get(i).setMySelect(false);
                                    }
                                    adddonChildModel.setMySelect(true);
                                    onItemClick.position(position);
                                    new Thread(() -> {
                                        MenuZ.getDataManager().updateMySelectionAddonChild(true, adddonChildModel.getAddonId());
                                        // notifyDataSetChanged();
                                    }).start();
                                    //  notifyDataSetChanged();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, 500);
                }
            }
        });



       holder.addon_iv_cancle.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                  if (position != -1) {
                      final Handler handler = new Handler();
                      handler.postDelayed(new Runnable() {
                          @Override
                          public void run() {
                              try {
                                  AdddonChildModel adddonChildModel = addOnList.get(position);
                                  if (!adddonChildModel.isSyncSelect) {
                                      count--;
                                      AdddonChildModel addOnModel = addOnList.get(position);
                                      if (onItemClick != null) {
                                          onItemClick.cancel(position);
                                      }
                                      addOnModel.setMySelect(false);
                                      new Thread(() -> {
                                          MenuZ.getDataManager().updateMySelectionAddonChild(false, adddonChildModel.getAddonId());
                                      }).start();
                                      // notifyDataSetChanged();
                                      //  removeSelections(adddonChildModel.getAddonId());z
                                      //  notifyDataSetChanged();
                                  }
                                  if (position != -1) {
                                      onItemClick.cancel(position);
                                  }
                              } catch (Exception e) {
                                  e.printStackTrace();
                              }
                          }
                      }, 500);
                  }
           }
       });

    }

    @Override
    public int getItemCount() {
        return addOnList.size();
    }

    private void showToast(String msg) {
        if (!TextUtils.isEmpty(msg))
            MyToast.getInstance(context).showDasuAlert(msg);
    }


    public interface OnItemClick {
        void position(int position);

        void cancel(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView addon_item_Name;
        private TextView addonPlusIcon;

        private RelativeLayout pre_card_view;
        private ImageView addon_iv_cancle;

        ViewHolder(View itemView) {
            super(itemView);
            addon_item_Name = itemView.findViewById(R.id.pre_item_Name);
            addonPlusIcon = itemView.findViewById(R.id.addonPlusIcon);

            pre_card_view = itemView.findViewById(R.id.pre_card_view);
            addon_iv_cancle = itemView.findViewById(R.id.addon_iv_cancle);

         /*   pre_card_view.setOnClickListener(v -> {
                 try {
                AdddonChildModel adddonChildModel = addOnList.get(getAdapterPosition());
                if (navigation.equals("neworder")) {
                    int count = 0;
                    for (AdddonChildModel addOnBean : addOnList) {
                        if (addOnBean.isMySelect()) {
                            count++;
                        }
                    }
                    if (count >= Integer.parseInt(limit) && Integer.parseInt(limit) != 0 && !adddonChildModel.isMySelect()) {
                        showToast("You can't select more than " + limit);
                        if (adddonChildModel.isMySelect())
                            onItemClick.position(getAdapterPosition());

                    } else {
                        // AdddonChildModel adddonChildModel = addOnList.get(getAdapterPosition());
                        adddonChildModel.setMySelect(true);
                        addOnList.set(getAdapterPosition(), adddonChildModel);
                        onItemClick.position(getAdapterPosition());
                        //  allOfChieldSelected.add(adddonChildModel.getAddonId());
                        new Thread(() -> {
                            MenuZ.getDataManager().updateMySelectionAddonChild(true, adddonChildModel.getAddonId());

                        }).start();
                        notifyDataSetChanged();
                    }

                } else {
                    for (int i = 0; i < addOnList.size(); i++) {
                        addOnList.get(i).setMySelect(false);
                    }
                    adddonChildModel.setMySelect(true);
                    onItemClick.position(getAdapterPosition());
                    new Thread(() -> {
                        MenuZ.getDataManager().updateMySelectionAddonChild(true, adddonChildModel.getAddonId());
                    }).start();
                    notifyDataSetChanged();
                }
                 }catch (IndexOutOfBoundsException e){e.printStackTrace();}
            });
*/
           /* addon_iv_cancle.setOnClickListener((View v) -> {
                try {
                    AdddonChildModel adddonChildModel = addOnList.get(getAdapterPosition());
                    if (getAdapterPosition() != -1) {
                        if (!adddonChildModel.isSyncSelect) {
                            count--;
                            AdddonChildModel addOnModel = addOnList.get(getAdapterPosition());
                            if (onItemClick != null) {
                                onItemClick.cancel(getAdapterPosition());
                            }
                            addOnModel.setMySelect(false);
                            new Thread(() -> {
                                MenuZ.getDataManager().updateMySelectionAddonChild(false, adddonChildModel.getAddonId());
                            }).start();
                            notifyDataSetChanged();
                            //  removeSelections(adddonChildModel.getAddonId());z
                            //  notifyDataSetChanged();
                        } else {
                            if (getAdapterPosition() != -1) {
                                onItemClick.cancel(getAdapterPosition());
                            }
                        }

                    }
                }catch (Exception e){e.printStackTrace();}

            });*/
        }

    }

    public void setItems(List<AdddonChildModel> addOnList) {
        this.addOnList = addOnList;
    }

    public void setlimit(String limit) {
        this.limit = limit;
    }

    public void removeSelections(String id) {
        if (allOfChieldSelected.contains(id))
            allOfChieldSelected.remove(id);
    }

}

