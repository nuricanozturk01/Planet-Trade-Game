package nuricanozturk.dev.factory;

import nuricanozturk.dev.entity.Planet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.util.Constants.MAX_DISTANCE_BETWEEN_PLANETS;
import static nuricanozturk.dev.util.Constants.MIN_DISTANCE_BETWEEN_PLANETS;

public final class DistanceBetweenPlanetsFactory
{
    private DistanceBetweenPlanetsFactory()
    {
    }

    private static Map<Planet, Integer> createDistances(List<Planet> planets, Planet currentPlanet)
    {
        return planets.stream().filter(p -> !p.getName().equals(currentPlanet.getName()))
                .collect(HashMap::new, DistanceBetweenPlanetsFactory::addToMap, HashMap::putAll);
    }

    private static void addToMap(Map<Planet, Integer> map, Planet planet)
    {
        map.put(planet, getRandomInstance().nextInt(MIN_DISTANCE_BETWEEN_PLANETS, MAX_DISTANCE_BETWEEN_PLANETS));
    }

    public static void createDistanceBetweenPlanets(List<Planet> planets, Planet currentPlanet)
    {
        currentPlanet.setDistanceMap(createDistances(planets, currentPlanet));
    }
}
