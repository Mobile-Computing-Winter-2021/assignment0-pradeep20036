package com.example.assignment4_sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment4_sensors.database.SensorsDatabase;
import com.example.assignment4_sensors.database.entities.Accelerometer_data;
import com.example.assignment4_sensors.database.entities.Light_data;
import com.example.assignment4_sensors.database.entities.LinearAcceleration_data;
import com.example.assignment4_sensors.database.entities.Proximity_data;
import com.example.assignment4_sensors.database.entities.Temperature_data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;

//    list of sensors that we need to register....

    Switch accelerometer;
    Switch gps;
    Switch linearAcceleration;
    Switch light;
    Switch proximity;
    Switch temperature;
    TextView tv_result;
    TextView tv_motionDetector;
    SensorsDatabase sensorsDatabase;
    boolean motionDetectionUnabled=false;
    float previous= (float) 9.8;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

//     Initializing the ids of the switche  s
        accelerometer = findViewById(R.id.accelerometer_switch);
        gps = findViewById(R.id.gps_switch);
        linearAcceleration = findViewById(R.id.linearAccelerationSwitch);
        light = findViewById(R.id.light_switch);
        proximity = findViewById(R.id.proximitySwitch);
        temperature = findViewById(R.id.temperature_switch);
        tv_result = findViewById(R.id.tv_results);
        tv_motionDetector = findViewById(R.id.tv_motionDetection);

        tv_motionDetector.setVisibility(View.INVISIBLE);
//        handling databasse
        sensorsDatabase = SensorsDatabase.getDatabase(this);


        sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor arg0, int arg1) {

            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                Sensor sensor = event.sensor;
                if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                    System.out.println("Type Accelerometer");

//                    handling motion sensor to detect if motion is there or not....



                    float x=event.values[0];
                    float y=event.values[1];
                    float z=event.values[2];

                    if(motionDetectionUnabled){
                        float result= (float) Math.sqrt(x*x+y*y+z*z);
                        System.out.println("absolute value: " +Math.abs(result-previous));
                        if(Math.abs(result-9.8)>0.1) {

                            tv_motionDetector.setText("Moving");
                            tv_motionDetector.setVisibility(View.VISIBLE);
                        }
                        else{
                            tv_motionDetector.setText("Not Moving");
                            tv_motionDetector.setVisibility(View.VISIBLE);
                        }
                    }else{
                        tv_motionDetector.setVisibility(View.INVISIBLE);
                    }





                    long time = System.currentTimeMillis();
                    Accelerometer_data object = new Accelerometer_data(time, event.values[0], event.values[1], event.values[2]);

//                    getting the accelerometer data and putting it into the table
                    SensorsDatabase.databaseWriteExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            sensorsDatabase.sensorsDao().insertAccelerometer(object);
                        }
                    });


                } else if (sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
                    System.out.println("Type linear Acceleration");

                    long time = System.currentTimeMillis();
                    LinearAcceleration_data object = new LinearAcceleration_data(time, event.values[0], event.values[1], event.values[2]);

//                  getting the accelerometer data and putting it into the table
                    SensorsDatabase.databaseWriteExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            sensorsDatabase.sensorsDao().insertLinearAcceleration(object);
                        }
                    });

                } else if (sensor.getType() == Sensor.TYPE_LIGHT) {
                    System.out.println("Type Light");
                    long time = System.currentTimeMillis();
                    Light_data object = new Light_data(time, event.values[0]);
//                    getting the Light data and putting it into the table
                    SensorsDatabase.databaseWriteExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            sensorsDatabase.sensorsDao().insertLight(object);
                        }
                    });


                } else if (sensor.getType() == Sensor.TYPE_PROXIMITY) {
                    System.out.println("Type proximity");

                    long time = System.currentTimeMillis();
                    Proximity_data object = new Proximity_data(time, event.values[0]);
//                    getting the Light data and putting it into the table
                    SensorsDatabase.databaseWriteExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            sensorsDatabase.sensorsDao().insertProximity(object);
                        }
                    });


                } else if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                    System.out.println("sensor Temperature");

                    long time = System.currentTimeMillis();
                    Temperature_data object = new Temperature_data(time, event.values[0]);

