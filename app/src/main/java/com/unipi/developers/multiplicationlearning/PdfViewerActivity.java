package com.unipi.developers.multiplicationlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class PdfViewerActivity extends FullScreen {

    String fromActivity,assetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        PDFView pdfView = findViewById(R.id.pdfView);

        fromActivity = getIntent().getStringExtra("fromActivity");

        switch (fromActivity){
            case "login":
                assetName = "Σελίδα_Εισόδου.pdf";
                break;
            case "singupteacher":
                assetName = "Σελίδα_Εγγραφής_(καθηγητής).pdf";
                break;
            case "signupstudent":
                assetName = "Σελίδα_Εγγραφής_(μαθητής).pdf";
                break;
            case "minitest":
                assetName = "Σελίδα_αξιολογήσεων_ανα_μάθημα_(μαθητής).pdf";
                break;
            case "test":
                assetName = "Σελίδα_επαναληπτικών_αξιολογήσεων_(μαθητής).pdf";
                break;
            case "createaccount":
                assetName = "Σελίδα_επιλογής_ρόλου_για_εγγραφή_χρήστη.pdf";
                break;
            case "theory":
                assetName = "Σελίδα_Θεωρίας_(μαθητής).pdf";
                break;
            case "teacher":
                assetName = "Σελίδα_Καθηγητή.pdf";
                break;
            case "lessons":
                assetName = "Σελίδα_Μαθημάτων_(μαθητής).pdf";
                break;
            case "wrongs":
                assetName = "Σελίδα_παρουσίασης_λαθών.pdf";
                break;
            case "myclasses":
                assetName = "Σελίδα_προβολής_τάξεων_(καθηγητής).pdf";
                break;
            case "createclass":
                assetName = "Σελίδα_προσθήκης_τάξης_(καθηγητής).pdf";
                break;
            case "stats":
                assetName = "Σελίδα_Στατιστικών_(καθηγητής).pdf";
                break;
        }

        pdfView.fromAsset(assetName).load();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }
}