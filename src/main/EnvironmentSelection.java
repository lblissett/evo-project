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
     * @param populations
     * @return List of individuals
     */
    public Populations start(Populations populations, Integer
            countFittest, Integer countRandomIndividuals) {

        populations.setReal(this.selectFittestAndRandom(populations.getReal(),
                countFittest,
                countRandomIndividuals));
        populations.setBinaryOnePoint(this.selectFittestAndRandom(populations
                .getBinaryOnePoint(), countFittest, countRandomIndividuals));
        populations.setBinaryTwoPoint(this.selectFittestAndRandom(populations
                .getBinaryTwoPoint(), countFittest, countRandomIndividuals));
        return populations;
    }

    private Population selectFittestAndRandom(Population population, Integer
            countFittest, Integer countRandomIndividuals) {

        List<List<Double>> parentsAndChildren = population.getParents();
        parentsAndChildren.addAll(population.getChildren());

        List<List<Double>> fittest = FitnessFunction
                .calculateGriewankSorted(parentsAndChildren);
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
        population.setParents(topIndividuals);
        return population;
    }
}
