package com.example.qxz14sru.locationtest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    TextView textview;
    double latitude;
    double longitude;
    private static final int PERMISSION_ACCESS_FINE_LOCATION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the location manager
        LocationManager locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria cri = new Criteria();

        String provider = locationmanager.getBestProvider(cri, false);

        if (provider != null && !provider.equals("")) {
            Location location = locationmanager.getLastKnownLocation(provider);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSION_ACCESS_FINE_LOCATION);

            }
            locationmanager.requestLocationUpdates(provider, 2000, 1, this);

            if (location != null)
            {
                onLocationChanged(location);
            } else {
                Toast.makeText(getApplicationContext(), "location not found", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Provider is null", Toast.LENGTH_LONG).show();
        }

        String stringLatitude = String.valueOf(latitude);
        textview = (TextView) findViewById(R.id.fieldLatitude);
        textview.setText(stringLatitude);

        String stringLongitude = String.valueOf(longitude);
        textview = (TextView) findViewById(R.id.fieldLongitude);
        textview.setText(stringLongitude);

    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override

    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        String stringLatitude = String.valueOf(latitude);
        textview = (TextView) findViewById(R.id.fieldLatitude);
        textview.setText(stringLatitude);

        longitude = location.getLongitude();
        String stringLongitude = String.valueOf(longitude);
        textview = (TextView) findViewById(R.id.fieldLongitude);
        textview.setText(stringLongitude);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }
}