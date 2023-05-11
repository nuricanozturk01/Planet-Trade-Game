package nuricanozturk.dev.action;

import project.gameengine.base.Action;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;

public class ActionGenerator
{
    private static ActionGenerator m_actionGenerator;
    private final Action m_buyFuelAction;
    private final Action m_buyItemAction;
    private final Action m_buySpaceShipAction;
    private final Action m_soldItemAction;
    private final Action m_planTravellingAction;
    private final Action m_selectPlanetAction;
    private final ActionType[] m_actionTypes;

    private ActionGenerator()
    {
        m_selectPlanetAction = new SelectPlanet();
        m_actionTypes = ActionType.values();
        m_buyFuelAction = new BuyFuel();
        m_buyItemAction = new BuyItem();
        m_buySpaceShipAction = new BuySpaceship();
        m_soldItemAction = new SoldItem();
        m_planTravellingAction = new PlanTravelling();
    }

    public ActionType getRandomActionType()
    {
        return m_actionTypes[getRandomInstance().nextInt(0, m_actionTypes.length)];
    }

    public Action getRandomAction()
    {
        return getAction(getRandomActionType());
    }

    public Action getBuySpacehipAction()
    {
        return m_buySpaceShipAction;
    }
    public static ActionGenerator getActionGenerator()
    {
        return m_actionGenerator == null ? new ActionGenerator() : m_actionGenerator;
    }

    public Action getAction(ActionType actionType)
    {
        return switch (actionType)
        {
            case BUY_FUEL -> m_buyFuelAction;
            case BUY_ITEM -> m_buyItemAction;
            case SOLD_ITEM -> m_soldItemAction;
            case SELECT_PLANET -> m_selectPlanetAction;

            default -> m_planTravellingAction;
        };
    }
}
