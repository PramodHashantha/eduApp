package com.s22010578.eduapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etLoginEmail, etLoginPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etLoginEmail.getText().toString();
                String password = etLoginPassword.getText().toString();

                DatabaseHelper db = new DatabaseHelper(LoginActivity.this);
                String userType = db.validateUser(email, password);

                if (userType != null) {
                    navigateToDashboard(email, userType);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void navigateToDashboard(String email, String userType) {
        Intent intent;
        switch (userType) {
            case "Teacher":
                intent = new Intent(this, TeacherActivity.class);
                intent.putExtra("key_email", email);
                break;
            case "Student":
                intent = new Intent(this, StudentActivity.class);
                intent.putExtra("key_email", email);
                break;
            case "Parent":
                intent = new Intent(this, ParentActivity.class);
                intent.putExtra("key_email",email);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + userType);
        }
        startActivity(intent);
        finish();
    }

    public void openRegisterActivity(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
