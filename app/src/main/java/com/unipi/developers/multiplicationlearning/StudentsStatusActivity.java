package com.unipi.developers.multiplicationlearning;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class StudentsStatusActivity extends FullScreen {
    private FirebaseAuth mAuth;
    FirebaseFirestore db_get_classes = FirebaseFirestore.getInstance();
    FirebaseFirestore db_get_scores = FirebaseFirestore.getInstance();
    ArrayList<String> classesID;
    ArrayList<String> classesNAME;
    ArrayList<String> students_progress;
    ArrayList<String> students_username;
    ArrayList<String> students_classId;
    BarChart barChart;
    RadarChart radarChart;
    int current_user = -1;
    TextView txt_student_name;
    AppCompatButton btn_prev, btn_next;
    int zero_lesson, one_lesson, two_lesson, three_lesson, four_lesson, five_lesson, six_lesson, seven_lesson, eight_lesson, nine_lesson, review1, review2, review3, final_review;
    String json_data;
    ImageView btn_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_status);

        btn_help = findViewById(R.id.btn_help);
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentsStatusActivity.this,PdfViewerActivity.class);
                intent.putExtra("fromActivity","stats");
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        barChart = findViewById(R.id.barChart);
        radarChart = findViewById(R.id.radarChart);
        txt_student_name = findViewById(R.id.txt_student_name);
        btn_next = findViewById(R.id.btn_next);
        btn_prev = findViewById(R.id.btn_prev);

        btn_prev.setOnClickListener(v -> {
            if (current_user != -1) {
                if (current_user > 0) {
                    current_user--;
                } else {
                    current_user = students_username.size() - 1;
                }
                make_chart(current_user);
            }
        });

        btn_next.setOnClickListener(v -> {
            if (current_user != -1) {
                if (current_user < students_username.size() - 1) {
                    current_user++;
                } else {
                    current_user = 0;
                }
                make_chart(current_user);
            }
        });
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

    private void get_classes() {
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
                                    if (!entry.getKey().equals("0000") && !entry.getKey().equals("email")) {
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

    private void get_scores() {
        if (classesID.isEmpty()){
            Toast.makeText(getApplicationContext(),getString(R.string.create_class_students_first),Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        db_get_scores.collection("students")
                .whereIn("classId", classesID)
                .get()
                .addOnSuccessListener(found -> {
                    if (!found.getDocuments().isEmpty()) {
                        for (DocumentSnapshot document : found.getDocuments()) {
                            students_progress.add(Objects.requireNonNull(document.get("progress")).toString());
                            students_username.add(Objects.requireNonNull(document.get("username")).toString());
                            students_classId.add(Objects.requireNonNull(document.get("classId")).toString());
                        }
                    }
                    current_user = 0;
                    make_chart(0);
                });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void make_chart(int pos) {
        if (students_username.isEmpty()){
            return;
        }
        txt_student_name.setText(students_username.get(pos));
        json_data = students_progress.get(pos);
        JSONObject json;
        try {
            json = new JSONObject(json_data);
            zero_lesson = json.getJSONObject("0").getInt("success");
            one_lesson = json.getJSONObject("1").getInt("success");
            two_lesson = json.getJSONObject("2").getInt("success");
            three_lesson = json.getJSONObject("3").getInt("success");
            four_lesson = (json.getJSONObject("4").getInt("success"));
            five_lesson = (json.getJSONObject("5").getInt("success"));
            six_lesson = (json.getJSONObject("6").getInt("success"));
            seven_lesson = (json.getJSONObject("7").getInt("success"));
            eight_lesson = (json.getJSONObject("8").getInt("success"));
            nine_lesson = (json.getJSONObject("9").getInt("success"));
            review1 = (json.getJSONObject("test1").getInt("success"));
            review2 = (json.getJSONObject("test2").getInt("success"));
            review3 = (json.getJSONObject("test3").getInt("success"));
            final_review = (json.getJSONObject("finalTest").getInt("success"));

            ArrayList<BarEntry> stats = new ArrayList<>();
            stats.add(new BarEntry(0, zero_lesson));
            stats.add(new BarEntry(1, one_lesson));
            stats.add(new BarEntry(2, two_lesson));
            stats.add(new BarEntry(3, three_lesson));
            stats.add(new BarEntry(4, four_lesson));
            stats.add(new BarEntry(5, five_lesson));
            stats.add(new BarEntry(6, six_lesson));
            stats.add(new BarEntry(7, seven_lesson));
            stats.add(new BarEntry(8, eight_lesson));
            stats.add(new BarEntry(9, nine_lesson));

            BarDataSet barDataSet = new BarDataSet(stats, getString(R.string.lessons));
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            barDataSet.setValueTextColor(Color.BLACK);
            barDataSet.setValueTextSize(16f);

            BarData barData = new BarData(barDataSet);

            barChart.setFitBars(true);
            barChart.setData(barData);
            barChart.getDescription().setText(getString(R.string.student_stats));
            barChart.animateY(2000);

            ArrayList<RadarEntry> reviews = new ArrayList<>();
            reviews.add(new RadarEntry(review1));
            reviews.add(new RadarEntry(review2));
            reviews.add(new RadarEntry(review3));
            reviews.add(new RadarEntry(final_review));

            RadarDataSet radarDataSet = new RadarDataSet(reviews, getString(R.string.reviews));
            radarDataSet.setColor(Color.RED);
            radarDataSet.setLineWidth(2f);
            radarDataSet.setValueTextColor(Color.RED);
            radarDataSet.setValueTextSize(14f);

            RadarData radarData = new RadarData();
            radarData.addDataSet(radarDataSet);

            String[] labels = {getString(R.string.review1), getString(R.string.review2), getString(R.string.review3), getString(R.string.final_review),};

            XAxis xAxis = radarChart.getXAxis();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

            radarChart.clear();
            radarChart.getDescription().setEnabled(false);
            radarChart.setData(radarData);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}