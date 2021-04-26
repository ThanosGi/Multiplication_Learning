package com.unipi.developers.multiplicationlearning;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    Context context=this;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_teacher);
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.et_email);
        password=findViewById(R.id.et_password);
        password2=findViewById(R.id.et_password2);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    public void sign_up_teacher(View view){
        try{
            if(password.getText().toString().equals(password2.getText().toString())){
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

            }else{
                Toast.makeText(getApplicationContext(), getString(R.string.pass_not_match), Toast.LENGTH_SHORT).show();
            }
        }catch (IllegalArgumentException exception){
            Toast.makeText(getApplicationContext(), getString(R.string.empty_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser account){

        if(account != null) {

            // Create a new user with a first, middle, and last name
            Map<String, Object> user = new HashMap<>();
            user.put("email", email.getText().toString());
            user.put("classId", "none");

            // Add a new document with a generated ID
            db.collection("teachers")
                    .document(account.getUid())
                    .set(user)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(context, getString(R.string.success_sign_in), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context, TeacherActivity.class));                        })
                    .addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show());
        }
    }
}