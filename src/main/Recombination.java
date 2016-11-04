package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geopras on 16.10.16.
 */
public class Recombination {

    /**
     * Creates a new recombination object for building a new child generation from parent generation
     */
    public Recombination() {

    }

    /**
     * Returns a new child generation after recombining within parent generation
     * @param parentCouples List of parent couples
     * @return List of child individuals
     */
    public List<Individual> createChildren(List<List<Individual>> parentCouples) {
        return intermediateMethod(parentCouples);
    }

    private List<Individual> intermediateMethod(List<List<Individual>> parentCouples) {

        List<Individual> children = new ArrayList<>();

        for (int i = 0; i < parentCouples.size(); i++) {
            List<Individual> couple = parentCouples.get(i);
            Individual parentA = couple.get(0);
            Individual parentB = couple.get(1);
            List<Gene> childGenome = new ArrayList<>();

            for (int j = 0; j < parentA.getGenome().size(); j++) {
                Gene geneA = parentA.getGenome().get(j);
                Gene geneB = parentB.getGenome().get(j);
                // Der Mittelwert zwischen den beiden zugehoerigen
                // Eltern-Allelen bildet das neue Kind-Allel und damit Kind-Gen:
                Gene childGene = new Gene((geneA.getAllele() + geneB
                        .getAllele()) / 2);
                childGenome.add(childGene);
            }
            children.add(new Individual(childGenome));
        }

        return children;
    }

    private List<Individual> onePointMethod(List<Individual[]> parentCouples) {
        List<Individual> children = new ArrayList<>();

        return children;
    }
}
