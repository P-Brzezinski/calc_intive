package mainMenu;

import calculations.MatrixCalculations;
import calculations.NumberCalculations;
import calculations.VectorCalculations;
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

    public void init() {
        Value valueFromString;
        do {
            //First value
            do {
                value1 = input.getString("Enter first value:");
                valueFromString = getValueFromString(value1);
            } while (valueFromString.equals(UNRECOGNIZED));

            //Calculation type
            showPossibleCalcs(valueFromString);
            calc = getAction(valueFromString);

            //Second Value
            do {
                value2 = input.getString("Enter second value:");
                valueFromString = getValueFromString(value2);
                if (!valueFromString.getDescription().equals(calc.getValue2())) {
                    System.out.println("Wrong value, should be of type " + calc.getValue2());
                    valueFromString = UNRECOGNIZED;
                }
            } while (valueFromString.equals(UNRECOGNIZED));

            //Result
            System.out.println(value1 + " " + calc.getDescription() + " " + value2);
            System.out.print("Result is: ");
            doMatch(calc, value1, value2);

            nextAction = nextAction();
        } while (nextAction);
    }

    private Calculation getAction(Value value) {
        int pickOption = input.getInt("Choose calculation", 1, value.getPossibleCalcs().length);
        return value.getPossibleCalcs()[pickOption - 1];
    }

    private void doMatch(Calculation calc, String value1, String value2) {
        switch (calc.getValue1()) {
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

    private boolean nextAction() {
        String command = input.getString("Again?\nType: yes, exit or help");
        System.out.println("");
        boolean badCommand = false;
        boolean choice = true;
        do {
            switch (command) {
                case AGAIN_COMMAND:
                    choice = true;
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
