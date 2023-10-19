package com.codegym.aurora.util;

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

}
