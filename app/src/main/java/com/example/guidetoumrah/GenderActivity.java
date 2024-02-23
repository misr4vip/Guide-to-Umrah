package com.example.guidetoumrah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

public class GenderActivity extends AppCompatActivity {

    RadioButton male , female ;
    Button next;
    boolean isMale = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        next = findViewById(R.id.btnSaveGender);
        male = findViewById(R.id.rbMale);
        female = findViewById(R.id.rbFemale);
        next.setOnClickListener(view->{
            if (female.isChecked())
            {
                isMale = false;

            }
            Intent i = new Intent(this,StartOfUmrahActivity.class);
            i.putExtra("isMale" , isMale);
            startActivity(i);

        });
    }
}