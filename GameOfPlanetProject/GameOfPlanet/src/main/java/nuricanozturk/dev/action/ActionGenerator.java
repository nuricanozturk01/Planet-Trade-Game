package nuricanozturk.dev.action;

import nuricanozturk.dev.action.actions.*;
import project.gameengine.base.Action;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;
import static nuricanozturk.dev.util.Util.LOGGER;

public final class ActionGenerator
{
    private final static Action m_buyFuelAction = new BuyFuel();
    private final static Action m_buyItemAction = new BuyItem();
    private final static Action m_buySpaceShipAction = new BuySpaceship();
    private final static Action m_soldItemAction = new SellItem();
    private final static Action m_planTravellingAction = new PlanTravelling();
    private final static Action m_selectPlanetAction = new SelectPlanet();
    private final static Action m_changeSpaceshipAction = new ChangeSpaceship();
    private static ActionGenerator m_actionGenerator;

    private final ActionType[] m_actionTypes;

    private ActionGenerator()
    {
        m_actionTypes = ActionType.values();
        LOGGER.log("\n");
    }

    public static ActionGenerator getActionGeneratorInstance()
    {
        return m_actionGenerator == null ? new ActionGenerator() : m_actionGenerator;
    }

    public static Action getBuySpaceshipAction()
    {
        return m_buySpaceShipAction;
    }

    public Action getActionByActionType(ActionType actionType)
    {
        return getAction(actionType);
    }

    //------------------------------------------------------------------------------------------
    private ActionType getRandomActionType()
    {
        return m_actionTypes[getRandomInstance().nextInt(0, m_actionTypes.length)];
    }

    public Action getChangeSpaceshipAction()
    {
        return m_changeSpaceshipAction;
    }

    public Action getRandomAction()
    {
        return getAction(getRandomActionType());
    }

    public Action getBuySpacehipAction()
    {
        return m_buySpaceShipAction;
    }

    public Action getSelectPlanetAction()
    {
        return m_selectPlanetAction;
    }

    public Action getAction(ActionType actionType)
    {
        return switch (actionType)
        {
            case BUY_FUEL -> m_buyFuelAction;
            case BUY_ITEM -> m_buyItemAction;
            case SOLD_ITEM -> m_soldItemAction;
            case CHANGE_SPACESHIP -> m_changeSpaceshipAction;

            default -> m_planTravellingAction;
        };
    }
}