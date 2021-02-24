package pl.brzezinski.enums;

import pl.brzezinski.configuration.Configuration;

import static pl.brzezinski.enums.CalculationType.*;

public enum Value {

    NUMBER("Number", Configuration.NUMBER_PATTERN,
            new CalculationType[]{NUM_ADD_NUM, NUM_SUB_NUM, NUM_MULTI_NUM, NUM_DIV_NUM, NUM_POWER_TO_NUM, NUM_SQUARE, NUM_MULTI_VECTOR, NUM_MULTI_MATRIX}),
    VECTOR("Vector", Configuration.getVectorPattern(),
            new CalculationType[]{VECTOR_ADD_VECTOR, VECTOR_SUB_VECTOR, VECTOR_MULTI_NUM, VECTOR_MULTI_MATRIX}),
    MATRIX("Matrix", Configuration.getMatrixPattern(),
            new CalculationType[]{MATRIX_ADD_MATRIX, MATRIX_SUB_MATRIX, MATRIX_MULTI_MATRIX, MATRIX_MULTI_NUM, MATRIX_MULTI_VECTOR}),
    UNRECOGNIZED("Unrecognized value", "NULL",
            new CalculationType[0]);

    private final String description;
    private final String pattern;
    private final CalculationType[] possibleCalculationTypes;

    Value(String description, String pattern, CalculationType[] possibleCalculationTypes) {
        this.description = description;
        this.pattern = pattern;
        this.possibleCalculationTypes = possibleCalculationTypes;
    }

    // console app feature
    public static void showPossibleCalculations(Value value) {
        CalculationType[] possibleCalculationTypes = value.getPossibleCalculations();
        for (int i = 0; i < possibleCalculationTypes.length; i++) {
            System.out.println(i + 1 + " - " + possibleCalculationTypes[i].getDescription());
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

    public static CalculationType[] getPossibleCalculationsForValue(Value value){
        Value[] values = Value.values();
        for (Value v : values) {
            if (v.equals(v)){
                return v.getPossibleCalculations();
            }
        }
        return UNRECOGNIZED.getPossibleCalculations();
    }

    public String getDescription() {
        return description;
    }

    public CalculationType[] getPossibleCalculations() {
        return possibleCalculationTypes;
    }
}
