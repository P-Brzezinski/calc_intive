package mainMenu;

import enums.Calc;
import input.Input;
import enums.Value;

import static enums.Value.*;

public class MainMenu {

    Input input = Input.getInstance();

    public void init() {
        String userInput;
        while (true) {
            userInput = input.getString("\nEnter first value:");
            if (userInput.equals("exit"))
                break;
            Value value1 = getValueFromString(userInput);

            switch (value1) {
                case NUMBER:
                    showPossibleActions(NUMBER);
                    break;
                case VECTOR:
                    showPossibleActions(VECTOR);
                    break;
                case MATRIX:
                    showPossibleActions(MATRIX);
                    break;
                case UNRECOGNIZED:
                    System.out.println(UNRECOGNIZED.getDescription());
                    break;
            }
        }
    }
}
