package calculations;

import enums.Calc;

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
            case NUM_MULTI_VECTOR:
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
        return null;
    }
}
