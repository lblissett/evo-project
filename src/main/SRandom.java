package main;

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
    public static Double createRandomDouble(int minValue, int maxValue) throws
            Exception{

        if (minValue >= maxValue) {
            throw new Exception("Fehler in main.Gene.createRandom(): Der " +
                    "Maximalwert muss groesser als der " +
                    "Minimalwert des Wertebereichs für das Allel sein!");
        }
        try {
            Random random = new Random();
            int precision = 1000000000;
            int minRandom = minValue * precision;
            int maxRandom = maxValue * precision;
            int bound = Math.abs(maxRandom - minRandom) + 1;
            return ((double) random.nextInt(bound) + minRandom) /
                    precision;

        } catch (Exception ex) {
            throw new Exception("Fehler in main.Gene.createRandom(): " + ex);
        }
    }
}
