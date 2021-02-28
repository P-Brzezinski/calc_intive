package pl.brzezinski.calculations;

import org.springframework.stereotype.Service;
import pl.brzezinski.enums.CalculationType;
import pl.brzezinski.exceptions.MatrixException;
import pl.brzezinski.exceptions.VectorException;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class VectorCalculations extends CommonCalculations implements Calculations {

    private static final String ERROR_MESSAGE = "Vectors must have same length and can not be empty if you want to";

    @Override
    public String doCalculation(CalculationType calc, String a, String b) throws VectorException, MatrixException {
        switch (calc) {
            case VECTOR_ADD_VECTOR:
                return add(a, b);
            case VECTOR_SUB_VECTOR:
                return sub(a, b);
            case VECTOR_MULTI_NUM:
                return vectorMultiNumber(a, b);
            case VECTOR_MULTI_MATRIX:
                return vectorMultiMatrix(a, b);
        }
        return "No suitable operation found.";
    }

    private String add(String a, String b) throws VectorException {
        double[] vector1 = getVectorFromString(a);
        double[] vector2 = getVectorFromString(b);
        double[] result;
        BigDecimal tempValue;

        if (sameLength(vector1, vector2) && noEmptyVectors(vector1, vector2)) {
            result = new double[vector1.length];
            for (int i = 0; i < result.length; i++) {
                tempValue = BigDecimal.valueOf(vector1[i]).add(BigDecimal.valueOf(vector2[i]));
                result[i] = tempValue.doubleValue();
            }
            return Arrays.toString(result);
        } else {
            throw new VectorException(String.format("%s %s", ERROR_MESSAGE, "add"));
        }
    }

    private String sub(String a, String b) throws VectorException {
        double[] vector1 = getVectorFromString(a);
        double[] vector2 = getVectorFromString(b);
        double[] result;
        BigDecimal tempValue;

        if (sameLength(vector1, vector2) && noEmptyVectors(vector1, vector2)) {
            result = new double[vector1.length];
            for (int i = 0; i < result.length; i++) {
                tempValue = BigDecimal.valueOf(vector1[i]).subtract(BigDecimal.valueOf(vector2[i]));
                result[i] = tempValue.doubleValue();
            }
            return Arrays.toString(result);
        } else {
            throw new VectorException(String.format("%s %s", ERROR_MESSAGE, "subtract"));
        }
    }

    private String vectorMultiNumber(String a, String b) throws VectorException {
        return vMultiN(a, b);
    }

    private String vectorMultiMatrix(String a, String b) throws VectorException, MatrixException {
        return vMultiM(a,b);
    }

    private boolean sameLength(double[] vector1, double[] vector2) {
        return vector1.length == vector2.length;
    }

    private boolean noEmptyVectors(double[] vector1, double[] vector2) {
        return vector1.length != 0 && vector2.length != 0;
    }
}
