package com.examples.singamnist;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.app.Service;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Environment;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import com.opencsv.CSVWriter;
import java.lang.Thread;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.w3c.dom.Text;

public class MainActivity extends Activity implements SensorEventListener {

    private  final String TAG = "MainActivity";
    private  final int PIXEL_WIDTH = 28;
    private TextView mResultText;
    private float mLastX;
    private float mLastY;
    LinearLayout activity_main;
    private SensorManager mSensorManager;
    private Sensor accelerometer;
    private DrawModel mModel;
    private DrawView mDrawView;
    public TextView X,Y,Z;
    float ALPHA;
    final static int windowSize=128;
    protected float[] gravSensorVals= new float[3];
    protected float[] gravSensorvals= new float[]{0,0,0};
    protected float[] gravSensorVals_1= new float[3];
    protected float[] gravSensorvals_1= new float[]{0,0,0};
    private static ArrayList<float[]> AccHistory = new ArrayList<float[]>();
    private static ArrayList<float[]> AccHistory_1 = new ArrayList<float[]>();
    private static ArrayList<float[]> AccHistory_2 = new ArrayList<float[]>();
    private static ArrayList<float[]> AccHistory_3 = new ArrayList<float[]>();
    private static ArrayList<float[]> AccHistory_4 = new ArrayList<float[]>();
    private static List<float[]> results = new ArrayList<float[]>();
    private static ComputationHandler compHandler;
    private static CompHandler_1 compHandler_1;
    private static CompHandler_2 compHandler_2;
    private static CompHandler_3 compHandler_3;
    private static CompHandler_4 compHandler_4;
    public File resultsFile;
    protected float[] gravity= new float[]{0,0,0};
    protected float[] gravity_1= new float[]{0,0,0};
    final float[] linear_acceleration= new float[3];
    float[] gyro_linear_acceleration = new float[3];
    float[] final_results = new float[3];
    double[] la = new double[3];
    float[] f = new float[3];
    float[] f_1 = new float[3];
    final float alpha = 0.3f;
    static final float timeConstant = 0.07854f;
    static final float timeConstant_1 = 5.235f;
    float alpha_1 = 0.0f;
    float alpha_2 = 0.0f;
    float alpha_3 = 0.0f;
    float alpha_4 = 0.0f;
    float timestamp = System.nanoTime();
    float timestampOld = 0;
    int count=0;
    int count_1=0;
    private boolean logData = true;
    private long logTime = 0;
    private DecimalFormat df;
    private String log;
    android.os.Handler handler = new android.os.Handler();
    Button button_logger_mode;
    SensorEventListener Se;
    private static Button saveButton;
    protected boolean dataReady= false;
    MedianFilter mdfOutput = new MedianFilter();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        compHandler = new ComputationHandler();
        compHandler_1 = new CompHandler_1();
        compHandler_2 = new CompHandler_2();
        compHandler_3 = new CompHandler_3();
        compHandler_4 = new CompHandler_4();

        initButtonLogger();

        if (accelerometer != null) {
            mSensorManager.registerListener(this, accelerometer, 20);
            dataReady = true;
        }

