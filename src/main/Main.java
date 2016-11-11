package main;

import main.enums.Encoding;
import main.enums.RecombinationTypeBinary;
import main.enums.RecombinationTypeReal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geopras on 14.10.16.
 */
public class Main {

    public static void main(String[] args) {

        //region Initialization parameter

        // 1) allgemein:
        int countEvolutionCycles = 2000;  // maximale Anzahl Evolutionszyklen
        Double stopCriterion = 0.01;      // Abbruchkriterium

        // 2) Population:
        int startSizePopulation = 10;     // Anfangsgröße der Population
        int countGenes = 5;               // Anzahl Gene pro Individuum
        int minAllele = -512;             // Minimalwert Wertebereich
        int maxAllele = 511;              // Minimalwert Wertebereich
        Encoding encoding = Encoding.REAL;
        int numberPreceedingDigits = 10;  // Binärcode Anzahl Vorkommastellen
        int lengthMantissa = 8;           // Binärcode Anzahl Nachkommastellen

        // 3) Elternselektion:
        int countParentCouples = 10;      // Anzahl Elternpaare

        // 4) Rekombination:
        RecombinationTypeReal recombinationTypeReal = RecombinationTypeReal
                .INTERMEDIUM;
        RecombinationTypeBinary recombinationTypeBinary =
                RecombinationTypeBinary.ONEPOINT;
        Double recombinationProbability = 0.7; // Wahrsch. Rekombination

        // 5) Mutation:
        Double mutationProbability = 0.5; // Wahrsch. Mutation

        // 6) Umweltselektion:
        Integer startCountFittest = 10;   // Startanzahl determ. Umweltselektion
        Integer countRandomIndividuals = 3; // Anzahl zufällige Umweltselektion
        //endregion

        //region Initialisierung des Algorithmus

        Population population = Population.createRandom(startSizePopulation,
                countGenes, minAllele, maxAllele);
        ParentSelection parentSelection = new ParentSelection(countParentCouples, recombinationProbability);
        Recombination recombination = new Recombination(encoding,
                recombinationTypeReal, recombinationTypeBinary,
                numberPreceedingDigits, lengthMantissa);
        Mutation mutation = new Mutation(mutationProbability, encoding,
                minAllele, maxAllele);
        EnvironmentSelection environmentSelection = new EnvironmentSelection();
        //endregion

        //region Ablauf Evolutionszyklen

        int currentCycle = 0;
        Integer countFittest = startCountFittest;
        List<Double> fittestIndividual = new ArrayList<>();
        Double fitnessValue = 1.0;

        while (currentCycle < countEvolutionCycles && fitnessValue > stopCriterion) {

            // 1) Define parents
            population.setParentCouples(parentSelection.start(population));
            population.resetChildren();  // delete list of child individuals

            // 2) main.Recombination of parents to get new child generation:
            population.setChildren(recombination.start(population
                    .getParentCouples()));

            // 3) mutation of survived individuals:
            population.setParents(mutation.start(population.getParents()));

            // 4) environment selection:
            population.setParents(environmentSelection.start(population,
                    countFittest, countRandomIndividuals));

            fittestIndividual = environmentSelection.start
                    (population, 1, 0).get(0);
            fitnessValue = FitnessFunction
                    .calculateGriewank(fittestIndividual);
            countFittest++;
            currentCycle++;
        }
        //endregion

        //region Ausgabe

        System.out.println("Bestes Individuum: " + fittestIndividual);
        System.out.println("Fitnesswert: " + fitnessValue);
        //endregion
    }
}
