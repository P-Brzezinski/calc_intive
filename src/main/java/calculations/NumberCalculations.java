package calculations;

import enums.Calc;
import exceptions.DivideByZeroException;

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

    public int add(String a, String b) {
        return Integer.parseInt(a) + Integer.parseInt(b);
    }

    public int sub(String a, String b) {
        return Integer.parseInt(a) - Integer.parseInt(b);
    }

    private int multi(String a, String b) {
        return Integer.parseInt(a) * Integer.parseInt(b);
    }

    private int div(String a, String b) {
        int result = 0;
        try {
            result = Integer.parseInt(a) / Integer.parseInt(b);
        } catch (ArithmeticException e) {
            System.out.println("Divide by zero operation is not possible");
        }
        return result;
    }

    private double power(String a, String b) {
        return Math.pow(Double.parseDouble(a), Double.parseDouble(b));

    }

    private double square(String a, String b) {
        return Math.sqrt(Double.parseDouble(a));
    }

    //TODO empty array as input
    private String multiVector(String a, String b) {
        int[] arrayFromString = Calculations.super.getArrayFromString(b);
        for (int i = 0; i < arrayFromString.length; i++) {
            arrayFromString[i] = arrayFromString[i] * Integer.parseInt(a);
        }
        return Arrays.toString(arrayFromString);
    }

    //TODO NUM_MULTI_MATRIX
}
