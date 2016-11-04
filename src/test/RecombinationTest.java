package test;

import main.Gene;
import main.Individual;
import main.Recombination;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by geopras on 04.11.16.
 */
public class RecombinationTest {

    private static final double DELTA = 1e-15;

    @Test
    public void intermediateMethod_should_return_correct_child() {

        Individual parentA = new Individual(new ArrayList<>(Arrays.asList(new
                Gene(3.0))));
        Individual parentB = new Individual(new ArrayList<>(Arrays.asList(new
                Gene(6.0))));
        List<Individual> parents = new ArrayList<>(Arrays.asList(parentA, parentB));
        List<List<Individual>> parentsList = new ArrayList<>(Arrays.asList
                (parents));
        Recombination recombination = new Recombination();

        List<Individual> children = recombination.createChildren(parentsList);
        Assert.assertEquals(children.get(0).getGenome().get(0).getAllele(),
                4.5, DELTA);


    }
}
