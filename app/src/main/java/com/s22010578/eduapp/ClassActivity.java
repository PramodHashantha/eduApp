package com.s22010578.eduapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import java.util.List;

public class ClassActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnAddNewClass;
    private TeacherClassAdapter classAdapter;
    private List<Class> classList;
    private DatabaseHelper databaseHelper;
    private String teacherEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        recyclerView = findViewById(R.id.recyclerView);
        btnAddNewClass = findViewById(R.id.btnAddNewClass);
        databaseHelper = new DatabaseHelper(this);

        teacherEmail = getIntent().getStringExtra("teacher_email");

        classList = databaseHelper.getAllClasses(teacherEmail);
        classAdapter = new TeacherClassAdapter(classList, this, teacherEmail);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(classAdapter);

        btnAddNewClass.setOnClickListener(v -> {
            Intent intent = new Intent(ClassActivity.this, AddClassActivity.class);
            intent.putExtra("teacher_email", teacherEmail);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        classList.clear();
        classList.addAll(databaseHelper.getAllClasses(teacherEmail));
        classAdapter.notifyDataSetChanged();
    }
}
