package nuricanozturk.dev.factory;

public class NotCreatedException extends RuntimeException
{
    public NotCreatedException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NotCreatedException(String message)
    {
        super(message);
    }
}
