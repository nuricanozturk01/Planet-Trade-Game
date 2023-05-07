package nuricanozturk.dev.util;

public class Util
{
    public static double getBigFormattedNumber(double number)
    {
        return Math.round(number * 100.0) / 100.0;
    }
}