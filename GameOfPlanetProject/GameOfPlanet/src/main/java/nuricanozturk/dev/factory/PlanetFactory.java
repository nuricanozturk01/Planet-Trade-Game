/**
 * Create planet randomly
 */
package nuricanozturk.dev.factory;

import nuricanozturk.dev.entity.Planet;
import nuricanozturk.dev.generator.name.NameGeneratorFactory;
import nuricanozturk.dev.generator.name.NameType;

import java.util.List;
import java.util.stream.IntStream;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.util.Constants.*;

public final class PlanetFactory
{
    private PlanetFactory()
    {
    }

    public static List<Planet> createPlanets(int count)
    {
        return IntStream.range(0, count)
                .mapToObj(PlanetFactory::createPlanet)
                .toList();
    }

    public static Planet createPlanet(int count)
    {
        return new Planet(NameGeneratorFactory.create(NameType.Planet, count + 1),
                getRandomInstance().nextDouble(MIN_UNIT_FUEL_PRICE, MAX_UNIT_FUEL_PRICE),
                getRandomInstance().nextDouble(MIN_TURN_PARKING_PRICE, MAX_TURN_PARKING_PRICE),
                MarketFactory.createMarket());
    }
}