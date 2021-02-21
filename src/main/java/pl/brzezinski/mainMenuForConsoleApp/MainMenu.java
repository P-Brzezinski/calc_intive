package pl.brzezinski.mainMenuForConsoleApp;

import pl.brzezinski.calculations.MatrixCalculations;
import pl.brzezinski.calculations.NumberCalculations;
import pl.brzezinski.calculations.VectorCalculations;
import pl.brzezinski.enums.CalculationType;
import pl.brzezinski.exceptions.MatrixException;
import pl.brzezinski.exceptions.VectorException;
import pl.brzezinski.input.Input;
import pl.brzezinski.enums.Value;

import static pl.brzezinski.enums.Value.*;

public class MainMenu {

    private static final String AGAIN_COMMAND = "yes";
    private static final String HELP_COMMAND = "help";
    private static final String EXIT_COMMAND = "exit";

    private final Input input = Input.getInstance();

    public void initMenu() {
        Value valueFromString;
        boolean nextAction;
        do {
            //First value
            String value1;
            do {
                value1 = input.getString("Enter first value:");
                valueFromString = getValueFromString(value1);
            } while (valueFromString.equals(UNRECOGNIZED));

            //Calculation type
            showPossibleCalculations(valueFromString);
            CalculationType calc = getCalculation(valueFromString);

            //Second Value
            String value2;
            do {
                value2 = input.getString("Enter second value:");
                valueFromString = getValueFromString(value2);
                if (!valueFromString.getDescription().equals(calc.getValueB())) {
                    //TODO in case of vector and matrix, inform user of max length accepted (if failed)
                    System.out.println("Should be type of " + calc.getValueB());
                    valueFromString = UNRECOGNIZED;
                }
            } while (valueFromString.equals(UNRECOGNIZED));

            String result = null;
            try {
                result = doMatch(calc, value1, value2);
            } catch (VectorException | MatrixException e) {
                e.printStackTrace();
            }
            System.out.printf("%s %s %s = %s", value1, calc.getOperator(), value2, result);

            nextAction = nextAction();
        } while (nextAction);
    }

    private CalculationType getCalculation(Value value) {
        int pickOption = input.getInt("Choose calculation", 1, value.getPossibleCalculations().length);
        return value.getPossibleCalculations()[pickOption - 1];
    }

    private String doMatch(CalculationType calculationType, String a, String b) throws VectorException, MatrixException {
        switch (calculationType.getValueA()) {
            case "Number":
                NumberCalculations nc = new NumberCalculations();
                return nc.doCalculation(calculationType, a, b);
            case "Vector":
                VectorCalculations vc = new VectorCalculations();
                return vc.doCalculation(calculationType, a, b);
            case "Matrix":
                MatrixCalculations mc = new MatrixCalculations();
                return mc.doCalculation(calculationType, a, b);
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
