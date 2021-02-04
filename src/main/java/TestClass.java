import input.Input;

public class TestClass {

    private static final int MAX_VECTOR_LENGTH = 4;
    private static String VECTOR_PATTERN = "";
    private static String MATRIX_PATTERN = "";
    private static final int MATRIX_X = 2;
    private static final int MATRIX_Y = 2;

    static Input input = Input.getInstance();

    public static void main(String[] args) {

        VECTOR_PATTERN = createVectorPattern(MAX_VECTOR_LENGTH);
        MATRIX_PATTERN = createMatrixPattern(MATRIX_X, MATRIX_Y);

        System.out.println(VECTOR_PATTERN);
        System.out.println(MATRIX_PATTERN);
        while (true) {
            String userInput = input.getString("Enter:");
            if (userInput.matches(MATRIX_PATTERN)) {
                System.out.println("Matches");
            } else {
                System.out.println("not matches");
            }
        }
    }

    private static String createMatrixPattern(int x, int y) {
        String pattern = "";
        String openParentheses = "\\[";
        String singleVectorPattern = createVectorPattern(y);
        String closeParentheses = "\\]";
        String coma = "?,";

        StringBuilder patternBuilder = new StringBuilder(pattern);
        patternBuilder.append(openParentheses);

        for (int i = 0; i < y; i++) {
            patternBuilder.append(singleVectorPattern);
            if ((i + 1) < y) {
                patternBuilder.append(coma);
            }
        }
        patternBuilder.append(closeParentheses);
        return patternBuilder.toString();
    }

    private static String createVectorPattern(int maxVectorLength) {
        String pattern = "";
        String openParentheses = "\\[";
        String singleDigit = "[+-]?\\d*(\\.\\d*)?";
        String closeParentheses = "\\]";
        String coma = ",?";

        StringBuilder patternBuiler = new StringBuilder(pattern);

        patternBuiler.append(openParentheses);

        for (int i = 0; i < maxVectorLength; i++) {
            patternBuiler.append(singleDigit);
            if ((i + 1) < maxVectorLength) {
                patternBuiler.append(coma);
            }
        }
        patternBuiler.append(closeParentheses);
        return patternBuiler.toString();
    }

}
