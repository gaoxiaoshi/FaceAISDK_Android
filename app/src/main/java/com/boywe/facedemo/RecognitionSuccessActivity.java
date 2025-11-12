package com.boywe.facedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

public class RecognitionSuccessActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognition_success);

        // 设置工具栏，与其他页面保持一致
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        String name = getIntent().getStringExtra("person_name");
        if (name == null || name.trim().isEmpty()) {
            name = "用户";
        }

        TextView tvGreeting = findViewById(R.id.tv_greeting);
        tvGreeting.setText("你好，" + name + "!");

        // 加载头像到布局（圆形裁剪）
        try {
            String avatarPath = getIntent().getStringExtra("avatar_path");
            if (avatarPath != null && !avatarPath.isEmpty()) {
                Bitmap avatarBmp = BitmapFactory.decodeFile(avatarPath);
                ImageView ivAvatar = findViewById(R.id.iv_avatar);
                if (ivAvatar != null && avatarBmp != null) {
                    RoundedBitmapDrawable rounded = RoundedBitmapDrawableFactory.create(getResources(), avatarBmp);
                    rounded.setCircular(true);
                    rounded.setAntiAlias(true);
                    ivAvatar.setImageDrawable(rounded);
                }
            }
        } catch (Exception ignored) {
        }
    }
}