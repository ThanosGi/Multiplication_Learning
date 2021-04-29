package com.unipi.developers.multiplicationlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class TestActivity extends FullScreen {
    final int zeroID=R.drawable.zero, oneID=R.drawable.one, twoID=R.drawable.two, threeID=R.drawable.three, fourID=R.drawable.four, fiveID=R.drawable.five, sixID=R.drawable.six, sevenID=R.drawable.seven, eightID=R.drawable.eight, nineID=R.drawable.nine;
    ImageView num1, num2, num3, num4, num5, num6, result1_num1, result1_num2, result2_num1, result2_num2, result3_num1, result3_num2, next, page1, page2,  page3, page4, page5, page6, page7, page8, page9;
    Button true1, true2, true3, false1, false2, false3;
    int random, rUnit, id, mul, curPage;
    String[] result;
    String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

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

        curPage = getIntent().getIntExtra("page",1);
        from = getIntent().getStringExtra("from");

        if(from.equals("test1")){
            rUnit = 3;
        }else if(from.equals("test2")){
            rUnit = 6;
        }else if(from.equals("test3")){
            rUnit = 9;
        }else{
            rUnit = 10;
        }

        makeItDark(curPage);
        makeCards(rUnit);

        true1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                true1.setBackgroundColor(Color.WHITE);
                false1.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
            }
        });

        false1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                false1.setBackgroundColor(Color.WHITE);
                true1.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
            }
        });

        true2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                true2.setBackgroundColor(Color.WHITE);
                false2.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
            }
        });


        false2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                false2.setBackgroundColor(Color.WHITE);
                true2.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
            }
        });

        true3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                true3.setBackgroundColor(Color.WHITE);
                false3.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
            }
        });

        false3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                false3.setBackgroundColor(Color.WHITE);
                true3.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(curPage< 9) {
                    intent = new Intent(TestActivity.this, TestActivity.class);
                    intent.putExtra("from", from);
                    intent.putExtra("page", curPage + 1);
                }else{
                    intent = new Intent(TestActivity.this, LessonsActivity.class);
                }
                startActivity(intent);
            }
        });

    }

    private void makeItDark(int curPage) {
        switch (curPage){
            case 1:
                break;
            case 2:
                page1.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                break;
            case 3:
                page1.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page2.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                break;
            case 4:
                page1.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page2.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page3.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                break;
            case 5:
                page1.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page2.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page3.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page4.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                break;
            case 6:
                page1.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page2.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page3.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page4.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page5.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                break;
            case 7:
                page1.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page2.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page3.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page4.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page5.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page6.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                break;
            case 8:
                page1.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page2.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page3.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page4.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page5.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page6.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page7.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                break;
            case 9:
                page1.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page2.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page3.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page4.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page5.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page6.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page7.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                page8.setBackgroundColor(getColor(R.color.backcolor_grey_dark));
                break;
        }
    }

    private void makeCards(int rUnit) {
        mul=1;
        random = new Random().nextInt(rUnit);
        mul*=random;
        id = getTranslation(random);
        num1.setImageDrawable(ContextCompat.getDrawable(this, id));
        random = new Random().nextInt(rUnit);
        mul*=random;
        id = getTranslation(random);
        num2.setImageDrawable(ContextCompat.getDrawable(this, id));
        result = String.valueOf(mul).split("");
        if(result.length == 2){
            id = getTranslation(Integer.parseInt(String.valueOf(result[1])));
            result1_num2.setImageDrawable(ContextCompat.getDrawable(this, R.color.white));
            result1_num1.setImageDrawable(ContextCompat.getDrawable(this, id));
        }else{
            id = getTranslation(Integer.parseInt(String.valueOf(result[1])));
            result1_num1.setImageDrawable(ContextCompat.getDrawable(this, id));
            id = getTranslation(Integer.parseInt(String.valueOf(result[2])));
            result1_num2.setImageDrawable(ContextCompat.getDrawable(this, id));
        }


        mul=1;
        random = new Random().nextInt(rUnit);
        mul*=random;
        id = getTranslation(random);
        num3.setImageDrawable(ContextCompat.getDrawable(this, id));
        random = new Random().nextInt(rUnit);
        mul*=random;
        id = getTranslation(random);
        num4.setImageDrawable(ContextCompat.getDrawable(this, id));
        result = String.valueOf(mul).split("");
        if(result.length == 2){
            id = getTranslation(Integer.parseInt(String.valueOf(result[1])));
            result2_num2.setImageDrawable(ContextCompat.getDrawable(this, R.color.white));
            result2_num1.setImageDrawable(ContextCompat.getDrawable(this, id));
        }else {
            id = getTranslation(Integer.parseInt(String.valueOf(result[1])));
            result2_num1.setImageDrawable(ContextCompat.getDrawable(this, id));
            id = getTranslation(Integer.parseInt(String.valueOf(result[2])));
            result2_num2.setImageDrawable(ContextCompat.getDrawable(this, id));
        }


        mul=1;
        random = new Random().nextInt(rUnit);
        mul*=random;
        id = getTranslation(random);
        num5.setImageDrawable(ContextCompat.getDrawable(this, id));
        random = new Random().nextInt(rUnit);
        mul*=random;
        id = getTranslation(random);
        num6.setImageDrawable(ContextCompat.getDrawable(this, id));
        result = String.valueOf(mul).split("");
        if(result.length == 2){
            id = getTranslation(Integer.parseInt(String.valueOf(result[1])));
            result3_num2.setImageDrawable(ContextCompat.getDrawable(this, R.color.white));
            result3_num1.setImageDrawable(ContextCompat.getDrawable(this, id));
        }else {
            id = getTranslation(Integer.parseInt(String.valueOf(result[1])));
            result3_num1.setImageDrawable(ContextCompat.getDrawable(this, id));
            id = getTranslation(Integer.parseInt(String.valueOf(result[2])));
            result3_num2.setImageDrawable(ContextCompat.getDrawable(this, id));
        }
    }

    private int getTranslation(int random) {
        switch (random){
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
}