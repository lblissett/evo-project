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
     * @param recombinationProbability {Double} probability of being a
     *                                 parent couple (between 0-1)
     */
    public ParentSelection(Double recombinationProbability) {

        this.recombinationProbability = recombinationProbability;
    }

    /**
     * Selects population couples as new parents
     * @param populations Populations of parent and child individuals for
     *                    different encoding
     * @return List of arrays of individuals as parent couples
     */
    public Populations start(Populations populations, int countSelectedParentCouples) {

        return randomSelection(populations, countSelectedParentCouples);
    }

    private Populations randomSelection(Populations populations, int countSelectedParentCouples) {

        populations.resetChildren();
        populations.resetParentCouples();

        int countParents = populations.getReal().getParents().size();
        int counter = 0;
        while(counter < countSelectedParentCouples) {

            // zufällig Elternteile auswählen:
            int randomIndex1 = SRandom.getRandomIndex(countParents);
            int randomIndex2 = SRandom.getRandomIndex(countParents);

            // Wenn 2x das gleiche Elternteil ausgewählt wurde, Auswahl
            // verwerfen
            if (randomIndex1 == randomIndex2) continue;

            List<Double> parentReal1 = populations.getReal().getParents().get
                    (randomIndex1);
            List<Double> parentReal2 = populations.getReal().getParents().get
                    (randomIndex2);

            List<List<Double>> parentsReal = new ArrayList<>();
            parentsReal.add(parentReal1);
            parentsReal.add(parentReal2);

            // Wenn diese Kombination bereits existiert, dann Auswahl verwerfen
            if (populations.getReal().getParentCouples().contains(parentsReal))
                continue;

            if (SRandom.getRandomProbability() < this.recombinationProbability) {

                populations.addParentCoupleReal(parentsReal);

                List<Double> parentBinaryOnePoint1 = populations
                        .getBinaryOnePoint().getParents().get(randomIndex1);
                List<Double> parentBinaryOnePoint2 = populations
                        .getBinaryTwoPoint().getParents().get(randomIndex2);
                List<List<Double>> parentsBinaryOnePoint = new ArrayList<>();
                parentsBinaryOnePoint.add(parentBinaryOnePoint1);
                parentsBinaryOnePoint.add(parentBinaryOnePoint2);
                populations.addParentCoupleBinaryOnePoint(parentsBinaryOnePoint);

                List<Double> parentBinaryTwoPoint1 = populations
                        .getBinaryTwoPoint().getParents().get(randomIndex1);
                List<Double> parentBinaryTwoPoint2 = populations
                        .getBinaryTwoPoint().getParents().get(randomIndex2);
                List<List<Double>> parentsBinaryTwoPoint = new ArrayList<>();
                parentsBinaryTwoPoint.add(parentBinaryTwoPoint1);
                parentsBinaryTwoPoint.add(parentBinaryTwoPoint2);
                populations.addParentCoupleBinaryTwoPoint(parentsBinaryTwoPoint);

                ++counter;
            }
        }

        return populations;
    }

    private List<List<List<Double>>> tournamentSelection(Population population) {
        return new ArrayList<>();
    }
}
