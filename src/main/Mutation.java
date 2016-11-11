package main;

import main.enums.Encoding;

import java.util.List;

/**
 * Class for mutation of individuals
 */
public class Mutation {

    private Double probability;
    private Encoding encoding;
    private Integer minValue;
    private Integer maxValue;
    private Integer countPreceedingDigits;
    private Integer lengthMantissa;

    /**
     * Creates a mutation object for mutating genes after a certain probability
     * @param probability
     */
    public Mutation(Double probability, Encoding encoding, Integer minValue,
                    Integer maxValue, Integer countPreceedingDigits, Integer
                            lengthMantissa) {
        this.probability = probability;
        this.encoding = encoding;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.countPreceedingDigits = countPreceedingDigits;
        this.lengthMantissa = lengthMantissa;
    }

    /**
     * Starts mutating genes of individuals after a certain probability
     * @param individuals
     * @return List of individuals which are maybe mutated
     */
    public List<List<Double>> start(List<List<Double>> individuals) {

        switch (this.encoding) {
            case REAL: return mutateReal(individuals);
            case BINARY: return mutateBinary(individuals);
        }
        return mutateReal(individuals);  // Default
    }

    private List<List<Double>> mutateReal(List<List<Double>> individuals) {

        for (int i = 0; i < individuals.size(); i++) {

            List<Double> currentIndividual = individuals.get(i);
            int randomGeneIndex = SRandom.getRandomIndex(currentIndividual.size());
            Double randomMutationProbability = SRandom.getRandomProbability();
            Double randomMutationValue = 0.0;
            try {
                randomMutationValue = (double)SRandom.createRandomInt(this
                        .minValue, this.maxValue);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return individuals;
            }

            if (randomMutationProbability < this.probability) {
//                System.out.println("Folgendes Individuum wird mutiert: " +
//                        currentIndividual);
                Double mutatedAllele = currentIndividual.get(randomGeneIndex) +
                        randomMutationValue;
                if (mutatedAllele < this.minValue) {
                    mutatedAllele = (double)this.minValue;
                } else if (mutatedAllele > this.maxValue) {
                    mutatedAllele = (double)this.maxValue;
                }
                currentIndividual.set(randomGeneIndex, mutatedAllele);
                individuals.set(i, currentIndividual);
//                System.out.println("So sieht es nach der Mutation aus: " +
//                        individuals.get(i));
            }
        }
        return individuals;
    }

    private List<List<Double>> mutateBinary(List<List<Double>> individuals) {

        for (int i = 0; i < individuals.size(); i++) {

            List<Double> currentIndividual = individuals.get(i);

            int randomGeneIndex = SRandom.getRandomIndex(currentIndividual.size());
            Double chosenGeneReal = currentIndividual.get(randomGeneIndex);

            Double randomMutationProbability = SRandom.getRandomProbability();
            if (randomMutationProbability < this.probability) {

                Double randomMutationValue;
                String chosenGeneBinary;
                int randomBitIndex;
                try {
                    randomMutationValue = (double)SRandom.createRandomInt(this
                            .minValue, this.maxValue);
                    chosenGeneBinary = SConverter.doubleToBinaryString
                            (chosenGeneReal, this.countPreceedingDigits, this
                                    .lengthMantissa);
                    randomBitIndex = SRandom.getRandomIndex(chosenGeneBinary.length());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return individuals;
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
                currentIndividual.set(randomGeneIndex, mutatedAllele);
                individuals.set(i, currentIndividual);
//                System.out.println("So sieht es nach der Mutation aus: " +
//                        individuals.get(i));
            }
        }
        return individuals;
    }
}
