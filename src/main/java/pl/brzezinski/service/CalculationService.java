package pl.brzezinski.service;

import pl.brzezinski.calculations.Calculations;
import pl.brzezinski.calculations.NumberCalculations;
import pl.brzezinski.dto.CalculationRequest;
import pl.brzezinski.enums.Calculation;
import pl.brzezinski.enums.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    private final NumberCalculations numberCalculations;

    @Autowired
    public CalculationService(NumberCalculations numberCalculations) {
        this.numberCalculations = numberCalculations;
    }

    public String doMatch(CalculationRequest calculationRequest) {
        String valueA = calculationRequest.getValueA();
        String valueB = calculationRequest.getValueB();
        String operator = calculationRequest.getOperator();

        String result = null;

        Value a = null;
        Value b = null;
        Calculation calculation = null;

        for (Value value : Value.values()) {
            if (valueA.matches(value.getPattern())) {
                a = value;
                break;
            } else {
                a = Value.UNRECOGNIZED;
            }
        }

        System.out.println("String A: " + valueA);
        System.out.println("Value A " + a.getDescription());

        Calculation[] possibleCalculations = a.getPossibleCalculations();
        for (Calculation calc : possibleCalculations) {
            if (calc.getOperator().equals(operator)) {
                calculation = calc;
                break;
            }
        }

        System.out.println("Operator : " + operator);
        System.out.println("Calc " + calculation.getDescription());

        for (Value value : Value.values()) {
            if (valueB.matches(value.getPattern())) {
                b = value;
                break;
            } else {
                b = Value.UNRECOGNIZED;
            }
        }

        System.out.println("String B: " + valueB);
        System.out.println("Value B " + b.getDescription());


        if (calculation.getValue2().equals(b.getDescription())) {

            result = numberCalculations.doCalc(calculation, valueA, valueB);
        }

        System.out.println(result);
        return result;
    }
}

