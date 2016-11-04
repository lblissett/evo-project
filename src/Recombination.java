import java.util.ArrayList;
import java.util.List;

/**
 * Created by geopras on 16.10.16.
 */
public class Recombination {

    /**
     * Creates a new recombination object for building a new child generation from parent generation
     */
    public Recombination() {

    }

    /**
     * Returns a new child generation after recombining within parent generation
     * @param parentCouples List of parent couples
     * @return List of child individuals
     */
    public List<Individual> createChildren(List<Individual[]> parentCouples) {
        return onePointMethod(parentCouples);
    }

    private List<Individual> onePointMethod(List<Individual[]> parentCouples) {
        List<Individual> children = new ArrayList<>();

        return children;
    }
}