        Runnable runable = new Runnable()
        {
            @Override
            public void run()
            {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSensorManager.unregisterListener(Se);
                    }
                }, 24000);
            }
        };

        X = (TextView) findViewById(R.id.X);
        Y = (TextView) findViewById(R.id.Y);
        Z = (TextView) findViewById(R.id.Z);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        gravSensorVals = mdfOutput.addSamples(event.values);
        gravSensorvals = lowPass(gravSensorVals,gravSensorvals);
        gravity = lowPass_1(gravSensorvals,gravity);

        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];

        Gy gy = new Gy();
        gyro_linear_acceleration = gy.return_values();

        la= convertFloatsToDoubles(linear_acceleration);
        FastFourierTransformer fft= new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] nw= fft.transform(la,TransformType.FORWARD);

        la= convertFloatsToDoubles(gyro_linear_acceleration);
        FastFourierTransformer fft_1= new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] nw_1= fft_1.transform(la,TransformType.FORWARD);

        f[0]= (float)nw[0].getReal();
        f[1]= (float)nw[1].getReal();
        f[2]= (float)nw[2].getReal();

        f_1[0]= (float)nw_1[0].getReal();
        f_1[1]= (float)nw_1[1].getReal();
        f_1[2]= (float)nw_1[2].getReal();

        AccHistory.add(gravity);    //time_gravity_acc
        Compute();
        AccHistory_1.add(linear_acceleration);  //time_body_acc
        Compute_1();
        AccHistory_2.add(gyro_linear_acceleration); //time_body_gyro
        Compute_2();
        AccHistory_3.add(f); //frequency_body_acc
        Compute_3();
        AccHistory_4.add(f_1); //frequency_gyro_acc
        Compute_4();


        X.setText("X:" + gravity[0]);
        Y.setText("Y:" + gravity[1]);
        Z.setText("Z:" + gravity[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    public void updateUI(String x){}

    protected float[] lowPass(float[] input, float[] output) {
        if(timestampOld ==0){
            timestampOld = System.nanoTime();
        }
        timestamp = System.nanoTime();
        float dt = 1/(count++ / ((timestamp - timestampOld) / 1000000000.0f));

        alpha_1 = timeConstant / (timeConstant+dt);

     if(count > 5){

        output[0] = (alpha_1 *output[0]) + ((1.0f-alpha_1) * (input[0])) ;
        output[1] = (alpha_1 *output[1]) + ((1.0f-alpha_1) * (input[1])) ;
        output[2] = (alpha_1 *output[2]) + ((1.0f-alpha_1) * (input[2])) ;
        }
        return output;
    }
    protected float[] lowPass_1(float[] input, float[] output) {
        if(timestampOld ==0){
            timestampOld = System.nanoTime();
        }
        timestamp = System.nanoTime();
        float dt = 1/(count_1++ / ((timestamp - timestampOld) / 1000000000.0f));

        alpha_2 = timeConstant_1 / (timeConstant_1+dt);

        if(count_1 > 5){

            output[0] = (alpha_2 *output[0]) + ((1.0f-alpha_2) * (input[0])) ;
            output[1] = (alpha_2 *output[1]) + ((1.0f-alpha_2) * (input[1])) ;
            output[2] = (alpha_2 *output[2]) + ((1.0f-alpha_2) * (input[2])) ;
        }
        return output;
    }

    public static void Compute() {

        if (AccHistory.size() >= windowSize) {

            final ArrayList<float[]> dataToProcess = new ArrayList<float[]>();

                for (int counter = 0; counter < windowSize; counter++) {
                    float[] temp = new float[3];

                    temp[0] = AccHistory.get(counter)[0];
                    temp[1] = AccHistory.get(counter)[1];
                    temp[2] = AccHistory.get(counter)[2];

                    dataToProcess.add(temp);

                }
                Thread computationThread = new Thread() {
                    @Override
                    public void run() {
                        //Min,max and standard deviation of the current window are calculated and stored
                        compHandler.processData(dataToProcess);
                    }
                };

                for (int counter = (int) windowSize / 2; counter > 0; counter--) {
                    AccHistory.remove(0);
                }
                computationThread.start();

                if (!saveButton.isClickable()) saveButton.setClickable(true);
            }
    }

    public static void Compute_1() {

        if (AccHistory_1.size() >= windowSize) {

            final ArrayList<float[]> dataToProcess_1 = new ArrayList<float[]>();

            for (int counter = 0; counter < windowSize; counter++) {
                float[] temp_1 = new float[3];

                temp_1[0] = AccHistory_1.get(counter)[0];
                temp_1[1] = AccHistory_1.get(counter)[1];
                temp_1[2] = AccHistory_1.get(counter)[2];

                dataToProcess_1.add(temp_1);

            }
            Thread computationThread = new Thread() {
                @Override
                public void run() {
                    //Min,max and standard deviation of the current window are calculated and stored
                    compHandler_1.processData(dataToProcess_1);
                }
            };

            for (int counter = (int) windowSize / 2; counter > 0; counter--) {
                AccHistory_1.remove(0);
            }
            computationThread.start();

            if (!saveButton.isClickable()) saveButton.setClickable(true);
        }
    }

    public static void Compute_2() {

        if (AccHistory_2.size() >= windowSize) {

            final ArrayList<float[]> dataToProcess_2 = new ArrayList<float[]>();

            for (int counter = 0; counter < windowSize; counter++) {
                float[] temp_2 = new float[3];

                temp_2[0] = AccHistory_2.get(counter)[0];
                temp_2[1] = AccHistory_2.get(counter)[1];
                temp_2[2] = AccHistory_2.get(counter)[2];

                dataToProcess_2.add(temp_2);

            }
            Thread computationThread = new Thread() {
                @Override
                public void run() {
                    //Min,max and standard deviation of the current window are calculated and stored
                    compHandler_2.processData(dataToProcess_2);
                }
            };

            for (int counter = (int) windowSize / 2; counter > 0; counter--) {
                AccHistory_2.remove(0);
            }
            computationThread.start();

            if (!saveButton.isClickable()) saveButton.setClickable(true);
        }
    }

    public static void Compute_3() {

        if (AccHistory_3.size() >= windowSize) {

            final ArrayList<float[]> dataToProcess_3 = new ArrayList<float[]>();

            for (int counter = 0; counter < windowSize; counter++) {
                float[] temp_3 = new float[3];

                temp_3[0] = AccHistory_3.get(counter)[0];
                temp_3[1] = AccHistory_3.get(counter)[1];
                temp_3[2] = AccHistory_3.get(counter)[2];

                dataToProcess_3.add(temp_3);

            }
            Thread computationThread = new Thread() {
                @Override
                public void run() {
                    //Min,max and standard deviation of the current window are calculated and stored
                    compHandler_3.processData(dataToProcess_3);
                }
            };

            for (int counter = (int) windowSize / 2; counter > 0; counter--) {
                AccHistory_3.remove(0);
            }
            computationThread.start();

            if (!saveButton.isClickable()) saveButton.setClickable(true);
        }
    }


    public static void Compute_4() {

        if (AccHistory_4.size() >= windowSize) {

            final ArrayList<float[]> dataToProcess_4 = new ArrayList<float[]>();

            for (int counter = 0; counter < windowSize; counter++) {
                float[] temp_4 = new float[3];

                temp_4[0] = AccHistory_4.get(counter)[0];
                temp_4[1] = AccHistory_4.get(counter)[1];
                temp_4[2] = AccHistory_4.get(counter)[2];

                dataToProcess_4.add(temp_4);

            }
            Thread computationThread = new Thread() {
                @Override
                public void run() {
                    //Min,max and standard deviation of the current window are calculated and stored
                    compHandler_4.processData(dataToProcess_4);
                }
            };

            for (int counter = (int) windowSize / 2; counter > 0; counter--) {
                AccHistory_4.remove(0);
            }
            computationThread.start();

            if (!saveButton.isClickable()) saveButton.setClickable(true);
        }
    }

    public static double[] convertFloatsToDoubles(float[] input)
    {
        if (input == null)
        {
            return null; // Or throw an exception - your choice
        }
        double[] output = new double[input.length];
        for (int i = 0; i < input.length; i++)
        {
            output[i] = input[i];
        }
        return output;
    }

    private void initButtonLogger() {
        Button button = (Button) this.findViewById(R.id.button_logger_mode);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Logger.class);
                startActivity(intent);
            }
        });
}
}




