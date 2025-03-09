package com.s22010578.eduapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<String> students;

    public StudentAdapter(List<String> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        String studentEmail = students.get(position);
        holder.studentEmail.setText(studentEmail);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView studentEmail;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentEmail = itemView.findViewById(R.id.studentEmail);
        }
    }

    public void updateStudents(List<String> newStudents) {
        this.students = newStudents;
        notifyDataSetChanged();
    }
}
