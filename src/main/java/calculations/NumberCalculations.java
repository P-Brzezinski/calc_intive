package calculations;

import config.Configuration;
import enums.Calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class NumberCalculations implements Calculations {

    @Override
    public String doCalc(Calculation calc, String a, String b) {
        switch (calc) {
            case NUM_ADD_NUM:
                return add(a, b);
            case NUM_SUB_NUM:
                return sub(a, b);
            case NUM_MULTI_NUM:
                return multi(a, b);
            case NUM_DIV_NUM:
                return div(a, b);
            case NUM_POWER_TO_NUM:
                return power(a, b);
            case NUM_SQUARE:
                return square(a, b);
            case NUM_MULTI_VECTOR:
                return numberMultiVector(a, b);
            case NUM_MULTI_MATRIX:
                return numberMultiMatrix(a, b);
        }
        return "No suitable operation found.";
    }

    public String add(String a, String b) {
        BigDecimal result = BigDecimal.valueOf(Double.parseDouble(a)).add(BigDecimal.valueOf(Double.parseDouble(b)));
        return result.toString();
    }

    public String sub(String a, String b) {
        BigDecimal result = BigDecimal.valueOf(Double.parseDouble(a)).subtract(BigDecimal.valueOf(Double.parseDouble(b)));
        return result.toString();
    }

    private String multi(String a, String b) {
        BigDecimal result = BigDecimal.valueOf(Double.parseDouble(a)).multiply(BigDecimal.valueOf(Double.parseDouble(b)));
        return result.toString();
    }

    private String div(String a, String b) {
        String result;
        try {
            BigDecimal divide = BigDecimal.valueOf(Double.parseDouble(a)).divide(BigDecimal.valueOf(Double.parseDouble(b)), RoundingMode.CEILING);
            result = divide.toString();
        } catch (ArithmeticException e) {
            result = "Not possible";
            System.out.println("Divide by zero operation is not possible");
        }
        return result;
    }

    private String power(String a, String b) {
        if (Double.parseDouble(b) > Configuration.getMaxPower()) {
            return "Power cannot not be higher than " + Configuration.getMaxPower();
        } else {
            return String.valueOf(Math.pow(Double.parseDouble(a), Double.parseDouble(b)));
        }
    }

    private String square(String a, String b) {
        System.out.println("Value " + b + " is ignored here.");
        return String.valueOf(Math.sqrt(Double.parseDouble(a)));
    }

    private String numberMultiVector(String a, String b) {
        double[] vector = Calculations.super.getVectorFromString(b);
        BigDecimal multiplier = BigDecimal.valueOf(Double.parseDouble(a));
        BigDecimal tempValue;
        for (int i = 0; i < vector.length; i++) {
            tempValue = BigDecimal.valueOf(vector[i]).multiply(multiplier);
            vector[i] = tempValue.doubleValue();
        }
        return Arrays.toString(vector);
    }

    private String numberMultiMatrix(String a, String b) {
        double multiNum = Double.parseDouble(a);
        double[][] matrix = Calculations.super.getMatrixFromString(b);
        double[][] result = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            if (i > matrix[0].length) {
                break;
            } else {
                for (int j = 0; j < matrix[0].length; j++) {
                    result[i][j] = matrix[i][j] * multiNum;
                }
            }
        }
        return Arrays.deepToString(result);
    }
}
