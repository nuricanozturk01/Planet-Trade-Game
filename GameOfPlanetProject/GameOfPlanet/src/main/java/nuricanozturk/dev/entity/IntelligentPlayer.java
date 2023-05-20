package nuricanozturk.dev.entity;

import nuricanozturk.dev.action.IAction;
import nuricanozturk.dev.action.actions.BuyItem;
import project.gameengine.base.Action;
import project.gameengine.base.GameContext;

import java.util.Comparator;

import static java.util.Comparator.comparingDouble;
import static java.util.Comparator.comparingInt;
import static nuricanozturk.dev.util.Util.LOGGER;

public class IntelligentPlayer extends PlayerImpl
{
    public IntelligentPlayer(String name, double initPrice)
    {
        super(name, initPrice);
    }

    @Override
    public Action play(GameContext context)
    {
        var action = (IAction) decideAction();

        action.apply(this, context);

        return action;
    }

    private Action decideAction()
    {
        return new BuyItem();
    }

    @Override
    public void prepareForGame(GameContext context)
    {
        selectPlanet(context);

        buySpaceship(context);

        LOGGER.log("Intelligent Player " + getName() + " is prepared for game");
        LOGGER.log("---- Current Planet: " + getCurrentPlanet() + ", SpaceShip: " + getSpaceShip());
    }

    @Override
    protected void buySpaceship(GameContext context)
    {
        var spaceships = ((PlanetTradeGameContext) context).getSpaceShips();

        var selectedSpaceship = spaceships.stream()
                .filter(s -> !s.isSold())
                .sorted(comparingDouble(SpaceShip::getPrice))
                .sorted(comparingInt(SpaceShip::getFuelCapacity).reversed())
                .min(comparingDouble(SpaceShip::getFuelUsagePerLightYear))
                .get();

        setSpaceShip(selectedSpaceship);
    }

    @Override
    protected void selectPlanet(GameContext context)
    {
        var planets = ((PlanetTradeGameContext) context).getPlanets();

        var selectedPlanet = planets.stream()
                .sorted(comparingDouble(Planet::getParkingPricePerTurn))
                .min(comparingDouble(Planet::getUnitFuelPrice))
                .get();

        setCurrentPlanet(selectedPlanet);
    }
}
