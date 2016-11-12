package main;

import java.util.List;

/**
 * Created by geopras on 11.11.16.
 */
public class Populations {

    private Population real;
    private Population binaryOnePoint;
    private Population binaryTwoPoint;

    public Population getReal() {
        return real;
    }

    public void setReal(Population real) {
        this.real = real;
    }

    public Population getBinaryOnePoint() {
        return binaryOnePoint;
    }

    public void setBinaryOnePoint(Population binaryOnePoint) {
        this.binaryOnePoint = binaryOnePoint;
    }

    public Population getBinaryTwoPoint() {
        return binaryTwoPoint;
    }

    public void setBinaryTwoPoint(Population binaryTwoPoint) {
        this.binaryTwoPoint = binaryTwoPoint;
    }

    public void resetChildren() {
        this.real.resetChildren();
        this.binaryOnePoint.resetChildren();
        this.binaryTwoPoint.resetChildren();
    }

    public void resetParents() {
        this.real.resetParents();
        this.binaryOnePoint.resetParents();
        this.binaryTwoPoint.resetParents();
    }

    public void resetParentCouples() {
        this.real.resetParentCouples();
        this.binaryOnePoint.resetParentCouples();
        this.binaryTwoPoint.resetParentCouples();
    }

    public void addParentCoupleReal(List<List<Double>> parentCouple) {
        Population real = this.getReal();
        real.addParentCouple(parentCouple);
        this.setReal(real);
    }

    public void addParentCoupleBinaryOnePoint(List<List<Double>> parentCouple) {
        Population onePoint = this.getBinaryOnePoint();
        onePoint.addParentCouple(parentCouple);
        this.setBinaryOnePoint(onePoint);
    }

    public void addParentCoupleBinaryTwoPoint(List<List<Double>> parentCouple) {
        Population twoPoint = this.getBinaryTwoPoint();
        twoPoint.addParentCouple(parentCouple);
        this.setBinaryTwoPoint(twoPoint);
    }

    public Populations(Population startPopulation) {
        this.real = new Population(startPopulation.getParents());
        this.binaryOnePoint = new Population(startPopulation.getParents());
        this.binaryTwoPoint = new Population(startPopulation.getParents());
    }

    public static Populations createRandom(int countIndividuals, int
            countGenes, int minAllele, int maxAllele) {

        Population randomPopulation = Population.createRandom
                (countIndividuals, countGenes, minAllele, maxAllele);
        return new Populations(randomPopulation);
    }


}
