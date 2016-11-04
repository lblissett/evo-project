import java.util.ArrayList;
import java.util.List;

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
    public List<Individual[]> start(Population population) {
        List<Individual[]> selectedPopulation = new ArrayList<>();

        return selectedPopulation;
    }
}
