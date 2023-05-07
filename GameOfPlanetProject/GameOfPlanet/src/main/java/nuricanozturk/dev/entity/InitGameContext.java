package nuricanozturk.dev.entity;

import nuricanozturk.dev.action.BuyItem;
import nuricanozturk.dev.action.IAction;
import nuricanozturk.dev.action.SoldItem;
import project.gameengine.base.Action;
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
    private final List<Action> m_actions;


    public InitGameContext(List<SpaceShip> spaceShips, Galaxy galaxy, List<Planet> planets, List<Action> actionList)
    {
        m_actions = actionList;
        m_spaceShips = spaceShips;
        m_galaxy = galaxy;
        m_planets = planets;
    }

    public List<Action> getActions()
    {
        return m_actions;
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



    public void buyCommodities(Action action)
    {

    }

    public void sellAnyCargo()
    {
        throw new UnsupportedOperationException("TODO: ");
    }

    public void buyFuel()
    {
        throw new UnsupportedOperationException("TODO: ");
    }

    public void planNextJourney()
    {
        throw new UnsupportedOperationException("TODO: ");
    }

    public void playTurn(Action action, List<Player> players, List<Action> actionList)
    {

        buyCommodities(action);
        sellAnyCargo();
        buyFuel();
        planNextJourney();
    }
}