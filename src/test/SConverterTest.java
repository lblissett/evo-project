package test;

import main.SConverter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by geopras on 10.11.16.
 */
public class SConverterTest {

    @Test
    public void doubleToBinary_return_True_if_Binary_is_correct() {

        Double value = -1.75;
        String binaryString = "1000000000111000000";
        String testResult = "";
        try {
            testResult = SConverter.doubleToBinaryString(value, 10, 8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(binaryString, testResult);
    }

    @Test
    public void binaryToDouble_return_True_if_Double_is_correct() {

        Double value = -1.75;
        String binaryString = "1000000000111000000";
        Double testResult = 0.0;
        try {
            testResult = SConverter.binaryStringToDouble(binaryString, 10, 8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(value, testResult);
    }
}
