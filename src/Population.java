import java.util.ArrayList;
import java.util.List;

/**
 * Created by geopras on 16.10.16.
 */
public class Population {

    private List<Individual> individuals;
    private List<Individual[]> parentCouples;
    private List<Individual> children;

    public List<Individual> getIndividuals() {
        return this.individuals;
    }

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }

    public List<Individual[]> getParentCouples() {
        return this.parentCouples;
    }

    public void setParentCouples(List<Individual[]> couples) {
        this.parentCouples = couples;
    }

    public List<Individual> getChildren() {
        return this.children;
    }

    public void setChildren(List<Individual> children) {
        this.children = children;
    }

    public void resetChildren() {
        this.children = new ArrayList<>();
    }

    public Population(List<Individual> individuals) {
        this.individuals = individuals;
        this.parentCouples = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public Population() {
        this.individuals = new ArrayList<>();
        this.parentCouples = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public static Population createPopulation(int countIndividuals, int
            countGenes, int minAllele, int maxAllele) throws Exception {

        List<Individual> individuals = new ArrayList<>();

        for (int i=0; i < countIndividuals; i++) {
            try {
                individuals.add(Individual.createRandom(countGenes, minAllele,
                        maxAllele));
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        }
        return new Population(individuals);
    }


}
