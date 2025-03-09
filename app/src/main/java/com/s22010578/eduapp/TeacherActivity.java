package com.s22010578.eduapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.util.ArrayList;

public class TeacherActivity extends AppCompatActivity {

    private String etLoginEmail;
    private String userName;
    private String userType;
    private TextView profileUserName;
    private CardView classCard,profileCard,locationCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        etLoginEmail = getIntent().getStringExtra("key_email");
        profileUserName = findViewById(R.id.profile_UserName);
        classCard = findViewById(R.id.ClassCard);
        profileCard = findViewById(R.id.ProfileCard);
        locationCard = findViewById(R.id.LocationCard);

        getUserDetails();


        classCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherActivity.this, ClassActivity.class);
                intent.putExtra("teacher_email", etLoginEmail);
                startActivity(intent);
            }
        });


        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherActivity.this, ProfileActivity.class);
                intent.putExtra("user_email", etLoginEmail);
                intent.putExtra("user_name", userName);
                intent.putExtra("user_type", userType);
                startActivity(intent);
            }
        });

        locationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getUserDetails() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        ArrayList<User> userDetails = databaseHelper.getLoggedInUserDetails(etLoginEmail);
        if (!userDetails.isEmpty()) {
            User user = userDetails.get(0);
            userName = user.getUserName();
            userType = user.getUserType();
            profileUserName.setText(userName);
        }
    }
}
