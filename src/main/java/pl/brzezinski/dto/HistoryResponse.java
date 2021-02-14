package pl.brzezinski.dto;

public class HistoryResponse {

    private String date;
    private String calculation;

    public HistoryResponse() {
    }

    public HistoryResponse(String date, String calculation) {
        this.date = date;
        this.calculation = calculation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCalculation() {
        return calculation;
    }

    public void setCalculation(String calculation) {
        this.calculation = calculation;
    }
}
