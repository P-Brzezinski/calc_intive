package values;

public enum Value {

    NUMBER("Number", "\\d"),
    VECTOR("Vector", "TODO"),
    MATRIX("Matrix", "TODO"),
    UNRECOGNIZED("Wrong value", "NULL");

    String description;
    String pattern;

    Value(String description, String pattern) {
        this.description = description;
        this.pattern = pattern;
    }

    public static Value getValueFromString(String stringValue) {
        Value[] values = Value.values();
        for (Value value : values) {
            if (stringValue.matches(value.pattern)){
                return value;
            }
        }
        return Value.UNRECOGNIZED;
    }

    public String getDescription() {
        return description;
    }
}
