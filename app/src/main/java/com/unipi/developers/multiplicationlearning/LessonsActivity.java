package com.unipi.developers.multiplicationlearning;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import org.json.JSONException;
import org.json.JSONObject;

public class LessonsActivity extends FullScreen {
    FirebaseFirestore db;
    ImageView help;
    CardView card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, cardTest1, cardTest2, cardTest3, cardFinalTest;
    TextView txt_rate0, txt_rate1, txt_rate2, txt_rate3, txt_rate4, txt_rate5, txt_rate6, txt_rate7, txt_rate8, txt_rate9, txt_rater1, txt_rater2, txt_rater3, txt_ratefr;
    TextView logout,txt_des_rate0, txt_des_rate1, txt_des_rate2, txt_des_rate3, txt_des_rate4, txt_des_rate5, txt_des_rate6, txt_des_rate7, txt_des_rate8, txt_des_rate9, txt_des_rater1, txt_des_rater2, txt_des_rater3, txt_des_ratefr;
    int zero_lesson, one_lesson, two_lesson, three_lesson, four_lesson, five_lesson, six_lesson, seven_lesson, eight_lesson, nine_lesson, review1, review2, review3, final_review;
    String json_data;

    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build();

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
        updateUI(FirebaseAuth.getInstance().getCurrentUser());
        db = FirebaseFirestore.getInstance();
        db.setFirestoreSettings(settings);

        card0 = findViewById(R.id.card0);
        txt_rate0=findViewById(R.id.txt_rate0);
        txt_des_rate0=findViewById(R.id.txt_des_rate0);

        card1 = findViewById(R.id.card1);
        txt_rate1=findViewById(R.id.txt_rate1);
        txt_des_rate1=findViewById(R.id.txt_des_rate1);

        card2 = findViewById(R.id.card2);
        txt_rate2=findViewById(R.id.txt_rate2);
        txt_des_rate2=findViewById(R.id.txt_des_rate2);

        card3 = findViewById(R.id.card3);
        txt_rate3=findViewById(R.id.txt_rate3);
        txt_des_rate3=findViewById(R.id.txt_des_rate3);

        card4 = findViewById(R.id.card4);
        txt_rate4=findViewById(R.id.txt_rate4);
        txt_des_rate4=findViewById(R.id.txt_des_rate4);

        card5 = findViewById(R.id.card5);
        txt_rate5=findViewById(R.id.txt_rate5);
        txt_des_rate5=findViewById(R.id.txt_des_rate5);

        card6 = findViewById(R.id.card6);
        txt_rate6=findViewById(R.id.txt_rate6);
        txt_des_rate6=findViewById(R.id.txt_des_rate6);

        card7 = findViewById(R.id.card7);
        txt_rate7=findViewById(R.id.txt_rate7);
        txt_des_rate7=findViewById(R.id.txt_des_rate7);

        card8 = findViewById(R.id.card8);
        txt_rate8=findViewById(R.id.txt_rate8);
        txt_des_rate8=findViewById(R.id.txt_des_rate8);

        card9 = findViewById(R.id.card9);
        txt_rate9=findViewById(R.id.txt_rate9);
        txt_des_rate9=findViewById(R.id.txt_des_rate9);

        cardTest1 = findViewById(R.id.test1);
        txt_rater1=findViewById(R.id.txt_rater1);
        txt_des_rater1=findViewById(R.id.txt_des_rater1);

        cardTest2 = findViewById(R.id.test2);
        txt_rater2=findViewById(R.id.txt_rater2);
        txt_des_rater2=findViewById(R.id.txt_des_rater2);

        cardTest3 = findViewById(R.id.test3);
        txt_rater3=findViewById(R.id.txt_rater3);
        txt_des_rater3=findViewById(R.id.txt_des_rater3);

        cardFinalTest = findViewById(R.id.finalTest);
        txt_ratefr=findViewById(R.id.txt_ratefr);
        txt_des_ratefr=findViewById(R.id.txt_des_ratefr);

        help = findViewById(R.id.btn_help);
        logout = findViewById(R.id.btn_log_out);
        help.setOnClickListener(v->{

        });

