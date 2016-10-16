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

    public Population() {
        this.individuals = new ArrayList<>();
        this.parentCouples = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    /**
     * Creates a new list for parent generation with random genes
     * @param countIndividuals count of parent individuals in first generation
     * @param countGenes count of genes of one individual
     * @param minGeneValue minimum of allele valuation for creating one gene
     * @param maxGeneValue maximum of allele valuation for creating one gene
     */
    public void createIndividuals(int countIndividuals, int countGenes, Double minGeneValue, Double maxGeneValue) {

    }
}
