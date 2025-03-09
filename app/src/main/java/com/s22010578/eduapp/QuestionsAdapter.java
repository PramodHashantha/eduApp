package com.s22010578.eduapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {
    private List<Question> questionList;
    private Context context;

    public QuestionsAdapter(Context context, List<Question> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_question_attempt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.questionTextView.setText(question.getQuestionText());
        holder.option1RadioButton.setText(question.getOption1());
        holder.option2RadioButton.setText(question.getOption2());
        holder.option3RadioButton.setText(question.getOption3());
        holder.option4RadioButton.setText(question.getOption4());
        holder.correctAnswerTextView.setText(question.getCorrectAnswer());
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionTextView;
        RadioButton option1RadioButton, option2RadioButton, option3RadioButton, option4RadioButton;
        RadioGroup optionsRadioGroup;
        TextView correctAnswerTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
            option1RadioButton = itemView.findViewById(R.id.option1RadioButton);
            option2RadioButton = itemView.findViewById(R.id.option2RadioButton);
            option3RadioButton = itemView.findViewById(R.id.option3RadioButton);
            option4RadioButton = itemView.findViewById(R.id.option4RadioButton);
            optionsRadioGroup = itemView.findViewById(R.id.optionsRadioGroup);
            correctAnswerTextView = itemView.findViewById(R.id.correctAnswerTextView);
            correctAnswerTextView.setVisibility(View.GONE);
        }
    }
}
