package com.unipi.developers.multiplicationlearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpStudentActivity extends FullScreen {
    private FirebaseAuth mAuth;
    EditText username;
    EditText passphrase;
    EditText classid;

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
        setContentView(R.layout.activity_sign_up_student);
        mAuth = FirebaseAuth.getInstance();

        username=findViewById(R.id.et_username);
        passphrase=findViewById(R.id.et_passphrase);
        classid=findViewById(R.id.et_classid);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    public void sign_up_students(View view){
        try{
            mAuth.createUserWithEmailAndPassword(username.getText().toString(), passphrase.getText().toString())
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }catch (IllegalArgumentException exception){
            Toast.makeText(getApplicationContext(), getString(R.string.empty_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser account){

        if(account != null) {
            Toast.makeText(this, getString(R.string.success_sign_in), Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LessonsActivity.class));
        }
    }
}