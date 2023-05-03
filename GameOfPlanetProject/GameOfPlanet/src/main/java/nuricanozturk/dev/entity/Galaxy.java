package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;

import java.util.ArrayList;
import java.util.List;
public class Galaxy implements GameContext
{

    private final List<Planet> m_planets;

    public Galaxy()
    {
        m_planets = new ArrayList<>();
    }

    public void addPlanet(Planet planet)
    {
        m_planets.add(planet);
    }

    public List<Planet> getPlanets()
    {
        return m_planets;
    }

    /*
    A galaxy contains a set of planets that are certain distance away (in terms of lightyear) from each other.

     */
}
