package pl.brzezinski.calculations;

import org.springframework.stereotype.Service;
import pl.brzezinski.configuration.Configuration;
import pl.brzezinski.enums.CalculationType;
import pl.brzezinski.exceptions.MatrixException;
import pl.brzezinski.exceptions.VectorException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

@Service
public class NumberCalculations extends CommonCalculations implements Calculations {

    @Override
    public String doCalculation(CalculationType calc, String a, String b) throws ArithmeticException, MatrixException, VectorException {
        switch (calc) {
            case NUM_ADD_NUM:
                return add(a, b);
            case NUM_SUB_NUM:
                return sub(a, b);
            case NUM_MULTI_NUM:
                return multi(a, b);
            case NUM_DIV_NUM:
                return div(a, b);
            case NUM_POWER_TO_NUM:
                return power(a, b);
            case NUM_SQUARE:
                return square(a, b);
            case NUM_MULTI_VECTOR:
                return numberMultiVector(a, b);
            case NUM_MULTI_MATRIX:
                return numberMultiMatrix(a, b);
        }
        return "No suitable operation found.";
    }

    public String add(String a, String b) {
        BigDecimal result = BigDecimal.valueOf(Double.parseDouble(a)).add(BigDecimal.valueOf(Double.parseDouble(b)));
        return result.toString();
    }

    public String sub(String a, String b) {
        BigDecimal result = BigDecimal.valueOf(Double.parseDouble(a)).subtract(BigDecimal.valueOf(Double.parseDouble(b)));
        return result.toString();
    }

    private String multi(String a, String b) {
        BigDecimal result = BigDecimal.valueOf(Double.parseDouble(a)).multiply(BigDecimal.valueOf(Double.parseDouble(b)));
        return result.toString();
    }

    private String div(String a, String b) throws ArithmeticException {
        if (Integer.parseInt(b) == 0) {
            throw new ArithmeticException("Divide by zero operation is not possible");
        }
        BigDecimal divide = BigDecimal.valueOf(Double.parseDouble(a)).divide(BigDecimal.valueOf(Double.parseDouble(b)), RoundingMode.CEILING);
        return divide.toString();
    }

    private String power(String a, String b) {
        if (Double.parseDouble(b) > Configuration.MAX_POWER) {
            return "Power cannot not be higher than " + Configuration.MAX_POWER;
        } else {
            return String.valueOf(Math.pow(Double.parseDouble(a), Double.parseDouble(b)));
        }
    }

    private String square(String a, String b) {
        System.out.println("Value " + b + " is ignored here.");
        return String.valueOf(Math.sqrt(Double.parseDouble(a)));
    }

    private String numberMultiVector(String a, String b) throws VectorException {
        return vMultiN(b, a);
    }

    private String numberMultiMatrix(String a, String b) throws MatrixException {
        return mMultiN(b, a);
    }


}
