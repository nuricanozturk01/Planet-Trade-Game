package nuricanozturk.dev.util;

import nuricanozturk.dev.util.logger.ILogger;
import nuricanozturk.dev.util.logger.LoggerType;
import project.gameengine.base.Action;

import static nuricanozturk.dev.action.ActionGenerator.getActionGeneratorInstance;
import static nuricanozturk.dev.util.Constants.LOGGER_FILE_NAME;
import static nuricanozturk.dev.util.logger.Logger.getLoggerInstance;

public class Util {
    public static final ILogger LOGGER = getLoggerInstance(LOGGER_FILE_NAME).getLogger(LoggerType.FILE);
    public static final LinkedList<Action> actions = getActionGeneratorInstance().getActionLinkedList();

    public static double getBigFormattedNumber(double number) {
        return Math.round(number * 100.0) / 100.0;
    }
}