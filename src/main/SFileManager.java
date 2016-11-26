package main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by geopras on 11.11.16.
 */
public class SFileManager {

    public static void savePopulation(List<List<Double>> individuals, String
            savePath) {

        Path path = new File(savePath).toPath();

        File file = new File(path.toString());
        if (!Files.exists(path.getParent())) {
            try {
                Files.createDirectory(path.getParent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!file.exists()) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            for (List<Double> individual : individuals) {
                for (Double gene : individual) {
                    bw.write(gene.toString() + ";");
                }
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Population readPopulation(String savePath) {

        List<List<Double>> individuals = new ArrayList<>();

        File file = new File(savePath);

        if (!file.exists()) {
            System.out.println(String.format("Die Datei %s existiert nicht!", file
                    .getPath()));
        }

        try {
            FileReader fr = new FileReader(file.getPath());
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {

                List<Double> individual = new ArrayList<>();
                String[] splitLine = line.split(";");

                for (int i = 0; i < splitLine.length; i++) {

                    individual.add(Double.parseDouble(splitLine[i]));
                }
                individuals.add(individual);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Population(individuals);
    }

    public static void saveResults(List<List<String>> results, String
            savePath) {

        Path path = new File(savePath).toPath();

        File file = new File(path.toString());
        if (!Files.exists(path.getParent())) {
            try {
                Files.createDirectory(path.getParent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!file.exists()) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Zyklus, bester Fitnesswert Real, Binaer1P, Binaer2P, worstReal, worstBinaer1P, worstBinaer2P");
            bw.newLine();
            for (int i = 0; i < results.get(0).size(); i++) {

                bw.write(i + "," + results.get(0).get(i) + "," + results.get(1).get(i)  +
                        "," + results.get(2).get(i) + "," + results.get(3).get(i)
                                + "," + results.get(4).get(i) + "," +
                        results.get(5).get(i));
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
