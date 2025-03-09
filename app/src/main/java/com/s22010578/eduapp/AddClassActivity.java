package com.s22010578.eduapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddClassActivity extends AppCompatActivity {

    private EditText etClassName, etClassLocation;
    private Button btnAddClass;
    private DatabaseHelper databaseHelper;
    private String teacherEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        etClassName = findViewById(R.id.etClassName);
        etClassLocation = findViewById(R.id.etClassLocation);
        btnAddClass = findViewById(R.id.btnAddClass);
        databaseHelper = new DatabaseHelper(this);

        teacherEmail = getIntent().getStringExtra("teacher_email");

        btnAddClass.setOnClickListener(v -> {
            String className = etClassName.getText().toString().trim();
            String classLocation = etClassLocation.getText().toString().trim();

            if (className.isEmpty() || classLocation.isEmpty()) {
                Toast.makeText(AddClassActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                return;
            }

            databaseHelper.addClass(className, classLocation, teacherEmail);
            Toast.makeText(AddClassActivity.this, "Class added successfully", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
