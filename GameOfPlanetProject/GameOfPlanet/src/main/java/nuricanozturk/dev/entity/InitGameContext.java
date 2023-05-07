package nuricanozturk.dev.entity;

import nuricanozturk.dev.action.BuyItem;
import nuricanozturk.dev.action.IAction;
import nuricanozturk.dev.action.SoldItem;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.List;

/*
    Wrapper class for GameOfPlanet
 */
public class InitGameContext implements GameContext
{
    private final List<SpaceShip> m_spaceShips;
    private final Galaxy m_galaxy;
    private final List<Planet> m_planets;
    private final IAction m_buyAction;
    private final IAction m_soldAction;

    public InitGameContext(List<SpaceShip> spaceShips, Galaxy galaxy, List<Planet> planets)
    {
        m_buyAction = new BuyItem();
        m_soldAction = new SoldItem();
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

    public void updateItems()
    {
    }
}