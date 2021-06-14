package com.unipi.developers.multiplicationlearning;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class WrongsActivity extends FullScreen {
    JSONObject wrongs;
    ImageView btn_help;
    TableLayout tableLayout;
    TextView lesson_row, wrongs_row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrongs);

        btn_help = findViewById(R.id.btn_help);
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WrongsActivity.this,PdfViewerActivity.class);
                intent.putExtra("fromActivity","wrongs");
                startActivity(intent);
            }
        });

        try {
            wrongs=new JSONObject(getIntent().getStringExtra("wrongs"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tableLayout = findViewById(R.id.tableLayout);

        try {
            Iterator<String> keys = wrongs.keys();
            while (keys.hasNext()){
                TableRow row = new TableRow(this);
                TableRow.LayoutParams lp;
                lp = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 4f);

                String lesson_key=keys.next();

                lesson_row = new TextView(this);
                lesson_row.setText(lesson_key);
                lesson_row.setTextColor(Color.rgb(249, 195, 89));
                lesson_row.setPadding(10, 10, 10, 10);
                lesson_row.setTextSize(20);
                lesson_row.setGravity(Gravity.CENTER_HORIZONTAL);
                lesson_row.setLayoutParams(lp);

                wrongs_row = new TextView(this);
                if(!wrongs.get(lesson_key).toString().equals("")){
                    wrongs_row.setText(wrongs.get(lesson_key).toString().replace(", ",",\n"));
                }else{
                    wrongs_row.setText(getString(R.string.none));
                }
                wrongs_row.setTextColor(Color.rgb(249, 195, 89));
                wrongs_row.setPadding(10, 10, 10, 10);
                wrongs_row.setTextSize(20);
                wrongs_row.setGravity(Gravity.CENTER_HORIZONTAL);
                wrongs_row.setLayoutParams(lp);
                row.addView(lesson_row);
                row.addView(wrongs_row);
                tableLayout.addView(row);
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Wrong Data", Toast.LENGTH_SHORT).show();
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