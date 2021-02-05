package mainMenu;

import config.Configuration;

public class Help {

    public void showHelp() {
        System.out.println("Please enter values without white spaces.");
        System.out.println("Correct values for numbers: 1, 2.3, 5111, -43, .6");
        System.out.println("Incorrect values:  1, 2. 3, 5 111");
        System.out.println("For floating point use '.' not ','");
        System.out.println("Correct values for vectors: [1], [1,2], [1,2,3]");
        System.out.println("Incorrect values: [], [1,]");
        System.out.println("Maximum numbers in vector is: " + Configuration.getMaxVectorLength());
        System.out.println("Correct values for matrix: [[1][2]], [[1,2][2,3]], [[.3,-3][.2,3]]");
        System.out.println("Maximum x & y values for matrix are: " + Configuration.getMatrixX() + " " + Configuration.getMatrixY());
        System.out.println();
    }
}
