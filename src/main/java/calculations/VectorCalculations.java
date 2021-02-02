package calculations;

import enums.Calc;

import java.util.Arrays;

public class VectorCalculations implements Calculations {

    @Override
    public void doCalc(Calc calc, String a, String b) {
        switch (calc) {
            case VECTOR_ADD_VECTOR:
                System.out.println(add(a, b));
                break;
            case VECTOR_SUB_VECTOR:
                System.out.println(sub(a, b));
                break;
            case VECTOR_MULTI_NUM:
                System.out.println(multi(a, b));
                break;
        }
    }

    private String add(String a, String b) {
        return null;
    }

    private String sub(String a, String b) {
        return null;
    }

    private String multi(String a, String b) {
        int[] arrayFromString = Calculations.super.getArrayFromString(a);
        for (int i = 0; i < arrayFromString.length; i++) {
            arrayFromString[i] = arrayFromString[i] * Integer.parseInt(b);
        }
        return Arrays.toString(arrayFromString);
    }
}
