package nuricanozturk.dev.entity;

import project.gameengine.base.Action;
import project.gameengine.base.GameContext;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;

public class Player implements project.gameengine.base.Player
{
    private final String m_name;
    private double m_currentMoney;
    private SpaceShip m_spaceShip;
    private Planet m_currentPlanet;

    public Player(String name, double currentMoney, SpaceShip spaceShip, Planet currentPlanet)
    {
        m_name = name;
        m_currentMoney = currentMoney;
        m_spaceShip = spaceShip;
        m_currentPlanet = currentPlanet;
    }

    public Player(String name, Planet currentPlanet)
    {
        this(name, 0.0, null, currentPlanet);
    }

    public Player(String name)
    {
        this(name, null);
    }

    @Override
    public String getName()
    {
        return m_name;
    }

    public double getCurrentMoney()
    {
        return m_currentMoney;
    }

    public void setCurrentMoney(double currentMoney)
    {
        m_currentMoney = currentMoney;
    }

    public SpaceShip getSpaceShip()
    {
        return m_spaceShip;
    }

    public void setSpaceShip(SpaceShip spaceShip)
    {
        m_spaceShip = spaceShip;
    }

    public Planet getCurrentPlanet()
    {
        return m_currentPlanet;
    }

    public void setCurrentPlanet(Planet currentPlanet)
    {
        m_currentPlanet = currentPlanet;
    }

    @Override
    public Action play(GameContext context)
    {
        throw new UnsupportedOperationException("TODO: ");
    }
    @Override
    public void prepareForGame(GameContext context)
    {
        var planets = ((Galaxy) context).getPlanets();
        setCurrentPlanet(planets.get(getRandomInstance().nextInt(0, planets.size())));
    }
}
