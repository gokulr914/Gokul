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
import android.support.v7.widget.LinearLayoutCompat;
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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import com.csvreader.CsvWriter;
import java.lang.Runnable;


public class MainActivity extends Activity{

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

    //Computation values and declaration
    private static ArrayList<float[]> Time_Gravity_Acc = new ArrayList<float[]>();
    private static ArrayList<float[]> Time_Linear_Acc = new ArrayList<float[]>();
    private static ArrayList<float[]> Time_Gyro_Acc = new ArrayList<float[]>();
    private static ArrayList<float[]> Freq_Linear_Acc = new ArrayList<float[]>();
    private static ArrayList<float[]> Freq_Gyro_Acc = new ArrayList<float[]>();
    private static List<float[]> results = new ArrayList<float[]>();

    private static Time_Gravity_Acc_Compute Compute_Time_Gravity_Acc;
    private static Time_Linear_Acc_Compute Compute_Time_Linear_Acc;
    private static Time_Gyro_Acc_Compute Compute_Time_Gyro_Acc;
    private static Freq_Linear_Acc_Compute Compute_Freq_Linear_Acc;
    private static Freq_Gyro_Acc_Compute Compute_Freq_Gyro_Acc;

    //filter values and declaration
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

    android.os.Handler handler = new android.os.Handler();
    Button button_logger_mode;
    private static Button saveButton;
    protected boolean dataReady= false;
    MedianFilter mdfOutput = new MedianFilter();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialise Sensor Manager and Sensor
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Creating Instances of the three classes
        Compute_Time_Gravity_Acc = new Time_Gravity_Acc_Compute();
        Compute_Time_Linear_Acc = new Time_Linear_Acc_Compute();
        // Compute_Time_Gyro_Acc = new Time_Gyro_Acc_Compute();

        //Register Sensor Event Listener
        if (accelerometer != null) {
            mSensorManager.registerListener(Se, accelerometer, 20, handler);
        }

        //Unregister Sensor Event Listener
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

        //Initialise Text View Variables
        X = (TextView) findViewById(R.id.X);
        Y = (TextView) findViewById(R.id.Y);
        Z = (TextView) findViewById(R.id.Z);

