package calculations;

import enums.Calc;

public interface Calculations {

    void doCalc(Calc calc, String value1, String value2);

    default int[] getArrayFromString(String stringArray){
        stringArray = stringArray.substring(1, stringArray.length() - 1);
        String[] split = stringArray.split(",");
        int[] intArray = new int[split.length];
        for (int i = 0; i < intArray.length; i++) {
            int integer = Integer.parseInt(String.valueOf(split[i]));
            intArray[i] = integer;
        }
        return intArray;
    }
}
