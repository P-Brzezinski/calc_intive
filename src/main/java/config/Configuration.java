package config;

public class Configuration {
    // old pattern
//    private static final String NUMBER_PATTERN = "^[+-]?([0-9]*[.])?[0-9]+$";
    // new pattern
    private static final String NUMBER_PATTERN = "[+-]?\\d*(\\.\\d*)?";
    private static String VECTOR_PATTERN = "";
    private static final int MAX_VECTOR_LENGTH = 4;
    private static final int MAX_POWER = 128;
    private static String MATRIX_PATTERN = "";
    private static final int MATRIX_X = 2;
    private static final int MATRIX_Y = 2;

    public void initConfig(){
        VECTOR_PATTERN = createVectorPattern(MAX_VECTOR_LENGTH);
        MATRIX_PATTERN = createMatrixPattern(MATRIX_X, MATRIX_Y);
    }

    private String createMatrixPattern(int x, int y) {
        String pattern = "";
        String openParentheses = "\\[";
        String singleVectorPattern = createVectorPattern(x);
        String closeParentheses = "\\]";
        String coma = "?,";

        StringBuilder patternBuilder = new StringBuilder(pattern);
        patternBuilder.append(openParentheses);

        for (int i = 0; i < y; i++) {
            patternBuilder.append(singleVectorPattern);
            if ((i + 1) < y){
                patternBuilder.append(coma);
            }
        }
        patternBuilder.append(closeParentheses);
        return patternBuilder.toString();
    }

    private String createVectorPattern(int maxVectorLength) {
        String pattern = "";
        String openParentheses = "\\[";
        String closeParentheses = "\\]";
        String coma = ",?";

        StringBuilder patternBuiler = new StringBuilder(pattern);

        patternBuiler.append(openParentheses);

        for (int i = 0; i < maxVectorLength; i++) {
            patternBuiler.append(NUMBER_PATTERN);
            if ((i + 1) < maxVectorLength) {
                patternBuiler.append(coma);
            }
        }
        patternBuiler.append(closeParentheses);
        return patternBuiler.toString();
    }

    public static String getNumberPattern() {
        return NUMBER_PATTERN;
    }

    public static String getVectorPattern() {
        return VECTOR_PATTERN;
    }

    public static String getMatrixPattern() {
        return MATRIX_PATTERN;
    }

    public static int getMaxVectorLength() {
        return MAX_VECTOR_LENGTH;
    }

    public static int getMaxPower() {
        return MAX_POWER;
    }
}
