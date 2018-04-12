package com.example.josh.assignment_04;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class playingWithSpace extends AppCompatActivity implements SensorEventListener, LocationListener {
    private SensorManager senseMgr;
    private Sensor senseAccel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_with_space);

        senseMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senseAccel = senseMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senseMgr.registerListener(this, senseAccel, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        senseMgr.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        senseMgr.registerListener(this, senseAccel, SensorManager.SENSOR_DELAY_NORMAL);
    }

   public void startGPS_click (View view){
       if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
           ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101 );
       }else {
           LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

           LocationListener locationListener = new LocationListener() {
               @Override
               public void onLocationChanged(Location location) {
                    updateGPS(location);
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
           };
           locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
       }
   }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            updateAccel(x, y, z);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void updateAccel(float x, float y, float z){
        TextView _x = findViewById(R.id.acc_x);
        TextView _y = findViewById(R.id.acc_y);
        TextView _z = findViewById(R.id.acc_z);

        _x.setText("X: " + String.valueOf(x));
        _y.setText("Y: " + String.valueOf(y));
        _z.setText("Z: " + String.valueOf(z));
    }




    public void updateGPS(Location location){
        TextView _gpsLat = findViewById(R.id.gps_lat);
        TextView _gpsLon = findViewById(R.id.gps_long);
        _gpsLat.setText("Latitude: " + Double.toString(location.getLatitude()));
        _gpsLon.setText("Longitude: " + Double.toString(location.getLongitude()));

    }

    public void onLocationChanged(Location location) {

    }

    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    public void onProviderEnabled(String provider) {

    }

    public void onProviderDisabled(String provider) {

    }
}
