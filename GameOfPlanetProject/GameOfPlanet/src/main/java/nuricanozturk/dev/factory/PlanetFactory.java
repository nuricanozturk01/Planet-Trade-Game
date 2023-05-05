/**
 * Create planet randomly
 */
package nuricanozturk.dev.factory;

import nuricanozturk.dev.entity.Planet;
import nuricanozturk.dev.generator.name.NameType;

import java.util.List;
import java.util.stream.IntStream;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.factory.MarketFactory.createMarket;
import static nuricanozturk.dev.generator.name.NameGeneratorFactory.create;
import static nuricanozturk.dev.util.Constants.*;
import static nuricanozturk.dev.util.Util.getBigFormattedNumber;
import static nuricanozturk.dev.util.Util.getFormattedNumber;

public final class PlanetFactory {
    private PlanetFactory() {
    }

    public static List<Planet> createPlanets(int count)
    {
        System.out.println("Planets are created");
        return IntStream
                .range(0, count)
                .mapToObj(PlanetFactory::createPlanet)
                .toList();
    }

    public static Planet createPlanet(int count)
    {
        return new Planet.Builder()
                .setName(create(NameType.Planet, count + 1))
                .setUnitFuelPrice(getBigFormattedNumber(getRandomInstance().nextDouble(MIN_UNIT_FUEL_PRICE, MAX_UNIT_FUEL_PRICE)))
                .setParkingPricePerTurn(getBigFormattedNumber(getRandomInstance().nextDouble(MIN_TURN_PARKING_PRICE, MAX_TURN_PARKING_PRICE)))
                .setMarket(createMarket())
                .build();
    }
}