package com.unipi.developers.multiplicationlearning;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

public class LessonsActivity extends FullScreen {
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    ImageView help;
    CardView card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, cardTest1, cardTest2, cardTest3, cardFinalTest;
    int zero_lesson,one_lesson,two_lesson,three_lesson,four_lesson,five_lesson,six_lesson,seven_lesson,eight_lesson,nine_lesson,review1,review2,review3,final_review;
    String json_data;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
        db = FirebaseFirestore.getInstance();
        card0 = findViewById(R.id.card0);
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);
        card6 = findViewById(R.id.card6);
        card7 = findViewById(R.id.card7);
        card8 = findViewById(R.id.card8);
        card9 = findViewById(R.id.card9);
        cardTest1 = findViewById(R.id.test1);
        cardTest2 = findViewById(R.id.test2);
        cardTest3 = findViewById(R.id.test3);
        cardFinalTest = findViewById(R.id.finalTest);

        mAuth = FirebaseAuth.getInstance();
        help=findViewById(R.id.btn_help);
        help.setOnClickListener(this::logout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DocumentReference docRef = db.collection("students").document(mAuth.getUid());
        Thread newThread = new Thread(() -> {
            docRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        JSONObject json;
                        String temp=document.getString("progress");
                        try {
                            json = new JSONObject(temp);
                            zero_lesson =json.getJSONObject("0").getInt("success");
                            one_lesson =json.getJSONObject("1").getInt("success");
                            two_lesson =json.getJSONObject("2").getInt("success");
                            three_lesson =json.getJSONObject("3").getInt("success");
                            four_lesson =(json.getJSONObject("4").getInt("success"));
                            five_lesson =(json.getJSONObject("5").getInt("success"));
                            six_lesson =(json.getJSONObject("6").getInt("success"));
                            seven_lesson =(json.getJSONObject("7").getInt("success"));
                            eight_lesson =(json.getJSONObject("8").getInt("success"));
                            nine_lesson =(json.getJSONObject("9").getInt("success"));
                            review1 =(json.getJSONObject("Review1").getInt("success"));
                            review2 =(json.getJSONObject("Review2").getInt("success"));
                            review3 =(json.getJSONObject("Review3").getInt("success"));
                            final_review =(json.getJSONObject("Final_Review").getInt("success"));

                            json_data=document.getString("progress");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }
            });
        });
        synchronized (newThread) {
            newThread.start();
        }

        final int num=10;

        card0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                intent.putExtra("lesson_number", 0);
                intent.putExtra("json", json_data);
                startActivity(intent);
            }
        });
        if(zero_lesson>=num){
            card1.setEnabled(true);
            card1.setForeground(null);
            card1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                    intent.putExtra("lesson_number", 1);
                    intent.putExtra("json", json_data);
                    startActivity(intent);
                }
            });
        }else{
            card1.setEnabled(false);
            card1.setForeground(ContextCompat.getDrawable(this,R.drawable.fence));
        }

        if(one_lesson>=num){
            card2.setEnabled(true);
            card2.setForeground(null);
            card2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                    intent.putExtra("lesson_number", 2);
                    intent.putExtra("json", json_data);
                    startActivity(intent);
                }
            });
        }else{
            card2.setEnabled(false);
            card2.setForeground(ContextCompat.getDrawable(this,R.drawable.fence));
        }

        if(two_lesson>=num){
            card3.setEnabled(true);
            card3.setForeground(null);
            card3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                    intent.putExtra("lesson_number", 3);
                    intent.putExtra("json", json_data);
                    startActivity(intent);
                }
            });
        }else{
            card3.setEnabled(false);
            card3.setForeground(ContextCompat.getDrawable(this,R.drawable.fence));
        }

        if(review1>=num){
            card4.setEnabled(true);
            card4.setForeground(null);
            card4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                    intent.putExtra("lesson_number", 4);
                    intent.putExtra("json", json_data);
                    startActivity(intent);
                }
            });
        }else{
            card4.setEnabled(false);
            card4.setForeground(ContextCompat.getDrawable(this,R.drawable.fence));
        }

        if(four_lesson>=num){
            card5.setEnabled(true);
            card5.setForeground(null);
            card5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                    intent.putExtra("lesson_number", 5);
                    intent.putExtra("json", json_data);
                    startActivity(intent);
                }
            });
        }else{
            card5.setEnabled(false);
            card5.setForeground(ContextCompat.getDrawable(this,R.drawable.fence));
        }

        if(review2>=num){
            card6.setEnabled(true);
            card6.setForeground(null);
            card6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                    intent.putExtra("lesson_number", 6);
                    intent.putExtra("json", json_data);
                    startActivity(intent);
                }
            });
        }else{
            card6.setEnabled(false);
            card6.setForeground(ContextCompat.getDrawable(this,R.drawable.fence));
        }

        if(six_lesson>=num){
            card7.setEnabled(true);
            card7.setForeground(null);
            card7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                    intent.putExtra("lesson_number", 7);
                    intent.putExtra("json", json_data);
                    startActivity(intent);
                }
            });
        }else{
            card7.setEnabled(false);
            card7.setForeground(ContextCompat.getDrawable(this,R.drawable.fence));
        }

        if(seven_lesson>=num){
            card8.setEnabled(true);
            card8.setForeground(null);
            card8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                    intent.putExtra("lesson_number", 8);
                    intent.putExtra("json", json_data);
                    startActivity(intent);
                }
            });
        }else{
            card8.setEnabled(false);
            card8.setForeground(ContextCompat.getDrawable(this,R.drawable.fence));
        }

        if(review3>=num){
            card9.setEnabled(true);
            card9.setForeground(null);
            card9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                    intent.putExtra("lesson_number", 9);
                    intent.putExtra("json", json_data);
                    startActivity(intent);
                }
            });
        }else{
            card9.setEnabled(false);
            card9.setForeground(ContextCompat.getDrawable(this,R.drawable.fence));
        }

        if(two_lesson>=num){

            cardTest1.setEnabled(true);
            cardTest1.setForeground(null);
            cardTest1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LessonsActivity.this, TestActivity.class);
                    intent.putExtra("from", "test1");
                    intent.putExtra("page", 1);
                    intent.putExtra("json", json_data);
                    startActivity(intent);
                }
            });
        }else{
            cardTest1.setEnabled(false);
            cardTest1.setForeground(ContextCompat.getDrawable(this,R.drawable.fence));
        }

        if(five_lesson>=num){
            cardTest2.setEnabled(true);
            cardTest2.setForeground(null);
            cardTest2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LessonsActivity.this, TestActivity.class);
                    intent.putExtra("from", "test2");
                    intent.putExtra("json", json_data);
                    startActivity(intent);
                }
            });
        }else{
            cardTest2.setEnabled(false);
            cardTest2.setForeground(ContextCompat.getDrawable(this,R.drawable.fence));
        }

        if(eight_lesson>=num){
            cardTest3.setEnabled(true);
            cardTest3.setForeground(null);
            cardTest3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LessonsActivity.this, TestActivity.class);
                    intent.putExtra("from", "test3");
                    intent.putExtra("json", json_data);
                    startActivity(intent);
                }
            });
        }else{
            cardTest3.setEnabled(false);
            cardTest3.setForeground(ContextCompat.getDrawable(this,R.drawable.fence));
        }

        if(nine_lesson>=num){
            cardFinalTest.setEnabled(true);
            cardFinalTest.setForeground(null);
            cardFinalTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LessonsActivity.this, TestActivity.class);
                    intent.putExtra("from", "finalTest");
                    intent.putExtra("json", json_data);
                    startActivity(intent);
                }
            });
        }else{
            cardFinalTest.setEnabled(false);
            cardFinalTest.setForeground(ContextCompat.getDrawable(this,R.drawable.fence));
        }

    }

    private void updateUI(FirebaseUser account){

        if(account == null) {
            Toast.makeText(this, getString(R.string.log_out), Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LogInActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}