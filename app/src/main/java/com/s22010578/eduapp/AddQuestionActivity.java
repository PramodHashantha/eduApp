package com.s22010578.eduapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddQuestionActivity extends AppCompatActivity {
    private EditText questionEditText, option1EditText, option2EditText, option3EditText, option4EditText, correctAnswerEditText;
    private Button addQuestionButton;
    private RecyclerView questionsRecyclerView;
    private QuestionsAdapter questionsAdapter;
    private List<Question> questionList;
    private DatabaseHelper databaseHelper;
    private int quizId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        questionEditText = findViewById(R.id.questionEditText);
        option1EditText = findViewById(R.id.option1EditText);
        option2EditText = findViewById(R.id.option2EditText);
        option3EditText = findViewById(R.id.option3EditText);
        option4EditText = findViewById(R.id.option4EditText);
        correctAnswerEditText = findViewById(R.id.correctAnswerEditText);
        addQuestionButton = findViewById(R.id.addQuestionButton);
        questionsRecyclerView = findViewById(R.id.questionsRecyclerView);

        databaseHelper = new DatabaseHelper(this);
        quizId = getIntent().getIntExtra("quizId", -1);
        questionList = databaseHelper.getAllQuestionsForQuiz(quizId);

        questionsAdapter = new QuestionsAdapter(this, questionList);
        questionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        questionsRecyclerView.setAdapter(questionsAdapter);

        addQuestionButton.setOnClickListener(v -> addQuestion());
    }

    private void addQuestion() {
        String questionText = questionEditText.getText().toString().trim();
        String option1 = option1EditText.getText().toString().trim();
        String option2 = option2EditText.getText().toString().trim();
        String option3 = option3EditText.getText().toString().trim();
        String option4 = option4EditText.getText().toString().trim();
        String correctAnswer = correctAnswerEditText.getText().toString().trim();

        if (!questionText.isEmpty() && !option1.isEmpty() && !option2.isEmpty() && !option3.isEmpty() && !option4.isEmpty() && !correctAnswer.isEmpty()) {
            long questionId = databaseHelper.addQuestion(quizId, questionText, option1, option2, option3, option4, correctAnswer);
            if (questionId != -1) {
                Question question = new Question((int) questionId, quizId, questionText, option1, option2, option3, option4, correctAnswer);
                questionList.add(question);
                questionsAdapter.notifyDataSetChanged();
                questionEditText.setText("");
                option1EditText.setText("");
                option2EditText.setText("");
                option3EditText.setText("");
                option4EditText.setText("");
                correctAnswerEditText.setText("");
            }
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }
}
