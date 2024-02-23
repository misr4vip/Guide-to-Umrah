package com.example.guidetoumrah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

public class AfterTawafActivity extends AppCompatActivity {

    boolean isMale = false , isPressed = false;
    TextView tv ;
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_tawaf);
        tv = findViewById(R.id.tvAfterTawaf);
        Intent i = getIntent();
        isMale = i.getBooleanExtra("isMale",true);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(v->{
            if (isPressed){
                Intent next = new Intent(getApplicationContext(),SaieeActivity.class);
                next.putExtra("isMale",isMale);
                next.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(next);
            }else
            {
                tv.setText("أخي المعتمر عند الضوء الاخضر يجب عليك الهرولة قدر الامكان والهرولة للرجال دون النساء");
                isPressed = true;
            }
        });


    }
}