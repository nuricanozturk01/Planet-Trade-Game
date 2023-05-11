package nuricanozturk.dev.util;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public final class ExceptionUtil
{
    public static <T extends RuntimeException> void throwException(String msg, Class<T> cls, Throwable ex)
    {
        try
        {
            throw cls.getConstructor(String.class, Throwable.class).newInstance(msg, ex);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e)
        {
            throw new UnsupportedOperationException("Unsupported Exception...");
        }
    }

    /*
        Create exception class using reflection with msg constructor.
     */
    public static <T extends RuntimeException> void handleException(String msg, Class<T> $class,
                                                                    Runnable actionRunnable, Runnable caughtRunnable)
    {
        try
        {
            actionRunnable.run();
        } catch (Throwable ex)
        {
            caughtRunnable.run();
            throwException(msg, $class, ex);
        }
    }


    public static <T, R extends RuntimeException> T handleException(Supplier<T> supplier, Class<R> cls, String msg)
    {
        T val = null;
        try
        {
            val = supplier.get();
        } catch (Throwable ex)
        {
            throwException(msg, cls, ex);
        }

        return val;
    }

}