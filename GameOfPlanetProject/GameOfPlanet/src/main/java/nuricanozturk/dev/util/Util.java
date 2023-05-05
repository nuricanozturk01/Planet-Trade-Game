package nuricanozturk.dev.util;

import java.text.DecimalFormat;

public class Util {
    private final static DecimalFormat NUMBER_FORMAT = new DecimalFormat("00,000");

    public static double getFormattedNumber(double number) {
        return number == 0 ? 0.0 : Double.parseDouble(NUMBER_FORMAT.format(number));
    }
    public static double getBigFormattedNumber(double number)
    {
        return Math.round(number * 100.0) / 100.0;
    }
}