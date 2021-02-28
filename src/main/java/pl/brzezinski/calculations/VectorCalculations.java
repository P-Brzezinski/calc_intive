package pl.brzezinski.calculations;

import org.springframework.stereotype.Service;
import pl.brzezinski.enums.CalculationType;
import pl.brzezinski.exceptions.VectorException;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class VectorCalculations implements Calculations {

    private static final String ERROR_MESSAGE = "Vectors must have same length and can not be empty if you want to";

    @Override
    public String doCalculation(CalculationType calc, String a, String b) throws VectorException {
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

        if (sameLength(vector1, vector2) && noEmptyVector(vector1, vector2)) {
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

        if (sameLength(vector1, vector2) && noEmptyVector(vector1, vector2)) {
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
        double[] vector = getVectorFromString(a);
        if (vector.length == 0){
            throw new VectorException("Vector can not be empty if you want to multiply by number");
        }
        BigDecimal multiplier = BigDecimal.valueOf(Double.parseDouble(b));
        BigDecimal tempValue;
        for (int i = 0; i < vector.length; i++) {
            tempValue = BigDecimal.valueOf(vector[i]).multiply(multiplier);
            vector[i] = tempValue.doubleValue();
        }
        return Arrays.toString(vector);
    }

    private String vectorMultiMatrix(String a, String b) throws VectorException {
        double[] vector = getVectorFromString(a);
        double[][] matrix = getMatrixFromString(b);

        for (int i = 0; i < matrix.length; i++) {
            if (!(matrix[i].length == vector.length)) {
                throw new VectorException("To multiply a row vector by a column vector, the row vector must have as many columns as the column vector has rows.");
            } else {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[i][j] = matrix[i][j] * vector[i];
                }
            }
        }
        return Arrays.deepToString(matrix);
    }

    private boolean sameLength(double[] vector1, double[] vector2) {
        return vector1.length == vector2.length;
    }

    private boolean noEmptyVector(double[] vector1, double[] vector2) {
        return vector1.length != 0 && vector2.length != 0;
    }
}
