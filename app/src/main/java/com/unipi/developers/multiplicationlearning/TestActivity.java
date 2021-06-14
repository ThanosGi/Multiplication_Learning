package com.unipi.developers.multiplicationlearning;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class TestActivity extends FullScreen {
    final int zeroID = R.drawable.zero, oneID = R.drawable.one, twoID = R.drawable.two, threeID = R.drawable.three, fourID = R.drawable.four, fiveID = R.drawable.five, sixID = R.drawable.six, sevenID = R.drawable.seven, eightID = R.drawable.eight, nineID = R.drawable.nine;
    ImageView num1, num2, num3, num4, num5, num6, result1_num1, result1_num2, result2_num1, result2_num2, result3_num1, result3_num2, next, page1, page2, page3, page4, page5, page6, page7, page8, page9, btn_help;
    Button true1, true2, true3, false1, false2, false3;
    boolean bTrue1 = false, bTrue2 = false, bTrue3 = false, bFalse1 = false, bFalse2 = false, bFalse3 = false;
    int random, random2, rUnit, rUnit2, id, mul, curPage;
    String from;
    String[] result;
    JSONObject wrongs;
    String wrongs_temp;
    private long backPressedTime;
    private Toast backToast;

    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context = this;
    private int score = 0;
    int last_score;
    Boolean answer1, answer2, answer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mAuth = FirebaseAuth.getInstance();

        btn_help = findViewById(R.id.btn_help);
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this,PdfViewerActivity.class);
                intent.putExtra("fromActivity","test");
                startActivity(intent);
            }
        });

        result1_num1 = findViewById(R.id.result1_num1);
        result1_num2 = findViewById(R.id.result1_num2);
        result2_num1 = findViewById(R.id.result2_num1);
        result2_num2 = findViewById(R.id.result2_num2);
        result3_num1 = findViewById(R.id.result3_num1);
        result3_num2 = findViewById(R.id.result3_num2);
        false1 = findViewById(R.id.btn_false1);
        false2 = findViewById(R.id.btn_false2);
        false3 = findViewById(R.id.btn_false3);
        true1 = findViewById(R.id.btn_true1);
        true2 = findViewById(R.id.btn_true2);
        true3 = findViewById(R.id.btn_true3);
        next = findViewById(R.id.btn_next);
        page1 = findViewById(R.id.Page1);
        page2 = findViewById(R.id.Page2);
        page3 = findViewById(R.id.Page3);
        page4 = findViewById(R.id.Page4);
        page5 = findViewById(R.id.Page5);
        page6 = findViewById(R.id.Page6);
        page7 = findViewById(R.id.Page7);
        page8 = findViewById(R.id.Page8);
        page9 = findViewById(R.id.Page9);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);

        curPage = getIntent().getIntExtra("page", 1);
        from = getIntent().getStringExtra("from");
        last_score = getIntent().getIntExtra("old_score", 0);

        //here it checks which test it is comes from to dynamically generate the numbers
        switch (from) {
            case "test1":
                rUnit = 3;
                rUnit2 = 19;
                break;
            case "test2":
                rUnit = 6;
                rUnit2 = 46;
                break;
            case "test3":
                rUnit = 9;
                rUnit2 = 73;
                break;
            default:
                rUnit = 10;
                rUnit2 = 82;
                break;
        }

        lockTimeLine(curPage);
        makeCards(rUnit);

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.WHITE);
        gd.setStroke(2, Color.BLUE);

        true1.setOnClickListener(v -> {
            bTrue1 = true;
            bFalse1 = false;
            true1.setBackground(gd);
            false1.setBackgroundColor(Color.WHITE);
        });

        false1.setOnClickListener(v -> {
            bTrue1 = false;
            bFalse1 = true;
            false1.setBackground(gd);
            true1.setBackgroundColor(Color.WHITE);
        });

        true2.setOnClickListener(v -> {
            bTrue2 = true;
            bFalse2 = false;
            true2.setBackground(gd);
            false2.setBackgroundColor(Color.WHITE);
        });


        false2.setOnClickListener(v -> {
            bTrue2 = false;
            bFalse2 = true;
            false2.setBackground(gd);
            true2.setBackgroundColor(Color.WHITE);
        });

        true3.setOnClickListener(v -> {
            bTrue3 = true;
            bFalse3 = false;
            true3.setBackground(gd);
            false3.setBackgroundColor(Color.WHITE);
        });

        false3.setOnClickListener(v -> {
            bTrue3 = false;
            bFalse3 = true;
            false3.setBackground(gd);
            true3.setBackgroundColor(Color.WHITE);
        });

        next.setOnClickListener(v -> {
            if (!((bTrue1 || bFalse1) && (bTrue2 || bFalse2) && (bTrue3 || bFalse3))) {
                Toast.makeText(this, R.string.warning, Toast.LENGTH_SHORT).show();
            } else {
                JSONObject jsonObject = new JSONObject();
                JSONObject json_temp;
                String wrongs_temp2="";
                try {
                    if(curPage==1){
                        wrongs_temp="";
                    }else{
                        wrongs_temp=getIntent().getStringExtra("wrongs_temp");
                    }
                    if ((true1.getBackground() == gd && answer1 || false1.getBackground() == gd && !answer1)
                            || (true2.getBackground() == gd && answer2 || false2.getBackground() == gd && !answer2)
                            || (true3.getBackground() == gd && answer3 || false3.getBackground() == gd && !answer3))
                        score = 10;
                    if(!(true1.getBackground() == gd && answer1 || false1.getBackground() == gd && !answer1)) {
                        if(!wrongs_temp2.equals("")) {
                            wrongs_temp2=wrongs_temp2+", "+(num1.getTag()+"x"+num2.getTag()+"="+result1_num1.getTag()+result1_num2.getTag());
                        } else {
                            wrongs_temp2=(num1.getTag()+"x"+num2.getTag()+"="+result1_num1.getTag()+result1_num2.getTag());
                        }
                    }
                    if(!(true2.getBackground() == gd && answer2 || false2.getBackground() == gd && !answer2)) {
                        if(!wrongs_temp2.equals("")) {
                            wrongs_temp2=wrongs_temp2+", "+(num3.getTag()+"x"+num4.getTag()+"="+result2_num1.getTag()+result2_num2.getTag());
                        } else {
                            wrongs_temp2=(num3.getTag()+"x"+num4.getTag()+"="+result2_num1.getTag()+result2_num2.getTag());
                        }
                    }
                    if(!(true3.getBackground() == gd && answer3 || false3.getBackground() == gd && !answer3)) {
                        if(!wrongs_temp2.equals("")) {
                            wrongs_temp2=wrongs_temp2+", "+(num5.getTag()+"x"+num6.getTag()+"="+result3_num1.getTag()+result3_num2.getTag());
                        } else {
                            wrongs_temp2=(num5.getTag()+"x"+num6.getTag()+"="+result3_num1.getTag()+result3_num2.getTag());
                        }

                    }
                    if(!wrongs_temp2.equals("")) {
                        if(!wrongs_temp.equals("")){
                            wrongs_temp=wrongs_temp+", "+wrongs_temp2;
                        }else {
                            wrongs_temp+=wrongs_temp2;
                        }
                    }


                    jsonObject = new JSONObject(getIntent().getStringExtra("json"));
                    wrongs=new JSONObject(getIntent().getStringExtra("wrongs"));


                    json_temp = new JSONObject();
                    if (curPage == 1) {
                        last_score = jsonObject.getJSONObject(from).getInt("success");
                        json_temp.put("success", score);
                    } else if (curPage < 9) {
                        json_temp.put("success", jsonObject.getJSONObject(from).getInt("success") + score);
                    } else if (curPage == 9 && last_score < jsonObject.getJSONObject(from).getInt("success") + score) {
                        if (jsonObject.getJSONObject(from).getInt("success") + score < 80) {
                            Toast.makeText(context, getString(R.string.lesson_continue), Toast.LENGTH_LONG).show();
                        }
                        json_temp.put("success", jsonObject.getJSONObject(from).getInt("success") + score);
                        wrongs.put(from,wrongs_temp);
                    } else {
                        json_temp.put("success", last_score);
                        wrongs.put(from,wrongs_temp);
                    }
                    jsonObject.put(from, json_temp);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent intent;
                if (curPage < 9) {
                    intent = new Intent(TestActivity.this, TestActivity.class);
                    intent.putExtra("from", from);
                    intent.putExtra("old_score", last_score);
                    intent.putExtra("page", curPage + 1);
                    intent.putExtra("wrongs_temp",wrongs_temp);
                } else {
                    Map<String, Object> user = new HashMap<>();
                    user.put("progress", jsonObject.toString());
                    user.put("wrongs",wrongs.toString());

                    db.collection("students")
                            .document(Objects.requireNonNull(mAuth.getUid()))
                            .set(user, SetOptions.merge())
                            .addOnSuccessListener(aVoid ->
                                    Toast.makeText(context, getString(R.string.progress), Toast.LENGTH_LONG).show())
                            .addOnFailureListener(e ->
                                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show());
                    intent = new Intent(TestActivity.this, LessonsActivity.class);
                }
                intent.putExtra("json", jsonObject.toString());
                intent.putExtra("wrongs",wrongs.toString());
                startActivity(intent);
                if (curPage < 9) overridePendingTransition(0, 0);
            }
        });

    }

    private void lockTimeLine(int curPage) {
        //this function keeps the progress of the test and makes the progress bar
        switch (curPage) {
            case 1:
                break;
            case 2:
                page1.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                break;
            case 3:
                page1.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page2.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                break;
            case 4:
                page1.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page2.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page3.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                break;
            case 5:
                page1.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page2.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page3.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page4.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                break;
            case 6:
                page1.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page2.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page3.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page4.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page5.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                break;
            case 7:
                page1.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page2.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page3.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page4.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page5.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page6.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                break;
            case 8:
                page1.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page2.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page3.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page4.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page5.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page6.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page7.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                break;
            case 9:
                page1.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page2.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page3.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page4.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page5.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page6.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page7.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                page8.setForeground(ContextCompat.getDrawable(this, R.drawable.fence));
                break;
        }
    }

    private void makeCards(int rUnit) {
        //this function makes the results of multiplies
        //making the result imageViews from the first multiply
        mul = 1;
        random = new Random().nextInt(rUnit);
        mul *= random;
        id = getTranslation(random);
        num1.setImageDrawable(ContextCompat.getDrawable(this, id));
        num1.setTag(String.valueOf(random));
        random = new Random().nextInt(rUnit);
        mul *= random;
        id = getTranslation(random);
        num2.setImageDrawable(ContextCompat.getDrawable(this, id));
        num2.setTag(String.valueOf(random));

        random2 = new Random().nextInt(2);
        if (random2 == 0) {
            answer1 = false;
            random2 = new Random().nextInt(rUnit2);
            fillTheIcons(random2, result1_num1, result1_num2);
        } else {
            answer1 = true;
            fillTheIcons(mul, result1_num1, result1_num2);
        }

        //making the result imageViews from the second multiply
        mul = 1;
        random = new Random().nextInt(rUnit);
        mul *= random;
        id = getTranslation(random);
        num3.setImageDrawable(ContextCompat.getDrawable(this, id));
        num3.setTag(String.valueOf(random));
        random = new Random().nextInt(rUnit);
        mul *= random;
        id = getTranslation(random);
        num4.setImageDrawable(ContextCompat.getDrawable(this, id));
        num4.setTag(String.valueOf(random));

        random2 = new Random().nextInt(2);
        if (random2 == 0) {
            answer2 = false;
            random2 = new Random().nextInt(rUnit2);
            fillTheIcons(random2, result2_num1, result2_num2);
        } else {
            answer2 = true;
            fillTheIcons(mul, result2_num1, result2_num2);
        }

        //making the result imageViews from the third multiply
        mul = 1;
        random = new Random().nextInt(rUnit);
        mul *= random;
        id = getTranslation(random);
        num5.setImageDrawable(ContextCompat.getDrawable(this, id));
        num5.setTag(String.valueOf(random));
        random = new Random().nextInt(rUnit);
        mul *= random;
        id = getTranslation(random);
        num6.setImageDrawable(ContextCompat.getDrawable(this, id));
        num6.setTag(String.valueOf(random));

        random2 = new Random().nextInt(2);
        if (random2 == 0) {
            answer3 = false;
            random2 = new Random().nextInt(rUnit2);
            fillTheIcons(random2, result3_num1, result3_num2);
        } else {
            answer3 = true;
            fillTheIcons(mul, result3_num1, result3_num2);
        }
    }

    public void fillTheIcons(int number, ImageView image1, ImageView image2) {
        //this function decides about the result of the multiplication, to put a wrong or the correct answer
        result = String.valueOf(number).split("");
        if (result[0].equals("")) {
            if (result.length == 2) {
                id = getTranslation(Integer.parseInt(result[1]));
                image2.setImageDrawable(ContextCompat.getDrawable(this, R.color.white));
                image2.setTag("");
                image1.setImageDrawable(ContextCompat.getDrawable(this, id));
                image1.setTag(result[1]);
            } else {
                id = getTranslation(Integer.parseInt(result[1]));
                image1.setImageDrawable(ContextCompat.getDrawable(this, id));
                image1.setTag(result[1]);
                id = getTranslation(Integer.parseInt(result[2]));
                image2.setImageDrawable(ContextCompat.getDrawable(this, id));
                image2.setTag(result[2]);
            }
        } else {
            if (result.length == 1) {
                id = getTranslation(Integer.parseInt(result[0]));
                image2.setImageDrawable(ContextCompat.getDrawable(this, R.color.white));
                image2.setTag("");
                image1.setImageDrawable(ContextCompat.getDrawable(this, id));
                image1.setTag(result[0]);
            } else {
                id = getTranslation(Integer.parseInt(result[0]));
                image1.setImageDrawable(ContextCompat.getDrawable(this, id));
                image1.setTag(result[0]);
                id = getTranslation(Integer.parseInt(result[1]));
                image2.setImageDrawable(ContextCompat.getDrawable(this, id));
                image2.setTag(result[1]);
            }
        }
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