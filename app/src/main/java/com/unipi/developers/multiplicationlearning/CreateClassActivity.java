package com.unipi.developers.multiplicationlearning;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    EditText class_id, classname;
    String class_id_as_string;
    Context context = this;
    ImageView btn_help;
    FirebaseFirestore db_check = FirebaseFirestore.getInstance();
    FirebaseFirestore db_create = FirebaseFirestore.getInstance();
    FirebaseFirestore db_create_instance = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
        create = findViewById(R.id.btn_createclass);
        class_id = findViewById(R.id.classID);
        classname = findViewById(R.id.className);
        mAuth = FirebaseAuth.getInstance();

        btn_help = findViewById(R.id.btn_help);
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateClassActivity.this,PdfViewerActivity.class);
                intent.putExtra("fromActivity","createclass");
                startActivity(intent);
            }
        });

        create.setOnClickListener(v -> {
            class_id_as_string = class_id.getText().toString();
            class_id_as_string = getIntent().getStringExtra("username") + "_" + class_id_as_string;
            if (!class_id_as_string.equals("") && !classname.getText().toString().equals("")) {
                db_check.collection("classes")
                        .whereEqualTo(class_id_as_string, true)
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
        data.put(class_id_as_string, classname.getText().toString());

        db_create.collection("teachers").document(Objects.requireNonNull(mAuth.getUid()))
                .set(data, SetOptions.merge()).addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Map<String, Object> glob_class = new HashMap<>();
                        glob_class.put(class_id_as_string, true);
                        db_create_instance.collection("classes").document("kXJy56RB69Jewvnh23Pf").set(glob_class, SetOptions.merge());

                        Toast.makeText(context, getString(R.string.class_success), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, TeacherActivity.class);
                        intent.putExtra("username",getIntent().getStringExtra("username"));
                        startActivity(intent);
                    } else {
                        Toast.makeText(context, getString(R.string.class_failed), Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }
}