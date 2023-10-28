package com.example.shake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private FrameLayout cardContainer;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardContainer = findViewById(R.id.cardContainer);

        ProgressBar progressBar = findViewById(R.id.progressBar);

        progressBar.setProgress(50);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                return true;
            }

        });


        // Initialize the sensor manager and accelerometer sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }






    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }



  // If user want move card in left, right, up and down

   @Override
    public void onSensorChanged(SensorEvent event) {
        // Get accelerometer data
        float x = event.values[0];
        float y = event.values[1];

        // Update card position based on sensor data
        cardContainer.setTranslationX(x * 4);
        cardContainer.setTranslationY(y * 4);
    }


    // If user want card move only in left and right direction

   /* public void onSensorChanged(SensorEvent event) {
        // Get accelerometer data
        float x = event.values[0];

        // Restrict movement to left and right
        if (x > 1.0f) {
            // Move the card to the right
            cardContainer.setTranslationX(50); // Adjust the value as needed
        } else if (x < -1.0f) {
            // Move the card to the left
            cardContainer.setTranslationX(-50); // Adjust the value as needed
        } else {
            // Return the card to the center when the phone is level
            cardContainer.setTranslationX(0);
        }
    }*/


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }
}