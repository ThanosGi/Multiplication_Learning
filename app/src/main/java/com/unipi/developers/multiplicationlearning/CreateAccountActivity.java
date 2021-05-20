package com.unipi.developers.multiplicationlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatDelegate;

public class CreateAccountActivity extends FullScreen {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    public void dog_button(View view) {
        Intent intent = new Intent(CreateAccountActivity.this, SignUpStudentActivity.class);
        intent.putExtra("from", "CreateAccountActivity");
        startActivity(intent);
    }

    public void cat_button(View view) {
        startActivity(new Intent(getApplicationContext(), SignUpTeacherActivity.class));
    }
}