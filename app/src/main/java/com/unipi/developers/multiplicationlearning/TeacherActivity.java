package com.unipi.developers.multiplicationlearning;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class TeacherActivity extends FullScreen {
    ImageView help;
    CardView card_myClasses, card_createClass, card_studentStats, card_createStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        help = findViewById(R.id.btn_help);
        help.setOnClickListener(v->{

        });

        card_myClasses = findViewById(R.id.card_teacher_my_classes);
        card_createClass = findViewById(R.id.card_teacher_create_class);
        card_studentStats = findViewById(R.id.card_teacher_student_statistics);
        card_createStudent = findViewById(R.id.card_teacher_create_student);

        card_myClasses.setOnClickListener(v -> {
            Intent intent = new Intent(TeacherActivity.this, MyClassesActivity.class);
            startActivity(intent);
        });

        card_createClass.setOnClickListener(v -> {
            Intent intent = new Intent(TeacherActivity.this, CreateClassActivity.class);
            startActivity(intent);
        });

        card_createStudent.setOnClickListener(v -> {
            Intent intent = new Intent(TeacherActivity.this, SignUpStudentActivity.class);
            intent.putExtra("from", "TeacherActivity");
            startActivity(intent);
        });

        card_studentStats.setOnClickListener(v -> {
            Intent intent = new Intent(TeacherActivity.this, StudentsStatus.class);
            startActivity(intent);
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
        finish();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LogInActivity.class));
        Toast.makeText(this, getString(R.string.logout_success), Toast.LENGTH_LONG).show();
    }
}