package nuricanozturk.dev.factory;

import nuricanozturk.dev.entity.SpaceShip;

import java.util.List;
import java.util.stream.IntStream;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.util.Constants.*;
import static nuricanozturk.dev.util.Util.LOGGER;
import static nuricanozturk.dev.util.Util.getBigFormattedNumber;

public class SpaceshipFactory
{
    private SpaceshipFactory()
    {
    }

    public static List<SpaceShip> createSpaceships()
    {
        var list = IntStream
                .range(0, getRandomInstance().nextInt(MIN_SPACESHIP_COUNT, MAX_SPACESHIP_COUNT))
                .mapToObj(SpaceshipFactory::createSpaceShip)
                .toList();

        LOGGER.log("\n\n");

        return list;
    }

    private static int createSpeed(double price)
    {
        var range1 = MIN_SPACESHIP_COST + 10_000D;
        var range2 = MIN_SPACESHIP_COST + 20_000D;
        var range3 = (MIN_SPACESHIP_COST + MAX_SPACESHIP_COST) / 2;
        var range4 = MAX_SPACESHIP_COST - 10_000D;
        var range5 = MAX_SPACESHIP_COST - 5_000D;

        if (price <= range1)
            return getRandomInstance().nextInt(MIN_SPEED, MIN_SPEED + 200);
        if (price <= range2)
            return getRandomInstance().nextInt(MIN_SPEED + 200, MIN_SPEED + 400);
        if (price <= range3)
            return getRandomInstance().nextInt(MIN_SPEED + 400, MIN_SPEED + 600);
        if (price <= range4)
            return getRandomInstance().nextInt(MIN_SPEED + 600, MIN_SPEED + 800);
        if (price <= range5)
            return getRandomInstance().nextInt(MIN_SPEED + 800, MAX_SPEED);

        return MAX_SPEED;
    }

    private static SpaceShip createSpaceShip(int ignore)
    {
        var price = getBigFormattedNumber(getRandomInstance().nextDouble(MIN_SPACESHIP_COST, MAX_SPACESHIP_COST));
        var fuelCapacity = getRandomInstance().nextInt(MIN_FUEL_CAPACITY, MAX_FUEL_CAPACITY);
        var speed = createSpeed(price);

        var volumeCapacity = getRandomInstance().nextInt(MIN_VOLUME, MAX_VOLUME);

        var spaceShip = new SpaceShip.Builder()
                .setName(SPACESHIP_NAMES.next())
                .setFuelCapacity(fuelCapacity)
                .setFuelUsagePerLightYear(getRandomInstance().nextInt(10, 20))
                .setVolumeCapacity(volumeCapacity)
                .setCurrentFuel(DEFAULT_INIT_FUEL_CAPACITY)
                .setPrice(price)
                .setSpeed(speed)
                .build();

        LOGGER.log(spaceShip + " is created...");

        return spaceShip;
    }
}