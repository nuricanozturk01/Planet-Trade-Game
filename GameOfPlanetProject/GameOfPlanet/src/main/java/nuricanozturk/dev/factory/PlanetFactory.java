/**
 * Create planet randomly
 */
package nuricanozturk.dev.factory;

import nuricanozturk.dev.entity.Planet;
import nuricanozturk.dev.generator.name.NameGeneratorFactory;
import nuricanozturk.dev.generator.name.NameType;
import nuricanozturk.dev.util.Constants;

import java.util.List;
import java.util.stream.IntStream;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.factory.MarketFactory.createMarket;
import static nuricanozturk.dev.util.Constants.*;
import static nuricanozturk.dev.util.Util.LOGGER;
import static nuricanozturk.dev.util.Util.getBigFormattedNumber;

public final class PlanetFactory
{
    private PlanetFactory()
    {
    }

    public static List<Planet> createPlanets(int count)
    {
       LOGGER.log("-----Planets are creating...\n");
        return IntStream
                .range(0, count)
                .mapToObj(PlanetFactory::createPlanet)
                .toList();
    }

    public static Planet createPlanet(int count)
    {
        var planet = new Planet.Builder()
                .setName(PLANET_NAMES.next())
                .setUnitFuelPrice(getBigFormattedNumber(getRandomInstance().nextDouble(MIN_UNIT_FUEL_PRICE, MAX_UNIT_FUEL_PRICE)))
                .setParkingPricePerTurn(getBigFormattedNumber(getRandomInstance().nextDouble(MIN_TURN_PARKING_PRICE, MAX_TURN_PARKING_PRICE)))
                .setMarket(createMarket())
                .build();
        LOGGER.log("\n");
        LOGGER.log("Planet " + planet.getName() + " is created...\n");
        return planet;
    }
}