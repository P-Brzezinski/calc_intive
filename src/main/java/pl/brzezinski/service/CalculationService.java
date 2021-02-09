package pl.brzezinski.service;

import pl.brzezinski.calculations.Calculations;
import pl.brzezinski.calculations.MatrixCalculations;
import pl.brzezinski.calculations.NumberCalculations;
import pl.brzezinski.calculations.VectorCalculations;
import pl.brzezinski.dto.CalculationRequest;
import pl.brzezinski.dto.PossibleCalculationsResponse;
import pl.brzezinski.dto.Result;
import pl.brzezinski.enums.Calculation;
import pl.brzezinski.enums.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brzezinski.exceptions.CalculationNotPossibleException;
import pl.brzezinski.exceptions.OperatorNotFoundException;
import pl.brzezinski.exceptions.UnrecognizedValueException;
import pl.brzezinski.exceptions.VectorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculationService {

    private final NumberCalculations numberCalculations;
    private final VectorCalculations vectorCalculations;
    private final MatrixCalculations matrixCalculations;

    @Autowired
    public CalculationService(NumberCalculations numberCalculations, VectorCalculations vectorCalculations, MatrixCalculations matrixCalculations) {
        this.numberCalculations = numberCalculations;
        this.vectorCalculations = vectorCalculations;
        this.matrixCalculations = matrixCalculations;
    }

    public Result doCalculation(CalculationRequest request) throws UnrecognizedValueException, OperatorNotFoundException, CalculationNotPossibleException, ArithmeticException, VectorException {
        String result;
        Value a = getValue(request.getValueA());
        Value b = getValue(request.getValueB());
        String operator = getOperator(request.getOperator());
        Calculation calculation = getCalculation(a, b, operator);

        switch (calculation.getValueA()) {
            case "Number":
                result = numberCalculations.doCalculation(calculation, request.getValueA(), request.getValueB());
                break;
            case "Vector":
                result = vectorCalculations.doCalculation(calculation, request.getValueA(), request.getValueB());
                break;
            case "Matrix":
                result = matrixCalculations.doCalculation(calculation, request.getValueA(), request.getValueB());
                break;
            default:
                result = "Error. I do not know math...";
        }
        return new Result(result, "Calculation result saved to file");
    }

    public Value getValue(String x) throws UnrecognizedValueException {
        Value value = Value.getValueFromString(x);
        if (value.equals(Value.UNRECOGNIZED)) {
            throw new UnrecognizedValueException(value.getDescription() + ": " + x);
        }
        return value;
    }

    private String getOperator(String operator) throws OperatorNotFoundException {
        List<String> possibleOperators = Calculation.getPossibleOperators();
        for (String possibleOperator : possibleOperators) {
            if (possibleOperator.equals(operator)) {
                return possibleOperator;
            }
        }
        throw new OperatorNotFoundException("Unrecognized operator: " + operator);
    }

    private Calculation getCalculation(Value a, Value b, String operator) throws CalculationNotPossibleException {
        Calculation[] possibleCalculations = a.getPossibleCalculations();
        for (Calculation possibleCalculation : possibleCalculations) {
            if (possibleCalculation.getOperator().equals(operator) && b.getDescription().equals(possibleCalculation.getValueB())) {
                return possibleCalculation;
            }
        }
        throw new CalculationNotPossibleException("Calculation not possible for given combination of values or operator. Please try again.");
    }


    public List<PossibleCalculationsResponse> getListOfPossibleCalculations() {
        List<PossibleCalculationsResponse> response = new ArrayList<>();
        Calculation[] values = Calculation.values();

        for (Calculation value : values) {
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

