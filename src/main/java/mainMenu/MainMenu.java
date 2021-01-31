package mainMenu;

import input.Input;
import values.Value;

public class MainMenu {

    Input input = Input.getInstance();

    public void init(){
        String stringValue1 = input.getString("Enter first value:");
        System.out.println(stringValue1);
        Value value1 = Value.getValueFromString(stringValue1);
        System.out.println(value1.getDescription());
    }
}
