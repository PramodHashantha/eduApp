package com.s22010578.eduapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileUserName;
    private TextView profileEmail;
    private TextView profileUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileUserName = findViewById(R.id.profile_UserName);
        profileEmail = findViewById(R.id.profile_Email);
        profileUserType = findViewById(R.id.profile_UserType);

        String email = getIntent().getStringExtra("user_email");
        String userName = getIntent().getStringExtra("user_name");
        String userType = getIntent().getStringExtra("user_type");

        profileUserName.setText(userName);
        profileEmail.setText(email);
        profileUserType.setText(userType);
    }
}
