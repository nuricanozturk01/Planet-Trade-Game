package nuricanozturk.dev.util.logger;

import nuricanozturk.dev.util.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static nuricanozturk.dev.util.Constants.LOGGER_FILE_NAME;

public class Logger
{
    private static Logger ms_logger;
    private final ILogger m_fileLogger;
    private final ILogger m_dbLogger;
    private Logger(String fileName) {
        try
        {
            Files.deleteIfExists(Path.of(fileName));
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        m_fileLogger = new FileLogger();
        m_dbLogger = new MySqlLogger();
    }

    public static Logger getLoggerInstance(String fileName)
    {
        return ms_logger == null ? new Logger(fileName) : ms_logger;
    }
    public ILogger getLogger(LoggerType loggerType)
    {
        return switch (loggerType)
        {
            case FILE -> m_fileLogger;

            default -> m_dbLogger;
        };
    }
}
