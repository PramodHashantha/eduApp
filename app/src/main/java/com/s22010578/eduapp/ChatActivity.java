package com.s22010578.eduapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ListView listViewMessages;
    private EditText editTextMessage;
    private Button buttonSend;
    private List<String> messageList;
    private ArrayAdapter<String> messageAdapter;

    private DatabaseHelper databaseHelper;
    private int currentClassId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        listViewMessages = findViewById(R.id.list_view_messages);
        editTextMessage = findViewById(R.id.edit_text_message);
        buttonSend = findViewById(R.id.button_send);

        messageList = new ArrayList<>();
        messageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messageList);
        listViewMessages.setAdapter(messageAdapter);

        // Initialize the DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Retrieve classId from intent
        currentClassId = getIntent().getIntExtra("classId", -1);

        // Load messages for the current class
        loadMessages();

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void loadMessages() {
        try {
            // Retrieve all messages for the current class from the database
            messageList.clear();
            messageList.addAll(databaseHelper.getAllMessagesForClass(currentClassId));
            // Notify the adapter that the data set has changed
            messageAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String message = editTextMessage.getText().toString().trim();
        if (!message.isEmpty()) {
            // Add the message to the database
            databaseHelper.addMessage(message, currentClassId);
            // Add the message to the list and notify the adapter
            messageList.add(message);
            messageAdapter.notifyDataSetChanged();
            // Clear the input field
            editTextMessage.setText("");
        } else {
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database connection when the activity is destroyed
        databaseHelper.close();
    }
}
