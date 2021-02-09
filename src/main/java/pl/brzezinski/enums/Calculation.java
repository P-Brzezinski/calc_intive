package pl.brzezinski.enums;

import java.util.ArrayList;
import java.util.List;

public enum Calculation {

    //Possible calculations for number
    NUM_ADD_NUM("Add number to number", "+","Number", "Number"),
    NUM_SUB_NUM("Subtract number from number", "-", "Number", "Number"),
    NUM_MULTI_NUM("Multiply number by number", "*", "Number", "Number"),
    NUM_DIV_NUM("Divide number by number", "/","Number", "Number"),
    NUM_POWER_TO_NUM("Raise number to power", "power of", "Number", "Number"),
    NUM_SQUARE("Radical of number", "square of", "Number",  "Number"),
    NUM_MULTI_VECTOR("Multiply number by vector", "*", "Number", "Vector"),
    NUM_MULTI_MATRIX("Multiply number by matrix", "*", "Number", "Matrix"),

    //    Possible calculations for vector
    VECTOR_ADD_VECTOR("Add vector to vector", "+", "Vector", "Vector"),
    VECTOR_SUB_VECTOR("Subtract vector from vector", "-", "Vector", "Vector"),
    VECTOR_MULTI_NUM("Multiply vector by number", "*", "Vector", "Number"),
    VECTOR_MULTI_MATRIX("Multiply vector by matrix", "*", "Vector", "Matrix"),

    //    Possible calculations for matrix
    MATRIX_ADD_MATRIX("Add matrix to matrix", "+", "Matrix", "Matrix"),
    MATRIX_SUB_MATRIX("Subtract matrix from matrix", "-", "Matrix", "Matrix"),
    MATRIX_MULTI_NUM("Multiple matrix by number", "*", "Matrix", "Number"),
    MATRIX_MULTI_MATRIX("Matrix multiply by matrix", "*", "Matrix", "Matrix"),
    MATRIX_MULTI_VECTOR("Matrix multiply by vector", "*", "Matrix", "Vector");

//    UNRECOGNIZED("Calculation not possible for given combination of values or operator. Please try again.", "Unrecognized operator", "Unrecognized calculation - value 1 not possible", "Unrecognized calculation - value 2 not possible");

    String description;
    String operator;
    String valueA;
    String valueB;

    Calculation(String description, String operator, String valueA, String valueB) {
        this.description = description;
        this.operator = operator;
        this.valueA = valueA;
        this.valueB = valueB;
    }

    public static List<String> getPossibleOperators(){
        List<String> operators = new ArrayList<>();
        Calculation[] values = Calculation.values();
        for (Calculation value : values) {
            operators.add(value.getOperator());
        }
        return operators;
    }

    public String getDescription() {
        return description;
    }

    public String getValueA() {
        return valueA;
    }

    public String getValueB() {
        return valueB;
    }

    public String getOperator() {
        return operator;
    }
}
