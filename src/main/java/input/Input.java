package input;

import java.util.Scanner;

public final class Input {

    private static Input input = null;
    private Scanner scanner;

    private Input() {
        scanner = new Scanner(System.in);
    }

    public static Input getInstance(){
        if (input == null){
            input = new Input();
        }
        return input;
    }
}
