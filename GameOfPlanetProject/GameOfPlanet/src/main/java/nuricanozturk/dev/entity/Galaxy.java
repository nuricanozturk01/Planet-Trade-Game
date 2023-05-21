package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;

import java.util.ArrayList;
import java.util.List;

public class Galaxy implements IGalaxy, GameContext
{
    private final String m_name;
    private final List<Planet> m_planets;

    public Galaxy(String name)
    {
        m_name = name;
        m_planets = new ArrayList<>();
    }

    public String getName()
    {
        return m_name;
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
