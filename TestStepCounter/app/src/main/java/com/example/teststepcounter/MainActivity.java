package com.example.teststepcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor sensor;
    Boolean isSensorPresent = false;

    TextView stepCounter;
    SharedPreferences sharedPreferences;
    float lastStep;
    float step;

    public static final String KEY_LAST_STEP = "last_step";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stepCounter = findViewById(R.id.tv_counter);

        // Inisiasi SensorManager
        sensorManager = (SensorManager)this.getSystemService(SENSOR_SERVICE);

        // Cek sensor STEP
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isSensorPresent = true;
        } else {
            isSensorPresent = false;
            Toast.makeText(this, "Sensor tidak ditemukan", Toast.LENGTH_LONG).show();
        }

        sharedPreferences = this.getSharedPreferences("step_counter", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(isSensorPresent){
            // SENSOR_DELAY_NORMAL digunakan untuk mendelay pembacaan data sensor
            // dengan kecepatan normal
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        lastStep = sharedPreferences.getFloat(KEY_LAST_STEP, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isSensorPresent){
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(KEY_LAST_STEP, step);
        editor.commit();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
//        stepCounter.setText(String.valueOf(sensorEvent.values[0]));

        if (!sharedPreferences.contains(KEY_LAST_STEP)){
            lastStep = sensorEvent.values[0];

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat(KEY_LAST_STEP, lastStep);
            editor.commit();
        }

        step = sensorEvent.values[0];
        float counter = step - lastStep;

        stepCounter.setText(String.valueOf(counter));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
