package com.unipi.developers.multiplicationlearning;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class StudentsStatus extends FullScreen {
    private FirebaseAuth mAuth;
    FirebaseFirestore db_get_classes = FirebaseFirestore.getInstance();
    FirebaseFirestore db_get_scores = FirebaseFirestore.getInstance();
    ArrayList<String> classesID;
    ArrayList<String> classesNAME;
    ArrayList<String> students_progress;
    ArrayList<String> students_username;
    ArrayList<String> students_classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_status);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        classesID = new ArrayList<>();
        classesNAME = new ArrayList<>();
        students_progress = new ArrayList<>();
        students_username = new ArrayList<>();
        students_classId = new ArrayList<>();
        get_classes();

    }

    private void get_classes(){
        db_get_classes.collection("teachers")
                .document(Objects.requireNonNull(mAuth.getUid()))
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (Objects.requireNonNull(document).exists()) {
                            Map<String, Object> map = document.getData();
                            if (map != null) {
                                for (Map.Entry<String, Object> entry : map.entrySet()) {
                                    if(!entry.getKey().equals("0000") && !entry.getKey().equals("email")){
                                        classesNAME.add(entry.getValue().toString());
                                        classesID.add(entry.getKey());
                                    }
                                }
                            }
                            get_scores();
                        }
                    }
                });
    }

    private void get_scores(){
        db_get_scores.collection("students")
                .whereIn("classId",classesID)
                .get()
                .addOnSuccessListener(found -> {
                    if (!found.getDocuments().isEmpty()){
                        for (DocumentSnapshot document: found.getDocuments()) {
                            students_progress.add(Objects.requireNonNull(document.get("progress")).toString());
                            students_username.add(Objects.requireNonNull(document.get("username")).toString());
                            students_classId.add(Objects.requireNonNull(document.get("classId")).toString());
                        }
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
}