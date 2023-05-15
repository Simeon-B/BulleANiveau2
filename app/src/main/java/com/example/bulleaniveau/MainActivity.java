package com.example.bulleaniveau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.OrientationEventListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    OrientationEventListener orientationEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orientationEventListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int orientation) {
                updateOrientation(orientation);
            }
        };

        if (orientationEventListener.canDetectOrientation()) {
            orientationEventListener.enable();
        }
    }

    private void updateOrientation(int orientation) {
        int x = 0;
        int y = 0;

        if (orientation >= 45 && orientation < 135) {
            // Orientation : Portrait (à plat)
            x = 0;
            y = 1;
        } else if (orientation >= 135 && orientation < 225) {
            // Orientation : Paysage inversé (écran large, à plat)
            x = 1;
            y = 0;
        } else if (orientation >= 225 && orientation < 315) {
            // Orientation : Portrait inversé (à plat)
            x = 0;
            y = -1;
        } else {
            // Orientation : Paysage (écran large, debout)
            x = -1;
            y = 0;
        }

        String orientationText = "x" + x + ":y" + y;
        TextView orientationTextView = findViewById(R.id.orientationTextView);
        orientationTextView.setText(orientationText);
    }


}