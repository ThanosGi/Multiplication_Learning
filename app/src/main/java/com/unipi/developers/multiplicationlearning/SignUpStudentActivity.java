package com.unipi.developers.multiplicationlearning;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUpStudentActivity extends FullScreen {
    private FirebaseAuth mAuth;
    EditText username;
    EditText passphrase;
    Spinner class_id;
    Context context = this;
    FirebaseFirestore db_exists = FirebaseFirestore.getInstance();
    FirebaseFirestore db_auth = FirebaseFirestore.getInstance();
    ArrayList<String> classes;
    TextView txt_create_account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_student);
        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.et_username);
        passphrase = findViewById(R.id.et_passphrase);
        class_id = findViewById(R.id.et_classid);
        classes = new ArrayList<>();
        txt_create_account=findViewById(R.id.txt_create_account);
        txt_create_account.setOnClickListener(v -> startActivity(new Intent(this,LogInActivity.class)));


        db_exists.collection("classes")
                .document("kXJy56RB69Jewvnh23Pf")
                .get()
                .addOnCompleteListener(found -> {
                    DocumentSnapshot temp = found.getResult();
                    for (String data : Objects.requireNonNull(temp.getData()).keySet()) {
                        if (!data.equals("0000")) classes.add(data);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, classes);
                    class_id.setSelection(0);
                    class_id.setAdapter(adapter);
                });

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
            if (!classes.contains(class_id.getSelectedItem().toString())) {
                Toast.makeText(context, getString(R.string.class_does_not_exist), Toast.LENGTH_LONG).show();
            } else {
                create_account();
            }
        } catch (IllegalArgumentException exception) {
            Toast.makeText(getApplicationContext(), getString(R.string.empty_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void create_account() {
        mAuth.createUserWithEmailAndPassword(username.getText().toString(), passphrase.getText().toString())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUI(FirebaseUser account) {
        if (account != null) {

            Map<String, Object> user = new HashMap<>();
            user.put("username", username.getText().toString());
            user.put("classId", class_id.getSelectedItem().toString());
            user.put("progress", "{\"0\":{\"success\":0},\"1\":{\"success\":0},\"2\":{\"success\":0},\"test1\":{\"success\":0},\"3\":{\"success\":0},\"4\":{\"success\":0},\"5\":{\"success\":0},\"test2\":{\"success\":0},\"6\":{\"success\":0},\"7\":{\"success\":0},\"8\":{\"success\":0},\"test3\":{\"success\":0},\"9\":{\"success\":0},\"finalTest\":{\"success\":0}}");
            user.put("wrongs","{}");

            db_auth.collection("students")
                    .document(account.getUid())
                    .set(user)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(context, getString(R.string.success_sign_in), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context, LessonsActivity.class)
                                .putExtra("json", (String) user.get("progress"))
                                .putExtra("wrongs",(String) user.get("wrongs")));

                    }).addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show());
        }
    }
}