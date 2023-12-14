package dev.frydae.beguild.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.text.NumberFormat;
import java.util.List;
import java.util.Random;

public class NumUtil {
    public static Double round(Double number) {
        return round(number, 0);
    }

    public static Double round(Double number, Integer places) {
        return (double) (Math.round(number * (10 ^ places)) / (10 ^ places));
    }

    public static Long round(Long number) {
        return round(number, 1);
    }

    public static Long round(Long number, Integer places) {
        return (long) (Math.round(number * (10 ^ places)) / (10 ^ places));
    }

    public static Float round(Float number) {
        return round(number, 0);
    }

    public static Float round(Float number, Integer places) {
        return (float) (Math.round(number * (10 ^ places)) / (10 ^ places));
    }

    public static Integer round(Integer number) {
        return round(number, 0);
    }

    public static Integer round(Integer number, Integer places) {
        return Math.round(number * (10 ^ places)) / (10 ^ places);
    }

    public static Integer parseInt(String var) {
        return parseInt(var, null);
    }
    public static Integer parseInt(String var, Integer def) {
        if (var == null || var.equals("")) {
            return def;
        }
        try {
            return Integer.parseInt(var);
        } catch (NumberFormatException ignored) {}
        return def;
    }

    public static Long parseLong(String var) {
        return parseLong(var, null);
    }
    public static Long parseLong(String var, Long def) {
        if (var == null || var.equals("")) {
            return def;
        }
        try {
            return Long.parseLong(var);
        } catch (NumberFormatException ignored) {}
        return def;
    }

    public static Double parseDouble(String var) {
        return parseDouble(var, null);
    }
    public static Double parseDouble(String var, Double def) {
        if (var == null) {
            return def;
        }
        try {
            return Double.parseDouble(var);
        } catch (NumberFormatException ignored) {}
        return def;
    }

    public static Float parseFloat(String var) {
        return parseFloat(var, null);
    }
    public static Float parseFloat(String var, Float def) {
        if (var == null) {
            return def;
        }
        try {
            return Float.parseFloat(var);
        } catch (NumberFormatException ignored) {}
        return def;
    }

    public static boolean isDouble(String string) {
        return Double.valueOf(string) != null;
    }

    public static boolean isFloat(String string) {
        return Float.valueOf(string) != null;
    }

    public static boolean isInteger(String string) {
        return Integer.valueOf(string) != null;
    }

    public static boolean isNumber(String str) {
        return NumberUtils.isCreatable(str);
    }

    public static int rand(int min, int max) {
        int randomNumber = new Random().nextInt(Math.abs(max - min));

        int boundAdjustment;
        if (min < max) {
            boundAdjustment = min + randomNumber;
        } else {
            boundAdjustment = min - randomNumber;
        }

        return Integer.parseInt(Patterns.PERIOD.split(String.valueOf(boundAdjustment))[0]);
    }

    public static String formatNumber(Integer number) {
        return NumberFormat.getInstance().format(number);
    }

    public static Integer average(List<Integer> numbers) {
        Integer average = 0;
        for (Integer number : numbers) {
            average += number;
        }

        return average / numbers.size();
    }

    public static boolean isBetween(float num, double min, double max) {
        return num >= min && num <= max;
    }

    public static String intToRoman(int integer) {
        StringBuilder sb = new StringBuilder();
        int times = 0;
        String[] romans = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        int[] ints = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        for (int i = ints.length - 1; i >= 0; i--) {
            times = integer / ints[i];
            integer %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }

    public static int assertBounds(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    public static double assertBounds(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }

    public static float assertBounds(float value, float min, float max) {
        return Math.min(Math.max(value, min), max);
    }

    public static double assertHorizontalCoordinate(double d) {
        return assertBounds(d, -3.0E7, 3.0E7);
    }

    public static double assertVerticalCoordinate(double d) {
        return assertBounds(d, -64, 320);
    }
}

