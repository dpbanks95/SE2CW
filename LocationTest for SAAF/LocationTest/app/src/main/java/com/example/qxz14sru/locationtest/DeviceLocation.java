package com.example.qxz14sru.locationtest;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class DeviceLocation implements LocationListener {

    Context c;
    double latitude;
    double longitude;
    private static final int PERMISSION_ACCESS_FINE_LOCATION = 0;

    public DeviceLocation(Context c){
        this.c = c;
        checkPermission();
    }

    public void checkPermission(){
        //Get the location manager
        LocationManager locationmanager = (LocationManager) c.getSystemService(Context.LOCATION_SERVICE);

        Criteria cri = new Criteria();

        String provider = locationmanager.getBestProvider(cri, false);

        if (provider != null && !provider.equals("")) {
            android.location.Location location = locationmanager.getLastKnownLocation(provider);
            if (ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) c, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSION_ACCESS_FINE_LOCATION);

            }
            locationmanager.requestLocationUpdates(provider, 2000, 1, this);

            if (location != null)
            {
                onLocationChanged(location);
            } else {
                Toast.makeText(c, "location not found", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(c, "Provider is null", Toast.LENGTH_LONG).show();
        }

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Log.d("MyApp","LOCATION CHANGE");

        String stringLatitude = String.valueOf(latitude);
        TextView textview = (TextView) ((Activity)c).findViewById(R.id.fieldLatitude);
        textview.setText(stringLatitude);

        String stringLongitude = String.valueOf(longitude);
        textview = (TextView) ((Activity)c).findViewById(R.id.fieldLongitude);
        textview.setText(stringLongitude);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
