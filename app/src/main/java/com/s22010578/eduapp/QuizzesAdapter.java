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

public class QuizzesAdapter extends RecyclerView.Adapter<QuizzesAdapter.QuizViewHolder> {

    private List<Quiz> quizList;
    private Context context;
    private String studentEmail;

    public QuizzesAdapter(Context context, List<Quiz> quizList, String studentEmail) {
        this.context = context;
        this.quizList = quizList;
        this.studentEmail = studentEmail;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        Quiz quiz = quizList.get(position);
        holder.tvQuizTitle.setText(quiz.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AttemptQuizActivity.class);
                intent.putExtra("quizId", quiz.getId());
                intent.putExtra("studentEmail", studentEmail);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public static class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuizTitle;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuizTitle = itemView.findViewById(R.id.tvQuizTitle);
        }
    }
}
