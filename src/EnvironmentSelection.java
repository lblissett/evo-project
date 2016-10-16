import java.util.ArrayList;
import java.util.List;

/**
 * Created by geopras on 16.10.16.
 */
public class EnvironmentSelection {

    private FitnessFunction fitnessFunction;
    private int countSelectedIndividuals;

    /**
     * Returns a new object for selecting individuals from a given population
     *
     * @param fitnessFunction          FitnessFunction object for calculation the fitness value of each individual
     * @param countSelectedIndividuals Count of individuals to select
     */
    public EnvironmentSelection(FitnessFunction fitnessFunction, int countSelectedIndividuals) {
        this.fitnessFunction = fitnessFunction;
        this.countSelectedIndividuals = countSelectedIndividuals;
    }

    /**
     * Starts environment selection to kill individuals after certain rules
     * @param population
     * @return List of individuals
     */
    public List<Individual> start(Population population) {
        List<Individual> survivedIndividuals = new ArrayList<>();

        return survivedIndividuals;
    }
}
