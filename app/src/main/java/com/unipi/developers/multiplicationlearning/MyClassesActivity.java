package com.unipi.developers.multiplicationlearning;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class MyClassesActivity extends FullScreen {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> classesID;
    ArrayList<String> classesNAME;
    TableLayout tableLayout;
    TextView class_name_row, class_id_row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_classes);
        mAuth = FirebaseAuth.getInstance();
        tableLayout = findViewById(R.id.tableLayout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        classesID = new ArrayList<>();
        classesNAME = new ArrayList<>();

        db.collection("teachers")
                .document(Objects.requireNonNull(mAuth.getUid()))
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (Objects.requireNonNull(document).exists()) {

                            Map<String, Object> map = document.getData();
                            if (map != null) {
                                for (Map.Entry<String, Object> entry : map.entrySet()) {
                                    if (!entry.getKey().equals("0000") && !entry.getKey().equals("email")) {
                                        classesNAME.add(entry.getValue().toString());
                                        classesID.add(entry.getKey());
                                    }
                                }
                            }

                            if (classesNAME.size() == classesID.size()) {
                                for (int i = 0; i < classesNAME.size(); i++) {
                                    TableRow row = new TableRow(this);
                                    TableRow.LayoutParams lp;
                                    lp = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 4f);
                                    //row.setLayoutParams(lp);
                                    class_name_row = new TextView(this);
                                    class_name_row.setText(classesNAME.get(i));
                                    class_name_row.setTextColor(Color.rgb(249, 195, 89));
                                    class_name_row.setPadding(10, 10, 10, 10);
                                    class_name_row.setTextSize(20);
                                    class_name_row.setGravity(Gravity.CENTER_HORIZONTAL);
                                    class_name_row.setLayoutParams(lp);
                                    class_id_row = new TextView(this);
                                    class_id_row.setText(classesID.get(i));
                                    class_id_row.setTextColor(Color.rgb(249, 195, 89));
                                    class_id_row.setPadding(10, 10, 10, 10);
                                    class_id_row.setTextSize(20);
                                    class_id_row.setGravity(Gravity.CENTER_HORIZONTAL);
                                    class_id_row.setLayoutParams(lp);
                                    row.addView(class_name_row);
                                    row.addView(class_id_row);
                                    tableLayout.addView(row);
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong Data", Toast.LENGTH_SHORT).show();
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