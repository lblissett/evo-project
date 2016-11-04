package main;

import java.util.Random;

/**
 * Created by geopras on 16.10.16.
 */
public class Gene {

    private Double allele;

    public Double getAllele() {
        return allele;
    }

    public void setAllele(Double allele) {
        this.allele = allele;
    }

    /**
     * Create a gene with given allele
     */
    public Gene(Double allele) {
        this.allele = allele;
    }

    /**
     * Create a gene with a default allele (1.0)
     */
    public Gene() {
        this.allele = 1.0;
    }

    /**
     * Generate a gene object with random allele
     * @param minAllele {int} minimum of valuation for random allele
     * @param maxAllele {int} maximum of valuation for random allele
     * @return {main.Gene} new gene object
     * @throws Exception
     */
    public static Gene createRandom(int minAllele, int maxAllele) throws
            Exception{

        if (minAllele >= maxAllele) {
            throw new Exception("Fehler in main.Gene.createRandom(): Der " +
                    "Maximalwert muss groesser als der " +
                    "Minimalwert des Wertebereichs für das Allel sein!");
        }
        try {
            Random random = new Random();
            int precision = 1000000000;
            int minRandom = minAllele * precision;
            int maxRandom = maxAllele * precision;
            int bound = Math.abs(maxRandom - minRandom) + 1;
            Double randomAllele = ((double) random.nextInt(bound) + minRandom) / precision;
            return new Gene(randomAllele);
        } catch (Exception ex) {
            throw new Exception("Fehler in main.Gene.createRandom(): " + ex);
        }
    }
}
