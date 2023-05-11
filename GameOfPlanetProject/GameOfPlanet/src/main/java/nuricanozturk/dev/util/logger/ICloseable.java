package nuricanozturk.dev.util.logger;

import java.io.Closeable;

@FunctionalInterface
public interface ICloseable extends AutoCloseable
{
    void close();
}
