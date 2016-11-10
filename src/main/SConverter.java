package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geopras on 10.11.16.
 */
public class SConverter {

    public static String doubleToBinaryString(Double value, int
            numberPreceedingDigits, int lengthMantissa) throws Exception {

        String valueString = value.toString();
        String[] splitValue = valueString.split("\\.");
        String beforeComma = splitValue[0];
        String afterComma = splitValue[1];
        Integer numberBefore = Integer.parseInt(beforeComma);
        Integer numberAfter = Integer.parseInt(afterComma);

        // Binärcode für Vorkommazahl
        // Prüfe, ob Dezimalzahl außerhalb des angegebenen Binärwertebereichs liegt:
        if (numberBefore >= Math.pow(2, numberPreceedingDigits)) {
            throw new Exception("Die Dezimalzahl ist größer als mit der " +
                    "Anzahl an Vorkommastellen abgedeckt werden kann!");
        }

        String binaryBefore = Integer.toBinaryString(numberBefore);

        // Binärcode für Nachkommazahl:
        String numberAfterString = "0." + numberAfter.toString();
        Double result = Double.parseDouble(numberAfterString);

        List<Character> binaryAfter = new ArrayList<>();
        while ((result < 0.0 - Double.MIN_VALUE || result > 0.0 + Double
                .MIN_VALUE) && binaryAfter.size() < lengthMantissa) {

            result = result * 2.0;
            if (result < 1.0) {
                binaryAfter.add('0');
            } else {
                StringBuffer resultString = new StringBuffer(result.toString());
                resultString.setCharAt(0, '0');
                result = Double.parseDouble(resultString.toString());
                binaryAfter.add('1');
            }
        }

        // BinärString auffüllen mit Nullen, sodass vorgegebene Länge
        // eingehalten wird:
        StringBuffer resultBefore = new StringBuffer();
        for (int i = numberPreceedingDigits; i > 0; i--) {

        }


        return binaryBefore + binaryAfter;
    }
}
