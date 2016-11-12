package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geopras on 16.10.16.
 */
public class Recombination {

    private int countPreceedingDigits;
    private int lengthMantissa;

    /**
     * Creates a new recombination object for building a new child generation from parent generation
     */
    public Recombination(int countPreceedingDigits, int
            lengthMantissa) {
        this.countPreceedingDigits = countPreceedingDigits;
        this.lengthMantissa = lengthMantissa;
    }

    /**
     * Returns a new child generation after recombining within parent generation
     * @param {Population} List of population
     * @return population with new generation
     */
    public Populations start(Populations populations) {

        populations.setReal(this.intermediateMethod(populations.getReal()));
        populations.setBinaryOnePoint(this.onePointMethod(populations.getBinaryOnePoint()));
        populations.setBinaryTwoPoint(this.twoPointMethod(populations
                .getBinaryTwoPoint()));
        return populations;
    }

    private Population intermediateMethod(Population population) {

        List<List<Double>> children = new ArrayList<>();

        for (List<List<Double>> parents : population.getParentCouples()) {

            List<Double> parentA = parents.get(0);
            List<Double> parentB = parents.get(1);
            List<Double> childGenome = new ArrayList<>();

            for (int j = 0; j < parentA.size(); j++) {
                Double alleleA = parentA.get(j);
                Double alleleB = parentB.get(j);
                // Der Mittelwert zwischen den beiden zugehoerigen
                // Eltern-Allelen bildet das neue Kind-Allel und damit Kind-Gen:
                Double childGene = new Double((alleleA + alleleB) / 2);
                childGenome.add(childGene);
            }
            children.add(new ArrayList<>(childGenome));
        }
        population.setChildren(children);
        return population;
    }

    private List<List<Double>> arithmeticMethod(List<List<List<Double>>>
                                                      parentCouples) {
        return new ArrayList<>();
    }

    private Population onePointMethod(Population population) {

        List<List<Double>> children = new ArrayList<>();

        for (List<List<Double>> parents : population.getParentCouples()) {

            List<Double> parentA = parents.get(0);
            List<String> parentBinaryA;
            List<Double> parentB = parents.get(1);
            List<String> parentBinaryB;
            try {
                parentBinaryA = SConverter
                        .doubleListToBinaryStringList(parentA, this
                                .countPreceedingDigits, this.lengthMantissa);
                parentBinaryB = SConverter
                        .doubleListToBinaryStringList(parentB, this
                                .countPreceedingDigits, this.lengthMantissa);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return population;
            }

            List<Double> childGenome = new ArrayList<>();

            for (int i = 0; i < parentA.size(); i++) {
                String alleleA = parentBinaryA.get(i);
                String alleleB = parentBinaryB.get(i);

                // Bestimme Zufallsindex, bis zu dem rekombiniert werden soll
                int randomIndex = SRandom.getRandomIndex(alleleA.length());

                String childAllele = alleleA.substring(0, randomIndex) +
                        alleleB.substring(randomIndex);
                childGenome.add(SConverter.binaryStringToDouble(childAllele,
                        this.countPreceedingDigits, this.lengthMantissa));
            }
            children.add(childGenome);
        }
        population.setChildren(children);
        return population;
    }

    private Population twoPointMethod(Population population) {

        List<List<Double>> children = new ArrayList<>();

        for (List<List<Double>> parents : population.getParentCouples()) {

            List<Double> parentA = parents.get(0);
            List<String> parentBinaryA;
            List<Double> parentB = parents.get(1);
            List<String> parentBinaryB;
            try {
                parentBinaryA = SConverter
                        .doubleListToBinaryStringList(parentA, this
                                .countPreceedingDigits, this.lengthMantissa);
                parentBinaryB = SConverter
                        .doubleListToBinaryStringList(parentB, this
                                .countPreceedingDigits, this.lengthMantissa);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return population;
            }

            List<Double> childGenome = new ArrayList<>();

            for (int i = 0; i < parentA.size(); i++) {
                String alleleA = parentBinaryA.get(i);
                String alleleB = parentBinaryB.get(i);

                // Bestimme 2 Zufallsindizees, innerhalb derer rekombiniert
                // werden soll
                int randomIndex1 = 0;
                int randomIndex2 = 0;
                int randomIndexLeft = 0;
                int randomIndexRight = 0;
                while (randomIndex1 == randomIndex2) {

                    randomIndex1 = SRandom.getRandomIndex(alleleA.length());
                    randomIndex2 = SRandom.getRandomIndex(alleleA.length());

                    if (randomIndex1 < randomIndex2) {
                        randomIndexLeft = randomIndex1;
                        randomIndexRight = randomIndex2;
                    } else if (randomIndex1 > randomIndex2) {
                        randomIndexLeft = randomIndex2;
                        randomIndexRight = randomIndex1;
                    }
                }

                // Es soll nur von Eternteil B der Bereich innerhalb der
                // Punkte an das Kind vererbt werden:
                String childAllele = alleleA;
                String subA = alleleA.substring(randomIndexLeft,
                        randomIndexRight);
                String subB = alleleB.substring(randomIndexLeft,
                        randomIndexRight);
                childAllele = childAllele.replace(subA, subB);
                childGenome.add(SConverter.binaryStringToDouble(childAllele,
                        this.countPreceedingDigits, this.lengthMantissa));
            }
            children.add(childGenome);
        }
        population.setChildren(children);
        return population;
    }
}
