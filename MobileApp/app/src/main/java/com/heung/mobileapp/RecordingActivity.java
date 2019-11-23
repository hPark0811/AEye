package com.heung.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RecordingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        getSupportActionBar().setTitle("Record");
        getSupportActionBar().show();
    }
}
