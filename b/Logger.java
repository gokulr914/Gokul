package com.examples.singamnist;

import com.csvreader.CsvWriter;
import com.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Logger extends MainActivity implements Runnable
{
    float[] acceleration = new float[3];

    public Logger(){}

    private final static String tag = Logger.class.getSimpleName();
    private boolean logData = false;
    private int generation = 0;
    LinearLayout layout_logger;
    private long logTime = 0;
    private DecimalFormat df;
    private String plotAccelXAxisTitle = "X-Axis";
    private String plotAccelYAxisTitle = "Y-Axis";
    private String plotAccelZAxisTitle = "Z-Axis";
    private String log;
    private Thread thread;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.getDefault());
        df = (DecimalFormat) nf;
        initStartButton();

        Runnable runable = new Runnable() {
            @Override
            public void run()
            {
                handler.postDelayed(this, 10);
            }
        };
    }

    @Override
    public void run(){}
    private void initStartButton()
    {
        final Button button = (Button) findViewById(R.id.button_start);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (!logData)
                {
                    button.setText("Stop Log");
                    logData();
                    thread = new Thread(Logger.this);
                    thread.start();
                }
                else
                {
                    button.setText("Start Log");
                    stopDataLog();
                }
            }
        });
    }

    private void logData()
    {
            CharSequence text = "Logging Data";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
            //  logTime = System.currentTimeMillis();
            //float timestamp = (System.currentTimeMillis() - logTime) / 1000.0f;
            //   log += df.format(timestamp) + ",";

            log += df.format(gravity[0]) + ",";
            log += df.format(gravity[1]) + ",";
            log += df.format(gravity[2]) + ",";
            log += System.getProperty("line.separator");

            logData=true;
    }

    private void stopDataLog()
    {
        if (logData)
        {
            writeLogToFile();
        }
        if (logData && thread != null)
        {
            logData = false;
            thread.interrupt();
            thread = null;
        }
    }

    private void writeLogToFile()
    {
        Calendar c = Calendar.getInstance();
        String filename = "AccelerationExplorer-" + c.get(Calendar.YEAR) + "-"
                + (c.get(Calendar.MONTH) + 1) + "-"
                + c.get(Calendar.DAY_OF_MONTH) + c.get(Calendar.HOUR) + c.get(Calendar.MINUTE)+ c.get(Calendar.SECOND)+"-1"
                + ".csv";

        File dir = new File(Environment.getExternalStorageDirectory()
                + File.separator + "AccelerationExplorer" + File.separator
                + "Logs");
        if (!dir.exists())
        {
            dir.mkdirs();
        }
        File file = new File(dir, filename);

        try
        {
            CsvWriter csvOutput = new CsvWriter(new FileWriter(file,true),',');
            csvOutput.write(log);
            csvOutput.endRecord();
            csvOutput.close();
            CharSequence text = "Log Saved";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }

        catch (FileNotFoundException e)
        {
            CharSequence text = e.toString();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }
        catch (IOException e) {}
        finally
        {
            MediaScannerConnection.scanFile(this, new String[]
                            { file.getPath() }, null,
                    new MediaScannerConnection.OnScanCompletedListener()
                    {
                        @Override
                        public void onScanCompleted(final String path,
                                                    final Uri uri)
                        {

                        }
                    });
        }
    }

}