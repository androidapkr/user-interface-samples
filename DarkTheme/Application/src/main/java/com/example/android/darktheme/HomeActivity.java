package com.example.android.darktheme;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.darktheme.settings.SettingActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        findViewById(R.id.button_setting).setOnClickListener(view -> startActivityForResult(new Intent(HomeActivity.this, SettingActivity.class), 1001));
    }
}