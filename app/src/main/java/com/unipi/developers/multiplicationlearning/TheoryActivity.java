package com.unipi.developers.multiplicationlearning;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.SetOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TheoryActivity extends FullScreen {

    CustomScrollView scrollView;
    RelativeLayout relativeLayout;
    ImageView clear1,clear2,clear3,clear4,clear5,clear6,clear7,clear8,clear9;
    ImageView img_num1,img_num2,img_num3,img_num4,img_num5,img_num6,img_num7,img_num8,img_num9;
    ImageView img_res1_1,img_res2_1,img_res2_2,img_res3_1,img_res3_2,img_res4_1,img_res4_2,img_res5_1,img_res5_2,img_res6_1,img_res6_2,img_res7_1,img_res7_2,img_res8_1,img_res8_2,img_res9_1,img_res9_2;
    MyCanvas canvas1,canvas2,canvas3,canvas4,canvas5,canvas6,canvas7,canvas8,canvas9;
    AppCompatButton btn_seeVideo;
    int lesson_number;
    TextView txt_number;
    boolean click_canvas1,click_canvas2,click_canvas3,click_canvas4,click_canvas5,click_canvas6,click_canvas7,click_canvas8,click_canvas9;
    int score=1;
    private FirebaseAuth mAuth;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    Context context=this;
    String json_data;

    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory);

        mAuth = FirebaseAuth.getInstance();
        db.setFirestoreSettings(settings);

        lesson_number = getIntent().getIntExtra("lesson_number",10);

        scrollView = findViewById(R.id.scrollview);
        relativeLayout = findViewById(R.id.relativeLayout);
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

        btn_seeVideo.setOnClickListener(v -> {
            Intent intent = new Intent(TheoryActivity.this,VideoActivity.class);
            intent.putExtra("lesson_number",lesson_number);
            startActivity(intent);
        });


        scrollView.setEnableScrolling(true);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.setEnableScrolling(true);
            }
        });

        click_canvas1=false;
        canvas1.setOnClickListener(click->{
            if(!click_canvas1){
                score+=1;
            }
            scrollView.setEnableScrolling(false);
            click_canvas1=true;
        });
        clear1.setOnClickListener(v -> canvas1.clear());

        click_canvas2=false;
        canvas2.setOnClickListener(click->{
            if(!click_canvas2){
                score+=1;
            }
            scrollView.setEnableScrolling(false);
            click_canvas2=true;
        });
        clear2.setOnClickListener(v -> canvas2.clear());

        click_canvas3=false;
        canvas3.setOnClickListener(click->{
            if(!click_canvas3){
                score+=1;
            }
            scrollView.setEnableScrolling(false);
            click_canvas3=true;
        });
        clear3.setOnClickListener(v -> canvas3.clear());

        click_canvas4=false;
        canvas4.setOnClickListener(click->{
            if(!click_canvas4){
                score+=1;
            }
            scrollView.setEnableScrolling(false);
            click_canvas4=true;
        });
        clear4.setOnClickListener(v -> canvas4.clear());

        click_canvas5=false;
        canvas5.setOnClickListener(click->{
            if(!click_canvas5){
                score+=1;
            }
            scrollView.setEnableScrolling(false);
            click_canvas5=true;
        });
        clear5.setOnClickListener(v -> canvas5.clear());

        click_canvas6=false;
        canvas6.setOnClickListener(click->{
            if(!click_canvas6){
                score+=1;
            }
            scrollView.setEnableScrolling(false);
            click_canvas6=true;
        });
        clear6.setOnClickListener(v -> canvas6.clear());

        click_canvas7=false;
        canvas7.setOnClickListener(click->{
            if(!click_canvas7){
                score+=1;
            }
            scrollView.setEnableScrolling(false);
            click_canvas7=true;
        });
        clear7.setOnClickListener(v -> canvas7.clear());

        click_canvas8=false;
        canvas8.setOnClickListener(click->{
            if(!click_canvas8){
                score+=1;
            }
            scrollView.setEnableScrolling(false);
            click_canvas8=true;
        });
        clear8.setOnClickListener(v -> canvas8.clear());

        click_canvas9=false;
        canvas9.setOnClickListener(click->{
            if(!click_canvas9){
                score+=1;
            }
            scrollView.setEnableScrolling(false);
            click_canvas9=true;
        });
        clear9.setOnClickListener(v -> canvas9.clear());

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

    @Override
    public void onBackPressed() {
        save_data();
        Intent data = new Intent();
        data.putExtra("json", json_data);
        setResult(Activity.RESULT_OK, data);
        super.onBackPressed();
    }

    private void save_data(){
        JSONObject jsonObject = new JSONObject();
        JSONObject json_temp;
        try {
            json_temp= new JSONObject();
            json_temp.put("success",score*10);
            String temp = getIntent().getStringExtra("json");
            jsonObject = new JSONObject(temp);
            if(jsonObject.getJSONObject(String.valueOf(lesson_number)).getInt("success")<score*10){
                jsonObject.put(String.valueOf(lesson_number),json_temp);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        json_data=jsonObject.toString();

        Map<String, Object> user = new HashMap<>();
        user.put("progress", json_data);

        db.collection("students")
                .document(Objects.requireNonNull(mAuth.getUid()))
                .set(user, SetOptions.merge())
                .addOnSuccessListener(aVoid -> Toast.makeText(context, getString(R.string.progress), Toast.LENGTH_LONG).show()).addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show());
    }
}