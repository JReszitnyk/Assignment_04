package com.example.josh.assignment_04;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listSensors_click(View view){
        //listing sensors
        SensorManager mgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = mgr.getSensorList(Sensor.TYPE_ALL);
        EditText multi = findViewById(R.id.multiText);
        for (Sensor sensor : sensors) {
            multi.append(sensor.getName() + "\n");
        }

        //number of sensors
        EditText single = findViewById(R.id.singleText);
        single.setText("Number: " + sensors.size());
    }

    public void playingWithSpace_click(View view){
        //starting new activity
        Intent intent = new Intent(this, playingWithSpace.class);
        startActivity(intent);
    }
}
