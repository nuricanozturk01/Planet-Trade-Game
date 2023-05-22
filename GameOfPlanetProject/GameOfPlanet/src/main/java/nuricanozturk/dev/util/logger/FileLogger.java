package nuricanozturk.dev.util.logger;

import nuricanozturk.dev.util.exception.ExceptionUtil;
import nuricanozturk.dev.util.exception.NotCreatedException;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static nuricanozturk.dev.util.Constants.LOGGER_FILE_NAME;
import static nuricanozturk.dev.util.exception.ExceptionUtil.handleException;

public class FileLogger implements ILogger
{
    @Override
    public void log(String msg)
    {
        try (var writer = handleException(this::getWriter, "Writer not created...", NotCreatedException.class))
        {
            writer.write(msg);
            writer.newLine();

        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

    private BufferedWriter getWriter() throws FileNotFoundException
    {
        var fos = new FileOutputStream(LOGGER_FILE_NAME, true);
        return new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
    }
}
