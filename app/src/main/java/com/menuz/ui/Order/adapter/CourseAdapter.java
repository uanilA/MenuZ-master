package com.menuz.ui.Order.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.ui.Order.model.CourseModel;

import java.util.ArrayList;

/**
 * Created by mindiii on 21/12/18.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private ArrayList<CourseModel> courseModelArrayList;
    private Context context;
    private getCourseName getCourseName;


    public CourseAdapter(ArrayList<CourseModel> courseModelArrayList, Context context,getCourseName getCourseName) {
        this.courseModelArrayList = courseModelArrayList;
        this.context = context;
        this.getCourseName=getCourseName;
    }


    public interface getCourseName{
        void getCourse(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_adapter, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseModel courseModel = courseModelArrayList.get(position);
        if (courseModel.isSelect) {
            holder.txtCourse.setTextColor(context.getResources().getColor(R.color.white));
            holder.imgCourse.setImageResource(courseModel.itemImgActive);
            holder.llParent.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

        }else {
            holder.txtCourse.setTextColor(context.getResources().getColor(R.color.black));
            holder.imgCourse.setImageResource(courseModel.itemImg);
            holder.llParent.setBackgroundColor(context.getResources().getColor(R.color.white));

        }
        holder.txtCourse.setText(courseModel.itemName);

    }

    @Override
    public int getItemCount() {
        return courseModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtCourse;
        ImageView imgCourse;
        LinearLayout llParent;

        public ViewHolder(View itemView) {
            super(itemView);
            txtCourse = itemView.findViewById(R.id.txtCourse);
            imgCourse = itemView.findViewById(R.id.imgCourse);
            llParent = itemView.findViewById(R.id.llParent);
            llParent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            CourseModel courseModel = courseModelArrayList.get(getAdapterPosition());
            switch (v.getId()) {
                case R.id.llParent:
                    for (int i = 0; i < courseModelArrayList.size(); i++) {
                        courseModelArrayList.get(i).isSelect = false;
                    }
                    courseModel.isSelect = true;
                    getCourseName.getCourse(getAdapterPosition());
                    notifyDataSetChanged();
                    break;

            }
        }
    }
}
