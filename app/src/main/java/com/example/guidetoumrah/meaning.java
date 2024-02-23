package com.example.guidetoumrah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class meaning extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meaning);
        getSupportActionBar().setTitle("معنى السنن والواجبات");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}