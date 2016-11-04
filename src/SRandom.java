import java.util.List;
import java.util.Random;

/**
 * Created by atari on 04.11.16.
 */
public class SRandom {

    public static int getRandomIndex(int sizeList) {

        Random random = new Random();
        return random.nextInt(sizeList);
    }

    public static double getRandomProbability() {

        Random random = new Random();
        double a = (double) random.nextInt(101);
        double b = a / 100;
        return b;
    }
}
