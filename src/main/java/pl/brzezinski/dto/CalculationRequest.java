package pl.brzezinski.dto;

public class CalculationRequest {

    String valueA;
    String operator;
    String valueB;

    public CalculationRequest() {
    }

    public CalculationRequest(String valueA, String operator, String valueB) {
        this.valueA = valueA;
        this.operator = operator;
        this.valueB = valueB;
    }

    public String getValueA() {
        return valueA;
    }

    public void setValueA(String valueA) {
        this.valueA = valueA;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValueB() {
        return valueB;
    }

    public void setValueB(String valueB) {
        this.valueB = valueB;
    }
}