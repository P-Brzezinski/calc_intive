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

        if (!hasSameDimensions(matrixA, matrixB))
            return "Matrices must have same dimensions if you want to add them!";

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

        if (!hasSameDimensions(matrixA, matrixB))
            return "Matrices must have same dimensions if you want to subtract them!";

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

        if (!hasSameDimensions(matrixA, matrixB))
            return "Matrices must have same dimensions if you want to multiply them!";

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

    private boolean hasSameDimensions(double[][] matrixA, double[][] matrixB) {
        boolean hasSameDimensions = true;
        if (matrixA.length == matrixB.length) {
            for (int i = 0; i < matrixA.length; i++) {
                if (matrixA[i].length != matrixB[i].length) {
                    hasSameDimensions = false;
                    break;
                }
            }
        } else {
            hasSameDimensions = false;
        }
        return hasSameDimensions;
    }

}
