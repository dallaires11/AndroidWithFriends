package com.example.androsim;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class mini_game_test extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor accSensor;
    float last_x;
    float last_y;
    float last_z;
    long lastUpdate;
    private static final int SHAKE_THRESHOLD = 8000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_game_test);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accSensor,SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this, accSensor);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){

        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){

            long curTime = System.currentTimeMillis();

            if((curTime - lastUpdate)>100){
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    Log.d("sensor", "shake detected w/ speed: " + speed);
                    Toast.makeText(this, "Lighting Bolt!!!", Toast.LENGTH_SHORT).show();
                    //Intent startIntent = new Intent(getApplicationContext(), Combat.class).putExtra("damage", 1);
                    //startActivity(startIntent);
                    setResult(RESULT_OK, new Intent().putExtra("damage",10));
                    finish();
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    public void onBackPressed(){

    }
}
