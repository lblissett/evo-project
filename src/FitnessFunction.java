/**
 * Created by geopras on 16.10.16.
 */
public class FitnessFunction {

    public static Double calculateFitnessValue_1(double x1, double x2, double x3, double x4) {

        double Fx = Math.pow((x1 + 10 * x2), 2) +
                    5 * Math.pow((x3 - x4), 2) +
                    Math.pow((x2 - 2 * x3), 4) +
                    10 * Math.pow((x1 - x4), 4);
        return Fx;
    }

    public static Double calculateFitnessValue_2() {
        return 0.0;
    }
}
