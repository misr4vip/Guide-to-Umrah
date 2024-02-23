package com.example.guidetoumrah;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class StartOfUmrahActivity extends AppCompatActivity {
    int LOCATION_REFRESH_TIME = 2000; // 2 seconds to update
    int LOCATION_REFRESH_DISTANCE = 10; // 10 meters to update
    int LOCATION_PERMISSION_REQUEST = 101;
    boolean isMale = true;
    ArrayList<String> azkarArray ;
    Location currentLocation;
    TextView tvAzkar, tvTawafCounter;
    int tawafCounter = 0, currentAzkarIndex = 0;
    LocationManager locationManager;
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {

            currentLocation = location;
            checkForStartOfTawaf(location);
            if (tvTawafCounter != null) {
                tvTawafCounter.setText(String.valueOf(tawafCounter));
            }
            if (tawafCounter >= 7)
            {

                    Intent i = new Intent(getApplicationContext(),AfterTawafActivity.class);
                    i.putExtra("isMale",isMale);
                    startActivity(i);


            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_of_umrah);
        tvAzkar = findViewById(R.id.tvAzkar);
        tvTawafCounter = findViewById(R.id.tvTwafCount);
        tvTawafCounter.setText(String.valueOf(tawafCounter));
        Intent i = getIntent();
        isMale = i.getBooleanExtra("isMale",true);
        if (isMale)
        {
            tvAzkar.setText("أخى المعتمر يجب عليك الكشف عن كتفك الايمن في الثلاث أشواط الأولى والهرولة مااستطعت ");
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        initArray();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        getLocationService();
        @SuppressLint("HandlerLeak") Handler mHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (tvAzkar != null)
                {
                    tvAzkar.setText(azkarArray.get(currentAzkarIndex));
                }

            }
        };
        TimerTask  task =  new TimerTask() {
            @Override
            public void run() {
                mHandler.obtainMessage(1).sendToTarget();

                if (currentAzkarIndex == azkarArray.size()-1)
                {
                    currentAzkarIndex = 0;
                }else{
                    currentAzkarIndex++;
                }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task,10000,10000);
    }

    public void getLocationService() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // request location permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST);

        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                    LOCATION_REFRESH_DISTANCE, locationListener);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                getLocationService();
            }
        }
    }

    public void checkForStartOfTawaf(Location location)
    {

        Gfg test = new Gfg();
        Gfg.Point[] polygon
                = { new Gfg.Point(21.4224, 39.8263 ), new Gfg.Point(21.4221, 39.8273 ), new Gfg.Point(21.4225, 39.8274 ), new Gfg.Point(21.4226, 39.8263 ) };
        Gfg.Point p = new Gfg.Point(location.getLatitude(), location.getLongitude() );
        int n = 4;


        if (test.checkInside(polygon, n, p)==1) {
            /// if haj location is same as tawaf start increase tawaf counter by 1

//
//            AlertDialog.Builder alert =  new AlertDialog.Builder(this);
//            alert.setTitle("رسالة نظام");
//            alert.setMessage("هل أتممت شوط كامل الان في الطواف");
//            alert.setPositiveButton("Yes", (dialog, id) -> {
                tawafCounter++;
//                dialog.cancel();
//            });
//            alert.setNegativeButton("No", (dialog, id) -> {
//                //  Action for 'NO' Button
//
//                dialog.cancel();
//            });
//            alert.create().show();

        }


    }



    @Override
    protected void onStop() {
        super.onStop();
        if(locationManager !=null)
            locationManager.removeUpdates(locationListener);
    }

    public void initArray()
    {
        azkarArray =  new ArrayList<>();
        azkarArray.add("اللهم إنا نسألك في هذا المقام المبارك أن تكتبنا من عتقائك من النار.");
        azkarArray.add("اللهم أعتق رقابَنا ورقابَ آبائنا وأمّهاتنا وسائر قراباتنا من النار يا عزيز يا غفّار.");
        azkarArray.add("اللهم لا تفرّق جمعنا هذا إلا بذنبٍ مغفور، وعيبٍ مستور، وتجارةٍ لن تبور، يا عزيز يا غفور");
        azkarArray.add("اللهم اجعل اجتماعنا هذا اجتماعًا مرحومًا، واجعل تفرّقنا بعده تفرّقًا معصومًا، ولا تجعل معنا شقيًا ولا محرومًا.");
        azkarArray.add("اللهم لا تدع لنا ذنبًا إلا غفرته، ولا همًا إلا فرجته، ولا كربًا إلا نفَّسْته، ولا غما إلا أزلته، ولا دَيْنًا إلا قضيته، ولا عسيرًا إلا يسّرته، ولا عيبًا إلا سترته، ولا مبتلًا إلا عافيته، ولا مريضًا إلا شفيته، ولا ميتًا إلا رحمته، ولا عدوا إلا أهلكته، ولا مجاهدًا إلا نصرته ولا مظلوما إلا أيّدته، ولا ظالما إلا قصمته، ولا ضالًا إلا هديته، ولا حاجة من حوائج الدنيا والآخرة لك فيها رضا ولنا فيها صلاح إلا أعَنتنا على قضائها ويسّرتها برحمتك يا أرحم الراحمين.");
        azkarArray.add("اللهم أن هذا البيت بيتك والحرم حرمك والأمن أمنك والعبد عبدك وأنا عبدك وابن عبدك وهذا مقام العائذ بك من النار وحرم لحومنا وبشرتنا على النار.");
    }

}