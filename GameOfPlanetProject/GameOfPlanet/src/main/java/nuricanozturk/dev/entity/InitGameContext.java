package nuricanozturk.dev.entity;

import nuricanozturk.dev.action.IAction;
import nuricanozturk.dev.util.LinkedList;
import project.gameengine.base.Action;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import java.util.List;

import static nuricanozturk.dev.action.ActionGenerator.getActionGeneratorInstance;

/*
    Wrapper class for GameOfPlanet

 */
public class InitGameContext implements GameContext
{
    private final List<SpaceShip> m_spaceShips;
    private final Galaxy m_galaxy;
    private final List<Planet> m_planets;
    private Action currentAction;
    //private final LinkedList<Action> actions = getActionGeneratorInstance().getActionLinkedList();

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

   /* public IAction getAction()
    {
        return ((IAction) actions.next());
    }*/

    public List<SpaceShip> getSpaceShips()
    {
        return m_spaceShips;
    }

    public Galaxy getGalaxy()
    {
        return m_galaxy;
    }
    public Action getCurrentAction()
    {
        return currentAction;
    }
    public List<Planet> getPlanets()
    {
        return m_planets;
    }

    public void setAction(Action action)
    {
        currentAction = action;
    }
}