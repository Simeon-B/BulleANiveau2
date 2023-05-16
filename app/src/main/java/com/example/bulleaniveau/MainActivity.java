package com.example.bulleaniveau;

// importation des outils
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Mon application
 * qui test les capteurs de GRAVITY
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // initialisation des variable
    private SensorManager sensorManager;
    private Sensor gravitySensor;
    private TextView orientationTextView;
    private View lineViewx;
    private View lineViewy;
    private View lineViewz;

    /**
     * début de l'application s'éxecute uniquement lors du démarage de l'application
     * org.codehaus.groovy.control.MultipleCompilationErrorsException: startup failed:
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // récupère le textView
        orientationTextView = findViewById(R.id.orientationTextView);
        lineViewx = findViewById(R.id.lineViewx);
        lineViewy = findViewById(R.id.lineViewy);
        lineViewz = findViewById(R.id.lineViewz);


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

    /**
     * Regarde si les capteurs change et change en même temps les affichages
     *
     * @param event the {@link android.hardware.SensorEvent SensorEvent}.
     */
    @Override
    public void onSensorChanged(SensorEvent event) {

        // Mise à jour des valeurs de x, y et z
        int xValue = (int) (event.values[0] * 10);
        int yValue = (int) (event.values[1] * -10);
        int zValue = (int) (event.values[2] * 10);

        // Mise à jour de l'affichage dans le TextView
        orientationTextView.setText("x : " + xValue + "%\ny : " + yValue + "%\nz : " + zValue + "%");

        // Mise à jour de l'affichage des éguilles des axes
        lineViewx.setRotation(xValue);
        lineViewy.setRotation(yValue);
        ViewGroup.LayoutParams layoutParamsx = lineViewz.getLayoutParams();
        ViewGroup.LayoutParams layoutParamsz = lineViewz.getLayoutParams();
        layoutParamsx.height = (int) ((xValue < 0 ? xValue * -1 : xValue) * 3.5);
        layoutParamsz.height = (int) ((zValue < 0 ? zValue * -1 : zValue) * 3.5);
        lineViewz.setLayoutParams(layoutParamsz);
        lineViewx.setLayoutParams(layoutParamsx);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Ne fait rien pour le moment
    }
}