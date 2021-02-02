package config;

public class Configuration {

    private static String NUMBER_PATTERN = "^[+-]?([0-9]*[.])?[0-9]+$";
    private static String VECTOR_PATTERN = "";
    private static final int MAX_VECTOR_LENGTH = 4;

    {
        String pattern = "";
        String openParentheses = "\\[";
        String singleDigit = "\\d+?";
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

    public static String getNumberPattern() {
        return NUMBER_PATTERN;
    }

    public static String getVectorPattern() {
        return VECTOR_PATTERN;
    }

    public static int getMaxVectorLength() {
        return MAX_VECTOR_LENGTH;
    }
}
