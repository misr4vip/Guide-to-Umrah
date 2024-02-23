 package com.example.guidetoumrah;



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


 public class SaieeActivity extends AppCompatActivity {
     int LOCATION_REFRESH_TIME = 2000; // 2 seconds to update
     int LOCATION_REFRESH_DISTANCE = 10; // 10 meters to update
     int LOCATION_PERMISSION_REQUEST = 101;

     public static final String TAG = "SaieeActivity";
     ArrayList<String>  safaArray = new ArrayList<>();
     ArrayList<String>  marwaArray = new ArrayList<>();
     ArrayList<String>  azkarArray = new ArrayList<>();
     ArrayList<String>  currentArray = new ArrayList<>();
     TextView tvAzkar, tvSaieCounter, tvAzkarTitle;

     int saieCounter = 0, currentAzkarIndex = 0, safaCounter= 0 , marwaCounter = 0;
     LocationManager locationManager;
     private final LocationListener locationListener = new LocationListener() {
         @Override
         public void onLocationChanged(@NonNull Location location) {
             Log.e(TAG, "safaCounter :" + safaCounter );
             Log.e(TAG, "marwaCounter :" + marwaCounter );
             Log.e(TAG, "saieCounter :" + saieCounter );
             if (saieCounter >= 7)
             {

                 tvAzkar.setText("مبارك أخي الحاج لقد اتممت السعي يجب عليك الان الحل من الاحرام عن طريق قص جزء من الشعر ");
                 new Handler().postDelayed(() -> {
                     startActivity(new Intent(getApplicationContext(),MainActivity.class));
                 },10000);


             }else if (checkIsSafaLocation(location) && safaCounter == 0) {
                 Log.e(TAG, "onLocationChanged:start of  Start Of SAFA" );

                 tvAzkarTitle.setText("أنت الان في بداية المسعى يجب أن تستقبل القبلة وتقرأ تلك الاذكار");
                 safaCounter++;
                 currentArray = safaArray;
                 currentAzkarIndex = 0;
                 Log.e(TAG, "onLocationChanged:end of Start Of SAFA" );


             }else if (checkIsSafaLocation(location) && safaCounter > 0)
             {
                 Log.e(TAG, "onLocationChanged: start of  SAFA > 0" );
                 tvAzkarTitle.setText("أذكار مستحبة في الصفا ");
                 saieCounter++;
                 safaCounter++;
                 currentAzkarIndex = 0;
                 currentArray = safaArray;
                 Log.e(TAG, "onLocationChanged: end of  SAFA > 0" );
             }else if (checkIsMarwaLocation(location))
             {
                 Log.e(TAG, "onLocationChanged: start of  Marwa" );
                 tvAzkarTitle.setText("أذكار مستحبة في المروة ");
                 saieCounter++;
                 marwaCounter++;
                 currentAzkarIndex = 0;
                 currentArray = marwaArray;
                 Log.e(TAG, "onLocationChanged: end of  Marwa" );
             }else{
                 Log.e(TAG, "onLocationChanged:start of another Location" );
                 tvAzkarTitle.setText("أذكار مستحبة بين الصفا والمروة ");
                 currentAzkarIndex = 0;
                 currentArray = azkarArray;
                 Log.e(TAG, "onLocationChanged:end of another Location" );
             }

         }
     };

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_saiee);
         tvAzkarTitle = findViewById(R.id.tvAzkarTitle);
         tvAzkar = findViewById(R.id.tvSaieeAzkar);
         tvSaieCounter = findViewById(R.id.tvSaieCount);
         initArrays();
         locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
         getLocationService();
         Log.e(TAG, "onCreate" );

     }



     @Override
     protected void onStart() {
         super.onStart();
         Log.e(TAG, "onStart" );
         @SuppressLint("HandlerLeak") Handler mHandler = new Handler(){
             @Override
             public void handleMessage(@NonNull Message msg) {
                 if (tvAzkar != null)
                 {
                     tvSaieCounter.setText(String.valueOf(saieCounter));
                     tvAzkar.setText(currentArray.get(currentAzkarIndex));
                 }
             }
         };
         TimerTask task =  new TimerTask() {
             @Override
             public void run() {
                 mHandler.obtainMessage(1).sendToTarget();

                 if (currentAzkarIndex == currentArray.size()-1)
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
             ActivityCompat.requestPermissions(this,
                     new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                             Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST);

         }else {
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

     public boolean checkIsSafaLocation(Location location)
     {
          double x1 = 21.4238 , x2 = 21.4224 , x3 = 21.4231 ,x4 = 21.4244 ,
                  y1 = 39.8288 , y2 = 39.8280 , y3 = 39.8270 , y4 =39.8277  ;
         Gfg test = new Gfg();
         Gfg.Point[] polygon   = { new Gfg.Point(x1, y1 ), new Gfg.Point(x2, y2 ), new Gfg.Point(x3, y3 ), new Gfg.Point(x4, y4 ) };
         Gfg.Point p = new Gfg.Point(location.getLatitude(), location.getLongitude() );
         return test.checkInside(polygon, 4, p)==1;
     }
     public boolean checkIsMarwaLocation(Location location)
     {
         double x1 = 21.4255 , x2 = 21.4244 , x3 =21.4251 ,x4 =21.4265 ,
                 y1 = 39.8245 , y2 = 39.8237 , y3 = 39.8225 , y4 = 39.8235 ;

         Gfg test = new Gfg();
         Gfg.Point[] polygon   = { new Gfg.Point(x1, y1 ), new Gfg.Point(x2, y2 ), new Gfg.Point(x3, y3 ), new Gfg.Point(x4, y4 ) };
         Gfg.Point p = new Gfg.Point(location.getLatitude(), location.getLongitude() );
         return test.checkInside(polygon, 4, p)==1;
     }
     public void initArrays()
     {
         /// safa array
         safaArray.add("إِنَّ الصَّفَا وَالْمَرْوَةَ مِنْ شَعَائِرِ اللَّهِ فَمَنْ حَجَّ الْبَيْتَ أَوِ اعْتَمَرَ فَلا جُنَاحَ عَلَيْهِ أَنْ يَطَّوَّفَ بِهِمَا وَمَنْ تَطَوَّعَ خَيْرًا فَإِنَّ اللَّهَ شَاكِرٌ عَلِيم");
         safaArray.add("لله أكبر الله أكبر، الله أكبر و لله الحمد، الله أكبر على ما هدانا والحمد لله على ما أولانا، لا إله إلا الله وحده لا شريك له، له الملك وله الحمد يحيي ويميت، بيده الخير، وهو على كل شيء قدير");
         safaArray.add("لا إله إلا الله وحده، صدق وعده، ونصر عبده، وأعز جنده، و هزم الأحزاب و حده، لا إله إلا الله، ولا نعبد إلا إياه مخلصين له الدين ولو كره الكافرون، اللهم إنك قلت: ادعوني استجب لكم, وإنك لا تخلف الميعاد، وإني أسألك كما هديتني للإسلام أن لا تنزعه مني حتى تتوفاني وأنا مسلم");
         safaArray.add("اللهم إني أسألك موجبات رحمتك وعزائم مغفرتك والسلامة من كل إثم والغنيمة من كل بر والفوز بالجنة والنجاة من النار");
         /// marwa Array
         marwaArray.add("لله أكبر الله أكبر، الله أكبر و لله الحمد، الله أكبر على ما هدانا والحمد لله على ما أولانا، لا إله إلا الله وحده لا شريك له، له الملك وله الحمد يحيي ويميت، بيده الخير، وهو على كل شيء قدير");
         marwaArray.add("لا إله إلا الله وحده، صدق وعده، ونصر عبده، وأعز جنده، و هزم الأحزاب و حده، لا إله إلا الله، ولا نعبد إلا إياه مخلصين له الدين ولو كره الكافرون، اللهم إنك قلت: ادعوني استجب لكم, وإنك لا تخلف الميعاد، وإني أسألك كما هديتني للإسلام أن لا تنزعه مني حتى تتوفاني وأنا مسلم");
         marwaArray.add("اللهم إني أسألك موجبات رحمتك وعزائم مغفرتك والسلامة من كل إثم والغنيمة من كل بر والفوز بالجنة والنجاة من النار");
         /// azkar array
         azkarArray.add("اللهم إنا نسألك في هذا المقام المبارك أن تكتبنا من عتقائك من النار");
         azkarArray.add("اللهم أعتق رقابَنا ورقابَ آبائنا وأمّهاتنا وسائر قراباتنا من النار يا عزيز يا غفّار.");
         azkarArray.add("اللهم لا تفرّق جمعنا هذا إلا بذنبٍ مغفور، وعيبٍ مستور، وتجارةٍ لن تبور، يا عزيز يا غفور");
         azkarArray.add("اللهم اجعل اجتماعنا هذا اجتماعًا مرحومًا، واجعل تفرّقنا بعده تفرّقًا معصومًا، ولا تجعل معنا شقيًا ولا محرومًا.");
         azkarArray.add("اللهم لا تدع لنا ذنبًا إلا غفرته، ولا همًا إلا فرجته، ولا كربًا إلا نفَّسْته، ولا غما إلا أزلته، ولا دَيْنًا إلا قضيته، ولا عسيرًا إلا يسّرته، ولا عيبًا إلا سترته، ولا مبتلًا إلا عافيته، ولا مريضًا إلا شفيته، ولا ميتًا إلا رحمته");
         azkarArray.add("اللهم أن هذا البيت بيتك والحرم حرمك والأمن أمنك والعبد عبدك وأنا عبدك وابن عبدك وهذا مقام العائذ بك من النار وحرم لحومنا وبشرتنا على النار");

         currentArray = azkarArray;
     }
}