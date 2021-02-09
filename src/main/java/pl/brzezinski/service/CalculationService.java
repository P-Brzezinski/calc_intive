package pl.brzezinski.service;

import pl.brzezinski.calculations.NumberCalculations;
import pl.brzezinski.dto.CalculationRequest;
import pl.brzezinski.enums.Calculation;
import pl.brzezinski.enums.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brzezinski.exceptions.OperatorNotFoundException;
import pl.brzezinski.exceptions.UnrecognizedValueException;

@Service
public class CalculationService {

    private final NumberCalculations numberCalculations;

    @Autowired
    public CalculationService(NumberCalculations numberCalculations) {
        this.numberCalculations = numberCalculations;
    }

    public String makeCalc(CalculationRequest request) throws UnrecognizedValueException, OperatorNotFoundException {
        String result;
        Value a = getValue(request);
        Value b = getValue(request);

        Calculation calculation = getCalculation(request);

        Calculation[] possibleCalculationsForValue = Value.getPossibleCalculationsForValue(a);

        for (Calculation calc : possibleCalculationsForValue) {
            if (calc.equals(calculation) && calc.getValue2().equals(b.getDescription())){
                result = numberCalculations.doCalc(calculation, request.getValueA(), request.getValueB());
                return result;
            }
        }

        return "ERROR";
    }

    public Value getValue(CalculationRequest request) throws UnrecognizedValueException {
        Value value = Value.getValueFromString(request.getValueA());
        if (value.equals(Value.UNRECOGNIZED)) {
            throw new UnrecognizedValueException(value.getDescription());
        }
        return value;
    }

    public Calculation getCalculation(CalculationRequest request) throws OperatorNotFoundException {
        Calculation calculation = Calculation.getCalculationFromStringOperator(request.getOperator());
        if (calculation.equals(Calculation.UNRECOGNIZED)){
            throw new OperatorNotFoundException("Unrecognized operator");
        }
        return calculation;
    }
}

