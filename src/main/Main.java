package main;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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

        //Kurzversion Parameter:
        // n = Anzahl Gene
        // ps = Startpopulationsgröße
        // rp = Rekombinationswahrscheinlichkeit
        // mp = Mutationswahrscheinlichkeit
        // crI = Anzahl zufällig selektierter Individuen durch
        // Umwelt
        // cpc = Anzahl der selektierten Elternpaare

        int ps;
        double rp;
        double mp;
        int crI;
        int cpc;

        ps = 100;
        rp = 0.9;
        mp = 0.1;
        crI = 0;
        cpc = 10;
        List<Boolean> idGrowingListSmart = new ArrayList<>(Arrays.asList(false, true));
        int countTrials = 100;


        // 1) allgemein:
        logger.info("Initialisierungs-Parameter");
        int countEvolutionCycles = 500;  // maximale Anzahl Evolutionszyklen
        logger.info("Anzahl der Evolutionsschritte: " + countEvolutionCycles);

        // 2) Population:
        int startSizePopulation = ps;     // Anfangsgröße der Population
        logger.info("Anfangsgroesse der Population: " + startSizePopulation);
        List<Integer> countGenes = new ArrayList<>(Arrays.asList(5, 20,
                50));
        List<Boolean> isGrowingList = idGrowingListSmart;
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
        int startCountParentCouples = cpc;      // Anzahl Elternpaare am Anfang
        logger.info("Anzahl der Elternpaare am Anfang: " +
                startCountParentCouples);

        // 4) Rekombination:
        Double recombinationProbability = rp; // Wahrsch. Rekombination
        logger.info("Rekombinationswahrscheinlichkeit: " +
                recombinationProbability);

        // 5) Mutation:
        Double mutationProbability = mp; // Wahrsch. Mutation
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
        Integer countRandomIndividuals = crI; // Anzahl zufällige
        // Umweltselektion
        logger.info("Anzahl der zufaellig auszuwaehlenden Individuen: " +
                countRandomIndividuals);
        //endregion

        //region Initialisierung des Algorithmus
        Populations populations;

        for (int i = 0; i < countGenes.size(); i++) {

            int n = countGenes.get(i);
//            System.out.println("n = " + n);

            String populationSavePath = "src/data/population_n" + n + ".txt";

            for (int j = 0; j < isGrowingList.size(); j++) {

                boolean isGrowing = isGrowingList.get(j);
//                System.out.println("Is Growing = " + isGrowing);

                // Datenlisten:
                Map<Integer, List<Double>> realBest = new TreeMap<>();
                Map<Integer, List<Double>> realWorst = new TreeMap<>();
                Map<Integer, List<Double>> binary1PBest = new TreeMap<>();
                Map<Integer, List<Double>> binary1PWorst = new TreeMap<>();
                Map<Integer, List<Double>> binary2PBest = new TreeMap<>();
                Map<Integer, List<Double>> binary2PWorst = new TreeMap<>();

                for (int trial = 0; trial < countTrials; trial++) {

                    System.out.println("n: " + n + " | isGrowing: " +
                            isGrowing +
                            " | trial: " + trial);
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

                        if (realBest.get(currentCycle) == null) {
                            realBest.put(currentCycle, new ArrayList<>());
                            realWorst.put(currentCycle, new ArrayList<>());
                            binary1PBest.put(currentCycle, new ArrayList<>());
                            binary1PWorst.put(currentCycle, new ArrayList<>());
                            binary2PBest.put(currentCycle, new ArrayList<>());
                            binary2PWorst.put(currentCycle, new ArrayList<>());
                        }


                        //System.out.println("Aktueller Schritt: " +
                                //currentCycle);

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

                        List<Double> oldRealBest = realBest.get(currentCycle);
                        List<Double> oldRealWorst = realWorst.get(currentCycle);
                        List<Double> oldB1PBest = binary1PBest.get(currentCycle);
                        List<Double> oldB1PWorst = binary1PWorst.get(currentCycle);
                        List<Double> oldB2PBest = binary2PBest.get(currentCycle);
                        List<Double> oldB2PWorst = binary2PWorst.get(currentCycle);

                        List<Double> newRealBest = cloneList(oldRealBest);
                        newRealBest.add(fitnessValueReal);
                        List<Double> newRealWorst = cloneList(oldRealWorst);
                        newRealWorst.add(worstValueReal);
                        List<Double> newB1PBest = cloneList(oldB1PBest);
                        newB1PBest.add(fitnessValueBinary1P);
                        List<Double> newB1PWorst = cloneList(oldB1PWorst);
                        newB1PWorst.add(worstValueBinary1P);
                        List<Double> newB2PBest = cloneList(oldB2PBest);
                        newB2PBest.add(fitnessValueBinary2P);
                        List<Double> newB2PWorst = cloneList(oldB2PWorst);
                        newB2PWorst.add(worstValueBinary2P);

                        realBest.replace(currentCycle, oldRealBest, newRealBest);
                        realWorst.replace(currentCycle, oldRealWorst, newRealWorst);
                        binary1PBest.replace(currentCycle, oldB1PBest, newB1PBest);
                        binary1PWorst.replace(currentCycle, oldB1PWorst, newB1PWorst);
                        binary2PBest.replace(currentCycle, oldB2PBest, newB2PBest);
                        binary2PWorst.replace(currentCycle, oldB2PWorst, newB2PWorst);

                        //endregion

                        //Vergroeßern der Population
                        if (isGrowing) {
                            countFittest++;
                            countParentCouples++;
                        }
                        currentCycle++;
                    }

                    //endregion
                }

                // Ergebnis ermitteln:
                // Mittelwerte bestimmen aus allen Versuchen:

                // Datenstruktur: Zyklusnummer (1. Spalte), bester
                // Fitnesswert Real (2. Spalte), B1P (3.Spalte), B2P (4.Spalte)
                List<List<String>> results = new ArrayList<>();
                List<String> realBest_mean = new ArrayList<>();
                List<String> realWorst_mean = new ArrayList<>();
                List<String> b1PBest_mean = new ArrayList<>();
                List<String> b1PWorst_mean = new ArrayList<>();
                List<String> b2PBest_mean = new ArrayList<>();
                List<String> b2PWorst_mean = new ArrayList<>();
                for (int cycle = 0; cycle < realBest.size(); cycle++) {

                    realBest_mean.add(calculateAverage(realBest.get(cycle)).toString());
                    realWorst_mean.add(calculateAverage(realWorst.get(cycle))
                            .toString());
                    b1PBest_mean.add(calculateAverage(binary1PBest.get(cycle)).toString
                            ());
                    b1PWorst_mean.add(calculateAverage(binary1PWorst.get(cycle)).toString
                            ());
                    b2PBest_mean.add(calculateAverage(binary2PBest.get(cycle)).toString
                            ());
                    b2PWorst_mean.add(calculateAverage(binary2PWorst.get(cycle)).toString
                            ());
                }
                results.add(realBest_mean);
                results.add(b1PBest_mean);
                results.add(b2PBest_mean);
                results.add(realWorst_mean);
                results.add(b1PWorst_mean);
                results.add(b2PWorst_mean);

                String resultsSavePath = "src/data/results/mean"+ countTrials
                + "_n" + n + "_isGrowing_" + isGrowing + ".csv";
                SFileManager.saveResults(results, resultsSavePath);

                //region Ausgabe und Speichern der Ergebnisse
                System.out.println("n = " + n + " | isGrowing = " + isGrowing);
                System.out.println("");
//                System.out.println("isGrowing = " + isGrowing);
//                System.out.println("Bestes Individuum Reelle Kodierung der " +
//                        "letzten Generation:\n" + fittestReal);
                System.out.println("Bester Fitnesswert (Reell): " +
                                Collections.min(realBest_mean));
                System.out.println("Schlechtester Fitnesswert (Reell): " +
                        Collections.max(realWorst_mean));

                System.out.println("");
//                System.out.println("Bestes Individuum Binäre Kodierung (1P) der " +
//                        "letzten Generation:\n" + fittestBinary1P);
                System.out.println("Bester Fitnesswert (B1P): " +
                        Collections.min(b1PBest_mean));
                System.out.println("Schlechtester Fitnesswert (B1P): " +
                        Collections.max(b1PWorst_mean));
                System.out.println("");
//                System.out.println("Bestes Individuum Binäre Kodierung (2P) der " +
//                        "letzten Generation:\n" + fittestBinary2P);
                System.out.println("Bester Fitnesswert (B2P): " +
                        Collections.min(b2PBest_mean));
                System.out.println("Schlechtester Fitnesswert (B2P): " +
                        Collections.min(b2PWorst_mean));
//                System.out.println("");
//                System.out.println("Bestes Individuum Reelle Kodierung aller " +
//                        "Generationen:\n" + bestIndividualReal);
//                System.out.println("Fitnesswert Reell: " +
//                        bestFitnessValueReal);
//                System.out.println("");
//                System.out.println("Bestes Individuum Binäre Kodierung (1P) aller " +
//                        "Generationen:\n" + bestIndividualBinary1P);
//                System.out.println("Fitnesswert B1P: " +
//                        bestFitnessValueBinary1P);
//                System.out.println("");
//                System.out.println("Bestes Individuum Binäre Kodierung (2P) aller " +
//                        "Generationen:\n" + bestIndividualBinary2P);
//                System.out.println("Fitnesswert B2P: " +
//                        bestFitnessValueBinary2P);
                //endregion
//        logger.info("Zugehoerige result-Datei: " + resultsSavePath);
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

    public static List<Double> cloneList(List<Double> list) {

        List<Double> clonedList = new ArrayList<>();
        for (Double value : list) {

            clonedList.add(new Double(value));
        }
        return clonedList;
    }

    public static Double calculateAverage(List<Double> values) {
        Double sum = 0.0;
        if(!values.isEmpty()) {
            for (Double mark : values) {
                sum += mark;
            }
            return sum / values.size();
        }
        return sum;
    }


}
