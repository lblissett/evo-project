package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse zur Definierung einer Population abhängig von der Kodierungsart
 */
public class Population {

    private List<List<Double>> parents;
    private List<List<Double>> children;
    private List<List<List<Double>>> parentCouples;

    public List<List<Double>> getParents() {
        return parents;
    }

    public void setParents(List<List<Double>> parents) {
        this.parents = parents;
    }

    public List<List<Double>> getChildren() {
        return children;
    }

    public void setChildren(List<List<Double>> children) {
        this.children = children;
    }

    public List<List<List<Double>>> getParentCouples() {
        return parentCouples;
    }

    public void setParentCouples(List<List<List<Double>>> parentCouples) {
        this.parentCouples = parentCouples;
    }

    public void resetParents() {
        this.parents = new ArrayList<>();
    }

    public void resetChildren() {
        this.children = new ArrayList<>();
    }

    public void resetParentCouples() {
        this.parentCouples = new ArrayList<>();
    }

    public void addParentCouple(List<List<Double>> parentCouple) {
        this.parentCouples.add(parentCouple);
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
