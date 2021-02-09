package pl.brzezinski.service;

import pl.brzezinski.calculations.Calculations;
import pl.brzezinski.calculations.MatrixCalculations;
import pl.brzezinski.calculations.NumberCalculations;
import pl.brzezinski.calculations.VectorCalculations;
import pl.brzezinski.dto.CalculationRequest;
import pl.brzezinski.enums.Calculation;
import pl.brzezinski.enums.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brzezinski.exceptions.CalculationNotPossibleException;
import pl.brzezinski.exceptions.OperatorNotFoundException;
import pl.brzezinski.exceptions.UnrecognizedValueException;

import java.util.List;

@Service
public class CalculationService {

    private final NumberCalculations numberCalculations;

    @Autowired
    public CalculationService(NumberCalculations numberCalculations) {
        this.numberCalculations = numberCalculations;
    }

    public String makeCalc(CalculationRequest request) throws UnrecognizedValueException, OperatorNotFoundException, CalculationNotPossibleException {
        Value a = getValue(request.getValueA());
        Value b = getValue(request.getValueB());
        String operator = getOperator(request.getOperator());
        Calculation calculation = getCalculation(a, b, operator);

        switch (calculation.getValue1()) {
            case "Number":
                NumberCalculations nc = new NumberCalculations();
                return nc.doCalc(calculation, request.getValueA(), request.getValueB());
            case "Vector":
                VectorCalculations vc = new VectorCalculations();
                return vc.doCalc(calculation, request.getValueA(), request.getValueB());
            case "Matrix":
                MatrixCalculations mc = new MatrixCalculations();
                return mc.doCalc(calculation, request.getValueA(), request.getValueB());
            default:
                return "Error. I do not know math...";
        }
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
        throw new OperatorNotFoundException(Calculation.UNRECOGNIZED.getOperator() + ": " + operator);
    }

    private Calculation getCalculation(Value a, Value b, String operator) throws CalculationNotPossibleException {
        Calculation[] possibleCalculations = a.getPossibleCalculations();
        for (Calculation possibleCalculation : possibleCalculations) {
            if (possibleCalculation.getOperator().equals(operator) && b.getDescription().equals(possibleCalculation.getValue2())) {
                return possibleCalculation;
            }
        }
        throw new CalculationNotPossibleException(Calculation.UNRECOGNIZED.getDescription());
    }


}

