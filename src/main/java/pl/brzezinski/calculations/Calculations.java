package pl.brzezinski.calculations;

import pl.brzezinski.enums.CalculationType;
import pl.brzezinski.exceptions.MatrixException;
import pl.brzezinski.exceptions.VectorException;

import java.math.BigDecimal;

public interface Calculations {

    String doCalculation(CalculationType calc, String value1, String value2) throws VectorException, MatrixException;
}
