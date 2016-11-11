package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by atari on 04.11.16.
 */
public class SRandom {

    public static int getRandomIndex(int sizeList) {

        Random random = new Random();
        return random.nextInt(sizeList);
    }

    public static double getRandomProbability() {

        Random random = new Random();
        double a = (double) random.nextInt(101);
        double b = a / 100;
        return b;
    }

    /**
     * Generate a gene object with random allele
     * @param minValue {int} minimum of valuation for random allele
     * @param maxValue {int} maximum of valuation for random allele
     * @return {Double} random Double value
     * @throws Exception
     */
    public static Integer createRandomInt(int minValue, int maxValue) throws
            Exception{

        if (minValue >= maxValue) {
            throw new Exception("Fehler in main.Gene.createRandom(): Der " +
                    "Maximalwert muss groesser als der " +
                    "Minimalwert des Wertebereichs für das Allel sein!");
        }
        try {
            Random rand = new Random();
            return rand.nextInt(maxValue - minValue + 1) + minValue;

        } catch (Exception ex) {
            throw new Exception("Fehler in createRandom(): " + ex);
        }
    }

    /**
     * Creates a random list of Integer values with given count of values and
     * valuation
     * for creating random allele
     * @param countValues
     * @param minValue
     * @param maxValue
     * @return {main.Individual} individual object
     * @throws Exception if minAllele >= maxAllele
     */
    public static List<Double> createRandomIntList(int countValues, int
            minValue, int maxValue) throws Exception {

        List<Double> genome = new ArrayList<>();

        for (int i = 0; i < countValues; i++) {
            try {
                genome.add((double)createRandomInt(minValue, maxValue));
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        }

        return genome;
    }
}
