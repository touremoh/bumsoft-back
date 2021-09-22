package io.bumsoft.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BumsoftUtils {
    public static long randomNumber(int numberOfDigits) {
        double min = Math.pow(10,numberOfDigits - 1);
        double max = Math.pow(10,numberOfDigits) - 1;
        return Math.round(Math.random() * ((max - min) + 1) + min);
    }

    public static LocalDate toLocalDate(String date, String pattern) {
       return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }
}
