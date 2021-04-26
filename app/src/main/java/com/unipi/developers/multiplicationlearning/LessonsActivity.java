package com.unipi.developers.multiplicationlearning;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LessonsActivity extends FullScreen {
    private FirebaseAuth mAuth;
    ImageView help;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        mAuth = FirebaseAuth.getInstance();
        help=findViewById(R.id.btn_help);
        help.setOnClickListener(this::logout);
    }

    private void updateUI(FirebaseUser account){

        if(account == null) {
            Toast.makeText(this, getString(R.string.log_out), Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LogInActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}