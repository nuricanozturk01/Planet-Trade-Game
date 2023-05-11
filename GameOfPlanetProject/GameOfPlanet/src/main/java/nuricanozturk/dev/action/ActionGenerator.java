package nuricanozturk.dev.action;

import project.gameengine.base.Action;

import static nuricanozturk.dev.config.RandomConfig.getRandomInstance;

public class ActionGenerator
{
    private static ActionGenerator m_actionGenerator;
    private final ActionType[] m_actionTypes;
    private final Action m_buyFuelAction, m_buyItemAction, m_buySpaceShipAction,
            m_soldItemAction, m_planTravellingAction, m_selectPlanetAction;

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

    public static ActionGenerator getActionGeneratorInstance()
    {
        return m_actionGenerator == null ? new ActionGenerator() : m_actionGenerator;
    }
//------------------------------------------------------------------------------------------
    private ActionType getRandomActionType()
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