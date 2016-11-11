package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse zur Definierung einer Population abhängig von der Kodierungsart
 */
public class Population {

    private List<List<Double>> parents;
    private List<List<List<Double>>> parentCouples;
    private List<List<Double>> children;

    public List<List<Double>> getParents() {
        return this.parents;
    }

    public void setParents(List<List<Double>> parents) {
        this.parents = parents;
    }

    public List<List<List<Double>>> getParentCouples() {
        return this.parentCouples;
    }

    public void setParentCouples(List<List<List<Double>>> couples) {
        this.parentCouples = couples;
    }

    public List<List<Double>> getChildren() {
        return this.children;
    }

    public void setChildren(List<List<Double>> children) {
        this.children = children;
    }

    public void resetChildren() {
        this.children = new ArrayList<>();
    }

    public Population(List<List<Double>> individuals) {
        this.parents = individuals;
        this.parentCouples = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public Population() {
        this.parents = new ArrayList<>();
        this.parentCouples = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public static Population createRandom(int countIndividuals, int
            countGenes, int minAllele, int maxAllele) {

        List<List<Double>> individuals = new ArrayList<>();

        for (int i=0; i < countIndividuals; i++) {
            try {
                individuals.add(SRandom.createRandomIntList(countGenes, minAllele,
                        maxAllele));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return new Population(individuals);
    }


}
