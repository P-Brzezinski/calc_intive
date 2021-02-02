package config;

public class Configuration {

    public static String VECTOR_PATTERN = "";
    public static final int MAX_VECTOR_LENGTH = 4;

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
            if ((j + 1) < MAX_VECTOR_LENGTH){
                buildPattern.append(coma);
            }
        }

        buildPattern.append(closeParentheses);

        VECTOR_PATTERN = buildPattern.toString();
    }
}
