package calculations;

import config.Configuration;
import enums.Calculation;

import java.math.BigDecimal;
import java.util.Arrays;

public interface Calculations {

    String doCalc(Calculation calc, String value1, String value2);

    default double[] getArrayFromString(String stringArray) {
        //take out square brackets
        stringArray = stringArray.substring(1, stringArray.length() - 1);
        if (stringArray.isEmpty()) {
            return new double[0];
        } else {
            String[] split = stringArray.split(",");
            double[] newArray = new double[split.length];
            for (int i = 0; i < newArray.length; i++) {
                BigDecimal x = BigDecimal.valueOf(Double.parseDouble(String.valueOf(split[i])));
                newArray[i] = x.doubleValue();
            }
            return newArray;
        }
    }

    //TODO
    default double[][] getMatrixFromString(String matrixArray) {
        matrixArray = matrixArray.substring(1, matrixArray.length() - 1);
        matrixArray = matrixArray.replace("][", "]|[");
        String[] split = matrixArray.split("\\|");
        double[][] result = new double[Configuration.getMatrixX()][Configuration.getMatrixY()];

        for (int i = 0; i < result.length; i++) {
            double[] innerTable = result[i];
            for (int j = 0; j < innerTable.length; j++) {
                innerTable[j] = Double.parseDouble(split[j]);
            }
        }
        return result;
    }
}
