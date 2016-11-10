package main;

import java.util.List;

/**
 * Created by geopras on 16.10.16.
 */
public class Mutation {

    private Double probability;

    /**
     * Creates a mutation object for mutating genes after a certain probability
     * @param probability
     */
    public Mutation(Double probability) {
        this.probability = probability;
    }

    /**
     * Starts mutating genes of individuals after a certain probability
     * @param individuals
     * @return List of individuals which are maybe mutated
     */
    public List<Individual> start(List<Individual> individuals) {



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
