package nuricanozturk.dev.factory;

import nuricanozturk.dev.entity.Galaxy;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.factory.PlanetFactory.createPlanets;
import static nuricanozturk.dev.util.Constants.*;
import static nuricanozturk.dev.util.Util.LOGGER;

public final class GalaxyFactory
{
    private GalaxyFactory() {}

    public static Galaxy createGalaxy()
    {
        var planetCount = getRandomInstance().nextInt(MIN_PLANET_SIZE, MAX_PLANET_SIZE);

        var galaxy = new Galaxy(GALAXY_NAMES.next(), planetCount);

        LOGGER.log("Galaxy " + galaxy.getName() + " is created...\n");

        var planets = createPlanets(planetCount);

        planets.forEach(galaxy::addPlanet);

        return galaxy;
    }
}
