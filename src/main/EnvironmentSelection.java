package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geopras on 16.10.16.
 */
public class EnvironmentSelection {

    /**
     * Returns a new object for selecting individuals from a given population
     *
     */
    public EnvironmentSelection() {

    }

    /**
     * Starts environment selection to kill individuals after certain rules
     * @param population
     * @return List of individuals
     */
    public List<List<Double>> start(Population population, Integer
            countFittest, Integer countRandomIndividuals) {

        return selectFittest(population, countFittest, countRandomIndividuals);
    }

    private List<List<Double>> selectFittest(Population population, Integer
            countFittest, Integer countRandomIndividuals) {

        List<List<Double>> parentsAndChildren = population.getParents();
        parentsAndChildren.addAll(population.getChildren());

        List<List<Double>> fittest = FitnessFunction
                .calculateGriewankSorted(population.getParents());
        List<List<Double>> topIndividuals = fittest
                .subList(0, countFittest);

        // Zufallsauswahl aus Rest, sofern noch Rest vorhanden
        if (topIndividuals.size() < fittest.size()) {

            List<List<Double>> rest = fittest.subList
                    (countFittest, fittest.size());

            List<List<Double>> randomIndividuals = new ArrayList<>();
            while (randomIndividuals.size() < countRandomIndividuals) {

                List<Double> randomIndividual = rest.get(SRandom.getRandomIndex
                        (rest.size()));
                if (!randomIndividuals.contains(randomIndividual)) {
                    randomIndividuals.add(randomIndividual);
                }
            }
            topIndividuals.addAll(randomIndividuals);
        }

        return topIndividuals;
    }
}
