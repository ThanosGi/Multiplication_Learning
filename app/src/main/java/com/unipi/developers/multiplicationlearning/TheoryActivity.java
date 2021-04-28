package com.unipi.developers.multiplicationlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;

public class TheoryActivity extends FullScreen {

    CustomScrollView scrollView;
    AppCompatButton btn_draw;
    boolean isDrawable = true;
    ImageView clear1,clear2,clear3,clear4,clear5,clear6,clear7,clear8,clear9;
    MyCanvas canvas1,canvas2,canvas3,canvas4,canvas5,canvas6,canvas7,canvas8,canvas9;
    AppCompatButton btn_seeVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory);

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
        btn_seeVideo = findViewById(R.id.btn_seeVideo);

        btn_seeVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TheoryActivity.this,VideoActivity.class));
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
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }
}