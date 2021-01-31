package mainMenu;

import enums.Calc;
import input.Input;
import enums.Value;

import static enums.Value.*;

public class MainMenu {

    Input input = Input.getInstance();

    public void init() {
        String firstValue = input.getString("\nEnter first value:");
        Value firsValueFromString = getValueFromString(firstValue);
        showPossibleActionsForValue(firsValueFromString);
        Calc calc = getAction(firsValueFromString);
        String secondValue = input.getString("Enter second value:");
        System.out.println(firstValue + ", " + secondValue + ", " + calc.getDescription());


    }

    private Calc getAction(Value value) {
        int pickOption = input.getInt("Choose option:");
        return value.getPossibleCalcs()[pickOption - 1];
    }

    private void showPossibleActionsForValue(Value valueFromString) {
        switch (valueFromString) {
            case NUMBER:
                showPossibleCalcs(NUMBER);
                break;
            case VECTOR:
                showPossibleCalcs(VECTOR);
                break;
            case MATRIX:
                showPossibleCalcs(MATRIX);
                break;
            case UNRECOGNIZED:
                System.out.println(UNRECOGNIZED.getDescription());
                break;
        }
    }
}
