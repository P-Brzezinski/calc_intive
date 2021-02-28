package pl.brzezinski.calculations;

import pl.brzezinski.exceptions.VectorException;

import java.math.BigDecimal;
import java.util.Arrays;

public class CommonCalculations {

    protected String nMultiV(String v, String n) throws VectorException {
        double[] vector = getVectorFromString(v);
        if (vector.length == 0) {
            throw new VectorException("Vector can not be empty if you want to multiply by number");
        }
        BigDecimal multiplier = BigDecimal.valueOf(Double.parseDouble(n));
        BigDecimal tempValue;
        for (int i = 0; i < vector.length; i++) {
            tempValue = BigDecimal.valueOf(vector[i]).multiply(multiplier);
            vector[i] = tempValue.doubleValue();
        }
        return Arrays.toString(vector);
    }

    protected double[] getVectorFromString(String stringArray) {
        stringArray = stringArray.substring(1, stringArray.length() - 1);
        if (stringArray.isEmpty())
            return new double[0];

        String[] split = stringArray.split(",");
        double[] newArray = new double[split.length];
        for (int i = 0; i < newArray.length; i++) {
            BigDecimal x = BigDecimal.valueOf(Double.parseDouble(String.valueOf(split[i])));
            newArray[i] = x.doubleValue();
        }
        return newArray;
    }

    protected double[][] getMatrixFromString(String stringMatrix) {
        stringMatrix = stringMatrix.substring(2, stringMatrix.length() - 2);
        if (stringMatrix.isEmpty())
            return new double[0][0];

        String[] strings = stringMatrix.split("]\\[");
        double[][] result = new double[strings.length][];
        for (int i = 0; i < result.length; i++) {
            String[] tempStringArray = strings[i].split(",");
            double[] singleVector = new double[tempStringArray.length];
            for (int j = 0; j < singleVector.length; j++) {
                singleVector[j] = Double.parseDouble(tempStringArray[j]);
            }
            result[i] = singleVector;
        }
        return result;
    }

    protected boolean noEmptyMatrix(double[][] matrix) {
        boolean noEmpty = true;
        if (matrix.length != 0) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i].length == 0) {
                    noEmpty = false;
                    break;
                }
            }
        }else {
            noEmpty = false;
        }
        return noEmpty;
    }
}
