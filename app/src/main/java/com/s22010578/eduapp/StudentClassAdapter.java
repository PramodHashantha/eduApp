package com.s22010578.eduapp;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StudentClassAdapter extends RecyclerView.Adapter<StudentClassAdapter.ClassViewHolder> {

    private List<Class> classList;
    private Context context;
    private String studentEmail;

    public StudentClassAdapter(List<Class> classList, Context context, String studentEmail) {
        this.classList = classList;
        this.context = context;
        this.studentEmail = studentEmail;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_student, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        Class classItem = classList.get(position);
        holder.tvClassName.setText(classItem.getName());
        holder.tvClassLocation.setText(classItem.getLocation());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Student_ClassDetailActivity.class);
                intent.putExtra("classId", classItem.getId());
                intent.putExtra("studentEmail", studentEmail);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder {
        TextView tvClassName, tvClassLocation;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            tvClassName = itemView.findViewById(R.id.tvClassName);
            tvClassLocation = itemView.findViewById(R.id.tvClassLocation);
        }
    }
}