        logout.setOnClickListener(v -> {
            finish();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(LessonsActivity.this, LogInActivity.class));
            Toast.makeText(LessonsActivity.this, getString(R.string.logout_success), Toast.LENGTH_LONG).show();
        });

        if (!getIntent().getStringExtra("json").equals("")) {
            json_data = getIntent().getStringExtra("json");
        } else {
            json_data = "{\"0\":{\"success\":0},\"1\":{\"success\":0},\"2\":{\"success\":0},\"test1\":{\"success\":0},\"3\":{\"success\":0},\"4\":{\"success\":0},\"5\":{\"success\":0},\"test2\":{\"success\":0},\"6\":{\"success\":0},\"7\":{\"success\":0},\"8\":{\"success\":0},\"test3\":{\"success\":0},\"9\":{\"success\":0},\"finalTest\":{\"success\":0}}";
        }
        update_lessons_status();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                json_data = data.getStringExtra("json");
                update_lessons_status();
            }
        }
    }

    private void update_lessons_status() {

        JSONObject json;
        try {
            json = new JSONObject(json_data);
            zero_lesson = json.getJSONObject("0").getInt("success");
            one_lesson = json.getJSONObject("1").getInt("success");
            two_lesson = json.getJSONObject("2").getInt("success");
            three_lesson = json.getJSONObject("3").getInt("success");
            four_lesson = json.getJSONObject("4").getInt("success");
            five_lesson = json.getJSONObject("5").getInt("success");
            six_lesson = json.getJSONObject("6").getInt("success");
            seven_lesson = json.getJSONObject("7").getInt("success");
            eight_lesson = json.getJSONObject("8").getInt("success");
            nine_lesson = json.getJSONObject("9").getInt("success");
            review1 = json.getJSONObject("test1").getInt("success");
            review2 = json.getJSONObject("test2").getInt("success");
            review3 = json.getJSONObject("test3").getInt("success");
            final_review = json.getJSONObject("finalTest").getInt("success");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final int num = 80;

        set_colors(zero_lesson, txt_rate0, txt_des_rate0);
        card0.setOnClickListener(v -> {
            Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
            intent.putExtra("lesson_number", 0);
            intent.putExtra("json", json_data);
            startActivityForResult(intent, 1);
        });

        if (zero_lesson >= num) {
            set_colors(one_lesson, txt_rate1, txt_des_rate1);
            card1.setEnabled(true);
            card1.setForeground(null);
            card1.setOnClickListener(v -> {
                Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                intent.putExtra("lesson_number", 1);
                intent.putExtra("json", json_data);
                startActivityForResult(intent, 1);
            });
        } else {
            card1.setEnabled(false);
            card1.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
        }

        if (one_lesson >= num) {
            set_colors(two_lesson, txt_rate2, txt_des_rate2);
            card2.setEnabled(true);
            card2.setForeground(null);
            card2.setOnClickListener(v -> {
                Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                intent.putExtra("lesson_number", 2);
                intent.putExtra("json", json_data);
                startActivityForResult(intent, 1);
            });
        } else {
            card2.setEnabled(false);
            card2.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
        }

        if (two_lesson >= num) {
            set_colors(review1, txt_rater1, txt_des_rater1);
            cardTest1.setEnabled(true);
            cardTest1.setForeground(null);
            cardTest1.setOnClickListener(v -> {
                Intent intent = new Intent(LessonsActivity.this, TestActivity.class);
                intent.putExtra("from", "test1");
                intent.putExtra("page", 1);
                intent.putExtra("old_score", 0);
                intent.putExtra("json", json_data);
                startActivity(intent);
            });
        } else {
            cardTest1.setEnabled(false);
            cardTest1.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
        }

        if (review1 >= num) {
            set_colors(three_lesson, txt_rate3, txt_des_rate3);
            card3.setEnabled(true);
            card3.setForeground(null);
            card3.setOnClickListener(v -> {
                Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                intent.putExtra("lesson_number", 3);
                intent.putExtra("json", json_data);
                startActivityForResult(intent, 1);
            });
        } else {
            card3.setEnabled(false);
            card3.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
        }

        if (three_lesson >= num) {
            set_colors(four_lesson, txt_rate4, txt_des_rate4);
            card4.setEnabled(true);
            card4.setForeground(null);
            card4.setOnClickListener(v -> {
                Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                intent.putExtra("lesson_number", 4);
                intent.putExtra("json", json_data);
                startActivityForResult(intent, 1);
            });
        } else {
            card4.setEnabled(false);
            card4.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
        }

        if (four_lesson >= num) {
            set_colors(five_lesson, txt_rate5, txt_des_rate5);
            card5.setEnabled(true);
            card5.setForeground(null);
            card5.setOnClickListener(v -> {
                Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                intent.putExtra("lesson_number", 5);
                intent.putExtra("json", json_data);
                startActivityForResult(intent, 1);
            });
        } else {
            card5.setEnabled(false);
            card5.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
        }

        if (five_lesson >= num) {
            set_colors(review2, txt_rater2, txt_des_rater2);
            cardTest2.setEnabled(true);
            cardTest2.setForeground(null);
            cardTest2.setOnClickListener(v -> {
                Intent intent = new Intent(LessonsActivity.this, TestActivity.class);
                intent.putExtra("from", "test2");
                intent.putExtra("page", 1);
                intent.putExtra("old_score", 0);
                intent.putExtra("json", json_data);
                startActivity(intent);
            });
        } else {
            cardTest2.setEnabled(false);
            cardTest2.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
        }

        if (review2 >= num) {
            set_colors(six_lesson, txt_rate6, txt_des_rate6);
            card6.setEnabled(true);
            card6.setForeground(null);
            card6.setOnClickListener(v -> {
                Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                intent.putExtra("lesson_number", 6);
                intent.putExtra("json", json_data);
                startActivityForResult(intent, 1);
            });
        } else {
            card6.setEnabled(false);
            card6.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
        }

        if (six_lesson >= num) {
            set_colors(seven_lesson, txt_rate7, txt_des_rate7);
            card7.setEnabled(true);
            card7.setForeground(null);
            card7.setOnClickListener(v -> {
                Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                intent.putExtra("lesson_number", 7);
                intent.putExtra("json", json_data);
                startActivityForResult(intent, 1);
            });
        } else {
            card7.setEnabled(false);
            card7.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
        }

        if (seven_lesson >= num) {
            set_colors(eight_lesson, txt_rate8, txt_des_rate8);
            card8.setEnabled(true);
            card8.setForeground(null);
            card8.setOnClickListener(v -> {
                Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                intent.putExtra("lesson_number", 8);
                intent.putExtra("json", json_data);
                startActivityForResult(intent, 1);
            });
        } else {
            card8.setEnabled(false);
            card8.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
        }

        if (eight_lesson >= num) {
            set_colors(review3, txt_rater3, txt_des_rater3);
            cardTest3.setEnabled(true);
            cardTest3.setForeground(null);
            cardTest3.setOnClickListener(v -> {
                Intent intent = new Intent(LessonsActivity.this, TestActivity.class);
                intent.putExtra("from", "test3");
                intent.putExtra("page", 1);
                intent.putExtra("old_score", 0);
                intent.putExtra("json", json_data);
                startActivity(intent);
            });
        } else {
            cardTest3.setEnabled(false);
            cardTest3.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
        }

        if (review3 >= num) {
            set_colors(nine_lesson, txt_rate9, txt_des_rate9);
            card9.setEnabled(true);
            card9.setForeground(null);
            card9.setOnClickListener(v -> {
                Intent intent = new Intent(LessonsActivity.this, TheoryActivity.class);
                intent.putExtra("lesson_number", 9);
                intent.putExtra("json", json_data);
                startActivityForResult(intent, 1);
            });
        } else {
            card9.setEnabled(false);
            card9.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
        }

        if (nine_lesson >= num) {
            set_colors(final_review, txt_ratefr, txt_des_ratefr);
            cardFinalTest.setEnabled(true);
            cardFinalTest.setForeground(null);
            cardFinalTest.setOnClickListener(v -> {
                Intent intent = new Intent(LessonsActivity.this, TestActivity.class);
                intent.putExtra("from", "finalTest");
                intent.putExtra("page", 1);
                intent.putExtra("old_score", 0);
                intent.putExtra("json", json_data);
                startActivity(intent);
            });
        } else {
            cardFinalTest.setEnabled(false);
            cardFinalTest.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
        }

    }

    private void updateUI(FirebaseUser account) {

        if (account == null) {
            Toast.makeText(this, getString(R.string.log_out), Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LogInActivity.class));
        }
    }

    @Override
    public void onBackPressed() {

    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    private void set_colors(int lesson,TextView txt_rate,TextView txt_des_rate){
        txt_rate.setText(lesson +"%");
        if(lesson<50) {
            txt_rate.setTextColor(R.color.red);
            if(zero_lesson==0){
                txt_des_rate.setText(getString(R.string.poor));
            }else{
                txt_des_rate.setText(getString(R.string.good));
            }
            txt_des_rate.setTextColor(R.color.red);
        }
        if(lesson<80) {
            txt_rate.setTextColor(R.color.yellow);
            txt_des_rate.setText(getString(R.string.excellent));
            txt_des_rate.setTextColor(R.color.yellow);
        }
        if(lesson<=100) {
            txt_rate.setTextColor(R.color.green);
            txt_des_rate.setText(getString(R.string.perfect));
            txt_des_rate.setTextColor(R.color.green);
        }
    }
}