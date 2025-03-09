package com.s22010578.eduapp;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LightSensorActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightSensorListener;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);

        rootView = findViewById(android.R.id.content);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (lightSensor == null) {
            // Ambient light sensor not available
            Log.e("LightSensorActivity", "Ambient light sensor not available");
        } else {
            // Ambient light sensor available
            lightSensorListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    float lux = event.values[0];
                    displayToastMessage(lux);
                    updateBackgroundColor(lux);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };
            sensorManager.registerListener(lightSensorListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (lightSensor != null) {
            sensorManager.unregisterListener(lightSensorListener);
        }
    }

    private void displayToastMessage(float lux) {
        if (lux < 50) {
            // Show a toast message
            Toast.makeText(this, "Low Light Detected. Consider adjusting your screen brightness for better visibility.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateBackgroundColor(float lux) {
        if (lux < 50) {
            rootView.setBackgroundColor(Color.DKGRAY); // Dark background for low light
        } else if (lux < 200) {
            rootView.setBackgroundColor(Color.LTGRAY); // Light gray background for moderate light
        } else {
            rootView.setBackgroundColor(Color.WHITE); // White background for bright light
        }
    }
}
