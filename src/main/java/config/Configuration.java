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
    private static final int MATRIX_X = 4;
    private static final int MATRIX_Y = 4;

    public void initConfig(){
        VECTOR_PATTERN = createVectorPattern(MAX_VECTOR_LENGTH);
        MATRIX_PATTERN = createMatrixPattern(MATRIX_X, MATRIX_Y);
    }

    private String createVectorPattern(int maxVectorLength) {
        String pattern = "";
        StringBuilder patternBuilder = new StringBuilder(pattern);
        patternBuilder.append("\\[");
        for (int i = 0; i < maxVectorLength; i++) {
            patternBuilder.append(NUMBER_PATTERN);
            if ((i + 1) < maxVectorLength) {
                patternBuilder.append(",?");
            }
        }
        patternBuilder.append("\\]");
        return patternBuilder.toString();
    }

    private String createMatrixPattern(int x, int y) {
        String pattern = "";
        String singleVectorPattern = createVectorPattern(y);
        StringBuilder patternBuilder = new StringBuilder(pattern);
        patternBuilder.append("\\[");

        for (int i = 0; i < x; i++) {
            patternBuilder.append("(");
            patternBuilder.append(singleVectorPattern);
            patternBuilder.append(")?");
        }
        patternBuilder.append("\\]");
        return patternBuilder.toString();
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

    public static int getMatrixX() {
        return MATRIX_X;
    }

    public static int getMatrixY() {
        return MATRIX_Y;
    }
}
