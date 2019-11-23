package com.heung.mobileapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SupportViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_view);
        this.getSupportActionBar().show();
        getSupportActionBar().setTitle("Message");

    }
}
