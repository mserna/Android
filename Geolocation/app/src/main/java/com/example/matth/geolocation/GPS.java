package com.example.matth.geolocation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;
import android.util.Log;

public class GPS extends Activity implements LocationListener {
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_layout);
        results = (TextView) findViewById(R.id.textView);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, GPS.this);

    }
    @Override
    public void onLocationChanged(Location location) {
        txtLat = (TextView) findViewById(R.id.textView);
        double lati = location.getLatitude();
        double longi = location.getLongitude();
        txtLat.setText("Latitude:"  + lati + ", Longitude:" + longi );

        String latiStr = Double.toString(lati);
        String longiStr = Double.toString(longi);
        NetworkAsyncTask async = new NetworkAsyncTask();
        new NetworkAsyncTask().executeOnExecutor(ExecutorlatiStr, longiStr);

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }
}
