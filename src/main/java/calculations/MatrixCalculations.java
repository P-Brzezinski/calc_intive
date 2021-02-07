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

    //TODO
    private String add(String a, String b) {
        double[][] matrixA = Calculations.super.getMatrixFromString(a);
        double[][] matrixB = Calculations.super.getMatrixFromString(b);

        System.out.println("X " + matrixA.length);
        System.out.println("Y " + matrixA[0].length);

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
        return "Iam sorry, this feature is not yet implemented...";
    }

    private String matrixMultiMatrix(String a, String b) {
        return "Iam sorry, this feature is not yet implemented...";
    }

    private String matrixMultiNumber(String a, String b) {
        return "Iam sorry, this feature is not yet implemented...";
    }
}
