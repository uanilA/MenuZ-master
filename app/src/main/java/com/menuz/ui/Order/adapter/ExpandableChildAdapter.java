package com.menuz.ui.Order.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.menuz.R;

import java.util.List;


/**
 * Created by mindiii on 10/12/18.
 */

public class ExpandableChildAdapter extends RecyclerView.Adapter<ExpandableChildAdapter.ViewHolder> {
    private List<String> shiftModalArrayList;
    Context context;


    ExpandableChildAdapter(List<String> shiftModalArrayList, Context context) {
        this.shiftModalArrayList = shiftModalArrayList;
        this.context = context;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_item_subcategory, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ExpandableChildAdapter.ViewHolder holder, final int position) {
        String item = shiftModalArrayList.get(position).trim();

        if (item.contains("Preprations:-")){
            /*bbxvvfvf bbxvvfvf Preprations:- (Lavana Blush בצד,Sesame Sauce בצד)*/
            //For preprations case
            String[] tokens = item.split("Preprations:-");
            String tokenFirst = tokens[0].trim();
            if (tokenFirst.contains("(")){
                //prepartion
                String txtPrep = "<font color='#44D652'>" + " Preprations:- " + "</font>";
                String prep = "<font color='#000000'>" + tokens[1] + "</font>";
                holder.txtAddon.setText(Html.fromHtml(txtPrep.concat(prep)), TextView.BufferType.SPANNABLE);
            }else{
                String txtRemark = "<font color='#44D652'>" + tokens[0] + "</font>";
                String txtPrep = "<font color='#FC1D1D'>" + " Preprations:- " + "</font>";
                String prep = "<font color='#000000'>" + tokens[1] + "</font>";
                if (prep.contains("null ")){
                   String prepnew= prep.replace("null ","");
                    holder.txtAddon.setText(Html.fromHtml(txtRemark.concat(txtPrep).concat(prepnew)), TextView.BufferType.SPANNABLE);

                }else {
                    holder.txtAddon.setText(Html.fromHtml(txtRemark.concat(txtPrep).concat(prep)), TextView.BufferType.SPANNABLE);

                }
            }
        }else{
            //For addOns case
            if (item.contains(":")){
                String[] tokens = item.split(":");
                if (tokens.length<=2){
                    String tokenFirst = tokens[0].trim();

                    if (tokenFirst.contains("(")){
                        String first = "<font color='#000000'>" + tokens[0] + "</font>";
                        String sec = "<font color='#44D652'>" + " :"+ tokens[1] + "</font>";
                        holder.txtAddon.setText(Html.fromHtml(first.concat(sec)), TextView.BufferType.SPANNABLE);
                    }else{
                        String first = "<font color='#FC1D1D'>" + tokens[0] + "</font>";

                        if (tokens[0].length()>1&&tokens[1].contains("(")){
                            String[] subToken = tokens[1].split(" \\(");
                            String sec = "<font color='#44D652'>" + " "+ subToken[0] + "</font>";
                            String third = "<font color='#000000'>" +" ("+ subToken[1] + "</font>";
                            holder.txtAddon.setText(Html.fromHtml(first.concat(sec).concat(third)), TextView.BufferType.SPANNABLE);
                        }else {
                            String sec = "<font color='#44D652'>" + " :"+ tokens[1] + "</font>";
                            holder.txtAddon.setText(Html.fromHtml(first.concat(sec)), TextView.BufferType.SPANNABLE);
                        }

                    }
                }else {
                    String tokenFirst = tokens[0].trim();
                    String finalTxt = "<font color='#FC1D1D'>" + tokenFirst + "</font>";
                    holder.txtAddon.setText(Html.fromHtml(finalTxt), TextView.BufferType.SPANNABLE);

                }
            } else if (!item.contains("(")&&!item.contains(".")){
                holder.txtAddon.setTextColor(context.getResources().getColor(R.color.red));
                holder.txtAddon.setText(item);
            }else if (item.contains(".")&&!item.contains("(")){
                holder.txtAddon.setTextColor(context.getResources().getColor(R.color.lightgreenn));
                holder.txtAddon.setText(item);

            }

            else if (item.contains("(")){
                String[] tokens = item.split("\\(");
                String tokenFirst = tokens[0].trim();
                String subToken = tokens[1].trim();

                String sec = "<font color='#FC1D1D'>" + " "+tokenFirst + "</font>";
                String third = "<font color='#000000'>" +" ("+ subToken + "</font>";
                holder.txtAddon.setText(Html.fromHtml(sec.concat(third)), TextView.BufferType.SPANNABLE);

            }

        }
       /* if (item.contains("(") && item.contains(":")&&!item.contains("Preprations:-")) {
            StringTokenizer tokens = new StringTokenizer(item, "(" + ":");
            String first = tokens.nextToken();
            String second = " : " + tokens.nextToken();
            String third = "(" + tokens.nextToken();
            String textAddon = "<font color='#FC1D1D'>" + first + "</font>";
            String textNewRemark = "<font color='#44D652'>" + second + "</font>";
            String textPrep = "<font color='#000000'>" + third + "</font>";
            holder.txtAddon.setText(Html.fromHtml(textAddon + textNewRemark + textPrep), TextView.BufferType.SPANNABLE);

        } else if (item.contains("(")&&item.contains(")")&&!item.contains("Preprations:-")) {
            StringTokenizer tokens = new StringTokenizer(item, "(");
            String first = tokens.nextToken();
            String second = "(" + tokens.nextToken();
            String textAddon = "<font color='#44D652'>" + first + "</font>";
            String textNewRemark = "<font color='#000000'>" + second + "</font>";
            holder.txtAddon.setText(Html.fromHtml(textAddon + textNewRemark), TextView.BufferType.SPANNABLE);

            } else if (!item.contains("()") && item.contains(",")&&!item.contains("Preprations:-")) {
            holder.txtAddon.setTextColor(context.getResources().getColor(R.color.black));
            holder.txtAddon.setText(item);
        } else if (item.contains("(")&& item.contains(",")&&!item.contains("Preprations:-")) {
            String textAddon;
            String first;
            StringTokenizer tokens = new StringTokenizer(item, "");
            if (tokens.nextToken().equals("null")){
                 first = "";
                 textAddon = "<font color='#FC1D1D'>" + first + "</font>";
            }else {
                 first = tokens.nextToken();
                 textAddon = "<font color='#FC1D1D'>" + first + "</font>";
            }
            String second = "(" + tokens.nextToken();
             //textAddon = "<font color='#FC1D1D'>" + first + "</font>";
            String textNewRemark = "<font color='#000000'>" + second + "</font>";
            holder.txtAddon.setText(Html.fromHtml(textAddon + textNewRemark), TextView.BufferType.SPANNABLE);

          *//*  holder.txtAddon.setTextColor(context.getResources().getColor(R.color.red));
            holder.txtAddon.setText(item);*//*
        } else if (item.contains("Preprations:-")&&item.contains(" ")) {
            StringTokenizer tokens = new StringTokenizer(item, "Preprations:-" +" ");
            String first = tokens.nextToken();
            String second = "Preprations:-";

            String fourth=tokens.nextToken();
            String textAddon = "<font color='#FC1D1D'>" + first + "</font>";
            String textPrep = "<font color='#44D652'>" + second + "</font>";
            String textPrep2 = "<font color='#00000'>" + fourth + "</font>";
         //   String textPreprations = "<font color='#000000'>" + third + "</font>";
            holder.txtAddon.setText(Html.fromHtml(textAddon + textPrep + textPrep2), TextView.BufferType.SPANNABLE);
        }

        else {
            holder.txtAddon.setTextColor(context.getResources().getColor(R.color.red));
            holder.txtAddon.setText(item);
        }*/


    }

    @Override
    public int getItemCount() {
        return shiftModalArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtAddon;
        private TextView txtPrep;
        private TextView txtRemark;

        ViewHolder(View itemView) {
            super(itemView);
            txtAddon = itemView.findViewById(R.id.txtAddon);
            txtPrep = itemView.findViewById(R.id.txtPrep);
            txtRemark = itemView.findViewById(R.id.txtRemark);


        }


    }
}