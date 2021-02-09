package pl.brzezinski.dto;

public class PossibleCalculationsResponse {
    private String description;
    private String valueA;
    private String operator;
    private String valueB;

    public PossibleCalculationsResponse(String description, String valueA, String operator, String valueB) {
        this.description = description;
        this.valueA = valueA;
        this.operator = operator;
        this.valueB = valueB;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValueA() {
        return valueA;
    }

    public void setValueA(String valueA) {
        this.valueA = valueA;
    }

    public String getValueB() {
        return valueB;
    }

    public void setValueB(String valueB) {
        this.valueB = valueB;
    }
}

