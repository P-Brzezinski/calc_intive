import input.Input;
import mainMenu.MainMenu;

public class Main {

    public static void main(String[] args) {

//        Input input = Input.getInstance();
//        String pattern = buildPattern(4);
//        System.out.println(pattern);
//
//        while (true) {
//
//            String getS = input.getString("---> ");
//            if (getS.matches(pattern)) {
//                System.out.println(getS);
//            } else {
//                System.out.println("No match");
//            }
//
//        }

        MainMenu mainMenu = new MainMenu();
        mainMenu.init();
    }

//    public static String buildPattern(int i) {
//        String pattern = "";
//        String openParentheses = "\\[";
//        String singleDigit = "\\d+?";
//        String closeParentheses = "\\]";
//        String coma = ",?";
//
//        StringBuilder buildPattern = new StringBuilder(pattern);
//
//        buildPattern.append(openParentheses);
//
//        for (int j = 0; j < i; j++) {
//            buildPattern.append(singleDigit);
//            if ((j + 1) < i){
//                buildPattern.append(coma);
//            }
//        }
//        buildPattern.append(closeParentheses);
//        return buildPattern.toString();
//    }
}
