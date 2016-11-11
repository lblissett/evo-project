package main;

import main.enums.Encoding;
import main.enums.RecombinationTypeBinary;
import main.enums.RecombinationTypeReal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geopras on 16.10.16.
 */
public class Recombination {

    private Encoding encoding;
    private RecombinationTypeReal recombinationTypeReal;
    private RecombinationTypeBinary recombinationTypeBinary;
    private int numberPreceedingDigits;
    private int lengthMantissa;

    /**
     * Creates a new recombination object for building a new child generation from parent generation
     */
    public Recombination(Encoding encoding, RecombinationTypeReal
            recombinationTypeReal, RecombinationTypeBinary
            recombinationTypeBinary, int numberPreceedingDigits, int
            lengthMantissa) {
        this.encoding = encoding;
        this.recombinationTypeReal = recombinationTypeReal;
        this.recombinationTypeBinary = recombinationTypeBinary;
        this.numberPreceedingDigits = numberPreceedingDigits;
        this.lengthMantissa = lengthMantissa;
    }

    /**
     * Returns a new child generation after recombining within parent generation
     * @param parentCouples List of parent couples
     * @return List of child individuals
     */
    public List<List<Double>> start(List<List<List<Double>>> parentCouples) {

        switch (this.encoding) {
            case REAL:
                switch (this.recombinationTypeReal) {
                    case INTERMEDIUM: return this.intermediateMethod(parentCouples);
                    case ARITHMETIC: return this.arithmeticMethod(parentCouples);
                }
            case BINARY:
                switch (this.recombinationTypeBinary) {
                    case ONEPOINT: return this.onePointMethod(parentCouples);
                    case TWOPOINT: return this.twoPointMethod(parentCouples);
                }
        }
        return intermediateMethod(parentCouples);  // Default
    }

    private List<List<Double>> intermediateMethod(List<List<List<Double>>> parentCouples) {

        List<List<Double>> children = new ArrayList<>();

        for (List<List<Double>> parents : parentCouples) {

            List<Double> parentA = parents.get(0);
            List<Double> parentB = parents.get(1);
            List<Double> childGenome = new ArrayList<>();

            for (int j = 0; j < parentA.size(); j++) {
                Double alleleA = parentA.get(j);
                Double alleleB = parentB.get(j);
                // Der Mittelwert zwischen den beiden zugehoerigen
                // Eltern-Allelen bildet das neue Kind-Allel und damit Kind-Gen:
                Double childGene = new Double((alleleA + alleleB) / 2);
                childGenome.add(childGene);
            }
            children.add(new ArrayList<>(childGenome));
        }

        return children;
    }

    private List<List<Double>> arithmeticMethod(List<List<List<Double>>>
                                                      parentCouples) {
        return new ArrayList<>();
    }

    private List<List<Double>> onePointMethod(List<List<List<Double>>> parentCouples) {

        List<List<Double>> children = new ArrayList<>();

        for (List<List<Double>> parents : parentCouples) {

            List<Double> parentA = parents.get(0);
            List<String> parentBinaryA = null;
            try {
                parentBinaryA = SConverter
                        .doubleListToBinaryStringList(parentA, this
                                .numberPreceedingDigits, this.lengthMantissa);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            List<Double> parentB = parents.get(1);
            List<String> parentBinaryB = null;
            try {
                parentBinaryB = SConverter
                        .doubleListToBinaryStringList(parentB, this
                                .numberPreceedingDigits, this.lengthMantissa);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            List<Double> childGenome = new ArrayList<>();

            for (int i = 0; i < parentA.size(); i++) {
                String alleleA = parentBinaryA.get(i);
                String alleleB = parentBinaryB.get(i);

                // Bestimme Zufallsindex, bis zu dem rekombiniert werden soll
                int randomIndex = SRandom.getRandomIndex(alleleA.length());

                String childAllele = alleleA.substring(0, randomIndex) +
                        alleleB.substring(randomIndex);
                childGenome.add(SConverter.binaryStringToDouble(childAllele,
                        this.numberPreceedingDigits, this.lengthMantissa));
            }
            children.add(childGenome);
        }

        return children;
    }

    private List<List<Double>> twoPointMethod(List<List<List<Double>>> parentCouples) {
        List<List<Double>> children = new ArrayList<>();

        return children;
    }
}
