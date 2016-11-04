package main;

/**
 * Created by geopras on 14.10.16.
 */
public class Main {
    public static void main(String[] args) {

        // Initialization parameter:
        int countEvolutionCycles = 1000;  // count of evolution cycles
        int countIndividuals = 10;
        int countGenes = 4;  // genes count of one individual
        int minAllele = -1000;  // min value for creating a gene allele
        int maxAllele = 1000;  // max value for creating a gene allele
        int countParentCouples = 10;  // count of parent couples who create a new child
        Double recombinationProbability = 0.7; // probability if two parents
        // create a new child (between 0-1)
        Double mutationProbability = 0.01; // probability if a gene of an individual mutates
        int countSelectedIndividuals = countParentCouples;  // count of selected population individuals

        // Workflow objects for evolution process:
        Population population = createPopulationRandom(countIndividuals, countGenes, minAllele, maxAllele);
        ParentSelection parentSelection = new ParentSelection(countSelectedIndividuals, recombinationProbability);
        Recombination recombination = new Recombination();
        EnvironmentSelection environmentSelection = new EnvironmentSelection(countSelectedIndividuals);
        Mutation mutation = new Mutation(mutationProbability);

        // Begin evolution cycles:
        int currentCycle = 0;
        while (currentCycle < countEvolutionCycles) {
            // 1) Define parents
            population.setParentCouples(parentSelection.start(population));
            population.resetChildren();  // delete list of child individuals

            // 2) main.Recombination of parents to get new child generation:
            population.setChildren(recombination.createChildren(population.getParentCouples()));

            // 3) environment selection:
            population.setIndividuals(environmentSelection.start(population));

            // 4) mutation of survived individuals:
            population.setIndividuals(mutation.start(population.getIndividuals()));

            currentCycle++;
        }

        // Find fittest individual:
        System.out.println();

        System.out.println("Result of Fitnessfunction " + FitnessFunction.calculateFitnessValue_1(1.0, 1.0, 1.0, 1.0));

    }

    private static Population createPopulationRandom(int countIndividuals,
                                                     int countGenes, int
                                                             minAllele,
                                                     int maxAllele) {
        Population population = new Population();
        try {
            population = Population.createPopulation(countIndividuals,
                    countGenes,
                    minAllele, maxAllele);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return population;
    }



//    private static main.Population createPopulationFix() {
//
//    }
}
