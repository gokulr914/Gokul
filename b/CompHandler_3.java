package com.examples.singamnist;

/**
 * Created by AdminCOOP on 9/12/2016.
 */

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.csvreader.CsvWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.regression.RegressionResults;

public class CompHandler_3{

    private List<float[]> results;
    private DescriptiveStatistics stats;
    public StatUtils Stats;

    public CompHandler_3() {
        results = new ArrayList<float[]>();
    }

    public List<float[]> getResults() {
        return results;
    }

    public void processData(List<float[]> incomingData) {

        if (incomingData == null) return;

        for (int i = 0; i < incomingData.get(0).length; i++) {

            stats = new DescriptiveStatistics();

            double[] nw = convertFloatsIndexToDoubles(incomingData, i);
            for (int j = 0; j < nw.length; j++) {
                stats.addValue(nw[j]);
            }
            float[] tempResults = new float[7];

            tempResults[0] = (float) stats.getMean();
            tempResults[1] = (float) stats.getMax();
            tempResults[2] = (float) stats.getStandardDeviation();
            tempResults[3] = (float) stats.getMin();
            tempResults[4] = (float) stats.getVariance();
            tempResults[5] = (float) stats.getKurtosis();
            tempResults[6] = (float) stats.getSkewness();

            //Min,max and std dev are stored into the results element
            results.add(tempResults);
        }
        writeLogToFile();
    }

    public String[] convertfromfloattoString(float[] vals) {

        String[] sVals = new String[vals.length];
        for (int i = 0; i < vals.length; i++) {
            sVals[i] = "" + vals[i];
        }
        return sVals;
    }


    private void writeLogToFile()
    {
        Calendar c = Calendar.getInstance();
        String filename = "AccelerationExplorer-" + c.get(Calendar.YEAR) + "-"
                + (c.get(Calendar.MONTH) + 1) + "-"
                + c.get(Calendar.DAY_OF_MONTH) + c.get(Calendar.HOUR) + c.get(Calendar.MINUTE)+ c.get(Calendar.SECOND)+"3"
                + ".csv";

        File dir = new File(Environment.getExternalStorageDirectory()
                + File.separator + "AccelerationExplorer" + File.separator
                + "Logs");
        if (!dir.exists())
        {
            dir.mkdirs();
        }
        File file = new File(dir, filename);

        List<float[]> log = getResults();
        float[] nw=log.get(0);
        String[] fw = convertfromfloattoString(nw);

        try
        {
            CsvWriter csvOutput = new CsvWriter(new FileWriter(file,true),',');
            csvOutput.writeRecord(fw);
            csvOutput.close();
            CharSequence text = "Log Saved";
            int duration = Toast.LENGTH_SHORT;
            //Toast toast = Toast.makeText(t, text, duration);
            //toast.show();
        }

        catch (FileNotFoundException e)
        {
            CharSequence text = e.toString();
            int duration = Toast.LENGTH_SHORT;

            //Toast toast = Toast.makeText(this, text, duration);
            //toast.show();
        }
        catch (IOException e) {}
        finally
        {
            // MediaScannerConnection.scanFile(this, new String[]
            //               { file.getPath() }, null,
            new MediaScannerConnection.OnScanCompletedListener()
            {
                @Override
                public void onScanCompleted(final String path,
                                            final Uri uri)
                {

                }
            };
        }
    }

    public double[] convertFloatsIndexToDoubles(List<float[]> input, int index) {

        if (input == null || input.get(0).length < index) {
            return null;
        }

        double[] output = new double[input.size()];

        for (int i = 0; i < input.size(); i++) {
            output[i] = input.get(i)[index];
        }
        return output;
    }
}
