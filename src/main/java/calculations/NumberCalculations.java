package calculations;

import config.Configuration;
import enums.Calc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class NumberCalculations implements Calculations {

    @Override
    public void doCalc(Calc calc, String a, String b) {
        switch (calc) {
            case NUM_ADD_NUM:
                System.out.println(add(a, b));
                break;
            case NUM_SUB_NUM:
                System.out.println(sub(a, b));
                break;
            case NUM_MULTI_NUM:
                System.out.println(multi(a, b));
                break;
            case NUM_DIV_NUM:
                System.out.println(div(a, b));
                break;
            case NUM_POWER_TO_NUM:
                System.out.println(power(a, b));
                break;
            case NUM_SQUARE:
                System.out.println(square(a, b));
                break;
            case NUM_MULTI_VECTOR:
                System.out.println(multiVector(a, b));
        }
    }

    public double add(String a, String b) {
        BigDecimal x = new BigDecimal(a);
        BigDecimal y = new BigDecimal(b);
        BigDecimal result = x.add(y);
        return result.doubleValue();
    }

    public double sub(String a, String b) {
        BigDecimal x = new BigDecimal(a);
        BigDecimal y = new BigDecimal(b);
        BigDecimal result = x.subtract(y);
        return result.doubleValue();
    }

    private double multi(String a, String b) {
        BigDecimal x = new BigDecimal(a);
        BigDecimal y = new BigDecimal(b);
        BigDecimal result = x.multiply(y);
        return result.doubleValue();
    }

    private double div(String a, String b) {
        BigDecimal x = new BigDecimal(a);
        BigDecimal y = new BigDecimal(b);
        BigDecimal result = new BigDecimal(0);
        try {
            result = x.divide(y, RoundingMode.CEILING);
        } catch (ArithmeticException e) {
            System.out.println("Divide by zero operation is not possible");
        }
        return result.doubleValue();
    }

    private double power(String a, String b) {
        if (Integer.parseInt(b) > Configuration.getMaxPower()) {
            System.out.println("Power cannot not be higher than " + Configuration.getMaxPower());
            return 0;
        } else {
            return Math.pow(Double.parseDouble(a), Double.parseDouble(b));
        }
    }

    private double square(String a, String b) {
        System.out.println("Value " + b + " is ignored here.");
        return Math.sqrt(Double.parseDouble(a));
    }

    //TODO empty array as input
    private String multiVector(String a, String b) {
        double[] arrayFromString = Calculations.super.getArrayFromString(b);
        for (int i = 0; i < arrayFromString.length; i++) {
            arrayFromString[i] = arrayFromString[i] * Integer.parseInt(a);
        }
        return Arrays.toString(arrayFromString);
    }

    //TODO NUM_MULTI_MATRIX
}
