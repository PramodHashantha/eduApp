package com.s22010578.eduapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class StudentsInClass extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnAddNewStudent, btnRemoveStudent;
    private DatabaseHelper databaseHelper;
    private EditText etStudentEmail;
    private StudentAdapter studentAdapter;
    private int classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_in_class);

        recyclerView = findViewById(R.id.recyclerViewStudentInClass);
        btnAddNewStudent = findViewById(R.id.btnAddNewStudent);
        btnRemoveStudent = findViewById(R.id.btnRemoveStudent);
        etStudentEmail = findViewById(R.id.etStudentEmail);
        databaseHelper = new DatabaseHelper(this);

        // Assume classId is passed as an intent extra
        classId = getIntent().getIntExtra("CLASS_ID", -1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateStudentList();

        btnAddNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentEmail = etStudentEmail.getText().toString().trim();
                if (databaseHelper.addStudentToClass(classId, studentEmail)) {
                    updateStudentList();
                    etStudentEmail.setText("");
                    Toast.makeText(StudentsInClass.this, "Student added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StudentsInClass.this, "Failed to add student", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRemoveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentEmail = etStudentEmail.getText().toString().trim();
                if (databaseHelper.removeStudentFromClass(classId, studentEmail)) {
                    updateStudentList();
                    etStudentEmail.setText("");
                    Toast.makeText(StudentsInClass.this, "Student removed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StudentsInClass.this, "Failed to remove student", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateStudentList() {
        List<String> students = databaseHelper.getStudentsInClass(classId);
        if (studentAdapter == null) {
            studentAdapter = new StudentAdapter(students);
            recyclerView.setAdapter(studentAdapter);
        } else {
            studentAdapter.updateStudents(students);
        }
    }
}
