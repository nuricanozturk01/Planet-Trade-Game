package nuricanozturk.dev.config;

import java.security.SecureRandom;
import java.util.Random;

/**
 * SecureRandom class represented with Singleton Pattern
 */
public final class RandomConfig
{
    private static final Random m_secureRandom = new SecureRandom();

    private RandomConfig()
    {
    }

    public static Random getRandomInstance()
    {
        return m_secureRandom;
    }
}
