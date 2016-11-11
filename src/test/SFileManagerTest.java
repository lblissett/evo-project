package test;

import main.Population;
import main.SFileManager;
import org.junit.Test;

/**
 * Created by geopras on 11.11.16.
 */
public class SFileManagerTest {

//    @Test
//    public void writePopulation_test() {
//
//        Population population = Population.createRandom(5, 5, -512, 511);
//        SFileManager.savePopulation(population.getParents());
//    }

    @Test
    public void readPopulation_test() {

        Population population = SFileManager.readPopulation("src/data/population.txt");
        System.out.println(population.getParents());
    }
}
