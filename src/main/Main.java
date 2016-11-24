package main;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.*;

/**
 * Hauptprogramm Evolutionaerer Algorithmus
 */
public class Main {

    public static void main(String[] args) {

        //Log-Datei zur Aufzeichnung des Algorithmus-Verlaufs:
        String now = LocalDateTime.now().toString();
        now = now.replace(":", "").replace(".", "");
        Logger logger = initializeLogger(now);
        logger.setLevel(Level.OFF);

        //region Initialization parameter

        // 1) allgemein:
        logger.info("Initialisierungs-Parameter");
        int countEvolutionCycles = 1500;  // maximale Anzahl Evolutionszyklen
        logger.info("Anzahl der Evolutionsschritte: " + countEvolutionCycles);

        // 2) Population:
        int startSizePopulation = 10;     // Anfangsgröße der Population
        logger.info("Anfangsgroesse der Population: " + startSizePopulation);
        List<Integer> countGenes = new ArrayList<>(Arrays.asList(5, 20, 50));
        List<Boolean> isGrowingList = new ArrayList<>(Arrays.asList(false,
                true));
        //
        // Anzahl Gene pro
        // Individuum
        logger.info("Anzahl der Gene: " + countGenes);
        int minAllele = -512;             // Minimalwert Wertebereich
        logger.info("Kleinster Wert eines Gens: " + minAllele);
        int maxAllele = 511;              // Minimalwert Wertebereich
        logger.info("Groesster Wert eines Gens: " + maxAllele);
        int countPreceedingDigits = 10;  // Binärcode Anzahl Vorkommastellen
        logger.info("Anzahl Vorkommastellen bei der Binaerkodierung: " +
                countPreceedingDigits);
        int lengthMantissa = 8;           // Binärcode Anzahl Nachkommastellen
        logger.info("Mantissenlaenge bei der Binaerkodierung: " +
                lengthMantissa);

        // 3) Elternselektion:
        int startCountParentCouples = 10;      // Anzahl Elternpaare am Anfang
        logger.info("Anzahl der Elternpaare am Anfang: " +
                startCountParentCouples);

        // 4) Rekombination:
        Double recombinationProbability = 0.9; // Wahrsch. Rekombination
        logger.info("Rekombinationswahrscheinlichkeit: " +
                recombinationProbability);

        // 5) Mutation:
        Double mutationProbability = 0.1; // Wahrsch. Mutation
        logger.info("Mutationswahrscheinlichkeit: " +
                mutationProbability);
        Double fixMutationValue = 5.0;
        logger.info("Fixer Mutationswert (bei Reeller Kodierung): " +
                fixMutationValue);

        // 6) Umweltselektion:
        Integer startCountFittest = startCountParentCouples;   // Startanzahl determ.
        // Umweltselektion
        logger.info("Anzahl der auszuwaehlenden besten Individuen: " +
                startCountFittest);
        Integer countRandomIndividuals = 3; // Anzahl zufällige Umweltselektion
        logger.info("Anzahl der zufaellig auszuwaehlenden Individuen: " +
                countRandomIndividuals);
        //endregion

        //region Initialisierung des Algorithmus
        Populations populations;

        for (int i = 0; i < isGrowingList.size(); i++) {

            boolean isGrowing = isGrowingList.get(i);
            System.out.println("Is Growing = " + isGrowing);

            for (int j = 0; j < countGenes.size(); j++) {

                int n = countGenes.get(j);
                System.out.println("n = " + n);

                String populationSavePath = "src/data/population_n" + n +".txt";

                String resultsSavePath = "src/data/results_n" + n +
                        "_isGrowing_" + isGrowing + ".csv";
                logger.info("Zugehoerige result-Datei: " + resultsSavePath);
                logger.info("Initialisiere Population...");

                File populationFile = new File(populationSavePath);
                if (!populationFile.exists()) {
                    populations = Populations.createRandom(startSizePopulation,
                            n, minAllele, maxAllele);
                    SFileManager.savePopulation(populations.getReal().getParents(),
                            populationSavePath);
                } else {
                    populations = new Populations(SFileManager.readPopulation
                            (populationSavePath));
                }

                logger.info("Startpopulation:");
                logger.info(populations.getReal().toString());

                ParentSelection parentSelection = new ParentSelection(recombinationProbability);
                Recombination recombination = new Recombination(countPreceedingDigits, lengthMantissa);
                Mutation mutation = new Mutation(mutationProbability, fixMutationValue,
                        minAllele, maxAllele, countPreceedingDigits, lengthMantissa);
                EnvironmentSelection environmentSelection = new EnvironmentSelection();
                //endregion

                //region Ablauf Evolutionszyklen

                Integer currentCycle = 0;
                Integer countFittest = startCountFittest;
                Integer countParentCouples = startCountParentCouples;

                //results
                // Datenstruktur: Zyklusnummer (1. Spalte), bester
                // Fitnesswert Real (2. Spalte), B1P (3.Spalte), B2P (4.Spalte)
                List<List<String>> results = new ArrayList<>();

                List<Double> fittestReal = new ArrayList<>();
                Double fitnessValueReal = -1.0;
                List<Double> worstReal = new ArrayList<>();
                Double worstValueReal = -1.0;
                List<Double> fittestBinary1P = new ArrayList<>();
                Double fitnessValueBinary1P = -1.0;
                List<Double> worstBinary1P = new ArrayList<>();
                Double worstValueBinary1P = -1.0;
                List<Double> fittestBinary2P = new ArrayList<>();
                Double fitnessValueBinary2P = -1.0;
                List<Double> worstBinary2P = new ArrayList<>();
                Double worstValueBinary2P = -1.0;
                Double bestFitnessValueReal = (double) maxAllele;
                List<Double> bestIndividualReal = new ArrayList<>();
                Double bestFitnessValueBinary1P = (double) maxAllele;
                List<Double> bestIndividualBinary1P = new ArrayList<>();
                Double bestFitnessValueBinary2P = (double) maxAllele;
                List<Double> bestIndividualBinary2P = new ArrayList<>();

                logger.info("Start Evolutionszyklen");

                while (currentCycle < countEvolutionCycles) {

                    System.out.println("Aktueller Schritt: " + currentCycle);

                    logger.info("");
                    logger.info("###################### Neuer Zyklus " +
                            "######################");
                    logger.info("Aktuelle Generation: " + currentCycle);
                    logger.info("Anzahl der Individuen: " + populations.getReal()
                            .getParents().size());
                    logger.info("");

                    // 1) Define parents
                    logger.info("Start Elternselektion");
                    populations = parentSelection.start(populations, countParentCouples);
                    logger.info("Ausgewaehlte Eltern der Population mit Reeller " +
                            "Kodierung:");
                    logger.info(populations.getReal().getParentCouples().toString());
                    logger.info("");
                    logger.info("Ausgewaehlte Eltern der Population mit Binärer " +
                            "Kodierung (1P):");
                    logger.info(populations.getBinaryOnePoint().getParentCouples().toString());
                    logger.info("");
                    logger.info("Ausgewaehlte Eltern der Population mit Binärer " +
                            "Kodierung (2P):");
                    logger.info(populations.getBinaryTwoPoint().getParentCouples().toString());
                    logger.info("");

                    // 2) Recombination of parents to get new child generation:
                    logger.info("Start Rekombination");
                    populations = recombination.start(populations);
                    logger.info("Entstandene Kinder der Population mit Reeller " +
                            "Kodierung:");
                    logger.info(populations.getReal().getChildren().toString());
                    logger.info("");
                    logger.info("Entstandene Kinder der Population mit Binärer " +
                            "Kodierung (1P):");
                    logger.info(populations.getBinaryOnePoint().getChildren().toString());
                    logger.info("");
                    logger.info("Entstandene Kinder der Population mit Binärer " +
                            "Kodierung (2P):");
                    logger.info(populations.getBinaryTwoPoint().getChildren().toString());
                    logger.info("");

                    // 3) mutation of survived individuals:
                    logger.info("Start Mutation");
                    populations = mutation.start(populations);
                    logger.info("Kinder der Population mit Reeller " +
                            "Kodierung:");
                    logger.info(populations.getReal().getChildren().toString());
                    logger.info("");
                    logger.info("Kinder der Population mit Binärer " +
                            "Kodierung (1P):");
                    logger.info(populations.getBinaryOnePoint().getChildren().toString());
                    logger.info("");
                    logger.info("Kinder der Population mit Binärer " +
                            "Kodierung (2P):");
                    logger.info(populations.getBinaryTwoPoint().getChildren().toString());
                    logger.info("");

                    // 4) environment selection:
                    logger.info("Start Umweltselektion");
                    populations = environmentSelection.start(populations,
                            countFittest, countRandomIndividuals);
                    logger.info("Neue Generation mit Reeller " +
                            "Kodierung:");
                    logger.info(populations.getReal().getParents().toString());
                    logger.info("");
                    logger.info("Neue Generation mit Binärer " +
                            "Kodierung (1P):");
                    logger.info(populations.getBinaryOnePoint().getParents().toString());
                    logger.info("");
                    logger.info("Neue Generation mit Binärer " +
                            "Kodierung (2P):");
                    logger.info(populations.getBinaryTwoPoint().getParents().toString());
                    logger.info("");

                    //region Ergebnisse speichern:
                    fittestReal = populations.getReal().getParents()
                            .get(0); // erstes Individuum = bestes
                    worstReal = populations.getReal().getParents()
                            .get(populations.getReal().getParents().size() - 1);

                    fitnessValueReal = FitnessFunction.calculateGriewank(fittestReal);
                    worstValueReal = FitnessFunction.calculateGriewank(worstReal);
                    if (fitnessValueReal < bestFitnessValueReal) {
                        bestFitnessValueReal = fitnessValueReal;
                        bestIndividualReal = fittestReal;
                    }
                    fittestBinary1P = populations.getBinaryOnePoint()
                            .getParents().get(0);
                    worstBinary1P = populations.getBinaryOnePoint()
                            .getParents().get(populations.getReal().getParents().size() - 1);
                    fitnessValueBinary1P = FitnessFunction.calculateGriewank(fittestBinary1P);
                    worstValueBinary1P = FitnessFunction.calculateGriewank(worstBinary1P);
                    if (fitnessValueBinary1P < bestFitnessValueBinary1P) {
                        bestFitnessValueBinary1P = fitnessValueBinary1P;
                        bestIndividualBinary1P = fittestBinary1P;
                    }
                    fittestBinary2P = populations.getBinaryTwoPoint()
                            .getParents().get(0);
                    worstBinary2P = populations.getBinaryTwoPoint()
                            .getParents().get(populations.getReal().getParents().size() - 1);
                    fitnessValueBinary2P = FitnessFunction.calculateGriewank(fittestBinary2P);
                    worstValueBinary2P = FitnessFunction.calculateGriewank(worstBinary2P);
                    if (fitnessValueBinary2P < bestFitnessValueBinary2P) {
                        bestFitnessValueBinary2P = fitnessValueBinary2P;
                        bestIndividualBinary2P = fittestBinary2P;
                    }

                    List<String> resultsCycle = new ArrayList<>();
                    resultsCycle.add(currentCycle.toString());
                    resultsCycle.add(fitnessValueReal.toString());
                    resultsCycle.add(fitnessValueBinary1P.toString());
                    resultsCycle.add(fitnessValueBinary2P.toString());
                    resultsCycle.add(worstValueReal.toString());
                    resultsCycle.add(worstValueBinary1P.toString());
                    resultsCycle.add(worstValueBinary2P.toString());
                    results.add(resultsCycle);
                    //endregion

                    //Vergroeßern der Population
                    if (isGrowing) {
                        countFittest++;
                        countParentCouples++;
                    }
                    currentCycle++;
                }
                //endregion

                //region Ausgabe und Speichern der Ergebnisse
                System.out.println("");
                System.out.println("Bestes Individuum Reelle Kodierung der " +
                        "letzten Generation:\n" + fittestReal);
                System.out.println("Fitnesswert: " + fitnessValueReal);
                System.out.println("");
                System.out.println("Bestes Individuum Binäre Kodierung (1P) der " +
                        "letzten Generation:\n" + fittestBinary1P);
                System.out.println("Fitnesswert: " + fitnessValueBinary1P);
                System.out.println("");
                System.out.println("Bestes Individuum Binäre Kodierung (2P) der " +
                        "letzten Generation:\n" + fittestBinary2P);
                System.out.println("Fitnesswert: " + fitnessValueBinary2P);
                System.out.println("");
                System.out.println("Bestes Individuum Reelle Kodierung aller " +
                        "Generationen:\n" + bestIndividualReal);
                System.out.println("Fitnesswert: " + bestFitnessValueReal);
                System.out.println("");
                System.out.println("Bestes Individuum Binäre Kodierung (1P) aller " +
                        "Generationen:\n" + bestIndividualBinary1P);
                System.out.println("Fitnesswert: " + bestFitnessValueBinary1P);
                System.out.println("");
                System.out.println("Bestes Individuum Binäre Kodierung (2P) aller " +
                        "Generationen:\n" + bestIndividualBinary2P);
                System.out.println("Fitnesswert: " + bestFitnessValueBinary2P);

                SFileManager.saveResults(results, resultsSavePath);
                //endregion
            }
        }
    }

    public static Logger initializeLogger(String now) {

        Logger logger = Logger.getLogger("MyLog");
        logger.setUseParentHandlers(false);
        String logFileName = "src/log/evoLog_" + now + ".txt";
        try {

            // This block configure the logger with handler and formatter
            FileHandler fh = new FileHandler(logFileName);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info("=============== Log-Datei zum Evolutionaeren " +
                    "Algorithmus ================");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return logger;
    }
}
