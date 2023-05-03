/**
 * Create planet randomly
 */
package nuricanozturk.dev.factory;

import nuricanozturk.dev.entity.Planet;
import nuricanozturk.dev.generator.name.NameType;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public final class PlanetFactory
{
    private static final SecureRandom m_random = new SecureRandom();
    private static int NAME_COUNT = 1;
    private static int COMMODITIES_COUNT = 1;
    private PlanetFactory()
    {
    }

    public static List<Planet> createPlanets(int count)
    {
        var list = new ArrayList<Planet>();


        NAME_COUNT++;
        COMMODITIES_COUNT = 1;

        return list;
    }

    private static String createName(NameType nameType)
    {
        return nameType.getName() + "-" + NAME_COUNT;
    }

    private static int createPlanet()
    {
        return 0;
    }
}
