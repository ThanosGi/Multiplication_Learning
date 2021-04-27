package com.unipi.developers.multiplicationlearning;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TeacherActivity extends FullScreen {
    ImageView help;
    CardView card_myClasses, card_createClass, card_studentStats, card_createStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        help=findViewById(R.id.btn_help);
        help.setOnClickListener(this::logout);

        card_myClasses = findViewById(R.id.card_teacher_my_classes);
        card_createClass = findViewById(R.id.card_teacher_create_class);
        card_studentStats = findViewById(R.id.card_teacher_student_statistics);
        card_createStudent = findViewById(R.id.card_teacher_create_student);


        card_createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherActivity.this, CreateClassActivity.class);
                startActivity(intent);
            }
        });

        card_createStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherActivity.this, SignUpStudentActivity.class);
                intent.putExtra("from","TeacherActivity");
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}