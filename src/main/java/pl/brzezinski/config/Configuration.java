package pl.brzezinski.config;

public class Configuration {

    public static final long MAX_LINES_IN_FILE = 5;
    public static final String NUMBER_PATTERN = "[+-]?\\d*(\\.\\d*)?";
    private static String VECTOR_PATTERN = "";
    public static final int MAX_VECTOR_LENGTH = 4;
    public static final int MAX_POWER = 128;
    private static String MATRIX_PATTERN = "";
    public static final int MATRIX_X = 4;
    public static final int MATRIX_Y = 4;

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

    public static String getMatrixPattern() {
        return MATRIX_PATTERN;
    }

    public static String getVectorPattern() {
        return VECTOR_PATTERN;
    }
}
