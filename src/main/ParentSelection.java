package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geopras on 16.10.16.
 */
public class ParentSelection {

    private int countSelectedParentCouples;
    private Double recombinationProbability;

    /**
     * Returns a new object for selecting individuals from a given population
     * @param countSelectedParentCouples Count of parentCouples to select
     * @param recombinationProbability {Double} probability of being a
     *                                 parent couple (between 0-1)
     */
    public ParentSelection(int countSelectedParentCouples, Double
            recombinationProbability) {
        this.countSelectedParentCouples = countSelectedParentCouples;
        this.recombinationProbability = recombinationProbability;
    }

    /**
     * Selects population couples as new parents
     * @param population Population of parent and child individuals
     * @return List of arrays of individuals as parent couples
     */
    public List<List<List<Double>>> start(Population population) {
        return randomSelection(population);
    }

    private List<List<List<Double>>> randomSelection(Population population) {

        List<List<List<Double>>> parentCouples = new ArrayList<>();
        //All individuals in one list
        List<List<Double>> allIndividuals = population.getParents();

        int counter = 0;

        while(counter < this.countSelectedParentCouples) {

            List<Double> parent1 = allIndividuals.get(SRandom.getRandomIndex(allIndividuals.size()));
            List<Double> parent2 = allIndividuals.get(SRandom.getRandomIndex(allIndividuals.size()));

            if (SRandom.getRandomProbability() < this.recombinationProbability) {
                List<List<Double>> parents= new ArrayList<>();
                parents.add(parent1);
                parents.add(parent2);
                parentCouples.add(parents);
                ++counter;
            }
        }

        return parentCouples;
    }

    private List<List<List<Double>>> tournamentSelection(Population population) {
        return new ArrayList<>();
    }
}
