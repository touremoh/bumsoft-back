package io.bumsoft.helper;

public class BumsoftUtils {
    public static long randomNumber(int numberOfDigits) {
        double min = Math.pow(10,numberOfDigits - 1);
        double max = Math.pow(10,numberOfDigits) - 1;
        return Math.round(Math.random() * ((max - min) + 1) + min);
    }
}
