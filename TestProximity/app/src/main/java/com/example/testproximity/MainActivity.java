package com.example.testproximity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor sensor;
    Boolean isSensorPresent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisiasi SensorManager
        sensorManager = (SensorManager)this.getSystemService(SENSOR_SERVICE);

        // Cek sensor proximity
        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            isSensorPresent = true;
        } else {
            isSensorPresent = false;
            Toast.makeText(this, "Sensor tidak ditemukan", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(isSensorPresent){
            // SENSOR_DELAY_NORMAL digunakan untuk mendelay pembacaan data sensor
            // dengan kecepatan normal
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isSensorPresent){
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.values[0] < sensor.getMaximumRange()){
            // Jika terdapat obyek sangat dekat dengan sensor proximity
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        } else {
            // Jika tidak ada obyek yang dekat dengan sensor proximity
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
