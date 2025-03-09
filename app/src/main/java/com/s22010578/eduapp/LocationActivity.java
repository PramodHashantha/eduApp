package com.s22010578.eduapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LocationActivity extends AppCompatActivity {

    private EditText edStartLocation;
    private EditText edDestinationLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        edStartLocation = findViewById(R.id.edStartLocation);
        edDestinationLocation = findViewById(R.id.edDestinationLocation);

        findViewById(R.id.btnGetDirection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDirectionClicked();
            }
        });
    }

    private void getDirectionClicked() {
        String startLocation = edStartLocation.getText().toString();
        String destinationLocation = edDestinationLocation.getText().toString();

        if (startLocation.isEmpty() || destinationLocation.isEmpty()) {
            Toast.makeText(LocationActivity.this, "Please Enter Start and Destination", Toast.LENGTH_SHORT).show();
        } else {
            getPath(startLocation, destinationLocation);
        }
    }

    private void getPath(String startLocation, String destinationLocation) {
        try {
            Uri uri = Uri.parse("https://www.google.com/maps/dir/" + startLocation + "/" + destinationLocation);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
