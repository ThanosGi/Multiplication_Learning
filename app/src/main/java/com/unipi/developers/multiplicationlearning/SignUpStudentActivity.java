package com.unipi.developers.multiplicationlearning;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUpStudentActivity extends FullScreen {
    private FirebaseAuth mAuth;
    EditText username;
    EditText passphrase;
    EditText class_id;
    Context context = this;
    FirebaseFirestore db_exists = FirebaseFirestore.getInstance();
    FirebaseFirestore db_auth = FirebaseFirestore.getInstance();
    String from;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_student);
        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.et_username);
        passphrase = findViewById(R.id.et_passphrase);
        class_id = findViewById(R.id.et_classid);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    public void sign_up_students(View view) {
        try {
            db_exists.collection("classes")
                    .whereEqualTo(class_id.getText().toString(), true)
                    .get()
                    .addOnSuccessListener(found -> {
                        if (found.getDocuments().isEmpty()) {
                            Toast.makeText(context, getString(R.string.class_does_not_exist), Toast.LENGTH_LONG).show();
                        } else {
                            create_account();
                        }
                    });

        } catch (IllegalArgumentException exception) {
            Toast.makeText(getApplicationContext(), getString(R.string.empty_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void create_account() {
        mAuth.createUserWithEmailAndPassword(username.getText().toString(), passphrase.getText().toString())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        if (from.equals("CreateAccountActivity")) {
                            updateUI(mAuth.getCurrentUser());
                        }else{
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUI(FirebaseUser account) {
        if (account != null) {

            Map<String, Object> user = new HashMap<>();
            user.put("username", username.getText().toString());
            user.put("classId", class_id.getText().toString());
            user.put("progress", "{\"0\":{\"success\":0},\"1\":{\"success\":0},\"2\":{\"success\":0},\"test1\":{\"success\":0},\"3\":{\"success\":0},\"4\":{\"success\":0},\"5\":{\"success\":0},\"test2\":{\"success\":0},\"6\":{\"success\":0},\"7\":{\"success\":0},\"8\":{\"success\":0},\"test3\":{\"success\":0},\"9\":{\"success\":0},\"finalTest\":{\"success\":0}}");

            db_auth.collection("students")
                    .document(account.getUid())
                    .set(user)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(context, getString(R.string.success_sign_in), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context, LessonsActivity.class));

                    }).addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show());
        }
    }
}