package nuricanozturk.dev.entity;

import nuricanozturk.dev.action.IAction;
import nuricanozturk.dev.util.Util;
import project.gameengine.base.Action;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import static nuricanozturk.dev.action.ActionGenerator.getActionGeneratorInstance;
import static nuricanozturk.dev.util.Util.LOGGER;
import static nuricanozturk.dev.util.Util.getBigFormattedNumber;

public class PlayerImpl implements Player
{
    private final String m_name;
    private double m_currentMoney;
    private SpaceShip m_spaceShip;
    private Planet m_currentPlanet;
    private Action m_currentAction;

    public PlayerImpl(String name, double initPrice)
    {
        m_name = name;
        m_currentMoney = initPrice;
        LOGGER.log("Player " + name + " is created...");
        LOGGER.log(String.format("---[%s]", this));
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
        var action = (IAction) Util.actions.next();
        action.apply(this, context);
        return action;
    }

    private void buySpaceship(GameContext context)
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
                ", m_spaceShip=" + m_spaceShip +
                ", m_currentPlanet=" + m_currentPlanet +
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

    private void selectPlanet(GameContext context)
    {
        var setPlanetAction = (IAction) getActionGeneratorInstance().getSelectPlanetAction();
        setPlanetAction.apply(this, context);
    }
}