package calculations;

import enums.Calculation;

public class MatrixCalculations implements calculations.Calculations {

    @Override
    public void doCalc(Calculation calc, String a, String b) {
        switch (calc) {
            case MATRIX_ADD_MATRIX:
                System.out.println(add(a, b));
                break;
            case MATRIX_SUB_MATRIX:
                System.out.println(sub(a, b));
                break;
            case MATRIX_MULTI_MATRIX:
                System.out.println(matrixMultiMatrix(a, b));
                break;
            case MATRIX_MULTI_NUM:
                System.out.println(matrixMultiNumber(a, b));
        }

    }

    private String add(String a, String b) {
        return null;
    }

    private String sub(String a, String b) {
        return null;
    }

    private String matrixMultiMatrix(String a, String b) {
        return null;
    }

    private String matrixMultiNumber(String a, String b) {
        return null;
    }
}
