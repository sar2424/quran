package com.example.quran;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SurahTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_text);

        TextView surahNameTextView = findViewById(R.id.surah_name_text_view);
        TextView surahTextView = findViewById(R.id.surah_text_view);

        // Get the data from the intent
        String surahName = getIntent().getStringExtra("surahName");
        String surahText = getIntent().getStringExtra("surahText");

        // Set the data to the TextViews
        surahNameTextView.setText(surahName);
        surahTextView.setText(surahText);
    }
}