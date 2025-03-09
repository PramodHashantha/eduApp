package com.s22010578.eduapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Teacher_ClassDetailActivity extends AppCompatActivity {

    private int classId;
    private String teacherEmail;
    private CardView studentsCard,lessonCard,messageCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class_detail);

        // Retrieve classId and teacherEmail from intent
        Intent intent = getIntent();
        classId = intent.getIntExtra("classId", -1);
        teacherEmail = intent.getStringExtra("teacherEmail");

        // Find views
        ImageView backFromClass = findViewById(R.id.backFromClass);
        studentsCard = findViewById(R.id.teacher_studentsCard);
        lessonCard= findViewById(R.id.teacher_lessonCard);
        messageCard = findViewById(R.id.teacher_messageCard);

        // Set click listener for back button
        backFromClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current activity when back button is clicked
            }
        });

        lessonCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lessonsIntent = new Intent(Teacher_ClassDetailActivity.this, AddQuizActivity.class);
                startActivity(lessonsIntent);
            }
        });

        studentsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Teacher_ClassDetailActivity.this,StudentsInClass.class);
                intent.putExtra("CLASS_ID",classId);
                startActivity(intent);
            }
        });

        messageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messagesIntent = new Intent(Teacher_ClassDetailActivity.this, ChatActivity.class);
                messagesIntent.putExtra("classId", classId);
                startActivity(messagesIntent);
            }
        });

    }

}
