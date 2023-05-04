package nuricanozturk.dev.factory;

import nuricanozturk.dev.entity.Galaxy;
import nuricanozturk.dev.generator.name.NameType;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.factory.PlanetFactory.createPlanets;
import static nuricanozturk.dev.generator.name.NameGeneratorFactory.create;
import static nuricanozturk.dev.util.Constants.MAX_PLANET_SIZE;
import static nuricanozturk.dev.util.Constants.MIN_PLANET_SIZE;

public final class GalaxyFactory
{

    private GalaxyFactory()
    {
    }

    /**
     * Create galaxy with planets, markets etc...
     *
     * @return
     */
    public static Galaxy createGalaxy()
    {
        System.out.println("Galaxy Created....");
        var planetCount = getRandomInstance().nextInt(MIN_PLANET_SIZE, MAX_PLANET_SIZE);

        var galaxy = new Galaxy(create(NameType.Galaxy, 1), planetCount);

        var planets = createPlanets(planetCount);

        planets.forEach(galaxy::addPlanet);

        return galaxy;
    }
}
