package nuricanozturk.dev.entity;

import nuricanozturk.dev.action.ActionType;
import nuricanozturk.dev.action.IAction;
import project.gameengine.base.Action;
import project.gameengine.base.GameContext;
import project.gameengine.base.Player;

import static nuricanozturk.dev.action.ActionGenerator.getActionGenerator;
import static nuricanozturk.dev.action.ActionType.BUY_SPACESHIP;
import static nuricanozturk.dev.util.Util.getBigFormattedNumber;

public class PlayerImpl implements Player
{
    private final String m_name;
    private double m_currentMoney;
    private SpaceShip m_spaceShip;
    private Planet m_currentPlanet;

    public PlayerImpl(String name, double initPrice)
    {
        m_name = name;
        m_currentMoney = initPrice;
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
        var action = (IAction) getActionGenerator().getRandomAction();
        action.apply(this, context);
        return action;
    }

    private void buySpaceship(GameContext context)
    {
        var action = (IAction) getActionGenerator().getBuySpacehipAction();

        action.apply(this, context);

    }

    @Override
    @SuppressWarnings("all") // Planets cannot be null
    public void prepareForGame(GameContext context)
    {
        selectPlanet(context);

        buySpaceship(context);

        System.out.println(m_name + " is " + m_spaceShip.getName());
    }

    private void selectPlanet(GameContext context)
    {
        var setPlanetAction = (IAction) getActionGenerator().getAction(ActionType.SELECT_PLANET);
        setPlanetAction.apply(this, context);
    }
}