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

    private String add(String a, String b) {
        int[] vector1 = Calculations.super.getArrayFromString(a);
        int[] vector2 = Calculations.super.getArrayFromString(b);
        int[] result;

        if (sameLength(vector1, vector2)) {
            result = new int[vector1.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = vector1[i] + vector2[i];
            }
            return Arrays.toString(result);
        } else {
            return String.format("Vectors must have same length if you want to add them! Your vectors lengths are: %d and %d", vector1.length, vector2.length);
        }
    }

    private String sub(String a, String b) {
        int[] vector1 = Calculations.super.getArrayFromString(a);
        int[] vector2 = Calculations.super.getArrayFromString(b);
        int[] result;

        if (sameLength(vector1, vector2)) {
            result = new int[vector1.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = vector1[i] - vector2[i];
            }
            return Arrays.toString(result);
        } else {
            return String.format("Vectors must have same length if you want to subtract them! Your vectors lengths are: %d and %d", vector1.length, vector2.length);
        }
    }

    private String multi(String a, String b) {
        int[] arrayFromString = Calculations.super.getArrayFromString(a);
        for (int i = 0; i < arrayFromString.length; i++) {
            arrayFromString[i] = arrayFromString[i] * Integer.parseInt(b);
        }
        return Arrays.toString(arrayFromString);
    }

    private boolean sameLength(int[] vector1, int[] vector2) {
        return vector1.length == vector2.length;
    }
}
