package pl.brzezinski.input;

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
            System.out.println("Number is required input.");
            System.out.println(askForInt);
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    public int getInt(String sPrompt, int nLow, int nHigh) {
        int nInput;
        do {
            System.out.printf("%s (%d-%d): ", sPrompt, nLow, nHigh);
            while (!scanner.hasNextInt()) {
                System.out.println("Number is required input.");
                System.out.printf("%s (%d-%d): ", sPrompt, nLow, nHigh);
                scanner.nextLine();
            }
            nInput = scanner.nextInt();
            if (nInput >= nLow && nInput <= nHigh)
                break;
            System.out.println("Value out of range.");
            scanner.nextLine();
        } while (true);
        return nInput;
    }
}
