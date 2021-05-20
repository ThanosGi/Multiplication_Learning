package com.unipi.developers.multiplicationlearning;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Locale;

public class VideoActivity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    int lesson_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        lesson_number = getIntent().getIntExtra("lesson_number", 10);

        youTubePlayerView = findViewById(R.id.youtubePlay);
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                switch (lesson_number) {
                    case 0:
                        if (Locale.getDefault().getLanguage().equals("el")) {
                            youTubePlayer.loadVideo("AFc63zA52cA");
                        } else {
                            youTubePlayer.loadVideo("xR0Pi4DaQ8w");
                        }
                        break;
                    case 1:
                        if (Locale.getDefault().getLanguage().equals("el")) {
                            youTubePlayer.loadVideo("HDWXU45S2o8");
                        } else {
                            youTubePlayer.loadVideo("y_L1zD0b5uU");
                        }
                        break;
                    case 2:
                        if (Locale.getDefault().getLanguage().equals("el")) {
                            youTubePlayer.loadVideo("ZJnvaEF5K4Y");
                        } else {
                            youTubePlayer.loadVideo("fx2X0i-1JWQ");
                        }
                        break;
                    case 3:
                        if (Locale.getDefault().getLanguage().equals("el")) {
                            youTubePlayer.loadVideo("QzDbPcFaYXU");
                        } else {
                            youTubePlayer.loadVideo("K8GeEp8ReOQ");
                        }
                        break;
                    case 4:
                        if (Locale.getDefault().getLanguage().equals("el")) {
                            youTubePlayer.loadVideo("aLDSHfvPCtA");
                        } else {
                            youTubePlayer.loadVideo("_fddxui9y0U");
                        }
                        break;
                    case 5:
                        if (Locale.getDefault().getLanguage().equals("el")) {
                            youTubePlayer.loadVideo("x42bMxG5nfc");
                        } else {
                            youTubePlayer.loadVideo("4UbEDUbucrI");
                        }
                        break;
                    case 6:
                        if (Locale.getDefault().getLanguage().equals("el")) {
                            youTubePlayer.loadVideo("vK_-Mk8_GzI");
                        } else {
                            youTubePlayer.loadVideo("XT9VWW-gNes");
                        }
                        break;
                    case 7:
                        if (Locale.getDefault().getLanguage().equals("el")) {
                            youTubePlayer.loadVideo("Q760D8AJr0Q");
                        } else {
                            youTubePlayer.loadVideo("96yaX5uFMSg");
                        }
                        break;
                    case 8:
                        if (Locale.getDefault().getLanguage().equals("el")) {
                            youTubePlayer.loadVideo("3GspVyLttvo");
                        } else {
                            youTubePlayer.loadVideo("M04ZwnHObCA");
                        }
                        break;
                    case 9:
                        if (Locale.getDefault().getLanguage().equals("el")) {
                            youTubePlayer.loadVideo("M7q256fneZs");
                        } else {
                            youTubePlayer.loadVideo("rKF-fAXHUYg");
                        }
                        break;
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), "Failed to load video", Toast.LENGTH_SHORT).show();
            }
        };

        youTubePlayerView.initialize(YoutubeConfig.getApiKey(), onInitializedListener);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    public void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}