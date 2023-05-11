package nuricanozturk.dev.util.logger;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import static nuricanozturk.dev.util.Constants.LOGGER_FILE_NAME;

public class FileLogger implements ILogger
{
    @Override
    public void log(String msg)
    {
        try (FileOutputStream fos = new FileOutputStream(LOGGER_FILE_NAME, true);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8)))
        {
            writer.write(msg);
            writer.newLine();
        } catch (IOException e)
        {

        }
    }

}
