package com.codegym.aurora.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class NumeroloryUtils {
    public static int calculateDigitSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
    public static int reduceNumber(int number) {
        while (number > 9) {
            int sum = 0;
            while (number > 0) {
                sum += number % 10;
                number /= 10;
            }
            number = sum;
        }
        return number;
    }

    public static int reduceToSingleDigit(int number) {
        if (number <= 9) {
            return number;
        }

        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }

        return reduceToSingleDigit(sum);
    }

    public static String removeAccent(String text) {
        text = text.toUpperCase().trim();
        String temp = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp = pattern.matcher(temp).replaceAll("");
        return temp.replaceAll("Đ", "D");
    }
}
