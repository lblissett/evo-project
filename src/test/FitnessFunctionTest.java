package test;

import main.FitnessFunction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by geopras on 11.11.16.
 */
public class FitnessFunctionTest {

    @Test
    public void calculateGriewankSorted_return_correct_Toplist() {

        List<List<Double>> individuals = new ArrayList<>();
        individuals.add(new ArrayList<>(Arrays.asList(1.0, 5.0, -20.0)));
        individuals.add(new ArrayList<>(Arrays.asList(76.0, 34.0, -130.0)));
        individuals.add(new ArrayList<>(Arrays.asList(-10.0, 9.0, 80.0)));

        List<List<Double>> result = FitnessFunction.calculateGriewankSorted
                (individuals);
        //System.out.println(result.subList(0,2));
    }
}
