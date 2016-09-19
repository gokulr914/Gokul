package com.examples.singamnist;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import android.app.Activity;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

/**
 * Created by AdminCOOP on 8/10/2016.
 */
public class Gy extends MainActivity  implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor gyroscope;
    private ArrayList<String> list = new ArrayList<String>();
    private static final String FILE_HEADER = "x-value,y-value,z-value";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String COMMA_DELIMITER = ",";
    SensorEventListener Se;
    MainActivity mainActivity = new MainActivity();
    static final float ALPHA = 20f;
    protected float[] gravSensorVals;
    protected float[] gravSensorvals;
    final float[] gravity= new float[3];
    final float[] linear_acceleration= new float[3];
    final float[] gyro_linear_acceleration;
    final float alpha = 0.3f;
    MedianFilter mdfOutput = new MedianFilter();
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public Gy() {
        gyro_linear_acceleration = new float[3];
    }

    public void onCreate() {
        android.os.Handler handler = new android.os.Handler();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if (gyroscope != null) {
            mSensorManager.registerListener(Se, gyroscope, 20);
        }


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSensorManager.unregisterListener(Se);
            }
        }, 24000);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        gravSensorVals_1 = mdfOutput.addSamples(event.values);
        gravSensorvals_1 = lowPass(gravSensorVals_1,gravSensorvals_1);
        gravity_1 = lowPass_1(gravSensorvals_1,gravity_1);

        gravity_1[0] = alpha * gravity_1[0] + (1 - alpha) * gravSensorvals[0];
        gravity_1[1] = alpha * gravity_1[1] + (1 - alpha) * gravSensorvals[1];
        gravity_1[2] = alpha * gravity_1[2] + (1 - alpha) * gravSensorvals[2];

        linear_acceleration[0] = event.values[0] - gravity_1[0];
        linear_acceleration[1] = event.values[1] - gravity_1[1];
        linear_acceleration[2] = event.values[2] - gravity_1[2];

    }

    public float[] return_values(){

        gyro_linear_acceleration[0] = linear_acceleration[0];
        gyro_linear_acceleration[1] = linear_acceleration[1];
        gyro_linear_acceleration[2] = linear_acceleration[2];

        return gyro_linear_acceleration;
    }

    protected float[] lowPass(float[] input, float[] output) {
        if(timestampOld ==0){
            timestampOld = System.nanoTime();
        }
        timestamp = System.nanoTime();
        float dt = 1/(count++ / ((timestamp - timestampOld) / 1000000000.0f));

        alpha_3 = timeConstant / (timeConstant+dt);

        if(count > 5){

            output[0] = (alpha_3 *output[0]) + ((1.0f-alpha_3) * (input[0])) ;
            output[1] = (alpha_3 *output[1]) + ((1.0f-alpha_3) * (input[1])) ;
            output[2] = (alpha_3 *output[2]) + ((1.0f-alpha_3) * (input[2])) ;
        }
        return output;
    }
    protected float[] lowPass_1(float[] input, float[] output) {
        if(timestampOld ==0){
            timestampOld = System.nanoTime();
        }
        timestamp = System.nanoTime();
        float dt = 1/(count_1++ / ((timestamp - timestampOld) / 1000000000.0f));

        alpha_4 = timeConstant_1 / (timeConstant_1+dt);

        if(count_1 > 5){

            output[0] = (alpha_4 *output[0]) + ((1.0f-alpha_4) * (input[0])) ;
            output[1] = (alpha_4 *output[1]) + ((1.0f-alpha_4) * (input[1])) ;
            output[2] = (alpha_4 *output[2]) + ((1.0f-alpha_4) * (input[2])) ;
        }
        return output;
    }
}
