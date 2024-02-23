package com.example.guidetoumrah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class location_of_miqat extends AppCompatActivity implements OnMapReadyCallback {
        private GoogleMap mMap;
        private final LatLng wadi_Mehrim_Miqat = new LatLng(21.34600239778269, 40.32760574744848);
        private final LatLng miqat_Qarn_Al_Manazil = new LatLng(21.633485093551478, 40.42750985766271);
        private final LatLng miqat_Yalamlam = new LatLng(20.517149531112004, 39.87104818262503);
        private final LatLng dhul_Hulaifah_Miqat_Mosque = new LatLng(24.413815809855596, 39.54297294035603);
        private final LatLng mosque_Miqat_Zaat_Irq = new LatLng(21.929964, 40.425497);
        private final LatLng miqat_Al_Juhfah = new LatLng(22.70482805667712, 39.146730626838455);

    TextView tvMiqatTxt;
        public static final String TAG = "mapTest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_of_miqat);

        tvMiqatTxt = findViewById(R.id.tvMiqatTxt);
        getSupportActionBar().setTitle("أماكن الميقات");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);




    }



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(marker -> {
            Log.e(TAG, "setOnMarkerClickListener  : Map Clicked lat :"+marker.getPosition().latitude + " long :" + marker.getPosition().longitude );
            if (marker.getPosition().latitude == wadi_Mehrim_Miqat.latitude && marker.getPosition().longitude == wadi_Mehrim_Miqat.longitude ){
                tvMiqatTxt.setText("وادي محرم، أو ميقات وادي محرم، هو الميقات الذي يحرم منه الحجاج والمعتمرون المارون من الشرق بالهدا، الطائف. (وهم في الغالب من أهل نجد  ممن يرغب النزول عن طريق جبل كرا)، ويسيل من الجبال الواقعة بين جبال غفار غرباً وجبل الغمير شرقاً، ويقع جنوب وادي قرن في لحف جبل كرا.");
            }else if (marker.getPosition().latitude == miqat_Qarn_Al_Manazil.latitude && marker.getPosition().longitude == miqat_Qarn_Al_Manazil.longitude ){
                tvMiqatTxt.setText("ميقات قرن المنازل");
            }else if (marker.getPosition().latitude == miqat_Yalamlam.latitude && marker.getPosition().longitude == miqat_Yalamlam.longitude ){
                tvMiqatTxt.setText("ميقات يلمم");
            }else if (marker.getPosition().latitude == dhul_Hulaifah_Miqat_Mosque.latitude && marker.getPosition().longitude == dhul_Hulaifah_Miqat_Mosque.longitude ){
                tvMiqatTxt.setText("ميقات حليفة");
            }else if (marker.getPosition().latitude == mosque_Miqat_Zaat_Irq.latitude && marker.getPosition().longitude == mosque_Miqat_Zaat_Irq.longitude ){
                tvMiqatTxt.setText("ميقات جامع ذات عرق");
            }else if (marker.getPosition().latitude == miqat_Al_Juhfah.latitude && marker.getPosition().longitude == miqat_Al_Juhfah.longitude ){
                tvMiqatTxt.setText("ميقات الجحفة");
            }else {
                tvMiqatTxt.setText("");
            }
            return true;
        });

        List<Marker> markerList = new ArrayList<>();

        Marker mwadi_Mehrim_Miqat = mMap.addMarker(new MarkerOptions().position(wadi_Mehrim_Miqat).title("ميقات وادي محرم"));
        mwadi_Mehrim_Miqat.setTag(0);
        markerList.add(mwadi_Mehrim_Miqat);

        Marker mmiqat_Qarn_Al_Manazil = mMap.addMarker(new MarkerOptions().position(miqat_Qarn_Al_Manazil).title("ميقات قرن المنازل"));
        mmiqat_Qarn_Al_Manazil.setTag(1);
        markerList.add(mmiqat_Qarn_Al_Manazil);

        Marker mmiqat_Yalamlam = mMap.addMarker(new MarkerOptions().position(miqat_Yalamlam).title("ميقات يلملم"));
        mwadi_Mehrim_Miqat.setTag(2);
        markerList.add(mmiqat_Yalamlam);

        Marker mdhul_Hulaifah_Miqat_Mosque = mMap.addMarker(new MarkerOptions().position(dhul_Hulaifah_Miqat_Mosque).title("ميقات ذو الحليفه"));
        mdhul_Hulaifah_Miqat_Mosque.setTag(3);
        markerList.add(mdhul_Hulaifah_Miqat_Mosque);

        Marker mmosque_Miqat_Zaat_Irq = mMap.addMarker(new MarkerOptions().position(mosque_Miqat_Zaat_Irq).title("ميقات ذات عرق"));
        mmosque_Miqat_Zaat_Irq.setTag(4);
        markerList.add(mmosque_Miqat_Zaat_Irq);

        Marker mmiqat_Al_Juhfah = mMap.addMarker(new MarkerOptions().position(miqat_Al_Juhfah).title("ميقات الجحفة"));
        mmiqat_Al_Juhfah.setTag(5);
        markerList.add(mmiqat_Al_Juhfah);

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        for (Marker m : markerList){
            LatLng latLng = new LatLng(m.getPosition().latitude, m.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));


        }

        Log.e(TAG, "onMapReady: map is ready " );

    }




}