package pl.brzezinski.configuration;

public class Configuration {

    //Database configuration
    public static final String FILE_SERVICE = "fileService";
    public static final String H2_SERVICE = "H2Service";
    public static final String DB = FILE_SERVICE;

    //FileService configuration
    public static final String PATH = "./calculation_sheets/";
    public static final String FILE_NAME = "calculations_history.txt";
    public static final long MAX_LINES_IN_FILE = 5;

    //CalculationType, Value and Calculations configuration
    public static final String NUMBER_PATTERN = "[+-]?\\d*(\\.\\d*)?";
    private static String VECTOR_PATTERN = "";
    public static final int MAX_VECTOR_LENGTH = 4;
    public static final int MAX_POWER = 128;
    private static String MATRIX_PATTERN = "";
    public static final int MATRIX_X = 4;
    public static final int MATRIX_Y = 4;

    //DateTimePattern
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    //patterns init
    public void initPatterns(){
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
