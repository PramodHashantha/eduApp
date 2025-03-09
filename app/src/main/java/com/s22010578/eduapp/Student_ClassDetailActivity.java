package com.s22010578.eduapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class Student_ClassDetailActivity extends AppCompatActivity {

    private int classId;
    private String studentEmail;
    private CardView student_studentsCard, student_quizCard, student_messageCard;
    private RecyclerView quizzesRecyclerView;
    private QuizzesAdapter quizzesAdapter;
    private DatabaseHelper databaseHelper;
    private List<Quiz> quizList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class_detail);

        // Retrieve classId and studentEmail from intent
        Intent intent = getIntent();
        classId = intent.getIntExtra("classId", -1);
        studentEmail = intent.getStringExtra("studentEmail");

        // Find views
        ImageView backFromClass = findViewById(R.id.backFromClass);
        student_studentsCard = findViewById(R.id.student_studentsCard);
        student_quizCard = findViewById(R.id.student_quizCard);
        student_messageCard = findViewById(R.id.student_messageCard);


        // Initialize database helper and get quizzes
        databaseHelper = new DatabaseHelper(this);
        quizList = databaseHelper.getQuizzesForClass(classId);

        // Set up RecyclerView
        quizzesAdapter = new QuizzesAdapter(this, quizList, studentEmail);
        quizzesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        quizzesRecyclerView.setAdapter(quizzesAdapter);

        // Set click listener for back button
        backFromClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current activity when back button is clicked
            }
        });

        student_quizCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle lesson card click
            }
        });

        student_studentsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Student_ClassDetailActivity.this, StudentsInClass.class);
                intent.putExtra("CLASS_ID", classId);
                startActivity(intent);
            }
        });

        student_messageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messagesIntent = new Intent(Student_ClassDetailActivity.this, ChatActivity.class);
                messagesIntent.putExtra("classId", classId);
                startActivity(messagesIntent);
            }
        });
    }
}
