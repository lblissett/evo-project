import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by geopras on 16.10.16.
 */
public class ParentSelection {

    private int countSelectedIndividuals;
    private Double recombinationProbability;

    /**
     * Returns a new object for selecting individuals from a given population
     * @param countSelectedIndividuals Count of individuals to select
     * @param recombinationProbability {Double} probability of being a
     *                                 parent couple (between 0-1)
     */
    public ParentSelection(int countSelectedIndividuals, Double recombinationProbability) {
        this.countSelectedIndividuals = countSelectedIndividuals;
        this.recombinationProbability = recombinationProbability;
    }

    /**
     * Selects population couples as new parents
     * @param population Population of parent and child individuals
     * @return List of arrays of individuals as parent couples
     */
    public List<List<Individual>> start(Population population) {
        return randomSelection(population);
    }

    private List<List<Individual>> randomSelection(Population population) {

        List<List<Individual>> selectedParents = new ArrayList<>();
        //All indiviudals in one list
        List<Individual> allIndividuals = population.getIndividuals();

        int counter = 0;

        while(counter < countSelectedIndividuals) {

            Individual parent1 = allIndividuals.get(SRandom.getRandomIndex(allIndividuals.size()));
            Individual parent2 = allIndividuals.get(SRandom.getRandomIndex(allIndividuals.size()));

            if (SRandom.getRandomProbability() < this.recombinationProbability) {
                List<Individual> parents= new ArrayList<>();
                parents.add(parent1);
                parents.add(parent2);
                selectedParents.add(parents);
                ++counter;
            }
        }

        return selectedParents;
    }

    private List<Individual[]> tournamentSelection(Population population) {
        return new ArrayList<>();
    }
}
