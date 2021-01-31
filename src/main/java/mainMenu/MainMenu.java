package mainMenu;

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
        do {
            value1 = input.getString("Enter first value:");
            valueFromString = getValueFromString(value1);
        } while (valueFromString.equals(UNRECOGNIZED));

        showPossibleCalcs(valueFromString);
        calc = getAction(valueFromString);

        do {
            value2 = input.getString("Enter second value:");
            valueFromString = getValueFromString(value2);
        } while (valueFromString.equals(UNRECOGNIZED));

        System.out.println(value1 + ", " + value2 + ", " + calc.getDescription());
    }

    private Calc getAction(Value value) {
        int pickOption = input.getInt("Choose calculation:");
        return value.getPossibleCalcs()[pickOption - 1];
    }
}
