import input.Input;

public class TestClass {

    private static final int MAX_VECTOR_LENGTH = 1;
    private static String VECTOR_PATTERN = "";

    static Input input = Input.getInstance();

    public static void main(String[] args) {

        createVectorPattern();
        double text = Double.parseDouble(".4");
        System.out.println(text);
        System.out.println(VECTOR_PATTERN);
        while (true) {
            String userInput = input.getString("Enter:");
            if (userInput.matches(VECTOR_PATTERN)) {
                System.out.println("Matches");
            } else {
                System.out.println("not matches");
            }
        }
    }

    private static void createVectorPattern() {
        String pattern = "";
        String openParentheses = "\\[";
        String singleDigit = "[+-]?\\d*(\\.\\d*)?";
        String closeParentheses = "\\]";
        String coma = ",?";

        StringBuilder buildPattern = new StringBuilder(pattern);

        buildPattern.append(openParentheses);

        for (int j = 0; j < MAX_VECTOR_LENGTH; j++) {
            buildPattern.append(singleDigit);
            if ((j + 1) < MAX_VECTOR_LENGTH) {
                buildPattern.append(coma);
            }
        }
        buildPattern.append(closeParentheses);
        VECTOR_PATTERN = buildPattern.toString();
    }

}
