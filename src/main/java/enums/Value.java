package enums;

import static enums.Calc.*;

public enum Value {

    NUMBER("Number", "\\d+",
            new Calc[]{NUM_ADD_NUM, NUM_SUB_NUM, NUM_MULTI_NUM, NUM_DIV_NUM, NUM_POWER_TO_NUM, NUM_RADICAL, NUM_MULTI_VECTOR, NUM_MULTI_MATRIX}),
    VECTOR("Vector", "TODO",
            new Calc[]{VECTOR_ADD_VECTOR, VECTOR_SUB_VECTOR, VECTOR_MULTI_NUM}),
    MATRIX("Matrix", "TODO",
            new Calc[]{MATRIX_ADD_MATRIX, MATRIX_SUB_MATRIX, MATRIX_MULTI_NUM}),
    UNRECOGNIZED("Wrong value", "NULL",
            new Calc[0]);

    String description;
    String pattern;
    Calc[] possibleCalcs;

    Value(String description, String pattern, Calc[] possibleCalcs) {
        this.description = description;
        this.pattern = pattern;
        this.possibleCalcs = possibleCalcs;
    }

    public static void showPossibleActions(Value value) {
        Calc[] possibleCalcs = value.getPossibleCalcs();
        for (int i = 0; i < possibleCalcs.length; i++) {
            System.out.println(i + 1 + " - " + possibleCalcs[i].getDescription());
        }
    }

    public static Value getValueFromString(String stringValue) {
        Value[] values = Value.values();
        for (Value value : values) {
            if (stringValue.matches(value.pattern)) {
                return value;
            }
        }
        return Value.UNRECOGNIZED;
    }


    public String getDescription() {
        return description;
    }

    public Calc[] getPossibleCalcs() {
        return possibleCalcs;
    }
}
