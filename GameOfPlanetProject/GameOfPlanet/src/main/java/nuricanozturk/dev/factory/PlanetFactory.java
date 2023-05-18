package nuricanozturk.dev.factory;

import nuricanozturk.dev.entity.Planet;

import java.util.List;
import java.util.stream.IntStream;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.factory.DistanceBetweenPlanetsFactory.createDistanceBetweenPlanets;
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

        var planets = IntStream.range(0, count).mapToObj(PlanetFactory::createPlanet).toList();

        planets.forEach(p -> createDistanceBetweenPlanets(planets, p));

        return planets;
    }

    public static Planet createPlanet(int count)
    {
        var planet = new Planet.Builder()
                .setName(PLANET_NAMES.next())
                .setUnitFuelPrice(getBigFormattedNumber(getRandomInstance().nextDouble(MIN_UNIT_FUEL_PRICE, MAX_UNIT_FUEL_PRICE)))
                .setParkingPricePerTurn(getBigFormattedNumber(getRandomInstance().nextDouble(MIN_TURN_PARKING_PRICE, MAX_TURN_PARKING_PRICE)))
                .setMarket(createMarket())
                .build();

        LOGGER.log("\nPlanet " + planet.getName() + " is created...\n");

        return planet;
    }
}