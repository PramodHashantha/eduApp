//package com.s22010578.eduapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//
//import java.util.List;
//
//public class CreateQuizActivity extends AppCompatActivity {
//    private EditText quizTitleEditText;
//    private Button createQuizButton;
//    private RecyclerView questionsRecyclerView;
//    private QuestionsAdapter questionsAdapter;
//    private List<Question> questionList;
//    private int classId;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create_quiz);
//
//        quizTitleEditText = findViewById(R.id.quizTitleEditText);
//        createQuizButton = findViewById(R.id.createQuizButton);
//        questionsRecyclerView = findViewById(R.id.questionsRecyclerView);
//        classId = getIntent().getIntExtra("classId", -1);
//        questionList = new ArrayList<>();
//        questionsAdapter = new QuestionsAdapter(questionList);
//
//        questionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        questionsRecyclerView.setAdapter(questionsAdapter);
//
//        createQuizButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                createQuiz();
//            }
//        });
//    }
//
//    private void createQuiz() {
//        String title = quizTitleEditText.getText().toString().trim();
//        if (!title.isEmpty()) {
//            DatabaseHelper databaseHelper = new DatabaseHelper(this);
//            long quizId = databaseHelper.addQuiz(title, classId);
//            if (quizId != -1) {
//                Intent intent = new Intent(CreateQuizActivity.this, AddQuestionActivity.class);
//                intent.putExtra("quizId", quizId);
//                startActivity(intent);
//            }
//        } else {
//            Toast.makeText(this, "Please enter a quiz title", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
