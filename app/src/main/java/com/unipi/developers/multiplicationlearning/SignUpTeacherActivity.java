package com.unipi.developers.multiplicationlearning;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUpTeacherActivity extends FullScreen {
    private FirebaseAuth mAuth;
    EditText email;
    EditText password;
    EditText password2;
    TextView txt_create_account;
    AppCompatButton btn_signupteacher;
    ImageView btn_help;

    Context context = this;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_teacher);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        password2 = findViewById(R.id.et_password2);
        txt_create_account=findViewById(R.id.txt_create_account);
        txt_create_account.setOnClickListener(v -> startActivity(new Intent(this,LogInActivity.class)));
        btn_signupteacher = findViewById(R.id.btn_signupteacher);
        btn_signupteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_up_teacher();
            }
        });
        btn_help = findViewById(R.id.btn_help);
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpTeacherActivity.this,PdfViewerActivity.class);
                intent.putExtra("fromActivity","singupteacher");
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

    public void sign_up_teacher() {
        try {
            if (password.getText().toString().equals(password2.getText().toString())) {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
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

            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.pass_not_match), Toast.LENGTH_SHORT).show();
            }
        } catch (IllegalArgumentException exception) {
            Toast.makeText(getApplicationContext(), getString(R.string.empty_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser account) {

        if (account != null) {

            // Create a new user with a first, middle, and last name
            Map<String, Object> user = new HashMap<>();
            user.put("email", email.getText().toString());
            user.put("0000", "Test Class");

            // Add a new document with a generated ID
            db.collection("teachers")
                    .document(account.getUid())
                    .set(user)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(context, getString(R.string.success_sign_in), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context, TeacherActivity.class));
                    })
                    .addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show());
        }
    }
}