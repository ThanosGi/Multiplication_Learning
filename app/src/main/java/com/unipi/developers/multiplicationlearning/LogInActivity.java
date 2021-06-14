package com.unipi.developers.multiplicationlearning;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.File;
import java.util.Objects;

public class LogInActivity extends FullScreen {
    private FirebaseAuth mAuth;
    TextView txtCreateAccount;
    EditText username;
    EditText passphrase;
    ImageView btn_help;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseFirestore db_json = FirebaseFirestore.getInstance();


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        btn_help = findViewById(R.id.btn_help);
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this,PdfViewerActivity.class);
                intent.putExtra("fromActivity","login");
                startActivity(intent);
            }
        });

        username = findViewById(R.id.et_username);
        passphrase = findViewById(R.id.et_passphrase);

        txtCreateAccount = findViewById(R.id.txt_create_account);
        txtCreateAccount.setOnClickListener(v -> {
            Intent intent = new Intent(LogInActivity.this, CreateAccountActivity.class);
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

    public void log_in(View view) {
        try {
            mAuth.signInWithEmailAndPassword(username.getText().toString(), passphrase.getText().toString())
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IllegalArgumentException exception) {
            Toast.makeText(getApplicationContext(), getString(R.string.empty_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser account) {

        if (account != null) {
            Context context = this;

            db.collection("teachers")
                    .whereEqualTo("email", username.getText().toString())
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot ignored : Objects.requireNonNull(task.getResult())) {
                                Intent intent = new Intent(context, TeacherActivity.class);
                                intent.putExtra("username", username.getText().toString().split("@")[0]);
                                startActivity(intent);
                            }
                        }
                    });
            db.collection("students")
                    .whereEqualTo("username", username.getText().toString())
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot ignored : Objects.requireNonNull(task.getResult())) {

                                DocumentReference docRef = db_json.collection("students").document(Objects.requireNonNull(mAuth.getUid()));
                                docRef.get().addOnCompleteListener(found -> {
                                    if (found.isSuccessful()) {
                                        DocumentSnapshot document_json = found.getResult();
                                        if (Objects.requireNonNull(document_json).exists()) {
                                            Intent intent = new Intent(context, LessonsActivity.class);
                                            intent.putExtra("json", document_json.getString("progress"));
                                            intent.putExtra("wrongs",document_json.getString("wrongs"));
                                            startActivity(intent);
                                        }
                                    }
                                });
                            }
                        }
                    });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}