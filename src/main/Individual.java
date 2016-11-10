package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse zur Definierung eines Individuums abhängig von der Kodierungsart
 */
public class Individual {

    private List<Double> genome;

    public List<Double> getGenome() {
        return this.genome;
    }

    public void setGenome(List<Double> genome) {
        this.genome = genome;
    }

    /**
     * Create new individual object with given list of genes
     * @param genome {List<main.Gene>} list of genes
     */
    public Individual(List<Double> genome) {
        this.genome = genome;
    }

    public Individual() {
        this.genome = new ArrayList<>();
    }

    /**
     * Creates a random individual object with given count of genes and valuation
     * for creating random allele
     * @param countGenes
     * @param minAllele
     * @param maxAllele
     * @return {main.Individual} individual object
     * @throws Exception if minAllele >= maxAllele
     */
    public static Individual createRandom(int countGenes, int minAllele, int
            maxAllele) throws Exception {

        List<Double> genome = new ArrayList<>();

        for (int i = 0; i < countGenes; i++) {
            try {
                genome.add(SRandom.createRandomDouble(minAllele, maxAllele));
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        }

        return new Individual(genome);
    }

}
