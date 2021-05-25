package com.unipi.developers.multiplicationlearning;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class TeacherActivity extends FullScreen {
    ImageView help;
    TextView logout;
    TextView welcome;
    CardView card_myClasses, card_createClass, card_studentStats;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        help = findViewById(R.id.btn_help);
        logout = findViewById(R.id.btn_log_out2);
        welcome = findViewById(R.id.txt_hello_teacher);
        welcome.setText(getString(R.string.hello_teacher_name)+" "+getIntent().getStringExtra("username"));
        help.setOnClickListener(v->{

        });

        logout.setOnClickListener(v -> {
            finish();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(TeacherActivity.this, LogInActivity.class));
            Toast.makeText(TeacherActivity.this, getString(R.string.logout_success), Toast.LENGTH_LONG).show();
        });

        card_myClasses = findViewById(R.id.card_teacher_my_classes);
        card_createClass = findViewById(R.id.card_teacher_create_class);
        card_studentStats = findViewById(R.id.card_teacher_student_statistics);

        card_myClasses.setOnClickListener(v -> {
            Intent intent = new Intent(TeacherActivity.this, MyClassesActivity.class);
            startActivity(intent);
        });

        card_createClass.setOnClickListener(v -> {
            Intent intent = new Intent(TeacherActivity.this, CreateClassActivity.class);
            intent.putExtra("username",getIntent().getStringExtra("username"));
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
    }
}