package nuricanozturk.dev.util.logger;

import java.nio.file.Files;
import java.nio.file.Path;

import static nuricanozturk.dev.util.ExceptionUtil.handleFileException;

public class Logger
{
    private static Logger ms_logger;
    private final ILogger m_fileLogger;

    private Logger(String fileName)
    {
        handleFileException("File does not exists!", () -> Files.deleteIfExists(Path.of(fileName)));
        m_fileLogger = new FileLogger();
    }

    public static Logger getLoggerInstance(String fileName)
    {
        return ms_logger == null ? new Logger(fileName) : ms_logger;
    }

    public ILogger getLogger(LoggerType loggerType)
    {
        return switch (loggerType)
        {
            default -> m_fileLogger;
        };
    }
}
