package com.s22010578.eduapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AttemptQuizActivity extends AppCompatActivity {
    private RecyclerView questionsRecyclerView;
    private QuestionsAdapter questionsAdapter;
    private List<Question> questionList;
    private DatabaseHelper databaseHelper;
    private int quizId;
    private int studentId;
    private Button submitQuizButton;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempt_quiz);

        questionsRecyclerView = findViewById(R.id.questionsRecyclerView);
        submitQuizButton = findViewById(R.id.submitQuizButton);

        databaseHelper = new DatabaseHelper(this);
        quizId = getIntent().getIntExtra("quizId", -1);
        studentId = getIntent().getIntExtra("studentId", -1);
        questionList = databaseHelper.getAllQuestionsForQuiz(quizId);

        questionsAdapter = new QuestionsAdapter(this, questionList);
        questionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        questionsRecyclerView.setAdapter(questionsAdapter);

        submitQuizButton.setOnClickListener(v -> submitQuiz());
    }

    private void submitQuiz() {
        // Calculate the score
        for (int i = 0; i < questionsRecyclerView.getChildCount(); i++) {
            View itemView = questionsRecyclerView.getChildAt(i);
            TextView correctAnswerTextView = itemView.findViewById(R.id.correctAnswerTextView);
            RadioGroup optionsRadioGroup = itemView.findViewById(R.id.optionsRadioGroup);

            int selectedOptionId = optionsRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = itemView.findViewById(selectedOptionId);

            String selectedAnswer = selectedRadioButton.getText().toString();
            String correctAnswer = correctAnswerTextView.getText().toString();

            if (selectedAnswer.equals(correctAnswer)) {
                score++;
            }
        }

        long attemptId = databaseHelper.addAttempt(studentId, quizId, score);

        if (attemptId != -1) {
            Toast.makeText(this, "Quiz submitted! Your score: " + score, Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Error submitting quiz. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
