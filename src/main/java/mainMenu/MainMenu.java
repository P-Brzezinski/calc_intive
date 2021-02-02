package mainMenu;

import calculations.MatrixCalculations;
import calculations.NumberCalculations;
import calculations.VectorCalculations;
import config.Configuration;
import enums.Calc;
import input.Input;
import enums.Value;

import static enums.Value.*;

public class MainMenu {

    Input input = Input.getInstance();
    String value1;
    String value2;
    Calc calc;

    public void init() {
        Value valueFromString;
        do { //TODO loop always for testing - to be replaced by interface
            // Step 1. take first value and check if valid
            do {
                value1 = input.getString("Enter first value:");
                valueFromString = getValueFromString(value1);
            } while (valueFromString.equals(UNRECOGNIZED));

            //Step 2. show possible calculations for value from first step
            showPossibleCalcs(valueFromString);
            calc = getAction(valueFromString);

            //Step 3. take second value
            do {
                value2 = input.getString("Enter second value:");
                valueFromString = getValueFromString(value2);
                // Step 4. check if second value matches second value needed for calculation
                if (!valueFromString.getDescription().equals(calc.getValue2())){
                    // if not, loop
                    //TODO check if 0 with div method
                    //TODO take only one var if Square operation
                    //TODO potęgowanie - zakres akceptowanego wykładnika zawiera się w przedziale 1-128
                    //TODO pierwiastkowanie - stopien 2
                    System.out.println("Wrong value, should be of type " + calc.getValue2());
                    valueFromString = UNRECOGNIZED;
                }
            } while (valueFromString.equals(UNRECOGNIZED));

            System.out.println(value1 + " " + calc.getDescription() + " " + value2);
            doMatch(calc, value1, value2);

        } while (true);
    }

    private Calc getAction(Value value) {
        int pickOption = input.getInt("Choose calculation", 1, value.getPossibleCalcs().length);
        return value.getPossibleCalcs()[pickOption - 1];
    }

    private void doMatch(Calc calc, String value1, String value2) {
        switch (calc.getValue1()){
            case "Number":
                NumberCalculations nc = new NumberCalculations();
                nc.doCalc(calc, value1, value2);
                break;
            case "Vector":
                VectorCalculations vc = new VectorCalculations();
                vc.doCalc(calc, value1, value2);
                break;
            case "Matrix":
                MatrixCalculations mc = new MatrixCalculations();
                mc.doCalc(calc, value1, value2);
                break;
        }
    }
}
