package nuricanozturk.dev.factory;

import nuricanozturk.dev.entity.SpaceShip;
import nuricanozturk.dev.generator.name.NameType;

import java.util.List;
import java.util.stream.IntStream;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.generator.name.NameGeneratorFactory.create;
import static nuricanozturk.dev.util.Constants.*;
import static nuricanozturk.dev.util.Util.getBigFormattedNumber;
import static nuricanozturk.dev.util.Util.getFormattedNumber;

public final class SpaceshipFactory {
    private SpaceshipFactory() {}

    public static List<SpaceShip> createSpaceships()
    {
        System.out.println("Spaceships are created!");
        return IntStream
                .range(0, getRandomInstance().nextInt(MIN_SPACESHIP_COUNT, MAX_SPACESHIP_COUNT))
                .mapToObj(SpaceshipFactory::createSpaceShip)
                .toList();
    }
    /*

     */
    private static int createSpeed(double price)
    {
        // MIN: 3_500
        // MAX 50_000
        var range1 = MIN_SPACESHIP_COST + 10_000D; // 13_500
        var range2 = MIN_SPACESHIP_COST + 20_000D; // 23_500
        var range3 = (MIN_SPACESHIP_COST + MAX_SPACESHIP_COST) / 2; // 26_750
        var range4 = MAX_SPACESHIP_COST - 10_000D; // 40_000
        var range5 = MAX_SPACESHIP_COST - 5_000D; // 45_000

        if (price <= range1) // price < 13_500
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

    private static int createFuelUsagePerLightYear(int fuelCapacity, double speed, double price)
    {
        if (fuelCapacity < 1000 || fuelCapacity > 2000) {
        System.out.println("Invalid fuel capacity. Please enter a value between 1000 and 2000.");
        return -1;
    }

        // Check if speed and price are valid
        if (speed <= 0 || price <= 0) {
            System.out.println("Speed and price must be positive and non-zero.");
            return -1;
        }

        double maxDistance = fuelCapacity * speed;
        double fuelConsumption = price / speed;
        double fuelUsagePerLightYear = (maxDistance * fuelConsumption) / 9.461e+12;

        // Check if fuel usage is valid
        if (fuelUsagePerLightYear <= 0) {
            System.out.println("Invalid fuel usage per light year.");
            return -1;
        }

        return (int) fuelUsagePerLightYear;
    }
    private static SpaceShip createSpaceShip(int i)
    {
        var price = getBigFormattedNumber(getRandomInstance().nextDouble(MIN_SPACESHIP_COST, MAX_SPACESHIP_COST));
        var fuelCapacity = getRandomInstance().nextInt(MIN_FUEL_CAPACITY, MAX_FUEL_CAPACITY);
        var speed = createSpeed(price);

        var fuelUsagePerLightYear = createFuelUsagePerLightYear(fuelCapacity, speed, price);
        var volumeCapacity = getRandomInstance().nextInt(MIN_VOLUME, MAX_VOLUME);


        return new SpaceShip.Builder()
                .setName(create(NameType.SpaceShip, i + 1))
                .setFuelCapacity(fuelCapacity)
                .setFuelUsagePerLightYear(fuelUsagePerLightYear)
                .setVolumeCapacity(volumeCapacity)
                .setCurrentFuel(DEFAULT_INIT_FUEL_CAPACITY)
                .setPrice(price)
                .setSpeed(speed)
                .build();
    }
}