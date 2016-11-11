package main;

import main.enums.Encoding;
import main.enums.RecombinationTypeBinary;
import main.enums.RecombinationTypeReal;

import java.io.File;
import java.util.*;

/**
 * Created by geopras on 14.10.16.
 */
public class Main {

    public static void main(String[] args) {

        //region Initialization parameter

        // 1) allgemein:
        int countEvolutionCycles = 2000;  // maximale Anzahl Evolutionszyklen
        Double stopCriterion = 0.01;      // Abbruchkriterium
        String populationSavePath = "src/data/population.txt";
        String resultsSavePath="src/data/result.csv";

        // 2) Population:
        int startSizePopulation = 10;     // Anfangsgröße der Population
        int countGenes = 5;               // Anzahl Gene pro Individuum
        int minAllele = -512;             // Minimalwert Wertebereich
        int maxAllele = 511;              // Minimalwert Wertebereich
        Encoding encoding = Encoding.BINARY;
        int countPreceedingDigits = 10;  // Binärcode Anzahl Vorkommastellen
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
        Double mutationProbability = 0.1; // Wahrsch. Mutation

        // 6) Umweltselektion:
        Integer startCountFittest = 10;   // Startanzahl determ. Umweltselektion
        Integer countRandomIndividuals = 3; // Anzahl zufällige Umweltselektion
        //endregion

        //region Initialisierung des Algorithmus
        Population population;

        File populationFile = new File(populationSavePath);
        if (!populationFile.exists()) {
            population = Population.createRandom(startSizePopulation,
                    countGenes, minAllele, maxAllele);
            SFileManager.savePopulation(population.getParents(), populationSavePath);
        } else {
            population = SFileManager.readPopulation(populationSavePath);
        }

        ParentSelection parentSelection = new ParentSelection(countParentCouples, recombinationProbability);
        Recombination recombination = new Recombination(encoding,
                recombinationTypeReal, recombinationTypeBinary,
                countPreceedingDigits, lengthMantissa);
        Mutation mutation = new Mutation(mutationProbability, encoding,
                minAllele, maxAllele, countPreceedingDigits, lengthMantissa);
        EnvironmentSelection environmentSelection = new EnvironmentSelection();
        //endregion

        //region Ablauf Evolutionszyklen

        int currentCycle = 0;
        Integer countFittest = startCountFittest;
        List<Double> fittestIndividual = new ArrayList<>();
        Double fitnessValue = 1.0;

        //result list
        Map<String, String> results = new HashMap<>();

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

            String currentCycleString = ((Integer)currentCycle).toString();
            String fitnessValueString = fitnessValue.toString();

            results.put(currentCycleString, fitnessValueString);


        }
        //endregion

        //region Ausgabe

        System.out.println("Bestes Individuum: " + fittestIndividual);
        System.out.println("Fitnesswert: " + fitnessValue);

        File resultsFile = new File(resultsSavePath);
        SFileManager.saveResults(results, resultsSavePath);
        //endregion
    }
}
