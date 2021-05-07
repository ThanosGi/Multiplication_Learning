package com.unipi.developers.multiplicationlearning;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TheoryActivity extends FullScreen {

    CustomScrollView scrollView;
    AppCompatButton btn_draw;
    boolean isDrawable = true;
    ImageView clear1,clear2,clear3,clear4,clear5,clear6,clear7,clear8,clear9;
    ImageView img_num1,img_num2,img_num3,img_num4,img_num5,img_num6,img_num7,img_num8,img_num9;
    ImageView img_res1_1,img_res2_1,img_res2_2,img_res3_1,img_res3_2,img_res4_1,img_res4_2,img_res5_1,img_res5_2,img_res6_1,img_res6_2,img_res7_1,img_res7_2,img_res8_1,img_res8_2,img_res9_1,img_res9_2;
    MyCanvas canvas1,canvas2,canvas3,canvas4,canvas5,canvas6,canvas7,canvas8,canvas9;
    AppCompatButton btn_seeVideo;
    int lesson_number;
    TextView txt_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory);

        lesson_number = getIntent().getIntExtra("lesson_number",10);

        scrollView = findViewById(R.id.scrollview);
        clear1 = findViewById(R.id.clear1);
        clear2 = findViewById(R.id.clear2);
        clear3 = findViewById(R.id.clear3);
        clear4 = findViewById(R.id.clear4);
        clear5 = findViewById(R.id.clear5);
        clear6 = findViewById(R.id.clear6);
        clear7 = findViewById(R.id.clear7);
        clear8 = findViewById(R.id.clear8);
        clear9 = findViewById(R.id.clear9);
        canvas1 = findViewById(R.id.canvas1);
        canvas2 = findViewById(R.id.canvas2);
        canvas3 = findViewById(R.id.canvas3);
        canvas4 = findViewById(R.id.canvas4);
        canvas5 = findViewById(R.id.canvas5);
        canvas6 = findViewById(R.id.canvas6);
        canvas7 = findViewById(R.id.canvas7);
        canvas8 = findViewById(R.id.canvas8);
        canvas9 = findViewById(R.id.canvas9);
        img_num1 = findViewById(R.id.img_num1);
        img_num2 = findViewById(R.id.img_num2);
        img_num3 = findViewById(R.id.img_num3);
        img_num4 = findViewById(R.id.img_num4);
        img_num5 = findViewById(R.id.img_num5);
        img_num6 = findViewById(R.id.img_num6);
        img_num7 = findViewById(R.id.img_num7);
        img_num8 = findViewById(R.id.img_num8);
        img_num9 = findViewById(R.id.img_num9);
        img_res1_1 = findViewById(R.id.img_res1_1);
        img_res2_1 = findViewById(R.id.img_res2_1);
        img_res2_2 = findViewById(R.id.img_res2_2);
        img_res3_1 = findViewById(R.id.img_res3_1);
        img_res3_2 = findViewById(R.id.img_res3_2);
        img_res4_1 = findViewById(R.id.img_res4_1);
        img_res4_2 = findViewById(R.id.img_res4_2);
        img_res5_1 = findViewById(R.id.img_res5_1);
        img_res5_2 = findViewById(R.id.img_res5_2);
        img_res6_1 = findViewById(R.id.img_res6_1);
        img_res6_2 = findViewById(R.id.img_res6_2);
        img_res7_1 = findViewById(R.id.img_res7_1);
        img_res7_2 = findViewById(R.id.img_res7_2);
        img_res8_1 = findViewById(R.id.img_res8_1);
        img_res8_2 = findViewById(R.id.img_res8_2);
        img_res9_1 = findViewById(R.id.img_res9_1);
        img_res9_2 = findViewById(R.id.img_res9_2);
        txt_number = findViewById(R.id.txt_number);
        btn_seeVideo = findViewById(R.id.btn_seeVideo);

        btn_seeVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TheoryActivity.this,VideoActivity.class);
                intent.putExtra("lesson_number",lesson_number);
                startActivity(intent);
            }
        });

        scrollView.setEnableScrolling(true);
        canvas1.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
        canvas2.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
        canvas3.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
        canvas4.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
        canvas5.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
        canvas6.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
        canvas7.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
        canvas8.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
        canvas9.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));

        clear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas1.clear();
            }
        });
        clear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas2.clear();
            }
        });
        clear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas3.clear();
            }
        });
        clear4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas4.clear();
            }
        });
        clear5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas5.clear();
            }
        });
        clear6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas6.clear();
            }
        });
        clear7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas7.clear();
            }
        });
        clear8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas8.clear();
            }
        });
        clear9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas9.clear();
            }
        });


        btn_draw = findViewById(R.id.btn_draw);
        btn_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.setEnableScrolling(!isDrawable);
                if (!isDrawable){
                    canvas1.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
                    canvas2.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
                    canvas3.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
                    canvas4.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
                    canvas5.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
                    canvas6.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
                    canvas7.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
                    canvas8.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
                    canvas9.setForeground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.fence));
                }else {
                    canvas1.setForeground(null);
                    canvas2.setForeground(null);
                    canvas3.setForeground(null);
                    canvas4.setForeground(null);
                    canvas5.setForeground(null);
                    canvas6.setForeground(null);
                    canvas7.setForeground(null);
                    canvas8.setForeground(null);
                    canvas9.setForeground(null);
                }
                isDrawable = !isDrawable;
            }
        });

        switch (lesson_number){
            case 0:
                txt_number.setText(R.string.number_zero);
                img_num1.setImageResource(R.drawable.zero);
                img_num2.setImageResource(R.drawable.zero);
                img_num3.setImageResource(R.drawable.zero);
                img_num4.setImageResource(R.drawable.zero);
                img_num5.setImageResource(R.drawable.zero);
                img_num6.setImageResource(R.drawable.zero);
                img_num7.setImageResource(R.drawable.zero);
                img_num8.setImageResource(R.drawable.zero);
                img_num9.setImageResource(R.drawable.zero);
                img_res1_1.setImageResource(R.drawable.zero);
                img_res2_1.setImageResource(R.drawable.zero);
                img_res2_2.setVisibility(View.INVISIBLE);
                img_res3_1.setImageResource(R.drawable.zero);
                img_res3_2.setVisibility(View.INVISIBLE);
                img_res4_1.setImageResource(R.drawable.zero);
                img_res4_2.setVisibility(View.INVISIBLE);
                img_res5_1.setImageResource(R.drawable.zero);
                img_res5_2.setVisibility(View.INVISIBLE);
                img_res6_1.setImageResource(R.drawable.zero);
                img_res6_2.setVisibility(View.INVISIBLE);
                img_res7_1.setImageResource(R.drawable.zero);
                img_res7_2.setVisibility(View.INVISIBLE);
                img_res8_1.setImageResource(R.drawable.zero);
                img_res8_2.setVisibility(View.INVISIBLE);
                img_res9_1.setImageResource(R.drawable.zero);
                img_res9_2.setVisibility(View.INVISIBLE);
                break;
            case 1:
                txt_number.setText(R.string.number_one);
                img_num1.setImageResource(R.drawable.one);
                img_num2.setImageResource(R.drawable.one);
                img_num3.setImageResource(R.drawable.one);
                img_num4.setImageResource(R.drawable.one);
                img_num5.setImageResource(R.drawable.one);
                img_num6.setImageResource(R.drawable.one);
                img_num7.setImageResource(R.drawable.one);
                img_num8.setImageResource(R.drawable.one);
                img_num9.setImageResource(R.drawable.one);
                img_res1_1.setImageResource(R.drawable.one);
                img_res2_1.setImageResource(R.drawable.two);
                img_res2_2.setVisibility(View.INVISIBLE);
                img_res3_1.setImageResource(R.drawable.three);
                img_res3_2.setVisibility(View.INVISIBLE);
                img_res4_1.setImageResource(R.drawable.four);
                img_res4_2.setVisibility(View.INVISIBLE);
                img_res5_1.setImageResource(R.drawable.five);
                img_res5_2.setVisibility(View.INVISIBLE);
                img_res6_1.setImageResource(R.drawable.six);
                img_res6_2.setVisibility(View.INVISIBLE);
                img_res7_1.setImageResource(R.drawable.seven);
                img_res7_2.setVisibility(View.INVISIBLE);
                img_res8_1.setImageResource(R.drawable.eight);
                img_res8_2.setVisibility(View.INVISIBLE);
                img_res9_1.setImageResource(R.drawable.nine);
                img_res9_2.setVisibility(View.INVISIBLE);
                break;
            case 2:
                txt_number.setText(R.string.number_two);
                img_num1.setImageResource(R.drawable.two);
                img_num2.setImageResource(R.drawable.two);
                img_num3.setImageResource(R.drawable.two);
                img_num4.setImageResource(R.drawable.two);
                img_num5.setImageResource(R.drawable.two);
                img_num6.setImageResource(R.drawable.two);
                img_num7.setImageResource(R.drawable.two);
                img_num8.setImageResource(R.drawable.two);
                img_num9.setImageResource(R.drawable.two);
                img_res1_1.setImageResource(R.drawable.two);
                img_res2_1.setImageResource(R.drawable.four);
                img_res2_2.setVisibility(View.INVISIBLE);
                img_res3_1.setImageResource(R.drawable.six);
                img_res3_2.setVisibility(View.INVISIBLE);
                img_res4_1.setImageResource(R.drawable.eight);
                img_res4_2.setVisibility(View.INVISIBLE);
                img_res5_1.setImageResource(R.drawable.one);
                img_res5_2.setImageResource(R.drawable.zero);
                img_res6_1.setImageResource(R.drawable.one);
                img_res6_2.setImageResource(R.drawable.two);
                img_res7_1.setImageResource(R.drawable.one);
                img_res7_2.setImageResource(R.drawable.four);
                img_res8_1.setImageResource(R.drawable.one);
                img_res8_2.setImageResource(R.drawable.six);
                img_res9_1.setImageResource(R.drawable.one);
                img_res9_2.setImageResource(R.drawable.eight);
                break;
            case 3:
                txt_number.setText(R.string.number_three);
                img_num1.setImageResource(R.drawable.three);
                img_num2.setImageResource(R.drawable.three);
                img_num3.setImageResource(R.drawable.three);
                img_num4.setImageResource(R.drawable.three);
                img_num5.setImageResource(R.drawable.three);
                img_num6.setImageResource(R.drawable.three);
                img_num7.setImageResource(R.drawable.three);
                img_num8.setImageResource(R.drawable.three);
                img_num9.setImageResource(R.drawable.three);
                img_res1_1.setImageResource(R.drawable.three);
                img_res2_1.setImageResource(R.drawable.six);
                img_res2_2.setVisibility(View.INVISIBLE);
                img_res3_1.setImageResource(R.drawable.nine);
                img_res3_2.setVisibility(View.INVISIBLE);
                img_res4_1.setImageResource(R.drawable.one);
                img_res4_2.setImageResource(R.drawable.two);
                img_res5_1.setImageResource(R.drawable.one);
                img_res5_2.setImageResource(R.drawable.five);
                img_res6_1.setImageResource(R.drawable.one);
                img_res6_2.setImageResource(R.drawable.eight);
                img_res7_1.setImageResource(R.drawable.two);
                img_res7_2.setImageResource(R.drawable.one);
                img_res8_1.setImageResource(R.drawable.two);
                img_res8_2.setImageResource(R.drawable.four);
                img_res9_1.setImageResource(R.drawable.two);
                img_res9_2.setImageResource(R.drawable.seven);
                break;
            case 4:
                txt_number.setText(R.string.number_four);
                img_num1.setImageResource(R.drawable.four);
                img_num2.setImageResource(R.drawable.four);
                img_num3.setImageResource(R.drawable.four);
                img_num4.setImageResource(R.drawable.four);
                img_num5.setImageResource(R.drawable.four);
                img_num6.setImageResource(R.drawable.four);
                img_num7.setImageResource(R.drawable.four);
                img_num8.setImageResource(R.drawable.four);
                img_num9.setImageResource(R.drawable.four);
                img_res1_1.setImageResource(R.drawable.four);
                img_res2_1.setImageResource(R.drawable.eight);
                img_res2_2.setVisibility(View.INVISIBLE);
                img_res3_1.setImageResource(R.drawable.one);
                img_res3_2.setImageResource(R.drawable.two);
                img_res4_1.setImageResource(R.drawable.two);
                img_res4_2.setImageResource(R.drawable.six);
                img_res5_1.setImageResource(R.drawable.two);
                img_res5_2.setImageResource(R.drawable.zero);
                img_res6_1.setImageResource(R.drawable.two);
                img_res6_2.setImageResource(R.drawable.four);
                img_res7_1.setImageResource(R.drawable.two);
                img_res7_2.setImageResource(R.drawable.eight);
                img_res8_1.setImageResource(R.drawable.three);
                img_res8_2.setImageResource(R.drawable.two);
                img_res9_1.setImageResource(R.drawable.three);
                img_res9_2.setImageResource(R.drawable.six);
                break;
            case 5:
                txt_number.setText(R.string.number_five);
                img_num1.setImageResource(R.drawable.five);
                img_num2.setImageResource(R.drawable.five);
                img_num3.setImageResource(R.drawable.five);
                img_num4.setImageResource(R.drawable.five);
                img_num5.setImageResource(R.drawable.five);
                img_num6.setImageResource(R.drawable.five);
                img_num7.setImageResource(R.drawable.five);
                img_num8.setImageResource(R.drawable.five);
                img_num9.setImageResource(R.drawable.five);
                img_res1_1.setImageResource(R.drawable.five);
                img_res2_1.setImageResource(R.drawable.one);
                img_res2_2.setImageResource(R.drawable.zero);
                img_res3_1.setImageResource(R.drawable.one);
                img_res3_2.setImageResource(R.drawable.five);
                img_res4_1.setImageResource(R.drawable.two);
                img_res4_2.setImageResource(R.drawable.zero);
                img_res5_1.setImageResource(R.drawable.two);
                img_res5_2.setImageResource(R.drawable.five);
                img_res6_1.setImageResource(R.drawable.three);
                img_res6_2.setImageResource(R.drawable.zero);
                img_res7_1.setImageResource(R.drawable.three);
                img_res7_2.setImageResource(R.drawable.five);
                img_res8_1.setImageResource(R.drawable.four);
                img_res8_2.setImageResource(R.drawable.zero);
                img_res9_1.setImageResource(R.drawable.four);
                img_res9_2.setImageResource(R.drawable.five);
                break;
            case 6:
                txt_number.setText(R.string.number_six);
                img_num1.setImageResource(R.drawable.six);
                img_num2.setImageResource(R.drawable.six);
                img_num3.setImageResource(R.drawable.six);
                img_num4.setImageResource(R.drawable.six);
                img_num5.setImageResource(R.drawable.six);
                img_num6.setImageResource(R.drawable.six);
                img_num7.setImageResource(R.drawable.six);
                img_num8.setImageResource(R.drawable.six);
                img_num9.setImageResource(R.drawable.six);
                img_res1_1.setImageResource(R.drawable.six);
                img_res2_1.setImageResource(R.drawable.one);
                img_res2_2.setImageResource(R.drawable.two);
                img_res3_1.setImageResource(R.drawable.one);
                img_res3_2.setImageResource(R.drawable.eight);
                img_res4_1.setImageResource(R.drawable.two);
                img_res4_2.setImageResource(R.drawable.four);
                img_res5_1.setImageResource(R.drawable.three);
                img_res5_2.setImageResource(R.drawable.zero);
                img_res6_1.setImageResource(R.drawable.three);
                img_res6_2.setImageResource(R.drawable.six);
                img_res7_1.setImageResource(R.drawable.four);
                img_res7_2.setImageResource(R.drawable.two);
                img_res8_1.setImageResource(R.drawable.four);
                img_res8_2.setImageResource(R.drawable.eight);
                img_res9_1.setImageResource(R.drawable.five);
                img_res9_2.setImageResource(R.drawable.four);
                break;
            case 7:
                txt_number.setText(R.string.number_seven);
                img_num1.setImageResource(R.drawable.seven);
                img_num2.setImageResource(R.drawable.seven);
                img_num3.setImageResource(R.drawable.seven);
                img_num4.setImageResource(R.drawable.seven);
                img_num5.setImageResource(R.drawable.seven);
                img_num6.setImageResource(R.drawable.seven);
                img_num7.setImageResource(R.drawable.seven);
                img_num8.setImageResource(R.drawable.seven);
                img_num9.setImageResource(R.drawable.seven);
                img_res1_1.setImageResource(R.drawable.seven);
                img_res2_1.setImageResource(R.drawable.one);
                img_res2_2.setImageResource(R.drawable.four);
                img_res3_1.setImageResource(R.drawable.two);
                img_res3_2.setImageResource(R.drawable.one);
                img_res4_1.setImageResource(R.drawable.two);
                img_res4_2.setImageResource(R.drawable.eight);
                img_res5_1.setImageResource(R.drawable.three);
                img_res5_2.setImageResource(R.drawable.five);
                img_res6_1.setImageResource(R.drawable.four);
                img_res6_2.setImageResource(R.drawable.two);
                img_res7_1.setImageResource(R.drawable.four);
                img_res7_2.setImageResource(R.drawable.nine);
                img_res8_1.setImageResource(R.drawable.five);
                img_res8_2.setImageResource(R.drawable.six);
                img_res9_1.setImageResource(R.drawable.six);
                img_res9_2.setImageResource(R.drawable.three);
                break;
            case 8:
                txt_number.setText(R.string.number_eight);
                img_num1.setImageResource(R.drawable.eight);
                img_num2.setImageResource(R.drawable.eight);
                img_num3.setImageResource(R.drawable.eight);
                img_num4.setImageResource(R.drawable.eight);
                img_num5.setImageResource(R.drawable.eight);
                img_num6.setImageResource(R.drawable.eight);
                img_num7.setImageResource(R.drawable.eight);
                img_num8.setImageResource(R.drawable.eight);
                img_num9.setImageResource(R.drawable.eight);
                img_res1_1.setImageResource(R.drawable.eight);
                img_res2_1.setImageResource(R.drawable.one);
                img_res2_2.setImageResource(R.drawable.six);
                img_res3_1.setImageResource(R.drawable.two);
                img_res3_2.setImageResource(R.drawable.four);
                img_res4_1.setImageResource(R.drawable.three);
                img_res4_2.setImageResource(R.drawable.two);
                img_res5_1.setImageResource(R.drawable.four);
                img_res5_2.setImageResource(R.drawable.zero);
                img_res6_1.setImageResource(R.drawable.four);
                img_res6_2.setImageResource(R.drawable.eight);
                img_res7_1.setImageResource(R.drawable.five);
                img_res7_2.setImageResource(R.drawable.six);
                img_res8_1.setImageResource(R.drawable.six);
                img_res8_2.setImageResource(R.drawable.four);
                img_res9_1.setImageResource(R.drawable.seven);
                img_res9_2.setImageResource(R.drawable.two);
                break;
            case 9:
                txt_number.setText(R.string.number_nine);
                img_num1.setImageResource(R.drawable.nine);
                img_num2.setImageResource(R.drawable.nine);
                img_num3.setImageResource(R.drawable.nine);
                img_num4.setImageResource(R.drawable.nine);
                img_num5.setImageResource(R.drawable.nine);
                img_num6.setImageResource(R.drawable.nine);
                img_num7.setImageResource(R.drawable.nine);
                img_num8.setImageResource(R.drawable.nine);
                img_num9.setImageResource(R.drawable.nine);
                img_res1_1.setImageResource(R.drawable.nine);
                img_res2_1.setImageResource(R.drawable.one);
                img_res2_2.setImageResource(R.drawable.eight);
                img_res3_1.setImageResource(R.drawable.two);
                img_res3_2.setImageResource(R.drawable.seven);
                img_res4_1.setImageResource(R.drawable.three);
                img_res4_2.setImageResource(R.drawable.six);
                img_res5_1.setImageResource(R.drawable.four);
                img_res5_2.setImageResource(R.drawable.five);
                img_res6_1.setImageResource(R.drawable.five);
                img_res6_2.setImageResource(R.drawable.four);
                img_res7_1.setImageResource(R.drawable.six);
                img_res7_2.setImageResource(R.drawable.three);
                img_res8_1.setImageResource(R.drawable.seven);
                img_res8_2.setImageResource(R.drawable.two);
                img_res9_1.setImageResource(R.drawable.eight);
                img_res9_2.setImageResource(R.drawable.one);
                break;
            default:
                break;
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