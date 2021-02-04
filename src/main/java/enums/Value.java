package enums;

import config.Configuration;

import static enums.Calculation.*;

public enum Value {

    NUMBER("Number", Configuration.getNumberPattern(),
            new Calculation[]{NUM_ADD_NUM, NUM_SUB_NUM, NUM_MULTI_NUM, NUM_DIV_NUM, NUM_POWER_TO_NUM, NUM_SQUARE, NUM_MULTI_VECTOR, NUM_MULTI_MATRIX}),
    VECTOR("Vector", Configuration.getVectorPattern(),
            new Calculation[]{VECTOR_ADD_VECTOR, VECTOR_SUB_VECTOR, VECTOR_MULTI_NUM}),
    MATRIX("Matrix", Configuration.getMatrixPattern(),
            new Calculation[]{MATRIX_ADD_MATRIX, MATRIX_SUB_MATRIX, MATRIX_MULTI_NUM}),
    UNRECOGNIZED("Unrecognized value", "NULL",
            new Calculation[0]);

    String description;
    String pattern;
    Calculation[] possibleCalcs;

    Value(String description, String pattern, Calculation[] possibleCalcs) {
        this.description = description;
        this.pattern = pattern;
        this.possibleCalcs = possibleCalcs;
    }

    public static void showPossibleCalcs(Value value) {
        Calculation[] possibleCalcs = value.getPossibleCalcs();
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

    public Calculation[] getPossibleCalcs() {
        return possibleCalcs;
    }
}
