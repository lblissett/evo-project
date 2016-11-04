/**
 * Created by geopras on 16.10.16.
 */
public class Individual {

    private Genome genome;

    public Genome getGenome() {
        return genome;
    }

    public void setGenome(Genome genome) {
        this.genome = genome;
    }

    public Individual(Genome genome) {
        this.genome = genome;
    }

    public Individual() {
        this.genome = new Genome();
    }

    public static Individual createRandom(int countGenes, int minAllele, int
            maxAllele) throws
            Exception {
        return new Individual(Genome.createRandom(countGenes, minAllele,
                maxAllele));
    }

}