//                    getting the accelerometer data and putting it into the table
                    SensorsDatabase.databaseWriteExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            sensorsDatabase.sensorsDao().insertTemperature(object);
                        }
                    });

                }
            }
        };
    }

    public void controlAccelerometer(View view) {
        if(accelerometer.isChecked()){
            Toast.makeText(this,"Accelerometer Sensor Running",Toast.LENGTH_SHORT).show();
            sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
        }else{
            Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.unregisterListener(sensorEventListener,sensor);
        }
    }

    public void controlLinearAcceleration(View view) {
        if(linearAcceleration.isChecked()){
            Toast.makeText(this,"LinearAcceleration Sensor Running",Toast.LENGTH_SHORT).show();
            sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_GAME);
        }
        else{
            Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
            sensorManager.unregisterListener(sensorEventListener,sensor);
        }

    }

    public void controlProximity(View view) {
        if(proximity.isChecked()){
            Toast.makeText(this,"Proximity Sensor Running",Toast.LENGTH_SHORT).show();
            sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_GAME);
        }else{
            Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            sensorManager.unregisterListener(sensorEventListener,sensor);
        }
    }

    public void controlLight(View view) {
        if(light.isChecked()){
            Toast.makeText(this,"Light Sensor Running",Toast.LENGTH_SHORT).show();
            sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_GAME);
        }else{
            Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            sensorManager.unregisterListener(sensorEventListener,sensor);

        }
    }

    public void controlTemperature(View view) {
        if(temperature.isChecked()){
            Toast.makeText(this,"Temperature Sensor Running",Toast.LENGTH_SHORT).show();
            Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            if(sensor==null){
                Toast.makeText(this,"This sensor is not Available",Toast.LENGTH_SHORT).show();
            }
            sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE ), SensorManager.SENSOR_DELAY_GAME);
        }else{
            Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            sensorManager.unregisterListener(sensorEventListener,sensor);
        }
    }

    public void controlGps(View view) {
        if(gps.isChecked()){
            sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_GAME);
        }else{
            Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
            sensorManager.unregisterListener(sensorEventListener,sensor);
        }
    }

    public void avgAccelerometer(View view) {

        SensorsDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if(sensorsDatabase!=null){
                    long time= System.currentTimeMillis();
                    List<Accelerometer_data> table=sensorsDatabase.sensorsDao().getOneHourData(time);

                    double avg_x=0;
                    double avg_y=0;
                    double avg_z=0;

                    for(Accelerometer_data row: table){
                        avg_x+=row.getX_dir();
                        avg_y+=row.getY_dir();
                        avg_z+=row.getZ_dir();
                    }

                    System.out.println(avg_x/table.size()+":  "+avg_y/table.size()+" :"+avg_z/table.size());

                    String result="X :"+String.format("%.3f ",avg_x/table.size())+"   Y: "+String.format("%.3f",avg_y/table.size())+"   Z: "+String.format("%.3f",avg_z/table.size());
                    tv_result.setText(result);
                }
                else
                    System.out.println("database is null");
            }
        });
    }


    public void avgLightData(View view) {

        SensorsDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if(sensorsDatabase!=null){
                    long time= System.currentTimeMillis();
                    List<Light_data> table=sensorsDatabase.sensorsDao().getOneHourDataLight(time);

                    double avg_light=0;
                    for(Light_data row: table){
                        avg_light+=row.getLight_lux();
                    }

                    String result="Avg Light Lux :"+String.format("%.3f",avg_light/table.size());
                    tv_result.setText(result);

                }
                else{
                    Toast.makeText(getApplicationContext(),"Database is null",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void checkMotion(View view) {
//    checking motion the phone using patterns in the accelerometer data
        if(motionDetectionUnabled==false){
            motionDetectionUnabled=true;
        }
        else{
            motionDetectionUnabled=false;
        }
    }

    public void avgTempData(View view) {
        SensorsDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if(sensorsDatabase!=null){
                    long time= System.currentTimeMillis();
                    List<Temperature_data> table=sensorsDatabase.sensorsDao().getOneHourTemperatureData(time);

                    double avg_temp=0;

                    for(Temperature_data row: table){
                        avg_temp+=row.temperature;
                    }

                    String result="Avg Temp :"+String.format("%.3f",avg_temp/table.size());
                    tv_result.setText(result);
                }
                else
                    System.out.println("database is null");
            }
        });
    }
}