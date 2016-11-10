package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geopras on 16.10.16.
 */
public class FitnessFunction {




    public static Double calculateFitnessValue_1(double x1, double x2, double x3, double x4) {

        double Fx = Math.pow((x1 + 10 * x2), 2) +
                    5 * Math.pow((x3 - x4), 2) +
                    Math.pow((x2 - 2 * x3), 4) +
                    10 * Math.pow((x1 - x4), 4);
        return Fx;
    }

    public static Double calculateGriewank(ArrayList<Double> values) {

        double Fx = 0;
        double sum = 0;
        double prod = 1;

        for(int i=0; i < values.size(); i++) {

           sum += Math.pow(values.get(i),2) / (400 * values.size()) ;
           prod *= Math.cos(values.get(i) / Math.sqrt(i + 1));

        }

        return Fx = 1 + sum - prod;
    }
}
