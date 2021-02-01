package enums;

public enum Calc {

    //Possible calculations for number
    NUM_ADD_NUM("Add number to number", "Number", "Number"),
    NUM_SUB_NUM("Subtract number from number", "Number", "Number"),
    NUM_MULTI_NUM("Multiply number by number", "Number", "Number"),
    NUM_DIV_NUM("Divide number by number", "Number", "Number"),
    NUM_POWER_TO_NUM("Raise number to power", "Number", "Number"),
    NUM_SQUARE("Radical of number", "Number", "Number"),  //TODO check translation
    NUM_MULTI_VECTOR("Multiply vector by number", "Number", "Vector"),
    NUM_MULTI_MATRIX("Multiply matrix by number", "Number", "Matrix"),

    //    Possible calculations for vector
    VECTOR_ADD_VECTOR("Add vector to vector", "Vector", "Vector"),
    VECTOR_SUB_VECTOR("Subtract vector from vector", "Vector", "Vector"),
    VECTOR_MULTI_NUM("Multiply vector by number", "Vector", "Number"),

    //    Possible calculations for matrix
    MATRIX_ADD_MATRIX("Add matrix to matrix", "Matrix", "Matrix"),
    MATRIX_SUB_MATRIX("Subtract matrix from matrix", "Matrix", "Matrix"),
    MATRIX_MULTI_NUM("Multiple matrix by number", "Matrix", "Number");

    String description;
    String value1;
    String value2;

    Calc(String description, String value1, String value2) {
        this.description = description;
        this.value1 = value1;
        this.value2 = value2;
    }

    public String getDescription() {
        return description;
    }

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }
}
