package main;

import java.util.List;

/**
 * Class for mutation of individuals
 */
public class Mutation {

    private Double probability;
    private Double fixMutationValue;
    private Integer minValue;
    private Integer maxValue;
    private Integer countPreceedingDigits;
    private Integer lengthMantissa;

    /**
     * Creates a mutation object for mutating genes after a certain probability
     * @param probability
     */
    public Mutation(Double probability, Double fixMutationValue, Integer minValue,
                    Integer maxValue, Integer countPreceedingDigits, Integer
                            lengthMantissa) {
        this.probability = probability;
        this.fixMutationValue = fixMutationValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.countPreceedingDigits = countPreceedingDigits;
        this.lengthMantissa = lengthMantissa;
    }

    /**
     * Starts mutating genes of individuals after a certain probability
     * @param populations
     * @return List of individuals which are maybe mutated
     */
    public Populations start(Populations populations) {

        populations.setReal(this.mutateReal(populations.getReal()));
        populations.setBinaryOnePoint(this.mutateBinary(populations
                .getBinaryOnePoint()));
        populations.setBinaryTwoPoint(this.mutateBinary(populations
                .getBinaryTwoPoint()));
        return populations;
    }

    private Population mutateReal(Population population) {

        List<List<Double>> children = population.getChildren();

        for (int i = 0; i < children.size(); i++) {

            List<Double> currentChild = children.get(i);
            int randomGeneIndex = SRandom.getRandomIndex(currentChild.size());
            Double randomMutationProbability = SRandom.getRandomProbability();

            if (randomMutationProbability < this.probability) {
//                System.out.println("Folgendes Individuum wird mutiert: " +
//                        currentIndividual);
                Double mutatedAllele = currentChild.get(randomGeneIndex) +
                        this.fixMutationValue;
                if (mutatedAllele < this.minValue) {
                    mutatedAllele = (double)this.minValue;
                } else if (mutatedAllele > this.maxValue) {
                    mutatedAllele = (double)this.maxValue;
                }
                currentChild.set(randomGeneIndex, mutatedAllele);
                children.set(i, currentChild);
//                System.out.println("So sieht es nach der Mutation aus: " +
//                        individuals.get(i));
            }
        }
        population.setChildren(children);
        return population;
    }

    private Population mutateBinary(Population population) {

        List<List<Double>> children = population.getChildren();

        for (int i = 0; i < children.size(); i++) {

            List<Double> currentChild = children.get(i);

            int randomGeneIndex = SRandom.getRandomIndex(currentChild.size());
            Double chosenGeneReal = currentChild.get(randomGeneIndex);

            Double randomMutationProbability = SRandom.getRandomProbability();
            if (randomMutationProbability < this.probability) {

                String chosenGeneBinary;
                int randomBitIndex;
                try {
                    chosenGeneBinary = SConverter.doubleToBinaryString
                            (chosenGeneReal, this.countPreceedingDigits, this
                                    .lengthMantissa);
                    randomBitIndex = SRandom.getRandomIndex(chosenGeneBinary.length());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return population;
                }
//                System.out.println("Folgendes Individuum wird mutiert: " +
//                        currentIndividual);

                // Switch bit of chosen gene:
                StringBuffer chosenGene = new StringBuffer(chosenGeneBinary);
                if (chosenGene.charAt(randomBitIndex) == '0') {
                    chosenGene.setCharAt(randomBitIndex, '1');
                } else {
                    chosenGene.setCharAt(randomBitIndex, '0');
                }
                Double mutatedAllele = SConverter.binaryStringToDouble
                        (chosenGene.toString(), this.countPreceedingDigits, this
                                .lengthMantissa);
                currentChild.set(randomGeneIndex, mutatedAllele);
                children.set(i, currentChild);
//                System.out.println("So sieht es nach der Mutation aus: " +
//                        individuals.get(i));
            }
        }
        population.setChildren(children);
        return population;
    }
}
