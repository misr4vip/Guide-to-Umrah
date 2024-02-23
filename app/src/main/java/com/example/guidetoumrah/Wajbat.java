package com.example.guidetoumrah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Wajbat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wajbat);
        getSupportActionBar().setTitle("الواجبات");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}