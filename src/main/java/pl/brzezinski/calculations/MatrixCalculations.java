package pl.brzezinski.calculations;

import org.springframework.stereotype.Service;
import pl.brzezinski.enums.CalculationType;
import pl.brzezinski.exceptions.MatrixException;

import java.math.BigDecimal;
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
        double[][] matrixA = getMatrixFromString(a);
        double[][] matrixB = getMatrixFromString(b);
        double[][] result;
        BigDecimal tempValue;

        if (sameLength(matrixA, matrixB) && noEmptyMatrices(matrixA, matrixB)) {
            result = new double[matrixA.length][matrixA[0].length];
            for (int i = 0; i < matrixA.length; i++) {
                if (i > matrixA[0].length) {
                    break;
                } else {
                    for (int j = 0; j < matrixA[0].length; j++) {
                        tempValue = BigDecimal.valueOf(matrixA[i][j]).add(BigDecimal.valueOf(matrixB[i][j]));
                        result[i][j] = tempValue.doubleValue();
                    }
                }
            }
            return Arrays.deepToString(result);
        } else {
            throw new MatrixException(String.format("%s %s", ERROR_MESSAGE, "add"));
        }
    }

    private String sub(String a, String b) throws MatrixException {
        double[][] matrixA = getMatrixFromString(a);
        double[][] matrixB = getMatrixFromString(b);
        double[][] result;
        BigDecimal tempValue;

        if (sameLength(matrixA, matrixB) && noEmptyMatrices(matrixA, matrixB)) {
            result = new double[matrixA.length][matrixA[0].length];
            for (int i = 0; i < matrixA.length; i++) {
                if (i > matrixA[0].length) {
                    break;
                } else {
                    for (int j = 0; j < matrixA[0].length; j++) {
                        tempValue = BigDecimal.valueOf(matrixA[i][j]).subtract(BigDecimal.valueOf(matrixB[i][j]));
                        result[i][j] = tempValue.doubleValue();
                    }
                }
            }
        } else {
            throw new MatrixException(String.format("%s %s", ERROR_MESSAGE, "subtract"));
        }
        return Arrays.deepToString(result);
    }

    private String matrixMultiMatrix(String a, String b) throws MatrixException {
        double[][] matrixA = getMatrixFromString(a);
        double[][] matrixB = getMatrixFromString(b);
        double[][] result;
        BigDecimal tempValue;

        if (sameLength(matrixA, matrixB) && noEmptyMatrices(matrixA, matrixB)) {
            result = new double[matrixA.length][matrixA[0].length];
            for (int i = 0; i < matrixA.length; i++) {
                if (i > matrixA[0].length) {
                    break;
                } else {
                    for (int j = 0; j < matrixA[0].length; j++) {
                        tempValue = BigDecimal.valueOf(matrixA[i][j]).multiply(BigDecimal.valueOf(matrixB[i][j]));
                        result[i][j] = tempValue.doubleValue();
                    }
                }
            }
        } else {
            throw new MatrixException(String.format("%s %s", ERROR_MESSAGE, "multiply"));
        }
        return Arrays.deepToString(result);
    }

    private String matrixMultiNumber(String a, String b) throws MatrixException {
        double multiNum = Double.parseDouble(b);
        double[][] matrix = getMatrixFromString(a);
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

    private String matrixMultiVector(String a, String b) throws MatrixException {
        double[][] matrix = getMatrixFromString(a);
        double[] vector = getVectorFromString(b);
        BigDecimal tempValue;

        for (int i = 0; i < matrix.length; i++) {
            if (noEmptyMatrix(matrix) && (matrix[i].length == vector.length)) {
                for (int j = 0; j < vector.length; j++) {
                    tempValue = BigDecimal.valueOf(matrix[i][j]).multiply(BigDecimal.valueOf(vector[i]));
                    matrix[i][j] = tempValue.doubleValue();
                }
            } else {
                throw new MatrixException("To multiply a row vector by a column vector, the row vector must have as many columns as the column vector has rows.");
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
        if (matrixA.length == 0 && matrixB.length == 0) {
            noEmpty = false;
        } else {
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
}
