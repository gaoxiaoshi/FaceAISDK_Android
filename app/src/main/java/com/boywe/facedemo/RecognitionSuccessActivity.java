package com.boywe.facedemo;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecognitionSuccessActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognition_success);

        String name = getIntent().getStringExtra("person_name");
        if (name == null || name.trim().isEmpty()) {
            name = "用户";
        }

        TextView tvGreeting = findViewById(R.id.tv_greeting);
        tvGreeting.setText("你好，" + name + "!");
    }
}