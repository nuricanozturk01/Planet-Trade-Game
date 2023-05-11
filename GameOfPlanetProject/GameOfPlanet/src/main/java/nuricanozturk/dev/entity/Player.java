package nuricanozturk.dev.entity;

import nuricanozturk.dev.action.BuySpaceship;
import nuricanozturk.dev.action.IAction;
import project.gameengine.base.Action;
import project.gameengine.base.GameContext;

import java.util.List;
import java.util.Optional;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.util.Util.getBigFormattedNumber;

public class Player implements project.gameengine.base.Player
{
    private final String m_name;
    private double m_currentMoney;
    private SpaceShip m_spaceShip;
    private Planet m_currentPlanet;

    public Player(String name, double initPrice)
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
        var actions = ((InitGameContext) context).getActions();
        var action = Optional.of((IAction) actions.get(getRandomInstance().nextInt(0, actions.size())));

        action.get().apply(this);

        return action.get();
    }


    private void buySpaceship(List<SpaceShip> spaceships)
    {
        var spaceship = spaceships.stream().filter(this::select).findAny();

        if (spaceship.isPresent())
        {
            setSpaceShip(spaceship.get());
            setCurrentMoney(getCurrentMoney() - spaceship.get().getPrice());

            IAction action = new BuySpaceship(spaceship.get(), this);
            action.apply(this);
        }
    }

    @Override
    @SuppressWarnings("all") // Planets cannot be null
    public void prepareForGame(GameContext context)
    {
        var planets = ((InitGameContext) context).getPlanets();
        var spaceships = ((InitGameContext) context).getSpaceShips();

        setCurrentPlanet(planets.stream().findAny().get());

        buySpaceship(spaceships);
    }

    private boolean select(SpaceShip spaceShip)
    {
        return m_currentMoney >= spaceShip.getPrice() && !spaceShip.isSold();
    }
}