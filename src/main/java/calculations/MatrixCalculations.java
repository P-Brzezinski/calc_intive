package calculations;

import enums.Calculation;

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
        return "Iam sorry, this feature is not yet implemented...";
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
