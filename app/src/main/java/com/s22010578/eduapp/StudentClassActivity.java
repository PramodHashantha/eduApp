package com.s22010578.eduapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentClassActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentClassAdapter studentClassAdapter;
    private List<Class> classList;
    private DatabaseHelper databaseHelper;
    private String studentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class);

        recyclerView = findViewById(R.id.recyclerViewStudentClass);
        databaseHelper = new DatabaseHelper(this);

        studentEmail = getIntent().getStringExtra("user_email");

        classList = databaseHelper.getClassesForStudent(studentEmail);
        studentClassAdapter = new StudentClassAdapter(classList, this, studentEmail);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentClassAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        classList.clear();
        classList.addAll(databaseHelper.getClassesForStudent(studentEmail));
        studentClassAdapter.notifyDataSetChanged();
    }
}
