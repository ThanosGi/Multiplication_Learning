package com.unipi.developers.multiplicationlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatDelegate;

public class CreateAccountActivity extends FullScreen {

    ImageView btn_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        btn_help = findViewById(R.id.btn_help);
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccountActivity.this,PdfViewerActivity.class);
                intent.putExtra("fromActivity","createaccount");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    public void dog_button(View view) {
        startActivity(new Intent(CreateAccountActivity.this, SignUpStudentActivity.class));
    }

    public void cat_button(View view) {
        startActivity(new Intent(getApplicationContext(), SignUpTeacherActivity.class));
    }
}