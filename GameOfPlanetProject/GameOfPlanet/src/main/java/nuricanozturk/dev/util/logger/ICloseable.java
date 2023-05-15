package nuricanozturk.dev.util.logger;

@FunctionalInterface
public interface ICloseable extends AutoCloseable
{
    void close();
}
