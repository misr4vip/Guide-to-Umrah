package com.example.guidetoumrah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ServiceActivity extends AppCompatActivity {

    ListView lv ;
    ArrayList<serviceModel> modelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        modelList = new ArrayList<>();

        modelList.add(new serviceModel("تاكسي وباصات العزيزية",21.420181311453987, 39.8275439452952));
        modelList.add(new serviceModel("مركز شرطة الحرم الشريف",21.422925266424393, 39.83065080090224));
        modelList.add(new serviceModel("موقف الحافلات الي كدي",21.41744192896532, 39.82636999497764));
        modelList.add(new serviceModel("مركز طوارئ الحرم",21.422885266713365, 39.82171365300342));
        modelList.add(new serviceModel("محطة قطار الحرمين",21.426910194658845, 39.818634443671925));
        modelList.add(new serviceModel("النقل الجماعي التنعيم",21.42829845466546, 39.819385488419385));

        lv = findViewById(R.id.lvServices);
        lv.setAdapter(new myAdapter(this,R.layout.listview_cell,modelList));

    }
}