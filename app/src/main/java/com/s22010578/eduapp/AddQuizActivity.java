package com.s22010578.eduapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddQuizActivity extends AppCompatActivity {
    private EditText quizTitleEditText;
    private Button addQuizButton;
    private RecyclerView quizzesRecyclerView;
    private QuizzesAdapter quizzesAdapter;
    private List<Quiz> quizList;
    private DatabaseHelper databaseHelper;
    private int classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);

        quizTitleEditText = findViewById(R.id.quizTitleEditText);
        addQuizButton = findViewById(R.id.addQuizButton);
        quizzesRecyclerView = findViewById(R.id.quizzesRecyclerView);

        databaseHelper = new DatabaseHelper(this);
        classId = getIntent().getIntExtra("classId", -1);
        quizList = databaseHelper.getAllQuizzesForClass(classId);

        quizzesAdapter = new QuizzesAdapter(this, quizList);
        quizzesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        quizzesRecyclerView.setAdapter(quizzesAdapter);

        addQuizButton.setOnClickListener(v -> addQuiz());
    }

    private void addQuiz() {
        String title = quizTitleEditText.getText().toString().trim();
        if (!title.isEmpty()) {
            long quizId = databaseHelper.addQuiz(title, classId);
            if (quizId != -1) {
                Quiz quiz = new Quiz((int) quizId, title, classId);
                quizList.add(quiz);
                quizzesAdapter.notifyDataSetChanged();
                quizTitleEditText.setText("");
            }
        } else {
            Toast.makeText(this, "Please enter a quiz title", Toast.LENGTH_SHORT).show();
        }
    }
}
