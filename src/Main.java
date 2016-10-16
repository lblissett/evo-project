import java.util.List;

/**
 * Created by geopras on 14.10.16.
 */
public class Main {
    public static void main(String[] args) {

        // Initialization parameter:
        int countEvolutionCycles = 1000;  // count of evolution cycles
        int countParentCouples = 10;  // count of parent couples who create a new child
        int countGenes = 4;  // genes count of one individual
        Double minGeneValue = -1000.0;  // min value for creating an allele of a gene
        Double maxGeneValue = 1000.0;  // max value for creating an allele of a gene
        Double recombinationProbability = 0.7; // probability if two parents create a new child
        Double mutationProbability = 0.01; // probability if a gene of an individual mutates
        int countSelectedIndividuals = countParentCouples;  // count of selected population individuals

        // Workflow objects for evolution process:
        Population population = new Population();
        population.createIndividuals(countParentCouples, countGenes, minGeneValue, maxGeneValue);  // create new parent generation
        FitnessFunction fitnessFunction = new FitnessFunction();
        Recombination recombination = new Recombination(recombinationProbability);
        EnvironmentSelection environmentSelection = new EnvironmentSelection(fitnessFunction, countSelectedIndividuals);
        Mutation mutation = new Mutation(mutationProbability);
        ParentSelection parentSelection = new ParentSelection(fitnessFunction, countSelectedIndividuals);

        // Begin evolution cycles:
        int currentCycle = 0;
        while (currentCycle < countEvolutionCycles) {
            // 1) Define parents
            population.setParentCouples(parentSelection.start(population));
            population.resetChildren();  // delete list of child individuals

            // 2) Recombination of parents to get new child generation:
            population.setChildren(recombination.createChildren(population.getParentCouples()));

            // 3) environment selection:
            population.setIndividuals(environmentSelection.start(population));

            // 4) mutation of survived individuals:
            population.setIndividuals(mutation.start(population.getIndividuals()));

            currentCycle++;
        }

        // Find fittest individual:
        System.out.println();
    }

    public static Individual FindFittestIndividual(List<Individual> individuals) {
        return new Individual();
    }
}
