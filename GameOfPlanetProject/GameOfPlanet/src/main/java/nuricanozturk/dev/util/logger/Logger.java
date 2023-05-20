package nuricanozturk.dev.util.logger;

import nuricanozturk.dev.util.exception.NotCreatedException;

import java.nio.file.Files;
import java.nio.file.Path;

import static nuricanozturk.dev.util.exception.ExceptionUtil.doForRunnable;

public class Logger
{
    private static Logger ms_logger;
    private final ILogger m_fileLogger;

    private Logger(String fileName)
    {
        doForRunnable(() -> Files.deleteIfExists(Path.of(fileName)), "File does not exists!",
                NotCreatedException.class);

        m_fileLogger = new FileLogger();
    }

    public static Logger getLoggerInstance(String fileName)
    {
        return ms_logger == null ? new Logger(fileName) : ms_logger;
    }

    public ILogger getDefaultLogger()
    {
        return m_fileLogger;
    }

    public ILogger getLogger(LoggerType loggerType)
    {
        return switch (loggerType)
        {
            //....
            default -> m_fileLogger;
        };
    }
}
