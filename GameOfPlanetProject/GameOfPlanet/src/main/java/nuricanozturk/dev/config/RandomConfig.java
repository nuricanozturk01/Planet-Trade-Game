package nuricanozturk.dev.config;

import java.security.SecureRandom;

/**
 * SecureRandom class represented with Singleton Pattern
 */
public final class RandomConfig
{
    private final static SecureRandom ms_secureRandom = new SecureRandom();

    private RandomConfig() {}

    public static SecureRandom getRandomInstance()
    {
        return ms_secureRandom;
    }
}
