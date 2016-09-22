package com.examples.singamnist;

import android.app.Activity;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.StatUtils;


public class Time_Linear_Acc_Compute extends Activity {

    private List<float[]> results = new ArrayList<float[]>();
    private DescriptiveStatistics stats;
    public StatUtils Stats;

    public Time_Linear_Acc_Compute() {
        results = new ArrayList<float[]>();
    }

    public List<float[]> getResults() {
        return results;
    }

    public void processData(List<float[]> incomingData) {

        if (incomingData == null) return;

        for (int i = 0; i < incomingData.get(0).length; i++) {

            //Declare Constructor
            DescriptiveStatistics stats = new DescriptiveStatistics(convertFloatsIndexToDoubles(incomingData, i));

            float[] Window_results = new float[5];   //Store Results of the Window processing in an array

            Window_results[0] = (float) stats.getMean(); //Get Mean of the Window
            Window_results[1] = (float) stats.getMax();    //Get Max of the Window
            Window_results[2] = (float) stats.getStandardDeviation(); //Get Standard Deviation of the Window
            Window_results[3] = (float) stats.getMin();  //Get minimum of the Window
            Window_results[4] = (float) stats.getVariance(); //Get Variance of the Window

            results.add(Window_results);
        }
    }

    private double[] convertFloatsIndexToDoubles(List<float[]> input, int index) {

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

