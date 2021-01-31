package mainMenu;

import input.Input;

public class MainMenu {

    Input input = Input.getInstance();

    public void init(){
        String stringValue1 = input.getString("Enter first value:");
        System.out.println(stringValue1);
    }
}
