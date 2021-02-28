package pl.brzezinski.calculations;

import pl.brzezinski.exceptions.MatrixException;
import pl.brzezinski.exceptions.VectorException;

import java.math.BigDecimal;
import java.util.Arrays;

public class CommonCalculations {

    protected String vMultiN(String v, String n) throws VectorException {
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

    protected String mMultiN(String m, String n) throws MatrixException {
        double multiNum = Double.parseDouble(n);
        double[][] matrix = getMatrixFromString(m);
        double[][] result;
        BigDecimal tempValue;

        if (noEmptyMatrix(matrix)) {
            result = new double[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                if (i > matrix[0].length) {
                    break;
                } else {
                    for (int j = 0; j < matrix[0].length; j++) {
                        tempValue = BigDecimal.valueOf(matrix[i][j]).multiply(BigDecimal.valueOf(multiNum));
                        result[i][j] = tempValue.doubleValue();
                    }
                }
            }
        } else {
            throw new MatrixException("Matrix can not be empty if you want multiply by number");
        }
        return Arrays.deepToString(result);
    }

    protected String vMultiM(String v, String m) throws VectorException, MatrixException {
        double[][] matrix = getMatrixFromString(m);
        double[] vector = getVectorFromString(v);
        BigDecimal tempValue;

        if (!noEmptyVector(vector)) {
            throw new VectorException("Vector can not be empty");
        } else if (!noEmptyMatrix(matrix)) {
            throw new MatrixException("Matrix can not be empty");
        } else {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i].length == vector.length) {
                    for (int j = 0; j < vector.length; j++) {
                        tempValue = BigDecimal.valueOf(matrix[i][j]).multiply(BigDecimal.valueOf(vector[i]));
                        matrix[i][j] = tempValue.doubleValue();
                    }
                } else {
                    throw new MatrixException("To multiply a row vector by a column vector, the row vector must have as many columns as the column vector has rows.");
                }
            }
        }
        return Arrays.deepToString(matrix);
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
        } else {
            noEmpty = false;
        }
        return noEmpty;
    }

    private boolean noEmptyVector(double[] vector) {
        return vector.length != 0;
    }
}
