package nuricanozturk.dev.util;

import nuricanozturk.dev.util.logger.ILogger;

import static nuricanozturk.dev.util.Constants.LOGGER_FILE_NAME;
import static nuricanozturk.dev.util.logger.Logger.getLoggerInstance;

public class Util
{
    public static final ILogger LOGGER = getLoggerInstance(LOGGER_FILE_NAME).getDefaultLogger();

    public static double getBigFormattedNumber(double number)
    {
        return Math.round(number * 100.0) / 100.0;
    }
}