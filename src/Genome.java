import java.util.ArrayList;
import java.util.List;

/**
 * Created by geopras on 03.11.16.
 */
public class Genome {

    private List<Gene> genes;

    public List<Gene> getGenes() {
        return genes;
    }

    public void setGenes(List<Gene> genes) {
        this.genes = genes;
    }

    /**
     * Create new genome object with given list of genes
     * @param genes {List<Gene>} list of genes
     */
    public Genome(List<Gene> genes) {
        this.genes = genes;
    }

    public Genome() {
        this.genes = new ArrayList<>();
    }

    /**
     * Creates a random genome object with given count of genes and valuation
     * for creating random allele
     * @param countGenes
     * @param minAllele
     * @param maxAllele
     * @return {Genome} genome object
     * @throws Exception if minAllele >= maxAllele
     */
    public static Genome createRandom(int countGenes, int minAllele, int
            maxAllele) throws Exception {

        List<Gene> geneList = new ArrayList<>();

        for (int i = 0; i < countGenes; i++) {
            try {

            } catch (Exception ex) {
                throw new Exception(ex);
            }
            geneList.add(Gene.createRandom(minAllele, maxAllele));
        }

        return new Genome(geneList);
    }
}
