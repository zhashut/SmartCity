package com.zhashut.smartcity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.zhashut.smartcity.R;

public class TestActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        preferences = getSharedPreferences("config", MODE_PRIVATE);
        TextView tv_token = findViewById(R.id.tv_token);
        String token = preferences.getString("token", "");
        tv_token.setText(token);
    }
}