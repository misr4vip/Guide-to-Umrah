package com.example.guidetoumrah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

        private Button  button,Button2,button3,button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("دليل العمرة");
        button = (Button) findViewById(R.id.button);
        Button2 = (Button) findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(v->{
            startActivity(new Intent(this,ServiceActivity.class));
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLocationOfMiqat();
            }
        });
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sandw();
            }

        });
        button3.setOnClickListener(view->{
            startActivity(new Intent(this,GenderActivity.class));
        });
    }


    public void openLocationOfMiqat(){
        Intent intent = new Intent(this, location_of_miqat.class);
        startActivity(intent);
    }
    public void Sandw(){
        Intent int1 = new Intent(this, maps.class);
        startActivity(int1);
    }
}