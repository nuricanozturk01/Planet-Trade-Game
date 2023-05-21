package nuricanozturk.dev.entity;

import nuricanozturk.dev.action.ActionType;
import nuricanozturk.dev.action.IAction;
import project.gameengine.NullAction;
import project.gameengine.base.Action;
import project.gameengine.base.GameContext;

import java.util.ArrayList;
import java.util.Map;

import static java.util.Comparator.comparingDouble;
import static java.util.Comparator.comparingInt;
import static nuricanozturk.dev.action.ActionGenerator.getActionGeneratorInstance;
import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.util.Constants.*;
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
        var action = decideAction();

        if (action instanceof NullAction)
            new NullAction();

        else ((IAction) action).apply(this, context);

        return action;
    }

    private Action decideAction()
    {
        var possibleActions = new ArrayList<Action>();
        var currentMoney = getCurrentMoney();
        var currentFuel = getSpaceShip().getCurrentFuel();
        var currentPlanet = getCurrentPlanet();
        var actionGenerator = getActionGeneratorInstance();

        if (currentMoney <= MIN_COMMODITY_UNIT_BUY_PRICE && getSpaceShip().getCargos().isEmpty())
            return actionGenerator.getActionByActionType(ActionType.NO_ACTION);

        if (currentMoney <= MIN_COMMODITY_UNIT_BUY_PRICE && !getSpaceShip().getCargos().isEmpty())
            possibleActions.add(actionGenerator.getActionByActionType(ActionType.SOLD_ITEM));

        if (currentMoney >= MIN_COMMODITY_UNIT_BUY_PRICE + 150)
            possibleActions.add(actionGenerator.getActionByActionType(ActionType.BUY_ITEM));

        if (currentMoney <= MIN_COMMODITY_UNIT_BUY_PRICE / 10 || currentMoney <= MIN_UNIT_FUEL_PRICE)
            possibleActions.add(actionGenerator.getActionByActionType(ActionType.NO_ACTION));

        if (currentMoney >= MIN_SPACESHIP_COST + 150 && getRandomInstance().nextDouble() > 0.1 && getRandomInstance().nextDouble() <= 0.6)
            possibleActions.add(actionGenerator.getChangeSpaceshipAction());

        var applicablePlanetEntry = getTheApplicablePlanet();
        var fuelUsageForTravel = costTravelToPlanet(applicablePlanetEntry.getValue());
        var costForTravel = fuelUsageForTravel * currentPlanet.getUnitFuelPrice();

        if (fuelUsageForTravel <= currentFuel)
            possibleActions.add(actionGenerator.getActionByActionType(ActionType.PLAN_TRAVELLING));

        if (currentMoney >= costForTravel)
            possibleActions.add(actionGenerator.getActionByActionType(ActionType.BUY_FUEL));

        return possibleActions.get(getRandomInstance().nextInt(possibleActions.size()));
    }


    private double costTravelToPlanet(int distance)
    {
        return getSpaceShip().getFuelUsagePerLightYear() * distance;
    }

    private Map.Entry<Planet, Integer> getTheApplicablePlanet()
    {
        return getCurrentPlanet().getDistanceMap().entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .findFirst()
                .get();
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