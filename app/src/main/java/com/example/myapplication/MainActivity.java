package com.example.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor gyroscope;
    private Sensor lightSensor;
    private Sensor proximitySensor;
    private Sensor stepCounter;
    private Sensor stepDetector;
    private Sensor magneticField;
    private Sensor rotationVector;
    private Sensor pressureSensor;

    private TextView accelerometerValue;
    private TextView gyroscopeValue;
    private TextView lightSensorValue;
    private TextView proximitySensorValue;
    private TextView stepCounterValue;
    private TextView stepDetectorValue;
    private TextView magneticFieldValue;
    private TextView rotationVectorValue;
    private TextView pressureSensorValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accelerometerValue = findViewById(R.id.accelerometerValue);
        gyroscopeValue = findViewById(R.id.gyroscopeValue);
        lightSensorValue = findViewById(R.id.lightSensorValue);
        proximitySensorValue = findViewById(R.id.proximitySensorValue);
        stepCounterValue = findViewById(R.id.stepCounterValue);
        stepDetectorValue = findViewById(R.id.stepDetectorValue);
        magneticFieldValue = findViewById(R.id.magneticFieldValue);
        rotationVectorValue = findViewById(R.id.rotationVectorValue);
        pressureSensorValue = findViewById(R.id.pressureSensorValue);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        stepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        rotationVector = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        findViewById(R.id.accelerometerButton).setOnClickListener(v -> registerSensorListener(accelerometer, accelerometerValue));
        findViewById(R.id.gyroscopeButton).setOnClickListener(v -> registerSensorListener(gyroscope, gyroscopeValue));
        findViewById(R.id.lightSensorButton).setOnClickListener(v -> registerSensorListener(lightSensor, lightSensorValue));
        findViewById(R.id.proximitySensorButton).setOnClickListener(v -> registerSensorListener(proximitySensor, proximitySensorValue));
        findViewById(R.id.stepCounterButton).setOnClickListener(v -> registerSensorListener(stepCounter, stepCounterValue));
        findViewById(R.id.stepDetectorButton).setOnClickListener(v -> registerSensorListener(stepDetector, stepDetectorValue));
        findViewById(R.id.magneticFieldButton).setOnClickListener(v -> registerSensorListener(magneticField, magneticFieldValue));
        findViewById(R.id.rotationVectorButton).setOnClickListener(v -> registerSensorListener(rotationVector, rotationVectorValue));
        findViewById(R.id.pressureSensorButton).setOnClickListener(v -> registerSensorListener(pressureSensor, pressureSensorValue));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Bu metod sensörler için gerekli ancak boş bırakıldı
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                accelerometerValue.setText("Accelerometer Value: X = " + x + ", Y = " + y + ", Z = " + z);
                break;
            case Sensor.TYPE_GYROSCOPE:
                float xGyro = event.values[0];
                float yGyro = event.values[1];
                float zGyro = event.values[2];
                gyroscopeValue.setText("Gyroscope Value: X = " + xGyro + ", Y = " + yGyro + ", Z = " + zGyro);
                break;
            case Sensor.TYPE_LIGHT:
                float light = event.values[0];
                lightSensorValue.setText("Light Sensor Value: " + light);
                break;
            case Sensor.TYPE_PROXIMITY:
                float proximity = event.values[0];
                proximitySensorValue.setText("Proximity Sensor Value: " + proximity);
                break;
            case Sensor.TYPE_STEP_COUNTER:
                float stepCount = event.values[0];
                stepCounterValue.setText("Step Counter Value: " + stepCount);
                break;
            case Sensor.TYPE_STEP_DETECTOR:
                stepDetectorValue.setText("Step Detector Value: Step Detected");
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                float magX = event.values[0];
                float magY = event.values[1];
                float magZ = event.values[2];
                magneticFieldValue.setText("X = " + magX + ", Y = " + magY + ", Z = " + magZ);
                break;
            case Sensor.TYPE_ROTATION_VECTOR:
                float rotX = event.values[0];
                float rotY = event.values[1];
                float rotZ = event.values[2];
                rotationVectorValue.setText("X = " + rotX + ", Y = " + rotY + ", Z = " + rotZ);
                break;
            case Sensor.TYPE_PRESSURE:
                float pressure = event.values[0];
                pressureSensorValue.setText("Pressure Sensor Value: " + pressure);
                break;
        }
    }

    private void registerSensorListener(Sensor sensor, TextView textView) {
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textView.setText("Sensor not available");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}