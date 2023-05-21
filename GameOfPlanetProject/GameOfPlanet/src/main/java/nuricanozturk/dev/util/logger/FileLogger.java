package nuricanozturk.dev.util.logger;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static nuricanozturk.dev.util.Constants.LOGGER_FILE_NAME;

public class FileLogger implements ILogger
{
    @Override
    public void log(String msg)
    {
        try (var writer = getWriter())
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
