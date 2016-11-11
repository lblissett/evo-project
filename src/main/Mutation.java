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

    /**
     * Creates a mutation object for mutating genes after a certain probability
     * @param probability
     */
    public Mutation(Double probability, Encoding encoding, Integer minValue,
                    Integer maxValue) {
        this.probability = probability;
        this.encoding = encoding;
        this.minValue = minValue;
        this.maxValue = maxValue;
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
            int randomIndex = SRandom.getRandomIndex(currentIndividual.size());
            Double randomMutationProbability = SRandom.getRandomProbability();
            Double randomMutationValue = 0.0;
            try {
                randomMutationValue = (double)SRandom.createRandomInt(this
                        .minValue, this.maxValue);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            if (randomMutationProbability < this.probability) {
//                System.out.println("Folgendes Individuum wird mutiert: " +
//                        currentIndividual);
                Double mutatedAllele = currentIndividual.get(randomIndex) +
                        randomMutationValue;
                if (mutatedAllele < this.minValue) {
                    mutatedAllele = (double)this.minValue;
                } else if (mutatedAllele > this.maxValue) {
                    mutatedAllele = (double)this.maxValue;
                }
                currentIndividual.set(randomIndex, mutatedAllele);
                individuals.set(i, currentIndividual);
//                System.out.println("So sieht es nach der Mutation aus: " +
//                        individuals.get(i));
            }
        }
        return individuals;
    }

    private List<List<Double>> mutateBinary(List<List<Double>> individuals) {
        return individuals;
    }

    public List<Individual> mutateReal(List<Individual> individuals, double mutationProbability, int w) {


        for(int i = 0; i < individuals.size(); i++) {


            if (SRandom.getRandomProbability() < mutationProbability)  {

                int randomGene = SRandom.getRandomIndex(individuals.get(i).getGenome().size());

                individuals.get(i).getGenome().set(randomGene, individuals.get(i).getGenome().get(randomGene) + w);

            }

        }
        
        return individuals;
    }
}
