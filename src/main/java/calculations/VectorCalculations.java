package calculations;

import enums.Calc;

import java.util.Arrays;

public class VectorCalculations implements Calculations {

    @Override
    public void doCalc(Calc calc, String a, String b) {
        switch (calc) {
            case VECTOR_ADD_VECTOR:
                System.out.println(add(a, b));
                break;
            case VECTOR_SUB_VECTOR:
                System.out.println(sub(a, b));
                break;
            case VECTOR_MULTI_NUM:
                System.out.println(multi(a, b));
                break;
        }
    }

    //TODO vectors must have same length
    private String add(String a, String b) {
        int[] vector1 = Calculations.super.getArrayFromString(a);
        int[] vector2 = Calculations.super.getArrayFromString(b);
        int[] result = new int[vector1.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = vector1[i] + vector2[i];
        }
        return Arrays.toString(result);
    }

    //TODO vectors must have same length
    private String sub(String a, String b) {
        int[] vector1 = Calculations.super.getArrayFromString(a);
        int[] vector2 = Calculations.super.getArrayFromString(b);
        int[] result = new int[vector1.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = vector1[i] - vector2[i];
        }
        return Arrays.toString(result);
    }

    private String multi(String a, String b) {
        int[] arrayFromString = Calculations.super.getArrayFromString(a);
        for (int i = 0; i < arrayFromString.length; i++) {
            arrayFromString[i] = arrayFromString[i] * Integer.parseInt(b);
        }
        return Arrays.toString(arrayFromString);
    }
}
