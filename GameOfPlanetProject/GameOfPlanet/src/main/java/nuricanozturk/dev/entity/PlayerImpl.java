package nuricanozturk.dev.entity;

import nuricanozturk.dev.action.IAction;
import project.gameengine.NullAction;
import project.gameengine.base.Action;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import static nuricanozturk.dev.action.ActionGenerator.getActionGeneratorInstance;
import static nuricanozturk.dev.util.Util.*;

public class PlayerImpl implements Player
{
    private final String m_name;
    private double m_currentMoney;
    private ISpaceship m_spaceShip;
    private Planet m_currentPlanet;

    public PlayerImpl(String name, double initPrice)
    {
        m_name = name;
        m_currentMoney = initPrice;
        LOGGER.log("Player " + name + " is created...");
        LOGGER.log(String.format("---[%s]\n", this));
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
        m_currentMoney = getBigFormattedNumber(currentMoney);
    }

    public SpaceShip getSpaceShip()
    {
        return (SpaceShip) m_spaceShip;
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

        var action = (IAction) getActionGeneratorInstance().getRandomAction();

        action.apply(this, context);

        return action;
    }

    protected void buySpaceship(GameContext context)
    {
        var action = (IAction) getActionGeneratorInstance().getBuySpacehipAction();

        action.apply(this, context);
    }

    @Override
    public String toString()
    {
        return "PlayerImpl{" +
                "m_name='" + m_name + '\'' +
                ", m_currentMoney=" + m_currentMoney +
                "m_spaceShip=" + m_spaceShip +
                "m_currentPlanet=" + m_currentPlanet +
                '}';
    }

    @Override
    @SuppressWarnings("all") // Planets cannot be null
    public void prepareForGame(GameContext context)
    {
        selectPlanet(context);

        buySpaceship(context);

        LOGGER.log("Player " + m_name + " is prepared for game");
        LOGGER.log("---- Current Planet: " + m_currentPlanet + ", SpaceShip: " + m_spaceShip);
    }

    protected void selectPlanet(GameContext context)
    {
        var setPlanetAction = (IAction) getActionGeneratorInstance().getSelectPlanetAction();
        setPlanetAction.apply(this, context);
    }
}