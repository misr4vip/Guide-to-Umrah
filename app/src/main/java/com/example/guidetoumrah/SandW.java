package com.example.guidetoumrah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SandW extends AppCompatActivity {
    private Button Button8;
    private Button Button12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sand_w);
        getSupportActionBar().setTitle("السنن والواجبات");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button8 = (Button) findViewById(R.id.button8);
        Button12 = (Button) findViewById(R.id.button12);


        Button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(SandW.this, Snn.class);
                startActivity(in1);
            }

        });
        Button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2 = new Intent(SandW.this, Wajbat.class);
                startActivity(in2);

            }

        });


    }
}

