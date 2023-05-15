package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;

import java.util.ArrayList;
import java.util.List;

import static nuricanozturk.dev.util.Util.LOGGER;

public class Galaxy implements IGalaxy, GameContext
{
    private final String m_name;
    private final List<Planet> m_planets;
    private final int m_planetCount;

    public Galaxy(String name, int planetCount)
    {
        m_name = name;
        m_planets = new ArrayList<>();
        m_planetCount = planetCount;
    }

    public String getName()
    {
        return m_name;
    }

    public int getPlanetCount()
    {
        return m_planetCount;
    }

    @Override
    public void addPlanet(Planet planet)
    {
        m_planets.add(planet);
    }

    public List<Planet> getPlanets()
    {
        return m_planets;
    }


}