        //Initialise Save Results Button
        saveButton = (Button) findViewById(R.id.SaveButton);
        saveButton.setClickable(false);

    }

    SensorEventListener Se = new SensorEventListener() {

        public void onSensorChanged(SensorEvent event) {

            //Real-time sensor values after going through median filter
            gravSensorVals = mdfOutput.addSamples(event.values);
            //Real-time Sensor Values after low-pass Butterworth filter
            gravSensorvals = lowPass(gravSensorVals, gravSensorvals);
            //Real-time Sensor Values after another low-pass filter
            gravity = lowPass_1(gravSensorvals, gravity);



            //Real-time Linear Acceleration values after removing the effects gravity
            linear_acceleration[0] = event.values[0] - gravity[0];
            linear_acceleration[1] = event.values[1] - gravity[1];
            linear_acceleration[2] = event.values[2] - gravity[2];

            // store real-time gravity_acc in time domain
            Time_Gravity_Acc.add(gravity);
            Create_Window_Compute_Gravity_Acc_Time();

            //store real-time Linear Acc in time domain
            Time_Linear_Acc.add(linear_acceleration);
            Create_Window_Compute_Linear_Acc_Time();

            //display acceleration values on screen
            X.setText("X:" + gravity[0]);
            Y.setText("Y:" + gravity[1]);
            Z.setText("Z:" + gravity[2]);
        }


        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

    public void updateUI(String x){}



    //Pass through low-pass filter of butterWorth filter
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

    //Pass through another low-pass filter
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


    public  void Create_Window_Compute_Gravity_Acc_Time() {

        if (Time_Gravity_Acc.size() >= windowSize) {   //Create Window and process only if ArrayList size is greater than WindowSize

            final ArrayList<float[]> Window_To_Be_Processed = new ArrayList<float[]>();  //Create a temporary array to store the values

            for (int counter = 0; counter < windowSize; counter++) {

                float[] temp = new float[3];
                temp[0] = Time_Gravity_Acc.get(counter)[0];  //Creating a time window of X,Y and Z values
                temp[1] = Time_Gravity_Acc.get(counter)[1];
                temp[2] = Time_Gravity_Acc.get(counter)[2];

                Window_To_Be_Processed.add(temp);
            }

            Thread computationThread = new Thread() {
                @Override
                public void run() {
                    Compute_Time_Gravity_Acc.processData(Window_To_Be_Processed);    //Sending the Window data for Computation
                }
            };

            for (int counter = (int) windowSize / 2; counter > 0; counter--) {
                Time_Gravity_Acc.remove(0);      //Removing the first half of the window so that overlap occurs
            }

            computationThread.start();

            if (!saveButton.isClickable()) saveButton.setClickable(true);
        }
    }

    public static void Create_Window_Compute_Linear_Acc_Time() {

        if (Time_Linear_Acc.size() >= windowSize) {  //Create Window and process only if ArrayList size is greater than WindowSize

            final ArrayList<float[]> Window_To_Be_Processed_1 = new ArrayList<float[]>();

            for (int counter = 0; counter < windowSize; counter++) {
                float[] temp_1 = new float[3];   //Create a temporary array to store the values

                temp_1[0] = Time_Linear_Acc.get(counter)[0];  //Creating a time window of X,Y and Z values
                temp_1[1] = Time_Linear_Acc.get(counter)[1];
                temp_1[2] = Time_Linear_Acc.get(counter)[2];

                Window_To_Be_Processed_1.add(temp_1);

            }
            Thread computationThread = new Thread() {
                @Override
                public void run() {
                    Compute_Time_Linear_Acc.processData(Window_To_Be_Processed_1);   //Sending the Window data for Computation
                }
            };

            for (int counter = (int) windowSize / 2; counter > 0; counter--) {
                Time_Linear_Acc.remove(0);   //Removing the first half of the window so that overlap occurs
            }
            computationThread.start();

            if (!saveButton.isClickable()) saveButton.setClickable(true);
        }
    }


    public void SaveResults(View view) {

        Thread fileThread = new Thread() {
            @Override
            public void run() {
                Calendar c = Calendar.getInstance();
                String filename = "AccelerationExplorer-" + c.get(Calendar.YEAR) + "-"
                        + (c.get(Calendar.MONTH) + 1) + "-"
                        + c.get(Calendar.DAY_OF_MONTH) + c.get(Calendar.HOUR) + c.get(Calendar.MINUTE) + c.get(Calendar.SECOND) + "-000"
                        + ".csv";

                File dir = new File(Environment.getExternalStorageDirectory()
                        + File.separator + "AccelerationExplorer" + File.separator
                        + "Logs");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, filename);

                try {
                    //Get the Results for the  processed signals
                    List<float[]> Time_Gravity_Acc_Results = Compute_Time_Gravity_Acc.getResults();
                    List<float[]> Time_Linear_Acc_Results = Compute_Time_Linear_Acc.getResults();


                    //Create an Iterator instance to iterate through each of the results
                    Iterator<float[]> Time_Gravity_Acc_results_Iterator = Time_Gravity_Acc_Results.iterator();
                    Iterator<float[]> Time_Linear_Acc_results_Iterator = Time_Linear_Acc_Results.iterator();


                    while (Time_Gravity_Acc_results_Iterator.hasNext() && Time_Linear_Acc_results_Iterator.hasNext())
                    {

                        //Get the Computed X,Y,Z results for the Gravity Acceleration in Time domain
                        float[] Time_Gravity_Acc_results_X = (float[]) Time_Gravity_Acc_results_Iterator.next();
                        float[] Time_Gravity_Acc_results_Y = (float[]) Time_Gravity_Acc_results_Iterator.next();
                        float[] Time_Gravity_Acc_results_Z = (float[]) Time_Gravity_Acc_results_Iterator.next();

                        //Get the Computed X,Y,Z results for the Linear Acceleration results in Time domain
                        float[] Time_Linear_Acc_results_X = (float[]) Time_Linear_Acc_results_Iterator.next();
                        float[] Time_Linear_Acc_results_Y = (float[]) Time_Linear_Acc_results_Iterator.next();
                        float[] Time_Linear_Acc_results_Z = (float[]) Time_Linear_Acc_results_Iterator.next();

                        CsvWriter csvOutput = new CsvWriter(new FileWriter(file, true), ',');

                        //Write the files to a CSV file for each of the signals in this order and combine them
                        //X-Mean, Y-Mean, Z-Mean, X-Max,Y-Max,Z-Max,X-Standard Deviation, Y-Standard Deviation, Z-Standard Deviation
                        //X-Min, Y-Min, Z-Min, X-Variance, Y- Variance, Z-Variance

                        for(int i=0;i<5;i++){
                            csvOutput.write(String.valueOf(Time_Gravity_Acc_results_X[i]));
                            csvOutput.write(String.valueOf(Time_Gravity_Acc_results_Y[i]));
                            csvOutput.write(String.valueOf(Time_Gravity_Acc_results_Z[i]));
                        }

                        for(int i=0;i<5;i++){
                            csvOutput.write(String.valueOf(Time_Linear_Acc_results_X[i]));
                            csvOutput.write(String.valueOf(Time_Linear_Acc_results_Y[i]));
                            csvOutput.write(String.valueOf(Time_Linear_Acc_results_Z[i]));
                        }

                        csvOutput.endRecord();
                        csvOutput.close();
                    }
                } catch (FileNotFoundException e) {} catch (IOException e) {
                } finally {
                    new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(final String path,
                                                    final Uri uri) {
                        }
                    };
                }
            }

        };
        fileThread.start();
    }
}


