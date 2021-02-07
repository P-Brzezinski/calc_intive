package calculations;

import enums.Calculation;

import java.util.Arrays;

public class MatrixCalculations implements Calculations {

    @Override
    public String doCalc(Calculation calc, String a, String b) {
        switch (calc) {
            case MATRIX_ADD_MATRIX:
                return add(a, b);
            case MATRIX_SUB_MATRIX:
                return sub(a, b);
            case MATRIX_MULTI_MATRIX:
                return matrixMultiMatrix(a, b);
            case MATRIX_MULTI_NUM:
                return matrixMultiNumber(a, b);
        }
        return "No suitable operation found.";
    }

    // example [[22,33,44][44,22,33][11,22,44]]

    private String add(String a, String b) {
        double[][] matrixA = Calculations.super.getMatrixFromString(a);
        double[][] matrixB = Calculations.super.getMatrixFromString(b);

        double[][] result = new double[matrixA.length][matrixA[0].length];

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
    }

    private String sub(String a, String b) {
        double[][] matrixA = Calculations.super.getMatrixFromString(a);
        double[][] matrixB = Calculations.super.getMatrixFromString(b);

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
        return "Iam sorry, this feature is not yet implemented...";
    }

    private String matrixMultiNumber(String a, String b) {
        return "Iam sorry, this feature is not yet implemented...";
    }
}
