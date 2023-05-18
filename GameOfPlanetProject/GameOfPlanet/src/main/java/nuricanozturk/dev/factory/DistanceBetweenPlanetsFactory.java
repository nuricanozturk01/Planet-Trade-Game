package nuricanozturk.dev.factory;

import nuricanozturk.dev.entity.Planet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;

public final class DistanceBetweenPlanetsFactory
{
    private DistanceBetweenPlanetsFactory()
    {
    }

    private static Map<Planet, Integer> createDistances(List<Planet> planets, Planet currentPlanet)
    {
        return planets.stream()
                .filter(p -> !p.getName().equals(currentPlanet.getName()))
                .collect(HashMap::new, DistanceBetweenPlanetsFactory::addToMap, HashMap::putAll);
    }

    private static void addToMap(Map<Planet, Integer> map, Planet planet)
    {
        map.put(planet, getRandomInstance().nextInt(0, 150));
    }

    public static void createDistanceBetweenPlanets(List<Planet> planets, Planet currentPlanet)
    {
        var distancesBetweenPlanets = createDistances(planets, currentPlanet);

        currentPlanet.setDistanceMap(distancesBetweenPlanets);
    }
}
