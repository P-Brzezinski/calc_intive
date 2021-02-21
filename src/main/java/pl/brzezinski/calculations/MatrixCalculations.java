package pl.brzezinski.calculations;

import org.springframework.stereotype.Service;
import pl.brzezinski.enums.CalculationType;
import pl.brzezinski.exceptions.MatrixException;

import java.util.Arrays;

@Service
public class MatrixCalculations implements Calculations {

    private static final String ERROR_MESSAGE = "Matrices must have same dimensions and cannot be empty if you want to";

    @Override
    public String doCalculation(CalculationType calc, String a, String b) throws MatrixException {
        switch (calc) {
            case MATRIX_ADD_MATRIX:
                return add(a, b);
            case MATRIX_SUB_MATRIX:
                return sub(a, b);
            case MATRIX_MULTI_MATRIX:
                return matrixMultiMatrix(a, b);
            case MATRIX_MULTI_NUM:
                return matrixMultiNumber(a, b);
            case MATRIX_MULTI_VECTOR:
                return matrixMultiVector(a, b);
        }
        return "No suitable operation found.";
    }

    private String add(String a, String b) throws MatrixException {
        double[][] matrixA = Calculations.super.getMatrixFromString(a);
        double[][] matrixB = Calculations.super.getMatrixFromString(b);
        double[][] result;
        
        if (sameLength(matrixA, matrixB) && noEmptyMatrices(matrixA, matrixB)) {
            result = new double[matrixA.length][matrixA[0].length];
            for (int i = 0; i < matrixA.length; i++) {
                if (i > matrixA[0].length) {
                    break;
                } else {
                    for (int j = 0; j < matrixA[0].length; j++) {
                        result[i][j] = matrixA[i][j] + matrixB[i][j];
                    }
                }
            }
            return Arrays.deepToString(result);
        } else {
            throw new MatrixException(String.format("%s %s", ERROR_MESSAGE, "add"));
        }
    }

    private String sub(String a, String b) {
        double[][] matrixA = Calculations.super.getMatrixFromString(a);
        double[][] matrixB = Calculations.super.getMatrixFromString(b);

        if (sameLength(matrixA, matrixB) && noEmptyMatrices(matrixA, matrixB))
            return String.format("%s %s", ERROR_MESSAGE, "subtract");

        double[][] result = new double[matrixA.length][matrixA[0].length];

        for (int i = 0; i < matrixA.length; i++) {
            if (i > matrixA[0].length) {
                break;
            } else {
                for (int j = 0; j < matrixA[0].length; j++) {
                    result[i][j] = matrixA[i][j] - matrixB[i][j];
                }
            }
        }
        return Arrays.deepToString(result);
    }

    private String matrixMultiMatrix(String a, String b) {
        double[][] matrixA = Calculations.super.getMatrixFromString(a);
        double[][] matrixB = Calculations.super.getMatrixFromString(b);

        if (sameLength(matrixA, matrixB) && noEmptyMatrices(matrixA, matrixB))
            return String.format("%s %s", ERROR_MESSAGE, "multiply");

        double[][] result = new double[matrixA.length][matrixA[0].length];

        for (int i = 0; i < matrixA.length; i++) {
            if (i > matrixA[0].length) {
                break;
            } else {
                for (int j = 0; j < matrixA[0].length; j++) {
                    result[i][j] = matrixA[i][j] * matrixB[i][j];
                }
            }
        }
        return Arrays.deepToString(result);
    }

    private String matrixMultiNumber(String a, String b) {
        double[][] matrixA = Calculations.super.getMatrixFromString(a);
        double multiNum = Double.parseDouble(b);
        double[][] result = new double[matrixA.length][matrixA[0].length];

        if (noEmptyMatrices(matrixA) && multiNum != 0) {
            return "Matrix can not be empty and multiplier can not be 0, check your values.";
        }

        for (int i = 0; i < matrixA.length; i++) {
            if (i > matrixA[0].length) {
                break;
            } else {
                for (int j = 0; j < matrixA[0].length; j++) {
                    result[i][j] = matrixA[i][j] * multiNum;
                }
            }
        }
        return Arrays.deepToString(result);
    }

    private String matrixMultiVector(String a, String b) {
        double[][] matrix = Calculations.super.getMatrixFromString(a);
        double[] vector = Calculations.super.getVectorFromString(b);

        for (int i = 0; i < matrix.length; i++) {
            if (!(matrix[i].length == vector.length)) {
                return "To multiply a row vector by a column vector, the row vector must have as many columns as the column vector has rows.";
            } else {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[i][j] = matrix[i][j] * vector[i];
                }
            }
        }
        return Arrays.deepToString(matrix);
    }

    private boolean sameLength(double[][] matrixA, double[][] matrixB) {
        boolean sameDimensions = true;
        if (matrixA.length == matrixB.length) {
            for (int i = 0; i < matrixA.length; i++) {
                if (matrixA[i].length != matrixB[i].length) {
                    sameDimensions = false;
                    break;
                }
            }
        } else {
            sameDimensions = false;
        }
        return sameDimensions;
    }

    private boolean noEmptyMatrices(double[][] matrixA, double[][] matrixB) {
        boolean noEmpty = true;
        if (matrixA.length != 0 && matrixB.length != 0) {
            for (int i = 0; i < matrixA.length; i++) {
                if (matrixA[i].length == 0) {
                    noEmpty = false;
                    break;
                } else if (matrixB[i].length == 0) {
                    noEmpty = false;
                    break;
                }
            }
        }
        return noEmpty;
    }

    private boolean noEmptyMatrices(double[][] matrix) {
        boolean noEmpty = true;
        if (matrix.length != 0) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i].length == 0) {
                    noEmpty = false;
                    break;
                }
            }
        }
        return noEmpty;
    }
}
