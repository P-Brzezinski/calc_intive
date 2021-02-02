package enums;

import config.Configuration;

import static enums.Calc.*;

public enum Value {

    NUMBER("Number", "^[+-]?([0-9]*[.])?[0-9]+$",
            new Calc[]{NUM_ADD_NUM, NUM_SUB_NUM, NUM_MULTI_NUM, NUM_DIV_NUM, NUM_POWER_TO_NUM, NUM_SQUARE, NUM_MULTI_VECTOR, NUM_MULTI_MATRIX}),
    VECTOR("Vector", Configuration.VECTOR_PATTERN,
            new Calc[]{VECTOR_ADD_VECTOR, VECTOR_SUB_VECTOR, VECTOR_MULTI_NUM}),
    MATRIX("Matrix", "TODO",
            new Calc[]{MATRIX_ADD_MATRIX, MATRIX_SUB_MATRIX, MATRIX_MULTI_NUM}),
    UNRECOGNIZED("Unrecognized value", "NULL",
            new Calc[0]);

    String description;
    String pattern;
    Calc[] possibleCalcs;

    Value(String description, String pattern, Calc[] possibleCalcs) {
        this.description = description;
        this.pattern = pattern;
        this.possibleCalcs = possibleCalcs;
    }

    public static void showPossibleCalcs(Value value) {
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
        System.out.println(UNRECOGNIZED.getDescription());
        return Value.UNRECOGNIZED;
    }

    public String getDescription() {
        return description;
    }

    public String getPattern() {
        return pattern;
    }

    public Calc[] getPossibleCalcs() {
        return possibleCalcs;
    }
}
