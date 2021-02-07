package calculations;

import enums.Calculation;

import java.math.BigDecimal;

public interface Calculations {

    String doCalc(Calculation calc, String value1, String value2);

    default double[] getVectorFromString(String stringArray) {
        stringArray = stringArray.substring(1, stringArray.length() - 1);
        if (stringArray.isEmpty())
            return new double[0];

        String[] split = stringArray.split(",");
        double[] newArray = new double[split.length];
        for (int i = 0; i < newArray.length; i++) {
            BigDecimal x = BigDecimal.valueOf(Double.parseDouble(String.valueOf(split[i])));
            newArray[i] = x.doubleValue();
        }
        return newArray;

    }

    default double[][] getMatrixFromString(String stringMatrix) {
        stringMatrix = stringMatrix.substring(2, stringMatrix.length() - 2);
        if (stringMatrix.isEmpty())
            return new double[0][0];

        String[] strings = stringMatrix.split("]\\[");
        double[][] result = new double[strings.length][];
        for (int i = 0; i < result.length; i++) {
            String[] tempStringArray = strings[i].split(",");
            double[] singleVector = new double[tempStringArray.length];
            for (int j = 0; j < singleVector.length; j++) {
                singleVector[j] = Double.parseDouble(tempStringArray[j]);
            }
            result[i] = singleVector;
        }
        return result;
    }
}
