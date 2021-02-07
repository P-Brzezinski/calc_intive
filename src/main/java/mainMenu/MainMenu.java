package mainMenu;

import calculations.Calculations;
import calculations.MatrixCalculations;
import calculations.NumberCalculations;
import calculations.VectorCalculations;
import config.Configuration;
import enums.Calculation;
import input.Input;
import enums.Value;

import static enums.Value.*;

public class MainMenu {

    private static final String AGAIN_COMMAND = "yes";
    private static final String HELP_COMMAND = "help";
    private static final String EXIT_COMMAND = "exit";

    Input input = Input.getInstance();
    String value1;
    String value2;
    Calculation calc;
    boolean nextAction;

    public void initMenu() {
        Value valueFromString;
        do {
            //First value
            do {
                value1 = input.getString("Enter first value:");
                valueFromString = getValueFromString(value1);
            } while (valueFromString.equals(UNRECOGNIZED));

            //Calculation type
            showPossibleCalculations(valueFromString);
            calc = getCalculation(valueFromString);

            //Second Value
            do {
                value2 = input.getString("Enter second value:");
                valueFromString = getValueFromString(value2);
                if (!valueFromString.getDescription().equals(calc.getValue2())) {
                    //TODO in case of vector and matrix, inform user of max length accepted (if failed)
                    System.out.println("Should be type of " + calc.getValue2());
                    valueFromString = UNRECOGNIZED;
                }
            } while (valueFromString.equals(UNRECOGNIZED));

            String result = doMatch(calc, value1, value2);
            System.out.printf("%s %s %s = %s", value1, calc.getOperator(), value2, result);

            nextAction = nextAction();
        } while (nextAction);
    }

    private Calculation getCalculation(Value value) {
        int pickOption = input.getInt("Choose calculation", 1, value.getPossibleCalculations().length);
        return value.getPossibleCalculations()[pickOption - 1];
    }

    private String doMatch(Calculation calc, String value1, String value2) {
        switch (calc.getValue1()) {
            case "Number":
                NumberCalculations nc = new NumberCalculations();
                return nc.doCalc(calc, value1, value2);
            case "Vector":
                VectorCalculations vc = new VectorCalculations();
                return vc.doCalc(calc, value1, value2);
            case "Matrix":
                MatrixCalculations mc = new MatrixCalculations();
                return mc.doCalc(calc, value1, value2);
        }
        return "Error. I do not know math...";
    }

    private boolean nextAction() {
        String command = "";
        boolean badCommand = false;
        boolean choice = true;
        do {
            command = input.getString("\nAgain? (yes, exit or help)");
            switch (command) {
                case AGAIN_COMMAND:
                    break;
                case EXIT_COMMAND:
                    choice = false;
                    break;
                case HELP_COMMAND:
                    Help help = new Help();
                    help.showHelp();
                    break;
                default:
                    System.out.println("Unrecognized command, try again:");
                    badCommand = true;
                    break;
            }
        } while (badCommand);
        return choice;
    }
}
