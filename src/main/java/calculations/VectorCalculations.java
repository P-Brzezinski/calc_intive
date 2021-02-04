package calculations;

import enums.Calculation;

import java.math.BigDecimal;
import java.util.Arrays;

public class VectorCalculations implements Calculations {

    @Override
    public String doCalc(Calculation calc, String a, String b) {
        switch (calc) {
            case VECTOR_ADD_VECTOR:
                return add(a, b);
            case VECTOR_SUB_VECTOR:
                return sub(a, b);
            case VECTOR_MULTI_NUM:
                return vectorMultiNumber(a, b);
        }
        return "No suitable operation found.";
    }

    private String add(String a, String b) {
        double[] vector1 = Calculations.super.getArrayFromString(a);
        double[] vector2 = Calculations.super.getArrayFromString(b);
        double[] result;
        BigDecimal tempValue;

        if (sameLength(vector1, vector2) && noEmptyVector(vector1, vector2)) {
            result = new double[vector1.length];
            for (int i = 0; i < result.length; i++) {
                tempValue = BigDecimal.valueOf(vector1[i]).add(BigDecimal.valueOf(vector2[i]));
                result[i] = tempValue.doubleValue();
            }
            return Arrays.toString(result);
        } else {
            return String.format("Vectors must have same length and can not be empty if you want to add them! Your vectors lengths are: %d and %d", vector1.length, vector2.length);
        }
    }

    private String sub(String a, String b) {
        double[] vector1 = Calculations.super.getArrayFromString(a);
        double[] vector2 = Calculations.super.getArrayFromString(b);
        double[] result;
        BigDecimal tempValue;

        if (sameLength(vector1, vector2) && noEmptyVector(vector1, vector2)) {
            result = new double[vector1.length];
            for (int i = 0; i < result.length; i++) {
                tempValue = BigDecimal.valueOf(vector1[i]).subtract(BigDecimal.valueOf(vector2[i]));
                result[i] = tempValue.doubleValue();
            }
            return Arrays.toString(result);
        } else {
            return String.format("Vectors must have same length and can not be empty if you want to subtract them! Your vectors lengths are: %d and %d", vector1.length, vector2.length);
        }
    }

    private String vectorMultiNumber(String a, String b) {
        double[] arrayFromString = Calculations.super.getArrayFromString(a);
        BigDecimal tempValue;
        for (int i = 0; i < arrayFromString.length; i++) {
            tempValue = BigDecimal.valueOf(arrayFromString[i]).multiply(BigDecimal.valueOf(Double.parseDouble(b)));
            arrayFromString[i] = tempValue.doubleValue();
        }
        return Arrays.toString(arrayFromString);
    }

    private boolean sameLength(double[] vector1, double[] vector2) {
        return vector1.length == vector2.length;
    }

    private boolean noEmptyVector(double[] vector1, double[] vector2) {
        return vector1.length != 0 && vector2.length != 0;
    }
}
