package com.unipi.developers.multiplicationlearning;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.SetOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MiniTestActivity extends FullScreen {
    final int zeroID = R.drawable.zero, oneID = R.drawable.one, twoID = R.drawable.two, threeID = R.drawable.three, fourID = R.drawable.four, fiveID = R.drawable.five, sixID = R.drawable.six, sevenID = R.drawable.seven, eightID = R.drawable.eight, nineID = R.drawable.nine;
    ImageView num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, next, btn_help;
    EditText result1, result2, result3, result4, result5, result6, result7, result8, result9, result10;
    int number, id, from;
    ArrayList<Integer> results = new ArrayList<>();
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    Context context = this;
    private int score = 0;
    private String json_data;
    JSONObject wrongs;
    private long backPressedTime;
    private Toast backToast;

    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_test);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        db.setFirestoreSettings(settings);

        btn_help = findViewById(R.id.btn_help_mini);
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MiniTestActivity.this,PdfViewerActivity.class);
                intent.putExtra("fromActivity","minitest");
                startActivity(intent);
            }
        });

        result1 = findViewById(R.id.result1_mini);
        result2 = findViewById(R.id.result2_mini);
        result3 = findViewById(R.id.result3_mini);
        result4 = findViewById(R.id.result4_mini);
        result5 = findViewById(R.id.result5_mini);
        result6 = findViewById(R.id.result6_mini);
        result7 = findViewById(R.id.result7_mini);
        result8 = findViewById(R.id.result8_mini);
        result9 = findViewById(R.id.result9_mini);
        result10 = findViewById(R.id.result10_mini);
        num1 = findViewById(R.id.num1_mini);
        num2 = findViewById(R.id.num2_mini);
        num3 = findViewById(R.id.num3_mini);
        num4 = findViewById(R.id.num4_mini);
        num5 = findViewById(R.id.num5_mini);
        num6 = findViewById(R.id.num6_mini);
        num7 = findViewById(R.id.num7_mini);
        num8 = findViewById(R.id.num8_mini);
        num9 = findViewById(R.id.num9_mini);
        num10 = findViewById(R.id.num10_mini);
        next = findViewById(R.id.btn_next_mini);


        from = getIntent().getIntExtra("from", 0);
        json_data = getIntent().getStringExtra("json");


        //here it checks which test it is comes from to dynamically generate the numbers
        switch (from) {
            case 1:
                number = 1;
                break;
            case 2:
                number = 2;
                break;
            case 3:
                number = 3;
                break;
            case 4:
                number = 4;
                break;
            case 5:
                number = 5;
                break;
            case 6:
                number = 6;
                break;
            case 7:
                number = 7;
                break;
            case 8:
                number = 8;
                break;
            case 9:
                number = 9;
                break;
            default:
                number = 0;
                break;
        }


        makeCards(number);

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.WHITE);
        gd.setStroke(2, Color.BLUE);


        next.setOnClickListener(v -> {
            if (result1.getText().toString().equals("") || result2.getText().toString().equals("") ||
                    result3.getText().toString().equals("") || result4.getText().toString().equals("") ||
                    result5.getText().toString().equals("") || result6.getText().toString().equals("") ||
                    result7.getText().toString().equals("") || result8.getText().toString().equals("") ||
                    result9.getText().toString().equals("") || result10.getText().toString().equals("")) {
                Toast.makeText(this, R.string.warning, Toast.LENGTH_SHORT).show();
            } else {
                try {
                    wrongs = new JSONObject(getIntent().getStringExtra("wrongs"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String temp= "";

                if (Objects.equals(Integer.parseInt(result1.getText().toString()), results.get(0))) {
                    score += 10;
                }else{
                    if(!temp.equals("")) temp=temp+", "+(from+"x0="+result1.getText().toString());
                    else temp=(from+"x0="+result1.getText().toString());
                }
                if (Objects.equals(Integer.parseInt(result2.getText().toString()), results.get(1))) {
                    score += 10;
                }else{
                    if(!temp.equals("")) temp=temp+", "+(from+"x1="+result2.getText().toString());
                    else temp=(from+"x1="+result2.getText().toString());
                }
                if (Objects.equals(Integer.parseInt(result3.getText().toString()), results.get(2))) {
                    score += 10;
                }else{
                    if(!temp.equals("")) temp=temp+", "+(from+"x2="+result3.getText().toString());
                    else temp=(from+"x2="+result3.getText().toString());
                }
                if (Objects.equals(Integer.parseInt(result4.getText().toString()), results.get(3))) {
                    score += 10;
                }else{
                    if(!temp.equals("")) temp=temp+", "+(from+"x3="+result4.getText().toString());
                    else temp=(from+"x3="+result4.getText().toString());
                }
                if (Objects.equals(Integer.parseInt(result5.getText().toString()), results.get(4))) {
                    score += 10;
                }else{
                    if(!temp.equals("")) temp=temp+", "+(from+"x4="+result5.getText().toString());
                    else temp=(from+"x4="+result5.getText().toString());
                }
                if (Objects.equals(Integer.parseInt(result6.getText().toString()), results.get(5))) {
                    score += 10;
                }else{
                    if(!temp.equals("")) temp=temp+", "+(from+"x5="+result6.getText().toString());
                    else temp=(from+"x5="+result6.getText().toString());
                }
                if (Objects.equals(Integer.parseInt(result7.getText().toString()), results.get(6))) {
                    score += 10;
                }else{
                    if(!temp.equals("")) temp=temp+", "+(from+"x6="+result7.getText().toString());
                    else temp=(from+"x6="+result7.getText().toString());
                }
                if (Objects.equals(Integer.parseInt(result8.getText().toString()), results.get(7))) {
                    score += 10;
                }else{
                    if(!temp.equals("")) temp=temp+", "+(from+"x7="+result8.getText().toString());
                    else temp=(from+"x7="+result8.getText().toString());
                }
                if (Objects.equals(Integer.parseInt(result9.getText().toString()), results.get(8))) {
                    score += 10;
                }else{
                    if(!temp.equals("")) temp=temp+", "+(from+"x8="+result9.getText().toString());
                    else temp=(from+"x8="+result9.getText().toString());
                }
                if (Objects.equals(Integer.parseInt(result10.getText().toString()), results.get(9))) {
                    score += 10;
                }else{
                    if(!temp.equals("")) temp=temp+", "+(from+"x9="+result10.getText().toString());
                    else temp=(from+"x9="+result10.getText().toString());
                }
                try {
                    wrongs.put(String.valueOf(from),temp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                JSONObject jsonObject = new JSONObject();
                JSONObject json_temp;
                try {
                    jsonObject = new JSONObject(json_data);

                    json_temp = new JSONObject();
                    json_temp.put("success", Math.max(jsonObject.getJSONObject(String.valueOf(from)).getInt("success"), score));
                    jsonObject.put(String.valueOf(from), json_temp);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (score < 80) {
                    Toast.makeText(context, getString(R.string.lesson_continue), Toast.LENGTH_LONG).show();
                }

                String new_json = jsonObject.toString();

                Map<String, Object> user = new HashMap<>();
                user.put("progress", new_json);
                user.put("wrongs",wrongs.toString());

                db.collection("students")
                        .document(Objects.requireNonNull(mAuth.getUid()))
                        .set(user, SetOptions.merge())
                        .addOnSuccessListener(aVoid -> Toast.makeText(context, getString(R.string.progress), Toast.LENGTH_LONG).show()).addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show());
                Intent intent = new Intent(MiniTestActivity.this, LessonsActivity.class);
                intent.putExtra("json", new_json);
                intent.putExtra("wrongs",wrongs.toString());

                startActivity(intent);
                overridePendingTransition(0, 0);

            }
        });
    }


    private void makeCards(int rUnit) {
        //this function makes the results of multiplies
        //making the imageViews

        id = getTranslation(rUnit);
        num1.setImageDrawable(ContextCompat.getDrawable(this, id));
        results.add(0);
        num2.setImageDrawable(ContextCompat.getDrawable(this, id));
        results.add(rUnit);
        num3.setImageDrawable(ContextCompat.getDrawable(this, id));
        results.add(rUnit * 2);
        num4.setImageDrawable(ContextCompat.getDrawable(this, id));
        results.add(rUnit * 3);
        num5.setImageDrawable(ContextCompat.getDrawable(this, id));
        results.add(rUnit * 4);
        num6.setImageDrawable(ContextCompat.getDrawable(this, id));
        results.add(rUnit * 5);
        num7.setImageDrawable(ContextCompat.getDrawable(this, id));
        results.add(rUnit * 6);
        num8.setImageDrawable(ContextCompat.getDrawable(this, id));
        results.add(rUnit * 7);
        num9.setImageDrawable(ContextCompat.getDrawable(this, id));
        results.add(rUnit * 8);
        num10.setImageDrawable(ContextCompat.getDrawable(this, id));
        results.add(rUnit * 9);
    }

    private int getTranslation(int random) {
        //this function translates the numbers into image id of specific number
        switch (random) {
            case 0:
                return zeroID;
            case 1:
                return oneID;
            case 2:
                return twoID;
            case 3:
                return threeID;
            case 4:
                return fourID;
            case 5:
                return fiveID;
            case 6:
                return sixID;
            case 7:
                return sevenID;
            case 8:
                return eightID;
            case 9:
                return nineID;
            default:
                return 0;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
        } else {
            backToast = Toast.makeText(getApplicationContext(), getString(R.string.back_press_exit), Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}