package main;

import java.util.*;

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

    public static Double calculateGriewank(List<Double> individual) {

        Double sum = 0.0;
        Double prod = 1.0;
        Integer n = individual.size();

        for (int i = 0; i < n; i++) {

            sum += Math.pow(individual.get(i), 2) / (400.0 * n);
            prod *= Math.cos(individual.get(i) / Math.sqrt(i + 1));
        }

        return 1 + sum - prod;
    }

    public static List<List<Double>> calculateGriewankSorted(List<List<Double>>
                                                               individuals) {
        Map<Double, List<Double>> topIndividuals = new TreeMap<>
                ((o1, o2) -> o2.compareTo(o1));

        for (List<Double> individual : individuals) {

            topIndividuals.put(calculateGriewank(individual), individual);
        }

//        System.out.println("Besten Individuen: " + topIndividuals);

        List<List<Double>> topList = new ArrayList<>(topIndividuals.values());
        Collections.reverse(topList);
        return topList;
    }
}
