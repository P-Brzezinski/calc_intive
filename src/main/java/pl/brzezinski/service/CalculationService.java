package pl.brzezinski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.brzezinski.calculations.MatrixCalculations;
import pl.brzezinski.calculations.NumberCalculations;
import pl.brzezinski.calculations.VectorCalculations;
import pl.brzezinski.dto.CalculationRequest;
import pl.brzezinski.dto.PossibleCalculationsResponse;
import pl.brzezinski.dto.ResultResponse;
import pl.brzezinski.enums.CalculationType;
import pl.brzezinski.enums.Value;
import org.springframework.stereotype.Service;
import pl.brzezinski.exceptions.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CalculationService {

    private final NumberCalculations numberCalculations;
    private final VectorCalculations vectorCalculations;
    private final MatrixCalculations matrixCalculations;
    private final FileService fileService;
    private final H2Service dbService;

    @Autowired
    public CalculationService(NumberCalculations numberCalculations, VectorCalculations vectorCalculations, MatrixCalculations matrixCalculations, FileService fileService, H2Service dbService) {
        this.numberCalculations = numberCalculations;
        this.vectorCalculations = vectorCalculations;
        this.matrixCalculations = matrixCalculations;
        this.fileService = fileService;
        this.dbService = dbService;
    }

    public String doCalculation(CalculationRequest request) throws UnrecognizedValueException, OperatorNotFoundException, CalculationNotPossibleException, ArithmeticException, VectorException, MatrixException {
        String a = request.getValueA();
        String b = request.getValueB();
        String operator = request.getOperator();
        String result;

        //find proper calculation type based on given values and operator
        CalculationType calculationType = getCalculation(getValueFromString(a), getValueFromString(b), getOperatorFromString(operator));

        //basing on first value and calculation type, do calculation
        switch (calculationType.getValueA()) {
            case "Number":
                result = numberCalculations.doCalculation(calculationType, request.getValueA(), request.getValueB());
                break;
            case "Vector":
                result = vectorCalculations.doCalculation(calculationType, request.getValueA(), request.getValueB());
                break;
            case "Matrix":
                result = matrixCalculations.doCalculation(calculationType, request.getValueA(), request.getValueB());
                break;
            default:
                result = "Error. I do not know math...";
        }
        return result;
    }

    public Value getValueFromString(String x) throws UnrecognizedValueException {
        Value value = Value.getValueFromString(x);
        if (value.equals(Value.UNRECOGNIZED)) {
            throw new UnrecognizedValueException(value.getDescription() + ": " + x);
        }
        return value;
    }

    private String getOperatorFromString(String operator) throws OperatorNotFoundException {
        List<String> possibleOperators = CalculationType.getPossibleOperators();
        for (String possibleOperator : possibleOperators) {
            if (possibleOperator.equals(operator)) {
                return possibleOperator;
            }
        }
        throw new OperatorNotFoundException("Unrecognized operator: " + operator);
    }

    private CalculationType getCalculation(Value a, Value b, String operator) throws CalculationNotPossibleException {
        CalculationType[] possibleCalculationTypes = a.getPossibleCalculations();
        for (CalculationType possibleCalculationType : possibleCalculationTypes) {
            if (possibleCalculationType.getOperator().equals(operator) && b.getDescription().equals(possibleCalculationType.getValueB())) {
                return possibleCalculationType;
            }
        }
        throw new CalculationNotPossibleException("Calculation not possible for given combination of values or operator. Please try again.");
    }

    public List<PossibleCalculationsResponse> getListOfPossibleCalculations() {
        List<PossibleCalculationsResponse> response = new ArrayList<>();
        CalculationType[] values = CalculationType.values();

        for (CalculationType value : values) {
            response.add(new PossibleCalculationsResponse(
                    value.getDescription(),
                    value.getValueA(),
                    value.getOperator(),
                    value.getValueB()
            ));
        }
        return response;
    }
}

