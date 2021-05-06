package com.unipi.developers.multiplicationlearning;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyClassesActivity extends FullScreen {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> classesID;
    ArrayList<String> classesNAME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_classes);
        mAuth = FirebaseAuth.getInstance();



    }

    @Override
    protected void onResume() {
        super.onResume();
        classesID = new ArrayList<>();
        classesNAME = new ArrayList<>();

        db.collection("teachers")
                .document(mAuth.getUid())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {

                            Map<String, Object> map = document.getData();
                            if (map != null) {
                                for (Map.Entry<String, Object> entry : map.entrySet()) {
                                    if(!entry.getKey().equals("0000") && !entry.getKey().equals("email")){
                                        classesNAME.add(entry.getValue().toString());
                                        classesID.add(entry.getKey());
                                    }
                                }
                            }

                            for (String s : classesNAME) {
                                Log.e("Name", s);
                            }
                            for (String s : classesID) {
                                Log.e("ID", s);
                            }
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