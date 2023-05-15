package nuricanozturk.dev.entity;

import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class InitGameContext implements GameContext
{
    private final List<SpaceShip> m_spaceShips;
    private final Galaxy m_galaxy;
    private final List<Planet> m_planets;

    public InitGameContext(List<SpaceShip> spaceShips, Galaxy galaxy, List<Planet> planets)
    {
        m_spaceShips = spaceShips;
        m_galaxy = galaxy;
        m_planets = planets;
    }

    public void init(List<Player> players)
    {
        //other init contexts on constructor
        players.forEach(p -> p.prepareForGame(this));
    }

    public List<SpaceShip> getSpaceShips()
    {
        return m_spaceShips;
    }

    public Galaxy getGalaxy()
    {
        return m_galaxy;
    }

    public List<Planet> getPlanets()
    {
        return m_planets;
    }

    public void updateTurn()
    {
        // updated each turn... R-(20, 21, 22)
    }
}