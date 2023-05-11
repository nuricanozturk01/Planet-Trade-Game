package nuricanozturk.dev.util.logger;

public class Logger
{
    private static Logger ms_logger;
    private final ILogger m_fileLogger;
    private final ILogger m_dbLogger;
    private Logger() {
        m_fileLogger = new FileLogger();
        m_dbLogger = new MySqlLogger();
    }
    public static Logger getLoggerInstance()
    {
        return ms_logger == null ? new Logger() : ms_logger;
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
