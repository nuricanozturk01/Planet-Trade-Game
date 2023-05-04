package nuricanozturk.dev.factory;

import nuricanozturk.dev.entity.SpaceShip;
import nuricanozturk.dev.generator.name.NameGeneratorFactory;
import nuricanozturk.dev.generator.name.NameType;

import java.util.List;
import java.util.stream.IntStream;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.util.Constants.*;

public final class SpaceshipFactory
{
    private SpaceshipFactory()
    {
    }

    public static List<SpaceShip> createSpaceships()
    {
        return IntStream
                .range(0, getRandomInstance().nextInt(MIN_SPACESHIP_COUNT, MAX_SPACESHIP_COUNT))
                .mapToObj(SpaceshipFactory::createSpaceShip)
                .toList();
    }

    private static SpaceShip createSpaceShip(int i)
    {
        return new SpaceShip.Builder()
                .setName(NameGeneratorFactory.create(NameType.SpaceShip, i + 1))
                .setPrice(getRandomInstance().nextDouble(MIN_SPACESHIP_COST, MAX_SPACESHIP_COST))
                .setFuelCapacity(1)
                .setFuelUsagePerLightYear(1)
                .setVolumeCapacity(1)
                .build();
    }
}