package com.example.guidetoumrah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class maps extends AppCompatActivity {
private FloatingActionButton navigation_btn;
    int round=0;
    String black_stone= String.valueOf(Uri.parse("google.navigation:q= 21.4219, 39.8261"));
    String current_location= String.valueOf(Uri.parse("google.navigation:q=21.4219, 39.8261"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        navigation_btn = findViewById(R.id.launch);
        navigation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("My Notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager manager = getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(channel);
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q= 21.422458, 39.826225&mode=w"));
                intent.setPackage("com.google.android.apps.maps");

                if(intent.resolveActivity(getPackageManager()) !=null){
                startActivity(intent);}
              while(round!=7){
               if(black_stone==current_location) {
                   NotificationCompat.Builder builder = new NotificationCompat.Builder(maps.this, "My Notification");
                    builder.setContentTitle("الطواف");
                    builder.setContentText("خلصت دورة رقم : "+round);
                    builder.setAutoCancel(true);
                   round++;
               }}

            }
        });


    }
}