package com.unipi.developers.multiplicationlearning;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LessonsActivity extends FullScreen {
    private FirebaseAuth mAuth;
    ImageView help;
    CardView card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, cardTest1, cardTest2, cardTest3, cardFinalTest;
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

        card0 = findViewById(R.id.card0);
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);
        card6 = findViewById(R.id.card6);
        card7 = findViewById(R.id.card7);
        card8 = findViewById(R.id.card8);
        card9 = findViewById(R.id.card9);
        cardTest1 = findViewById(R.id.test1);
        cardTest2 = findViewById(R.id.test2);
        cardTest3 = findViewById(R.id.test3);
        cardFinalTest = findViewById(R.id.finalTest);

        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                startActivity(intent);
            }
        });

        cardTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonsActivity.this, TestActivity.class);
                intent.putExtra("from", "test1");
                intent.putExtra("page", 1);
                startActivity(intent);
            }
        });

        cardTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonsActivity.this, TestActivity.class);
                intent.putExtra("from", "test2");
                startActivity(intent);
            }
        });

        cardTest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonsActivity.this, TestActivity.class);
                intent.putExtra("from", "test3");
                startActivity(intent);
            }
        });

        cardFinalTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonsActivity.this, TestActivity.class);
                intent.putExtra("from", "finalTest");
                startActivity(intent);
            }
        });

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