package com.s22010578.eduapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditClassActivity extends AppCompatActivity {

    private EditText etClassName, etLocation;
    private Button btnUpdateClass, btnDeleteClass;
    private DatabaseHelper databaseHelper;
    private int classId;
    private String teacherEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);

        etClassName = findViewById(R.id.etClassName);
        etLocation = findViewById(R.id.etLocation);  // Corrected the ID for location
        btnUpdateClass = findViewById(R.id.btnUpdateClass);
        btnDeleteClass = findViewById(R.id.btnDeleteClass);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        classId = intent.getIntExtra("class_id", -1);
        String className = intent.getStringExtra("class_name");
        String classLocation = intent.getStringExtra("class_location");
        teacherEmail = intent.getStringExtra("teacherEmail");  // Retrieve the teacher's email from the intent

        etClassName.setText(className);
        etLocation.setText(classLocation);

        btnUpdateClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateClass();
            }
        });

        btnDeleteClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteClass();
            }
        });
    }

    private void updateClass() {
        String className = etClassName.getText().toString().trim();
        String classLocation = etLocation.getText().toString().trim();

        if (className.isEmpty() || classLocation.isEmpty()) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
            return;
        }

        // Pass the teacherEmail when updating the class
        databaseHelper.updateClass(classId, className, classLocation, teacherEmail);
        Toast.makeText(this, "Class updated successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void deleteClass() {
        databaseHelper.deleteClass(classId, teacherEmail);
        Toast.makeText(this, "Class deleted successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
