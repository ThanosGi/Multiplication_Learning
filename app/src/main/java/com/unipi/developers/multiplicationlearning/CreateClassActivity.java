package com.unipi.developers.multiplicationlearning;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreateClassActivity extends FullScreen {
    private FirebaseAuth mAuth;
    Button create;
    EditText classid, classname;
    Context context = this;
    FirebaseFirestore db_check = FirebaseFirestore.getInstance();
    FirebaseFirestore db_create = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
        create = findViewById(R.id.btn_createclass);
        classid = findViewById(R.id.classID);
        classname = findViewById(R.id.className);
        mAuth = FirebaseAuth.getInstance();

        create.setOnClickListener(v -> {
            if (!classid.getText().toString().equals("") && !classname.getText().toString().equals("")) {
                db_check.collection("classes")
                        .whereEqualTo(classid.getText().toString(), true)
                        .get()
                        .addOnSuccessListener(found -> {
                            if (found.getDocuments().isEmpty()) {
                                create_class();
                            } else {
                                Toast.makeText(context, getString(R.string.class_exists), Toast.LENGTH_LONG).show();
                            }
                        });

            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.empty_error), Toast.LENGTH_SHORT).show();
            }


        });
    }

    private void create_class() {
        Map<String, Object> data = new HashMap<>();
        data.put(classid.getText().toString(), classname.getText().toString());

        db_create.collection("teachers").document(Objects.requireNonNull(mAuth.getUid()))
                .set(data, SetOptions.merge()).addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(context, getString(R.string.class_success), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, getString(R.string.class_failed), Toast.LENGTH_LONG).show();
                    }
                }
        );

        Map<String, Object> glob_class = new HashMap<>();
        glob_class.put(classid.getText().toString(), true);
        db_create.collection("classes").document("kXJy56RB69Jewvnh23Pf").set(glob_class, SetOptions.merge());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }
}