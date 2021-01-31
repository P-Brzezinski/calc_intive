package input;

import java.util.Scanner;

public final class Input {

    private static Input input = null;
    private Scanner scanner;

    private Input() {
        scanner = new Scanner(System.in);
    }

    public static Input getInstance() {
        if (input == null) {
            input = new Input();
        }
        return input;
    }

    public String getString(String askForString) {
        System.out.println(askForString);
        String input = scanner.next();
        scanner.nextLine(); //clear bad input data from keyboard
        return input;
    }

    public int getInt(String askForInt) {
        System.out.println(askForInt);
        while (!scanner.hasNextInt()) {
            System.out.println("Number is required input");
            System.out.println(askForInt);
            scanner.nextLine(); //clear bad input data from keyboard
        }
        return scanner.nextInt();
    }
}
