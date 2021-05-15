package com.unipi.developers.multiplicationlearning;

import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class LogInActivity extends FullScreen {
    private FirebaseAuth mAuth;
    TextView txtCreateAccount;
    EditText username;
    EditText passphrase;
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

        username=findViewById(R.id.et_username);
        passphrase=findViewById(R.id.et_passphrase);

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

    public void log_in(View view){
        try{
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
        }catch (IllegalArgumentException exception){
            Toast.makeText(getApplicationContext(), getString(R.string.empty_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser account){

        if(account != null) {
            Context context=this;

            db.collection("teachers")
                    .whereEqualTo("email", username.getText().toString())
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                startActivity(new Intent(context, TeacherActivity.class));
                            }
                        }
                    });
            db.collection("students")
                    .whereEqualTo("username", username.getText().toString())
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {

                                DocumentReference docRef = db_json.collection("students").document(mAuth.getUid());
                                docRef.get().addOnCompleteListener(found -> {
                                        if (found.isSuccessful()) {
                                            DocumentSnapshot document_json = found.getResult();
                                            if (document_json.exists()) {
                                                JSONObject json;
                                                String temp=document_json.getString("progress");
                                                Intent intent = new Intent(context, LessonsActivity.class);
                                                intent.putExtra("json", temp);
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