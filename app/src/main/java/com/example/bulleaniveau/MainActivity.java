package com.example.bulleaniveau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // initialisation des variable
    private SensorManager sensorManager;
    private Sensor gravitySensor;
    private TextView orientationTextView;
    private View lineView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // récupère le textView
        orientationTextView = findViewById(R.id.orientationTextView);

        // récupère le sensor en mode gravity
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
    }

    /**
     * regarde les changements du capteur
     */
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // Mise à jour des valeurs de x et y
        int xValue = (int) (event.values[0] * 10);
        int yValue = (int) (event.values[1] * 10);
        int zValue = (int) (event.values[2] * 10);

        // Mise à jour de l'affichage dans le TextView
        orientationTextView.setText("x : " + xValue + "%\ny : " + yValue + "%\nz : " + zValue + "%");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Ne fait rien pour le moment
    }
}