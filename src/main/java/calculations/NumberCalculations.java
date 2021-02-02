package calculations;

import enums.Calc;

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

    private double div(String a, String b) {
        return Double.parseDouble(a) / Double.parseDouble(b);
    }

    private double power(String a, String b) {
        return Math.pow(Double.parseDouble(a), Double.parseDouble(b));

    }

    private double square(String a, String b) {
        return Math.sqrt(Double.parseDouble(a));
    }

    //TODO empty array as input
    private String multiVector(String a, String b) {
        b = b.substring(1, b.length() - 1);
        String[] split = b.split(",");
        int[] intArray = new int[split.length];
        for (int i = 0; i < intArray.length; i++) {
            int integer = Integer.parseInt(String.valueOf(split[i]));
            intArray[i] = integer * Integer.parseInt(a);
        }
        return Arrays.toString(intArray);
    }

    //TODO NUM_MULTI_MATRIX
}
