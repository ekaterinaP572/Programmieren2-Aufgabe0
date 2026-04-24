package org.htw.prog2.aufgabe0;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.SwingWrapper;

import java.util.ArrayList;


public class MyProject {

    /**
     * Calculate root X of a value S according to babylonian algorithm, starting with
     * an initial estimate X(0):
     * <ol>
     *     <li>Estimate the error e(n): e(n)=(S-X(n-1)²)/(2*X(n-1))</li>
     *     <li>Calculate X(n): X(n)=X(n-1)+e(n)</li>
     * </ol>
     * Continue until the estimated error reaches the desired maximum error
     * @param value The value to calculate the root of
     * @param initial The initial value to start the calculation with
     * @param maxerror The maximum allowed error
     * @return An array containing the values of all iterations. The last value in the array is the final estimate.
     */
    public static double[] calculateBabylonianRoot(double value, double initial, double maxerror) {
        double root = Math.sqrt(value);
        ArrayList<Double> initials = new ArrayList<>();
        double toCompare = root - initial;
        double initialUpdated;
        double e;
        if(maxerror<0){
            maxerror = maxerror*(-1);
        }
        if(value <=0 || root <= 0){
            return new double[] {0};
        }
        while(toCompare > maxerror){
            e = (value - (initial*initial))/(2*initial);
            initialUpdated = e + initial;
            initial = initialUpdated;
            initials.add(initial);
            toCompare = root - initial;
            if(toCompare<0){
                toCompare = toCompare*(-1);
            }
        }
        double[] array = new double[initials.size()];
        for(int i = 0; i<initials.size(); i++){
            double key = initials.get(i);
            array[i] = key;
        }

        return array;
    }
    public static void plotData(double[] values) {
        XYChart chart = new XYChart(500, 500);
        chart.addSeries("Data", values);
        new SwingWrapper(chart).displayChart();
    }
    public static void main(String[] args) {
        plotData(calculateBabylonianRoot(74821, 5, 0.1));
    }
}
