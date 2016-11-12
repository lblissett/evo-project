package test;

/**
 * Created by geopras on 04.11.16.
 */
public class RecombinationTest {

    private static final double DELTA = 1e-15;

//    @Test
//    public void intermediateMethod_should_return_correct_child() {
//
//        List<Double> parentA = new ArrayList<>(Arrays.asList(new
//                Double(3.0)));
//        List<Double> parentB = new ArrayList<>(Arrays.asList(new
//                Double(6.0)));
//        List<List<Double>> parents = new ArrayList<>(Arrays.asList(parentA, parentB));
//        List<List<List<Double>>> parentsList = new ArrayList<>(Arrays.asList
//                (parents));
//        Recombination recombination = new Recombination(Encoding.REAL,
//                RecombinationTypeReal.INTERMEDIUM, RecombinationTypeBinary
//                .ONEPOINT, 10, 8);
//
//        List<List<Double>> children = recombination.start(parentsList);
//        Assert.assertEquals(children.get(0).get(0),
//                4.5, DELTA);
//    }
//
//    @Test
//    public void onePointMethod_should_return_correct_child() {
//
//        List<Double> parentA = new ArrayList<>(Arrays.asList(new
//                Double(3.0)));
//        List<Double> parentB = new ArrayList<>(Arrays.asList(new
//                Double(6.0)));
//        List<List<Double>> parents = new ArrayList<>(Arrays.asList(parentA, parentB));
//        List<List<List<Double>>> parentsList = new ArrayList<>(Arrays.asList
//                (parents));
//        Recombination recombination = new Recombination(Encoding.BINARY,
//                RecombinationTypeReal.INTERMEDIUM, RecombinationTypeBinary
//                .ONEPOINT, 5, 2);
//
//        List<List<Double>> children = recombination.start(parentsList);
//        Assert.assertEquals(2.0, children.get(0).get(0), DELTA);
//    }
//
//    @Test
//    public void twoPointMethod_should_return_correct_child() {
//
//        List<Double> parentA = new ArrayList<>(Arrays.asList(new
//                Double(3.0)));
//        List<Double> parentB = new ArrayList<>(Arrays.asList(new
//                Double(6.0)));
//        List<List<Double>> parents = new ArrayList<>(Arrays.asList(parentA, parentB));
//        List<List<List<Double>>> parentsList = new ArrayList<>(Arrays.asList
//                (parents));
//        Recombination recombination = new Recombination(Encoding.BINARY,
//                RecombinationTypeReal.INTERMEDIUM, RecombinationTypeBinary
//                .TWOPOINT, 5, 2);
//
//        List<List<Double>> children = recombination.start(parentsList);
//        Assert.assertEquals(2.0, children.get(0).get(0), DELTA);
//    }
}
