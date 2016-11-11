package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geopras on 10.11.16.
 */
public class SConverter {

    public static String doubleToBinaryString(Double value, int
            numberPreceedingDigits, int lengthMantissa) throws Exception {

        String endResult;
        if (value < 0) {  // Vorzeichenbit setzen
            endResult = "1";
            value = -value;
        } else {
            endResult = "0";
        }

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

        StringBuffer binaryAfter = new StringBuffer();
        while ((result < 0.0 - Double.MIN_VALUE || result > 0.0 + Double
                .MIN_VALUE) && binaryAfter.length() < lengthMantissa) {

            result = result * 2.0;
            if (result < 1.0) {
                binaryAfter.append('0');
            } else {
                StringBuffer resultString = new StringBuffer(result.toString());
                resultString.setCharAt(0, '0');
                result = Double.parseDouble(resultString.toString());
                binaryAfter.append('1');
            }
        }

        // BinärString auffüllen mit Nullen, sodass vorgegebene Länge
        // eingehalten wird:
        StringBuffer resultBefore = new StringBuffer();

        for (int i = 0; i < numberPreceedingDigits - binaryBefore.length(); i++) {
            resultBefore.append('0');
        }

        endResult += resultBefore.toString() + binaryBefore;
        endResult += binaryAfter.toString();

        StringBuffer resultAfter = new StringBuffer();
        for (int i = 0; i < lengthMantissa - binaryAfter.length(); i++) {
            resultAfter.append('0');
        }
        return endResult + resultAfter.toString();
    }

    public static Double binaryStringToDouble(String value, int
            numberPreceedingDigits, int lengthMantissa) {

        Character firstChar = value.charAt(0); // Vorzeichenbit
        String binaryBefore = value.substring(1, numberPreceedingDigits + 1);
        String binaryAfter = value.substring(numberPreceedingDigits + 1);

        Double result = 0.0;
        for (int i = 0; i < numberPreceedingDigits; i++) {
            if (i == 9) {
                int a = 1;
            }
            Character c = binaryBefore.charAt(i);
            String s = String.valueOf(c);
            result += Math.pow(2, numberPreceedingDigits - i - 1) *
                    Integer.parseInt(s);
        }
        for (int i = 0; i < lengthMantissa; i++) {
            result += Math.pow(2, -(i+1)) *
                    Integer.parseInt(String.valueOf(binaryAfter.charAt(i)));
        }
        if (firstChar.equals('1'))
        {
            result = -result;
        }
        return result;
    }

    public static List<String> doubleListToBinaryStringList(List<Double> values,
                                                            int numberPreceedingDigits,
                                                            int lengthMantissa) throws Exception {
        List<String> result = new ArrayList<>();

        for (Double value : values) {
            result.add(doubleToBinaryString(value, numberPreceedingDigits, lengthMantissa));
        }

        return result;
    }

    public static List<Double> binaryStringListToDoubleList(List<String>
                                                                     values,
                                                            int numberPreceedingDigits,
                                                            int lengthMantissa) throws Exception {
        List<Double> result = new ArrayList<>();

        for (String value : values) {
            result.add(binaryStringToDouble(value, numberPreceedingDigits, lengthMantissa));
        }

        return result;
    }
}
