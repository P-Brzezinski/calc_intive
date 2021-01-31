package enums;

public enum Calc {

    //Possible calculations for number
    NUM_ADD_NUM("Add number to number"),
    NUM_SUB_NUM("Subtract number from number"),
    NUM_MULTI_NUM("Multiply number by number"),
    NUM_DIV_NUM("Divide number by number"),
    NUM_POWER_TO_NUM("Raise number to power"),
    NUM_RADICAL("Radical of number"),  //TODO check translation
    NUM_MULTI_VECTOR("Multiply number by vector"),
    NUM_MULTI_MATRIX("Multiply number by matrix"),

    //Possible calculations for vector
    VECTOR_ADD_VECTOR("Add vector to vector"),
    VECTOR_SUB_VECTOR("Subtract vector from vector"),
    VECTOR_MULTI_NUM("Multiply vector by number"),

    //Possible calculations for matrix
    MATRIX_ADD_MATRIX("Add matrix to matrix"),
    MATRIX_SUB_MATRIX("Subtract matrix from matrix"),
    MATRIX_MULTI_NUM("Multiple matrix by number");

    String description;

    Calc(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
