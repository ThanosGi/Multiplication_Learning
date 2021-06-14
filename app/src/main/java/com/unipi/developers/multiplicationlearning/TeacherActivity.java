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
    ImageView btn_help;
    TextView logout;
    TextView welcome;
    CardView card_myClasses, card_createClass, card_studentStats;
    private long backPressedTime;
    private Toast backToast;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        btn_help = findViewById(R.id.btn_help);
        logout = findViewById(R.id.btn_log_out2);
        welcome = findViewById(R.id.txt_hello_teacher);
        welcome.setText(getString(R.string.hello_teacher_name) + " " + getIntent().getStringExtra("username"));
        btn_help.setOnClickListener(v -> {
            Intent intent = new Intent(TeacherActivity.this,PdfViewerActivity.class);
            intent.putExtra("fromActivity","teacher");
            startActivity(intent);
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

        card_myClasses.setOnClickListener(v -> startActivity(new Intent(TeacherActivity.this, MyClassesActivity.class)));

        card_createClass.setOnClickListener(v -> startActivity(new Intent(TeacherActivity.this, CreateClassActivity.class).putExtra("username", getIntent().getStringExtra("username"))));

        card_studentStats.setOnClickListener(v -> startActivity(new Intent(TeacherActivity.this, StudentsStatusActivity.class)));

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
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            finishAffinity();
        } else {
            backToast = Toast.makeText(getApplicationContext(), getString(R.string.back_press_exit), Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}